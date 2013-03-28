/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.deployer.facetmapmultitypes;

import java.io.File;

import com.bluexml.side.util.deployer.war.Activator;
import com.bluexml.side.util.deployer.war.DirectWebAppsDeployer;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public class FacetMapDeployer extends DirectWebAppsDeployer {
	protected final static String facetWebAppName = "facetmap-content";
	protected final static String contentWebAppName = "facetmap-facets";

	protected boolean isContent = false;

	public FacetMapDeployer(String cleanKey, String webAppName) {
		super(cleanKey, webAppName, null, "zip");
		if (webAppName.indexOf(contentWebAppName) != -1) {
			isContent = true;
		}
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		monitor.beginTask(Activator.Messages.getString("DirectWebAppsDeployer.0")); //$NON-NLS-1$
		boolean result = true;
		// delete exploded webapps
		if (getDeployedWebbAppFolder().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("WarDeployer.2", getDeployedWebbAppFolder().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
			FileHelper.deleteFile(getDeployedWebbAppFolder(), false);
		}

		// unzip .war or .org if exist
		if (getWarToPatchFile().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.5"), Activator.Messages.getString("DirectWebAppsDeployer.4", getWarToPatchFile().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
			TrueZipHelper tzh2 = new TrueZipHelper(warToPatchExt);
			result = tzh2.copyFiles(getWarToPatchFile(), getDeployedWebbAppFolder(), true);
		} else {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.7") + getWarToPatchFile().getAbsolutePath() + Activator.Messages.getString("DirectWebAppsDeployer.9")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (!result) {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.10")); //$NON-NLS-1$
		}
		monitor.taskDone(Activator.Messages.getString("DirectWebAppsDeployer.2")); //$NON-NLS-1$
	}

	@Override
	public File getWarToPatchFile() {
		if (warToPatchFile == null) {
			String warName = "";
			if (isContent) {
				warName = contentWebAppName + "."+warToPatchExt;
			} else {
				warName = facetWebAppName +  "."+warToPatchExt;
			}
			warToPatchFile = new File(getTomcatHome() + File.separator + webapps + File.separator + warName); //$NON-NLS-1$
		}
		return warToPatchFile;
	}
}
