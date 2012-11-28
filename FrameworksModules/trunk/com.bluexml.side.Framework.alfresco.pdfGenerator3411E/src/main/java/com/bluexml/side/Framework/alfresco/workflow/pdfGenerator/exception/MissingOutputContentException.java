/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class MissingOutputContentException extends Exception {

	private static final long serialVersionUID = -8971991380954492676L;
	
	public static final String MISSING_OUTPUT_CONTENT_KEY = "";
	
	public MissingOutputContentException(String message){
		super(message);
	}

}
