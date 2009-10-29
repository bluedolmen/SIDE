/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.structure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateInputPdfException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingInputPdfKeyException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.NoPdfFileException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;
import com.lowagie.text.pdf.PdfReader;

/**
 * @author dchevrier
 *
 */
public class AlfrescoStructure {
	
	private static final String LUCENE_SEARCH_QUERY_INDICATOR = "ID:";
	
	private static ServiceRegistry serviceRegistry;

	public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
		AlfrescoStructure.serviceRegistry = serviceRegistry;
	}

	public static NodeRef getContent(Map<String,String> commands, String[] parameters) throws DuplicateOutputContentException, 
	                                                                                          MissingOutputContentException {
		NodeRef content = null;
		if (commands.containsKey(parameters[0]) && commands.containsKey(parameters[1])){
			throw new DuplicateOutputContentException(DuplicateOutputContentException.DUPLICATE_OUTPUT_CONTENT_KEY);
		}
		else if (!commands.containsKey(parameters[0]) && !commands.containsKey(parameters[1])){
			throw new MissingOutputContentException(MissingOutputContentException.MISSING_OUTPUT_CONTENT_KEY);
		}
		else if (!commands.containsKey(parameters[0]) && commands.containsKey(parameters[1])){
			content = getContentByPath(commands.get(parameters[1]));
		}
		else if (commands.containsKey(parameters[0]) && !commands.containsKey(parameters[1])){
			content = getContentByUUID(commands.get(parameters[0]));
		}
		return content;
	}

	public static NodeRef getContentByPath(String pathContent) {
		NodeRef content = null;
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_XPATH,pathContent);
		if (nodeRefSet.length() == 1) {
			content = nodeRefSet.getNodeRefs().get(0);
		}
		return content;
	}
	
	public static NodeRef getContentByUUID(String UUIDContent) {
		NodeRef content = null;
		StringBuffer query = new StringBuffer();
		query.append(LUCENE_SEARCH_QUERY_INDICATOR);
		query.append("\"");
		query.append(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		query.append("/");
		query.append(UUIDContent);
		query.append("\"");
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_LUCENE,query.toString());
		if (nodeRefSet.length() == 1) {
			content = nodeRefSet.getNodeRefs().get(0);
		}
		return content;
	}
	
	public static PdfReader openAlfrescoPdf(Map<String, String> commands) throws DuplicateInputPdfException, MissingInputPdfKeyException, IOException, NoPdfFileException {
		PdfReader reader = null;
		if (commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) && commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			throw new DuplicateInputPdfException(DuplicateInputPdfException.DUPLICATE_INPUT_PDF_KEY);
		}
		else if (!commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) && !commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			throw new MissingInputPdfKeyException(MissingInputPdfKeyException.MISSING_INPUT_PDF_KEY);
		}
		else if (!commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) && commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			reader = getReaderFromAlfrescoPathToPdf(commands.get(ConstantsLanguage.INPUT_PDF_KEYS[1]));
		}
		else if (commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) && !commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			reader = getReaderFromAlfrescoUUIDToPdf(commands.get(ConstantsLanguage.INPUT_PDF_KEYS[0]));
		}
		return reader;
	}
	
	private static PdfReader getReaderFromAlfrescoPathToPdf(String alfrescoPath) throws IOException, NoPdfFileException {
		PdfReader reader = null;
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_XPATH,alfrescoPath);
		if (nodeRefSet.length() == 1) {
			NodeRef alfrescoPdf = nodeRefSet.getNodeRefs().get(0);
			InputStream inputStreamOfAlfrescoPdf = serviceRegistry.getFileFolderService().getReader(alfrescoPdf).getContentInputStream();
			reader = new PdfReader(inputStreamOfAlfrescoPdf);
		} 
		else {
			throw new NoPdfFileException(NoPdfFileException.FILE_DOES_NOT_EXISTS);
		}
		return reader;
	}
	
	private static PdfReader getReaderFromAlfrescoUUIDToPdf(String alfrescoUUID) throws IOException, NoPdfFileException {
		PdfReader reader = null;
		StringBuffer query = new StringBuffer();
		query.append(LUCENE_SEARCH_QUERY_INDICATOR);
		query.append("\"");
		query.append(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		query.append("/");
		query.append(alfrescoUUID);
		query.append("\"");
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_LUCENE,query.toString());
		if (nodeRefSet.length() == 1) {
			NodeRef alfrescoPdf = nodeRefSet.getNodeRefs().get(0);
			InputStream inputStreamOfAlfrescoPdf = serviceRegistry.getFileFolderService().getReader(alfrescoPdf).getContentInputStream();
			reader = new PdfReader(inputStreamOfAlfrescoPdf);
		} 
		else {
			throw new NoPdfFileException(NoPdfFileException.FILE_DOES_NOT_EXISTS);
		}
		return reader;
	}
	
}
