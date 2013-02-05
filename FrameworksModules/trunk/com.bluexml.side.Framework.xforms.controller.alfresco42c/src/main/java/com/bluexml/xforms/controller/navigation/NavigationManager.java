package com.bluexml.xforms.controller.navigation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.chiba.agent.web.WebFactory;
import org.chiba.xml.xforms.core.Submission;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.actions.AbstractAction;
import com.bluexml.xforms.actions.CancelAction;
import com.bluexml.xforms.actions.CreateClassAction;
import com.bluexml.xforms.actions.CreateFormAction;
import com.bluexml.xforms.actions.DeleteAction;
import com.bluexml.xforms.actions.EditClassAction;
import com.bluexml.xforms.actions.EditFormAction;
import com.bluexml.xforms.actions.EnumAction;
import com.bluexml.xforms.actions.ExecuteAction;
import com.bluexml.xforms.actions.GetAction;
import com.bluexml.xforms.actions.ListAction;
import com.bluexml.xforms.actions.SetTypeAction;
import com.bluexml.xforms.actions.SubmitAction;
import com.bluexml.xforms.actions.WorkflowSaveAction;
import com.bluexml.xforms.actions.WorkflowTransitionAction;
import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.beans.PageInfoBean;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class NavigationManager.
 */
public class NavigationManager {

	/** The Constant ACTIONS. */
	private static final Class<?>[] ACTIONS = { CancelAction.class, CreateClassAction.class, CreateFormAction.class, EditClassAction.class, EditFormAction.class, EnumAction.class, ExecuteAction.class, GetAction.class, ListAction.class, SubmitAction.class, DeleteAction.class,
			SetTypeAction.class,
			/*
			 * WorkflowFormGetAction.class, WorkflowGetAction.class,
			 * WorkflowInstanceListAction.class,
			 * WorkflowProcessListAction.class,
			 * WorkflowStartAction.class,
			 */
			WorkflowTransitionAction.class,
			WorkflowSaveAction.class
			};

	/** The Constant PAGE_ID. */
	public static final String PAGE_ID = "pageId";

	/** The Constant TEMPLATE_ID. */
	public static final String TEMPLATE_ID = "template";

	/** The string by which we accept an upload path. */
	public static final String UPLOADPATH_ID = "uploadPath";

	/** The Constant instance. */
	protected static final NavigationManager instance = new NavigationManager();

	/** The actions. */
	protected Map<String, Class<? extends AbstractAction>> actions;

	/** The logger. */
	protected static Log logger = LogFactory.getLog(NavigationManager.class);

	// /** The servlet url. */
	// protected String servletURL = null;

	// /** The servletURLs identified by sessionId. */
	// protected Map<String, String> servletURLs;
	//
	/** The document transformer. */
	protected Transformer documentTransformer;

	/** The document builder. */
	protected DocumentBuilder documentBuilder;

	private AlfrescoController controller;

	/**
	 * Gets the single instance of NavigationManager.
	 * 
	 * @return single instance of NavigationManager
	 */
	public static NavigationManager getInstance() {
		return instance;
	}

