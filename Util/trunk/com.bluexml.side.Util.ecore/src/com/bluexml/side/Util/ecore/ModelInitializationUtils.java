package com.bluexml.side.Util.ecore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsFactory;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.tools.DiagramFileInitializer;
import org.topcased.modeler.tools.Importer;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.util.libs.ui.UIUtils;

public final class ModelInitializationUtils {

	public static final IPath SRC_PATH = new Path("src"); // $NON-NLS-1$ // should be defined elsewhere
	public static final IPath SRC_MODELS_PATH = SRC_PATH.append("models"); // $NON-NLS-1$
	public static final String DIAGRAM_SUFFIX = "di"; // $NON-NLS-1$
	
	@SuppressWarnings("serial")
	private static final Map<String, String> FOLDER_NAME_MAP = new HashMap<String, String>() {{
		put(SIDEEditorUtils.DATA_MODEL_EDITOR_ID,"data");
		put(SIDEEditorUtils.FORM_MODEL_EDITOR_ID,"form");
		put(SIDEEditorUtils.PORTAL_MODEL_EDITOR_ID,"portal");
		put(SIDEEditorUtils.REQUIREMENTS_MODEL_EDITOR_ID,"requirement");
		put(SIDEEditorUtils.VIEW_MODEL_EDITOR_ID,"view");
		put(SIDEEditorUtils.WORKFLOW_MODEL_EDITOR_ID,"workflow");
		put(SIDEEditorUtils.APPLICATION_MODEL_EDITOR_ID,"application");

	}};
	
	private final static Logger LOGGER = Logger.getLogger(ModelInitializationUtils.class.getName());
	
	/*
	 * BASIC I/O MODEL MANAGEMENT
	 */
	
	/**
	 * Save a new model in file.
	 * 
	 * @param file
	 * @param rootObject
	 * @throws IOException
	 */
	public static void saveModel(File file, EObject rootObject) throws IOException {
		FileOutputStream os = new FileOutputStream(file);
		ResourceSetImpl set = new ResourceSetImpl();
		Resource outputResource = set.createResource(URI.createFileURI(file.getCanonicalPath()));
		outputResource.getContents().add(rootObject);
		outputResource.save(os, null);
		os.close();
	}

