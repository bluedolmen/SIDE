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
package com.bluexml.side.integration.eclipse.builder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.sideLabs.referential.references.Model;
import org.sideLabs.referential.references.ModelsDocument;

import com.bluexml.side.integration.eclipse.builder.nature.SIDENature;
import com.bluexml.side.integration.eclipse.builder.nature.SIDENatureWithBuilder;

public class SIDEBuilderUtil {

	static public IFile getBackupModel(IFile file) {
		IPath path = file.getFullPath().removeFirstSegments(1);
		IFolder folder = file.getProject().getFolder(SIDEBuilderConstants.metadataFolder);
		folder = folder.getFolder(path.removeLastSegments(1));
		path = folder.getFullPath().append(file.getName());
		return file.getProject().getFile(path.removeFirstSegments(1));
	}

	public static boolean isDiagram(IResource resource) {
		for (int i = 0; i < SIDEBuilderConstants.availableExtensionsDiagrams.length; ++i) {
			if (resource.getFileExtension() != null && resource.getFileExtension().equalsIgnoreCase(SIDEBuilderConstants.availableExtensionsDiagrams[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean isModel(IResource resource) {
		for (int i = 0; i < SIDEBuilderConstants.availableExtensions.length; ++i) {
			if (resource.getFileExtension() != null && resource.getFileExtension().equalsIgnoreCase(SIDEBuilderConstants.availableExtensions[i])) {
				return true;
			}
		}
		return false;
	}

	static public void prepareFolder(IFolder folder) throws CoreException {
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder)
			prepareFolder((IFolder) parent);
		if (!folder.exists())
			folder.create(true, true, null);
	}

	static public void deleteEmptyFolders(IFolder folder) throws CoreException {
		for (IResource r : folder.members()) {
			if (r instanceof IFolder) {
				IFolder f = (IFolder) r;
				deleteEmptyFolders(f);
			}
		}

		if (folder.members().length == 0)
			folder.delete(true, null);
	}

	static public boolean clearFolder(IFolder folder) throws CoreException {
		boolean result = true;
		if (folder.exists()) {
			for (IResource r : folder.members()) {
				if (r instanceof IFolder) {
					IFolder f = (IFolder) r;
					result = result & clearFolder(f);
				} else if (r instanceof IFile) {
					IFile f = (IFile) r;
					if (f.exists())
						if (canClear(f))
							f.delete(true, null);
						else
							result = false;
				}
			}
		}
		return result;
	}

	private static boolean canClear(IFile f) {
		if (!f.getName().startsWith("."))
			return true;
		else {
			int i = f.getName().substring(1).indexOf('.');
			String prefix = f.getName().substring(0, i + 1);
			boolean result = true;
			for (String p : SIDEBuilderConstants.protectedPrefix)
				if (prefix.equals(p))
					result = false;
			return result;
		}
	}

	public static IFile getBackupModel(IPath from) {
		IPath path = from.removeFirstSegments(1);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(from.segment(0));
		IFolder folder = project.getFolder(SIDEBuilderConstants.metadataFolder);
		folder = folder.getFolder(path.removeLastSegments(1));
		path = folder.getFullPath().append(from.lastSegment());
		return project.getFile(path.removeFirstSegments(1));
	}

	public static IFile getFile(IPath path) {
		IPath p = path.removeFirstSegments(1);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(path.segment(0));
		return project.getFile(p);
	}

	public static void cleanReferential(IProject project) throws CoreException {
		IFile referential = project.getFile(SIDEBuilderConstants.referentialFileName);
		referential.refreshLocal(IResource.DEPTH_ZERO, null);
		if (referential.exists()) {
			ModelsDocument doc = null;
			try {
				doc = ModelsDocument.Factory.parse(referential.getContents());
				if (doc != null && doc.getModels() != null) {
					int i = 0;
					List<Integer> toDelete = new ArrayList<Integer>();
					for (Model m : doc.getModels().getModelArray()) {
						String path = m.getPath();
						IFile model = SIDEBuilderUtil.getFile(new Path(path));
						if (!model.exists()) {
							toDelete.add(i);
						}
						i++;
					}

					int deleteElements = 0;
					for (int j : toDelete) {
						doc.getModels().removeModel(j - deleteElements);
						deleteElements++;
					}
					doc.save(referential.getRawLocation().toFile());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isSIDEProject(IProject project) {
		return hasNature(project, SIDENature.NATURE_ID) || hasNature(project, SIDENatureWithBuilder.NATURE_ID);
	}

	public static boolean hasNature(IProject project, String nature) {
		boolean found = false;
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();

			for (int i = 0; i < natures.length; ++i) {
				if (nature.equals(natures[i]))
					found = true;
			}

		} catch (CoreException e) {
		}
		return found;
	}
}
