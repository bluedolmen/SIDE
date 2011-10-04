package com.bluexml.side.integration.eclipse.builder.incremental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.metamodel.Side;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.compare.util.EMFComparePreferenceConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.ComponantConfiguration;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.DeployerConfiguration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.ModuleConstraint;
import com.bluexml.side.application.Option;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.action.MustBeStopped;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.integration.eclipse.builder.Activator;
import com.bluexml.side.integration.eclipse.builder.SIDEBuilderConstants;
import com.bluexml.side.integration.eclipse.builder.settings.SIDEBuilderConfiguration;
import com.bluexml.side.integration.standalone.ApplicationModelGenerateGenerationJob;
import com.bluexml.side.integration.standalone.GenerateModelHelper;
import com.bluexml.side.util.libs.CollectionHelper;

public class IncrementalBuilderHelper {
	private static final String ONLY_DEPLOYERS_APPLICATION = "onlyDeployers.application";
	private static final String INCREMENTAL_APPLICATION = "incremental.application";
	private static final String WHITOUT_DEPLOYERS_APPLICATION = "whitoutDeployers.application";
	private static final String MULTI_MODELS_CHANGES_APPLICATION = "multiModelsChanges.application";
	private static final String[] builderModels = new String[] { ONLY_DEPLOYERS_APPLICATION, INCREMENTAL_APPLICATION, WHITOUT_DEPLOYERS_APPLICATION, MULTI_MODELS_CHANGES_APPLICATION };
	IProject project;
	SIDEBuilderConfiguration sideConf = null;

	/**
	 * @return the sideConf
	 */
	public SIDEBuilderConfiguration getSideConf() {
		return sideConf;
	}

	public IncrementalBuilderHelper(IProject project) {
		this.project = project;
		this.sideConf = new SIDEBuilderConfiguration(getProject());
	}

	public void generateChangedModelsIncremental() throws Exception, MustBeStopped {
		if (sideConf.load()) {
			// models to process

			String confName = sideConf.getConfigurationName();
			// try to get root .application from current project
			IFile file = sideConf.getApplicationFile();
			if (file == null || !file.exists()) {
				System.out.println("oupse");
			}

			Application app = (Application) com.bluexml.side.Util.ecore.ModelInitializationUtils.openModel(file).get(0);

			generateApplicationWithoutClean(confName, app, null);
		}
	}

	private void generateApplicationWithoutClean(String confName, Application app, List<String> models) throws CoreException, Exception, MustBeStopped {
		// run all selected deployer
		Application onlyDeployers = (Application) EcoreUtil.copy(app);
		// deployed must do not use clean
		Configuration configuration = onlyDeployers.getConfiguration(confName);

		for (ComponantConfiguration comp : configuration.getDeployerConfigurations()) {
			// unselect clean
			comp.getOptions().clear();
		}
		disableGenerationClean(configuration);
		deleteBuilderApplicationModels();
		IFile onlyDeployersAppModelFile = saveApplicationModel(MULTI_MODELS_CHANGES_APPLICATION, onlyDeployers);
		generateChangedModels(onlyDeployersAppModelFile, confName, true, models);
	}

	public void deleteBuilderApplicationModels() throws CoreException {
		for (String s : builderModels) {
			IFile onlyDeployersAppModelFile_ = getIncrementalModel(s);
			onlyDeployersAppModelFile_.delete(true, false, new NullProgressMonitor());
		}
	}

