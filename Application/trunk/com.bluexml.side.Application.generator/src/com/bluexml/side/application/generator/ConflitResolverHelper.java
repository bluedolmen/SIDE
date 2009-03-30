package com.bluexml.side.application.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.FileHandler;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import com.bluexml.side.util.libs.*;
public class ConflitResolverHelper {
	String basePath = null;
	String temporyFolderPath = null;

	public ConflitResolverHelper(String basePath, String temporyFolderPath) {
		this.basePath = basePath;
		this.temporyFolderPath = temporyFolderPath;
	}

	public IFile getFinalFilePath(IFile tmpfile) {
		return getFinalFilePath_(basePath, tmpfile, temporyFolderPath);
	}

	public IFile getTempFilePath(IFile file) {
		return getFinalFilePath_(temporyFolderPath, file, basePath);
	}

	private IFile getFinalFilePath_(String basePath, IFile tmpfile, String temporyFolderPath) {
		String fullPathG = tmpfile.getFullPath().toOSString();
		String fullPathFinal_ = fullPathG.substring(temporyFolderPath.length());
		String fullPathFinal = basePath + fullPathFinal_;
		IFile finalFile = getIFile(fullPathFinal);
		return finalFile;
	}

	/**
	 * Method to list files in conflict
	 * @param basePath
	 * @param filesTocheck
	 * @param temporyFolderPath
	 * @return files (in final folder)
	 */
	public List<IFile> searchForConflict(String basePath, Collection<IFile> filesTocheck, String temporyFolderPath) {
		List<IFile> conflict = new ArrayList<IFile>();

		// search for existing files in generated directory
		for (IFile f : filesTocheck) {
			// f is the generated file in the temporary folder
			// test if this file is already created by another generator
			IFile finalFile = getFinalFilePath(f);
			if (finalFile != null) {
				// the file have been already created so we have a conflict
				conflict.add(finalFile);
			}
		}
		return conflict;
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
		if (folder.exists()) {
			return folder;
		}
		return null;
	}
	
	public void copyToFinalFolder() throws CoreException, IOException {
		File src = getIFolder(temporyFolderPath).getRawLocation().makeAbsolute().toFile();
		File dest = getIFolder(basePath).getRawLocation().makeAbsolute().toFile();
		FileHelper.copyFiles(src, dest);
	}
	
	public static void deleteFolder(String ressource) throws CoreException {		
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = myWorkspaceRoot.getFolder(new Path(ressource));
		folder.delete(true, null);
	}
}
