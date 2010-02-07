/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping;

import java.util.ArrayList;
import java.util.Collection;

import org.alfresco.service.namespace.NamespaceService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bluexml.side.Framework.alfresco.dataGenerator.data.AlfrescoModelData;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;

/**
 * @author dchevrier
 *
 */
public class XMLForACPMappingBuilder implements IMapping {
	
	private Document document;
	private XMLForACPMappingHelper helper;
	private AlfrescoModelData alfrescoModelData;
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	public XMLForACPMappingHelper getHelper() {
		return helper;
	}

	public void setHelper(XMLForACPMappingHelper helper) {
		this.helper = helper;
	}
	
	public Document build (){
		
		document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element root = helper.createRoot();
		mapsAllNodes(root);
		mapsAllArcs(root);
		return document;
		
	}
	
	public AlfrescoModelData getAlfrescoModelData() {
		return alfrescoModelData;
	}

	public void setAlfrescoModelData(AlfrescoModelData alfrescoModelData) {
		this.alfrescoModelData = alfrescoModelData;
	}

	private void mapsAllNodes(Element root) {
		Collection<INode> generatedNodes = ((AlfrescoModelData) alfrescoModelData).getGeneratedTypesInstances();
		for (INode node : generatedNodes) {
			Element type = helper.createType(root,node);
			helper.createAspects(type,node);
			helper.createProperties(type,node);
		}
		
	}
	
	private void mapsAllArcs(Element root) {
		Collection<IArc> generatedArcs = ((AlfrescoModelData)alfrescoModelData).getGeneratedAssociationsInstances();
		Collection<IArc> tempUsedArcs = new ArrayList<IArc>();
		for (IArc iArc : generatedArcs) {
			if (!tempUsedArcs.contains(iArc)){
				INode src = ((AlfrescoArc)iArc).getSource();
				Element assoc = helper.createAssociation(root,src).addElement(helper.getServices().createTag(NamespaceService.REPOSITORY_VIEW_PREFIX,Constants.ASSOCIATIONS));
				Collection<IArc> arcsBySource = helper.getArcsBySource(((AlfrescoArc) iArc).getSource(),generatedArcs);
				for (IArc arcBySource : arcsBySource) {
					helper.createTargetAssociation(assoc,arcBySource);
				}
				tempUsedArcs.addAll(arcsBySource);	
			}
		}
	}

}
