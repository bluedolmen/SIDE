/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.processDiagram.policies;

import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;

/**
 * @_generated
 */
public class ProDiagramLayoutEditPolicy extends ModelerLayoutEditPolicy {
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public ProDiagramLayoutEditPolicy() {
		super();
	}

	@Override
	protected boolean isExternalObjectAllowed(GraphNode parent, GraphNode child) {
		return true;
	}

}
