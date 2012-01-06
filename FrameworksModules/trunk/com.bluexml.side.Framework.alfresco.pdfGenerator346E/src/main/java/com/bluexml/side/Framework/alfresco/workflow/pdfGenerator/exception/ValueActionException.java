package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

public class ValueActionException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public static final String BAD_FORMAT = "The value of action parameter is empty or incorrect.";
	
	public ValueActionException (String message){
		super (message);
	}
	
}
