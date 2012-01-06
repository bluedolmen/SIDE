/**
 * 
 */
package com.bluexml.xforms.controller.beans;

/**
 * Bean for passing parameters from the list action to the controller.
 * 
 * @author Amenel
 * 
 */
public class GetInstanceFormBean {
	/** The form id. Also referred to as 'type' in the mapping agent. */
	private String formName;

	/**
	 * The id of the object to load. If <b>null</b>, the form is empty (with the exception of
	 * default values [from in the model] and initial values [from URL parameters]. If non null, the
	 * form is filled with values from the object.
	 */
	private String id;

	/** The comma-separated list of ids in case of mass tagging. */
	private String massIds;

	/** whether the form is read only, which has an influence on the rendering of some fields */
	private boolean readOnly;

	/** Whether a mass tagging operation is being performed*/
	private boolean massTagging;

	/**
	 * @param formName
	 * @param id
	 * @param massIds
	 * @param formIsReadOnly
	 * @param formIsMassTagging
	 */
	public GetInstanceFormBean(String formName, String id, boolean readOnly, boolean massTagging,
			String massIds) {
		this.formName = formName;
		this.id = id;
		this.readOnly = readOnly;
		this.massIds = massIds;
		this.massTagging = massTagging;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the massIds
	 */
	public String getMassIds() {
		return massIds;
	}

	/**
	 * @return the formIsReadOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * @return the formIsMassTagging
	 */
	public boolean isMassTagging() {
		return massTagging;
	}

}
