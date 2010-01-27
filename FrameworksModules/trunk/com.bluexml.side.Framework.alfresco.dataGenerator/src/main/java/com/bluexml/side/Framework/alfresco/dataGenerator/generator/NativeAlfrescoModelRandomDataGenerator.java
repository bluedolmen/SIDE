/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.QNamePattern;
import org.alfresco.util.ISO8601DateFormat;

import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.NativeAlfrescoModelStructure;

/**
 * @author davidchevrier
 *
 */
public class NativeAlfrescoModelRandomDataGenerator implements IRandomGenerator {
	
	private AlfrescoModelRandomDataGenerator generator;
	private Random randomGenerator = new Random();
	private Calendar calendar = new GregorianCalendar();
	private IStructure nativeAlfrescoStructure;
	
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
	public IStructure getNativeAlfrescoStructure() {
		return nativeAlfrescoStructure;
	}
	/**
	 * @param nativeAlfrescoStructure the nativeAlfrescoStructure to set
	 */
	public void setNativeAlfrescoStructure(IStructure nativeAlfrescoStructure) {
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
	
	public Map<QNamePattern,Object> generateNativeDatasProperties(TypeDefinition type) {
		Map<QNamePattern,Object> datasProperties = new HashMap<QNamePattern, Object>();
		Collection<QNamePattern> nativeProperites = ((NativeAlfrescoModelStructure) nativeAlfrescoStructure).getNativeMandatoryProperties();
		for (QNamePattern property : nativeProperites) {
			datasProperties.put(property,fillNativeDataProperty(property,type));
		}
		return datasProperties;
	}
	private Object fillNativeDataProperty(QNamePattern property, TypeDefinition type) {
		
		Object data = new Object();
		if (((QName) property).equals(ContentModel.PROP_CONTENT)){
			data = generateUrl(type);
		}
		else if (((QName) property).equals(ContentModel.PROP_NAME)){
			data = generateName(type);
		}
		else if (((QName) property).equals(ContentModel.PROP_CREATOR)){
			data = NativeConstants.CREATOR;
		}
		else if(((QName) property).equals(ContentModel.PROP_CREATED)||((QName) property).equals(ContentModel.PROP_MODIFIED)){
			data = generateAlfrescoDate();
		}
		else if (((QName) property).equals(ContentModel.PROP_MODIFIER)){
			data = NativeConstants.MODIFIER;
		}
		else if (((QName) property).equals(ContentModel.PROP_NODE_UUID)){
			data = generateUUID();
		}
		else if (((QName) property).equals(ContentModel.PROP_NODE_DBID)){
			data = generateDBID();
		}
		return data;
		
	}
	
	private Object generateDBID() {
		Object data = new Object();
		data = randomGenerator.nextInt();
		while ((Integer) data < 0){
			data = randomGenerator.nextInt();
		}
		return data;
	}
	private Object generateAlfrescoDate() {
		Date currentDate = calendar.getTime();
		return ISO8601DateFormat.format(currentDate);
	}
	private Object generateUUID() {
		Long idarg1 = randomGenerator.nextLong();
		Long idarg2 = randomGenerator.nextLong();
		return new UUID(idarg1,idarg2);
	}
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
	
	private Object getDocumentLocale(File document) {
		Locale locale = Locale.getDefault();
		return locale.toString();
	}
	
	private Object getDocumentEncoding(File document) {
		// waiting for something else ...:
		return "utf-8";
	}
	
	private Object getDocumentSize(File document) {	
		return document.length();
	}
	
	private Object getMimeTypeDocument(File document) {
		return new MimetypesFileTypeMap().getContentType(document.getName());
	}
	
	private Object getDocumentTitle(File document) {
		return document.getName();
	}
	
	private File chooseRandomlyDocument() {
		File folder = new File(pathToDocumentsFolder);
		File[] docs = folder.listFiles();
		File document = null;
		if (docs.length > 0){
			document = docs[randomGenerator.nextInt(docs.length)];
			documents.add(document);
		}
		return document;
	}
	
	public Map<QNamePattern,Object> generateNativeDatasAspects(TypeDefinition type){
		Map<QNamePattern,Object> datasAspects = new HashMap<QNamePattern, Object>();
		Collection<QNamePattern> aspects = ((NativeAlfrescoModelStructure) nativeAlfrescoStructure).getNativeAspects();
		for (QNamePattern aspect : aspects) {
			datasAspects.put(aspect,fillNativeDataAspect(aspect,type));
		}
		return datasAspects;
	}
	private Object fillNativeDataAspect(QNamePattern aspect, TypeDefinition type) {
		Object data = new Object();
		if (((QName) aspect).equals(ContentModel.ASPECT_TEMPORARY)){
			data = "";
		}
		return data;
	}
	
}
