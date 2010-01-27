package com.bluexml.xforms.controller.alfresco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.Path;
import org.alfresco.service.cmr.security.AuthorityType;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowInstance;
import org.alfresco.service.cmr.workflow.WorkflowPath;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.alfresco.service.cmr.workflow.WorkflowTaskDefinition;
import org.alfresco.service.cmr.workflow.WorkflowTaskState;
import org.alfresco.service.namespace.QName;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.util.XMLCatalogResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.binding.AssociationType;
import com.bluexml.xforms.controller.binding.AttributeType;
import com.bluexml.xforms.controller.binding.Batch;
import com.bluexml.xforms.controller.binding.CanisterType;
import com.bluexml.xforms.controller.binding.EnumType;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import com.bluexml.xforms.controller.mapping.MappingTool;
import com.bluexml.xforms.controller.mapping.MappingToolCommon;
import com.bluexml.xforms.controller.mapping.RepoContentInfoBean;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;
import com.thoughtworks.xstream.XStream;

/**
 * The Class AlfrescoController.
 */
public class AlfrescoController {

	/** The upload base directory in the file system. */
	public static File UPLOAD_DIRECTORY = null;

	/** The upload base directory in the content management system. */
	public static String UPLOAD_REPOSITORY = null;

	/** The temp directory. */
	public static File TEMP_DIRECTORY = null;

	public static String MAX_RESULTS = "50";

	/** The alfresco url. */
	public static String ALFRESCO_URL = null;

	/** The alfresco xforms url. */
	public static String ALFRESCO_XFORMS_URL = null;

	/** The default user name for transactions */
	public static String USER_NAME;

	/** The user password */
	public static String USER_PSWD;
	//
	//
	private static final String BLUEXML_WORKFLOW_PREFIX = "wfbx";

	private static final String PACKAGE_PATH = MsgId.INT_BLUEXML_DEFAULT_STORE_PATH
			+ "/cm:WORKFLOW_PACKAGES";

	private static final XStream xstream = new XStream();

	/** The logger. */
	protected static Log logger = LogFactory.getLog(AlfrescoController.class);

	/** The doc builder. */
	private static DocumentBuilder docBuilder;

	/** The controller. */
	private static AlfrescoController instance = null;

	//
	// general settings that are persisted through sessions and users
	private static String formsPropertiesPath;

	private static String messagesPropertiesPath;

	/** whether calls to the webscript are intercepted */
	private static boolean standaloneMode;

	private static String CssUrl;

	private static String redirectXmlPath;

	/** Stores redirection info keyed by form names */
	private static Map<String, RedirectionBean> targetTable = new HashMap<String, RedirectionBean>();
	//
	//

	/** The last initParams we saw. */
	Map<String, String> initParameters = null;

	/** The last form type we saw. */
	String lastFormName;

	/** The current upload path, set by getUploadPathInFileSystem */
	File uploadDir;

	static {
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// ** #1330
			// XMLReader reader = new XMLReader();
			List<String> catalogs = new ArrayList<String>(8);
			URL xhtml_strict = AlfrescoController.class.getResource("/xhtml1/catalog.xml");
			catalogs.add(xhtml_strict.toString());
			XMLCatalogResolver resolver = new XMLCatalogResolver();
			resolver.setPreferPublic(true);
			resolver.setCatalogList(catalogs.toArray(new String[0]));
			docBuilder.setEntityResolver(resolver);
			// ** #1330
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}

		instance = new AlfrescoController();
		messagesPropertiesPath = null;
		formsPropertiesPath = null;
		CssUrl = null;
		standaloneMode = false;

		// statically load properties
		try {
			loadMappingXml();
			loadProperties(formsPropertiesPath, messagesPropertiesPath);
			loadRedirectionTable(null);
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @throws Exception
	 */
	private static void loadMappingXml() throws Exception {
		URL url = AlfrescoController.class.getResource("/mapping.xml");
		File file = new File(new URI(url.toString()));
		InputStream mappingStream = new FileInputStream(file);
		instance.initMapping(mappingStream);
	}

	/**
	 * @throws IOException
	 * @throws Exception
	 */
	public static void loadProperties(String formsFilePath, String messagesFilePath)
			throws IOException, Exception {
		// forms.properties
		Properties config = new Properties();
		URL formsURL = AlfrescoController.class.getResource("/forms.properties");
		File formsFile = new File(new URI(formsURL.toString()));
		InputStream streamForms = new FileInputStream(formsFile);
		InputStream defStreamForms = streamForms;
		if (StringUtils.trimToNull(formsFilePath) != null) {
			try {
				File theFile = new File(formsFilePath);
				streamForms = new FileInputStream(theFile);
				// keep the path so that a subsequent reload does not require re-giving the path
				formsPropertiesPath = formsFilePath;
			} catch (Exception e) {
				e.printStackTrace();
				streamForms = defStreamForms;
			}
		} else if (StringUtils.trimToNull(formsPropertiesPath) != null) {
			try {
				File theFile = new File(formsPropertiesPath);
				streamForms = new FileInputStream(theFile);
			} catch (Exception e) {
				e.printStackTrace();
				streamForms = defStreamForms;
			}
		}

		config.load(streamForms);
		instance.initConfig(config);

		// messages.properties
		URL msgURL = AlfrescoController.class.getResource("/messages.properties");
		File msgFile = new File(new URI(msgURL.toString()));
		InputStream streamMsgs = new FileInputStream(msgFile);
		InputStream defStreamMsgs = streamMsgs;
		if (StringUtils.trimToNull(messagesFilePath) != null) {
			try {
				File theFile = new File(messagesFilePath);
				streamMsgs = new FileInputStream(theFile);
				// keep the path so that a subsequent reload does not require re-giving the path
				messagesPropertiesPath = messagesFilePath;
			} catch (Exception e) {
				e.printStackTrace();
				streamMsgs = defStreamMsgs;
			}
		} else if (StringUtils.trimToNull(messagesPropertiesPath) != null) {
			try {
				File theFile = new File(messagesPropertiesPath);
				streamMsgs = new FileInputStream(theFile);
			} catch (Exception e) {
				e.printStackTrace();
				streamMsgs = defStreamMsgs;
			}
		}
		MsgPool.setInputStream(streamMsgs);
	}

	/**
	 * Instantiates a new alfresco controller.
	 */
	private AlfrescoController() {
		super();
		// singleton
	}

	/**
	 * Gets the single instance of AlfrescoController.
	 * 
	 * @return single instance of AlfrescoController
	 */
	public static AlfrescoController getInstance() {
		return instance;
	}

	/**
	 * Patch data id. Adds "workspace://SpacesStore/" as a prefix to dataId.
	 * 
	 * @param dataId
	 *            the data id
	 * 
	 * @return the string
	 */
	public static String patchDataId(String dataId) {
		String result = null;
		if (dataId != null) {
			if (!dataId.startsWith("workspace://SpacesStore/")) {
				result = "workspace://SpacesStore/" + dataId;
			} else {
				result = dataId;
			}
		}
		return result;
	}

	/**
	 * Removes the prefix from a full data id.
	 * 
	 * @param dataId
	 * @return the hex code par of the id
	 */
	public static String unpatchDataId(String dataId) {
		return dataId.substring(dataId.lastIndexOf('/') + 1);
	}

	/**
	 * Provides a unique package path built from the arguments.
	 * 
	 * @param processName
	 * @param instanceId
	 * @param dataId
	 *            a full data Id with protocol and space
	 * @return the complete path to the package folder.
	 */
	public static String buildWorkflowPackagePath(String processName, String instanceId,
			String dataId) {
		String instance = instanceId.replaceAll(":", "_");
		String data = unpatchDataId(dataId);
		String result = PACKAGE_PATH + "/cm:" + processName + "/cm:" + instance + "/cm:" + data;
		return result;
	}

	/**
	 * @return the URL to the Alfresco host
	 */
	public static String getALFRESCO_URL() {
		return ALFRESCO_URL;
	}

	/**
	 * @param alfresco_url
	 *            the URL to the Alfresco host to set
	 */
	public static void setALFRESCO_URL(String alfresco_url) {
		ALFRESCO_URL = alfresco_url;
	}

	/** The config. */
	protected Properties config = null;

	/** The mapping tool. */
	private MappingTool mappingTool;

	/**
	 * Inits the mapping.
	 * 
	 * @param mapping
	 *            the mapping
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void initMapping(InputStream mapping) throws Exception {
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance("com.bluexml.xforms.controller.binding");
			Unmarshaller mappingUnmarshaller = jaxbContext.createUnmarshaller();
			Mapping mappingInstance = (Mapping) mappingUnmarshaller.unmarshal(mapping);
			mappingTool = new MappingTool(mappingInstance, this);
		} catch (JAXBException e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			mapping.close();
		}
	}

	/**
	 * Gets the class.
	 * 
	 * @param type
	 *            the type
	 * @param id
	 *            the id
	 * @param initParams
	 *            the init params
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the class
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document getClass(AlfrescoTransaction transaction, String type, String id,
			Map<String, String> initParams, boolean formIsReadOnly)
			throws AlfrescoControllerException {
		Document instance = null;
		// save the initParams
		this.initParameters = initParams;
		try {
			if (id == null) {
				instance = createElement(transaction, type, initParams,
						new Stack<AssociationType>(), formIsReadOnly);
			} else {
				instance = getElement(transaction, id, new Stack<AssociationType>(), formIsReadOnly);
			}
		} catch (Exception e) {
			throw new AlfrescoControllerException(e);
		}
		return instance;
	}

	/**
	 * Crée un formulaire pour une classe, en création ou en édition.
	 * 
	 * @param type
	 *            nom de la classe (ou type de contenu)
	 * @param id
	 *            id de l'objet à modifier. Si null, le formulaire est vide (hors valeurs par
	 *            défaut). Si non null, le formulaire est pré-renseigné.
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the form
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document getForm(AlfrescoTransaction transaction, String type, String id,
			Map<String, String> initParams, boolean formIsReadOnly)
			throws AlfrescoControllerException {
		Document instance = null;
		// save some data for later
		this.initParameters = initParams;
		this.lastFormName = type;
		try {
			if (id == null) {
				instance = createForm(transaction, type, initParams, formIsReadOnly);
			} else {
				// WorkflowBean bean = new WorkflowBean();
				// bean.dataId = id;
				// bean.processId = initParams.get(MsgId.PARAM_WORKFLOW_PROCESS_ID.getText());
				// bean.instanceId = initParams.get(MsgId.PARAM_WORKFLOW_INSTANCE_ID.getText());
				instance = loadForm(transaction, type, id, formIsReadOnly);
			}
		} catch (Exception e) {
			throw new AlfrescoControllerException(e);
		}
		return instance;
	}

	/**
	 * Gets the element.
	 * 
	 * @param id
	 *            the id
	 * @param stack
	 *            the stack
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the element
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document getElement(AlfrescoTransaction transaction, String id,
			Stack<AssociationType> stack, boolean formIsReadOnly)
			throws AlfrescoControllerException {
		Document alfrescoNode = processRead(transaction, id);

		return mappingTool.transformAlfrescoToClassForms(transaction, alfrescoNode, stack,
				formIsReadOnly);
	}

	/**
	 * Process read.
	 * 
	 * @param id
	 *            the id
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the document
	 * 
	 * @throws AlfrescoControllerException
	 *             the controller exception
	 */
	public Document processRead(AlfrescoTransaction transaction, String id)
			throws AlfrescoControllerException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("objectId", id);
		return requestDocumentFromAlfresco(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_READ);
	}

