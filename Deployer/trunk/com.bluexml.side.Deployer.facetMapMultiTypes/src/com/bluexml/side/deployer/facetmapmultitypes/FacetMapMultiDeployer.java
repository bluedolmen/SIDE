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
import java.io.FileFilter;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.deployer.AbstractMultiDeployer;

public class FacetMapMultiDeployer extends AbstractMultiDeployer {

	@Override
	public void initialize(Map<String, String> configurationParameters, Map<String, String> generationParameters, List<String> options, ComponentMonitor monitor) {
		// initialize this class
		super.initialize(configurationParameters, generationParameters, options, monitor);
		// instantiate deployers

		File filetoDeploy = getFileWhereToDeploy();
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File pathname) {
				boolean ok = false;
				int content = pathname.getName().indexOf(FacetMapDeployer.contentWebAppName);
				int facet = pathname.getName().indexOf(FacetMapDeployer.facetWebAppName);
				ok = content != -1 || facet != -1;
				return ok;
			}
		};
		
		for (File packages : filetoDeploy.listFiles(fileFilter)) {
			final String fName = packages.getName();
			String webappName = fName.replaceFirst("module\\.", "");
			webappName = webappName.replaceFirst("\\.zip", "");
			FacetMapDeployer d = new FacetMapDeployer(fName, webappName);
			d.setFileToDeploy(packages);
			this.deployers.add(d);
		}
		// initialize all
		super.initialize(configurationParameters, generationParameters, options, monitor);
	}

	public FacetMapMultiDeployer() {
		super("", null);
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

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
