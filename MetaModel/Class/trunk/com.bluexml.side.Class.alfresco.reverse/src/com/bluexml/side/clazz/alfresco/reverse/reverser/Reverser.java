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
package com.bluexml.side.clazz.alfresco.reverse.reverser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.alfresco.binding.Class;
import com.bluexml.side.alfresco.binding.Model;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class Reverser {

	public static void executeReverse(Collection<File> alfrescoModels, File sideModelRepo, List<IFile> sideModels, boolean verbose) throws Exception {
		ReverseScheduler rs = new ReverseScheduler(alfrescoModels, verbose);
		rs.schedule();
		Map<Integer, List<Model>> tree = rs.getTree();
		// reverse according to scheduled order (import dependencies tree)
		ReverseModel rm = new ReverseModel(verbose);
		// load and register EObject from existing SIDE models
		rm.loadSIDEModels(sideModels);
		// execute reverse
		for (Map.Entry<Integer, List<Model>> ent : tree.entrySet()) {
			for (Model model : ent.getValue()) {
				EObject sideO = rm.reverse(model);
				String name = model.getName();
				String modelName = ReverseHelper.extractLocalNameFromAlfQName(name);
				String modelPrefix = ReverseHelper.extractPrefixFromAlfQName(name);

				File file = new File(sideModelRepo, modelPrefix + StringUtils.capitalize(modelName) + ".dt");
				file.getParentFile().mkdirs();
				file.createNewFile();
				System.out.println("save model :" + file);
				EResourceUtils.saveModel(file, sideO);
			}
		}

		List<Object> notReverted = rm.getNotReverted();

		if (notReverted.size() > 0) {
			List<String> errorRepport = new ArrayList<String>();
			for (Object object : notReverted) {
				Class c = (Class) object;
				errorRepport.add(c.getName());
			}

			System.err.println("Fail to reverse Overrides for :" + errorRepport);
		}
	}
}
