package com.bluexml.side.framework.alfresco.unicity;
 
import java.util.List;

import org.alfresco.service.namespace.QName;

public abstract class  AbstractUnicityChecker implements UnicityChecker {
	
	
	
	UnicityXMLReader unicityDescriptorReader = null;
	
	/**
	 * @return the unicityDescriptorReader
	 */
	public UnicityXMLReader getUnicityDescriptorReader() {
		return unicityDescriptorReader;
	}

	/**
	 * @param unicityDescriptorReader the unicityDescriptorReader to set
	 */
	public void setUnicityDescriptorReader(UnicityXMLReader unicityDescriptorReader) {
		this.unicityDescriptorReader = unicityDescriptorReader;
	}
	
	public List<QName> getUnicityKeysFor(QName type) throws Exception {
		return getUnicityDescriptorReader().getUnicityDictionary().get(type);
	}
	
	
	
}
