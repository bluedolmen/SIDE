package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;

import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.NewSIDEProjectCreator.CreateOptions.InvalidCreateOptionsException;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newSideProject.project.ProjectGenerator;
import com.bluexml.side.Util.ecore.SIDEEditorUtils;
import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.alfresco.reverse.library.ModelLibrary;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator.ASK_USER;
import com.bluexml.side.clazz.edit.ui.actions.initializer.creator.ApplicationCreator;
import com.bluexml.side.clazz.edit.ui.actions.initializer.creator.ClassModelCreator;
import com.bluexml.side.clazz.edit.ui.actions.initializer.creator.WorkflowModelCreator;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.integration.eclipse.builder.nature.SIDENature;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.requirements.RequirementsFactory;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.eclipse.WorkspaceModifyOperation;
import com.bluexml.side.view.ViewFactory;

public class NewSIDEProjectCreator {

	static final String DEFAULT_SRC_DIR = "src"; //$NON-NLS-1$ // should be defined in a reusable layer ?

	static final String DEFAULT_LOG_DIR = "build/logs"; //$NON-NLS-1$
	static final String DEFAULT_GEN_DIR = "build/generated"; //$NON-NLS-1$

	private static final String[] srcDirs = { 
		"models", //$NON-NLS-1$
		"modules/mavenProjects", //$NON-NLS-1$ 
		"configs/commonConf/webapps/alfresco", //$NON-NLS-1$
		"configs/commonConf/webapps/share", //$NON-NLS-1$
		"configs/" + System.getProperty("user.name") + "/webapps/alfresco", //$NON-NLS-1$
		"configs/" + System.getProperty("user.name") + "/webapps/share" //$NON-NLS-1$
	};
	private static final String[] rootDirs = { ".settings" }; //$NON-NLS-1$
	

	private final List<IFile> createdModels = new ArrayList<IFile>();
	private final IProject newProject;
	private final CreateOptions createOptions;
	private final URI location;
	private final String baseModelName;
	

	
	public static enum CreateFields {
		
		BASE_DATA_MODEL_PACKAGES (String.class), 
		BASE_MODEL_NAME (String.class), 
		DO_CREATE_DATA_MODEL (Boolean.class, Boolean.FALSE), 
		DO_CREATE_FORM_MODEL (Boolean.class, Boolean.FALSE), 
		DO_CREATE_WORKFLOW_MODEL (Boolean.class, Boolean.FALSE), 
		DO_CREATE_PORTAL_MODEL (Boolean.class, Boolean.FALSE), 
		DO_CREATE_VIEW_MODEL (Boolean.class, Boolean.FALSE), 
		DO_CREATE_REQUIREMENT_MODEL (Boolean.class, Boolean.FALSE), 
		TECHNOLOGY (String.class), 
		ALFRESCO_HOME (String.class);
		
		private final Class<?> type;
		private final Object defaultValue;
		
		private CreateFields(Class<?> type) {
			this.type = type;
			this.defaultValue = null;
		}

		private CreateFields(Class<?> type, Object defaultValue) {
			this.type = type;
			this.defaultValue = defaultValue;
		}

		private Object getDefaultValue() {
			return defaultValue;
		}
		
		public Class<?> getType() {
			return type;
		}
		
	}

	/**
	 * Options used to create a new SIDE Project.
	 * 
	 * @author pajot-b
	 *
	 */
	public static class CreateOptions {
		
		public static final class InvalidCreateOptionsException extends Exception {
			private static final long serialVersionUID = 1L;
			
			public InvalidCreateOptionsException(String message) {
				super(message);
			}
			
		}
		
		private final Map<CreateFields, Object> values = new HashMap<NewSIDEProjectCreator.CreateFields, Object>();
		
		/**
		 * Set a field value.
		 * <p>
		 * Allows chaining by returning this
		 * 
		 * @param field
		 * @param value
		 * @return
		 */
		public CreateOptions setValue(CreateFields field, Object value) {
			values.put(field, value);
			
			return this;
		}
				
