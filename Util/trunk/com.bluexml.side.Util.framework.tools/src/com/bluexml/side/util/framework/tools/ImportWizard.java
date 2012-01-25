package com.bluexml.side.util.framework.tools;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import com.bluexml.side.util.alfresco.tools.ToolingUtils;

public class ImportWizard extends Wizard implements IImportWizard {

	public ImportWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish() {
		ImportPage page = (ImportPage) getContainer().getCurrentPage();
		String libraryId = page.getFieldValueString(ImportPage.Fields.library.toString());
		// import library
		try {
			ToolingUtils.importLibrary(libraryId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void addPages() {
		addPage(new ImportPage());
	}

}
