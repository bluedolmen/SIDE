package com.bluexml.xforms.generator.forms.renderable.classes;

import java.util.Stack;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.rendered.RenderedTab;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderableAspect.
 */
public class RenderableAspect extends Renderable {

	/** The label. */
	private String label;

	/** The name. */
	private String name;

	/** The renderable layout. */
	private RenderableLayout renderableLayout;

	/**
	 * Instantiates a new renderable aspect.
	 * 
	 * @param aspect
	 *            the aspect
	 */
	public RenderableAspect(Aspect aspect) {
		this.label = ModelTools.getTitle(aspect);
		this.name = aspect.getName();

		this.renderableLayout = new RenderableLayout(aspect);
		add(renderableLayout);

		EList<Attribute> attributes = aspect.getAttributes();
		for (Attribute attribute : attributes) {
			if (!ModelTools.isProperty(attribute, "hidden")) {
				renderableLayout
						.addAttribute(attribute, new RenderableAttribute(aspect, attribute));
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
		return new Path(PathType.relativePath, this.name + "/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		return new RenderedTab(label, doShowTab(renderedParents));
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return renderableLayout.isEmpty();
	}

}
