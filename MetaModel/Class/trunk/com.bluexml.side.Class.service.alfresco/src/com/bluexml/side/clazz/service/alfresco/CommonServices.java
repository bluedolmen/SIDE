package com.bluexml.side.clazz.service.alfresco;

import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.util.libs.StringHelper;

public class CommonServices {
	public static String getNamedModelElementQName(NamedModelElement node) {
		return convertFullNameToQualifiedName(node.getFullName());
	}

	public static String convertFullNameToQualifiedName(String fullName) {
		//return fullName.replaceAll("\\.", "_");
		return StringHelper.getJavaQName(fullName, "\\.");
	}
}
