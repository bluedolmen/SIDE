package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableField;

/**
 * The Class RenderableChoiceInput.
 */
public class RenderableChoiceInput extends RenderableField<ChoiceField> {

	/**
	 * Instantiates a new renderable choice input.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param formElement
	 *            the form element
	 */
	public RenderableChoiceInput(XFormsGenerator generationManager, FormElement parent,
			ChoiceField formElement) {
		super(generationManager, parent, formElement);
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
		Enumeration anEnum = null;
		com.bluexml.side.common.ModelElement ref;

		ref = formElement.getRef();
		ref = (com.bluexml.side.common.ModelElement) generationManager.getFormGenerator()
				.getRealObject(ref);
		if (ref instanceof Attribute) {
			Attribute attribute = (Attribute) ref;
			anEnum = attribute.getValueList();
		}

		SelectBean selectBean = new SelectBean();
		selectBean.setEnumeration(anEnum);
		String enumContext = null;
		String enumParent = null;
		if (anEnum.getDynamic()) {
			enumContext = getDynEnumContextString(formElement.getId());
			enumParent = getDynEnumParentString(formElement.getId());
		}
		selectBean.setEnumContext(enumContext);
		selectBean.setEnumParent(enumParent);
		selectBean.setLabel(slabel);
		selectBean.setModelElementBindSimple(meb);
		selectBean.setMultiple(formElement.isMultiple());
		selectBean.setLimited(false);
		selectBean.setWidgetType(formElement.getWidget());
		return getSelectElement(rendered, selectBean);
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
		return "string";
	}

}
