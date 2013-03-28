/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * 
 */
package com.bluexml.side.integration.eclipse.builder;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.sideLabs.referential.references.Model;
import org.sideLabs.referential.references.ModelsDocument;

class SIDEDeltaVisitor implements IResourceDeltaVisitor {
	/**
	 * 
	 */
	private final SIDEBuilderChecker checker;

	/**
	 * @param sideBuilder
	 */
	SIDEDeltaVisitor(SIDEBuilderChecker checker) {
		this.checker = checker;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse
	 * .core.resources.IResourceDelta)
	 */
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource resource = delta.getResource();
		String previousName = resource.getFullPath().toString();
		if (delta.getMovedFromPath() != null) {
			previousName = delta.getMovedFromPath().toString();
		}
		try {
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				// handle added resource
				System.out.println("SIDEDeltaVisitor.visit() ADDED :" + resource);
				checker.checkModel(resource, previousName);

				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				System.out.println("SIDEDeltaVisitor.visit() REMOVE :" + resource);
				//Delete backup model
				if (resource instanceof IFile) {
					IFile file = (IFile) resource;
					IFile backupModel = SIDEBuilderUtil.getBackupModel(file);
					if (backupModel.exists()) {
						backupModel.delete(true, null);
					}
				}

				String path = resource.getFullPath().toString();
				ModelsDocument doc = null;
				try {
					IProject p = resource.getProject();
					IResource r = p.findMember(SIDEBuilderConstants.referentialFileName);
					if (r != null && r instanceof IFile && r.exists()) {
						IFile referential = (IFile) r;
						doc = ModelsDocument.Factory.parse(referential.getContents());
						if (doc.getModels() != null) {
							int i = 0;
							boolean finded = false;
							for (Model m : doc.getModels().getModelArray()) {
								if (m.getPath().equals(path)) {
									finded = true;
								} else if (!finded) {
									i++;
								}
							}
							if (finded) {
								doc.getModels().removeModel(i);
							}

							//Save the referential
							SIDEBuilderUtil.prepareFolder((IFolder) referential.getParent());
							File f = referential.getRawLocation().toFile();
							if (!f.exists()) {
								f.createNewFile();
							}
							doc.save(f);
						}
					}
				} catch (Exception e) {
					//Nothing to do
				}
				break;
			case IResourceDelta.CHANGED:
				System.out.println("SIDEDeltaVisitor.visit() CHANGED :" + resource);
				// handle changed resource
				checker.checkModel(resource, previousName);
				break;
			}
		} catch (Exception e1) {
			throw new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error whill execute SIDE builder", e1));
		}
		//return true to continue visiting children.
		return true;
	}
}
