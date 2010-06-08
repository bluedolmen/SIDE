package com.bluexml.side.util.feedback.management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.bluexml.side.util.feedback.FeedbackActivator;
import com.bluexml.side.util.feedback.utils.FeedbackUtils;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.httpRequest.ClientHttpRequest;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.ArchiveException;
import de.schlichtherle.io.File;

public class FeedbackSender {

	public static void doSend() {
		if (FeedbackActivator.getFeedbackTermOfUseAccepted()) {
			Job job = new Job("Sending SIDE feedback data.") {
				protected IStatus run(IProgressMonitor monitor) {
			           if (FeedbackSender.send())
			        	   return Status.OK_STATUS;
			           else
			        	   return Status.CANCEL_STATUS;
			        }
			};
			job.setPriority(Job.SHORT);
			job.schedule();
			// Update last update date
			Date nowDate = new Date();
			FeedbackActivator.getDefault().getPreferenceStore().setValue(FeedbackActivator.LAST_UPDATE_DATE, nowDate.getTime());
		}
	}

	/**
	 * Send all *-log.xml files to blueXml server.
	 */
	protected static boolean send() {
		File zipFile = null;
		boolean noError = true;
		// Zip creation
		try {
			zipFile = createZip();
		} catch (ArchiveException e) {
			e.printStackTrace();
			noError = false;
		}

		if (zipFile != null && zipFile.list() != null && zipFile.list().length > 0) {
			// Send it
			try {
				sendFile(zipFile);
				// Remove all files send previously
				removeSendedFiles(zipFile);
			} catch (Exception e) {
				e.printStackTrace();
				noError = false;
			}
		}
		return noError;
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
			File.umount(true);
		} catch (ArchiveException e) {
			e.printStackTrace();
		}

		// Delete with trueZip function doesn't work
		java.io.File testFile = new java.io.File(source,FeedbackActivator.ZIP_FILE_NAME);
		if (testFile.exists()) {
			try{
				testFile.delete();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Send the given file to bluexml server
	 * @param file
	 * @throws IOException
	 */
	private static void sendFile(File file) throws IOException {
		if (file != null) {
			ClientHttpRequest request = new ClientHttpRequest(FeedbackActivator.SERVICE_URL);
			request.setParameter(FeedbackActivator.LOGFILE_ATTRIBUTE_NAME, file);
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
		File zipFile = new File(source, FeedbackActivator.ZIP_FILE_NAME);
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
		File.update();
        return zipFile;
	}
}
