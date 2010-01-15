/**
 * 
 */
package com.bluexml.xforms.actions;

import com.bluexml.xforms.messages.MsgId;

/**
 * ExecuteAction. <br/>
 * Provides support for user defined actions through action buttons. The action must be implemented
 * as a Java class with signature:
 * 
 * <pre>
 * java.lang.String run(org.w3c.dom.Node.class, java.lang.String)
 * </pre>
 * 
 * . <br/>
 * 
 * @author Amenel
 * 
 */
public class ExecuteAction extends AbstractWriteAction {

	private static final String CLASS_NAME = "classname";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getActionName()
	 */
	@Override
	public String getActionName() {

		return MsgId.INT_ACT_CODE_EXECUTE.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#getParamNames()
	 */
	@Override
	protected String[] getParamNames() {
		return new String[] { CLASS_NAME };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.actions.AbstractAction#submit()
	 */
	@Override
	public void submit() throws Exception {
		try {
			// load the class
			String className = requestParameters.get(CLASS_NAME);
			callExternalAction(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
