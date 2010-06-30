package com.bluexml.xforms.generator.forms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.xforms.generator.FormGeneratorsManager;
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
	 * 
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
	 * @return
	 */
	protected String buildListActionUriFragment(String typeCompleteName, String formatPattern,
			String maxLength, String identifier, String filterAssoc, boolean isComposition,
			boolean isForSearchMode, String luceneQuery) {
		StringBuffer result = new StringBuffer(MsgId.INT_ACT_CODE_LIST.getText() + "/");
		result.append(typeCompleteName).append("/");
		result.append(StringUtils.trimToEmpty(formatPattern)).append("/");
		result.append(maxLength).append("/");
		result.append(StringUtils.trimToEmpty(identifier)).append("/");
		result.append(StringUtils.trimToEmpty(filterAssoc)).append("/");
		result.append(isComposition ? "1" : "0").append("/");
		result.append(isForSearchMode ? "1" : "0").append("/");
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
		result.append(queryEncoded).append("/");
		return result.toString();
	}

}
