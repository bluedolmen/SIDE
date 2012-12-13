package com.bluexml.side.alfresco.builtin;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.jscript.ValueConverter;
import org.alfresco.service.namespace.QName;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class TreeNodeScript extends BaseScopableProcessorExtension {

	public Scriptable getTreeNodeChidren(Object search, ScriptNode nodeRef) throws Exception {
		Object[] results = null;

		if (search instanceof Serializable) {
			Serializable obj = new ValueConverter().convertValueForRepo((Serializable) search);
			if (obj instanceof Map) {
				Map<Serializable, Serializable> def = (Map<Serializable, Serializable>) obj;
				QName nodeType = (QName) def.get("nodeType");
				QName rootProperty = (QName) def.get("rootProperty");
				String site = (String) def.get("site");
				String path = (String) def.get("path");
				boolean selectableTypeIsAspect = (Boolean) def.get("selectableTypeIsAspect");
				QName assoType = (QName) def.get("assoType");
				boolean selectableRoot = (Boolean) def.get("selectableRoot");
				String rootName = (String) def.get("rootName");
				DataListTreeHelper dlt = new DataListTreeHelper(assoType, nodeType, rootProperty, path, site, selectableTypeIsAspect, rootName, selectableRoot, null);
				dlt.getTreeNodeChidren(nodeRef.getNodeRef()).toArray();
			}
		}
		return Context.getCurrentContext().newArray(getScope(), results);
	}
}
