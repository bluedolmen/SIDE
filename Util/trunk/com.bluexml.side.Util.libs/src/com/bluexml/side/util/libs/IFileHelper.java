package com.bluexml.side.util.libs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class IFileHelper {

	/**
	 * Return the IFile with the with the given project relative path.
	 *
	 * @param path
	 * @return
	 */
	public static IFile getIFile(String path) {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = myWorkspaceRoot.getFile(new Path(path));
//		if (file.exists() || file.getRawLocation().makeAbsolute().toFile().exists()) {
//			return file;
//		}
		return file;
	}

	/**
	 * Try to convert a file to IFile. If not found return null.
	 *
	 * @param toConvert
	 * @return
	 */
	public static IFile getIFile(File toConvert) {
		IFile result = null;
		ResourcesPlugin.getWorkspace().getRoot().findContainersForLocationURI(toConvert.toURI());
		IFile[] results = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(toConvert.toURI());
		if (results.length == 1) {
			result = results[0];
		}
		return result;
	}

	public static IFolder getIFolder(File toConvert) {
		IContainer result = null;
		IContainer[] results = ResourcesPlugin.getWorkspace().getRoot().findContainersForLocationURI(toConvert.toURI());

		if (results.length == 1) {
			result = results[0];
		}
		return (IFolder) result;

	}

	/**
	 * Return the IFolder with the with the given project relative path.
	 *
	 * @param path
	 * @return
	 */
	public static IFolder getIFolder(String path) {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = myWorkspaceRoot.getFolder(new Path(path));
		return folder;
	}

	public static File convertIRessourceToFile(IResource ir) {
		return ir.getRawLocation().makeAbsolute().toFile();
	}

	public static String convertIRessourceToSystemString(IResource ir) {
		return ir.getRawLocation().makeAbsolute().toOSString();
	}

	/**
	 * Delete the folder pointed with the given project relative path.
	 *
	 * @param ressource
	 * @throws CoreException
	 */
	public static void deleteFolder(String ressource) throws CoreException {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = myWorkspaceRoot.getFolder(new Path(ressource));
		deleteResource(folder);
	}

	/**
	 * Delete the given resource if exists.
	 *
	 * @param res
	 * @throws CoreException
	 */
	public static void deleteResource(IResource res) throws CoreException {
		if (res.exists()) {
			res.delete(true, null);
		}
	}

	/**
	 * Delete the content of the given folder
	 *
	 * @param folder
	 * @throws CoreException
	 */
	public static void deleteFolderContent(IFolder folder) throws CoreException {
		if (folder.exists()) {
			for (IResource res : folder.members()) {
				IFileHelper.deleteResource(res);
			}
		}
	}

	/**
	 * Delete the file pointed with the given project relative path.
	 *
	 * @param ressource
	 * @throws CoreException
	 */
	public static void deleteFile(String ressource) throws CoreException {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IResource res = myWorkspaceRoot.getFile(new Path(ressource));
		deleteResource(res);
	}

	/**
	 * Create a folder (with given project relative path) in the active
	 * workspace or return an already created folder. Will create sub folder if
	 * not found.
	 *
	 * @param ressource
	 * @return
	 * @throws CoreException
	 */
	public static IFolder createFolder(String ressource) throws CoreException {
		Path p = new Path(ressource);
		if (p.segmentCount() > 1) {
			IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			IFolder folder = myWorkspaceRoot.getFolder(p);
			if (!folder.exists()) {
				if (folder.getFullPath().segmentCount() > 1) {
					String parentPath = folder.getFullPath().removeLastSegments(1).toOSString();
					
					Path parentPathP = new Path(parentPath);
					IContainer container;
					if (parentPathP.segmentCount() > 1) {
						container = IFileHelper.getIFolder(parentPath);
						if (!container.exists()) {
							IFileHelper.createFolder(parentPath);
						}
					}
				}
				folder.create(true, true, null);
			}
			return folder;
		} else
			//It's a project
			return null;
	}

	/**
	 * Create a file in the given folder
	 *
	 * @param folder
	 * @param fileName
	 * @throws CoreException
	 */
	public static IFile createFile(IFolder folder, String fileName) throws CoreException {
		IFile file = null;
		if (folder.exists()) {
			IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			file = myWorkspaceRoot.getFile(new Path(folder.getFullPath() + System.getProperty("file.separator") + fileName));
			if (!file.exists()) {
				file.create(null, false, null);
			}
		}
		return file;
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
	 *
	 * @param iresource
	 * @return
	 */
	public static File getFile(IResource iresource) {
		return iresource.getLocation().makeAbsolute().toFile();
	}

	/**
	 * Return all files for a given folder. Iterate over sub folder too (folder
	 * aren't added)
	 *
	 * @param folder
	 * @return
	 * @throws Exception
	 */
	public static List<IFile> getAllFiles(IFolder folder) throws Exception {
		return IFileHelper.getAllFiles((IContainer) folder);
	}

	public static List<IFile> getAllFiles(IContainer folder) throws Exception {
		List<IFile> results = new ArrayList<IFile>();
		if (folder.exists())
			for (IResource r : folder.members()) {
				if (r instanceof IFile) {
					results.add((IFile) r);
				} else {
					results.addAll(getAllFiles((IFolder) r));
				}
			}
		return results;
	}

	/**
	 * Return all files for the given folder; won't add file in sub folder.
	 *
	 * @param folder
	 * @return
	 * @throws Exception
	 */
	public static List<IFile> getAllFilesForFolder(IFolder folder) throws Exception {
		List<IFile> results = new ArrayList<IFile>();
		for (IResource r : folder.members()) {
			if (r instanceof IFile) {
				results.add((IFile) r);
			}
		}
		return results;
	}

	/**
	 * Return all files for the given folder; won't add file in sub folder.
	 *
	 * @param folder
	 * @return
	 * @throws Exception
	 */
	public static List<IFolder> getAllFolderForFolder(IFolder folder) throws Exception {
		List<IFolder> results = new ArrayList<IFolder>();
		for (IResource r : folder.members()) {
			if (r instanceof IFolder) {
				results.add((IFolder) r);
			}
		}
		return results;
	}

	/**
	 * Refresh the given folder
	 *
	 * @param folder
	 * @throws CoreException
	 */
	public static void refreshFolder(IFolder folder) throws CoreException {
		if (folder.exists()) {
			folder.refreshLocal(IResource.DEPTH_INFINITE, null);
		}
	}

	/**
	 * Refresh the given folder using his path
	 *
	 * @param folderPath
	 * @throws CoreException
	 */
	public static void refreshFolder(String folderPath) throws CoreException {
		refreshFolder(getIFolder(folderPath));
	}

	/**
	 * Refresh the given folder
	 * @param folderPath
	 * @throws CoreException
	 */
	public static void refreshFolder(File folderPath) throws CoreException {
		refreshFolder(getIFolder(folderPath));
	}

	/**
	 * Move the given file to the given folder
	 *
	 * @param file
	 * @param dest
	 * @throws CoreException
	 */
	public static void moveFile(IFile file, IFolder dest, boolean eraseIfExist) throws CoreException {
		boolean doWork = true;
		IPath p = dest.getFullPath().append(file.getName());
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile destFile = myWorkspaceRoot.getFile(p);
		if (destFile.exists()) {
			if (!eraseIfExist) {
				doWork = false;
			} else {
				IFileHelper.deleteFile(destFile.getFullPath().toOSString());
			}
		}
		if (doWork) {
			file.move(p, true, null);
		}
	}

	public static IFile getIFile(IPath p) {
		return ResourcesPlugin.getWorkspace().getRoot().getFile(p);
	}
}
