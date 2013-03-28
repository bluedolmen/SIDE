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
package com.bluexml.side.util.generator.acceleo;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractAcceleoPackageGenerator extends AbstractAcceleoGenerator {

	abstract public Collection<IFile> buildPackages(String modelId) throws Exception;

	public Collection<IFile> complete(Map<String, List<IFile>> models) throws Exception {
		if (models != null && (groupedModels == null || groupedModels.size() == 0)) {
			groupedModels = models;
		}
		for (Map.Entry<String, List<IFile>> l : groupedModels.entrySet()) {
			String rootName = l.getKey();
			setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + rootName);
			// packages name/id do not need to have suffix eClass().name			
			Collection<IFile> packageFile = buildPackages(rootName.substring(0, rootName.lastIndexOf("_")));
			generatedFiles.addAll(packageFile);
			for (IFile p : packageFile) {
				monitor.getLog().addFileGeneratedLog(p.getName() + " created.", p.getName() + " created in " + p.getFullPath(), IFileHelper.getFile(p).toURI());
			}
		}
		for (IFile f : generatedFiles) {
			monitor.getLog().addFileGeneratedLog("Files Generated", f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI());
		}

		return generatedFiles;
	}

}
