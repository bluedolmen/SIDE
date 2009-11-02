/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language;

/**
 * @author dchevrier
 *
 */
public class ConstantsLanguage {
	
	public static final String COMMANDS_SEPARATOR = ";";
	public static final String COMMANDS_SEPARATOR_SPACE = "; ";
	public static final String KEY_VALUE_SEPARATOR = "=";
	
	public static final String ACTION_KEY = "parameter.action";
	public static final String[] ACTION_VALUES = {"IMPORT_FILLED_PDF","EXPORT_FILLED_PDF"};
	
	public static final String[] INPUT_PDF_KEYS = {"parameter.pdf.uuid","parameter.pdf.path"};
	public static final String[] OUTPUT_CONTENT_KEYS = {"parameter.output.uuid","parameter.output.path"};
	public static final String OUTPUT_PDF_KEY = "parameter.output.path";
	public static final String[] INPUT_CONTENT_KEYS = {"parameter.input.uuid","parameter.input.path"};
	public static final String OUTPUT_TYPE_CONTENT_KEY = "parameter.output.type";
	public static final String FORCE_OVERRIDE_PDF_KEY = "parameter.output.force";
	public static final String[] FORCE_OVERRIDE_PDF_VALUES = {"true","false"};
	
	public static final String PDF_PARAMETER_PREFIX = "field";
	public static final String NAVIGATION_INDICATOR = "->";
	public static final String CONSTANT_INDICATOR = "'";	
	public static final String DATE_VALUE_INDICATOR = "/";
	public static final String[] BOOLEAN_VALUES = {"Yes","No"};
	public static final String OUTPUT_TYPE_SEPARATOR = ":";
	public static final String FORMAT_DATE_INDICATOR = "format";
	public static final String PARAMETER_SEPARATOR  = "\\.";
	
}
