/**
 * This class allows the serialization of the builded xml containing 
 * metadata for .acp
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping.XMLForACPMappingBuilder;

/**
 * @author dchevrier
 *
 */
public class XMLForACPSerializer implements ISerialization{

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
	
	/**
	 * write the Document object (dom4j) in file of given name
	 * @return true if the process is successful
	 * @throws Exception
	 */
	public boolean serializeXml() throws Exception {
		FileOutputStream fos = new FileOutputStream(fileName);
		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
	    builder.build().write(osw); 
	    osw.close();
	    fos.close();
	    return true;
	}	
}
