/**
 * 
 */
package com.bluexml.side.application.deployer;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.security.Checkable;
import com.bluexml.side.util.libs.IFileHelper;

/**
 * @author davidabad generic Deployer must be implemented to match with target
 *         technology
 */
public abstract class Deployer implements Checkable {
	String workingDirKey = "generation.options.destinationPath";
	private Map<String, String> configurationParameters;
	private Map<String, String> generationParameters;
	public static String DEPLOYER_CODE = null;
	protected String cleanKey = null;
	protected List<String> options = null;

	public void initialize(Map<String, String> configurationParameters,Map<String, String> generationParameters,List<String> options) {
		this.configurationParameters = configurationParameters;
		this.options = options;
		this.generationParameters = generationParameters;
	}
	
	public Map<String, String> getGenerationParameters() {
		return generationParameters;
	}
	
	public void deploy(String id_techno) throws Exception {
		String IfilewkDirPath = getTargetPath();
		String absoluteWKDirePath = IFileHelper.getSystemFolderPath(IfilewkDirPath);
		File fileToDeploy = new File(absoluteWKDirePath + File.separator + id_techno);
		preProcess(fileToDeploy);
		if (doClean()) {
			clean(fileToDeploy);
		}
		deployProcess(fileToDeploy);
		postProcess(fileToDeploy);
	}

	protected abstract void deployProcess(File fileToDeploy) throws Exception;

	protected abstract void clean(File fileToDeploy) throws Exception;

	protected abstract void postProcess(File fileToDeploy) throws Exception;

	protected abstract void preProcess(File fileToDeploy) throws Exception;

	public final String getTargetPath() {
		return configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral());
	}

	public Map<String, String> getConfigurationParameters() {
		return configurationParameters;
	}

	protected boolean doClean() {
		return options != null && options.contains(cleanKey);		
	}
}
