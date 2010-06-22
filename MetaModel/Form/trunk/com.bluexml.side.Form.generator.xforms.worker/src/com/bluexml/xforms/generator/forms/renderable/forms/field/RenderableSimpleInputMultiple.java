package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import javax.xml.namespace.QName;

import org.jdom.Element;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableField;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The widget for standard text inputs that have the 'multiple' property set to 'true'.
 * 
 * @author Amenel
 */
public class RenderableSimpleInputMultiple<F extends Field> extends RenderableField<F> {

	/** The xsd type. */
	private String xsdType;
	private String currentPath;
	private String fieldId = null;

	ModelElementBindSimple inputBind = null;
	ModelElementBindSimple storeBind = null;
	ModelElementBindHolder repeatBind = null;

	/**
	 * Instantiates a new renderable simple input.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param formElement
	 *            the form element
	 * @param xsdType
	 *            the xsd type
	 */
	public RenderableSimpleInputMultiple(XFormsGenerator generationManager, FormElement parent,
			F formElement, String xsdType) {
		super(generationManager, parent, formElement);

		this.xsdType = xsdType;
		add(new RenderableSimpleInputMultipleActions(this));
		add(new RenderableSimpleInputMultipleOutput(this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField#
	 * getCustomElement(com.bluexml.xforms.generator.forms.Rendered,
	 * com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple, java.lang.String,
	 * java.util.Stack, java.util.Stack)
	 */
	@Override
	protected Element getCustomElement(Rendered rendered, ModelElementBindSimple meb,
			String slabel, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField#getXsdType
	 * ()
	 */
	@Override
	protected String getXsdType() {
		return xsdType;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField#render(java.lang.String, java.util.Stack, java.util.Stack, boolean)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {

		currentPath = path + "/";

		Rendered rendered = new RenderedXMLElement();
		Element rootElt = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		rootElt.setAttribute("class", MsgId.INT_CSS_INPUT_MULTIPLE.getText());

		String slabel = MsgPool.getMsg(MsgId.MSG_FIELD_LABEL_FORMAT, getTitle());
		Element inputElt = XFormsGenerator.createElementWithLabel("input",
				XFormsGenerator.NAMESPACE_XFORMS, slabel);
		inputElt.setAttribute("id", getFieldId());
		inputElt.setAttribute("class", "xformstdleft");
		
		ModelElementBindSimple mebInput = getInputBind();
		ModelElementBindHolder mebRepeat = getRepeatBind();
		ModelElementBindSimple mebStore = getStoreBind();

		mebInput.setMultipleInputReference(mebStore.getNodeset());

		mebInput.addLinkedElement(inputElt);
		rootElt.addContent(inputElt);

		rendered.setXformsElement(rootElt);

		rendered.addModelElement(mebInput);
		rendered.addModelElement(mebRepeat);
		rendered.addModelElement(mebStore);

		// we are overriding the rendering defined in a parent class so these are necessary
		applyConstraints(mebInput); // must come before addHintAndMessages
		addHintAndMessages(inputElt);
		applyStyle(rendered, formElement.getStyle());

		return rendered;
	}

	private void initBinds() {
		inputBind = new ModelElementBindSimple(currentPath + MsgId.INT_INSTANCE_INPUT_MULT_INPUT);
		inputBind.setType(new QName(getXsdType()));
		storeBind = new ModelElementBindSimple(currentPath + MsgId.INT_INSTANCE_INPUT_MULT_ITEM);
		repeatBind = new ModelElementBindHolder(currentPath + MsgId.INT_INSTANCE_INPUT_MULT_ITEM
				+ "[position()!=last()]");
		ModelElementBindSimple itemBind;
		itemBind = new ModelElementBindSimple(MsgId.INT_INSTANCE_INPUT_MULT_VALUE.getText());
		repeatBind.addSubBind(itemBind);
		if (formElement.isMandatory()) {
			inputBind.setMultipleInputReference(storeBind.getNodeset());
		}
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Renderable#renderEnd(com.bluexml.xforms.generator.forms.Rendered)
	 */
	@Override
	public void renderEnd(Rendered rendered) {
		inputBind = null;
		storeBind = null;
		Element root = rendered.getXformsElement();
		Element clearElt = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		clearElt.setAttribute("class", "xformstdclear");
		root.addContent(clearElt);
	}

	/**
	 * @return the inputBind
	 */
	public ModelElementBindSimple getInputBind() {
		if (inputBind == null) {
			initBinds();
		}
		return inputBind;
	}

	/**
	 * @return the storeBind
	 */
	public ModelElementBindSimple getStoreBind() {
		if (storeBind == null) {
			initBinds();
		}
		return storeBind;
	}

	/**
	 * @return the repeatBind
	 */
	public ModelElementBindHolder getRepeatBind() {
		if (repeatBind == null) {
			initBinds();
		}
		return repeatBind;
	}

	public String getFieldId() {
		if (fieldId == null) {
			fieldId = XFormsGenerator.getId(getOwner() + "_" + getName());
		}
		return fieldId;
	}

	public String getRepeaterId() {
		return getFieldId() + "Repeater";
	}

}
