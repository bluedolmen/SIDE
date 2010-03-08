/**
 *
 */
package com.bluexml.xforms.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bluexml.side.form.utils.DOMUtil;
import com.bluexml.xforms.controller.navigation.Page;

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
 * @deprecated as of 2010-02-12: this was moved to {@link GetAction}.
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
		// return MsgId.INT_ACT_CODE_GET_FORM_WKFLW.getText();
		return "dummy";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#resolve()
	 */
	@Override
	public Node resolve() throws ServletException {
		Page currentPage = navigationPath.peekCurrentPage();
		String wkFormName = currentPage.getFormName();
		//
		// get the instance for the task
		Document docWkflw = controller.getWorkflowFormInstance(wkFormName);
		controller.workflowPatchInstance(transaction, wkFormName, docWkflw, currentPage
				.getWkflwInstanceId());
		//
		// get the instance of the attached data form
		Map<String, String> initParams = currentPage.getInitParams();
		String dataId = currentPage.getDataId();
		String dataFormName = controller.getUnderlyingDataFormForWorkflow(wkFormName);
		Document docForm = controller.getInstanceForm(transaction, dataFormName, dataId, initParams, false);
		//
		// we need to nest the data form instance under workflow
		Element wkDocElt = docWkflw.getDocumentElement();
		List<Element> childrenWk = DOMUtil.getAllChildren(wkDocElt);
		Element wkElt = DOMUtil.getOneElementByTagName(childrenWk, wkFormName);

		Element clone;
		List<Element> children = DOMUtil.getAllChildren(docForm.getDocumentElement());
		Element dataElt = DOMUtil.getOneElementByTagName(children, dataFormName);
		clone = (Element) dataElt.cloneNode(true);
		docWkflw.adoptNode(clone);
		wkElt.appendChild(clone);
		//
		// also copy supplementary tags that are added for internal usage (SIDEDataType, etc.)
		for (Element child : children) {
			if (child.getTagName().equals(dataFormName) == false) {
				clone = (Element) child.cloneNode(true);
				docWkflw.adoptNode(clone);
				wkDocElt.appendChild(clone);
			}
		}
		DOMUtil.logXML(docWkflw, false);

		return docWkflw;
	}

}
