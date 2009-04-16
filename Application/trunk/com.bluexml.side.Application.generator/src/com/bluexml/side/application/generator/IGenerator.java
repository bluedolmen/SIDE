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
	 */
	public void initialize(Map<String, String> generationParameters,
			Map<String, Boolean> generatorOptions,
			Map<String, String> configurationParameters);

	/**
	 * This method check if the user have the license to use this generator.
	 * 
	 * @return true if the generator can be used.
	 */
	public boolean check();

	/**
	 * This method launch the generation.
	 * 
	 * @param model the input model
	 * @return the list of generated files
	 * @throws Exception 
	 */
	public Collection<IFile> generate(IFile model) throws Exception;
	
	public void generate(HashMap<String, List<IFile>> modelsInfo);

	/**
	 * This method run the post-action after the generation. 
	 *  
	 * @return the list of modified files
	 * @throws Exception 
	 */
	public Collection<IFile> complete() throws Exception;
	
	/**
	 * This method is run after generation and post-action
	 * packaged files can be deployed into webserver or other target
	 * 
	 * @throws Exception
	 */
	public void deploy() throws Exception;
}
