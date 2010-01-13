package com.bluexml.side.util.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
@Deprecated
public class ConflitResolverHelper {
	String basePath = null;
	String temporyFolderPath = null;
	public static String rawDirectory = "Raw";

	public ConflitResolverHelper(String basePath, String temporyFolderPath) {
		this.basePath = basePath + File.separator + rawDirectory;
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
		IFile finalFile = IFileHelper.getIFile(fullPathFinal);
		return finalFile;
	}

	/**
	 * Method to list files in conflict
	 * 
	 * @param basePath
	 * @param filesTocheck
	 * @param temporyFolderPath
	 * @return files (in final folder)
	 */
	public List<IFile> searchForConflict(Collection<IFile> filesTocheck) {
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

	public void copyToFinalFolder() throws CoreException, IOException {
		File src = IFileHelper.getIFolder(temporyFolderPath).getRawLocation().makeAbsolute().toFile();
		File dest = IFileHelper.getIFolder(basePath).getRawLocation().makeAbsolute().toFile();
		FileHelper.copyFiles(src, dest, false);
	}

}
