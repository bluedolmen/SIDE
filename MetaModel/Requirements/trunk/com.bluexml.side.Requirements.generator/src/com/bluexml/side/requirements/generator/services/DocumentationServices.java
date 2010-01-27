package com.bluexml.side.requirements.generator.services;

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
