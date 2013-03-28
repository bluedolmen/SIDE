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
package com.bluexml.side.integration.cleanEclipse.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {
	/**
	 * This function will copy files or directories from one location to
	 * another. note that the source and the destination must be mutually
	 * exclusive. This function can not be used to copy a directory to a sub
	 * directory of itself. The function will also have problems if the
	 * destination files already exist.
	 * 
	 * @param src
	 *            -- A File object that represents the source for the copy
	 * @param dest
	 *            -- A File object that represents the destination for the copy.
	 * @throws IOException
	 *             if unable to copy.
	 */
	public static void copyFiles(File src, File dest, boolean override)
			throws IOException {
		// Check to ensure that the source is valid...
		if (!src.exists()) {
			throw new IOException("copyFiles: Can not find source: "
					+ src.getAbsolutePath() + ".");
		} else if (!src.canRead()) { // check to ensure we have rights to the
			// source...
			throw new IOException("copyFiles: No right to source: "
					+ src.getAbsolutePath() + ".");
		}
		// is this a directory copy?
		if (src.isDirectory()) {
			if (!src.getName().startsWith(".svn")) {
				if (!dest.exists()) { // does the destination already exist?
					// if not we need to make it exist if possible (note this is
					// mkdirs not mkdir)
					if (!dest.mkdirs()) {
						throw new IOException(
								"copyFiles: Could not create direcotry: "
										+ dest.getAbsolutePath() + ".");
					}
				}
				// get a listing of files...
				String list[] = src.list();
				// copy all the files in the list.
				for (int i = 0; i < list.length; i++) {
					File dest1 = new File(dest, list[i]);
					File src1 = new File(src, list[i]);
					copyFiles(src1, dest1, override);
				}
			}
		} else {
			// This was not a directory, so lets just copy the file
			FileInputStream fin = null;
			FileOutputStream fout = null;
			byte[] buffer = new byte[4096]; // Buffer 4K at a time (you can
			// change this).
			int bytesRead;
			try {
				// open the files for input and output
				fin = new FileInputStream(src);

				// dest can be a file or a folder
				if (dest.isDirectory()) {
					// copy the file as child of dest
					// compute the new filePath
					dest = new File(dest.getAbsolutePath() + File.separator
							+ src.getName());
				}

				if (!dest.exists() || override) {
					// create parents before create the file if needed
					dest.getParentFile().mkdirs();
					// dest do not exist or override is allowed
					// while bytesRead indicates a successful read, lets
					// write...
					fout = new FileOutputStream(dest);
					while ((bytesRead = fin.read(buffer)) >= 0) {
						fout.write(buffer, 0, bytesRead);
					}
				}

			} catch (IOException e) { // Error copying file...
				IOException wrapper = new IOException(
						"copyFiles: Unable to copy file: "
								+ src.getAbsolutePath() + "to"
								+ dest.getAbsolutePath() + ".");
				wrapper.initCause(e);
				wrapper.setStackTrace(e.getStackTrace());
				throw wrapper;
			} finally { // Ensure that the files are closed (if they were open).
				if (fin != null) {
					fin.close();
				}
				if (fout != null) {
					fout.close();
				}
			}
		}
	}

	public static boolean deleteFile(File f) {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			for (int i = 0; i < fl.length; i++) {
				deleteFile(fl[i]);
			}
		}
		if (f.exists()) {
			return f.delete();
		}
		return false;
	}
}
