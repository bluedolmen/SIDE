/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

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
	
	public boolean serializeXml() throws Exception {
		FileOutputStream fos = new FileOutputStream(fileName);
		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
	    builder.build().write(osw); 
	    osw.close();
	    fos.close();
	    return true;
	}	
}
