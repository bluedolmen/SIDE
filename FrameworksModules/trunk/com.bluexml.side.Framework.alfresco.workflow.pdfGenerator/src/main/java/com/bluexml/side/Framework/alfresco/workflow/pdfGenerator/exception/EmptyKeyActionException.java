/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author GMA
 *
 */
public class EmptyKeyActionException extends Exception {

	private static final long serialVersionUID = -8271565376227201469L;
	
	public static final String EMPTY_KEY = "";
	
	public EmptyKeyActionException(String message) {
		super(message);
	}


}
