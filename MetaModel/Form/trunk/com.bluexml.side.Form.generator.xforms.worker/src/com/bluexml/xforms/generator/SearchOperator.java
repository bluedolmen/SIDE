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
package com.bluexml.xforms.generator;

import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The list of search operators defined in the Form metamodel. All operators are in a single list
 * (instead of one list per type) because type-awareness is useless to the generator... for now.
 * 
 * @author Amenel
 * 
 */
public enum SearchOperator {
		// Operators are listed in the same order as in the Form metamodel
		// Char
		contains(MsgId.MSG_SEARCH_OPERATOR_CONTAINS),
		icontains(MsgId.MSG_SEARCH_OPERATOR_ICONTAINS),
		startsWith(MsgId.MSG_SEARCH_OPERATOR_STARTSWITH),
		istartsWith(MsgId.MSG_SEARCH_OPERATOR_ISTARTSWITH),
		endsWith(MsgId.MSG_SEARCH_OPERATOR_ENDSWITH),
		iendsWith(MsgId.MSG_SEARCH_OPERATOR_IENDSWITH),
		empty(MsgId.MSG_SEARCH_OPERATOR_EMPTY),
		ignore(MsgId.MSG_SEARCH_OPERATOR_IGNORE),
		is(MsgId.MSG_SEARCH_OPERATOR_IS),
		// Date
		between(MsgId.MSG_SEARCH_OPERATOR_BETWEEN),
		before(MsgId.MSG_SEARCH_OPERATOR_BEFORE),
		after(MsgId.MSG_SEARCH_OPERATOR_AFTER),
		exactly(MsgId.MSG_SEARCH_OPERATOR_EXACTLY),
		notBetween(MsgId.MSG_SEARCH_OPERATOR_NOTBETWEEN),
		// Numerical
		below(MsgId.MSG_SEARCH_OPERATOR_BELOW),
		above(MsgId.MSG_SEARCH_OPERATOR_ABOVE),
		// Boolean
		isNot(MsgId.MSG_SEARCH_OPERATOR_ISNOT),
		// Choice
		oneOf(MsgId.MSG_SEARCH_OPERATOR_ONEOF),
		none(MsgId.MSG_SEARCH_OPERATOR_NONE),
		allBut(MsgId.MSG_SEARCH_OPERATOR_ALLBUT),
		// File
		fileType(MsgId.MSG_SEARCH_OPERATOR_FILETYPE),
		size(MsgId.MSG_SEARCH_OPERATOR_SIZE),
		contents(MsgId.MSG_SEARCH_OPERATOR_CONTENTS);

	private MsgId labelId;

	private SearchOperator(MsgId labelId) {
		this.labelId = labelId;
	}

	public String getId() {
		return this.toString();
	}

	public String getLabel() {
		return MsgPool.getMsg(labelId);
	}
}
