/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.webscript;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.web.scripts.Cache;
import org.alfresco.web.scripts.DeclarativeWebScript;
import org.alfresco.web.scripts.Status;
import org.alfresco.web.scripts.WebScriptRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import com.bluexml.side.Framework.alfresco.dataGenerator.dictionary.AlfrescoModelDictionary;
import com.bluexml.side.Framework.alfresco.dataGenerator.generator.AlfrescoModelRandomDataGenerator;
import com.bluexml.side.Framework.alfresco.dataGenerator.generator.NativeAlfrescoModelRandomDataGenerator;
import com.bluexml.side.Framework.alfresco.dataGenerator.load.FolderStructure;
import com.bluexml.side.Framework.alfresco.dataGenerator.load.ImportACP;
import com.bluexml.side.Framework.alfresco.dataGenerator.serialization.ACPPackaging;
import com.bluexml.side.Framework.alfresco.dataGenerator.serialization.XMLForACPSerializer;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;

/**
 * @author davidchevrier
 *
 */
public class Generate extends DeclarativeWebScript {
	
	private static Log logger = LogFactory.getLog(Generate.class);
	
	//parameters names (cf fillparameters.get.html.ftl)
	private static final String MODEL_PARAMETER_NAME = "model";
	private static final String NUMBER_OF_CONTENTS_PARAMETER_NAME ="numOfInstances";
	private static final String NUMBER_OF_OUTPUT_ARCS_PARAMETER_NAME = "numOfOutputArcs";
	private static final String PATH_TO_ALFRESCO_REPOSITORY = "alfrescoRepository";
	private static final String PATH_TO_DOCUMENTS = "pathToDocuments";
	private static final String SCENARIO = "scenario";
	private static final String INDEXES = "indexes";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String FOLDERS_STRUCTURE = "folders";
	
	private static final String XML_FILE_NAME = "Data.xml";
	public static final String ACP_FILE_NAME = "Data";
	
