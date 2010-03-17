/**
 * This class allows generation of data for native Alfresco properties
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.activation.MimetypesFileTypeMap;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.QNamePattern;

import com.bluexml.side.Framework.alfresco.dataGenerator.structure.NativeAlfrescoModelStructure;

/**
 * @author davidchevrier
 *
 */
public class NativeAlfrescoModelRandomDataGenerator {
	
	private AlfrescoModelRandomDataGenerator generator;
	private Random randomGenerator = new Random();
	private Calendar calendar = new GregorianCalendar();
	private NativeAlfrescoModelStructure nativeAlfrescoStructure;
	
	private String pathToDocumentsFolder;
	private Collection<File> documents = new ArrayList<File>();
	
	private static String separator = "|";
	
	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}
	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	/**
	 * @return the nativeAlfrescoStructure
	 */
	public NativeAlfrescoModelStructure getNativeAlfrescoStructure() {
		return nativeAlfrescoStructure;
	}
	/**
	 * @param nativeAlfrescoStructure the nativeAlfrescoStructure to set
	 */
	public void setNativeAlfrescoStructure(NativeAlfrescoModelStructure nativeAlfrescoStructure) {
		this.nativeAlfrescoStructure = nativeAlfrescoStructure;
	}
	
	/**
	 * @return the generator
	 */
	public AlfrescoModelRandomDataGenerator getGenerator() {
		return generator;
	}
	/**
	 * @param generator the generator to set
	 */
	public void setGenerator(AlfrescoModelRandomDataGenerator generator) {
		this.generator = generator;
	}
	/**
	 * @return the randomGenerator
	 */
	public Random getRandomGenerator() {
		return randomGenerator;
	}
	/**
	 * @param randomGenerator the randomGenerator to set
	 */
	public void setRandomGenerator(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}
	
	/**
	 * @return the pathToDocumentsFolder
	 */
	public String getPathToDocumentsFolder() {
		return pathToDocumentsFolder;
	}
	/**
	 * @param pathToDocumentsFolder the pathToDocumentsFolder to set
	 */
	public void setPathToDocumentsFolder(String pathToDocumentsFolder) {
		this.pathToDocumentsFolder = pathToDocumentsFolder;
	}
	
	/**
	 * @return the documents
	 */
	public Collection<File> getDocuments() {
		return documents;
	}
	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(Collection<File> documents) {
		this.documents = documents;
	}
	
	/**
	 * 
	 * @param type
	 * @return the filled native properties (data per native property) 
	 */
	public Map<QNamePattern,Object> generateNativeDatasProperties(TypeDefinition type) {
		Map<QNamePattern,Object> datasProperties = new HashMap<QNamePattern, Object>();
		Collection<QNamePattern> nativeProperites = ((NativeAlfrescoModelStructure) nativeAlfrescoStructure).getNativeMandatoryProperties();
		for (QNamePattern property : nativeProperites) {
			datasProperties.put(property,fillNativeDataProperty(property,type));
		}
		return datasProperties;
	}
	
	/**
	 * 
	 * @param property
	 * @param type
	 * @return the filled data of property
	 */
	private Object fillNativeDataProperty(QNamePattern property, TypeDefinition type) {
		
		Object data = new Object();
		if (((QName) property).equals(ContentModel.PROP_CONTENT)){
			data = generateUrl(type);
		}
		else if (((QName) property).equals(ContentModel.PROP_NAME)){
			data = generateName(type);
		}
		return data;
		
	}
	
	/**
	 * 
	 * @param type
	 * @return the name of the node associated with type definition
	 */
	private String generateName(TypeDefinition type) {
		String name = null;
		if (type.getTitle() != null){
			name = type.getTitle();
		}
		else{
			String qName = type.getName().toString();
			String[] parts = qName.split("}");
			name = parts[parts.length-1];
		}
		if (generator.getScenario().equals("random")){
			name += "_" + randomGenerator.nextInt();
		}
		else if (generator.getScenario().equals("incremental")){
			name += "_" + AlfrescoModelRandomDataGenerator.getIndexType().get(type).toString();
		}
		return name;
	}
	
	/**
	 * 
	 * @param type
	 * @return the url content of node associated with type definition
	 */
	private String generateUrl(TypeDefinition type){
		StringBuffer url = new StringBuffer();
		File document = chooseRandomlyDocument();
		if (document != null){
			url.append("contentUrl=");
			url.append(getDocumentTitle(document));
			url.append(separator);
			url.append("mimetype=");
			url.append(getMimeTypeDocument(document));
			url.append(separator);
			url.append("size=");
			url.append(getDocumentSize(document));
			url.append(separator);
			url.append("encoding=");
			url.append(getDocumentEncoding(document));
			url.append(separator);
			url.append("locale=");
			url.append(getDocumentLocale(document));
		}
		return url.toString();
	}
	
	/**
	 * 
	 * @param document
	 * @return the locale of document
	 */
	private Object getDocumentLocale(File document) {
		Locale locale = Locale.getDefault();
		return locale.toString();
	}
	
	/**
	 * 
	 * @param document
	 * @return the document encoding
	 */
	private Object getDocumentEncoding(File document) {
		// waiting for something else ...:
		return "utf-8";
	}
	
	/**
	 * 
	 * @param document
	 * @return the document size
	 */
	private Object getDocumentSize(File document) {	
		return document.length();
	}
	
	/**
	 * 
	 * @param document
	 * @return the mime type of document
	 */
	private Object getMimeTypeDocument(File document) {
		return new MimetypesFileTypeMap().getContentType(document.getName());
	}
	
	/**
	 * 
	 * @param document
	 * @return the document title
	 */
	private Object getDocumentTitle(File document) {
		return document.getName();
	}
	
	/**
	 * Randomly choose a document (.pdf, .txt, ...) in indicated folder 
	 * @return a randomly choosed document
	 */
	private File chooseRandomlyDocument() {
		File document = null;
		if (!pathToDocumentsFolder.equals("")){
			File folder = new File(pathToDocumentsFolder);
			File[] docs = folder.listFiles();
			if (docs != null && docs.length > 0){
				document = docs[randomGenerator.nextInt(docs.length)];
				documents.add(document);
			}
		}
		return document;
	}
	
	/**
	 * 
	 * @param type
	 * @return filled native aspects (data per aspect)
	 */
	public Map<QNamePattern,Object> generateNativeDatasAspects(TypeDefinition type){
		Map<QNamePattern,Object> datasAspects = new HashMap<QNamePattern, Object>();
		Collection<QNamePattern> aspects = ((NativeAlfrescoModelStructure) nativeAlfrescoStructure).getNativeAspects();
		for (QNamePattern aspect : aspects) {
			datasAspects.put(aspect,fillNativeDataAspect(aspect,type));
		}
		return datasAspects;
	}
	
	/**
	 * 
	 * @param aspect
	 * @param type
	 * @return the filled data native aspect
	 */
	private Object fillNativeDataAspect(QNamePattern aspect, TypeDefinition type) {
		Object data = new Object();
		if (((QName) aspect).equals(ContentModel.ASPECT_TEMPORARY)){
			data = "";
		}
		return data;
	}
	
}
