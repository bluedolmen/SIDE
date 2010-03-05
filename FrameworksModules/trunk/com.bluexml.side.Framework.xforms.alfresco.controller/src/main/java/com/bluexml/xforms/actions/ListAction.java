package com.bluexml.xforms.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.chiba.processor.XFormsProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.xforms.messages.MsgId;

/**
 * The Class ListAction.<br>
 * Get the list of a specified type with a search query. This action is a read and write action
 * hence its not being under {@link AbstractReadAction} or {@link AbstractWriteAction}.
 */
public class ListAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeResolve()
	 */
	@Override
	public Node resolve() throws ServletException {
		// retrieves elements
		return list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	/**
	 * Used when performing searches on lists. Invoked via ModelElementListUpdater.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void submit() throws ServletException {
		// update list using search
		Document doc = (Document) node;
		String query = "";
		String maxResults = "";
		Element queryElement = doc.getDocumentElement();
		NodeList childNodes = queryElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child instanceof Element) {
				Element element = (Element) child;
				if (StringUtils.equals(element.getTagName(), "query")) {
					query = element.getTextContent();
				}
				if (StringUtils.equals(element.getTagName(), "maxResults")) {
					maxResults = element.getTextContent();
				}
			}
		}

		requestParameters.put(MsgId.INT_ACT_PARAM_LIST_QUERY.getText(), query);
		requestParameters.put(MsgId.INT_ACT_PARAM_LIST_SIZE.getText(), maxResults);

		// retrieves elements
		Node list = list();

		// convert to string
		Source xmlSource = new DOMSource(list);
		ByteArrayOutputStream pos = new ByteArrayOutputStream();
		Result outputTarget = new StreamResult(pos);
		try {
			documentTransformer.transform(xmlSource, outputTarget);
		} catch (TransformerException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Failed to convert the list document into a string", e);
			}
			throw new ServletException(MsgId.MSG_DEFAULT_ERROR_MSG.getText());
		}

		ByteArrayInputStream pis = new ByteArrayInputStream(pos.toByteArray());

		result.put(XFormsProcessor.SUBMISSION_RESPONSE_STREAM, pis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_LIST.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] {
				MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText(),
				MsgId.INT_ACT_PARAM_LIST_FORMAT.getText(),
				MsgId.INT_ACT_PARAM_LIST_MAXLENGTH.getText() };
	}

	/**
	 * Bridge to the controller to get a list of items from a data type.
	 * 
	 * @return the node
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private Node list() throws ServletException {
		// simply call controller
		String dataType = requestParameters.get(MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText());
		String query = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_QUERY.getText());
		String maxResults = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_SIZE.getText());
		String format = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_FORMAT.getText());
		String maxLength = requestParameters.get(MsgId.INT_ACT_PARAM_LIST_MAXLENGTH.getText());
		// "format" was partially decoded so we need to re encode the format pattern, since it
		// will be transmitted again via URL to the webscript where it'll have to be decoded again.
		try {
			if (StringUtils.trimToNull(format) != null) {
				format = URLDecoder.decode(format, "UTF-8");
				format = URLEncoder.encode(format, "UTF-8");
				// format = StringEscapeUtils.escapeXml(format);
			}
		} catch (UnsupportedEncodingException e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("Unsupported encoding scheme");
			}
			throw new RuntimeException("Unsupported encoding scheme");
		}

		return controller.getList(transaction, dataType, query, maxResults, format, maxLength);
	}

}
