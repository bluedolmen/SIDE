package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.Messages;
import com.bluexml.side.util.libs.eclipse.ProjectNatureHelper;
import com.bluexml.side.util.libs.ui.UIUtils;

public abstract class ModelInitializer {
	public static final String NATURE_ID = "com.bluexml.side.integration.eclipse.nature"; //$NON-NLS-1$
	public static final String NATURE_WithBuilder_ID = "com.bluexml.side.integration.eclipse.nature.activatedBuilder"; //$NON-NLS-1$
	public static final String classExt = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.clazz.presentation.ClazzEditorID"); //$NON-NLS-1$

	protected final IFile classModel;
	protected final ClassPackage root;

	protected final IProject project;
	protected final String newModelExt;
	protected final IPath newModelPath;

	protected boolean initialized = false;
	protected boolean headless = false;

	/**
	 * @return the newModelPath
	 */
	public IPath getNewModelPath() {
		return newModelPath;
	}

	protected final String modelTypeSegment;
	protected final InitializerRegister register;

	protected EObject newRootObject;
	protected ASK_USER ask;

	public ModelInitializer(IFile classModel, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		this(classModel, (ClassPackage) ModelInitializationUtils.openModel(classModel).get(0), newModelExt, modelTypeSegment, register, ask, formModelFileName);
	}

	private void setHeadless() {
		try {
			Display display = UIUtils.getDisplay();
			if (display == null) {
				headless = true;
			}
		} catch (Exception e) {
			// no display available force headless mode
			headless = true;
		}
		headless=true;
	}

	public ModelInitializer(IFile classModel, ClassPackage root, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		this.classModel = classModel;
		this.root = root;
		this.newModelExt = newModelExt;
		this.modelTypeSegment = modelTypeSegment;
		this.project = classModel.getProject();
		this.newModelPath = getNewModelPath(classModel, formModelFileName);
		this.register = register;
		this.ask = ask;
		setHeadless();
	}

	protected abstract Command initialize(EditingDomain editingDomain) throws Exception;

	protected abstract void headLessInitialize() throws Exception;

	protected IPath getNewModelPath(IFile classModel, String formModelFileName) {
		IProject project = classModel.getProject();
		// get folder where to create new model
		IPath newModelParentPath;
		if (ProjectNatureHelper.hasNature(project, NATURE_ID) || ProjectNatureHelper.hasNature(project, NATURE_WithBuilder_ID)) {
			// side layout
			// get models path			
			newModelParentPath = classModel.getParent().getParent().getRawLocation().append(modelTypeSegment);
		} else {
			newModelParentPath = classModel.getRawLocation().removeLastSegments(1);
		}

		// get new Model path
		if (formModelFileName == null) {
			formModelFileName = classModel.getFullPath().lastSegment();
			formModelFileName = formModelFileName.replaceAll(classExt, newModelExt);
		}
		return newModelParentPath.append(formModelFileName);
	}

	protected void saveNewModel() throws IOException {
		ModelInitializationUtils.saveModel(newModelPath.toFile(), newRootObject);
	}

	public void initialize() throws Exception {
		project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		// get new Resource
		final IFile newModelFile = getNewModelIFile();
		boolean doWork = true;
		boolean exists = newModelFile.exists();

		if (exists && !headless) {
			if (ask.equals(ASK_USER.ASK)) {
				// open warning dialog to ask user about overriding existing models
				doWork = UIUtils.showConfirmation(Messages.ModelInitializer_3 + newModelFile.getName() + Messages.ModelInitializer_4, Messages.ModelInitializer_5);
			} else if (ask.equals(ASK_USER.SKIP)) {
				// do no ask user, just skip initializing
				doWork = false;
			}
		}

		if (doWork) {
			// create new Resource and save it to file system so editor can open it
			ResourceSet resourceSet = new ResourceSetImpl();
			URI fileURI = URI.createPlatformResourceURI(newModelFile.getFullPath().toString(), true);
			Resource resource = resourceSet.createResource(fileURI);
			Map<Object, Object> options = new HashMap<Object, Object>();
			options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
			resource.save(options);
			if (headless) {
				headLessInitialize();
				saveNewModel();
				initialized = true;
			} else {
				try {
					PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

						public void run() {
							// open editor to initialize model
							IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
							IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(newModelPath.toFile().getName());
							IEditorPart editorPart;
							try {
								editorPart = page.openEditor(new FileEditorInput(newModelFile), desc.getId());

								// initialize model
								IEditingDomainProvider editor = (IEditingDomainProvider) editorPart;

								Command creationCommand = initialize(editor.getEditingDomain());

								editor.getEditingDomain().getCommandStack().execute(creationCommand);
								postInitialization(newModelFile, page, desc, editorPart);
							} catch (Exception e) {
								throw new RuntimeException(e);
							}
						}
					});
				} catch (Throwable e) {
					initialized = false;
					e.printStackTrace();
					return;
				}
				initialized = true;
			}
		}
	}

	public IFile getNewModelIFile() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		IFile newModelFile = (IFile) workspaceRoot.getFileForLocation(newModelPath);
		return newModelFile;
	}

	protected void postInitialization(IFile newModelFile, IWorkbenchPage page, IEditorDescriptor desc, IEditorPart editorPart) throws IOException, PartInitException {
		page.closeEditor(editorPart, false);
		// save initialized resource
		saveNewModel();

		// open editor
		page.openEditor(new FileEditorInput(newModelFile), desc.getId());
	}

	public String getModelName() {
		return getNewFileName().replace(newModelExt, ""); //$NON-NLS-1$
	}

	public String getNewFileName() {
		return newModelPath.lastSegment();
	}

	/**
	 * @return the newRootObject
	 */
	public EObject getNewRootObject() {
		return newRootObject;
	}

	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	public enum ASK_USER {
		ASK, SKIP, OVERRIDE
	}
}
