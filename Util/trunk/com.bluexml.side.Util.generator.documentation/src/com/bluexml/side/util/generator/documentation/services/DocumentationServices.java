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
package com.bluexml.side.util.generator.documentation.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public class DocumentationServices {
	protected static String modelName;
	protected static List<String> diagImgPath = new ArrayList<String>();
	protected static List<String> outlineRelativePath = new ArrayList<String>();

	public static String getModelName(EObject o) {
		if (modelName !=null && modelName.length() > 0) {
			return modelName;
		} else {
			Date d = new Date();
			return Long.toString(d.getTime());
		}
	}

	public static String getModelName() {
		return modelName;
	}

	public static void setModelName(String p_modelName) {
		String name = p_modelName;
		if (p_modelName.contains(".")) {
			name = p_modelName.replace(".", "-");
		}
		if (name.length() == 0) {
			Date d = new Date();
			name = Long.toString(d.getTime());
		}
		DocumentationServices.modelName = name;
	}

	public static void addDiagImgPath(String string) {
		diagImgPath.add(string);
	}

	public static List<String> getDiagImgPath(EObject o) {
		return diagImgPath;
	}

	public static void addOutlineRelativePath(String string) {
		outlineRelativePath.add(string);
	}

	public static List<String> getOutlineRelativePath(EObject o) {
		return outlineRelativePath;
	}

	public static void clearAll() {
		modelName = null;
		outlineRelativePath.clear();
		diagImgPath.clear();
	}
}
