package com.bluexml.xforms.controller.navigation;

import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;

/**
 * The Class Page. Holds some data about pages served to users. No significant processing is done
 * here.
 */
public class Page {

	/** The form type. */
	private FormTypeEnum formType;

	/** The underlying datatype. */
	private String dataType;

	/** The form name. */
	private String formName;

	/** The data type set. */
	private boolean dataTypeSet;

	/** The data id. */
	private String dataId;

	/** The language for this page, if applicable */
	private String language;

	/** The node. */
	private Document node;

	/** The init params. */
	private Map<String, String> initParams;

	/** The template. */
	private String template;

	private boolean showSubmitButtons;
	private boolean showCancel;
	private boolean showDelete;
	private boolean showValidate;
	
	private boolean wrongCallType;
	
	private String cssUrl;
	
	/**
	 * the definition id of the current workflow if the workflow is not started or the workflow
	 * instance id if the workflow is started
	 */
	private String wkflwProcessId;

	/** the id of the current task for the current workflow */
	private String wkflwInstanceId;

	/**
	 * Instantiates a new page.
	 * 
	 * @param bean
	 *            the page information bean
	 */
	public Page(PageInfoBean bean) {
		super();
		this.formType = bean.formType;
		this.formName = bean.formName;
		this.dataType = bean.dataType;
		this.dataTypeSet = false;
		this.dataId = bean.dataId;
		this.template = bean.templateId;
		if (bean.initParams != null) {
			this.initParams = bean.initParams;
		} else {
			this.initParams = new TreeMap<String, String>();
		}
		this.language = bean.language;
		this.wkflwProcessId = bean.processId;
		this.wkflwInstanceId = bean.instanceId;
		this.showSubmitButtons = bean.showSubmits;
		this.wrongCallType = bean.wrongCallType;
		this.showCancel = bean.showCancel;
		this.showDelete = bean.showDelete;
		this.showValidate = bean.showValidate;
	}

	/**
	 * Gets the form type.
	 * 
	 * @return the form type
	 */
	public FormTypeEnum getFormType() {
		return formType;
	}

	/**
	 * Sets the form type.
	 * 
	 * @param type
	 *            the new form type
	 */
	public void setFormType(FormTypeEnum type) {
		this.formType = type;
	}

	/**
	 * Gets the form name.
	 * 
	 * @return the form name
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * Sets the form name.
	 * 
	 * @param formName
	 *            the new form name
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * Checks if is data type set.
	 * 
	 * @return true, if is data type set
	 */
	public boolean isDataTypeSet() {
		return dataTypeSet;
	}

	/**
	 * Sets the data type set.
	 * 
	 * @param dataTypeSet
	 *            the new data type set
	 */
	public void setDataTypeSet(boolean dataTypeSet) {
		this.dataTypeSet = dataTypeSet;
	}

	/**
	 * Gets the data id.
	 * 
	 * @return the data id
	 */
	public String getDataId() {
		return dataId;
	}

	/**
	 * Sets the data id.
	 * 
	 * @param dataId
	 *            the new data id
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	/**
	 * Gets the template.
	 * 
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * Sets the template.
	 * 
	 * @param template
	 *            the new template
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * Gets the node.
	 * 
	 * @return the node
	 */
	public Document getNode() {
		return node;
	}

	/**
	 * Sets the node.
	 * 
	 * @param node
	 *            the new node
	 */
	public void setNode(Document node) {
		this.node = node;
	}

	/**
	 * Gets the inits the params.
	 * 
	 * @return the inits the params
	 */
	public Map<String, String> getInitParams() {
		return initParams;
	}

	/**
	 * Sets the init params.
	 * 
	 * @param initParams
	 *            the init params
	 */
	public void setInitParams(Map<String, String> initParams) {
		this.initParams = initParams;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the showSubmitButtons
	 */
	public boolean isShowSubmitButtons() {
		return showSubmitButtons;
	}

	/**
	 * @param showSubmitButtons
	 *            the showSubmitButtons to set
	 */
	public void setShowSubmitButtons(boolean showSubmitButtons) {
		this.showSubmitButtons = showSubmitButtons;
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
	 * @return the wkflwProcessId
	 */
	public String getWkflwProcessId() {
		return wkflwProcessId;
	}

	/**
	 * @param wkflwProcessId the wkflwProcessId to set
	 */
	public void setWkflwProcessId(String wkflwProcessId) {
		this.wkflwProcessId = wkflwProcessId;
	}

	/**
	 * @return the wkflwInstanceId
	 */
	public String getWkflwInstanceId() {
		return wkflwInstanceId;
	}

	/**
	 * @param wkflwInstanceId the wkflwInstanceId to set
	 */
	public void setWkflwInstanceId(String wkflwInstanceId) {
		this.wkflwInstanceId = wkflwInstanceId;
	}

	/**
	 * @param wrongCallType the wrongCallType to set
	 */
	public void setWrongCallType(boolean wrongCallType) {
		this.wrongCallType = wrongCallType;
	}

	/**
	 * @return the wrongCallType
	 */
	public boolean isWrongCallType() {
		return wrongCallType;
	}

	/**
	 * @param cSSUrl the cSSUrl to set
	 */
	public void setCssUrl(String cssUrl) {
		this.cssUrl = cssUrl;
	}

	/**
	 * @return the URL to the additional CSS file
	 */
	public String getCssUrl() {
		return cssUrl;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

}
