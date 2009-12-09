package com.bluexml.xforms.generator.forms.modelelement;

import org.apache.commons.lang.StringUtils;
import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class ModelElementListUpdater.
 */
public class ModelElementUpdaterList extends AbstractModelElementUpdater {

	private String formatPattern;

	private String maxLength;

	public ModelElementUpdaterList(Clazz target, String instanceName, String formatPattern,
			String maxLength) {
		super(target, instanceName);
		this.formatPattern = formatPattern;
		this.maxLength = maxLength;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#getModelElement()
	 */
	@Override
	public Element getModelElement() {
		Element submission = XFormsGenerator.createElement("submission",
				XFormsGenerator.NAMESPACE_XFORMS);
		submission.setAttribute("action", MsgId.INT_URI_SCHEME_WRITER
				+ MsgId.INT_ACT_CODE_LIST.getText() + "/" + typeCompleteName + "/"
				+ StringUtils.trimToEmpty(formatPattern) + "/" + maxLength);
		submission.setAttribute("replace", "instance");
		submission.setAttribute("instance", instanceName);
		submission.setAttribute("method", "post");

		String submitId = XFormsGenerator.getId("submit");
		for (Element linkedElement : linkedElements) {
			linkedElement.setAttribute("submission", submitId);
		}
		submission.setAttribute("id", submitId);
		submission.setAttribute("ref", "instance('" + instanceName + "')/query");
		return submission;
	}

}
