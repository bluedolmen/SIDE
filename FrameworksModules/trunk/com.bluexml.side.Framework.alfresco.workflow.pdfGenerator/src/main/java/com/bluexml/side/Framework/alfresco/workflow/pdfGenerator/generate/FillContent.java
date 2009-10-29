/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.AttributeContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateInputPdfException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidAssociationException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidValueOfParameterException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingInputPdfKeyException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.NoContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.NoPdfFileException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.OutputTypeKeyException;
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

	public void execute(Map<String, String> commands) throws DuplicateInputPdfException, MissingInputPdfKeyException, 
	                                                         IOException, NoPdfFileException, DuplicateOutputContentException, 
	                                                         MissingOutputContentException, NoContentException, 
	                                                         InvalidValueOfParameterException, AttributeContentException, 
	                                                         InvalidAssociationException, OutputTypeKeyException {
		PdfReader reader = openAlfrescoPdf(commands);
		HashMap<String,String> data = ExtractDataFromPDF.extractDataFromPDF(reader);
		NodeRef content = getContent(commands);
		if (content == null){
			content = createContent(commands);
		}
		HashMap<String,String> importCommands = LanguageMethods.getPdfToContentCommands(commands);
		FillDataContent.fillContent(serviceRegistry,content,importCommands,data);
	}

	private NodeRef createContent(Map<String, String> commands) throws NoContentException, OutputTypeKeyException {
		String outputTypeValue = commands.get(ConstantsLanguage.OUTPUT_TYPE_CONTENT_KEY);
		String[] contentTypeInfos = null;
		if (outputTypeValue != null){
			contentTypeInfos = outputTypeValue.split(ConstantsLanguage.OUTPUT_TYPE_SEPARATOR);
		}
		else{
			throw new OutputTypeKeyException(OutputTypeKeyException.DOES_NOT_EXISTS);
		}
		String uri = getUriModel(contentTypeInfos[0]);
		QName type = QName.createQName(uri,contentTypeInfos[1]);
		QName assoc = QName.createQName(uri,"contains");
		NodeRef parent = getFolderContentParent(commands);
		TypeDefinition typeDef = serviceRegistry.getDictionaryService().getType(type);
		Map<QName,Serializable> propertyName = FillDataContent.createNameProperty(typeDef);
		ChildAssociationRef assocRef = serviceRegistry.getNodeService().createNode(parent, ContentModel.ASSOC_CONTAINS, assoc, type, propertyName);
		return assocRef.getChildRef();
	}

	private NodeRef getFolderContentParent(Map<String, String> commands) throws NoContentException {
		NodeRef parentFolder = null;
		Set<String> keys = commands.keySet();
		if (keys.contains(ConstantsLanguage.OUTPUT_CONTENT_KEYS[1])){
			parentFolder = getParentFolderByPath(commands.get(ConstantsLanguage.OUTPUT_CONTENT_KEYS[1]));
		}
		else if (keys.contains(ConstantsLanguage.OUTPUT_CONTENT_KEYS[0])){
			//we can't find anything
		}
		return parentFolder;
	}

	private NodeRef getParentFolderByPath(String path) throws NoContentException {
		String[] pathSteps = path.split("/");
		StringBuffer parentPath = new StringBuffer();
		for (int i = 1; i < pathSteps.length-1; i++){
			parentPath.append("/");
			parentPath.append(pathSteps[i]);
		}
		return getContentByPath(parentPath.toString());
	}

	private String getUriModel(String prefix) {
		String uri = null;
		Collection<QName> models = serviceRegistry.getDictionaryService().getAllModels();
		for (QName model : models) {
			if (model.getNamespaceURI().contains(prefix)){
				uri = model.getNamespaceURI();
			}
		}
		return uri;
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
//		else{
//			throw new NoContentException(NoContentException.CONTENT_DOES_NOT_EXISTS);
//		}
		return content;
	}

	private NodeRef getContentByPath(String pathContent) throws NoContentException {
		NodeRef content = null;
		ResultSet nodeRefSet = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,SearchService.LANGUAGE_XPATH,pathContent);
		if (nodeRefSet.length() == 1) {
			content = nodeRefSet.getNodeRefs().get(0);
		}
//		else{
//			throw new NoContentException(NoContentException.CONTENT_DOES_NOT_EXISTS);
//		}
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
