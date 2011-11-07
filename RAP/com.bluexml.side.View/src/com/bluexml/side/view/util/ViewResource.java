package com.bluexml.side.view.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class ViewResource extends XMIResourceImpl {

	public ViewResource() {
		super();
	}
	
	public ViewResource(URI uri) {
		super(uri);
	}
	
	@Override
	protected boolean useUUIDs() {
		return true;
	}
}
