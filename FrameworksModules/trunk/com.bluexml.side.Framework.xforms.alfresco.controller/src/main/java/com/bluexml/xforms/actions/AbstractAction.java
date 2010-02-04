package com.bluexml.xforms.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.chiba.xml.xforms.core.Submission;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.alfresco.AlfrescoController;
import com.bluexml.xforms.controller.alfresco.AlfrescoTransaction;
import com.bluexml.xforms.controller.navigation.NavigationPath;
import com.bluexml.xforms.controller.navigation.NavigationSessionListener;
import com.bluexml.xforms.messages.MsgId;

import edu.yale.its.tp.cas.client.CASReceipt;
import edu.yale.its.tp.cas.client.filter.CASFilter;

/**
 * The Class AbstractAction.<br>
 * Base of all actions
 */
public abstract class AbstractAction {

	/** The logger. */
	protected static Log logger = LogFactory.getLog(AbstractAction.class);

	/** The Constant DATA_TYPE. */
	public static final String DATA_TYPE = "type";

	/** The Constant RAW_DATA_TYPE. */
	public static final String RAW_DATA_TYPE = "rawtype";

	/** The Constant FORM_TYPE. */
	public static final String FORM_TYPE = "formType";

	/** The Constant DATA_ASSOC. */
	public static final String DATA_ASSOC = "assoc";

	/** The Constant DATA_ID. */
	public static final String DATA_ID = "id";

	/** The Constant DATA_QUERY. */
	public static final String DATA_QUERY = "query";

	/** The Constant DATA_MAXRESULTS. */
	public static final String DATA_MAXRESULTS = "size";

	public static final String LABEL_FORMAT = "format";

