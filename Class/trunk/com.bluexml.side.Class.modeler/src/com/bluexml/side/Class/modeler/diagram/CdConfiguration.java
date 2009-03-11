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

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.diagram.edit.AspectEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.AssociationEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.AttributeEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.ClazzEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.CommentEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.EnumerationEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.EnumerationLiteralEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.OperationEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.StereotypeEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.ViewEditPart;

/**
 * A diagram configuration : manages Palette, EditPartFactory for this diagram.
 *
 * @generated
 */
public class CdConfiguration implements IConfiguration {
	/**
	 * @generated
	 */
	private CdPaletteManager paletteManager;

	/**
	 * @generated
	 */
	private CdEditPartFactory editPartFactory;

	/**
	 * @generated
	 */
	private CdCreationUtils creationUtils;

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
	public CdConfiguration() {
		registerAdapters();
	}

	/**
	 * Registers the Adapter Factories for all the EditParts
	 *
	 * @generated
	 */
	private void registerAdapters() {
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(StereotypeEditPart.class, com.bluexml.side.common.Stereotype.class), StereotypeEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ClazzEditPart.class, com.bluexml.side.clazz.Clazz.class), ClazzEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(AspectEditPart.class, com.bluexml.side.clazz.Aspect.class), AspectEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(AttributeEditPart.class, com.bluexml.side.clazz.Attribute.class), AttributeEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(OperationEditPart.class, com.bluexml.side.clazz.Operation.class), OperationEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(CommentEditPart.class, com.bluexml.side.common.Comment.class), CommentEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(EnumerationEditPart.class, com.bluexml.side.clazz.Enumeration.class), EnumerationEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(EnumerationLiteralEditPart.class, com.bluexml.side.clazz.EnumerationLiteral.class), EnumerationLiteralEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ViewEditPart.class, com.bluexml.side.clazz.View.class), ViewEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(AssociationEditPart.class, com.bluexml.side.clazz.Association.class), AssociationEditPart.class);
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getId()
	 * @generated
	 */
	public String getId() {
		return new String("com.bluexml.side.Class.modeler.diagram");
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getName()
	 * @generated
	 */
	public String getName() {
		return new String("Class Diagram");
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getEditPartFactory()
	 * @generated
	 */
	public EditPartFactory getEditPartFactory() {
		if (editPartFactory == null) {
			editPartFactory = new CdEditPartFactory();
		}

		return editPartFactory;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getPaletteManager()
	 * @generated
	 */
	public IPaletteManager getPaletteManager() {
		if (paletteManager == null) {
			paletteManager = new CdPaletteManager(getCreationUtils());
		}

		return paletteManager;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getCreationUtils()
	 * @generated
	 */
	public ICreationUtils getCreationUtils() {
		if (creationUtils == null) {
			creationUtils = new CdCreationUtils(getDiagramGraphConf());
		}

		return creationUtils;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
	 * @generated
	 */
	public DiagramGraphConf getDiagramGraphConf() {
		if (diagramGraphConf == null) {
			URL url = ClazzPlugin.getDefault().getBundle().getResource("com/bluexml/side/Class/modeler/diagram/diagram.graphconf");
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