/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.QNamePattern;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoNode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.NativeAlfrescoNode;

/**
 * @author davidchevrier
 *
 */
public class StructureTagFactory extends AbstractXmlOutput {
	
	public void init(){
		xmlOutPut = new StringBuffer();
	}
	
	public StringBuffer createOpenTypeTag(INode node){
		xmlOutPut = clear();
		String sideTag = ((AlfrescoNode) node).getTypeDefinition().getName().toPrefixString();
		xmlOutPut.append(sideTag);
		xmlOutPut.append(TagsConstants.BLANK_SEPARATOR);
		return xmlOutPut;
	}
	
	public StringBuffer addEmptyAttributeToOpenTag(){
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.BLANK_SEPARATOR);
		xmlOutPut.append(TagsConstants.DECLARATIVE_PREFIX_NAMESPACE);
		xmlOutPut.append(TagsConstants.DEFINITION_SEPARATOR);
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		xmlOutPut.append(TagsConstants.BLANK_SEPARATOR);
		return xmlOutPut;
	}
	
	public StringBuffer fillAttributesOfOpenTag(Map<String,String> attributes){
		xmlOutPut = clear();
		Set<String> uris = ((HashMap<String,String>) attributes).keySet();
		for (String uri : uris) {
			xmlOutPut.append(TagsConstants.BLANK_SEPARATOR);
			xmlOutPut.append(TagsConstants.DECLARATIVE_PREFIX_NAMESPACE);
			xmlOutPut.append(TagsConstants.SEPARATOR);
			xmlOutPut.append(attributes.get(uri));
			xmlOutPut.append(TagsConstants.DEFINITION_SEPARATOR);
			xmlOutPut.append(TagsConstants.URI_BRAKET);
			xmlOutPut.append(uri);
			xmlOutPut.append(TagsConstants.URI_BRAKET);
		}
		return xmlOutPut;
	}
	
	public StringBuffer addChildAttributeToOpenTypeTag(INode node){
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_CHILDNAME);
		xmlOutPut.append(TagsConstants.DEFINITION_SEPARATOR);
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		xmlOutPut.append(NamespaceService.CONTENT_MODEL_PREFIX);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		Object data = new Object();
		Map<QNamePattern,Object> nativeProperties = ((NativeAlfrescoNode) ((AlfrescoNode) node).getNativeNode()).getNativeDatasProperties();
		if (nativeProperties.containsKey(ContentModel.PROP_NAME)){
			data = nativeProperties.get(ContentModel.PROP_NAME);
		}
		xmlOutPut.append(data.toString());
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		return xmlOutPut;
	}
	
	public StringBuffer addPathrefAttributeToOpenSourceAssociationTag(IArc arc){
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_PATHREF);
		xmlOutPut.append(TagsConstants.DEFINITION_SEPARATOR);
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		xmlOutPut.append(NamespaceService.CONTENT_MODEL_PREFIX);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		AlfrescoNode sourceNode =(AlfrescoNode) ((AlfrescoArc) arc).getSource();
		Object data = new Object();
		Map<QNamePattern,Object> nativeProperties = ((NativeAlfrescoNode) ((AlfrescoNode) sourceNode).getNativeNode()).getNativeDatasProperties();
		if (nativeProperties.containsKey(ContentModel.PROP_NAME)){
			data = nativeProperties.get(ContentModel.PROP_NAME);
		}
		xmlOutPut.append(data.toString().replace(" ", TagsConstants.BLANK_REPLACE_IN_PATHREF));
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		return xmlOutPut;
	}
	
	public StringBuffer createOpenPropertiesTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_PROPERTIES);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createClosePropertiesTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_PROPERTIES);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createSideOpenPropertyTag(PropertyDefinition property) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(property.getName().toPrefixString());
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createSideClosePropertyTag(PropertyDefinition property) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		xmlOutPut.append(property.getName().toPrefixString());
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createNativeOpenPropertyTag(QNamePattern nativeProperty) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		String prefix = new String();
		if (((QName) nativeProperty).equals(ContentModel.PROP_NODE_UUID)||((QName) nativeProperty).equals(ContentModel.PROP_NODE_DBID)){
			prefix = NamespaceService.SYSTEM_MODEL_PREFIX;
		}
		else{
			prefix = NamespaceService.CONTENT_MODEL_PREFIX;
		}
		xmlOutPut.append(prefix);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(((QName) nativeProperty).getLocalName());
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createNativeClosePropertyTag(QNamePattern nativeProperty) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		String prefix = new String();
		if (((QName) nativeProperty).equals(ContentModel.PROP_NODE_UUID)||((QName) nativeProperty).equals(ContentModel.PROP_NODE_DBID)){
			prefix = NamespaceService.SYSTEM_MODEL_PREFIX;
		}
		else{
			prefix = NamespaceService.CONTENT_MODEL_PREFIX;
		}
		xmlOutPut.append(prefix);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(((QName) nativeProperty).getLocalName());
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}
	
	public StringBuffer createOpenSourceAndTargetAssociationTag(){
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_ASSOCIATION);
		xmlOutPut.append(TagsConstants.BLANK_SEPARATOR);
		return xmlOutPut;
	}

	public StringBuffer createOpenAssociationTag(AssociationDefinition typeAssociation) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(typeAssociation.getName().toPrefixString());
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createCloseAssociationTag(AssociationDefinition typeAssociation) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		xmlOutPut.append(typeAssociation.getName().toPrefixString());
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer addPathrefAttributeToOpenTargetAssociationTag(IArc arc) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_PATHREF);
		xmlOutPut.append(TagsConstants.DEFINITION_SEPARATOR);
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		xmlOutPut.append(NamespaceService.CONTENT_MODEL_PREFIX);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		AlfrescoNode targetNode =(AlfrescoNode) ((AlfrescoArc) arc).getTarget();
		Object data = new Object();
		Map<QNamePattern,Object> nativeProperties = ((NativeAlfrescoNode) ((AlfrescoNode) targetNode).getNativeNode()).getNativeDatasProperties();
		if (nativeProperties.containsKey(ContentModel.PROP_NAME)){
			data = nativeProperties.get(ContentModel.PROP_NAME);
		}
		xmlOutPut.append(data.toString().replace(" ", TagsConstants.BLANK_REPLACE_IN_PATHREF));
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		return xmlOutPut;
	}

	public StringBuffer createCloseAssociationTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_ASSOCIATION);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createOpenFirstViewTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.BLANK_SEPARATOR);
		xmlOutPut.append(TagsConstants.DECLARATIVE_PREFIX_NAMESPACE);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(NamespaceService.REPOSITORY_VIEW_PREFIX);
		xmlOutPut.append(TagsConstants.DEFINITION_SEPARATOR);
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		xmlOutPut.append(NamespaceService.REPOSITORY_VIEW_1_0_URI);
		xmlOutPut.append(TagsConstants.URI_BRAKET);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createCloseLastViewTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createOpenAssociationsTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_ASSOCIATIONS);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createCloseAssociationsTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_ASSOCIATIONS);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createCloseTypeTag(INode node) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		String sideTag = ((AlfrescoNode) node).getTypeDefinition().getName().toPrefixString();
		xmlOutPut.append(sideTag);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createOpenAclTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_ACL);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createCloseAclTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_ACL);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createOpenAspectTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_ASPECTS);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createCloseAspectTag() {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		xmlOutPut.append(TagsConstants.PRINCIPAL_TAG);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(TagsConstants.TAG_ARG_ASPECTS);
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createOpenNativeAspects(QNamePattern nativeAspect) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(NamespaceService.CONTENT_MODEL_PREFIX);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(((QName) nativeAspect).getLocalName());
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}

	public StringBuffer createCloseNativeAspects(QNamePattern nativeAspect) {
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_CLOSING_TAG);
		xmlOutPut.append(NamespaceService.CONTENT_MODEL_PREFIX);
		xmlOutPut.append(TagsConstants.SEPARATOR);
		xmlOutPut.append(((QName) nativeAspect).getLocalName());
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}


}
