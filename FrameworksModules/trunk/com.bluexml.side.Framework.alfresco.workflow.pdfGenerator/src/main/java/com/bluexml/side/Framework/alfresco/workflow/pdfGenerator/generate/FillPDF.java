package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.AttributeContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateInputPdfException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidAssociationException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidValueOfParameterException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingInputPdfKeyException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.NoPdfFileException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.extract.ExtractDataFromContent;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.fill.FillDataPDF;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.LanguageMethods;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.structure.AlfrescoStructure;
import com.lowagie.text.pdf.PdfReader;

public class FillPDF {
	
	private ServiceRegistry serviceRegistry;
	
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public void execute(Map<String, String> commands) throws DuplicateInputPdfException, MissingInputPdfKeyException, 
	                                                         IOException, NoPdfFileException, DuplicateOutputContentException, 
	                                                         MissingOutputContentException, InvalidValueOfParameterException, 
	                                                         AttributeContentException, InvalidAssociationException, 
	                                                         InvalidContentException {
		PdfReader reader = AlfrescoStructure.openAlfrescoPdf(commands);
		NodeRef content = AlfrescoStructure.getContent(commands,ConstantsLanguage.INPUT_CONTENT_KEYS);
		HashMap<String,String> exportCommands = LanguageMethods.getScriptCommands(commands);
		HashMap<String,Object> data = ExtractDataFromContent.extractData(content,serviceRegistry,exportCommands);
		FillDataPDF.fillPDF(serviceRegistry,reader,exportCommands,data);
	}

}
