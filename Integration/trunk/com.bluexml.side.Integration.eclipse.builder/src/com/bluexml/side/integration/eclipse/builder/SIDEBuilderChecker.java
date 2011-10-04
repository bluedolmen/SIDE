package com.bluexml.side.integration.eclipse.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.sideLabs.referential.references.Model;
import org.sideLabs.referential.references.ModelsDocument;
import org.sideLabs.referential.references.Reference;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.integration.eclipse.builder.incremental.DeltaCollector;
import com.bluexml.side.util.libs.IFileHelper;

public class SIDEBuilderChecker {
	private List<IFile> checkedFiles = new ArrayList<IFile>();
	private List<IFile> valideRessource = new ArrayList<IFile>();

	/**
	 * @return the valideRessource
	 */
	public List<IFile> getValideRessource() {
		return valideRessource;
	}

	public void checkModel(IResource resource, String previousName) throws Exception {
//		System.out.println("SIDEBuilder.checkModel() " + resource);
		boolean validFile = SIDEBuilderUtil.isModel(resource);
		boolean diagramFile = SIDEBuilderUtil.isDiagram(resource);

		if (diagramFile) {
			IPath p = new Path(resource.getFullPath().removeLastSegments(1).toString());
			p = p.append(resource.getFullPath().lastSegment().substring(0, resource.getFullPath().lastSegment().length() - 2));
			p = p.removeFirstSegments(1);
			IFile f = resource.getProject().getFile(p);
			if (f != null && f.exists()) {
				System.out.println("SIDEBuilderChecker.checkModel() diagram " + f);
				check(f);
			}
		} else if (resource instanceof IFile && validFile) {
			System.out.println("SIDEBuilder.checkModel() valide :" + resource);
			IFile file = (IFile) resource;

			MarkerHelper.deleteMarkers(file);

			if (!file.getFullPath().segment(1).equals(SIDEBuilderConstants.metadataFolder)) {
				System.out.println("SIDEBuilderChecker.checkModel() before check" + file);
				valideRessource.add(file);
				check(file);

				checkLinkedElementsSimple(file, previousName);

				SIDEBuilderReferencies.computeReferential(file);

			}
		} else {
//			System.out.println("SIDEBuilder.checkModel() invalide :" + resource);
		}
	}

	public List<IFile> getModels() {
		return valideRessource;
	}

	public void backupModels() {
		for (IFile f : checkedFiles) {
			backupModel(f);
		}
	}

	private void backupModel(IFile file) {
		try {
			if (file.exists()) {
				System.out.println("SIDEBuilderChecker.backupModel() " + file);
				IFile backupModel = SIDEBuilderUtil.getBackupModel(file);
				IResource container = backupModel.getParent();
				if (!container.exists() && container instanceof IFolder) {
					SIDEBuilderUtil.prepareFolder((IFolder) container);
				}

				Resource src = EResourceUtils.openModel(file.getLocation().toString(), new HashMap<Object, Object>());
				Resource target = new XMIResourceImpl(URI.createFileURI(backupModel.getLocation().toString()));
				target.getContents().addAll(src.getContents());
				HashMap<String, Boolean> options = new HashMap<String, Boolean>();
				options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
				target.save(options);
				backupModel.refreshLocal(IResource.DEPTH_ONE, null);
			}
		} catch (Exception e) {
			//Nothing to do
			e.printStackTrace();
			MarkerHelper.addMarker(file, e.getMessage(), 0, IMarker.SEVERITY_ERROR);
		}
	}

	private boolean contains(List<IFile> l, IFile f) {
		for (IFile iFile : l) {
			if (iFile.getFullPath().toPortableString().equals(f.getFullPath().toPortableString())) {
				return true;
			}
		}
		return false;
	}

	private void check(IFile model) {
		System.out.println("SIDEBuilderChecker.check() " + model);
		if (model.exists() && !contains(checkedFiles, model)) {
			checkedFiles.add(model);
			try {
				if (!ApplicationUtil.validate(model)) {
					MarkerHelper.addMarker(model, "The model " + model.getName() + " is not valid. Please launch 'Validate' on the top model element of this model.", 0, IMarker.SEVERITY_ERROR);
				}
			} catch (Exception e) {
				//Nothing to do
			}
		}
	}

