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
package com.bluexml.side.Portal.modeler.diagram;

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

import com.bluexml.side.portal.PortalPackage;

/**
 * Generated Palette Manager
 *
 * @generated
 */
public class PdPaletteManager extends ModelerPaletteManager {
	// declare all the palette categories of the diagram
	/**
	 * @generated
	 */
	private PaletteDrawer objectDrawer;
	/**
	 * @generated
	 */
	private PaletteDrawer linkDrawer;

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
	public PdPaletteManager(ICreationUtils utils) {
		super();
		this.creationUtils = utils;
	}

	/**
	 * Creates the main categories of the palette
	 *
	 * @generated
	 */
	protected void createCategories() {
		createObjectDrawer();
		createLinkDrawer();
	}

	/**
	 * Updates the main categories of the palette
	 *
	 * @generated
	 */
	protected void updateCategories() {
		// deletion of the existing categories and creation of the updated categories

		getRoot().remove(objectDrawer);
		createObjectDrawer();

		getRoot().remove(linkDrawer);
		createLinkDrawer();
	}

	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createObjectDrawer() {
		objectDrawer = new PaletteDrawer("Object", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CreationFactory factory;

		factory = new GraphElementCreationFactory(creationUtils, PortalPackage.eINSTANCE.getPage(), "default");
		entries.add(new ModelerCreationToolEntry("Page", "Page", factory, PdImageRegistry.getImageDescriptor("PAGE"), PdImageRegistry.getImageDescriptor("PAGE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, PortalPackage.eINSTANCE.getPortalLayout(), "default");
		entries.add(new ModelerCreationToolEntry("Layout", "Layout", factory, PdImageRegistry.getImageDescriptor("PORTALLAYOUT"), PdImageRegistry.getImageDescriptor("PORTALLAYOUT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, PortalPackage.eINSTANCE.getPortlet(), "default");
		entries.add(new ModelerCreationToolEntry("Portlet", "Portlet", factory, PdImageRegistry.getImageDescriptor("PORTLET"), PdImageRegistry.getImageDescriptor("PORTLET_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, PortalPackage.eINSTANCE.getPortletType(), "default");
		entries.add(new ModelerCreationToolEntry("Portal Portlet", "Portal Portlet", factory, PdImageRegistry.getImageDescriptor("PORTLETTYPE"), PdImageRegistry.getImageDescriptor("PORTLETTYPE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, PortalPackage.eINSTANCE.getPortletInternal(), "default");
		entries.add(new ModelerCreationToolEntry("Generated Portlet", "Generated Portlet", factory, PdImageRegistry.getImageDescriptor("PORTLETINTERNAL"), PdImageRegistry.getImageDescriptor("PORTLETINTERNAL_LARGE")));

		objectDrawer.addAll(entries);
		getRoot().add(objectDrawer);
	}

	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createLinkDrawer() {
		linkDrawer = new PaletteDrawer("Link", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CreationFactory factory;

		factory = new GraphElementCreationFactory(creationUtils, PortalPackage.eINSTANCE.getisChildPage(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("ChildPage", "ChildPage", factory, PdImageRegistry.getImageDescriptor("ISCHILDPAGE"), PdImageRegistry.getImageDescriptor("ISCHILDPAGE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, PdSimpleObjectConstants.SIMPLE_OBJECT_USELAYOUT, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Use Layout", "Use Layout", factory, PdImageRegistry.getImageDescriptor("USELAYOUT"), PdImageRegistry.getImageDescriptor("USELAYOUT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, PortalPackage.eINSTANCE.getInstanciatePortletType(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Is Portal Portlet", "Is Portal Portlet", factory, PdImageRegistry.getImageDescriptor("INSTANCIATEPORTLETTYPE"), PdImageRegistry.getImageDescriptor("INSTANCIATEPORTLETTYPE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, PdSimpleObjectConstants.SIMPLE_OBJECT_ISINTERNALPORTLET, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Is Generated Portlet", "Is Generated Portlet", factory, PdImageRegistry.getImageDescriptor("ISINTERNALPORTLET"), PdImageRegistry.getImageDescriptor("ISINTERNALPORTLET_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils, PortalPackage.eINSTANCE.getHavePortlet(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Is On Page", "Is On Page", factory, PdImageRegistry.getImageDescriptor("HAVEPORTLET"), PdImageRegistry.getImageDescriptor("HAVEPORTLET_LARGE")));

		linkDrawer.addAll(entries);
		getRoot().add(linkDrawer);
	}

}
