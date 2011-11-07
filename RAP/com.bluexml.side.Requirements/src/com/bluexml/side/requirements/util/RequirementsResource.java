package com.bluexml.side.requirements.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class RequirementsResource extends XMIResourceImpl {

	public RequirementsResource() {
		super();
	}
	
	public RequirementsResource(URI uri) {
		super(uri);
	}
	
	@Override
	protected boolean useUUIDs() {
		return true;
	}
}
