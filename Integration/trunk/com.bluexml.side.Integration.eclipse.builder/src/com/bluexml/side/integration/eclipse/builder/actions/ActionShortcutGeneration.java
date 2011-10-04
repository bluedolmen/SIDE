package com.bluexml.side.integration.eclipse.builder.actions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import com.bluexml.side.application.ui.menu.DynamicMenuAction;
import com.bluexml.side.integration.eclipse.builder.settings.SIDEBuilderConfiguration;

public class ActionShortcutGeneration extends DynamicMenuAction {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.application.ui.menu.DynamicMenuAction#setApplication()
	 */
	@Override
	protected IFile setApplication() throws FileNotFoundException, IOException {
		final IProject project = (IProject) selection.getFirstElement();
		SIDEBuilderConfiguration sideconf = new SIDEBuilderConfiguration(project);
		if (sideconf.load()) {
			application= sideconf.getApplication();
			return sideconf.getApplicationFile();
		}
		return null;
	}

}
