/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

/**
 * @author davidchevrier
 *
 */
public class ACPPackaging implements ISerialization {
	
	private OutputStream acpArchive;
	private String archiveName;
	private XMLForACPSerialization xmlSerializer;
	private static int bufferSize = 2048;

	/**
	 * @return the acpArchive
	 */
	public OutputStream getAcpArchive() {
		return acpArchive;
	}

	/**
	 * @param acpArchive the acpArchive to set
	 */
	public void setAcpArchive(OutputStream acpArchive) {
		this.acpArchive = acpArchive;
	}
	
	/**
	 * @return the xmlSerializer
	 */
	public XMLForACPSerialization getXmlSerializer() {
		return xmlSerializer;
	}

	/**
	 * @param xmlSerializer the xmlSerializer to set
	 */
	public void setXmlSerializer(XMLForACPSerialization xmlSerializer) {
		this.xmlSerializer = xmlSerializer;
	}
	
	/**
	 * @return the archiveName
	 */
	public String getArchiveName() {
		return archiveName;
	}

	/**
	 * @param archiveName the archiveName to set
	 */
	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}
	
	public File packageACP() throws IOException{
		acpArchive = new FileOutputStream(archiveName + ".acp");
		byte[] datas = new byte[bufferSize];
		
		BufferedOutputStream buffer = new BufferedOutputStream(acpArchive);
		ZipOutputStream archiveOutput = new ZipOutputStream(buffer);
		
		//Here, we just zip the xml file
		FileInputStream xmlFile = new FileInputStream(xmlSerializer.getFileName());
		BufferedInputStream xmlFileBuffer = new BufferedInputStream(xmlFile, bufferSize);
		ZipEntry entry = new ZipEntry(xmlSerializer.getFileName());
		archiveOutput.putNextEntry(entry);
		int count;
		while((count = xmlFileBuffer.read(datas, 0, bufferSize)) != -1) {
			archiveOutput.write(datas, 0, count);
		}
		archiveOutput.closeEntry();
		xmlFileBuffer.close();
		archiveOutput.close();
		File acp =new File(archiveName + ".acp"); 
		System.out.println("ACP File :"+acp.getAbsolutePath());
		FileUtils.copyFileToDirectory(acp, new File("/Users/davidabad/Desktop/acpTest"));
		return acp;
	}

}
