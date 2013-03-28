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
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.fill;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingDateFormatException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfStamper;

/**
 * @author dchevrier
 *
 */
public class FillDataPDF {

	public static void fillPDF(PdfStamper stamper, HashMap<String, String> exportCommands, HashMap<String, Object> data) throws IOException, DocumentException, MissingDateFormatException {
		AcroFields form = stamper.getAcroFields();
		Set<String> exportCommandsKeys = exportCommands.keySet();
		for (String commandKey : exportCommandsKeys) {
			String value = convertToStringAlfrescoType(commandKey,data.get(commandKey),exportCommands);
			form.setField(commandKey,value);
		}
		stamper.close();
	}

	private static String convertToStringAlfrescoType(String commandKey, Object object, HashMap<String, String> commands) throws MissingDateFormatException {
		String value = null;
		if (object instanceof Boolean){
			if (object.toString().equals(Boolean.TRUE.toString())){
				value = ConstantsLanguage.BOOLEAN_VALUES[0];
			}
			else{
				value = ConstantsLanguage.BOOLEAN_VALUES[1];
			}
		}
		else if (object instanceof Date){
			String format = getFormat(commandKey,object,commands);
			if (format == null){
				//throw new MissingDateFormatException(MissingDateFormatException.DOES_NOT_EXISTS);
				//Default format
				format = "EEE, d MMM yyyy HH:mm:ss";
			}
			SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
			value = simpleFormat.format(object);
		}
		else{
			if (object == null)
				value = "";
			else
				value = object.toString();
		}
		return value;
	}

	private static String getFormat(String commandKey,Object object, HashMap<String, String> commands) {
		String formatValue = null;
		String[] attributeDateNameParameter = commandKey.split(ConstantsLanguage.PARAMETER_SEPARATOR);
		String attributeDateName = null;
		if (attributeDateNameParameter.length == 0){
			attributeDateName = commandKey;
		}
		else{
			attributeDateName = attributeDateNameParameter[attributeDateNameParameter.length-1];
		}
		Set<String> formatKeys = commands.keySet();
		for (String formatKey : formatKeys) {
			if (formatKey.contains(ConstantsLanguage.FORMAT_DATE_INDICATOR) && formatKey.contains(attributeDateName)){
				formatValue = commands.get(formatKey);
			}
		}
		return formatValue;
	}

}
