/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.figures;

import org.eclipse.draw2d.Locator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.topcased.modeler.edit.locators.EdgeObjectOffsetLocator;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.figures.IEdgeObjectOffsetFigure;

/**
 * @generated
 */
public class PrivilegeGroupFigure extends PolylineConnectionEx {

	private IEdgeObjectFigure middleNameEdgeObject;
	private Locator middleNameLocator;

	/**
	 * The constructor
	 * 
	 * @_generated
	 */
	public PrivilegeGroupFigure() {
		super();
		setLineStyle(SWT.LINE_DASHDOT);

		middleNameEdgeObject = new CommentFigure(this);
		middleNameLocator = new EdgeObjectOffsetLocator(
				(IEdgeObjectOffsetFigure) middleNameEdgeObject);
		add(middleNameEdgeObject, middleNameLocator);
	}
	
	@Override
	public Color getForegroundColor() {
		return new Color(null,72,230,89);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the object figure
	 * @_generated
	 */
	public IEdgeObjectFigure getmiddleNameEdgeObjectFigure() {
		return middleNameEdgeObject;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
	 * @_generated
	 */
	public Locator getLocator(IEdgeObjectFigure edgeObjectfigure) {
		if (edgeObjectfigure == middleNameEdgeObject) {
			return middleNameLocator;
		}
		return null;
	}

}