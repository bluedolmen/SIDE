package com.bluexml.side.application.ui.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
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
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.SWTResourceManager;
import com.bluexml.side.application.ui.action.contraints.ConstraintsChecker;
import com.bluexml.side.application.ui.action.table.GeneratorParameter;
import com.bluexml.side.application.ui.action.table.GeneratorParameterCellModifier;
import com.bluexml.side.application.ui.action.table.GeneratorParameterContentProvider;
import com.bluexml.side.application.ui.action.table.GeneratorParameterDataStructure;
import com.bluexml.side.application.ui.action.table.GeneratorParameterLabelProvider;
import com.bluexml.side.application.ui.action.tree.ConfigurationContentProvider;
import com.bluexml.side.application.ui.action.tree.ConfigurationLabelProvider;
import com.bluexml.side.application.ui.action.tree.Deployer;
import com.bluexml.side.application.ui.action.tree.Generator;
import com.bluexml.side.application.ui.action.tree.ImplNode;
import com.bluexml.side.application.ui.action.tree.Metamodel;
import com.bluexml.side.application.ui.action.tree.OptionComponant;
import com.bluexml.side.application.ui.action.tree.Technology;
import com.bluexml.side.application.ui.action.tree.TreeElement;
import com.bluexml.side.application.ui.action.tree.TreeNode;
import com.bluexml.side.application.ui.action.tree.TreeView;
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
	private TreeView genOptionsTree;
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
	private String columnNameValue = Activator.Messages.getString("ApplicationDialog.0"); //$NON-NLS-1$
	private String columnNameLabel = Activator.Messages.getString("ApplicationDialog.1"); //$NON-NLS-1$
	private GeneratorParameterDataStructure dataStructure;
	private TableViewer generatorParametersViewer;
	private Button documentationButton;
	private Button skipValidationButton;
	private Text destinationText;
	private Text logText;
	private org.eclipse.swt.widgets.List list;
	private GeneratorParameterContentProvider generatorParameterContentProvider;
	private GeneratorParameterLabelProvider generatorParameterLabelProvider;
	private GeneratorParameterCellModifier generatorParameterCellModifier;
	private TabFolder tabFolder;
	private TabItem generationConfigurationTabItem;
	private TreeView deployOptionsTree;
	private TabItem deployementTabItem;
	private Table modelPropertiesTable;
	private Button cleanButton;
