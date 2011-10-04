/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception;

/**
 * @author GMA
 *
 */
public class NoPdfFileException extends Exception {

	private static final long serialVersionUID = 2740531488459022130L;
	
	public static final String FILE_DOES_NOT_EXISTS = "";

	public NoPdfFileException(String message){
		super(message);
	}
	
}
