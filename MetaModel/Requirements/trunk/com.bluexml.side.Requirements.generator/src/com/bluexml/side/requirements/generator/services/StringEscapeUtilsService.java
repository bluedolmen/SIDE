package com.bluexml.side.requirements.generator.services;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.emf.ecore.EObject;

public class StringEscapeUtilsService {

	public static String escapeHtml(EObject node, String label) {
		return StringEscapeUtils.escapeHtml(label.replaceAll("\n","<br/>").replaceAll("\r",""));
	}
}
