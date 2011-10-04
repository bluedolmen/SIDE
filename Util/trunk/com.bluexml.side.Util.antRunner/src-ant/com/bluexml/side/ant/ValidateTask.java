/**
 * 
 */
package com.bluexml.side.ant;

import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.integration.standalone.ApplicationModelJob;

/**
 * @author davidabad
 */
public class ValidateTask extends SideApplicationTask {

	@Override
	public void executeImp() throws BuildException {
		
		Application application = getInitApplicationModel();
		
		try {
			new ApplicationModelJob(application) {
				
				@Override
				public void run() throws Exception {
					final List<String> models = getModelPaths();
					final Map<String, List<IFile>> modelsInfo = ApplicationUtil.getAssociatedMetaModel(models);
					
					for (List<IFile> modelIFiles : modelsInfo.values()) {
						for (IFile modelIFile : modelIFiles) {
							if (ApplicationUtil.validate(modelIFile)) {
								LOGGER.fine("model '" + modelIFile.getName() + "' validated");
							} else {
								LOGGER.warning(Activator.Messages.getString("Generate.7", modelIFile.getName()));
							}
						}						
					}
				}
				
			}.run();
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}
}
