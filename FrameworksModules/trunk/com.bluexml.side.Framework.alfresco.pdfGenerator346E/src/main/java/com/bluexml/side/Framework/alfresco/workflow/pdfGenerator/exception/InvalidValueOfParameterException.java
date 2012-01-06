/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class InvalidValueOfParameterException extends Exception {

	private static final long serialVersionUID = -2263390281328107327L;
	
	public static final String BAD_FORMAT = "";
	
	public InvalidValueOfParameterException(String message){
		super(message);
	}

}
