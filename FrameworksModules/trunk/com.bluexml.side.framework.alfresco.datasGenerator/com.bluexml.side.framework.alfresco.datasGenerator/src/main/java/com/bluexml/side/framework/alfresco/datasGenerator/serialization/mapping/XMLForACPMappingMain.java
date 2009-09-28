package com.bluexml.side.framework.alfresco.datasGenerator.serialization.mapping;

import java.util.ArrayList;
import java.util.Collection;

import com.bluexml.side.framework.alfresco.datasGenerator.datas.AlfrescoModelDatas;
import com.bluexml.side.framework.alfresco.datasGenerator.datas.IDatas;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.AlfrescoArc;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.AlfrescoNode;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.IArc;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.INode;

public class XMLForACPMappingMain extends AbstractXmlOutput implements IMapping {

	private IDatas alfrescoModelDatas;
	private IMapping mappingMethods;
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
	/**
	 * @return the mappingMethods
	 */
	public IMapping getMappingMethods() {
		return mappingMethods;
	}
	/**
	 * @param mappingMethods the mappingMethods to set
	 */
	public void setMappingMethods(IMapping mappingMethods) {
		this.mappingMethods = mappingMethods;
	}
	
	public void init(){
		xmlOutPut = new StringBuffer();
	}
	
	public StringBuffer mainMapping(){
		xmlOutPut = clear();
		XMLForACPMappingMethods methods = (XMLForACPMappingMethods) mappingMethods;
		xmlOutPut.append(methods.header());
		xmlOutPut.append(methods.mapsOpenFirstViewTag());
		xmlOutPut.append(mapsAllNodes(new StringBuffer(),methods));
		xmlOutPut.append(mapsAllArcs(new StringBuffer(),methods));
		xmlOutPut.append(methods.mapsCloseLastViewTag());
		return xmlOutPut;
	}
	
	private StringBuffer mapsAllNodes(StringBuffer xmlOutPut, XMLForACPMappingMethods methods) {
		Collection<INode> generatedNodes = ((AlfrescoModelDatas) alfrescoModelDatas).getGeneratedTypesInstances();
		for (INode node : generatedNodes) {
			xmlOutPut.append(methods.mapsOpenTypeTag(node));
			xmlOutPut.append(methods.mapsACL());
			xmlOutPut.append(methods.mapsTypePropertiesTags(node));
			xmlOutPut.append(methods.mapsCloseTypeTag(node));
		}
		return xmlOutPut;
	}
	
	private StringBuffer mapsAllArcs(StringBuffer xmlOutPut, XMLForACPMappingMethods methods) {
		Collection<IArc> generatedArcs = ((AlfrescoModelDatas) alfrescoModelDatas).getGeneratedAssociationsInstances();
		Collection<IArc> tempUsedArcs = new ArrayList<IArc>();
		for (IArc arc : generatedArcs) {
			if (!tempUsedArcs.contains(arc)){
				xmlOutPut.append(methods.mapsOpenSourceAssociationTag(arc));
				xmlOutPut.append(methods.mapsOpenAssociationsTag());
				Collection<IArc> arcsBySource = getArcsBySource(((AlfrescoArc) arc).getSource(),generatedArcs);
				for (IArc arcBySource : arcsBySource) {
					xmlOutPut.append(methods.mapsTargetAssociationTag(arcBySource));
				}
				tempUsedArcs.addAll(arcsBySource);
				xmlOutPut.append(methods.mapsCloseAssociationsTag());
				xmlOutPut.append(methods.mapsCloseAssociationTag());
			}
		}
		return xmlOutPut;
	}
	private Collection<IArc> getArcsBySource(INode source, Collection<IArc> generatedArcs) {
		Collection<IArc> arcsBySource = new ArrayList<IArc>();
		for (IArc generatedArc : generatedArcs){
			if (((AlfrescoNode)((AlfrescoArc) generatedArc).getSource()).equals((AlfrescoNode) source)){
				arcsBySource.add(generatedArc);
			}
		}
		return arcsBySource;
	}
	
}
