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
package com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import com.bluexml.side.clazz.edit.ui.Activator;
import com.bluexml.side.clazz.edit.ui.Messages;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass.pages.InitializerPageWelcome;

public class InitializerWizard extends Wizard implements IWorkbenchWizard {

	private IFile classModel;
	InitializerRegister ini;
	boolean side_gene;

	/**
	 * @return the ini
	 */
	public InitializerRegister getIni() {
		return ini;
	}

	/**
	 * @return the side_gene
	 */
	public boolean isSide_gene() {
		return side_gene;
	}

	public InitializerWizard(IFile classModel) {
		this.classModel = classModel;
		setNeedsProgressMonitor(true);
		this.setWindowTitle(Messages.InitializerWizard_0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		addPage(new InitializerPageWelcome());
	}

	@Override
	public boolean performFinish() {
		try {
			System.out.println("InitializerWizard.performFinish()"); //$NON-NLS-1$
			InitializerPageWelcome page = (InitializerPageWelcome) getContainer().getCurrentPage();
			final String alf_home = page.getFieldValueString(InitializerPageWelcome.Fields.alfresco_home.toString());
			final String alf_ver = page.getFieldValueString(InitializerPageWelcome.Fields.alfresco_version.toString());
			side_gene = Boolean.parseBoolean(page.getFieldValueString(InitializerPageWelcome.Fields.generate.toString()));

			this.ini = InitializerRegister.getInitializerRegisterFromClassModel(classModel, alf_home, alf_ver);

			final InitializerRegister ini = this.ini;

			// run as long running process
			IRunnableWithProgress runPro = new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.InitializerWizard_2, -1);
					// initialize models
					try {
						//TODO use fork to run this job and use syncExec only for action than require to run in UI thread instead of do all the job in UI thread
						// so progress bar can be updated ...
						ini.initialize();
						classModel.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}
					monitor.done();

				}
			};

			this.getContainer().run(true, true, runPro);

			while (!ini.isInitialized()) { //$NON-NLS-1$
				try {
					this.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Alfresco Extension project creation fail !", e)); //$NON-NLS-1$
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Alfresco Extension project creation fail !", e)); //$NON-NLS-1$
		}
		System.out.println("InitializerWizard.performFinish() DONE"); //$NON-NLS-1$
		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}

}