	/**
	 * Inits the config.
	 * 
	 * @param config
	 *            the config
	 */
	public void initConfig(Properties config) {
		String fsPath;
		this.config = config;

		// user name and password
		USER_NAME = config.getProperty(MsgId.KEY_USER_NAME.getText());
		if (StringUtils.trimToNull(USER_NAME) == null) {
			USER_NAME = "admin";
		}
		USER_PSWD = config.getProperty(MsgId.KEY_USER_PSWD.getText());
		if (StringUtils.trimToNull(USER_PSWD) == null) {
			USER_PSWD = "admin";
		}
		// file system folder for uploads, if none, default to current folder
		fsPath = config.getProperty(MsgId.KEY_UPLOAD_DIRECTORY.getText());
		if (StringUtils.trimToNull(fsPath) == null) {
			fsPath = ".";
		}
		UPLOAD_DIRECTORY = new File(fsPath);
		// repo folder for uploads, if none given, use "/app:company_home"
		UPLOAD_REPOSITORY = config.getProperty(MsgId.KEY_UPLOAD_REPOSITORY.getText());
		if (StringUtils.trimToNull(UPLOAD_REPOSITORY) == null) {
			UPLOAD_REPOSITORY = "/app:company_home";
		}
		// temp dir for file system uploads
		fsPath = config.getProperty(MsgId.KEY_TEMP_DIRECTORY.getText());
		if (StringUtils.trimToNull(fsPath) == null) {
			fsPath = ".";
		}
		TEMP_DIRECTORY = new File(fsPath);

		String property = config.getProperty(MsgId.KEY_MAX_RESULTS.getText());
		if (StringUtils.trimToNull(property) != null) {
			MAX_RESULTS = property;
		}
		checkDirectoryExists(UPLOAD_DIRECTORY, true);
		checkDirectoryExists(TEMP_DIRECTORY, false);
		ALFRESCO_URL = config.getProperty(MsgId.KEY_ALFRESCO_URL.getText());
		ALFRESCO_XFORMS_URL = ALFRESCO_URL + "/service/xforms/";
	}

