/**
 * 
 */
package com.bluexml.side.application.deployer;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.bluexml.side.application.Option;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.application.security.Checkable;
/**
 * @author davidabad
 * generic Deployer must be implemented to match with target technology
 */
public abstract class Deployer implements Checkable{
	String workingDirKey = "generation.options.destinationPath";
	private Map<String, String> configurationParameters;
	private Map<String, String> generationParameters;
	public static String DEPLOYER_CODE = null;
	
	
	public Map<String, String> getGenerationParameters() {
		return generationParameters;
	}
	public void setGenerationParameters(Map<String, String> generationParameters) {
		this.generationParameters = generationParameters;
	}
	public void deploy(String id_techno,List<Option> options) throws Exception {
		String IfilewkDirPath = configurationParameters.get(workingDirKey);		
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

	public void setConfigurationParameters(Map<String, String> configurationParameters) {
		this.configurationParameters = configurationParameters;
	}

	public Map<String, String> getConfigurationParameters() {
		return configurationParameters;
	}
	
	protected boolean doClean() {
		if (configurationParameters != null && configurationParameters.containsKey(StaticConfigurationParameters.GENERATIONOPTIONSCLEAN.getLiteral())) {
			return Boolean.parseBoolean(configurationParameters.get(StaticConfigurationParameters.GENERATIONOPTIONSCLEAN.getLiteral()));
		}
		return false;
	}
}
