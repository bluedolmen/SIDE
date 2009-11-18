package com.bluexml.side.portal.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class PortalResource extends XMIResourceImpl {

	public PortalResource() {
		super();
	}
	
	public PortalResource(URI uri) {
		super(uri);
	}
	
	@Override
	protected boolean useUUIDs() {
		return true;
	}
}
