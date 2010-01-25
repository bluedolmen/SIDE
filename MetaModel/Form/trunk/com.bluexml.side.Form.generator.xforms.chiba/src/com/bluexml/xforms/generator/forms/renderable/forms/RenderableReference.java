package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.Reference;
import com.bluexml.xforms.generator.FormGeneratorsManager;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableFormContainer;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;

/**
 * The Class RenderableReference.
 */
public class RenderableReference extends RenderableFormElement<Reference> {

	/**
	 * Instantiates a new renderable reference.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param formElement
	 *            the form element
	 */
	public RenderableReference(XFormsGenerator generationManager, FormElement parent,
			Reference formElement) {
		super(generationManager, parent, formElement);
		int i = 0;
		// FIXME: vérifier que le cast formContainer -> formClass est safe.
		// FIXME: vérifier que l'incrémentation de i intervient au bon endroit

		EList<FormContainer> targets = formElement.getTarget();
		FormClass formClass;
		for (FormContainer formContainer : targets) {
			if (formContainer instanceof FormClass) {
				formClass = (FormClass) formContainer; //
				RenderableFormContainer renderableTarget = generationManager
						.getRenderableForm(formClass);
				add(renderableTarget);
			}
			i++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.forms.RenderableFormElement #compute()
	 */
	@Override
	public void compute() {
		// nothing
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
		return new Path(PathType.relativePath, FormGeneratorsManager.getUniqueName(formElement)
				+ "/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		return new RenderedParentGroup(renderedParents);
	}

}
