package com.bluexml.side.form.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.form.ClassFormCollection;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.clazz.utils.SynchronizeWithClass;
import com.bluexml.side.form.workflow.utils.SynchronizeWorkflowFormWithClass;
import com.bluexml.side.util.libs.eclipse.AbstractIFileJob;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class SynchronizeFormJob extends AbstractIFileJob {
	public SynchronizeFormJob(IFile iFile) {
		super("Synchronize Form", iFile);
	}

	public void execute() throws Exception {
		EObject fc = EResourceUtils.openModel(iFile).get(0);
		if (fc instanceof ClassFormCollection) {
			SynchronizeWithClass swc = new SynchronizeWithClass();
			swc.synchronize((FormCollection) fc);
		} else if (fc instanceof WorkflowFormCollection) {
			SynchronizeWorkflowFormWithClass sw = new SynchronizeWorkflowFormWithClass();
			sw.synchronize((WorkflowFormCollection) fc);
		}
		EResourceUtils.saveModel(iFile, fc);
	}
}