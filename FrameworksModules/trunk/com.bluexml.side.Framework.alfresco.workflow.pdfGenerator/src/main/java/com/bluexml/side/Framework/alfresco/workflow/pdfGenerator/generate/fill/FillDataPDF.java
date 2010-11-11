/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.generate.fill;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.exception.MissingDateFormatException;
import com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language.ConstantsLanguage;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfAnnotation;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.TextField;

/**
 * @author dchevrier
 * 
 */
public class FillDataPDF {

	public static void fillPDF(PdfReader reader, PdfStamper stamper, HashMap<String, String> exportCommands,
			HashMap<String, Object> data) throws IOException, DocumentException, MissingDateFormatException {
		AcroFields form = stamper.getAcroFields();
		Set<String> exportCommandsKeys = exportCommands.keySet();
		Map<String, List<Object>> fieldsGroups = new HashMap<String, List<Object>>();
		for (String commandKey : exportCommandsKeys) {
			Object dataValue = data.get(commandKey);
			if (dataValue instanceof ArrayList<?> && ((List<Object>) dataValue).size() > 1) {
				fieldsGroups.put(commandKey, (List<Object>) dataValue);
			} else {
				String value = convertToStringAlfrescoType(commandKey, dataValue, exportCommands);
				form.setField(commandKey, value);
			}
		}
		if (fieldsGroups.size() > 0) {
			repeatFillFields(fieldsGroups, reader, stamper, exportCommands, form);
		}
		stamper.close();
	}

	private static void repeatFillFields(Map<String, List<Object>> fieldsGroups, PdfReader reader,
			PdfStamper stamper, HashMap<String, String> exportCommands, AcroFields form) throws IOException,
			DocumentException, MissingDateFormatException {
		List<Map<String, List<Object>>> listOfFieldsGroup = getListOfFieldsGroups(fieldsGroups, reader);
		for (Map<String, List<Object>> group : listOfFieldsGroup) {
			duplicateGroup(group, reader, stamper, form);
		}
	}

	private static List<Map<String, List<Object>>> getListOfFieldsGroups(
			Map<String, List<Object>> fieldsGroups, PdfReader reader) {
		List<Map<String, List<Object>>> listOfGroups = new ArrayList<Map<String, List<Object>>>();

		List<String> usedCommandKeys = new ArrayList<String>();
		Set<String> testCommandKeys = fieldsGroups.keySet();
		while (usedCommandKeys.size() < testCommandKeys.size()) {
			Map<String, List<Object>> usedFields = new HashMap<String, List<Object>>();
			for (String testKey : testCommandKeys) {
				if (!usedCommandKeys.contains(testKey)) {
					usedCommandKeys.add(testKey);
					usedFields.put(testKey, fieldsGroups.get(testKey));
				}
				String identTestKey = testKey.replaceAll("[1-9]", "");
				Iterator<String> currentKeyIt = testCommandKeys.iterator();
				while (currentKeyIt.hasNext()) {
					String currentKey = currentKeyIt.next();
					String identCurrentKey = currentKey.replaceAll("[1-9]", "");
					if (identCurrentKey.equalsIgnoreCase(identTestKey)
							&& !usedCommandKeys.contains(currentKey)) {
						usedCommandKeys.add(currentKey);
						usedFields.put(currentKey, fieldsGroups.get(currentKey));
					}
				}
			}
			testCommandKeys.removeAll(usedCommandKeys);
			listOfGroups.add(usedFields);
		}

		return listOfGroups;
	}

	private static void duplicateGroup(Map<String, List<Object>> group, PdfReader reader,
			PdfStamper stamper, AcroFields form) throws IOException, DocumentException {
		Map<String, Object> duplicateGroups = new HashMap<String, Object>();

		// All dataValue have same size; we pick up just one.
		Iterator<Entry<String, List<Object>>> itEntries = group.entrySet().iterator();
		Entry<String, List<Object>> anEntry = itEntries.next();
		int dataSize = group.get(anEntry.getKey()).size();
		int indexData = 0;
		String ident = "";
		while (indexData < dataSize) {
			Iterator<Entry<String, List<Object>>> itTreatEntries = group.entrySet().iterator();
			while (itTreatEntries.hasNext()) {
				Entry<String, List<Object>> entry = itTreatEntries.next();
				if (form.getFieldItem(entry.getKey() + ident) != null) {
					form.setField(entry.getKey() + ident, (String) entry.getValue().get(indexData));
				} else {
					duplicateGroups.put(entry.getKey() + ident, entry.getValue().get(indexData));
				}
			}
			if(indexData > 0){
				ident = Integer.valueOf(indexData - 1).toString();
				duplicateGroupInPDF(reader, stamper, form, ident, group.keySet(), duplicateGroups);
			}
			ident = Integer.valueOf(indexData).toString();
			indexData++;
		}
	}

