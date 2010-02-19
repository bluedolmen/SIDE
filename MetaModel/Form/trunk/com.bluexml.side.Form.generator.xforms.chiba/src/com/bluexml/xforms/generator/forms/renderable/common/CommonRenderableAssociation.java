package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.FormTypeRendered;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.renderable.common.association.inline.RenderableIMultiple;
import com.bluexml.xforms.generator.forms.renderable.common.association.inline.RenderableIUnique;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple.RenderableSMultiple;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique.RenderableSSingle;
import com.bluexml.xforms.generator.forms.rendered.RenderedGroup;

/**
 * The Class CommonRenderableAssociation. Représente un aiguilleur vers le type adéquat de widget,
 * en fonction des propriétés 'inline' et 'multiple'.
 * @JavaFormatter("off")
 */
public class CommonRenderableAssociation extends Renderable {

	/** The title. */
	@SuppressWarnings("unused")
	private String title;

	/** The association bean. */
	private AssociationBean associationBean;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new common renderable association.
	 * 
	 * @param properties
	 *            the properties
	 */
	public CommonRenderableAssociation(AssociationProperties properties) {
		super();
		this.name = properties.getName();
		this.title = properties.getAssocTitle();

		associationBean = new AssociationBean();
		associationBean.setDestinationClass(properties.getDestination());
		associationBean.setName(name);
		associationBean.setTitle(properties.getAssocTitle());
		associationBean.setHint(properties.getHint());
		associationBean.setDestinationRenderable(properties.getDestinationRenderable());
		if (properties.getCreateEditFormType().equals(FormTypeRendered.formForm)) {
			associationBean.setCreateEditForm(properties.getCreateEditFormName());
		}
		associationBean.setHiBound(properties.getHiBound());

		// hide or display action buttons
		associationBean.setShowingActions(properties.isShowingActions());
		associationBean.setDisabled(properties.isDisabled());
		associationBean.setMandatory(properties.isMandatory());

		// set maximum number of items to display
		associationBean.setFieldSize(properties.getFieldSize());

		associationBean.setFormatPattern(properties.getFormatPattern());
		associationBean.setLabelLength(properties.getLabelLength());
		
		if (properties.isInline()) {
			if (properties.isMultiple()) {
				add(new RenderableIMultiple(associationBean));
			} else {
				add(new RenderableIUnique(associationBean, false, false));
			}
		} else {
			RenderableSelector selector = new RenderableSelector(associationBean);
			if (properties.isMultiple()) {
				add(new RenderableSMultiple(associationBean, selector));
			} else {
				add(new RenderableSSingle(associationBean, selector));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return new Path(PathType.relativePath, name + "/associationItem/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		return new RenderedGroup(null, name);
	}

}
