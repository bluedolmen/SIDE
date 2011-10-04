package com.bluexml.side.integration.eclipse.builder;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.sideLabs.referential.references.Model;
import org.sideLabs.referential.references.ModelsDocument;
import org.sideLabs.referential.references.Reference;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.util.antrunner.AntFileGeneratorAction;

public class SIDEBuilderReferencies {
	public static void computeReferential(IFile file) {
		//Create XML documents
		ModelsDocument doc = null;
		try {
			IProject p = file.getProject();
			IResource r = p.findMember(SIDEBuilderConstants.referentialFileName);
			if (r != null && r instanceof IFile && r.exists()) {
				IFile referential = (IFile) r;
				referential.refreshLocal(0, null);
				doc = ModelsDocument.Factory.parse(referential.getContents());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (doc == null) {
			doc = ModelsDocument.Factory.newInstance();
		}

		//Search the good node
		Map<String, Model> presentModels = new HashMap<String, Model>();
		if (doc.getModels() != null) {
			for (Model m : doc.getModels().getModelArray()) {
				presentModels.put(m.getPath(), m);
			}
		} else {
			doc.addNewModels();
		}

		try {
			//getParser().parse(file.getContents(), reporter);
			Resource r = EResourceUtils.openModel(file.getLocation().toString(), new HashMap<Object, Object>());
			if (r instanceof XMIResource) {
				XMIResource xmi = (XMIResource) r;

				//Specific case with application models
				if (file.getName().endsWith(".application")) {
					EObject o = xmi.getContents().get(0);
					for (EObject eobj : o.eContents()) {
						if (eobj.eClass().getName().equalsIgnoreCase("Model")) {
							Method method = eobj.getClass().getMethod("getFile", new Class[0]);
							String path = method.invoke(eobj, new Object[0]).toString();
							IFile referencedModel = SIDEBuilderUtil.getFile(new Path(path));
							if (referencedModel.exists()) {
								Model mod;
								if (presentModels.containsKey(referencedModel.getFullPath().toString())) {
									mod = presentModels.get(referencedModel.getFullPath().toString());
								} else {
									mod = doc.getModels().addNewModel();
									mod.setPath(referencedModel.getFullPath().toString());
									presentModels.put(referencedModel.getFullPath().toString(), mod);
								}

								Reference ref = null;
								for (Reference ref2 : mod.getReferencedByArray()) {
									String source = EcoreUtil.getURI(eobj).fragment();
									if (ref2.getModel().equals(file.getFullPath().toString()) && ref2.getUuid().equals(source)) {
										ref = ref2;
									}
								}

								if (ref == null) {
									ref = mod.addNewReferencedBy();
									ref.setModel(file.getFullPath().toString());
									ref.setUuid(EcoreUtil.getURI(eobj).fragment());
								}
							} else {
								MarkerHelper.addMarker(file, "The model " + referencedModel.getFullPath().toString() + " does not exist.", -1, IMarker.SEVERITY_ERROR);
							}
						}
					}
					// may execute the build.xml generation but only if one unique application model exist

					// count sibling application model
					IResource[] childs = file.getParent().members();
					int c = 0;
					for (IResource iResource : childs) {
						if (iResource.getFileExtension().equals("application")) {
							c++;
						}
					}
					if (c == 1) {
						// only one application file exist
						System.out.println("SIDE Builder : update application/build.xml");
					} else {
						// warn before generate
						System.err.println("SIDE Builder : Warning application/build.xml updated from " + file.getLocation().toOSString());
					}
					// maybe execute in a runner

					AntFileGeneratorAction.generate(file);
				}

				Map<EObject, Collection<Setting>> m = EcoreUtil.ProxyCrossReferencer.find(xmi);
				for (EObject o : m.keySet()) {
					String path = EcoreUtil.getURI(o).toFileString();
					IResource referencedModel = null;

					IProject proj = file.getProject();
					referencedModel = proj.findMember(path);

					if (referencedModel == null) {
						IPath ipath = new Path(path);
						//ipath = ipath.removeFirstSegments(Platform.getLocation().segmentCount());
						String projectName = ipath.segment(0);
						if (projectName.equals(proj.getName())) {
							ipath = ipath.removeFirstSegments(1);
							referencedModel = proj.findMember(ipath);
						}
					}

					//Try to search model using the absolute path
					if (referencedModel == null) {
						IPath ipath = new Path(path);
						if (ipath.segmentCount() >= Platform.getLocation().segmentCount()) {
							ipath = ipath.removeFirstSegments(Platform.getLocation().segmentCount());
							String projectName = ipath.segment(0);
							if (projectName.equals(proj.getName())) {
								ipath = ipath.removeFirstSegments(1);
								referencedModel = proj.findMember(ipath);
							}
						}
					}

					if (referencedModel != null) {
						Model mod;
						if (presentModels.containsKey(referencedModel.getFullPath().toString())) {
							mod = presentModels.get(referencedModel.getFullPath().toString());
						} else {
							mod = doc.getModels().addNewModel();
							mod.setPath(referencedModel.getFullPath().toString());
							presentModels.put(referencedModel.getFullPath().toString(), mod);
						}

						Reference ref = null;
						for (Reference ref2 : mod.getReferencedByArray()) {
							String target = EcoreUtil.getURI(o).fragment();
							Setting setting = (Setting) m.get(o).toArray()[0];
							String source = EcoreUtil.getURI(setting.getEObject()).fragment();
							if (ref2.getModel().equals(file.getFullPath().toString()) && ref2.getUuid().equals(target) && ref2.getSource().equals(source)) {
								ref = ref2;
							}
						}

						if (ref == null) {
							ref = mod.addNewReferencedBy();
							ref.setModel(file.getFullPath().toString());
							ref.setUuid(EcoreUtil.getURI(o).fragment());
							Setting setting = (Setting) m.get(o).toArray()[0];
							ref.setSource(EcoreUtil.getURI(setting.getEObject()).fragment());
						}
					} else {
						IPath projectPath = Platform.getLocation().append(proj.getName());
						if (path.startsWith(projectPath.toString())) {
							String name = (new Path(path)).removeFirstSegments(projectPath.segmentCount() - 1).toString();
							MarkerHelper.addMarker(file, "The model " + name + " does not exist.", -1, IMarker.SEVERITY_ERROR);
						} else {
							MarkerHelper.addMarker(file, "The model " + path + " is not managed because it don't exists in this project.", -1, IMarker.SEVERITY_WARNING);
						}
					}

				}
				//Save the referential
				IProject p = file.getProject();
				IFile referential = p.getFile(SIDEBuilderConstants.referentialFileName);
				SIDEBuilderUtil.prepareFolder((IFolder) referential.getParent());
				File f = referential.getRawLocation().toFile();
				if (!f.exists()) {
					f.createNewFile();
				}
				doc.save(f);
			}
		} catch (Exception e1) {
			//Nothing to do
			e1.printStackTrace();
		}
	}
}
