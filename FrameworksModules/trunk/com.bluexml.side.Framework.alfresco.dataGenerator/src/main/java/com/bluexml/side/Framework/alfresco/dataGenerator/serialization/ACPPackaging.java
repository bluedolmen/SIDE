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
import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.bluexml.side.Framework.alfresco.dataGenerator.generator.NativeAlfrescoModelRandomDataGenerator;

/**
 * @author davidchevrier
 *
 */
public class ACPPackaging implements ISerialization {
	
	private OutputStream acpArchive;
	private String archiveName;
	private XMLForACPSerializer xmlSerializer;
	private NativeAlfrescoModelRandomDataGenerator nativeGenerator;
	
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
	
//	/**
//	 * @return the xmlSerializer
//	 */
//	public XMLForACPSerialization getXmlSerializer() {
//		return xmlSerializer;
//	}
//
//	/**
//	 * @param xmlSerializer the xmlSerializer to set
//	 */
//	public void setXmlSerializer(XMLForACPSerialization xmlSerializer) {
//		this.xmlSerializer = xmlSerializer;
//	}
	
	/**
	 * @return the archiveName
	 */
	public String getArchiveName() {
		return archiveName;
	}

	public XMLForACPSerializer getXmlSerializer() {
		return xmlSerializer;
	}

	public void setXmlSerializer(XMLForACPSerializer xmlSerializer) {
		this.xmlSerializer = xmlSerializer;
	}

	/**
	 * @param archiveName the archiveName to set
	 */
	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}
	
	/**
	 * @return the nativeGenerator
	 */
	public NativeAlfrescoModelRandomDataGenerator getNativeGenerator() {
		return nativeGenerator;
	}

	/**
	 * @param nativeGenerator the nativeGenerator to set
	 */
	public void setNativeGenerator(
			NativeAlfrescoModelRandomDataGenerator nativeGenerator) {
		this.nativeGenerator = nativeGenerator;
	}

	public File packageACP() throws IOException{
		acpArchive = new FileOutputStream(archiveName + ".acp");
		byte[] datas = new byte[bufferSize];
		
		BufferedOutputStream buffer = new BufferedOutputStream(acpArchive);
		ZipOutputStream archiveOutput = new ZipOutputStream(buffer);
		
		//zip xml first
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
		
		//then, zip documents
		Collection<File> documents = nativeGenerator.getDocuments();
		Collection<String> entries = new ArrayList<String>();
		for (File file : documents) {
			FileInputStream doc = new FileInputStream(file.getAbsolutePath());
			BufferedInputStream docBuffer = new BufferedInputStream(doc, bufferSize);
			ZipEntry e = new ZipEntry(file.getName());
			if (!entries.contains(e.getName())){
				entries.add(e.getName());
				archiveOutput.putNextEntry(e);
				int counter;
				while((counter = docBuffer.read(datas, 0, bufferSize)) != -1) {
					archiveOutput.write(datas, 0, counter);
				}
			}
			archiveOutput.closeEntry();
			docBuffer.close();
		}
		
		archiveOutput.close();
		
		documents.clear();
		nativeGenerator.setDocuments(documents);
		
		File acp =new File(archiveName + ".acp");
		//System.out.println("ACP File :"+acp.getAbsolutePath());
		return acp;
	}
	
	public boolean clean(File acp){
		acp.deleteOnExit();
		File xml = new File(xmlSerializer.getFileName());
		xml.deleteOnExit();
		
		return true;
	}

}
