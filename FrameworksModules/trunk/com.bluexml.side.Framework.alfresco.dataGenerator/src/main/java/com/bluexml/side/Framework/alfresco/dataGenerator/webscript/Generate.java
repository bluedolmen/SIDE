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
import org.springframework.context.ApplicationContext;

import com.bluexml.side.Framework.alfresco.dataGenerator.context.SpringContext;
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
	
	private SpringContext context;
	private String pathToAlfrescoRepository;
	
	//parameters names (cf fillparameters.get.html.ftl)
	private static final String MODEL_PARAMETER_NAME = "model";
	private static final String NUMBER_OF_CONTENTS_PARAMETER_NAME ="numOfInstances";
	private static final String NUMBER_OF_OUTPUT_ARCS_PARAMETER_NAME = "numOfOutputArcs";
	private static final String PATH_TO_ALFRESCO_REPOSITORY = "alfrescoRepository";
	
	private static final String XML_FILE_NAME = "test.xml";
	public static final String ACP_FILE_NAME = "TestACP";

	/**
	 * @return the context
	 */
	public SpringContext getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(SpringContext context) {
		this.context = context;
	}

	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req,Status status, Cache cache) { 
		//get context (i.e. access to beans)
		ApplicationContext ctx = SpringContext.getContext();
		
		//get and fill generator parameters
		Object generator = ctx.getBean("alfrescoModelRandomGenerator");
		String[] numOfContentsParameterValues = req.getParameterValues(NUMBER_OF_CONTENTS_PARAMETER_NAME);
		String[] numOfOutPutArcsParameterValues = req.getParameterValues(NUMBER_OF_OUTPUT_ARCS_PARAMETER_NAME);
		((AlfrescoModelRandomDataGenerator) generator).setNumberOfNodes(Integer.valueOf(numOfContentsParameterValues[0]));
		((AlfrescoModelRandomDataGenerator) generator).setNumberOfOutputArcs(Integer.valueOf(numOfOutPutArcsParameterValues[0]));
		Object dictionary = ctx.getBean("alfrescoModelDictionary");
		String[] modelParameterValues = req.getParameterValues(MODEL_PARAMETER_NAME);
		((AlfrescoModelDictionary) dictionary).setQnameStringModel(modelParameterValues[0]);
		
		//get model structure
		IStructure structure = ((AlfrescoModelDictionary) dictionary).getStructure(((AlfrescoModelDictionary) dictionary).getQnameStringModel());
		
		//genarate datas 
		((AlfrescoModelRandomDataGenerator) generator).generateNodesInstances(structure);
		((AlfrescoModelRandomDataGenerator) generator).generateArcsInstances(structure);
		
		//serialize xml for acp
		Object serializer = ctx.getBean("xmlForACPSerialization");
		((XMLForACPSerialization) serializer).setFileName(XML_FILE_NAME);
		try {
			((XMLForACPSerialization) serializer).serializeXml();
		} catch (IOException e) {
			//log
			e.printStackTrace();
		}
		
		//package to alfresco repository
		Object packager = ctx.getBean("acpPackaging");
		((ACPPackaging) packager).setArchiveName(ACP_FILE_NAME);
		File acp = null;
		try {
			acp = ((ACPPackaging) packager).packageACP();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//manage import repository
		ImportACP importer = (ImportACP) ctx.getBean("importACP");
		pathToAlfrescoRepository = req.getParameter(PATH_TO_ALFRESCO_REPOSITORY);
		NodeRef repository = importer.manageAlfrescoRepository(pathToAlfrescoRepository);
		
		//import (and deploy) acp to Alfresco repository
		if (acp != null){
			importer.importACP(acp,repository);
			try {
				importer.saveACP(acp,repository);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new HashMap<String, Object>();
	}
	
	

}
