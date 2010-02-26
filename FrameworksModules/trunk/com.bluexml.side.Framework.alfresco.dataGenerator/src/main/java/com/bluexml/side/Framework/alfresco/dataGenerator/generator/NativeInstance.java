/**
 * This class allows instanciation of the native node embedeed in node 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.util.Map;

import org.alfresco.service.cmr.dictionary.TypeDefinition;
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
	public NativeAlfrescoNode instanciation(TypeDefinition type){
		NativeAlfrescoNode nativeNode = new NativeAlfrescoNode();
		Map<QNamePattern, Object> nativeDatasProperties = generator.generateNativeDatasProperties(type);
		((NativeAlfrescoNode) nativeNode).setNativeDatasProperties(nativeDatasProperties);
		Map<QNamePattern, Object> nativeDatasAspects = generator.generateNativeDatasAspects(type);
		((NativeAlfrescoNode) nativeNode).setNativeDatasAspects(nativeDatasAspects);
		return nativeNode;
	}

}
