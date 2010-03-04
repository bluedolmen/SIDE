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
import java.util.Formatter;
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

import org.alfresco.service.cmr.repository.NodeRef;
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
import com.bluexml.xforms.controller.binding.ClassType;
import com.bluexml.xforms.controller.binding.EnumType;
import com.bluexml.xforms.controller.binding.FormFieldType;
import com.bluexml.xforms.controller.binding.FormType;
import com.bluexml.xforms.controller.binding.GenericAttribute;
import com.bluexml.xforms.controller.binding.GenericClass;
import com.bluexml.xforms.controller.binding.Mapping;
import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import com.bluexml.xforms.controller.mapping.MappingTool;
import com.bluexml.xforms.controller.mapping.MappingToolCommon;
import com.bluexml.xforms.controller.mapping.RepoContentInfoBean;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;
import com.thoughtworks.xstream.XStream;

/**
 * The Class AlfrescoController.
 */
public class AlfrescoController {

	/** The upload base directory in the file system. */
	public static File UPLOAD_DIRECTORY = null;

	/** Depth of the random path where to distribute uploaded files. */
	public static int UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH = 3;

	/** The upload base directory in the content management system. */
	public static String UPLOAD_REPOSITORY = null;

	/** Whether file names of uploads receive a '(x)' in case the initial name already exists. */
	public static boolean UPLOAD_REPOSITORY_APPEND = true;

	/** whether info of repository uploads are formatted in the same way as content information */
	public static boolean UPLOAD_REPOSITORY_FORMAT_INFO = false;

	/** whether to check that the form being opened matches the data id */
	public static boolean CHECK_MATCH_DATA_FORM = true;

	/** The temp directory. */
	public static File TEMP_DIRECTORY = null;

	public static int MAX_RESULTS = 50;

	/** The alfresco url. */
	public static String ALFRESCO_URL = null;

	/** The alfresco xforms url. */
	public static String ALFRESCO_XFORMS_URL = null;

	/** The default user name for transactions */
	public static String USER_NAME = null;

	/** The user password */
	public static String USER_PSWD = null;
	//
	//
	private static final String BLUEXML_WORKFLOW_PREFIX = "wfbx";

	private static final XStream xstream = new XStream();

	/** The logger. */
	protected static Log logger = LogFactory.getLog(AlfrescoController.class);

	/** The doc builder. */
	private static DocumentBuilder docBuilder = null;

	/** The controller. */
	private static AlfrescoController instance = null;

	//
	// general settings that are persisted through sessions and users
	private static String formsPropertiesPath = null;
	private static String messagesPropertiesPath = null;
	private static String CssUrl = null;
	private static String redirectXmlPath = null;
	//

	/** whether calls to the webscript are intercepted */
	private static boolean standaloneMode = false;

	/** Stores redirection info keyed by form names */
	private static Map<String, RedirectionBean> targetTable = new HashMap<String, RedirectionBean>();

	/** The last initParams we saw. */
	// <-- not safe in multi-user or production environments
	Map<String, String> initParameters = null;

	/** The last form type we saw. */
	String lastFormName;

	/** The current upload path, set by getUploadPathInFileSystem */
	File uploadDir;

	static {
		// set the document builder and catalogs.
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// ** #1330
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
	 * Loads (or reloads) the mapping.xml file.
	 * 
	 * @throws Exception
	 */
	private static void loadMappingXml() throws Exception {
		URL url = AlfrescoController.class.getResource("/mapping.xml");
		File file = new File(new URI(url.toString()));
		InputStream mappingStream = new FileInputStream(file);
		instance.unmarshallMapping(mappingStream);
	}

	/**
	 * Loads the properties files and passes them to the appropriate processor for parsing.
	 * 
	 * @return true if both files have been loaded. If any of the files was found neither at the
	 *         specified location or at the default location, returns false. If true, the files may
	 *         have been loaded from default locations instead of the given paths.
	 */
	public static boolean loadProperties(String formsFilePath, String messagesFilePath) {

		//
		// forms.properties
		boolean resForms;
		if (StringUtils.trimToNull(formsFilePath) != null) { // we may be setting a new path
			try {
				File theFile = new File(formsFilePath);
				InputStream stream = new FileInputStream(theFile);
				resForms = loadPropertiesFormsFromStream(stream);
				// keep the path so that a subsequent reload does not require re-giving the path
				formsPropertiesPath = formsFilePath;
			} catch (Exception e) {
				logger.error("Configuration file 'forms.properties' not found at '" + formsFilePath
						+ "'. Will use defaults.", e);
				resForms = loadPropertiesFormsDefault();
			}
		} else if (StringUtils.trimToNull(formsPropertiesPath) != null) { // reusing previous path
			try {
				File theFile = new File(formsPropertiesPath);
				InputStream stream = new FileInputStream(theFile);
				resForms = loadPropertiesFormsFromStream(stream);
			} catch (Exception e) {
				logger.error("Configuration file 'forms.properties' not found at last location "
						+ formsPropertiesPath, e);
				resForms = loadPropertiesFormsDefault();
			}
		} else {
			resForms = loadPropertiesFormsDefault();
		}
		if (resForms == false) {
			return false;
		}

		// messages.properties
		InputStream streamMsgs;
		if (StringUtils.trimToNull(messagesFilePath) != null) {
			try {
				File theFile = new File(messagesFilePath);
				streamMsgs = new FileInputStream(theFile);
				messagesPropertiesPath = messagesFilePath;
			} catch (Exception e) {
				logger.error("Configuration file 'messages.properties' not found at '"
						+ messagesFilePath + "'. Will use defaults.", e);
				streamMsgs = loadPropertiesMessagesDefaults();
			}
		} else if (StringUtils.trimToNull(messagesPropertiesPath) != null) {
			try {
				File theFile = new File(messagesPropertiesPath);
				streamMsgs = new FileInputStream(theFile);
			} catch (Exception e) {
				logger.error("Configuration file 'messages.properties' not found at last location "
						+ messagesPropertiesPath, e);
				streamMsgs = loadPropertiesMessagesDefaults();
			}
		} else {
			streamMsgs = loadPropertiesMessagesDefaults();
		}

		if (streamMsgs == null) {
			return false;
		}
		MsgPool.setInputStream(streamMsgs);
		return true;
	}

	private static boolean loadPropertiesFormsDefault() {
		// get the default file
		URL formsURL = AlfrescoController.class.getResource("/forms.properties");
		if (formsURL == null) {
			logger
					.error("Configuration file 'forms.properties' not found in WEB-INF/classes. Null URL received from system.");
			return false;
		}
		try {
			File formsFile = new File(new URI(formsURL.toString()));
			InputStream stream = new FileInputStream(formsFile);
			return loadPropertiesFormsFromStream(stream);
		} catch (URISyntaxException e) {
			// I don't think this will ever be reached
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.error("Configuration file 'forms.properties' not found in WEB-INF/classes.", e);
		}
		return false;
	}

	private static InputStream loadPropertiesMessagesDefaults() {
		// get the default file
		URL msgURL = AlfrescoController.class.getResource("/messages.properties");
		if (msgURL == null) {
			logger
					.error("Configuration file 'messages.properties' not found in WEB-INF/classes. Null URL received from system.");
			return null;
		}
		try {
			File formsFile = new File(new URI(msgURL.toString()));
			InputStream stream = new FileInputStream(formsFile);
			return stream;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Configuration file 'messages.properties' not found in WEB-INF/classes.",
					e);
		}
		return null;
	}

