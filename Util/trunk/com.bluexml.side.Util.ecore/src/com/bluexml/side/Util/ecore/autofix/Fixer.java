package com.bluexml.side.Util.ecore.autofix;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.libs.IFileHelper;

public class Fixer {

	ProtectedStatement action = null;
	Map<String, AbstractFixer> fixers = new HashMap<String, AbstractFixer>();
	IFile model = null;
	File modelFile = null;

	public Fixer(ProtectedStatement action, IFile model) {
		this.action = action;
		this.model = model;
		this.modelFile = new File(model.getRawLocation().toOSString());
	}

	public void registerFixer(AbstractFixer fixer) {
		if (!fixers.containsKey(fixer.getClass().getName())) {
			// only one instance of fixer
			fixers.put(fixer.getClass().getName(), fixer);
		}
	}

	public Object run() throws Exception {
		Object rt = null;
		try {
			rt = action.execute(modelFile);
		} catch (Exception e) {
			// ok let autofix try do do something
			rt = fix(e);
			if (rt == null) {
				throw new Exception("Unable to autoFix ", e);
			}
		}
		IFileHelper.refreshFolder(model.getParent());
		return rt;
	}

	protected Object fix(Exception e) {
		for (AbstractFixer fixer : fixers.values()) {
			boolean ok = fixer.fixe(modelFile, e);
			if (ok) {
				// try to run again
				try {
					File fixedFile = fixer.getFixedFile();
					Object rt = action.execute(fixedFile);
					// job done, so persist changes
					FileUtils.copyFile(fixedFile, modelFile);
					System.out.println("model " + modelFile + " have been fixed by " + fixer);
					return rt;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println("fixer " + fixer + " is unable to perform correction for this error");
			}

		}
		System.out.println("no fixer found for +" + modelFile + " and error " + e);
		return null;
	}

}
