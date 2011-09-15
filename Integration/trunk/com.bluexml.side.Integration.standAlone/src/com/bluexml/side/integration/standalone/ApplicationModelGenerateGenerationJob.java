package com.bluexml.side.integration.standalone;

import java.text.DecimalFormat;
import java.util.logging.Level;

import org.eclipse.core.runtime.NullProgressMonitor;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ui.action.utils.Generate;

/**
 * This is an extension of {@link ApplicationModelJob} which main task
 * is to generate files configured in the {@link Application} model.
 * 
 * @author pajot-b
 * 
 */
public class ApplicationModelGenerateGenerationJob extends ApplicationModelGenerateJob {
	
	
	public ApplicationModelGenerateGenerationJob(Application applicationModel) {
		this(applicationModel, null);
	}
	
	public ApplicationModelGenerateGenerationJob(Application applicationModel, String configurationName) {
		super(applicationModel, configurationName);
	}

	/**
	 * Performs the generation job.
	 * 
	 * @throws Exception
	 */
	public void generate() throws Exception {
		
		long stepStartTime = System.currentTimeMillis();
		LOGGER.log(Level.FINE, String.format("Processing application '%s'", getApplicationName()));
		
		Generate gen = getHeadlessGenerate();
		try {
			gen.run_(new NullProgressMonitor()); // don't use job scheduler, but invoke the main method
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, String.format("The generation of the file '%s' raised an unepected exception.", getApplicationName()), e);
		}
		
		long stepProcessTime = System.currentTimeMillis() - stepStartTime;
		float timeInSec = stepProcessTime / 1000f;
		LOGGER.log(Level.FINE, String.format("Generated application '%s' in '%s' sec.", getApplicationName(), new DecimalFormat("#.###").format(timeInSec)));
		
	}

	@Override
	public void run() throws Exception {
		generate();
	}

}
