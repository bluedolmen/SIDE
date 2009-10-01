/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.webscript;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.model.FileFolderService;
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
	private static final String TEMPLATE_PARAM_ID_FOLDERS = "folders";
	
	private DictionaryService dictionaryService;
	private FileFolderService folderService;

	/**
	 * @param dictionaryService the dictionaryService to set
	 */
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/**
	 * @param foldersService the foldersService to set
	 */
	public void setFolderService(FileFolderService folderService) {
		this.folderService = folderService;
	}



	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req,Status status, Cache cache) {
		//get loaded models
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
		
		//get alfresco folders path
		//NodeRef rootNode = new NodeRef(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,STORE_ROOT_ID);
		//List<FileInfo> folders = folderService.listFolders(rootNode);
		//Collection<String> foldersName = new ArrayList<String>();
		//for (FileInfo folder : folders) {
			//foldersName.add(folder.getName());
		//}
		
		Map<String, Object> trees = new HashMap<String, Object>();
		trees.put(TEMPLATE_PARAM_ID_MODELS, models);
		//trees.put(TEMPLATE_PARAM_ID_FOLDERS, foldersName);
		
		return trees;
	}

}
