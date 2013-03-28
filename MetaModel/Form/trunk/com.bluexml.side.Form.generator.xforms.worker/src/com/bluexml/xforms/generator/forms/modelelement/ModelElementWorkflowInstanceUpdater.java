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
/**
 * 
 */
package com.bluexml.xforms.generator.forms.modelelement;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * @author Amenel
 * @deprecated
 */
@Deprecated
public class ModelElementWorkflowInstanceUpdater extends AbstractModelElementUpdater {

	public ModelElementWorkflowInstanceUpdater() {
		// super("", MsgId.INT_WKFLW_INSTANCE_INSTANCE_NAME.getText());
		super("", null);
	}

	@Override
	public Element getModelElement() {
		Element submission = XFormsGenerator.createElement("submission",
				XFormsGenerator.NAMESPACE_XFORMS);
		// submission.setAttribute("action", MsgId.INT_URI_SCHEME_WRITER.getText()
		// + MsgId.INT_ACT_CODE_WRKFLW_INSTANCE_LIST + "/");
		submission.setAttribute("replace", "instance");
		submission.setAttribute("instance", instanceName);
		submission.setAttribute("method", "post");

		String submitId = XFormsGenerator.getId("submit");
		for (Element linkedElement : linkedElements) {
			linkedElement.setAttribute("submission", submitId);
		}
		submission.setAttribute("id", submitId);
		return submission;
	}
}
