package com.bluexml.side.clazz.service.alfresco;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.util.libs.StringHelper;

public class CommonServices {
	public static String getNamedModelElementQName(NamedModelElement node) {
		return convertFullNameToQualifiedName(node.getFullName());
	}

	public static String convertFullNameToQualifiedName(String fullName) {
		// return fullName.replaceAll("\\.", "_");
		return StringHelper.getJavaQName(fullName, "\\.");
	}

	

	static EObject getRootContainer(EObject o) {
		if (o.eContainer() != null) {
			return getRootContainer(o.eContainer());
		} else {
			return o;
		}
	}
}
