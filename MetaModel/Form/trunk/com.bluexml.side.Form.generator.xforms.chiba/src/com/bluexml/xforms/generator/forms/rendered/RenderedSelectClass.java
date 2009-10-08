package com.bluexml.xforms.generator.forms.rendered;

import java.util.List;

import javax.xml.namespace.QName;

import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderedSelectClass.
 */
public class RenderedSelectClass extends Rendered {

	/** The choices. */
	private Element choices;

	/**
	 * Instantiates a new rendered select class.
	 * 
	 * @param path
	 *            the path
	 * @param classesList
	 *            the classes list
	 */
	public RenderedSelectClass(String path, List<Clazz> classesList) {
		super();
		xformsElement = XFormsGenerator.createElementWithLabel("select1",
				XFormsGenerator.NAMESPACE_XFORMS, "Type");
		choices = XFormsGenerator.createElement("choices", XFormsGenerator.NAMESPACE_XFORMS);

		ModelElementBindSimple meb = new ModelElementBindSimple(path
				+ MsgId.INT_INSTANCE_SIDE_DATATYPE.getText());
		meb.setType(new QName("string"));

		meb.addLinkedElement(xformsElement);
		xformsElement.addContent(choices);

		addModelElement(meb);

		addCases(classesList);
	}

	/**
	 * Adds the cases.
	 * 
	 * @param classesList
	 *            the classes list
	 */
	public void addCases(List<Clazz> classesList) {
		for (Clazz classe : classesList) {
			Element classItem = XFormsGenerator.createElement("item",
					XFormsGenerator.NAMESPACE_XFORMS);

			Element label = XFormsGenerator.createElementWithContent("label",
					XFormsGenerator.NAMESPACE_XFORMS, ModelTools.getTitle(classe));
			classItem.addContent(label);

			Element value = XFormsGenerator.createElementWithContent("value",
					XFormsGenerator.NAMESPACE_XFORMS, ModelTools.getCompleteName(classe));
			classItem.addContent(value);

			choices.addContent(classItem);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return false;
	}

}
