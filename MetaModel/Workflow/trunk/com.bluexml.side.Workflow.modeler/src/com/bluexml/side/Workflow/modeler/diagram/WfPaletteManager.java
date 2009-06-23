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

import com.bluexml.side.workflow.WorkflowPackage;

/**
 * Generated Palette Manager
 *
 * @generated
 */
public class WfPaletteManager extends ModelerPaletteManager {
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
	public WfPaletteManager(ICreationUtils utils) {
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
				WorkflowPackage.eINSTANCE.getSwimlane(), "default");
		entries.add(new ModelerCreationToolEntry("Actor", "Actor", factory,
				WfImageRegistry.getImageDescriptor("SWIMLANE"), WfImageRegistry
						.getImageDescriptor("SWIMLANE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getTaskNode(), "default");
		entries.add(new ModelerCreationToolEntry("Task", "Task", factory,
				WfImageRegistry.getImageDescriptor("TASKNODE"), WfImageRegistry
						.getImageDescriptor("TASKNODE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getNode(), "default");
		entries.add(new ModelerCreationToolEntry("Node", "Node", factory,
				WfImageRegistry.getImageDescriptor("NODE"), WfImageRegistry
						.getImageDescriptor("NODE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getStartState(), "default");
		entries.add(new ModelerCreationToolEntry("Start state", "Start state",
				factory, WfImageRegistry.getImageDescriptor("STARTSTATE"),
				WfImageRegistry.getImageDescriptor("STARTSTATE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getEndState(), "default");
		entries.add(new ModelerCreationToolEntry("End state", "End state",
				factory, WfImageRegistry.getImageDescriptor("ENDSTATE"),
				WfImageRegistry.getImageDescriptor("ENDSTATE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getProcessState(), "default");
		entries.add(new ModelerCreationToolEntry("Sub process", "Sub process",
				factory, WfImageRegistry.getImageDescriptor("PROCESSSTATE"),
				WfImageRegistry.getImageDescriptor("PROCESSSTATE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getJoin(), "default");
		entries.add(new ModelerCreationToolEntry("Join", "Join", factory,
				WfImageRegistry.getImageDescriptor("JOIN"), WfImageRegistry
						.getImageDescriptor("JOIN_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getFork(), "default");
		entries.add(new ModelerCreationToolEntry("Fork", "Fork", factory,
				WfImageRegistry.getImageDescriptor("FORK"), WfImageRegistry
						.getImageDescriptor("FORK_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getDecision(), "default");
		entries.add(new ModelerCreationToolEntry("Decision", "Decision",
				factory, WfImageRegistry.getImageDescriptor("DECISION"),
				WfImageRegistry.getImageDescriptor("DECISION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getTimer(), "default");
		entries.add(new ModelerCreationToolEntry("Timer", "Timer", factory,
				WfImageRegistry.getImageDescriptor("TIMER"), WfImageRegistry
						.getImageDescriptor("TIMER_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getAction(), "default");
		entries.add(new ModelerCreationToolEntry("Action", "Action", factory,
				WfImageRegistry.getImageDescriptor("ACTION"), WfImageRegistry
						.getImageDescriptor("ACTION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getEvent(), "default");
		entries.add(new ModelerCreationToolEntry("Event", "Event", factory,
				WfImageRegistry.getImageDescriptor("EVENT"), WfImageRegistry
						.getImageDescriptor("EVENT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WorkflowPackage.eINSTANCE.getAttribute(), "default");
		entries.add(new ModelerCreationToolEntry("Attribute", "Attribute",
				factory, WfImageRegistry.getImageDescriptor("ATTRIBUTE"),
				WfImageRegistry.getImageDescriptor("ATTRIBUTE_LARGE")));

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
				WorkflowPackage.eINSTANCE.getTransition(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Transition",
				"Transition", factory, WfImageRegistry
						.getImageDescriptor("TRANSITION"), WfImageRegistry
						.getImageDescriptor("TRANSITION_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WfSimpleObjectConstants.SIMPLE_OBJECT_MANAGE, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Manage", "Manage",
				factory, WfImageRegistry.getImageDescriptor("MANAGE"),
				WfImageRegistry.getImageDescriptor("MANAGE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WfSimpleObjectConstants.SIMPLE_OBJECT_INITIALIZE, "default",
				false);
		entries.add(new ModelerConnectionCreationToolEntry("Initialize",
				"Initialize", factory, WfImageRegistry
						.getImageDescriptor("INITIALIZE"), WfImageRegistry
						.getImageDescriptor("INITIALIZE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WfSimpleObjectConstants.SIMPLE_OBJECT_ACTIONS, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Actions",
				"Actions", factory, WfImageRegistry
						.getImageDescriptor("ACTIONS"), WfImageRegistry
						.getImageDescriptor("ACTIONS_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WfSimpleObjectConstants.SIMPLE_OBJECT_HASTIMER, "default",
				false);
		entries.add(new ModelerConnectionCreationToolEntry("Has timer",
				"Has timer", factory, WfImageRegistry
						.getImageDescriptor("HASTIMER"), WfImageRegistry
						.getImageDescriptor("HASTIMER_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				WfSimpleObjectConstants.SIMPLE_OBJECT_ISASSOCIATEDWITH,
				"default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Reference class",
				"Reference class", factory, WfImageRegistry
						.getImageDescriptor("ISASSOCIATEDWITH"),
				WfImageRegistry.getImageDescriptor("ISASSOCIATEDWITH_LARGE")));

		linksDrawer.addAll(entries);
		getRoot().add(linksDrawer);
	}

}