	/**
	 * Check file exists.
	 * 
	 * @param file
	 *            the file
	 * @param isUploadDir
	 */
	private void checkDirectoryExists(File file, boolean isUploadDir) {
		if (!file.exists()) {
			logger.error(file.getAbsolutePath() + " doesn't exist. Will try to create the path.");
			if (file.mkdirs() == false) {
				String dirName = isUploadDir ? "upload" : "temp";

				logger.error("Couldn't create " + file.getAbsolutePath() + ". Will default to '"
						+ dirName + "' under the webapp's current folder.");
				// we need to create the directory under the webapp's folder
				URL url = AlfrescoController.class.getResource("/mapping.xml");
				File mappingFile = null; 
				try {
					mappingFile = new File(new URI(url.toString()));
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				String dirPath = mappingFile.getAbsolutePath();
				// @Amenel: I am not comfortable with the assumption about the path to mapping.xml:
				// although true today, it may not be so in the future, depending on OS or JVM
				// the easiest would have been to create the dirs as siblings of mapping.xml
				dirPath = StringUtils.replace(dirPath, "/WEB-INF/classes/mapping.xml", "");
				dirPath += File.separator + dirName;
				boolean result;
				if (isUploadDir) {
					UPLOAD_DIRECTORY = new File(dirPath);
					result = UPLOAD_DIRECTORY.mkdirs();
				} else {
					TEMP_DIRECTORY = new File(dirPath);
					result = TEMP_DIRECTORY.mkdirs();
				}
				if (result == false) {
					logger.error("Couldn't create directory " + dirPath
							+ ". Uploads will not perform correctly.");
				}
			}
		}
	}

	/**
	 * Persist class.
	 * 
	 * @param instance
	 *            the instance
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the string
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	public String persistClass(AlfrescoTransaction transaction, Node instance)
			throws AlfrescoControllerException, ServletException {
		GenericClass alfClass = mappingTool.transformClassFormsToAlfresco(transaction, instance);
		if (alfClass.getId() == null) {
			alfClass.setId(save(transaction, alfClass));
		} else {
			update(transaction, alfClass);
		}
		return alfClass.getId();
	}

	/**
	 * Saves a content type. The values from the instance have already been collected. Moves
	 * uploaded files to the file system/repository and enqueues the operation in the transaction,
	 * returning the id given by the transaction manager.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the string
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private String save(AlfrescoTransaction transaction, GenericClass alfClass)
			throws AlfrescoControllerException, ServletException {
		String fileName = null;
		String filePath = null;
		String mimeType = null;
		// enqueue the operation
		transaction.queueSave(alfClass);

		// content file(s); these will be saved to the server's filesystem
		fileName = StringUtils
				.trimToNull(mappingTool.getFileContentFileName(transaction, alfClass));
		if (fileName != null && fileName.startsWith("file:")) {
			String type = alfClass.getQualifiedName();
			fileName = moveFileToUploadDir(type, fileName, transaction);
			mappingTool.setFileContentFileName(alfClass, fileName);
		}

		// repository content file(s); these will be directly uploaded to the repository
		RepoContentInfoBean infoBean = mappingTool.getRepoContentInfo(transaction, alfClass);
		fileName = null;
		if (infoBean != null) {
			fileName = infoBean.getName();
			filePath = infoBean.getPath();
			mimeType = infoBean.getMimeType();
			if (filePath != null && filePath.startsWith("file:")) {
				String location = getUploadPathInRepository();
				fileName = processRepoContent(transaction, fileName, filePath, location, mimeType);
				if (StringUtils.trimToNull(fileName) == null) {
					throw new ServletException(MsgPool.getMsg(MsgId.MSG_UPLOAD_FAILED));
				}
				mappingTool.setRepoContentFileName(alfClass, fileName);
			}
		} else {
			mappingTool.setRepoContentFileName(alfClass, "");
		}

		// node content file; there's at most one instance of this.
		RepoContentInfoBean nodeContentInfoBean = mappingTool.getNodeContentInfo(transaction,
				alfClass);
		if (nodeContentInfoBean != null) {
			fileName = nodeContentInfoBean.getName();
			filePath = nodeContentInfoBean.getPath();
			mimeType = nodeContentInfoBean.getMimeType();

			if (filePath != null && filePath.startsWith("file:")) {
				File file;
				URI fileURI = URI.create(filePath);
				String fullFileName = fileURI.getPath();
				file = new File(fullFileName);

				if (!file.exists()) {
					logger.error("The file '" + fullFileName + "' to be uploaded does not exist.");
					throw new ServletException(
							"The file to upload does not exist. Your session may have expired. Please load and submit the form again.");
				}

				if (file.length() > 0) {
					transaction.queueAttachContent(alfClass.getId(), fileName, fullFileName,
							mimeType, alfClass.getQualifiedName());
				}
			}
		}

		return alfClass.getId();
	}

	/**
	 * Returns a path for uploading files into the file system. If none given in initParams, fall
	 * back to the value from configuration init.
	 */
	private File getUploadPathInFileSystem() {

		String result = null;
		if (initParameters != null) {
			result = initParameters.get(MsgId.PARAM_UPLOAD_DIRECTORY.getText());
		}
		if (StringUtils.trimToNull(result) == null) {
			return UPLOAD_DIRECTORY;
		}
		File resFile = new File(result);
		return resFile.exists() ? resFile : UPLOAD_DIRECTORY;
	}

	/**
	 * Returns a path for uploading files into the repository. If none given in initParams, fall
	 * back to the value from configuration init.
	 */
	private String getUploadPathInRepository() {

		String result = null;
		if (initParameters != null) {
			result = initParameters.get(MsgId.PARAM_UPLOAD_REPOSITORY.getText());
		}
		return (result != null) ? result : UPLOAD_REPOSITORY;
	}

	/**
	 * Update.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param transaction
	 *            the transaction
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	private void update(AlfrescoTransaction transaction, GenericClass alfClass)
			throws AlfrescoControllerException {
		String previousContentFileName = null;
		RepoContentInfoBean previousRepoContentInfo = null;

		String xformsFileName = StringUtils.trimToNull(mappingTool.getFileContentFileName(
				transaction, alfClass));
		if (xformsFileName != null && xformsFileName.startsWith("file:")) {
			GenericClass oldClass = null;
			try {
				oldClass = MappingToolCommon.unmarshal(processRead(transaction, alfClass.getId()));
			} catch (JAXBException e) {
				throw new AlfrescoControllerException(e);
			}
			previousContentFileName = StringUtils.trimToNull(mappingTool.getFileContentFileName(
					transaction, oldClass));
			previousRepoContentInfo = mappingTool.getRepoContentInfo(transaction, oldClass);
			String type = alfClass.getQualifiedName();
			String fileName = moveFileToUploadDir(type, xformsFileName, transaction);
			mappingTool.setFileContentFileName(alfClass, fileName);
		}

		transaction.queueUpdate(alfClass);
		// TODO: attach content

		// TODO: avoid deleting the temp file
		if (xformsFileName != null) {
			try {
				File tmpFile = new File(xformsFileName);
				File tmpParent = tmpFile.getParentFile();
				tmpFile.delete();
				tmpParent.delete();
			} catch (Exception e) {
				logger.error("Failed to delete temp file");
			}
		}
		// FIXME: ceci ne fonctionnera probablement pas comme il faut lorsque
		// l'upload directory est fourni en paramètre dans l'url. L'ancien
		// fichier associé peut être dans un autre dossier
		if (previousContentFileName != null) {
			File toDelete = new File(getUploadPathInFileSystem().getAbsolutePath(),
					previousContentFileName);
			if (toDelete.delete() == false) {
				logger.error("Failed to delete file: " + toDelete.getAbsolutePath());
			}
		}
		// dans le cas d'un repoContent en update, supprimer l'ancien fichier
		if (previousRepoContentInfo != null) {
			if (StringUtils.startsWithIgnoreCase(previousRepoContentInfo.getPath(), "workspace")) {
				Map<String, String> parameters = new TreeMap<String, String>();
				parameters.put("nodeRef", previousRepoContentInfo.getPath());
				requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_DELETE);
			}
		}
	}

	/**
	 * Process content. Moves the uploaded file to a randomly chosen folder under the file system
	 * upload directory.
	 * 
	 * @param type
	 *            the type
	 * @param fileName
	 *            the file name
	 * 
	 * @return the string
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	private String moveFileToUploadDir(String type, String fileName, AlfrescoTransaction transaction)
			throws AlfrescoControllerException {
		URI fileURI = URI.create(fileName);
		File sourceFile = null;
		try { // #1160
			sourceFile = new File(fileURI);
		} catch (Exception e) {
			String message = "XForms Controller: error when processing the file to upload. Check the path: "
					+ fileURI;
			logger.error(message + e);
			return null;
		}

		uploadDir = getUploadPathInFileSystem();
		File targetFile = findNewName(type, sourceFile.getName());

		copyFile(sourceFile, targetFile);
		String relativePath = targetFile.getAbsolutePath().replace(uploadDir.getAbsolutePath(), "");

		// #1278: we won't delete temporary versions of uploaded files here anymore
		// File parentFile = sourceFile.getParentFile();
		// try {
		// FileUtils.deleteDirectory(parentFile);
		// } catch (IOException e) {
		// logger.error(e);
		// }
		String outputFileName = relativePath.replace('\\', '/');
		transaction.setUploadedFileName(outputFileName);
		transaction.setTempFileName(sourceFile.getAbsolutePath());
		return outputFileName;
	}

	/**
	 * Process repository content. Moves the uploaded file to the location into the content manager.
	 * 
	 * @param transaction
	 * @param fileName
	 *            the file name, name and extension
	 * @param filePath
	 *            the file system complete path to the file to be uploaded. The name and extension
	 *            may be different from parameter 'fileName'.
	 * @param location
	 *            path to a folder in the content management system
	 * @return the string
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	private String processRepoContent(AlfrescoTransaction transaction, String fileName,
			String filePath, String location, String mimetype) throws AlfrescoControllerException {

		// collect parameters
		Map<String, String> parameters = new TreeMap<String, String>();
		URI fileURI = URI.create(filePath);
		String fullFileName = fileURI.getPath();
		parameters.put("filename", fileName);
		parameters.put("filepath", fullFileName);
		parameters.put("location", location);
		parameters.put("mimetype", mimetype);
		// call the webscript
		String resultId = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_UPLOAD);

		return resultId;
	}

	/**
	 * Copy file.
	 * 
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	private void copyFile(File sourceFile, File targetFile) throws AlfrescoControllerException {
		FileChannel srcChannel = null;
		FileChannel dstChannel = null;
		try {
			try {
				srcChannel = new FileInputStream(sourceFile).getChannel();
				dstChannel = new FileOutputStream(targetFile).getChannel();
				dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
			} catch (IOException e) {
				throw new AlfrescoControllerException(e);
			} finally {
				if (srcChannel != null)
					srcChannel.close();
				if (dstChannel != null)
					dstChannel.close();
			}
		} catch (Exception e) {
			throw new AlfrescoControllerException(e);
		}
	}

	/**
	 * Find new name. NOTE: uploadDir must have been set.
	 * 
	 * @param type
	 *            the type
	 * @param fileName
	 *            the file name
	 * 
	 * @return the file
	 */
	private File findNewName(String type, String fileName) {
		if (fileName.contains("\\")) {
			int lastIndexOf = StringUtils.lastIndexOf(fileName, '\\');
			fileName = StringUtils.substring(fileName, lastIndexOf + 1);
		}
		String rootPath = uploadDir.getAbsolutePath() + File.separator + type;

		String randomPath = RandomStringUtils.randomNumeric(3);
		rootPath = rootPath + File.separator + randomPath.charAt(0) + File.separator
				+ randomPath.charAt(1) + File.separator + randomPath.charAt(2);

		File root = new File(rootPath);

		File result = new File(root, fileName);
		if (result.exists()) {
			int dotPos = fileName.lastIndexOf(".");

			String fileNameWihoutExtension = null;
			String fileNameExtension = null;

			if (dotPos == -1) {
				fileNameWihoutExtension = fileName;
			} else {
				fileNameWihoutExtension = fileName.substring(0, dotPos);
				fileNameExtension = fileName.substring(dotPos + 1);
			}
			int i = 0;
			do {
				String newFileName = fileNameWihoutExtension + "-" + i;
				if (fileNameExtension != null) {
					newFileName = newFileName + "." + fileNameExtension;
				}
				result = new File(root, newFileName);
				i++;
			} while (result.exists());
		}
		result.getParentFile().mkdirs();
		return result;
	}

	public void executeBatch(AlfrescoTransaction alfrescoTransaction, Batch batch)
			throws AlfrescoControllerException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("datas", MappingToolCommon.marshal(batch));

		Map<String, String> ids = new HashMap<String, String>();

		Document result = requestDocumentFromAlfresco(alfrescoTransaction, parameters,
				MsgId.INT_WEBSCRIPT_OPCODE_BATCH);
		Element idsElement = result.getDocumentElement();
		NodeList childNodes = idsElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node idNode = childNodes.item(i);
			if (idNode instanceof Element) {
				Element idElement = (Element) idNode;
				String to = null;
				String from = null;
				NodeList idChildNodes = idElement.getChildNodes();
				for (int j = 0; j < idChildNodes.getLength(); j++) {
					Node node = idChildNodes.item(j);
					if (node instanceof Element) {
						Element element = (Element) node;
						if (StringUtils.equals(element.getTagName(), "from")) {
							from = StringUtils.trim(element.getTextContent());
						}
						if (StringUtils.equals(element.getTagName(), "to")) {
							to = StringUtils.trim(element.getTextContent());
						}
					}
				}
				ids.put(from, to);
			}
		}

