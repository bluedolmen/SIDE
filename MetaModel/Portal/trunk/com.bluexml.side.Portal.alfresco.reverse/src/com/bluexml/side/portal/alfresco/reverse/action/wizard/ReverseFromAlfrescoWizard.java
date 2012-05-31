package com.bluexml.side.portal.alfresco.reverse.action.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.alfresco.reverse.action.wizard.pages.WelcomePage;
import com.bluexml.side.portal.alfresco.reverse.reverser.LayoutReverser;
import com.bluexml.side.portal.alfresco.reverse.reverser.ReversePortal;
import com.bluexml.side.util.alfresco.tools.ToolingUtils;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.eclipse.RunnableWithProgress;
import com.bluexml.side.util.libs.eclipse.RunnableWithState;
import com.bluexml.side.util.libs.eclipse.wizards.AbstractFieldsPage;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class ReverseFromAlfrescoWizard extends Wizard implements IWorkbenchWizard {
	IFolder alfrescoXML;

	public ReverseFromAlfrescoWizard() {
		setNeedsProgressMonitor(true);
		this.setWindowTitle("Reverse Alfresco Models to SIDE Data Models");

	}

	public ReverseFromAlfrescoWizard(IFolder alfrescoXML) {
		this.alfrescoXML = alfrescoXML;
	}

	@Override
	public boolean performFinish() {
		try {
			AbstractFieldsPage page = (AbstractFieldsPage) getPage(WelcomePage.ID);
			String root = page.getFieldValueString(WelcomePage.Fields.root.toString());
			String output = page.getFieldValueString(WelcomePage.Fields.output.toString());

			String libraryId = page.getFieldValueString(WelcomePage.Fields.library.toString());
			final List<IFile> sideModels = new ArrayList<IFile>();
			if (StringUtils.trimToNull(libraryId) != null) {
				// reverse may use some SIDE models (reversed)

				// import library
				ToolingUtils.importLibrary(libraryId);

				// models can be acceded in current workspace
				IConfigurationElement libFromLabel = ToolingUtils.getModelLibraryForId(libraryId);
				String projectId = libFromLabel.getAttribute(ToolingUtils.MODEL_LIBRARY_PROJECT_ID);
				IFolder iFolder = IFileHelper.getIFolder("/" + projectId + "/data");
				sideModels.addAll(IFileHelper.getAllFiles(iFolder));

			}

			final File sideModelRepo = new File(output);
			final File rootFile = new File(root);
			// Convert all Founded IFiles to System.io.File, and avoid duplicate entry

			RunnableWithProgress myRunnable = new RunnableWithProgress() {

				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					current = STATE.started;
					monitor.beginTask("reversing", -1);
					try {
						if (rootFile.isFile()) {
							Portal createPortal = PortalFactory.eINSTANCE.createPortal();
							LayoutReverser lr = new LayoutReverser(rootFile, createPortal, rootFile.getAbsolutePath());
							lr.parse();
							EResourceUtils.saveModel(new File("/Users/davidabad/workspaces/runtime-New_configuration/Model-library-Alfresco-4.0.d-CE/portal/alfresco.portal"), createPortal);
						} else {
							ReversePortal rp = new ReversePortal(rootFile, sideModelRepo);
							
							rp.reverse();
							rp.persist();
						}

					} catch (Exception e) {
						throw new InvocationTargetException(e, "Error while executing reverse");
					}
					monitor.done();
					current = STATE.finished;
				}
			};

			this.getContainer().run(true, true, myRunnable);

			while (!myRunnable.getState().equals(RunnableWithState.STATE.finished)) {
				wait(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			WizardPage currentPage = (WizardPage) getContainer().getCurrentPage();
			currentPage.setErrorMessage(e.getLocalizedMessage());
			return false;
		}
		return false;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		System.out.println("init");
		System.out.println(selection);

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		System.out.println("add page");
		WelcomePage wp = new WelcomePage(alfrescoXML);
		addPage(wp);
	}

}
