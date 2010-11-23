package com.bluexml.side.clazz.service.alfresco;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.Clazz;
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

	public static boolean isNativeModel(ModelElement element) {		
		List<Tag> ts = element.getTags();
		for (Tag tag : ts) {
			String key = tag.getKey();
			String value = tag.getValue();
			System.err.println("key "+key);
			if (key.contains("reversed") && value.contains("alfresco")) {
				return true;
			}
		}
		System.out.println("not native");
		return false;
	}
	
	public static String getPrefixFromTag(ModelElement element) {		
		List<Tag> ts = element.getTags();
		for (Tag tag : ts) {
			String key = tag.getKey();
			String value = tag.getValue();
			if (key.equals("prefix")) {
				return value;
			}
		}
		return "";
	}
	
	public static String getNsURIFromTag(ModelElement element) {		
		List<Tag> ts = element.getTags();
		for (Tag tag : ts) {
			String key = tag.getKey();
			String value = tag.getValue();
			if (key.equals("reversedURI")) {
				return value;
			}
		}
		return "";
	}
	
	public static boolean isReversed(ModelElement element) {		
		List<Tag> ts = element.getTags();
		for (Tag tag : ts) {
			String key = tag.getKey();
			if (key.contains("reversed")) {
				return true;
			}
		}
		return false;
	}
}
