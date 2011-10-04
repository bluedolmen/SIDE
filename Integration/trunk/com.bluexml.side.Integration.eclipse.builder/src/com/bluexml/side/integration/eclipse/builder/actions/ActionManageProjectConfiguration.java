package com.bluexml.side.integration.eclipse.builder.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;

import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.integration.eclipse.builder.settings.SIDEBuilderConfiguration;
import com.bluexml.side.util.libs.ui.UIUtils;

public class ActionManageProjectConfiguration extends com.bluexml.side.application.ui.action.CreateGenerationConfiguration {

	@Override
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) selection;
			if (iss.getFirstElement() instanceof IProject) {
				final IProject project = (IProject) iss.getFirstElement();
				IFile rwm_model = null;
				// load sideProject configuration
				SIDEBuilderConfiguration sideconf = new SIDEBuilderConfiguration(project);
				if (sideconf.load()) {
					rwm_model = sideconf.getApplicationFile();
				} else {
					UIUtils.showError("Configuration error","please check SIDE Project properties");
				}

				// We open a dialog only if no dialog already open on it
				if (!inUse(rwm_model)) {
					Shell shell = new Shell();

					ApplicationDialog dialog = new ApplicationDialog(shell, rwm_model);
					addFileUnUse(rwm_model);
					dialog.open();
					removeFileUnUse(rwm_model);
				}
			} else {
				UIUtils.showError(Activator.Messages.getString("Erreur.Title.1"), Activator.Messages.getString("Erreur.Msg.1")); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}
}
