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
package com.bluexml.side.Integration.eclipse.branding.enterprise.actions;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.bluexml.side.util.libs.maven.PomHelper;
import com.bluexml.side.util.libs.maven.pom.Dependency;
import com.bluexml.side.util.libs.maven.pom.Model;
import com.bluexml.side.util.libs.maven.pom.Model.Dependencies;

public class PomMigrationHelper {

	protected static void updateMavenPom(File file, String[] groupId, String newVersion, String newClassifier) throws JAXBException {
		JAXBElement<Model> pom = PomHelper.load(file);

		Dependencies dependencies = pom.getValue().getDependencies();
		if (dependencies != null) {
			List<Dependency> dependency = dependencies.getDependency();
			for (Dependency itemDep : dependency) {
				if (Arrays.asList(groupId).contains(itemDep.getGroupId())) {
					itemDep.setVersion(newVersion);
					itemDep.setClassifier(newClassifier);
				}
			}
		}
		PomHelper.save(pom, file);
	}
}
