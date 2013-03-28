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
package com.bluexml.side.util.deployer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.componentmonitor.ComponentMonitor;

public abstract class AbstractMultiDeployer extends Deployer {

	public AbstractMultiDeployer(String cleanKey, String logChanges) {
		super(cleanKey, logChanges);
	}

	protected List<Deployer> deployers = new ArrayList<Deployer>();

	public void addDeployer(Deployer dep) {
		deployers.add(dep);
	}

	@Override
	protected void deployProcess(File fileToDeploy, DeployMode mode) throws Exception {

		for (Deployer wd : deployers) {
			wd.deploy();
		}

	}

	@Override
	public void initialize(Map<String, String> configurationParameters, Map<String, String> generationParameters, List<String> options, ComponentMonitor monitor) {
		super.initialize(configurationParameters, generationParameters, options, monitor);
		for (Deployer wd : deployers) {
			wd.initialize(configurationParameters, generationParameters, options, monitor);
			// propagate options key
			wd.setCleanKey(cleanKey);
			wd.setLogChanges(logChangesKey);
		}
	}
}
