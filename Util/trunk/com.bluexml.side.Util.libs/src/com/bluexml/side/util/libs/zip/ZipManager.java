package com.bluexml.side.util.libs.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipManager {

	static int BUFFER = 2048;

	public static void zip(File file, File outFile, boolean includeRoot) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
		if (file.isDirectory()) {
			if (includeRoot) {
				zipFolder(file, out, file.getParentFile().getPath());
			} else {
				zipFolder(file, out, file.getPath());
			}
		} else {
			zipFile(file, out);
		}
		cleanUp(out);
	}

	public static void zipFile(File inFolder, File outFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
		zipFile(inFolder, out);
		cleanUp(out);
	}

	private static void zipFile(File inFolder, ZipOutputStream out) throws Exception {

		// System.out.println("Adding: " + files[i]);
		BufferedInputStream in = null;
		byte[] data = new byte[BUFFER];
		in = new BufferedInputStream(new FileInputStream(inFolder.getPath()), BUFFER);

		out.putNextEntry(new ZipEntry(inFolder.getPath())); // write

		int count;
		while ((count = in.read(data, 0, BUFFER)) != -1) {
			out.write(data, 0, count);
		}
		out.closeEntry(); // close each entry
		cleanUp(in);
	}

	public static void zipFolder(File inFolder, File outFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
		zipFolder(inFolder, out, inFolder.getPath());
		cleanUp(out);
	}

	private static ZipOutputStream zipFolder(File inFolder, ZipOutputStream out, String rootPath) {

		try {
			// compress outfile stream

			// writting stream
			BufferedInputStream in = null;

			byte[] data = new byte[BUFFER];
			File files[] = inFolder.listFiles();

			for (int i = 0; i < files.length; i++) {
				File current = files[i];
				if (current.isDirectory()) {
					zipFolder(current, out, rootPath);
				} else {
					// System.out.println("Adding: " + files[i]);
					in = new BufferedInputStream(new FileInputStream(current), BUFFER);

					out.putNextEntry(new ZipEntry(buildZipPath(current, rootPath))); // write
					// data
					// header
					// (name, size, etc)
					int count;
					while ((count = in.read(data, 0, BUFFER)) != -1) {
						out.write(data, 0, count);
					}
					out.closeEntry(); // close each entry
				}
			}
			// cleanUp(out);
			cleanUp(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	private static void cleanUp(InputStream in) throws Exception {
		if (in != null) {
			in.close();
		}
	}

	private static void cleanUp(OutputStream out) throws Exception {
		if (out != null) {
			out.flush();
			out.close();
		}
	}

	private static String buildZipPath(File file, String rootPath) {
		String entryFilePath = file.getPath().replaceAll("\\\\", "/");
		if (rootPath.equals("")) {
			return entryFilePath;
		} else {
			String pathToRemove = rootPath.replaceAll("\\\\", "/") + "/";
			entryFilePath = entryFilePath.replaceFirst(pathToRemove, "");
			return entryFilePath;
		}
	}

	public static void main(String[] args) {
		try {
			File files = new File(args[1]);
			File zip = new File(args[0]);

			// Zip
			ZipManager.zip(files, zip, false);

			// unzip

			File dest = new File(files.getParentFile(), "unzipped");
			dest.mkdirs();
			ZipManager.unzip(zip, dest, true, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void unzip(File file, File destinationFolder, boolean override, boolean verbose) throws ZipException, IOException {
		if (verbose) {
			System.out.println("zipFile :" + file);
			System.out.println("destinationFolder :" + destinationFolder);
		}
		destinationFolder.mkdirs();
		if (file.exists() && destinationFolder.exists() && destinationFolder.isDirectory()) {

			int BUFFER = 2048;

			ZipFile zip = new ZipFile(file);

			Enumeration<? extends ZipEntry> zipFileEntries = zip.entries();

			// Process each entry
			while (zipFileEntries.hasMoreElements()) {
				// grab a zip file entry
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

				// beware of entry created with windows file separator ??
				String currentEntry = entry.getName().replaceAll("\\\\", "/");

				File destFile = new File(destinationFolder, currentEntry);
				File destinationParent = destFile.getParentFile();

				// create the parent directory structure if needed
				boolean mkd = destinationParent.mkdirs();

				if (!entry.isDirectory() && (!destFile.exists() || (destFile.exists() && override))) {
					if (verbose) {
						System.out.println("unzip : " + entry.getName() + " to " + destFile);
					}
					BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}

			}

		} else {
			throw new IOException("some files do not exists");
		}
	}

}