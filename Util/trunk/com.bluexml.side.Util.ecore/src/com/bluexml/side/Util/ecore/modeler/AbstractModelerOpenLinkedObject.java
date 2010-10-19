package com.bluexml.side.Util.ecore.modeler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractModelerOpenLinkedObject extends WorkbenchPartAction implements ISelectionChangedListener {

	protected ISelection selection;
	protected EObject selectedObject;
	protected String fileExtension;
	protected Class<?> type;
	protected IProject project;

	protected String message1 = "";
	protected String message2 = "";

	public AbstractModelerOpenLinkedObject(IWorkbenchPart part, String fileExtension, Class<?> t) {
		super(part);
		this.fileExtension = fileExtension;
		this.type = t;

	}

	public void selectionChanged(SelectionChangedEvent event) {
		this.selection = event.getSelection();
		setSelection(this.selection);

	}

	protected void setSelection(ISelection s) {
		if (!(s instanceof IStructuredSelection)) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) s;
		selectedObject = null;
		if (selection.getFirstElement() instanceof EMFGraphNodeEditPart) {
			EMFGraphNodeEditPart editPart = (EMFGraphNodeEditPart) selection.getFirstElement();
			selectedObject = editPart.getEObject();
		}
	}

	@Override
	public void run() {

		// Search the good view model
		// Get project
		IFile iFile = XMIResource2IFile((XMIResource) selectedObject.eResource());
		project = iFile.getProject();

		List<EObject> views = searchEObject();

		if (views.size() == 0) {
			setMessages();
			MessageDialog.openInformation(null, message1, message2);
		} else {
			recomputeAndSelect(views);
		}
	}

	private static EObject openModel(IFile model) throws IOException {
		return ModelInitializationUtils.openModel(model).get(0);
	}

	private static IFile XMIResource2IFile(XMIResource resource) {
		URI uri = resource.getURI();
		return IFileHelper.URI2IFile(uri);
	}

	private static List<EObject> searchFilesWithExtension(IContainer container, String extension) {
		List<EObject> result = new ArrayList<EObject>();
		try {
			if (!container.isHidden() && !container.getName().startsWith(".")) {
				for (IResource r : container.members()) {
					if (r instanceof IContainer) {
						result.addAll(searchFilesWithExtension((IContainer) r, extension));
					} else if (r instanceof IFile) {
						IFile file = (IFile) r;
						if (r.getFileExtension().equals(extension)) {
							result.add(openModel(file));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * search in project models and return root elements
	 * 
	 * @return
	 */
	protected List<EObject> searchRootElements() {
		List<EObject> result = new ArrayList<EObject>();
		for (EObject obj : searchFilesWithExtension(project, fileExtension)) { //$NON-NLS-1$
			if (type.isAssignableFrom(obj.getClass())) {
				result.add((EObject) obj);
			}
		}
		return result;
	}

	/**
	 * for each element open Editor and select them
	 * 
	 * @param elements
	 */
	protected void recomputeAndSelect(List<EObject> elements) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		Map<Object, ArrayList<EObject>> index = new HashMap<Object, ArrayList<EObject>>();
		for (EObject view : elements) {
			XMIResource eResource = (XMIResource) view.eResource();
			IFile xmiResource2IFile = XMIResource2IFile(eResource);
			addEntry(index,xmiResource2IFile,view);
		}
		for (Map.Entry<Object, ArrayList<EObject>> viewEntry : index.entrySet()) {
			IFile xmiResource2IFile = (IFile)viewEntry.getKey();
			EObject view = viewEntry.getValue().get(0);
			IEditorPart editorPart;
			try {
				IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(xmiResource2IFile.getName());
				editorPart = page.openEditor(new FileEditorInput(XMIResource2IFile((XMIResource) view.eResource())), desc.getId());

				selectEObject(editorPart, viewEntry.getValue());
				
			} catch (PartInitException e) {
				// TODO: use Eclipse logger
				e.printStackTrace();
			}
		}
	}

	/**
	 * select target element in editorPart
	 * 
	 * @param editorPart
	 * @param view
	 */
	protected abstract void selectEObject(IEditorPart editorPart, List<EObject> element);

	/**
	 * use searchRootElement to get rootElements to search from the selected
	 * Object
	 * 
	 * @return
	 */
	protected abstract List<EObject> searchEObject();

	/**
	 * set messages to display if no target element founds
	 */
	protected abstract void setMessages();

	public void addEntry(Map<Object, ArrayList<EObject>> map, Object key, EObject obj) {
		if (map.containsKey(key)) {
			map.get(key).add(obj);
		} else {
			ArrayList<EObject> nl = new ArrayList<EObject>();
			nl.add(obj);
			map.put(key, nl);
		}
	}
}