	/**
	 * Loads the properties from the given stream and initializes default values.
	 * 
	 * @param stream
	 * @return
	 */
	private static boolean loadPropertiesFormsFromStream(InputStream stream) {
		Properties config = new Properties();
		try {
			config.load(stream);
			instance.initConfig(config);
			return true;
		} catch (Exception e) {
			logger.error("Failed in loading and initializing 'forms.properties'.", e);
			return false;
		}
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
	 * @return the hex code part of the id
	 */
	public static String unpatchDataId(String dataId) {
		return dataId.substring(dataId.lastIndexOf('/') + 1);
	}

	/**
	 * @return the URL to the Alfresco host
	 */
	public static String getAlfrescoUrl() {
		return ALFRESCO_URL;
	}

	/**
	 * @param alfresco_url
	 *            the URL to the Alfresco host to set
	 */
	public static void setAlfrescoUrl(String alfresco_url) {
		ALFRESCO_URL = alfresco_url;
	}

	/** The config. */
	protected Properties config = null;

	/** The mapping tool. */
	private MappingTool mappingTool;

	/**
	 * Unmarshalls the mapping from the given stream.
	 * 
	 * @param mapping
	 *            the mapping
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void unmarshallMapping(InputStream mapping) throws Exception {
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
	 * Creates or loads the instance for a default class form.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param type
	 *            the content type
	 * @param id
	 *            the id
	 * @param initParams
	 *            the init params
	 * @param idAsServlet
	 *            whether the request comes from a servlet
	 * 
	 * @return the class
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document getInstanceClass(AlfrescoTransaction transaction, String type, String id,
			Map<String, String> initParams, boolean formIsReadOnly, boolean isServletRequest)
			throws ServletException {
		Document instance = null;
		// save the initParams
		this.initParameters = initParams;
		try {
			if (id == null) {
				instance = createClassFormInstance(transaction, type, initParams,
						new Stack<AssociationType>(), formIsReadOnly, isServletRequest);
			} else {
				instance = getObjectInstance(transaction, id, new Stack<AssociationType>(),
						formIsReadOnly, isServletRequest);
			}
		} catch (ServletException se) {
			throw se; // just propagate
		} catch (Exception e) {
			throw new ServletException(e);
		}
		return instance;
	}

	/**
	 * Creates or loads the instance for a FormClass.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param type
	 *            the content type
	 * @param id
	 *            the id of the object to load. If <b>null</b>, the form is empty (with the
	 *            exception of default values [from in the model] and initial values [from URL
	 *            parameters]. If non null, the form is filled with values from the object.
	 * 
	 * @return the form
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document getInstanceForm(AlfrescoTransaction transaction, String type, String id,
			Map<String, String> initParams, boolean formIsReadOnly) throws ServletException {
		Document instance = null;
		// save some data for later
		this.initParameters = initParams;
		this.lastFormName = type;
		try {
			if (id == null) {
				instance = createForm(transaction, type, initParams, formIsReadOnly);
			} else {
				instance = loadForm(transaction, type, id, formIsReadOnly);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
		return instance;
	}

	/**
	 * Gets an XForms instance document for a FormSearch form.
	 * 
	 * @param currentPage
	 * @param formName
	 *            the form Id
	 * @param initParams
	 * @return
	 */
	public Document getInstanceSearch(Page currentPage, String formName,
			Map<String, String> initParams) {
		return mappingTool.getInstanceSearch(currentPage, formName, initParams);
	}

	/**
	 * Gets the XForms instance document for an object.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param id
	 *            the id
	 * @param stack
	 *            the stack
	 * @param isServletRequest
	 * 
	 * @return the element
	 * 
	 * @throws ServletException
	 */
	public Document getObjectInstance(AlfrescoTransaction transaction, String id,
			Stack<AssociationType> stack, boolean formIsReadOnly, boolean isServletRequest)
			throws ServletException {
		Document alfrescoNode = readObjectFromRepository(transaction, id);

		return mappingTool.transformAlfrescoToClassForms(transaction, alfrescoNode, stack,
				formIsReadOnly, isServletRequest);
	}

	/**
	 * Reads an object form Alfresco.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param id
	 *            the id
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 */
	public Document readObjectFromRepository(AlfrescoTransaction transaction, String id)
			throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("objectId", id);
		return requestDocumentFromAlfresco(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_READ);
	}

	/**
	 * Initializes configuration elements by interpreting the configuration properties file.<br/>
	 * NOTE: the properties file has already been read.
	 * 
	 * @param config
	 *            the properties from the configuration file
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

		// temp dir for file system uploads
		fsPath = config.getProperty(MsgId.KEY_TEMP_DIRECTORY.getText());
		if (StringUtils.trimToNull(fsPath) == null) {
			fsPath = "/tmp"; // TODO: check that this works on all platforms
		}
		TEMP_DIRECTORY = new File(fsPath);

		// file system archive folder for uploads, if none, default to current folder
		fsPath = config.getProperty(MsgId.KEY_UPLOAD_DIRECTORY.getText());
		if (StringUtils.trimToNull(fsPath) == null) {
			fsPath = "/tmp/uploads"; // TODO: check on all platforms
		}
		UPLOAD_DIRECTORY = new File(fsPath);

		// repo folder for uploads, if none given, use "/app:company_home/app:user_homes"
		UPLOAD_REPOSITORY = config.getProperty(MsgId.KEY_UPLOAD_REPOSITORY.getText());
		if (StringUtils.trimToNull(UPLOAD_REPOSITORY) == null) {
			UPLOAD_REPOSITORY = "/app:company_home/app:user_homes";
		}

		// max results: default number of items in lists
		String property = config.getProperty(MsgId.KEY_MAX_RESULTS.getText());
		try {
			int value = Integer.parseInt(property);
			MAX_RESULTS = value;
		} catch (NumberFormatException e) {
			logger.error("Can't parse the value '" + property + "' for key '"
					+ MsgId.KEY_MAX_RESULTS + "'. Will revert to the default value.", e);
			MAX_RESULTS = 50;
		}

		// whether to check matching of form vs data
		property = config.getProperty(MsgId.KEY_CHECK_MATCH_DATA_FORM.getText());
		CHECK_MATCH_DATA_FORM = !(StringUtils.equals(property, "false"));

		// whether to append ordering suffix to file names
		property = config.getProperty(MsgId.KEY_UPLOAD_REPOSITORY_APPEND.getText());
		UPLOAD_REPOSITORY_APPEND = !(StringUtils.equals(property, "false"));

		// whether to format info of repo uploads like the info of node content
		property = config.getProperty(MsgId.KEY_UPLOAD_REPOSITORY_FORMAT_INFO.getText());
		UPLOAD_REPOSITORY_FORMAT_INFO = StringUtils.equals(property, "true");

		// depth of the random path
		property = config.getProperty(MsgId.KEY_UPLOAD_DIR_PATH_DEPTH.getText());
		try {
			int depth = Integer.parseInt(property);
			UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH = depth;
		} catch (NumberFormatException e) {
			logger.error("Can't parse the value '" + property + "' for key '"
					+ MsgId.KEY_UPLOAD_DIR_PATH_DEPTH + "'. Will revert to the default value.", e);
			UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH = 3;
		}

		checkDirectoryExists(UPLOAD_DIRECTORY, true);
		checkDirectoryExists(TEMP_DIRECTORY, false);

		ALFRESCO_URL = config.getProperty(MsgId.KEY_ALFRESCO_URL.getText());
		ALFRESCO_XFORMS_URL = ALFRESCO_URL + "/service/xforms/";
	}

	/**
	 * Checks whether a directory (from the configuration file) exists. If not, attempts to create
	 * the paths. If that fails, creates a path under the webapp's folder.
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
					String dirPath = mappingFile.getAbsolutePath();
					// @Amenel: I am not comfortable with the assumption about the path to
					// mapping.xml: although true today, it may not be so in the future, depending
					// on OS or JVM. The easiest would've been to create the dirs as siblings of it
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
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Persists a search form: collects the search info on search forms.
	 * 
	 * @param transaction
	 * @param instance
	 * @param isServletRequest
	 * @return
	 * @throws ServletException
	 */
	public String persistSearch(String formName, Node instance, boolean shortPropertyNames)
			throws ServletException {
		return mappingTool.transformSearchForm(formName, instance, shortPropertyNames);
	}

