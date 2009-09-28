/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.serialization.mapping;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.namespace.QNamePattern;

import com.bluexml.side.framework.alfresco.datasGenerator.datas.AlfrescoModelDatas;
import com.bluexml.side.framework.alfresco.datasGenerator.datas.IDatas;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.AlfrescoArc;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.AlfrescoNode;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.IArc;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.INode;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.NativeAlfrescoNode;

/**
 * @author davidchevrier
 *
 */
public class XMLForACPMappingMethods extends AbstractXmlOutput implements IMapping {
	
	private StructureTagFactory tagFactory;
	private FillDatasFactory datasFactory;
	private IDatas alfrescoModelDatas;
	
	/**
	 * @return the tagFactory
	 */
	public StructureTagFactory getTagFactory() {
		return tagFactory;
	}

	/**
	 * @param tagFactory the tagFactory to set
	 */
	public void setTagFactory(StructureTagFactory tagFactory) {
		this.tagFactory = tagFactory;
	}
	
	/**
	 * @return the datasFactory
	 */
	public FillDatasFactory getDatasFactory() {
		return datasFactory;
	}

	/**
	 * @param datasFactory the datasFactory to set
	 */
	public void setDatasFactory(FillDatasFactory datasFactory) {
		this.datasFactory = datasFactory;
	}

	/**
	 * @return the alfrescoModelDatas
	 */
	public IDatas getAlfrescoModelDatas() {
		return alfrescoModelDatas;
	}

	/**
	 * @param alfrescoModelDatas the alfrescoModelDatas to set
	 */
	public void setAlfrescoModelDatas(IDatas alfrescoModelDatas) {
		this.alfrescoModelDatas = alfrescoModelDatas;
	}
	
	public void init(){
		xmlOutPut = new StringBuffer();
	}
	
