/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
