package com.bluexml.side.util.dependencies;

import java.io.File;
import java.io.InputStream;

import org.apache.maven.embedder.MavenEmbedder;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public class DependenciesDeployer {

	public static void deploy() throws Exception {
		InputStream stream = DependenciesDeployer.class.getResourceAsStream("mavenRepository/m2repositoryForSIDE.zip");

		File repository = MavenEmbedder.defaultUserLocalRepository;

//		repository = new File("/Users/davidabad/Workspace2.0/com.bluexml.side.Application.enbededDependencies/repository");

		File tmpZip = File.createTempFile("side_repo", ".zip");
		tmpZip.deleteOnExit();
		FileHelper.writeStreamInFile(tmpZip, stream);

		TrueZipHelper tzh = new TrueZipHelper("zip");
		tzh.copyFiles(tmpZip, repository, true);

	}

}
