/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.webscript;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.web.scripts.Cache;
import org.alfresco.web.scripts.DeclarativeWebScript;
import org.alfresco.web.scripts.Status;
import org.alfresco.web.scripts.WebScriptRequest;

import com.bluexml.side.Framework.alfresco.dataGenerator.dictionary.AlfrescoModelDictionary;
import com.bluexml.side.Framework.alfresco.dataGenerator.generator.AlfrescoModelRandomDataGenerator;
import com.bluexml.side.Framework.alfresco.dataGenerator.load.ImportACP;
import com.bluexml.side.Framework.alfresco.dataGenerator.serialization.ACPPackaging;
import com.bluexml.side.Framework.alfresco.dataGenerator.serialization.XMLForACPSerialization;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

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
	
	private static final String XML_FILE_NAME = "test.xml";
	public static final String ACP_FILE_NAME = "TestACP";

	
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req,Status status, Cache cache) {		
		//get and fill generator parameters
		String numOfContentsParameterValue = req.getParameter(NUMBER_OF_CONTENTS_PARAMETER_NAME);
		String numOfOutPutArcsParameterValue = req.getParameter(NUMBER_OF_OUTPUT_ARCS_PARAMETER_NAME);
		generator.setNumberOfNodes(Integer.valueOf(numOfContentsParameterValue));
		generator.setNumberOfOutputArcs(Integer.valueOf(numOfOutPutArcsParameterValue));
		
		String modelParameterValue = req.getParameter(MODEL_PARAMETER_NAME);
		dictionary.setQnameStringModel(modelParameterValue);
		
		//get model structure
		IStructure structure = null;
		try {
			structure = dictionary.getStructure(dictionary.getQnameStringModel());
		} catch (ParserConfigurationException e2) {
			logger.error("Error :", e2);
		} catch (SAXException e2) {
			logger.error("Error :", e2);
		} catch (IOException e2) {
			logger.error("Error :", e2);
		}
		
		//genarate datas 
		try {
			generator.generateNodesInstances(structure);
		} catch (Exception e1) {
			logger.error("Error :", e1);
		}
		try {
			generator.generateArcsInstances(structure);
		} catch (Exception e1) {
			logger.error("Error :", e1);
		}
		
		//serialize xml for acp
		serializer.setFileName(XML_FILE_NAME);
		try {
			serializer.serializeXml();
		} catch (IOException e) {
			logger.error("Error :", e);
		}
		
		//package to alfresco repository
		packager.setArchiveName(ACP_FILE_NAME);
		File acp = null;
		try {
			acp = packager.packageACP();
		} catch (IOException e) {
			logger.error("Error :", e);
		}
		
		//manage import repository
		String pathToAlfrescoRepository = req.getParameter(PATH_TO_ALFRESCO_REPOSITORY);
		pathToAlfrescoRepository="/app:company_home/"+pathToAlfrescoRepository;
		NodeRef repository=null;
		try {
			repository = importer.manageAlfrescoRepository(pathToAlfrescoRepository);
		} catch (Exception e1) {
			logger.error("Error :", e1);
		}
		
		//import (and deploy) acp to Alfresco repository
		if (acp != null){
			try {
				importer.saveACP(acp,repository);
			} catch (IOException e) {
				logger.error("Error :", e);
			}
			// Brice : First save (in case of problem during import in order to analyze the situation), next import
			importer.importACP(acp,repository);

			
		}
		
		return new HashMap<String, Object>();
	}
	
	/*
	 * Spring DI material
	 */
	
	AlfrescoModelRandomDataGenerator generator = null;
	AlfrescoModelDictionary dictionary = null;
	XMLForACPSerialization serializer = null;
	ACPPackaging packager = null;
	ImportACP importer = null;
	
	public void setAlfrescoModelRandomGenerator (AlfrescoModelRandomDataGenerator generator_) {
		generator = generator_;
	}
	
	public void setAlfrescoModelDictionary (AlfrescoModelDictionary dictionary_) {
		dictionary = dictionary_;
	}
	
	public void setXMLForACPSerialization (XMLForACPSerialization serializer_) {
		serializer = serializer_;
	}
	
	public void setACPPackaging (ACPPackaging packager_) {
		packager = packager_;
	}
	
	public void setImportACP (ImportACP importer_) {
		importer = importer_;
	}


}
