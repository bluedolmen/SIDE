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
package com.bluexml.side.util.libs.eclipse;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.internal.adaptor.ContextFinder;

public class EclipseUtils {

	@SuppressWarnings("restriction")
	public static ClassLoader getContextFinderClassLoader() {
		ClassLoader cl0 = Thread.currentThread().getContextClassLoader();
		ClassLoader cl = null;
		if (cl0 instanceof ContextFinder) {
			cl = cl0;
		} else {
			cl = new ContextFinder(cl0);
		}
		return cl;
	}

	public static IProject importEclipseProject(final File projectFile) throws CoreException {
		File file = new File(projectFile, ".project");
		System.out.println(file);
		System.out.println(file.exists());
		Path projectDescriptionFile = new Path(file.getAbsolutePath());
		return importEclipseProject(projectDescriptionFile);
	}

	public static IProject importEclipseProject(Path projectDescriptionFile) throws CoreException {
		IProjectDescription description = ResourcesPlugin.getWorkspace().loadProjectDescription(projectDescriptionFile);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
		if (!project.exists()) {
			project.create(description, null);
		}

		if (!project.isOpen()) {
			project.open(null);
		}

		return project;
	}
}
