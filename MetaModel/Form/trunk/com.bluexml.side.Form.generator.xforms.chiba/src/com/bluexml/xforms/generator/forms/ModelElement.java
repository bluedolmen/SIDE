package com.bluexml.xforms.generator.forms;

import java.util.List;

import org.jdom.Element;

import com.bluexml.xforms.generator.FormGeneratorsManager;

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
	 * @param formGenerator the formGenerator to set
	 */
	public static void setFormGenerator(FormGeneratorsManager formGenerator) {
		ModelElement.formGenerator = formGenerator;
	}


}
