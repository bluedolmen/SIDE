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
