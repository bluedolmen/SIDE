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