	public void generateChangedModels(boolean complete, IFile changedmodel) throws Exception, MustBeStopped {

		if (sideConf.load()) {
			// models to process
			List<String> newModels = new ArrayList<String>();

			String confName = sideConf.getConfigurationName();
			// try to get root .application from current project
			IFile file = sideConf.getApplicationFile();
			if (file == null || !file.exists()) {
				System.out.println("oupse");
			}

			Application app = (Application) com.bluexml.side.Util.ecore.ModelInitializationUtils.openModel(file).get(0);

			// changed model type application or other
			System.out.println(" changedmodel " + changedmodel);
			System.out.println(" application model :" + file);

			if (changedmodel.getFullPath().toPortableString().equals(file.getFullPath().toPortableString())) {
				// changed model is the application model set for SIDE builder
				System.out.println("application model change");
				// the changed model is application model

				// must compute advanced delta on Ecore elements
				// Loads the new version
				String modelLocation = changedmodel.getLocation().toString();
				System.out.println("SIDEBuilder.generateChangedModels() N:" + modelLocation);
				final Application _new = app;

				//Load the previous version
				IPath path = changedmodel.getFullPath().removeFirstSegments(1);
				IFolder folder = changedmodel.getProject().getFolder(SIDEBuilderConstants.metadataFolder);
				folder = folder.getFolder(path.removeLastSegments(1));
				path = folder.getFullPath().append(changedmodel.getName());
				IFile previous = changedmodel.getProject().getFile(path.removeFirstSegments(1));
				if (previous.exists()) {
					String previousModel = previous.getLocation().toString();
					System.out.println("SIDEBuilder.generateChangedModels() P:" + previousModel);
					final Application _old = (Application) EResourceUtils.openModel(previousModel, new HashMap<Object, Object>()).getContents().get(0);

					/**
					 * == Add ==
					 * new model -> same as incremental on the new model
					 * new component, new option
					 * we could run generator only with new options
					 * generator must have all generation part in options the
					 * main part must be configured as an activated hidden
					 * option, so we can disable main part
					 * Copy all generator parameter from new model
					 **/

					/**
					 * Add models
					 */
					updateModelList(newModels, _new, _old);

					/**
					 * get configuration delta
					 */
					Configuration currentConfiguration = _new.getConfiguration(confName);
					Configuration previousConfiguration = _old.getConfiguration(confName);
					Map<DifferenceKind, List<Entry<EObject, DiffElement>>> searchConfDiff = searchDiff(previousConfiguration, currentConfiguration);

					// now we build a Configuration that feet incremental process
					Configuration incrementalConf = ApplicationFactory.eINSTANCE.createConfiguration();
					incrementalConf.setName(confName);

					/**
					 * Add components OK
					 */
					addComponents(searchConfDiff, incrementalConf);

					/**
					 * Add components option
					 */

					addOptions(searchConfDiff, incrementalConf);

					/**
					 * Add generator parameters OK
					 */
					EList<ConfigurationParameters> parameters = currentConfiguration.getParameters();
					incrementalConf.getParameters().addAll(EcoreUtil.copyAll(parameters));
					disableGenerationClean(incrementalConf);
					
					/**
					 * Changed parameters -> include all related generator OK
					 */
					List<ComponantConfiguration> components = new ArrayList<ComponantConfiguration>();
					components.addAll(currentConfiguration.getDeployerConfigurations());
					components.addAll(currentConfiguration.getGeneratorConfigurations());
					for (Map.Entry<EObject, DiffElement> diffElement : searchConfDiff.get(DifferenceKind.CHANGE)) {
						if (diffElement.getKey() instanceof ConfigurationParameters) {
							ConfigurationParameters param = (ConfigurationParameters) diffElement.getKey();
							String key = param.getKey();
							for (ComponantConfiguration componantConfiguration : components) {

								IConfigurationElement iConfigurationElement = ApplicationUtil.getIConfigurationElement(componantConfiguration);
								IConfigurationElement[] children = iConfigurationElement.getChildren("configurationParameter");

								for (IConfigurationElement Iparam : children) {
									if (Iparam.getAttribute("key").equalsIgnoreCase(key)) {
										// must add this component because it use a parameter that changed 
										ComponantConfiguration componantConfigurationCopy = (ComponantConfiguration) EcoreUtil.copy(componantConfiguration);

										if (componantConfigurationCopy instanceof GeneratorConfiguration) {
											EList<GeneratorConfiguration> generatorConfigurations = incrementalConf.getGeneratorConfigurations();
											if (!contains(generatorConfigurations, componantConfigurationCopy)) {
												generatorConfigurations.add((GeneratorConfiguration) componantConfigurationCopy);
											}
										} else if (componantConfigurationCopy instanceof DeployerConfiguration) {
											EList<DeployerConfiguration> deployerConfigurations = incrementalConf.getDeployerConfigurations();
											if (!contains(deployerConfigurations, componantConfigurationCopy)) {
												deployerConfigurations.add((DeployerConfiguration) componantConfigurationCopy);
											}
										}
										break;
									}
								}
							}
						}
					}

					/**
					 * == delete ==
					 * model -> delete generated packages
					 * generator, delete generated packages
					 * generator option -> delete package and run the component
					 * deployer, ... need to undo deploy or do clean the webapp
					 * deployer option ...
					 */

					// (deployer must do not use clean !)

					// debug save on FS the incremental application model

					// launch side process

					// run whole process on new models list (deployer disabled)
					boolean incrementalForNewModel = newModels.size() > 0;
					boolean incrementalForGeneratorChanges = incrementalConf.getGeneratorConfigurations().size() > 0;
					boolean needTodeploy = incrementalForGeneratorChanges || incrementalForNewModel;

					// delete previous builder application models
					deleteBuilderApplicationModels();
					if (incrementalForNewModel) {
						Application wholeWithoutDeployers = (Application) EcoreUtil.copy(_new);
						wholeWithoutDeployers.getElements().removeAll(ApplicationUtil.getModels(wholeWithoutDeployers));
						for (String m : newModels) {
							Model createModel = ApplicationFactory.eINSTANCE.createModel();
							createModel.setFile(m);
							String lastSegment = Path.fromPortableString(m).lastSegment();
							createModel.setName(lastSegment);
							wholeWithoutDeployers.getElements().add(createModel);
						}
						// remove deployers
						wholeWithoutDeployers.getConfiguration(confName).getDeployerConfigurations().clear();
						IFile wholeWithoutDeployersAppModelFile = saveApplicationModel(WHITOUT_DEPLOYERS_APPLICATION, wholeWithoutDeployers);
						generateChangedModels(wholeWithoutDeployersAppModelFile, confName, complete, newModels);
					}

					if (incrementalForGeneratorChanges) {
						// run incremental application model on other models
						List<String> AllOldModels = ApplicationUtil.getModelsPathFromApplication(_old);
						Application createApplication = ApplicationFactory.eINSTANCE.createApplication();
						createApplication.getElements().add(incrementalConf);
						// copy models entries into incremental application not really used but usefull for debug
						List<Model> models = ApplicationUtil.getModels(_old);
						createApplication.getElements().addAll(EcoreUtil.copyAll(models));
						IFile incrementalApplicationModel = saveApplicationModel(INCREMENTAL_APPLICATION, createApplication);
						generateChangedModels(incrementalApplicationModel, confName, complete, AllOldModels);
					}

					if (needTodeploy) {
						// run all selected deployer
						Application onlyDeployers = (Application) EcoreUtil.copy(_new);
						// deployed must do not use clean
						Configuration configuration = onlyDeployers.getConfiguration(confName);
						configuration.getGeneratorConfigurations().clear();
						for (ComponantConfiguration comp : configuration.getDeployerConfigurations()) {
							// unselect clean
							comp.getOptions().clear();
						}
						// disable clean generation option if exists
						disableGenerationClean(configuration);
						
						IFile onlyDeployersAppModelFile = saveApplicationModel(ONLY_DEPLOYERS_APPLICATION, onlyDeployers);
						generateChangedModels(onlyDeployersAppModelFile, confName, complete, new ArrayList<String>());
					}

				}

				/**
				 * = manage changes in application => accurate incremental =
				 * only run changed side components on the DEFAULT configuration
				 * (see builder
				 * settings)
				 * other configuration is run by user
				 * == change ==
				 * parameters changes -> run list of components that use the
				 * parameter (need
				 * method to get the list)
				 */

			} else {
				boolean validFile = false;
				for (String ext : SIDEBuilderConstants.availableExtensions) {
					String fileExtension = changedmodel.getFileExtension();
					if (!ext.equals("application") && fileExtension != null && fileExtension.equalsIgnoreCase(ext)) {
						validFile = true;
						break;
					}
				}
				if (validFile) {
					// process incremental for the changed model
					EList<ModelElement> elements = app.getElements();
					for (ModelElement modelElement : elements) {
						if (modelElement instanceof com.bluexml.side.application.Model) {
							com.bluexml.side.application.Model model = (com.bluexml.side.application.Model) modelElement;
							if (model.getFile().equals(changedmodel.getFullPath().toOSString())) {
								// ok model exist in application file so process the generation
								newModels.add(model.getFile());
								System.out.println("changed models :" + model.getFile());
							}
						}
					}
					System.out.println("try to execute generators for the model (whithout any clean options)");
					generateApplicationWithoutClean(confName, app, newModels);
				}
			}
		} else {
			// SIDE project configuration unavailable
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, "SIDE Builder Fail, missing project configuration, please to set SIDE project properties"));
		}

	}

	private void disableGenerationClean(Configuration configuration) {
		EList<ConfigurationParameters> parameters2 = configuration.getParameters();
		for (ConfigurationParameters configurationParameters : parameters2) {
			if (configurationParameters.getKey().equals(StaticConfigurationParameters.GENERATIONOPTIONSCLEAN.getLiteral())) {
				configurationParameters.setValue("false");
			}
		}
	}

	private IProject getProject() {
		return project;
	}

	private IFile saveApplicationModel(String incrementalFile, Application createApplication) throws CoreException, Exception {
		IFile incrementalApplicationModel = getIncrementalModel(incrementalFile);
		if (!incrementalApplicationModel.exists()) {
			incrementalApplicationModel.create(null, true, new NullProgressMonitor());
		}
		ModelInitializationUtils.saveModel(incrementalApplicationModel, createApplication);
		return incrementalApplicationModel;
	}

	private IFile getIncrementalModel(String incrementalFile) {
		return getProject().getFolder(SIDEBuilderConstants.metadataFolder).getFile(incrementalFile);
	}

	private void addOptions(Map<DifferenceKind, List<Entry<EObject, DiffElement>>> searchConfDiff, Configuration incrementalConf) throws Exception {
		// need to group options by parent
		List<Option> beforecopy = new ArrayList<Option>();

		for (Map.Entry<EObject, DiffElement> diffElement : searchConfDiff.get(DifferenceKind.CHANGE)) {
			if (diffElement.getKey() instanceof Option) {
				//							beforecopy.add((Option) diffElement.getKey());

			}
		}

		for (Map.Entry<EObject, DiffElement> diffElement : searchConfDiff.get(DifferenceKind.ADDITION)) {
			if (diffElement.getKey() instanceof Option) {
				beforecopy.add((Option) diffElement.getKey());
			}
		}

		Map<ComponantConfiguration, List<Option>> options = new HashMap<ComponantConfiguration, List<Option>>();

		CollectionHelper<ComponantConfiguration, Option> p = new CollectionHelper<ComponantConfiguration, Option>(options);
		// group by parent because need  to include parent with new options 
		for (Option option : beforecopy) {
			ComponantConfiguration parent = (ComponantConfiguration) option.eContainer();
			p.addToMap(parent, option, false);
		}
		Set<Entry<ComponantConfiguration, List<Option>>> entrySet = options.entrySet();
		for (Entry<ComponantConfiguration, List<Option>> entry : entrySet) {
			// for each key create or find component
			ComponantConfiguration component = (ComponantConfiguration) EcoreUtil.copy(entry.getKey());

			// options
			component.getOptions().clear();
			List<Option> optionsL = entry.getValue();
			component.getOptions().addAll(EcoreUtil.copyAll(optionsL));

			// module constraints
			// clear
			component.getModuleContraints().clear();
			// search for module constraints
			for (Map.Entry<EObject, DiffElement> diffElement : searchConfDiff.get(DifferenceKind.ADDITION)) {
				EObject module = diffElement.getKey();
				if (module instanceof ModuleConstraint) {
					ComponantConfiguration parent = (ComponantConfiguration) module.eContainer();
					if (parent.getId().equals(component.getId())) {
						// module constraints for this component
						component.getModuleContraints().add((ModuleConstraint) EcoreUtil.copy(module));
					}
				}
			}

			if (component instanceof GeneratorConfiguration) {
				incrementalConf.getGeneratorConfigurations().add((GeneratorConfiguration) component);
			} else if (component instanceof DeployerConfiguration) {
				incrementalConf.getDeployerConfigurations().add((DeployerConfiguration) component);
			}
		}
		// add to conf
	}

	private boolean contains(EList<? extends ComponantConfiguration> generatorConfigurations, ComponantConfiguration c) {
		for (ComponantConfiguration componantConfiguration : generatorConfigurations) {
			if (componantConfiguration.getId().equals(c.getId())) {
				return true;
			}
		}
		return false;
	}

	private void addComponents(Map<DifferenceKind, List<Entry<EObject, DiffElement>>> searchConfDiff, Configuration incrementalConf) {
		// deal with added components
		//		for (Map.Entry<EObject, DiffElement> diffElement : searchConfDiff.get(DifferenceKind.CHANGE)) {
		//			if (diffElement.getKey() instanceof GeneratorConfiguration) {
		//				GeneratorConfiguration m = (GeneratorConfiguration) EcoreUtil.copy(diffElement.getKey());
		//				System.out.println("SIDEBuilder.addComponents() add " + m);
		//				incrementalConf.getGeneratorConfigurations().add(m);
		//			}
		//			if (diffElement.getKey() instanceof DeployerConfiguration) {
		//				DeployerConfiguration m = (DeployerConfiguration) EcoreUtil.copy(diffElement.getKey());
		//				System.out.println("SIDEBuilder.addComponents() add " + m);
		//				incrementalConf.getDeployerConfigurations().add(m);
		//			}
		//		}
		// no need to search for children it's a new component
		for (Map.Entry<EObject, DiffElement> diffElement : searchConfDiff.get(DifferenceKind.ADDITION)) {
			if (diffElement.getKey() instanceof GeneratorConfiguration) {
				GeneratorConfiguration m = (GeneratorConfiguration) EcoreUtil.copy(diffElement.getKey());
				System.out.println("SIDEBuilder.addComponents() add " + m);
				incrementalConf.getGeneratorConfigurations().add(m);
			}
			if (diffElement.getKey() instanceof DeployerConfiguration) {
				DeployerConfiguration m = (DeployerConfiguration) EcoreUtil.copy(diffElement.getKey());
				System.out.println("SIDEBuilder.addComponents() add " + m);
				incrementalConf.getDeployerConfigurations().add(m);
			}
		}
	}

	private void updateModelList(List<String> models, final Application _new, final Application _old) throws InterruptedException {
		Map<DifferenceKind, List<Entry<EObject, DiffElement>>> searchDiff = searchDiff(_old, _new);
		// deal with models list

		for (Map.Entry<EObject, DiffElement> diffElement : searchDiff.get(DifferenceKind.CHANGE)) {
			if (diffElement.getKey() instanceof com.bluexml.side.application.Model) {
				com.bluexml.side.application.Model m = (com.bluexml.side.application.Model) diffElement.getKey();
				models.add(m.getFile());
			}
		}

		for (Map.Entry<EObject, DiffElement> diffElement : searchDiff.get(DifferenceKind.ADDITION)) {
			if (diffElement.getKey() instanceof com.bluexml.side.application.Model) {
				com.bluexml.side.application.Model m = (com.bluexml.side.application.Model) diffElement.getKey();
				models.add(m.getFile());
			}
		}
	}

	protected Map<DifferenceKind, List<Map.Entry<EObject, DiffElement>>> searchDiff(EObject _old, EObject _new) throws InterruptedException {
		HashMap<DifferenceKind, List<Entry<EObject, DiffElement>>> diffs = new HashMap<DifferenceKind, List<Map.Entry<EObject, DiffElement>>>();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(EMFComparePreferenceConstants.PREFERENCES_KEY_IGNORE_ID, false);
		options.put(EMFComparePreferenceConstants.PREFERENCES_KEY_IGNORE_XMIID, false);
		System.out.println("SIDEBuilder.generateChangedModels() call match with options :" + options);
		final MatchModel match = MatchService.doMatch(_new, _old, options);

		EList<UnmatchElement> unmatchedElements = match.getUnmatchedElements();
		System.out.println("SIDEBuilder.generateChangedModels() unmatched :" + unmatchedElements.size());
		for (UnmatchElement unmatchElement : unmatchedElements) {
			EObject element = unmatchElement.getElement();
			Side side = unmatchElement.getSide();
			System.out.println("SIDEBuilder.generateChangedModels() UnmatchedElements :");
			System.out.println("* side :" + side);
			System.out.println("* element :" + element);
		}

		final DiffModel diff = DiffService.doDiff(match, false);
		EList<DiffElement> ownedElements = diff.getOwnedElements();

		Map<EObject, DiffElement> objs = DeltaCollector.collectDifferences(ownedElements);

		Collection<Map.Entry<EObject, DiffElement>> values = objs.entrySet();
		List<Map.Entry<EObject, DiffElement>> adds = new ArrayList<Map.Entry<EObject, DiffElement>>();

		List<Map.Entry<EObject, DiffElement>> delete = new ArrayList<Map.Entry<EObject, DiffElement>>();

		List<Map.Entry<EObject, DiffElement>> update = new ArrayList<Map.Entry<EObject, DiffElement>>();

		List<Map.Entry<EObject, DiffElement>> move = new ArrayList<Map.Entry<EObject, DiffElement>>();

		for (Map.Entry<EObject, DiffElement> e : values) {
			int kindV = e.getValue().getKind().getValue();
			if (kindV == (DifferenceKind.ADDITION_VALUE)) {
				adds.add(e);
			} else if (kindV == (DifferenceKind.CHANGE_VALUE)) {
				update.add(e);
			} else if (kindV == (DifferenceKind.DELETION_VALUE)) {
				delete.add(e);
			} else if (kindV == (DifferenceKind.MOVE_VALUE)) {
				move.add(e);
			}
		}
		diffs.put(DifferenceKind.ADDITION, adds);
		diffs.put(DifferenceKind.CHANGE, update);
		diffs.put(DifferenceKind.DELETION, delete);
		diffs.put(DifferenceKind.MOVE, move);
		return diffs;
	}

	protected void generateChangedModels(IFile applicationIFile, String confName, boolean complete, final List<String> models) throws Exception, MustBeStopped {
		Application application = GenerateModelHelper.getApplication(applicationIFile, false);
		ApplicationModelGenerateGenerationJob job = new ApplicationModelGenerateGenerationJob(application) {
			@Override
			protected List<String> getModelPaths() {
				return models;
			}
		};
		job.setConfigurationName(confName);
		job.generate();
	}

	public void executeSIDEProjectProcess() throws Exception, MustBeStopped {
		SIDEBuilderConfiguration sideConf = new SIDEBuilderConfiguration(getProject());

		if (sideConf.load()) {
			// models to process
			deleteBuilderApplicationModels();
			String confName = sideConf.getConfigurationName();
			// try to get project .application from current project
			IFile application = sideConf.getApplicationFile();
			generateChangedModels(application, confName, true, null);
		}
	}
}
