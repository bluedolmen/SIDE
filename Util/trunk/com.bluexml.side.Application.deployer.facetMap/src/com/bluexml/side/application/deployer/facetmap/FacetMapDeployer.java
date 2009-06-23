/**
 * 
 */
package com.bluexml.side.application.deployer.facetmap;

import java.io.File;

import com.bluexml.side.application.deployer.war.MultiWarDeployer;
import com.bluexml.side.application.deployer.war.WarDeployer;

/**
 * @author davidabad
 * 
 */
public class FacetMapDeployer extends MultiWarDeployer {

	public FacetMapDeployer() {
		this.cleanKey = "facetMap.deployer.clean";

		// build map of ModuleID,(facetmap-facets.zip,facetmap-conent.zip)
		// for each moduleID build 2 war deployer and process them

		// record first webapp deployer
		final String webappName1 = "facetmap-facets";
		WarDeployer wd1 = new WarDeployer() {
			@Override
			protected void postProcess(File fileToDeploy) throws Exception {
			};

			public File getFileToDeploy(String absoluteWKDirePath) {
				return new File(absoluteWKDirePath + File.separator + techVersion + File.separator + webappName1 + ".zip");
			}

		};

		addWarDeployer(webappName1, wd1);

		// record decond webapp deployer
		final String webappName2 = "facetmap-content";
		WarDeployer wd2 = new WarDeployer() {
			@Override
			protected void postProcess(File fileToDeploy) throws Exception {
			};

			public File getFileToDeploy(String absoluteWKDirePath) {
				return new File(absoluteWKDirePath + File.separator + techVersion + File.separator + webappName2 + ".zip");
			}
		};
		addWarDeployer(webappName2, wd2); 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.application.deployer.Deployer#postProcess(java.io.File)
	 */
	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// nothing to do
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.side.application.security.Checkable#check()
	 */
	public boolean check() {
		// TODO Auto-generated method stub
		return true;
	}

}