	/**
	 * Persists a class form: transforms the XForms instance into a GenericClass, and either saves
	 * or updates a node.
	 * 
	 * @param instance
	 *            the instance
	 * @param transaction
	 *            the transaction
	 * @param isServletRequest
	 * 
	 * @return the object id as given by the transaction manager
	 * 
	 * @throws ServletException
	 */
	public String persistClass(AlfrescoTransaction transaction, Node instance,
			boolean isServletRequest) throws ServletException {
		GenericClass alfClass = mappingTool.transformClassFormsToAlfresco(transaction, instance,
				isServletRequest);
		if (alfClass.getId() == null) {
			alfClass.setId(saveObject(transaction, alfClass));
		} else {
			uploadProcessOnUpdate(transaction, alfClass);
		}
		return alfClass.getId();
	}

	/**
	 * Saves a node. Moves uploaded files to the file system/repository and enqueues the 'save'
	 * operation in the transaction, returning the id given by the transaction manager. <br/>
	 * NOTE: The values from the instance <b>must</b> have already been collected.
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the string
	 * 
	 * @throws ServletException
	 */
	private String saveObject(AlfrescoTransaction transaction, GenericClass alfClass)
			throws ServletException {
		// enqueue the operation
		transaction.queueSave(alfClass);

		// process file upload fields if any
		uploadProcessOnSave(transaction, alfClass);

		return alfClass.getId();
	}

	/**
	 * Updates a node: enqueues the update operation in the transaction and processes file uploads.
	 * 
	 * @param transaction
	 * @param alfClass
	 * @return
	 * @throws ServletException
	 */
	private String updateObject(AlfrescoTransaction transaction, GenericClass alfClass)
			throws ServletException {
		// enqueue the operation
		transaction.queueUpdate(alfClass);

		// process file upload fields if any
		uploadProcessOnUpdate(transaction, alfClass);

		return alfClass.getId();
	}

