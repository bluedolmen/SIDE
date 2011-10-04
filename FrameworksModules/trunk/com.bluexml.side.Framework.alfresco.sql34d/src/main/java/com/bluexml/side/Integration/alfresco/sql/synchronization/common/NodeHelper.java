package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import java.util.ArrayList;
import java.util.List;

import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;

public class NodeHelper {
	
	/**
	 * Return the parent QNames of the nodeRef including self
	 * Self has to be the first element of the returned list
	 * 
	 * @param nodeRef
	 * @return a list of parent names
	 */
	public List<QName> getParentAndSelfQNames(NodeRef nodeRef) {
		List<QName> result = new ArrayList<QName>();

		QName currentType = nodeService.getType(nodeRef);

		while (filterer.acceptTypeQName(currentType)) {
			result.add(currentType);
			TypeDefinition nodeRefTypeDefinition = dictionaryService.getType(currentType);
			if (nodeRefTypeDefinition == null ) {
				break;
			}
			QName parentType = nodeRefTypeDefinition.getParentName();
			currentType = parentType;
		}
		return result;
	}

	
	//
	// IoC/DI Spring
	//

	// Dependencies
	private NodeService nodeService;
	private DictionaryService dictionaryService;
	private Filterer filterer;

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}

	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public void setFilterer(Filterer filterer_) {
		filterer = filterer_;
	}
	

}
