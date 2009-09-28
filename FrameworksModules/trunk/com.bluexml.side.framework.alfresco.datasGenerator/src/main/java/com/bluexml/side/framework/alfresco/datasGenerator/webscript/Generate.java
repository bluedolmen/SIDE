/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.webscript;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.web.scripts.Cache;
import org.alfresco.web.scripts.DeclarativeWebScript;
import org.alfresco.web.scripts.Status;
import org.alfresco.web.scripts.WebScriptRequest;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.bluexml.side.framework.alfresco.datasGenerator.context.SpringContext;
import com.bluexml.side.framework.alfresco.datasGenerator.dictionary.AlfrescoModelDictionary;
import com.bluexml.side.framework.alfresco.datasGenerator.generator.AlfrescoModelRandomDatasGenerator;
import com.bluexml.side.framework.alfresco.datasGenerator.serialization.ACPPackaging;
import com.bluexml.side.framework.alfresco.datasGenerator.serialization.XMLForACPSerialization;
import com.bluexml.side.framework.alfresco.datasGenerator.structure.IStructure;

/**
 * @author davidchevrier
 *
 */
public class Generate extends DeclarativeWebScript {
	
	private SpringContext context;
	
	private static final String SERVICE_CONTEXT_FILE = "service-context.xml";
	private static final String NOT_MODEL_PARAMETER = "submit";
	
	//parameters names (cf fillparameters.get.html.ftl)
	private static final String MODEL_PARAMETER_NAME = "model";
	private static final String NUMBER_OF_CONTENTS_PARAMETER_NAME ="numOfInstances";
	private static final String NUMBER_OF_OUTPUT_ARCS_PARAMETER_NAME = "numOfOutputArcs";
	
	private static final String XML_FILE_NAME = "test.xml";
	private static final String ACP_FILE_NAME = "TestACP";

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
		((AlfrescoModelRandomDatasGenerator) generator).setNumberOfNodes(Integer.valueOf(numOfContentsParameterValues[0]));
		((AlfrescoModelRandomDatasGenerator) generator).setNumberOfOutputArcs(Integer.valueOf(numOfOutPutArcsParameterValues[0]));
		Object dictionary = ctx.getBean("alfrescoModelDictionary");
		String[] modelParameterValues = req.getParameterValues(MODEL_PARAMETER_NAME);
		((AlfrescoModelDictionary) dictionary).setQnameStringModel(modelParameterValues[0]);
		
		//get model structure
		IStructure structure = ((AlfrescoModelDictionary) dictionary).getStructure(((AlfrescoModelDictionary) dictionary).getQnameStringModel());
		
		//genarate datas 
		((AlfrescoModelRandomDatasGenerator) generator).generateNodesInstances(structure);
		((AlfrescoModelRandomDatasGenerator) generator).generateArcsInstances(structure);
		
		//serialize xml for acp
		Object serializer = ctx.getBean("xmlForACPSerialization");
		((XMLForACPSerialization) serializer).setFileName(XML_FILE_NAME);
		try {
			((XMLForACPSerialization) serializer).serializeXml();
		} catch (IOException e) {
			//log
			e.printStackTrace();
		}
		Object packager = ctx.getBean("acpPackaging");
		((ACPPackaging) packager).setArchiveName(ACP_FILE_NAME);
		try {
			((ACPPackaging) packager).packageACP();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new HashMap<String, Object>();
	}
	
	

}
