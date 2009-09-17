package com.bluexml.side.integration.m2.zipPackage;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

/**
 * Goal which touches a timestamp file.
 * 
 * 
 * @goal zip
 * @phase package
 */
public class ZipPackage extends AbstractMojo {
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
	 * The name of the generated AMP.
	 * 
	 * @parameter expression="${project.build.finalName}"
	 * @required
	 */
	private String mAmpName;
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
	 * @throws MojoExecutionException
	 */
	public void makePackage() throws MojoExecutionException {
		// run ant script (much friendly)

		Properties additionalUserProperties = new Properties();
		additionalUserProperties.setProperty("module.version", getProject().getVersion());
		additionalUserProperties.setProperty("module.title", getProject().getName());
		additionalUserProperties.setProperty("module.description", getProject().getDescription());
		additionalUserProperties.setProperty("module.id", getProject().getArtifactId());
		additionalUserProperties.setProperty("baseDir", getProject().getBasedir().toString());
		getLog().info("ready to launch ant script");
		String path = "build.xml";
		String buildFile = "";
		InputStream in = ZipPackage.class.getClassLoader().getResourceAsStream(path);
		getLog().info("Get Stream :" + in);
		try {
			File tmpFile = File.createTempFile("makePackage", "buildFile");
			writeStreamInFile(tmpFile, in);
			buildFile = tmpFile.getAbsolutePath();
		} catch (Exception e) {
			throw new MojoExecutionException("Error when creating tempory file", e);
			// TODO Auto-generated catch block
		}

		if (buildFile == null) {
			throw new MojoExecutionException("Erreur durant la récupération du script ant");
		}
		String[] args = { "package", "-buildfile", buildFile.toString() };

		ClassLoader coreLoader = this.getClass().getClassLoader();

		org.apache.tools.ant.Main.start(args, additionalUserProperties, coreLoader);
	}

	public void execute() throws MojoExecutionException {
		makePackage();
		File f = outputDirectory;
		if (!f.exists()) {
			f.mkdirs();
		}

		String archiveName = project.getBuild().getFinalName() + ".zip";
		File custFile = new File(outputDirectory, archiveName);

		project.getArtifact().setFile(custFile);
		// create the classes to be attached if necessary
		getLog().info("attached ? :" + isAttachClasses());
		if (isAttachClasses()) {
			getLog().info("attach jar file :getProject()" + getProject());
			getLog().info("attach jar file :type" + "jar");
			getLog().info("attach jar file :vClassifier" + "vClassifier");
			getLog().info("attach jar file :getTargetClassesFile" + getTargetClassesFile());
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
		File targetFile = getTargetFile(new File(getOutputDirectory()), getAmpName(), getClassesClassifier(), "jar");
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
	 * get the the internal value for the <code>ampName</code> property.
	 * <p>
	 * The <code>ampName</code> property
	 * 
	 * @return Returns the internal value for the ampName property.
	 */
	protected String getAmpName() {
		return this.mAmpName;
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
