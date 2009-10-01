/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping.XMLForACPMappingMain;

/**
 * @author davidchevrier
 *
 */
public class XMLForACPSerialization implements ISerialization {

	private XMLForACPMappingMain mainMap;
	private OutputStream fileStream;
	private String fileName;

	/**
	 * @return the mainMapping
	 */
	public XMLForACPMappingMain getMainMap() {
		return mainMap;
	}

	/**
	 * @param mainMapping the mainMapping to set
	 */
	public void setMainMap(XMLForACPMappingMain mainMap) {
		this.mainMap = mainMap;
	}

	/**
	 * @return the fileStream
	 */
	public OutputStream getFileStream() {
		return fileStream;
	}

	/**
	 * @param fileStream the fileStream to set
	 */
	public void setFileStream(OutputStream fileStream) {
		this.fileStream = fileStream;
	}
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void serializeXml() throws IOException{
		fileStream = new FileOutputStream(fileName);
		byte[] xmlStringToBytes = mainMap.mainMapping().toString().getBytes();
		fileStream.write(xmlStringToBytes);
		fileStream.close();
	}

}
