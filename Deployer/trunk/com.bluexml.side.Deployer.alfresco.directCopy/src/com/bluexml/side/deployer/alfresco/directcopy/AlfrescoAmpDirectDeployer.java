package com.bluexml.side.deployer.alfresco.directcopy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bluexml.side.util.deployer.war.DirectWebAppsDeployer;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

import de.schlichtherle.io.FileInputStream;

public class AlfrescoAmpDirectDeployer extends DirectWebAppsDeployer {

	public AlfrescoAmpDirectDeployer() {
		this.webappName = "alfresco";
		packageExt = "amp";
		tzh = new TrueZipHelper(packageExt);
	}

	protected void dispatchFiles(List<File> files, Map<String, File> mapper) throws Exception {
		for (File f : files) {
			for (Map.Entry<String, File> ent : mapper.entrySet()) {
				if (f.getAbsolutePath().indexOf(ent.getKey()) != -1) {
					String path = f.getAbsolutePath();
					String pathIn = ent.getValue().getAbsolutePath() + File.separator + path.substring(path.indexOf(ent.getKey()) + ent.getKey().length());
					File dest = new File(pathIn);
					dest.getParentFile().mkdirs();
					// put to this target directory
					FileHelper.copyFiles(f, dest, true);
				}
			}
			if (f.getAbsolutePath().indexOf("/module.properties") != -1) {
				Properties moduleProperties = getModuleProperties(f);
				String moduleId = moduleProperties.getProperty("module.id");

				File dest = new File(getDeployedWebbAppFolder(), "/WEB-INF/classes/alfresco/module/".replace("/", File.separator) + moduleId);
				dest.mkdirs();
				// put to this dir
				FileHelper.copyFiles(f, dest, true);
			}
		}

	}

	public static void main(String[] args) throws Exception {
		AlfrescoAmpDirectDeployer d = new AlfrescoAmpDirectDeployer();
		File fileToDeploy = new File("/Users/davidabad/Workspace2.0/bigTest/generated/alfresco_3.x");
		d.initialize(new HashMap<String, String>(), new HashMap<String, String>(), new ArrayList<String>());
		d.getGenerationParameters().put(CONFIGURATION_PARAMETER_CATALINA_HOME, "/Users/davidabad/servers/Alfresco/tomcat");
		d.preProcess(fileToDeploy);
		d.deployProcess(fileToDeploy);
	}

	private Properties getModuleProperties(File wkdir) throws FileNotFoundException, IOException {
		File modulePropertiesfile = new File(wkdir.getAbsolutePath());
		Properties moduleProperties = new Properties();
		moduleProperties.load(new FileInputStream(modulePropertiesfile));
		return moduleProperties;
	}

	@Override
	public Map<String, File> createMapper(File packageDirectory) {
		File packageFolder = getDeployedWebbAppFolder();

		Map<String, File> mapper = new HashMap<String, File>();
		addToMap(mapper, "/config/", packageFolder, "/WEB-INF/classes/");
		addToMap(mapper, "/web/", packageFolder, "/");
		addToMap(mapper, "/lib/", packageFolder, "/WEB-INF/lib/");
		addToMap(mapper, "/licenses/", packageFolder, "/licenses");

		return mapper;
	}

}