	/**
	 * this implementation search model that link the given model
	 * and run validation on them
	 * 
	 * @param model
	 * @param previousName
	 */
	private void checkLinkedElementsSimple(IFile model, String previousName) {
		ModelsDocument doc = null;
		try {
			IProject p = model.getProject();
			// get referential.xml file that contains references
			IResource r = p.findMember(SIDEBuilderConstants.referentialFileName);
			if ((r != null) && (r instanceof IFile) && r.exists()) {
				IFile referential = (IFile) r;
				referential.refreshLocal(0, null);
				doc = ModelsDocument.Factory.parse(referential.getContents());
			}

			if ((doc != null) && (doc.getModels() != null)) {
				for (Model m : doc.getModels().getModelArray()) {
					List<String> linkedFiles = new ArrayList<String>();
					IFile iFile = IFileHelper.getIFile(m.getPath());
					// get references for this model
					if (iFile.toString().equals(model.toString())) {
						for (Reference ref : m.getReferencedByArray()) {
							if (!linkedFiles.contains(ref.getModel())) {
								// record new reference
								linkedFiles.add(ref.getModel());
							}
						}
						// run validation for each model that refer to the given model
						for (String s : linkedFiles) {
							IFile iFileToValidate = IFileHelper.getIFile(s);
							MarkerHelper.deleteMarkers(iFileToValidate);
							check(iFileToValidate);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO : use Eclipse logger
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	@Deprecated
	private void checkLinkedElements(IFile model, String previousName) {
		ModelsDocument doc = null;
		try {
			IProject p = model.getProject();
			IResource r = p.findMember(SIDEBuilderConstants.referentialFileName);
			if (r != null && r instanceof IFile && r.exists()) {
				IFile referential = (IFile) r;
				referential.refreshLocal(0, null);
				doc = ModelsDocument.Factory.parse(referential.getContents());
			}

			if (doc != null && doc.getModels() != null) {
				try {
					// Loads the new version
					final EObject _new = EResourceUtils.openModel(model.getLocation().toString(), new HashMap<Object, Object>()).getContents().get(0);

					//Load the previous version
					IPath path = model.getFullPath().removeFirstSegments(1);
					IFolder folder = model.getProject().getFolder(SIDEBuilderConstants.metadataFolder);
					folder = folder.getFolder(path.removeLastSegments(1));
					path = folder.getFullPath().append(model.getName());
					IFile previous = model.getProject().getFile(path.removeFirstSegments(1));
					if (previous.exists()) {
						final EObject _old = EResourceUtils.openModel(previous.getLocation().toString(), new HashMap<Object, Object>()).getContents().get(0);

						// Creates the match then the diff model for those two models
						final MatchModel match = MatchService.doMatch(_new, _old, Collections.<String, Object> emptyMap());
						final DiffModel diff = DiffService.doDiff(match, false);
						Collection<EObject> objs = DeltaCollector.collectDifferences(diff.getOwnedElements()).keySet();

						for (EObject o : objs) {
							String URI = EcoreUtil.getURI(o).fragment();
							String spath = EcoreUtil.getURI(o).toFileString();
							IResource referencedModel = null;

							IProject proj = model.getProject();
							referencedModel = proj.findMember(spath);

							if (referencedModel == null) {
								IPath ipath = new Path(spath);
								ipath = ipath.removeFirstSegments(Platform.getLocation().segmentCount());
								String projectName = ipath.segment(0);
								if (projectName.equals(proj.getName())) {
									ipath = ipath.removeFirstSegments(1);
									referencedModel = proj.findMember(ipath);
								}
							}

							if (referencedModel != null) {
								String modelName = referencedModel.getName();

								for (Model m : doc.getModels().getModelArray()) {
									List<String> linkedFiles = new ArrayList<String>();
									if (m.getPath().equals(modelName)) {
										for (Reference ref : m.getReferencedByArray()) {
											if (ref.getUuid().equals(URI)) {
												if (!linkedFiles.contains(ref.getModel())) {
													linkedFiles.add(ref.getModel());
												}
											}
										}

										for (String s : linkedFiles) {
											path = new Path(s);
											IFile file = p.getFile(path.removeFirstSegments(1));
											MarkerHelper.deleteMarkers(file);
											check(file);
										}
									}
								}
							}
						}
					}
				} catch (final Exception e) {
					// shouldn't be thrown
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			//Nothing to do
		}
	}

}
