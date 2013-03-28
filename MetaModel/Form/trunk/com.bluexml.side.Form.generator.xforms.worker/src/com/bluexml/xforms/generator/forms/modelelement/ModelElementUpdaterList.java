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

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class ModelElementListUpdater. Provides an element of an XForms template's "model" section.
 * The element will trigger the fetching <em>and replacing</em> of a selection widget's item list.
 */
public class ModelElementUpdaterList extends AbstractModelElementUpdater {

	private String formatPattern;

	private String maxLength;

	private String identifier; // #1529

	private String filterAssoc; // #1536

	private boolean isComposition; // #1536

	private String luceneQuery;

	private String dataSourceUri;

	/**
	 * If <code>true</code>, the widget this model element is for is in search mode. Which implies
	 * that the widget should load as empty.
	 */
	private boolean isForSearch;

	/** Constructor for normal associations. */
	public ModelElementUpdaterList(AbstractClass abstractClass, String instanceName, AssociationBean bean) {
		super(abstractClass, instanceName);

		initFields(bean);

		this.identifier = ""; // constructor used with Clazz provided. No need for this field.
	}

	/** Constructor for selection-capable text inputs. */
	public ModelElementUpdaterList(String overridingType, String instanceName, AssociationBean bean) {
		super(overridingType, instanceName);

		initFields(bean);

		this.filterAssoc = null;
		this.isComposition = false;
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
		Element submission = XFormsGenerator.createElement("submission",
				XFormsGenerator.NAMESPACE_XFORMS);
		String sourceURI;
		if (dataSourceUri == null || dataSourceUri.contains("alfresco")) {
			sourceURI = MsgId.INT_URI_SCHEME_WRITER
					+ buildListActionUriFragment(typeCompleteName, formatPattern, maxLength,
							identifier, filterAssoc, isComposition, isForSearch, luceneQuery, dataSourceUri);
		} else {
			sourceURI = dataSourceUri;
		}
		submission.setAttribute("action", sourceURI);
		submission.setAttribute("replace", "instance");
		submission.setAttribute("instance", instanceName);
		submission.setAttribute("method", "post");

		String submitId = XFormsGenerator.getId("submit");
		for (Element linkedElement : linkedElements) {
			linkedElement.setAttribute("submission", submitId);
		}
		submission.setAttribute("id", submitId);
		submission.setAttribute("ref", "instance('" + instanceName + "')/query");
		return submission;
	}

}
