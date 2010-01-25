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
