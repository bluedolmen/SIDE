package com.bluexml.xforms.generator.forms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.xforms.generator.FormGeneratorsManager;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class ModelElement.<br>
 * Represents an element rendered inside the model of a form
 */
public abstract class ModelElement {

	private static FormGeneratorsManager formGenerator;

	/** The model element. */
	private Element modelElement;

	private boolean inWorkflowForm;

	/**
	 * Gets the model element.<br>
	 * DOM element to be added to document
	 * 
	 * @return the model element
	 */
	public Element getModelElement() {
		return modelElement;
	}

	/**
	 * Checks for clone.
	 * 
	 * @param allModelElementsClean
	 *            the model elements already added
	 * @return true, if self has already a clone inside provided list
	 */
	public abstract boolean hasClone(List<ModelElement> allModelElementsClean);

	/**
	 * @param inWorkflowForm
	 *            the inWorkflowForm to set
	 */
	public void setInWorkflowForm(boolean inWorkflowForm) {
		this.inWorkflowForm = inWorkflowForm;
	}

	/**
	 * @return the inWorkflowForm
	 */
	public boolean isInWorkflowForm() {
		return inWorkflowForm;
	}

	/**
	 * @return the formGenerator
	 */
	public static FormGeneratorsManager getFormGenerator() {
		return formGenerator;
	}

	/**
	 * @param formGenerator
	 *            the formGenerator to set
	 */
	public static void setFormGenerator(FormGeneratorsManager formGenerator) {
		ModelElement.formGenerator = formGenerator;
	}

	/**
	 * Builds the path section of the URI for the list action
	 * 
	 * @param identifier
	 * @param maxLength
	 * @param formatPattern
	 * @param typeCompleteName
	 * @param dataSourceUri
	 * @return
	 */
	protected String buildListActionUriFragment(String typeCompleteName, String formatPattern, String maxLength, String identifier, String filterAssoc, boolean isComposition, boolean isForSearchMode, String luceneQuery, String dataSourceUri) {
		// StringBuffer result = new StringBuffer(MsgId.INT_ACT_CODE_LIST.getText() + "/");
		// result.append(typeCompleteName).append("/");
		// result.append(StringUtils.trimToEmpty(formatPattern)).append("/");
		// result.append(maxLength).append("/");
		// result.append(StringUtils.trimToEmpty(identifier)).append("/");
		// result.append(StringUtils.trimToEmpty(filterAssoc)).append("/");
		// result.append(isComposition ? "1" : "0").append("/");
		// result.append(isForSearchMode ? "1" : "0").append("/");
		String queryEncoded = StringUtils.trimToEmpty(luceneQuery);
		try {
			// encoded twice (because of our using the "/" as a param separator in URIs) so that the
			// user doesn't have to encode "/" as "%252F"
			queryEncoded = URLEncoder.encode(queryEncoded, "UTF-8");
			queryEncoded = URLEncoder.encode(queryEncoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			queryEncoded = "";
		}
		// result.append(queryEncoded).append("/");
		// return result.toString();
		String result = MsgId.INT_ACT_CODE_LIST.getText() + "?";

		List<MsgId> param = new ArrayList<MsgId>();
		List<String> value = new ArrayList<String>();
		String isCompStr = isComposition ? "1" : "0";
		String isSearchStr = isForSearchMode ? "1" : "0";

		param.add(MsgId.INT_ACT_PARAM_ANY_DATATYPE);
		value.add(typeCompleteName);
		param.add(MsgId.INT_ACT_PARAM_LIST_FORMAT);
		value.add(formatPattern);
		param.add(MsgId.INT_ACT_PARAM_LIST_MAXLENGTH);
		value.add(maxLength);
		param.add(MsgId.INT_ACT_PARAM_LIST_IDENTIFIER);
		value.add(identifier);
		param.add(MsgId.INT_ACT_PARAM_LIST_FILTER_ASSOC);
		value.add(filterAssoc);
		param.add(MsgId.INT_ACT_PARAM_LIST_IS_COMPOSITION);
		value.add(isCompStr);
		param.add(MsgId.INT_ACT_PARAM_LIST_IS_SEARCH_MODE);
		value.add(isSearchStr);
		param.add(MsgId.INT_ACT_PARAM_LIST_LUCENE_QUERY);
		value.add(queryEncoded);
		if (dataSourceUri != null) {
			param.add(MsgId.INT_ACT_PARAM_LIST_DATA_SOURCE_URI);
			value.add(dataSourceUri.replaceAll("&", "@\\$@"));
		}

		for (int i = 0; i < param.size(); i++) {
			if (i > 0) {
				result += "&";
			}
			result += param.get(i) + "=" + StringUtils.trimToEmpty(value.get(i));
		}
		return result;
	}

	protected String buildEnumActionUriFragment(SelectBean selectBean) {
		String enumName;
		if (selectBean.getEnumeration() != null) {
			enumName = ModelTools.getCompleteName(selectBean.getEnumeration());
		} else {
			enumName = selectBean.getOperatorType();
		}
		// StringBuffer sb = new StringBuffer(enumName);
		// sb.append("/");
		// sb.append(StringUtils.trimToEmpty(selectBean.getEnumParent()));
		// sb.append("/");
		// sb.append(StringUtils.trimToEmpty(selectBean.getEnumContext()));
		// sb.append("/");
		// if (selectBean.isLimited()) {
		// sb.append("1");
		// } else {
		// sb.append("0");
		// }
		String parent = selectBean.getEnumParent();
		String context = selectBean.getEnumContext();
		String limit = selectBean.isLimited() ? "1" : "0";

		StringBuffer sb = new StringBuffer();
		sb.append(MsgId.INT_ACT_PARAM_ENUM_RAWTYPE + "=" + enumName);
		sb.append("&" + MsgId.INT_ACT_PARAM_ENUM_FILTER_PARENT + "=" + StringUtils.trimToEmpty(parent));
		sb.append("&" + MsgId.INT_ACT_PARAM_ENUM_FILTER_DATA + "=" + StringUtils.trimToEmpty(context));
		sb.append("&" + MsgId.INT_ACT_PARAM_ENUM_LIMITED + "=" + limit);

		return sb.toString();
	}

}
