package com.bluexml.xforms.generator.forms.modelelement;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.tools.ModelTools;

public abstract class AbstractModelElementUpdater extends ModelElement {

	/** The type complete name. */
	protected String typeCompleteName;

	/** The linked elements. */
	protected List<Element> linkedElements = new ArrayList<Element>();

	/** The instance name. */
	protected String instanceName;

	/**
	 * Instantiates a new model element list updater.
	 * 
	 * @param typeCompleteName
	 *            the type complete name
	 * @param instanceName
	 *            the instance name
	 */
	public AbstractModelElementUpdater(String typeCompleteName, String instanceName) {
		this.typeCompleteName = typeCompleteName;
		this.instanceName = instanceName;
	}

	public AbstractModelElementUpdater(com.bluexml.side.common.ModelElement target,
			String instanceName) {
		this(ModelTools.getCompleteName(target), instanceName);
		if (target instanceof AbstractClass) {
			this.typeCompleteName = ModelTools.getNamespacePrefix(target) + ":"
					+ getFormGenerator().getClassQualifiedName((AbstractClass) target);
		}
	}

	/**
	 * Adds the linked element.
	 * 
	 * @param linkedElement
	 *            the linked element
	 */
	public void addLinkedElement(Element linkedElement) {
		linkedElements.add(linkedElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#hasClone(java.util.List)
	 */
	@Override
	public boolean hasClone(List<ModelElement> allModelElementsClean) {
		return false;
	}

}
