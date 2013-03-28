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
package com.bluexml.xforms.controller;

import org.chiba.xml.xforms.connector.AbstractConnector;
import org.chiba.xml.xforms.connector.URIResolver;
import org.chiba.xml.xforms.exception.XFormsException;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.navigation.NavigationManager;

/**
 * The Class CustomURIResolver.<br />
 * Retrieve XForms instance thanks to a custom uri
 */
public class CustomURIResolver extends AbstractConnector implements URIResolver {

	/* (non-Javadoc)
	 * @see org.chiba.xml.xforms.connector.URIResolver#resolve()
	 */
	public Object resolve() throws XFormsException {
		Node document = null;
		try {
			document = NavigationManager.getInstance().xformsResolve(getURI());
		} catch (Exception e) {
			throw new XFormsException(e);
		}
		return document;
	}
}
