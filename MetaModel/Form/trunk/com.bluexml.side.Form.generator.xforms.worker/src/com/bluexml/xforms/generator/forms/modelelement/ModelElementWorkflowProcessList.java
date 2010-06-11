/**
 * 
 */
package com.bluexml.xforms.generator.forms.modelelement;

import java.util.List;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * @author Amenel
 * 
 */
public class ModelElementWorkflowProcessList extends ModelElement {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#getModelElement()
	 */
	public Element getModelElement() {
		Element instance = XFormsGenerator.createElement("instance",
				XFormsGenerator.NAMESPACE_XFORMS);
		// instance.setAttribute("src", MsgId.INT_URI_SCHEME_READER
		// + MsgId.INT_ACT_CODE_WRKFLW_PROCESS_LIST.getText() + "/");
		// instance.setAttribute("id", MsgId.INT_WKFLW_PROCESS_INSTANCE_NAME.getText());
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#hasClone(java.util.List)
	 */
	@Override
	public boolean hasClone(List<ModelElement> allModelElementsClean) {
		// TODO Auto-generated method stub
		return false;
	}

}
