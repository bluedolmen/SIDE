package com.bluexml.xforms.generator.forms.modelelement;

import org.jdom.Element;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class ModelElementListUpdater. Provides an element of an XForms template's "model" section.
 * The element will trigger the fetching <em>and replacing</em> of a selection widget's item set.
 */
public class ModelElementUpdaterList extends AbstractModelElementUpdater {

	private String formatPattern;

	private String maxLength;

	private String identifier; // #1529

	private String filterAssoc; // #1536

	private boolean isComposition; // #1536

	public ModelElementUpdaterList(Clazz classe, String instanceName, String formatPattern,
			String maxLength, String filterAssoc, boolean isComposition) {
		super(classe, instanceName);
		this.formatPattern = formatPattern;
		this.maxLength = maxLength;
		this.identifier = ""; // constructor used with Clazz provided. No need for this field.
		this.filterAssoc = filterAssoc;
		this.isComposition = isComposition;
	}

	public ModelElementUpdaterList(String overridingType, String instanceName,
			String formatPattern, String labelLength, String identifier) {
		super(overridingType, instanceName);
		this.formatPattern = formatPattern;
		this.maxLength = labelLength;
		this.identifier = identifier;
		this.filterAssoc = null;
		this.isComposition = false;
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
				+ buildListURI(typeCompleteName, formatPattern, maxLength, identifier, filterAssoc,
						isComposition));
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