	/**
	 * Instantiates a new navigation manager.
	 */
	private NavigationManager() {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			documentTransformer = TransformerFactory.newInstance().newTransformer();
			documentTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		controller = getController();

		List<Class<?>> classes = new ArrayList<Class<?>>(ACTIONS.length);
		classes.addAll(Arrays.asList(ACTIONS));

		// InputStream actionsXmlStream =
		// NavigationManager.class.getResourceAsStream("/actions.xml");
		// if (actionsXmlStream != null) {
		// try {
		// Document actionsDocument = documentBuilder.parse(actionsXmlStream);
		// addActions(actionsDocument, classes);
		// } catch (Exception e) {
		// logger.error(e);
		// }
		// }

		actions = new TreeMap<String, Class<? extends AbstractAction>>();

		for (Class<?> classAction : classes) {
			try {
				AbstractAction abstractAction = (AbstractAction) classAction.newInstance();
				actions.put(abstractAction.getActionName(), abstractAction.getClass());
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("Error while registering actions", e);
				}
			}
		}
	}

	/**
	 * Adds the actions.
	 * 
	 * @param actionsDocument
	 *            the actions document
	 * @param classes
	 *            the classes
	 * @deprecated
	 */
	@Deprecated
	@SuppressWarnings("unused")
	private void addActions(Document actionsDocument, List<Class<?>> classes) {
		NodeList actionNodes = actionsDocument.getDocumentElement().getChildNodes();
		for (int i = 0; i < actionNodes.getLength(); i++) {
			Node item = actionNodes.item(i);
			if (item instanceof Element) {
				Element action = (Element) item;
				if (action.getTagName().equals("action")) {

					String className = null;

					NodeList childNodes = action.getChildNodes();
					for (int j = 0; j < childNodes.getLength(); j++) {
						Node childNode = childNodes.item(j);
						if (childNode instanceof Element) {
							Element subElement = (Element) childNode;
							if (subElement.getTagName().equals("class")) {
								className = StringUtils.trimToNull(subElement.getTextContent());
							}

						}
					}
					if (className != null) {
						try {
							Class<?> actionClassName = Class.forName(className);
							classes.add(actionClassName);
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error(e);
							}
						}
					}

				}
			}
		}

	}

	/**
	 * Log.
	 * 
	 * @param messages
	 *            the messages
	 */
	private void log(String... messages) {
		if (logger.isDebugEnabled()) {
			for (String message : messages) {
				logger.debug(message);
			}
		}
	}

	/**
	 * Log xml.
	 * 
	 * @param node
	 *            the node
	 * @param messages
	 *            the messages
	 */
	private void logXML(Node node, String... messages) {
		if (logger.isDebugEnabled()) {
			log(messages);
			if (node != null) {
				Source xmlSource = new DOMSource(node);
				StringWriter sw = new StringWriter();
				Result outputTarget = new StreamResult(sw);
				try {
					documentTransformer.transform(xmlSource, outputTarget);
				} catch (TransformerException e) {
					logger.error(e);
				}
				logger.debug(sw.toString());
			}
		}
	}

	/**
	 * Send XForms to Chiba filter.<br>
	 * Inserts session id into form.<br>
	 * No data manipulation has to be made here.
	 * 
	 * @param req
	 *            the req
	 * @param resp
	 *            the resp
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void sendXForms(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		String sessionId = session.getId();

		controller = getController();

		String testStr = StringUtils.trimToNull(req.getParameter(MsgId.PARAM_SERVE_TEST_PAGE.getText()));
		boolean serveTestPage = StringUtils.equals(testStr, "true");
		String pageId = StringUtils.trimToNull(req.getParameter(PAGE_ID));

		// called from a direct link? set our info (pageId, stackId)
		if (pageId == null) {
			// check for a possible initialisation call
			boolean isInit = StringUtils.equals(req.getParameter(MsgId.PARAM_INIT_CALL.getText()), "true");
			if (isInit) {
				ServletOutputStream stream = resp.getOutputStream();
				String result = (loadConfiguration(req, true) == -1) ? "success" : "failure";
				stream.write(result.getBytes());
				stream.close();
				return;
			}
			pageId = NavigationSessionListener.getPageId(sessionId);
			NavigationPath navigationPath = NavigationSessionListener.getNavigationPath(sessionId, pageId);

			// check whether reloading of the mapping.xml file was asked for
			if (StringUtils.equals(req.getParameter(MsgId.PARAM_RELOAD_MAPPING_FILE.getText()), "true")) {
				controller.performDynamicReload();
			}
			// check whether reloading of properties/configuration files was asked for
			if (StringUtils.equals(req.getParameter(MsgId.PARAM_RELOAD_PROPERTIES.getText()), "true")) {
				int resLoad = loadConfiguration(req, false);
				if (logger.isDebugEnabled()) {
					if (resLoad == -1) {
						logger.debug("Reloaded properties: OK.");
					} else {
						String reason = "";
						switch (resLoad) {
						case 0:
							reason = "an exception occured";
							break;
						case 1:
							reason = "properties files";
							break;
						case 2:
							reason = "redirection file";
							break;
						}
						logger.debug("Failed in loading the configuration. Reason: " + reason);
					}
				}
			}
			// set specific CSS if given
			this.setCssUrl(req);
			// initial status message. CAUTION: may be overridden later in case of errors.
			String statusMsg = StringUtils.trimToNull(req.getParameter(MsgId.PARAM_STATUS_MSG.getText()));
			if (statusMsg != null) {
				navigationPath.setStatusMsg(statusMsg);
			}
			// deal with standalone mode
			if (StringUtils.equals(req.getParameter(MsgId.PARAM_STANDALONE.getText()), "true")) {
				controller.setStandaloneMode(true);
			}
			if (StringUtils.equals(req.getParameter(MsgId.PARAM_STANDALONE.getText()), "false")) {
				controller.setStandaloneMode(false);
			}

			PageInfoBean pageInfo = collectPageInfo(req);
			// save session and URL we were called with (useful when a host is multi-domain'ed)
			String curServletURL = this.registerSessionURL(req, sessionId);
			// remember where we are
			navigationPath.setCurrentPage(pageInfo);
			String location = curServletURL + "?pageId=" + pageId + "&stackId=" + navigationPath.getSize();
			// propagate queryString
			location += "&" + req.getQueryString();
			if (serveTestPage == false) {
				// redirect the web client, providing ids we need
				resp.sendRedirect(resp.encodeRedirectURL(location));
				return;
			}
		}
		// the ids are available
		NavigationPath navigationPath = NavigationSessionListener.getNavigationPath(sessionId, pageId);
		if (navigationPath.isEmpty()) {
			// the servlet is called directly with ids we did not register
			throw new ServletException(MsgPool.getMsg(MsgId.MSG_SESSION_TIMED_OUT));
		}
		Page currentPage = navigationPath.peekCurrentPage();
		// set the warning if page was called with an object it can't display
		if (currentPage.isWrongCallType()) {
			navigationPath.setStatusMsg("WARNING: the data Id provided is not appropriate for this form.");
		}

		// get the form template as a string
		String statusDisplayedMsg = navigationPath.getStatusDisplayedMsg();
		Document doc = loadXFormsDocument(req, sessionId, pageId, statusDisplayedMsg, currentPage);

		req.setAttribute(WebFactory.XFORMS_NODE, doc);
		resp.getOutputStream().close();
	}

	/**
	 * @param req
	 * @param sessionId
	 * @param pageId
	 * @param statusDisplayedMsg
	 * @param currentPage
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @throws DOMException
	 */
	private Document loadXFormsDocument(HttpServletRequest req, String sessionId, String pageId, String statusDisplayedMsg, Page currentPage) throws IOException, ServletException, DOMException {
		String xformsString;
		String dataType = currentPage.getFormName();
		FormTypeEnum formType = currentPage.getFormType();
		boolean dataTypeSet = currentPage.isDataTypeSet();
		String templateId = currentPage.getTemplate();
		String pageLanguage = currentPage.getLanguage();
		Map<String, String> initParams = currentPage.getInitParams();
		xformsString = getXFormsString(formType, dataType, dataTypeSet, templateId, sessionId, pageId, req.getContextPath(), pageLanguage, initParams, req);
		Document doc;
		// try {
		// doc = org.chiba.xml.dom.DOMUtil.parseString(xformsString, true, false);
		// } catch (ParserConfigurationException e) {
		// throw new ServletException(e);
		// } catch (SAXException e) {
		// throw new ServletException(e);
		// }
		doc = DOMUtil.getDocumentFromString(xformsString);
		if (doc == null) {
			throw new RuntimeException("Unable to build the XForms document");
		}
		Element docElt = doc.getDocumentElement();

		// add CSS file if one is provided
		if (StringUtils.trimToNull(controller.getCssUrl()) != null) {
			Element head = DOMUtil.getChild(docElt, "xhtml:head");
			Element css = doc.createElementNS("http://www.w3.org/1999/xhtml", "link");
			css.setAttribute("rel", "stylesheet");
			css.setAttribute("type", "text/css");
			css.setAttribute("href", controller.getCssUrl());
			head.appendChild(css);
		}
		// hide buttons if applicable
		if (currentPage.isShowSubmitButtons() == false) {
			String tagName = MsgId.INT_SUBMIT_BUTTONS_GROUP_ID.getText();
			DOMUtil.removeEltInDescentByAttrValue(docElt, "id", tagName);
		} else { // #1181: individual hiding of submission buttons
			if (currentPage.isShowCancel() == false) {
				removeSubmitButton(docElt, MsgId.CAPTION_BUTTON_CANCEL);
			}
			if (currentPage.isShowDelete() == false) {
				removeSubmitButton(docElt, MsgId.CAPTION_BUTTON_DELETE);
			}
			if (currentPage.isShowValidate() == false) {
				removeSubmitButton(docElt, MsgId.CAPTION_BUTTON_SUBMIT);
			}
		}
		// add status message
		String statusMsg = statusDisplayedMsg;
		if (statusMsg != null) {
			String tagVal = MsgId.INT_CSS_STATUS_BAR_ID.getText();
			Element status = DOMUtil.getEltInDescentByAttrValue(docElt, "id", tagVal);
			status.setTextContent(statusMsg);
		}
		return doc;
	}

	/**
	 * Removes a specific submit button from the group of submit buttons.
	 * 
	 * @param docElt
	 * @param buttonId
	 */
	private void removeSubmitButton(Element docElt, MsgId buttonId) {
		String realCaption = MsgPool.getMsg(buttonId);
		Element button = null;
		// find the group of submits
		String groupId = MsgId.INT_SUBMIT_BUTTONS_GROUP_ID.getText();
		Element submitGroup = DOMUtil.getEltInDescentByAttrValue(docElt, "id", groupId);
		// find the "xf:submit" node that has the right caption in its
		// "xf:label"
		List<Element> listOfSubmits = DOMUtil.getAllChildren(submitGroup);
		for (Element aSubmit : listOfSubmits) {
			if (StringUtils.equals(aSubmit.getTagName(), "xf:submit")) {
				Element label = DOMUtil.getChild(aSubmit, "xf:label");
				if (StringUtils.equals(label.getTextContent(), realCaption)) {
					button = aSubmit;
					break;
				}
			}
		}
		if (button != null) {
			submitGroup.removeChild(button);
		}
	}

	/**
	 * Registers the URL to associate with a session id.
	 * 
	 * @param req
	 * @param sessionId
	 * @return
	 */
	private String registerSessionURL(HttpServletRequest req, String sessionId) {
		String curServletURL = NavigationSessionListener.getServletURL(sessionId);
		if (logger.isDebugEnabled()) {
			logger.debug("--> looking up session id '" + sessionId + "' for url: " + curServletURL);
		}
		if (curServletURL == null) {
			curServletURL = req.getRequestURL().toString();
			if (NavigationSessionListener.registerServletURL(sessionId, curServletURL)) {
				if (logger.isInfoEnabled()) {
					logger.info("Registered session '" + sessionId + "' for url: " + curServletURL);
				}
			} else {
				if (logger.isInfoEnabled()) {
					logger.info("FAILED to register session '" + sessionId + "' for url: " + curServletURL);
				}
			}
		}
		return curServletURL;
	}

	/**
	 * @param req
	 * @return -1 if all files are loaded OK. 0 if an exception occurred. 1 if
	 *         failed at properties
	 *         files. 2 if failed at redirection file.
	 * @throws IOException
	 * @throws Exception
	 */
	private int loadConfiguration(HttpServletRequest req, boolean fromInitCall) {
		int result = -1;
		try {
			String formsPath = req.getParameter(MsgId.PARAM_PROPERTIES_FILE_FORMS.getText());
			String msgPath = req.getParameter(MsgId.PARAM_PROPERTIES_FILE_MESSAGES.getText());
			if (AlfrescoController.loadProperties(formsPath, msgPath) == false) {
				return 1;
			}

			String redirectPath = req.getParameter(MsgId.PARAM_REDIRECTOR_CONFIG_FILE.getText());
			if (controller.loadRedirectionTable(redirectPath) == false) {
				result = 2; // continue anyway cause some projects may not use workflows
			}
			if (fromInitCall) {
				// we'll also deal with CSS, alfrescoHost
				this.setCssUrl(req);
				String host = req.getParameter(MsgId.PARAM_ALFRESCO_HOST.getText());
				if (StringUtils.trimToNull(host) != null) {
					controller.setAlfrescoUrl(host);
				}
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Couldn't load all configuration files.", e);
			}
			return 0;
		}
		return result;
	}

	/**
	 * @param req
	 */
	private void setCssUrl(HttpServletRequest req) {
		String curCssUrl = StringUtils.trimToNull(req.getParameter(MsgId.PARAM_CSS_FILE.getText()));
		controller.setCssUrl(StringUtils.trimToNull(curCssUrl));
	}

	/**
	 * Assembles some page-specific info from the request into a bean: formType,
	 * dataType, language,
	 * dataId, etc.
	 * 
	 * @param req
	 *            the HTTP request
	 * @return the bean with the info
	 */
	private PageInfoBean collectPageInfo(HttpServletRequest req) throws ServletException {
		PageInfoBean bean = new PageInfoBean();

		// init params
		bean.setInitParams(getInitParams(req));

		// form type
		FormTypeEnum formType = FormTypeEnum.CLASS; // <-- default type
		String paramFormType = req.getParameter(MsgId.PARAM_FORM_TYPE.getText());
		if (StringUtils.equals(paramFormType, MsgId.INT_FORMTYPE_FORM.getText())) {
			formType = FormTypeEnum.FORM;
		}
		if (StringUtils.equals(paramFormType, MsgId.INT_FORMTYPE_LIST.getText())) {
			formType = FormTypeEnum.LIST;
		}
		if (StringUtils.equals(paramFormType, MsgId.INT_FORMTYPE_SEARCH.getText())) {
			formType = FormTypeEnum.SEARCH;
		}
		if (StringUtils.equals(paramFormType, MsgId.INT_FORMTYPE_SELECTOR.getText())) {
			formType = FormTypeEnum.SELECTOR;
		}
		if (StringUtils.equals(paramFormType, MsgId.INT_FORMTYPE_WKFLW.getText())) {
			formType = FormTypeEnum.WKFLW;
		}
		bean.setFormType(formType);

		// type-related
		String originalDatatype = req.getParameter(MsgId.PARAM_DATA_TYPE.getText());
		String dataType = null;
		if (formType == FormTypeEnum.CLASS) {
			dataType = StringUtils.trimToNull(originalDatatype).replace('_', '.');
		} else if (formType == FormTypeEnum.SELECTOR) {
			dataType = originalDatatype.substring(0, originalDatatype.indexOf(MsgId.INT_SUFFIX_FILENAME_SELECTORS.getText()));
		} else {
			dataType = originalDatatype;
		}
		if (dataType == null) {
			throw new ServletException("type cannot be null");
		}
		bean.setFormName(dataType);
		// #1222
		AlfrescoController controller = getController();
		bean.setDataType(controller.getDataTypeFromFormName(bean.getFormName()));
		bean.setWrongCallType(false);

		String dataId = StringUtils.trimToNull(req.getParameter(MsgId.PARAM_DATA_ID.getText()));
		// check that the form is appropriate for the data id
		if (controller.getParamCheckMatchDataForm(bean.getInitParams())) {
			String realFormName = originalDatatype;
			// if (bean.formType == FormTypeEnum.WKFLW) {
			// WorkflowTaskType taskType = controller.getWorkflowTaskType(dataType);
			// realFormName = taskType.getDataForm();
			// }
			if (dataId != null) {
				String userName = controller.getParamUserName(bean.getInitParams());
				AlfrescoTransaction transaction = new AlfrescoTransaction(controller, userName);

				QName contentType = controller.systemGetNodeType(transaction, dataId);
				if (contentType == null) {
					dataId = null;
					logger.error("Content is not valid - Form is opened without Content Id.");
				} else {
					if (bean.getFormType() == FormTypeEnum.WKFLW) {
						dataType = controller.getUnderlyingTypeForWorkflow(realFormName);
						if (dataType == null) {
							logger.error("Workflow form is not associated to a Form data - Form is opened without passing Content Id. Use the parameter 'checkMatchDF=false' if you want to keep the content id");
						}
					} else {
						dataType = controller.getUnderlyingTypeForForm(realFormName);
					}
					if (StringUtils.equals(dataType, contentType.getLocalName()) == false) {
						bean.setWrongCallType(true);
						dataId = null;
					}
				}
			}
		}

		bean.setDataId(controller.patchDataId(dataId));
		bean.setTemplateId(req.getParameter(TEMPLATE_ID));
		bean.setLanguage(req.getParameter(MsgId.PARAM_LANGUAGE.getText()));
		bean.setProcessId(req.getParameter(MsgId.PARAM_WORKFLOW_PROCESS_ID.getText()));
		bean.setInstanceId(req.getParameter(MsgId.PARAM_WORKFLOW_INSTANCE_ID.getText()));

		// ** #1421: support for mass tagging of multiple objects
		String ids = StringUtils.trimToNull(req.getParameter(MsgId.PARAM_MASS_IDS.getText()));
		if (ids != null) {
			bean.setMassTagging(true);
			bean.setMassIds(ids);
		}
		// ** #1421

		// deal with submit buttons
		String submitParam;

		submitParam = req.getParameter(MsgId.PARAM_SHOW_SUBMITS.getText());
		if ((StringUtils.trimToNull(submitParam) != null) && (StringUtils.equalsIgnoreCase(submitParam, "false"))) {
			bean.setShowSubmits(false);
		}
		//
		submitParam = req.getParameter(MsgId.PARAM_SHOW_CANCEL.getText());
		if ((StringUtils.trimToNull(submitParam) != null) && (StringUtils.equalsIgnoreCase(submitParam, "false"))) {
			bean.setShowCancel(false);
		}
		//
		submitParam = req.getParameter(MsgId.PARAM_SHOW_DELETE.getText());
		if ((StringUtils.trimToNull(submitParam) != null) && (StringUtils.equalsIgnoreCase(submitParam, "false"))) {
			bean.setShowDelete(false);
		}
		//
		submitParam = req.getParameter(MsgId.PARAM_SHOW_VALIDATE.getText());
		if ((StringUtils.trimToNull(submitParam) != null) && (StringUtils.equalsIgnoreCase(submitParam, "false"))) {
			bean.setShowValidate(false);
		}

		return bean;
	}

	/**
	 * Gets a map of all non-empty parameters provided in the request, skipping
	 * some of those we are
	 * interested in (e.g. "pageId").
	 * 
	 * @param req
	 *            the req
	 * @return the inits the params
	 */
	@SuppressWarnings("all")
	private Map<String, String> getInitParams(HttpServletRequest req) {

		Map<String, String> result = new TreeMap<String, String>();
		Map<String, String[]> parameters = req.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameters.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String key = entry.getKey();
			if (!StringUtils.equals(key, PAGE_ID) && !StringUtils.equals(key, MsgId.PARAM_DATA_TYPE.getText())) {
				String[] values = entry.getValue();
				if (values.length > 0) {
					result.put(key, values[0]);
				}
			}
		}
		return result;
	}

	/**
	 * Locates a generated XForms template file and returns its contents as a
	 * string in which
	 * placeholders are replaced. Placeholders for indirected user messages and
	 * dynamic enums are
	 * also replaced.
	 * 
	 * @param formType
	 *            the form type, used to select the appropriate subfolder
	 * @param dataType
	 *            the data type
	 * @param dataTypeSet
	 *            the data type set, for abstract types, used to show selector
	 *            forms instead of
	 *            actual forms
	 * @param template
	 *            the template... ;-?
	 * @param sessionId
	 *            the session id, whose placeholder is
	 *            INT_GEN_PLACEHOLDER_SESSION_ID
	 * @param pageId
	 *            the page id
	 * @param contextPath
	 *            the context path, whose placeholder is
	 *            INT_GEN_PLACEHOLDER_CONTEXT_PATH
	 * @param pageLanguage
	 * @param initParams
	 *            the parameters in the uri
	 * @param req
	 * @return the x forms string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 */
	private String getXFormsString(FormTypeEnum formType, String dataType, boolean dataTypeSet, String template, String sessionId, String pageId, String contextPath, String pageLanguage, Map<String, String> initParams, HttpServletRequest req) throws IOException, ServletException {
		StringBuffer xformsStringBuffer = new StringBuffer();
		InputStream xformsStream = null;

		String baseFolder = controller.getParamBaseFolder(initParams) + File.separator + "forms" + File.separator;
		if (logger.isDebugEnabled()) {
			logger.debug("Trying to load form '" + dataType + " from base folder: '" + baseFolder + "'");
		}
		
		String base = null; // the directory and filename (without extension)

		// no path given, we use the default behavior
		if (formType == FormTypeEnum.FORM) {
			base = MsgId.INT_DIRECTORY_FORM_FORMS.getText() + File.separatorChar + dataType;
		} else if (formType == FormTypeEnum.SEARCH) {
			base = MsgId.INT_DIRECTORY_FORM_SEARCH.getText() + File.separatorChar + dataType;
		} else if (formType == FormTypeEnum.WKFLW) {
			base = MsgId.INT_DIRECTORY_FORM_WKFLW.getText() + File.separatorChar + dataType;
		} else if (formType == FormTypeEnum.LIST) {
			base = MsgId.INT_DIRECTORY_FORM_LISTS.getText() + File.separatorChar + dataType;
		} else if (formType == FormTypeEnum.SELECTOR) {
			base = MsgId.INT_DIRECTORY_FORM_SELECTOR.getText() + File.separatorChar + dataType + MsgId.INT_SUFFIX_FILENAME_SELECTORS;
		} else {
			if (!dataTypeSet) {
				if (getController().hasSubTypes(dataType)) {
					base = MsgId.INT_DIRECTORY_FORM_SELECTOR.getText() + File.separatorChar + dataType + MsgId.INT_SUFFIX_FILENAME_SELECTORS;
				} else {
					base = MsgId.INT_DIRECTORY_FORM_CLASSES.getText() + File.separatorChar + dataType;
				}
			} else {
				base = MsgId.INT_DIRECTORY_FORM_CLASSES.getText() + File.separatorChar + dataType;
			}
		}
		// xformsStream = NavigationSessionListener.getContext().getResourceAsStream(
		// "/forms/custom/" + base + "_" + template + ".xhtml");
		xformsStream = getInputStream(baseFolder + "custom" + File.separator + base + (template != null ? "_" : "") + StringUtils.trimToEmpty(template) + ".xhtml");

		if (xformsStream == null) {
			xformsStream = getInputStream(baseFolder + "custom" + File.separator + base + ".xhtml");
			if (xformsStream == null) {
				xformsStream = getInputStream(baseFolder + base + ".xhtml");
			}
			if (xformsStream == null) {
				xformsStream = getInputStream(baseFolder + base.replace('.', '_') + ".xhtml");
			}
		}

		if (xformsStream == null) {
			throw new ServletException("Form not found for type " + base + " (template : " + StringUtils.trimToEmpty(template) + ")");
		}

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(xformsStream, "UTF-8"));
		String line;
		String suffix = (pageLanguage == null) ? "" : "_" + pageLanguage;
		// build parameter map
		Map<String, String> parameters = buildParametersMap(req);
		while (null != ((line = bufferedReader.readLine()))) {
			String replaced = StringUtils.replace(line, MsgId.INT_GEN_PLACEHOLDER_SESSION_ID.getText(), sessionId + ";" + pageId);
			replaced = StringUtils.replace(replaced, MsgId.INT_GEN_PLACEHOLDER_CONTEXT_PATH.getText(), contextPath);
			// replace {@@} tokens
			replaced = substituteParameters(replaced, parameters);
			replaced = substituteUserStrings(replaced, suffix); // used ?? seem to not work very well 
			replaced = substituteDynEnumData(replaced, initParams);
			xformsStringBuffer.append(replaced);
		}
		String xformsString = xformsStringBuffer.toString();
		return xformsString;
	}

	protected static String substituteParameters(String toParse, Map<String, String> params) {
		String result = toParse;
		if (logger.isTraceEnabled()) {
			logger.trace("Parameter Token parsing :");
		}

		// get token
		String regex = "\\{@@([^\\}]*)}";
		Pattern patt = Pattern.compile(regex);
		Matcher mat = patt.matcher(toParse);
		while (mat.find()) {
			String current = mat.group();
			String capture = mat.group(1);
			if (logger.isTraceEnabled()) {
				logger.trace("current :" + current);
				logger.trace("capture :" + capture);
			}
			String replacement = params.get(capture);
			if (replacement != null) {
				result = result.replaceAll(current.replaceAll("\\{", "\\\\{"), replacement);
				if (logger.isDebugEnabled()) {
					logger.debug("substituteParameters : " + capture + " by " + replacement);
				}

			}
		}
		return result;
	}

	protected static Map<String, String> buildParametersMap(HttpServletRequest req) {
		Map<String, String> map = new HashMap<String, String>();
		Map<?, ?> reqp = req.getParameterMap();
		for (Map.Entry<?, ?> entry : reqp.entrySet()) {
			
			Object value_ = entry.getValue();
			if (entry.getKey() instanceof String && value_ instanceof String[]) {
				String key = (String) entry.getKey();
				String value = ((String[]) entry.getValue())[0];
				map.put("requests." + key, value);
			}

		}
		// add default parameters
		map.put("requests.queryString", req.getQueryString().replaceAll("&", "&amp;"));
		logger.debug("parameterMap :"+map);
		return map;
	}

	/**
	 * Gets an input stream for an XForms file, returning <code>null</code> if
	 * the file does not
	 * exist.
	 * 
	 * @param filepath
	 * @return
	 */
	private InputStream getInputStream(String filepath) {
		try {
			InputStream is = new FileInputStream(filepath);
			return is;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * This function is for filling placeholders of dynamic enumerations with
	 * some actual dynamic
	 * enumeration selection data. If this can't be achieved, the concept of
	 * having dynamic
	 * enumerations isn't being fully exploited. The exact mechanism has not
	 * been decided yet so we
	 * just return the string as-is.
	 * 
	 * @param replaced
	 * @param initParams
	 * @return
	 */
	private String substituteDynEnumData(String replaced, Map<String, String> initParams) {
		// TODO Auto-generated method stub
		return replaced;
	}

	/**
	 * Returns the next identifier token from a specific position in the string.
	 * 
	 * @param text
	 * @param pPos
	 * @return the token, or <code>null</code> if not a valid identifier
	 */
	private String nextIdentifierToken(String text, int pPos) {
		String res;
		char[] name = new char[256];
		int idx = 0;
		int pos = pPos;

		char car = text.charAt(pos++);
		if (car == '.' || car == '_' || (car >= 'A' && car <= 'Z') || (car >= 'a' && car <= 'z')) {
			// we don't accept digits on first position

			while (car == '.' || car == '_' || (car >= 'A' && car <= 'Z') || (car >= 'a' && car <= 'z') || (car >= '0' && car <= '9')) {
				name[idx++] = car;
				car = text.charAt(pos++);
			}
			res = new String(name).trim();
			return res;
		}
		return null; // #1672: we must detect invalid tokens
	}

	/**
	 * Replaces all user strings with some dynamic text (currently, from the
	 * properties file).
	 * 
	 * @param pageText
	 *            the text in which substitutions happen
	 * @param suffix
	 *            the language suffix, e.g. "_fr_FR"
	 * @return the modified string
	 */
	private String substituteUserStrings(String pageText, String suffix) {
		String userKey;
		String prop;
		String res;
		String doubleShiftReplace;
		int pos;
		final char REPLACE_MARKER = '#';

		// choose the right surrogate for "##" and do the replacement
		if (pageText.indexOf("@@") == -1) {
			doubleShiftReplace = "@@";
		} else if (pageText.indexOf("&@") == -1) {
			doubleShiftReplace = "&@";
		} else {
			doubleShiftReplace = "@&";
		}
		String doubleMarker = "" + REPLACE_MARKER + REPLACE_MARKER;
		res = pageText.replaceAll(doubleMarker, doubleShiftReplace);

		pos = pageText.indexOf(REPLACE_MARKER);
		while ((pos != -1) && (pos < pageText.length())) {
			// compute the key
			userKey = nextIdentifierToken(pageText, pos + 1);
			if (userKey != null) {

				// look up key in properties file
				prop = MsgPool.testMsg(userKey + suffix);

				// if not found, look up without the suffix
				if (StringUtils.trimToNull(prop) == null) {
					prop = MsgPool.testMsg(userKey);
				}

				//
				// in case other sources for the value exist, like URL params, use them here
				//

				if (StringUtils.trimToNull(prop) == null) {
					// there is no valid replacement string found, so we make no replacement
				} else {
					res = StringUtils.replace(res, REPLACE_MARKER + userKey, prop);
				}
			}
			pos = pageText.indexOf(REPLACE_MARKER, pos + 1);
		}

		return res.replaceAll(doubleShiftReplace, "" + REPLACE_MARKER);
	}

	/**
	 * Gets the controller.
	 * 
	 * @return the controller
	 */
	private AlfrescoController getController() {
		return AlfrescoController.getInstance();
	}

	/**
	 * Inits the action.
	 * 
	 * @param uri
	 *            the uri
	 * @return the abstract action
	 * @throws Exception
	 *             the exception
	 */
	private AbstractAction initAction(String uri) throws Exception {
		URI realUri = new URI(uri);
		String[] fragments = realUri.getSchemeSpecificPart().split("/");

		String actionAndParams = fragments[3];
		int pos = actionAndParams.indexOf('?');
		if (pos == -1) {
			pos = actionAndParams.length();
		}
		String actionName = actionAndParams.substring(0, pos);

		AbstractAction action = actions.get(actionName).newInstance();
		return action;
	}

	/**
	 * Xforms submit.
	 * 
	 * @param result
	 *            the result
	 * @param uri
	 *            the uri
	 * @param submission
	 *            the submission
	 * @param node
	 *            the node
	 * @param chibaSessionId
	 *            the chiba session id
	 * @throws Exception
	 *             the exception
	 */
	public void xformsSubmit(Map<String, Object> result, String uri, Submission submission, Node node, String chibaSessionId) throws Exception {
		logXML(node, "xformsSubmit", uri);

		AbstractAction action = initAction(uri);
		action.setProperties(getController(), uri);
		action.setSubmitProperties(result, submission, node, NavigationSessionListener.getServletURL(action.getSessionId()));
		action.submit();
	}

	/**
	 * Xforms resolve.
	 * 
	 * @param uri
	 *            the uri
	 * @return the node
	 * @throws Exception
	 *             the exception
	 */
	public Node xformsResolve(String uri) throws Exception {
		log("xformsResolve", uri);

		Node node = null;

		AbstractAction action = initAction(uri);
		action.setProperties(getController(), uri);
		node = action.resolve();

		logXML(node, "xformsResolve", uri);
		return node;
	}

	/**
	 * Send image.
	 * 
	 * @param req
	 *            the req
	 * @param resp
	 *            the resp
	 */
	public void sendImage(HttpServletRequest req, HttpServletResponse resp) {
		String location = req.getParameter("location");
		InputStream in = null;
		if (StringUtils.trimToNull(location) != null) {
			boolean isImage = false;
			int lastIndexOf = location.lastIndexOf('.');
			if (lastIndexOf != -1) {
				String extension = location.substring(lastIndexOf).toUpperCase();
				if (extension.endsWith("JPEG") || extension.endsWith("JPG") || extension.endsWith("PNG") || extension.endsWith("BMP") || extension.endsWith("GIF")) {
					isImage = true;
				}
			}
			if (isImage) {
				File sourceFile = null;
				if (location.startsWith("file:")) {
					try {
						// location = URLEncoder.encode(location, "UTF-8");
						// URI fileURI = URI.create(location);
						location = location.replace("file:", "");
						if (location.charAt(2) == ':') {
							location = location.subSequence(1, location.length()).toString();
						}
						sourceFile = new File(location);
					} catch (Exception e) {
						sourceFile = null;
						if (logger.isErrorEnabled()) {
							logger.error(e);
						}
					}
				} else {
					sourceFile = new File(AlfrescoController.UPLOAD_DIRECTORY, location);
				}

				try {
					in = new FileInputStream(sourceFile);
				} catch (FileNotFoundException e) {
					if (logger.isErrorEnabled()) {
						logger.error(e);
					}
				}

			}
		}
		if (in == null) {
			in = NavigationSessionListener.getContext().getResourceAsStream("/resources/images/blank.png");
		}

		ServletOutputStream out = null;

		try {
			out = resp.getOutputStream();
			byte[] bytes = new byte[1024];
			int bytesRead;

			while ((bytesRead = in.read(bytes)) != -1) {
				out.write(bytes, 0, bytesRead);
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
		} finally {

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					if (logger.isErrorEnabled()) {
						logger.error(e);
					}
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					if (logger.isErrorEnabled()) {
						logger.error(e);
					}
				}
			}
		}

	}

}
