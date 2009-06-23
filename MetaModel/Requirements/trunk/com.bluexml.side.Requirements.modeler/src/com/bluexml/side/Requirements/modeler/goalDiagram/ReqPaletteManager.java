/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram;

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

import com.bluexml.side.requirements.RequirementsPackage;

/**
 * Generated Palette Manager
 *
 * @generated
 */
public class ReqPaletteManager extends ModelerPaletteManager {
	// declare all the palette categories of the diagram
	/**
	 * @generated
	 */
	private PaletteDrawer nodeDrawer;
	/**
	 * @generated
	 */
	private PaletteDrawer edgeDrawer;

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
	public ReqPaletteManager(ICreationUtils utils) {
		super();
		this.creationUtils = utils;
	}

	/**
	 * Creates the main categories of the palette
	 *
	 * @generated
	 */
	protected void createCategories() {
		createNodeDrawer();
		createEdgeDrawer();
	}

	/**
	 * Updates the main categories of the palette
	 *
	 * @generated
	 */
	protected void updateCategories() {
		// deletion of the existing categories and creation of the updated categories

		getRoot().remove(nodeDrawer);
		createNodeDrawer();

		getRoot().remove(edgeDrawer);
		createEdgeDrawer();
	}

	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createNodeDrawer() {
		nodeDrawer = new PaletteDrawer("Node", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CreationFactory factory;

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getGoal(), "default");
		entries.add(new ModelerCreationToolEntry("Goal", "Goal", factory,
				ReqImageRegistry.getImageDescriptor("GOAL"), ReqImageRegistry
						.getImageDescriptor("GOAL_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getAgent(), "default");
		entries.add(new ModelerCreationToolEntry("Agent", "Agent", factory,
				ReqImageRegistry.getImageDescriptor("AGENT"), ReqImageRegistry
						.getImageDescriptor("AGENT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getEntity(), "default");
		entries.add(new ModelerCreationToolEntry("Entity", "Entity", factory,
				ReqImageRegistry.getImageDescriptor("ENTITY"), ReqImageRegistry
						.getImageDescriptor("ENTITY_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getAttribute(), "default");
		entries.add(new ModelerCreationToolEntry("Attribute", "Attribute",
				factory, ReqImageRegistry.getImageDescriptor("ATTRIBUTE"),
				ReqImageRegistry.getImageDescriptor("ATTRIBUTE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getPrivilegeGroup(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Privilege Group",
				"Privilege Group", factory, ReqImageRegistry
						.getImageDescriptor("PRIVILEGEGROUP"), ReqImageRegistry
						.getImageDescriptor("PRIVILEGEGROUP_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getPrivilege(), "default");
		entries.add(new ModelerCreationToolEntry("Privilege", "Privilege",
				factory, ReqImageRegistry.getImageDescriptor("PRIVILEGE"),
				ReqImageRegistry.getImageDescriptor("PRIVILEGE_LARGE")));

		nodeDrawer.addAll(entries);
		getRoot().add(nodeDrawer);
	}

	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createEdgeDrawer() {
		edgeDrawer = new PaletteDrawer("Edge", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CreationFactory factory;

		factory = new GraphElementCreationFactory(creationUtils,
				ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_SUB_GOAL, "default",
				false);
		entries.add(new ModelerConnectionCreationToolEntry("Sub goal",
				"Sub goal", factory, ReqImageRegistry
						.getImageDescriptor("IS_SUB_GOAL"), ReqImageRegistry
						.getImageDescriptor("IS_SUB_GOAL_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_RESPONSIBLE,
				"default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Is responsible",
				"Is responsible", factory, ReqImageRegistry
						.getImageDescriptor("IS_RESPONSIBLE"), ReqImageRegistry
						.getImageDescriptor("IS_RESPONSIBLE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getRelationShip(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Relationship",
				"Relationship", factory, ReqImageRegistry
						.getImageDescriptor("RELATIONSHIP"), ReqImageRegistry
						.getImageDescriptor("RELATIONSHIP_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getPrivilegeGroup(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Strategy",
				"Strategy", factory, ReqImageRegistry
						.getImageDescriptor("PRIVILEGEGROUP"), ReqImageRegistry
						.getImageDescriptor("PRIVILEGEGROUP_LARGE")));

		edgeDrawer.addAll(entries);
		getRoot().add(edgeDrawer);
	}

}
