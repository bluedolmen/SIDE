package com.bluexml.side.application.ui.action.utils.viewFilter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class SideFileFiter extends ViewerFilter {

	static String EXTENSIONPOINT_ID = "org.eclipse.ui.editors";

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof org.eclipse.core.resources.IFile) {
			IFile file = (IFile) element;
			String fext = file.getFileExtension();
			if (getFilesExtension().contains(fext)) {				
				return true;
			}
		} else {
			return true;
		}
		return false;
	}

	public List<String> getFilesExtension() {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIONPOINT_ID);
		List<String> fexts = new ArrayList<String>();
		for (IConfigurationElement config : contributions) {
			if (config.getName().equalsIgnoreCase("editor")) {
				if (config.getAttribute("id").indexOf("side") != -1) {
					String ext = config.getAttribute("extensions");
					if (!ext.endsWith("di")) {
						// remove all diagram files
						fexts.add(ext);
					}
				}
			}
		}
		return fexts;
	}
}
