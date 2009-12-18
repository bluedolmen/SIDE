/**
 * 
 */
package com.bluexml.side.deployer.facetmap;

import java.io.File;

import com.bluexml.side.util.deployer.war.WarDeployer;

/**
 * @author davidabad
 * 
 */
public class FacetMapDeployer extends WarDeployer {

	public FacetMapDeployer() {
		super("com.bluexml.side.Deployer.facetMap.clean", null, "facetmap", "");
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

}
