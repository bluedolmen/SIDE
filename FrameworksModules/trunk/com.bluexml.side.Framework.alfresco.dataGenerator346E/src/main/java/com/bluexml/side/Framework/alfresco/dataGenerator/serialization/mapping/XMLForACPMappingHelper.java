/**
 * This class contains useful methods to help builder to build xml elements
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QNamePattern;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoNode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.NativeAlfrescoNode;

/**
 * @author dchevrier
 *
 */
public class XMLForACPMappingHelper {
	
	private XMLForACPMappingBuilder builder;
	private XMLForACPMappingServices services;
	
	public XMLForACPMappingBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(XMLForACPMappingBuilder builder) {
		this.builder = builder;
	}
	
	public XMLForACPMappingServices getServices() {
		return services;
	}

	public void setServices(XMLForACPMappingServices services) {
		this.services = services;
	}
	
	/**
	 * create the Document's root element
	 * @return the xml's root element
	 */
	public Element createRoot(){
		String tag = services.createTag(NamespaceService.REPOSITORY_VIEW_PREFIX,NamespaceService.REPOSITORY_VIEW_PREFIX);
		Element root = builder.getDocument().addElement(tag,NamespaceService.REPOSITORY_VIEW_1_0_URI);
		return root;
	}
	
	/**
	 * create the xml's element corresponding to type of generated node
	 * @param root
	 * @param node
	 * @return element corresponding to given generated node's type
	 */
	public Element createType(Element root, INode node){
		Element type = root.addElement(createType(node));
		type.addNamespace(Constants.NATIVE_SIDE_PREFIX, Constants.NATIVE_SIDE_URI);
		return type;
	}
	
	/**
	 * create the dom4j qualified name of given generated node
	 * @param node
	 * @return the dom4j qualified name of given generated node
	 */
	public QName createType(INode node) {
		String qualifiedName = services.getQualifiedName(node);
		String name = services.getName(qualifiedName);
		String prefix = services.getPrefix(qualifiedName);
		String uri = services.getSIDEUri(node);
		Namespace namespace = new Namespace(prefix,uri);
		return new QName(name,namespace,qualifiedName);
	}
	
	/**
	 * create xml's elements corresponding to native Alfresco's aspects of given node
	 * @param type
	 * @param node
	 */
	public void createAspects(Element type, INode node) {
		Element aspects = type.addElement(services.createTag(NamespaceService.REPOSITORY_VIEW_PREFIX, Constants.ASPECTS));
		createNativeAspects(aspects,node);
	}
	
	/**
	 * create and fill the xml's elements corresponding to native Alfresco's aspects of given node 
	 * under aspects element
	 * @param aspects
	 * @param node
	 */
	private void createNativeAspects(Element aspects, INode node) {
		Map<QNamePattern,Object> dataAspects = ((NativeAlfrescoNode) ((AlfrescoNode) node).getNativeNode()).getNativeDatasAspects();
		Set<QNamePattern> nativeAspects = dataAspects.keySet();
		for (QNamePattern nativeAspect : nativeAspects) {
			String tag = ((org.alfresco.service.namespace.QName) nativeAspect).toPrefixString();
			String data = dataAspects.get(nativeAspect).toString();
			String uri = ((org.alfresco.service.namespace.QName) nativeAspect).getNamespaceURI();
			aspects.addElement(tag,uri).addText(data);	
		}
		
	}
	
	/**
	 * create and fill xml's elements corresponding to SIDE properties
	 * @param prop
	 * @param propertiesData
	 */
	private void createAndFillSIDEProperties(Element prop, Map<PropertyDefinition, Object> propertiesData) {
		Set<PropertyDefinition> properties = propertiesData.keySet();
		for (PropertyDefinition property : properties) {
			String tag = property.getName().toPrefixString();
			String uri = property.getName().getNamespaceURI();
			String data = propertiesData.get(property).toString();
			prop.addElement(tag, uri).addText(data);
		}
		
	}
	
