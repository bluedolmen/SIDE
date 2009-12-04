/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.processDiagram;

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
public class ProPaletteManager extends ModelerPaletteManager {
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
	public ProPaletteManager(ICreationUtils utils) {
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
				ProImageRegistry.getImageDescriptor("GOAL"), ProImageRegistry
						.getImageDescriptor("GOAL_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getAgent(), "default");
		entries.add(new ModelerCreationToolEntry("Agent", "Agent", factory,
				ProImageRegistry.getImageDescriptor("AGENT"), ProImageRegistry
						.getImageDescriptor("AGENT_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getEntity(), "default");
		entries.add(new ModelerCreationToolEntry("Entity", "Entity", factory,
				ProImageRegistry.getImageDescriptor("ENTITY"), ProImageRegistry
						.getImageDescriptor("ENTITY_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getAttribute(), "default");
		entries.add(new ModelerCreationToolEntry("Attribute", "Attribute",
				factory, ProImageRegistry.getImageDescriptor("ATTRIBUTE"),
				ProImageRegistry.getImageDescriptor("ATTRIBUTE_LARGE")));

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

		/*factory = new GraphElementCreationFactory(creationUtils,
				ProSimpleObjectConstants.SIMPLE_OBJECT_IS_SUB_GOAL, "default",
				false);
		entries.add(new ModelerConnectionCreationToolEntry("Sub goal",
				"Sub goal", factory, ProImageRegistry
						.getImageDescriptor("IS_SUB_GOAL"), ProImageRegistry
						.getImageDescriptor("IS_SUB_GOAL_LARGE")));*/

		factory = new GraphElementCreationFactory(creationUtils,
				ProSimpleObjectConstants.SIMPLE_OBJECT_IS_RESPONSIBLE,
				"default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Is responsible",
				"Is responsible", factory, ProImageRegistry
						.getImageDescriptor("IS_RESPONSIBLE"), ProImageRegistry
						.getImageDescriptor("IS_RESPONSIBLE_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getRelationShip(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Relationship",
				"Relationship", factory, ProImageRegistry
						.getImageDescriptor("RELATIONSHIP"), ProImageRegistry
						.getImageDescriptor("RELATIONSHIP_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getPrivilegeGroup(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Strategy",
				"Strategy", factory, ProImageRegistry
						.getImageDescriptor("PRIVILEGEGROUP"), ProImageRegistry
						.getImageDescriptor("PRIVILEGEGROUP_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				RequirementsPackage.eINSTANCE.getGoalStep(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Next goal",
				"Next goal", factory, ProImageRegistry
						.getImageDescriptor("GOALSTEP"), ProImageRegistry
						.getImageDescriptor("GOALSTEP_LARGE")));

		edgeDrawer.addAll(entries);
		getRoot().add(edgeDrawer);
	}

}
