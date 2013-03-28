/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.Class.modeler.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.Class.modeler.ClazzImageRegistry;
import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.editor.ClazzEditor;
import com.bluexml.side.Util.ecore.modeler.AbstractModelerOpenLinkedObject;
import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.presentation.FormEditor;

public class ShowFormAction extends AbstractModelerOpenLinkedObject {
	static public String ID = "com.bluexml.side.Class.modeler.actions.showForm"; //$NON-NLS-1$

	public ShowFormAction(IWorkbenchPart part) {
		super(part, "form", FormCollection.class);
	}

	@Override
	protected boolean calculateEnabled() {
		ClazzEditor editor = (ClazzEditor) getWorkbenchPart();
		setSelection(editor.getSelection());
		return (selectedObject != null);
	}

	@Override
	protected void init() {
		setId(ID);
		setText(ClazzPlugin.Messages.getString("ShowFormAction.5")); //$NON-NLS-1$
		setImageDescriptor(ClazzImageRegistry.getImageDescriptor("FORMMODEL")); //$NON-NLS-1$
	}

	@Override
	protected List<EObject> searchEObject() {
		Clazz clazz = (Clazz) selectedObject;
		List<EObject> roots = searchRootElements();
		List<EObject> result = new ArrayList<EObject>();
		for (EObject wfc_ : roots) {
			FormCollection wfc = (FormCollection) wfc_;
			for (FormContainer fc : wfc.getForms()) {
				if (fc instanceof ClassReference) {
					ClassReference fw = (ClassReference) fc;

					AbstractClass c = fw.getReal_class();
					if (c.getFullName().equals(clazz.getFullName())) {
						result.add(fw);
						System.out.println("match ! record :"+fw);
					}
				} else {
					System.err.println("no matches :" + fc);
				}
			}
		}

		return result;
	}

	@Override
	protected void selectEObject(IEditorPart editorPart, List<EObject> elements) {
		ArrayList<EObject> l = new ArrayList<EObject>();
		if (editorPart instanceof FormEditor) {
			FormEditor editor = (FormEditor) editorPart;

			for (EObject eObject : elements) {
				ClassReference foundedFc = (ClassReference) eObject;

				AbstractClass clazz = foundedFc.getReal_class();
				TreeViewer treeViewer = (TreeViewer) editor.getViewer();
				TreeItem item = treeViewer.getTree().getItem(0);
				item.setExpanded(true);
				treeViewer.refresh();
				FormCollection wfc = (FormCollection) item.getItem(0).getData();

				for (FormContainer fc : wfc.getForms()) {
					if (fc instanceof ClassReference) {
						ClassReference fw = (ClassReference) fc;
						AbstractClass c = fw.getReal_class();

						if (c.getFullName().equals(clazz.getFullName())) {
							l.add(fw);
						}
					}
				}
			}
			editor.setSelectionToViewer(l);
		}
	}

	@Override
	protected void setMessages() {
		message1 = ClazzPlugin.Messages.getString("ShowFormAction.1");
		message2 = ClazzPlugin.Messages.getString("ShowFormAction.2", project.getName());
	}

}
