package com.bluexml.side.Integration.m2.ampMojo;

import java.io.File;
import java.io.IOException;

import org.alfresco.plexus.archiver.AmpArchiver;
import org.apache.maven.archiver.MavenArchiver;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProjectHelper;
import org.codehaus.plexus.archiver.ArchiverException;
import org.codehaus.plexus.archiver.jar.ManifestException;
import org.codehaus.plexus.util.FileUtils;

/**
 * Build a amp/webapp.
 * <p>
 * Note this is a modification of Emmanuel Venisse's (evenisse@apache.org) WAR
 * Mojo and has been adapted to build an Alfresco AMP.
 * @version $Id:$
 * @goal amp
 * @phase package
 * @requiresDependencyResolution runtime
 */
public class AmpWithAttachementMojo extends AbstractAmpMojo {
	/* ====================================================================== */
	// constructors
	/* ====================================================================== */

	/**
	 * default constructor
	 */
	public AmpWithAttachementMojo() {
		this.setAmpArchiver(null);
		this.setAmpName(null);
		this.setOutputDirectory(null);
	}

	/* ====================================================================== */
	// properties
	/* ====================================================================== */

	/**
	 * Whether this is the main artifact being built. Set to <code>false</code>
	 * if you don't want to install or deploy it to the local repository instead
	 * of the default one in an execution.
	 * 
	 * @parameter expression="${primaryArtifact}" default-value="true"
	 */
	private boolean mPrimaryArtifact;

	/**
	 * Whether classes (that is the content of the WEB-INF/classes directory)
	 * should be attached to the project.
	 * 
	 * @parameter default-value="false"
	 * @since 2.1-alpha-2
	 */
	private boolean attachClasses = false;
	/**
     * The classifier to use for the attached classes artifact.
     *
     * @parameter default-value="classes"
     * @since 2.1-alpha-2
     */
    private String classesClassifier = "classes";
	/**
	 * get the the internal value for the <code>ampArchiver</code> property.
	 * <p>
	 * The <code>ampArchiver</code> property
	 * 
	 * @return Returns the internal value for the ampArchiver property.
	 */
	protected AmpArchiver getAmpArchiver() {
		return this.mAmpArchiver;
	}

	/**
	 * set the internal value for the <code>ampArchiver</code> property
	 * 
	 * @param pAmpArchiver
	 *            The <code>ampArchiver</code> to set.
	 */
	protected void setAmpArchiver(AmpArchiver pAmpArchiver) {
		this.mAmpArchiver = pAmpArchiver;
	}

	/**
	 * get the the internal value for the <code>ampName</code> property.
	 * <p>
	 * The <code>ampName</code> property
	 * 
	 * @return Returns the internal value for the ampName property.
	 */
	protected String getAmpName() {
		return this.mAmpName;
	}

	/**
	 * set the internal value for the <code>ampName</code> property
	 * 
	 * @param pAmpName
	 *            The <code>ampName</code> to set.
	 */
	protected void setAmpName(String pAmpName) {
		this.mAmpName = pAmpName;
	}

	/**
	 * get the the internal value for the <code>outputDirectory</code> property.
	 * <p>
	 * The <code>outputDirectory</code> property
	 * 
	 * @return Returns the internal value for the outputDirectory property.
	 */
	protected String getOutputDirectory() {
		return this.mOutputDirectory;
	}

	/**
	 * set the internal value for the <code>outputDirectory</code> property
	 * 
	 * @param pOutputDirectory
	 *            The <code>outputDirectory</code> to set.
	 */
	protected void setOutputDirectory(String pOutputDirectory) {
		this.mOutputDirectory = pOutputDirectory;
	}

	/**
	 * get the the internal value for the <code>primaryArtifact</code> property.
	 * <p>
	 * The <code>primaryArtifact</code> property
	 * 
	 * @return Returns the internal value for the primaryArtifact property.
	 */
	protected boolean isPrimaryArtifact() {
		return this.mPrimaryArtifact;
	}

