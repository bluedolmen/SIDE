package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.List;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormGroupPresentationType;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.SearchField;
import com.bluexml.side.form.StaticText;
import com.bluexml.side.form.VirtualField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableTabContainer;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableTabWrapper;
import com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableGroup;

/**
 * The Class RenderableFormElement.
 */
public abstract class RenderableFormElement<FE extends FormElement> extends Renderable {

	/** The generation manager. */
	protected XFormsGenerator generationManager;

	/** The parent form element. */
	protected FormElement parentFormElement;

	/** The form element. */
	protected FE formElement;

	/**
	 * @return the formElement
	 */
	public FE getFormElement() {
		return formElement;
	}

	/** The renderable tab container. */
	protected RenderableTabContainer renderableTabContainer;

	/**
	 * Instantiates a new renderable form element.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param formElement
	 *            the form element
	 */
	public RenderableFormElement(XFormsGenerator generationManager, FormElement parent,
			FE formElement) {
		super();
		this.generationManager = generationManager;
		this.parentFormElement = parent;
		this.formElement = formElement;
	}

	/**
	 * Adds the form elements.
	 * 
	 * @param elements
	 *            the elements
	 */
	protected void addFormElements(List<FormElement> elements) {
		for (FormElement formElement : elements) {
			addFormElement(formElement);
		}
	}

	/**
	 * Gets the tab container.
	 * 
	 * @return the tab container
	 */
	protected RenderableTabContainer getTabContainer() {
		if (renderableTabContainer == null) {
			renderableTabContainer = new RenderableTabContainer(XFormsGenerator
					.getId("TabContainer" + formElement.getId()), formElement.getLabel(), true);
			add(renderableTabContainer);
		}
		return renderableTabContainer;
	}

	/**
	 * Adds the tab.
	 * 
	 * @param renderable
	 *            the renderable
	 * @param title
	 *            the title
	 */
	protected void addTab(Renderable renderable, String title) {
		getTabContainer().add(new RenderableTabWrapper(renderable, title));
	}

	/**
	 * Adds the form element.
	 * 
	 * @param formElement
	 *            the form element
	 */
	protected void addFormElement(FormElement formElement) {
		if (formElement instanceof FormClass) {
			FormClass formClass = (FormClass) formElement;
			Renderable renderable = generationManager.getRenderableForm(formClass);
			if (formClass.getPresentation() == FormGroupPresentationType.TABBED) {
				addTab(renderable, formClass.getLabel());
			} else {
				add(renderable);
			}
		} else {
			Renderable renderable = null;

			if (formElement instanceof Reference) {
				renderable = new RenderableReference(generationManager, formElement,
						(Reference) formElement);
			} else if (formElement instanceof ModelChoiceField) {
				renderable = new RenderableModelChoiceField(generationManager, formElement,
						(ModelChoiceField) formElement);
			} else if (formElement instanceof VirtualField) {
				renderable = new RenderableVirtualField(generationManager, formElement,
						(VirtualField) formElement);
			} else if (formElement instanceof Field) {
				Field field = (Field) formElement;
				renderable = RenderableField.getRenderable(generationManager, formElement, field);
				// } else if (formElement instanceof FormAspect) {
				// renderable = new RenderableAspect(generationManager,
				// formElement, (FormAspect) formElement);
			} else if (formElement instanceof FormGroup) {
				renderable = new RenderableGroup<FormGroup>(generationManager, formElement,
						(FormGroup) formElement);
			} else if (formElement instanceof StaticText) {
				renderable = new RenderableStaticText(generationManager, formElement,
						(StaticText) formElement);
			} else if (formElement instanceof SearchField) {
				SearchField searchField = (SearchField) formElement;
				renderable = RenderableSearchField.getRenderable(generationManager, formElement,
						searchField);
			}

			// Commenté car hidden est traité via l'attribut "relevant" dans le bind
			// if (formElement instanceof Field) {
			// Field field = (Field) formElement;
			// if (field.isHidden()) {
			// renderableCompute(renderable);
			// renderable = null;
			// }
			// }
			if (formElement instanceof FormGroup && renderable != null) {
				FormGroup formGroup = (FormGroup) formElement;
				if (formGroup.getPresentation() == FormGroupPresentationType.TABBED) {
					addTab(renderable, formGroup.getLabel());
					renderableCompute(renderable);
					renderable = null;
				}
			}

			if (renderable != null) {
				add(renderable);
				renderableCompute(renderable);
			}
		}
	}

	/**
	 * Renderable compute.
	 * 
	 * @param renderable
	 *            the renderable
	 */
	private void renderableCompute(Renderable renderable) {
		if (renderable != null) {
			if (renderable instanceof RenderableFormElement<?>) {
				((RenderableFormElement<?>) renderable).compute();
			}
		}
	}

	/**
	 * Compute. Used for collecting the renderables of children form elements. Relevant only when
	 * the form element is capable of having children form elements.
	 */
	public abstract void compute();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return super.toString() + " " + formElement.getId();
	}

}
