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
package com.bluexml.xforms.hook.actions;

import javax.servlet.ServletException;

import com.bluexml.xforms.actions.AbstractWriteAction;

public abstract class AbstractTransactionalAction extends AbstractWriteAction {

	boolean isSearching = false; // #1465

	protected abstract void prepareSubmit() throws ServletException;

	protected abstract void afterSubmit() throws ServletException;

	@Override
	public void submit() throws ServletException {

		if (controller.isInStandaloneMode()) {
			String msg = "The Alfresco Controller has no write capability.";
			navigationPath.setStatusMsg(msg);
			throw new ServletException(msg);
		}

	}

	/**
	 * @return the isSearching
	 */
	public boolean isSearching() {
		return isSearching;
	}

	/**
	 * @param isSearching the isSearching to set
	 */
	public void setSearching(boolean isSearching) {
		this.isSearching = isSearching;
	}

}
