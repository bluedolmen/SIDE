/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.util.generator.acceleo.chain;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import fr.obeo.acceleo.chain.impl.spec.CChain;
import fr.obeo.acceleo.chain.impl.spec.CGenerate;
import fr.obeo.acceleo.chain.tools.CObject;
import fr.obeo.acceleo.chain.tools.PreConditionGenFilter;
import fr.obeo.acceleo.gen.IGenFilter;
import fr.obeo.acceleo.gen.phantom.ChainPhantomProvider;
import fr.obeo.acceleo.gen.template.TemplateElement;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;
import fr.obeo.acceleo.tools.resources.Resources;

public class CustomCChain extends CChain {

	public List<?> getGeneratedFiles() {
		return generatedFiles;
	}

	public void launch(IGenFilter genFilter, IProgressMonitor monitor, LaunchManager mode) throws CoreException {
		
		if (mode.isProfiling()) {
			TemplateElement.getProfiler().start(getFile());
		}
		try {
			launchSub(genFilter, monitor, mode);
		} finally {
			if (mode.isProfiling()) {
				TemplateElement.getProfiler().stop();
			}
		}
	}
	
	private void launchSub(IGenFilter genFilter, IProgressMonitor monitor, LaunchManager mode) throws CoreException {
		//Line disabled
		//AcceleoConsole.getDefault().asDefaultForSystemOut();
		if (mode.getMode() == LaunchManager.PREVIEW_MODE) {
			if (file != null) {
				final IContainer targetBackup = Resources.getContainer(file.getProject(), new Path(CGenerate.PHANTOM_PREVIEW_FOLDER_BACKUP), monitor);
				if (targetBackup.isAccessible()) {
					targetBackup.delete(true, monitor);
				}
				final IContainer target = Resources.getContainer(file.getProject(), new Path(CGenerate.PHANTOM_PREVIEW_FOLDER_GENERATED), monitor);
				if (target.isAccessible()) {
					target.delete(true, monitor);
				}
			}
		} else {
			ChainPhantomProvider.getDefault().clearForUpdate(file);
			genFilter = new PreConditionGenFilter(this, genFilter, monitor, mode);
		}
		// Chain actions
		generatedFiles.clear();
		mode.setMonitor(monitor);
		try {
			final Iterator<?> actions = getActions().iterator();
			while (actions.hasNext()) {
				final CObject action = (CObject) actions.next();
				action.launch(this, genFilter, monitor, mode);
			}
			if (getPostGenerationAction() != null) {
				getPostGenerationAction().postProcessing(monitor);
			}
		} finally {
			CGenerate.clearModelContext();
			mode.setMonitor(null);
			ChainPhantomProvider.getDefault().update(getFile(), (IFile[]) generatedFiles.toArray(new IFile[generatedFiles.size()]), monitor);
		}
	}
}
