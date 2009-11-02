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

		File filetoDeploy = getFileToDeploy();
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
		// TODO Auto-generated method stub
		return true;
	}

}
