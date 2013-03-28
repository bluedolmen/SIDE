/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.util.generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.dependencies.DependencesManager;

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
	public void initialize(Map<String, String> generationParameters, Map<String, Boolean> generatorOptions, Map<String, String> configurationParameters, DependencesManager dm, ComponentMonitor monitor) throws Exception;

	public boolean shouldGenerate(HashMap<String, List<IFile>> modelsInfo, String id_metamodel);

	/**
	 * This method launch the generation.
	 * 
	 * @param models
	 *            the input models
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
	public Collection<IFile> complete(Map<String, List<IFile>> models) throws Exception;

	/**
	 * This method get dependencies and put them into the right folder
	 * @throws Exception
	 */
	public void addDependences() throws Exception;
}