	/**
	 * create and fill xml's elements corresponding to all properties of given generated node
	 * @param type
	 * @param node
	 */
	public void createProperties(Element type, INode node) {
		Element properties = type.addElement(services.createTag(NamespaceService.REPOSITORY_VIEW_PREFIX,Constants.PROPERTIES));
		
		Map<PropertyDefinition, Object> dataSIDEProperties = ((AlfrescoNode)node).getDatasProperties();
		createAndFillSIDEProperties(properties,dataSIDEProperties);
		
		Map<QNamePattern,Object> dataNativeProperties = ((NativeAlfrescoNode)((AlfrescoNode)node).getNativeNode()).getNativeDatasProperties();
		createAndFillNativeProperties(properties,dataNativeProperties);
		
		Map<AspectDefinition,Map<PropertyDefinition,Object>> dataSIDEAspects = ((AlfrescoNode) node).getDataAspects();
		Set<AspectDefinition> sideAspects = dataSIDEAspects.keySet();
		for (AspectDefinition sideAspect : sideAspects) {
			createAndFillSIDEProperties(properties,dataSIDEAspects.get(sideAspect));
		}
	}
	
	/**
	 * create and fill xml's elements corresponding to native properties of given generated node
	 * @param properties
	 * @param dataNativeProperties
	 */
	private void createAndFillNativeProperties(Element properties, Map<QNamePattern, Object> dataNativeProperties) {
		Set<QNamePattern> nativePorperties = dataNativeProperties.keySet();
		for (QNamePattern nativeProperty : nativePorperties) {
			String tag = ((org.alfresco.service.namespace.QName)nativeProperty).toPrefixString();
			String uri = ((org.alfresco.service.namespace.QName)nativeProperty).getNamespaceURI();
			String data = dataNativeProperties.get(nativeProperty).toString();
			if (((org.alfresco.service.namespace.QName)nativeProperty).equals(ContentModel.PROP_TITLE)){
				data = services.getNodeName(dataNativeProperties);
			}
			properties.addElement(tag,uri).addText(data);
		}
		
	}
	
	/**
	 * create and fill the xml's elements corresponding to generated arcs of given source node
	 * @param root
	 * @param src
	 * @return the associations root xml element
	 */
	public Element createAssociation(Element root, INode src) {
		String tag = services.createTag(NamespaceService.REPOSITORY_VIEW_PREFIX,Constants.REFERENCE);
		
		String attributeName = services.createTag(NamespaceService.REPOSITORY_VIEW_PREFIX,Constants.PATHREF);
		Map<QNamePattern,Object> nativeDataProperties = ((NativeAlfrescoNode)((AlfrescoNode)src).getNativeNode()).getNativeDatasProperties();
		String attrValue = services.getNodeName(nativeDataProperties).replace(" ", Constants.BLANK_REPLACE);
		String attributeValue = services.createTag(NamespaceService.CONTENT_MODEL_PREFIX,attrValue);
		
		return root.addElement(tag,NamespaceService.REPOSITORY_VIEW_1_0_URI).addAttribute(attributeName,attributeValue);
	}
	
	/**
	 * create and fill the xml's elements corresponding to target of given generated arc
	 * @param assoc
	 * @param arc
	 */
	public void createTargetAssociation(Element assoc, IArc arc) {
		org.alfresco.service.namespace.QName arcQName = ((AlfrescoArc)arc).getTypeAssociation().getName();
		Element definedAssoc = assoc.addElement(arcQName.toPrefixString(),arcQName.getNamespaceURI());
		
		createAssociation(definedAssoc,((AlfrescoArc)arc).getTarget());	
	}
	
	/**
	 * give generated arcs by given source
	 * @param source
	 * @param generatedArcs
	 * @return the generated atcs of given source
	 */
	public Collection<IArc> getArcsBySource(INode source, Collection<IArc> generatedArcs) {
		Collection<IArc> arcsBySource = new ArrayList<IArc>();
		for (IArc generatedArc : generatedArcs){
			if (((AlfrescoNode)((AlfrescoArc) generatedArc).getSource()).equals((AlfrescoNode) source)){
				arcsBySource.add(generatedArc);
			}
		}
		return arcsBySource;
	}

}
