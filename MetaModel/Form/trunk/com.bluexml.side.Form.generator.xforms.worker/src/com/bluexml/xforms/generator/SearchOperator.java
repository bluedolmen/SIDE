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