	private static void duplicateGroupInPDF(PdfReader reader, PdfStamper stamper, AcroFields form,
			String ident, Set<String> fieldsNames, Map<String, Object> fieldsValues)
			throws IOException, DocumentException {
		String firstField = getFirstFieldInPdf(fieldsNames, form);
		String lastField = getLastFieldInPdf(fieldsNames, form);
		float formYSize = getFormSize(form, firstField, lastField);

		for (String fieldName : fieldsNames) {
			float[] positions = form.getFieldPositions(fieldName);
			Rectangle place = new Rectangle(positions[1], positions[2] + formYSize, positions[3],
					positions[4] + formYSize);
			TextField newField = new TextField(stamper.getWriter(), place, fieldName + ident);
			PdfFormField newTextField = newField.getTextField();
			newTextField.setValueAsString((String) fieldsValues.get(fieldName + ident));
			stamper.addAnnotation(newTextField, 1);
		}
	}

	private static float getFormSize(AcroFields form, String firstField, String lastField) {
		float down = form.getFieldPositions(lastField)[2];
		float up = form.getFieldPositions(firstField)[4];
		return down - up - 50;
	}

	private static String getFirstFieldInPdf(Set<String> fieldsNames, AcroFields form) {
		float llmax = 0;
		String lastFieldname = "";
		for (String name : fieldsNames) {
			float[] fieldPositions = form.getFieldPositions(name);
			float llcurrent = fieldPositions[2];
			if (llcurrent >= llmax) {
				llmax = llcurrent;
				lastFieldname = name;
			}
		}
		return lastFieldname;
	}

	private static String getLastFieldInPdf(Set<String> fieldsNames, AcroFields form) {
		float urmin = 100000; // Arbitrary high value to initialize test.
		String firstFieldName = "";
		for (String name : fieldsNames) {
			float[] fieldPositions = form.getFieldPositions(name);
			float urcurrent = fieldPositions[4];
			if (urcurrent <= urmin) {
				urmin = urcurrent;
				firstFieldName = name;
			}
		}
		return firstFieldName;
	}

	private static String convertToStringAlfrescoType(String commandKey, Object object,
			HashMap<String, String> commands) throws MissingDateFormatException {
		String value = null;
		if (object instanceof Boolean) {
			if (object.toString().equals(Boolean.TRUE.toString())) {
				value = ConstantsLanguage.BOOLEAN_VALUES[0];
			} else {
				value = ConstantsLanguage.BOOLEAN_VALUES[1];
			}
		} else if (object instanceof Date) {
			String format = getFormat(commandKey, object, commands);
			if (format == null) {
				// throw new
				// MissingDateFormatException(MissingDateFormatException.DOES_NOT_EXISTS);
				// Default format
				format = "EEE, d MMM yyyy HH:mm:ss";
			}
			SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
			value = simpleFormat.format(object);
		} else {
			if (object == null)
				value = "";
			else
				value = object.toString();
		}
		return value;
	}

	private static String getFormat(String commandKey, Object object, HashMap<String, String> commands) {
		String formatValue = null;
		String[] attributeDateNameParameter = commandKey.split(ConstantsLanguage.PARAMETER_SEPARATOR);
		String attributeDateName = null;
		if (attributeDateNameParameter.length == 0) {
			attributeDateName = commandKey;
		} else {
			attributeDateName = attributeDateNameParameter[attributeDateNameParameter.length - 1];
		}
		Set<String> formatKeys = commands.keySet();
		for (String formatKey : formatKeys) {
			if (formatKey.contains(ConstantsLanguage.FORMAT_DATE_INDICATOR)
					&& formatKey.contains(attributeDateName)) {
				formatValue = commands.get(formatKey);
			}
		}
		return formatValue;
	}

}
