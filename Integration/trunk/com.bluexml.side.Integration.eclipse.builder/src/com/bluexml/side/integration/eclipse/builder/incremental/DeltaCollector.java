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
package com.bluexml.side.integration.eclipse.builder.incremental;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.compare.diff.metamodel.AttributeChange;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChange;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.MoveModelElement;
import org.eclipse.emf.ecore.EObject;

public class DeltaCollector {
	public static Map<EObject, DiffElement> collectDifferences(List<DiffElement> differences) {
		Map<EObject, DiffElement> result = new HashMap<EObject, DiffElement>();
		for (DiffElement el : differences) {
			System.out.println("SIDEBuilder.collectDifferences() diff :" + el.getClass().getName());
			System.out.println("SIDEBuilder.collectDifferences() diff :" + el);
			System.out.println("SIDEBuilder.collectDifferences() kind :" + el.getKind());
			EObject eobject = null;
			if (el instanceof AttributeChange) {
				System.out.println("SIDEBuilder.collectDifferences() attchange ");
				AttributeChange change = (AttributeChange) el;
				eobject = change.getLeftElement();
			} else if (el instanceof ModelElementChange) {
				System.out.println("SIDEBuilder.collectDifferences() modelElement change");
				ModelElementChange change = (ModelElementChange) el;
				if (change instanceof ModelElementChangeRightTarget) {
					System.out.println("* SIDEBuilder.collectDifferences() ModelElementRightChange");
					ModelElementChangeRightTarget changeR = (ModelElementChangeRightTarget) change;
					eobject = changeR.getRightElement();
				} else if (change instanceof MoveModelElement) {
					System.out.println("* SIDEBuilder.collectDifferences() moveModelElement");
					MoveModelElement move = (MoveModelElement) change;
					eobject = move.getLeftElement();
				} else if (change instanceof ModelElementChangeLeftTarget) {
					System.out.println("* SIDEBuilder.collectDifferences() ModelElementChangeLeftTarget");
					ModelElementChangeLeftTarget left = (ModelElementChangeLeftTarget) change;
					eobject = left.getLeftElement();
				} else {
					System.err.println("* SIDEBuilder.collectDifferences() another ModelElementChange :" + el.getClass().getName());
				}
			} else {
				System.err.println("SIDEBuilder.collectDifferences() another change :" + el.getClass().getName());
			}
			if (eobject != null) {
				System.out.println("SIDEBuilder.collectDifferences() add EObject " + eobject.getClass().getName());
				result.put(eobject, el);
			}
			System.out.println("SIDEBuilder.collectDifferences() CALL Collect SubDiff");
			result.putAll(collectDifferences(el.getSubDiffElements()));
		}
		return result;
	}
}
