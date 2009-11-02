/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.fill;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfStamper;

/**
 * @author dchevrier
 *
 */
public class FillDataPDF {

	public static void fillPDF(PdfStamper stamper, HashMap<String, String> exportCommands, HashMap<String, Object> data) throws IOException, DocumentException {
		AcroFields form = stamper.getAcroFields();
		Set<String> exportCommandsKeys = exportCommands.keySet();
		for (String commandKey : exportCommandsKeys) {
			String value = convertToStringAlfrescoType(data.get(commandKey));
			form.setField(commandKey,value);
		}
		stamper.close();
	}

	private static String convertToStringAlfrescoType(Object object) {
		String value = null;
		if (object instanceof Boolean){
			if (object.toString().equals(Boolean.TRUE.toString())){
				value = ConstantsLanguage.BOOLEAN_VALUES[0];
			}
			else{
				value = ConstantsLanguage.BOOLEAN_VALUES[1];
			}
		}
		else{
			value = object.toString();
		}
		return value;
	}

}
