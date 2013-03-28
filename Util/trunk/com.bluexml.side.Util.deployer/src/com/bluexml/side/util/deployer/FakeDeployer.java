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

public class FakeDeployer extends Deployer {

	public FakeDeployer() {
		super("fakeClean", "fakeLog");
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		// nothing to do
	}

	@Override
	protected void deployProcess(File fileToDeploy, DeployMode mode) throws Exception {
		// nothing to do
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// nothing to do
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		// nothing to do
	}

	public boolean check() {
		return true;
	}

	public boolean checkOption(String optionID) {
		return true;
	}

}
