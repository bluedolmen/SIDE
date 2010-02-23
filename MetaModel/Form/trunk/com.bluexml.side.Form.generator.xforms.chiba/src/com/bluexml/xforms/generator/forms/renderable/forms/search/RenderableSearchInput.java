/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import java.util.Stack;

import javax.xml.namespace.QName;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchInput;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchSelectInput;
import com.bluexml.xforms.messages.MsgId;

/**
 * An input for widgets of search fields.
 * 
 * @author Amenel
 * 
 */
public class RenderableSearchInput extends Renderable {
	private RenderableSearchField<? extends SearchField> refField;

	/** the tag name for the instance node to receive data from this UI control */
	private String nodeset;

	/** if true, a compact multiple-enabled select is rendered instead of a standard input */
	private boolean isSelect;

	/** if true, sets the bind's type to Date instead of the default String */
	private boolean isDate;

	public RenderableSearchInput(RenderableSearchField<? extends SearchField> field, String nodeset) {
		this.refField = field;
		this.nodeset = nodeset;
		this.isSelect = false;
		this.isDate = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack, boolean)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		ModelElementBindSimple inputBind = new ModelElementBindSimple(path + nodeset);
		if (isDate == false) {
			inputBind.setType(new QName(MsgId.INT_TYPE_XSD_STRING.getText()));
		} else {
			inputBind.setType(new QName(MsgId.INT_TYPE_XSD_DATE.getText()));
		}

		Rendered rendered;
		String fullName = refField.getFormElement().getId() + "_" + nodeset;
		if (isSelect == false) {
			rendered = new RenderedSearchInput(inputBind, fullName);
		} else {
			ModelElement ref = refField.getFormElement().getRef();
			Attribute attribute = (Attribute) getFormGenerator().getRealObject(ref);
			Enumeration valueList = attribute.getValueList();
			rendered = new RenderedSearchSelectInput(inputBind, fullName, valueList);
		}
		rendered.addModelElement(inputBind);
		return rendered;
	}

	/**
	 * @param isDate the isDate to set
	 */
	public void setDate(boolean isDate) {
		this.isDate = isDate;
	}

	/**
	 * @param isSelect
	 */
	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

}
