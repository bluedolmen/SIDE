/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.util.Map;

import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QNamePattern;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.NativeAlfrescoNode;

/**
 * @author davidchevrier
 *
 */
public class NativeInstance implements IInstance {
	
	IRandomGenerator generator;
	
	/**
	 * @return the generator
	 */
	public IRandomGenerator getGenerator() {
		return generator;
	}

	/**
	 * @param generator the generator to set
	 */
	public void setGenerator(IRandomGenerator generator) {
		this.generator = generator;
	}

	public INode instanciation(TypeDefinition type){
		INode nativeNode = new NativeAlfrescoNode();
		Map<QNamePattern, Object> nativeDatasProperties = ((NativeAlfrescoModelRandomDataGenerator) generator).generateNativeDatasProperties(type);
		((NativeAlfrescoNode) nativeNode).setNativeDatasProperties(nativeDatasProperties);
		Map<QNamePattern, Object> nativeDatasAspects = ((NativeAlfrescoModelRandomDataGenerator) generator).generateNativeDatasAspects(type);
		((NativeAlfrescoNode) nativeNode).setNativeDatasAspects(nativeDatasAspects);
		return nativeNode;
	}

}
