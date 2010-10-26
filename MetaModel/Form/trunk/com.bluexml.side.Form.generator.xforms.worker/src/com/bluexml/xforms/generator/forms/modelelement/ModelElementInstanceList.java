package com.bluexml.xforms.generator.forms.modelelement;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class ModelElementInstanceList. Provides an element of an XForms template's "model" section.
 * The element will trigger the fetching of the initial item set of a selection widget.
 */
public class ModelElementInstanceList extends ModelElement {

	/** The type complete name. */
	private String typeCompleteName;

	/** The instance name. */
	private String instanceName;

	private String formatPattern;

	private String maxLength;

	private String identifier; // #1529

	private String filterAssoc; // #1536

	private boolean isComposition; // #1536

	private boolean isForSearch; // #1536

	private String luceneQuery;

	private String dataSourceUri;

	/**
	 * Initial constructor, for use with associations that target classes defined in model files.
	 * Instantiates a new model element for lists.
	 * 
	 * @param modelClazz
	 *            the type of the objects to fetch in the list
	 * @param instanceName
	 *            the instance name
	 */
	public ModelElementInstanceList(AssociationBean bean, String instanceName) {
		Clazz modelClazz = bean.getDestinationClass();
		this.typeCompleteName = ModelTools.getNamespacePrefix(modelClazz) + ":"
				+ getFormGenerator().getClassQualifiedName(modelClazz);
		this.instanceName = instanceName;

		initFields(bean);

		this.identifier = "";
	}

	/**
	 * Constructor specifically for attaching a selection widget to a selection-capable field.
	 * 
	 * @param overridingType
	 *            the overriding type. Not necessary since it's available via the bean, but we use
	 *            it to distinguish the constructors from one another.
	 * @param instanceName
	 *            the instance name, for identification of the instance by the XForms engine
	 * @param bean
	 *            the association bean
	 */
	public ModelElementInstanceList(@SuppressWarnings("unused") String overridingType,
			String instanceName, AssociationBean bean) {
		this.typeCompleteName = bean.getOverridingType();
		this.instanceName = instanceName;

		initFields(bean);

		this.filterAssoc = null;
		this.isComposition = false;
		this.identifier = bean.getIdentifierPropName();
	}

	/**
	 * @param bean
	 */
	private void initFields(AssociationBean bean) {
		this.formatPattern = bean.getFormatPattern();
		this.maxLength = bean.getLabelLength();
		this.filterAssoc = bean.getFilterAssoc();
		this.isComposition = bean.isComposition();
		this.isForSearch = bean.isInFeatureSearchMode();
		this.luceneQuery = bean.getLuceneQuery();
		this.dataSourceUri = StringUtils.trimToNull(bean.getDataSourceUri());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#getModelElement()
	 */
	@Override
	public Element getModelElement() {
		Element instance = XFormsGenerator.createElement("instance",
				XFormsGenerator.NAMESPACE_XFORMS);
		String sourceURI;
		if (dataSourceUri == null || dataSourceUri.contains("alfresco")) {
			sourceURI = MsgId.INT_URI_SCHEME_READER
					+ buildListActionUriFragment(typeCompleteName, formatPattern, maxLength,
							identifier, filterAssoc, isComposition, isForSearch, luceneQuery, dataSourceUri);
		} else {
			sourceURI = dataSourceUri;
		}

		instance.setAttribute("src", sourceURI);
		instance.setAttribute("id", instanceName);
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.ModelElement#hasClone(java.util.List)
	 */
	@Override
	public boolean hasClone(List<ModelElement> allModelElementsClean) {
		return false;
	}

}
