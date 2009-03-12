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

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPartFactory;
import org.topcased.modeler.editor.IConfiguration;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.graphconf.exceptions.MissingGraphConfFileException;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.diagram.edit.HavePortletEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.InstanciatePortletTypeEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PageEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortalLayoutEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortletEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortletInternalEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortletTypeEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.isChildPageEditPart;

/**
 * A diagram configuration : manages Palette, EditPartFactory for this diagram.
 *
 * @generated
 */
public class PdConfiguration implements IConfiguration {
	/**
	 * @generated
	 */
	private PdPaletteManager paletteManager;

	/**
	 * @generated
	 */
	private PdEditPartFactory editPartFactory;

	/**
	 * @generated
	 */
	private PdCreationUtils creationUtils;

	/**
	 * The DiagramGraphConf that contains graphical informations on the configuration
	 * @generated
	 */
	private DiagramGraphConf diagramGraphConf;

	/**
	 * Constructor. Initialize Adapter factories.
	 *
	 * @generated
	 */
	public PdConfiguration() {
		registerAdapters();
	}

	/**
	 * Registers the Adapter Factories for all the EditParts
	 *
	 * @generated
	 */
	private void registerAdapters() {
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PageEditPart.class, com.bluexml.side.portal.Page.class), PageEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(isChildPageEditPart.class, com.bluexml.side.portal.isChildPage.class), isChildPageEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PortalLayoutEditPart.class, com.bluexml.side.portal.PortalLayout.class), PortalLayoutEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PortletEditPart.class, com.bluexml.side.portal.Portlet.class), PortletEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PortletTypeEditPart.class, com.bluexml.side.portal.PortletType.class), PortletTypeEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PortletInternalEditPart.class, com.bluexml.side.portal.PortletInternal.class), PortletInternalEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(HavePortletEditPart.class, com.bluexml.side.portal.HavePortlet.class), HavePortletEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(InstanciatePortletTypeEditPart.class, com.bluexml.side.portal.InstanciatePortletType.class), InstanciatePortletTypeEditPart.class);
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getId()
	 * @generated
	 */
	public String getId() {
		return new String("com.bluexml.side.Portal.modeler.diagram");
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getName()
	 * @generated
	 */
	public String getName() {
		return new String("PortalDiagram");
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getEditPartFactory()
	 * @generated
	 */
	public EditPartFactory getEditPartFactory() {
		if (editPartFactory == null) {
			editPartFactory = new PdEditPartFactory();
		}

		return editPartFactory;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getPaletteManager()
	 * @generated
	 */
	public IPaletteManager getPaletteManager() {
		if (paletteManager == null) {
			paletteManager = new PdPaletteManager(getCreationUtils());
		}

		return paletteManager;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getCreationUtils()
	 * @generated
	 */
	public ICreationUtils getCreationUtils() {
		if (creationUtils == null) {
			creationUtils = new PdCreationUtils(getDiagramGraphConf());
		}

		return creationUtils;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
	 * @generated
	 */
	public DiagramGraphConf getDiagramGraphConf() {
		if (diagramGraphConf == null) {
			URL url = PortalPlugin.getDefault().getBundle().getResource("com/bluexml/side/Portal/modeler/diagram/diagram.graphconf");
			if (url != null) {
				URI fileURI = URI.createURI(url.toString());
				ResourceSet resourceSet = new ResourceSetImpl();
				Resource resource = resourceSet.getResource(fileURI, true);
				if (resource != null && resource.getContents().get(0) instanceof DiagramGraphConf) {
					diagramGraphConf = (DiagramGraphConf) resource.getContents().get(0);
				}
			} else {
				new MissingGraphConfFileException("The *.diagramgraphconf file can not be retrieved. Check if the path is correct in the Configuration class of your diagram.");
			}
		}

		return diagramGraphConf;
	}

}