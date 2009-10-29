/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class InvalidContentException extends Exception {

	private static final long serialVersionUID = -3369514673341156110L;
	
	public static final String DOES_NOT_EXISTS = "";
	
	public InvalidContentException(String message){
		super(message);
	}

}
