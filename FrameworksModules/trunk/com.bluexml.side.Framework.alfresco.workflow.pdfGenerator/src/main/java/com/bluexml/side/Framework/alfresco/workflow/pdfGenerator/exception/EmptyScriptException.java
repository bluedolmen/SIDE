/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author dchevrier
 *
 */
public class EmptyScriptException extends Exception {
	
	private static final long serialVersionUID = 1238236636757992333L;
	
	public static final String EMPTY_SCRIPT = "";
	
	public EmptyScriptException(String message){
		super(message);
	}

}
