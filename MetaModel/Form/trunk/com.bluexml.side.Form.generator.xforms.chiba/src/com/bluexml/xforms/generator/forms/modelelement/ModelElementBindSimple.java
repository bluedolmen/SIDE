package com.bluexml.xforms.generator.forms.modelelement;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class ModelElementBindSimple.
 */
public class ModelElementBindSimple extends ModelElement {

	/** The nodeset. */
	protected String nodeset;

	/** The linked elements. */
	protected List<Element> linkedElements = new ArrayList<Element>();

	/** The constraint. */
	protected String constraint;

	/** The length constraint. */
	private String lengthConstraint = null;

	/** The type. */
	protected QName type;

	/** The required. */
	protected boolean required = false;

	/** The read only. */
	protected boolean readOnly = false;

	/** The visibility of the form control. */
	protected boolean hidden = false;

	protected ModelElementBindSimple anotherMeb = null;

	/**
	 * Instantiates a new model element bind simple.
	 * 
	 * @param nodeset
	 *            the nodeset
	 */
	public ModelElementBindSimple(String nodeset) {
		this.nodeset = nodeset;
		// by default, not in a workflow
		setInWorkflowForm(false);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.green.xforms.generator.forms.ModelElement#getModelElement()
	 */
	public Element getModelElement() {
		String bindId = XFormsGenerator.getId(MsgId.INT_GEN_PREFIX_BIND_FORM.getText());
		Element bindElement = XFormsGenerator.createElement("bind",
				XFormsGenerator.NAMESPACE_XFORMS);
		bindElement.setAttribute("nodeset", nodeset);
		bindElement.setAttribute("id", bindId);
		if (StringUtils.trimToNull(constraint) != null) {
			bindElement.setAttribute("constraint", constraint);
		}
		if (type != null) {
			bindElement.setAttribute("type", type.toString());
		}
		if (isRequired()) {
			bindElement.setAttribute("required", "true()");
		} else {
			if (getLengthConstraint() != null) {
				bindElement.setAttribute("required", "not (string-length(.) = 0 or ("
						+ getLengthConstraint() + "))");
			}
		}
		if (isReadOnly()) {
			bindElement.setAttribute("readonly", "true()");
		}
		if (isHidden()) {
			bindElement.setAttribute("relevant", "false()");
		}

		for (Element linkedElement : linkedElements) {
			linkedElement.setAttribute("bind", bindId);
		}

		return bindElement;
	}

	/**
	 * Gets the nodeset.
	 * 
	 * @return the nodeset
	 */
	public String getNodeset() {
		return nodeset;
	}

	/**
	 * Sets the constraint.
	 * 
	 * @param constraint
	 *            the new constraint
	 */
	public void setConstraint(String constraint) {
		if (StringUtils.trimToNull(this.constraint) != null) {
			this.constraint = this.constraint + " and " + constraint;
		} else {
			this.constraint = constraint;
		}
	}

	/**
	 * @return the constraint
	 */
	public String getConstraint() {
		return constraint;
	}

	/**
	 * Sets the nodeset.
	 * 
	 * @param nodeset
	 *            the new nodeset
	 */
	public void setNodeset(String nodeset) {
		this.nodeset = nodeset;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * Sets the required.
	 * 
	 * @param required
	 *            the new required
	 */

	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden
	 *            the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * Checks if is read only.
	 * 
	 * @return true, if is read only
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * Sets the read only.
	 * 
	 * @param readOnly
	 *            the new read only
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(QName type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.green.xforms.generator.forms.ModelElement#hasClone(java.util.List)
	 */
	@Override
	public boolean hasClone(List<ModelElement> allModelElementsClean) {
		for (ModelElement modelElement : allModelElementsClean) {
			if (modelElement == this) {
				return true;
			}
			if (modelElement instanceof ModelElementBindSimple) {
				if (!(modelElement instanceof ModelElementBindHolder)) {
					ModelElementBindSimple modelElementBindSimple = (ModelElementBindSimple) modelElement;
					String nodeset1 = removeIndices(modelElementBindSimple.nodeset);
					String nodeset2 = removeIndices(nodeset);
					if (nodeset1.equals(nodeset2)) {
						for (Element element : linkedElements) {
							modelElementBindSimple.linkedElements.add(element);
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Removes the indices.
	 * 
	 * @param nodeset
	 *            the nodeset
	 * 
	 * @return the string
	 */
	private String removeIndices(String nodeset) {
		String[] split = StringUtils.split(nodeset, "[]");
		if (split.length > 1) {
			StringBuffer result = new StringBuffer("");
			int i = 0;
			for (String string : split) {
				if (i % 2 == 0) {
					result.append(string);
				}
				i++;
			}
			return result.toString();
		}
		return nodeset;
	}

	public ModelElementBindSimple getAnotherMeb() {
		return anotherMeb;
	}

	public void setAnotherMeb(ModelElementBindSimple anotherMeb) {
		this.anotherMeb = anotherMeb;
	}

	/**
	 * Sauvegarde la contrainte (sans parenthèses englobantes) sur la longueur fournie. Ensuite,
	 * appelle setConstraint pour prendre en compte la contrainte longueur dans l'attribut
	 * "constraint" du meb. Si le field associé n'est pas required, la contrainte longueur transmise
	 * à setConstraint est modifiée de façon à autoriser les chaînes vides.
	 * 
	 * @param lengthConstraint
	 *            the constraint with min-length and max-length
	 */
	public void setLengthConstraint(String lengthConstraint) {

		this.lengthConstraint = lengthConstraint;
		if (isRequired()) {
			setConstraint(lengthConstraint);
		} else {
			setConstraint("((string-length(.) = 0) or (" + lengthConstraint + "))");
		}

	}

	/**
	 * @return the lengthConstraint
	 */
	public String getLengthConstraint() {
		return lengthConstraint;
	}

}
