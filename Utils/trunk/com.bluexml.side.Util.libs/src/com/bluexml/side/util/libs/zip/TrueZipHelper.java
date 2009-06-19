package com.bluexml.side.util.libs.zip;

import com.bluexml.side.util.libs.FileHelper;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.DefaultArchiveDetector;
import de.schlichtherle.io.File;

public class TrueZipHelper {
	protected ArchiveDetector archiveDetector;

	public TrueZipHelper() {
		archiveDetector = new DefaultArchiveDetector("zip");
	}

	public TrueZipHelper(String fileExtentions) {
		archiveDetector = new DefaultArchiveDetector(fileExtentions);
	}

	public boolean deleteArchiveFile(java.io.File f) {
		return FileHelper.deleteFile(new File(f, archiveDetector));
	}

	/**
	 * can copies :<br>
	 * <ul>
	 * <li>a file to a folder</li>
	 * <li>a file to an Archive</li>
	 * <li>a folder contents into a folder</li>
	 * <li>a folder contents into an Archive</li>
	 * <li>Archive contents into a folder (unzip)</li>
	 * <li>Archive contents into another Archive</li>
	 * </ul>
	 * Note : Archive contains into Archive of same type (zip included into another zip), will be unzipped too<br>
	 * See TrueZip documentation for more details, and limitation
	 * <a>https://truezip.dev.java.net/</a>
	 * @param src
	 * @param dest
	 * @param override
	 * @return
	 * @throws Exception
	 */
	public boolean copyFiles(java.io.File src, java.io.File dest, boolean override) throws Exception {
		boolean result = false;
		File inputF = new File(src, archiveDetector);
		File destF = new File(dest);
		
		if (inputF.isFile() & destF.isFile()) {
			// copy using a new file name
			FileHelper.copyFiles(src, dest, override);
		} else if (inputF.isFile() & destF.isDirectory()) {
			FileHelper.copyFiles(src, dest, override);
			//throw new Exception("copyFile : File to Folder Not Supported !");
		} else if (inputF.isFile() & destF.isArchive()) {
			inputF.copyTo(destF);
			//throw new Exception("copyFile : File to Archive Not Supported !");
		} else if (inputF.isDirectory() & destF.isDirectory()) {
			FileHelper.copyFiles(src, dest, override);
			//throw new Exception("copyFile : Folder to Folder Not Supported !");
		} else if (inputF.isDirectory() & destF.isArchive()) {
			inputF.copyAllTo(destF);
			//throw new Exception("copyFile : File to Archive Not Supported !");
		} else if (inputF.isArchive() & destF.isDirectory()) {
			inputF.archiveCopyAllTo(destF);
			throw new Exception("copyFile : File to Archive Not Supported !");
		} else if (inputF.isArchive() & destF.isArchive()) {
			String fileExt = FileHelper.getFileExt(dest);
			result = inputF.archiveCopyAllTo(destF, archiveDetector, new DefaultArchiveDetector(fileExt));
		}

		// mandatory call see TrueZip doc
		File.update();
		return result;
	}

}
