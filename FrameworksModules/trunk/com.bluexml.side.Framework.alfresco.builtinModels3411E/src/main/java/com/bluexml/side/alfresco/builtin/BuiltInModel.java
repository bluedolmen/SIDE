package com.bluexml.side.alfresco.builtin;

import org.alfresco.service.namespace.QName;

public class BuiltInModel {

	public static final String MODEL_URI = "http://www.bluexml.com/model/content/buildInLibraryAlfresco/1.0";
	
	public static final QName TYPE_STRUCTURE_PAGETYPE = QName.createQName(MODEL_URI, "HasTreeFilter");
	public static final QName ASPECT_HasTreeFilter = QName.createQName(MODEL_URI, "HasTreeFilter");
	public static final QName ASPECT_TreeFilter = QName.createQName(MODEL_URI, "TreeFilter");
	public static final QName PROP_TreeFilter_ROOT = QName.createQName(MODEL_URI, "TreeFilter_root");
	public static final QName ASSO_HasTreeFilter = QName.createQName(MODEL_URI, "HasTreeFilter_hasTreeFilter_TreeFilter");
	public static final QName ASSO_TreeFilter_PARENT = QName.createQName(MODEL_URI, "TreeFilter_AssociationTree_TreeFilter");

}
