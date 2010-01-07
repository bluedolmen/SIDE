package com.bluexml.xforms.generator.forms.rendered;

import java.util.ArrayList;
import java.util.List;

import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class RenderedForm.
 */
public class RenderedForm extends Rendered {

	/** The group form. */
	private Element body;

	/** The xmodel. */
	private Element model;

	/**
	 * Instantiates a new rendered form.
	 * 
	 * @param title
	 *            the title
	 */
	public RenderedForm(String title, boolean isAWorkflowForm, boolean isWrkflwSelectionForm) {
		setRootContainer(this);
		xformsElement = XFormsGenerator.createElement("html", XFormsGenerator.NAMESPACE_XHTML);
		Element head = XFormsGenerator.createElement("head", XFormsGenerator.NAMESPACE_XHTML);

		Element titleElement = XFormsGenerator.createElementWithContent("title",
				XFormsGenerator.NAMESPACE_XHTML, title);
		head.addContent(titleElement);

		model = XFormsGenerator.createElement("model", XFormsGenerator.NAMESPACE_XFORMS);
		model.setAttribute("id", MsgId.INT_GEN_ID_OBJECTMODEL.getText());
		Element modelInstance = XFormsGenerator.createElement("instance",
				XFormsGenerator.NAMESPACE_XFORMS);
		modelInstance.setAttribute("id", "minstance");
		//
		MsgId getAction = MsgId.INT_ACT_CODE_GET_FORM;
		if (isAWorkflowForm) {
			getAction = MsgId.INT_ACT_CODE_GET_FORM_WKFLW;
		} else if (isWrkflwSelectionForm) {
			getAction = MsgId.INT_ACT_CODE_GET_WKFLW_SELECTION;
		}
		modelInstance.setAttribute("src", MsgId.INT_URI_SCHEME_READER + getAction.getText() + "/");
		model.addContent(modelInstance);

		head.addContent(model);

		Element tabContainerScript = XFormsGenerator.createElement("script",
				XFormsGenerator.NAMESPACE_XHTML);
		tabContainerScript.setAttribute("type", "text/javascript");
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append("dojo.require(\"dojo.widget.LayoutContainer\");");
		stringBuffer.append("dojo.require(\"dojo.widget.TabContainer\");");
		stringBuffer.append("dojo.require(\"dojo.widget.ContentPane\");");
		tabContainerScript.setText(stringBuffer.toString());
		head.addContent(tabContainerScript);

		addCSS(head, MsgId.INT_GEN_PLACEHOLDER_CONTEXT_PATH
				+ "/resources/styles/xforms.generated.css");
		addCSS(head, MsgId.INT_GEN_PLACEHOLDER_CONTEXT_PATH + "/resources/styles/custom.css");

		xformsElement.addContent(head);
		body = XFormsGenerator.createElement("body", XFormsGenerator.NAMESPACE_XHTML);
		xformsElement.addContent(body);

	}

	/**
	 * Adds the css.
	 * 
	 * @param head
	 *            the head
	 * @param css
	 *            the css
	 */
	private void addCSS(Element head, String css) {
		Element customCSS = XFormsGenerator.createElement("link", XFormsGenerator.NAMESPACE_XHTML);
		customCSS.setAttribute("rel", "stylesheet");
		customCSS.setAttribute("type", "text/css");
		customCSS.setAttribute("href", css);
		head.addContent(customCSS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#addRendered(com.bluexml.xforms
	 * .generator.forms.Rendered, com.bluexml.xforms.generator.forms.Renderable)
	 */
	@Override
	public void addRendered(Rendered rendered, Renderable renderable) {
		super.addRendered(rendered, renderable);
		Element renderedElement = rendered.getXformsElement();
		if (renderedElement != null) {
			body.addContent(renderedElement);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#renderEnd(com.bluexml.xforms
	 * .generator.forms.Renderable)
	 */
	@Override
	public void renderEnd(Renderable renderable) {
		List<ModelElement> allModelElements = new ArrayList<ModelElement>(getModelElements());
		List<ModelElement> allModelElementsClean = new ArrayList<ModelElement>();
		// filterModelElements(allModelElements);
		List<Element> modelElementsRendered = new ArrayList<Element>();
		for (ModelElement modelElement : allModelElements) {
			if (!modelElement.hasClone(allModelElementsClean)) {
				allModelElementsClean.add(modelElement);
			}
		}
		for (ModelElement modelElement : allModelElementsClean) {
			Element modelEle = modelElement.getModelElement();
			if (modelEle != null) {
				modelElementsRendered.add(modelEle);
				// addModelRendered(modelElementsRendered, modelEle,
				// modelElement);
			}
		}

		for (Element element : modelElementsRendered) {
			model.addContent(element);
		}
		setRootContainer(null);
}

}
