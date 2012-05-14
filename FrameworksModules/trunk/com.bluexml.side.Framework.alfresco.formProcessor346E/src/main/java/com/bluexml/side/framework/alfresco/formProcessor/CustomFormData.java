package com.bluexml.side.framework.alfresco.formProcessor;

import java.util.ArrayList;
import java.util.List;

import org.alfresco.repo.forms.FormData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.webscripts.servlet.FormData.FormField;

public class CustomFormData extends FormData {
	private static Log logger = LogFactory.getLog(CustomFormData.class);

	public CustomFormData() {
		super();
		logger.debug("create instance");
	}

	boolean fileNameIsGenerated = false;

	public boolean isFileNameIsGenerated() {
		return fileNameIsGenerated;
	}

	public void setFileNameIsGenerated(boolean fileNameIsGenerated) {
		this.fileNameIsGenerated = fileNameIsGenerated;
	}

	@Override
	public void addFieldData(String fieldName, Object fieldValue) {
		// TODO Auto-generated method stub
		super.addFieldData(fieldName, fieldValue);
	}

	@Override
	public void addFieldData(String fieldName, Object fieldValue, boolean overwrite) {
		// check whether some data already exists
		if (this.data.containsKey(fieldName)) {
			// if we are overwriting just replace with provided data
			if (overwrite) {
				this.data.put(fieldName, new FieldData(fieldName, fieldValue, false));
			} else {
				// pull out the existing value and create a List if necessary
				List currentValues = null;
				Object currentValue = this.data.get(fieldName).getValue();
				if (currentValue instanceof List) {
					currentValues = (List) currentValue;
				} else {
					// a non List value is present, create the new list
					// and add the current value to it
					currentValues = new ArrayList(4);
					currentValues.add(currentValue);
					this.data.put(fieldName, new FieldData(fieldName, currentValues, false));
				}

				// add the provided value to the list
				currentValues.add(fieldValue);
			}
		} else {
			this.data.put(fieldName, new FieldData(fieldName, fieldValue, false));
		}
	}

	@Override
	public void addFieldData(FormField field) {
		logger.debug("adding field data :" + field);
		FieldData fieldData = new FieldData(field);
		this.data.put(fieldData.getName(), fieldData);
	}

	public class FieldData extends org.alfresco.repo.forms.FormData.FieldData {
		String mimetype = null;
		String fileName = null;

		/**
		 * Constructs a FieldData object from a WebScript FormField object
		 * 
		 * @param field
		 *            The WebScript FormData object to create the field from
		 */
		public FieldData(FormField field) {
			super(field);
			logger.debug("FormField name :" + field.getName());
			logger.debug("FormField isFile :" + field.getIsFile());
			logger.debug("FormField fileName :" + field.getFilename());
			logger.debug("FormField content :" + field.getContent());

			if (isFile) {
				fileName = field.getFilename();
				logger.debug("isFile");
				mimetype = field.getMimetype();
				logger.debug("mineType :" + this.mimetype);
			} else {
				logger.debug("field is not a file :" + field.getName());

			}
		}

		public FieldData(String fieldName, Object fieldValue, boolean b) {
			super(fieldName, fieldValue, b);
		}

		public String getMimetype() {
			return mimetype;
		}

		public String getFileName() {
			return fileName;
		}

	}
}
