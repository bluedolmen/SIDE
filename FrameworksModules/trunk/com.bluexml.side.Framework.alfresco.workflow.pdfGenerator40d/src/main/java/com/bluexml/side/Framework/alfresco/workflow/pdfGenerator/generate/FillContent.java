/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.AttributeContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateInputPdfException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.DuplicateOutputContentException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidAssociationException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.InvalidFormatParameterException;
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
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.structure.AlfrescoStructure;
import com.lowagie.text.pdf.PdfReader;

/**
 * @author dchevrier
 *
 */
public class FillContent {

	private ServiceRegistry serviceRegistry;

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public void execute(Map<String, String> commands) throws DuplicateInputPdfException, MissingInputPdfKeyException, 
	                                                         IOException, NoPdfFileException, DuplicateOutputContentException, 
	                                                         MissingOutputContentException, NoContentException, 
	                                                         InvalidValueOfParameterException, AttributeContentException, 
	                                                         InvalidAssociationException, OutputTypeKeyException, InvalidNodeRefException, 
	                                                         ParseException, InvalidFormatParameterException {
		PdfReader reader = AlfrescoStructure.openAlfrescoPdf(commands);
		HashMap<String,String> data = ExtractDataFromPDF.extractData(reader);
		NodeRef content = AlfrescoStructure.getContent(commands,ConstantsLanguage.OUTPUT_CONTENT_KEYS);
		if (content == null){
			content = createContent(commands);
		}
		HashMap<String,String> importCommands = LanguageMethods.getScriptCommands(commands);
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
		return AlfrescoStructure.getContentByPath(parentPath.toString());
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
	
}
