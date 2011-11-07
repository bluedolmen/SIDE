package com.bluexml.side.form.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class FormResource extends XMIResourceImpl {

	public FormResource() {
		super();
	}
	
	public FormResource(URI uri) {
		super(uri);
	}
	
	@Override
	protected boolean useUUIDs() {
		return true;
	}
}
