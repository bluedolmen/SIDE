package com.bluexml.side.workflow.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class WorkflowResource extends XMIResourceImpl {

	public WorkflowResource() {
		super();
	}
	
	public WorkflowResource(URI uri) {
		super(uri);
	}
	
	@Override
	protected boolean useUUIDs() {
		return true;
	}
}
