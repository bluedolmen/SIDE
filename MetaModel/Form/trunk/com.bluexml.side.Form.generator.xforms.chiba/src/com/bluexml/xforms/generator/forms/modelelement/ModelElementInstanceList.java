package com.bluexml.xforms.generator.forms.modelelement;

import java.util.List;

import org.jdom.Element;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class ModelElementInstanceList. Provides an element of an XForms template's "model" section.
 * The element will trigger the fetching of the initial item set of a selection widget.
 */
public class ModelElementInstanceList extends ModelElement {

	/** The type complete name. */
	private String typeCompleteName;

	/** The instance name. */
	private String instanceName;

	private String formatPattern;

	private String maxLength;

	private String identifier; // #1529

	private String filterAssoc; // #1536

	private boolean isComposition; // #1536

	/**
	 * Constructor specifically for attaching a selection widget to a field.
	 * 
	 * @param overrideType
	 *            the name of the type to search
	 * @param instanceName
	 *            the instance name, for identification of the instance by the XForms engine
	 */
	public ModelElementInstanceList(String overrideType, String instanceName, String formatPattern,
			String maxLength, String identifier) {
		this.typeCompleteName = overrideType;
		this.instanceName = instanceName;
		this.formatPattern = formatPattern;
		this.maxLength = maxLength;
		this.identifier = identifier;
		this.filterAssoc = null;
		this.isComposition = false;
	}

	/**
	 * Initial constructor, for use with associations that target classes defined in model files.
	 * Instantiates a new model element for lists.
	 * 
	 * @param modelClazz
	 *            the type of the objects to fetch in the list
	 * @param instanceName
	 *            the instance name
	 */
	public ModelElementInstanceList(Clazz modelClazz, String instanceName, String formatPattern,
			String maxLength, String filterAssoc, boolean isComposition) {
		this.typeCompleteName = ModelTools.getNamespacePrefix(modelClazz) + ":"
				+ getFormGenerator().getClassQualifiedName(modelClazz);
		this.instanceName = instanceName;
		this.formatPattern = formatPattern;
		this.maxLength = maxLength;
		this.identifier = "";
		this.filterAssoc = filterAssoc;
		this.isComposition = isComposition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#getModelElement()
	 */
	public Element getModelElement() {
		Element instance = XFormsGenerator.createElement("instance",
				XFormsGenerator.NAMESPACE_XFORMS);
		instance.setAttribute("src", MsgId.INT_URI_SCHEME_READER
				+ buildListURI(typeCompleteName, formatPattern, maxLength, identifier, filterAssoc,
						isComposition));
		instance.setAttribute("id", instanceName);
		return instance;
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
