package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.List;

import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;

/**
 * Configures a selector which is rendered as a 'select' or 'select1' in XForms, which means as a
 * combo box, selection list or group of radiobuttons/checkboxes on an web page.<br/>
 * Used for associations and enumerations (on data forms), fields with allowed values (on workflow
 * forms) and operations (on search forms).
 * <p/>
 * Assumptions: when marked as a workflow enum, the list of allowed values provides the itemset
 * rendered directly in the XHTML template. Otherwise, the itemset is provided via a URI whose
 * actual writing is given by the enumeration's name (if applicable) or by the operator type.
 */
public class SelectBean {

	/** The model element bind simple. */
	private ModelElementBindSimple modelElementBindSimple;

	/** The label, normally provided by the field (on forms) or the attribute. */
	private String label;

	/** The enumeration. */
	private Enumeration enumeration;

	/** Whether multiple values can be selected in the UI control. */
	private boolean multiple;

	private boolean limited;

	private String enumContext;

	private String enumParent;

	/** Whether this is an enum with allowed values. If so, the itemset is embedded in the template. */
	private boolean isWorkflowEnum;

	/** A list of allowed values used for workflow fields. */
	private List<String> allowedValues;

	/** The type of the search field */
	private String operatorType;

	/** For choosing the UI rendering, combobox, list or radiobuttons/checkboxes */
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
	 * @param enumContext
	 *            the enumContext to set
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
	 * @param enumParent
	 *            the enumParent to set
	 */
	public void setEnumParent(String enumParent) {
		this.enumParent = enumParent;
	}

	/**
	 * @param isWorkflowEnum
	 *            the isWorkflowEnum to set
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
	 * @param allowedValues
	 *            the allowedValues to set
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

	/**
	 * @return the operatorType
	 */
	public String getOperatorType() {
		return operatorType;
	}

	/**
	 * @param operatorType
	 *            the operatorType to set
	 */
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

}
