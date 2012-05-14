package org.alfresco.repo.forms.processor.node;

import static org.alfresco.repo.forms.processor.node.FormFieldConstants.ASSOC_DATA_ADDED_SUFFIX;
import static org.alfresco.repo.forms.processor.node.FormFieldConstants.ASSOC_DATA_REMOVED_SUFFIX;
import static org.alfresco.repo.forms.processor.node.FormFieldConstants.DOT_CHARACTER;
import static org.alfresco.repo.forms.processor.node.FormFieldConstants.DOT_CHARACTER_REPLACEMENT;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.forms.FormData;
import org.alfresco.repo.forms.FormData.FieldData;
import org.alfresco.repo.forms.FormException;
import org.alfresco.repo.forms.FormNotFoundException;
import org.alfresco.repo.forms.Item;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ChildAssociationDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.DuplicateChildNodeNameException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.InvalidQNameException;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.GUID;
import org.alfresco.util.ISO9075;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluexml.side.framework.alfresco.commons.configurations.SimplePropertiesConfiguration;
import com.bluexml.side.framework.alfresco.formProcessor.CustomFormData;

public class CustomTypeFormProcessor extends TypeFormProcessor {
	private static Log logger = LogFactory.getLog(CustomTypeFormProcessor.class);

	protected String CREATE_BEHAVIOR_KEY = "formprocessor.creationbehavior";

	CREATE_BEHAVIORS creationbehavior = CREATE_BEHAVIORS.FAIL;

	SimplePropertiesConfiguration config;

	SearchService searchService;

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public SimplePropertiesConfiguration getConfig() {
		return config;
	}

	public void setConfig(SimplePropertiesConfiguration config) {
		this.config = config;
	}

	public CustomTypeFormProcessor() {
		propertyNamePattern = Pattern.compile(FormFieldConstants.PROP_DATA_PREFIX + "([^_]*){1}?_(.*){1}?");
		transientPropertyPattern = Pattern.compile(FormFieldConstants.PROP_DATA_PREFIX + "([^_]*){1}?");
		associationNamePattern = Pattern.compile(FormFieldConstants.ASSOC_DATA_PREFIX + "([^_]*){1}?_(.*){1}?(_[a-zA-Z]+)");
		logger.info("[X] Custom Type Processor loaded ...[X]");
	}

	public void init() {
		this.register();
		String value = config.getValue(CREATE_BEHAVIOR_KEY);

		if (value != null) {
			creationbehavior = CREATE_BEHAVIORS.valueOf(value);
			logger.info("create file behavior : " + creationbehavior);
		} else {
			logger.info("create file using default behavior (no configuration founded) : " + creationbehavior);
		}

	}

