/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.policies;

import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;

/**
 * @generated
 */
public class ReqDiagramLayoutEditPolicy extends ModelerLayoutEditPolicy {
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public ReqDiagramLayoutEditPolicy() {
		super();
	}

	@Override
	protected boolean isExternalObjectAllowed(GraphNode arg0, GraphNode arg1) {
		return true;
	}
}