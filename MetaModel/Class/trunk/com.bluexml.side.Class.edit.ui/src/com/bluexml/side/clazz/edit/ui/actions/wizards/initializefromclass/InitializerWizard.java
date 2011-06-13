package com.bluexml.side.clazz.edit.ui.actions.wizards.initializefromclass;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.Activator;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ApplicationModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer.ASK_USER;
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
		this.setWindowTitle("Initialize SIDE Models");
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		super.addPages();
		addPage(new InitializerPageWelcome());
	}

	@Override
	public boolean performFinish() {
		try {
			System.out.println("InitializerWizard.performFinish()");
			InitializerPageWelcome page = (InitializerPageWelcome) getContainer().getCurrentPage();
			final String alf_home = page.getFieldValue(InitializerPageWelcome.Fields.alfresco_home.toString());
			final String alf_ver = page.getFieldValue(InitializerPageWelcome.Fields.alfresco_version.toString());
			side_gene = Boolean.parseBoolean(page.getFieldValue(InitializerPageWelcome.Fields.generate.toString()));

			this.ini = getInitializerRegister(alf_home, alf_ver);

			final InitializerRegister ini = this.ini;

			// run as long running process
			IRunnableWithProgress runPro = new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					Thread current = Thread.currentThread();
					Thread ui = Display.getDefault().getThread();
					System.out.println(current);
					System.out.println(ui);

					monitor.beginTask("Start initializing SIDE Models", -1);
					// initialize models
					try {
						//TODO use fork to run this job and use syncExec only for action than require to run in UI thread instead of do all the job in UI thread
						// so progress bar can be updated ...
						initialize(ini);

					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}
					monitor.done();

				}
			};

			this.getContainer().run(true, true, runPro);

			System.out.println("InitializerWizard.performFinish() DONE");
		} catch (Exception e) {
			e.printStackTrace();
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Alfresco Extension project creation fail !", e)); //$NON-NLS-1$
		}
		while (!ini.getApplicationInitializer().get("").isInitialized()) {
			try {
				this.wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	private InitializerRegister getInitializerRegister(String alf_home, String alf_ver) throws Exception {
		InitializerRegister initilizerRegister = null;
		ClassPackage cp = openModel(classModel);

		initilizerRegister = InitializerRegister.getDefaultInitializerRegister(classModel, cp, ASK_USER.OVERRIDE);

		ApplicationModelInitializer applicationModelInit = new ApplicationModelInitializer(classModel, cp, initilizerRegister, ASK_USER.OVERRIDE, null, alf_ver, alf_home);
		// add ApplicationInitializer to register
		initilizerRegister.getApplicationInitializer().put("", applicationModelInit);

		return initilizerRegister;
	}

	private void initialize(InitializerRegister initilizerRegister) throws Exception, CoreException {
		for (ModelInitializer initializer : initilizerRegister.getViewInitializer().values()) {
			initializer.initialize();
		}
		for (ModelInitializer initializer : initilizerRegister.getFormInitializer().values()) {
			initializer.initialize();
		}
		for (ModelInitializer initializer : initilizerRegister.getPortalInitializer().values()) {
			initializer.initialize();
		}
		for (ModelInitializer initializer : initilizerRegister.getApplicationInitializer().values()) {
			initializer.initialize();
		}

		classModel.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	private static ClassPackage openModel(IFile classModel) throws IOException {
		EList<?> l = ModelInitializationUtils.openModel(classModel);
		return (ClassPackage) l.get(0);
	}
}
