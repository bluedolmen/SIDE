/**
 *
 */
package com.bluexml.xforms.actions;

import java.util.List;
import java.util.Map;

import com.bluexml.xforms.controller.binding.WorkflowTaskType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.navigation.FormTypeEnum;
import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.side.form.utils.DOMUtil;

/**
 * Returns the instance for workflow forms, which includes the instance for the data form. <br/>
 * NOTE: the form name is expected in the returned instance (incl. process & task names), and the
 * data form is nested under the form name tag.
 * <p>
 * Format:
 * 
 * <pre>
 * &lt;workflow&gt; &lt;!-- this is the node returned by getDocumentElement() on XForms instances --&gt;
 *   &lt;FORM NAME&gt;
 *     PROPERTIES OF THE WORFKLOW
 *     &lt;DATA FORM &gt;
 *       THE DATA FORM'S PROPERTIES
 *     &lt;/DATA FORM &gt;
 *   &lt;/FORM NAME&gt;
 * &lt;/workflow&gt;
 * </pre>
 * 
 * @author Amenel
 */
public class WorkflowFormGetAction extends AbstractReadAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return MsgId.INT_ACT_CODE_GET_FORM_WKFLW.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#resolve()
	 */
	@Override
	public Node resolve() throws Exception {
		Page currentPage = navigationPath.peekCurrentPage();
		String wkFormName = currentPage.getFormName();
		//
		// get the instance for the task
		Document instance = controller.getWorkflowFormInstance(wkFormName);
		controller.patchWorkflowInstance(transaction, wkFormName, instance, currentPage
				.getWkflwInstanceId());
		//
		// get the instance of the attached data form
		Map<String, String> initParams = currentPage.getInitParams();
		String dataId = currentPage.getDataId();
		WorkflowTaskType taskType = controller.getWorkflowTaskType(currentPage.getFormName());
		String dataFormName = taskType.getDataForm();
		Document docForm = GetAction.provideInstance(controller, transaction, dataFormName,
				initParams, dataId, null, FormTypeEnum.FORM, false);
		//
		// we need to nest the data form instance under workflow
		Element wkDocElt = instance.getDocumentElement();
		List<Element> childrenWk = DOMUtil.getAllChildren(wkDocElt);
		Element wkElt = DOMUtil.getOneElementByTagName(childrenWk, wkFormName);

		Element clone;
		List<Element> children = DOMUtil.getAllChildren(docForm.getDocumentElement());
		Element dataElt = DOMUtil.getOneElementByTagName(children, dataFormName);
		clone = (Element) dataElt.cloneNode(true);
		instance.adoptNode(clone);
		wkElt.appendChild(clone);
		//
		// also copy supplementary tags that are added for internal usage
		for (Element child : children) {
			if (child.getTagName().equals(dataFormName) == false) {
				clone = (Element) child.cloneNode(true);
				instance.adoptNode(clone);
				wkDocElt.appendChild(clone);
			}
		}
		DOMUtil.logXML(instance, false);

		return instance;
	}

}
