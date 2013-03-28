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
package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.xforms.generator.FormGeneratorsManager;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.SelectBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique.RenderableSSingle;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;

@Deprecated
// this class is unused in the rest of the code
public class RenderableChoiceInputSearch extends Renderable {

	private ChoiceField choiceField;

	public RenderableChoiceInputSearch(XFormsGenerator generationManager, ChoiceField choiceField) {
		super();
		this.choiceField = choiceField;
		addSelector(generationManager, choiceField);
	}

	private void addSelector(XFormsGenerator generationManager, ChoiceField choiceField) {
		SelectBean selectBean = new SelectBean();

		com.bluexml.side.common.ModelElement ref;

		ref = choiceField.getRef();
		ref = (com.bluexml.side.common.ModelElement) generationManager.getFormGenerator()
				.getRealObject(ref);

		Attribute attributeRef = (Attribute) ref;
		selectBean.setEnumeration(attributeRef.getValueList());
		selectBean.setLabel(choiceField.getLabel());
		selectBean.setMultiple(choiceField.isMultiple());
		selectBean.setLimited(true);

		AssociationBean associationBean = new AssociationBean();

		associationBean.setCreateEditForms(null);
		associationBean.setDestinationRenderable(null);
		associationBean.setDestinationSelect(selectBean);
		associationBean.setName(FormGeneratorsManager.getUniqueName(choiceField));
		associationBean.setTitle(choiceField.getLabel());
		associationBean.setHint(choiceField.getHelp_text());
		associationBean.setShowingActions(false);
		String lsize = "" + choiceField.getField_size();
		if (StringUtils.trimToNull(lsize) != null) {
			associationBean.setFieldSize(lsize);
		}
		associationBean.setMandatory(choiceField.isMandatory());

		// custom configuration parameters
		FormGeneratorsManager formGenerator = getFormGenerator();
		associationBean.setDataSourceUri(formGenerator.getXtensionDataSourceUri(choiceField));
		associationBean.setFeatureMode(formGenerator.getXtensionFeatureMode(choiceField));
		associationBean.setLuceneQuery(formGenerator.getXtensionLuceneQuery(choiceField));
		associationBean.setNoAutoSearch(formGenerator.getXtensionNoAutoSearch(choiceField));
		associationBean.setNoStatsOutput(formGenerator.getXtensionNoStatsOutput(choiceField));

		RenderableSelector selector = new RenderableSelector(associationBean);
		add(new RenderableSSingle(associationBean, selector));
		throw new RuntimeException("This class is deprecated");
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return new Path(PathType.relativePath, FormGeneratorsManager.getUniqueName(choiceField)
				+ "/");
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		return new RenderedParentGroup(renderedParents);
	}

}
