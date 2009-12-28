package com.bluexml.side.integration.eclipse.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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
				checkXML(resource, previousName);
				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				
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
				checkXML(resource, previousName);
				break;
			}
			//return true to continue visiting children.
			return true;
		}
	}

	class EMFResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) {
			checkXML(resource, resource.getFullPath().toString());
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

	void checkXML(IResource resource, String previousName) {
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
			p.append(resource.getFullPath().lastSegment().substring(0, resource.getFullPath().lastSegment().length()-2));
			p = p.removeFirstSegments(1);
			IFile f = resource.getProject().getFile(p);
			if (f != null)
				check(f);
		}
		
		if (resource instanceof IFile && validFile) {
			IFile file = (IFile) resource;

			deleteMarkers(file);
			EMFErrorHandler reporter = new EMFErrorHandler(file);
			
			check(file);
			checkLinkedFiles(file, previousName);
			computeReferential(file);
		}
	}
	
	private void check(IFile model) {
		if (model.exists() && !checkedFiles.contains(model.getFullPath().toString())) {
			checkedFiles.add(model.getFullPath().toString());
			try {
				if (!ApplicationUtil.validate(model))
					addMarker(model, "The model "+model.getName()+" is not valid. Please launch 'Validate' on top model element of this model.",0,IMarker.SEVERITY_ERROR);
			} catch (Exception e) {
				//Nothing to do
			}
		}
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

	private void checkLinkedFiles(IFile file, String previousName) {
		ModelsDocument doc = null;
		try {
			IProject p = file.getProject();
			IResource r = p.findMember(SIDEBuilderConstants.referentialFileName);
			if (r != null && r instanceof IFile && r.exists()) {
				IFile referential = (IFile) r;
				referential.refreshLocal(0, null);
				doc = ModelsDocument.Factory.parse(referential.getContents());
			}
			
			if (doc != null && doc.getModels() != null) {
				for (Model m : doc.getModels().getModelArray()) {
					List<String> linkedFiles = new ArrayList<String>();
					if (m.getPath().equals(previousName)) {
						for (Reference ref : m.getReferencedByArray()) {
							if (!linkedFiles.contains(ref.getModel()))
								linkedFiles.add(ref.getModel());
						}
						
						for (String s : linkedFiles) {
							Path path = new Path(s);
							IFile model = p.getFile(path.removeFirstSegments(1));
							check(model);
						}
					}
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
