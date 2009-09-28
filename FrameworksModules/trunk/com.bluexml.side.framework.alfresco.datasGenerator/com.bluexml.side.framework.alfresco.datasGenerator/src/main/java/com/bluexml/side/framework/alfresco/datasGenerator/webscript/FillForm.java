/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.webscript;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.repo.dictionary.DictionaryComponent;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.namespace.QName;
import org.alfresco.web.scripts.Cache;
import org.alfresco.web.scripts.DeclarativeWebScript;
import org.alfresco.web.scripts.Status;
import org.alfresco.web.scripts.WebScriptRequest;

/**
 * @author davidchevrier
 *
 */
public class FillForm extends DeclarativeWebScript {
	
	private static final String TEMPLATE_PARAM_ID_MODELS = "qnameModels";
	private static final String SIDE_URI = "http://www.bluexml.com/model/content/";
	private static final String CONTENT_MODEL_LOCAL_NAME = "contentmmodel";
	
	private DictionaryService dictionaryService;

	/**
	 * @param dictionaryService the dictionaryService to set
	 */
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req,Status status, Cache cache) {
		Collection<QName> qnameModels = new ArrayList<QName>();
		qnameModels.addAll(dictionaryService.getAllModels());
		Collection<String> models = new ArrayList<String>();
		if (qnameModels != null){
			for (QName qnameModel : qnameModels) {
				if (qnameModel.getNamespaceURI().startsWith(SIDE_URI) && !qnameModel.getLocalName().equals(CONTENT_MODEL_LOCAL_NAME)){
					models.add("{"+qnameModel.getNamespaceURI()+"}"+qnameModel.getLocalName());
				}
			}
		}
		Map<String, Object> modelsTree = new HashMap<String, Object>();
		modelsTree.put(TEMPLATE_PARAM_ID_MODELS, models);
		return modelsTree;
	}

}
