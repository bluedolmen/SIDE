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

import org.apache.maven.archiver.MavenArchiveConfiguration;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.archiver.zip.ZipArchiver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	* @parameter expression="${project}"
	* @required
	* @readonly
	*/
	private MavenProject project;

	/**
	* The directory containing generated classes.
	* @parameter expression="${project.build.outputDirectory}"
	* @required
	* @readonly
	*/
	private File classesDirectory;

	/**
	 * Location of the file.
	 * 
	 * @parameter expression="${project.build.directory}"
	 * @required
	 */
	private File outputDirectory;

	
	
	protected MavenArchiveConfiguration archive = new MavenArchiveConfiguration();
	private ZipArchiver zarchiver;
	
	public void execute() throws MojoExecutionException {
		File f = outputDirectory;
		if (!f.exists()) {
			f.mkdirs();
		}

		String archiveName = project.getBuild().getFinalName() + ".zip";
	    File custFile = new File(outputDirectory, archiveName);

		project.getArtifact().setFile(custFile);
				
	}
}
