package com.bluexml.side.Requirements.modeler.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TreeItem;
import org.topcased.modeler.editor.Modeler;

import com.bluexml.side.Requirements.modeler.goalDiagram.ReqImageRegistry;
import com.bluexml.side.requirements.Attribute;
import com.bluexml.side.requirements.BasicElement;
import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.ModelElement;
import com.bluexml.side.requirements.Privilege;
import com.bluexml.side.requirements.PrivilegeGroup;
import com.bluexml.side.requirements.PrivilegeNature;
import com.bluexml.side.requirements.RelationShip;
import com.bluexml.side.requirements.RequirementsDefinition;
import com.bluexml.side.requirements.RequirementsFactory;

public class PrivilegeDialog extends Dialog implements IDialogConstants {

	private static final int MIN_DIALOG_WIDTH = 500;
	private static final int MIN_DIALOG_HEIGHT = 300;

	private PrivilegeGroup group = null;
	private Button createAccess;
	private Button readAccess;
	private Button updateAccess;
	private Button deleteAccess;
	private TreeViewer viewer;
	private Modeler modeler;

	public PrivilegeDialog(Shell parent, PrivilegeGroup g) {
		super(parent);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		group = g;
		modeler = null;
	}
	
	public PrivilegeDialog(Shell activeWorkbenchShell, PrivilegeGroup g,
			Modeler editor) {
		super(activeWorkbenchShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		group = g;
		modeler = editor;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// Create Open button
	    Button OK = createButton(parent, Window.OK, "OK", true);
	    // Initially activate it
	    OK.setEnabled(true);
	    // Add a SelectionListener
	    OK.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        // Set return code
	        setReturnCode(Window.OK);
	        // Close dialog
	        close();
	      }
	    });
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createPrivilegeGroup(dialogComposite);

		loadData();
		viewer.refresh();
		viewer.expandAll();
		
		return dialogComposite;
	}

	private void loadData() {
		for (Privilege p : group.getPrivileges()) {
			if (p.getElement() instanceof RelationShip && p.getCategory().equals(PrivilegeNature.READ)) {
				RelationShip r = (RelationShip) p.getElement();
				((PrivilegeContentProvider) viewer.getContentProvider()).entities.add(r.getSource());
				((PrivilegeContentProvider) viewer.getContentProvider()).entities.add(r.getTarget());
			}
		}
	}

	private void createPrivilegeGroup(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		createPrivilegeTab(tabFolder);
	}

	private void createPrivilegeTab(Composite parent) {
		TabItem tab = new TabItem((TabFolder) parent, SWT.NONE);
		tab.setText("Privileges");
		Composite composite = new Composite(parent, SWT.NONE);
		tab.setControl(composite);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		viewer = new TreeViewer(composite, SWT.BORDER);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new PrivilegeContentProvider());
		viewer.setLabelProvider(new PrivilegeLabelProvider());
		viewer.setInput(group);
		viewer.expandAll();

		viewer.getTree().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem ti = (TreeItem) e.item;
				
				//Refresh checkboxes
				createAccess.setSelection(false);
				createAccess.setEnabled(true);
				readAccess.setSelection(false);
				readAccess.setEnabled(true);
				updateAccess.setSelection(false);
				updateAccess.setEnabled(true);
				deleteAccess.setSelection(false);
				deleteAccess.setEnabled(true);
				if (!(ti.getData() instanceof Entity)) {
					createAccess.setEnabled(false);
					deleteAccess.setEnabled(false);
				}
				for (Privilege p : group.getPrivileges()) {
					if (p.getElement() != null && p.getElement().equals(ti.getData())) {
						if (p.getCategory().equals(PrivilegeNature.CREATE))
							createAccess.setSelection(true);
						if (p.getCategory().equals(PrivilegeNature.READ))
							readAccess.setSelection(true);
						if (p.getCategory().equals(PrivilegeNature.UPDATE))
							updateAccess.setSelection(true);
						if (p.getCategory().equals(PrivilegeNature.DELETE))
							deleteAccess.setSelection(true);
					}
				}
			}
		});

		Composite rules = new Composite(composite, SWT.NONE);
		rules.setLayout(new GridLayout(1, false));
		rules.setLayoutData(new GridData(GridData.FILL_BOTH));

		createAccess = new Button(rules, SWT.CHECK);
		createAccess.setText("Create");
		createAccess.setEnabled(false);
		createAccess.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean selected = ((Button) e.widget).getSelection();
				BasicElement elt = (BasicElement) ((TreeSelection) viewer.getSelection()).getFirstElement();
				
				if (selected)
					addPrivilege(elt, PrivilegeNature.CREATE);
				else
					deletePrivilege(elt, PrivilegeNature.CREATE);
			}
		});
		readAccess = new Button(rules, SWT.CHECK);
		readAccess.setText("Read");
		readAccess.setEnabled(false);
		readAccess.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean selected = ((Button) e.widget).getSelection();
				BasicElement elt = (BasicElement) ((TreeSelection) viewer.getSelection()).getFirstElement();
				
				//Feature to add new entity
				if (elt instanceof RelationShip && selected) {
					RelationShip r = (RelationShip) elt;
					Set<Entity> entities = ((PrivilegeContentProvider) viewer.getContentProvider()).entities;
					entities.add(r.getTarget());
					entities.add(r.getSource());
					viewer.refresh();
					viewer.expandAll();
				}
				
				
				if (selected)
					addPrivilege(elt, PrivilegeNature.READ);
				else
					deletePrivilege(elt, PrivilegeNature.READ);
			}
		});
		updateAccess = new Button(rules, SWT.CHECK);
		updateAccess.setText("Update");
		updateAccess.setEnabled(false);
		updateAccess.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean selected = ((Button) e.widget).getSelection();
				BasicElement elt = (BasicElement) ((TreeSelection) viewer.getSelection()).getFirstElement();
				
				if (selected)
					addPrivilege(elt, PrivilegeNature.UPDATE);
				else
					deletePrivilege(elt, PrivilegeNature.UPDATE);
			}
		});
		deleteAccess = new Button(rules, SWT.CHECK);
		deleteAccess.setText("Delete");
		deleteAccess.setEnabled(false);
		deleteAccess.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean selected = ((Button) e.widget).getSelection();
				BasicElement elt = (BasicElement) ((TreeSelection) viewer.getSelection()).getFirstElement();
				
				if (selected)
					addPrivilege(elt, PrivilegeNature.DELETE);
				else
					deletePrivilege(elt, PrivilegeNature.DELETE);
			}
		});
	}
	
	private void deletePrivilege(BasicElement elt, PrivilegeNature nature) {
		boolean modified = false;
		HashSet<Privilege> toDelete = new HashSet<Privilege>();
		for (Privilege p : group.getPrivileges()) {
			if (p.getCategory().equals(nature) && p.getElement()!=null && p.getElement().equals(elt)) {
				toDelete.add(p);
				modified = true;
			}
		}
		group.getPrivileges().removeAll(toDelete);
		
		//Refresh
		Set<RelationShip> rs = new HashSet<RelationShip>();
		if (modified) {
			for (Privilege p : group.getPrivileges())
				if (p.getCategory().equals(PrivilegeNature.READ) && p.getElement() instanceof RelationShip) {
					RelationShip r = (RelationShip) p.getElement();
					rs.add(r);
				}
		}
		
		Set<Entity> entities = new HashSet<Entity>();
		entities.add(group.getEntryPoint());
		Set<RelationShip> validers = computeValidRelationShip(rs,entities);
		
		Set<Entity> se = new HashSet<Entity>();
		for (RelationShip r : validers) {
			se.add(r.getSource());
			se.add(r.getTarget());
		}
		
		((PrivilegeContentProvider) viewer.getContentProvider()).entities.clear();
		((PrivilegeContentProvider) viewer.getContentProvider()).entities.add(group.getEntryPoint());
		((PrivilegeContentProvider) viewer.getContentProvider()).entities.addAll(se);
		
		viewer.refresh();
		viewer.expandAll();
	}

	private Set<RelationShip> computeValidRelationShip(Set<RelationShip> rs, Set<Entity> entities) {
		Set<RelationShip> result = new HashSet<RelationShip>();
		Set<Entity> entities_tmp = new HashSet<Entity>(entities);
		for (RelationShip r : rs) {
			if (entities.contains(r.getSource()) || entities.contains(r.getTarget())) {
				result.add(r);
				entities_tmp.add(r.getSource());
				entities_tmp.add(r.getTarget());
			}
		}
		
		if (entities_tmp.size() != entities.size()) {
			return computeValidRelationShip(rs, entities_tmp);
		}
		return result;
	}

	private void addPrivilege(BasicElement elt, PrivilegeNature nature) {
		Privilege p = RequirementsFactory.eINSTANCE.createPrivilege();
		p.setCategory(nature);
		p.setElement(elt);
		
		group.getPrivileges().add(p);
	}

	private Set<RelationShip> searchRelationShips(Entity e) {
		Set<RelationShip> result = new HashSet<RelationShip>();

		EObject element = e;
		while (element != null && element.eContainer() != null && !(element.eContainer() instanceof RequirementsDefinition)) {
			element = element.eContainer();
		}
		RequirementsDefinition def = (RequirementsDefinition) element.eContainer();
		
		Set<RelationShip> relations = new HashSet<RelationShip>();
		for (ModelElement me : def.getChildElements())
			if (me instanceof RelationShip) {
				RelationShip r = (RelationShip) me;
				relations.add(r);
			}

		for (RelationShip r : relations) {
			if (r.getSource() == null || r.getTarget() == null) {
				if (modeler != null) {
					DeleteCommand cmd = new DeleteCommand(modeler.getEditingDomain(), Collections.singleton(r));
					modeler.getEditingDomain().getCommandStack().execute(cmd);
				}
			} else
				if (r.getSource().equals(e) || r.getTarget().equals(e))
					result.add(r);
		}

		return result;
	}

	/**
	 * This class provides the content for the tree in FileTree
	 */
	class PrivilegeContentProvider implements ITreeContentProvider {

		public Set<Entity> entities;
		public Set<RelationShip> relationships;

		public PrivilegeContentProvider() {
			entities = new HashSet<Entity>();
			relationships = new HashSet<RelationShip>();
		}

		/**
		 * Gets the children of the specified object
		 * 
		 * @param arg0
		 *            the parent object
		 * @return Object[]
		 */
		public Object[] getChildren(Object object) {
			if (object instanceof Entity) {
				Entity e = (Entity) object;
				entities.add(e);
				List<Object> result = new ArrayList<Object>();
				result.addAll(e.getAttributes());

				Set<RelationShip> all = searchRelationShips(e);
				result.addAll(all);
				return result.toArray();
			}
			return null;
		}

		private Set<RelationShip> searchVisibleRelationShips() {
			Set<RelationShip> result = new HashSet<RelationShip>();
			for (TreeItem ti : viewer.getTree().getItems()) {
				result.addAll(searchAllItems(ti));
			}
			return result;
		}

		private Set<RelationShip> searchAllItems(TreeItem treeItem) {
			Set<RelationShip> result = new HashSet<RelationShip>();
			for (TreeItem ti : treeItem.getItems()) {
				if (ti.getData() != null && ti.getData() instanceof RelationShip) {
					result.add((RelationShip) ti.getData());
					result.addAll(searchAllItems(ti));
				}
			}
			return result;
		}

		/**
		 * Gets the parent of the specified object
		 * 
		 * @param arg0
		 *            the object
		 * @return Object
		 */
		public Object getParent(Object object) {
			if (object instanceof Entity)
				return null;
			else
				return ((EObject) object).eContainer();
		}

		/**
		 * Returns whether the passed object has children
		 * 
		 * @param arg0
		 *            the parent object
		 * @return boolean
		 */
		public boolean hasChildren(Object arg0) {
			// Get the children
			Object[] obj = getChildren(arg0);

			// Return whether the parent has children
			return obj == null ? false : obj.length > 0;
		}

		/**
		 * Gets the root element(s) of the tree
		 * 
		 * @param arg0
		 *            the input data
		 * @return Object[]
		 */
		public Object[] getElements(Object object) {
			if (object instanceof PrivilegeGroup) {
				List<Entity> result = new ArrayList<Entity>(entities);
				result.remove(group.getEntryPoint());
				result.add(0, group.getEntryPoint());
				return result.toArray();
			} else
				return getChildren(object);
		}

		public void dispose() {
			// TODO Auto-generated method stub

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * This class provides the labels for the file tree
	 */

	class PrivilegeLabelProvider implements ILabelProvider {
		// The listeners
		private List listeners;

		/**
		 * Constructs a FileTreeLabelProvider
		 */
		public PrivilegeLabelProvider() {
			listeners = new ArrayList<Object>();
		}

		/**
		 * Gets the image to display for a node in the tree
		 * 
		 * @param arg0
		 *            the node
		 * @return Image
		 */
		public Image getImage(Object object) {
			if (object instanceof Entity)
				return ReqImageRegistry.getImage("ENTITY");
			else if (object instanceof Attribute)
				return ReqImageRegistry.getImage("ATTRIBUTE");
			else
				return ReqImageRegistry.getImage("RELATIONSHIP");
		}

		/**
		 * Gets the text to display for a node in the tree
		 * 
		 * @param arg0
		 *            the node
		 * @return String
		 */
		public String getText(Object object) {
			if (object instanceof BasicElement) {
				BasicElement elt = (BasicElement) object;
				return elt.getName();
			}
			return "";
		}

		/**
		 * Adds a listener to this label provider
		 * 
		 * @param arg0
		 *            the listener
		 */
		public void addListener(ILabelProviderListener arg0) {
			listeners.add(arg0);
		}

		/**
		 * Returns whether changes to the specified property on the specified
		 * element would affect the label for the element
		 * 
		 * @param arg0
		 *            the element
		 * @param arg1
		 *            the property
		 * @return boolean
		 */
		public boolean isLabelProperty(Object arg0, String arg1) {
			return false;
		}

		/**
		 * Removes the listener
		 * 
		 * @param arg0
		 *            the listener to remove
		 */
		public void removeListener(ILabelProviderListener arg0) {
			listeners.remove(arg0);
		}

		public void dispose() {
		}
	}
}
