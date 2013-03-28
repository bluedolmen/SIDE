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
package com.bluexml.xforms.controller.beans;

import com.bluexml.xforms.controller.binding.GenericClass;

/**
 * Bean for the result of a request for form persistence.
 * 
 * @author Amenel
 * 
 */
public class PersistFormResultBean {
	String resultStr; // for default forms and FormClass's
	GenericClass resultClass; // for worfklow forms

	/**
	 * 
	 */
	public PersistFormResultBean() {
		super();
		resultStr = null;
		resultClass = null;
	}

	/**
	 * @return the resultStr
	 */
	public String getResultStr() {
		return resultStr;
	}

	/**
	 * @param resultStr
	 *            the resultStr to set
	 */
	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	/**
	 * @return the resultClass
	 */
	public GenericClass getResultClass() {
		return resultClass;
	}

	/**
	 * @param resultClass
	 *            the resultClass to set
	 */
	public void setResultClass(GenericClass resultClass) {
		this.resultClass = resultClass;
	}

}
