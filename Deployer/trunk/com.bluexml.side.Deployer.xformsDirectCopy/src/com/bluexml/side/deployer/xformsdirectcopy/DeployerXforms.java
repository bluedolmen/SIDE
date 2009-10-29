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
	public DeployerXforms() {
		super("com.bluexml.side.Deployer.xformsDirectCopy.clean", "xforms", "com.bluexml.side.Deployer.xforms.webappName");
	}

}
