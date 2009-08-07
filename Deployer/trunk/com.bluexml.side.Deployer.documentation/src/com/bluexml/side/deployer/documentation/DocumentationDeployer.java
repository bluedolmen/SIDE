package com.bluexml.side.deployer.documentation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.documentation.LogSave;
import com.bluexml.side.util.libs.IFileHelper;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.DefaultArchiveDetector;
import com.bluexml.side.util.generator.documentation.services.DocumentationServices;

public class DocumentationDeployer extends Deployer {

	protected String[] toJar = new String[]{"meta.xml","manifest.xml","styles.xml","styles.xml","content.xml"};    //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	protected static String IDENTIFIER_END_FILENAME = "-info.xml"; //$NON-NLS-1$
	protected static String DOC_EXT = ".odt"; //$NON-NLS-1$

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		FileUtils.deleteDirectory(fileToDeploy);
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		IContainer src = IFileHelper.getIFolder(fileToDeploy);
		if (src != null) {
			IFolder dest = IFileHelper.createFolder(getConfigurationParameters().get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + File.separator + getConfigurationParameters().get("configurationName") + File.separator + LogSave.LOG_DOC_FOLDER + File.separator); //$NON-NLS-1$
			IFileHelper.refreshFolder((IFolder)src);

			AntRunner runner = new AntRunner();
			Map<String,String> properties = new HashMap<String, String>();
			runner.addUserProperties(properties);
			File f = getAntBuildFile();
			runner.setBuildFileLocation(f.getAbsolutePath());
			properties.put("destDir", dest.getLocation().toFile().getAbsolutePath()); //$NON-NLS-1$
			properties.put("sourceDir", src.getLocation().toFile().getAbsolutePath()); //$NON-NLS-1$
			properties.put("docName", DocumentationServices.getModelName()); //$NON-NLS-1$
			runner.addUserProperties(properties);
			runner.run();

//				List<IFile> toDeploy = IFileHelper.getAllFiles(src);
//				List<IFile> toBuild = new ArrayList<IFile>();
//				String docName = ""; //$NON-NLS-1$
//				de.schlichtherle.io.File.setDefaultArchiveDetector(new DefaultArchiveDetector( ArchiveDetector.DEFAULT, // the delegate, could be ArchiveDetector.NULL, too.
//						new String[] { "odg|odp|ods|odt|otg|otp|ots|ott|odb|odf|odm|oth", // ODF suffixes //$NON-NLS-1$
//						"de.schlichtherle.io.archive.zip.OdfDriver", // driver class, lazily loaded. //$NON-NLS-1$
//				}));
//				de.schlichtherle.io.File.setDefaultArchiveDetector(new DefaultArchiveDetector( "ear|jar|war|zip|odg|odp|ods|odt|otg|otp|ots|ott|odb|odf|odm|oth")); // default and ODF suffixes //$NON-NLS-1$

//				for (IFile xmlFile : toDeploy) {
//					if (ArrayUtils.contains(toJar, xmlFile.getName())) {
//						toBuild.add(xmlFile);
//					} else if (xmlFile.getName().endsWith(IDENTIFIER_END_FILENAME)) {
//						docName = xmlFile.getName().substring(0,xmlFile.getName().length() - IDENTIFIER_END_FILENAME.length());
//					}
//				}
//				de.schlichtherle.io.File docFile = new de.schlichtherle.io.File(IFileHelper.getFile(dest),docName + DOC_EXT);
//				new de.schlichtherle.io.File ("document.odt", ArchiveDetector.ALL).list();
//				try {
//					docFile.mkdir();
//					for (IFile f : toBuild) {
//						de.schlichtherle.io.File file = new de.schlichtherle.io.File(IFileHelper.getFile(f));
//
//						de.schlichtherle.io.File zipEntry = new de.schlichtherle.io.File(docFile, file.getName(), ArchiveDetector.NULL );
//						file.copyTo( zipEntry );
//						de.schlichtherle.io.File.cp(file, zipEntry);
//					}
//
////
////					for (IFile file : toBuild) {
////						de.schlichtherle.io.File zipEntry = new de.schlichtherle.io.File(IFileHelper.getFile(file));
////						if (docFile.archiveCopyAllFrom(IFileHelper.getFile(src))) {
////							System.err.println("Copy OK of ");
////						} else {
////							System.err.println("Error Copy of ");
////						}
////					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					de.schlichtherle.io.File.umount();
//				}
//
//
//			}
		}
	}

	private File getAntBuildFile() throws URISyntaxException, IOException {
		Bundle b = Platform.getBundle(Activator.PLUGIN_ID);
		URL entry = b.getEntry("src/com/bluexml/side/deployer/documentation/build.xml"); //$NON-NLS-1$
		File f = new File(FileLocator.toFileURL(entry).toURI());
		return f;
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub
	}

	public boolean check() {
		return true;
	}

}

