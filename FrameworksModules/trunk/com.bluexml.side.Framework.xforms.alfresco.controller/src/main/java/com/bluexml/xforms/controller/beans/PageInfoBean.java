/**
 * 
 */
package com.bluexml.xforms.controller.beans;

import java.util.HashMap;
import java.util.Map;

import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.Page;

/**
 * Bean for keeping page-specific info.<br/>
 * <b>NOTE:</b> <em>dataTypeSet</em> is the only page info not reported here. Set by default to "false" in
 * the Page constructor, it is checked only for abstract data types in
 * {@link NavigationManager#getXFormsString()}.
 * 
 * @author Amenel
 * 
 */
public class PageInfoBean {

	private String templateId;
	private String dataId;
	private String language;
	private String processId;
	private String instanceId;
	private boolean showSubmits;
	private boolean showCancel;
	private boolean showDelete;
	private boolean showValidate;
	private FormTypeEnum formType;
	private String formName; // e.g. Person_RO
	private String dataType; // e.g. Person
	private Map<String, String> initParams;
	/** true if the form was called to display an object whose type does not match the form */
	private boolean wrongCallType;

	/**
	 * 
	 */
	public PageInfoBean() {
		this.templateId = null;
		this.dataId = null;
		this.language = null;
		this.processId = null;
		this.instanceId = null;
		this.showSubmits = true;
		this.showCancel = true;
		this.showDelete = true;
		this.showValidate = true;
		this.formType = FormTypeEnum.BOTH;
		this.formName = null;
		this.dataType = null;
		this.initParams = null;
		this.wrongCallType = false;
	}

	/**
	 * Create a bean initialized with info from the given page.
	 * 
	 * @param page
	 */
	public PageInfoBean(Page page) {
		this.templateId = page.getTemplate();
		this.dataId = page.getDataId();
		this.language = page.getLanguage();
		this.processId = page.getWkflwProcessId();
		this.instanceId = page.getWkflwInstanceId();
		this.showSubmits = page.isShowSubmitButtons();
		this.showCancel = page.isShowCancel();
		this.showDelete = page.isShowDelete();
		this.showValidate = page.isShowValidate();
		this.formType = page.getFormType();
		this.dataType = page.getDataType();
		this.formName = page.getFormName();
		this.initParams = new HashMap<String, String>(page.getInitParams());
		this.wrongCallType = page.isWrongCallType();
	}

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the dataId
	 */
	public String getDataId() {
		return dataId;
	}

	/**
	 * @param dataId the dataId to set
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return the instanceId
	 */
	public String getInstanceId() {
		return instanceId;
	}

	/**
	 * @param instanceId the instanceId to set
	 */
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * @return the showSubmits
	 */
	public boolean isShowSubmits() {
		return showSubmits;
	}

	/**
	 * @param showSubmits the showSubmits to set
	 */
	public void setShowSubmits(boolean showSubmits) {
		this.showSubmits = showSubmits;
	}

	/**
	 * @return the showCancel
	 */
	public boolean isShowCancel() {
		return showCancel;
	}

	/**
	 * @param showCancel the showCancel to set
	 */
	public void setShowCancel(boolean showCancel) {
		this.showCancel = showCancel;
	}

	/**
	 * @return the showDelete
	 */
	public boolean isShowDelete() {
		return showDelete;
	}

	/**
	 * @param showDelete the showDelete to set
	 */
	public void setShowDelete(boolean showDelete) {
		this.showDelete = showDelete;
	}

	/**
	 * @return the showValidate
	 */
	public boolean isShowValidate() {
		return showValidate;
	}

	/**
	 * @param showValidate the showValidate to set
	 */
	public void setShowValidate(boolean showValidate) {
		this.showValidate = showValidate;
	}

	/**
	 * @return the formType
	 */
	public FormTypeEnum getFormType() {
		return formType;
	}

	/**
	 * @param formType the formType to set
	 */
	public void setFormType(FormTypeEnum formType) {
		this.formType = formType;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the initParams
	 */
	public Map<String, String> getInitParams() {
		return initParams;
	}

	/**
	 * @param initParams the initParams to set
	 */
	public void setInitParams(Map<String, String> initParams) {
		this.initParams = initParams;
	}

	/**
	 * @return the wrongCallType
	 */
	public boolean isWrongCallType() {
		return wrongCallType;
	}

	/**
	 * @param wrongCallType the wrongCallType to set
	 */
	public void setWrongCallType(boolean wrongCallType) {
		this.wrongCallType = wrongCallType;
	}

}
