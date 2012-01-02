package com.bluexml.side.integration.eclipse.builder.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import com.bluexml.side.clazz.edit.ui.actions.SynchronizeModels;
import com.bluexml.side.integration.eclipse.builder.settings.SIDEBuilderConfiguration;

public class ActionSynchronizeModels extends SynchronizeModels {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.clazz.edit.ui.actions.SynchronizeModels#getApplication()
	 */
	@Override
	protected IFile getApplication() {
		IProject project = (IProject) this._selection.getFirstElement();
		SIDEBuilderConfiguration sideconf = new SIDEBuilderConfiguration(project);
		if (sideconf.load()) {
			return sideconf.getApplicationFile();
		}
		return null;
	}

}
