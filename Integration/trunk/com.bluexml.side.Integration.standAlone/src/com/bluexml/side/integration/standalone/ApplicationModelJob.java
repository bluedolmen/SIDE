package com.bluexml.side.integration.standalone;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;

/**
 * Provides a (headless) processing environment on an a given
 * {@link Application} model and configuration-name.
 * <p>
 * If application files are completely independent and processors (e.g.,
 * generators) are thread-safe, then this job can be used to generate
 * applications concurrently.
 * 
 * @author pajot-b
 * 
 */
public abstract class ApplicationModelJob {
	
	protected static final Logger LOGGER = Logger.getLogger(ApplicationModelJob.class.getName());

	private final Application applicationModel;
	private final String configurationName;

	private final String applicationName;

	public ApplicationModelJob(Application applicationModel) {
		this(applicationModel, null);
	}
	
	protected ApplicationModelJob(Application applicationModel, String configurationName) {
		if (applicationModel == null) {
			throw new IllegalArgumentException("The provided application model has to be non-null");
		}

		this.applicationModel = applicationModel;
		applicationName = applicationModel.getName();
		
		this.configurationName = configurationName;
	}	
	
	protected final String getApplicationName() {
		return applicationName;
	}
	
	protected final Application getApplicationModel() {
		return applicationModel;
	}
	
	protected List<String> getModelPaths() {
		return ApplicationUtil.getModelsPathFromApplication(applicationModel);
	}
	
	/**
	 * Retrieves the {@link Configuration}.
	 * 
	 * @return the configuration
	 */
	protected final Configuration getConfiguration() {

		Configuration configuration = null;

		if (configurationName != null) {
			configuration = getApplicationModel().getConfiguration(configurationName);
			
			if (configuration == null) {
				LOGGER.warning(String.format("The provided configuration name '%s' cannot be found in the provided application '%s', default behavior will be applied", configurationName, getApplicationName()));
			}
		}

		if (configuration == null) {	
			// get the first configuration if no configuration name is provided
			LOGGER.fine(String.format("Loading first configuration from application file '%s'", applicationName));
			EList<ModelElement> elements = applicationModel.getElements();
			for (ModelElement modelElement : elements) {
				if (modelElement instanceof Configuration) {
					configuration = (Configuration) modelElement;
					break;
				}
			}
		}
		
		if (configuration != null) {
			LOGGER.info(String.format("Using configuration '%s'", configuration.getName()));
		} else {
			LOGGER.severe(String.format("Cannot find any configuration in the provided application '%s'", applicationName));
		}
		
		return configuration;
	}

	/**
	 * Method that should be implemented by all
	 * {@link ApplicationModelJob}s which performs an operation on the
	 * embedded {@link Application}.
	 * 
	 * @throws Exception
	 */
	public abstract void run() throws Exception;
	
}
