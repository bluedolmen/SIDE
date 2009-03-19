package com.bluexml.side.application.ui.action;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationElement;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.SWTResourceManager;
import com.bluexml.side.application.ui.action.tree.Generator;
import com.bluexml.side.application.ui.action.tree.Metamodel;
import com.bluexml.side.application.ui.action.tree.OptionGenerator;
import com.bluexml.side.application.ui.action.tree.Technology;
import com.bluexml.side.application.ui.action.tree.TechnologyVersion;
import com.bluexml.side.application.ui.action.tree.TreeElement;

public class ApplicationDialog extends Dialog {

	private Text config_description;
	private TreeViewer viewer;
	private Tree tree_1;
	private static Combo combo_1;
	public static boolean loadingTree = false;
	
	private Set<Metamodel> metamodelSet;
	private Set<Technology> technologySet;
	private Set<TechnologyVersion> technologyVersionSet;
	private Set<Generator> generatorSet;
	private Browser documentationText;
	private static Application application;
	private IFile model;
	
	/**
	 * Create the dialog
	 * @param parentShell
	 * @param rwm_model 
	 */
	public ApplicationDialog(Shell parentShell, IFile file) {
		super(parentShell);
		
		try {
			URI uri = URI.createFileURI(file.getRawLocation().toFile().getAbsolutePath());
			XMIResource resource = new XMIResourceImpl(uri);

			FileInputStream fi = new FileInputStream(file.getRawLocation().toFile());
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put(ApplicationPackage.eINSTANCE.getNsURI(), ApplicationPackage.eINSTANCE);
			map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
			resource.load(fi, map);
			
			application = (Application) resource.getContents().get(0);
			model = file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		metamodelSet = new HashSet<Metamodel>();
		technologySet = new HashSet<Technology>();
		technologyVersionSet = new HashSet<TechnologyVersion>();
		generatorSet = new HashSet<Generator>();
	}

	private void refreshConfiguration() {
		loadingTree = true;
		if (combo_1.getSelectionIndex() != -1) {
			String name = combo_1.getItem(combo_1.getSelectionIndex());
			Configuration configuration = application.getConfiguration(name);

			//Refresh documentation
			if (configuration != null)
				if (configuration.getDescription() != null)
					config_description.setText(configuration.getDescription());
			
			//Refresh tree
			initializeTree();
			Set<TreeItem> generators = collectGenerators();
			configureTree(configuration, generators);
		}
		loadingTree = false;
	}
	
	private Set<TreeItem> collectGenerators() {
		Set<TreeItem> generators = new HashSet<TreeItem>();
		collectGenerators(viewer.getTree().getItems(), generators);
		return generators;
	}

	private void collectGenerators(TreeItem[] items, Set<TreeItem> generators) {
		for (TreeItem item : items) {
			if (item.getData() instanceof Generator)
				generators.add(item);
			collectGenerators(item.getItems(), generators);
		}
	}

	private void configureTree(Configuration configuration, Set<TreeItem> generators) {
		for (TreeItem item : generators) {
			Generator g = (Generator) item.getData();
			for (ConfigurationElement ce : configuration.getElements())
				if (ce.getId_generator().equals(g.getId())) {
					g.setChecked(true);
					g.setEnabled(true);
					viewer.update(g, null);
			
					for (OptionGenerator o : g.getOptions())
						for (Option opt : ce.getOptions()) {
							o.setEnabled(true);
							if (opt.getKey().equals(o.getKey())) {
								o.setChecked(true);
							}
						}
					//Refresh options
					for (TreeItem option : item.getItems()) {
						viewer.update(option.getData(), null);
					}
			
					refreshParents(item);
				}
		}
	}

	private void refreshParents(TreeItem item) {
		TreeItem parent = item.getParentItem();
		if (parent != null) {
			//Check and enable parent
			TreeElement el = (TreeElement) parent.getData();
			el.setChecked(true);
			el.setEnabled(true);
			viewer.update(parent.getData(), null);
			
			//Enable all sibling nodes
			for (TreeItem sibItem : parent.getItems()) {
				TreeElement sibEl = (TreeElement) sibItem.getData();
				sibEl.setEnabled(true);
				viewer.update(sibItem.getData(), null);
			}
			
			refreshParents(parent);
		}
	}

	private void initializeTree() {
		TreeItem[] items = viewer.getTree().getItems();
		initializeTree(items);
	}
	
	private void initializeTree(TreeItem[] items) {
		for (TreeItem item : items) {
			TreeElement el = (TreeElement) item.getData();
			el.setChecked(false);
			el.setEnabled(false);			
			if (item.getData() instanceof Metamodel)
				el.setEnabled(true);
			
			viewer.update(item.getData(), null);
			
			initializeTree(item.getItems());
		}
	}

	/**
	 * Create contents of the dialog
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);

		final Composite composite = new Composite(container, SWT.NONE);
		composite.setBounds(10, 10, 477, 36);

		combo_1 = new Combo(composite, SWT.READ_ONLY);
		combo_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				refreshConfiguration();
			}
		});
		combo_1.setBounds(150, 3, 209, 25);
		
		//Fill the combo
		for (ModelElement elt : application.getElements()) {
			if (elt instanceof Configuration) {
				Configuration configuration = (Configuration) elt;
				combo_1.add(configuration.getName());
			}
		}
		if (combo_1.getItemCount() > 0)
			combo_1.select(0);

		final Label listOfConfigurayionsLabel = new Label(composite, SWT.NONE);
		listOfConfigurayionsLabel.setBounds(10, 10, 134, 15);
		listOfConfigurayionsLabel.setText("List of configurations :");

		final Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				Configuration config = ApplicationFactory.eINSTANCE.createConfiguration();

				int i = 0;
				String newName = "New configuration";
				while (application.getConfiguration(newName) != null) {
					i++;
					newName = "New configuration ("+i+")";
				}
				config.setName(newName);
				combo_1.add(newName);
				combo_1.select(combo_1.getItemCount()-1);
				application.getElements().add(config);
				
				refreshConfiguration();
			}
		});
		button.setImage(SWTResourceManager.getImage(ApplicationDialog.class, "tree/img/add.png"));
		button.setBounds(433, 2, 42, 28);

		final Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (combo_1.getItemCount() > 0) {
					if (combo_1.getSelectionIndex() != -1) {
						String name = combo_1.getItem(combo_1.getSelectionIndex());
						Configuration config = application.getConfiguration(name);
						if (config != null) {
							application.getElements().remove(config);
							combo_1.remove(name);
							if (combo_1.getItemCount() > 0)
								combo_1.select(0);
						}
					}
				}
				
				refreshConfiguration();
			}
		});
		button_1.setImage(SWTResourceManager.getImage(ApplicationDialog.class, "tree/img/delete.png"));
		button_1.setBounds(396, 2, 42, 28);

		final Button button_2 = new Button(composite, SWT.NONE);
		button_2.setBounds(359, 2,42, 28);
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (combo_1.getItemCount() > 0) {
					if (combo_1.getSelectionIndex() != -1) {
						String name = combo_1.getItem(combo_1.getSelectionIndex());
						Configuration config = application.getConfiguration(name);						
						ConfigurationDialog dialog = new ConfigurationDialog(new Shell(), config.getName(), config.getDescription());
						if (dialog.open() == Window.OK) {
							config.setName(dialog.getName());
							config.setDescription(dialog.getDescription());
							
							String[] items = combo_1.getItems();
							int index = combo_1.getSelectionIndex();
							items[index] = dialog.getName();
							combo_1.removeAll();
							combo_1.setItems(items);
							combo_1.select(index);
							config_description.setText(config.getDescription());
						}
					}
				}
			}
		});
		button_2.setImage(SWTResourceManager.getImage(ApplicationDialog.class, "tree/img/edit.png"));

		viewer = new TreeViewer(container, SWT.BORDER);
		tree_1 = viewer.getTree();
		tree_1.setBounds(10, 52, 480, 466);
		viewer.setContentProvider(new ConfigurationContentProvider());
		viewer.setLabelProvider(new ConfigurationLabelProvider());
		viewer.setInput(this);
		viewer.expandAll();
		
		viewer.getTree().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				documentationText.setText(builDocumentationText());
				TreeItem item = (TreeItem) event.item;
				TreeElement el = (TreeElement) item.getData();

				// Check if enabled
				if (el.isEnabled()) {

					// Inverse
					el.setChecked(!(el.isChecked()));
					viewer.update(el, null);

					if (el.isChecked()) {
						// Enable all sub elements
						enableAllSubElements(item);

						// Unckeck all sibling nodes
						if (!(item.getData() instanceof OptionGenerator)) {
							for (TreeItem ti : item.getParentItem().getItems())
								if (!ti.equals(item)) {
									TreeElement subEl = (TreeElement) ti.getData();
									if (subEl.isChecked()) {
										subEl.setChecked(false);
										disableAllSubElements(ti);
										viewer.update(subEl, null);
									}
								}
						}
					} else {
						// Enable all sub elements
						disableAllSubElements(item);
					}
				}
			}

			private void enableAllSubElements(TreeItem item) {
				for (TreeItem ti : item.getItems()) {
					TreeElement el = (TreeElement) ti.getData();
					if (!el.isEnabled()) {
						el.setEnabled(true);
						viewer.update(el, null);
					}
					if (el.isChecked())
						enableAllSubElements(ti);
				}
			}
			
			private void disableAllSubElements(TreeItem item) {
				for (TreeItem ti : item.getItems()) {
					TreeElement el = (TreeElement) ti.getData();
					if (el.isEnabled()) {
						el.setEnabled(false);
						viewer.update(el, null);
					}
					disableAllSubElements(ti);
				}
			}
		});

		config_description = new Text(container, SWT.READ_ONLY | SWT.BORDER | SWT.WRAP);
		config_description.setBounds(493, 10, 297, 169);

		documentationText = new Browser(container, SWT.NONE);
		documentationText.setBounds(500, 185, 287, 331);

		
		refreshConfiguration();
		
		return container;
	}
	
	private String builDocumentationText() {
		String result = "<font face=\"Helvetica, Arial\" size=\"2\">";
		TreeItem[] items = viewer.getTree().getSelection();
		if (items.length > 0) {
			TreeItem item = items[0];

			if (item.getData() instanceof Metamodel) {
				result += "<b>Documentation :</b><br/><br/>";
				Metamodel m = (Metamodel) item.getData();
				if (m.getDescription() != null)
					result += m.getDescription();
				result += "<br/><br/>";
				result += "Lien : ";
				result += "<a href=\"" + m.getURL() + "\">" + m.getURL()
						+ "</a>";
			} else if (item.getData() instanceof OptionGenerator) {
				OptionGenerator o = (OptionGenerator) item.getData();
				if (o.getDescription() != null)
					result += o.getDescription();
			} else {
				Technology t = null;
				if (item.getData() instanceof Technology)
					t = (Technology) item.getData();
				else if (item.getData() instanceof TechnologyVersion)
					t = ((TechnologyVersion) item.getData()).getTechnology();
				else if (item.getData() instanceof Generator)
					t = ((Generator) item.getData()).getTechnologyVersion()
							.getTechnology();

				if (t != null) {
					result += "<b>Documentation :</b><br/><br/>";
					if (t.getDescription() != null)
						result += t.getDescription();
					result += "<br/><br/>";
					result += "Lien : ";
					result += "<a href=\"" + t.getURL() + "\">" + t.getURL()
							+ "</a>";
				}
			}
		}
		result += "</font>";
		return result;
	}
	
	class ConfigurationContentProvider implements ITreeContentProvider {

		public ConfigurationContentProvider() {
		}

		public Object[] getChildren(Object object) {
			if (object instanceof Metamodel) {
				Metamodel elt = (Metamodel) object;
				return elt.getTechnology().toArray();
			} else if (object instanceof Technology) {
				Technology elt = (Technology) object;
				return elt.getTechnologyVersion().toArray();
			} else if (object instanceof TechnologyVersion) {
				TechnologyVersion elt = (TechnologyVersion) object;
				return elt.getGenerator().toArray();
			} else if (object instanceof Generator) {
				Generator elt = (Generator) object;
				return elt.getOptions().toArray();
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
			if (object instanceof ApplicationDialog) {
				initialize();
				return metamodelSet.toArray();
			} else
				return getChildren(object);
		}

		public void initialize() {
			String extensionPointId = "com.bluexml.application.com_bluexml_application_configuration";
			IConfigurationElement[] contributions = Platform
					.getExtensionRegistry().getConfigurationElementsFor(
							extensionPointId);
			// Scan for metamodels
			for (IConfigurationElement config : contributions) {
				if (config.getName().equalsIgnoreCase("metamodel")) {
					Metamodel m = new Metamodel(config);
					metamodelSet.add(m);
				}
			}
			// Scan for technology
			for (IConfigurationElement config : contributions) {
				if (config.getName().equalsIgnoreCase("technology")) {
					Technology t = new Technology(config, metamodelSet);
					technologySet.add(t);
				}
			}
			// Scan for technology version
			for (IConfigurationElement config : contributions) {
				if (config.getName().equalsIgnoreCase("technologyVersion")) {
					TechnologyVersion tv = new TechnologyVersion(config,
							technologySet);
					technologyVersionSet.add(tv);
				}
			}
			// Scan for generator
			for (IConfigurationElement config : contributions) {
				if (config.getName().equalsIgnoreCase("generatorVersion")) {
					Generator g = new Generator(config, technologyVersionSet);
					generatorSet.add(g);

					// Scan for generator options
					for (IConfigurationElement options : config.getChildren()) {
						if (options.getName().equalsIgnoreCase("option")) {
							new OptionGenerator(options, g);
						}
					}
				}
				
			}
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	class ConfigurationLabelProvider implements ILabelProvider {

		private List<Object> listeners;

		/**
		 * Constructs a InterpretationLabelProvider
		 */
		public ConfigurationLabelProvider() {
			listeners = new ArrayList<Object>();
		}

		public Image getImage(Object object) {
			String suffix = "";
			if (object instanceof TreeElement) {
				TreeElement te = (TreeElement) object;
				if (te.isEnabled())
					suffix += "_enabled";
				else
					suffix += "_disabled";
				if (te.isChecked())
					suffix += "_checked";
				else
					suffix += "_unchecked";
			}

			if (object instanceof Metamodel) {
				return new Image(null, ApplicationDialog.class
						.getResourceAsStream("tree/img/metamodel/metamodel"
								+ suffix + ".png"));
			} else if (object instanceof Technology) {
				return new Image(null, ApplicationDialog.class
						.getResourceAsStream("tree/img/technology/technology"
								+ suffix + ".png"));
			} else if (object instanceof TechnologyVersion) {
				return new Image(
						null,
						ApplicationDialog.class
								.getResourceAsStream("tree/img/technologyVersion/technologyVersion"
										+ suffix + ".png"));
			} else if (object instanceof Generator) {
				return new Image(null, ApplicationDialog.class
						.getResourceAsStream("tree/img/generator/generator"
								+ suffix + ".png"));
			} else if (object instanceof OptionGenerator) {
				return new Image(null, ApplicationDialog.class
						.getResourceAsStream("tree/img/optionGenerator/options"
								+ suffix + ".png"));
			}
			return null;
		}

		public String getText(Object object) {
			if (object instanceof IConfigurationElement) {
				IConfigurationElement elt = (IConfigurationElement) object;
				return elt.getAttribute("label");
			} else if (object instanceof Metamodel) {
				Metamodel elt = (Metamodel) object;
				return elt.getLabel();
			} else if (object instanceof Technology) {
				Technology elt = (Technology) object;
				return elt.getLabel();
			} else if (object instanceof TechnologyVersion) {
				TechnologyVersion elt = (TechnologyVersion) object;
				return elt.getVersion();
			} else if (object instanceof Generator) {
				Generator elt = (Generator) object;
				return elt.getVersion();
			} else if (object instanceof OptionGenerator) {
				OptionGenerator elt = (OptionGenerator) object;
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

	/**
	 * Create contents of the button bar
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(800, 600);
	}
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("application", new XMIResourceFactoryImpl());
			resourceSet.getPackageRegistry().put(ApplicationPackage.eNS_URI, ApplicationPackage.eINSTANCE);
			org.eclipse.emf.ecore.resource.Resource resource = resourceSet.createResource(URI.createURI(model.getRawLocation().toFile().getAbsolutePath()));
			resource.getContents().add(application);
			
			try {
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(model.getRawLocation().toFile().getAbsolutePath()));
				Map<String, Object> saveOptions = new HashMap<String, Object>();
				resource.save(out, saveOptions);
				out.close();
				model.refreshLocal(-1, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		super.buttonPressed(buttonId);
	}
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Configuration");
	}
	
	static public Configuration getCurrentConfiguration() {
		if (combo_1.getSelectionIndex() != -1) {
			String name = combo_1.getItem(combo_1.getSelectionIndex());
			Configuration configuration = application.getConfiguration(name);
			return configuration;
		}
		return null;
	}

}
