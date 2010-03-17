/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.fill;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ISO8601DateFormat;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.AttributeContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidAssociationException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidFormatParameterException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidValueOfParameterException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;

/**
 * @author dchevrier
 *
 */
public class FillDataContent {

	public static void fillContent(ServiceRegistry services, NodeRef content, HashMap<String, String> importCommands, HashMap<String, String> data) throws InvalidValueOfParameterException, 
																																						   AttributeContentException, 
																																						   InvalidAssociationException, 
																																						   InvalidNodeRefException, 
																																						   ParseException, InvalidFormatParameterException {
		Collection<String> navigations = new ArrayList<String>();
		Map<String,String> datesFormats = new HashMap<String, String>();
		Set<String> keysCommands = importCommands.keySet();
		NodeRef finalTarget = null;
		for(String key : keysCommands){
			if (key.contains(ConstantsLanguage.FORMAT_DATE_INDICATOR)){
				datesFormats.putAll(getDateFormat(key,importCommands.get(key)));
			}
			else if (importCommands.get(key).contains(ConstantsLanguage.NAVIGATION_INDICATOR)){
				String[] navigation = importCommands.get(key).split(ConstantsLanguage.NAVIGATION_INDICATOR);
				String pureNavigation = deleteLastElement(navigation);
				if (!navigations.contains(pureNavigation)){
					navigations.add(pureNavigation);
					QName qnameType = services.getNodeService().getType(content);
					finalTarget = followAssociations(services,content,qnameType,navigation,0);
				}
				fillAttributeContent(services,finalTarget,navigation[navigation.length-1],data.get(key),datesFormats);
			}
			else if(importCommands.get(key).contains(ConstantsLanguage.CONSTANT_INDICATOR)){
				throw new InvalidValueOfParameterException(InvalidValueOfParameterException.BAD_FORMAT);
			}
			else {
				fillAttributeContent(services,content,importCommands.get(key),data.get(key),datesFormats);
			}
		}
	}

	private static Map<String, String> getDateFormat(String key, String format) throws InvalidFormatParameterException {
		Map<String, String> map = new HashMap<String, String>();
		String[] dateFormat = key.split(ConstantsLanguage.PARAMETER_SEPARATOR);
		if (dateFormat.length == 0){
			throw new InvalidFormatParameterException(InvalidFormatParameterException.BAD_FORMAT);
		}
		else{
			map.put(dateFormat[0], format);
		}
		return map;
	}

	private static String deleteLastElement(String[] navigation) {
		StringBuffer pureNavigation = new StringBuffer();
		for (int i = 0; i < navigation.length-1; i++){
			pureNavigation.append(navigation[i]);
		}
		return pureNavigation.toString();
	}

	private static NodeRef createTargetContent(ServiceRegistry services, NodeRef content, QName association, QName finalQnameTarget) {
		NodeRef parent = services.getNodeService().getPrimaryParent(content).getParentRef();
		TypeDefinition targetTypeDef = services.getDictionaryService().getType(finalQnameTarget);
		QName targetType = targetTypeDef.getName();
		Map<QName,Serializable> properties = createNameProperty(targetTypeDef);
		ChildAssociationRef childassoc = services.getNodeService().createNode(parent, ContentModel.ASSOC_CONTAINS, association, targetType, properties);
		return childassoc.getChildRef();
	}

	public static Map<QName, Serializable> createNameProperty(TypeDefinition targetTypeDef) {
		Map<QName, Serializable> propertyName = new HashMap<QName, Serializable>();
		QName key = ContentModel.PROP_NAME;
		String value = createNameValue(targetTypeDef);
		propertyName.put(key, value);
		return propertyName;
	}

	private static String createNameValue(TypeDefinition targetTypeDef) {
		Random generator = new Random();
		String id = String.valueOf(generator.nextInt());
		String name = targetTypeDef.getTitle();
		return name + "_" + id;
	}

	private static NodeRef followAssociations(ServiceRegistry services, NodeRef content, QName qnameType, String[] navigation, int indexNavigation) throws InvalidAssociationException {
		NodeRef finalTarget = null;
		TypeDefinition type = services.getDictionaryService().getType(qnameType);
		String uri = qnameType.getNamespaceURI();
		Map<QName,AssociationDefinition> associations = type.getAssociations();
		Set<QName> associationsNames = associations.keySet();
		for (QName associationName : associationsNames){
			if (associationName.toString().contains(uri) && associationName.toString().contains(navigation[indexNavigation])){
				QName target = associations.get(associationName).getTargetClass().getName();
				if (indexNavigation < navigation.length-2){
					followAssociations(services,content,target,navigation,indexNavigation++);
				}
				else{
					finalTarget = createTargetContent(services,content,associationName,target);
				}
			}
			else{
				throw new InvalidAssociationException(InvalidAssociationException.DOES_NOT_EXISTS);
			}
		}
		return finalTarget;
	}

	private static void fillAttributeContent(ServiceRegistry services, NodeRef content, String importCommand, String data, Map<String,String> datesFormats) throws AttributeContentException, InvalidNodeRefException, ParseException {
		QName contentType = services.getNodeService().getType(content);
		Map<QName,Object> attributes = getAttributes(services,contentType, new HashMap<QName,Object>());
		Set<QName> names = attributes.keySet();
		boolean attributeExists = false;
		String uri = contentType.getNamespaceURI();
		for (QName name : names) {
			if (name.toString().contains(uri) && name.toString().contains(importCommand)){
				attributeExists = true;
				services.getNodeService().setProperty(content,name,convertToAlfrescoType(data,importCommand,datesFormats));
			}
		}
		if (!attributeExists){
			throw new AttributeContentException(AttributeContentException.DOES_NOT_EXISTS);
		}
	}

	private static Map<QName,Object> getAttributes(ServiceRegistry services, QName contentType, Map<QName,Object> attributes) {
		TypeDefinition nodeType = services.getDictionaryService().getType(contentType);
		attributes.putAll(nodeType.getProperties());
		List<AspectDefinition> aspects = nodeType.getDefaultAspects();
		for (AspectDefinition aspect : aspects) {
			attributes.putAll(aspect.getProperties());
		}
		QName parent = nodeType.getParentName();
		if (parent != null){
			getAttributes(services,parent,attributes);
			parent = services.getDictionaryService().getType(parent).getParentName();
		}
		return attributes;
	}

	private static Serializable convertToAlfrescoType(String data, String importCommandKey, Map<String,String> datesFormats) throws ParseException {
		Serializable alfrescoData = data;
		if (data.contains(ConstantsLanguage.DATE_VALUE_INDICATOR)){
			alfrescoData = convertToAlfrescoDate(data,importCommandKey,datesFormats);
		}
		else if (data.equals(ConstantsLanguage.BOOLEAN_VALUES[0])){//and itemType = checkBox
			alfrescoData = Boolean.TRUE;
		}
		else if (data.equals(ConstantsLanguage.BOOLEAN_VALUES[1])){
			alfrescoData = Boolean.FALSE;
		}
		return alfrescoData;
	}

	private static Serializable convertToAlfrescoDate(String data, String importCommandKey, Map<String,String> datesFormats) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(datesFormats.get(importCommandKey));
		Date date = dateFormat.parse(data);
		return ISO8601DateFormat.format(date);
	}
	


}
