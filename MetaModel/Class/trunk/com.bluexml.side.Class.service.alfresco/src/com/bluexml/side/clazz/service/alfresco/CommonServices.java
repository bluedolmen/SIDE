package com.bluexml.side.clazz.service.alfresco;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.Tag;

public class CommonServices {
	public static String getNamedModelElementQName(NamedModelElement node) {
		if (isNativeModel(node)) {
			return node.getName();
		}
		return convertFullNameToQualifiedName(node.getFullName());
	}

	public static String convertFullNameToQualifiedName(String fullName) {
		return fullName.replaceAll("\\.", "_");
		// return StringHelper.getJavaQName(fullName, "\\.");
	}

	static EObject getRootContainer(EObject o) {
		if (o.eContainer() != null) {
			return getRootContainer(o.eContainer());
		} else {
			return o;
		}
	}

	static boolean isNativeModel(ModelElement element) {		
		List<Tag> ts = element.getTags();
		for (Tag tag : ts) {
			String key = tag.getKey();
			String value = tag.getValue();
			if (key.contains("reversed") && value.contains("alfresco")) {
				return true;
			}
		}
		return false;
	}
}
