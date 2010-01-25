package com.bluexml.xforms.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.bluexml.xforms.messages.MsgId;
import com.bluexml.side.form.utils.DOMUtil;

/**
 * This servlet is intended to act as a switching (shunting) agent who, depending on specifications
 * from the configuration file, redirects a client to a URL, passing along any parameter received
 * and adding a "nextPage" parameter.<br/>
 * Setup for moving seamlessly from one workflow form to the other when the transition is
 * successfully followed.
 * 
 * @author Amenel
 * 
 */
public class RedirectionAgentServlet extends HttpServlet {

	private class RedirectionBean {
		private String targetUrl;
		private boolean autoAdvance;

		public RedirectionBean(String targetUrl, boolean autoAdvance) {
			super();
			this.targetUrl = targetUrl;
			this.autoAdvance = autoAdvance;
		}

		/**
		 * @return the targetUrl
		 */
		public String getTargetUrl() {
			return targetUrl;
		}

		/**
		 * @return the autoAdvance feature
		 */
		public boolean isAutoAdvance() {
			return autoAdvance;
		}

	}

	private String ServletName = "BlueXML XForms RedirectorAgentServlet";

	/** Stores redirection info keyed by form names */
	private Map<String, RedirectionBean> targetTable = new HashMap<String, RedirectionBean>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1583124850841529853L;

	String configFilePath = "redirect.xml";

	@Override
	public void init() throws ServletException {
		super.init();
		if (readConfigTable() == false) {
			System.err
					.println(ServletName
							+ ": Couldn't read the redirection table configuration. Unavailable until a configuration file is provided.");
		}
		System.out.println(ServletName + " initialized.");
	}

	/**
	 * Reads the configuration file and builds the target table.
	 * <p/>
	 * The file is an XML file with the following format:<br/>
	 * 
	 * <pre>
	 * &lt;entries&gt;
	 *   &lt;entry&gt;
	 *     &lt;name&gt;
	 *     &lt;url&gt;
	 *     &lt;auto&gt;
	 *   &lt;/entry&gt;
	 *   &lt;entry&gt;
	 *   ...
	 * &lt;/entries&gt;
	 * </pre>
	 * 
	 * @return true if the file was found and read successfully, false otherwise.
	 */
	private boolean readConfigTable() {
		InputStream stream = RedirectionAgentServlet.class.getResourceAsStream(configFilePath);
		String formName = null;
		String url = null;
		boolean autoAdvance = false;

		Document document = DOMUtil.getDocumentFromStream(stream);
		if (document == null) {
			return false;
		}
		
		// we won't check the tag name for the root element
		Element root = document.getDocumentElement();
		List<Element> entries = DOMUtil.getChildren(root, "entry");
		// for each entry of the file, store the info
		for (Element entry : entries) {
			Element nameElt = DOMUtil.getChild(entry, "name");
			if (nameElt == null) {
				// get rid of everything previously read
				targetTable = new HashMap<String, RedirectionBean>();
				return false;
			}
			formName = nameElt.getTextContent();
			Element urlElt = DOMUtil.getChild(entry, "url");
			url = urlElt.getTextContent();
			Element autoElt = DOMUtil.getChild(entry, "auto");
			autoAdvance = StringUtils.equals(autoElt.getTextContent(), "true");
			RedirectionBean bean = new RedirectionBean(url, autoAdvance);

			targetTable.put(formName, bean);
		}
		System.out.println(ServletName + " configuration file '" + configFilePath
				+ "' successfully read.");

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	/**
	 * We expect this to be called when the web client is redirected to this servlet after clicking
	 * a transition button.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		super.doGet(req, resp);
		// find formName from the request
		String formName = req.getParameter(MsgId.PARAM_LEAVING_FORM.getText());
		if (StringUtils.trimToNull(formName) != null) {
			RedirectionBean bean = targetTable.get(formName);
			if (bean != null) {
				StringBuffer outParamsStr = new StringBuffer();
				// copy parameters
				Map<String, ?> paramsMap = req.getParameterMap();
				Set<String> paramsSet = paramsMap.keySet();

				boolean first = true;
				for (String param : paramsSet) {
					if (isParameterToSkip(param)) {
						break;
					}
					addParam(outParamsStr, first, param, req.getParameterValues(param));
				}
				if (bean.isAutoAdvance()) {
					String[] paramValues = { "true" };
					addParam(outParamsStr, first, MsgId.PARAM_AUTO_ADVANCE.getText(), paramValues);
				} else {
					String[] paramValues = new String[1];
					paramValues[0] = req.getContextPath() + req.getServletPath();
					addParam(outParamsStr, first, MsgId.PARAM_PAGE_SUCCESS.getText(), paramValues);
				}
				// redirect to specified url
				String location = bean.getTargetUrl() + "?" + outParamsStr;
				resp.sendRedirect(resp.encodeRedirectURL(location));
			}
		}
		// otherwise...
		// TODO:
	}

	/**
	 * Tells whether a parameter should not be reported to the redirection address.
	 * @param paramName
	 * @return
	 */
	private boolean isParameterToSkip(String paramName) {
		String[] paramsToSkip = { MsgId.PARAM_LEAVING_FORM.getText(),
				MsgId.PARAM_REDIRECT_FROM_URL.getText() };

		for (String param : paramsToSkip) {
			if (param.equals(paramName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param outParamsStr
	 * @param first
	 * @param param
	 */
	private void addParam(StringBuffer outParamsStr, boolean first, String paramName,
			String[] paramValue) {
		if (first == false) {
			outParamsStr.append('&');
		}
		outParamsStr.append(paramName + "=" + paramValue);
		first = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String paramConfigPath = req.getParameter(MsgId.PARAM_REDIRECTOR_CONFIG_FILE.getText());
		if (paramConfigPath != null) {
			loadConfigTable(paramConfigPath, resp);
		}
	}

	private void loadConfigTable(String paramConfigPath, HttpServletResponse resp)
			throws IOException {
		configFilePath = paramConfigPath;
		if (readConfigTable() == true) {
			sendReplyLoadConfigFile(resp);
		}
	}

	/**
	 * Writes a simple HTML page for notifying a successful loading of the configuration file.
	 * 
	 * @param resp
	 * @throws IOException
	 */
	private void sendReplyLoadConfigFile(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);
		ServletOutputStream stream = resp.getOutputStream();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<head><title>");
		buffer.append(ServletName);
		buffer.append("</title></head>");
		buffer.append("<body>");
		buffer.append("<h1>Successfully initialized configuration file.</h1>");
		buffer.append("</body>");

		stream.print(buffer.toString());
		stream.close();
	}

}
