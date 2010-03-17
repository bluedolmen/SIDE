/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class AttributeContentException extends Exception {

	private static final long serialVersionUID = -8046152522849971117L;
	
	public static final String DOES_NOT_EXISTS = "";
	
	public AttributeContentException(String message){
		super(message);
	}

}
