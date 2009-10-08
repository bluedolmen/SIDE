package com.bluexml.xforms.generator.forms;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

/**
 * A rendered item.
 */
public abstract class Rendered {

	/** The rendered children. */
	private List<Rendered> children = new ArrayList<Rendered>();

	/** The model elements to be inserted. */
	private List<ModelElement> modelElements = new ArrayList<ModelElement>();

	/** The xforms DOM element. */
	protected Element xformsElement;

	/** The optional data. */
	private String optionnalData;

	/** Does layout has to return to line after this element?. */
	private boolean returnToLine = false;

	/**
	 * Gets the optional data.
	 * 
	 * @return the optional data
	 */
	public String getOptionalData() {
		return optionnalData;
	}

	/**
	 * Sets the optional data.
	 * 
	 * @param optionnalData
	 *            the new optional data
	 */
	public void setOptionalData(String optionnalData) {
		this.optionnalData = optionnalData;
	}

	/**
	 * Adds the rendered item to owned collections.
	 * 
	 * @param rendered
	 *            the rendered
	 * @param renderable
	 *            the renderable
	 */
	public void addRendered(Rendered rendered, Renderable renderable) {
		modelElements.addAll(rendered.modelElements);
		getChildren().add(rendered);
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public List<Rendered> getChildren() {
		return children;
	}

	/**
	 * Gets the model elements.
	 * 
	 * @return the model elements
	 */
	public List<ModelElement> getModelElements() {
		return modelElements;
	}

	/**
	 * Adds the all model element.
	 * 
	 * @param modelElement
	 *            the model element
	 */
	public void addAllModelElement(List<ModelElement> modelElement) {
		for (ModelElement modelElementToAdd : modelElement) {
			addModelElement(modelElementToAdd);
		}
	}

	/**
	 * Adds the model element.
	 * 
	 * @param modelElement
	 *            the model element
	 */
	public void addModelElement(ModelElement modelElement) {
		modelElements.add(modelElement);
	}

	/**
	 * Gets the xforms DOM element.
	 * 
	 * @return the xforms element
	 */
	public Element getXformsElement() {
		return xformsElement;
	}

	/**
	 * Checks if is holder (to not have to create a group for each rendered item).
	 * 
	 * @return true, if is holder
	 */
	public abstract boolean isHolder();

	/**
	 * Render end.
	 * 
	 * @param renderable
	 *            the renderable
	 */
	public void renderEnd(Renderable renderable) {
		// nothing by default
	}

	/**
	 * Sets the xforms element.
	 * 
	 * @param xformsElement
	 *            the new xforms element
	 */
	public void setXformsElement(Element xformsElement) {
		this.xformsElement = xformsElement;
	}

	/**
	 * Checks if is return to line.
	 * 
	 * @return true, if is return to line
	 */
	public boolean isReturnToLine() {
		return returnToLine;
	}

	/**
	 * Sets the return to line.
	 * 
	 * @param returnToLine
	 *            the new return to line
	 */
	public void setReturnToLine(boolean returnToLine) {
		this.returnToLine = returnToLine;
	}

}