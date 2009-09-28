/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.dictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.framework.alfresco.datasGenerator.structure.AlfrescoModelStructure;
import com.bluexml.side.framework.alfresco.datasGenerator.structure.IStructure;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoModelDictionary implements IDictionary {

	private DictionaryService dictionaryService;
	private String qnameStringModel;
	private IStructure alfrescoModelStructure;
	
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
	
	public IStructure getStructure(String qnameModel) {
		QName qNameModel = getModel(qnameModel).getName();
		
		((AlfrescoModelStructure) alfrescoModelStructure).setTypes(getTypes(qNameModel));
		
		Map<TypeDefinition,Collection<PropertyDefinition>> properties = new HashMap<TypeDefinition, Collection<PropertyDefinition>>();
		for (TypeDefinition typeDefinition : ((AlfrescoModelStructure) alfrescoModelStructure).getTypes()) {
			//QName typeQNamed = typeDefinition.getName();	
			//Collection<PropertyDefinition> propertiesByType = getProperties(typeQNamed);
			Collection<PropertyDefinition> propertiesByType = getProperties(typeDefinition);
			properties.put(typeDefinition, propertiesByType);
		}
		((AlfrescoModelStructure) alfrescoModelStructure).setProperties(properties);
		
		((AlfrescoModelStructure) alfrescoModelStructure).setAssociations(getAssociations(qNameModel));
		
		return alfrescoModelStructure;
	}
	
}
