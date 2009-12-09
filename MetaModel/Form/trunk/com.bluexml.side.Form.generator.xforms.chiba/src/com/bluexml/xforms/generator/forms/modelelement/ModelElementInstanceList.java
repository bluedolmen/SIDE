package com.bluexml.xforms.generator.forms.modelelement;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class ModelElementTypeEnum.
 */
public class ModelElementInstanceList extends ModelElement {

	/** The type complete name. */
	private String typeCompleteName;

	/** The instance name. */
	private String instanceName;

	private String formatPattern;

	private String maxLength;

	/**
	 * 
	 * @param typeEnumCompleteName
	 *            the type enum complete name
	 * @param instanceName
	 *            the instance name
	 */
	public ModelElementInstanceList(String typeEnumCompleteName, String instanceName,
			String formatPattern, String maxLength) {
		this.typeCompleteName = typeEnumCompleteName;
		this.instanceName = instanceName;
		this.formatPattern = formatPattern;
		this.maxLength = maxLength;
	}

	/**
	 * Instantiates a new model element type enum.
	 * 
	 * @param typeEnum
	 *            the type enum
	 * @param instanceName
	 *            the instance name
	 */
	public ModelElementInstanceList(Clazz typeEnum, String instanceName, String formatPattern,
			String maxLength) {
		this.typeCompleteName = ModelTools.getCompleteName(typeEnum);
		this.instanceName = instanceName;
		this.formatPattern = formatPattern;
		this.maxLength = maxLength;
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
				+ MsgId.INT_ACT_CODE_LIST.getText() + "/" + typeCompleteName + "/"
				+ StringUtils.trimToEmpty(formatPattern) + "/" + maxLength);
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
