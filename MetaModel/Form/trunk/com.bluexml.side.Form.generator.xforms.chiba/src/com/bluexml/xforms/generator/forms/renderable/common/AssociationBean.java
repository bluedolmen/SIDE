package com.bluexml.xforms.generator.forms.renderable.common;


import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.Renderable;

/**
 * The Class AssociationBean.
 */
public class AssociationBean {

	/**
	 * The Enum AssociationType.
	 */
	public enum AssociationType {

			/** The clazz. */
			clazz,

			wkflwProcess,
			wkflwInstance,

			/** The enume. */
			enume;
	}

	/** The association type. */
	private AssociationType associationType;

	/** The destination. */
	private Clazz destinationClass = null;

	/** The destination enumeration. */
	private SelectBean destinationSelect = null;

	/** The name. */
	private String name;

	/** The label displayed as name of the form control. */
	private String title;

	/** Hint/help text for the form control */
	private String hint;

	/** The destination renderable. */
	private Renderable destinationRenderable;

	/** The create edit form. */
	private String createEditForm = null;

	/** Display status of action buttons. */
	private boolean showingActions;

	private boolean disabled;

	private boolean forWorkflow;

	/** The default number of elements to display. */
	private String fieldSize;

	/** The higher bound. */
	private int hiBound;

	/** The lower bound. */
	private int loBound;

	/** Whether at least one element is required. */
	private boolean mandatory;
	
	/** The format of labels displayed for the association items.*/
	private String formatPattern;

	/** The length at which to truncate labels.*/
	private String labelLength;

	public AssociationBean() {
		super();
		fieldSize = "0";
		formatPattern = "";
		labelLength = "0";
		forWorkflow = false;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the destination renderable.
	 * 
	 * @return the destination renderable
	 */
	public Renderable getDestinationRenderable() {
		return destinationRenderable;
	}

	/**
	 * Sets the destination renderable.
	 * 
	 * @param destinationRenderable
	 *            the new destination renderable
	 */
	public void setDestinationRenderable(Renderable destinationRenderable) {
		this.destinationRenderable = destinationRenderable;
	}

	/**
	 * Gets the creates the edit form.
	 * 
	 * @return the creates the edit form
	 */
	public String getCreateEditForm() {
		return createEditForm;
	}

	/**
	 * Sets the creates the edit form.
	 * 
	 * @param createEditForm
	 *            the new creates the edit form
	 */
	public void setCreateEditForm(String createEditForm) {
		this.createEditForm = createEditForm;
	}

	/**
	 * Sets the showing actions.
	 * 
	 * @param showingActions
	 *            the new showing actions
	 */
	public void setShowingActions(boolean showingActions) {
		this.showingActions = showingActions;
	}

	/**
	 * Checks if is showing actions.
	 * 
	 * @return true, if is showing actions
	 */
	public boolean isShowingActions() {
		return showingActions;
	}

	/**
	 * Gets the destination class.
	 * 
	 * @return the destination class
	 */
	public Clazz getDestinationClass() {
		return destinationClass;
	}

	public int getHiBound() {
		return hiBound;
	}

	public void setHiBound(int bound) {
		this.hiBound = bound;
	}

	/**
	 * Sets the destination class.
	 * 
	 * @param destinationClass
	 *            the new destination class
	 */
	public void setDestinationClass(Clazz destinationClass) {
		this.destinationClass = destinationClass;
		this.associationType = AssociationType.clazz;
	}

	public SelectBean getDestinationSelect() {
		return destinationSelect;
	}

	public void setDestinationSelect(SelectBean destinationSelect) {
		this.destinationSelect = destinationSelect;
		this.associationType = AssociationType.enume;
	}

	public void setDestinationProcessSelect(SelectBean destinationSelect) {
		this.destinationSelect = destinationSelect;
		this.associationType = AssociationType.wkflwProcess;
	}

	public void setDestinationInstanceSelect(SelectBean destinationSelect) {
		this.destinationSelect = destinationSelect;
		this.associationType = AssociationType.wkflwInstance;
	}

	/**
	 * Gets the association type.
	 * 
	 * @return the association type
	 */
	public AssociationType getAssociationType() {
		return associationType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("destination : ");
		sb.append(" - name : ");
		sb.append(name);
		return sb.toString();
	}

	/**
	 * @return the fieldSize
	 */
	public String getFieldSize() {
		return fieldSize;
	}

	/**
	 * Sets the field size to a value different from the <b>default value</b> ( which is 0).
	 * 
	 * @param fieldSize
	 *            the fieldSize to set
	 */
	public void setFieldSize(String fieldSize) {
		this.fieldSize = fieldSize;
	}

	/**
	 * @return the forWorkflow
	 */
	public boolean isForWorkflow() {
		return forWorkflow;
	}

	/**
	 * @param forWorkflow
	 *            the forWorkflow to set
	 */
	public void setForWorkflow(boolean forWorkflow) {
		this.forWorkflow = forWorkflow;
	}

	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the mandatory
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 * @param mandatory
	 *            the mandatory to set
	 */
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the hint
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * @param hint
	 *            the hint to set
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}

	/**
	 * 
	 * @return whether the association is multiple
	 */
	public boolean isMultiple() {
		return (getHiBound() > 1);
	}

	/**
	 * @param loBound
	 *            the loBound to set
	 */
	public void setLoBound(int loBound) {
		this.loBound = loBound;
	}

	/**
	 * @return the loBound
	 */
	public int getLoBound() {
		return loBound;
	}

	/**
	 * @param formatPattern the formatPattern to set
	 */
	public void setFormatPattern(String formatPattern) {
		this.formatPattern = formatPattern;
	}

	/**
	 * @return the formatPattern
	 */
	public String getFormatPattern() {
		return formatPattern;
	}

	/**
	 * @param labelLength the labelLength to set
	 */
	public void setLabelLength(String labelLength) {
		this.labelLength = labelLength;
	}

	/**
	 * @return the labelLength
	 */
	public String getLabelLength() {
		return labelLength;
	}

}
