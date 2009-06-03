package com.bluexml.side.util.libs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

public class IFileHelper {

	public static File getFileFromWorkspace(String path) {
		IResource ir = getIFile(path);
		if (ir == null) {
			IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			return new File(myWorkspaceRoot.getRawLocation().makeAbsolute().toFile().getAbsolutePath() + File.separator + path);
		}
		return convertIRessourceToFile(ir);
	}

	public static IFile getIFile(String path) {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = myWorkspaceRoot.getFile(new Path(path));
		if (file.exists() || file.getRawLocation().makeAbsolute().toFile().exists()) {
			return file;
		}
		return null;
	}

	public static IFolder getIFolder(String path) {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = myWorkspaceRoot.getFolder(new Path(path));
		/*
		if (folder.exists() || folder.getRawLocation().makeAbsolute().toFile().exists()) {
			return folder;
		}
		*/
		return folder;
	}

	public static File convertIRessourceToFile(IResource ir) {
		return ir.getRawLocation().makeAbsolute().toFile();
	}
	
	public static String convertIRessourceToSystemString(IResource ir) {
		return ir.getRawLocation().makeAbsolute().toOSString();
	}
	
	public static void deleteFolder(String ressource) throws CoreException {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = myWorkspaceRoot.getFolder(new Path(ressource));
		folder.delete(true, null);
	}
	
	/**
	 * Create a folder in the active workspace or return an already created folder.
	 * @param ressource
	 * @return
	 * @throws CoreException
	 */
	public static IFolder createFolder(String ressource) throws CoreException {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = myWorkspaceRoot.getFolder(new Path(ressource));
		if (!folder.exists()) {
			folder.create(false, true, null);
		}
		return folder;
	}
	
	
	public static String getSystemFolderPath(String iFilePath) {
		IFolder ff = IFileHelper.getIFolder(iFilePath);
		return ff.getRawLocation().makeAbsolute().toOSString();
	}
	
	public static String getSystemFilePath(String iFilePath) {
		IFile ff = IFileHelper.getIFile(iFilePath);
		return ff.getRawLocation().makeAbsolute().toOSString();
	}
	
	/**
	 * Return the file for resource given.
	 * @param iresource
	 * @return
	 */
	public static File getFile(IResource iresource) {
		return iresource.getLocation().makeAbsolute().toFile();
	}
	
	/**
	 * Return all files for a given folder. Iterate over sub folder too (folder aren't added)
	 * @param folder
	 * @return
	 * @throws Exception
	 */
	public static List<IFile> getAllFiles(IFolder folder) throws Exception {
		List<IFile> results = new ArrayList<IFile>();
		for (IResource r : folder.members()) {
			if (r instanceof IFile) {
				results.add((IFile)r);
			} else {
				results.addAll(getAllFiles((IFolder)r));
			}
		}
		return results;
	}
}
