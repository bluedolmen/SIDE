/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class MissingOverridePdfKeyException extends Exception {

	private static final long serialVersionUID = -453002700993087136L;
	
	public static final String DOES_NOT_EXISTS = "";
	
	public MissingOverridePdfKeyException(String message){
		super(message);
	}

}
