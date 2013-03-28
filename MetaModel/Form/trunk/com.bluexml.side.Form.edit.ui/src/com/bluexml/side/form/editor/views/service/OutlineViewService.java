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
package com.bluexml.side.form.editor.views.service;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.common.utils.FormDiagramUtils;

public class OutlineViewService {

	protected static String nameOfSelectedForm = "";
	protected static boolean doAll = false;

	public static String getNameOfSelectedForm(EObject element) {
		return nameOfSelectedForm;
	}

	public static void setNameOfSelectedForm(String p_nameOfSelectedForm) {
		nameOfSelectedForm = p_nameOfSelectedForm;
	}

	/**
	 * Will search the field to know if this field have been virtualized
	 * @param element
	 */
	public static boolean isVirtualized(EObject element) {
		boolean isVirtualized = false;
		if (element instanceof Field) {
			Field f = (Field) element;
			isVirtualized = FormDiagramUtils.IsVirtualized(f);
		}
		return isVirtualized;
	}

	public static boolean isDoAll() {
		return doAll;
	}

	public static void setDoAll(boolean doAll) {
		OutlineViewService.doAll = doAll;
	}
}
