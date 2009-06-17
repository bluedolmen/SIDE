package com.bluexml.side.requirements.transformation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.requirements.RequirementsPackage;

public class SIDE_XMIResource extends XMIResourceImpl {

	public SIDE_XMIResource(URI uri) {
		super(uri);
	}

	@Override
	protected boolean useUUIDs() {
		return true;
	}

	public static IFile export(IFile model, String newInputModelName) {
		IFile newInputModelFile = null;
		try {
			if (model.exists()) {
				IPath p = new Path(newInputModelName);

				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put(RequirementsPackage.eNS_URI, RequirementsPackage.eINSTANCE);
				map.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION,Boolean.TRUE);
				Resource r = EResourceUtils.openModel(model.getRawLocation().toFile()
						.getAbsolutePath(),map);

				EObject o = null;
				if (r.getContents().size() > 0)
					o = r.getContents().get(0);

				if (o != null) {
					URI uri = URI.createFileURI(newInputModelName);
					SIDE_XMIResource resource2 = new SIDE_XMIResource(uri);
					resource2.getContents().add(o);
					resource2.save(map);
					newInputModelFile = ResourcesPlugin.getWorkspace().getRoot().getFile(p);
				}
			}
		} catch (Exception e) {
			// Nothing
			e.printStackTrace();
		}

		return newInputModelFile;
	}

}
