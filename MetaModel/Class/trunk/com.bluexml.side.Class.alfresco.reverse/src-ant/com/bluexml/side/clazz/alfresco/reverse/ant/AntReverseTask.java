package com.bluexml.side.clazz.alfresco.reverse.ant;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FilenameUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.ResourceCollection;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.clazz.alfresco.reverse.reverser.Reverser;
import com.bluexml.side.util.libs.IFileHelper;

public class AntReverseTask extends Task {
	File output = null;
	protected Vector<ResourceCollection> rcs = new Vector<ResourceCollection>();
	private boolean failonerror = true;
	private boolean verbose = false;

	/**
	 * @return the verbose
	 */
	public boolean isVerbose() {
		return verbose;
	}

	/**
	 * @param verbose
	 *            the verbose to set
	 */
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	/**
	 * @return the output
	 */
	public File getOutput() {
		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(File output) {
		this.output = output;
	}

	/**
	 * @return the failonerror
	 */
	public boolean isFailonerror() {
		return failonerror;
	}

	/**
	 * @param failonerror
	 *            the failonerror to set
	 */
	public void setFailonerror(boolean failonerror) {
		this.failonerror = failonerror;
	}

	public void addFileset(FileSet set) {
		add(set);
	}

	/**
	 * Add a collection of files to copy.
	 * 
	 * @param res
	 *            a resource collection to copy.
	 * @since Ant 1.7
	 */
	public void add(ResourceCollection res) {
		rcs.add(res);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {

		Collection<File> alfrescoModels = new ArrayList<File>();
		List<IFile> sideModels = new ArrayList<IFile>();
		// get files
		int msgErr = Project.MSG_ERR;
		for (int i = 0; i < rcs.size(); i++) {
			ResourceCollection rc = (ResourceCollection) rcs.elementAt(i);

			// Step (1) - beware of the ZipFileSet
			if (rc instanceof FileSet && rc.isFilesystemOnly()) {
				FileSet fs = (FileSet) rc;
				DirectoryScanner ds = null;
				try {
					ds = fs.getDirectoryScanner(getProject());
				} catch (BuildException e) {
					if (failonerror || !getMessage(e).endsWith(" not found.")) {
						throw e;
					} else {
						log("Warning: " + getMessage(e), msgErr);
						continue;
					}
				}
				File fromDir = fs.getDir(getProject());
				if (verbose)
					log("FROMDir :" + fromDir);

				String[] srcFiles = ds.getIncludedFiles();
				if (verbose)
					log("srcFiles :");

				for (String string : srcFiles) {

					String fullpath = fromDir + File.separator + string;
					if (verbose)
						log("* " + fullpath);
					File file = new File(fullpath);
					if (file.exists()) {
						if (FilenameUtils.getExtension(file.getName()).toLowerCase().equals("xml")) {
							alfrescoModels.add(file);
						} else if (FilenameUtils.getExtension(file.getName()).toLowerCase().equals("dt")) {
							sideModels.add(IFileHelper.getIFile(file));
						}
					}

				}
				String[] srcDirs = ds.getIncludedDirectories();
				if (verbose)
					log("srcDirs");
				for (String string : srcDirs) {
					if (verbose)
						log("* " + string);
				}
			}
		}

		try {
			Reverser.executeReverse(alfrescoModels, output, sideModels, verbose);
		} catch (Exception e) {
			log(e, msgErr);
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (StackTraceElement stackTraceElement : stackTrace) {
				log(stackTraceElement.toString(), msgErr);
			}
			if (failonerror) {
				throw new BuildException(e);
			}
		}
		log("execute end");
	}

	/**
	 * Handle getMessage() for exceptions.
	 * 
	 * @param ex
	 *            the exception to handle
	 * @return ex.getMessage() if ex.getMessage() is not null
	 *         otherwise return ex.toString()
	 */
	private String getMessage(Exception ex) {
		return ex.getMessage() == null ? ex.toString() : ex.getMessage();
	}
}
