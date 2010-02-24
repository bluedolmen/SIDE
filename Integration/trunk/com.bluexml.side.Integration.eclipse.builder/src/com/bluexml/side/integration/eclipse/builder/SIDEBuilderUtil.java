package com.bluexml.side.integration.eclipse.builder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.sideLabs.referential.references.Model;
import org.sideLabs.referential.references.ModelsDocument;

public class SIDEBuilderUtil {
	
	static public IFile getBackupModel(IFile file) {
		IPath path = file.getFullPath().removeFirstSegments(1);
		IFolder folder = file.getProject().getFolder(SIDEBuilderConstants.metadataFolder);
		folder = folder.getFolder(path.removeLastSegments(1));
		path = folder.getFullPath().append(file.getName());	
		return file.getProject().getFile(path.removeFirstSegments(1));
	}
	
	static public void prepareFolder(IFolder folder) throws CoreException
	{
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder)
			prepareFolder((IFolder) parent);
		if (!folder.exists())
			folder.create(true, true, null);
	}

	static public void deleteEmptyFolders(IFolder folder) throws CoreException
	{
		for (IResource r : folder.members()) {
			if (r instanceof IFolder) {
				IFolder f = (IFolder) r;
				deleteEmptyFolders(f);
			}
		}
		
		if (folder.members().length == 0)
			folder.delete(true, null);
	}
	
	static public boolean clearFolder(IFolder folder) throws CoreException
	{
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
			String prefix = f.getName().substring(0, i+1);
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
						doc.getModels().removeModel(j-deleteElements);
						deleteElements++;
					}
					doc.save(referential.getRawLocation().toFile());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
