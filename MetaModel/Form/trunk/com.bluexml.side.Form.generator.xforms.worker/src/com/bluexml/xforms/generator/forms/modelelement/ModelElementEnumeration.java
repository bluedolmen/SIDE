package com.bluexml.xforms.generator.forms.modelelement;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.messages.MsgId;

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
		// String dataSourceURI = MsgId.INT_URI_SCHEME_READER + "enum/" + getParameters();
		String dataSourceURI = MsgId.INT_URI_SCHEME_READER + "enum?"
				+ buildEnumActionUriFragment(selectBean);

		if (StringUtils.trimToNull(selectBean.getDataSourceUri()) != null) { // #1660
			dataSourceURI = selectBean.getDataSourceUri();
		}

		enumsInstance.setAttribute("src", dataSourceURI);
		enumsInstance.setAttribute("id", enumInstanceName);
		return enumsInstance;
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
