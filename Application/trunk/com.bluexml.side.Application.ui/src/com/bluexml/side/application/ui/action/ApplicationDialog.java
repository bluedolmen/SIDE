package com.bluexml.side.application.ui.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.internal.resources.Container;
import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
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
import org.eclipse.jface.viewers.TreeSelection;
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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.ComponantConfiguration;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.security.Checkable;
import com.bluexml.side.application.ui.SWTResourceManager;
import com.bluexml.side.application.ui.action.table.GeneratorParameter;
import com.bluexml.side.application.ui.action.table.GeneratorParameterCellModifier;
import com.bluexml.side.application.ui.action.table.GeneratorParameterContentProvider;
import com.bluexml.side.application.ui.action.table.GeneratorParameterDataStructure;
import com.bluexml.side.application.ui.action.table.GeneratorParameterLabelProvider;
import com.bluexml.side.application.ui.action.tree.Deployer;
import com.bluexml.side.application.ui.action.tree.Generator;
import com.bluexml.side.application.ui.action.tree.ImplNode;
import com.bluexml.side.application.ui.action.tree.Metamodel;
import com.bluexml.side.application.ui.action.tree.OptionComponant;
import com.bluexml.side.application.ui.action.tree.OptionDeployer;
import com.bluexml.side.application.ui.action.tree.OptionGenerator;
import com.bluexml.side.application.ui.action.tree.Technology;
import com.bluexml.side.application.ui.action.tree.TechnologyVersion;
import com.bluexml.side.application.ui.action.tree.TreeElement;
import com.bluexml.side.application.ui.action.tree.TreeNode;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.validator.FolderSelectionValidator;
import com.bluexml.side.application.ui.action.utils.viewFilter.SideFileFiter;

@SuppressWarnings("restriction")
public class ApplicationDialog extends Dialog {

	private Group optionsGroup;
	private static final int APPLY_ID = IDialogConstants.CLIENT_ID + 2;
	private static final int GEN_ID = IDialogConstants.CLIENT_ID + 1;
	private Text config_description;
	private Label errorMsg;
	private TreeViewer genOptionsTree;
	private Tree tree_1;
	private static Combo configurationList;
	public static boolean loadingTree = false;
	public static boolean applicationModified = false;

	private Map<String, GeneratorParameter> configurationParameters;
	private Map<String, GeneratorParameter> deployerParameters;
	private Map<String, List<String>> genParamConfByGenerator;
	private Map<String, List<String>> deployParamConfByGenerator;
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
	private Button skipValidationButton;
	private Text destinationText;
	private Text logText;
	private org.eclipse.swt.widgets.List list;
	private GeneratorParameterContentProvider generatorParameterContentProvider;
	private GeneratorParameterLabelProvider generatorParameterLabelProvider;
	private GeneratorParameterCellModifier generatorParameterCellModifier;
	private TabFolder tabFolder;
	private TabItem generationConfigurationTabItem;
	private TreeViewer deployOptionsTree;
	private TabItem deployementTabItem;
	private Table modelPropertiesTable;

	public static String KEY_VERBOSE = "generation.options.verbose";
	public static String KEY_SKIPVALIDATION = "generation.option.Skip.Validation";
	public static String KEY_LOGPATH = "generation.options.logPath";
	public static String KEY_GENPATH = "generation.options.destinationPath";

	public static List<String> staticFieldsName = Arrays.asList(KEY_GENPATH,
			KEY_LOGPATH, KEY_SKIPVALIDATION, KEY_VERBOSE);

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

		configurationParameters = new HashMap<String, GeneratorParameter>();
		deployerParameters = new HashMap<String, GeneratorParameter>();
		genParamConfByGenerator = new HashMap<String, List<String>>();
		deployParamConfByGenerator = new HashMap<String, List<String>>();
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

			Set<TreeItem> generators = collectImplNode(genOptionsTree);
			configureTree(configuration, generators, genOptionsTree);

			Set<TreeItem> deployers = collectImplNode(deployOptionsTree);
			configureTree(configuration, deployers, deployOptionsTree);

