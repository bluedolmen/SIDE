package com.bluexml.xforms.controller.alfresco;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluexml.xforms.controller.binding.AttachContentInfo;
import com.bluexml.xforms.controller.binding.Batch;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.GenericCreate;
import com.bluexml.xforms.controller.binding.GenericDelete;
import com.bluexml.xforms.controller.binding.GenericUpdate;
import com.bluexml.xforms.controller.binding.ObjectFactory;
import com.bluexml.xforms.controller.binding.ServiceRequestSource;

public class AlfrescoTransaction {

	protected static Log logger = LogFactory.getLog(AlfrescoTransaction.class);

	private final ObjectFactory objectFactory = new ObjectFactory();

	private String login;
	private int counter = 0;
	private AlfrescoController alfrescoController;
	private Batch batch;
	private Map<String, String> ids = null;

	/** the name of the form's */
	private String formId = null; // #1212

	/** the name of the uploaded file in the file store, so that it can be removed in case of error */
	private String uploadedFileName = null; // #1278
	/** the name of the temporary file for the upload, so that it can be removed in case of success */
	private String tempFileName = null; // #1278

	public AlfrescoTransaction(AlfrescoController alfrescoController) {
		super();
		this.alfrescoController = alfrescoController;
		this.batch = objectFactory.createBatch();
		this.login = null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	private void initializeBatch() {
		this.batch = objectFactory.createBatch();
		// ** #1365
		ServiceRequestSource requesterId = objectFactory.createServiceRequestSource();
		requesterId.setId("XFormsController");
		batch.getCreateOrUpdateOrDelete().add(requesterId);
		// ** #1365
	}

	/**
	 * Note that the "attach content" should be unique in the list of actions requested and should
	 * ALWAYS come AFTER the actions that process the receiver and provider. DO NOT rely on the
	 * webscript putting things back in the correct order.
	 * 
	 * @param receiver
	 *            either a node ref or a placeholder id (e.g. 'transactionID-1'). The webscript will
	 *            figure each case out.
	 * @param fileName
	 *            the display name for the node
	 * @param filePath
	 *            the complete path to the file on the server side
	 * @param mimeType
	 *            the MIME type as served by the web client
	 * @param contentType
	 *            the qualified name of the (generated) content type that has the meta data. This
	 *            type must correspond to the type of the receiver
	 */
	public void queueAttachContent(String receiver, String fileName, String filePath,
			String mimeType, String contentType) {
		if (batch == null) {
			initializeBatch();
		}
		AttachContentInfo entry = objectFactory.createAttachContentInfo();
		entry.setTargetNode(receiver);
		entry.setFileName(fileName);
		entry.setFilePath(filePath);
		entry.setMimeType(mimeType);
		entry.setContentType(contentType);

		batch.getCreateOrUpdateOrDelete().add(entry);
	}

	public void queueDelete(String id) {
		if (batch == null) {
			initializeBatch();
		}
		GenericDelete entry = objectFactory.createGenericDelete();
		entry.setId(id);
		entry.setAssocObject(null);
		batch.getCreateOrUpdateOrDelete().add(entry);
	}

	public void queueSave(GenericClass alfClass) {
		if (batch == null) {
			initializeBatch();
		}
		alfClass.setId(createId());

		GenericCreate genericCreate = objectFactory.createGenericCreate();
		genericCreate.setClazz(alfClass);
		batch.getCreateOrUpdateOrDelete().add(genericCreate);
	}

	public void queueUpdate(GenericClass alfClass) {
		if (batch == null) {
			initializeBatch();
		}
		GenericUpdate genericUpdate = objectFactory.createGenericUpdate();
		genericUpdate.setClazz(alfClass);
		batch.getCreateOrUpdateOrDelete().add(genericUpdate);
	}

	private String createId() {
		counter++;
		return "transactionID-" + counter;
	}

	public void executeBatch() throws AlfrescoControllerException {
		if (batch == null) {
			initializeBatch();
		}
		if (batch.getCreateOrUpdateOrDelete().size() > 0) {
			alfrescoController.executeBatch(this, batch);
		}
	}

	public Map<String, String> getIds() {
		return ids;
	}

	public void setIds(Map<String, String> ids) {
		this.ids = ids;
	}

	/**
	 * @return the form name for this transaction
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * @param formId
	 *            the form name to set
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * @return the uploadedFileName
	 */
	public String getUploadedFileName() {
		return uploadedFileName;
	}

	/**
	 * @param uploadedFileName
	 *            the uploadedFileName to set
	 */
	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}

	/**
	 * @param tempFileName
	 *            the tempFileName to set
	 */
	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}

	/**
	 * @return the tempFileName
	 */
	public String getTempFileName() {
		return tempFileName;
	}

}
