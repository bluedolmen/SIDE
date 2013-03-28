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
package com.bluexml.side.ant;

import org.apache.tools.ant.BuildException;

import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.GeneratorConfiguration;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.application.ui.action.utils.Generate;

public class GenerateTask extends SideComponentTask {

	private boolean generate = true;
	private boolean pack = true;
	private boolean resolveDependencies = true;

	public void setGenerate(boolean generate) {
		this.generate = generate;
	}

	public void setPack(boolean pack) {
		this.pack = pack;
	}

	public boolean isPack() {
		return pack;
	}

	public boolean isGenerate() {
		return generate;
	}

	public void setResolveDependencies(boolean resolveDependencies) {
		this.resolveDependencies = resolveDependencies;
	}

	public boolean isResolveDependencies() {
		return resolveDependencies;
	}

	@Override
	public void executeImp() throws BuildException {
		
		new ApplicationModelGenerateJobWrapper() {
			
			@Override
			public void run() throws Exception {
				Generate gen = getHeadlessGenerate();
				// override activated generation phase
				gen.setDoCompletion(pack);
				gen.setDoGenerate(generate);
				gen.setDoResolveDep(resolveDependencies);
				gen.setDoDocumentation(false);

				// get generator configuration to run
				Configuration configuration = gen.getConfiguration();
				GeneratorConfiguration elem = (GeneratorConfiguration) ApplicationUtil.getComponantConfiguration(configuration, getComponentid());

				boolean error = false;
				error = gen.launchGenerationConfiguration(error, elem);
				gen.refreshFolders();
				
				if (error) {
					throw new BuildException("Generation failed");
				}

			}
			
		}.performJob();

	}

}
