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
package com.bluexml.xforms.controller.alfresco;

/**
 * The Class ControllerException.
 * @deprecated
 */
@Deprecated
public class AlfrescoControllerException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4549685947309753498L;

	/**
	 * Instantiates a new controller exception.
	 * 
	 * @param e
	 *            the e
	 */
	public AlfrescoControllerException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new alfresco controller exception.
	 * 
	 * @param s
	 *            the s
	 */
	public AlfrescoControllerException(String s) {
		super(s);
	}

}
