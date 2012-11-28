/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class DuplicateInputPdfException extends Exception {
	
	private static final long serialVersionUID = 1450142186853118303L;
	
	public static final String DUPLICATE_INPUT_PDF_KEY = "";
	
	public DuplicateInputPdfException(String message){
		super(message);
	}

}
