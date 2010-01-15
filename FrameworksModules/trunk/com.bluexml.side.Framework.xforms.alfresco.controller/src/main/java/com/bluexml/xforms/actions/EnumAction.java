package com.bluexml.xforms.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.chiba.processor.XFormsProcessor;
import org.chiba.xml.dom.DOMUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.xforms.controller.navigation.NavigationSessionListener;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class EnumAction.<br/>
 * Retrieves a list of items for an enumeration.<br/>
 */
public class EnumAction extends AbstractReadAction {

	/** The Constants for the action's parameters names. */
	protected static final String FILTER_PARENT = "filterParent";
	protected static final String FILTER_DATA = "filterData";
	protected static final String LIMITED = "limited";

	/** The Constant enums. */
	private static final Map<String, Document> enums = new HashMap<String, Document>();

	// should be replaced by a double linked hash map
	private static final Map<String, Map<String, String>> enumValues = new HashMap<String, Map<String, String>>();
	private static final Map<String, Map<String, String>> enumKeys = new HashMap<String, Map<String, String>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_ENUM.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] { RAW_DATA_TYPE, FILTER_PARENT, FILTER_DATA, LIMITED };
	}

	/**
	 * Gets the enum.
	 * 
	 * @param type
	 *            the type
	 * @param filterData
	 *            the filter data
	 * @param filterParent
	 *            the filter parent
	 * @param query
	 * @return the enum
	 * @throws ServletException
	 *             the servlet exception
	 * @throws Exception
	 *             the exception
	 */
	protected Node getEnum(String type, String filterParent, String filterData, String query,
			boolean limited) throws Exception {
		Node result = null;
		boolean dynamic = controller.isDynamicEnum(type);
		if (dynamic) {
			result = controller.getDynamicEnum(transaction, type, filterParent, filterData, query,
					limited);
		} else {
			result = getStaticEnum(type);
		}
		return result;
	}

	/**
	 * Gets the static enum.
	 * 
	 * @param type
	 *            the type
	 * 
	 * @return the static enum
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 */
	private static synchronized Document getStaticEnum(String type) throws ServletException {
		Document result;
		result = enums.get(type);
		if (result == null) {
			InputStream xformsStream = NavigationSessionListener.getContext().getResourceAsStream(
					"/forms/" + type + ".enum.xml");
			try {
				result = DOMUtil.parseInputStream(xformsStream, true, false);
				fillValues(type, result);
			} catch (Exception e) {
				throw new ServletException(e);
			}
			enums.put(type, result);
		}
		return result;
	}

	private static void fillValues(String type, Document doc) {
		Element root = doc.getDocumentElement();

		Map<String, String> values = new HashMap<String, String>();
		Map<String, String> keys = new HashMap<String, String>();

		NodeList childNodes = root.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child instanceof Element) {
				Element element = (Element) child;
				addValue(element, values, keys);
			}
		}

		enumValues.put(type, values);
		enumKeys.put(type, keys);
	}

	private static void addValue(Element element, Map<String, String> values,
			Map<String, String> keys) {
		String id = null;
		String value = null;

		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child instanceof Element) {
				Element e = (Element) child;
				if (StringUtils.equals(e.getTagName(), "id")) {
					id = e.getTextContent();
				}
				if (StringUtils.equals(e.getTagName(), "value")) {
					value = e.getTextContent();
				}
			}
		}
		if (id != null && value != null) {
			values.put(id, value);
			keys.put(value, id);
		}
	}

	public static synchronized String getEnumValue(String type, String key) {
		try {
			getStaticEnum(type);
		} catch (ServletException e) {
			logger.error(e);
		}
		return enumValues.get(type).get(key);
	}

	public static synchronized String getEnumKey(String type, String value) {
		try {
			getStaticEnum(type);
		} catch (ServletException e) {
			logger.error(e);
		}
		return enumKeys.get(type).get(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeResolve()
	 */
	@Override
	public Node resolve() throws Exception {
		String dataType = requestParameters.get(RAW_DATA_TYPE);
		String filterParent = requestParameters.get(FILTER_PARENT);
		String filterData = requestParameters.get(FILTER_DATA);

		return getEnum(dataType, filterParent, filterData, null, isLimited());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	// TODO: remove this function
	@SuppressWarnings("deprecation")
	@Override
	public void submit() throws Exception {
		// update list using search
		Document doc = (Document) node;
		String query = doc.getDocumentElement().getTextContent();

		String dataType = requestParameters.get(RAW_DATA_TYPE);
		String filterParent = requestParameters.get(FILTER_PARENT);
		String filterData = requestParameters.get(FILTER_DATA);

		// retrieves elements
		Node list = getEnum(dataType, filterParent, filterData, query, isLimited());

		// convert to string
		Source xmlSource = new DOMSource(list);
		ByteArrayOutputStream pos = new ByteArrayOutputStream();
		Result outputTarget = new StreamResult(pos);
		documentTransformer.transform(xmlSource, outputTarget);

		ByteArrayInputStream pis = new ByteArrayInputStream(pos.toByteArray());

		result.put(XFormsProcessor.SUBMISSION_RESPONSE_STREAM, pis);
	}

	private boolean isLimited() {
		String limited = requestParameters.get(LIMITED);
		return StringUtils.equals(limited, "1");
	}

}
