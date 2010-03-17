/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.extract;

import java.util.HashMap;
import java.util.Set;

import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.AcroFields.Item;

/**
 * @author dchevrier
 *
 */
public class ExtractDataFromPDF {
	
	public static HashMap<String,String> extractData(PdfReader reader){
		HashMap<String,String> data = new HashMap<String, String>();
		AcroFields form = reader.getAcroFields();
		HashMap<String,Item> fields = form.getFields();
		Set<String> fieldsNames = fields.keySet();
		for (String fieldName : fieldsNames) {
			data.put(fieldName, form.getField(fieldName));
		}
		return data;
	}

}
