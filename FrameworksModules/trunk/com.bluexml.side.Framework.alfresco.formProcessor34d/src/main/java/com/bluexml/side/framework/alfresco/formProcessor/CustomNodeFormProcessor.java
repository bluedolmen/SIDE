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
					String mimetype = cfd.getMimetype();
					
					logger.debug("write content in :" + nodeRef);
					logger.debug("mimeType :" + mimetype);
					logger.debug("encoding :" + writer.getEncoding());
					
					writer.setMimetype(mimetype);
					writer.putContent(inputStream);
					try {
						inputStream.close();
					} catch (IOException e) {
						logger.error("trying to close inputStream fail", e);
					}
				} else {
					// TODO multi file upload not implemented yet
				}
				fileFieldCount++;
			}
		}
	}
}
