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
package com.bluexml.side.util.libs.ecore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

import com.bluexml.side.util.libs.IFileHelper;

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

	public static List<EObject> getValidationErrors(EObject eo) {
		List<EObject> errors = new ArrayList<EObject>();
		Diagnostician diag = new Diagnostician();
		BasicDiagnostic diagnostics = diag.createDefaultDiagnostic(eo);
		diag.validate(eo, diagnostics);

		if (diagnostics.getSeverity() == Diagnostic.ERROR) {
			List<Diagnostic> children = getAllDiag(diagnostics);
			for (Diagnostic diagnostic : children) {
				String message = diagnostic.getMessage();
				boolean invalideMessage = message.contains("constraint is violated");
				System.out.println("data of " + diagnostic);
				int code = diagnostic.getCode();
				System.out.println("Code :"+code);
				System.out.println("Source :"+diagnostic.getSource());
				
				List<?> data = diagnostic.getData();
				for (Object object : data) {
					System.out.println("EcoreHelper.getValidationErrors() " + object);
					if (object instanceof EObject && (code == Diagnostic.ERROR || invalideMessage)) {
						errors.add((EObject) object);
					}
				}
			}
		}
		return errors;
	}

	public static List<Diagnostic> getAllDiag(Diagnostic diagnostics) {
		List<Diagnostic> l = new ArrayList<Diagnostic>();
		l.add(diagnostics);
		List<Diagnostic> children = diagnostics.getChildren();
		for (Diagnostic diagnostic : children) {
			l.addAll(getAllDiag(diagnostic));
		}
		return l;
	}

	public static String getFileContent(EObject o, String filePath) {
		String content = "";
		try {
			// try to load from FS (not a good idea to use absolute path ....)
			File f = new File(filePath);
			if (!f.exists()) {
				// try to get File from eclipse workspace
				IFile iFile = IFileHelper.getIFile(filePath);
				f = IFileHelper.convertIRessourceToFile(iFile);
				if (!f.exists()) {
					// mmm
					f = IFileHelper.getFile(iFile);
				}
			}
			content = FileUtils.readFileToString(f);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
}
