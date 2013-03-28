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
package com.bluexml.xforms.actions;

import org.w3c.dom.Document;

import com.bluexml.xforms.messages.MsgId;

public abstract class AbstractCreateAction extends AbstractWriteAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] {
				MsgId.INT_ACT_PARAM_ANY_ASSOC.getText(),
				MsgId.INT_ACT_PARAM_ANY_DATATYPE.getText(),
				MsgId.INT_ACT_PARAM_ANY_HINT.getText() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#executeSubmit()
	 */
	@Override
	public void submit() {
		// save status
		navigationPath.peekCurrentPage().setDataId(null);
		navigationPath.peekCurrentPage().setNode((Document) node);
		navigationPath.clearStatusMsg();
		// create new page
		create();
		setSubmissionDefaultLocation(getServletURL(), result);
	}

	protected abstract void create();

}
