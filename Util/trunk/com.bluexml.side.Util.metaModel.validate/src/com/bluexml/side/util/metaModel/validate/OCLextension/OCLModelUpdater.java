package com.bluexml.side.util.metaModel.validate.OCLextension;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class OCLModelUpdater {

	public static <T> EList<T> applyChanges(IFile iFile, String oclSelector, OCLRunnable action, boolean autosave) throws Exception {
		EObject openModel = EResourceUtils.openRootModelElement(iFile);
		EList<T> result = applyChanges(openModel, oclSelector, action);
		if (autosave) {
			EResourceUtils.saveModel(iFile, openModel);
		}
		return result;
	}
	
	public static <T> EList<T> applyChanges(EObject openModel, String oclSelector, OCLRunnable action) throws Exception {
		EList<T> eval = OCLEvaluator.eval(openModel, oclSelector);
		EList<T> result = action.run(eval);
		return result;
	}

	public interface OCLRunnable {

		public <T> EList<T> run(EList<T> selection);
	}
}
