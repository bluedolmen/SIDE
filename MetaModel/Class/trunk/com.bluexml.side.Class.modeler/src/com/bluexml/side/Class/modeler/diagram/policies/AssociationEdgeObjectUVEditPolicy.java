/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevée (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package com.bluexml.side.Class.modeler.diagram.policies;

import org.topcased.modeler.edit.policies.EdgeObjectUVEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;

import com.bluexml.side.Class.modeler.diagram.figures.AssociationFigure;

/**
 * An edit policy to select and move the name and the cardinality label relative
 * to the EReference connection .<br>
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AssociationEdgeObjectUVEditPolicy extends EdgeObjectUVEditPolicy
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.topcased.modeler.edit.policies.EdgeObjectUVEditPolicy#isEnd(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated
     */
    protected boolean isEnd(IEdgeObjectFigure figure)
    {
        AssociationFigure associationFigure = (AssociationFigure) getHostFigure();

        if (figure == associationFigure.getsrcNameEdgeObjectFigure())
        {
            return false;
        }
        if (figure == associationFigure.getsrcCountEdgeObjectFigure())
        {
            return false;
        }
        if (figure == associationFigure.gettargetNameEdgeObjectFigure())
        {
            return true;
        }

        if (figure == associationFigure.gettargetCountEdgeObjectFigure())
        {
            return true;
        }

        return true;
    }

}
