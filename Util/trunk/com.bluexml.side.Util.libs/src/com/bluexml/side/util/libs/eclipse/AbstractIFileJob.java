/**
 * 
 */
package com.bluexml.side.util.libs.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;

import com.bluexml.side.util.libs.Activator;

public abstract class AbstractIFileJob extends WorkspaceJob {

	protected IFile iFile;

	public AbstractIFileJob(String name, IFile iFile) {
		super(name);
		this.iFile = iFile;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		try {
			execute();
			iFile.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
		}
		return new Status(IStatus.OK, Activator.PLUGIN_ID, "initialization done");
	}

	protected abstract void execute() throws Exception;

}