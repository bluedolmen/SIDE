/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language;

/**
 * @author dchevrier
 *
 */
public class ConstantsLanguage {
	
	public static final String COMMANDS_SEPARATOR = "; ";
	public static final String KEY_VALUE_SEPARATOR = "=";
	
	public static final String ACTION_KEY = "parameter.action";
	public static final String[] ACTION_VALUES = {"IMPORT_FILLED_PDF","EXPORT_FILLED_PDF"};
	
	public static final String[] INPUT_PDF_KEYS = {"parameter.pdf.uuid","parameter.pdf.path"};
	
	public static final String[] OUTPUT_CONTENT_KEYS = {"parameter.output.uuid","parameter.output.path"};

}