	/**
	 * Attaches the referenced content to a transaction. The attachment is not done if the file name
	 * is not a valid file URI (which happens when 1-there's no uploaded file and 2-a content has
	 * been uploaded previously).
	 * 
	 * @param transaction
	 * @param alfClass
	 * @param fileName
	 * @param filePath
	 * @param mimeType
	 * @param shouldAppendSuffix
	 * @throws ServletException
	 *             if the file doesn't exist
	 */
	private void uploadAttachContent(AlfrescoTransaction transaction, GenericClass alfClass,
			String fileName, String filePath, String mimeType, boolean shouldAppendSuffix)
			throws ServletException {
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
				transaction.queueAttachContent(alfClass.getId(), fileName, fullFileName, mimeType,
						shouldAppendSuffix, alfClass.getQualifiedName());
			}
		}
	}

	/**
	 * Returns the directory for uploading files into the file system. If none given in initParams
	 * or the directory does not exist, falls back to the value from configuration file.
	 */
	public File getParamUploadPathInFileSystem() {

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
	 * Returns a path for uploading files into the repository. If none given in initParams, falls
	 * back to the value from the configuration file.
	 */
	public String getParamUploadPathInRepository() {

		String result = null;
		if (initParameters != null) {
			result = initParameters.get(MsgId.PARAM_UPLOAD_REPOSITORY.getText());
		}
		return (result != null) ? result : UPLOAD_REPOSITORY;
	}

	public boolean getParamUploadRepoAppendSuffix() {
		String paramStr = null;
		if (initParameters != null) {
			paramStr = initParameters.get(MsgId.PARAM_UPLOAD_REPOSITORY_APPEND.getText());
		}
		if (StringUtils.trimToNull(paramStr) != null) {
			return !(StringUtils.equals(paramStr, "false"));
		}
		return UPLOAD_REPOSITORY_APPEND;
	}

	public boolean getParamUploadRepoFormatInfo() {
		String paramStr = null;
		if (initParameters != null) {
			paramStr = initParameters.get(MsgId.PARAM_UPLOAD_REPOSITORY_FORMAT.getText());
		}
		if (StringUtils.trimToNull(paramStr) != null) {
			return !(StringUtils.equals(paramStr, "false"));
		}
		return UPLOAD_REPOSITORY_FORMAT_INFO;
	}

	public boolean getParamCheckMatchDataForm() {
		String paramStr = null;
		if (initParameters != null) {
			paramStr = initParameters.get(MsgId.PARAM_CHECK_MATCH_DATA_FORM.getText());
		}
		if (StringUtils.trimToNull(paramStr) != null) {
			return !(StringUtils.equals(paramStr, "false"));
		}
		return CHECK_MATCH_DATA_FORM;
	}

	/**
	 * Processes all upload fields on initial submission. Moves filesystem uploads to the directory,
	 * stores repo uploads into the repository and attaches the (possible) node content to the
	 * transaction. If the transaction fails subsequently, all uploads should be removed.
	 * 
	 * @param transaction
	 * @param alfClass
	 * @throws ServletException
	 * @throws ServletException
	 */
	private void uploadProcessOnSave(AlfrescoTransaction transaction, GenericClass alfClass)
			throws ServletException {
		String fileName = null;
		String filePath = null;
		String mimeType = null;

		// content file(s); these will be saved to the server's filesystem
		List<RepoContentInfoBean> fileBeans = mappingTool.getUploadBeansFilesystem(transaction,
				alfClass);
		for (RepoContentInfoBean infoBean : fileBeans) {
			fileName = infoBean.getPath();
			if (fileName.startsWith("file:")) {
				String type = alfClass.getQualifiedName();
				fileName = uploadMoveFileToDir(type, fileName, transaction);
			}
			mappingTool.setFileUploadFileName(fileName, infoBean.getAttribute());
		}

		// repository content file(s); these will be directly uploaded to the repository
		List<RepoContentInfoBean> repoBeans = mappingTool.getUploadBeansRepo(transaction, alfClass);
		for (RepoContentInfoBean infoBean : repoBeans) {
			fileName = null;
			fileName = infoBean.getName();
			filePath = infoBean.getPath();
			mimeType = infoBean.getMimeType();
			GenericAttribute attribute = infoBean.getAttribute();
			if (filePath.startsWith("file:")) {
				String location = getParamUploadPathInRepository();
				fileName = uploadMoveFileToRepo(transaction, fileName, filePath, location,
						mimeType, infoBean.isShouldAppendSuffix());
				if (StringUtils.trimToNull(fileName) == null) {
					throw new ServletException(MsgPool.getMsg(MsgId.MSG_UPLOAD_FAILED));
				}
			}
			mappingTool.setFileUploadFileName(fileName, attribute);
		}

		// node content file; there's at most one instance of this.
		RepoContentInfoBean nodeInfoBean = mappingTool.getNodeContentInfo(transaction, alfClass);
		if (nodeInfoBean != null) {
			fileName = nodeInfoBean.getName();
			filePath = nodeInfoBean.getPath();
			mimeType = nodeInfoBean.getMimeType();

			uploadAttachContent(transaction, alfClass, fileName, filePath, mimeType, nodeInfoBean
					.isShouldAppendSuffix());
		}
	}

	/**
	 * Processes the upload files on update: deletes (if relevant) the old files and uploads the new
	 * ones. The deletions are not actually performed here: files/nodes to be deleted are put in
	 * queues.<br/>
	 * <p>
	 * <b>The file providing the node content is not deleted!</b> Don't know whether we should.
	 * </p>
	 * 
	 * @param alfClass
	 *            the alf class
	 * @param transaction
	 *            the transaction
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private void uploadProcessOnUpdate(AlfrescoTransaction transaction, GenericClass alfClass)
			throws ServletException {
		List<RepoContentInfoBean> previousFileContentInfo;
		List<RepoContentInfoBean> previousRepoContentInfo;
		List<RepoContentInfoBean> newFileContentInfo;
		List<RepoContentInfoBean> newRepoContentInfo;

		// read the old class
		GenericClass oldClass = null;
		try {
			oldClass = MappingToolCommon.unmarshal(readObjectFromRepository(transaction, alfClass
					.getId()));
		} catch (JAXBException e) {
			throw new ServletException(e);
		}
		previousFileContentInfo = mappingTool.getUploadBeansFilesystem(transaction, oldClass);
		previousRepoContentInfo = mappingTool.getUploadBeansRepo(transaction, oldClass);
		newFileContentInfo = mappingTool.getUploadBeansFilesystem(transaction, alfClass);
		newRepoContentInfo = mappingTool.getUploadBeansRepo(transaction, alfClass);

		//
		// enqueue files/nodes to be deleted
		String fileName;
		RepoContentInfoBean oldBean;
		for (RepoContentInfoBean newBean : newFileContentInfo) {
			fileName = newBean.getPath();
			if (fileName != null && fileName.startsWith("file:")) { // value is being replaced
				String qualifiedName = newBean.getAttribute().getQualifiedName();
				oldBean = uploadFindBean(previousFileContentInfo, qualifiedName);
				if (oldBean != null) {
					// we need to register as a temp file for deletion in case of success
					transaction.registerTempFileName(getParamUploadPathInFileSystem()
							+ File.separator + oldBean.getPath());
				}
			}
		}
		for (RepoContentInfoBean newBean : newRepoContentInfo) {
			fileName = newBean.getPath();
			if (fileName != null && fileName.startsWith("file:")) {
				String qualifiedName = newBean.getAttribute().getQualifiedName();
				oldBean = uploadFindBean(previousRepoContentInfo, qualifiedName);
				if (oldBean != null) {
					String oldNodeId = oldBean.getPath();
					transaction.registerTempNodeId(oldNodeId);
				}
			}
		}

		//
		// set the new version of the object
		uploadProcessOnSave(transaction, alfClass);
	}

	/**
	 * Finds the upload bean with the given attribute qname.
	 * 
	 * @param previousFileContentInfo
	 * @param qname
	 * @return the bean or null if not found
	 */
	private RepoContentInfoBean uploadFindBean(List<RepoContentInfoBean> list, String qname) {
		if (list == null) {
			return null;
		}
		for (RepoContentInfoBean bean : list) {
			GenericAttribute attribute = bean.getAttribute();
			if (attribute != null) {
				// if (attri
			}
		}
		return null;
	}

	/**
	 * Moves the uploaded file to a randomly chosen folder under the file system upload directory.
	 * 
	 * @param type
	 *            the type
	 * @param fileName
	 *            the file name
	 * 
	 * @return the string
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	private String uploadMoveFileToDir(String type, String fileName, AlfrescoTransaction transaction)
			throws ServletException {
		URI fileURI = URI.create(fileName);
		File sourceFile = null;
		try { // #1160
			sourceFile = new File(fileURI);
		} catch (Exception e) {
			String message = "XForms Controller: error when processing the file to upload. Check the path: "
					+ fileURI;
			logger.error(message);
			logger.error(e);
			return null;
		}

		uploadDir = getParamUploadPathInFileSystem();
		int depth = getParamUploadPathDepth();
		File targetFile = findNewName(depth, type, sourceFile.getName());

		copyFile(sourceFile, targetFile);
		String relativePath = targetFile.getAbsolutePath().replace(uploadDir.getAbsolutePath(), "");

		String outputFileName = relativePath.replace('\\', '/');
		transaction.registerUploadedFileName(outputFileName);
		transaction.registerTempFileName(sourceFile.getAbsolutePath());

		return outputFileName;
	}

	/**
	 * Process repository content. Moves the uploaded file to the given location into the content
	 * manager.
	 * 
	 * @param transaction
	 * @param fileName
	 *            the file name, name and extension
	 * @param filePath
	 *            the file system complete path to the file to be uploaded. The name and extension
	 *            may be different from parameter 'fileName'.
	 * @param location
	 *            path to a folder in the content management system
	 * @param shouldAppendSuffix
	 *            if set to true, an index [e.g. '(1)'] is appended to the filename if the original
	 *            filename is not available
	 * @return the string
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	private String uploadMoveFileToRepo(AlfrescoTransaction transaction, String fileName,
			String filePath, String location, String mimetype, boolean shouldAppendSuffix)
			throws ServletException {

		// collect parameters
		Map<String, String> parameters = new TreeMap<String, String>();
		URI fileURI = URI.create(filePath);
		String fullFileName = fileURI.getPath();

		parameters.put("filename", fileName);
		parameters.put("filepath", fullFileName);
		parameters.put("location", location);
		parameters.put("mimetype", mimetype);
		parameters.put("suffixappend", "" + shouldAppendSuffix);
		// call the webscript
		String resultId = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_UPLOAD);

		return resultId;
	}

	/**
	 * Copies the content of a source file to a target file.
	 * 
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	private void copyFile(File sourceFile, File targetFile) throws ServletException {
		FileChannel srcChannel = null;
		FileChannel dstChannel = null;
		try {
			try {
				srcChannel = new FileInputStream(sourceFile).getChannel();
				dstChannel = new FileOutputStream(targetFile).getChannel();
				dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
			} catch (IOException e) {
				throw new ServletException(e);
			} finally {
				if (srcChannel != null)
					srcChannel.close();
				if (dstChannel != null)
					dstChannel.close();
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * Finds a new name for an uploaded content. The name includes the content type, the random path
	 * and the original file name.
	 * <p/>
	 * NOTE: uploadDir must have been set.
	 * 
	 * @param type
	 *            the type
	 * @param fileName
	 *            the file name
	 * 
	 * @return the file
	 */
	private File findNewName(int depth, String type, String fileName) {
		String lFileName = fileName;
		if (lFileName.contains("\\")) {
			int lastIndexOf = StringUtils.lastIndexOf(lFileName, '\\');
			lFileName = StringUtils.substring(lFileName, lastIndexOf + 1);
		}
		String rootPath = uploadDir.getAbsolutePath() + File.separator + type;

		String randomPath = RandomStringUtils.randomNumeric(depth);
		for (int i = 0; i < depth; i++) {
			rootPath = rootPath + File.separator + randomPath.charAt(i);
		}

		File root = new File(rootPath);

		File result = new File(root, lFileName);
		if (result.exists()) {
			int dotPos = lFileName.lastIndexOf(".");

			String fileNameWihoutExtension = null;
			String fileNameExtension = null;

			if (dotPos == -1) {
				fileNameWihoutExtension = lFileName;
			} else {
				fileNameWihoutExtension = lFileName.substring(0, dotPos);
				fileNameExtension = lFileName.substring(dotPos + 1);
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
			throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("datas", MappingToolCommon.marshal(batch));

		Map<String, String> ids = new HashMap<String, String>();

		Document result = requestDocumentFromAlfresco(alfrescoTransaction, parameters,
				MsgId.INT_WEBSCRIPT_OPCODE_BATCH);
		if (result == null) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		}
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
	 * @throws ServletException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	@SuppressWarnings("all")
	public List<String> getCaptions(AlfrescoTransaction transaction, List<String> ids)
			throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("query", StringUtils.join(ids, ";"));
		Document requestDocument = requestDocumentFromAlfresco(transaction, parameters,
				MsgId.INT_WEBSCRIPT_OPCODE_LABELS);
		if (requestDocument == null) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		}

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
			throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("query", "enum;" + code);
		Document requestDocument = requestDocumentFromAlfresco(transaction, parameters,
				MsgId.INT_WEBSCRIPT_OPCODE_LABELS);
		if (requestDocument == null) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		}

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
	 * @throws ServletException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	public Node getDynamicEnum(AlfrescoTransaction transaction, String type, String filterParent,
			String filterData, String query, boolean limit) throws ServletException {
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
		Document reqDoc = requestDocumentFromAlfresco(transaction, parameters,
				MsgId.INT_WEBSCRIPT_OPCODE_ENUM);
		if (reqDoc == null) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		}
		Element docElt = reqDoc.getDocumentElement();

		Element queryElement = reqDoc.createElement("query");
		queryElement.setTextContent(StringUtils.trimToEmpty(query));
		docElt.appendChild(queryElement);
		docElt.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDID.getText()));
		docElt.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDLABEL.getText()));
		return reqDoc;
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
	 * @throws ServletException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	public Node getList(AlfrescoTransaction transaction, String type, String query,
			String maxResults, String format, String maxLength) throws ServletException {
		Map<String, String> parameters = new TreeMap<String, String>();

		String alfTypeName = null;
		/*
		 * maxSize fixe le nombre d'élements à afficher dans le widget, par défaut, MAX_RESULTS. Ce
		 * nbre sera réinitialisé par le bouton 'Tout'. Valeurs possibles: MAX_RESULTS ("50" ou
		 * celle indiquée ds forms.properties) ou fixé par la propriété 'field size' dans le
		 * modeleur. Dans ce cas, SELECTMAX conserve tjrs la valeur de field size.
		 */
		String maxSize = mappingTool.getFieldSizeForField(type, "" + getParamMaxResults(),
				lastFormName);
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
		docElement.appendChild(reqDoc.createElement(MsgId.INT_INSTANCE_SELECTEDTYPE.getText()));

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
			Element item = doc.createElement(MsgId.INT_INSTANCE_SELECT_ITEM.getText());
			Element id = doc.createElement(MsgId.INT_INSTANCE_SELECT_ID.getText());
			id.setTextContent("" + i);
			Element label = doc.createElement(MsgId.INT_INSTANCE_SELECT_LABEL.getText());
			label.setTextContent(alfrescoName + "_" + i);
			Element qname = doc.createElement(MsgId.INT_INSTANCE_SELECT_TYPE.getText());
			qname.setTextContent("type_" + alfrescoName + "_" + i);

			item.appendChild(id);
			item.appendChild(label);
			item.appendChild(qname);

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
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	private String requestString(AlfrescoTransaction transaction, Map<String, String> parameters,
			MsgId opCode) throws ServletException {
		String result = null;
		try {
			PostMethod post = requestPost(transaction, parameters, opCode);
			result = StringUtils.trim(post.getResponseBodyAsString());
		} catch (ConnectException e) {
			throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
		} catch (IOException e) {
			logger.error(e);
			throw new ServletException(MsgPool.getMsg(MsgId.MSG_DEFAULT_ERROR_MSG));
		}
		if (result != null && result.startsWith("<exception>")) {
			throw new AlfrescoWebscriptException(result);
		}
		return result;
	}

	/**
	 * Provides a bridge to the webscript, returning a Document.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param parameters
	 *            the parameters
	 * @param opCode
	 *            the opCode
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 * @throws ServletException
	 */
	private Document requestDocumentFromAlfresco(AlfrescoTransaction transaction,
			Map<String, String> parameters, MsgId opCode) throws ServletException {
		Document result = null;
		try {
			PostMethod post = requestPost(transaction, parameters, opCode);
			result = synchronizedParse(post.getResponseBodyAsStream()); // #1227
		} catch (ConnectException e) { // #1234
			if (!isDebugMode()) {
				throw new ServletException(MsgId.INT_MSG_ALFRESCO_SERVER_DOWN.getText());
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			throw new ServletException(e);
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
	 * @throws ServletException
	 * @throws IOException
	 */
	private PostMethod requestPost(AlfrescoTransaction transaction, Map<String, String> parameters,
			MsgId opCode) throws IOException {
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
			post.setParameter("username", getParamLoginUserName());
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

	public String getParamLoginUserName() {
		String result = null;
		if (initParameters != null) {
			result = initParameters.get(MsgId.PARAM_USER_NAME.getText());
		}
		return (StringUtils.trimToNull(result) == null) ? USER_NAME : result;
	}

	public String getParamLoginUserPswd() {
		String result = null;
		if (initParameters != null) {
			result = initParameters.get(MsgId.PARAM_USER_PSWD.getText());
		}
		return (StringUtils.trimToNull(result) == null) ? USER_PSWD : result;
	}

	public int getParamMaxResults() {
		int result = MAX_RESULTS;
		if (initParameters != null) {
			String resultStr = initParameters.get(MsgId.PARAM_MAX_RESULTS.getText());
			if (StringUtils.trimToNull(resultStr) != null) {
				try {
					result = Integer.parseInt(resultStr);
				} catch (NumberFormatException e) {
					logger.error("Can't parse the value '" + resultStr + "' for parameter '"
							+ MsgId.PARAM_MAX_RESULTS + "'. Will revert to the previous value.", e);
					result = MAX_RESULTS;
				}
			}
		}
		return result;
	}

	/**
	 * Provides the number of directory levels into which filesystem uploads should be randomly
	 * distributed.
	 * 
	 * @return
	 */
	public int getParamUploadPathDepth() {

		String result = null;
		if (initParameters != null) {
			result = initParameters.get(MsgId.PARAM_UPLOAD_DEPTH.getText());
		}
		if (StringUtils.trimToNull(result) == null) {
			return UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH;
		}
		try {
			return Integer.parseInt(result);
		} catch (NumberFormatException e) {
			return UPLOAD_DIRECTORY_RANDOM_PATH_DEPTH;
		}
	}

	/**
	 * Executes an HTTP method.
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
	 * Creates the instance for a class form.
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
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document createClassFormInstance(AlfrescoTransaction transaction, String type,
			Map<String, String> initParams, Stack<AssociationType> stack, boolean formIsReadOnly,
			boolean isServletRequest) throws ServletException {
		return mappingTool.createClassFormsInstance(transaction, type, initParams, stack,
				formIsReadOnly, isServletRequest);
	}

	/**
	 * Enqueues a delete operation for the current transaction.
	 * 
	 * @param nodeRef
	 *            the node ref
	 * @param transaction
	 *            the transaction
	 */
	public void delete(AlfrescoTransaction transaction, String nodeRef) {
		transaction.queueDelete(nodeRef);
	}

	/**
	 * Creates an empty form with no link with an existing object.
	 * 
	 * @param type
	 *            Name of the content type.
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the document
	 * 
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document createForm(AlfrescoTransaction transaction, String type,
			Map<String, String> initParams, boolean formIsReadOnly) throws ServletException {
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
	 * @throws ServletException
	 *             the alfresco controller exception
	 */
	public Document loadForm(AlfrescoTransaction transaction, String type, String id,
			boolean formIsReadOnly) throws ServletException {
		return mappingTool.createFormInstance(transaction, type, id, formIsReadOnly);
	}

	/**
	 * Persists a FormClass: transforms the XForms instance into a GenericClass, and either saves or
	 * updates a node.
	 * 
	 * @param type
	 *            the type
	 * @param instance
	 *            the instance
	 * @param transaction
	 *            the transaction
	 * 
	 * @return the temporary id assigned to the class
	 * 
	 * @throws ServletException
	 */
	public String persistForm(AlfrescoTransaction transaction, String type, Node instance)
			throws ServletException {
		GenericClass alfClass = this.transformsToAlfresco(transaction, type, instance);
		if (alfClass.getId() == null) {
			alfClass.setId(saveObject(transaction, alfClass));
		} else {
			updateObject(transaction, alfClass);
		}
		return alfClass.getId();
	}

	/**
	 * Returns the data on the form in a specific JSON format. This is to be used with forms other
	 * than FormSearch'es when called in search mode. If the form is a FormSearch, use
	 * {@link #persistSearch(AlfrescoTransaction, Node, boolean)}
	 * 
	 * @param transaction
	 * @param formName
	 * @param instance
	 * @return a JSON string
	 * @throws ServletException
	 */
	public String persistFormJSON(AlfrescoTransaction transaction, String formName, Node instance,
			boolean shortPropertyNames) throws ServletException {
		return mappingTool.transformsToJSON(transaction, formName, instance, shortPropertyNames);
	}

	/**
	 * Builds a GenericClass object from the instance of a workflow and processes the possible
	 * upload fields.
	 * 
	 * @param transaction
	 * @param taskTypeName
	 *            the id of the workflow form
	 * @param taskElt
	 *            the root element containing the workflow properties
	 * @return
	 * @throws ServletException
	 */
	public GenericClass persistWorkflow(AlfrescoTransaction transaction, String taskTypeName,
			Node taskElt) throws ServletException {
		GenericClass transformed = transformsToAlfresco(transaction, taskTypeName, taskElt);

		// #1209: we must support FileFields on workflow forms so we also simulate a queuing
		saveObject(transaction, transformed);
		return transformed;
	}

	/**
	 * Transforms the XForms instance into a GenericClass. Extracted from persistForm to provide an
	 * access to workflow actions.
	 * 
	 * @param transaction
	 * @param formName
	 * @param instance
	 * @return
	 * @throws ServletException
	 */
	private GenericClass transformsToAlfresco(AlfrescoTransaction transaction, String formName,
			Node instance) throws ServletException {
		return mappingTool.transformsToAlfresco(transaction, formName, instance);
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
	 * Checks whether a datatype has sub types.
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
	 * Checks if is an enumeration is dynamic.
	 * 
	 * @param type
	 *            the type
	 * 
	 * @return true, if is dynamic enum
	 */
	public boolean isDynamicEnum(String type) {
		EnumType enumType = mappingTool.getEnumType(type);
		if (enumType != null) {
			return mappingTool.isDynamic(enumType);
		}
		return false; // happens for search operators enums; they don't get into the mapping file
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
	 * Gets the label under which an association is displayed on a specific form. The label is
	 * indicated for the model choice field in the modeler.
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
	 * reset (i.e. emptied) so that subsequent editions can happen correctly on the same form.
	 * 
	 * @param node
	 * @return the id, or null (this latter case should not happen if the Edit button's action of
	 *         setting the <edit id> in the instance is done correctly).
	 */
	public EditNodeBean getEditNodeAndReset(Node node) {
		Element rootElt = ((Document) node).getDocumentElement();
		// find the edit id element
		Element editIdElt = DOMUtil.getElementInDescentByNameNonNull(rootElt,
				MsgId.INT_INSTANCE_SIDEEDIT.getText());
		if (editIdElt != null) {
			String id = editIdElt.getTextContent();
			editIdElt.setTextContent(null); // <- reset the id. MANDATORY.

			// get the data type // #1510
			String dataType = null;
			try {
				Element parent = (Element) editIdElt.getParentNode();
				Element typeElt = DOMUtil.getChild(parent, MsgId.INT_INSTANCE_SIDETYPE.getText());
				if (typeElt != null) {
					dataType = typeElt.getTextContent();
				}
			} catch (Exception e) {
				// nothing to do
			}
			logger.debug("Getting edit id, found: '" + id + "' with data type: '" + dataType + "'");
			return new EditNodeBean(id, dataType);
		}
		logger.error("No id found for node edition.");
		return null;
	}

	//
	// additions for workflows
	//
	/**
	 * Starts a workflow.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param workflowDefinitionId
	 * @param attributes
	 *            Qualified Alfresco Attributes
	 * @return The path for the newly created Workflow
	 */
	public WorkflowPath workflowStart(AlfrescoTransaction transaction, String workflowDefinitionId,
			Map<QName, Serializable> attributes) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("wfName", workflowDefinitionId);
		parameters.put("attributes", attributes);
		return (WorkflowPath) workflowRequestCall(transaction, "wfStart", parameters);
	}

	/**
	 * Ends a task by following the given transition.
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
	 */
	public WorkflowTask workflowEndTask(AlfrescoTransaction transaction, String taskId,
			String transitionId) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(taskId);
		paramList.add(transitionId);
		return (WorkflowTask) workflowRequestWrapper(transaction, "endTask", paramList);
	}

	/**
	 * Updates a task with the given properties.
	 * 
	 * @param transaction
	 * @param taskId
	 * @param properties
	 * @return
	 */
	public WorkflowTask workflowUpdateTask(AlfrescoTransaction transaction, String taskId,
			Map<QName, Serializable> properties) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("taskId", taskId);
		parameters.put("attributes", properties);
		parameters.put("add", null);
		parameters.put("remove", null);
		return (WorkflowTask) workflowRequestCall(transaction, "wfUpdate", parameters);
	}

	/**
	 * Cancels a workflow.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param workflowId
	 *            Id of the workflow to cancel
	 * @return The updated instance
	 */
	public WorkflowInstance workflowCancel(AlfrescoTransaction transaction, String workflowId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("workflowId", workflowId);
		return (WorkflowInstance) workflowRequestCall(transaction, "wfCancel", parameters);
	}

	/**
	 * Collects all non empty properties available on an instanceId.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param instanceId
	 *            the workflow instance Id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<QName, Serializable> workflowCollectInstanceProperties(
			AlfrescoTransaction transaction, String instanceId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("workflowId", instanceId);

		return (Map<QName, Serializable>) workflowRequestCall(transaction,
				"wfCollectInstanceProperties", parameters);
	}

	/**
	 * Creates a workflow package under the default package location and adds an existing content to
	 * that workflow package so that a call to WorkflowService.getWorkflowsForContent for that
	 * content links to the workflow for which the created package is the package.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @return the new package
	 * @throws ServletException
	 */
	public NodeRef workflowCreatePackage(AlfrescoTransaction transaction, String nodeToAdd,
			String userName) throws ServletException {

		// TODO: merge the two call: the createPackage should be done by the webscript (for
		// instance, if the 'package' parameter is null)
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
	 * Calls a method of org.alfresco.service.cmr.workflowWorkflowService, putting the parameters
	 * from the list into a map, and creating a transaction if none is given.<br/>
	 * 
	 * @param transaction
	 *            a transaction object. May be null.
	 * @param methodName
	 *            the name of the method to call, case-sensitive.
	 * @param methodParameters
	 *            a list containing the method parameters in the order of the function's signature.
	 *            CANNOT BE NULL.
	 * @return an object of the return type of the function that was called.
	 */
	public Object workflowRequestWrapper(AlfrescoTransaction transaction, String methodName,
			List<Object> methodParameters) {
		Map<String, Object> parameterMaps = new HashMap<String, Object>();
		int i = 0;
		for (Object parameter : methodParameters) {
			parameterMaps.put("arg" + i, parameter);
			i++;
		}

		AlfrescoTransaction realTransaction = transaction;
		if (realTransaction == null) {
			realTransaction = new AlfrescoTransaction(this);
		}
		return workflowRequestCall(realTransaction, methodName, parameterMaps);
	}

	/**
	 * Executes a workflow request on Alfresco by calling the webscript.
	 * 
	 * @param transaction
	 *            Alfresco transaction (for login)
	 * @param methodName
	 *            Method to call (mapped to a method in WorkflowService). <b>Case-sensitive</b>.
	 * @param methodParameters
	 *            Parameters needed by the method (same types as declared in WorkflowService)
	 * @return The deserialized Object or null if exception
	 */
	private Object workflowRequestCall(AlfrescoTransaction transaction, String methodName,
			Map<String, Object> methodParameters) {
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
		} catch (ServletException e) {
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
	 * @param propertyName
	 * @return the value of the property for the node. Returns <b>null</b> if an exception occurred
	 *         or if the value is <b>null</b>.
	 */
	public String systemGetNodeProperty(NodeRef node, QName propertyName) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "NodeService");
		parameters.put("methodName", "getProperty");
		Vector<Object> paramList = new Vector<Object>();
		// add parameters to the method in paramList
		paramList.add(node);
		paramList.add(propertyName);
		parameters.put("methodParams", xstream.toXML(paramList));
		String result;
		try {
			result = (String) xstream.fromXML(requestString(new AlfrescoTransaction(this),
					parameters, MsgId.INT_WEBSCRIPT_OPCODE_SERVICE));
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Gets the type of a node.
	 * 
	 * @param dataId
	 *            the node's id, with or without the protocol and store.
	 * @return the QName that corresponds to the content type. Returns <b>null</b> if an exception
	 *         occurred or if the value is <b>null</b>, which is a hint that either the webscript is
	 *         not available or the object does not exist.
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
	 * @return the noderef for the user name. Returns <b>null</b> if an exception occurred or if the
	 *         value is <b>null</b>.
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
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Returns the node ref for a user group identified by name.
	 * 
	 * @param groupName
	 *            the group name as can be seen in Alfresco's web client. The system prefix for
	 *            groups will be prepended before calling the web script.
	 * @return the noderef for the user group. Returns <b>null</b> if an exception occurred or if
	 *         the group does not exist.
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
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Returns all known groups, including standard Alfresco groups.
	 * 
	 * @param asGroups
	 *            if true, specifies that only groups are returned. If false, only users.
	 * @return the set of registered user groups. Returns <b>null</b> if an exception occurred or if
	 *         the value is <b>null</b> by itself.
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
		} catch (ServletException e) {
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
	 * @return the set of groups the user is part of. Returns <b>null</b> if an exception occurred
	 *         or if the value is <b>null</b> by itself.
	 */
	@SuppressWarnings("unchecked")
	public Set<String> systemGetContainingGroups(String userName) {

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
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Retrieves all in-progress tasks found for the instance. Since several paths may be associated
	 * with the instance, each active path may provide tasks.
	 * 
	 * @param path
	 * @return the list (possibly empty if exception) of in-progress tasks.
	 * @throws ServletException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkflowTask> workflowGetCurrentTasks(String instanceId) {
		List<WorkflowTask> result = new Vector<WorkflowTask>();
		// get the paths from the instance id, which should exist
		List<WorkflowPath> paths = null;
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(instanceId);
		paths = (List<WorkflowPath>) workflowRequestWrapper(null, "getWorkflowPaths", paramList);
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
				tasks = (List<WorkflowTask>) workflowRequestWrapper(null,
						"getTasksForWorkflowPath", params);
				if (tasks == null) {
					return result;
				}
				// add the tasks to complete
				for (WorkflowTask task : tasks) {
					if (task.state == WorkflowTaskState.IN_PROGRESS) {
						result.add(task);
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
	 */
	public WorkflowTaskDefinition workflowGetTaskDefinition(String processDefId, String task) {

		List<WorkflowTaskDefinition> taskDefs = workflowGetTaskDefinitions(processDefId);
		for (WorkflowTaskDefinition taskDef : taskDefs) {
			if (StringUtils.equals(taskDef.id, task)) {
				return taskDef;
			}
		}
		return null;
	}

	//
	// BRIDGE FUNCTIONS for accessing the WorkflowService API
	//
	@SuppressWarnings("unchecked")
	public List<WorkflowTaskDefinition> workflowGetTaskDefinitions(String processDefId) {
		String methodName = "getTaskDefinitions";
		List<Object> params = new ArrayList<Object>();
		params.add(processDefId);
		List<WorkflowTaskDefinition> workflowRequest = (List<WorkflowTaskDefinition>) workflowRequestWrapper(
				null, methodName, params);
		return workflowRequest;
	}

	@SuppressWarnings("unchecked")
	public List<WorkflowInstance> workflowGetWorkflowsForContent(String refStr, boolean onlyActive) {
		NodeRef nodeRef = new NodeRef(refStr);
		String methodName = "getWorkflowsForContent";
		List<Object> params = new ArrayList<Object>();
		params.add(nodeRef);
		params.add(onlyActive);
		List<WorkflowInstance> workflowRequest = (List<WorkflowInstance>) workflowRequestWrapper(
				null, methodName, params);
		return workflowRequest;
	}

	public WorkflowDefinition workflowGetWorkflowById(String defId) {
		String methodName = "getDefinitionById";
		List<Object> params = new ArrayList<Object>();
		params.add(defId);
		WorkflowDefinition workflowRequest = (WorkflowDefinition) workflowRequestWrapper(null,
				methodName, params);
		return workflowRequest;
	}

	/**
	 * Returns an instance for workflow forms so that they can be displayed.
	 * 
	 * @see com.bluexml.xforms.actions.WorkflowFormGetAction
	 * @param formName
	 * @return
	 * @throws ServletException
	 */
	public Document getWorkflowFormInstance(String formName) throws ServletException {
		Document instance = docBuilder.newDocument();

		WorkflowTaskType taskType = mappingTool.getWorkflowTaskType(formName, false);
		Map<String, GenericClass> alfrescoNodes = new HashMap<String, GenericClass>();

		Element taskElt = instance.createElement(formName);

		mappingTool.collectTaskProperties(instance, taskElt, taskType, alfrescoNodes, false);

		Element rootElement = instance.createElement(MsgId.INT_INSTANCE_WKFLW_NODESET.getText());
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
	 * @param formType
	 * @param fieldName
	 * @return
	 */
	private FormFieldType getFieldFromCanisterType(CanisterType formType, String fieldName) {
		return mappingTool.getFormFieldTypeFromCanister(formType, fieldName);
	}

	/**
	 * Creates a folder. Currently unused but left available as an access point to a webscript
	 * operation.
	 * 
	 * @param folderPath
	 * @param userName
	 * @return
	 * @throws ServletException
	 */
	public String createPathInRepository(String folderPath, String userName)
			throws ServletException {

		// collect parameters
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("path", folderPath);
		// call the webscript
		AlfrescoTransaction transaction = new AlfrescoTransaction(this);
		transaction.setLogin(userName);
		String resultId = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_MKDIR);

		return resultId;
	}

	/**
	 * Gets the task name from the form name.
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
		return MsgId.INT_NAMESPACE_BLUEXML_WORKFLOW + "/" + processName + "/1.0";
	}

	/**
	 * Gets a list of in-progress workflow tasks for a user.
	 * 
	 * @param userName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WorkflowTask> workflowGetPooledTasks(String userName) {
		String methodName = "getPooledTasks";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(userName);
		List<WorkflowTask> workflowRequest = (List<WorkflowTask>) workflowRequestWrapper(null,
				methodName, methodParameters);
		return workflowRequest;
	}

	/**
	 * Gets the WorkflowTask object for the given task id.
	 * 
	 * @param taskId
	 * @return the requested task object, or <b>null</b> if not found
	 */
	public WorkflowTask workflowGetTaskById(String taskId) {
		String methodName = "getTaskById";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(taskId);
		WorkflowTask workflowRequest = (WorkflowTask) workflowRequestWrapper(null, methodName,
				methodParameters);
		return workflowRequest;
	}

	/**
	 * Gets the contents of the workflow package for the given task id.
	 * 
	 * @param taskId
	 * @return the list of items associated with the task's workflow package
	 */
	@SuppressWarnings("unchecked")
	public List<NodeRef> workflowGetPackageContents(String taskId) {
		String methodName = "getPackageContents";
		List<Object> methodParameters = new ArrayList<Object>();
		methodParameters.add(taskId);
		List<NodeRef> workflowRequest = (List<NodeRef>) workflowRequestWrapper(null, methodName,
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
	 *            the workflow instance Id (previously provided by Alfresco)
	 * @return false if a lethal exception occurred. True if the normal end of the function was
	 *         reached, which does not imply anything about the setting of initial values.
	 */
	public boolean patchWorkflowInstance(AlfrescoTransaction transaction, String wkFormName,
			Document doc, String instanceId) {
		logger.debug("Patching workflow instance with Id:'" + instanceId + "', form name: "
				+ wkFormName);
		QName qname;
		String namespaceURI = null; // to be set once
		Map<QName, Serializable> properties = null; // to be set once

		if (StringUtils.trimToNull(instanceId) == null) {
			logger.debug("  No patching performed: the instanceId is null");
			return true;
		}
		if (instanceId.equals("null")) {
			logger.debug("  No patching performed, invalid instanceId with string 'null'");
			return true;
		}
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
					properties = workflowCollectInstanceProperties(transaction, instanceId);
					if (properties == null) {
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
					// we'll get this when displaying workflow forms while the webscript is down
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
	 * Sets the controller in or out of the stand alone mode.
	 * 
	 * @param standaloneMode
	 *            the standalone status to set: true=ON and false=OFF.
	 */
	public static void setStandaloneMode(boolean standaloneMode) {
		AlfrescoController.standaloneMode = standaloneMode;
	}

	/**
	 * Tells whether the controller is in stand alone mode. In which case, the Alfresco server is
	 * considered unavailable and all reads and writes are either simulated or disabled.
	 * 
	 * @return the standaloneMode
	 */
	public static boolean isStandaloneMode() {
		return standaloneMode;
	}

	/**
	 * Sets a CSS file URL that will be added in the head section of all forms at serving time. The
	 * URL applies from the moment it is set. In other words, setting it for one form sets it for
	 * all subsequent forms until the URL is changed.
	 * 
	 * @param cssUrl
	 *            the cssUrl to set
	 */
	public static void setCssUrl(String cssUrl) {
		CssUrl = cssUrl;
	}

	/**
	 * Gets the URL for the CSS file that was previously set.
	 * 
	 * @return the cssUrl
	 */
	public static String getCssUrl() {
		return CssUrl;
	}

	/**
	 * Gets the suffix appended to read only forms. <br/>
	 * In the BxDS version, that suffix is configured at generation time and stored in the mapping
	 * file. Here (in SIDE) we don't have (yet) any means to do the same.
	 * 
	 * @return
	 */
	public String getReadOnlyFormsSuffix() {
		return mappingTool.getReadOnlyFormsSuffix();
	}

	public boolean isDebugMode() {
		return mappingTool.getDebugModeStatus();
	}

	/**
	 * Tells whether the task is a start task.
	 * 
	 * @param taskType
	 * @return
	 */
	public boolean isStartTask(WorkflowTaskType taskType) {
		return mappingTool.isStartTask(taskType);
	}

	/**
	 * Gets the actual data type for a form. Added because read only forms are distinguished from
	 * R/W forms using a suffix. Hence the form name in itself cannot be used to designate the
	 * datatype (or "form id" to be more precise).
	 * 
	 * @param formName
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
	 * Tests authentication credentials with the current Alfresco instance (defined in the
	 * properties file or via the appropriate URL parameter).
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
		} catch (ServletException e) {
			e.printStackTrace();
			return false;
		}
		return StringUtils.equalsIgnoreCase(result, "success");
	}

	/**
	 * Gets the string that the webscript's help operation provides.
	 * 
	 * @return
	 */
	public String getWebscriptHelp() {
		String result;
		AlfrescoTransaction transaction = new AlfrescoTransaction(this);
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			result = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_HELP);
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Dynamically reloads all static (class-related) info (such as the mapping.xml file).
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
	 * Gets the local part of the node's type as returned by Alfresco.
	 * 
	 * @param dataId
	 * @return
	 */
	public String getNodeType(String dataId) {
		QName qname = systemGetNodeType(dataId);
		if (qname == null) {
			return null;
		}
		return qname.getLocalName();
	}

	/**
	 * Retrieves some information about the content of an existing node and provides a user-readable
	 * version of the collected info if the node has a defined content. <br/>
	 * For now, gets:
	 * <ul>
	 * <li>node name as displayed via a web client</li>
	 * <li>the localized content size, as number of bytes and readable file size. e.g. "7 614 525
	 * bytes (7.26 MB)"</li>
	 * </ul>
	 * 
	 * @param nodeId
	 *            the full node Id (including protocol and workspace)
	 * @return the info about a content, or null if an exception occured or empty if no content
	 */
	public String getWebscriptNodeContentInfo(String nodeId) {
		if (StringUtils.trimToNull(nodeId) == null) {
			return "";
		}
		String request;
		AlfrescoTransaction transaction = new AlfrescoTransaction(this);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("nodeId", nodeId);

		try {
			request = requestString(transaction, parameters, MsgId.INT_WEBSCRIPT_OPCODE_NODE_INFO);
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		}

		if (StringUtils.trimToNull(request) == null) {
			return "";
		}

		String result;
		String[] infos = request.split(","); // <- see the webscript for the actual format
		String nodeName = infos[0]; // the node name
		long size = Long.parseLong(infos[1]); // the content's size
		if (size > 0) {
			String sizeUnit = "";
			Formatter formatter = new Formatter();
			String sizeBytes = formatter.format("%,d", size).toString();
			char multiplier = getFileSizeUnit(size);
			if (multiplier != 'b') {
				sizeUnit = " (" + getFileSizeShortReadable(size, multiplier) + ")";
			} else {
				//
			}
			result = MsgPool.getMsg(MsgId.MSG_UPLOAD_CONTENT_REPO_FORMAT, nodeName, sizeBytes,
					sizeUnit, nodeId);
		} else {
			result = MsgPool.getMsg(MsgId.MSG_UPLOAD_CONTENT_NO_CONTENT);
		}
		// result += " " + result;
		return result;
	}

	/**
	 * Gets the "appropriate" unit for the given file size.
	 * 
	 * @param size
	 * @return 'b' or 'k' or 'm' or 'g'
	 */
	private static char getFileSizeUnit(long size) {
		char unit = 'b'; // bytes
		if (size > 1024 * 1024 * 1024) {
			unit = 'g';
		} else if (size > 1024 * 1024) {
			unit = 'm';
		} else if (size > 1024) {
			unit = 'k';
		}
		return unit;
	}

	/**
	 * Returns a "short" version of the given file size using the appropriate multiplier, and
	 * formatted with a fixed number (see the format) of decimal places. <br/>
	 * Examples: 1024 bytes => 1.00 KB, 7 614 525 bytes => 7.26 MB
	 * 
	 * @param transferred
	 * @param unit
	 * @return
	 */
	private static String getFileSizeShortReadable(long transferred, char unit) {
		float res = transferred;
		String unitStr = "B";
		switch (unit) {
			case 'k':
			case 'K':
				res = (float) transferred / (float) (1024);
				unitStr = "KB";
				break;
			case 'm':
			case 'M':
				res = (float) transferred / (float) (1024 * 1024);
				unitStr = "MB";
				break;
			case 'g':
			case 'G':
				res = (float) transferred / (float) (1024 * 1024 * 1024);
				unitStr = "GB";
				break;
		}
		Formatter formatter = new Formatter();
		return formatter.format("%,.2f", res) + " " + unitStr;
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
	 * Loads the redirection table for workflow forms. Other types of forms are not covered.
	 * 
	 * @return true if the file was successfully loaded, false otherwise.
	 */
	public static boolean loadRedirectionTable(String filePath) {
		if (logger.isDebugEnabled()) {
			logger.debug("Reading redirection configuration file.");
		}
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
			URL url = AlfrescoController.class.getResource("/redirect.xml");
			if (url == null) {
				logger.error("Redirection file not found. Redirection will not be available.");
				return false;
			}
			File file;
			try {
				file = new File(new URI(url.toString()));
				stream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
				return false;
			}

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
		if (logger.isDebugEnabled()) {
			logger.debug("Redirection configuration file successfully read.");
		}

		return true;
	}

	/**
	 * Finds the first FormClass that supports the given data type.
	 * 
	 * @param dataType
	 * @return
	 */
	public FormType getCustomFormForDataType(String dataType) {
		return mappingTool.getFormTypeWithDataType(dataType);
	}

	/**
	 * Finds the first default form that supports the given data type.
	 * 
	 * @param dataType
	 * @return
	 */
	public ClassType getDefaultFormForDataType(String dataType) {
		return mappingTool.getClassTypeWithDataType(dataType);
	}

}
