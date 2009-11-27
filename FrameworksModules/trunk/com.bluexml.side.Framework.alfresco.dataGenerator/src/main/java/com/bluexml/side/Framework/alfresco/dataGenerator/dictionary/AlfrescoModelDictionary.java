/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.dictionary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.bluexml.side.Framework.alfresco.dataGenerator.structure.AlfrescoModelStructure;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoModelDictionary implements IDictionary {

	private DictionaryService dictionaryService;
	private String qnameStringModel;
	private IStructure alfrescoModelStructure;
	
	private String alfrescoDirectory;
	
	/**
	 * @return the qnameStringModel
	 */
	public String getQnameStringModel() {
		return qnameStringModel;
	}

	/**
	 * @param qnameStringModel the qnameStringModel to set
	 */
	public void setQnameStringModel(String qnameStringModel) {
		this.qnameStringModel = qnameStringModel;
	}

	/**
	 * @return the dictionaryService
	 */
	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	/**
	 * @param dictionaryService the dictionaryService to set
	 */
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/**
	 * @return the alfrescoModelStructure
	 */
	public IStructure getAlfrescoModelStructure() {
		return alfrescoModelStructure;
	}

	/**
	 * @param alfrescoModelStructure the alfrescoModelStructure to set
	 */
	public void setAlfrescoModelStructure(IStructure alfrescoModelStructure) {
		this.alfrescoModelStructure = alfrescoModelStructure;
	}
	/**
	 * @return the alfrescoDirectory
	 */
	public String getAlfrescoDirectory() {
		return alfrescoDirectory;
	}

	/**
	 * @param alfrescoDirectory the alfrescoDirectory to set
	 */
	public void setAlfrescoDirectory(String alfrescoDirectory) {
		this.alfrescoDirectory = alfrescoDirectory;
	}

	public ModelDefinition getModel(String qnameModel){
		return dictionaryService.getModel(QName.createQName(qnameModel));
	}
	
	private Collection<TypeDefinition> getTypes(QName qNameModel){
		Collection<QName> typesQNamed = dictionaryService.getTypes(qNameModel);
		Collection<TypeDefinition> types = new ArrayList<TypeDefinition>();
		for (QName typeQNamed : typesQNamed) {
			types.add(dictionaryService.getType(typeQNamed));
		}
		return types;
	}
	
	private Collection<PropertyDefinition> getProperties(TypeDefinition type){
		Collection<PropertyDefinition> sidePropertiesByType = new ArrayList<PropertyDefinition>();
		Map<QName,PropertyDefinition> propertiesForType = type.getProperties();
		Set<QName> qnamesProperties = propertiesForType.keySet();
		for (QName qnameProperty : qnamesProperties) {
			if (!(qnameProperty.equals(ContentModel.PROP_NAME)) && !(qnameProperty.equals(ContentModel.PROP_CONTENT))){
				sidePropertiesByType.add(propertiesForType.get(qnameProperty));
			}
		}
		return sidePropertiesByType;
	}
	
	private Collection<AssociationDefinition> getAssociations(QName qNameModel){
		Collection<QName> associationsQNamed = dictionaryService.getAssociations(qNameModel);
		Collection<AssociationDefinition> associations = new ArrayList<AssociationDefinition>();
		for (QName associationQNamed : associationsQNamed) {
			associations.add(dictionaryService.getAssociation(associationQNamed));
		}
		return associations;
	}
	
	public Collection<ConstraintDefinition> getConstraintsByProperty(PropertyDefinition property){
		return property.getConstraints();
	}
	
	public IStructure getStructure(String qnameModel) throws ParserConfigurationException, SAXException, IOException {
		QName qNameModel = getModel(qnameModel).getName();
		Collection<TypeDefinition> types = getTypes(qNameModel);
		((AlfrescoModelStructure) alfrescoModelStructure).setTypes(types);	
		((AlfrescoModelStructure) alfrescoModelStructure).setProperties(getProperties(types,qNameModel));
		((AlfrescoModelStructure) alfrescoModelStructure).setAssociations(getAssociations(qNameModel));
		((AlfrescoModelStructure) alfrescoModelStructure).setAspects(getAspects(qNameModel,types));
		Collection<TypeDefinition> notAbstractTypes = removeAbstractTypes(types);
		((AlfrescoModelStructure) alfrescoModelStructure).setTypes(notAbstractTypes);
		
		return alfrescoModelStructure;
	}

private Collection<TypeDefinition> removeAbstractTypes(Collection<TypeDefinition> types) throws ParserConfigurationException, SAXException, IOException {
		Collection<TypeDefinition> tempTypes = new ArrayList<TypeDefinition>();
		Collection<QName> notAbstractTypes = getNotAbstractTypes();
		for (TypeDefinition type : types){
			QName qnamedType = type.getName();
			if (!notAbstractTypes.contains(qnamedType)){
				tempTypes.add(type);
			}
		}
		types.removeAll(tempTypes);
		return types;
	}

private Map<TypeDefinition, Collection<PropertyDefinition>> getProperties(Collection<TypeDefinition> types, QName qNameModel) {
	Map<TypeDefinition,Collection<PropertyDefinition>> properties = new HashMap<TypeDefinition, Collection<PropertyDefinition>>();
	for (TypeDefinition typeDefinition : ((AlfrescoModelStructure) alfrescoModelStructure).getTypes()) {
		Collection<PropertyDefinition> propertiesByType = getProperties(typeDefinition);
		QName qnamedParent = typeDefinition.getParentName();
		while (qnamedParent != null){
			TypeDefinition parentType = dictionaryService.getType(qnamedParent);
			propertiesByType.addAll(getProperties(parentType));
			qnamedParent = parentType.getParentName();
		}
		properties.put(typeDefinition, propertiesByType);
	}
	return properties;
}

	private Collection<QName> getNotAbstractTypes() throws ParserConfigurationException, SAXException, IOException {
		Collection<QName> notAbstractTypes = new ArrayList<QName>();
		Collection<String> stringQNameNotAbstractTypes = parseConfigFile();
		for (String notAbstractType : stringQNameNotAbstractTypes) {
			String[] tempNotAbstractType = notAbstractType.split(":");
			notAbstractType = tempNotAbstractType[1];
			String uri = QName.createQName(qnameStringModel).getNamespaceURI();
			notAbstractTypes.add(QName.createQName(uri,notAbstractType));
		}
		return notAbstractTypes;
	}

	private Collection<String> parseConfigFile() throws ParserConfigurationException, SAXException, IOException {
		Collection<String> notAbstractTypes = new ArrayList<String>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File xmlAlfrescoConfig = new File(getPathToConfigFile());
		Document document = builder.parse(xmlAlfrescoConfig);
		Element root = document.getDocumentElement();
		Element firstChild = (Element)root.getElementsByTagName("config").item(0);
		Element secondChild = (Element) firstChild.getElementsByTagName("content-types").item(0);
		NodeList contentsTypes = ((Element) secondChild).getElementsByTagName("type");
		for (int indexNode = 0; indexNode < contentsTypes.getLength(); indexNode++){
			//notAbstractTypes.add(contentsTypes.item(indexNode).getAttributes().item(0).getNodeValue());
			notAbstractTypes.add(((Element)contentsTypes.item(indexNode)).getAttribute("name"));
		}
		return notAbstractTypes;
	}

	private Map<TypeDefinition,Collection<AspectDefinition>> getAspects(QName qNameModel, Collection<TypeDefinition> types) {
		Map<TypeDefinition,Collection<AspectDefinition>> aspectsByTypes = new HashMap<TypeDefinition, Collection<AspectDefinition>>();
		Collection<QName> aspectsQNamedForModel = dictionaryService.getAspects(qNameModel);
		for (TypeDefinition type : types){
			Collection<AspectDefinition> aspects = new ArrayList<AspectDefinition>();
			List<AspectDefinition> aspectsByType = type.getDefaultAspects();
			for (QName aspectQName : aspectsQNamedForModel) {
				AspectDefinition aspectModel = dictionaryService.getAspect(aspectQName);
				if (aspectsByType.contains(aspectModel)){
					aspects.add(aspectModel);
				}
			}
			aspectsByTypes.put(type,aspects);
		}
		return aspectsByTypes;
	}

	private String getPathToConfigFile(){
		StringBuffer path = new StringBuffer();
		path.append(alfrescoDirectory);
		path.append(File.separator);
		path.append("tomcat");
		path.append(File.separator);
		path.append("webapps");
		path.append(File.separator);
		path.append("alfresco");
		path.append(File.separator);
		path.append("WEB-INF");
		path.append(File.separator);
		path.append("classes");
		path.append(File.separator);
		path.append("alfresco");
		path.append(File.separator);
		path.append("module");
		path.append(File.separator);
		path.append(getSideModule(qnameStringModel));
		path.append(File.separator);
		path.append("web-client-config-custom.xml");
		return path.toString();
	}

	private Object getSideModule(String qnameModel) {
		String name = "SIDE_ModelExtension_";
		String elements[] = qnameModel.split("}")[0].split("/");
		return name + elements[elements.length-2];
	}
}