		alfrescoTransaction.setIds(ids);
	}

	/**
	 * Gets the captions.
	 * 
	 * @param ids
	 *            the ids
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the captions
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	@SuppressWarnings("all")
	public List<String> getCaptions(AlfrescoTransaction transaction, List<String> ids)
			throws AlfrescoControllerException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("query", StringUtils.join(ids, ";"));
		Document requestDocument = requestDocumentFromAlfresco(transaction, parameters,
				MsgId.INT_WEBSCRIPT_OPCODE_LABELS);

		List<String> result = new ArrayList<String>();
		// List elements = DOMUtil.getChildElements(requestDocument.getDocumentElement());
		List<Element> elements = DOMUtil.getAllChildren(requestDocument.getDocumentElement());
		for (Element element : elements) {
			result.add(DOMUtil.getChild(element, "value").getTextContent());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getEnumCaption(AlfrescoTransaction transaction, String code)
			throws AlfrescoControllerException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("query", "enum;" + code);
		Document requestDocument = requestDocumentFromAlfresco(transaction, parameters,
				MsgId.INT_WEBSCRIPT_OPCODE_LABELS);

		String result = null;
		List elements = DOMUtil.getAllChildren(requestDocument.getDocumentElement());
		if (elements.size() > 0) {
			Object object = elements.get(0);
			Element element = (Element) object;
			result = DOMUtil.getChild(element, "value").getTextContent();
		}
		return result;
	}

	/**
	 * Gets the enum.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param type
	 *            the type
	 * @param filterParent
	 *            the filter parent
	 * @param filterData
	 *            the filter data
	 * @param query
	 * 
	 * @return the enum
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Node getDynamicEnum(AlfrescoTransaction transaction, String type, String filterParent,
			String filterData, String query, boolean limit) throws AlfrescoControllerException {
		Map<String, String> parameters = new TreeMap<String, String>();
		String fp = StringUtils.trimToNull(filterParent);
		if (fp != null) {
			parameters.put("parent", fp);
		}
		String fd = StringUtils.trimToNull(filterData);
		if (fd != null) {
			parameters.put("context", fd);
		}
		String q = StringUtils.trimToNull(query);
		if (q != null) {
			parameters.put("query", q);
		}
		if (limit) {
			parameters.put("limit", "true");
		}

		parameters.put("type", mappingTool.getEnumType(type).getName());
		Document requestDocument = requestDocumentFromAlfresco(transaction, parameters,
				MsgId.INT_WEBSCRIPT_OPCODE_ENUM);
		Element documentElement = requestDocument.getDocumentElement();

		Element queryElement = requestDocument.createElement("query");
		queryElement.setTextContent(StringUtils.trimToEmpty(query));
		documentElement.appendChild(queryElement);
		documentElement.appendChild(requestDocument.createElement(MsgId.INT_INSTANCE_SELECTEDID
				.getText()));
		documentElement.appendChild(requestDocument.createElement(MsgId.INT_INSTANCE_SELECTEDLABEL
				.getText()));
		return requestDocument;
	}

	/**
	 * Gets the list. Cette fonction est appelée une première fois pour fournir l'instance, et
	 * chaque fois qu'on tape dans la zone de recherche.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param type
	 *            the type
	 * @param format
	 *            the pattern for formatting labels of the list items
	 * 
	 * @return the list
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Node getList(AlfrescoTransaction transaction, String type, String query,
			String maxResults, String format, String maxLength) throws AlfrescoControllerException {
		Map<String, String> parameters = new TreeMap<String, String>();

		String alfTypeName = null;
		/*
		 * maxSize fixe le nombre d'élements à afficher dans le widget, par défaut, MAX_RESULTS. Ce
		 * nbre sera réinitialisé par le bouton 'Tout'. Valeurs possibles: MAX_RESULTS ("50" ou
		 * celle indiquée ds forms.properties) ou fixé par la propriété 'field size' dans le
		 * modeleur. Dans ce cas, SELECTMAX conserve tjrs la valeur de field size.
		 */
		String maxSize = mappingTool.getFieldSizeForField(type, MAX_RESULTS, lastFormName);
		alfTypeName = mappingTool.getClassType(type).getAlfrescoName();
		parameters.put("type", alfTypeName);
		parameters.put("format", StringUtils.trimToEmpty(format));
		parameters.put("maxLength", StringUtils.trimToEmpty(maxLength));

		String q = StringUtils.trimToNull(query);
		if (q != null) {
			parameters.put("query", q);
		}

		if (StringUtils.trimToNull(maxResults) != null) {
			parameters.put("maxResults", maxResults);
		} else {
			parameters.put("maxResults", maxSize);
		}

		Document reqDoc;
		if (isStandaloneMode()) {
			reqDoc = requestDummyDocumentList(alfTypeName, maxLength);
		} else {
			reqDoc = requestDocumentFromAlfresco(transaction, parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_LIST);
			// ** #1234
			if (reqDoc == null) {
				logger.error("The Alfresco server is unavailable. Returning a dummy list.");
				setStandaloneMode(true);
				reqDoc = requestDummyDocumentList(alfTypeName, "0");
			}
			// ** #1234
		}
		Element docElement = reqDoc.getDocumentElement();

		docElement.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDID.getText()));
		docElement.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDLABEL.getText()));

		Element maxResultsElement = reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDMAX.getText());
		maxResultsElement.setTextContent(maxSize);
		docElement.appendChild(maxResultsElement);

		return reqDoc;

	}

	/**
	 * Returns a fake list built without contacting Alfresco.
	 * 
	 * @param alfrescoName
	 * @param maxLength
	 * @return
	 */
	private Document requestDummyDocumentList(String alfrescoName, String maxLength) {
		Document doc = docBuilder.newDocument();
		int nb = Integer.parseInt(maxLength);
		if (nb == 0) {
			nb = 10;
		}
		Element root = doc.createElement("results");

		Element query = doc.createElement("query");
		Element count = doc.createElement("count");
		count.setTextContent("" + nb);

		Element maxResults = doc.createElement("maxResults");
		maxResults.setTextContent("0");

		Element returned = doc.createElement("returned");
		returned.setTextContent("" + nb);

		Element subquery = doc.createElement("query");
		query.appendChild(count);
		query.appendChild(maxResults);
		query.appendChild(returned);
		query.appendChild(subquery);

		root.appendChild(query);

		for (int i = 0; i < nb; i++) {
			Element item = doc.createElement("item");
			Element id = doc.createElement("id");
			id.setTextContent("" + i);
			Element label = doc.createElement("value");
			label.setTextContent(alfrescoName + "_" + i);

			item.appendChild(id);
			item.appendChild(label);

			root.appendChild(item);
		}
		doc.appendChild(root);
		return doc;
	}

	/**
	 * Provides a bridge to the webscript, returning a String.
	 * 
	 * @param parameters
	 *            the parameters
	 * @param opCode
	 *            the opCode
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the string
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	private String requestString(AlfrescoTransaction transaction, Map<String, String> parameters,
			MsgId opCode) throws AlfrescoControllerException {
		String result = null;
		try {
			PostMethod post = requestPost(transaction, parameters, opCode);
			result = StringUtils.trim(post.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error(e);
			throw new AlfrescoControllerException(e);
		}
		if (result != null && result.startsWith("<exception>")) {
			throw new AlfrescoWebscriptException(result);
		}
		return result;
	}

	/**
	 * Provides a bridge to the webscript, returning a Document.
	 * 
	 * @param parameters
	 *            the parameters
	 * @param opCode
	 *            the opCode
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the document
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	private Document requestDocumentFromAlfresco(AlfrescoTransaction transaction,
			Map<String, String> parameters, MsgId opCode) throws AlfrescoControllerException {
		Document result = null;
		try {
			PostMethod post = requestPost(transaction, parameters, opCode);
			result = synchronizedParse(post.getResponseBodyAsStream()); // #1227
		} catch (ConnectException e) { // #1234
			return null;
		} catch (Exception e) {
			logger.error(e);
			throw new AlfrescoControllerException(e);
		}
		if (result != null) {
			if (StringUtils.equalsIgnoreCase("exception", result.getDocumentElement().getTagName())) {
				throw new AlfrescoWebscriptException(result, transaction);
			}
		}
		return result;
	}

	/**
	 * @param post
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 */
	synchronized public Document synchronizedParse(InputStream is) throws IOException, SAXException {
		return docBuilder.parse(is);
	}

	/**
	 * Request post. Pont vers le webscript XForms sous Alfresco.
	 * 
	 * @param parameters
	 *            the parameters
	 * @param opCode
	 *            the opCode
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the post method
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws HttpException
	 *             the http exception
	 */
	private PostMethod requestPost(AlfrescoTransaction transaction, Map<String, String> parameters,
			MsgId opCode) throws IOException, HttpException {
		logger.debug("Alfresco request " + opCode);
		logger.debug("Parameters : ");
		Set<Entry<String, String>> entrySet2 = parameters.entrySet();
		for (Entry<String, String> entry2 : entrySet2) {
			logger.debug(entry2.getKey() + " = " + entry2.getValue());
		}

		PostMethod post = new PostMethod(ALFRESCO_XFORMS_URL + opCode);
		Set<Entry<String, String>> entrySet = parameters.entrySet();
		for (Entry<String, String> entry : entrySet) {
			post.setParameter(entry.getKey(), entry.getValue());
		}

		if (StringUtils.trimToNull(transaction.getLogin()) == null) {
			post.setParameter("username", getLoginUserName());
		} else {
			post.setParameter("username", transaction.getLogin());
		}

		post.setParameter("serviceCallerId", "XFormsController");
		post.setParameter("serviceCallerVersion", "2.0.0");

		executeMethod(post, false);

		logger.debug("Response : ");
		logger.debug(StringUtils.trim(post.getResponseBodyAsString()));

		return post;
	}

	public String getLoginUserName() {
		String result = null;
		if (initParameters != null) {
			result = initParameters.get(MsgId.KEY_USER_NAME.getText());
		}
		return (StringUtils.trimToNull(result) == null) ? USER_NAME : result;
	}

	@SuppressWarnings("unused")
	private String getLoginUserPswd() {
		String result = null;
		if (initParameters != null) {
			result = initParameters.get(MsgId.KEY_USER_PSWD.getText());
		}
		return (StringUtils.trimToNull(result) == null) ? USER_PSWD : result;
	}

	/**
	 * Execute method.
	 * 
	 * @param method
	 *            the method
	 * @param hasTicket
	 *            the has ticket
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws HttpException
	 *             the http exception
	 */
	private void executeMethod(HttpMethodBase method, boolean hasTicket) throws IOException,
			HttpException {
		HttpClient client = new HttpClient();
		client.executeMethod(method);
	}

	/**
	 * Creates the element.
	 * 
	 * @param type
	 *            the type
	 * @param initParams
	 *            the init params
	 * @param stack
	 *            the stack
	 * @param transaction
	 *            the transaction
	 * @param formIsReadOnly
	 * 
	 * @return the document
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document createElement(AlfrescoTransaction transaction, String type,
			Map<String, String> initParams, Stack<AssociationType> stack, boolean formIsReadOnly)
			throws AlfrescoControllerException {
		return mappingTool.createClassFormsInstance(transaction, type, initParams, stack,
				formIsReadOnly);
	}

	/**
	 * Delete.
	 * 
	 * @param nodeRef
	 *            the node ref
	 * @param transaction
	 *            the transaction
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public void delete(AlfrescoTransaction transaction, String nodeRef)
			throws AlfrescoControllerException {
		transaction.queueDelete(nodeRef);
	}

	/**
	 * Crée un formulaire totalement vide, sans lien avec un objet existant.
	 * 
	 * @param type
	 *            Nom du type de contenu.
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the document
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document createForm(AlfrescoTransaction transaction, String type,
			Map<String, String> initParams, boolean formIsReadOnly)
			throws AlfrescoControllerException {
		return mappingTool.newFormInstance(type, transaction, initParams, formIsReadOnly);
	}

	/**
	 * Load form.
	 * 
	 * @param type
	 *            the type
	 * @param id
	 *            the id
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the document
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 */
	public Document loadForm(AlfrescoTransaction transaction, String type, String id,
			boolean formIsReadOnly) throws AlfrescoControllerException {
		return mappingTool.createFormInstance(transaction, type, id, formIsReadOnly);
	}

	/**
	 * Persist form.
	 * 
	 * @param type
	 *            the type
	 * @param instance
	 *            the instance
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the string
	 * 
	 * @throws AlfrescoControllerException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	public String persistForm(AlfrescoTransaction transaction, String type, Node instance)
			throws AlfrescoControllerException, ServletException {
		GenericClass alfClass = this.transformsToAlfresco(transaction, type, instance);
		if (alfClass.getId() == null) {
			alfClass.setId(save(transaction, alfClass));
		} else {
			update(transaction, alfClass);
		}
		return alfClass.getId();
	}

	/**
	 * Bridge function. Extracted from persistForm to provide an access to workflow actions.
	 * 
	 * @param transaction
	 * @param type
	 * @param instance
	 * @return
	 * @throws AlfrescoControllerException
	 * @throws ServletException
	 */
	public GenericClass transformsToAlfresco(AlfrescoTransaction transaction, String type,
			Node instance) throws AlfrescoControllerException, ServletException {
		return mappingTool.transformsToAlfresco(transaction, this, type, instance);
	}

	/**
	 * Removes the reference.
	 * 
	 * @param node
	 *            the node
	 * @param elementId
	 *            the element id
	 */
	public void removeReference(Document node, String elementId) {
		mappingTool.removeReference(node, elementId);
	}

	/**
	 * Checks for sub types.
	 * 
	 * @param dataType
	 *            the data type
	 * 
	 * @return true, if successful
	 */
	public boolean hasSubTypes(String dataType) {
		return mappingTool.hasSubTypes(dataType);
	}

	/**
	 * Checks if is dynamic enum.
	 * 
	 * @param type
	 *            the type
	 * 
	 * @return true, if is dynamic enum
	 */
	public boolean isDynamicEnum(String type) {
		EnumType enumType = mappingTool.getEnumType(type);
		return enumType.isDynamic();
	}

	/**
	 * Tells whether an AttributeType refers to a file field with upload to file system.
	 * 
	 * @param xformsAttribute
	 *            the object to test
	 * @return the status
	 */
	public boolean isFileContent(AttributeType xformsAttribute) {
		return (xformsAttribute.getName().endsWith("content"));
	}

	/**
	 * Tells whether an AttributeType refers to a file field with upload to the repository.
	 * 
	 * @param xformsAttribute
	 *            the object to test
	 * @return the status
	 */
	public boolean isRepositoryContent(AttributeType xformsAttribute) {
		return (xformsAttribute.getName().endsWith("repositoryContent"));
	}

	/**
	 * Tells whether an AttributeType refers to a file upload field.
	 * 
	 * @param xformsAttribute
	 *            the object to test
	 * @return the status
	 */
	public boolean isFileField(AttributeType xformsAttribute) {
		return (isRepositoryContent(xformsAttribute) || isFileContent(xformsAttribute));
	}

	/**
	 * Gets the label under which an association is displayed on a specific form
	 * 
	 * @param completeAssoName
	 * @param dataType
	 *            the form id, i.e. a class/name or form/name in the mapping.xml file
	 * @return
	 */
	public String getShortAssociationName(String completeAssoName, String dataType) {
		return mappingTool.getDisplayLabelFromAssociationName(completeAssoName, dataType);
	}

	/**
	 * Extracts the id to be edited from an XForms instance. The DOM node providing that id must be
	 * reset (i.e. emptied) so that subsequent editions may happen correctly on the same form.
	 * 
	 * @param node
	 * @return the id, or null (this latter case should not happen if setting the id in the instance
	 *         is done correctly).
	 */
	public String getAndResetEditNode(Node node) {
		Element rootElt = ((Document) node).getDocumentElement();
		Element editIdElt = DOMUtil.getElementInDescentByNameNonNull(rootElt,
				MsgId.INT_INSTANCE_SIDEEDIT.getText());
		if (editIdElt != null) {
			String id = editIdElt.getTextContent();
			editIdElt.setTextContent(null);
			logger.debug("Received editedid: " + id);
			return id;
		}
		logger.error("No id found for node edition.");
		return null;
	}

	//
	// additions for workflows
	//
	/**
	 * Starts a workflow on Alfresco
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param workflowDefinitionId
	 * @param attributes
	 *            Qualified Alfresco Attributes
	 * @return Newly created Workflow
	 * @throws AlfrescoControllerException
	 */
	public WorkflowPath workflowStart(AlfrescoTransaction transaction, String workflowDefinitionId,
			Map<QName, Serializable> attributes) throws AlfrescoControllerException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("wfName", workflowDefinitionId);
		parameters.put("attributes", attributes);
		return (WorkflowPath) workflowRequestCall(transaction, "wfStart", parameters);
	}

	/**
	 * Update a workflow on Alfresco
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param taskId
	 *            Task to update
	 * @param attributes
	 *            Qualified Alfresco Attributes
	 * @param add
	 *            Linked instances to add
	 * @param remove
	 *            Linked instances to remove
	 * @return Updated task
	 * @throws AlfrescoControllerException
	 */
	public WorkflowTask workflowEndTask(AlfrescoTransaction transaction, String taskId,
			String transitionId) throws AlfrescoControllerException {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(taskId);
		paramList.add(transitionId);
		return (WorkflowTask) workflowRequest(transaction, "endTask", paramList);
	}

	public WorkflowTask workflowUpdateTask(AlfrescoTransaction transaction, String taskId,
			Map<QName, Serializable> attributes, Map<QName, List<NodeRef>> add,
			Map<QName, List<NodeRef>> remove) throws AlfrescoControllerException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("taskId", taskId);
		parameters.put("attributes", attributes);
		parameters.put("add", add);
		parameters.put("remove", remove);
		return (WorkflowTask) workflowRequestCall(transaction, "wfUpdate", parameters);
	}

	/**
	 * Cancel a workflow
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param workflowId
	 *            Id of the workflow to cancel
	 * @return The updated instance
	 * @throws AlfrescoControllerException
	 */
	public WorkflowInstance workflowCancel(AlfrescoTransaction transaction, String workflowId)
			throws AlfrescoControllerException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("workflowId", workflowId);
		return (WorkflowInstance) workflowRequestCall(transaction, "wfCancel", parameters);
	}

	/**
	 * Delete a workflow instance
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param workflowId
	 *            Id of the instance to delete
	 * @return The updated instance (yes, a deleted instance!...)
	 * @throws AlfrescoControllerException
	 */
	@Deprecated
	public WorkflowInstance workflowDelete(AlfrescoTransaction transaction, String workflowId)
			throws AlfrescoControllerException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("workflowId", workflowId);
		return (WorkflowInstance) workflowRequestCall(transaction, "wfDelete", parameters);
	}

	/**
	 * Collects all non empty properties available on an instanceId.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param instanceId
	 *            the workflow instance Id
	 * @return
	 * @throws AlfrescoControllerException
	 */
	@SuppressWarnings("unchecked")
	private Map<QName, Serializable> workflowCollectInstanceProperties(
			AlfrescoTransaction transaction, String instanceId) throws AlfrescoControllerException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("workflowId", instanceId);

		return (Map<QName, Serializable>) workflowRequestCall(transaction,
				"wfCollectInstanceProperties", parameters);
	}

	/**
	 * Creates a workflow package under the default package location. Adds an existing content to an
	 * existing workflow package so that a call to WorkflowService.getWorkflowsForContent for that
	 * content links to the workflow for which wkPackage is the package.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @return the new package
	 * @throws AlfrescoControllerException
	 */
	public NodeRef workflowCreatePackage(AlfrescoTransaction transaction, String nodeToAdd,
			String userName) throws AlfrescoControllerException {
		// we use workflowRequest as proxy here
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("container", (String) null);
		NodeRef wkPackage = (NodeRef) workflowRequestCall(transaction, "createPackage", params);
		// but here, we call the webscript directly
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("content", nodeToAdd);
		parameters.put("package", wkPackage.toString());
		transaction.setLogin(userName);
		String resultId = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_PACKAGE);

		if (StringUtils.trimToNull(resultId) != null) {
			return wkPackage;
		}
		return null;
	}

	/**
	 * Calls a method of org.alfresco.service.cmr.workflowWorkflowService.<br/>
	 * 
	 * @param transaction
	 *            a transaction object. May be null.
	 * @param methodName
	 *            the name of the method to call, case-sensitive
	 * @param methodParameters
	 *            a list containing the method parameters in the order of the function's signature.
	 *            CANNOT BE NULL.
	 * @return an object of the return type of the function that was called.
	 * @throws AlfrescoControllerException
	 */
	public Object workflowRequest(AlfrescoTransaction transaction, String methodName,
			List<Object> methodParameters) throws AlfrescoControllerException {
		Map<String, Object> parameterMaps = new HashMap<String, Object>();
		int i = 0;
		for (Object parameter : methodParameters) {
			parameterMaps.put("arg" + i, parameter);
			i++;
		}
		if (transaction == null) {
			transaction = new AlfrescoTransaction(this);
		}
		return workflowRequestCall(transaction, methodName, parameterMaps);
	}

	/**
	 * Execute a workflow request on Alfresco.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param methodName
	 *            Method to call (mapped in WorkflowService)
	 * @param methodParameters
	 *            Parameters needed by the method (same types as declared by the interface)
	 * @return The deserialized Object
	 * @throws AlfrescoControllerException
	 */
	private Object workflowRequestCall(AlfrescoTransaction transaction, String methodName,
			Map<String, Object> methodParameters) throws AlfrescoControllerException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("method", methodName);
		Set<String> keys = methodParameters.keySet();
		for (String key : keys) {
			Object parameterValue = methodParameters.get(key);
			if (parameterValue instanceof String) {
				parameters.put(key, (String) parameterValue);
			} else {
				String xmlParameter = xstream.toXML(parameterValue);
				parameters.put(key, xmlParameter);
			}
		}
		String sresult;

		if (AlfrescoController.isStandaloneMode()) {
			return null;
		}

		try {
			sresult = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_WORKFLOW);
		} catch (AlfrescoControllerException e) {
			return null;
		}
		Object result = null;
		if (StringUtils.trimToNull(sresult) != null) {
			result = xstream.fromXML(sresult);
		}
		return result;
	}

	/**
	 * Gets the value of a property for a node.
	 * 
	 * @param node
	 * @param propname
	 * @return the value of the property "propname" for a node. Returns null if an exception
	 *         occurred or if the value is <b>null</b>.
	 */
	public String systemGetNodeProperty(NodeRef node, QName propname) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "NodeService");
		parameters.put("methodName", "getProperty");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(node);
		paramList.add(propname);
		parameters.put("methodParams", xstream.toXML(paramList));
		String result;
		try {
			result = (String) xstream.fromXML(requestString(new AlfrescoTransaction(this),
					parameters, MsgId.INT_WEBSCRIPT_OPCODE_SERVICE));
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Gets the type of a node.
	 * 
	 * @param dataId
	 * @return the QName corresponding to the content type. Returns null if an exception occurred or
	 *         if the value is <b>null</b>, which is a hint that either the webscript is not
	 *         available or the object does not exist.
	 */
	public QName systemGetNodeType(String dataId) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "NodeService");
		parameters.put("methodName", "getType");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		NodeRef noderef = new NodeRef(patchDataId(dataId));
		paramList.add(noderef);
		parameters.put("methodParams", xstream.toXML(paramList));
		QName result;
		try {
			String requestString = requestString(new AlfrescoTransaction(this), parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (QName) xstream.fromXML(requestString);
			// } catch (AlfrescoControllerException e) {
			// e.printStackTrace();
			// return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Returns the node ref for a user identified by name. If no user with that name exists, the
	 * authority will not be created.
	 * 
	 * @param userName
	 * @return the noderef for the user name. Returns null if an exception occurred or if the value
	 *         is <b>null</b>.
	 */
	public NodeRef systemGetNodeRefForUser(String userName) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "AuthorityDAO");
		parameters.put("methodName", "getAuthorityNodeRefOrNull");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(userName);
		parameters.put("methodParams", xstream.toXML(paramList));
		NodeRef result;
		try {
			String resultStr = requestString(new AlfrescoTransaction(this), parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (NodeRef) xstream.fromXML(resultStr);
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public NodeRef systemGetNodeRefForPerson(String userName) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "PersonService");
		parameters.put("methodName", "getPerson");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(userName);
		parameters.put("methodParams", xstream.toXML(paramList));
		NodeRef result;
		try {
			String resultStr = requestString(new AlfrescoTransaction(this), parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (NodeRef) xstream.fromXML(resultStr);
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Returns the node ref for a user group identified by name.
	 * 
	 * @param groupName
	 *            the group name as can be seen in Alfresco's web client. The system prefix will be
	 *            prepended before calling the web script.
	 * @return the noderef for the user group. Returns null if an exception occurred or if the group
	 *         does not exist.
	 */
	public NodeRef systemGetNodeRefForGroup(String groupName) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "AuthorityDAO");
		parameters.put("methodName", "getAuthorityNodeRefOrNull");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(PermissionService.GROUP_PREFIX + groupName);
		parameters.put("methodParams", xstream.toXML(paramList));
		NodeRef result;
		try {
			String resultStr = requestString(new AlfrescoTransaction(this), parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (NodeRef) xstream.fromXML(resultStr);
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Returns all known groups, including standard Alfresco groups.
	 * 
	 * @param asGroups
	 *            if true, specifies only groups are returned. If false, only users.
	 * @return the set of registered user groups. Returns null if an exception occurred or if the
	 *         value is <b>null</b>.
	 */
	@SuppressWarnings("unchecked")
	public Set<String> systemGetAllAuthoritiesAsGroupsOrUsers(boolean asGroups) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "AuthorityDAO");
		parameters.put("methodName", "getAllAuthorities");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		if (asGroups) {
			paramList.add(AuthorityType.GROUP);
		} else {
			paramList.add(AuthorityType.USER);
		}
		parameters.put("methodParams", xstream.toXML(paramList));
		Set<String> result;
		try {
			String resultStr = requestString(new AlfrescoTransaction(this), parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (Set<String>) xstream.fromXML(resultStr);
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Returns all groups a specific user belongs to, including non immediate parent groups.
	 * 
	 * @param userName
	 *            the name of the user.
	 * @return the set of groups the user is part of. Returns null if an exception occurred or if
	 *         the value is <b>null</b>.
	 */
	@SuppressWarnings("unchecked")
	public Set<String> systemGetContaingGroups(String userName) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "AuthorityDAO");
		parameters.put("methodName", "getContainingAuthorities");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(AuthorityType.GROUP);
		paramList.add(userName);
		paramList.add(false);
		parameters.put("methodParams", xstream.toXML(paramList));
		Set<String> result;
		try {
			String resultStr = requestString(new AlfrescoTransaction(this), parameters,
					MsgId.INT_WEBSCRIPT_OPCODE_SERVICE);
			result = (Set<String>) xstream.fromXML(resultStr);
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * If an instance id is provided, uses it to fill process info. Otherwise, leaves the bean
	 * untouched.<br/>
	 * Process info provided: id, name.
	 * 
	 * @param bean
	 */
	// @Deprecated
	// public void fillInProcessInfo(WorkflowBean bean) {
	// if (bean.instanceId != null) {
	// Vector<Object> paramList = new Vector<Object>();
	// paramList.add(bean.instanceId);
	// WorkflowInstance wkInstance;
	// try {
	// wkInstance = (WorkflowInstance) workflowRequest(null, "getWorkflowById", paramList);
	// } catch (AlfrescoControllerException e) {
	// logger.error(MsgId.INT_EXC_WKFLW_GET_INSTANCE + bean.instanceId);
	// // any exception should let us continue so we just bail out.
	// return;
	// }
	// if (wkInstance == null) {
	// // ever reachable ?
	// logger.error("Bad instance id provided: " + bean.instanceId);
	// return;
	// }
	// bean.processId = wkInstance.definition.id;
	// bean.processName = workflowExtractProcessNameFromDefName(wkInstance.definition.name);
	// }
	// throw new RuntimeException("Deprecated function");
	// }
	/**
	 * Gets the name of the content type associated with the start task of a workflow definition. <br/>
	 * <b>Sets the process name, task name, task data type in the bean.</b>
	 * 
	 * @param bean
	 * @return the name of the node that models the start task or <b>null</b> if anything abnormal
	 *         occurs.
	 */
	// @Deprecated
	// public String getStartTaskName(WorkflowBean bean) {
	// WorkflowDefinition def;
	// Vector<Object> paramList = new Vector<Object>();
	// paramList.add(bean.processId);
	// try {
	// def = (WorkflowDefinition) workflowRequest(null, "getDefinitionById", paramList);
	// } catch (AlfrescoControllerException e) {
	// // any exception should let us continue so bail out.
	// return null;
	// }
	// if (def == null) {
	// // ever reachable ?
	// return null;
	// }
	// WorkflowTaskDefinition taskDef = def.getStartTaskDefinition();
	// bean.processName = workflowExtractProcessNameFromDefName(def.name);
	// bean.taskName = taskDef.node.name;
	// bean.taskDataType = taskDef.metadata.getName();
	// return taskDef.node.name;
	// throw new RuntimeException("Deprecated function");
	// }
	/**
	 * Retrieves all in-progress tasks found for the instance. Since several paths may be associated
	 * with the instance, each active path may provide tasks.
	 * 
	 * @param path
	 * @return null if exception, otherwise the list (possibly empty) of in-progress tasks.
	 * @throws AlfrescoControllerException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkflowTask> workflowGetCurrentTasks(String instanceId) {
		List<WorkflowTask> result = new Vector<WorkflowTask>();
		// get the paths from the instance id, which should exist
		List<WorkflowPath> paths = null;
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(instanceId);
		try {
			paths = (List<WorkflowPath>) workflowRequest(null, "getWorkflowPaths", paramList);
		} catch (AlfrescoControllerException e) {
			logger.error(MsgId.INT_EXC_WKFLW_GET_INSTANCE_PATHS + instanceId);
			e.printStackTrace();
			return null;
		}
		if (paths == null) {
			logger.error(MsgId.INT_ERR_NULL_WKFLW_INSTANCE_PATHS + instanceId);
			return result;
		}
		// we need to probe all active paths
		for (WorkflowPath path : paths) {
			if (path.active) {
				// get the tasks for the path
				List<WorkflowTask> tasks = null;
				ArrayList<Object> params = new ArrayList<Object>();
				params.add(path.id);
				try {
					tasks = (List<WorkflowTask>) workflowRequest(null, "getTasksForWorkflowPath",
							params);
				} catch (AlfrescoControllerException e) {
					e.printStackTrace();
					return null;
				}
				// add the tasks to complete
				if (tasks != null) {
					for (WorkflowTask task : tasks) {
						if (task.state == WorkflowTaskState.IN_PROGRESS) {
							result.add(task);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Retrieves a specific task definition for a given process Id.
	 * 
	 * @param processDefId
	 * @param task
	 *            the task to search for in the definition
	 * @return
	 * @throws AlfrescoControllerException
	 */
	public WorkflowTaskDefinition workflowGetTaskDefinition(String processDefId, String task)
			throws AlfrescoControllerException {

		List<WorkflowTaskDefinition> taskDefs = workflowGetTaskDefinitions(processDefId);
		for (WorkflowTaskDefinition taskDef : taskDefs) {
			if (StringUtils.equals(taskDef.id, task)) {
				return taskDef;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<WorkflowTaskDefinition> workflowGetTaskDefinitions(String processDefId)
			throws AlfrescoControllerException {
		String methodName = "getTaskDefinitions";
		List<Object> params = new ArrayList<Object>();
		params.add(processDefId);
		List<WorkflowTaskDefinition> workflowRequest = (List<WorkflowTaskDefinition>) workflowRequest(
				null, methodName, params);
		return workflowRequest;
	}

	@SuppressWarnings("unchecked")
	public List<WorkflowInstance> workflowGetWorkflowsForContent(String refStr, boolean onlyActive)
			throws AlfrescoControllerException {
		NodeRef nodeRef = new NodeRef(refStr);
		String methodName = "getWorkflowsForContent";
		List<Object> params = new ArrayList<Object>();
		params.add(nodeRef);
		params.add(onlyActive);
		List<WorkflowInstance> workflowRequest = (List<WorkflowInstance>) workflowRequest(null,
				methodName, params);
		return workflowRequest;
	}

	public WorkflowDefinition workflowGetWorkflowById(String defId)
			throws AlfrescoControllerException {
		String methodName = "getDefinitionById";
		List<Object> params = new ArrayList<Object>();
		params.add(defId);
		WorkflowDefinition workflowRequest = (WorkflowDefinition) workflowRequest(null, methodName,
				params);
		return workflowRequest;
	}

	/**
	 * Returns an instance for workflow forms so that they can be displayed.
	 * 
	 * @see com.bluexml.xforms.actions.WorkflowFormGetAction
	 * @param formName
	 * @return
	 * @throws ParserConfigurationException
	 * @throws AlfrescoControllerException
	 */
	public Document getWorkflowFormInstance(String formName) throws ParserConfigurationException,
			AlfrescoControllerException {
		Document instance = docBuilder.newDocument();

		WorkflowTaskType taskType = mappingTool.getWorkflowTaskType(formName, false);
		Map<String, GenericClass> alfrescoNodes = new HashMap<String, GenericClass>();

		Element taskElt = instance.createElement(formName);

		mappingTool.collectTaskProperties(instance, taskElt, taskType, alfrescoNodes, false);

		Element rootElement = instance.createElement(MsgId.INT_WKFLW_NODESET_PREFIX.getText());
		rootElement.appendChild(taskElt);

		instance.appendChild(rootElement);
		return instance;
	}

	/**
	 * Returns the mapping entry for a form based on the form name.
	 * 
	 * @param formName
	 * @return
	 */
	public FormType getFormType(String formName) {
		return mappingTool.getFormType(formName);
	}

	/**
	 * Returns the mapping entry for a task based on the form name.
	 * 
	 * @param formName
	 * @return
	 */
	public WorkflowTaskType getWorkflowTaskType(String formName) {
		return mappingTool.getWorkflowTaskType(formName, false);
	}

	/**
	 * Returns the mapping entry for a task based on the task id.
	 * 
	 * @param taskId
	 * @return
	 */
	public WorkflowTaskType getWorkflowTaskTypeById(String taskId) {
		return mappingTool.getWorkflowTaskType(taskId, true);
	}

	/**
	 * Returns the mapping entry for the task that contains the field whose alfrescoName matches.
	 * 
	 * @param fieldName
	 *            the value which alfrescoNameof fields are tested against
	 * @return
	 */
	public WorkflowTaskType getWorkflowTaskTypeWithField(String fieldName) {
		return mappingTool.getWorkflowTaskTypeWithField(fieldName);
	}

	/**
	 * Returns the mapping entry for a specific field from a specific form / workflow.
	 * 
	 * @param taskType
	 * @param fieldName
	 * @return
	 */
	private FormFieldType getFieldFromCanisterType(CanisterType taskType, String fieldName) {
		return mappingTool.getFormFieldTypeFromCanister(taskType, fieldName);
	}

	/**
	 * Creates a folder. Used when starting workflows to create workflow packages.
	 * 
	 * @param folderPath
	 * @param userName
	 * @return
	 * @throws AlfrescoControllerException
	 */
	public String createPathInRepository(String folderPath, String userName)
			throws AlfrescoControllerException {

		// collect parameters
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("path", folderPath);
		// call the webscript
		AlfrescoTransaction transaction = new AlfrescoTransaction(this);
		transaction.setLogin(userName);
		String resultId = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_MKDIR);

		return resultId;
	}

	// Remove. Initially written for debugging purposes.
	@Deprecated
	public String systemNodeGetPath(NodeRef noderef) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "NodeService");
		parameters.put("methodName", "getPath");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(noderef);
		//
		parameters.put("methodParams", xstream.toXML(paramList));
		Path result;
		try {
			result = (Path) xstream.fromXML(requestString(new AlfrescoTransaction(this),
					parameters, MsgId.INT_WEBSCRIPT_OPCODE_SERVICE));
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return null;
		}
		return result.toString();
	}

	/**
	 * Gets the process name from the form name.
	 * 
	 * @param formName
	 * @return the name of the process (e.g. "Evaluation_Demarrage" -> "Demarrage")
	 */
	public static String workflowExtractTaskNameFromFormName(String formName) {
		return formName.substring(formName.indexOf("_") + 1);
	}

	/**
	 * Gets the process name from the form name.<br/>
	 * NOTE: a duplicate of this function is also defined in MappingGenerator
	 * 
	 * @param formName
	 * @return the name of the process (e.g. "Evaluation_Demarrage" -> "Evaluation")
	 */
	public static String workflowExtractProcessNameFromFormName(String formName) {
		return formName.substring(0, formName.indexOf("_"));
	}

	/**
	 * Returns the namespace part of a generated workflow from a WorkflowDefinition.name. In case
	 * the process was not defined through the workflow modeler, the parameter is returned as is.
	 * 
	 * @param name
	 *            (e.g. "jbpm$wfbxEvaluation:Evaluation")
	 * @return the local name of the process (e.g. "wfbxEvaluation")
	 */
	public static String workflowExtractNamespaceName(String name) {
		int start = name.indexOf(BLUEXML_WORKFLOW_PREFIX);
		int end = name.indexOf(':');
		if ((start == -1) || (end == -1) || (end < start)) {
			return name;
		}
		String prefix = name.substring(start, end);
		return prefix;
	}

	/**
	 * Tells whether a workflow definition fits the definition name of processes generated via the
	 * modeler.
	 * 
	 * @param name
	 * @return true if the definition name is a candidate.
	 */
	public static boolean workflowIsBlueXMLDefinition(String name) {
		int start = name.indexOf(BLUEXML_WORKFLOW_PREFIX);
		int end = name.indexOf(':');
		if (start == -1 || end == -1 || (end < start)) {
			return false;
		}
		String prefix = name.substring(start, end);
		String processName = name.substring(end + 1);
		return StringUtils.endsWith(prefix, processName);
	}

	/**
	 * Gets the process name from WorkflowDefinition name.
	 * 
	 * @param name
	 * @return the local name of the process (e.g. "jbpm$wf:review" -> "review")
	 */
	public static String workflowExtractProcessNameFromDefName(String name) {
		return name.substring(name.indexOf(':') + 1);
	}

	/**
	 * Gives the name under which a process is known by the workflow engine under Alfresco. <br/>
	 * Used when reacting to a workflow transition action. <br/>
	 * e.g. "Evaluation" --> "jbpm$wfbxEvaluation:Evaluation"
	 * 
	 * @param processName
	 * @return the process definition as should be found in WorkflowDefinition.name.
	 */
	public static String workflowBuildBlueXMLDefinitionName(String processName) {
		return "jbpm$" + BLUEXML_WORKFLOW_PREFIX + processName + ":" + processName;
	}

	/**
	 * Gives the name under which a task is defined in the process definition model. <br/>
	 * e.g. "Evaluation_Annotation" --> "wfbxEvaluation:Annotation"<br/>
	 * NOTE: a duplicate of this function is also defined in MappingGenerator
	 * 
	 * @param formName
	 * @return the task name as should be found in the generated model.xml or processDefintion.xml.
	 */
	public static String workflowBuildBlueXMLTaskName(String formName) {
		return BLUEXML_WORKFLOW_PREFIX + formName.replace('_', ':');
	}

	/**
	 * Gives the name of the form that corresponds to a task name.<br/>
	 * e.g. "wfbxEvaluation:Annotation" --> "Evaluation_Annotation"<br/>
	 * 
	 * @param processName
	 * @return the form name.
	 */
	public static String workflowBuildFormNameFromTask(String taskName) {
		if (taskName.indexOf(BLUEXML_WORKFLOW_PREFIX) != 0) {
			return null;
		}
		String substr = taskName.substring(BLUEXML_WORKFLOW_PREFIX.length());
		return substr.replace(':', '_');
	}

	/**
	 * Builds the namespace URI for workflow models generated with SIDE.
	 * 
	 * @param processName
	 * @return
	 */
	public static String workflowBuildNamespaceURI(String processName) {
		return MsgId.INT_WKFKW_BLUEXML_NAMESPACE + processName + "/1.0";
	}

	/**
	 * Gets a list of in-progress workflow tasks for a user.
	 * 
	 * @param userName
	 * @return
	 * @throws AlfrescoControllerException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkflowTask> workflowGetPooledTasks(String userName)
			throws AlfrescoControllerException {
		String methodName = "getPooledTasks";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(userName);
		List<WorkflowTask> workflowRequest = (List<WorkflowTask>) workflowRequest(null, methodName,
				methodParameters);
		return workflowRequest;
	}

	/**
	 * Gets the WorkflowTask object for the given task id.
	 * 
	 * @param taskId
	 * @return the requested task object, or <b>null</b> if not found
	 * @throws AlfrescoControllerException
	 */
	public WorkflowTask workflowGetTaskById(String taskId) throws AlfrescoControllerException {
		String methodName = "getTaskById";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(taskId);
		WorkflowTask workflowRequest = (WorkflowTask) workflowRequest(null, methodName,
				methodParameters);
		return workflowRequest;
	}

	/**
	 * Gets the contents of the workflow package for the given task id.
	 * 
	 * @param taskId
	 * @return the list of items associated with the task's workflow package
	 * @throws AlfrescoControllerException
	 */
	@SuppressWarnings("unchecked")
	public List<NodeRef> workflowGetPackageContents(String taskId)
			throws AlfrescoControllerException {
		String methodName = "getPackageContents";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(taskId);
		List<NodeRef> workflowRequest = (List<NodeRef>) workflowRequest(null, methodName,
				methodParameters);
		return workflowRequest;
	}

	/**
	 * Sets initial values for workflow fields from BlueXML properties (stored in the repository) of
	 * the workflow instance.
	 * 
	 * @param wkFormName
	 *            the name of the workflow form to display
	 * @param doc
	 *            the XForms instance
	 * @param instanceId
	 *            the workflow instance Id the XForms instance
	 * @return false if a lethal exception occurred. True if the normal end of the function was
	 *         reached, which does not mean anything about the setting of initial values.
	 */
	public boolean patchWorkflowInstance(AlfrescoTransaction transaction, String wkFormName,
			Document doc, String instanceId) {
		QName qname;
		String namespaceURI = null; // to be set once
		Map<QName, Serializable> properties = null; // to be set once

		Element root = doc.getDocumentElement();
		Element formElt = DOMUtil.getChild(root, wkFormName);
		List<Element> allFields = DOMUtil.getAllChildren(formElt);

		// we need to fail silently so that the form is displayed even in the event of errors
		for (Element field : allFields) {
			String fieldUniqueName = field.getTagName();
			Serializable fieldValue = null;
			WorkflowTaskType taskType = getWorkflowTaskTypeWithField(fieldUniqueName);
			if (taskType != null) {
				// build the QName
				if (namespaceURI == null) {
					String processName = workflowExtractProcessNameFromFormName(wkFormName);
					namespaceURI = workflowBuildNamespaceURI(processName);
				}
				FormFieldType fieldType = getFieldFromCanisterType(taskType, fieldUniqueName);
				String localName = fieldType.getAlfrescoName();
				qname = QName.createQName(namespaceURI, localName);

				// read the QName value from the collected properties of the workflow instance
				if (properties == null) {
					try {
						properties = workflowCollectInstanceProperties(transaction, instanceId);
					} catch (AlfrescoControllerException e) {
						e.printStackTrace();
						return false; // there's no point in continuing without the properties
					}
				}
				try {
					// set the initial value
					fieldValue = properties.get(qname);
					if (fieldValue != null) {
						field.setTextContent(fieldValue.toString());
					}
				} catch (NullPointerException e) {
					// we'll get this when trying to display workflow forms and the webscript is
					// down
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the real form supporting data entry for any form. If the form is a workflow form,
	 * returns its data form. Otherwise, returns the given form name.
	 * 
	 * @param formName
	 * @return the name of the data form. If the form is already a data form, its name is returned.
	 *         If the form is a workflow form, the name of its data form is returned.
	 */
	public String getUnderlyingForm(String formName) {
		WorkflowTaskType taskType = getWorkflowTaskType(formName);
		if (taskType == null) {
			FormType formType = getFormType(formName);
			if (formType == null) {
				return null;
			}
			return formType.getName();
		}
		return getUnderlyingForm(taskType.getDataForm());
	}

	/**
	 * @param standaloneMode
	 *            the standaloneMode to set
	 */
	public static void setStandaloneMode(boolean standaloneMode) {
		AlfrescoController.standaloneMode = standaloneMode;
	}

	/**
	 * @return the standaloneMode
	 */
	public static boolean isStandaloneMode() {
		return standaloneMode;
	}

	/**
	 * @param cssUrl
	 *            the cssUrl to set
	 */
	public static void setCssUrl(String cssUrl) {
		CssUrl = cssUrl;
	}

	/**
	 * @return the cssUrl
	 */
	public static String getCssUrl() {
		return CssUrl;
	}

	public String getReadOnlyFormsSuffix() {
		return MsgId.INT_READ_ONLY_FORMS_SUFFIX.getText();
	}

	/**
	 * @param bean
	 * @return
	 */
	public String getDataTypeFromFormName(String formName) {
		String underlyingDataType = formName;
		String readOnlyFormsSuffix = AlfrescoController.getInstance().getReadOnlyFormsSuffix();
		if (StringUtils.endsWith(formName, readOnlyFormsSuffix)) {
			underlyingDataType = StringUtils.removeEnd(formName, readOnlyFormsSuffix);
		}
		return underlyingDataType;
	}

	/**
	 * Tests authentication credentials with an Alfresco instance.
	 * 
	 * @param username
	 * @param password
	 * @return true if the authentication succeeded, false otherwise or in case of exception.
	 */
	public boolean authenticate(String username, String password) {
		AlfrescoTransaction transaction = new AlfrescoTransaction(this);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", username);
		parameters.put("password", password);
		transaction.setLogin(username);
		String result;
		try {
			result = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_AUTHENTICATE);
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return false;
		}
		return StringUtils.equals(result, "success");
	}

	public String getWebscriptHelp() {
		String result;
		AlfrescoTransaction transaction = new AlfrescoTransaction(this);
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			result = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_HELP);
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Reloads dynamically all static (class-related) info (such as the mapping.xml file).
	 * 
	 * @return false if an exception occurred
	 */
	public boolean performDynamicReload() {
		try {
			AlfrescoController.loadMappingXml();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param nodeId
	 * @return the info about a content
	 */
	public String getWebscriptNodeContentInfo(String nodeId) {
		String result;
		String request;
		AlfrescoTransaction transaction = new AlfrescoTransaction(this);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("nodeId", nodeId);

		try {
			request = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_NODE_INFO);
		} catch (AlfrescoControllerException e) {
			e.printStackTrace();
			return null;
		}

		String[] infos = request.split(",");
		result = infos[0]; // the node name
		long size = Long.parseLong(infos[1]); // content size
		String sizeStr;
		if (size > 0) {
			sizeStr = ": " + size + " bytes";
			char multiplier = getFileSizeMultiplier(size);
			if (multiplier != 'b') {
				sizeStr += " (" + getFileSizeShortReadable(size, multiplier) + ")";
			}
		} else {
			sizeStr = "(no content)";
		}
		result += " " + sizeStr;
		return result;
	}

	private static char getFileSizeMultiplier(long size) {
		char multiplier = 'b'; // bytes
		if (size > 1024 * 1024 * 1024) {
			multiplier = 'g';
		} else if (size > 1024 * 1024) {
			multiplier = 'm';
		} else if (size > 1024) {
			multiplier = 'k';
		}
		return multiplier;
	}

	private static String getFileSizeShortReadable(long transferred, char multiplier) {
		float res = transferred;
		String unit = "B";
		switch (multiplier) {
			case 'k':
			case 'K':
				res = (float) transferred / (float) (1024);
				unit = "KB";
				break;
			case 'm':
			case 'M':
				res = (float) transferred / (float) (1024 * 1024);
				unit = "MB";
				break;
			case 'g':
			case 'G':
				res = (float) transferred / (float) (1024 * 1024 * 1024);
				unit = "GB";
				break;
		}
		return res + " " + unit;
	}

	//
	// REDIRECTION
	//

	/**
	 * Returns the redirection bean for a specific workflow form.
	 * 
	 * @param formName
	 *            the name of the form (e.g. Evaluation_Demarrage)
	 */
	public RedirectionBean workflowGetRedirectionBean(String formName) {
		return targetTable.get(formName);
	}

	/**
	 * 
	 * @return
	 */
	public static boolean loadRedirectionTable(String filePath) {
		InputStream stream = null;
		if (StringUtils.trimToNull(filePath) != null) {
			try {
				File file = new File(filePath);
				stream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (stream == null) {
			if (StringUtils.trimToNull(redirectXmlPath) != null) {
				try {
					File file = new File(redirectXmlPath);
					stream = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			stream = AlfrescoController.class.getResourceAsStream("/redirect.xml");
			if (stream == null) {
				return false;
			}
		} else {
			redirectXmlPath = filePath;
		}
		String formName;
		String url;
		boolean autoAdvance;
		boolean addParams;

		Document document = DOMUtil.getDocumentFromStream(stream);
		if (document == null) {
			return false;
		}

		// we won't check the tag name for the root element
		Element root = document.getDocumentElement();
		List<Element> entries = DOMUtil.getChildren(root, MsgId.INT_GEN_REDIRECT_ENTRY.getText());
		// for each entry of the file, store the info
		for (Element entry : entries) {
			Element nameElt = DOMUtil.getChild(entry, MsgId.INT_GEN_REDIRECT_NAME.getText());
			if (nameElt == null) {
				// get rid of everything previously read
				targetTable = new HashMap<String, RedirectionBean>();
				return false;
			}
			formName = nameElt.getTextContent();
			Element urlElt = DOMUtil.getChild(entry, MsgId.INT_GEN_REDIRECT_URL.getText());
			url = urlElt.getTextContent();
			Element autoElt = DOMUtil
					.getChild(entry, MsgId.INT_GEN_REDIRECT_AUTO_ADVANCE.getText());
			autoAdvance = StringUtils.equals(autoElt.getTextContent(), "true");

			Element addElt = DOMUtil.getChild(entry, MsgId.INT_GEN_REDIRECT_ADD_PARAMS.getText());
			addParams = StringUtils.equals(addElt.getTextContent(), "true");
			RedirectionBean bean = new RedirectionBean(url, autoAdvance, addParams);

			targetTable.put(formName, bean);
		}
		logger.debug("Redirection configuration file successfully read.");

		return false;
	}

}
