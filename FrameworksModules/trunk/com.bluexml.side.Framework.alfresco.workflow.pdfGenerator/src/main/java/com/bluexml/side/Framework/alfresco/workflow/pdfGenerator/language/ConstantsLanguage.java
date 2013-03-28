/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
	
	public static final String INPUT_PDF_NAME = "template.pdf";
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
