/**
 * 
 */
package com.bluexml.side.deployer.facetmap;

import java.io.File;

import com.bluexml.side.util.deployer.AbstractMultiDeployer;
import com.bluexml.side.util.deployer.war.WarDeployer;

/**
 * @author davidabad
 * 
 */
public class FacetMapDeployer extends AbstractMultiDeployer {

	public FacetMapDeployer() {
		this.cleanKey = "com.bluexml.side.Deployer.facetMap.clean";

		// build map of ModuleID,(facetmap-facets.zip,facetmap-conent.zip)
		// for each moduleID build 2 war deployer and process them

		// record first webapp deployer
		final String webappName1 = "facetmap-facets";
		WarDeployer wd1 = new WarDeployer(webappName1) {
			@Override
			protected void postProcess(File fileToDeploy) throws Exception {
			};

			public File getFileToDeploy(String absoluteWKDirePath) {
				return new File(absoluteWKDirePath + File.separator + techVersion + File.separator + webappName1 + ".zip");
			}

		};
		wd1.setWebappName(webappName1);
		addDeployer(wd1);

		// record second webapp deployer
		final String webappName2 = "facetmap-content";
		WarDeployer wd2 = new WarDeployer(webappName2) {
			@Override
			protected void postProcess(File fileToDeploy) throws Exception {
			};

			public File getFileToDeploy(String absoluteWKDirePath) {
				return new File(absoluteWKDirePath + File.separator + techVersion + File.separator + webappName2 + ".zip");
			}
		};
		wd2.setWebappName(webappName2);
		addDeployer(wd2);
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
		return true;
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		// nothing to do		
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		// nothing to do		
	}

}
