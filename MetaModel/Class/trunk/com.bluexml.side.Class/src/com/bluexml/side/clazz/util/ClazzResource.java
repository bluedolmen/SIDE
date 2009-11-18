package com.bluexml.side.clazz.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class ClazzResource extends XMIResourceImpl {

	public ClazzResource() {
		super();
	}
	
	public ClazzResource(URI uri) {
		super(uri);
	}
	
	@Override
	protected boolean useUUIDs() {
		return true;
	}
}
