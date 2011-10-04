package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.project;

import java.util.Map;

import com.bluexml.side.util.libs.velocity.VelocityGenerator;
/**
 * using this Class rather than VelocityGenerator in Util.lib to have access of plugin classLoader
 * to get templates
 * @author davidabad
 *
 */
public class VelocityGeneratorImp extends VelocityGenerator {

	public VelocityGeneratorImp(String velocityTemplatePath, String fileoutPath, Map<String, Object> context) {
		super(velocityTemplatePath, fileoutPath, context);
	}

}