//	private Button offlineMode;
	private TabItem modelsTabItem;

	public static String KEY_DOCUMENTATION = StaticConfigurationParameters.GENERATIONOPTIONSDOCUMENTATION.getLiteral();
	public static String KEY_SKIPVALIDATION = StaticConfigurationParameters.GENERATIONOPTION_SKIP_VALIDATION.getLiteral();
	public static String KEY_DOCLEAN = StaticConfigurationParameters.GENERATIONOPTIONSCLEAN.getLiteral();
	public static String KEY_LOGPATH = StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral();
	public static String KEY_GENPATH = StaticConfigurationParameters.GENERATIONOPTIONSDESTINATION_PATH.getLiteral();
	public static String KEY_OFFLINE = StaticConfigurationParameters.GENERATION_OPTION_OFFLINE_MODE.getLiteral();
	public static List<String> staticFieldsName = Arrays.asList(KEY_GENPATH, KEY_LOGPATH, KEY_SKIPVALIDATION, KEY_DOCUMENTATION, KEY_DOCLEAN, KEY_OFFLINE);

	/**
	 * Create the dialog
	 * 
	 * @param parentShell
	 * @param rwm_model
	 */
	public ApplicationDialog(Shell parentShell, IFile file) {
		super(parentShell);
		setShellStyle(SWT.DIALOG_TRIM | SWT.MODELESS | getDefaultOrientation());
		try {
			URI uri = URI.createFileURI(file.getRawLocation().toFile().getAbsolutePath());
			XMIResource resource = new XMIResourceImpl(uri);

			FileInputStream fi = new FileInputStream(file.getRawLocation().toFile());
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put(ApplicationPackage.eINSTANCE.getNsURI(), ApplicationPackage.eINSTANCE);
			map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
			resource.load(fi, map);

			application = (Application) resource.getContents().get(0);
			model = file;
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Application Dialog Launch Error", e)); //$NON-NLS-1$
		}
		configurationParameters = new HashMap<String, GeneratorParameter>();
		deployerParameters = new HashMap<String, GeneratorParameter>();
		genParamConfByGenerator = new HashMap<String, List<String>>();
		deployParamConfByGenerator = new HashMap<String, List<String>>();
	}

	public void refreshConfiguration() {
		if (configurationList.getSelectionIndex() != -1) {
			loadingTree = true;
			String name = configurationList.getItem(configurationList.getSelectionIndex());
			Configuration configuration = application.getConfiguration(name);
			try {
				ApplicationUtil.updateConfigurationFromExtensionPoint(configuration);
				// apply updates
				saveData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Refresh documentation
			if (configuration != null)
				if (configuration.getDescription() != null)
					config_description.setText(configuration.getDescription());

			// Refresh tree
			initializeTree();

			Set<TreeItem> generators = collectImplNode(genOptionsTree);
			boolean atleastOneCheckedGenerator = configureTree(configuration, generators, genOptionsTree);

			Set<TreeItem> deployers = collectImplNode(deployOptionsTree);
			boolean atleastOneCheckedDeployer = configureTree(configuration, deployers, deployOptionsTree);

			if (atleastOneCheckedGenerator) {
				collapseUnchecked(genOptionsTree.getTree().getItems());
			} else {
				genOptionsTree.expandAll();
			}
			if (atleastOneCheckedDeployer) {
				collapseUnchecked(deployOptionsTree.getTree().getItems());
			} else {
				deployOptionsTree.expandAll();
			}

			// Refresh static generator parameters
			initializeStaticParameters();
			// Refresh dynamic generator parameters
			refreshOptions(configuration);
			loadingTree = false;
		}

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
				} catch (Exception e) {
					e.printStackTrace();
				}
				IFile file = null;
				try {
					file = ApplicationUtil.getIFileForModel(m);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (m != null) {
					TableItem item = new TableItem(modelPropertiesTable, SWT.NONE);
					// Name
					item.setText(0, Activator.Messages.getString("ApplicationDialog.7")); //$NON-NLS-1$
					item.setText(1, m.getName());
				}
				if (metaModel != null) {
					TableItem item = new TableItem(modelPropertiesTable, SWT.NONE);
					// Metamodel
					item.setText(0, Activator.Messages.getString("ApplicationDialog.8")); //$NON-NLS-1$
					item.setText(1, metaModel.getNsURI());
				}
				if (file != null) {
					TableItem item = new TableItem(modelPropertiesTable, SWT.NONE);
					// Charset

					try {
						item.setText(1, file.getCharset());
						item.setText(0, Activator.Messages.getString("ApplicationDialog.9")); //$NON-NLS-1$
					} catch (CoreException e) {
						e.printStackTrace();
					}

					TableItem item2 = new TableItem(modelPropertiesTable, SWT.NONE);
					// Modification date
					IPath path = file.getLocation();
					if (path != null) {
						File ioFile = path.toFile();
						if (ioFile != null) {
							Date date = new Timestamp(ioFile.lastModified());
							Locale locale = Locale.getDefault();
							DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
							item2.setText(0, Activator.Messages.getString("ApplicationDialog.10")); //$NON-NLS-1$
							item2.setText(1, dateFormat.format(date));
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
		if (tabFolder.getSelection() != null && tabFolder.getSelection()[0].equals(generationConfigurationTabItem)) {
			initializeDynamicGenerationParameters();
		} else {
			initializeDynamicDeployementParameters();
		}
		configureGeneratorOptions(configuration);
	}

	public void refreshImplNodeOptions() {
		String name = configurationList.getItem(configurationList.getSelectionIndex());
		Configuration configuration = application.getConfiguration(name);
		refreshOptions(configuration);
	}

	private void initializeStaticParameters() {
		ConfigurationParameters docParam = ApplicationUtil.getConfigurationParmeterByKey(KEY_DOCUMENTATION);
		if (docParam != null) {
			documentationButton.setSelection(Boolean.parseBoolean(docParam.getValue()));
		}

		ConfigurationParameters skipValidationParam = ApplicationUtil.getConfigurationParmeterByKey(KEY_SKIPVALIDATION);
		if (skipValidationParam != null) {
			skipValidationButton.setSelection(Boolean.parseBoolean(skipValidationParam.getValue()));
		}

		ConfigurationParameters logPathParam = ApplicationUtil.getConfigurationParmeterByKey(KEY_LOGPATH);
		if (logPathParam != null) {
			logText.setText(logPathParam.getValue());
		}

		ConfigurationParameters doClean = ApplicationUtil.getConfigurationParmeterByKey(KEY_DOCLEAN);
		if (doClean != null) {
			cleanButton.setSelection(Boolean.parseBoolean(doClean.getValue()));
		}

		ConfigurationParameters updatePathParam = ApplicationUtil.getConfigurationParmeterByKey(KEY_GENPATH);
		if (updatePathParam != null) {
			destinationText.setText(updatePathParam.getValue());
		}

//		ConfigurationParameters offline = ApplicationUtil.getConfigurationParmeterByKey(KEY_OFFLINE);
//		if (offline != null) {
//			offlineMode.setSelection(Boolean.parseBoolean(offline.getValue()));
//		}
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
			for (ComponantConfiguration elem : ApplicationUtil.getComponantConfigurations(conf)) {
				confIds.add(elem.getId());
			}
			optionsGroup.setText(Activator.Messages.getString("ApplicationDialog.11")); //$NON-NLS-1$
		} else {
			confIds.add(gen.getId());
			optionsGroup.setText(Activator.Messages.getString("ApplicationDialog.12", gen.getVersion())); //$NON-NLS-1$
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
						neededParam.put(paramId, configurationParameters.get(paramId));
					}
				}
			}
		}
		for (GeneratorParameter genParam : neededParam.values()) {
			genParam.setValue(""); //$NON-NLS-1$
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
			for (ComponantConfiguration elem : ApplicationUtil.getComponantConfigurations(conf)) {
				confIds.add(elem.getId());
			}
			optionsGroup.setText(Activator.Messages.getString("ApplicationDialog.14")); //$NON-NLS-1$
		} else {
			confIds.add(dep.getId());
			optionsGroup.setText(Activator.Messages.getString("ApplicationDialog.15", dep.getVersion())); //$NON-NLS-1$
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
				genParam.setValue(""); //$NON-NLS-1$
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
			GeneratorParameter genParam = dataStructure.getParamMatching(confParam.getKey());
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
	 * 
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
	private boolean configureTree(Configuration configuration, Set<TreeItem> generators, TreeViewer tv) {
		boolean atleastOnchecked = false;
		ConfigurationContentProvider ctp = (ConfigurationContentProvider) tv.getContentProvider();
		for (TreeItem item : generators) {
			ImplNode g = (ImplNode) item.getData();
			for (ComponantConfiguration ce : ApplicationUtil.getComponantConfigurations(configuration)) {
				if (ce.getId().equals(g.getId())) {
					g.setChecked(true);
					g.setEnabled(true);
					tv.update(g, null);

					for (TreeNode tn : g.getChildren()) {
						OptionComponant o = (OptionComponant) tn;
						for (Option opt : ce.getOptions()) {
							o.setEnabled(true);
							ComponantConfiguration parent = (ComponantConfiguration)opt.eContainer();
							String idOpt= parent.getId()+"_"+opt.getKey();
							
							if (idOpt.equals(o.getId())) {
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
					atleastOnchecked = true;
					refreshParents(item, tv);
				}
			}
		}
		loadingTree = false;
		List<TreeNode> list = ctp.getToCheck();
		for (TreeNode t : list) {
			if (!t.isEnabled()) {
				t.setEnabled(true);
			}
			if (!t.isChecked()) {
				t.setChecked(true);
				if (t instanceof ImplNode) {
					ImplNode tt = (ImplNode) t;
					for (TreeNode treeNode : tt.getChildren()) {
						OptionComponant op = (OptionComponant) treeNode;
						op.setEnabled(true);
					}
					checkDefaultOptions(tt, tv);
				}

			}
		}
		// Ugly but avoid modification made pop up.
		saveData();

		return atleastOnchecked;
	}

	private void collapseUnchecked(TreeItem[] childs) {
		for (TreeItem treeItem : childs) {
			TreeElement item = (TreeElement) treeItem.getData();

			if (!(item.isChecked() && item.isEnabled())) {
				treeItem.setExpanded(false);
			} else {
				collapseUnchecked(treeItem.getItems());
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
	 * Open file dialog box to select files (here a model)
	 */
	protected void SelectModelFileDialog() {
		String filePath = null;
		String fileName = null;
		ElementTreeSelectionDialog ets = new ElementTreeSelectionDialog(Display.getDefault().getActiveShell(), new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());
		ets.setBlockOnOpen(true);
		ets.setAllowMultiple(true);
		ets.setTitle(Activator.Messages.getString("ApplicationDialog.17")); //$NON-NLS-1$
		ets.setMessage(Activator.Messages.getString("ApplicationDialog.18")); //$NON-NLS-1$
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
		// Composite container = (Composite) parent.getShell();
		container.setLayout(null);

		tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(15, 39, 472, 520);

		modelsTabItem = new TabItem(tabFolder, SWT.NONE);
		modelsTabItem.setText(Activator.Messages.getString("ApplicationDialog.19")); //$NON-NLS-1$

		final Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		modelsTabItem.setControl(composite_3);

		final Button addModelButton = new Button(composite_3, SWT.NONE);
		addModelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				SelectModelFileDialog();
			}
		});
		addModelButton.setText(Activator.Messages.getString("ApplicationDialog.20")); //$NON-NLS-1$
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
		removeModelButton.setText(Activator.Messages.getString("ApplicationDialog.21")); //$NON-NLS-1$
		removeModelButton.setBounds(146, 175, 130, 25);

		final Label generationsOptionsLabel_2 = new Label(composite_3, SWT.NONE);
		generationsOptionsLabel_2.setBounds(10, 8, 240, 24);
		generationsOptionsLabel_2.setFont(SWTResourceManager.getFont("", 12, SWT.BOLD)); //$NON-NLS-1$
		generationsOptionsLabel_2.setText(Activator.Messages.getString("ApplicationDialog.23")); //$NON-NLS-1$

		modelPropertiesTable = new Table(composite_3, SWT.BORDER);
		modelPropertiesTable.setLinesVisible(true);
		modelPropertiesTable.setHeaderVisible(false);
		modelPropertiesTable.setBounds(10, 226, 444, 115);
		modelPropertiesTable.setVisible(false);

		final TableColumn newColumnTableColumn_2 = new TableColumn(modelPropertiesTable, SWT.NONE);
		newColumnTableColumn_2.setWidth(127);
		newColumnTableColumn_2.setText(Activator.Messages.getString("ApplicationDialog.24")); //$NON-NLS-1$

		final TableColumn newColumnTableColumn_3 = new TableColumn(modelPropertiesTable, SWT.NONE);
		newColumnTableColumn_3.setWidth(294);
		newColumnTableColumn_3.setText(Activator.Messages.getString("ApplicationDialog.25")); //$NON-NLS-1$

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

		// Show a warning if the component is not valid
		errorMsg = new Label(container, SWT.BOLD);
		errorMsg.setBounds(493, 67, 297, 15);
		errorMsg.setForeground(new Color(container.getDisplay(), 255, 0, 0));

		generationConfigurationTabItem = new TabItem(tabFolder, SWT.NONE);
		generationConfigurationTabItem.setText(Activator.Messages.getString("ApplicationDialog.27")); //$NON-NLS-1$

		final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				if (!tabFolder.getEnabled()) {
					showAlert(Activator.Messages.getString("ApplicationDialog.28"), Activator.Messages.getString("ApplicationDialog.29")); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		});
		generationConfigurationTabItem.setControl(composite_1);

		genOptionsTree = new TreeView(composite_1, SWT.BORDER);
		tree_1 = genOptionsTree.getTree();
		tree_1.setBounds(0, 160, 459, 304);
		List<Class<?>> omitedClassForGen = new ArrayList<Class<?>>();
		omitedClassForGen.add(Deployer.class);
		genOptionsTree.setContentProvider(new ConfigurationContentProvider(Metamodel.class, omitedClassForGen, genOptionsTree, configurationParameters, deployerParameters, genParamConfByGenerator,
				deployParamConfByGenerator));
		genOptionsTree.setLabelProvider(new ConfigurationLabelProvider());
		genOptionsTree.setInput(this);
		genOptionsTree.expandAll();

		Listener checkOnClick = new GenerationOptionTreeListener();
		genOptionsTree.getTree().addListener(SWT.MouseDown, checkOnClick);

		logText = new Text(composite_1, SWT.BORDER);
		logText.addFocusListener(new FocusAdapter() {
			public void focusLost(final FocusEvent e) {
				ConfigurationParameters param = ApplicationUtil.getConfigurationParmeterByKey(KEY_LOGPATH);
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
		logLabel.setText(Activator.Messages.getString("ApplicationDialog.30")); //$NON-NLS-1$
		logLabel.setBounds(0, 10, 113, 15);

		final Label destinationLabel = new Label(composite_1, SWT.NONE);
		destinationLabel.setAlignment(SWT.RIGHT);
		destinationLabel.setBounds(0, 54, 113, 15);
		destinationLabel.setText(Activator.Messages.getString("ApplicationDialog.31")); //$NON-NLS-1$

		destinationText = new Text(composite_1, SWT.BORDER);
		destinationText.addFocusListener(new FocusAdapter() {
			public void focusLost(final FocusEvent e) {
				ConfigurationParameters param = ApplicationUtil.getConfigurationParmeterByKey(KEY_GENPATH);
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
				String filePath = ""; //$NON-NLS-1$
				Container folder = displaySelectFolderInWorkspace(Activator.Messages.getString("ApplicationDialog.33")); //$NON-NLS-1$

				if (folder != null) {
					filePath = folder.getFullPath().toPortableString();
					logText.setText(filePath);

					ConfigurationParameters param = ApplicationUtil.getConfigurationParmeterByKey(KEY_LOGPATH);
					if (param != null) {
						param.setValue(filePath);
						modificationMade();
					}
				}
			}

		});
		browseLogPathButton.setText(Activator.Messages.getString("ApplicationDialog.34")); //$NON-NLS-1$
		browseLogPathButton.setBounds(381, 12, 75, 25);

		final Button browseGenPathButton = new Button(composite_1, SWT.NONE);
		browseGenPathButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				String filePath = ""; //$NON-NLS-1$
				Container folder = displaySelectFolderInWorkspace(Activator.Messages.getString("ApplicationDialog.36")); //$NON-NLS-1$

				if (folder != null) {
					filePath = folder.getFullPath().toPortableString();
					destinationText.setText(filePath);

					ConfigurationParameters param = ApplicationUtil.getConfigurationParmeterByKey(KEY_GENPATH);
					if (param != null) {
						param.setValue(filePath);
						modificationMade();
					}
				}
			}
		});
		browseGenPathButton.setText(Activator.Messages.getString("ApplicationDialog.37")); //$NON-NLS-1$
		browseGenPathButton.setBounds(381, 54, 75, 25);

		final Label chooseYourGenerationLabel = new Label(composite_1, SWT.NONE);
		chooseYourGenerationLabel.setText(Activator.Messages.getString("ApplicationDialog.38")); //$NON-NLS-1$
		chooseYourGenerationLabel.setBounds(0, 95, 456, 15);

		documentationButton = new Button(composite_1, SWT.CHECK);
		documentationButton.setToolTipText(Activator.Messages.getString("ApplicationDialog.39")); //$NON-NLS-1$
		documentationButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ConfigurationParameters param = ApplicationUtil.getConfigurationParmeterByKey(KEY_DOCUMENTATION);
				Button b = (Button) e.getSource();
				if (param != null) {
					param.setValue(Boolean.toString(b.getSelection()));
				} else {
					addStaticParam(KEY_DOCUMENTATION, Boolean.toString(b.getSelection()));
				}
				ApplicationDialog.modificationMade();
			}
		});
		documentationButton.setText(Activator.Messages.getString("ApplicationDialog.40")); //$NON-NLS-1$
		documentationButton.setBounds(10, 116, 144, 20);

		skipValidationButton = new Button(composite_1, SWT.CHECK);
		skipValidationButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ConfigurationParameters param = ApplicationUtil.getConfigurationParmeterByKey(KEY_SKIPVALIDATION);
				Button b = (Button) e.getSource();
				if (param != null) {
					param.setValue(Boolean.toString(b.getSelection()));
				} else {
					addStaticParam(KEY_SKIPVALIDATION, Boolean.toString(b.getSelection()));
				}
				ApplicationDialog.modificationMade();
			}
		});
		skipValidationButton.setToolTipText(Activator.Messages.getString("ApplicationDialog.41")); //$NON-NLS-1$
		skipValidationButton.setText(Activator.Messages.getString("ApplicationDialog.43")); //$NON-NLS-1$
		skipValidationButton.setBounds(160, 116, 131, 20);

		cleanButton = new Button(composite_1, SWT.CHECK);
		cleanButton.setToolTipText(Activator.Messages.getString("ApplicationDialog.2")); //$NON-NLS-1$
		cleanButton.setText(Activator.Messages.getString("ApplicationDialog.44")); //$NON-NLS-1$
		cleanButton.setBounds(297, 116, 159, 16);
		cleanButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ConfigurationParameters param = ApplicationUtil.getConfigurationParmeterByKey(KEY_DOCLEAN);
				Button b = (Button) e.getSource();
				if (param != null) {
					param.setValue(Boolean.toString(b.getSelection()));
				} else {
					addStaticParam(KEY_DOCLEAN, Boolean.toString(b.getSelection()));
				}
				ApplicationDialog.modificationMade();
			}
		});

