package com.bluexml.xforms.actions;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.navigation.Page;
import com.bluexml.xforms.messages.MsgId;

/**
 * Superclass for actions that are called via <tt>writerscheme://...</tt> URIs. Write actions may
 * replace one instance or all. In practice, at least one instance is replaced. Examples: the action
 * for the Submit button, or the action for editing an item from selection widgets.
 * 
 * @author Amenel
 */
public abstract class AbstractWriteAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#resolve()
	 */
	@Override
	public Node resolve() {
		// This does not get called for write actions.
		return null;
	}

	/**
	 * Builds a redirection URL, adding or not the parameters to the given URL. Adding the
	 * parameters is the default behavior.
	 * 
	 * @param pSubmitURL
	 * @param currentPage
	 * @return the URL, with or without parameters appended
	 */
	protected String buildRedirectionUrlWithParams(String pSubmitURL, Page currentPage) {
		Map<String, String> initParams = currentPage.getInitParams();
		String submitURL = pSubmitURL;
		boolean foundInParams = initParams.containsKey(MsgId.PARAM_SKIP_ADDITIONAL_INFO.getText());
		boolean skipInfo = false;
		if (foundInParams) {
			skipInfo = (StringUtils.equals(initParams.get(MsgId.PARAM_SKIP_ADDITIONAL_INFO
					.getText()), "true"));
		} else { // #1656
			// there may be something specified in the Xtension property of this form
			skipInfo = controller.getXtensionSkipAdditionalInfo(currentPage.getFormName(),
					currentPage.getFormType());
		}
		if (skipInfo == false) {
			String separator = (submitURL.indexOf('?') == -1 ? "?" : "&");
			String idStr = StringUtils.trimToEmpty(currentPage.getDataId());
			submitURL += separator + "id=" + idStr;
			submitURL += "&" + MsgId.PARAM_LEAVING_FORM + "=" + currentPage.getFormName();
		}
		return submitURL;
	}


}
