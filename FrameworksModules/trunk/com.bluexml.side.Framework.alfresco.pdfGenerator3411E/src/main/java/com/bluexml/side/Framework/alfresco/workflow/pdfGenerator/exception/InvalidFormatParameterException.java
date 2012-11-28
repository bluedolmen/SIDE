/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class InvalidFormatParameterException extends Exception {

	private static final long serialVersionUID = -4192326829420869093L;
	
	public final static String BAD_FORMAT = "Probably, missing separator ('.') in date format definition" +
			                                "or invalid attribute date name.";
	
	public InvalidFormatParameterException(String message){
		super(message);
	}

}
