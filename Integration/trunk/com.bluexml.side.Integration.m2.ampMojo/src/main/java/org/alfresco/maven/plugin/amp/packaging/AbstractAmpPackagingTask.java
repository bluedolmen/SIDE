package org.alfresco.maven.plugin.amp.packaging;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import org.alfresco.maven.plugin.amp.AbstractAmpMojo;
import org.alfresco.maven.plugin.amp.util.AmpStructure;
import org.alfresco.maven.plugin.amp.util.MappingUtils;
import org.alfresco.maven.plugin.amp.util.PathSet;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.archiver.ArchiverException;
import org.codehaus.plexus.archiver.UnArchiver;
import org.codehaus.plexus.archiver.manager.NoSuchArchiverException;
import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.IOUtil;
import org.codehaus.plexus.util.InterpolationFilterReader;

/**
 * @author Stephane Nicoll
 */
public abstract class AbstractAmpPackagingTask
    implements AmpPackagingTask
{
    public static final String[] DEFAULT_INCLUDES = {"**/**"};

    
    public static final String META_INF_PATH = "META-INF";

    
    /**
     * Copies the files if possible with an optional target prefix.
     * <p/>
     * Copy uses a first-win strategy: files that have already been copied by previous
     * tasks are ignored. This method makes sure to update the list of protected files
     * which gives the list of files that have already been copied.
     * <p/>
     * If the structure of the source directory is not the same as the root of the
     * webapp, use the <tt>targetPrefix</tt> parameter to specify in which particular
     * directory the files should be copied. Use <tt>null</tt> to copy the files with
     * the same structure
     *
     * @param sourceId       the source id
     * @param context        the context to use
     * @param sourceBaseDir  the base directory from which the <tt>sourceFilesSet</tt> will be copied
     * @param sourceFilesSet the files to be copied
     * @param targetPrefix   the prefix to add to the target file name
     * @throws IOException if an error occured while copying the files
     */
    protected void copyFiles( String sourceId, AmpPackagingContext context, File sourceBaseDir, PathSet sourceFilesSet,
                              String targetPrefix )
        throws IOException
    {
        for ( Iterator iter = sourceFilesSet.iterator(); iter.hasNext(); )
        {
            final String fileToCopyName = (String) iter.next();
            final File sourceFile = new File( sourceBaseDir, fileToCopyName );

            String destinationFileName;
            if ( targetPrefix == null )
            {
                destinationFileName = fileToCopyName;
            }
            else
            {
                destinationFileName = targetPrefix + fileToCopyName;
            }

            copyFile( sourceId, context, sourceFile, destinationFileName );
        }
    }

    /**
     * Copies the files if possible as is.
     * <p/>
     * Copy uses a first-win strategy: files that have already been copied by previous
     * tasks are ignored. This method makes sure to update the list of protected files
     * which gives the list of files that have already been copied.
     *
     * @param sourceId       the source id
     * @param context        the context to use
     * @param sourceBaseDir  the base directory from which the <tt>sourceFilesSet</tt> will be copied
     * @param sourceFilesSet the files to be copied
     * @throws IOException if an error occured while copying the files
     */
    protected void copyFiles( String sourceId, AmpPackagingContext context, File sourceBaseDir, PathSet sourceFilesSet )
        throws IOException
    {
        copyFiles( sourceId, context, sourceBaseDir, sourceFilesSet, null );
    }

    /**
     * Copy the specified file if the target location has not yet already been used.
     * <p/>
     * The <tt>targetFileName</tt> is the relative path according to the root of
     * the generated web application.
     *
     * @param sourceId       the source id
     * @param context        the context to use
     * @param file           the file to copy
     * @param targetFilename the relative path according to the root of the webapp
     * @throws IOException if an error occured while copying
     */
    protected void copyFile( String sourceId, final AmpPackagingContext context, final File file,
                             String targetFilename )
        throws IOException
    {
        final File targetFile = new File( context.getAmpDirectory(), targetFilename );
        context.getAmpStructure().registerFile( sourceId, targetFilename, new AmpStructure.RegistrationCallback()
        {
            public void registered( String ownerId, String targetFilename )
                throws IOException
            {
                copyFile( context, file, targetFile, targetFilename, false );
            }

            public void alreadyRegistered( String ownerId, String targetFilename )
                throws IOException
            {
                copyFile( context, file, targetFile, targetFilename, true );
            }

            public void refused( String ownerId, String targetFilename, String actualOwnerId )
                throws IOException
            {
                context.getLog().debug( " - " + targetFilename + " wasn't copied because it has " +
                    "already been packaged for overlay[" + actualOwnerId + "]." );
            }

            public void superseded( String ownerId, String targetFilename, String deprecatedOwnerId )
                throws IOException
            {
                context.getLog().info( "File[" + targetFilename + "] belonged to overlay[" + deprecatedOwnerId +
                    "] so it will be overwritten." );
                copyFile( context, file, targetFile, targetFilename, false );
            }

            public void supersededUnknownOwner( String ownerId, String targetFilename, String unknownOwnerId )
                throws IOException
            {
                context.getLog().warn( "File[" + targetFilename + "] belonged to overlay[" + unknownOwnerId +
                    "] which does not exist anymore in the current project. It is recommended to invoke " +
                    "clean if the dependencies of the project changed." );
                copyFile( context, file, targetFile, targetFilename, false );
            }
        } );
    }

    /**
     * Copy the specified file if the target location has not yet already been
     * used and filter its content with the configureed filter properties.
     * <p/>
     * The <tt>targetFileName</tt> is the relative path according to the root of
     * the generated web application.
     *
     * @param sourceId       the source id
     * @param context        the context to use
     * @param file           the file to copy
     * @param targetFilename the relative path according to the root of the webapp
     * @return true if the file has been copied, false otherwise
     * @throws IOException            if an error occured while copying
     * @throws MojoExecutionException if an error occured while retrieving the filter properties
     */
    protected boolean copyFilteredFile( String sourceId, AmpPackagingContext context, File file, String targetFilename )
        throws IOException, MojoExecutionException
    {

        if ( context.getAmpStructure().registerFile( sourceId, targetFilename ) )
        {
            final File targetFile = new File( context.getAmpDirectory(), targetFilename );
            // buffer so it isn't reading a byte at a time!
            Reader fileReader = null;
            Writer fileWriter = null;
            try
            {
                // fix for MWAR-36, ensures that the parent dir are created first
                targetFile.getParentFile().mkdirs();

                fileReader = new BufferedReader( new FileReader( file ) );
                fileWriter = new FileWriter( targetFile );

                Reader reader = fileReader;
                for ( int i = 0; i < getFilterWrappers().length; i++ )
                {
                    FilterWrapper wrapper = getFilterWrappers()[i];
                    reader = wrapper.getReader( reader, context.getFilterProperties() );
                }

                IOUtil.copy( reader, fileWriter );
            }
            finally
            {
                IOUtil.close( fileReader );
                IOUtil.close( fileWriter );
            }
            // Add the file to the protected list
            context.getLog().debug( " + " + targetFilename + " has been copied." );
            return true;
        }
        else
        {
            context.getLog().debug( " - " + targetFilename + " wasn't copied because it has already been packaged." );
            return false;
        }
    }


    /**
     * Unpacks the specified file to the specified directory.
     *
     * @param context         the packaging context
     * @param file            the file to unpack
     * @param unpackDirectory the directory to use for th unpacked file
     * @throws MojoExecutionException if an error occured while unpacking the file
     */
    protected void doUnpack( AmpPackagingContext context, File file, File unpackDirectory )
        throws MojoExecutionException
    {
        String archiveExt = FileUtils.getExtension( file.getAbsolutePath() ).toLowerCase();
        
        // Uncompressing an AMP into another AMP does not require any 
        // special treatment so we just use a zip unarchiver 
        if ("amp".equals(archiveExt))
        {
        	archiveExt = "zip";
        }
        
        try
        {
            UnArchiver unArchiver = context.getArchiverManager().getUnArchiver( archiveExt );
            unArchiver.setSourceFile( file );
            unArchiver.setDestDirectory( unpackDirectory );
            unArchiver.setOverwrite( true );
            unArchiver.extract();
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error unpacking file[" + file.getAbsolutePath() + "]" + "to[" +
                unpackDirectory.getAbsolutePath() + "]", e );
        }
        catch ( ArchiverException e )
        {
            throw new MojoExecutionException( "Error unpacking file[" + file.getAbsolutePath() + "]" + "to[" +
                unpackDirectory.getAbsolutePath() + "]", e );
        }
        catch ( NoSuchArchiverException e )
        {
            context.getLog().warn( "Skip unpacking dependency file[" + file.getAbsolutePath() +
                " with unknown extension[" + archiveExt + "]" );
        }
    }

    /**
     * Copy file from source to destination. The directories up to <code>destination</code>
     * will be created if they don't already exist. if the <code>onlyIfModified</code> flag
     * is <tt>false</tt>, <code>destination</code> will be overwritten if it already exists. If the
     * flag is <tt>true</tt> destination will be overwritten if it's not up to date.
     * <p/>
     *
     * @param context        the packaging context
     * @param source         an existing non-directory <code>File</code> to copy bytes from
     * @param destination    a non-directory <code>File</code> to write bytes to (possibly overwriting).
     * @param targetFilename the relative path of the file from the webapp root directory
     * @param onlyIfModified if true, copy the file only if the source has changed, always copy otherwise
     * @return true if the file has been copied/updated, false otherwise
     * @throws IOException if <code>source</code> does not exist, <code>destination</code> cannot
     *                     be written to, or an IO error occurs during copying
     */
    protected boolean copyFile( AmpPackagingContext context, File source, File destination, String targetFilename,
                                boolean onlyIfModified )
        throws IOException
    {
        if ( onlyIfModified && destination.lastModified() >= source.lastModified() )
        {
            context.getLog().debug( " * " + targetFilename + " is up to date." );
            return false;
        }
        else
        {
            FileUtils.copyFile( source.getCanonicalFile(), destination );
            // preserve timestamp
            destination.setLastModified( source.lastModified() );
            context.getLog().debug( " + " + targetFilename + " has been copied." );
            return true;
        }
    }

    /**
     * Returns the file to copy. If the includes are <tt>null</tt> or empty, the
     * default includes are used.
     *
     * @param baseDir  the base directory to start from
     * @param includes the includes
     * @param excludes the excludes
     * @return the files to copy
     */
    protected PathSet getFilesToIncludes( File baseDir, String[] includes, String[] excludes )
    {
        final DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir( baseDir );

        if ( excludes != null )
        {
            scanner.setExcludes( excludes );
        }
        scanner.addDefaultExcludes();

        if ( includes != null && includes.length > 0 )
        {
            scanner.setIncludes( includes );
        }
        else
        {
            scanner.setIncludes( DEFAULT_INCLUDES );
        }

        scanner.scan();

        return new PathSet( scanner.getIncludedFiles() );

    }

    /**
     * Returns the final name of the specified artifact.
     * <p/>
     * If the <tt>outputFileNameMapping</tt> is set, it is used, otherwise
     * the standard naming scheme is used.
     *
     * @param context  the packaging context
     * @param artifact the artifact
     * @return the converted filename of the artifact
     */
    protected String getArtifactFinalName( AmpPackagingContext context, Artifact artifact )
    {
        if ( context.getOutputFileNameMapping() != null )
        {
            return MappingUtils.evaluateFileNameMapping( context.getOutputFileNameMapping(), artifact );
        }

        String classifier = artifact.getClassifier();
        if ( ( classifier != null ) && !( "".equals( classifier.trim() ) ) )
        {
            return MappingUtils.evaluateFileNameMapping( AbstractAmpMojo.DEFAULT_FILE_NAME_MAPPING_CLASSIFIER,
                                                         artifact );
        }
        else
        {
            return MappingUtils.evaluateFileNameMapping( AbstractAmpMojo.DEFAULT_FILE_NAME_MAPPING, artifact );
        }

    }

    private FilterWrapper[] getFilterWrappers()
    {
        return new FilterWrapper[]{
            // support ${token}
            new FilterWrapper()
            {
                public Reader getReader( Reader fileReader, Map filterProperties )
                {
                    return new InterpolationFilterReader( fileReader, filterProperties, "${", "}" );
                }
            },
            // support @token@
            new FilterWrapper()
            {
                public Reader getReader( Reader fileReader, Map filterProperties )
                {
                    return new InterpolationFilterReader( fileReader, filterProperties, "@", "@" );
                }
            }};
    }

    private interface FilterWrapper
    {
        Reader getReader( Reader fileReader, Map filterProperties );
    }

}
