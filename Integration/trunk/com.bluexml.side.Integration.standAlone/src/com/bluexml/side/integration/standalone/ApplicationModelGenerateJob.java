package com.bluexml.side.integration.standalone;

import java.util.List;

import org.eclipse.core.resources.WorkspaceJob;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.action.utils.Generate;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.componentmonitor.Monitor;
import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.componentmonitor.headless.LabelHeadLess;
import com.bluexml.side.util.componentmonitor.headless.StyledTextHeadless;
import com.bluexml.side.util.componentmonitor.headless.progressBarHeadLess;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;

/**
 * An {@link ApplicationModelJob} which provides a wrapper around
 * {@link Generate} {@link WorkspaceJob}.
 * 
 * @author pajot-b
 * 
 */
public abstract class ApplicationModelGenerateJob extends ApplicationModelJob {

	private static final String LOGFILE_NAME = "general_" + Generate.class.getName() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
	
	public ApplicationModelGenerateJob(Application applicationModel) {
		super(applicationModel);
	}
	
	protected final Generate getHeadlessGenerate() throws Exception {
		
		Configuration configuration = getConfiguration();
		List<String> models = getModelPaths();
		
		// instantiate general monitor
		ProgressBarInterface progressBar = new progressBarHeadLess();
		LabelInterface label = new LabelHeadLess();
		ProgressBarInterface progressBar2 = new progressBarHeadLess();
		LabelInterface label2 = new LabelHeadLess();
		StyledTextInterface styletext = new StyledTextHeadless();

		String otherLogPath = ""; //$NON-NLS-1$
		for (ConfigurationParameters p : configuration.getParameters()) {
			if (p.getKey().equals(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral())) {
				otherLogPath = p.getValue();
				break;
			}
		}

		Monitor generalMonitor = new Monitor(styletext, progressBar, label, otherLogPath, configuration.getName(), LOGFILE_NAME);
		ComponentMonitor componentMonitor = new ComponentMonitor(styletext, progressBar2, null, label2, generalMonitor, null, LogType.GENERATION, generalMonitor.getConsoleLog(), LOGFILE_NAME);

		Generate gen = new Generate(configuration, models, generalMonitor, componentMonitor);
		gen.setHeadless(true);
		gen.setUser(false);
		gen.setSystem(true);
		return gen;
	}

}
