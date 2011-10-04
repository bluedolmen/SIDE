/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class InvalidAssociationException extends Exception {

	private static final long serialVersionUID = -7780455835423121777L;
	
	public static final String DOES_NOT_EXISTS = "";
	
	public InvalidAssociationException(String message){
		super(message);
	}

}
