package com.bluexml.side.clazz.alfresco.reverse.reverser;

import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.Tag;

public class ReverseHelper {

	public static MetaInfo createMetaInfo(String key, Class<?> type, String stringValue) {
		MetaInfo createMetaInfo = CommonFactory.eINSTANCE.createMetaInfo();
		createMetaInfo.setKey(key);
		createMetaInfo.setValueType(type);
		createMetaInfo.setValue(stringValue);
		return createMetaInfo;
	}

	public static MetaInfo createSimpleMetaInfo(String key) {
		return createMetaInfo(key, null, "true");
	}

	public static MetaInfo createRequired() {
		String key = "required";
		return createSimpleMetaInfo(key);
	}

	public static MetaInfo createPropertySearched() {
		String key = "propertySearched";
		return createSimpleMetaInfo(key);
	}

	public static MetaInfo createIndexStored() {
		String key = "index.stored";
		return createSimpleMetaInfo(key);
	}

	public static MetaInfo createIndexAtomic() {
		String key = "index.atomic";
		return createSimpleMetaInfo(key);
	}

	public static MetaInfo createIndexTokenised(String value) {
		String key = "index.tokenised";
		return createMetaInfo(key, null, value);
	}

	public static MetaInfo createMultiple() {
		String key = "multiple";
		return createSimpleMetaInfo(key);
	}
	
	public static MetaInfo createEncrypted() {
		String key = "encrypted";
		return createSimpleMetaInfo(key);
	}

	public static MetaInfo createEmail() {
		String key = "email";
		return createSimpleMetaInfo(key);
	}

	public static MetaInfo createRegularExpression(String exp) {
		String key = "regular-expression";
		return createMetaInfo(key, null, exp);
	}
	public static MetaInfo createRegularExpressionMatch() {
		String key = "regular-expression.match";
		return createSimpleMetaInfo(key);
	}
	
	public static MetaInfo createMinLength(int size) {
		String key = "min-length";
		return createMetaInfo(key, null, Integer.toString(size));
	}

	public static MetaInfo createMaxLength(int size) {
		String key = "max-length";
		return createMetaInfo(key, null, Integer.toString(size));
	}

	public static MetaInfo createArchive() {
		String key = "archive";
		return createSimpleMetaInfo(key);
	}
	public static MetaInfo createIncludedInSuperTypeQuery() {
		String key = "includedInSuperTypeQuery";
		return createSimpleMetaInfo(key);
	}
	public static MetaInfo createChildAssoDuplicate() {
		String key = "duplicate";
		return createSimpleMetaInfo(key);
	}
	public static MetaInfo createChildAssoPropagateTimestamps() {
		String key = "propagateTimestamps";
		return createSimpleMetaInfo(key);
	}
	public static MetaInfo createMandatoryEnforced() {
		String key = "mandatory.enforced";
		return createSimpleMetaInfo(key);
	}
	
	public static String extractPrefixFromAlfQName(String qname) {
		return qname.split(":")[0];
	}
	
	public static String extractLocalNameFromAlfQName(String qname) {
		return qname.split(":")[1];
	}
	
	public static void addSimpleNameTag(ModelElement o) {
		Tag createTag = CommonFactory.eINSTANCE.createTag();
		createTag.setKey("simpleName");		
		createTag.setValue("true");
		o.getTags().add(createTag);
	}
}