	/**
	 * set the internal value for the <code>primaryArtifact</code> property
	 * 
	 * @param pPrimaryArtifact
	 *            The <code>primaryArtifact</code> to set.
	 */
	protected void setPrimaryArtifact(boolean pPrimaryArtifact) {
		this.mPrimaryArtifact = pPrimaryArtifact;
	}

	/**
	 * get the the internal value for the <code>projectHelper</code> property.
	 * <p>
	 * The <code>projectHelper</code> property
	 * 
	 * @return Returns the internal value for the projectHelper property.
	 */
	protected MavenProjectHelper getProjectHelper() {
		return this.mProjectHelper;
	}

	/**
	 * set the internal value for the <code>projectHelper</code> property
	 * 
	 * @param pProjectHelper
	 *            The <code>projectHelper</code> to set.
	 */
	protected void setProjectHelper(MavenProjectHelper pProjectHelper) {
		this.mProjectHelper = pProjectHelper;
	}

	/**
	 * Overload this to produce a test-war, for example.
	 */
	protected String getClassifier() {
		return mClassifier;
	}

	/**
	 * set the internal value for the <code>classifier</code> property
	 * 
	 * @param pClassifier
	 *            The <code>classifier</code> to set.
	 */
	protected void setClassifier(String pClassifier) {
		this.mClassifier = pClassifier;
	}

	/* ====================================================================== */
	// public methods
	/* ====================================================================== */

