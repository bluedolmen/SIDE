/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class OutputTypeKeyException extends Exception {

	private static final long serialVersionUID = -3954418812376806758L;
	
	public final static String DOES_NOT_EXISTS = "";
	
	public OutputTypeKeyException(String message){
		super(message);
	}

}
