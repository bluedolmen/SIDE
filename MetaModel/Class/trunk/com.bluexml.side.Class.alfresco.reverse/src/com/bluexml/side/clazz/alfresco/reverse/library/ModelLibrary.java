package com.bluexml.side.clazz.alfresco.reverse.library;

import java.io.File;
import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.eclipse.EclipseUtils;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public class ModelLibrary {

	private static final String LIBRARY_PATH = "/com/bluexml/side/clazz/alfresco/reverse/library/";

	public enum Libraries {
		LIBRARY_ALFRESCO_34D_CE("Alfresco 3.4.d CE", "Model-library-Alfresco-3.4.d-CE", "alfresco34d.zip"), LIBRARY_ALFRESCO_33R2_CE("Alfresco 3.2r2 CE", "Model-library-Alfresco-3.2r2-CE", "alfresco32r2.zip");
		String label = "";
		String projectId = "";
		String archiveName = "";

		private Libraries(String label, String projectId, String archiveName) {
			this.label = label;
			this.projectId = projectId;
			this.archiveName = archiveName;
		}

		@Override
		public String toString() {
			return this.label;
		}

		public static Libraries getLibFromLabel(String label) {
			Libraries lib = null;
			Libraries[] values = Libraries.values();
			for (Libraries libraries : values) {
				if (libraries.label.equals(label)) {
					lib = libraries;
				}
			}
			return lib;
		}

		/**
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * @return the projectId
		 */
		public String getProjectId() {
			return projectId;
		}

		/**
		 * @return the archiveName
		 */
		public String getArchiveName() {
			return archiveName;
		}
		
		
	}

	public static IProject importLibrary(String libraryId) throws Exception {
		Libraries lib = Libraries.getLibFromLabel(libraryId);
		String archiveName = lib.archiveName;
		String projectId = lib.projectId;
		String root = LIBRARY_PATH + archiveName;
		URI workspace = ResourcesPlugin.getWorkspace().getRoot().getLocationURI();
		File wkspaceFile = new File(workspace);
		File projectFile = new File(wkspaceFile, projectId);

		boolean exists = projectFile.exists();
		System.out.println(projectFile);
		System.out.println("exists ? " + exists);

		if (!exists) {
			// extract
			System.out.println("extract library into current workspace");
			File tmpZip = File.createTempFile("side_lib_" + archiveName, ".zip"); //$NON-NLS-1$ //$NON-NLS-2$
			tmpZip.deleteOnExit();
			FileHelper.writeStreamInFile(tmpZip, ModelLibrary.class.getResourceAsStream(root));
			System.out.println("file to unzip :" + tmpZip + ":" + tmpZip.length() + " b"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			TrueZipHelper tzh = new TrueZipHelper("zip"); //$NON-NLS-1$
			tzh.copyFiles(tmpZip, wkspaceFile, true);
		}

		return EclipseUtils.importEclipseProject(projectFile);

	}
}
