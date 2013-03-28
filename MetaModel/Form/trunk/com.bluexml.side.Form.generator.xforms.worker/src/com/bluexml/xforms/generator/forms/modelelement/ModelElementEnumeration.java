/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
	@Override
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
