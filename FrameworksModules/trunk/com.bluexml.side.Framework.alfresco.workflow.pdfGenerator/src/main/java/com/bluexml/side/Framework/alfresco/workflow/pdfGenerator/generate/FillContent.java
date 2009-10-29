/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.AttributeContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateInputPdfException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidAssociationException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidValueOfParameterException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingInputPdfKeyException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.NoContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.NoPdfFileException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.extract.ExtractDataFromPDF;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.fill.FillDataContent;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.LanguageMethods;
import com.lowagie.text.pdf.PdfReader;

/**
 * @author dchevrier
 *
 */
public class FillContent {
	
	private static final String LUCENE_SEARCH_QUERY_INDICATOR = "ID:";

	private ServiceRegistry serviceRegistry;

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public void execute(Map<String, String> commands) throws DuplicateInputPdfException, MissingInputPdfKeyException, IOException, NoPdfFileException, DuplicateOutputContentException, MissingOutputContentException, NoContentException, InvalidValueOfParameterException, AttributeContentException, InvalidAssociationException {
		PdfReader reader = openAlfrescoPdf(commands);
		HashMap<String,String> data = ExtractDataFromPDF.extractDataFromPDF(reader);
		NodeRef content = getContent(commands);
		HashMap<String,String> importCommands = LanguageMethods.getPdfToContentCommands(commands);
		FillDataContent.fillContent(serviceRegistry,content,importCommands,data);
	}

	private NodeRef getContent(Map<String, String> commands) throws DuplicateOutputContentException, MissingOutputContentException, NoContentException {
		NodeRef content = null;
		if (commands.containsKey(ConstantsLanguage.OUTPUT_CONTENT_KEYS[0]) && commands.containsKey(ConstantsLanguage.OUTPUT_CONTENT_KEYS[1])){
			throw new DuplicateOutputContentException(DuplicateOutputContentException.DUPLICATE_OUTPUT_CONTENT_KEY);
		}
		else if (!commands.containsKey(ConstantsLanguage.OUTPUT_CONTENT_KEYS[0]) && !commands.containsKey(ConstantsLanguage.OUTPUT_CONTENT_KEYS[1])){
			throw new MissingOutputContentException(MissingOutputContentException.MISSING_OUTPUT_CONTENT_KEY);
		}
		else if (!commands.containsKey(ConstantsLanguage.OUTPUT_CONTENT_KEYS[0]) && commands.containsKey(ConstantsLanguage.OUTPUT_CONTENT_KEYS[1])){
			content = getContentByPath(commands.get(ConstantsLanguage.OUTPUT_CONTENT_KEYS[1]));
		}
		else if (commands.containsKey(ConstantsLanguage.OUTPUT_CONTENT_KEYS[0]) && !commands.containsKey(ConstantsLanguage.OUTPUT_CONTENT_KEYS[1])){
			content = getContentByUUID(commands.get(ConstantsLanguage.OUTPUT_CONTENT_KEYS[0]));
		}
		return content;
	}

	private NodeRef getContentByUUID(String UUIDContent) throws NoContentException {
		NodeRef content = null;
		String query = LUCENE_SEARCH_QUERY_INDICATOR+"\""+StoreRef.STORE_REF_WORKSPACE_SPACESSTORE+"/"+UUIDContent+"\"";
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_LUCENE,query);
		if (nodeRefSet.length() == 1) {
			content = nodeRefSet.getNodeRefs().get(0);
		}
		else{
			throw new NoContentException(NoContentException.CONTENT_DOES_NOT_EXISTS);
		}
		return content;
	}

	private NodeRef getContentByPath(String pathContent) throws NoContentException {
		NodeRef content = null;
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_XPATH,pathContent);
		if (nodeRefSet.length() == 1) {
			content = nodeRefSet.getNodeRefs().get(0);
		}
		else{
			throw new NoContentException(NoContentException.CONTENT_DOES_NOT_EXISTS);
		}
		return content;
	}

	private PdfReader openAlfrescoPdf(Map<String, String> commands) throws DuplicateInputPdfException, MissingInputPdfKeyException, IOException, NoPdfFileException {
		PdfReader reader = null;
		if (commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) && commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			throw new DuplicateInputPdfException(DuplicateInputPdfException.DUPLICATE_INPUT_PDF_KEY);
		}
		else if (!commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) && !commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			throw new MissingInputPdfKeyException(MissingInputPdfKeyException.MISSING_INPUT_PDF_KEY);
		}
		else if (!commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) && commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			reader = getJavaPathFromAlfrescoPathToPdf(commands.get(ConstantsLanguage.INPUT_PDF_KEYS[1]));
		}
		else if (commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) && !commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			reader = getJavaPathFromAlfrescoUUIDToPdf(commands.get(ConstantsLanguage.INPUT_PDF_KEYS[0]));
		}
		return reader;
	}

	private PdfReader getJavaPathFromAlfrescoUUIDToPdf(String alfrescoUUID) throws IOException, NoPdfFileException {
		PdfReader reader = null;
		String query = LUCENE_SEARCH_QUERY_INDICATOR+"\""+StoreRef.STORE_REF_WORKSPACE_SPACESSTORE+"/"+alfrescoUUID+"\"";
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_LUCENE,query);
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

	private PdfReader getJavaPathFromAlfrescoPathToPdf(String alfrescoPath) throws IOException, NoPdfFileException {
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
	
}
