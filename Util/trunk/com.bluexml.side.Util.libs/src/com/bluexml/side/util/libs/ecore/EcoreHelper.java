package com.bluexml.side.util.libs.ecore;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

public class EcoreHelper {

	
	/**
	 * Launch validation on given EObject
	 * 
	 * @param eo
	 * @return
	 */
	public static boolean validate(EObject eo) {
		boolean result = true;
		Diagnostician diag = new Diagnostician();
		BasicDiagnostic diagnostics = diag.createDefaultDiagnostic(eo);
		diag.validate(eo, diagnostics);
		if (diagnostics.getSeverity() == Diagnostic.ERROR) {
			result = false;
		}
		return result;
	}
}
