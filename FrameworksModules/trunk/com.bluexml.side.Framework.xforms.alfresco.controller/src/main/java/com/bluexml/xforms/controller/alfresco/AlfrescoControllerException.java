package com.bluexml.xforms.controller.alfresco;

/**
 * The Class ControllerException.
 */
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
