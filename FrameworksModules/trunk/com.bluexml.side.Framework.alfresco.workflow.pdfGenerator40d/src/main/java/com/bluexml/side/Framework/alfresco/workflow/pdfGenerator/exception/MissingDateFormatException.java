/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class MissingDateFormatException extends Exception {

	private static final long serialVersionUID = -1108271799798939979L;
	
	public static final String DOES_NOT_EXISTS = "";
	
	public MissingDateFormatException(String message){
		super(message);
	}

}
