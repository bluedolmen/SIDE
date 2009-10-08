package com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple;

import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.inline.RenderableIUnique;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.generator.forms.rendered.RenderedRepeater;

/**
 * The Class RenderableSMultipleAssociationClass.
 */
public class RenderableSMultipleAssociationClass extends AbstractRenderable {

	/**
	 * Instantiates a new renderable s multiple association class.
	 * 
	 * @param bean
	 *            the bean
	 */
	public RenderableSMultipleAssociationClass(AssociationBean bean) {
		super(bean);
		add(new RenderableIUnique(bean, true, true));
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
		String path = StringUtils.removeEnd(parentPath, "/");
		Rendered parent = renderedParents.peek();
		String repeaterId = null;
		List<Rendered> renderedChildren = parent.getChildren();
		for (Rendered rendered : renderedChildren) {
			if (rendered instanceof RenderedRepeater) {
				repeaterId = ((RenderedRepeater) rendered).getRepeatId();
			}
		}
		path = path + "[index('" + repeaterId + "')]/";
		return new Path(PathType.absolutePath, path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		return new RenderedParentGroup(renderedParents);
	}

}