	private Map<String,Object> model = new HashMap<String, Object>();

	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req,Status status, Cache cache) {
		
		model.clear();
		
		//get and fill generator parameters
		String numOfContentsParameterValue = req.getParameter(NUMBER_OF_CONTENTS_PARAMETER_NAME);
		String numOfOutPutArcsParameterValue = req.getParameter(NUMBER_OF_OUTPUT_ARCS_PARAMETER_NAME);
		String scenarioParameterValue = req.getParameter(SCENARIO);
		String indexesParameterValue = req.getParameter(INDEXES);
		String loginParameterValue = req.getParameter(LOGIN);
		String passwordParameterValue = req.getParameter(PASSWORD);
		String foldersParameterValue = req.getParameter(FOLDERS_STRUCTURE);
		
		generator.setNumberOfNodes(Integer.valueOf(numOfContentsParameterValue));
		generator.setNumberOfOutputArcs(Integer.valueOf(numOfOutPutArcsParameterValue));
		generator.setScenario(scenarioParameterValue);
		if (!indexesParameterValue.equals("")){
			generator.setSavedStartIndexAttribute(Integer.valueOf(indexesParameterValue));
		}
		else{
			generator.setSavedStartIndexAttribute(Integer.valueOf(0));
		}
		
		String modelParameterValue = req.getParameter(MODEL_PARAMETER_NAME);
		dictionary.setQnameStringModel(modelParameterValue);
		
		String pathToDocuments = req.getParameter(PATH_TO_DOCUMENTS);
		nativeGenerator.setPathToDocumentsFolder(pathToDocuments);
		
		importer.setLogin(loginParameterValue);
		importer.setPassword(passwordParameterValue);
		
		//get model structure
		IStructure structure = null;
		try {
			structure = dictionary.getStructure(dictionary.getQnameStringModel());
		} catch (ParserConfigurationException e2) {
			model.put("error", e2);
			logger.error("Error getting model's structure:", e2);
		} catch (SAXException e2) {
			model.put("error", e2);
			logger.error("Error getting model's structure:", e2);
		} catch (IOException e2) {
			model.put("error", e2);
			logger.error("Error getting model's structure:", e2);
		}
		
		//genarate datas 
		boolean generated = false;
		try {
			generated = generator.generateNodesInstances(structure);
		} catch (Exception e1) {
			model.put("error", e1);
			logger.error("Error generating nodes instances: ", e1);
		}
		try {
			if (generated){
				generated = generator.generateArcsInstances(structure);
			}
		} catch (Exception e1) {
			model.put("error", e1);
			logger.error("Error generating arcs instances: ", e1);
		}
		boolean deleted = false;
		try {
			if (generated){
				deleted = generator.getGeneratorServices().deleteExceededNodes();
			}
		} catch (Exception e1) {
			model.put("error", e1);
			logger.error("Error deleting isolated nodes: ", e1);
		}
		
		
		//serialize xml for acp
		boolean serialized = false;
		serializer.setFileName(XML_FILE_NAME);
		try {
			if (deleted){
				serialized = serializer.serializeXml();
			}
		} catch (Exception e) {
			model.put("error", e);
			logger.error("Error creating xml file: ", e);
		}
		
		//package to alfresco repository (with contents)
		packager.setArchiveName(ACP_FILE_NAME);
		File acp = null;
		try {
			if (serialized){
				acp = packager.packageACP();
			}
		} catch (IOException e) {
			model.put("error", e);
			logger.error("Error packaging ACP: ", e);
		}
		
		//manage import repository
		String pathToAlfrescoRepository = req.getParameter(PATH_TO_ALFRESCO_REPOSITORY);
		pathToAlfrescoRepository="/app:company_home/"+pathToAlfrescoRepository;
		NodeRef repository=null;
		try {
			repository = importer.manageAlfrescoRepository(pathToAlfrescoRepository);
		} catch (Exception e1) {
			model.put("error", e1);
			logger.error("Error with managment of Alfresco repository: ", e1);
		}
		
		//import (and deploy) acp to Alfresco repository
		boolean saved = false;
		boolean imported = false;
		boolean clean = false;
		if (acp != null && repository != null){
			try {
				saved = importer.saveACP(acp,repository);
			} catch (IOException e) {
				model.put("error", e);
				logger.error("Error saving ACP: ", e);
			}
			// Brice : First save (in case of problem during import in order to analyze the situation), next import
			try {
				if (saved){
					imported = importer.importACP(acp,repository);
				}
			} catch (Exception e) {
				model.put("error", e);
				logger.error("Error importing ACP: ", e);
			}
			try {
				if (imported){
					clean = packager.clean(acp);
				}
			} catch (Exception e) {
				model.put("error", e);
				logger.error("Error cleaning generated files: ", e);
			}
			if (("on").equals(foldersParameterValue)){
				try {
					folders.manageFolders(repository);
				} catch (Exception e) {
					model.put("error", e);
					logger.error("Error creating folders: ", e);
				}
			}
		}
		
		if (clean && scenarioParameterValue.equals("incremental")){
			model.put("incremental", new Object());
			Integer maxAttributeIndex;
			try {
				maxAttributeIndex = Integer.valueOf(generator.getGeneratorServices().getMaxAttributeIndex());
				model.put("attributeIndex", maxAttributeIndex);
				
				Map<TypeDefinition,Map<PropertyDefinition,Integer>> index = AlfrescoModelRandomDataGenerator.getIndex();
				index.clear();
				AlfrescoModelRandomDataGenerator.setIndex(index);
				Map<TypeDefinition,Integer> indexType = AlfrescoModelRandomDataGenerator.getIndexType();
				indexType.clear();
				AlfrescoModelRandomDataGenerator.setIndexType(indexType);
			} catch (Exception e) {
				model.put("error", e);
				logger.error("Error creating post parameters: ", e);
			}
		}
		
		return model;
	}
	
	/*
	 * Spring DI material
	 */
	
	AlfrescoModelRandomDataGenerator generator = null;
	AlfrescoModelDictionary dictionary = null;
	XMLForACPSerializer serializer = null;
	ACPPackaging packager = null;
	ImportACP importer = null;
	NativeAlfrescoModelRandomDataGenerator nativeGenerator = null;
	FolderStructure folders = null;
	
	public void setAlfrescoModelRandomGenerator (AlfrescoModelRandomDataGenerator generator_) {
		generator = generator_;
	}
	
	public void setAlfrescoModelDictionary (AlfrescoModelDictionary dictionary_) {
		dictionary = dictionary_;
	}
	
	public void setXMLForACPSerializer (XMLForACPSerializer serializer_) {
		serializer = serializer_;
	}
	
	public void setACPPackaging (ACPPackaging packager_) {
		packager = packager_;
	}
	
	public void setImportACP (ImportACP importer_) {
		importer = importer_;
	}
	
	public void setNativeAlfrescoModelRandomDataGenerator(NativeAlfrescoModelRandomDataGenerator nativeGenerator_){
		nativeGenerator = nativeGenerator_;
	}
	
	public void setFolderStructure(FolderStructure folders_){
		folders = folders_;
	}

}