		/**
		 * Gets the value of the {@link CreateFields} field converting (and not
		 * only casting) result as the given type if possible (if the conversion
		 * is implemented)
		 * 
		 * @param field
		 * @param type
		 * @return
		 */
		public <T> T getCheckedValue(CreateFields field, Class<T> type) {

			if (!field.getType().equals(type)) {
				throw new UnsupportedOperationException("The provided type is incompatible with the field type. This operation is not implemented.");
			}

			Object value = values.get(field);			
			Object result = null;

			if (value == null) {
				result = field.getDefaultValue();
				
			} else if (type.isInstance(value)) {
				result = type.cast(value);
				
			} else if (String.class.equals(type)) {
				result = value.toString();
				
			} else if (Boolean.class.equals(type)) {
				if (value instanceof String) {
					result = Boolean.parseBoolean((String) value);
				}
				
			} else {
				throw new UnsupportedOperationException("Do not know how to cast the provided value in the targeted type " + type.getName());
			}
			
			return type.cast(result);
		}
		
		/**
		 * Same as previous, checking if the value is mandatory.
		 * 
		 * @param field
		 * @param type
		 * @param isValueMandatory
		 * @return
		 * @throws InvalidCreateOptionsException
		 */
		public <T> T getCheckedValue(CreateFields field, Class<T> type, boolean isValueMandatory) throws InvalidCreateOptionsException {
			T value = getCheckedValue(field, type);
			if (value == null) {
				throw new InvalidCreateOptionsException("The value of the field '" + field.name() + "' is mandatory.");
			}
			
			return value;
		}
	}
	
	
	public NewSIDEProjectCreator(IProject newProject, CreateOptions createOptions, URI location) {
		
		if (newProject == null) {
			throw new IllegalArgumentException("The provided IProject has to be non-null");
		}
		
		if (createOptions == null) {
			createOptions = new CreateOptions();
		}
		
		this.newProject = newProject;
		this.createOptions = createOptions;
		this.location = location;
		
		try {
			baseModelName = createOptions.getCheckedValue(CreateFields.BASE_MODEL_NAME, String.class, true);
		} catch (InvalidCreateOptionsException e) {
			throw new IllegalArgumentException(e.getLocalizedMessage());
		}
		
	}

	
	/**
	 * Attempts to select and reveal the specified resource in all parts within
	 * the supplied workbench window's active page.
	 * <p>
	 * Checks all parts in the active page to see if they implement
	 * <code>ISetSelectionTarget</code>, either directly or as an adapter. If
	 * so, tells the part to select and reveal the specified resource.
	 * </p>
	 * 
	 * @param resource
	 *            the resource to be selected and revealed
	 * @param window
	 *            the workbench window to select and reveal the resource
	 * @see ISetSelectionTarget
	 */
	static void selectAndReveal(IResource resource, IWorkbenchWindow window) {
		// validate the input
		if ((window == null) || (resource == null)) {
			return;
		}
		IWorkbenchPage page = window.getActivePage();
		if (page == null) {
			return;
		}

		// get all the view and editor parts
		List<IWorkbenchPart> parts = new ArrayList<IWorkbenchPart>();
		IWorkbenchPartReference refs[] = page.getViewReferences();
		for (IWorkbenchPartReference ref : refs) {
			IWorkbenchPart part = ref.getPart(false);
			if (part != null) {
				parts.add(part);
			}
		}
		refs = page.getEditorReferences();
		for (IWorkbenchPartReference ref : refs) {
			if (ref.getPart(false) != null) {
				parts.add(ref.getPart(false));
			}
		}

		final ISelection selection = new StructuredSelection(resource);
		Iterator<IWorkbenchPart> itr = parts.iterator();
		while (itr.hasNext()) {
			IWorkbenchPart part = itr.next();

			// get the part's ISetSelectionTarget implementation
			ISetSelectionTarget target = null;
			if (part instanceof ISetSelectionTarget) {
				target = (ISetSelectionTarget) part;
			} else {
				target = (ISetSelectionTarget) part.getAdapter(ISetSelectionTarget.class);
			}

			if (target != null) {
				// select and reveal resource
				final ISetSelectionTarget finalTarget = target;
				window.getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						finalTarget.selectReveal(selection);
					}
				});
			}
		}

	}

	public IWorkspaceRunnable createWorkspaceRunnable() {

		return new IWorkspaceRunnable() {
			
			public void run(IProgressMonitor monitor) throws CoreException {
				new CreationTaskWithMonitor(monitor).run();
			}
			
		};
		
	}
	
	public WorkspaceModifyOperation createWorkspaceModifyOperation() {
		
		return new WorkspaceModifyOperation() {
			
			@Override
			protected void start(IProgressMonitor monitor) throws CoreException {
				new CreationTaskWithMonitor(monitor).run();
			}

		};

	}
	
	/**
	 * A {@link CreationTaskWithMonitor} uses a {@link IProgressMonitor} to display the
	 * progress of the creation of the new side project.
	 * 
	 * @author pajot-b
	 * 
	 */
	private class CreationTaskWithMonitor {

		private final IProgressMonitor monitor;
		
		private CreationTaskWithMonitor(IProgressMonitor monitor) {
			this.monitor = monitor;
		}
		
		private void run() throws CoreException {
			try {
				monitor.beginTask("Creating SIDE project", IProgressMonitor.UNKNOWN);

				// create project
				createNewProjectAsSIDEProject();
				configureAlfrescoImportedLibrary();
				createDirectoryStructure();

				// create additional files build.xml etc ...
				ProjectGenerator.generate(newProject);

				newProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);

				monitor.subTask("Creating initial models");
				initializeModels(newProject);

			} catch (CoreException coreException) {
				throw coreException;
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (PlatformUI.isWorkbenchRunning()) {
				selectAndReveal(newProject, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			}
			
			monitor.done();			
		}
		
		private void createNewProjectAsSIDEProject() throws Exception {
	
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			
			final IProjectDescription description = workspace.newProjectDescription(newProject.getName());
			description.setLocationURI(location);
			newProject.create(description, monitor);
	
			// open project
			newProject.open(IResource.BACKGROUND_REFRESH, monitor);
	
			//Add the SIDE nature
			IProjectDescription desc = newProject.getDescription();
			String[] natures = desc.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = SIDENature.NATURE_ID;
			desc.setNatureIds(newNatures);
			newProject.setDescription(desc, monitor);
			
		}
	
		private void configureAlfrescoImportedLibrary() throws Exception {
			monitor.subTask("Importing Alfresco Library Project");
			
			// import library
			String alfrescoVersion = createOptions.getCheckedValue(CreateFields.TECHNOLOGY, String.class); 
			IProject importLibrary = null;
			if (alfrescoVersion != null && !alfrescoVersion.isEmpty()) {
				importLibrary = ModelLibrary.importLibrary(alfrescoVersion);

				if (importLibrary != null) {
					// add referenced project
					IProject[] importedProjects = new IProject[] { importLibrary };
					IProjectDescription description = newProject.getDescription();
					description.setReferencedProjects(importedProjects);
					newProject.setDescription(description, monitor);
				}

			}
			
		}
		
		private void createDirectoryStructure() throws CoreException {
			monitor.subTask("Creating Directory Structure");
	
			// Folders
			for (String dir : srcDirs) {
				prepareFolder(newProject.getFolder(NewSIDEProjectCreator.DEFAULT_SRC_DIR).getFolder(dir));
			}
			
			IFolder modelsFolder = ModelInitializationUtils.getModelsFolder(newProject);
			for (String modelDirectory : ModelInitializationUtils.getDeclaredModelFolderNames()) {
				IFolder newModelFolder = modelsFolder.getFolder(modelDirectory);
				prepareFolder(newModelFolder);
			}
			
			for (String dir : rootDirs) {
				prepareFolder(newProject.getFolder(dir));
			}
			
			prepareFolder(newProject.getFolder(NewSIDEProjectCreator.DEFAULT_GEN_DIR));
			prepareFolder(newProject.getFolder(NewSIDEProjectCreator.DEFAULT_LOG_DIR));		
		}
	
		private IFolder prepareFolder(IFolder folder) throws CoreException {
			IContainer parent = folder.getParent();
			if (parent instanceof IFolder) {
				prepareFolder((IFolder) parent);
			}
			if (!folder.exists()) {
				folder.create(true, true, monitor);
			}
			return folder;
		}
	
		private void initializeModels(IProject project) throws Exception {
			
			String alfrescoVersion = createOptions.getCheckedValue(CreateFields.TECHNOLOGY, String.class); 
			String alfrescoHome = createOptions.getCheckedValue(CreateFields.ALFRESCO_HOME, String.class);
			String packages = createOptions.getCheckedValue(CreateFields.BASE_DATA_MODEL_PACKAGES, String.class);
			
			// use old model creation for empty models
			// TODO replace usage of NewSIDEProjectCreator by ModelCreator instance as used for dt and workflow
			if (createOptions.getCheckedValue(CreateFields.DO_CREATE_FORM_MODEL, Boolean.class)) {
				createInitialModel(
						SIDEEditorUtils.FORM_MODEL_EDITOR_ID, 
						FormFactory.eINSTANCE.createClassFormCollection()
				);
			}
			
			if (createOptions.getCheckedValue(CreateFields.DO_CREATE_PORTAL_MODEL, Boolean.class)) {
				createInitialModelWithDiagram(
						SIDEEditorUtils.PORTAL_MODEL_EDITOR_ID, 
						PortalFactory.eINSTANCE.createPortal(), 
						"com.bluexml.side.Portal.modeler.diagram"
				);
			}
			
			if (createOptions.getCheckedValue(CreateFields.DO_CREATE_REQUIREMENT_MODEL, Boolean.class)) {
				createInitialModelWithDiagram(
						SIDEEditorUtils.REQUIREMENTS_MODEL_EDITOR_ID, 
						RequirementsFactory.eINSTANCE.createRequirementsDefinition(), 
						"com.bluexml.side.Requirements.modeler.goalDiagram"
				);
			}
			
			if (createOptions.getCheckedValue(CreateFields.DO_CREATE_VIEW_MODEL, Boolean.class)) {
				createInitialModel(
						SIDEEditorUtils.VIEW_MODEL_EDITOR_ID, 
						ViewFactory.eINSTANCE.createViewCollection()
				);
			}
	
			// use new Initializer to manage created models
			ASK_USER askUser = ASK_USER.OVERRIDE;
	
			String modelFileName = project.getName();
			InitializerRegister initializerRegister = new InitializerRegister();
			
			if (createOptions.getCheckedValue(CreateFields.DO_CREATE_DATA_MODEL, Boolean.class)) {
				ClassModelCreator cmi = new ClassModelCreator(project, initializerRegister, askUser, modelFileName, packages);
				initializerRegister.recordInitializer(InitializerRegister.DEFAULT_INITIALIZER_KEY, cmi);
			}
			
			if (createOptions.getCheckedValue(CreateFields.DO_CREATE_WORKFLOW_MODEL, Boolean.class)) {
				WorkflowModelCreator wmi = new WorkflowModelCreator(project, initializerRegister, askUser, modelFileName);
				initializerRegister.recordInitializer(InitializerRegister.DEFAULT_INITIALIZER_KEY, wmi);
			}
	
			// NOTE applicationCreator must be in the same InitializerRegistry
			ApplicationCreator appc = new ApplicationCreator(project, initializerRegister, askUser, modelFileName, alfrescoVersion, alfrescoHome, createdModels);
			initializerRegister.recordInitializer(InitializerRegister.DEFAULT_INITIALIZER_KEY, appc);
	
			// execute initializing
			initializerRegister.initialize();
	
		}

	}

	private IFile createInitialModel(String editorId, EObject model) throws IOException {
		IFile file = getIFileForEditorId(editorId);
		ModelInitializationUtils.saveModel(file.getLocation().toFile(), model);
		createdModels.add(file);
		return file;
	}

	private void createInitialModelWithDiagram(String editorId, EObject model, String modelerId) throws IOException {
		IFile iFile = createInitialModel(editorId, model);
		createDiagramFile(iFile, model, modelerId);
	}	

	private void createDiagramFile(IFile file, EObject root, String diagramid) throws IOException {
		IFile diagramFile = IFileHelper.getIFile(file.getFullPath().toOSString() + ModelInitializationUtils.DIAGRAM_SUFFIX);
		ModelInitializationUtils.createDiagramFile(root, diagramid, file.getName() + ModelInitializationUtils.DIAGRAM_SUFFIX, diagramFile);
	}

	private IFile getIFileForEditorId(String editorId) {
		String extension = ModelInitializationUtils.getExtensionForEditorId(editorId);
		IFolder targetFolder = ModelInitializationUtils.getIFolderForEditorId(newProject, editorId);

		if (targetFolder != null) {
			return targetFolder.getFile(baseModelName + (extension != null ? extension : "") ); // $NON-NLS-1$
		}
		
		return null;
	}	
	
}
