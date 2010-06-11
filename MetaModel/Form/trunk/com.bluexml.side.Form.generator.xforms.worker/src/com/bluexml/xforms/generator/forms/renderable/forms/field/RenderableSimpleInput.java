package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableField;

/**
 * The Class RenderableSimpleInput.
 */
public class RenderableSimpleInput<F extends Field> extends RenderableField<F> {

	/** The xsd type. */
	private String xsdType;

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
	public RenderableSimpleInput(XFormsGenerator generationManager, FormElement parent,
			F formElement, String xsdType) {
		super(generationManager, parent, formElement);
		this.xsdType = xsdType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.bluexml.xforms.generator.forms.renderable.common.field.AbstractRenderableField#
	 * getCustomElement(com.bluexml.xforms.generator.forms.Rendered,
	 * com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple, java.lang.String,
	 * java.util.Stack, java.util.Stack)
	 */
	@Override
	protected Element getCustomElement(Rendered rendered, ModelElementBindSimple meb,
			String slabel, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		return getStandardElement(meb, slabel);
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

}
