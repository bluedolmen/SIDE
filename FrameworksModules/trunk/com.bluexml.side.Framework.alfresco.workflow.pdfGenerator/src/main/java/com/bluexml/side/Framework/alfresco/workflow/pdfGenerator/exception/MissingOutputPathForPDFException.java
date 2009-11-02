/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class MissingOutputPathForPDFException extends Exception {

	private static final long serialVersionUID = -4565893599456315019L;
	
	public static final String DOES_NOT_EXISTS = "";
	
	public MissingOutputPathForPDFException(String message){
		super(message);
	}

}
