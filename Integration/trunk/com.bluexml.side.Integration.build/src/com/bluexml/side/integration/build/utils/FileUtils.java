package com.bluexml.side.integration.build.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	public static void copyDirectory(final File from, final File to)
			throws IOException {
		if (!to.exists()) {
			to.mkdir();
		}
		final File[] inDir = from.listFiles();
		for (int i = 0; i < inDir.length; i++) {
			final File file = inDir[i];
			copy(file, new File(to, file.getName()));
		}
	}

	public static void copyFile(final File from, final File to)
			throws IOException {
		final InputStream inStream = new FileInputStream(from);
		final OutputStream outStream = new FileOutputStream(to);
		copy(inStream, outStream, (int) Math.min(from.length(), 4 * 1024));
		inStream.close();
		outStream.close();
	}

	public static void copy(final File from, final File to) throws IOException {
		if (from.isFile()) {
			copyFile(from, to);
		} else if (from.isDirectory()) {
			copyDirectory(from, to);
		} else {
			throw new FileNotFoundException(from.toString() + " does not exist");
		}
	}

	public static void copy(final InputStream inStream,
			final OutputStream outStream, final int bufferSize)
			throws IOException {
		final byte[] buffer = new byte[bufferSize];
		int nbRead;
		while ((nbRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, nbRead);
		}
	}
	
	public static void recursifDelete(File path) throws IOException {
	       if (!path.exists()) throw new IOException(
	          "File not found '" + path.getAbsolutePath() + "'");
	       if (path.isDirectory()) {
	          File[] children = path.listFiles();
	          for (int i=0; children != null && i<children.length; i++)
	             recursifDelete(children[i]);
	          if (!path.delete()) throw new IOException(
	             "No delete path '" + path.getAbsolutePath() + "'");
	       }
	       else if (!path.delete()) throw new IOException(
	          "No delete file '" + path.getAbsolutePath() + "'");
	    }
	
	/**
	 * Suprimme le fichier donn�
	 * 
	 * @param file
	 */
	public static void deleteFile(String fileToDelete) {

		File file = new File(fileToDelete);
		if(file.exists())
			file.delete();
	}

}
