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

import java.util.Map;
import java.util.TreeMap;

import org.chiba.xml.xforms.connector.AbstractConnector;
import org.chiba.xml.xforms.connector.SubmissionHandler;
import org.chiba.xml.xforms.core.Submission;
import org.chiba.xml.xforms.exception.XFormsException;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.navigation.NavigationManager;

/**
 * The Class CustomSubmissionHandler.<br />
 * Stores XForms node into persistance
 */
public class CustomSubmissionHandler extends AbstractConnector implements
		SubmissionHandler {

	/* (non-Javadoc)
	 * @see org.chiba.xml.xforms.connector.SubmissionHandler#submit(org.chiba.xml.xforms.core.Submission, org.w3c.dom.Node)
	 */
	@SuppressWarnings("unchecked")
	public Map<?, ?> submit(Submission submission, Node instance)
			throws XFormsException {
		String chibaSessionId = null;

		Map<String, ?> tempMap = (Map<String, ?>) getContext().get("chiba.submission.response");
		if (tempMap != null) {
			Object object = tempMap.get("chiba.session.id");
			if (object != null) {
				chibaSessionId = object.toString();
				tempMap.put("chiba.session.id", null);
			}
		}
		Map<String, Object> result = new TreeMap<String, Object>();
		try {
			NavigationManager.getInstance().xformsSubmit(result, getURI(),
					submission, instance, chibaSessionId);
		} catch (Exception ce) {
			throw new XFormsException(ce);
		}
		return result;
	}

}
