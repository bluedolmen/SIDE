package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.FormElement;
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
public class RenderableChoiceInputSearch extends Renderable {

	private ChoiceField choiceField;

	public RenderableChoiceInputSearch(XFormsGenerator generationManager, FormElement parent,
			ChoiceField choiceField) {
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

		RenderableSelector selector = new RenderableSelector(associationBean);
		add(new RenderableSSingle(associationBean, selector));
		throw new RuntimeException("This class is deprecated");
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return new Path(PathType.relativePath, FormGeneratorsManager.getUniqueName(choiceField) + "/");
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		return new RenderedParentGroup(renderedParents);
	}

}
