/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.generator;

import java.util.Map;

import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QNamePattern;

import com.bluexml.side.framework.alfresco.datasGenerator.graph.INode;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.NativeAlfrescoNode;

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
		Map<QNamePattern, Object> nativeDatasProperties = ((NativeAlfrescoModelRandomDatasGenerator) generator).generateNativeDatasProperties(type);
		((NativeAlfrescoNode) nativeNode).setNativeDatasProperties(nativeDatasProperties);
		return nativeNode;
	}

}
