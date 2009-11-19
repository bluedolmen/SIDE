package com.bluexml.xforms.generator.forms.modelelement;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class ModelElementSubmission.
 */
public class ModelElementSubmission extends ModelElement {

	/** The action. */
	private String action;

	/** The caption. */
	private String caption;

	/** The linked elements. */
	protected List<Element> linkedElements = new ArrayList<Element>();

	/** The optionnal node set. */
	private String optionnalNodeSet;

	/** The replace all. */
	private boolean replaceAll;

	/** Whether XForms should validate the form before triggering the submit. */
	private boolean validateFirst;

	/** Whether this submission's button is always active, even in read-only forms. */
	private boolean alwaysActive;

	/**
	 * Instantiates a new model element submission.
	 * 
	 * @param action
	 *            the action
	 * @param caption
	 *            the caption
	 * @param replaceAll
	 *            the replace all
	 */
	public ModelElementSubmission(String action, String caption, boolean replaceAll,
			boolean validateFirst) {
		this.action = action;
		this.caption = caption;
		this.replaceAll = replaceAll;
		this.optionnalNodeSet = null;
		this.validateFirst = validateFirst;
		this.alwaysActive = true;
	}

	/**
	 * Adds the linked element.
	 * 
	 * @param linkedElement
	 *            the linked element
	 */
	public void addLinkedElement(Element linkedElement) {
		linkedElements.add(linkedElement);
	}

	/**
	 * Gets the caption.
	 * 
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Sets the optionnal node set.
	 * 
	 * @param optionnalNodeSet
	 *            the new optionnal node set
	 */
	public void setOptionnalNodeSet(String optionnalNodeSet) {
		this.optionnalNodeSet = optionnalNodeSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#getModelElement()
	 */
	public Element getModelElement() {
		Element submission = XFormsGenerator.createElement("submission",
				XFormsGenerator.NAMESPACE_XFORMS);
		submission.setAttribute("action", action);

		if ((isAlwaysActive() == false) && getFormGenerator().isInReadOnlyMode()) { // #1222
			submission.setAttribute("readonly", "true");
		}

		submission.setAttribute("replace", replaceAll ? "all" : "none");
		submission.setAttribute("validate", validateFirst ? "true" : "false");
		if (optionnalNodeSet != null) {
			submission.setAttribute("ref", optionnalNodeSet);
		}

		String submitId = XFormsGenerator.getId("submit");
		for (Element linkedElement : linkedElements) {
			linkedElement.setAttribute("submission", submitId);
		}

		submission.setAttribute("method", "post");
		submission.setAttribute("id", submitId);
		// prevent non-relevant elements from being pruned
		submission.setAttribute("relevant", "false");
		return submission;
	}

	/**
	 * Sets the action.
	 * 
	 * @param action
	 *            the new action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#hasClone(java.util.List)
	 */
	@Override
	public boolean hasClone(List<ModelElement> allModelElementsClean) {
		return false;
	}

	/**
	 * @return the alwaysActive feature
	 */
	public boolean isAlwaysActive() {
		return alwaysActive;
	}

	/**
	 * @param alwaysActive
	 *            the alwaysActive feature to set
	 */
	public void setAlwaysActive(boolean alwaysActive) {
		this.alwaysActive = alwaysActive;
	}

}
