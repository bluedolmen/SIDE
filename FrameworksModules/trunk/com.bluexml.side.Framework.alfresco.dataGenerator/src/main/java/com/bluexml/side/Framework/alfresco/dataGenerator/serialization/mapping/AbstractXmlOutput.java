/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping;

/**
 * @author davidchevrier
 *
 */
public abstract class AbstractXmlOutput {
	
	protected StringBuffer xmlOutPut;

	/**
	 * @return the xmlOutPut
	 */
	public StringBuffer getXmlOutPut() {
		return xmlOutPut;
	}

	/**
	 * @param xmlOutPut the xmlOutPut to set
	 */
	public void setXmlOutPut(StringBuffer xmlOutPut) {
		this.xmlOutPut = xmlOutPut;
	}
	
	public StringBuffer clear(){
		if (xmlOutPut != null){
			xmlOutPut.delete(0, xmlOutPut.length());
		}
		return xmlOutPut;
	}

}
