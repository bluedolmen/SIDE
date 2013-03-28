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
/**
 * This class allows instanciation of the native node embedeed in node 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.util.Map;

import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.namespace.QNamePattern;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.NativeAlfrescoNode;

/**
 * @author davidchevrier
 *
 */
public class NativeInstance {
	
	NativeAlfrescoModelRandomDataGenerator generator;
	
	/**
	 * @return the generator
	 */
	public NativeAlfrescoModelRandomDataGenerator getGenerator() {
		return generator;
	}

	/**
	 * @param generator the generator to set
	 */
	public void setGenerator(NativeAlfrescoModelRandomDataGenerator generator) {
		this.generator = generator;
	}

	/**
	 * 
	 * @param type
	 * @return instance of native node for the given type
	 */
	public NativeAlfrescoNode instanciation(ClassDefinition type){
		NativeAlfrescoNode nativeNode = new NativeAlfrescoNode();
		Map<QNamePattern, Object> nativeDatasProperties = generator.generateNativeDatasProperties(type);
		((NativeAlfrescoNode) nativeNode).setNativeDatasProperties(nativeDatasProperties);
		Map<QNamePattern, Object> nativeDatasAspects = generator.generateNativeDatasAspects(type);
		((NativeAlfrescoNode) nativeNode).setNativeDatasAspects(nativeDatasAspects);
		return nativeNode;
	}

}
