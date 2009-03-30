package com.bluexml.side.application.ui.action;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
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
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationElement;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.ui.SWTResourceManager;
import com.bluexml.side.application.ui.action.table.GeneratorParameter;
import com.bluexml.side.application.ui.action.table.GeneratorParameterCellModifier;
import com.bluexml.side.application.ui.action.table.GeneratorParameterContentProvider;
import com.bluexml.side.application.ui.action.table.GeneratorParameterDataStructure;
import com.bluexml.side.application.ui.action.table.GeneratorParameterLabelProvider;
import com.bluexml.side.application.ui.action.tree.Generator;
import com.bluexml.side.application.ui.action.tree.Metamodel;
import com.bluexml.side.application.ui.action.tree.OptionGenerator;
import com.bluexml.side.application.ui.action.tree.Technology;
import com.bluexml.side.application.ui.action.tree.TechnologyVersion;
import com.bluexml.side.application.ui.action.tree.TreeElement;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;

public class ApplicationDialog extends Dialog {

	private static final int APPLY_ID = IDialogConstants.CLIENT_ID + 2;
	private static final int GEN_ID = IDialogConstants.CLIENT_ID + 1;
	private Text config_description;
	private TreeViewer viewer;
	private Tree tree_1;
	private static Combo configurationList;
	public static boolean loadingTree = false;
	public static boolean applicationModified = false;

	private Map<String, Metamodel> metamodelSet;
	private Map<String, Technology> technologySet;
	private Map<String, TechnologyVersion> technologyVersionSet;
	private Map<String, Generator> generatorSet;
	private Map<String, GeneratorParameter> configurationParameters;
	private Map<String, List<String>> paramConfByGenerator;
	private Browser documentationText;
	private static Application application;
	private IFile model;
	private Table generatorParameters;
	private String[] columnNames;
	private String columnNameValue = "Value";
	private String columnNameLabel = "Label";
	private GeneratorParameterDataStructure dataStructure;
	private TableViewer generatorParametersViewer;
	private Button verboseButton;
	private Button updateTargetButton;
	private Button cleanButton;
	private Text destinationText;
	private Text logText;
	private org.eclipse.swt.widgets.List list;
	private GeneratorParameterContentProvider generatorParameterContentProvider;
	private GeneratorParameterLabelProvider generatorParameterLabelProvider;
	private GeneratorParameterCellModifier generatorParameterCellModifier;
	private Button applyButton;
	private TabFolder tabFolder;

	private static String KEY_VERBOSE = "generation.options.verbose";
	private static String KEY_CLEAN = "generation.options.clean";
	private static String KEY_UPDATE = "generation.options.updateTgt";
	private static String KEY_LOGPATH = "generation.options.logPath";
	private static String KEY_GENPATH = "generation.options.destinationPath";

	public static List<String> staticFieldsName = Arrays.asList(KEY_CLEAN,
			KEY_GENPATH, KEY_LOGPATH, KEY_UPDATE, KEY_VERBOSE);

	private static String EXTENSIONPOINT_ID = "com.bluexml.side.Application.com_bluexml_application_configuration";

	/**
	 * Create the dialog
	 * 
	 * @param parentShell
	 * @param rwm_model
	 */
	public ApplicationDialog(Shell parentShell, IFile file) {
		super(parentShell);
		try {
			URI uri = URI.createFileURI(file.getRawLocation().toFile()
					.getAbsolutePath());
			XMIResource resource = new XMIResourceImpl(uri);

			FileInputStream fi = new FileInputStream(file.getRawLocation()
					.toFile());
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put(ApplicationPackage.eINSTANCE.getNsURI(),
					ApplicationPackage.eINSTANCE);
			map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION,
					Boolean.TRUE);
			resource.load(fi, map);

			application = (Application) resource.getContents().get(0);
			model = file;
		} catch (Exception e) {
			e.printStackTrace();
		}

