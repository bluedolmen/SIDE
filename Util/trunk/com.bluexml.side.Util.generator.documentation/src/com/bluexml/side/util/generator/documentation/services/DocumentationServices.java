package com.bluexml.side.util.generator.documentation.services;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

public class DocumentationServices {
	protected static String modelName;

	public static String getModelName(EObject o) {
		return modelName;
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
			name = d.toString();
		}
		DocumentationServices.modelName = name;
	}

}
