/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram;

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

import com.bluexml.side.Requirements.modeler.RequirementsPlugin;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.AgentEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.AttributeEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.EntityEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.GoalEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.PrivilegeEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.PrivilegeGroupEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.RelationShipEditPart;

/**
 * A diagram configuration : manages Palette, EditPartFactory for this diagram.
 *
 * @generated
 */
public class ReqConfiguration implements IConfiguration {
	/**
	 * @generated
	 */
	private ReqPaletteManager paletteManager;

	/**
	 * @generated
	 */
	private ReqEditPartFactory editPartFactory;

	/**
	 * @generated
	 */
	private ReqCreationUtils creationUtils;

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
	public ReqConfiguration() {
		registerAdapters();
	}

	/**
	 * Registers the Adapter Factories for all the EditParts
	 *
	 * @generated
	 */
	private void registerAdapters() {
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(RelationShipEditPart.class,
						com.bluexml.side.requirements.RelationShip.class),
				RelationShipEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(GoalEditPart.class,
						com.bluexml.side.requirements.Goal.class),
				GoalEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(AgentEditPart.class,
						com.bluexml.side.requirements.Agent.class),
				AgentEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(EntityEditPart.class,
						com.bluexml.side.requirements.Entity.class),
				EntityEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(AttributeEditPart.class,
						com.bluexml.side.requirements.Attribute.class),
				AttributeEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(PrivilegeEditPart.class,
						com.bluexml.side.requirements.Privilege.class),
				PrivilegeEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(PrivilegeGroupEditPart.class,
						com.bluexml.side.requirements.PrivilegeGroup.class),
				PrivilegeGroupEditPart.class);
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getId()
	 * @generated
	 */
	public String getId() {
		return new String("com.bluexml.side.Requirements.modeler.goalDiagram");
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getName()
	 * @generated
	 */
	public String getName() {
		return new String("Goal");
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getEditPartFactory()
	 * @generated
	 */
	public EditPartFactory getEditPartFactory() {
		if (editPartFactory == null) {
			editPartFactory = new ReqEditPartFactory();
		}

		return editPartFactory;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getPaletteManager()
	 * @generated
	 */
	public IPaletteManager getPaletteManager() {
		if (paletteManager == null) {
			paletteManager = new ReqPaletteManager(getCreationUtils());
		}

		return paletteManager;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getCreationUtils()
	 * @generated
	 */
	public ICreationUtils getCreationUtils() {
		if (creationUtils == null) {
			creationUtils = new ReqCreationUtils(getDiagramGraphConf());
		}

		return creationUtils;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
	 * @generated
	 */
	public DiagramGraphConf getDiagramGraphConf() {
		if (diagramGraphConf == null) {
			URL url = RequirementsPlugin
					.getDefault()
					.getBundle()
					.getResource(
							"com/bluexml/side/Requirements/modeler/goalDiagram/diagram.graphconf");
			if (url != null) {
				URI fileURI = URI.createURI(url.toString());
				ResourceSet resourceSet = new ResourceSetImpl();
				Resource resource = resourceSet.getResource(fileURI, true);
				if (resource != null
						&& resource.getContents().get(0) instanceof DiagramGraphConf) {
					diagramGraphConf = (DiagramGraphConf) resource
							.getContents().get(0);
				}
			} else {
				new MissingGraphConfFileException(
						"The *.diagramgraphconf file can not be retrieved. Check if the path is correct in the Configuration class of your diagram.");
			}
		}

		return diagramGraphConf;
	}

}