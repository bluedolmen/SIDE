/**
 * 
 */
package com.bluexml.xforms.controller.navigation;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean for keeping page-specific info.<br/>
 * <b>NOTE:</b> dataTypeSet is the only page info not reported here. Set by default to "false" in
 * the Page constructor, it is checked only for abstract data types in
 * {@link NavigationManager#getXFormsString()}.
 * 
 * @author Amenel
 * 
 */
public class PageInfoBean {

	public String templateId;
	public String dataId;
	public String language;
	public String processId;
	public String instanceId;
	public boolean showSubmits;
	public boolean showCancel;
	public boolean showDelete;
	public boolean showValidate;
	public FormTypeEnum formType;
	public String formName;
	public String dataType;
	Map<String, String> initParams;
	/** true if the form was called to display an object whose type does not match the form */
	public boolean wrongCallType;

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
	}

}
