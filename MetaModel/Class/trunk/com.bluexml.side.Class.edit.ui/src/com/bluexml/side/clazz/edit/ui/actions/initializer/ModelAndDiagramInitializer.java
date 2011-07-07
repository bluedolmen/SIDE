package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class ModelAndDiagramInitializer extends ModelInitializer {
	protected String diagramTypeId;

	public ModelAndDiagramInitializer(IFile classModel, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, newModelExt, modelTypeSegment, register, ask, formModelFileName);
	}

	public ModelAndDiagramInitializer(IFile classModel, ClassPackage root, String newModelExt, String modelTypeSegment, String diagramTypeId, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, root, newModelExt, modelTypeSegment, register, ask, formModelFileName);
		this.diagramTypeId = diagramTypeId;
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer#
	 * initialize()
	 */
	@Override
	public void initialize() throws Exception {
		super.initialize();
		if (!headless) {
			this.initializeDiagram();
		}
	}

	protected void initializeDiagram() throws Exception {
		final IFile diagramFile = IFileHelper.getIFile(new File(newModelPath.toOSString() + "di")); //$NON-NLS-1$
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

			public void run() {
				try {
					//					ModelInitializationUtils.createDiagramFile(newRootObject, diagramTypeId, newModelPath.lastSegment() + "di", diagramFile); //$NON-NLS-1$
					ModelInitializationUtils.openImportDiagram(newRootObject, diagramTypeId);
					//					ModelInitializationUtils.createDiagramFromExistingModel(newRootObject, diagramTypeId);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer#
	 * postInitialization(org.eclipse.core.resources.IFile,
	 * org.eclipse.ui.IWorkbenchPage, org.eclipse.ui.IEditorDescriptor,
	 * org.eclipse.ui.IEditorPart)
	 */
	@Override
	protected void postInitialization(IFile newModelFile, IWorkbenchPage page, IEditorDescriptor desc, IEditorPart editorPart) throws IOException, PartInitException {
		page.closeEditor(editorPart, false);
		// save initialized resource
		saveNewModel();
	}

}