package com.bluexml.xforms.generator.forms.modelelement;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class ModelElementEnumeration.
 */
public class ModelElementEnumeration extends ModelElement {

	/** The enumeration. */
	private SelectBean selectBean;
	private String enumInstanceName;

	/**
	 * Instantiates a new model element enumeration.
	 * 
	 * @param selectBean
	 *            the enumeration
	 * @param enumInstance
	 */
	public ModelElementEnumeration(SelectBean selectBean, String enumInstance) {
		this.selectBean = selectBean;
		this.enumInstanceName = enumInstance;
	}

	/**
	 * Gets the enum instance name.
	 * 
	 * @return the enum instance name
	 */
	public String getEnumInstanceName() {
		return enumInstanceName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#getModelElement()
	 */
	public Element getModelElement() {
		Element enumsInstance = XFormsGenerator.createElement("instance",
				XFormsGenerator.NAMESPACE_XFORMS);
		enumsInstance.setAttribute("src", MsgId.INT_URI_SCHEME_READER + "enum/" + getParameters());
		enumsInstance.setAttribute("id", enumInstanceName);
		return enumsInstance;
	}

	private String getParameters() {
		String enumName;
		if (selectBean.getEnumeration() != null) {
			enumName = ModelTools.getCompleteName(selectBean.getEnumeration());
		} else {
			enumName = selectBean.getOperatorType();
		}
		StringBuffer sb = new StringBuffer(enumName);
		sb.append("/");
		sb.append(StringUtils.trimToEmpty(selectBean.getEnumParent()));
		sb.append("/");
		sb.append(StringUtils.trimToEmpty(selectBean.getEnumContext()));
		sb.append("/");
		if (selectBean.isLimited()) {
			sb.append("1");
		} else {
			sb.append("0");
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#hasClone(java.util.List)
	 */
	@Override
	public boolean hasClone(List<ModelElement> allModelElementsClean) {
		for (ModelElement modelElement : allModelElementsClean) {
			if (modelElement == this) {
				return true;
			}
			if (modelElement instanceof ModelElementEnumeration) {
				ModelElementEnumeration modelElementEnumeration = (ModelElementEnumeration) modelElement;
				if (isClone(modelElementEnumeration)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isClone(ModelElementEnumeration obj) {
		/*
		 * if (!StringUtils.equals(ModelTools.getCompleteName(obj.selectBean .getEnumeration()),
		 * ModelTools.getCompleteName(selectBean .getEnumeration()))) { return false; } else if
		 * (!StringUtils.equals(obj.selectBean.getFilterData(), selectBean.getFilterData())) {
		 * return false; } else if (!StringUtils.equals(obj.selectBean.getFilterParent(),
		 * selectBean.getFilterParent())) { return false; } return true;
		 */
		if (obj.getEnumInstanceName().equals(getEnumInstanceName())) {
			return true;
		}
		return false;
	}

}
