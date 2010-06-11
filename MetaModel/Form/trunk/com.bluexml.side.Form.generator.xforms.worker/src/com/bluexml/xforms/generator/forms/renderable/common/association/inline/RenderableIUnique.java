package com.bluexml.xforms.generator.forms.renderable.common.association.inline;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedGroup;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderableIUnique.
 */
public class RenderableIUnique extends AbstractRenderable {

	/** The own group. */
	private boolean ownGroup;

	/** The return to line. */
	private boolean returnToLine;

	/**
	 * Instantiates a new renderable i unique.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param ownGroup
	 *            the own group
	 * @param returnToLine
	 *            the return to line
	 */
	public RenderableIUnique(AssociationBean associationBean, boolean ownGroup, boolean returnToLine) {
		super(associationBean);
		add(associationBean.getDestinationRenderable());
		this.ownGroup = ownGroup;
		this.returnToLine = returnToLine;
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
		String path = "";
		return new Path(PathType.relativePath, path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		Rendered rendered = null;
		if (ownGroup) {
			String groupTitle = ModelTools.getTitle(bean.getDestinationClass());
			if (bean.getTitle() != null) { // #1279
				groupTitle = bean.getTitle();
			}
			rendered = new RenderedGroup(groupTitle, ModelTools.getCompleteNameJAXB(bean
					.getDestinationClass()), null);
			if (returnToLine) {
				rendered.setReturnToLine(true);
			}
		} else {
			rendered = new RenderedParentGroup(renderedParents);
		}
		return rendered;
	}

}