	@Override
	protected void persistNode(NodeRef nodeRef, FormData data) {
		boolean isFileNameIsGenerated = false;
		if (data instanceof CustomFormData) {
			isFileNameIsGenerated = ((CustomFormData) data).isFileNameIsGenerated();
		}
		String fileName = null;
		// implements FileField persistence
		int fileFieldCount = 0;
		for (FieldData fieldData : data) {
			logger.debug("current fieldData :" + fieldData.getName());
			logger.debug("current fieldData isFile :" + fieldData.isFile());
			logger.debug("current fieldData Type :" + fieldData.getClass().getName());

			if (fieldData.isFile() == true && fieldData instanceof CustomFormData.FieldData) {
				CustomFormData.FieldData cfd = (CustomFormData.FieldData) fieldData;

				if (fileFieldCount == 0) {
					fileName = cfd.getFileName();
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

		if (isFileNameIsGenerated && StringUtils.trimToNull(fileName) != null) {
			logger.debug("fill cm:name using fileName :" + fileName);
			// add fieldData to put fileName into cm:name properties
			data.addFieldData(NAME_PROP_DATA, fileName);
		}

		// let superclass persist properties
		super.persistNode(nodeRef, data);
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

	/**
	 * Creates a new instance of the given type.
	 * <p>
	 * If the form data has the name property present it is used as the name of
	 * the node.
	 * </p>
	 * <p>
	 * The new node is placed in the location defined by the "destination" data
	 * item in the form data (this will usually be a hidden field), this will
	 * also be the NodeRef representation of the parent for the new node.
	 * </p>
	 * This version is patched to support more flexible alf_destination, not
	 * only nodeRef, but could be xpath too
	 * 
	 * @param typeDef
	 *            The type defintion of the type to create
	 * @param data
	 *            The form data
	 * @return NodeRef representing the newly created node
	 */
	protected NodeRef createNode(TypeDefinition typeDef, FormData data) {
		if (logger.isDebugEnabled()) {
			String value = config.getValue(CREATE_BEHAVIOR_KEY);
			if (value != null) {
				creationbehavior = CREATE_BEHAVIORS.valueOf(value);
			}
		}
		NodeRef nodeRef = null;

		if (data != null) {
			// firstly, ensure we have a destination to create the node in
			NodeRef parentRef = null;
			FieldData destination = data.getFieldData(DESTINATION);

			if (destination == null) {
				throw new FormException("Failed to persist form for '" + typeDef.getName().toPrefixString(this.namespaceService) + "' as '" + DESTINATION + "' data was not provided.");
			}
			// create the parent NodeRef
			String destinationString = (String) destination.getValue();
			//.matches("([^:]*)://([^/])/(.*)")
			if (NodeRef.isNodeRef(destinationString)) {
				// is nodeRef
				parentRef = new NodeRef(destinationString);
			} else {
				// not nodeRef try to resolve the string
				String regexp = "/([^:]*):([^/]*)";
				String cleanPath = "";
				Pattern p = Pattern.compile(regexp);
				Matcher matcher = p.matcher(destinationString);
				while (matcher.find()) {
					String group1 = matcher.group(1);
					String group2 = matcher.group(2);

					cleanPath += "/" + group1 + ":" + ISO9075.encode(group2);
				}
				logger.debug("encoded destination XPath :" + cleanPath);

				ResultSet results = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_XPATH, cleanPath);
				List<NodeRef> nodeRefs = results.getNodeRefs();
				if (nodeRefs.size() == 1) {
					// ok
					parentRef = nodeRefs.get(0);
					if (!fileFolderService.getFileInfo(parentRef).isFolder()) {
						// error
						throw new FormException("the destination is not a folder, please check");
					}
				} else {
					// error
					throw new FormException("unable to convert destination path to nodeRef, please check");
				}
			}

			// remove the destination data to avoid warning during persistence,
			// this can
			// always be retrieved by looking up the created node's parent
			data.removeFieldData(DESTINATION);

			// TODO: determine what association to use when creating the node in
			// the destination,
			// defaults to ContentModel.ASSOC_CONTAINS

			// if a name property is present in the form data use it as the node
			// name,
			// otherwise generate a guid

			String nodeName = null;
			FieldData nameData = data.getFieldData(NAME_PROP_DATA);
			if (nameData != null) {
				nodeName = (String) nameData.getValue();

				// remove the name data otherwise 'rename' gets called in
				// persistNode
				data.removeFieldData(NAME_PROP_DATA);
			}

			if (nodeName == null || nodeName.length() == 0) {
				// no name found in fieldData, so search for a FileField
				if (data instanceof CustomFormData) {
					// search for file name in fields
					for (FieldData fieldData : data) {
						if (fieldData.isFile() == true && fieldData instanceof CustomFormData.FieldData) {
							CustomFormData.FieldData cfd = (CustomFormData.FieldData) fieldData;
							String fileName = cfd.getFileName();
							// add fieldData to put fileName into cm:name properties
							nodeName = fileName;
							logger.debug("nodeName found in fieldData :" + nodeName);
							break;
						}
					}
					// no file field so generate a GUID
					if (StringUtils.trimToNull(nodeName) == null) {
						nodeName = GUID.generate();
						if (data instanceof CustomFormData) {
							((CustomFormData) data).setFileNameIsGenerated(true);
							logger.debug("nodeName is generated :" + nodeName);
						}
					}
				}
			} else {
				logger.debug("nodeName found in FormData :" + nodeName);
			}

			// test if file Exists
			NodeRef searchSimple = this.fileFolderService.searchSimple(parentRef, nodeName);
			if (searchSimple != null) {
				logger.debug("File exists continu with behavior :" + this.creationbehavior);
				if (this.creationbehavior.equals(CREATE_BEHAVIORS.FAIL)) {
					// throw exception
					throw new DuplicateChildNodeNameException(parentRef, ContentModel.ASSOC_CONTAINS, nodeName, null);
				} else if (this.creationbehavior.equals(CREATE_BEHAVIORS.INCREMENT_NAME)) {
					// generate another name by adding counter
					int c = 1;
					String nodeName2 = null;
					while (searchSimple != null) {
						nodeName2 = nodeName.substring(0, nodeName.lastIndexOf(".")) + "_" + c + nodeName.substring(nodeName.lastIndexOf("."));
						searchSimple = this.fileFolderService.searchSimple(parentRef, nodeName2);
						c++;
					}
					nodeRef = createNode(typeDef, parentRef, nodeName2);
				} else if (this.creationbehavior.equals(CREATE_BEHAVIORS.OVERRIDE)) {
					// update existing node
					nodeRef = searchSimple;
				}
			} else {
				nodeRef = createNode(typeDef, parentRef, nodeName);
			}

		}

		return nodeRef;
	}

	protected NodeRef createNode(TypeDefinition typeDef, NodeRef parentRef, String nodeName) {
		NodeRef nodeRef;
		// create the node
		Map<QName, Serializable> nodeProps = new HashMap<QName, Serializable>(1);
		nodeProps.put(ContentModel.PROP_NAME, nodeName);
		nodeRef = this.nodeService.createNode(parentRef, ContentModel.ASSOC_CONTAINS, QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, QName.createValidLocalName(nodeName)), typeDef.getName(), nodeProps).getChildRef();
		return nodeRef;
	}

	@Override
	protected void processAssociationPersist(NodeRef nodeRef, Map<QName, AssociationDefinition> assocDefs, Map<QName, ChildAssociationDefinition> childAssocDefs, FieldData fieldData, List<org.alfresco.repo.forms.processor.node.AbstractAssocCommand> assocCommands) {
		if (getLogger().isDebugEnabled())
			getLogger().debug("Processing field " + fieldData + " for association persistence");

		String fieldName = fieldData.getName();
		Matcher m = this.associationNamePattern.matcher(fieldName.replaceAll(DOT_CHARACTER_REPLACEMENT, DOT_CHARACTER));
		if (m.matches()) {
			String qNamePrefix = m.group(1);
			String localName = m.group(2);
			String assocSuffix = m.group(3);

			QName fullQName = QName.createQName(qNamePrefix, localName, namespaceService);

			// ensure that the association being persisted is defined in the model
			AssociationDefinition assocDef = assocDefs.get(fullQName);

			// TODO: if the association is not defined on the node, check for the association
			// in all models, however, the source of an association can be critical so we
			// can't just look up the association in the model regardless. We need to
			// either check the source class of the node and the assoc def match or we
			// check that the association was defined as part of an aspect (where by it's
			// nature can have any source type)

			// SIDE : since forms and model are generated by SIDE we make the assertion that fields are valid, so no advanced validation are done
			if (assocDef == null) {
				if (getLogger().isWarnEnabled()) {
					getLogger().warn("Field '" + fieldName + "' as an association definition can not be found in the current model");
				}
				assocDef = this.dictionaryService.getAssociation(fullQName);

				if (assocDef == null) {
					if (getLogger().isWarnEnabled()) {
						getLogger().warn("Ignoring field '" + fieldName + "' as an association definition can not be found in ANY models ");
					}
					return;
				} else {
					if (getLogger().isDebugEnabled()) {
						getLogger().debug("Field '" + fieldName + "' Found as an association definition in another model : " + assocDef.getModel().getName());
					}
				}
			}

			String value = (String) fieldData.getValue();
			String[] nodeRefs = value.split(",");

			// Each element in this array will be a new target node in association
			// with the current node.
			for (String nextTargetNode : nodeRefs) {
				if (nextTargetNode.length() > 0) {
					if (NodeRef.isNodeRef(nextTargetNode)) {
						if (assocSuffix.equals(ASSOC_DATA_ADDED_SUFFIX)) {
							if (assocDef.isChild()) {
								assocCommands.add(new AddChildAssocCommand(nodeRef, new NodeRef(nextTargetNode), fullQName));
							} else {
								assocCommands.add(new AddAssocCommand(nodeRef, new NodeRef(nextTargetNode), fullQName));
							}
						} else if (assocSuffix.equals(ASSOC_DATA_REMOVED_SUFFIX)) {
							if (assocDef.isChild()) {
								assocCommands.add(new RemoveChildAssocCommand(nodeRef, new NodeRef(nextTargetNode), fullQName));
							} else {
								assocCommands.add(new RemoveAssocCommand(nodeRef, new NodeRef(nextTargetNode), fullQName));
							}
						} else {
							if (getLogger().isWarnEnabled()) {
								StringBuilder msg = new StringBuilder();
								msg.append("Ignoring 'fieldName ").append(fieldName).append("' as it does not have one of the expected suffixes (").append(ASSOC_DATA_ADDED_SUFFIX).append(" or ").append(ASSOC_DATA_REMOVED_SUFFIX).append(")");
								getLogger().warn(msg.toString());
							}
						}
					} else {
						if (getLogger().isWarnEnabled()) {
							StringBuilder msg = new StringBuilder();
							msg.append("targetNode ").append(nextTargetNode).append(" is not a valid NodeRef and has been ignored.");
							getLogger().warn(msg.toString());
						}
					}
				}
			}
		} else if (getLogger().isWarnEnabled()) {
			getLogger().warn("Ignoring unrecognised field '" + fieldName + "'");
		}
	}

	public enum CREATE_BEHAVIORS {
		INCREMENT_NAME, OVERRIDE, FAIL
	}
}
