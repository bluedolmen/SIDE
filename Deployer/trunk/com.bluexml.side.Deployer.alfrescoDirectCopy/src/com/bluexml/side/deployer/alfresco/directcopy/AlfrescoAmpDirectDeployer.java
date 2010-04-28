package com.bluexml.side.deployer.alfresco.directcopy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bluexml.side.util.componentmonitor.MonitorWriter;
import com.bluexml.side.util.deployer.war.DirectWebAppsDeployer;
import com.bluexml.side.util.libs.FileHelper;

import de.schlichtherle.io.FileInputStream;

public class AlfrescoAmpDirectDeployer extends DirectWebAppsDeployer {

	public AlfrescoAmpDirectDeployer() {
		super(null, "alfresco", "deployer.webappName.alfresco","amp"); //$NON-NLS-1$ $NON-NLS-1$ $NON-NLS-1$
	}

	protected void dispatchFiles(List<File> files, Map<String, File> mapper) throws Exception {
		MonitorWriter mw = new MonitorWriter(monitor, Activator.Messages.getString("AlfrescoAmpDirectDeployer.2"), ""); //$NON-NLS-1$ //$NON-NLS-2$
		for (File f : files) {
			for (Map.Entry<String, File> ent : mapper.entrySet()) {
				if (f.getAbsolutePath().indexOf(ent.getKey()) != -1) {
					String path = f.getAbsolutePath();
					String pathIn = ent.getValue().getAbsolutePath() + File.separator + path.substring(path.indexOf(ent.getKey()) + ent.getKey().length());
					File dest = new File(pathIn);
					dest.getParentFile().mkdirs();
					// put to this target directory
					FileHelper.copyFiles(f, dest, true, mw);
				}
			}
			if (f.getAbsolutePath().indexOf("/module.properties") != -1) { //$NON-NLS-1$
				Properties moduleProperties = getModuleProperties(f);
				String moduleId = moduleProperties.getProperty("module.id"); //$NON-NLS-1$

				File dest = new File(getDeployedWebbAppFolder(), "/WEB-INF/classes/alfresco/module/".replace("/", File.separator) + moduleId); //$NON-NLS-1$ //$NON-NLS-2$
				dest.mkdirs();
				// put to this dir
				FileHelper.copyFiles(f, dest, true, mw);
			}
		}

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
		addToMap(mapper, "/config/", packageFolder, "/WEB-INF/classes/"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/web/", packageFolder, "/"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/lib/", packageFolder, "/WEB-INF/lib/"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/licenses/", packageFolder, "/licenses"); //$NON-NLS-1$ //$NON-NLS-2$

		return mapper;
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
