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

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.messages.MsgId;

public class ModelElementUpdaterEnum extends AbstractModelElementUpdater {

	private AssociationBean associationBean;

	public ModelElementUpdaterEnum(AssociationBean associationBean, String instanceName) {
		super(associationBean.getDestinationSelect().getEnumeration(), instanceName);
		this.associationBean = associationBean;
	}

	// private String getParameters() {
	// SelectBean selectBean = associationBean.getDestinationSelect();
	// StringBuffer sb = new StringBuffer(ModelTools.getCompleteName(selectBean.getEnumeration()));
	// sb.append("/");
	// sb.append(StringUtils.trimToEmpty(selectBean.getEnumParent()));
	// sb.append("/");
	// sb.append(StringUtils.trimToEmpty(selectBean.getEnumContext()));
	// sb.append("/");
	// if (selectBean.isLimited()) {
	// sb.append("1");
	// } else {
	// sb.append("0");
	// }
	// return sb.toString();
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#getModelElement()
	 */
	@Override
	public Element getModelElement() {
		Element submission = XFormsGenerator.createElement("submission",
				XFormsGenerator.NAMESPACE_XFORMS);
		// submission.setAttribute("action", MsgId.INT_URI_SCHEME_WRITER + "enum/" +
		// getParameters());
		SelectBean selectBean = associationBean.getDestinationSelect();
		submission.setAttribute("action", MsgId.INT_URI_SCHEME_WRITER + "enum?"
				+ buildEnumActionUriFragment(selectBean)); // #1637
		submission.setAttribute("replace", "instance");
		submission.setAttribute("instance", instanceName);
		submission.setAttribute("method", "post");

		String submitId = XFormsGenerator.getId("submit");
		for (Element linkedElement : linkedElements) {
			linkedElement.setAttribute("submission", submitId);
		}
		submission.setAttribute("id", submitId);
		submission.setAttribute("ref", "instance('" + instanceName + "')/query");
		return submission;
	}

}