	public static void saveModel(IFile model, EObject rootObject) throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI fileURI = URI.createPlatformResourceURI(model.getFullPath().toString(), true);
		Resource resource = resourceSet.createResource(fileURI);
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$	
		resource.getContents().add(rootObject);
		resource.save(options);
	}

	/**
	 * Open the specified model.
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	public static EList<EObject> openModel(IFile model) throws IOException {
		ResourceSetImpl set = new ResourceSetImpl();
		URI uri = URI.createPlatformResourceURI(model.getFullPath().toString(), true);
		Resource inputResource = set.createResource(uri);
		inputResource.load(null);
		EList<EObject> l = inputResource.getContents();
		return l;
	}

	/**
	 * Returns an {@link EObject} of the given type if it can be found in the
	 * given {@link IFile}, else returns null.
	 * <p>
	 * This method uses the {@link ModelInitializationUtils} helpers to get the
	 * model from an {@link IFile}.
	 * 
	 * @param iFile
	 * @param type
	 * @return the {@link EObject} of the given type if it can be found, null
	 *         otherwise
	 */
	public static <T extends EObject> T getCheckedEObject(IFile iFile, Class<T> type) {
		if (iFile == null) {
			throw new IllegalArgumentException("The provided IFile cannot be null");
		}
		
		EList<EObject> eObjects = null;
		try {
			eObjects = ModelInitializationUtils.openModel(iFile);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, String.format("Cannot load %s from file '%s' because of an unexpected IO/Exception", type.getSimpleName(), iFile.getName()), e);
			return null;
		}
		
		if (eObjects.isEmpty()) {
			LOGGER.finest(String.format("The provided IFile '%s' does not contain any EObject", iFile.getName()));
			return null;
		}
		
		EObject firstEObject = eObjects.get(0);
		if (!type.isInstance(firstEObject) ) {
			LOGGER.finest(String.format("The provided IFile '%s' does not contain a %s object", iFile.getName(), type.getSimpleName()));
			return null;
		}
		
		return type.cast(firstEObject);
	}

	/**
	 * Same method as the preceding one of a File.
	 * <p>
	 * This method does not depend on {@link IFile}s and can be used outside a
	 * workspace. This method can thus be used to retrieve information from a
	 * particular model file without depending on a workspace.
	 * <p>
	 * Use this method with caution, since standard behavior seems to use the
	 * SIDE models only inside a workspace.
	 * 
	 * @param file
	 * @param type
	 * @return
	 * @throws IOException 
	 */
	public static <T extends EObject> T getCheckedEObject(File file, Class<T> type) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException("The provided File cannot be null");
		}

		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource inputResource = resourceSet.createResource(uri);
		inputResource.load(null);
		EList<EObject> rootEObjects = inputResource.getContents();

		if (rootEObjects.isEmpty()) {
			LOGGER.finest(String.format("The provided file '%s' does not contain any EObject", file.getName()));
			return null;
		}		
		
		EObject firstEObject = rootEObjects.get(0);
		if (!type.isInstance(firstEObject) ) {
			LOGGER.finest(String.format("The provided IFile '%s' does not contain a %s object", file.getName(), type.getSimpleName()));
			return null;
		}
		
		return type.cast(firstEObject);
	}
	
	
	/*
	 * MODEL DIAGRAM MANAGEMENT
	 */
	
	public static Resource createDiagramFile(final EObject root, String diagramId, String name, final IFile diagramFile) throws IOException {
		// retrieve the Diagrams and the DiagramInterchange factory singletons
		DiagramsFactory factory = DiagramsFactory.eINSTANCE;
		DiagramInterchangeFactory diFactory = DiagramInterchangeFactory.eINSTANCE;
		// create the EObject of the diagram model
		Diagrams diagrams = factory.createDiagrams();
		Diagram rootDiagram = diFactory.createDiagram();
		EMFSemanticModelBridge emfSemanticModelBridge = diFactory.createEMFSemanticModelBridge();

		// set the properties of the diagrams model
		diagrams.setModel(root);
		diagrams.getDiagrams().add(rootDiagram);

		// set the properties of the Diagram
		rootDiagram.setSize(new Dimension(100, 100));
		rootDiagram.setViewport(new Point(0, 0));
		rootDiagram.setPosition(new Point(0, 0));
		rootDiagram.setName(name);
		rootDiagram.setSemanticModel(emfSemanticModelBridge);

		// set the properties of the SemanticModelBridge
		emfSemanticModelBridge.setElement(root);
		emfSemanticModelBridge.setPresentation(diagramId);

		// create the diagram file and add the created model into
		URI fileURI = URI.createPlatformResourceURI(URI.decode(diagramFile.getFullPath().toString()), false);
		ResourceSet resourceSet = root.eResource().getResourceSet();
		final Resource resource = resourceSet.createResource(fileURI);
		resource.getContents().add(diagrams);

		// Save the resource contents to the file system.
		resource.save(Collections.EMPTY_MAP);

		Display display = UIUtils.getDisplay();
		// import objects
		display.syncExec(new Runnable() {
			public void run() {
				importObjects(resource, root, diagramFile);
			}
		});

		return resource;
	}
	
	public static boolean createDiagramFromExistingModel(final EObject rootDiagramObject, final String diagramId) {
		Runnable op = new Runnable() {

			public void run() {
				DiagramFileInitializer initializer = new DiagramFileInitializer();
				try {
					initializer.createDiagram(rootDiagramObject, diagramId, "main", true, new NullProgressMonitor());
				} catch (Throwable ioe) {
					ioe.printStackTrace();
				}
			}
		};

		try {
			UIUtils.getDisplay().syncExec(op);
			return true;
		} catch (Exception ie) {
			ie.printStackTrace();
		}
		return false;
	}

	private static Modeler openDiagram(Resource diagramResource, IFile file) {
		Modeler modeler = null;
		if (file != null) {
			// Open the newly created model
			try {
				IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
				IEditorPart part = IDE.openEditor(activePage, file, true);
				if (part instanceof Modeler) {
					modeler = (Modeler) part;
				}
			} catch (PartInitException pie) {
				modeler = null;
			}
		}

		return modeler;
	}

	
	private static void importObjects(Resource resource, EObject root, IFile diagramFile) {
		Modeler modeler = openDiagram(resource, diagramFile);

		if (modeler != null) {
			// Import graphical element
			final Importer importer = new Importer(modeler, getActiveRoot(modeler).eContents());

			GraphicalViewer viewer = (GraphicalViewer) modeler.getAdapter(GraphicalViewer.class);
			GraphicalEditPart target = (GraphicalEditPart) viewer.getEditPartRegistry().get(modeler.getActiveDiagram());

			importer.setTargetEditPart(target);
			Dimension insets = new Dimension(10, 10);
			target.getContentPane().translateToAbsolute(insets);
			importer.setLocation(target.getContentPane().getBounds().getTopLeft().translate(insets));
			importer.setAutoLayout(true);
		}
	}
	
	
	private static EObject getActiveRoot(Modeler editor) {
		return Utils.getElement(editor.getActiveDiagram());
	}


	/*
	 * EXTENSIONS MANAGEMENT
	 */
	
	private static final String ACCEPTED_EDITORS_PREFIX = "com.bluexml.side"; // $NON-NLS-1$
	private static Map<String, String> extensionsByEditorId = null;
	private static final String EXTENSIONS_SEPARATOR = ","; // $NON-NLS-1$

	/**
	 * Get the extension for a given editor id
	 * 
	 * @param id the editor id
	 * @return the extension if it is defined, else null
	 */
	public static String getExtensionForEditorId(String id) {
		
		String extension = getExtensionNameForEditorId(id);
		
		if (extension == null) {
			return null;
		} else {
			return '.' + extension;
		}
		
	}

	/**
	 * Get the extension name for a given editor id
	 * 
	 * @param id the editor id
	 * @return the extension name if it is defined, else null
	 */
	public static String getExtensionNameForEditorId(String id) {
		
		if (extensionsByEditorId == null) {
			initializeExtensions();
		}
		
		return extensionsByEditorId.get(id);
	}

	
	/**
	 * Initialize extensions by using org.eclipse.ui.editors extension point and
	 * the declared extensions.
	 */
	private static void initializeExtensions() {
		// Initialize at first call
		extensionsByEditorId = new HashMap<String, String>();
		
		for (IConfigurationElement ce : Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.ui.editors")) { // $NON-NLS-1$
			String editorId = ce.getAttribute("id");
			String extensions = ce.getAttribute("extensions");
			
			if (editorId != null && !editorId.isEmpty()) {
				if (!editorId.startsWith(ACCEPTED_EDITORS_PREFIX)) {
					// Only process SIDE editors
					continue;
				}
			}
			
			if (extensions != null && !extensions.isEmpty()) {
				
				if (extensions.contains(EXTENSIONS_SEPARATOR)) {
					// Defined several extensions => only keep first one
					extensions = extensions.split(EXTENSIONS_SEPARATOR)[0].trim();
					final String message = String.format(
							"Editor with id '%s' contains several extension definitions (%s), keeping only first", 
							editorId,
							extensions
					);
					Activator.getDefault().getLog().log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, message));
				}
				
				extensionsByEditorId.put(editorId, extensions);
				continue;	
			}

			// One of editorId or extensions is invalid, display a warning message!
			final String message = String.format(
					"Invalid combination editorId (%s) / extension (%s)", 
					editorId != null ? editorId : "unknown",
					extensions != null ? extensions : "unknown"
			);
			Activator.getDefault().getLog().log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, message));
				
		}		
	}
	
	/*
	 * MODELS FOLDER MANAGEMENT
	 */
	
	/**
	 * Returns the {@link IFolder} corresponding to the models directory of the
	 * given {@link IProject}
	 * 
	 * @param baseProject
	 * @return
	 */
	public static IFolder getModelsFolder(IProject baseProject) {
		IFolder modelsFolder = baseProject.getFolder(SRC_MODELS_PATH);
		return modelsFolder;
	}
	
	public static String getDirectoryNameForEditorId(String editorId) {
		return FOLDER_NAME_MAP.get(editorId);
	}
	
	/**
	 * Returns the target folder for a given file-extension (as a {@link String}
	 * ) and a base {@link IProject}
	 * 
	 * @param baseProject
	 * @param extension
	 * @return the target {@link IFolder}
	 */
	public static IFolder getIFolderForModelExtension(IProject baseProject, String extension) {
		
		IFolder modelsFolder = getModelsFolder(baseProject);
		
		for (Object key : FOLDER_NAME_MAP.keySet()) {
			String editorId = (String) key;
			
			if (extension.equalsIgnoreCase(ModelInitializationUtils.getExtensionForEditorId(editorId))) {
				String targetFolder = getDirectoryNameForEditorId(editorId);
				if (targetFolder != null) {
					return modelsFolder.getFolder(targetFolder);
				}
			}
		}
			
		return null;
	}

	/**
	 * Returns the target folder for a given editor id and a base
	 * {@link IProject}
	 * 
	 * @param baseProject
	 * @param editorId
	 * @return the target {@link IFolder}
	 */
	public static IFolder getIFolderForEditorId(IProject baseProject, String editorId) {
		
		IFolder modelsFolder = getModelsFolder(baseProject);
		if (modelsFolder != null) {
			String targetFolder = getDirectoryNameForEditorId(editorId);
			if (targetFolder != null) {
				return modelsFolder.getFolder(targetFolder);
			}
		}
		
		return null;
	}

	
	/**
	 * Gets all the declared model folder names
	 * 
	 * @return a list of folder names as a Java array
	 */
	public static String[] getDeclaredModelFolderNames() {
		return FOLDER_NAME_MAP.values().toArray(new String[0]);
	}

	
	private ModelInitializationUtils() {}; // Utility class
}
