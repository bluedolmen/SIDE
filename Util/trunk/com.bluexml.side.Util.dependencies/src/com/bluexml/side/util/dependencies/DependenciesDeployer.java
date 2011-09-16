package com.bluexml.side.util.dependencies;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.repository.RepositorySystem;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.eclipse.ExtensionPointUtils;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public class DependenciesDeployer {

	public static final String EXTENSIONPOINT_ID = "com.bluexml.side.Util.dependencies.com_bluexml_side_framework_module_repository"; //$NON-NLS-1$
	public static final String APPLICATION_CONSTRAINTS = "repository"; //$NON-NLS-1$
	public static final String REPOSITORY = "repositoryName"; //$NON-NLS-1$
	public static final String APPLICATION_CONSTRAINTS_PATH = "repositoryPath"; //$NON-NLS-1$
	public static final String APPLICATION_CONSTRAINTS_ACTIVATOR = "activator"; //$NON-NLS-1$
	public static final String APPLICATION_CONSTRAINTS_PLUID = "pluginId"; //$NON-NLS-1$

	public static void deploy(boolean force) throws Exception {
		// get repositories

		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIONPOINT_ID);
		// get all module constraints and construct instance of
		// com.bluexml.side.util.dependencies.ModuleConstraint
		for (IConfigurationElement config_exp : contributions) {
			String nodeName = APPLICATION_CONSTRAINTS;
			List<IConfigurationElement> matchs = ExtensionPointUtils.getIConfigurationElementsByName(config_exp, nodeName);
			for (IConfigurationElement configurationElement : matchs) {
				String moduleId = configurationElement.getAttribute(REPOSITORY);
				String path = configurationElement.getAttribute(APPLICATION_CONSTRAINTS_PATH);
				String className = configurationElement.getAttribute(APPLICATION_CONSTRAINTS_ACTIVATOR);
				String bundle = configurationElement.getAttribute(APPLICATION_CONSTRAINTS_PLUID);

				System.out.println("deploy repository :" + moduleId); //$NON-NLS-1$
				System.out.println("deploy className :" + className); //$NON-NLS-1$

				Bundle plugin = Platform.getBundle(bundle);
				String version = plugin.getVersion().toString();
				String lastversion = getVersionRepository(moduleId);

				if (force || !version.equals(lastversion)) {
					System.out.println("local repository must be updated :"); //$NON-NLS-1$
					System.out.println("* bundle version :" + version); //$NON-NLS-1$
					System.out.println("* local repository version :" + lastversion); //$NON-NLS-1$
					Class<?> c = ExtensionPointUtils.getGeneratorInstance(bundle, className);
					System.out.println("DependenciesDeployer.deploy() get Stream from :" + path); //$NON-NLS-1$
					InputStream stream = c.getResourceAsStream(path);
					
					Bundle eclipseBundle = Platform.getBundle(bundle);
					URL resourceURL = eclipseBundle.getResource(path);
					stream = resourceURL.openStream();


					deployRepository(stream);

					// record updated repo
					WorkbenchPreferencePage1.setRepoVersion(bundle, version);
				} else {
					// nothing to do repository is up to date
					System.out.println("repository is up to date bundle version :" + version); //$NON-NLS-1$
				}
			}
		}
	}

	private static void deployRepository(InputStream stream) throws Exception, IOException {
		File repository = RepositorySystem.defaultUserLocalRepository;
		String localRepository = getLocalRepository();
		if (localRepository != null) {
			repository = new File(localRepository);
		}
		if (!repository.exists()) {
			boolean done = repository.mkdirs();
			if (!done) {
				throw new Exception(Messages.DependenciesDeployer_13 + repository + Messages.DependenciesDeployer_14);
			}
		}

		File tmpZip = File.createTempFile("side_repo", ".zip"); //$NON-NLS-1$ //$NON-NLS-2$
		tmpZip.deleteOnExit();
		FileHelper.writeStreamInFile(tmpZip, stream);
		System.out.println("file to unzip :" + tmpZip + ":" + tmpZip.length() + " b"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		TrueZipHelper tzh = new TrueZipHelper("zip"); //$NON-NLS-1$
		tzh.copyFiles(tmpZip, repository, true);
	}

	private static String getVersionRepository(String repoId) {
		return WorkbenchPreferencePage1.getLastRepoVersion(repoId);
	}

	private static String getLocalRepository() {
		return WorkbenchPreferencePage1.getLocationPreference();
	}

	public static Map<String, String> getDefaultMavenPropertyMap() {
		HashMap<String, String> params = new HashMap<String, String>();
		String localRepository = getLocalRepository();
		if (localRepository != null) {
			params.put("maven.repo.local", localRepository); //$NON-NLS-1$
		}
		return params;
	}

}
