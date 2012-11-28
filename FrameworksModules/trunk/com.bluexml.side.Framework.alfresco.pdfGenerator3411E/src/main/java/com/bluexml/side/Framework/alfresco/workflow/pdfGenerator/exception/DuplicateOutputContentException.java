/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;


/**
 * @author dchevrier
 *
 */
public class DuplicateOutputContentException extends Exception {

	private static final long serialVersionUID = 3012424234142221375L;
	
	public static final String DUPLICATE_OUTPUT_CONTENT_KEY = "";
	
	public DuplicateOutputContentException(String message){
		super(message);
	}

}
