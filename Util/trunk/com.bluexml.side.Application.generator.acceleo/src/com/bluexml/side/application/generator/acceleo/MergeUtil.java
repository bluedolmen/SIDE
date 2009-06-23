package com.bluexml.side.application.generator.acceleo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.common.Package;
import com.bluexml.side.util.libs.IFileHelper;

public class MergeUtil {

	public static Map<String, List<IFile>> groupByRootPackage(List<IFile> models) throws IOException {
		Map<String, List<IFile>> gb = new HashMap<String, List<IFile>>();
		for (IFile model : models) {
			Resource modelResource = EResourceUtils.openModel(IFileHelper.convertIRessourceToSystemString(model), null);
			EObject root = modelResource.getContents().get(0);
			if (root instanceof Package) {
				String pname = ((Package) root).getName();
				if (gb.containsKey(pname)) {
					gb.get(pname).add(model);
				} else {
					ArrayList<IFile> l = new ArrayList<IFile>();					
					l.add(model);
					gb.put(pname, l);
				}
			}
		}
		return gb;
	}
}
