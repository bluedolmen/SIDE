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
package com.bluexml.side.util.libs.zip;

import com.bluexml.side.util.libs.FileHelper;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.DefaultArchiveDetector;
import de.schlichtherle.io.File;
import de.schlichtherle.io.archive.zip.Zip32Driver;

public class TrueZipHelper {
	protected ArchiveDetector archiveDetector;

	public TrueZipHelper() {
		archiveDetector = ArchiveDetector.DEFAULT;
	}

	public TrueZipHelper(String fileExtentions) {
		if (DefaultArchiveDetector.ALL_SUFFIXES.indexOf(fileExtentions) != -1) {
			archiveDetector = new DefaultArchiveDetector(fileExtentions);
		} else {
			archiveDetector = new DefaultArchiveDetector(fileExtentions, new Zip32Driver());

		}
	}

	public boolean deleteArchiveFile(java.io.File f) throws Exception {
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
	 * Note this method do not unpack archives included in the source, just the
	 * source <a>https://truezip.dev.java.net/</a>
	 * 
	 * @param src
	 * @param dest
	 * @param override
	 * @return
	 * @throws Exception
	 */
	public boolean copyFiles(java.io.File src, java.io.File dest, boolean override) throws Exception {
		boolean result = false;
		File srcF = new File(src, archiveDetector);
		File destF = new File(dest);

		if (srcF.isArchive()) {
			result = srcF.archiveCopyAllTo(destF, ArchiveDetector.NULL, ArchiveDetector.NULL);
		} else {
			result = srcF.copyAllTo(destF, ArchiveDetector.NULL, ArchiveDetector.NULL);
		}
		
		// mandatory call see TrueZip doc
		File.update();
		
		if (!result) {
			String message = "Error while extract files";
			System.err.println(message);
			System.err.println("Source:" + srcF);
			System.err.println("Dest:" + destF);			
			throw new Exception(message);
		}


		return result;
	}

	public boolean isDirectory(File f) {
		return f.isDirectory() && !f.isArchive();
	}

	public static File getTzFile(java.io.File f) throws Exception {
		return new File(f, new DefaultArchiveDetector(FileHelper.getFileExt(f), new Zip32Driver()));
	}

	/**
	 * return a TrueZip File managing Zip32 archives with given suffix
	 * 
	 * @param f
	 * @param suffix
	 * @return
	 */
	public static File getTzFile(java.io.File f, String suffix) {
		return new File(f, new DefaultArchiveDetector(suffix, new Zip32Driver()));
	}
}
