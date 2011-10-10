package com.bluexml.side.framework.alfresco.formProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.forms.FormData;
import org.alfresco.repo.forms.FormData.FieldData;
import org.alfresco.repo.forms.processor.node.FormFieldConstants;
import org.alfresco.repo.forms.processor.node.NodeFormProcessor;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CustomNodeFormProcessor extends NodeFormProcessor {
	private static Log logger = LogFactory.getLog(CustomNodeFormProcessor.class);

	public CustomNodeFormProcessor() {
		propertyNamePattern = Pattern.compile(FormFieldConstants.PROP_DATA_PREFIX + "([^_]*){1}?_(.*){1}?");
		transientPropertyPattern = Pattern.compile(FormFieldConstants.PROP_DATA_PREFIX + "([^_]*){1}?");
		associationNamePattern = Pattern.compile(FormFieldConstants.ASSOC_DATA_PREFIX + "([^_]*){1}?_(.*){1}?(_[a-zA-Z]+)");
		logger.info("[X] Custom Node Processor loaded ...[X]");
	}

	@Override
	protected void persistNode(NodeRef nodeRef, FormData data) {

		// let superclass persist properties
		super.persistNode(nodeRef, data);

		// implements File field persistance
		int fileFieldCount = 0;
		for (FieldData fieldData : data) {
			// NOTE: ignore file fields for now, not supported yet!
			if (fieldData.isFile() == true && fieldData instanceof CustomFormData.FieldData) {
				CustomFormData.FieldData cfd = (CustomFormData.FieldData) fieldData;
				if (fileFieldCount == 0) {
					InputStream inputStream = cfd.getInputStream();

					ContentWriter writer = this.contentService.getWriter(nodeRef, ContentModel.PROP_CONTENT, true);
					writer.setMimetype(cfd.getMimetype());

					writer.putContent(inputStream);
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// multi file upload not implemented
				}
				fileFieldCount++;
			}
		}
	}
}
