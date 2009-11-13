/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.structure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

import org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript;
import org.alfresco.repo.workflow.jbpm.JBPMNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.model.FileNotFoundException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.jbpm.graph.exe.ExecutionContext;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateInputPdfException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingInputPdfKeyException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingOutputPathForPDFException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingOverridePdfKeyException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.NoPdfFileException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * @author dchevrier
 *
 */
public class AlfrescoStructure {
	
	private static final String LUCENE_SEARCH_QUERY_INDICATOR = "ID:";
	
	private static ServiceRegistry serviceRegistry;

	public static ExecutionContext executionContext;

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

		StringBuffer prefix = new StringBuffer();
		prefix.append(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		prefix.append("/");
		
		StringBuffer query = new StringBuffer();
		query.append(LUCENE_SEARCH_QUERY_INDICATOR);
		query.append("\"");
		
		if (!UUIDContent.startsWith(prefix.toString()))
			query.append(prefix);
		
		query.append(UUIDContent);
		query.append("\"");
		
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_LUCENE,query.toString());
		if (nodeRefSet.length() >= 1) {
			content = nodeRefSet.getNodeRefs().get(0);
		} else {
			//Trying to evaluate as alfresco javascript
			Object result = null;
			try {
				result = AlfrescoJavaScript.executeScript(executionContext, serviceRegistry,UUIDContent, Collections.EMPTY_LIST);
			} catch (Exception e) {
				//Nothing to do
			}
			if (result instanceof JBPMNode) {
				JBPMNode jbpmnode = (JBPMNode) result;
				if (jbpmnode.getNodeRef() != null)
					content = getContentByUUID(jbpmnode.getNodeRef().toString());
			} else if (result instanceof String) {
				content = getContentByUUID(result.toString());
			}
		}
		return content;
	}
	
	public static PdfReader openAlfrescoPdf(Map<String, String> commands) throws DuplicateInputPdfException, MissingInputPdfKeyException, IOException, NoPdfFileException {
		PdfReader reader = null;
		if (commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) && commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			throw new DuplicateInputPdfException(DuplicateInputPdfException.DUPLICATE_INPUT_PDF_KEY);
		}
		else if (!commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0]) & !commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
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
	
	private static NodeRef getNodeRefFromAlfrescoPathToPdf(String alfrescoPath){
		NodeRef node =null;
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_XPATH,alfrescoPath);
		if (nodeRefSet.length() == 1) {
			node = nodeRefSet.getNodeRefs().get(0);
		} 
		return node;
	}
	
	private static NodeRef getNodeRefFromAlfrescoUUIDToPdf(String alfrescoUUID){
		NodeRef node = null;
		StringBuffer query = new StringBuffer();
		query.append(LUCENE_SEARCH_QUERY_INDICATOR);
		query.append("\"");
		query.append(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		query.append("/");
		query.append(alfrescoUUID);
		query.append("\"");
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_LUCENE,query.toString());
		if (nodeRefSet.length() == 1) {
			node = nodeRefSet.getNodeRefs().get(0);
		} 
		return node;
	}
	
	private static PdfReader getReaderFromAlfrescoPathToPdf(String alfrescoPath) throws IOException, NoPdfFileException {
		PdfReader reader = null;
		NodeRef node = getNodeRefFromAlfrescoPathToPdf(alfrescoPath);
		if (node != null) {
			InputStream inputStreamOfAlfrescoPdf = serviceRegistry.getFileFolderService().getReader(node).getContentInputStream();
			reader = new PdfReader(inputStreamOfAlfrescoPdf);
		} 
		else {
			throw new NoPdfFileException(NoPdfFileException.FILE_DOES_NOT_EXISTS);
		}
		return reader;
	}
	
	private static PdfReader getReaderFromAlfrescoUUIDToPdf(String alfrescoUUID) throws IOException, NoPdfFileException {
		PdfReader reader = null;
		NodeRef node = getNodeRefFromAlfrescoUUIDToPdf(alfrescoUUID);
		if (node != null) {
			InputStream inputStreamOfAlfrescoPdf = serviceRegistry.getFileFolderService().getReader(node).getContentInputStream();
			reader = new PdfReader(inputStreamOfAlfrescoPdf);
		} 
		else {
			//Trying to evaluate as alfresco javascript
			Object result = AlfrescoJavaScript.executeScript(executionContext, serviceRegistry,alfrescoUUID, Collections.EMPTY_LIST);
			if (result instanceof JBPMNode) {
				JBPMNode jbpmnode = (JBPMNode) result;
				if (jbpmnode.getNodeRef() != null)
					getReaderFromAlfrescoUUIDToPdf(jbpmnode.getNodeRef().toString());
			} else if (result instanceof String) {
				getReaderFromAlfrescoUUIDToPdf(result.toString());
			}
			throw new NoPdfFileException(NoPdfFileException.FILE_DOES_NOT_EXISTS);
		}
		return reader;
	}

	public static PdfStamper manageAlfrescoPDF(PdfReader reader,Map<String, String> commands) throws IOException, NoPdfFileException, 
	                                                                                                 MissingOutputPathForPDFException, 
	                                                                                                 DocumentException, 
	                                                                                                 MissingOverridePdfKeyException, 
	                                                                                                 FileExistsException, FileNotFoundException {
		PdfStamper stamper = null;
		if (!commands.containsKey(ConstantsLanguage.OUTPUT_PDF_KEY)){
			throw new MissingOutputPathForPDFException(MissingOutputPathForPDFException.DOES_NOT_EXISTS);
		}
		else if (commands.containsKey(ConstantsLanguage.OUTPUT_PDF_KEY)){
			OutputStream outputStream = null;
			String path = commands.get(ConstantsLanguage.OUTPUT_PDF_KEY);
			if (getNodeRefFromAlfrescoPathToPdf(path) == null){
				outputStream = createPdf(path,commands);
			}
			else{
				if (commands.containsKey(ConstantsLanguage.FORCE_OVERRIDE_PDF_KEY)){
					if (commands.get(ConstantsLanguage.FORCE_OVERRIDE_PDF_KEY).equals(ConstantsLanguage.FORCE_OVERRIDE_PDF_VALUES[0])){
						outputStream = getStreamFromAlfrescoPathToPdf(path);
					}
				}
				else{
					throw new MissingOverridePdfKeyException(MissingOverridePdfKeyException.DOES_NOT_EXISTS);
				}
			}
			stamper = new PdfStamper(reader,outputStream);
		}
		return stamper;
	}

	private static OutputStream createPdf(String path, Map<String, String> commands) throws FileExistsException, FileNotFoundException {
		NodeRef fillablePdf = null;
		if (commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[0])){
			fillablePdf = getNodeRefFromAlfrescoUUIDToPdf(commands.get(ConstantsLanguage.INPUT_PDF_KEYS[0]));
		}
		else if (commands.containsKey(ConstantsLanguage.INPUT_PDF_KEYS[1])){
			fillablePdf = getNodeRefFromAlfrescoPathToPdf(commands.get(ConstantsLanguage.INPUT_PDF_KEYS[1]));
		}
		String pathToFilledPdfParent = getPathToFilledPdfParent(path);
		NodeRef filledPdfParent = getNodeRefFromAlfrescoPathToPdf(pathToFilledPdfParent);
		String filledPdfName = path.split("/")[path.split("/").length-1].split(":")[1];
		FileInfo filledFile = serviceRegistry.getFileFolderService().copy(fillablePdf, filledPdfParent, filledPdfName);
		return serviceRegistry.getFileFolderService().getWriter(filledFile.getNodeRef()).getContentOutputStream();
	
	}

	private static String getPathToFilledPdfParent(String path) {
		StringBuffer parentPath = new StringBuffer();
		String[] pathSteps = path.split("/");
		for (int i = 1; i < pathSteps.length-1; i++){
			parentPath.append("/");
			parentPath.append(pathSteps[i]);
		}
		return parentPath.toString();
	}

	private static OutputStream getStreamFromAlfrescoPathToPdf(String path) {
		OutputStream outputStream = null;
		NodeRef node = getNodeRefFromAlfrescoPathToPdf(path);
		if (node != null) {
			outputStream = serviceRegistry.getFileFolderService().getWriter(node).getContentOutputStream();
		} 
		return outputStream;
	}
	
}
