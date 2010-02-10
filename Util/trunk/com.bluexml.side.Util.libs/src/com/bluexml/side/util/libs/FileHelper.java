package com.bluexml.side.util.libs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHelper {

	public static final String COMPARE_SAME = "=";
	public static final String COMPARE_ADDED = "+";
	public static final String COMPARE_DELETED = "-";

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
	public static void copyFiles(File src, File dest, boolean override, StringWriter report) throws IOException {
		// Check to ensure that the source is valid...
		if (!src.exists()) {
			throw new IOException("copyFiles: Can not find source: " + src.getAbsolutePath() + ".");
		} else if (!src.canRead()) { // check to ensure we have rights to the
			// source...
			throw new IOException("copyFiles: No right to source: " + src.getAbsolutePath() + ".");
		}
		// is this a directory copy?
		if (src.isDirectory()) {
			if (!dest.exists()) { // does the destination already exist?
				// if not we need to make it exist if possible (note this is
				// mkdirs not mkdir)
				if (!dest.mkdirs()) {
					throw new IOException("copyFiles: Could not create direcotry: " + dest.getAbsolutePath() + ".");
				}
			}
			// get a listing of files...
			String list[] = src.list();
			// copy all the files in the list.
			for (int i = 0; i < list.length; i++) {
				File dest1 = new File(dest, list[i]);
				File src1 = new File(src, list[i]);
				copyFiles(src1, dest1, override, report);
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
					dest = new File(dest.getAbsolutePath() + File.separator + src.getName());
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
				IOException wrapper = new IOException("copyFiles: Unable to copy file: " + src.getAbsolutePath() + "to" + dest.getAbsolutePath() + ".");
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
			// report action to writer
			if (report != null) {
				report.write(dest.toString());
			}
		}
	}

	public static void copyFiles(File src, File dest, boolean override) throws IOException {
		copyFiles(src, dest, override, null);
	}

	public static boolean deleteFile(File f) throws Exception {
		return deleteFile(f, true);
	}

	public static boolean deleteFile(File f, boolean failonError) throws Exception {
		boolean status = true;
		if (f.isDirectory()) {			
			File[] fl = f.listFiles();
			for (int i = 0; i < fl.length; i++) {
				status &= deleteFile(fl[i], failonError);
			}			
		}
		if (f.exists() && f.canWrite()) {
			return f.delete() && status;
		} else if (failonError) {
			throw new Exception("file can't be deleted: "+f);
		}
		return false;
	}

	public static String getFileExt(File f) throws Exception {
		if (f.isFile()) {
			int i = f.getName().lastIndexOf(".");
			return f.getName().substring(i + 1);
		}
		throw new Exception("no file ext for Folder");
	}

	public static List<File> listAll(File folder) {
		List<File> allFiles = new ArrayList<File>();
		if (folder.isFile()) {
			allFiles.add(folder);
		} else {
			for (File f : folder.listFiles()) {
				allFiles.addAll(listAll(f));
			}
		}
		return allFiles;
	}

	public static List<String> listAllAsFilePath(File folder) {
		List<String> allFilesPath = new ArrayList<String>();
		List<File> allFiles = listAll(folder);

		for (File f : allFiles) {
			allFilesPath.add(f.getAbsolutePath());
		}
		return allFilesPath;
	}

	public static Map<String, List<String>> diffFolder(File folder1, File folder2, String filter) {
		Map<String, List<String>> diff = new HashMap<String, List<String>>();

		List<String> allFilesPath1 = makeRelativeTo(listAllAsFilePath(folder1), folder1.getAbsolutePath());
		List<String> allFilesPath2 = makeRelativeTo(listAllAsFilePath(folder2), folder2.getAbsolutePath());

		if (filter.indexOf(COMPARE_SAME) != -1) {
			List<String> commonFiles = new ArrayList<String>(allFilesPath1);
			commonFiles.retainAll(allFilesPath2);
			diff.put(COMPARE_SAME, commonFiles);
		}
		if (filter.indexOf(COMPARE_DELETED) != -1) {
			List<String> onlyIn1 = new ArrayList<String>(allFilesPath1);
			onlyIn1.removeAll(allFilesPath2);
			diff.put(COMPARE_DELETED, onlyIn1);
		}
		if (filter.indexOf(COMPARE_ADDED) != -1) {
			List<String> onlyIn2 = new ArrayList<String>(allFilesPath2);
			onlyIn2.removeAll(allFilesPath1);
			diff.put(COMPARE_ADDED, onlyIn2);
		}

		return diff;
	}

	public static void diffFolder(File folder1, File folder2, Writer log, String filter) {
		Map<String, List<String>> diff = diffFolder(folder1, folder2, filter);
		try {
			log.write("DIFF " + folder1.getAbsolutePath() + " --> " + folder2.getAbsolutePath() + "\n");
			for (Map.Entry<String, List<String>> ent : diff.entrySet()) {
				for (String v : ent.getValue()) {
					log.write(ent.getKey() + " file://" + v + "\n");
				}
			}
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> makeRelativeTo(List<String> l, String root) {
		List<String> newList = new ArrayList<String>();
		for (String s : l) {
			if (s.startsWith(root)) {
				newList.add(s.substring(root.length()));
			}
		}
		return newList;
	}

	public static void writeStreamInFile(File f, InputStream in) throws Exception {
		FileOutputStream fout = null;
		byte[] buffer = new byte[4096]; // Buffer 4K at a time (you can
		// change this).
		int bytesRead;

		fout = new FileOutputStream(f);
		while ((bytesRead = in.read(buffer)) >= 0) {
			fout.write(buffer, 0, bytesRead);
		}
		// InputStream is consumed we close it
		in.close();
	}

	public static void readReplace(File file, String oldPattern, String replPattern) throws Exception {
		String line;

		StringBuffer sb = new StringBuffer();

		FileInputStream fis = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		while ((line = reader.readLine()) != null) {
			line = line.replaceAll(oldPattern, replPattern);
			sb.append(line + "\n");
		}
		reader.close();
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write(sb.toString());
		out.close();

	}

}
