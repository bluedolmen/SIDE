package com.bluexml.side.requirements.transformation.actions.dialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;

public class TransformModelDialog extends Dialog implements IDialogConstants {

	private static final int MIN_DIALOG_WIDTH = 500;
	private static final int MIN_DIALOG_HEIGHT = 300;
	private TreeViewer viewer;
	private Set<IConfigurationElement> selectedConfigurations;

	public TransformModelDialog(Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);
		setShellStyle(getShellStyle() | SWT.RESIZE);
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Requirements Transformation");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = MIN_DIALOG_WIDTH;
		dialogLayoutData.heightHint = MIN_DIALOG_HEIGHT;
		dialogComposite.setLayoutData(dialogLayoutData);

		createDialogContent(dialogComposite);

		return dialogComposite;
	}

	private void createDialogContent(Composite parent) {
		viewer = new TreeViewer(parent, SWT.BORDER | SWT.CHECK);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new InterpretationContentProvider());
		viewer.setLabelProvider(new InterpretationLabelProvider());
		viewer.setInput(this);
		viewer.expandAll();
		viewer.collapseAll();

		viewer.getTree().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TreeItem item = (TreeItem) event.item;
				// Check all childrens like this item
				checkAll(item);
				// If we uncheck, we unckeck all parents
				if (item.getChecked() == false) {
					uncheckInverse(item.getParentItem());
				}
				// If we check this node, we can verify that all sibling nodes
				// are checked
				if (item.getChecked() == true) {
					if (item.getParentItem() != null)
						checkInverse(item.getParentItem());
				}
			}

			private void checkInverse(TreeItem item) {
				boolean allChecked = true;
				for (TreeItem it : item.getItems())
					allChecked = allChecked && it.getChecked();
				item.setChecked(allChecked);
				if (item.getParentItem() != null)
					checkInverse(item.getParentItem());
			}

			private void uncheckInverse(TreeItem item) {
				if (item != null)
					item.setChecked(false);
				uncheckInverse(item.getParentItem());
			}

			private void checkAll(TreeItem item) {
				for (TreeItem it : item.getItems()) {
					it.setChecked(item.getChecked());
					checkAll(it);
				}
			}
		});
	}
	
	/**
	 * Save the values before the widgets are disposed
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		selectedConfigurations = new HashSet<IConfigurationElement>();
		for (TreeItem item : viewer.getTree().getItems())
			searchSelectedConfigurations(item);
		super.okPressed();
	}


	private void searchSelectedConfigurations(TreeItem item) {
		if (item.getData() instanceof IConfigurationElement && item.getChecked())
			selectedConfigurations.add((IConfigurationElement) item.getData());
		for (TreeItem it : item.getItems())
			searchSelectedConfigurations(it);
	}


	/**
	 * This class provides the content for the list
	 */
	class InterpretationContentProvider implements ITreeContentProvider {

		private InterpretationCategory rootCategory;

		public InterpretationContentProvider() {
		}

		public Object[] getChildren(Object object) {
			if (object instanceof InterpretationCategory) {
				InterpretationCategory elt = (InterpretationCategory) object;
				List<Object> l = new ArrayList<Object>();
				l.addAll(elt.getCategories());
				l.addAll(elt.getConfigurations());
				return l.toArray();
			}
			return null;
		}

		public Object getParent(Object object) {
			return null;
		}

		public boolean hasChildren(Object arg0) {
			// Get the children
			Object[] obj = getChildren(arg0);

			// Return whether the parent has children
			return obj == null ? false : obj.length > 0;
		}

		public Object[] getElements(Object object) {
			if (object instanceof TransformModelDialog) {
				rootCategory = new InterpretationCategory();
				rootCategory.initialize();
				List<Object> l = new ArrayList<Object>();
				l.addAll(rootCategory.getCategories());
				l.addAll(rootCategory.getConfigurations());
				return l.toArray();
			} else
				return getChildren(object);
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

	class InterpretationLabelProvider implements ILabelProvider {

		private List<Object> listeners;

		/**
		 * Constructs a InterpretationLabelProvider
		 */
		public InterpretationLabelProvider() {
			listeners = new ArrayList<Object>();
		}

		public Image getImage(Object object) {
			if (object instanceof IConfigurationElement) {
				return new Image(null, TransformModelDialog.class.getResourceAsStream("img/tool.png"));
			} else if (object instanceof InterpretationCategory) {
				return new Image(null, TransformModelDialog.class.getResourceAsStream("img/folder.png"));
			}
			return null;
		}

		public String getText(Object object) {
			if (object instanceof IConfigurationElement) {
				IConfigurationElement elt = (IConfigurationElement) object;
				return elt.getAttribute("label");
			} else if (object instanceof InterpretationCategory) {
				InterpretationCategory elt = (InterpretationCategory) object;
				return elt.getLabel();
			}
			return "";
		}

		public void addListener(ILabelProviderListener arg0) {
			listeners.add(arg0);
		}

		public boolean isLabelProperty(Object arg0, String arg1) {
			return false;
		}

		public void removeListener(ILabelProviderListener arg0) {
			listeners.remove(arg0);
		}

		public void dispose() {
		}
	}

	class InterpretationCategory {

		private String label;
		private List<InterpretationCategory> sub;
		private List<IConfigurationElement> configuration;

		public InterpretationCategory() {
			label = "";
			sub = new ArrayList<InterpretationCategory>();
			configuration = new ArrayList<IConfigurationElement>();
		}

		public InterpretationCategory(String category) {
			label = category;
			sub = new ArrayList<InterpretationCategory>();
			configuration = new ArrayList<IConfigurationElement>();
		}

		public Collection<? extends Object> getCategories() {
			return sub;
		}

		public Collection<? extends Object> getConfigurations() {
			return configuration;
		}

		public void initialize() {
			String extensionPointId = "com.bluexml.requirements.transformation.requirements_interpretation";
			IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(extensionPointId);
			for (IConfigurationElement config : contributions) {
				add(config, config.getAttribute("category"), this);
			}
		}

		private void add(IConfigurationElement config, String category, InterpretationCategory cat) {
			if (category != null && category.startsWith("/"))
				add(config, category.substring(1), cat);
			else if (category == null || category.length() == 0) {
				cat.addInterpretation(config);
			} else {
				String currentCategory = category.split("/")[0];
				InterpretationCategory ic = cat.getCategory(currentCategory);

				add(config, category.substring(currentCategory.length()), ic);
			}
		}

		private InterpretationCategory getCategory(String category) {
			if (category == null || category.length() == 0) {
				return this;
			} else {
				InterpretationCategory result = null;
				for (InterpretationCategory tmp : sub) {
					if (tmp.getLabel().equals(category)) {
						result = tmp;
						break;
					}
				}
				if (result == null) {
					result = new InterpretationCategory(category);
					sub.add(result);
				}
				return result;
			}
		}

		private void addInterpretation(IConfigurationElement config) {
			configuration.add(config);
		}

		public String getLabel() {
			return label;
		}

	}

	public Set<IConfigurationElement> getSelectedConfigurations() {
		return selectedConfigurations;
	}
}
