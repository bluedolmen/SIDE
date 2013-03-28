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
package com.bluexml.side.application.custommodules;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.AbstractGenerator;

public class Generator extends AbstractGenerator {

	@Override
	public boolean shouldGenerate(HashMap<String, List<IFile>> modelsInfo, String id_metamodel) {
		return true;
	}

	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> models, String id_metamodel) throws Exception {
		return Collections.emptyList();
	}

	@Override
	public Collection<IFile> complete(Map<String, List<IFile>> models) throws Exception {
		addDependences();
		return Collections.emptyList();
	}

	@Override
	public void addDependences() throws Exception {
		// get dependences
		dm.copyDependencies(getTemporarySystemFile(), getTargetSystemFile(), true);
		// dependences packages is now with other resources in the target folder
	}

}
