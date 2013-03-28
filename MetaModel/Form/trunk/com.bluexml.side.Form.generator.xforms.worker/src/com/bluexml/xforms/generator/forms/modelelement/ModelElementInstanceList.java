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
package com.bluexml.xforms.generator.forms.modelelement;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class ModelElementInstanceList. Provides an element of an XForms
 * template's "model" section.
 * The element will trigger the fetching of the initial item set of a selection
 * widget.
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
	 * Initial constructor, for use with associations that target classes
	 * defined in model files.
	 * Instantiates a new model element for lists.
	 * 
	 * @param modelClazz
	 *            the type of the objects to fetch in the list
	 * @param instanceName
	 *            the instance name
	 */
	public ModelElementInstanceList(AssociationBean bean, String instanceName) {
		AbstractClass modelClazz = bean.getDestinationClass();
		this.typeCompleteName = ModelTools.getNamespacePrefix(modelClazz) + ":" + getFormGenerator().getClassQualifiedName(modelClazz);
		this.instanceName = instanceName;

		initFields(bean);

		this.identifier = "";
	}

	/**
	 * Constructor specifically for attaching a selection widget to a
	 * selection-capable field.
	 * 
	 * @param overridingType
	 *            the overriding type. Not necessary since it's available via
	 *            the bean, but we use
	 *            it to distinguish the constructors from one another.
	 * @param instanceName
	 *            the instance name, for identification of the instance by the
	 *            XForms engine
	 * @param bean
	 *            the association bean
	 */
	public ModelElementInstanceList(String overridingType, String instanceName, AssociationBean bean) {
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
	 * @see com.bluexml.xforms.generator.forms.ModelElement#getModelElement()
	 */
	@Override
	public Element getModelElement() {
		Element instance = XFormsGenerator.createElement("instance", XFormsGenerator.NAMESPACE_XFORMS);
		String sourceURI;
		if (dataSourceUri == null || dataSourceUri.contains("alfresco")) {
			sourceURI = MsgId.INT_URI_SCHEME_READER + buildListActionUriFragment(typeCompleteName, formatPattern, maxLength, identifier, filterAssoc, isComposition, isForSearch, luceneQuery, dataSourceUri);
		} else {
			sourceURI = dataSourceUri;
		}

		instance.setAttribute("src", sourceURI);
		instance.setAttribute("id", instanceName);
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.xforms.generator.forms.ModelElement#hasClone(java.util.List)
	 */
	@Override
	public boolean hasClone(List<ModelElement> allModelElementsClean) {
		return false;
	}

}
