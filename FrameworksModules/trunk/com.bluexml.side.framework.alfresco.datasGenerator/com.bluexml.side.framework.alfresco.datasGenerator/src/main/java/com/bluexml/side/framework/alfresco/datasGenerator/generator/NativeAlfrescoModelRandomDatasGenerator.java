/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.generator;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.QNamePattern;
import org.alfresco.util.ISO8601DateFormat;

import com.bluexml.side.framework.alfresco.datasGenerator.graph.AlfrescoNode;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.INode;
import com.bluexml.side.framework.alfresco.datasGenerator.structure.IStructure;
import com.bluexml.side.framework.alfresco.datasGenerator.structure.NativeAlfrescoModelStructure;

/**
 * @author davidchevrier
 *
 */
public class NativeAlfrescoModelRandomDatasGenerator implements IRandomGenerator {
	
	private AlfrescoModelRandomDatasGenerator generator;
	private Random randomGenerator = new Random();
	private Calendar calendar = new GregorianCalendar();
	private IStructure nativeAlfrescoStructure;
	
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
	public AlfrescoModelRandomDatasGenerator getGenerator() {
		return generator;
	}
	/**
	 * @param generator the generator to set
	 */
	public void setGenerator(AlfrescoModelRandomDatasGenerator generator) {
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
			data = "";
		}
		if (((QName) property).equals(ContentModel.PROP_NAME)){
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
		String name = type.getTitle();
		//name += "_" + randomGenerator.nextInt(generator.getNumberOfNodes());
		name += "_" + randomGenerator.nextInt();
		return name;
	}
	
}
