package com.bluexml.side.util.libs.ecore;

import java.io.File;
import java.io.IOException;

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
