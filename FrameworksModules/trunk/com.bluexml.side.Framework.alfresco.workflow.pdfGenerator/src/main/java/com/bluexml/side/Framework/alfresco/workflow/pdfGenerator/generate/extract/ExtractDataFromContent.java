/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.extract;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.AttributeContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidAssociationException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidValueOfParameterException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;

/**
 * @author dchevrier
 *
 */
public class ExtractDataFromContent {

	public static HashMap<String, Object> extractData(NodeRef content, ServiceRegistry services, HashMap<String,String> commands) throws InvalidValueOfParameterException, 
	                                                                                                                                     AttributeContentException, 
	                                                                                                                                     InvalidAssociationException, 
	                                                                                                                                     InvalidContentException {
		HashMap<String, Object> data = new HashMap<String, Object>();
		Set<String> keysCommands = commands.keySet();
		for (String keyCommand : keysCommands) {
			if (commands.get(keyCommand).contains(ConstantsLanguage.CONSTANT_INDICATOR)){
				String constant = commands.get(keyCommand).split(ConstantsLanguage.CONSTANT_INDICATOR)[1];
				data.put(keyCommand,constant);
			}
			else if (commands.get(keyCommand).contains(ConstantsLanguage.NAVIGATION_INDICATOR)){
				NodeRef finalTarget = null;
				String[] navigation = commands.get(keyCommand).split(ConstantsLanguage.NAVIGATION_INDICATOR);
				QName qnameType = services.getNodeService().getType(content);
				finalTarget = followAssociations(services,content,qnameType,navigation,0);
				Object value = extractValueFromContent(services,finalTarget,navigation[navigation.length-1]);
				data.put(keyCommand, value);
			}
			else if (!commands.get(keyCommand).contains(ConstantsLanguage.CONSTANT_INDICATOR) 
					 && !commands.get(keyCommand).contains(ConstantsLanguage.NAVIGATION_INDICATOR)){
				Object value = extractValueFromContent(services,content,commands.get(keyCommand));
				data.put(keyCommand, value);
			}
			else {
				throw new InvalidValueOfParameterException(InvalidValueOfParameterException.BAD_FORMAT);
			}
		}
		return data;
	}

	private static Object extractValueFromContent(ServiceRegistry services, NodeRef content, String attribute) throws AttributeContentException {
		Object data = null;
		Map<QName,Serializable> properties = services.getNodeService().getProperties(content);
		Set<QName> propertiesNames = properties.keySet();
		boolean attributeExists = false;
		for (QName propertyName : propertiesNames) {
			if (propertyName.toString().contains(attribute)){
				attributeExists = true;
				data = (Object) properties.get(propertyName);
			}
		}
		if (!attributeExists){
			throw new AttributeContentException(AttributeContentException.DOES_NOT_EXISTS);
		}
//		if (data instanceof Date){
//			data = formatDate(data);
//		}
		return data;
	}
	
	private static NodeRef followAssociations(ServiceRegistry services, NodeRef content, QName qnameType, String[] navigation, int indexNavigation) throws InvalidAssociationException,
	                                                                                                                                                       InvalidContentException {
		NodeRef finalTarget = null;
		String uri = qnameType.getNamespaceURI();
		List<ChildAssociationRef> associations = services.getNodeService().getChildAssocs(content);
		for (ChildAssociationRef association : associations){
			if (association.getQName().toString().contains(uri) && association.getQName().toString().contains(navigation[indexNavigation])){
				NodeRef target = association.getChildRef();
				if (target != null){
					if (indexNavigation < navigation.length-2){
						QName targetType = services.getNodeService().getType(target);
						followAssociations(services,target,targetType,navigation,indexNavigation++);
					}
					else{
						finalTarget = target;
					}
				}
				else{
					throw new InvalidContentException(InvalidContentException.DOES_NOT_EXISTS);
				}
			}
			else{
				throw new InvalidAssociationException(InvalidAssociationException.DOES_NOT_EXISTS);
			}
		}
		return finalTarget;
	}

}
