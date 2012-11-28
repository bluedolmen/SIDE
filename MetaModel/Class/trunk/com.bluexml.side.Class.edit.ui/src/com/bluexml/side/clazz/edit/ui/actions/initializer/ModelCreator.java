package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.clazz.edit.ui.Messages;
import com.bluexml.side.util.libs.eclipse.ProjectNatureHelper;
import com.bluexml.side.util.libs.ecore.EResourceUtils;
import com.bluexml.side.util.libs.ui.UIUtils;

public abstract class ModelCreator {
	
	public static final String NATURE_ID = "com.bluexml.side.integration.eclipse.nature"; //$NON-NLS-1$
	public static final String NATURE_WithBuilder_ID = "com.bluexml.side.integration.eclipse.nature.activatedBuilder"; //$NON-NLS-1$
	
	protected final IProject project;
	protected final String newModelExt;
	protected IPath newModelPath;

	protected final String modelTypeSegment;
	protected final InitializerRegister register;

	// STATE
	protected boolean initialized = false;
	protected boolean headless = false;
	protected ASK_USER ask;
	protected EObject newRootObject;
	public final Set<Class<? extends ModelCreator>> dependencies = new HashSet<Class<? extends ModelCreator>>();

	protected abstract Command initialize(EditingDomain editingDomain) throws Exception;

	protected abstract void headLessInitialize() throws Exception;

	protected abstract void createRootObject();

	public ModelCreator(IProject project, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask, String modelFileName) throws IOException {
		this(project, newModelExt, modelTypeSegment, register, ask);
		newModelPath = getNewModelPath(modelFileName);
	}
	
	public ModelCreator(IProject project, String newModelExt, String modelTypeSegment, InitializerRegister register, ASK_USER ask) throws IOException {
		this.newModelExt = newModelExt;
		this.modelTypeSegment = modelTypeSegment;
		this.project = project;		
		this.register = register;
		this.ask = ask;
		setHeadless();
	}
	
	private void setHeadless() {
		try {
			// Use 'workbench is running' information since this is the method used
			// to run the initialize() method
			headless = ! PlatformUI.isWorkbenchRunning();
		} catch (Exception e) {
			// Any exception occurred => force headless mode
			headless = true;
		}
	}

	protected IPath getNewModelPath(String modelFileName) {
		// get folder where to create new model
		IPath newModelParentPath = project.getFullPath();
		
		if (ProjectNatureHelper.hasNature(project, NATURE_ID) || ProjectNatureHelper.hasNature(project, NATURE_WithBuilder_ID)) {
			// side layout
			// get models path			
			newModelParentPath = newModelParentPath.append(ModelInitializationUtils.SRC_MODELS_PATH).append(modelTypeSegment);
		}
		
		return newModelParentPath.append(modelFileName + newModelExt);
	}

	protected void saveNewModel() throws Exception {
		//		EResourceUtils.saveModel(newModelPath.toFile(), newRootObject);
		EResourceUtils.saveModel(getNewModelIFile(), newRootObject);
	}

	public IFile getNewModelIFile() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		//		IFile newModelFile = (IFile) workspaceRoot.getFileForLocation(newModelPath);
		IFile newModelFile = (IFile) workspaceRoot.getFile(newModelPath);
		return newModelFile;
	}

	protected void postInitialization(IFile newModelFile, IWorkbenchPage page, IEditorDescriptor desc, IEditorPart editorPart) throws Exception {
		page.closeEditor(editorPart, false);
		// save initialized resource
		saveNewModel();

		// open editor
//		page.openEditor(new FileEditorInput(newModelFile), desc.getId());
	}

	public String getModelName() {
		return getNewFileName().replace(newModelExt, ""); //$NON-NLS-1$
	}

	public String getNewFileName() {
		return newModelPath.lastSegment();
	}

	/**
	 * @return the newModelPath
	 */
	public IPath getNewModelPath() {
		return newModelPath;
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
		ASK, SKIP, OVERRIDE, UPDATE
	}

	protected static EObject openModel(IFile classModel) throws IOException {
		EList<EObject> l = EResourceUtils.openModel(classModel);
		return l.get(0);
	}

	public void initialize() throws Exception {
		if (initialized) {
			return;
		}

		// execute initialize on dependencies
		for (Class<? extends ModelCreator> m : dependencies) {
			Map<String, ModelCreator> initializers2 = register.getInitializers(m);
			if (initializers2 != null) {
				Collection<ModelCreator> initializers = initializers2.values();
				for (ModelCreator modelInitializer : initializers) {
					// cascading initializing, beware of circle dependencies ...
					modelInitializer.initialize();
				}
			} else {
				System.out.println("WARN instance of " + m + " not found for " + this);
			}

		}

		project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		// get new Resource
		final IFile newModelFile = getNewModelIFile();
		final Boolean doWork[] = new Boolean[]{true};
		boolean exists = newModelFile.exists();

		if (exists && !headless) {
			if (ask.equals(ASK_USER.ASK)) {
				// open warning dialog to ask user about overriding existing models
				Display currentDisp = ApplicationUtil.getDisplay();
				currentDisp.syncExec(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						doWork[0] = UIUtils.showConfirmation(Messages.ModelCreator_3 + newModelFile.getName() + Messages.ModelCreator_4, Messages.ModelCreator_5);		
					}
				});
				
			} else if (ask.equals(ASK_USER.SKIP)) {
				// do no ask user, just skip initializing
				doWork[0] = false;
			}
		}

		if (doWork[0]) {
			// the UDPATE case need to use current existing model, if implemented update is done instead of overriding model
			final boolean update = exists && this instanceof ModelUpdater;
			ModelUpdater modelUpdater = null;
			System.out.println("update ? " + update);
			System.out.println(this);
			if (update) {
				modelUpdater = (ModelUpdater) this;
				modelUpdater.loadTargetModel();
			} else {
				// create new Resource and save it to file system so editor can open it
				ResourceSet resourceSet = new ResourceSetImpl();
				URI fileURI = URI.createPlatformResourceURI(newModelFile.getFullPath().toString(), true);
				Resource resource = resourceSet.createResource(fileURI);
				Map<Object, Object> options = new HashMap<Object, Object>();
				options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
				resource.save(options);
				createRootObject();
			}

			if (headless) {
				if (update) {
					modelUpdater.headLessUpdate();
				} else {
					headLessInitialize();
				}
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

								Command creationCommand = null;
								EditingDomain editingDomain = editor.getEditingDomain();
								if (update) {
									creationCommand = ((ModelUpdater) ModelCreator.this).update(editingDomain);
								} else {
									creationCommand = initialize(editingDomain);
								}

								editingDomain.getCommandStack().execute(creationCommand);
								postInitialization(newModelFile, page, desc, editorPart);
							} catch (Exception e) {
								e.printStackTrace();
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
}
