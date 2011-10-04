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