	/**
	 * Executes the WarMojo on the current project.
	 * 
	 * @throws MojoExecutionException
	 *             if an error occured while building the webapp
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {

		File vAmpFile = AmpWithAttachementMojo.getAmpFile(new File(getOutputDirectory()), getAmpName(), getClassifier());

		try {
			this.performPackaging(vAmpFile);
		} catch (Exception eAssemblyFailure) {
			/*
			 * behavior is the same for the following exceptions:
			 * DependencyResolutionRequiredException ManifestException
			 * IOException ArchiverException
			 */
			throw new MojoExecutionException("Error assembling AMP: " + eAssemblyFailure.getMessage(), eAssemblyFailure);
		}
	}

	/* ====================================================================== */
	// protected methods
	/* ====================================================================== */

	/**
	 * composes the full file name for the AMP and gets a file handle for that
	 * file TODO: what happens when nulls are passed in TODO: what does a null
	 * response mean?
	 * 
	 * @param pBaseDir
	 *            Base directory for AMP
	 * @param pFileName
	 *            Final Name of AMP
	 * @param pClassifier
	 *            TODO: fill this in
	 */
	protected static File getAmpFile(File pBasedir, String pFinalName, String pClassifier) {
		String vClassifier = pClassifier;

		if (vClassifier == null) {
			vClassifier = "";
		} else if (vClassifier.trim().length() > 0 && !vClassifier.startsWith("-")) {
			vClassifier = "-" + vClassifier;
		}

		return new File(pBasedir, pFinalName + vClassifier + ".amp");
	}

	/**
	 * Generates the webapp according to the <tt>mode</tt> attribute.
	 * 
	 * @param pAmpFile
	 *            the target AMP file
	 * @throws IOException
	 * @throws ArchiverException
	 * @throws ManifestException
	 * @throws DependencyResolutionRequiredException
	 * 
	 */
	protected void performPackaging(File pAmpFile) throws IOException, ArchiverException, ManifestException, DependencyResolutionRequiredException, MojoExecutionException, MojoFailureException {
		getLog().info("Packaging Alfresco AMP (" + this.getAmpName() + ")");
		getLog().info("DEBUG: \n"+this.toString());
		this.buildExplodedAmp(this.getAmpDirectory());

		/* create and setup an archiver */
		MavenArchiver vArchiver = new MavenArchiver();
		vArchiver.setArchiver(this.getAmpArchiver());
		vArchiver.setOutputFile(pAmpFile);

		/* setup amp Archiver */
		this.getAmpArchiver().addDirectory(this.getAmpDirectory(), this.getIncludes(), this.getExcludes());

		// create archive
		vArchiver.createArchive(this.getProject(), archive);

		String vClassifier = this.getClassifier();

		if (vClassifier != null) {
			this.getProjectHelper().attachArtifact(this.getProject(), "amp", vClassifier, pAmpFile);
		} else {
			Artifact vArtifact = this.getProject().getArtifact();

			if (this.isPrimaryArtifact()) {
				vArtifact.setFile(pAmpFile);
			} else if (vArtifact.getFile() == null || vArtifact.getFile().isDirectory()) {
				vArtifact.setFile(pAmpFile);
			}
		}
		// create the classes to be attached if necessary
		getLog().info( "attached ? :" +isAttachClasses());
		if (isAttachClasses()) {
			getLog().info( "attach jar file :getProject()" +getProject());
			getLog().info( "attach jar file :type" +"jar");
			getLog().info( "attach jar file :vClassifier" +"vClassifier");
			getLog().info( "attach jar file :getTargetClassesFile" +getTargetClassesFile());
			getProjectHelper().attachArtifact(getProject(), "jar", getClassesClassifier(), getTargetClassesFile());
		}
	}

	/* ====================================================================== */
	// member fields
	/* ====================================================================== */
	
	/**
	 * The directory for the generated AMP.
	 * 
	 * @parameter expression="${project.build.directory}"
	 * @required
	 */
	private String mOutputDirectory;

	/**
	 * The name of the generated AMP.
	 * 
	 * @parameter expression="${project.build.finalName}"
	 * @required
	 */
	private String mAmpName;

	/**
	 * Classifier to add to the artifact generated. If given, the artifact will
	 * be an attachment instead.
	 * 
	 * @parameter
	 */
	private String mClassifier;

	/**
	 * The AMP archiver.
	 * 
	 * @parameter 
	 *            expression="${component.org.codehaus.plexus.archiver.Archiver#amp}"
	 * @required
	 */
	private AmpArchiver mAmpArchiver;

	/**
	 * @component
	 */
	private MavenProjectHelper mProjectHelper;

	public boolean isAttachClasses() {
		return attachClasses;
	}

	protected File getTargetClassesFile() {
		File sourceFile = getTargetFile(new File(getOutputDirectory(), this.mAmpName+File.separator+"lib"), getAmpName(), "", "jar");
		File targetFile = getTargetFile(new File(getOutputDirectory()), getAmpName(), getClassesClassifier(), "jar");
		try {
			FileUtils.copyFile( sourceFile.getCanonicalFile(), targetFile );
		} catch (IOException e) {
			getLog().error(e);
		}
        // preserve timestamp
		targetFile.setLastModified( sourceFile.lastModified() );
		
		return targetFile;
	}

	protected static File getTargetFile(File basedir, String finalName, String classifier, String type) {
		if (classifier == null) {
			classifier = "";
		} else if (classifier.trim().length() > 0 && !classifier.startsWith("-")) {
			classifier = "-" + classifier;
		}

		return new File(basedir, finalName + classifier + "." + type);
	}
	
	public String toString() {
		String objString ="";
		objString +="mAmpName :"+this.mAmpName+"\n";
		objString +="mClassifier :"+this.mClassifier+"\n";
		objString +="mOutputDirectory :"+this.mOutputDirectory+"\n";
		objString +="mAmpDirectory_ :"+this.getAmpDirectory()+"\n";
		objString +="getCacheFile :"+this.getCacheFile()+"\n";
		objString +="isUseCache :"+this.isUseCache()+"\n";
		
		
		
		
		return objString;
	}
	/**
     * The file containing the webapp structure cache.
     *
     * @parameter expression="${project.build.directory}/amp/work/amp-cache.xml"
     * @required
     * @since 2.1
     */
    private File mCacheFile_;
    public File getCacheFile()
    {
        return mCacheFile_;
    }
	/**
     * The directory where the webapp is built.
     *
     * @parameter expression="${project.build.directory}/${project.build.finalName}"
     * @required
     */
    private File mAmpDirectory_;
	public File getAmpDirectory() {
		return mAmpDirectory_;
		//return new File(getOutputDirectory(),"/amp/work");
	}
	
	public String getClassesClassifier()
    {
        return classesClassifier;
    }

    public void setClassesClassifier( String classesClassifier )
    {
        this.classesClassifier = classesClassifier;
    }
}
