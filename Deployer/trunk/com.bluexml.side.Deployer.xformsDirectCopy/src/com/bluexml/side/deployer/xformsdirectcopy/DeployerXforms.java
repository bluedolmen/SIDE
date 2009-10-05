/**
 * 
 */
package com.bluexml.side.deployer.xformsdirectcopy;

import com.bluexml.side.util.deployer.war.DirectWebAppsDeployer;

/**
 * @author davidabad
 * 
 */
public class DeployerXforms extends DirectWebAppsDeployer {

	/**
	 * @param webappName
	 */
	public DeployerXforms(String webappName) {
		super("xform");
		cleanKey = "com.bluexml.side.Deployer.xformsDirectCopy.clean";
	}

}
