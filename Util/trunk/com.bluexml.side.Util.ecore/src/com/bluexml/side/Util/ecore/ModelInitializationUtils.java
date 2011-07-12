package com.bluexml.side.Util.ecore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
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
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsFactory;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.tools.DiagramFileInitializer;
import org.topcased.modeler.tools.Importer;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.util.libs.ui.UIUtils;

public class ModelInitializationUtils {

	private static ResourceSet rsrcSet = new ResourceSetImpl();
	private static IConfigurationElement[] contributions;

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
//		String canonicalPath = model.getRawLocation().toFile().getCanonicalPath();
//		URI createFileURI = URI.createFileURI(canonicalPath);
		URI uri = URI.createPlatformResourceURI(model.getFullPath().toString(), true);
		Resource inputResource = set.createResource(uri);
		inputResource.load(null);
		EList<EObject> l = inputResource.getContents();
		return l;
	}

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
		final Resource resource = rsrcSet.createResource(fileURI);
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

	/**
	 * Get the extension for a given model plugin
	 * 
	 * @param id
	 * @return
	 */
	public static String getExtensionForExtensionId(String id) {
		String result = ".";
		if ((contributions == null) || (contributions.length == 0)) {
			contributions = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.ui.editors");
		}
		if (contributions.length > 0) {
			for (IConfigurationElement elem : contributions) {
				if (elem.getAttribute("id").equals(id)) {
					result += elem.getAttribute("extensions");
				}
			}
		}
		return result;
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

	public static void openImportDiagram(EObject rootDiagramObject, String diagramId) throws Exception {
		if (Thread.currentThread().equals(UIUtils.getDisplay().getThread())) {

			DiagramFileInitializer initializer = new DiagramFileInitializer();
			//			initializer.createDiagram(rootDiagramObject, diagramId, true, new NullProgressMonitor());
			initializer.createDiagram(rootDiagramObject, diagramId, "main", true, new NullProgressMonitor());
		} else {
			System.err.println("ModelInitializationUtils.OpenImportDiagram() :" + "bad news ! this method must be run into user-ui thread");
		}

	}

	public static boolean createDiagramFromExistingModel(final EObject rootDiagramObject, final String diagramId) {
		Runnable op = new Runnable() {

			public void run() {
				DiagramFileInitializer initializer = new DiagramFileInitializer();
				try {
					initializer.createDiagram(rootDiagramObject, diagramId, true, new NullProgressMonitor());
				} catch (Throwable ioe) {
					System.err.println(ioe);
				}
				System.out.println("done");
			}
		};

		try {
			
			UIUtils.getDisplay().syncExec(op);

			return true;
		} catch (Exception ie) {
			System.err.println(ie);
		}
		return false;
	}

	private static Modeler openDiagram(Resource diagramResource, IFile file) {
		Modeler modeler = null;
		if (file != null) {
			// Open the newly created model
			try {

				ModelerPlugin default1 = ModelerPlugin.getDefault();
				IWorkbench workbench = default1.getWorkbench();
				IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
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

	private static EObject getActiveRoot(Modeler editor) {
		return Utils.getElement(editor.getActiveDiagram());
	}
}