//		offlineMode = new Button(composite_1, SWT.CHECK);
//		offlineMode.setToolTipText(Activator.Messages.getString("ApplicationDialog.53")); //$NON-NLS-1$
//		offlineMode.setText(Activator.Messages.getString("ApplicationDialog.52")); //$NON-NLS-1$
//		offlineMode.setBounds(10, 137, 159, 16);
//		offlineMode.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(final SelectionEvent e) {
//				ConfigurationParameters param = ApplicationUtil.getConfigurationParmeterByKey(KEY_OFFLINE);
//				Button b = (Button) e.getSource();
//				if (param != null) {
//					param.setValue(Boolean.toString(b.getSelection()));
//				} else {
//					addStaticParam(KEY_OFFLINE, Boolean.toString(b.getSelection()));
//				}
//				ApplicationDialog.modificationMade();
//			}
//		});

		deployementTabItem = new TabItem(tabFolder, SWT.NONE);
		deployementTabItem.setText(Activator.Messages.getString("ApplicationDialog.45")); //$NON-NLS-1$

		tabFolder.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(final SelectionEvent e) {
				if (tabFolder.getSelection().length > 0) {
					if (tabFolder.getSelection()[0].equals(generationConfigurationTabItem) || tabFolder.getSelection()[0].equals(deployementTabItem)) {
						optionsGroup.setVisible(true);
						config_description.setVisible(true);
						documentationText.setBounds(490, 45, 297, 197);
						refreshOptions();
					} else {
						optionsGroup.setVisible(false);
						config_description.setVisible(false);
						documentationText.setBounds(490, 45, 297, 518);
						refreshModelPropertiesTable();
					}
				}
				documentationText.setText(""); //$NON-NLS-1$
			}
		});

		final Composite composite = new Composite(tabFolder, SWT.NONE);
		deployementTabItem.setControl(composite);

		deployOptionsTree = new TreeView(composite, SWT.BORDER);
		Tree tree_2 = deployOptionsTree.getTree();
		tree_2.setBounds(0, 36, 459, 410);
		Listener deployementListener = new DeployementOptionTreeListener();
		tree_2.addListener(SWT.MouseDown, deployementListener);

		List<Class<?>> omitedClassForDeploy = new ArrayList<Class<?>>();
		omitedClassForDeploy.add(Generator.class);
		omitedClassForDeploy.add(Metamodel.class);
		deployOptionsTree.setContentProvider(new ConfigurationContentProvider(Technology.class, omitedClassForDeploy, deployOptionsTree, configurationParameters, deployerParameters,
				genParamConfByGenerator, deployParamConfByGenerator));
		deployOptionsTree.setLabelProvider(new ConfigurationLabelProvider());
		deployOptionsTree.setInput(this);
		deployOptionsTree.expandAll();

		final Label chooseYourGenerationLabel_1 = new Label(composite, SWT.NONE);
		chooseYourGenerationLabel_1.setBounds(10, 15, 456, 15);
		chooseYourGenerationLabel_1.setText(Activator.Messages.getString("ApplicationDialog.47")); //$NON-NLS-1$

		for (ModelElement elem : application.getElements()) {
			if (elem instanceof Model) {
				Model model = (Model) elem;
				list.add(model.getFile());
			}
		}
		// Component thaht shows the description in the top right of the scree
		config_description = new Text(container, SWT.READ_ONLY | SWT.BORDER | SWT.WRAP);
		config_description.setBounds(490, 250, 297, 51);
		config_description.setVisible(false);
		// Browser that shows informations on the selected component (right)
		documentationText = new Browser(container, SWT.BORDER);
		documentationText.setBounds(490, 45, 297, 518);
		documentationText.setText(buildHelpDocumentationText("")); //$NON-NLS-1$
		
		final Label listOfConfigurayionsLabel = new Label(container, SWT.NONE);
		listOfConfigurayionsLabel.setBounds(5, 13, 136, 23);
		listOfConfigurayionsLabel.setText(Activator.Messages.getString("ApplicationDialog.49")); //$NON-NLS-1$

		final Button editBt = new Button(container, SWT.NONE);
		editBt.setBounds(325, 10, 48, 26);
		editBt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (configurationList.getItemCount() > 0) {
					if (configurationList.getSelectionIndex() != -1) {
						String name = configurationList.getItem(configurationList.getSelectionIndex());
						Configuration config = application.getConfiguration(name);
						ConfigurationDialog dialog = new ConfigurationDialog(new Shell(), config.getName(), config.getDescription());
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
		editBt.setImage(SWTResourceManager.getImage(ApplicationDialog.class, "tree/img/edit.png")); //$NON-NLS-1$

		final Button addBt = new Button(container, SWT.NONE);
		addBt.setBounds(375, 10, 48, 26);
		addBt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				createNewConfiguration();
			}
		});
		addBt.setImage(SWTResourceManager.getImage(ApplicationDialog.class, "tree/img/add.png")); //$NON-NLS-1$

		final Button copyBt = new Button(container, SWT.NONE);
		copyBt.setBounds(425, 10, 48, 26);
		copyBt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				copyConfiguration();
			}
		});
		copyBt.setImage(SWTResourceManager.getImage(ApplicationDialog.class, "tree/img/copy.png")); //$NON-NLS-1$

		
		
		final Button deleteBt = new Button(container, SWT.NONE);
		deleteBt.setBounds(475, 10, 48, 26);
		deleteBt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (configurationList.getItemCount() > 0) {
					if (configurationList.getSelectionIndex() != -1) {
						String name = configurationList.getItem(configurationList.getSelectionIndex());
						Configuration config = application.getConfiguration(name);
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
					errorMsg.setText(Activator.Messages.getString("ApplicationDialog.61")); //$NON-NLS-1$
				}
				refreshConfiguration();
			}
		});
		deleteBt.setImage(SWTResourceManager.getImage(ApplicationDialog.class, "tree/img/delete.png")); //$NON-NLS-1$

		optionsGroup = new Group(container, SWT.NONE);
		optionsGroup.setText(Activator.Messages.getString("ApplicationDialog.63")); //$NON-NLS-1$
		optionsGroup.setBounds(490, 300, 300, 259);
		optionsGroup.setVisible(false);

		generatorParameters = new Table(optionsGroup, SWT.BORDER + SWT.FULL_SELECTION);
		generatorParameters.setLayout(new TableLayout());
		generatorParameters.setBounds(0, 23, 295, 236);
		generatorParameters.setTopIndex(3);
		generatorParameters.getHorizontalBar().setVisible(true);
		generatorParameters.getHorizontalBar().setEnabled(true);
		generatorParameters.setLinesVisible(true);
		generatorParameters.setHeaderVisible(true);
		generatorParameters.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				GeneratorParameter param = (GeneratorParameter) ((StructuredSelection) generatorParametersViewer.getSelection()).getFirstElement();
				if (param != null) {
					documentationText.setText(buildHelpDocumentationText(param.getDocumentation()));
				} else {
					// event fire on empty raw
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		final TableColumn newColumnTableColumn = new TableColumn(generatorParameters, SWT.RIGHT);
		generatorParameters.setSortColumn(newColumnTableColumn);
		newColumnTableColumn.setWidth(109);
		newColumnTableColumn.setText(columnNames[0]);

		final TableColumn newColumnTableColumn_1 = new TableColumn(generatorParameters, SWT.LEFT);
		newColumnTableColumn_1.setWidth(315);
		newColumnTableColumn_1.setText(columnNames[1]);

		final Label generationsOptionsLabel_1 = new Label(optionsGroup, SWT.NONE);
		generationsOptionsLabel_1.setFont(SWTResourceManager.getFont("", 11, SWT.NONE)); //$NON-NLS-1$
		generationsOptionsLabel_1.setText(Activator.Messages.getString("ApplicationDialog.65")); //$NON-NLS-1$

		if (configurationList.getItemCount() > 0) {
			configurationList.select(0);
		} else {
			tabFolder.setEnabled(false);
			errorMsg.setText(Activator.Messages.getString("ApplicationDialog.26")); //$NON-NLS-1$
			addDefaultConfiguration();
		}

		refreshConfiguration();

		// bug fix http://bugs.bluexml.net/show_bug.cgi?id=1226
		tabFolder.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
			}

			public void mouseDown(MouseEvent e) {
				generatorParameterCellModifier.applyDirtyValue();
			}

			public void mouseDoubleClick(MouseEvent e) {
			}
		});

		return container;
	}

	/**
	 * Call when .application is open and no Configuration found in it.
	 */
	private void addDefaultConfiguration() {
		createNewConfiguration();
	}

	/**
	 * Create a new blank configuration
	 */
	private void createNewConfiguration() {
		Configuration config = ApplicationFactory.eINSTANCE.createConfiguration();

		int i = 0;
		String newName = Activator.Messages.getString("ApplicationDialog.51"); //$NON-NLS-1$
		while (application.getConfiguration(newName) != null) {
			i++;
			newName = "New configuration (" + i + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		config.setName(newName);
		configurationList.add(newName);
		configurationList.select(configurationList.getItemCount() - 1);
		addStaticParameters(config);
		application.getElements().add(config);
		modificationMade();
		tabFolder.setEnabled(true);
		errorMsg.setText(""); //$NON-NLS-1$
		refreshConfiguration();
	}

	private void copyConfiguration() {
		Configuration config_ =getCurrentConfiguration();
		int i = 0;

		String newName=config_.getName();
		String old = config_.getName().replaceFirst(" \\([0-9]*\\)","");
		
		while (application.getConfiguration(newName) != null) {
			i++;
			newName = old+" (" + i + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		Configuration config = ApplicationUtil.cloneConfiguration(config_);
		
		
		config.setName(newName);
		configurationList.add(newName);
		configurationList.select(configurationList.getItemCount() - 1);
		addStaticParameters(config);
		application.getElements().add(config);
		modificationMade();
		tabFolder.setEnabled(true);
		errorMsg.setText(""); //$NON-NLS-1$
		refreshConfiguration();
	}
	
	/**
	 * Create the static parameters (used for conf init)
	 * 
	 * @param config
	 */
	private void addStaticParameters(Configuration config) {
		addStaticParam(KEY_DOCUMENTATION, "false", config); //$NON-NLS-1$
		addStaticParam(KEY_SKIPVALIDATION, "false", config); //$NON-NLS-1$
		addStaticParam(KEY_DOCLEAN, "true", config); //$NON-NLS-1$

		String projectPath = "/" + model.getProject().getName() + "/";
		addStaticParam(KEY_LOGPATH, projectPath + "log", config); //$NON-NLS-1$
		addStaticParam(KEY_GENPATH, projectPath + "out", config); //$NON-NLS-1$
		addStaticParam(KEY_OFFLINE, "false", config); //$NON-NLS-1$
	}

	/**
	 * Add a static param to the current configuration
	 * 
	 * @param key
	 * @param value
	 */
	protected void addStaticParam(String key, String value) {
		Configuration config = getCurrentConfiguration();
		boolean exist = false;
		for (ConfigurationParameters p : config.getParameters())
			if (p.getKey() != null && p.getKey().equals(key))
				exist = true;
		if (!exist)
			addStaticParam(key, value, config);
	}

	/**
	 * Add a static param for a given configuration (useful when a new static
	 * param is added)
	 * 
	 * @param key
	 * @param value
	 * @param config
	 */
	protected void addStaticParam(String key, String value, Configuration config) {
		boolean exist = false;
		for (ConfigurationParameters p : config.getParameters())
			if (p.getKey() != null && p.getKey().equals(key))
				exist = true;
		if (!exist) {
			ConfigurationParameters param = ApplicationFactory.eINSTANCE.createConfigurationParameters();
			param.setKey(key);
			param.setValue(value);
			config.getParameters().add(param);
		}
	}

	/**
	 * Display the select folder box (only in workspace)
	 * 
	 * @param message
	 * @return
	 */
	private Container displaySelectFolderInWorkspace(String message) {
		Container result = null;
		ElementTreeSelectionDialog ets = new ElementTreeSelectionDialog(Display.getDefault().getActiveShell(), new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());
		ets.setBlockOnOpen(true);
		ets.setValidator((ISelectionStatusValidator) new FolderSelectionValidator());
		ets.setAllowMultiple(true);

		ets.setTitle(Activator.Messages.getString("ApplicationDialog.66")); //$NON-NLS-1$
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
		editors[1] = (CellEditor) textEditor;

		// Assign the cell editors to the viewer
		generatorParametersViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
		generatorParameterContentProvider = new GeneratorParameterContentProvider(dataStructure);
		generatorParametersViewer.setContentProvider(generatorParameterContentProvider);
		generatorParameterLabelProvider = new GeneratorParameterLabelProvider(dataStructure);
		generatorParametersViewer.setLabelProvider(generatorParameterLabelProvider);
		generatorParameterCellModifier = new GeneratorParameterCellModifier(dataStructure, columnNames, generatorParametersViewer);
		generatorParametersViewer.setCellModifier(generatorParameterCellModifier);

		generatorParametersViewer.setInput(dataStructure);
		generatorParametersViewer.refresh();

	}

	/**
	 * Build a html string for documentation on generator parameter
	 * 
	 * @return
	 */
	private String buildHelpDocumentationText(String documentation) {
		String result = "<html><body style=\"font-family: Verdana; " + "color: #444;" + "text-decoration: none;" + "word-spacing: normal;" + "text-align: justify;" + "letter-spacing: 0;" + "line-height: 1.2em;" + "font-size: 11px;\">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
		result += documentation;
		result += "</body></html>"; //$NON-NLS-1$
		return result;
	}

	/**
	 * Build the documentation text
	 * 
	 * @return
	 */
	private String builDocumentationText() {
		String result = "<html><body style=\"font-family: Verdana; " + "color: #444;" + "text-decoration: none;" + "word-spacing: normal;" + "text-align: justify;" + "letter-spacing: 0;" + "line-height: 1.2em;" + "font-size: 11px;\">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
		TreeItem[] items = null;
		if (isGenTabSelected()) {
			items = genOptionsTree.getTree().getSelection();
		} else if (isDeployTabSelected()) {
			items = deployOptionsTree.getTree().getSelection();
		}

		if (items != null && items.length > 0) {
			TreeItem item = items[0];

			if (item.getData() instanceof TreeElement) {
				TreeElement treeElem = (TreeElement) item.getData();
				if (treeElem.getDescription() != null) {
					result += treeElem.getDescription();
				}

				if (treeElem instanceof Metamodel) {
					Metamodel m = (Metamodel) treeElem;
					result += "<br/>"; //$NON-NLS-1$
					result += "Link :"; //$NON-NLS-1$
					result += "<a href=\"" + m.getURL() + "\" target=\"_blank\">" + m.getURL() + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}
		}
		result += "</body></html>"; //$NON-NLS-1$
		return result;
	}

	/**
	 * Create contents of the button bar
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, GEN_ID, Activator.Messages.getString("ApplicationDialog.90"), false); //$NON-NLS-1$
		createButton(parent, APPLY_ID, Activator.Messages.getString("ApplicationDialog.91"), false); //$NON-NLS-1$
		createButton(parent, IDialogConstants.CLOSE_ID, Activator.Messages.getString("ApplicationDialog.92"), false); //$NON-NLS-1$
	}

	/**
	 * Action for Close, Save and Launch
	 */
	protected void buttonPressed(int buttonId) {
		if (generatorParameterCellModifier != null) {
			// only for some OS that do not unselect cell Editor before fire
			// this event, so we record changes manually
			generatorParameterCellModifier.applyDirtyValue();
		}
		if (buttonId == IDialogConstants.CLOSE_ID) {
			if (applicationModified) {
				int result = showConfirmation(Activator.Messages.getString("ApplicationDialog.93"), Activator.Messages.getString("ApplicationDialog.94")); //$NON-NLS-1$ //$NON-NLS-2$
				if (result == SWT.YES) {
					saveData();
				}
			}
			getShell().close();
			close();
		}
		if (buttonId == APPLY_ID) {
			Display.getCurrent().getActiveShell().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT));
			saveData();
			Display.getCurrent().getActiveShell().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));
		}
		if (buttonId == GEN_ID) {
			if (applicationModified) {
				int result = showConfirmation(Activator.Messages.getString("ApplicationDialog.95"), Activator.Messages.getString("ApplicationDialog.96")); //$NON-NLS-1$ //$NON-NLS-2$
				if (result == SWT.YES) {
					saveData();
				}
			}
			final GeneratePopUp generationPopUp = new GeneratePopUp(Display.getDefault().getActiveShell(), getCurrentConfiguration());
			GeneratePopUp.launch(getCurrentConfiguration(), generationPopUp, application, model);
			close();
			return;
		}

		super.buttonPressed(buttonId);
	}

	/**
	 * Save data in XML file
	 */
	protected void saveData() {
		ApplicationUtil.saveData(model, application);
		applicationModified = false;
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Activator.Messages.getString("ApplicationDialog.98")); //$NON-NLS-1$
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
			confName = configurationList.getItem(configurationList.getSelectionIndex());
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
			String name = configurationList.getItem(configurationList.getSelectionIndex());
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
		MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(), style);
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
		MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(), style);
		mb.setText(title);
		mb.setMessage(message);
	}

	// **** Internal class

	public class GenerationOptionTreeListener extends ElementTreeListener implements Listener {

		public GenerationOptionTreeListener() {
			tv = genOptionsTree;
		}

		public void handleEvent(Event event) {

			Point point = new Point(event.x, event.y);
			// System.out.println("EnterInHandlerEvent for :"+event.widget);
			documentationText.setText(builDocumentationText());
			// System.out.println("HandlerEvent P0-1");
			TreeItem item = tv.getTree().getItem(point);
			// System.out.println("HandlerEvent P0-2");
			if (item == null) {
				// System.out.println("HandlerEvent bugFix #1090");
				// to avoid bug #1090
				return;
			}
			TreeElement el = (TreeElement) item.getData();
			// System.out.println("HandlerEvent P0-3");
			// Check if el is active or not in the key if it is a component
			boolean canCheck = true;
			// System.out.println("HandlerEvent P1");
			if (el instanceof ImplNode) {
				canCheck = ApplicationUtil.checkElementValidity(el);
				if (!canCheck) {
					errorMsg.setText(Activator.Messages.getString("ApplicationDialog.99")); //$NON-NLS-1$
				} else {
					errorMsg.setText(""); //$NON-NLS-1$
				}
				// System.out.println("HandlerEvent P2");
			} else {
				errorMsg.setText(""); //$NON-NLS-1$
			}
			// System.out.println("HandlerEvent P3");
			// If click on image : check it, else : just show informations
			if (canCheck && (item.getImageBounds(0) != null && event.x <= item.getImageBounds(0).x + item.getImageBounds(0).width)) {
				// System.out.println("is check box !");
				// Check if enabled
				if (el.isEnabled()) {
					// System.out.println("isEnabled !");
					// Inverse
					el.setChecked(!(el.isChecked()));

					if (el.isChecked()) {
						// System.out.println("is Checked !");
						// Enable all sub elements
						// action == SWT.YES or action== SWT.NO if messageBox
						// opened -1 if not
						int action = ConstraintsChecker.applyConstraints(tv, item, el);
						if (action == SWT.YES || action == -1) {
							enableAllSubElements(item);
							checkDefaultOptions(el, tv);
						} else if (action == SWT.NO) {
							// uncheck to return to previous state
							el.setChecked(false);
							// tv.update(el, null);
						}
					} else {
						// System.out.println("is Not Checked !");
						// Enable all sub elements
						disableAllSubElements(item);
					}
					tv.update(el, null);
				}

			}
			refreshOptions();
			// System.out.println("End of HandlerEvent");
		}
	}

	public class DeployementOptionTreeListener extends ElementTreeListener implements Listener {

		public DeployementOptionTreeListener() {
			tv = deployOptionsTree;
		}

		public void handleEvent(Event event) {
			Point point = new Point(event.x, event.y);
			documentationText.setText(builDocumentationText());
			TreeItem item = tv.getTree().getItem(point);
			TreeElement el = (TreeElement) item.getData();
			// If click on image : check it, else : just show informations
			if (item.getImageBounds(0) != null && event.x <= item.getImageBounds(0).x + item.getImageBounds(0).width) {
				// Check if enabled
				if (el.isEnabled()) {
					// Inverse
					el.setChecked(!(el.isChecked()));
					if (el.isChecked()) {
						// Enable all sub elements
						enableAllSubElements(item);
						checkDefaultOptions(el, tv);

						ConstraintsChecker.applyConstraints(tv, item, el);
					} else {
						// Enable all sub elements
						disableAllSubElements(item);
					}
					tv.update(el, null);
				}
			}
			refreshOptions();
		}
	}

	public class ElementTreeListener {

		protected TreeViewer tv;

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
					if (ApplicationUtil.checkElementValidity(el)) {
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

	protected boolean isDeployTabSelected() {
		return tabFolder.getSelection()[0].equals(deployementTabItem);
	}

	protected boolean isGenTabSelected() {
		return tabFolder.getSelection()[0].equals(generationConfigurationTabItem);
	}

	protected boolean isModelTabSelected() {
		return tabFolder.getSelection()[0].equals(modelsTabItem);
	}

	protected static void checkDefaultOptions(TreeElement item, TreeViewer tv) {

		// Check the validity if the component
		if (item instanceof ImplNode) {
			ImplNode node = (ImplNode) item;
			Collection<TreeNode> options = node.getChildren();
			for (TreeNode treeNode : options) {
				if (((OptionComponant) treeNode).isDefault()) {
					treeNode.setChecked(true);
					tv.update(treeNode, null);
				}
			}
		}

	}
}
