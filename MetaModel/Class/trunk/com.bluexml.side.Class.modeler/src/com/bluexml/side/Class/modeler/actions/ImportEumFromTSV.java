package com.bluexml.side.Class.modeler.actions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.diagram.commands.update.EnumerationUpdateCommand;
import com.bluexml.side.Class.modeler.diagram.dialogs.EnumerationEditDialog;
import com.bluexml.side.Class.modeler.diagram.dialogs.EnumerationLiteralDataStructure;
import com.bluexml.side.Class.modeler.diagram.edit.EnumerationEditPart;
import com.bluexml.side.Class.modeler.editor.ClazzEditor;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.clazz.modeler.Activator;
import com.bluexml.side.util.libs.ui.UIUtils;

public class ImportEumFromTSV extends WorkbenchPartAction {
	static public String ID = "com.bluexml.side.Class.modeler.actions.importEnumeration"; //$NON-NLS-1$
	private Enumeration selectedObject;
	private EnumerationEditPart editPart;
	public ImportEumFromTSV(IWorkbenchPart part) {
		super(part);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean calculateEnabled() {
		ClazzEditor editor = (ClazzEditor) getWorkbenchPart();
		setSelection(editor.getSelection());
		return (selectedObject != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		System.out.println(selectedObject);
		// display file selection
		File tsv = SelectFileToImportDialog();
		EList<EnumerationLiteral> lits = selectedObject.getLiterals();
		EnumerationLiteralDataStructure enulds = new EnumerationLiteralDataStructure(selectedObject);
		try {
			List<?> l = org.apache.commons.io.FileUtils.readLines(tsv, "UTF-8"); //$NON-NLS-1$

			for (Object object : l) {
				String ls = (String) object;
				String[] cols = ls.split("\t"); //$NON-NLS-1$

				String name = cols[0];
				String literal = cols[1];

				EnumerationLiteral el = ClazzFactory.eINSTANCE.createEnumerationLiteral();
				el.setEnum(selectedObject);
				el.setName(name);
				el.setValue(literal);
				lits.add(el);
				
				enulds.add(el);

			}
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put(EnumerationEditDialog.ENUMERATION_NAME, selectedObject.getName());
			data.put(EnumerationEditDialog.ENUMERATION_LITERALS, enulds);
			EnumerationUpdateCommand command = new EnumerationUpdateCommand(selectedObject, data);
			editPart.getViewer().getEditDomain().getCommandStack().execute(command);
			editPart.updateGraphycalElement(selectedObject);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void init() {
		setId(ID);
		setText(ClazzPlugin.Messages.getString("ImportEumFromTSV_2"));
		// load Eclipse icon
		ImageDescriptor img = UIUtils.getImage("org.eclipse.ui.ide", "/icons/full/etool16/import_wiz.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		setImageDescriptor(img);
	}

	public void setSelection(ISelection s) {
		if (!(s instanceof IStructuredSelection)) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) s;

		selectedObject = null;
		// Recompute the command according to the current selection
		if (selection.getFirstElement() instanceof EnumerationEditPart) {
			editPart = (EnumerationEditPart) selection.getFirstElement();
			if (editPart.getEObject() instanceof Enumeration) {
				selectedObject = (Enumeration) editPart.getEObject();
			}
		}
	}

	/**
	 * Open file dialog box to select files (here a model)
	 */
	protected File SelectFileToImportDialog() {
		String filePath = null;
		org.eclipse.swt.widgets.FileDialog fsel = new org.eclipse.swt.widgets.FileDialog(Display.getDefault().getActiveShell());
		fsel.setFilterExtensions(new String[] { "tsv" }); //$NON-NLS-1$
		filePath = fsel.open();
		System.out.println(filePath);
		return new File(filePath);
	}

}
