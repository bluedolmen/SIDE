package com.bluexml.xforms.generator.forms.renderable.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.GeneratorInterface.AssociationKind;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationProperties;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableTab;
import com.bluexml.xforms.generator.forms.rendered.RenderedTabContainer;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderableClass.
 */
public class RenderableClass extends Renderable {

	/** The classe. */
	private Clazz classe;

	/** The main tab. */
	private RenderableTab mainTab;

	/** The main layout. */
	private RenderableLayout mainLayout;

	/** The other associations tab. */
	private RenderableTab otherAssociationsTab;

	/** The other attributes tab. */
	private RenderableTab otherAttributesTab;

	/** The first render. */
	private boolean firstRender = true;

	/** The sub classes. */
	private List<Clazz> subClasses;

	/** The to render. */
	private boolean toRender;

	/**
	 * Instantiates a new renderable class.
	 * 
	 * @param classe
	 *            the classe
	 * @param toRender
	 *            the to render
	 */
	public RenderableClass(Clazz classe, boolean toRender) {
		super();
		this.classe = classe;
		this.toRender = toRender;

		this.mainTab = new RenderableTab(ModelTools.getTitle(classe));
		this.mainLayout = new RenderableLayout(classe);
		this.mainTab.add(this.mainLayout);
		this.otherAttributesTab = new RenderableTab("Attributs");
		this.otherAssociationsTab = new RenderableTab("Associations");

		this.subClasses = new ArrayList<Clazz>();
		if (!classe.isAbstract()) {
			this.subClasses.add(classe);
		}
	}

	/**
	 * Adds the sub class.
	 * 
	 * @param subClass
	 *            the sub class
	 * @param classBean
	 *            the class bean
	 */
	public void addSubClass(Clazz subClass, RenderableClass classBean) {
		subClasses.add(subClass);
	}

	/**
	 * Checks for sub classes.
	 * 
	 * @return true, if successful
	 */
	public boolean hasSubClasses() {
		return (subClasses.size() > 1);
	}

	/**
	 * Gets the sub classes.
	 * 
	 * @return the sub classes
	 */
	public List<Clazz> getSubClasses() {
		return subClasses;
	}

	/**
	 * Gets the classe.
	 * 
	 * @return the classe
	 */
	public Clazz getClasse() {
		return classe;
	}

	/**
	 * Checks if is to render.
	 * 
	 * @return true, if is to render
	 */
	public boolean isToRender() {
		return toRender;
	}

	/**
	 * Sets the to render.
	 * 
	 * @param toRender
	 *            the new to render
	 */
	public void setToRender(boolean toRender) {
		this.toRender = toRender;
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
		return ROOT_RELATIVE;
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
		if (firstRender) {
			if (otherAssociationsTab.getChildrenSize() > 0) {
				addFirst(otherAssociationsTab);
			}
			if (otherAttributesTab.getChildrenSize() > 0) {
				addFirst(otherAttributesTab);
			}
			if (!mainLayout.isEmpty()) {
				addFirst(mainTab);
			}
			firstRender = false;
		}
		boolean doShowTab = doShowTab(renderedParents);
		if (getChildrenSize() <= 1) {
			doShowTab = false;
		}
		return new RenderedTabContainer(ModelTools.getCompleteNameJAXB(classe)
				+ XFormsGenerator.getId("TabContainer"), ModelTools.getTitle(classe), doShowTab);
	}

	/**
	 * Adds the attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 */
	public void addAttribute(Attribute attribute) {
		// Commenté car hidden est traité via l'attribut "relevant" dans le bind
		// if (!ModelTools.isProperty(attribute, "hidden")) {
		RenderableAttribute renderableAttribute = new RenderableAttribute(classe, attribute);
		if (mainLayout.hasAttribute(attribute)) {
			mainLayout.addAttribute(attribute, renderableAttribute);
		} else {
			otherAttributesTab.add(renderableAttribute);
		}
		// }
	}

	/**
	 * Adds the association.
	 * 
	 * @param destination
	 *            the destination
	 * @param destination
	 * @param classBean
	 *            the class bean
	 * @param name
	 *            the name
	 * @param title
	 *            the title
	 * @param type
	 *            the type
	 * @param association
	 *            the association
	 * @param associationClasse
	 *            the association classe
	 * @param associationClasseBean
	 *            the association classe bean
	 */
	public void addAssociation(Clazz source, Clazz destination, RenderableClass classBean,
			String name, String title, AssociationKind type, Association association) {

		AssociationProperties properties = new AssociationProperties(destination, classBean, name,
				title, type.isInline(), type.getHiBound(), type.getLoBound());
		if (type.isFiltered()) {
			String alfrescoName = getFormGenerator().getAssoQualifiedName(association, source);
			properties.setFilterAssoc(alfrescoName);
		}
		boolean isComposition = (association.getAssociationType() == AssociationType.COMPOSITION);
		properties.setComposition(isComposition);

		RenderableAssociation renderableAssociation = new RenderableAssociation(properties,
				association);
		if (mainLayout.hasAssociation(association)) {
			mainLayout.addAssociation(association, renderableAssociation);
		} else {
			otherAssociationsTab.add(renderableAssociation);
		}
	}

	/**
	 * Adds the aspect.
	 * 
	 * @param aspect
	 *            the aspect
	 */
	public void addAspect(Aspect aspect) {
		RenderableAspect renderableAspect = new RenderableAspect(aspect);
		if (!renderableAspect.isEmpty()) {
			add(renderableAspect);
		}
	}

}
