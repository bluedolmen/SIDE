package com.bluexml.side.application.ui.menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.CompoundContributionItem;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.ui.action.GeneratePopUp;

public class DynamicMenuAction extends CompoundContributionItem implements IObjectActionDelegate, IMenuCreator {

	private Menu menu;
	private StructuredSelection selection;
	private Application application;

	public void dispose() {
		// System.err.println("dispose");
		if (menu != null) {
			menu.dispose();
			menu = null;
		}

	}

	public Menu getMenu(Control parent) {
		// System.err.println("getMenu(Control parent)");
		// Never used
		return null;

	}

	public Menu getMenu(Menu parent) {
		// System.err.println("getMenu");
		menu = new Menu(parent);

		try {
			if (selection != null && selection.getFirstElement() != null && selection.getFirstElement() instanceof IFile) {
				final IFile file = (IFile) selection.getFirstElement();
				URI uri = URI.createFileURI(file.getRawLocation().toFile().getAbsolutePath());
				XMIResource resource = new XMIResourceImpl(uri);

				FileInputStream fi = new FileInputStream(file.getRawLocation().toFile());
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put(ApplicationPackage.eINSTANCE.getNsURI(), ApplicationPackage.eINSTANCE);
				map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
				resource.load(fi, map);

				application = (Application) resource.getContents().get(0);

				for (ModelElement me : application.getElements()) {
					if (me instanceof Configuration) {
						final Configuration configuration = (Configuration) me;
						MenuItem item = new MenuItem(menu, SWT.RADIO);
						item.setText(configuration.getName());
						item.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent e) {
								final GeneratePopUp generationPopUp;
								try {
									generationPopUp = new GeneratePopUp(Display.getDefault().getActiveShell(), file, configuration.getName());

									GeneratePopUp.launch(configuration, generationPopUp, application, file);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}

							

						});
					}
				}
			}
			// model = file;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return menu;
	}

	public boolean isDynamic() {
		return true;
	}

	public void selectionChanged(IAction action, ISelection myselection) {
		// System.err.println("selectionChanged" + myselection.toString());
		this.selection = (StructuredSelection) myselection;

		action.setMenuCreator(this);
		action.setEnabled(true);
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		// System.err.println("setActivePart");

	}

	public void run(IAction action) {
		// System.err.println("run");
		// TODO Auto-generated method stub

	}

	@Override
	protected IContributionItem[] getContributionItems() {
		// TODO Auto-generated method stub
		// System.err.println("getContributionItems");
		return null;
	}
	
	

}
