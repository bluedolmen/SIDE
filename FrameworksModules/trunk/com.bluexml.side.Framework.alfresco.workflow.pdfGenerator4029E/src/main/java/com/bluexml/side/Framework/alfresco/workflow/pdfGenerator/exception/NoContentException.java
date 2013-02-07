/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class NoContentException extends Exception {

	private static final long serialVersionUID = -8886074333742342931L;
	
	public static final String CONTENT_DOES_NOT_EXISTS = "";
	
	public NoContentException(String message){
		super(message);
	}

}
