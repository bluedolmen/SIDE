package com.bluexml.side.framework.alfresco.formProcessor;

import org.alfresco.repo.forms.FormData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.extensions.webscripts.servlet.FormData.FormField;

public class CustomFormData extends FormData {
	private static Log logger = LogFactory.getLog(CustomFormData.class);

	@Override
	public void addFieldData(FormField field) {
		FieldData fieldData = new FieldData(field);
		this.data.put(fieldData.getName(), fieldData);
	}

	class FieldData extends org.alfresco.repo.forms.FormData.FieldData {
		String mimetype = null;

		/**
		 * Constructs a FieldData object from a WebScript FormField object
		 * 
		 * @param field
		 *            The WebScript FormData object to create the field from
		 */
		public FieldData(FormField field) {
			super(field);

			if (isFile) {
				logger.debug("isFile");
				mimetype = field.getMimetype();
				logger.debug("mineType :" + this.mimetype);
			}
		}

		public String getMimetype() {
			return mimetype;
		}

	}
}
