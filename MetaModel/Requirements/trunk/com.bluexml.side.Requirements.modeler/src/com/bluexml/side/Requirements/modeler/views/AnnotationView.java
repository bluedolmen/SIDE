package com.bluexml.side.Requirements.modeler.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.views.internal.IField;
import com.bluexml.side.Requirements.modeler.views.internal.TableView;
import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.requirements.Annotation;
import com.bluexml.side.requirements.util.RequirementsResource;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class AnnotationView extends TableView implements ISelectionListener{

	private IFile currentModel = null;
	private EObject currentObject = null;
	
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bluexml.side.Requirements.modeler.views.AnnotationView";
	private IField authorField = new AuthorField();
	private IField dateField = new DateField();
	private IField annotatedObjectField = new AnnotatedObjectField();
	private IField idField = new IdField();
	private IField statusField = new StatusField();

	public AnnotationView() {
		super();
		setContentProvider(new ViewContentProvider());
	}
	
	@Override
	public Object createViewerInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fillContextMenu(IMenuManager manager) {
		//Nothing to do
	}

	@Override
	protected IField[] getAllFields() {
		IField[] list = {idField,authorField,annotatedObjectField,dateField,statusField};
		return list; 
	}

	class ViewContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			List<Object> result = new ArrayList<Object>();
			TreeIterator<EObject> it = null;
			
			if (parent instanceof IFile) {
				IFile file = (IFile) parent;
				try {
					Resource r = EResourceUtils.openModel(file.getLocation().toString(), new HashMap<Object, Object>());
					it = r.getAllContents();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (parent instanceof EObject) {
				EObject eobj = (EObject) parent;
				it = eobj.eAllContents();
			}
			
			if (it != null)
				while (it.hasNext()) {
					EObject obj = it.next();
					if (obj instanceof Annotation) {
						Annotation a = (Annotation) obj;
						result.add(a);
					}
				}
			
			return result.toArray();
		}

		public Object[] getChildren(Object parentElement) {
			return null;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return false;
		}
	}

	@Override
	protected IDialogSettings getDialogSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IField[] getSortingFields() {
		IField[] list = {idField,authorField,annotatedObjectField,dateField,statusField};
		return list;
	}

	@Override
	protected void handleOpenEvent(OpenEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initToolBar(IToolBarManager tbm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void registerGlobalActions(IActionBars actionBars) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void viewerSelectionChanged(IStructuredSelection selection) {
	}
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		getViewSite().getPage().addSelectionListener(this);
	}

	 public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		 currentObject = null;
         if (selection instanceof IStructuredSelection) {
                 Object first = ((IStructuredSelection)selection).getFirstElement();
                 IFile model = null;
                 if (first instanceof IFile) {
                	IFile file = (IFile) first;
                	if (file.getName().endsWith(".requirements") && file.exists())
                		model = file;
                	else if (file.getName().endsWith(".requirementsdi") && file.exists()) {
                		IFile tmp = file.getParent().getFile(new Path(file.getName().substring(0, file.getName().length()-2))); 
                		if (tmp != null && tmp.exists())
                			model = tmp;
                	}
                 }
                 
                 if (first instanceof EditPart) {
                	 EditPart epart = (EditPart) first;
                	first = Utils.getElement((GraphElement) epart.getModel());
				}

                 if (first instanceof EObject) {
                	 EObject eobj = (EObject) first;
                	 currentObject = eobj;
                	 first = eobj.eResource();
				}
                 
                 if (first instanceof RequirementsResource) {
					RequirementsResource res = (RequirementsResource) first;
					IPath ePath = new Path(res.getURI().devicePath().toString());
					ePath = ePath.removeFirstSegments(1);
					ePath = ePath.makeAbsolute();
					IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(ePath);
                	if (file.getName().endsWith(".requirements") && file.exists())
                		model = file;
                	else if (file.getName().endsWith(".requirementsdi") && file.exists()) {
                		IFile tmp = file.getParent().getFile(new Path(file.getName().substring(0, file.getName().length()-2))); 
                		if (tmp != null && tmp.exists())
                			model = tmp;
                	}
				}

                 if (model != null)
                	 if (model != currentModel) {
                		 currentModel = model;
                		 cleanView();
                		 populateView();
                	 }
         }
 }

	private void populateView() {
		if (currentObject != null)
			getViewer().setInput(currentObject);
		else
			getViewer().setInput(currentModel);
	}

	private void cleanView() {
		getViewer().getTree().clearAll(true);
	}

	
}