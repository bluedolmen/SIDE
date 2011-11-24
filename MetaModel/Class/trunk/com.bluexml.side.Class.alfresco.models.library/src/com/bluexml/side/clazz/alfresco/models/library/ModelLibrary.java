package com.bluexml.side.clazz.alfresco.models.library;

import java.io.File;
import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.eclipse.EclipseUtils;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public class ModelLibrary {

	private static final String LIBRARY_PATH = "/com/bluexml/side/clazz/alfresco/models/library/";

	public enum Libraries {
		ALFRESCO_34D_CE("Alfresco 3.4.d CE", "Model-library-Alfresco-3.4.d-CE", "alfresco34d.zip"), ALFRESCO_32R2_CE("Alfresco 3.2r2 CE", "Model-library-Alfresco-3.2r2-CE", "alfresco32r2.zip");
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

	
}
