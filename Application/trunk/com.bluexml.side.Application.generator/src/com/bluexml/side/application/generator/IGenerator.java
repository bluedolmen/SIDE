package com.bluexml.side.application.generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

public interface IGenerator {

	/**
	 * This method is called before generation.
	 * 
	 * @param generationParameters
	 *            the list of selected options for the generation (clean,
	 *            verbose...), shared by all generators.
	 * @param generatorOptions
	 *            the list of selected options for the generator.
	 * @param configurationParamers
	 *            the list of technical parameters, shared by all generators.
	 * @throws Exception 
	 */
	public void initialize(Map<String, String> generationParameters,
			Map<String, Boolean> generatorOptions,
			Map<String, String> configurationParameters, String techVersion_) throws Exception;
	
	public boolean shouldGenerate(HashMap<String, List<IFile>> modelsInfo,
			String id_metamodel);
	
	/**
	 * This method launch the generation.
	 * 
	 * @param models the input models
	 * @return the list of generated files
	 * @throws Exception 
	 */
	public Collection<IFile> generate(Map<String, List<IFile>> models, String id_metamodel) throws Exception;

	/**
	 * This method run the post-action after the generation. 
	 *  
	 * @return the list of modified files
	 * @throws Exception 
	 */
	public Collection<IFile> complete() throws Exception;
	
	
}
