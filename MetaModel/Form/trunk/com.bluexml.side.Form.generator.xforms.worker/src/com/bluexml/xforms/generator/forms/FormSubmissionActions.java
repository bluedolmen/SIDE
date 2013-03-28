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
package com.bluexml.xforms.generator.forms;

import com.bluexml.xforms.messages.MsgId;

/**
 * @author Amenel
 * 
 */
public enum FormSubmissionActions {
		Cancel(MsgId.INT_ACT_CODE_CANCEL, MsgId.CAPTION_BUTTON_CANCEL, true, false),
		Delete(MsgId.INT_ACT_CODE_DELETE, MsgId.CAPTION_BUTTON_DELETE, true, false),
		SetType(MsgId.INT_ACT_CODE_SETTYPE, MsgId.CAPTION_BUTTON_SETTYPE, true, false),
		Search(MsgId.INT_ACT_CODE_SUBMIT, MsgId.CAPTION_BUTTON_SEARCH, true, true),
		Submit(MsgId.INT_ACT_CODE_SUBMIT, MsgId.CAPTION_BUTTON_SUBMIT, true, true),
		Workflow(MsgId.INT_ACT_CODE_SUBMIT, MsgId.CAPTION_BUTTON_SUBMIT, true, true);

	/** the name of the action in the xhtml templates */
	private MsgId name;

	/** the caption for the action's button on the forms */
	private MsgId caption;

	/** whether this button will replace all instances on the form */
	private boolean replaceAll;

	/** whether the form must be valid before the XForms engine triggers the submission */
	private boolean validateFirst;

	private FormSubmissionActions(MsgId name, MsgId caption, boolean replaceAll,
			boolean validateFirst) {
		this.name = name;
		this.caption = caption;
		this.replaceAll = replaceAll;
		this.validateFirst = validateFirst;
	}

	/**
	 * @return the name
	 */
	public MsgId getName() {
		return name;
	}

	/**
	 * @return the caption
	 */
	public MsgId getCaption() {
		return caption;
	}

	/**
	 * @return the replaceAll
	 */
	public boolean isReplaceAll() {
		return replaceAll;
	}

	/**
	 * @return the validateFirst
	 */
	public boolean isValidateFirst() {
		return validateFirst;
	}

}
