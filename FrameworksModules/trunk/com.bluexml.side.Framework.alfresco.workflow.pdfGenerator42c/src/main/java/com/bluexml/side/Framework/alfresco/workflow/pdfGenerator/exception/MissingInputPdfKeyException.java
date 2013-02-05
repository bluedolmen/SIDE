/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class MissingInputPdfKeyException extends Exception {
	
	public static final String MISSING_INPUT_PDF_KEY = "";
	
	public MissingInputPdfKeyException(String message){
		super(message);
	}

}
