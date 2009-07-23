package com.bluexml.side.util.feedback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.httpRequest.ClientHttpRequest;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.ArchiveException;
import de.schlichtherle.io.File;

public class FeedbackSender {


	//public static String SERVICE_URL = "http://localhost/upload.php";


	/**
	 * Send all *-log.xml files to blueXml server.
	 */
	public static void send() {
		File zipFile = null;
		// Zip creation
		try {
			zipFile = createZip();
		} catch (ArchiveException e) {
			e.printStackTrace();
		}
		// Send it
		try {
			sendFile(zipFile);
			// Remove all files send previously
			removeSendedFiles(zipFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove xml files and zipFile.
	 * @param zipFile
	 */
	private static void removeSendedFiles(File zipFile) {
		IPath pluginSaveFolder = FeedbackUtils.getFeedbackSaveFolder();
		java.io.File source = pluginSaveFolder.toFile();
		List<java.io.File> fileList = FileHelper.listAll(source);
		for (java.io.File f : fileList) {
			if (f.getName().endsWith(FeedbackUtils.END_FILE_NAME)) {
				f.delete();
			}
		}
		try {
			zipFile.umount();
		} catch (ArchiveException e) {
			e.printStackTrace();
		}
		zipFile.delete();
	}

	/**
	 * Send the given file to bluexml server
	 * @param file
	 * @throws IOException
	 */
	private static void sendFile(File file) throws IOException {
		if (file != null) {
			ClientHttpRequest request = new ClientHttpRequest(Activator.SERVICE_URL);
			request.setParameter("log", file);
			request.post();
		}
	}

	/**
	 * Search in plugin folder in .metadata and zip all files
	 * @throws ArchiveException
	 */
	private static File createZip() throws ArchiveException {
		// Get all log xml files
		IPath pluginSaveFolder = FeedbackUtils.getFeedbackSaveFolder();
		java.io.File source = pluginSaveFolder.toFile();
		List<java.io.File> fileList = FileHelper.listAll(source);

		List<File> logFiles = new ArrayList<File>();
		for (java.io.File f : fileList) {
			if (f.getName().endsWith(FeedbackUtils.END_FILE_NAME)) {
				logFiles.add(new File(f));
			}
		}

		// Zip creation
		File zipFile = new File(source, Activator.ZIP_FILE_NAME);
		if (zipFile.exists()) {
			zipFile.delete();
			zipFile = new File(source, Activator.ZIP_FILE_NAME);
		}
		zipFile.mkdir();

		// Add them to the zip
		for (File f : logFiles) {
			File zipEntry = new File( zipFile, f.getName(), ArchiveDetector.NULL );
			f.copyTo( zipEntry );
			if (zipFile.archiveCopyAllFrom(f)) {
				System.err.println("Copied " + f.getName());
			} else {
				System.err.println("Error in copy " + f.getName());
			}
		}
        File.umount();
        return zipFile;
	}
}
