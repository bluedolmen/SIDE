package com.bluexml.side.application.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class ApplicationResource extends XMIResourceImpl {

	public ApplicationResource() {
		super();
	}
	
	public ApplicationResource(URI uri) {
		super(uri);
	}
	
	@Override
	protected boolean useUUIDs() {
		return true;
	}
}
