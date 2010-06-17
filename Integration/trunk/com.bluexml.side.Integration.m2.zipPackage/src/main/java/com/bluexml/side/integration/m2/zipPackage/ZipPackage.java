package com.bluexml.side.integration.m2.zipPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

/**
 * Goal which touches a timestamp file.
 * 
 * 
 * @goal zip
 * @phase package
 */
public class ZipPackage extends AbstractMojo {
	private File antBuidFile = null;
	/**
	 * The maven project.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject mProject;

	/**
	 * The classifier to use for the attached classes artifact.
	 * 
	 * @parameter default-value="classes"
	 * @since 2.1-alpha-2
	 */
	private String classesClassifier = "classes";

	/**
	 * The directory for the generated AMP.
	 * 
	 * @parameter expression="${project.build.directory}"
	 * @required
	 */
	private String mOutputDirectory;

	/**
	 * The name of the generated package.
	 * 
	 * @parameter expression="${project.build.finalName}"
	 * @required
	 */
	private String mFinalName;
	/**
	 * The maven project.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * Location of the file.
	 * 
	 * @parameter expression="${project.build.directory}"
	 * @required
	 */
	private File outputDirectory;

	/**
	 * Whether classes (that is the content of the WEB-INF/classes directory)
	 * should be attached to the project.
	 * 
	 * @parameter default-value="false"
	 * @since 2.1-alpha-2
	 */
	private boolean attachClasses = false;

	/**
	 * @component
	 */
	private MavenProjectHelper mProjectHelper;

	/**
	 * use Ant to package all resources and jar
	 * 
	 * @throws MojoExecutionException
	 */
	public void makePackage() throws MojoExecutionException {
		// run ant script (much friendly)
		Project project = new Project();
		project.init();
		DefaultLogger logger = new AntLogger(getLog());
		logger.setMessageOutputLevel(Project.MSG_INFO);
		logger.setErrorPrintStream(System.err);
		logger.setOutputPrintStream(System.out);
		project.addBuildListener(logger);

		Properties props = getProject().getProperties();
		getLog().info("Properties :"+getProject().getArtifact().getFile());
		for (Map.Entry<Object, Object> iterable_element : props.entrySet()) {
			getLog().info(iterable_element.getKey() + " : " + iterable_element.getValue());

		}

		project.setProperty("ant.file", getBuildFile().getAbsolutePath());
		project.setUserProperty("module.version", getProject().getVersion());
		project.setUserProperty("module.title", getProject().getName());
		project.setUserProperty("module.description", getProject().getDescription());
		project.setUserProperty("module.id", getProject().getArtifactId());
		project.setUserProperty("baseDir", getProject().getBasedir().toString());
		project.setUserProperty("finalName", getFinalName());
		project.setBaseDir(getProject().getBasedir());

		ProjectHelper.configureProject(project, getBuildFile());

		try {
			getLog().debug("launch ant script");
			project.executeTarget("package");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MojoExecutionException(e.getMessage(), e);
		}
		getLog().debug("ant task finished");
	}

	public File getBuildFile() throws MojoExecutionException {
		if (antBuidFile == null) {
			String path = "build.xml";
			String buildFilePath = "";
			InputStream in = ZipPackage.class.getClassLoader().getResourceAsStream(path);
			getLog().debug("Get Stream :" + in);
			try {
				antBuidFile = File.createTempFile("makePackage", "buildFile");
				writeStreamInFile(antBuidFile, in);
				buildFilePath = antBuidFile.getAbsolutePath();
			} catch (Exception e) {
				throw new MojoExecutionException("Error when creating tempory file", e);
			}
			if (buildFilePath == null) {
				throw new MojoExecutionException("Erreur when getting ant script");
			}
		}
		return antBuidFile;
	}

	public void execute() throws MojoExecutionException {
		makePackage();
		File f = outputDirectory;
		if (!f.exists()) {
			f.mkdirs();
		}

		String archiveName = project.getBuild().getFinalName() + ".zip";
		File custFile = new File(outputDirectory, archiveName);
		getLog().debug("Artifact : " + custFile.getName());
		project.getArtifact().setFile(custFile);
		getLog().debug("Artifact Type : " + project.getArtifact().getType());
		// create the classes to be attached if necessary
		getLog().debug("attachement ? : " + isAttachClasses());
		if (isAttachClasses()) {
			getLog().debug("attach jar file :getProject()" + getProject());
			getLog().debug("attach jar file :type" + "jar");
			getLog().debug("attach jar file :vClassifier" + "vClassifier");
			getLog().debug("attach jar file :getTargetClassesFile" + getTargetClassesFile());
			getProjectHelper().attachArtifact(getProject(), "jar", getClassesClassifier(), getTargetClassesFile());
		}
	}

	public boolean isAttachClasses() {
		return attachClasses;
	}

	public MavenProject getProject() {
		return mProject;
	}

	protected File getTargetClassesFile() {
		File targetFile = getTargetFile(new File(getOutputDirectory()), getFinalName(), getClassesClassifier(), "jar");
		return targetFile;
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

	public String getClassesClassifier() {
		return classesClassifier;
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
	 * get the the internal value for the <code>finalName</code> property.
	 * <p>
	 * The <code>finalName</code> property
	 * 
	 * @return Returns the internal value for the finalName property.
	 */
	protected String getFinalName() {
		return this.mFinalName;
	}

	protected static File getTargetFile(File basedir, String finalName, String classifier, String type) {
		if (classifier == null) {
			classifier = "";
		} else if (classifier.trim().length() > 0 && !classifier.startsWith("-")) {
			classifier = "-" + classifier;
		}

		return new File(basedir, finalName + classifier + "." + type);
	}

	public static void writeStreamInFile(File f, InputStream in) throws Exception {
		FileOutputStream fout = null;
		byte[] buffer = new byte[4096]; // Buffer 4K at a time (you can
		// change this).
		int bytesRead;

		fout = new FileOutputStream(f);
		while ((bytesRead = in.read(buffer)) >= 0) {
			fout.write(buffer, 0, bytesRead);
		}
		// InputStream is consumed we close it
		in.close();
	}
}
