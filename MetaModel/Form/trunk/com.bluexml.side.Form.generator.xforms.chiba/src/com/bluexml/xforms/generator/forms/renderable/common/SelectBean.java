package com.bluexml.xforms.generator.forms.renderable.common;


import java.util.List;

import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;

/**
 * The Class SelectBean.<br>
 * Configures a selector
 */
public class SelectBean {

	/** The model element bind simple. */
	private ModelElementBindSimple modelElementBindSimple;

	/** The label. */
	private String label;

	/** The enumeration. */
	private Enumeration enumeration;

	/** The multiple. */
	private boolean multiple;

	private boolean limited;
	
	private String enumContext;

	private String enumParent;

	private boolean isWorkflowEnum;
	
	private List<String> allowedValues;

	private ChoiceWidgetType widgetType;

	public SelectBean(ModelElementBindSimple meb, String slabel, Enumeration valueList,
			boolean isMultiple) {
		super();
		// mandatory parameters
		setModelElementBindSimple(meb);
		setLabel(slabel);
		setEnumeration(valueList);
		setMultiple(isMultiple);

		// configuration by default
		setLimited(false);
		setWidgetType(ChoiceWidgetType.SHOW_ONE);
		setWorkflowEnum(false);
		setAllowedValues(null);
	}

	/**
	 * Instantiates a new select bean.<br>
	 * No option defined!
	 */
	public SelectBean() {
		super();
	}

	/**
	 * Gets the model element bind simple.
	 * 
	 * @return the model element bind simple
	 */
	public ModelElementBindSimple getModelElementBindSimple() {
		return modelElementBindSimple;
	}

	/**
	 * Sets the model element bind simple.
	 * 
	 * @param modelElementBindSimple
	 *            the new model element bind simple
	 */
	public void setModelElementBindSimple(ModelElementBindSimple modelElementBindSimple) {
		this.modelElementBindSimple = modelElementBindSimple;
	}

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 * 
	 * @param label
	 *            the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the enumeration.
	 * 
	 * @return the enumeration
	 */
	public Enumeration getEnumeration() {
		return enumeration;
	}

	/**
	 * Sets the enumeration.
	 * 
	 * @param enumeration
	 *            the new enumeration
	 */
	public void setEnumeration(Enumeration enumeration) {
		this.enumeration = enumeration;
	}

	/**
	 * Checks if is multiple.
	 * 
	 * @return true, if is multiple
	 */
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * Sets the multiple.
	 * 
	 * @param multiple
	 *            the new multiple
	 */
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public boolean isLimited() {
		return limited;
	}

	public void setLimited(boolean limited) {
		this.limited = limited;
	}

	/**
	 * @param widgetType
	 *            the widgetType to set
	 */
	public void setWidgetType(ChoiceWidgetType widgetType) {
		this.widgetType = widgetType;
	}

	/**
	 * @return the widgetType
	 */
	public ChoiceWidgetType getWidgetType() {
		return widgetType;
	}

	/**
	 * @return the enumContext
	 */
	public String getEnumContext() {
		return enumContext;
	}

	/**
	 * @param enumContext the enumContext to set
	 */
	public void setEnumContext(String enumContext) {
		this.enumContext = enumContext;
	}

	/**
	 * @return the enumParent
	 */
	public String getEnumParent() {
		return enumParent;
	}

	/**
	 * @param enumParent the enumParent to set
	 */
	public void setEnumParent(String enumParent) {
		this.enumParent = enumParent;
	}

	/**
	 * @param isWorkflowEnum the isWorkflowEnum to set
	 */
	public void setWorkflowEnum(boolean isWorkflowEnum) {
		this.isWorkflowEnum = isWorkflowEnum;
	}

	/**
	 * @return the isWorkflowEnum
	 */
	public boolean isWorkflowEnum() {
		return isWorkflowEnum;
	}

	/**
	 * @param allowedValues the allowedValues to set
	 */
	public void setAllowedValues(List<String> allowedValues) {
		this.allowedValues = allowedValues;
	}

	/**
	 * @return the allowedValues
	 */
	public List<String> getAllowedValues() {
		return allowedValues;
	}
}
