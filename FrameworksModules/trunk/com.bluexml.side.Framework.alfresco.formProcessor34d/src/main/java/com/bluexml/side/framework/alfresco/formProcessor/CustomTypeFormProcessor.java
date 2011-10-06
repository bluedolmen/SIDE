package com.bluexml.side.framework.alfresco.formProcessor;

import java.util.regex.Pattern;

import org.alfresco.repo.forms.FormNotFoundException;
import org.alfresco.repo.forms.Item;
import org.alfresco.repo.forms.processor.node.FormFieldConstants;
import org.alfresco.repo.forms.processor.node.TypeFormProcessor;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.InvalidQNameException;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CustomTypeFormProcessor extends TypeFormProcessor {
	private static Log logger = LogFactory.getLog(CustomTypeFormProcessor.class);

	public CustomTypeFormProcessor() {
		propertyNamePattern = Pattern.compile(FormFieldConstants.PROP_DATA_PREFIX + "([^_]*){1}?_(.*){1}?");
		transientPropertyPattern = Pattern.compile(FormFieldConstants.PROP_DATA_PREFIX + "([^_]*){1}?");
		associationNamePattern = Pattern.compile(FormFieldConstants.ASSOC_DATA_PREFIX + "([^_]*){1}?_(.*){1}?(_[a-zA-Z]+)");
		logger.info("[X] Custom Type Processor loaded ...[X]");
	}

	@Override
	protected TypeDefinition getTypedItem(Item item) {
		TypeDefinition typeDef = null;

		try {
			// convert the prefix type into full QName representation
			// the type name may be provided in the prefix form i.e.
			// prefix:type, the : may be replaced with _ if the item id
			// was passed on a URL or the full qname may be provided.
			QName type = null;
			String itemId = item.getId();
			if (itemId.startsWith("{")) {
				// item id looks like a full qname
				type = QName.createQName(itemId);
			} else if (itemId.indexOf("_") != -1 && itemId.indexOf(":") == -1) {
				// if item id contains _ change the first occurrence to :
				// as it's more than likely been converted for URL use
				int idx = itemId.indexOf("_");
				String parsedItemId = itemId.substring(0, idx) + ":" + itemId.substring(idx + 1);
				type = QName.createQName(parsedItemId, this.namespaceService);
			} else {
				// try and create the QName using the item id as is
				type = QName.createQName(itemId, this.namespaceService);
			}

			// retrieve the type from the dictionary
			typeDef = this.dictionaryService.getType(type);

			if (typeDef == null) {
				throw new FormNotFoundException(item, new IllegalArgumentException("Type does not exist: " + item.getId()));
			}
		} catch (InvalidQNameException iqne) {
			throw new FormNotFoundException(item, iqne);
		}

		// return the type definition object for the requested type
		return typeDef;
	}
}
