/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.webscript;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

/**
 * @author davidchevrier
 *
 */
public class Generate extends DeclarativeWebScript {
	
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
		IStructure structure = dictionary.getStructure(dictionary.getQnameStringModel());
		
		//genarate datas 
		generator.generateNodesInstances(structure);
		generator.generateArcsInstances(structure);
		
		//serialize xml for acp
		serializer.setFileName(XML_FILE_NAME);
		try {
			serializer.serializeXml();
		} catch (IOException e) {
			//log
			e.printStackTrace();
		}
		
		//package to alfresco repository
		packager.setArchiveName(ACP_FILE_NAME);
		File acp = null;
		try {
			acp = packager.packageACP();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//manage import repository
		String pathToAlfrescoRepository = req.getParameter(PATH_TO_ALFRESCO_REPOSITORY);
		NodeRef repository = importer.manageAlfrescoRepository(pathToAlfrescoRepository);
		
		//import (and deploy) acp to Alfresco repository
		if (acp != null){
			importer.importACP(acp,repository);
			// Brice : First save (in case of problem during import in order to analyze the situation), next import
			try {
				importer.saveACP(acp,repository);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
