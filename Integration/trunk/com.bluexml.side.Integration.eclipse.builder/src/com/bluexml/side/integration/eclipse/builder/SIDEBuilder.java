package com.bluexml.side.integration.eclipse.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.compare.diff.metamodel.AttributeChange;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChange;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.MoveModelElement;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.sideLabs.referential.references.Model;
import org.sideLabs.referential.references.ModelsDocument;
import org.sideLabs.referential.references.Reference;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;

public class SIDEBuilder extends IncrementalProjectBuilder {
	
	private List<String> checkedFiles;

	class SIDEDeltaVisitor implements IResourceDeltaVisitor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			String previousName = resource.getFullPath().toString();
			if (delta.getMovedFromPath() != null)
				previousName = delta.getMovedFromPath().toString();
			
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				// handle added resource
				checkModel(resource, previousName);
				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				
				//Delete backup model
				if (resource instanceof IFile) {
					IFile file = (IFile) resource;
					IFile backupModel = getBackupModel(file);
					if (backupModel.exists())
						backupModel.delete(true, null);
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
								if (m.getPath().equals(path))
									finded = true;
								else
									if (!finded)
										i++;
							}
							if (finded)
								doc.getModels().removeModel(i);
							
							//Save the referential
							prepareFolder((IFolder) referential.getParent());
							File f = referential.getRawLocation().toFile();
							if (!f.exists())
								f.createNewFile();
							doc.save(f);
						}
					}
				} catch (Exception e) {
					//Nothing to do
				}					
				break;
			case IResourceDelta.CHANGED:
				// handle changed resource
				checkModel(resource, previousName);
				break;
			}
			//return true to continue visiting children.
			return true;
		}
	}

	class EMFResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) {
			checkModel(resource, resource.getFullPath().toString());
			//return true to continue visiting children.
			return true;
		}
	}

	class EMFErrorHandler extends DefaultHandler {
		
		
		private IFile file;

		public EMFErrorHandler(IFile file) {
			this.file = file;
		}

		private void addMarker(SAXParseException e, int severity) {
			SIDEBuilder.this.addMarker(file, e.getMessage(), e
					.getLineNumber(), severity);
		}

		public void error(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_ERROR);
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_ERROR);
		}

		public void warning(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_WARNING);
		}
	}

	public static final String BUILDER_ID = "com.bluexml.side.integration.eclipse.builder";

	private static final String MARKER_TYPE = "com.bluexml.side.Integration.eclipse.builder.sideProblem";

	private void addMarker(IFile file, String message, int lineNumber,
			int severity) {
		try {
			IMarker marker = file.createMarker(MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			if (lineNumber == -1) {
				lineNumber = 1;
			}
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
		} catch (CoreException e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int,
	 *      java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		IFolder folder = getProject().getFolder(SIDEBuilderConstants.metadataFolder);
		prepareFolder(folder);
		
		checkedFiles = new ArrayList<String>();
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else if (hasMovedFile(delta)) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	private boolean hasMovedFile(IResourceDelta delta) {
		 if (delta.getKind() == IResourceDelta.CHANGED && delta.getMovedFromPath() != delta.getResource().getFullPath())
			 return true;
		 else
			 for (IResourceDelta d : delta.getAffectedChildren())
				 if (hasMovedFile(d))
					 return true;
		return false;
	}

	void checkModel(IResource resource, String previousName) {
		boolean validFile = false;
		boolean diagramFile = false;
		for (int i = 0; i < SIDEBuilderConstants.availableExtensions.length; ++i)
			if (resource.getFileExtension() != null && resource.getFileExtension().equalsIgnoreCase(SIDEBuilderConstants.availableExtensions[i]))
				validFile = true;
		for (int i = 0; i < SIDEBuilderConstants.availableExtensionsDiagrams.length; ++i)
			if (resource.getFileExtension() != null && resource.getFileExtension().equalsIgnoreCase(SIDEBuilderConstants.availableExtensionsDiagrams[i]))
				diagramFile = true;
		
		if (diagramFile) {
			IPath p = new Path(resource.getFullPath().removeLastSegments(1).toString());
			p = p.append(resource.getFullPath().lastSegment().substring(0, resource.getFullPath().lastSegment().length()-2));
			p = p.removeFirstSegments(1);
			IFile f = resource.getProject().getFile(p);
			if (f != null && f.exists())
				check(f);
		}
		
		if (resource instanceof IFile && validFile) {
			IFile file = (IFile) resource;

			deleteMarkers(file);
			EMFErrorHandler reporter = new EMFErrorHandler(file);
			
			if (!file.getFullPath().segment(1).equals(SIDEBuilderConstants.metadataFolder)) {
				check(file);
				checkLinkedElements(file, previousName);
				computeReferential(file);
			
				//Backup the last version
				backupModel(file);
			}
		}
	}
	
	private IFile getBackupModel(IFile file) {
		IPath path = file.getFullPath().removeFirstSegments(1);
		IFolder folder = file.getProject().getFolder(SIDEBuilderConstants.metadataFolder);
		folder = folder.getFolder(path.removeLastSegments(1));
		path = folder.getFullPath().append(file.getName());	
		return file.getProject().getFile(path.removeFirstSegments(1));
	}
	
	private void backupModel(IFile file) {
		try {
			if (file.exists()) {
				IFile backupModel = getBackupModel(file);
				if (backupModel.exists())
					backupModel.delete(true, null);
				IResource container = backupModel.getParent(); 
				if (!container.exists() && container instanceof IFolder)
					prepareFolder((IFolder) container);
				file.copy(backupModel.getFullPath(), true, null);
			}
		} catch (CoreException e) {
			//Nothing to do
			addMarker(file, e.getMessage(), 0, IMarker.SEVERITY_ERROR);
		}
	}

	private void check(IFile model) {
		if (model.exists() && !checkedFiles.contains(model.getFullPath().toString())) {
			checkedFiles.add(model.getFullPath().toString());
			try {
				if (!ApplicationUtil.validate(model))
					addMarker(model, "The model "+model.getName()+" is not valid. Please launch 'Validate' on the top model element of this model.",0,IMarker.SEVERITY_ERROR);
			} catch (Exception e) {
				//Nothing to do
			}
		}
	}

	private List<EObject> collectDifferences(List<DiffElement> differences) {
		List<EObject> result = new ArrayList<EObject>();
		for (DiffElement el : differences) {
			if (el instanceof AttributeChange) {
				AttributeChange change = (AttributeChange) el;
				result.add(change.getLeftElement());
			} else if (el instanceof ModelElementChange) {
				ModelElementChange change = (ModelElementChange) el;
				if (change instanceof ModelElementChangeRightTarget) {
					ModelElementChangeRightTarget changeR = (ModelElementChangeRightTarget) change;
					result.add(changeR.getRightElement());
				} else if (change instanceof MoveModelElement) {
					MoveModelElement move = (MoveModelElement) change;
					result.add(move.getLeftElement());
				}
			}
			result.addAll(collectDifferences(el.getSubDiffElements()));
		}
		return result;
	}

	private void computeReferential(IFile file) {
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
		if (doc == null)
			doc = ModelsDocument.Factory.newInstance();
		
		//Search the good node
		Map<String,Model> presentModels = new HashMap<String, Model>();
		if (doc.getModels() != null) {
			for (Model m : doc.getModels().getModelArray()) {
				presentModels.put(m.getPath(), m);
			}
		} else
			doc.addNewModels();

		try {
			//getParser().parse(file.getContents(), reporter);
			Resource r = EResourceUtils.openModel(file.getLocation().toString(), new HashMap<Object, Object>());
			if (r instanceof XMIResource) {
				XMIResource xmi = (XMIResource) r;
				Map<EObject, Collection<Setting>> m = EcoreUtil.ProxyCrossReferencer.find(xmi);
				for (EObject o : m.keySet()) {
					String path = EcoreUtil.getURI(o).toFileString();
					IResource referencedModel = null;

					IProject proj = file.getProject();
					referencedModel = proj.findMember(path);
					
					if (referencedModel == null) {
						IPath ipath = new Path(path);
						ipath = ipath.removeFirstSegments(Platform.getLocation().segmentCount());
						String projectName = ipath.segment(0);
						if (projectName.equals(proj.getName())) {
							ipath = ipath.removeFirstSegments(1);
							referencedModel = proj.findMember(ipath);
						}
					}
					
					if (referencedModel != null) {
						Model mod;
						if (presentModels.containsKey(referencedModel.getFullPath().toString()))
							mod = presentModels.get(referencedModel.getFullPath().toString());
						else {
							mod = doc.getModels().addNewModel();
							mod.setPath(referencedModel.getFullPath().toString());
							presentModels.put(referencedModel.getFullPath().toString(), mod);
						}

						Reference ref = null;
						for (Reference ref2 : mod.getReferencedByArray()) {
							String target = EcoreUtil.getURI(o).fragment();
							Setting setting = (Setting) m.get(o).toArray()[0];
							String source = EcoreUtil.getURI(setting.getEObject()).fragment(); 
							if (ref2.getModel().equals(file.getFullPath().toString()) && ref2.getUuid().equals(target) && ref2.getSource().equals(source))
								ref = ref2;
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
							String name = (new Path(path)).removeFirstSegments(projectPath.segmentCount()-1).toString();
							addMarker(file, "The model "+name+" does not exist.", -1, IMarker.SEVERITY_ERROR);
						} else
							addMarker(file, "The model "+path+" is not managed because it don't exists in this project.", -1, IMarker.SEVERITY_WARNING);
					}
					
					//Save the referential
					IProject p = file.getProject();
					IFile referential = p.getFile(SIDEBuilderConstants.referentialFileName);
					prepareFolder((IFolder) referential.getParent());
					File f = referential.getRawLocation().toFile();
					if (!f.exists())
						f.createNewFile();
					doc.save(f);
				}
			}
		} catch (Exception e1) {
			//Nothing to do
		}
	}

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
						List<EObject> objs = collectDifferences(diff.getOwnedElements());

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
											if (ref.getUuid().equals(URI))
												if (!linkedFiles.contains(ref.getModel()))
													linkedFiles.add(ref.getModel());
										}
										
										for (String s : linkedFiles) {
											path = new Path(s);
											IFile file = p.getFile(path.removeFirstSegments(1));
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

	private void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			IFolder folder = getProject().getFolder(SIDEBuilderConstants.metadataFolder);
			if (folder.exists())
				folder.delete(true, null);
			prepareFolder(folder);
			getProject().accept(new EMFResourceVisitor());
		} catch (CoreException e) {
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
        try {
           delta.accept(new IResourceDeltaVisitor() {
              public boolean visit(IResourceDelta delta) {
                 return true; // visit children too
              }
           });
        } catch (CoreException e) {
           e.printStackTrace();
        }

		
		// the visitor does the work.
		delta.accept(new SIDEDeltaVisitor());
	}
	
	public void prepareFolder(IFolder folder) throws CoreException
	{
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder)
			prepareFolder((IFolder) parent);
		if (!folder.exists())
			folder.create(true, true, null);
	}
}
		