			// Refresh static generator parameters
			initializeStaticParameters();
			// Refresh dynamic generator parameters
			refreshOptions(configuration);
		}
		loadingTree = false;
	}

	private void refreshModelPropertiesTable() {
		if (list.getSelection().length == 1) {
			modelPropertiesTable.setVisible(true);
			int[] selections = list.getSelectionIndices();
			if (selections.length == 1) {
					String modelPath = list.getItem(selections[0]);
					Model m = getModelByFilePath(modelPath);
					modelPropertiesTable.removeAll();
					EPackage metaModel = null;
					try {
						metaModel = ApplicationUtil.getMetaModelForModel(m);
					} catch (IOException e) {
						e.printStackTrace();
					}
					IFile file = null;
					try {
						file = ApplicationUtil.getIFileForModel(m);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if (m != null) {
						TableItem item = new TableItem (modelPropertiesTable, SWT.NONE);
						// Name
						item.setText (0, "Name");
						item.setText (1, m.getName());
					}
					if (metaModel != null) {
						TableItem item = new TableItem (modelPropertiesTable, SWT.NONE);
						// Metamodel
						item.setText (0, "Metamodel");
						item.setText (1, metaModel.getNsURI());
					}
					if (file != null) {
						TableItem item = new TableItem (modelPropertiesTable, SWT.NONE);
						// Charset
						
						try {
							item.setText (1, file.getCharset());
							item.setText (0, "Charset");
						} catch (CoreException e) {
							e.printStackTrace();
						}
						
						TableItem item2 = new TableItem (modelPropertiesTable, SWT.NONE);
						// Modification date
						IPath path = file.getLocation();
						if (path != null) {
							File ioFile = path.toFile();
							if (ioFile != null) {
								Date date = new Timestamp(ioFile.lastModified());
								Locale locale = Locale.getDefault();
								DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
								item2.setText (0, "Last Modification");
								item2.setText (1, dateFormat.format(date));
							}
						}
						
					}
			}
		} else {
			modelPropertiesTable.setVisible(false);
		}
	}
	
	/**
	 * Refresh option for the current configuration
	 */
	public void refreshOptions() {
		Configuration configuration = getCurrentConfiguration();
		refreshOptions(configuration);
	}

	/**
	 * Refresh option for the given configuration
	 * 
	 * @param configuration
	 */
	public void refreshOptions(Configuration configuration) {
		if (tabFolder.getSelection() != null
				&& tabFolder.getSelection()[0]
						.equals(generationConfigurationTabItem)) {
			initializeDynamicGenerationParameters();
		} else {
			initializeDynamicDeployementParameters();
		}
		configureGeneratorOptions(configuration);
	}

	public void refreshImplNodeOptions() {
		String name = configurationList.getItem(configurationList
				.getSelectionIndex());
		Configuration configuration = application.getConfiguration(name);
		refreshOptions(configuration);
	}

	private void initializeStaticParameters() {
		ConfigurationParameters verboseParam = ApplicationUtil
				.getConfigurationParmeterByKey(KEY_VERBOSE);
		if (verboseParam != null) {
			verboseButton.setSelection(Boolean.parseBoolean(verboseParam
					.getValue()));
		}

		ConfigurationParameters skipValidationParam = ApplicationUtil
				.getConfigurationParmeterByKey(KEY_SKIPVALIDATION);
		if (skipValidationParam != null) {
			skipValidationButton.setSelection(Boolean
					.parseBoolean(skipValidationParam.getValue()));
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

	/**
	 * Initialize the table with options for generation
	 */
	private void initializeDynamicGenerationParameters() {
		// List Generator Id used
		Generator gen = getSelectedGenerator();
		List<String> confIds = new ArrayList<String>();
		if (gen == null) {
			Configuration conf = getCurrentConfiguration();
			for (ComponantConfiguration elem : ApplicationUtil
					.getComponantConfigurations(conf)) {
				confIds.add(elem.getId());
			}
			optionsGroup.setText("Options for Generation");
		} else {
			confIds.add(gen.getId());
			optionsGroup.setText("Options for " + gen.getVersion());
		}
		if (dataStructure != null) {
			dataStructure.getData().clear();
		}
		HashMap<String, GeneratorParameter> neededParam = new HashMap<String, GeneratorParameter>();
		dataStructure = new GeneratorParameterDataStructure();
		// We get all params key needed by Generator
		for (String genId : confIds) {
			List<String> paramList = genParamConfByGenerator.get(genId);
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

	/**
	 * Initialize the table with options for deployement
	 */
	public void initializeDynamicDeployementParameters() {
		// List deployer Id used
		Deployer dep = getSelectedDeployer();
		List<String> confIds = new ArrayList<String>();
		if (dep == null) {
			Configuration conf = getCurrentConfiguration();
			for (ComponantConfiguration elem : ApplicationUtil
					.getComponantConfigurations(conf)) {
				confIds.add(elem.getId());
			}
			optionsGroup.setText("Options for Deployement");
		} else {
			confIds.add(dep.getId());
			optionsGroup.setText("Options for " + dep.getVersion());
		}
		if (dataStructure != null) {
			dataStructure.getData().clear();
		}
		HashMap<String, GeneratorParameter> neededParam = new HashMap<String, GeneratorParameter>();
		dataStructure = new GeneratorParameterDataStructure();
		// We get all params key needed by Deployer
		for (String genId : confIds) {
			List<String> paramList = deployParamConfByGenerator.get(genId);
			if (paramList != null) {
				// We construct one list without twice the same id
				for (String paramId : paramList) {
					if (!neededParam.containsKey(paramId)) {
						neededParam.put(paramId, deployerParameters.get(paramId));
					}
				}
			}
		}
		for (GeneratorParameter genParam : neededParam.values()) {
			if (genParam != null) {
				genParam.setValue("");
				dataStructure.addGeneratorParameter(genParam);
			}

		}
	}

	/**
	 * Return all ImplNode (Generator, Deployer) for the given tree
	 * 
	 * @param tv
	 * @return
	 */
	private Set<TreeItem> collectImplNode(TreeViewer tv) {
		Set<TreeItem> generators = new HashSet<TreeItem>();
		collectImplNode(tv.getTree().getItems(), generators);
		return generators;
	}

	private void collectImplNode(TreeItem[] items, Set<TreeItem> generators) {
		for (TreeItem item : items) {
			if (item.getData() instanceof ImplNode)
				generators.add(item);
			collectImplNode(item.getItems(), generators);
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

	private Generator getSelectedGenerator() {
		TreeSelection ts = (TreeSelection) genOptionsTree.getSelection();
		Object o = ts.getFirstElement();
		if (o instanceof TreeNode) {
			return getSelectedGenerator((TreeNode) o);
		} else {
			return null;
		}
	}

	/**
	 * Return the selected generator, or null if no generator top to the
	 * selected element or non selected generator.
	 * 
	 * @param o
	 * @return
	 */
	private Generator getSelectedGenerator(TreeNode o) {
		if (o instanceof Generator) {
			if (o.isChecked()) {
				return (Generator) o;
			} else {
				return null;
			}
		} else {
			if (o != null) {
				return getSelectedGenerator(o.getParent());
			} else {
				return null;
			}
		}
	}

	private Deployer getSelectedDeployer() {
		TreeSelection ts = (TreeSelection) deployOptionsTree.getSelection();
		Object o = ts.getFirstElement();
		if (o instanceof TreeNode) {
			return getSelectedDeployer((TreeNode) o);
		} else {
			return null;
		}
	}
	
	/**
	 * Return the model for the given FilePath
	 * @param text
	 * @return 
	 */
	protected Model getModelByFilePath(String filePath) {
		if (filePath != null) {
			for (ModelElement me : application.getElements()) {
				if (me instanceof Model) {
					Model m = (Model) me;
					if (filePath.equals(m.getFile())) {
						return m;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Return the selected generator, or null if no generator top to the
	 * selected element or non selected generator.
	 * 
	 * @param o
	 * @return
	 */
	private Deployer getSelectedDeployer(TreeNode o) {
		if (o instanceof Deployer) {
			if (o.isChecked()) {
				return (Deployer) o;
			} else {
				return null;
			}
		} else {
			if (o != null) {
				return getSelectedDeployer(o.getParent());
			} else {
				return null;
			}
		}
	}

	/**
	 * Load data in given tree for the given configuration
	 * 
	 * @param configuration
	 * @param generators
	 */
	private void configureTree(Configuration configuration,
			Set<TreeItem> generators, TreeViewer tv) {
		for (TreeItem item : generators) {

			ImplNode g = (ImplNode) item.getData();
			for (ComponantConfiguration ce : ApplicationUtil
					.getComponantConfigurations(configuration)) {
				if (ce.getId().equals(g.getId())) {
					g.setChecked(true);
					g.setEnabled(true);
					tv.update(g, null);

					for (TreeNode tn : g.getChildren()) {
						OptionComponant o = (OptionComponant) tn;
						for (Option opt : ce.getOptions()) {
							o.setEnabled(true);
							if (opt.getKey().equals(o.getKey())) {
								o.setChecked(true);
							}
						}
					}

					for (TreeNode tn : g.getChildren()) {
						tn.setEnabled(true);
					}

					// Refresh options
					for (TreeItem option : item.getItems()) {
						tv.update(option.getData(), null);
					}

					refreshParents(item, tv);
				}
			}
		}
	}

	private void refreshParents(TreeItem item, TreeViewer tv) {
		TreeItem parent = item.getParentItem();
		if (parent != null) {
			// Check and enable parent
			TreeElement el = (TreeElement) parent.getData();
			el.setChecked(true);
			el.setEnabled(true);
			tv.update(parent.getData(), null);

			// Enable all sibling nodes
			for (TreeItem sibItem : parent.getItems()) {
				TreeElement sibEl = (TreeElement) sibItem.getData();
				sibEl.setEnabled(true);
				tv.update(sibItem.getData(), null);
			}

			refreshParents(parent, tv);
		}
	}

	private void initializeTree() {
		TreeItem[] genItems = genOptionsTree.getTree().getItems();
		initializeTree(genItems, genOptionsTree, 0);
		TreeItem[] deployItems = deployOptionsTree.getTree().getItems();
		initializeTree(deployItems, deployOptionsTree, 0);
	}

	private void initializeTree(TreeItem[] items, TreeViewer tv, int level) {
		for (TreeItem item : items) {
			TreeElement el = (TreeElement) item.getData();
			el.setChecked(false);
			el.setEnabled(false);
			// if (item.getData() instanceof Metamodel)
			if (level == 0) {
				el.setEnabled(true);
			}
			tv.update(item.getData(), null);
			initializeTree(item.getItems(), tv, level + 1);
		}
	}

	/**
	 * Check if the element given is active in the key
	 * 
	 * @param el
	 *            : the element
	 * @return true if valid, false if not
	 */
	@SuppressWarnings("unchecked")
	private Boolean checkElementValidity(TreeElement el) {
		// If the element is a component and not valid we don't enable it
		try {
			ImplNode iN = ((ImplNode) el);
			Class<Checkable> gen;
			if (Platform.getBundle(iN.getId()) != null) {
				gen = Platform.getBundle(iN.getId()).loadClass(iN.getLaunchClass());
				Checkable gener = gen.newInstance();
				return gener.check();
			} else {
				throw new Exception("Error : " + iN.getId() + " isn't found as a plugin. Check your extension file.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		ets.setHelpAvailable(false);
		ets.addFilter(new SideFileFiter());

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

		tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(15, 39, 472, 484);

		final TabItem modelsTabItem = new TabItem(tabFolder, SWT.NONE);
		modelsTabItem.setText("Models");

		final Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		modelsTabItem.setControl(composite_3);

		final Button addModelButton = new Button(composite_3, SWT.NONE);
		addModelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				SelectModelFileDialog();
			}
		});
		addModelButton.setText("Add Model");
		addModelButton.setBounds(10, 175, 130, 25);

		list = new org.eclipse.swt.widgets.List(composite_3, SWT.BORDER);
		list.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				refreshModelPropertiesTable();
			}
		});
		list.setBounds(10, 38, 444, 115);

		final Button removeModelButton = new Button(composite_3, SWT.NONE);
		removeModelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int select = list.getSelectionIndex();
				if (select != -1) {
					ApplicationDialog.modificationMade();
					removeModel(list.getSelection());
					list.remove(select);
				}
				modelPropertiesTable.setVisible(false);
			}

		});
		removeModelButton.setText("Remove Model");
		removeModelButton.setBounds(146, 175, 130, 25);

		final Label generationsOptionsLabel_2 = new Label(composite_3, SWT.NONE);
		generationsOptionsLabel_2.setBounds(10, 8, 240, 24);
		generationsOptionsLabel_2.setFont(SWTResourceManager.getFont("", 12,
				SWT.BOLD));
		generationsOptionsLabel_2.setText("Model Lists");

		modelPropertiesTable = new Table(composite_3, SWT.BORDER);
		modelPropertiesTable.setLinesVisible(true);
		modelPropertiesTable.setHeaderVisible(false);
		modelPropertiesTable.setBounds(10, 226, 444, 115);
		modelPropertiesTable.setVisible(false);

		final TableColumn newColumnTableColumn_2 = new TableColumn(modelPropertiesTable, SWT.NONE);
		newColumnTableColumn_2.setWidth(127);
		newColumnTableColumn_2.setText("Propertie");

		final TableColumn newColumnTableColumn_3 = new TableColumn(modelPropertiesTable, SWT.NONE);
		newColumnTableColumn_3.setWidth(294);
		newColumnTableColumn_3.setText("Value");

		generationConfigurationTabItem = new TabItem(tabFolder, SWT.NONE);
		generationConfigurationTabItem.setText("Generation");

		final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				if (!tabFolder.getEnabled()) {
					showAlert("No Configuration",
							"You must create a configuration to edit it.");
				}
			}
		});
		generationConfigurationTabItem.setControl(composite_1);

		genOptionsTree = new TreeViewer(composite_1, SWT.BORDER);
		tree_1 = genOptionsTree.getTree();
		tree_1.setBounds(0, 142, 459, 304);
		List<Class<?>> omitedClassForGen = new ArrayList<Class<?>>();
		omitedClassForGen.add(Deployer.class);
		genOptionsTree.setContentProvider(new ConfigurationContentProvider(
				Metamodel.class, omitedClassForGen));
		genOptionsTree.setLabelProvider(new ConfigurationLabelProvider());
		genOptionsTree.setInput(this);
		genOptionsTree.expandAll();

		Listener checkOnClick = new GenerationOptionTreeListener();
		genOptionsTree.getTree().addListener(SWT.MouseDown, checkOnClick);

		logText = new Text(composite_1, SWT.BORDER);
		logText.addFocusListener(new FocusAdapter() {
			public void focusLost(final FocusEvent e) {
				ConfigurationParameters param = ApplicationUtil
						.getConfigurationParmeterByKey(KEY_LOGPATH);
				if (param != null) {
					Text t = (Text) e.getSource();
					param.setValue(t.getText());
					ApplicationDialog.modificationMade();
				}
			}
		});

		logText.setBounds(115, 7, 260, 30);

		final Label logLabel = new Label(composite_1, SWT.NONE);
		logLabel.setAlignment(SWT.RIGHT);
		logLabel.setText("Log Path :");
		logLabel.setBounds(20, 10, 93, 15);

		final Label destinationLabel = new Label(composite_1, SWT.NONE);
		destinationLabel.setAlignment(SWT.RIGHT);
		destinationLabel.setBounds(20, 54, 93, 15);
		destinationLabel.setText("Generation path :");

		destinationText = new Text(composite_1, SWT.BORDER);
		destinationText.addFocusListener(new FocusAdapter() {
			public void focusLost(final FocusEvent e) {
				ConfigurationParameters param = ApplicationUtil
						.getConfigurationParmeterByKey(KEY_GENPATH);
				if (param != null) {
					Text t = (Text) e.getSource();
					param.setValue(t.getText());
					ApplicationDialog.modificationMade();
				}

			}
		});
		destinationText.setBounds(115, 51, 260, 30);

		final Button browseLogPathButton = new Button(composite_1, SWT.NONE);
		browseLogPathButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				String filePath = "";
				Container folder = displaySelectFolderInWorkspace("Select Log Path");

				if (folder != null) {
					filePath = folder.getFullPath().toPortableString();
					logText.setText(filePath);

					ConfigurationParameters param = ApplicationUtil
							.getConfigurationParmeterByKey(KEY_LOGPATH);
					if (param != null) {
						param.setValue(filePath);
						modificationMade();
					}
				}
			}

		});
		browseLogPathButton.setText("Browse");
		browseLogPathButton.setBounds(381, 12, 75, 25);

		final Button browseGenPathButton = new Button(composite_1, SWT.NONE);
		browseGenPathButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				String filePath = "";
				Container folder = displaySelectFolderInWorkspace("Select Generation Path");

				if (folder != null) {
					filePath = folder.getFullPath().toPortableString();
					destinationText.setText(filePath);

					ConfigurationParameters param = ApplicationUtil
							.getConfigurationParmeterByKey(KEY_GENPATH);
					if (param != null) {
						param.setValue(filePath);
						modificationMade();
					}
				}
			}
		});
		browseGenPathButton.setText("Browse");
		browseGenPathButton.setBounds(381, 54, 75, 25);

		final Label chooseYourGenerationLabel = new Label(composite_1, SWT.NONE);
		chooseYourGenerationLabel.setText("Choose your generation options :");
		chooseYourGenerationLabel.setBounds(0, 95, 456, 15);

		verboseButton = new Button(composite_1, SWT.CHECK);
		verboseButton.setToolTipText("Will print out generation information.");
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
		verboseButton.setBounds(10, 116, 108, 20);

		skipValidationButton = new Button(composite_1, SWT.CHECK);
		skipValidationButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ConfigurationParameters param = ApplicationUtil
						.getConfigurationParmeterByKey(KEY_SKIPVALIDATION);
				if (param != null) {
					Button b = (Button) e.getSource();
					param.setValue(Boolean.toString(b.getSelection()));
				}
				ApplicationDialog.modificationMade();
			}
		});
		skipValidationButton
				.setToolTipText("If checked will skip the validation task.");
		skipValidationButton.getAccessible().addAccessibleListener(
				new AccessibleAdapter() {
					public void getHelp(AccessibleEvent e) {
						e.result = "If checked will move uploaded files to your application.";
					}
				});
		skipValidationButton.setText("Skip Validation");
		skipValidationButton.setBounds(160, 116, 108, 20);

		deployementTabItem = new TabItem(tabFolder, SWT.NONE);
		deployementTabItem.setText("Deployement");

		tabFolder.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(final SelectionEvent e) {
				if (tabFolder.getSelection().length > 0){
						if (tabFolder.getSelection()[0]
								.equals(generationConfigurationTabItem) || tabFolder
								.getSelection()[0].equals(deployementTabItem)) {
						optionsGroup.setVisible(true);
						refreshOptions();
					} else {
						optionsGroup.setVisible(false);
						refreshModelPropertiesTable();
					}
				}
				documentationText.setText("");
			}
		});

		final Composite composite = new Composite(tabFolder, SWT.NONE);
		deployementTabItem.setControl(composite);

		deployOptionsTree = new TreeViewer(composite, SWT.BORDER);
		Tree tree_2 = deployOptionsTree.getTree();
		tree_2.setBounds(0, 36, 459, 410);
		Listener deployementListener = new DeployementOptionTreeListener();
		tree_2.addListener(SWT.MouseDown, deployementListener);

		List<Class<?>> omitedClassForDeploy = new ArrayList<Class<?>>();
		omitedClassForDeploy.add(Generator.class);
		deployOptionsTree.setContentProvider(new ConfigurationContentProvider(
				Technology.class, omitedClassForDeploy));
		deployOptionsTree.setLabelProvider(new ConfigurationLabelProvider());
		deployOptionsTree.setInput(this);
		deployOptionsTree.expandAll();

		final Label chooseYourGenerationLabel_1 = new Label(composite, SWT.NONE);
		chooseYourGenerationLabel_1.setBounds(10, 15, 456, 15);
		chooseYourGenerationLabel_1
				.setText("Choose your deployement options :");

		for (ModelElement elem : application.getElements()) {
			if (elem instanceof Model) {
				Model model = (Model) elem;
				list.add(model.getFile());
			}
		}
		// Component thaht shows the description in the top right of the scree
		config_description = new Text(container, SWT.READ_ONLY | SWT.BORDER
				| SWT.WRAP);
		config_description.setBounds(493, 10, 297, 51);

		// Show a warning if the component is not valid
		errorMsg = new Label(container, SWT.BOLD);
		errorMsg.setBounds(493, 67, 297, 15);
		errorMsg.setForeground(new Color(container.getDisplay(), 255,
				0, 0));

		// Browser that shows informations on the selected component (right)
		documentationText = new Browser(container, SWT.BORDER);
		documentationText.setBounds(493, 88, 297, 197);
		documentationText.setText(buildHelpDocumentationText(""));

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
			errorMsg.setText("You must create and select a configuration to Edit.");
		}

		final Label listOfConfigurayionsLabel = new Label(container, SWT.NONE);
		listOfConfigurayionsLabel.setBounds(5, 13, 136, 23);
		listOfConfigurayionsLabel.setText("List of configurations :");

		final Button editBt = new Button(container, SWT.NONE);
		editBt.setBounds(325, 10, 48, 26);
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
		addBt.setBounds(375, 10, 48, 26);
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
				errorMsg.setText("");
				refreshConfiguration();
			}

			private void addStaticParameters(Configuration config) {
				ConfigurationParameters verboseParam = ApplicationFactory.eINSTANCE
						.createConfigurationParameters();
				verboseParam.setKey(KEY_VERBOSE);
				verboseParam.setValue("false");
				config.getParameters().add(verboseParam);

				ConfigurationParameters updateParam = ApplicationFactory.eINSTANCE
						.createConfigurationParameters();
				updateParam.setKey(KEY_SKIPVALIDATION);
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
		deleteBt.setBounds(425, 10, 48, 26);
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
					errorMsg.setText("You must create and select a configuration to Edit.");
				}
				refreshConfiguration();
			}
		});
		deleteBt.setImage(SWTResourceManager.getImage(ApplicationDialog.class,
				"tree/img/delete.png"));

		optionsGroup = new Group(container, SWT.NONE);
		optionsGroup.setText("Options");
		optionsGroup.setBounds(490, 291, 300, 222);
		optionsGroup.setVisible(false);

		generatorParameters = new Table(optionsGroup, SWT.BORDER);
		generatorParameters.setBounds(0, 23, 295, 210);
		generatorParameters.setTopIndex(3);
		generatorParameters.getHorizontalBar().setVisible(false);
		generatorParameters.getHorizontalBar().setEnabled(false);
		generatorParameters.setLinesVisible(true);
		generatorParameters.setHeaderVisible(true);
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
		newColumnTableColumn.setWidth(109);
		newColumnTableColumn.setText(columnNames[0]);
		
		final TableColumn newColumnTableColumn_1 = new TableColumn(
				generatorParameters, SWT.LEFT);
		newColumnTableColumn_1.setWidth(315);
		newColumnTableColumn_1.setText(columnNames[1]);
		
		final Label generationsOptionsLabel_1 = new Label(optionsGroup,
				SWT.NONE);
		generationsOptionsLabel_1.setFont(SWTResourceManager.getFont("", 11,
				SWT.NONE));
		generationsOptionsLabel_1
				.setText("Specific configuration generation options");

		refreshConfiguration();

		return container;
	}

	/**
	 * Display the select folder box (only in workspace)
	 * 
	 * @param message
	 * @return
	 */
	private Container displaySelectFolderInWorkspace(String message) {
		Container result = null;
		ElementTreeSelectionDialog ets = new ElementTreeSelectionDialog(Display
				.getDefault().getActiveShell(), new WorkbenchLabelProvider(),
				new BaseWorkbenchContentProvider());
		ets.setBlockOnOpen(true);
		ets.setValidator((ISelectionStatusValidator) new FolderSelectionValidator());
		ets.setAllowMultiple(true);
		
		ets.setTitle("Select Folder");
		ets.setMessage(message);
		ets.setInput(ResourcesPlugin.getWorkspace().getRoot());
		ets.setHelpAvailable(false);

		if (ElementTreeSelectionDialog.OK == ets.open()) {
			Object[] choice = ets.getResult();
			for (Object o : choice) {
				if (o instanceof Folder) {
					result = (Folder) o;
				} else if (o instanceof Project) {
					result = (Project) o;
				}
			}
		}
		return result;
	}

	/**
	 * Remove the selected model(s)
	 * 
	 * @param selection
	 */
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

	/**
	 * Will refresh in option table the dataStructure (don't refresh the
	 * datastructure itself)
	 */
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
		String result = "<html><body style=\"font-family: Verdana; "
				+ "color: #444;" + "text-decoration: none;"
				+ "word-spacing: normal;" + "text-align: justify;"
				+ "letter-spacing: 0;" + "line-height: 1.2em;"
				+ "font-size: 11px;\">";
		result += documentation;
		result += "</body></html>";
		return result;
	}

	/**
	 * Build the documentation text
	 * 
	 * @return
	 */
	private String builDocumentationText() {
		String result = "<html><body style=\"font-family: Verdana; "
				+ "color: #444;" + "text-decoration: none;"
				+ "word-spacing: normal;" + "text-align: justify;"
				+ "letter-spacing: 0;" + "line-height: 1.2em;"
				+ "font-size: 11px;\">";
		TreeItem[] items = genOptionsTree.getTree().getSelection();
		if (items.length > 0) {
			TreeItem item = items[0];

			if (item.getData() instanceof TreeElement) {
				TreeElement treeElem = (TreeElement) item.getData();
				if (treeElem.getDescription() != null) {
					result += treeElem.getDescription();
				}

				if (treeElem instanceof Metamodel) {
					Metamodel m = (Metamodel) treeElem;
					result += "<br/>";
					result += "Link :";
					result += "<a href=\"" + m.getURL() + "\">" + m.getURL()
							+ "</a>";
				}
			}
		}
		result += "</body></html>";
		return result;
	}

	/**
	 * Create contents of the button bar
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, GEN_ID, "Launch", false);
		createButton(parent, APPLY_ID, "Save", false);
		createButton(parent, IDialogConstants.CLOSE_ID, "Close", false);
	}

	/**
	 * Action for Close, Save and Launch
	 */
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
			Display.getCurrent().getActiveShell().setCursor(
					new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT));
			saveData();
			Display.getCurrent().getActiveShell().setCursor(
					new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));
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
					.getDefault().getActiveShell(), getCurrentConfiguration());
			generationPopUp.open();
			return;
		}

		super.buttonPressed(buttonId);
	}

	/**
	 * Save data in XML file
	 */
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


	/**
	 * Return the configuration equals to the given name.
	 * 
	 * @param p_name
	 * @return
	 */
	static public Configuration getConfigurationByName(String p_name) {
		return application.getConfiguration(p_name);
	}

	/**
	 * Return the current configuration name
	 * 
	 * @return
	 */
	static public String getCurrentConfiguratioName() {
		String confName = null;
		if (configurationList.getSelectionIndex() != -1) {
			confName = configurationList.getItem(configurationList
					.getSelectionIndex());
		}
		return confName;
	}

	/**
	 * Return the configuration being edited
	 * 
	 * @return
	 */
	static public Configuration getCurrentConfiguration() {
		if (configurationList.getSelectionIndex() != -1) {
			String name = configurationList.getItem(configurationList
					.getSelectionIndex());
			Configuration configuration = application.getConfiguration(name);
			return configuration;
		}
		return null;
	}

	/**
	 * To call when a data is changed, will prompt the
	 * "Do you want to save changes" message at the close or launch action.
	 */
	public static void modificationMade() {
		if (!applicationModified) {
			applicationModified = true;
		}
	}

	/**
	 * Show a confirmation message
	 * 
	 * @param title
	 * @param message
	 * @return
	 */
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

	/**
	 * Show an alert message
	 * 
	 * @param title
	 * @param message
	 */
	public static void showAlert(String title, String message) {
		int style = 0;
		style |= SWT.OK;
		MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(),
				style);
		mb.setText(title);
		mb.setMessage(message);
	}

	/**
	 ******************** INTERNAL CLASS ********************************
	 */

	/**
	 * Content provider for Tree
	 */
	class ConfigurationContentProvider implements ITreeContentProvider {

		private Map<?, ?> rootSet;
		private Map<String, Metamodel> metamodelSet = new HashMap<String, Metamodel>();
		private Map<String, Technology> technologySet = new HashMap<String, Technology>();
		private Map<String, TechnologyVersion> technologyVersionSet = new HashMap<String, TechnologyVersion>();
		private Map<String, Generator> generatorSet = new HashMap<String, Generator>();
		private Map<String, Deployer> deployerSet = new HashMap<String, Deployer>();
		private Map<String, OptionGenerator> optGeneratorSet = new HashMap<String, OptionGenerator>();
		private Map<String, OptionDeployer> optDeployerSet = new HashMap<String, OptionDeployer>();
		private Map<Class<?>, Map<?, ?>> classByLevel = new HashMap<Class<?>, Map<?, ?>>();
		private Class<?> neededRootClass;
		List<?> omitedObject;

		public ConfigurationContentProvider(Class<?> p_neededRootClass,
				List<?> p_ommitedObject) {
			neededRootClass = p_neededRootClass;
			if (p_ommitedObject != null) {
				omitedObject = p_ommitedObject;
			} else {
				omitedObject = new ArrayList<Class<?>>();
			}
			initializeClassByLevel();
			if (classByLevel.containsKey(neededRootClass)) {
				rootSet = classByLevel.get(neededRootClass);
			} else {
				rootSet = metamodelSet;
			}
		}

		/**
		 * Initialize the map with Class --> Set corresponding
		 */
		private void initializeClassByLevel() {
			classByLevel.put(Metamodel.class, metamodelSet);
			classByLevel.put(Technology.class, technologySet);
			classByLevel.put(TechnologyVersion.class, technologyVersionSet);
			classByLevel.put(Generator.class, generatorSet);
			classByLevel.put(Deployer.class, deployerSet);
			classByLevel.put(OptionGenerator.class, optGeneratorSet);
			classByLevel.put(OptionDeployer.class, optDeployerSet);
		}

		public Object[] getChildren(Object object) {
			if (object instanceof TreeNode) {
				TreeNode elt = (TreeNode) object;
				return elt.getChildren().toArray();
			}
			return null;
		}

		public Object getParent(Object object) {
			if (object instanceof TreeNode) {
				return ((TreeNode) object).getParent();
			}
			return null;
		}

		public boolean hasChildren(Object arg0) {
			// Get the children
			Object[] obj = getChildren(arg0);

			// Return whether the parent has children
			return obj == null ? false : obj.length > 0;
		}

		/**
		 * Return the elements corresponding (root nodes or childrens)
		 */
		public Object[] getElements(Object object) {
			if (object instanceof ApplicationDialog) {
				initialize();
				for (Object o : rootSet.values()) {
					if (o instanceof TreeNode) {
						((TreeNode) o).setEnabled(true);
					}
				}
				return rootSet.values().toArray();
			} else
				return getChildren(object);
		}

		/**
		 * Read all extension point and construct the tree
		 */
		public void initialize() {
			IConfigurationElement[] contributions = Platform
					.getExtensionRegistry().getConfigurationElementsFor(
							EXTENSIONPOINT_ID);

			for (IConfigurationElement config : contributions) {
				manageConfiguration(config, null);
			}
			initializeFromKey();
		}

		/**
		 * For each element of the extension will manage it and create the
		 * corresponding object
		 * 
		 * @param config
		 * @param parent
		 */
		private void manageConfiguration(IConfigurationElement config,
				TreeNode parent) {
			TreeNode futurParent = null;
			// Scan for metamodels
			if (config.getName().equalsIgnoreCase("metamodel")) { 
				// We create the metal for this config element
				Metamodel m = new Metamodel(config);
				// We check if we already have this metamodel in your set
				if (!metamodelSet.containsKey(m.getId())) {
					metamodelSet.put(m.getId(), m);
				} else {
					m = metamodelSet.get(m.getId());
				}
				futurParent = m;
			}

			// Scan for technology
			if (!omitedObject.contains(Technology.class)
					&& config.getName().equalsIgnoreCase("technology")) {
				Technology t = new Technology(config, (Metamodel) parent);
				if (!technologySet.containsKey(t.getId())
						|| (rootSet != technologySet && parent != technologySet
								.get(t.getId()).getParent())) {
					technologySet.put(t.getId(), t);
				} else {
					t = technologySet.get(t.getId());
				}
				futurParent = t;
			}

			// Scan for technology version
			if (!omitedObject.contains(TechnologyVersion.class)
					&& config.getName().equalsIgnoreCase("technologyVersion")) {
				TechnologyVersion tv = new TechnologyVersion(config,
						(Technology) parent);
				if (!technologyVersionSet.containsKey(tv.getId())
						|| (rootSet != technologyVersionSet && parent != technologyVersionSet
								.get(tv.getId()).getParent())) {
					technologyVersionSet.put(tv.getId(), tv);
				} else {
					tv = technologyVersionSet.get(tv.getId());
				}
				futurParent = tv;
			}

			// Scan for Generator Version
			if (!omitedObject.contains(Generator.class)
					&& config.getName().equalsIgnoreCase("generatorVersion")) {
				Generator gv = new Generator(config, (TechnologyVersion) parent);
				if (!generatorSet.containsKey(gv.getId())
						|| (rootSet != technologyVersionSet && parent != generatorSet
								.get(gv.getId()).getParent())) {
					generatorSet.put(gv.getId(), gv);
				} else {
					gv = generatorSet.get(gv.getId());
				}
				futurParent = gv;
			}

			// Scan for deployer version
			if (!omitedObject.contains(Deployer.class)
					&& config.getName().equalsIgnoreCase("deployerVersion")) {
				Deployer dv = new Deployer(config, (TechnologyVersion) parent);
				if (!deployerSet.containsKey(dv.getId())
						|| (rootSet != deployerSet && parent != deployerSet
								.get(dv.getId()).getParent())) {
					deployerSet.put(dv.getId(), dv);
				} else {
					dv = deployerSet.get(dv.getId());
				}
				futurParent = dv;
			}

			// Scan for generator or deployer option
			if (!omitedObject.contains(OptionComponant.class)
					&& config.getName().equalsIgnoreCase("option")) {
				OptionComponant opt = null;
				if (parent instanceof Generator) {
					opt = new OptionGenerator(config, (Generator) parent);
					if (!optGeneratorSet.containsKey(opt.getId())) {
						optGeneratorSet.put(opt.getId(), (OptionGenerator) opt);
					} else {
						opt = optGeneratorSet.get(opt.getId());
					}
				} else if (parent instanceof Deployer) {
					opt = new OptionDeployer(config, (Deployer) parent);
					if (!optDeployerSet.containsKey(opt.getId())) {
						optDeployerSet.put(opt.getId(), (OptionDeployer) opt);
					} else {
						opt = optDeployerSet.get(opt.getId());
					}
				}
				futurParent = opt;
			}

			// Scan for generator or deployer parameter
			if (config.getName().equalsIgnoreCase("configurationParameter")) {
				GeneratorParameter param = null;
				if (parent instanceof Generator) {
					Generator g = (Generator) parent;
					param = new GeneratorParameter(config);
					if (!genParamConfByGenerator.containsKey(g.getId())) {
						genParamConfByGenerator.put(g.getId(),
								new ArrayList<String>());
					}
					genParamConfByGenerator.get(g.getId()).add(param.getKey());
					configurationParameters.put(param.getKey(), param);
				} else if (parent instanceof Deployer) {
					Deployer d = (Deployer) parent;
					param = new GeneratorParameter(config);
					if (!deployParamConfByGenerator.containsKey(d.getId())) {
						deployParamConfByGenerator.put(d.getId(),
								new ArrayList<String>());
					}
					deployParamConfByGenerator.get(d.getId()).add(param.getKey());
					deployerParameters.put(param.getKey(),param);
				}
				futurParent = null;
			}

			// Will add children if not already set
			if (parent != null && futurParent != null) {
				if (parent.getChild(futurParent.getId()) == null) {
					parent.addChildren(futurParent);
				}
			}

			for (IConfigurationElement child : config.getChildren()) {
				// System.err.println("Manage conf for child " +
				// (child.getAttribute("id") != null ? child.getAttribute("id")
				// : child.getAttribute("key")) + " and parent " + (parent !=
				// null ? parent.getId() : ""));
				manageConfiguration(child, futurParent);

			}
		}

		/**
		 * Initialise les éléments de l'arbre en les checkant sur la clé. Si
		 * l'élément est invalide il sera desactivé
		 */
		public void initializeFromKey() {

		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	/**
	 * Internal class for label of the tree element
	 * 
	 * @author Eric
	 * 
	 */
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
			} else if (object instanceof Deployer) {
				return new Image(null, ApplicationDialog.class
						.getResourceAsStream("tree/img/deployer/deployer"
								+ suffix + ".png"));
			} else if (object instanceof OptionDeployer) {
				return new Image(null, ApplicationDialog.class
						.getResourceAsStream("tree/img/optionDeployer/options"
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
			} else if (object instanceof Deployer) {
				Deployer elt = (Deployer) object;
				return elt.getVersion();
			} else if (object instanceof OptionDeployer) {
				OptionDeployer elt = (OptionDeployer) object;
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

	public class ElementTreeListener {

		TreeViewer tv;

		/**
		 * Enable sub element of the given item.
		 * 
		 * @param item
		 */
		protected void enableAllSubElements(TreeItem item) {
			for (TreeItem ti : item.getItems()) {
				TreeElement el = (TreeElement) ti.getData();
				// Check the validity if the component
				if (el instanceof ImplNode) {
					if (checkElementValidity(el)) {
						el.setEnabled(true);
					}
					tv.update(el, null);
				} else if (!el.isEnabled()) {
					el.setEnabled(true);
					tv.update(el, null);
				}
				if (el.isChecked()) {
					if (el instanceof ImplNode) {
						refreshImplNodeOptions();
					}
					enableAllSubElements(ti);
				}
			}
		}

		/**
		 * Disable sub element of the given item.
		 * 
		 * @param item
		 */
		protected void disableAllSubElements(TreeItem item) {
			for (TreeItem ti : item.getItems()) {
				TreeElement el = (TreeElement) ti.getData();
				if (el.isEnabled()) {
					el.setEnabled(false);
					if (el instanceof ImplNode) {
						refreshImplNodeOptions();
					}
					tv.update(el, null);
				}
				disableAllSubElements(ti);
			}
		}
	}

	/**
	 * Internal class : use by generation option tree.
	 * 
	 * @author Eric
	 * 
	 */
	public class GenerationOptionTreeListener extends ElementTreeListener
			implements Listener {

		GenerationOptionTreeListener() {
			tv = genOptionsTree;
		}

		public void handleEvent(Event event) {
			Point point = new Point(event.x, event.y);
			documentationText.setText(builDocumentationText());
			TreeItem item = tv.getTree().getItem(point);

			TreeElement el = (TreeElement) item.getData();
			// Check if el is active or not in the key if it is a component
			boolean canCheck = true;
			if (el instanceof ImplNode) {
				canCheck = checkElementValidity(el);
				if (!canCheck) {
					errorMsg
							.setText("This element is not active in your key");
				} else {
					errorMsg.setText("");
				}
			} else {
				errorMsg.setText("");
			}
			// If click on image : check it, else : just show informations
			if (canCheck
					&& (item.getImageBounds(0) != null && event.x <= item
							.getImageBounds(0).x
							+ item.getImageBounds(0).width)) {
				// Check if enabled
				if (el.isEnabled()) {
					// Inverse
					el.setChecked(!(el.isChecked()));
					tv.update(el, null);

					if (el.isChecked()) {
						// Enable all sub elements
						enableAllSubElements(item);
					} else {
						// Enable all sub elements
						disableAllSubElements(item);
					}
				}
			}
			refreshOptions();
		}
	}

	/**
	 * Internal class : use by generation option tree.
	 * 
	 * @author Eric
	 * 
	 */
	public class DeployementOptionTreeListener extends ElementTreeListener
			implements Listener {

		public DeployementOptionTreeListener() {
			tv = deployOptionsTree;
		}

		public void handleEvent(Event event) {
			Point point = new Point(event.x, event.y);
			documentationText.setText(builDocumentationText());
			TreeItem item = tv.getTree().getItem(point);
			TreeElement el = (TreeElement) item.getData();
			// If click on image : check it, else : just show informations
			if (item.getImageBounds(0) != null
					&& event.x <= item.getImageBounds(0).x
							+ item.getImageBounds(0).width) {
				// Check if enabled
				if (el.isEnabled()) {
					// Inverse
					el.setChecked(!(el.isChecked()));
					tv.update(el, null);

					if (el.isChecked()) {
						// Enable all sub elements
						enableAllSubElements(item);
					} else {
						// Enable all sub elements
						disableAllSubElements(item);
					}
				}
			}
			refreshOptions();
		}
	}
}
