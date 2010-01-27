/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization;

import java.io.FileWriter;

import com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping.XMLForACPMappingBuilder;

/**
 * @author dchevrier
 *
 */
public class XMLForACPSerializer {

	private XMLForACPMappingBuilder builder;
	private String fileName;

	public XMLForACPMappingBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(XMLForACPMappingBuilder builder) {
		this.builder = builder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void serializeXml() throws Exception {
		FileWriter out = new FileWriter(fileName);
	    builder.build().write(out); 
	    out.close();
	}	
}
