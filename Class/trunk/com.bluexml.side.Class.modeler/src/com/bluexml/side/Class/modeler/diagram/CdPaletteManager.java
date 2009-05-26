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

		factory = new GraphElementCreationFactory(creationUtils, ClazzPackage.eINSTANCE.getClazz(), "default");
		entries.add(new ModelerCreationToolEntry("Class", "Class", factory, CdImageRegistry.getImageDescriptor("CLAZZ"), CdImageRegistry.getImageDescriptor("CLAZZ_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, ClazzPackage.eINSTANCE.getAspect(), "default");
		entries.add(new ModelerCreationToolEntry("Aspect", "Aspect", factory, CdImageRegistry.getImageDescriptor("ASPECT"), CdImageRegistry.getImageDescriptor("ASPECT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, ClazzPackage.eINSTANCE.getAttribute(), "default");
		entries.add(new ModelerCreationToolEntry("Property", "Property", factory, CdImageRegistry.getImageDescriptor("ATTRIBUTE"), CdImageRegistry.getImageDescriptor("ATTRIBUTE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, ClazzPackage.eINSTANCE.getClassComment(), "default");
		entries.add(new ModelerCreationToolEntry("Comment", "Comment", factory, CdImageRegistry.getImageDescriptor("CLASSCOMMENT"), CdImageRegistry.getImageDescriptor("CLASSCOMMENT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, ClazzPackage.eINSTANCE.getEnumeration(), "default");
		entries.add(new ModelerCreationToolEntry("Enumeration", "Enumeration", factory, CdImageRegistry.getImageDescriptor("ENUMERATION"), CdImageRegistry.getImageDescriptor("ENUMERATION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, ClazzPackage.eINSTANCE.getEnumerationLiteral(), "default");
		entries.add(new ModelerCreationToolEntry("Enumeration Literal", "Enumeration Literal", factory, CdImageRegistry.getImageDescriptor("ENUMERATIONLITERAL"), CdImageRegistry.getImageDescriptor("ENUMERATIONLITERAL_LARGE")));

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

		factory = new GraphElementCreationFactory(creationUtils, ClazzPackage.eINSTANCE.getAssociation(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Association", "Association", factory, CdImageRegistry.getImageDescriptor("ASSOCIATION"), CdImageRegistry.getImageDescriptor("ASSOCIATION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, CdSimpleObjectConstants.SIMPLE_OBJECT_GENERALIZATION, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Generalization", "Generalization", factory, CdImageRegistry.getImageDescriptor("GENERALIZATION"), CdImageRegistry.getImageDescriptor("GENERALIZATION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, CdSimpleObjectConstants.SIMPLE_OBJECT_ISCOMMENTED, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Is commented", "Is commented", factory, CdImageRegistry.getImageDescriptor("ISCOMMENTED"), CdImageRegistry.getImageDescriptor("ISCOMMENTED_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, CdSimpleObjectConstants.SIMPLE_OBJECT_HASASPECT, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("hasAspect", "hasAspect", factory, CdImageRegistry.getImageDescriptor("HASASPECT"), CdImageRegistry.getImageDescriptor("HASASPECT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, CdSimpleObjectConstants.SIMPLE_OBJECT_DEPENDS, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Depends of", "Depends of", factory, CdImageRegistry.getImageDescriptor("DEPENDS"), CdImageRegistry.getImageDescriptor("DEPENDS_LARGE")));

		linksDrawer.addAll(entries);
		getRoot().add(linksDrawer);
	}

}
