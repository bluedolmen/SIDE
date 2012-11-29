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
