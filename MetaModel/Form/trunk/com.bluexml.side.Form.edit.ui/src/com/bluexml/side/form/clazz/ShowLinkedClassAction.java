package com.bluexml.side.form.clazz;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramElement;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.presentation.ClazzEditor;
import com.bluexml.side.common.Package;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormUI;

public class ShowLinkedClassAction extends Action implements
		ISelectionChangedListener {

	protected EObject selectedObject;

	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection) {
			setEnabled(updateSelection((IStructuredSelection) event
					.getSelection()));
		} else {
			setEnabled(false);
		}
	}

	public boolean updateSelection(IStructuredSelection selection) {
		selectedObject = null;
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof FormClass) {
				selectedObject = (EObject) object;
			} else {
				return false;
			}
		}

		return selectedObject != null;
	}

	@Override
	public void run() {
		super.run();
		doAction((FormClass) selectedObject);
	}

	private void doAction(FormClass form) {
		Clazz clazz = form.getReal_class();
		if (clazz != null) {
			XMIResource r = (XMIResource) clazz.eResource();
			IFile file = XMIResource2IFile(r);
			IFile diagramFile = file.getParent().getFile(
					new Path(file.getName() + "di"));

			if (diagramFile.exists()) {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IEditorDescriptor desc = PlatformUI.getWorkbench()
						.getEditorRegistry().getDefaultEditor(diagramFile.getName());
				try {
					IEditorPart editor = page.openEditor(new FileEditorInput(
							diagramFile), desc.getId());
					
					if (editor instanceof Modeler) {
						Modeler modeler = (Modeler) editor;
						selectDiagramElement(modeler.getDiagrams(), modeler, clazz);
					}
				} catch (PartInitException e) {
					e.printStackTrace();
				}

			} else {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IEditorDescriptor desc = PlatformUI.getWorkbench()
						.getEditorRegistry().getDefaultEditor(file.getName());
				try {
					IEditorPart editor = page.openEditor(new FileEditorInput(
							file), desc.getId());
					if (editor instanceof ClazzEditor) {
						ClazzEditor cEditor = (ClazzEditor) editor;
						TreeViewer treeViewer = (TreeViewer) cEditor
								.getViewer();
						TreeItem item = treeViewer.getTree().getItem(0);
						item.setExpanded(true);
						treeViewer.refresh();

						Package p = (Package) item.getItem(0).getData();
						selectClass(p, cEditor, clazz);
					}
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}

		} else {
			MessageDialog.openError(null, FormUI.Messages
					.getString("ShowLinkedClass_1"), FormUI.Messages
					.getString("ShowLinkedClass_2"));
		}
	}

	private void selectDiagramElement(Diagrams diagrams, Modeler modeler, Clazz clazz) {
		for (Diagram diagram : diagrams.getDiagrams()) {
			for (DiagramElement el : diagram.getContained()) {
				if (el instanceof GraphElement) {
					GraphElement gel = (GraphElement) el;
					EObject obj = Utils.getElement(gel);
					if (obj instanceof Clazz) {
						Clazz c = (Clazz) obj;
						if (c.getFullName().equals(clazz.getFullName())) {
							modeler.setActiveDiagram(diagram);
							modeler.gotoEObject(c);
							break;
						}
					}
				}
			}
		}
	}

	private void selectClass(Package p, ClazzEditor cEditor, Clazz clazz) {
		if (p instanceof ClassPackage) {
			ClassPackage cp = (ClassPackage) p;
			for (Clazz cl : cp.getAllClasses())
				if (cl.getFullName().equals(clazz.getFullName()))
					cEditor.setSelectionToViewer(Collections.singleton(cl));
		}
	}

	private IFile XMIResource2IFile(XMIResource resource) {
		URI uri = resource.getURI();
		uri = resource.getResourceSet().getURIConverter().normalize(uri);
		String scheme = uri.scheme();
		if ("platform".equals(scheme) && uri.segmentCount() > 1 //$NON-NLS-1$
				&& "resource".equals(uri.segment(0))) { //$NON-NLS-1$
			StringBuffer platformResourcePath = new StringBuffer();
			for (int j = 1, size = uri.segmentCount(); j < size; ++j) {
				platformResourcePath.append('/');
				platformResourcePath.append(uri.segment(j));
			}
			return ResourcesPlugin.getWorkspace().getRoot().getFile(
					new Path(platformResourcePath.toString()));
		} else if ("file".equals(scheme)) { //$NON-NLS-1$
			StringBuffer platformResourcePath = new StringBuffer();
			for (int j = ResourcesPlugin.getWorkspace().getRoot().getLocation()
					.segments().length, size = uri.segmentCount(); j < size; ++j) {
				platformResourcePath.append('/');
				platformResourcePath.append(uri.segment(j));
			}
			return ResourcesPlugin.getWorkspace().getRoot().getFile(
					new Path(platformResourcePath.toString()));
		}
		return null;
	}

	@Override
	public String getText() {
		return FormUI.Messages.getString("ShowLinkedClass_0");
	}
}