	/** The document transformer. */
	protected static Transformer documentTransformer;
	static {
		// initialize statically documentTransformer
		try {
			documentTransformer = TransformerFactory.newInstance().newTransformer();
			documentTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** The request parameters. */
	protected Map<String, String> requestParameters;

	/** The session. */
	protected HttpSession session;

	/** The controller. */
	protected AlfrescoController controller;

	/** The uri. */
	protected String uri;

	/** The result. */
	protected Map<String, Object> result;

	/** The submission. */
	protected Submission submission;

	/** The node. */
	protected Node node;

	/** The servlet url. */
	protected String servletURL;

	/** The navigation path. */
	protected NavigationPath navigationPath;

	/** The transaction. */
	protected AlfrescoTransaction transaction;

	protected String transactionLogin;

	/** The session id. */
	protected String sessionId;

	/** The page id. */
	protected String pageId;

	/**
	 * Adds the entry.
	 * 
	 * @param fragments
	 *            the fragments
	 * @param name
	 *            the name
	 * @param index
	 *            the index
	 */
	protected void addEntry(String[] fragments, String name, int index) {
		if (fragments.length > index) {
			String fragment = fragments[index];
			if (name.equals(DATA_TYPE)) {
				fragment = fragment.replace('_', '.');
			}
			requestParameters.put(name, fragment);
		}
	}

	/**
	 * Execute resolve. Used for read operations.
	 * 
	 * @return the node
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public abstract Node resolve() throws Exception;

	/**
	 * Execute submit. Used for write operations.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public abstract void submit() throws Exception;

	/**
	 * Checks if is replace all.
	 * 
	 * @return true, if is replace all
	 */
	public boolean isReplaceAll() {
		return true;
	}

	/**
	 * Checks if this action requires the form to be validated first.
	 * 
	 * @return true, if it should be checked that mandatory fields are filled
	 */
	public boolean isValidateFirst() {
		return false;
	}

	/**
	 * Gets the action name. This is the name by which an action handler declares the urls it should
	 * be mapped to. <br/>
	 * e.g. the handler for bxwdswriter://.../get urls should return "get".
	 * 
	 * @return the action name
	 */
	public abstract String getActionName();

	/**
	 * Gets the caption by which the action is labeled on a button.
	 * 
	 * @return the action caption
	 */
	public String getActionCaption() {
		// by default, we consider the action is never used from a button
		return null;
	}

	/**
	 * Gets the param names. The parameters expected by the action are listed in a specific order.
	 * The url that maps to the action must respect that order, with params delimited by "/".<br/>
	 * An action that requires a type and parent must be
	 * <tt>scheme://.../ACTION_NAME/TYPE/PARENT</tt>.
	 * 
	 * @return the param names
	 */
	protected String[] getParamNames() {
		// by default, we consider that the action is self-contained
		return new String[] {};
	}

	/**
	 * Gets the request parameters.
	 * <p/>
	 * NOTE: <b>DO NOT</b> use transaction here. Otherwise, it would be null and cause an exception.
	 * (Amenel)
	 * 
	 * @param fragments
	 *            the fragments
	 * 
	 * @return the request parameters
	 */
	protected void getRequestParameters(String[] fragments) {
		requestParameters = new TreeMap<String, String>();

		String sessionIdPageId = fragments[2];
		String[] sessionFragments = sessionIdPageId.split(";");
		sessionId = sessionFragments[0];

		session = NavigationSessionListener.getSession(sessionId);
		if (session != null) {
			Object receiptAsObject = session.getAttribute(CASFilter.CAS_FILTER_RECEIPT);
			if (receiptAsObject != null) {
				CASReceipt receipt = (CASReceipt) receiptAsObject;
				transactionLogin = receipt.getUserName();
			}
		}
		pageId = sessionFragments[1];

		navigationPath = NavigationSessionListener.getNavigationPath(sessionId, pageId);

		String[] paramNames = getParamNames();
		int i = 4;
		for (String paramName : paramNames) {
			addEntry(fragments, paramName, i);
			i++;
		}
	}

	/**
	 * Sets the properties.
	 * 
	 * @param controller
	 *            the controller
	 * @param uri
	 *            the uri
	 */
	public void setProperties(AlfrescoController controller, String uri) {
		URI realUri;
		try {
			realUri = new URI(uri);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		String[] fragments = realUri.getSchemeSpecificPart().split("/");
		getRequestParameters(fragments);
		this.controller = controller;
		this.uri = uri;
		this.transaction = new AlfrescoTransaction(controller);
		this.transaction.setLogin(transactionLogin);
	}

	/**
	 * Redirects the client to the given URL after a submission action.
	 * 
	 * @param nextPageURL
	 */
	protected void redirectClient(String nextPageURL) {
		result.put("Location", nextPageURL);
	}

	/**
	 * Sets the submission default location.
	 * 
	 * @param servletURL
	 *            the servlet url
	 * @param result
	 *            the result
	 */
	protected void setSubmissionDefaultLocation(String servletURL, Map<String, Object> result) {
		String location = servletURL + "?pageId=" + pageId + "&stackId=" + navigationPath.getSize();
		result.put("Location", location);
	}

	/**
	 * Sets the submit properties.
	 * 
	 * @param result
	 *            the result
	 * @param submission
	 *            the submission
	 * @param node
	 *            the node
	 * @param servletURL
	 *            the servlet url
	 */
	public void setSubmitProperties(Map<String, Object> result, Submission submission, Node node,
			String servletURL) {
		this.result = result;
		this.submission = submission;
		this.node = node;
		this.servletURL = servletURL;
	}

	/**
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	protected String callExternalAction(String className) throws ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException, InvocationTargetException,
			InstantiationException {
		String resultStr = "";
		Class<?> theClass = Class.forName(className);
		Method method = theClass.getMethod("run", new Class[] { org.w3c.dom.Node.class,
				java.lang.String.class });
		String dataId = navigationPath.peekCurrentPage().getDataId();
		Object result = method.invoke(theClass.newInstance(), new Object[] { node, dataId });
		try {
			resultStr = (String) result;
		} catch (ClassCastException e) {
			logger.warn("Returned result is not of type java.lang.String; will be ignored.", e);
		}
		return resultStr;
	}

	/**
	 * Builds the URL for reaching the given form using the servlet URL that triggered the action.
	 * 
	 * @param formName
	 * @return
	 */
	protected String buildWorkflowFormURL(String formName) {
		return getServletURL() + "?" + MsgId.PARAM_DATA_TYPE + "=" + formName + "&"
				+ MsgId.PARAM_FORM_TYPE + "=" + MsgId.INT_FORMTYPE_WKFLW;
	}

	/**
	 * @return the servletURL
	 */
	public String getServletURL() {
		return servletURL;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

}
