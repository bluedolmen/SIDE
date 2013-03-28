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
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.extract;

import java.util.HashMap;
import java.util.Set;

import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.AcroFields.Item;
import com.lowagie.text.pdf.PdfReader;

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
