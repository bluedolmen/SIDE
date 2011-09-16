package com.bluexml.side.integration.standalone;

import java.util.List;
import java.util.logging.Logger;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.Configuration;
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
	
	/**
	 * Provides a {@link Configuration} from an {@link Application}.
	 * 
	 * @author pajot-b
	 *
	 */
	public static interface ApplicationConfigurationProvider {
		public Configuration getConfiguration(Application applicationModel);
	}
	
	/**
	 * The {@link DefaultConfigurationProvider} provides the first
	 * {@link Configuration} from an {@link Application} model
	 * 
	 * @author pajot-b
	 * 
	 */
	public static final class DefaultConfigurationProvider implements ApplicationConfigurationProvider {

		public Configuration getConfiguration(Application applicationModel) {
			// get the first configuration if no configuration name is provided
			LOGGER.fine(String.format("Loading first configuration from application model '%s'", applicationModel.getName()));
			return ApplicationUtil.getFirstConfiguration(applicationModel);
		}
		
	}
	
	public static final class NamedConfigurationProvider implements ApplicationConfigurationProvider {

		private final String configurationName;
		
		public NamedConfigurationProvider(String configurationName) {
			if (configurationName == null || configurationName.isEmpty()) {
				throw new IllegalArgumentException("The provided configuration-name cannot be null nor empty");
			}
			this.configurationName = configurationName;
		}
		
		public Configuration getConfiguration(Application applicationModel) {
			Configuration configuration = applicationModel.getConfiguration(configurationName);
			
			if (configuration == null) {
				LOGGER.warning(String.format("The provided configuration name '%s' cannot be found in the provided application '%s', default behavior will be applied", configurationName, applicationModel.getName()));
			}

			return configuration;
		}
		
	}
	
	public static final class StaticConfigurationProvider implements ApplicationConfigurationProvider {

		private final Configuration configuration;
		
		public StaticConfigurationProvider(Configuration configuration) {
			if (configuration == null) {
				throw new IllegalArgumentException("The provided static configuration cannot be null");
			}
			
			this.configuration = configuration;
		}
		
		public Configuration getConfiguration(Application applicationModel) {
			if (ApplicationUtil.getConfigurations(applicationModel).contains(configuration)) {
				return configuration;
			}

			LOGGER.warning(String.format("The provided configuration '%s' cannot be found in the provided application '%s'", configuration.getName(), applicationModel.getName()));
			return null;
		}
		
	}
	
	protected static final Logger LOGGER = Logger.getLogger(ApplicationModelJob.class.getName());

	private final Application applicationModel;
	private final String applicationName;
	
	private ApplicationConfigurationProvider configurationProvider = new DefaultConfigurationProvider();
	
	protected ApplicationModelJob(Application applicationModel) {
		if (applicationModel == null) {
			throw new IllegalArgumentException("The provided application model has to be non-null");
		}

		this.applicationModel = applicationModel;
		applicationName = applicationModel.getName();
		
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

		Configuration configuration = configurationProvider.getConfiguration(applicationModel);
		
		if (configuration != null) {
			LOGGER.info(String.format("Using configuration '%s'", configuration.getName()));
		} else {
			LOGGER.severe(String.format("Cannot apply any configuration on the provided application '%s'", applicationName));
		}
		
		return configuration;
	}

	public void setConfigurationProvider(ApplicationConfigurationProvider configurationProvider) {
		if (configurationProvider == null) {
			throw new IllegalArgumentException("The provided " + ApplicationConfigurationProvider.class.getName() + " cannot be null");
		}
		this.configurationProvider = configurationProvider;
	}
	
	public void setConfigurationName(String configurationName) {
		if (configurationName != null) {
			this.configurationProvider = new NamedConfigurationProvider(configurationName);
		} // else keep the default behavior (keep backward compatibility)
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
