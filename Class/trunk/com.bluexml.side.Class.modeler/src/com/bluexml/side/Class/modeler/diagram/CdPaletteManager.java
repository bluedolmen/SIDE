/*******************************************************************************
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;

import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.common.CommonPackage;

/**
 * Generated Palette Manager
 *
 * @generated
 */
public class CdPaletteManager extends ModelerPaletteManager {
	// declare all the palette categories of the diagram
	/**
	 * @generated
	 */
	private PaletteDrawer objectsDrawer;
	/**
	 * @generated
	 */
	private PaletteDrawer linksDrawer;

	/**
	 * @generated
	 */
	private ICreationUtils creationUtils;

	/**
	 * The Constructor
	 *
	 * @param utils the creation utils for the tools of the palette 
	 * @generated
	 */
	public CdPaletteManager(ICreationUtils utils) {
		super();
		this.creationUtils = utils;
	}

	/**
	 * Creates the main categories of the palette
	 *
	 * @generated
	 */
	protected void createCategories() {
		createObjectsDrawer();
		createLinksDrawer();
	}

	/**
	 * Updates the main categories of the palette
	 *
	 * @generated
	 */
	protected void updateCategories() {
		// deletion of the existing categories and creation of the updated categories

		getRoot().remove(objectsDrawer);
		createObjectsDrawer();

		getRoot().remove(linksDrawer);
		createLinksDrawer();
	}

	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createObjectsDrawer() {
		objectsDrawer = new PaletteDrawer("Objects", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CreationFactory factory;

		factory = new GraphElementCreationFactory(creationUtils,
				ClazzPackage.eINSTANCE.getClazz(), "default");
		entries.add(new ModelerCreationToolEntry("Class", "Class", factory,
				CdImageRegistry.getImageDescriptor("CLAZZ"), CdImageRegistry
						.getImageDescriptor("CLAZZ_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				ClazzPackage.eINSTANCE.getAspect(), "default");
		entries.add(new ModelerCreationToolEntry("Aspect", "Aspect", factory,
				CdImageRegistry.getImageDescriptor("ASPECT"), CdImageRegistry
						.getImageDescriptor("ASPECT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				ClazzPackage.eINSTANCE.getAttribute(), "default");
		entries.add(new ModelerCreationToolEntry("Property", "Property",
				factory, CdImageRegistry.getImageDescriptor("ATTRIBUTE"),
				CdImageRegistry.getImageDescriptor("ATTRIBUTE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				ClazzPackage.eINSTANCE.getOperation(), "default");
		entries.add(new ModelerCreationToolEntry("Operation", "Operation",
				factory, CdImageRegistry.getImageDescriptor("OPERATION"),
				CdImageRegistry.getImageDescriptor("OPERATION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				CommonPackage.eINSTANCE.getStereotype(), "default");
		entries.add(new ModelerCreationToolEntry("Stereotype", "Stereotype",
				factory, CdImageRegistry.getImageDescriptor("STEREOTYPE"),
				CdImageRegistry.getImageDescriptor("STEREOTYPE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				CommonPackage.eINSTANCE.getComment(), "default");
		entries.add(new ModelerCreationToolEntry("Comment", "Comment", factory,
				CdImageRegistry.getImageDescriptor("COMMENT"), CdImageRegistry
						.getImageDescriptor("COMMENT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				ClazzPackage.eINSTANCE.getEnumeration(), "default");
		entries.add(new ModelerCreationToolEntry("Enumeration", "Enumeration",
				factory, CdImageRegistry.getImageDescriptor("ENUMERATION"),
				CdImageRegistry.getImageDescriptor("ENUMERATION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				ClazzPackage.eINSTANCE.getEnumerationLiteral(), "default");
		entries
				.add(new ModelerCreationToolEntry("Enumeration Literal",
						"Enumeration Literal", factory, CdImageRegistry
								.getImageDescriptor("ENUMERATIONLITERAL"),
						CdImageRegistry
								.getImageDescriptor("ENUMERATIONLITERAL_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				ClazzPackage.eINSTANCE.getView(), "default");
		entries.add(new ModelerCreationToolEntry("View", "View", factory,
				CdImageRegistry.getImageDescriptor("VIEW"), CdImageRegistry
						.getImageDescriptor("VIEW_LARGE")));

		objectsDrawer.addAll(entries);
		getRoot().add(objectsDrawer);
	}

	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createLinksDrawer() {
		linksDrawer = new PaletteDrawer("Links", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CreationFactory factory;

		factory = new GraphElementCreationFactory(creationUtils,
				ClazzPackage.eINSTANCE.getAssociation(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Association",
				"Association", factory, CdImageRegistry
						.getImageDescriptor("ASSOCIATION"), CdImageRegistry
						.getImageDescriptor("ASSOCIATION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				CdSimpleObjectConstants.SIMPLE_OBJECT_GENERALIZATION,
				"default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Generalization",
				"Generalization", factory, CdImageRegistry
						.getImageDescriptor("GENERALIZATION"), CdImageRegistry
						.getImageDescriptor("GENERALIZATION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				CdSimpleObjectConstants.SIMPLE_OBJECT_ISCOMMENTED, "default",
				false);
		entries.add(new ModelerConnectionCreationToolEntry("Is commented",
				"Is commented", factory, CdImageRegistry
						.getImageDescriptor("ISCOMMENTED"), CdImageRegistry
						.getImageDescriptor("ISCOMMENTED_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				CdSimpleObjectConstants.SIMPLE_OBJECT_ISSTEREOTYPED, "default",
				false);
		entries.add(new ModelerConnectionCreationToolEntry("Is stereotyped",
				"Is stereotyped", factory, CdImageRegistry
						.getImageDescriptor("ISSTEREOTYPED"), CdImageRegistry
						.getImageDescriptor("ISSTEREOTYPED_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				CdSimpleObjectConstants.SIMPLE_OBJECT_ISASSOCIATIONCLASS,
				"default", false);
		entries
				.add(new ModelerConnectionCreationToolEntry(
						"Is association class", "Is association class",
						factory, CdImageRegistry
								.getImageDescriptor("ISASSOCIATIONCLASS"),
						CdImageRegistry
								.getImageDescriptor("ISASSOCIATIONCLASS_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				CdSimpleObjectConstants.SIMPLE_OBJECT_HASVIEW, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Has view",
				"Has view", factory, CdImageRegistry
						.getImageDescriptor("HASVIEW"), CdImageRegistry
						.getImageDescriptor("HASVIEW_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				CdSimpleObjectConstants.SIMPLE_OBJECT_HASASPECT, "default",
				false);
		entries.add(new ModelerConnectionCreationToolEntry("hasAspect",
				"hasAspect", factory, CdImageRegistry
						.getImageDescriptor("HASASPECT"), CdImageRegistry
						.getImageDescriptor("HASASPECT_LARGE")));

		linksDrawer.addAll(entries);
		getRoot().add(linksDrawer);
	}

}
