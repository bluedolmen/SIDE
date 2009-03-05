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
package com.bluexml.side.Workflow.modeler.diagram;

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

import com.bluexml.side.Workflow.modeler.WorkflowPlugin;
import com.bluexml.side.Workflow.modeler.diagram.edit.ActionEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.DecisionEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.EndStateEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.EventEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.ForkEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.JoinEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.NodeEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.StartStateEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.SwimlaneEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.TaskNodeEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.TimerEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.TransitionEditPart;

/**
 * A diagram configuration : manages Palette, EditPartFactory for this diagram.
 *
 * @generated
 */
public class WfConfiguration implements IConfiguration {
	/**
	 * @generated
	 */
	private WfPaletteManager paletteManager;

	/**
	 * @generated
	 */
	private WfEditPartFactory editPartFactory;

	/**
	 * @generated
	 */
	private WfCreationUtils creationUtils;

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
	public WfConfiguration() {
		registerAdapters();
	}

	/**
	 * Registers the Adapter Factories for all the EditParts
	 *
	 * @generated
	 */
	private void registerAdapters() {
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(TaskNodeEditPart.class,
						com.bluexml.side.workflow.TaskNode.class),
				TaskNodeEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(NodeEditPart.class,
						com.bluexml.side.workflow.Node.class),
				NodeEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(StartStateEditPart.class,
						com.bluexml.side.workflow.StartState.class),
				StartStateEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(EndStateEditPart.class,
						com.bluexml.side.workflow.EndState.class),
				EndStateEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(EventEditPart.class,
						com.bluexml.side.workflow.Event.class),
				EventEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(ActionEditPart.class,
						com.bluexml.side.workflow.Action.class),
				ActionEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(SwimlaneEditPart.class,
						com.bluexml.side.workflow.Swimlane.class),
				SwimlaneEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(DecisionEditPart.class,
						com.bluexml.side.workflow.Decision.class),
				DecisionEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(ForkEditPart.class,
						com.bluexml.side.workflow.Fork.class),
				ForkEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(JoinEditPart.class,
						com.bluexml.side.workflow.Join.class),
				JoinEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(TimerEditPart.class,
						com.bluexml.side.workflow.Timer.class),
				TimerEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(TransitionEditPart.class,
						com.bluexml.side.workflow.Transition.class),
				TransitionEditPart.class);
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getId()
	 * @generated
	 */
	public String getId() {
		return new String("com.bluexml.side.Workflow.modeler.diagram");
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getName()
	 * @generated
	 */
	public String getName() {
		return new String("Workflow");
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getEditPartFactory()
	 * @generated
	 */
	public EditPartFactory getEditPartFactory() {
		if (editPartFactory == null) {
			editPartFactory = new WfEditPartFactory();
		}

		return editPartFactory;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getPaletteManager()
	 * @generated
	 */
	public IPaletteManager getPaletteManager() {
		if (paletteManager == null) {
			paletteManager = new WfPaletteManager(getCreationUtils());
		}

		return paletteManager;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getCreationUtils()
	 * @generated
	 */
	public ICreationUtils getCreationUtils() {
		if (creationUtils == null) {
			creationUtils = new WfCreationUtils(getDiagramGraphConf());
		}

		return creationUtils;
	}

	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
	 * @generated
	 */
	public DiagramGraphConf getDiagramGraphConf() {
		if (diagramGraphConf == null) {
			URL url = WorkflowPlugin
					.getDefault()
					.getBundle()
					.getResource(
							"com/bluexml/side/Workflow/modeler/diagram/diagram.graphconf");
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