		metamodelSet = new HashMap<String, Metamodel>();
		technologySet = new HashMap<String, Technology>();
		technologyVersionSet = new HashMap<String, TechnologyVersion>();
		generatorSet = new HashMap<String, Generator>();
		configurationParameters = new HashMap<String, GeneratorParameter>();
		paramConfByGenerator = new HashMap<String, List<String>>();
	}

	public void refreshConfiguration() {
		loadingTree = true;
		if (configurationList.getSelectionIndex() != -1) {
			String name = configurationList.getItem(configurationList
					.getSelectionIndex());
			Configuration configuration = application.getConfiguration(name);

			// Refresh documentation
			if (configuration != null)
				if (configuration.getDescription() != null)
					config_description.setText(configuration.getDescription());

			// Refresh tree
			initializeTree();

			Set<TreeItem> generators = collectGenerators();
			configureTree(configuration, generators);

			// Refresh static generator parameters
			initializeStaticGeneratorParameters();
			// Refresh dynamic generator parameters
			refreshGeneratorOptions(configuration);
		}
		loadingTree = false;
	}

	public void refreshGeneratorOptions(Configuration configuration) {
		initializeDynamicGeneratorParameters();
		configureGeneratorOptions(configuration);
	}

	public void refreshGeneratorOptions() {
		String name = configurationList.getItem(configurationList
				.getSelectionIndex());
		Configuration configuration = application.getConfiguration(name);
		refreshGeneratorOptions(configuration);
	}

	private void initializeStaticGeneratorParameters() {
		ConfigurationParameters verboseParam = ApplicationUtil
				.getConfigurationParmeterByKey(KEY_VERBOSE);
		if (verboseParam != null) {
			verboseButton.setSelection(Boolean.parseBoolean(verboseParam
					.getValue()));
		}

		ConfigurationParameters cleanParam = ApplicationUtil
				.getConfigurationParmeterByKey(KEY_CLEAN);
		if (cleanParam != null) {
			cleanButton.setSelection(Boolean
					.parseBoolean(cleanParam.getValue()));
		}

		ConfigurationParameters updateParam = ApplicationUtil
				.getConfigurationParmeterByKey(KEY_UPDATE);
		if (updateParam != null) {
			updateTargetButton.setSelection(Boolean.parseBoolean(updateParam
					.getValue()));
		}

		ConfigurationParameters logPathParam = ApplicationUtil
				.getConfigurationParmeterByKey(KEY_LOGPATH);
		if (logPathParam != null) {
			logText.setText(logPathParam.getValue());
		}

		ConfigurationParameters updatePathParam = ApplicationUtil
				.getConfigurationParmeterByKey(KEY_GENPATH);
		if (updatePathParam != null) {
			destinationText.setText(updatePathParam.getValue());
		}
	}

	private void initializeDynamicGeneratorParameters() {
		// List Generator Id used
		Configuration conf = getCurrentConfiguration();
		List<String> confIds = new ArrayList<String>();
		for (ConfigurationElement elem : conf.getElements()) {
			confIds.add(elem.getId_generator());
		}
		if (dataStructure != null) {
			dataStructure.getData().clear();
		}
		HashMap<String, GeneratorParameter> neededParam = new HashMap<String, GeneratorParameter>();
		dataStructure = new GeneratorParameterDataStructure();
		// We get all params key needed by Generator
		for (String genId : confIds) {
			List<String> paramList = paramConfByGenerator.get(genId);
			if (paramList != null) {
				// We construct one list without twice the same id
				for (String paramId : paramList) {
					if (!neededParam.containsKey(paramId)) {
						neededParam.put(paramId, configurationParameters
								.get(paramId));
					}
				}
			}
		}
		for (GeneratorParameter genParam : neededParam.values()) {
			genParam.setValue("");
			dataStructure.addGeneratorParameter(genParam);
		}

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

	/**
	 * Will search the dataStructure where is save all generation option and
	 * will add value from the application model
	 * 
	 * @param configuration
	 */
	private void configureGeneratorOptions(Configuration configuration) {
		Configuration conf = getCurrentConfiguration();
		for (ConfigurationParameters confParam : conf.getParameters()) {
			GeneratorParameter genParam = dataStructure
					.getParamMatching(confParam.getKey());
			if (genParam != null) {
				genParam.setValue(confParam.getValue());
			}
		}
		// Update
		if (generatorParametersViewer == null) {
			createTableViewer();
		} else {
			refreshDataStructure();
		}
	}

	private void configureTree(Configuration configuration,
			Set<TreeItem> generators) {
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
					// Refresh options
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
			// Check and enable parent
			TreeElement el = (TreeElement) parent.getData();
			el.setChecked(true);
			el.setEnabled(true);
			viewer.update(parent.getData(), null);

			// Enable all sibling nodes
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
	 * Open file dialog box to select files (here a model)
	 */
	protected void SelectModelFileDialog() {
		String filePath = null;
		String fileName = null;
		ElementTreeSelectionDialog ets = new ElementTreeSelectionDialog(Display
				.getDefault().getActiveShell(), new WorkbenchLabelProvider(),
				new BaseWorkbenchContentProvider());
		ets.setBlockOnOpen(true);
		ets.setAllowMultiple(true);
		ets.setTitle("Select model file");
		ets.setMessage("Select model file (no diagram file)");
		ets.setInput(ResourcesPlugin.getWorkspace().getRoot());

		if (ElementTreeSelectionDialog.OK == ets.open()) {
			Object[] result = ets.getResult();
			for (Object o : result) {
				IFile file = (IFile) o;
				filePath = file.getFullPath().toPortableString();
				fileName = file.getName();
				if (filePath != null) {
					// Add to list
					list.add(filePath);
					ApplicationDialog.modificationMade();
					// Register it
					Model model = ApplicationFactory.eINSTANCE.createModel();
					model.setFile(filePath);
					model.setName(fileName);
					application.getElements().add(model);
				}
			}
		}

	}

	/**
	 * Create contents of the dialog
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		columnNames = new String[2];
		columnNames[0] = columnNameLabel;
		columnNames[1] = columnNameValue;

		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);

		documentationText = new Browser(container, SWT.NONE);
		documentationText.setBounds(493, 185, 294, 318);

		tabFolder = new TabFolder(container, SWT.NONE);
		
		tabFolder.setBounds(15, 39, 472, 464);

		final TabItem globalConfigurationTabItem = new TabItem(tabFolder,
				SWT.NONE);
		globalConfigurationTabItem.setText("Global Configuration");

		final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				if (!tabFolder.getEnabled()) {
					showAlert("No Configuration", "You must create a configuration to edit it.");
				}
			}
		});
		globalConfigurationTabItem.setControl(composite_1);

		viewer = new TreeViewer(composite_1, SWT.BORDER);
		tree_1 = viewer.getTree();
		tree_1.setBounds(5, 5, 454, 392);
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
						if (!(el instanceof OptionGenerator)) {
							for (TreeItem ti : item.getParentItem().getItems())
								if (!ti.equals(item)) {
									TreeElement subEl = (TreeElement) ti
											.getData();
									if (subEl.isChecked()) {
										subEl.setChecked(false);
										disableAllSubElements(ti);
										viewer.update(subEl, null);
									}
								}
						}
						if (el instanceof Generator) {
							refreshGeneratorOptions();
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
					if (el.isChecked()) {
						if (el instanceof Generator) {
							refreshGeneratorOptions();
						}
						enableAllSubElements(ti);
					}
				}
			}

			private void disableAllSubElements(TreeItem item) {
				for (TreeItem ti : item.getItems()) {
					TreeElement el = (TreeElement) ti.getData();
					if (el.isEnabled()) {
						el.setEnabled(false);
						if (el instanceof Generator) {
							refreshGeneratorOptions();
						}
						viewer.update(el, null);
					}
					disableAllSubElements(ti);
				}
			}
		});

		final TabItem optionsTabItem = new TabItem(tabFolder, SWT.NONE);
		optionsTabItem.setText("Options");

		final Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		optionsTabItem.setControl(composite_2);

		cleanButton = new Button(composite_2, SWT.CHECK);
		cleanButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ConfigurationParameters param = ApplicationUtil
						.getConfigurationParmeterByKey(KEY_CLEAN);
				if (param != null) {
					Button b = (Button) e.getSource();
					param.setValue(Boolean.toString(b.getSelection()));
				}
				ApplicationDialog.modificationMade();
			}
		});
		cleanButton.setText("Clean");
		cleanButton.setBounds(20, 40, 120, 16);

		final Label generationsOptionsLabel = new Label(composite_2, SWT.NONE);
		generationsOptionsLabel.setFont(SWTResourceManager.getFont("", 12,
				SWT.BOLD));
		generationsOptionsLabel.setText("General generation options");
		generationsOptionsLabel.setBounds(10, 10, 240, 24);

		verboseButton = new Button(composite_2, SWT.CHECK);
		verboseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ConfigurationParameters param = ApplicationUtil
						.getConfigurationParmeterByKey(KEY_VERBOSE);
				if (param != null) {
					Button b = (Button) e.getSource();
					param.setValue(Boolean.toString(b.getSelection()));
				}
				ApplicationDialog.modificationMade();
			}
		});
		verboseButton.setText("Verbose");
		verboseButton.setBounds(20, 60, 93, 16);

		updateTargetButton = new Button(composite_2, SWT.CHECK);
		updateTargetButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ConfigurationParameters param = ApplicationUtil
						.getConfigurationParmeterByKey(KEY_UPDATE);
				if (param != null) {
					Button b = (Button) e.getSource();
					param.setValue(Boolean.toString(b.getSelection()));
				}
				ApplicationDialog.modificationMade();
			}
		});
		updateTargetButton
				.setToolTipText("If checked will move uploaded files to your application.");
		updateTargetButton.getAccessible().addAccessibleListener(
				new AccessibleAdapter() {
					public void getHelp(AccessibleEvent e) {
						e.result = "If checked will move uploaded files to your application.";
					}
				});
		updateTargetButton.setText("Update target");
		updateTargetButton.setBounds(20, 80, 93, 16);

		final Label generationsOptionsLabel_1 = new Label(composite_2, SWT.NONE);
		generationsOptionsLabel_1.setBounds(10, 172, 439, 24);
		generationsOptionsLabel_1.setFont(SWTResourceManager.getFont("", 12,
				SWT.BOLD));
		generationsOptionsLabel_1
				.setText("Specific configuration generation options");

		generatorParameters = new Table(composite_2, SWT.BORDER);
		generatorParameters.getHorizontalBar().setVisible(false);
		generatorParameters.getHorizontalBar().setEnabled(false);
		generatorParameters.setLinesVisible(true);
		generatorParameters.setHeaderVisible(true);
		generatorParameters.setBounds(20, 202, 420, 218);
		generatorParameters.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				GeneratorParameter param = (GeneratorParameter) ((StructuredSelection) generatorParametersViewer
						.getSelection()).getFirstElement();
				documentationText.setText(buildHelpDocumentationText(param
						.getDocumentation()));

			}
		});
		final TableColumn newColumnTableColumn = new TableColumn(
				generatorParameters, SWT.RIGHT);
		generatorParameters.setSortColumn(newColumnTableColumn);
		newColumnTableColumn.setWidth(100);
		newColumnTableColumn.setText(columnNames[0]);

		final TableColumn newColumnTableColumn_1 = new TableColumn(
				generatorParameters, SWT.LEFT);
		newColumnTableColumn_1.setWidth(315);
		newColumnTableColumn_1.setText(columnNames[1]);

		logText = new Text(composite_2, SWT.BORDER);
		logText.addFocusListener(new FocusAdapter() {
			public void focusLost(final FocusEvent e) {
				ConfigurationParameters param = ApplicationUtil
						.getConfigurationParmeterByKey(KEY_LOGPATH);
				if (param != null) {
					Text t = (Text) e.getSource();
					param.setValue(t.getText());
				}
				ApplicationDialog.modificationMade();
			}
		});

		logText.setBounds(115, 108, 260, 25);

		final Label logLabel = new Label(composite_2, SWT.NONE);
		logLabel.setAlignment(SWT.RIGHT);
		logLabel.setText("Log File :");
		logLabel.setBounds(20, 115, 93, 15);

		final Label destinationLabel = new Label(composite_2, SWT.NONE);
		destinationLabel.setAlignment(SWT.RIGHT);
		destinationLabel.setBounds(20, 145, 93, 15);
		destinationLabel.setText("Generation path :");

		destinationText = new Text(composite_2, SWT.BORDER);
		destinationText.addFocusListener(new FocusAdapter() {
			public void focusLost(final FocusEvent e) {
				ConfigurationParameters param = ApplicationUtil
						.getConfigurationParmeterByKey(KEY_GENPATH);
				if (param != null) {
					Text t = (Text) e.getSource();
					param.setValue(t.getText());
				}
				ApplicationDialog.modificationMade();
			}
		});
		destinationText.setBounds(115, 140, 260, 25);

		final Button browseLogPathButton = new Button(composite_2, SWT.NONE);
		browseLogPathButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				String filePath = null;
				FileDialog fd = new FileDialog(getShell(), SWT.OK);
				fd.setFilterNames(new String[] { "Log Files", "All Files (*.*)" });
				fd.setFilterExtensions(new String[] { "*.log", "*.*" });
				if (logText.getText() == null || logText.getText().length() == 0) {
					fd.setFilterPath(ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toPortableString());
				} else {
					fd.setFilterPath(logText.getText());
				}
				
				filePath = fd.open();
				if (filePath != null) {
					logText.setText(filePath);
					modificationMade();
				}
			}
		});
		browseLogPathButton.setText("Browse");
		browseLogPathButton.setBounds(381, 106, 75, 25);

		final Button browseGenPathButton = new Button(composite_2, SWT.NONE);
		browseGenPathButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				String folderPath = null;
				DirectoryDialog dd = new DirectoryDialog(getShell(), SWT.OK);
				if (destinationText.getText() == null || destinationText.getText().length() == 0) {
					dd.setFilterPath(ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toPortableString());
				} else {
					dd.setFilterPath(destinationText.getText());
				}
				
				folderPath = dd.open();
				if (folderPath != null) {
					destinationText.setText(folderPath);
					modificationMade();
				}
			}
		});
		browseGenPathButton.setText("Browse");
		browseGenPathButton.setBounds(381, 140, 75, 25);

		final TabItem modelsTabItem = new TabItem(tabFolder, SWT.NONE);
		modelsTabItem.setText("Models");

		final Composite composite = new Composite(tabFolder, SWT.NONE);
		modelsTabItem.setControl(composite);

		final Button addModelButton = new Button(composite, SWT.NONE);
		addModelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				SelectModelFileDialog();
			}
		});
		addModelButton.setText("Add Model");
		addModelButton.setBounds(10, 175, 130, 25);

		list = new org.eclipse.swt.widgets.List(composite, SWT.BORDER);
		list.setBounds(10, 38, 444, 115);

		for (ModelElement elem : application.getElements()) {
			if (elem instanceof Model) {
				Model model = (Model) elem;
				list.add(model.getFile());
			}
		}

		final Button removeModelButton = new Button(composite, SWT.NONE);
		removeModelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int select = list.getSelectionIndex();
				if (select != -1) {
					ApplicationDialog.modificationMade();
					removeModel(list.getSelection());
					list.remove(select);
				}
			}

		});
		removeModelButton.setText("Remove Model");
		removeModelButton.setBounds(146, 175, 130, 25);

		final Label generationsOptionsLabel_2 = new Label(composite, SWT.NONE);
		generationsOptionsLabel_2.setBounds(10, 8, 240, 24);
		generationsOptionsLabel_2.setFont(SWTResourceManager.getFont("", 12,
				SWT.BOLD));
		generationsOptionsLabel_2.setText("Model Lists");

		config_description = new Text(container, SWT.READ_ONLY | SWT.BORDER
				| SWT.WRAP);
		config_description.setBounds(493, 10, 297, 169);

		configurationList = new Combo(container, SWT.READ_ONLY);
		configurationList.setBounds(128, 10, 191, 23);
		configurationList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				refreshConfiguration();
			}
		});

		// Fill the combo
		for (ModelElement elt : application.getElements()) {
			if (elt instanceof Configuration) {
				Configuration configuration = (Configuration) elt;
				configurationList.add(configuration.getName());
			}
		}

		if (configurationList.getItemCount() > 0) {
			configurationList.select(0);
		} else {
			tabFolder.setEnabled(false);
		}

		final Label listOfConfigurayionsLabel = new Label(container, SWT.NONE);
		listOfConfigurayionsLabel.setBounds(5, 13, 136, 23);
		listOfConfigurayionsLabel.setText("List of configurations :");

		final Button editBt = new Button(container, SWT.NONE);
		editBt.setBounds(325, 10, 40, 20);
		editBt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (configurationList.getItemCount() > 0) {
					if (configurationList.getSelectionIndex() != -1) {
						String name = configurationList
								.getItem(configurationList.getSelectionIndex());
						Configuration config = application
								.getConfiguration(name);
						ConfigurationDialog dialog = new ConfigurationDialog(
								new Shell(), config.getName(), config
										.getDescription());
						if (dialog.open() == Window.OK) {
							config.setName(dialog.getName());
							config.setDescription(dialog.getDescription());

							String[] items = configurationList.getItems();
							int index = configurationList.getSelectionIndex();
							items[index] = dialog.getName();
							configurationList.removeAll();
							configurationList.setItems(items);
							configurationList.select(index);
							config_description.setText(config.getDescription());
							modificationMade();
						}
					}
				}
			}
		});
		editBt.setImage(SWTResourceManager.getImage(ApplicationDialog.class,
				"tree/img/edit.png"));

		final Button addBt = new Button(container, SWT.NONE);
		addBt.setBounds(365, 10, 40, 20);
		addBt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				Configuration config = ApplicationFactory.eINSTANCE
						.createConfiguration();

				int i = 0;
				String newName = "New configuration";
				while (application.getConfiguration(newName) != null) {
					i++;
					newName = "New configuration (" + i + ")";
				}
				config.setName(newName);
				configurationList.add(newName);
				configurationList.select(configurationList.getItemCount() - 1);
				addStaticParameters(config);
				application.getElements().add(config);
				modificationMade();
				tabFolder.setEnabled(true);
				refreshConfiguration();
			}

			private void addStaticParameters(Configuration config) {
				ConfigurationParameters verboseParam = ApplicationFactory.eINSTANCE
						.createConfigurationParameters();
				verboseParam.setKey(KEY_VERBOSE);
				verboseParam.setValue("false");
				config.getParameters().add(verboseParam);

				ConfigurationParameters cleanParam = ApplicationFactory.eINSTANCE
						.createConfigurationParameters();
				cleanParam.setKey(KEY_CLEAN);
				cleanParam.setValue("false");
				config.getParameters().add(cleanParam);

				ConfigurationParameters updateParam = ApplicationFactory.eINSTANCE
						.createConfigurationParameters();
				updateParam.setKey(KEY_UPDATE);
				updateParam.setValue("false");
				config.getParameters().add(updateParam);

				ConfigurationParameters logPathParam = ApplicationFactory.eINSTANCE
						.createConfigurationParameters();
				logPathParam.setKey(KEY_LOGPATH);
				logPathParam.setValue("");
				config.getParameters().add(logPathParam);

				ConfigurationParameters generationPathParam = ApplicationFactory.eINSTANCE
						.createConfigurationParameters();
				generationPathParam.setKey(KEY_GENPATH);
				generationPathParam.setValue("");
				config.getParameters().add(generationPathParam);
			}
		});
		addBt.setImage(SWTResourceManager.getImage(ApplicationDialog.class,
				"tree/img/add.png"));

		final Button deleteBt = new Button(container, SWT.NONE);
		deleteBt.setBounds(400, 10, 40, 20);
		deleteBt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (configurationList.getItemCount() > 0) {
					if (configurationList.getSelectionIndex() != -1) {
						String name = configurationList
								.getItem(configurationList.getSelectionIndex());
						Configuration config = application
								.getConfiguration(name);
						if (config != null) {
							application.getElements().remove(config);
							configurationList.remove(name);
							if (configurationList.getItemCount() > 0)
								configurationList.select(0);
						}
					}
				}
				modificationMade();
				if (configurationList.getItemCount() == 0) {
					tabFolder.setEnabled(false);
				}
				refreshConfiguration();
			}
		});
		deleteBt.setImage(SWTResourceManager.getImage(ApplicationDialog.class,
				"tree/img/delete.png"));

		refreshConfiguration();

		return container;
	}

	private void removeModel(String[] selection) {
		List<String> list = Arrays.asList(selection);
		List<Model> toRemove = new ArrayList<Model>();
		for (ModelElement elem : application.getElements()) {
			if (elem instanceof Model) {
				Model model = (Model) elem;
				if (list.contains(model.getFile())) {
					toRemove.add(model);
				}
			}
		}
		application.getElements().removeAll(toRemove);
	}

	private void refreshDataStructure() {
		if (generatorParametersViewer != null) {
			generatorParameterContentProvider.setDataStructure(dataStructure);
			generatorParameterLabelProvider.setDataStructure(dataStructure);
			generatorParameterCellModifier.setDataStructure(dataStructure);
			generatorParametersViewer.setInput(dataStructure);
		}
	}

	/**
	 * Create the TableViewer
	 */
	private void createTableViewer() {
		generatorParametersViewer = new TableViewer(generatorParameters);
		generatorParametersViewer.setUseHashlookup(true);
		generatorParametersViewer.setColumnProperties(columnNames);

		// Create the cell editors
		CellEditor[] editors = new CellEditor[2];

		// Column 1 : Label (Text, readonly)
		editors[0] = new TextCellEditor(generatorParameters);

		// Column 2 : Value (Text, editable)
		TextCellEditor textEditor = new TextCellEditor(generatorParameters);
		((Text) textEditor.getControl()).setTextLimit(60);
		editors[1] = (CellEditor) textEditor;

		// Assign the cell editors to the viewer
		generatorParametersViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
		generatorParameterContentProvider = new GeneratorParameterContentProvider(
				dataStructure);
		generatorParametersViewer
				.setContentProvider(generatorParameterContentProvider);
		generatorParameterLabelProvider = new GeneratorParameterLabelProvider(
				dataStructure);
		generatorParametersViewer
				.setLabelProvider(generatorParameterLabelProvider);
		generatorParameterCellModifier = new GeneratorParameterCellModifier(
				dataStructure, columnNames, generatorParametersViewer);
		generatorParametersViewer
				.setCellModifier(generatorParameterCellModifier);

		generatorParametersViewer.setInput(dataStructure);
		generatorParametersViewer.refresh();

	}

	/**
	 * Build a html string for documentation on generator parameter
	 * 
	 * @return
	 */
	private String buildHelpDocumentationText(String documentation) {
		String result = "<font face=\"Helvetica, Arial\" size=\"2\">";
		result += documentation;
		result += "</font>";
		return result;
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
				return metamodelSet.values().toArray();
			} else
				return getChildren(object);
		}

		public void initialize() {
			IConfigurationElement[] contributions = Platform
					.getExtensionRegistry().getConfigurationElementsFor(
							EXTENSIONPOINT_ID);

			// Scan for metamodels
			for (IConfigurationElement config : contributions) {
				if (config.getName().equalsIgnoreCase("metamodel")) {
					Metamodel m = new Metamodel(config);

					if (!metamodelSet.containsKey(m.getId())) {
						metamodelSet.put(m.getId(), m);
					} else {
						m = metamodelSet.get(m.getId());
					}
					technologySet.clear();

					// Scan for technology
					for (IConfigurationElement techno : config
							.getChildren("technology")) {
						Technology t = new Technology(techno, m);

						if (!technologySet.containsKey(t.getId())) {
							technologySet.put(t.getId(), t);
						} else {
							t = technologySet.get(t.getId());
						}
						technologyVersionSet.clear();

						// Scan for technology version
						for (IConfigurationElement technoV : techno
								.getChildren("technologyVersion")) {
							TechnologyVersion tv = new TechnologyVersion(
									technoV, t);

							if (!technologyVersionSet.containsKey(tv.getId())) {
								technologyVersionSet.put(tv.getId(), tv);
							} else {
								tv = technologyVersionSet.get(tv.getId());
							}

							generatorSet.clear();
							// Scan for Generator Version
							for (IConfigurationElement gen : technoV
									.getChildren("generatorVersion")) {
								Generator g = new Generator(gen, tv);

								if (!generatorSet.containsKey(g.getId())) {
									generatorSet.put(g.getId(), g);
								} else {
									g = generatorSet.get(g.getId());
								}

								// Scan for option
								for (IConfigurationElement option : gen
										.getChildren("option")) {
									new OptionGenerator(option, g);
								}

								// Scan for configuration parameter
								for (IConfigurationElement confParam : gen
										.getChildren("configurationParameter")) {
									GeneratorParameter param = new GeneratorParameter(
											confParam, g);

									if (!paramConfByGenerator.containsKey(g
											.getId())) {
										paramConfByGenerator.put(g.getId(),
												new ArrayList<String>());
									}
									paramConfByGenerator.get(g.getId()).add(
											param.getKey());
									configurationParameters.put(param.getKey(),
											param);
								}
							}
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
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, GEN_ID, "Generate", false);
		applyButton = createButton(parent, APPLY_ID, "Apply", false);
		createButton(parent, IDialogConstants.CLOSE_ID, "Close", false);
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(800, 600);
	}

	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CLOSE_ID) {
			if (applicationModified) {
				int result = showConfirmation("Configuration Modified.",
						"Configuration modified, do you want to save it before closing?");
				if (result == SWT.YES) {
					saveData();
				}
			}
			close();
		}
		if (buttonId == APPLY_ID) {
			saveData();
		}
		if (buttonId == GEN_ID) {
			if (applicationModified) {
				int result = showConfirmation("Configuration Modified.",
						"Configuration modified, do you want to save it before generating?");
				if (result == SWT.YES) {
					saveData();
				}
			}
			GeneratePopUp generationPopUp = new GeneratePopUp(Display
					.getDefault().getActiveShell(), getCurrentConfiguration(),
					staticFieldsName, ApplicationUtil.getModels(application));
			generationPopUp.open();
			return;
		}

		super.buttonPressed(buttonId);
	}

	protected void saveData() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("application", new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(ApplicationPackage.eNS_URI,
				ApplicationPackage.eINSTANCE);
		org.eclipse.emf.ecore.resource.Resource resource = resourceSet
				.createResource(URI.createURI(model.getRawLocation().toFile()
						.getAbsolutePath()));
		resource.getContents().add(application);

		try {
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(model.getRawLocation().toFile()
							.getAbsolutePath()));
			Map<String, Object> saveOptions = new HashMap<String, Object>();
			resource.save(out, saveOptions);
			out.close();
			model.refreshLocal(-1, null);
			applicationModified = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Configuration");
	}

	static public Configuration getCurrentConfiguration() {
		if (configurationList.getSelectionIndex() != -1) {
			String name = configurationList.getItem(configurationList
					.getSelectionIndex());
			Configuration configuration = application.getConfiguration(name);
			return configuration;
		}
		return null;
	}

	public static void modificationMade() {
		if (!applicationModified) {
			applicationModified = true;
		}
	}

	public static int showConfirmation(String title, String message) {
		int style = 0;
		style |= SWT.YES | SWT.NO;
		MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(),
				style);
		mb.setText(title);
		mb.setMessage(message);
		int val = mb.open();
		return val;
	}

	public static void showAlert(String title, String message) {
		int style = 0;
		style |= SWT.OK;
		MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(),
				style);
		mb.setText(title);
		mb.setMessage(message);
	}
}