	public StringBuffer header(){
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.HEADER);
		return xmlOutPut;
	}
	
	public StringBuffer mapsOpenFirstViewTag(){
		xmlOutPut = clear();
		xmlOutPut.append(tagFactory.createOpenFirstViewTag());
		return xmlOutPut;
	}
	
	public StringBuffer mapsCloseLastViewTag(){
		xmlOutPut = clear();
		xmlOutPut.append(tagFactory.createCloseLastViewTag());
		return xmlOutPut;
	}
	
	public StringBuffer mapsOpenAssociationsTag(){
		xmlOutPut = clear();
		xmlOutPut.append(tagFactory.createOpenAssociationsTag());
		return xmlOutPut;
	}
	
	public StringBuffer mapsCloseAssociationsTag(){
		xmlOutPut = clear();
		xmlOutPut.append(tagFactory.createCloseAssociationsTag());
		return xmlOutPut;
	}
	
	public StringBuffer mapsOpenTypeTag(INode node){
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(tagFactory.createOpenTypeTag(node));
		xmlOutPut.append(tagFactory.fillAttributesOfOpenTag(TagsConstants.NATIVE_ATTRIBUTES_FOR_OPEN_TAG));
		xmlOutPut.append(tagFactory.fillAttributesOfOpenTag(TagsConstants.SERVICES_ATTRIBUTES_FOR_OPEN_TAG));
		xmlOutPut.append(tagFactory.fillAttributesOfOpenTag(TagsConstants.OTHERS_ATTRIBUTES_FOR_OPEN_TAG));
		xmlOutPut.append(tagFactory.fillAttributesOfOpenTag(TagsConstants.SIDE_ATTRIBUTES_FOR_OPEN_TAG));
		xmlOutPut.append(tagFactory.addEmptyAttributeToOpenTag());
		xmlOutPut.append(tagFactory.addChildAttributeToOpenTypeTag(node));
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}
	
	public StringBuffer mapsOpenSourceAssociationTag(IArc arc){
		xmlOutPut = clear();
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(tagFactory.createOpenSourceAndTargetAssociationTag());
		xmlOutPut.append(tagFactory.fillAttributesOfOpenTag(TagsConstants.NATIVE_ATTRIBUTES_FOR_OPEN_TAG));
		xmlOutPut.append(tagFactory.fillAttributesOfOpenTag(TagsConstants.SERVICES_ATTRIBUTES_FOR_OPEN_TAG));
		xmlOutPut.append(tagFactory.fillAttributesOfOpenTag(TagsConstants.OTHERS_ATTRIBUTES_FOR_OPEN_TAG));
		xmlOutPut.append(tagFactory.fillAttributesOfOpenTag(TagsConstants.SIDE_ATTRIBUTES_FOR_OPEN_TAG));
		xmlOutPut.append(tagFactory.addEmptyAttributeToOpenTag());
		xmlOutPut.append(tagFactory.addPathrefAttributeToOpenSourceAssociationTag(arc));
		xmlOutPut.append(TagsConstants.END_TAG);
		return xmlOutPut;
	}
	
	public StringBuffer mapsTypePropertiesTags(INode node){
		xmlOutPut = clear();
		xmlOutPut.append(tagFactory.createOpenPropertiesTag());
		xmlOutPut.append(createSideTagsAndFillDatasForPropertiesType(new StringBuffer(), node));
		xmlOutPut.append(createNativeTagsAndFillDatasForNativePropertiesType(new StringBuffer(), node));
		xmlOutPut.append(tagFactory.createClosePropertiesTag());
		return xmlOutPut;
	}


	private StringBuffer createNativeTagsAndFillDatasForNativePropertiesType(StringBuffer xmlOutPut, INode node) {
		Map<QNamePattern,Object> nativeDatasProperties = ((NativeAlfrescoNode) ((AlfrescoNode) node).getNativeNode()).getNativeDatasProperties();
		Set<QNamePattern> nativeProperties = nativeDatasProperties.keySet();
		for (QNamePattern nativeProperty : nativeProperties) {
			xmlOutPut.append(tagFactory.createNativeOpenPropertyTag(nativeProperty));
			Object dataProperty = getNativeDataProperty(node,nativeProperty);
			xmlOutPut.append(datasFactory.fillNativePropertyTag(dataProperty));
			xmlOutPut.append(tagFactory.createNativeClosePropertyTag(nativeProperty));
		}
		return xmlOutPut;
	}

	private Object getNativeDataProperty(INode node, QNamePattern nativeProperty) {
		Object data = new Object();
		//Collection<INode> generatedNodes = ((AlfrescoModelDatas) alfrescoModelDatas).getGeneratedTypesInstances();
		//for (INode node : generatedNodes) {
			Map<QNamePattern,Object> nativeProperties = ((NativeAlfrescoNode) ((AlfrescoNode) node).getNativeNode()).getNativeDatasProperties();
			if (nativeProperties.containsKey(nativeProperty)){
				return nativeProperties.get(nativeProperty);
			}
		//}
		return data;
	}

	private StringBuffer createSideTagsAndFillDatasForPropertiesType(StringBuffer xmlOutPut, INode node) {
		Map<PropertyDefinition,Object> datasProperties= ((AlfrescoNode) node).getDatasProperties();
		Set<PropertyDefinition> properties = datasProperties.keySet();
		for (PropertyDefinition property : properties) {
			xmlOutPut.append(tagFactory.createSideOpenPropertyTag(property));
			xmlOutPut.append(datasFactory.fillPropertyTag(datasProperties.get(property)));
			xmlOutPut.append(tagFactory.createSideClosePropertyTag(property));
		}	
		return xmlOutPut;
	}
	
	public StringBuffer mapsTargetAssociationTag(IArc arc){
		xmlOutPut = clear();
		xmlOutPut.append(tagFactory.createOpenAssociationTag(((AlfrescoArc) arc).getTypeAssociation()));
		xmlOutPut.append(TagsConstants.OPEN_TAG);
		xmlOutPut.append(tagFactory.createOpenSourceAndTargetAssociationTag());
		xmlOutPut.append(tagFactory.addPathrefAttributeToOpenTargetAssociationTag(arc));
		xmlOutPut.append(TagsConstants.END_TAG);
		xmlOutPut.append(tagFactory.createCloseAssociationTag());
		xmlOutPut.append(tagFactory.createCloseAssociationTag(((AlfrescoArc) arc).getTypeAssociation()));
		return xmlOutPut;
	}

	public StringBuffer mapsCloseAssociationTag() {
		xmlOutPut = clear();
		xmlOutPut.append(tagFactory.createCloseAssociationTag());
		return xmlOutPut;
	}

	public StringBuffer mapsCloseTypeTag(INode node) {
		xmlOutPut = clear();
		xmlOutPut.append(tagFactory.createCloseTypeTag(node));
		return xmlOutPut;
	}

	public StringBuffer mapsACL() {
		xmlOutPut = clear();
		xmlOutPut.append(tagFactory.createOpenAclTag());
		xmlOutPut.append(datasFactory.fillAclTag());
		xmlOutPut.append(tagFactory.createCloseAclTag());
		return xmlOutPut;
	}

}
