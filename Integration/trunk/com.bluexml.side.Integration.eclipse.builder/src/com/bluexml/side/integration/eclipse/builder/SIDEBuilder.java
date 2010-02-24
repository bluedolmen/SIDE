package com.bluexml.side.integration.eclipse.builder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
import org.eclipse.emf.common.util.URI;
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
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.ui.IWorkbenchPage;
import org.sideLabs.referential.references.Model;
import org.sideLabs.referential.references.ModelsDocument;
import org.sideLabs.referential.references.Reference;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;

public class SIDEBuilder extends IncrementalProjectBuilder {
	
	private List<String> checkedFiles;
	static private IWorkbenchPage _activePage;

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
					IFile backupModel = SIDEBuilderUtil.getBackupModel(file);
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
							SIDEBuilderUtil.prepareFolder((IFolder) referential.getParent());
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
			e.printStackTrace();
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
		SIDEBuilderUtil.prepareFolder(folder);
		
		checkedFiles = new ArrayList<String>();
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else if (getMovedFiles(delta).size() > 0) {
				manageDifferences(delta, getMovedFiles(delta));
				SIDEBuilderUtil.deleteEmptyFolders(folder);
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		SIDEBuilderUtil.cleanReferential(getProject());
		return null;
	}

	private void manageDifferences(IResourceDelta delta, Collection<IResourceDelta> movedFiles) {
		if (delta.getKind() == IResourceDelta.REMOVED) {
			//Manage moved elements
			IPath from = delta.getFullPath();
			IPath to = delta.getMovedToPath();
			
			if (from != to) {
				try {
					//Delete the backup of from models
					IFile backupModel = SIDEBuilderUtil.getBackupModel(from);
					if (backupModel.exists())
						backupModel.delete(true, null);
					
					//Try to move the diagram file
					String fileName = from.lastSegment();
					fileName = fileName.concat("di");
					IFile diagram = SIDEBuilderUtil.getFile(from.removeLastSegments(1).append(fileName));
					if (diagram.exists()) {
						IPath _to = to.removeLastSegments(1).append(fileName);
						diagram.move(_to, true, null);
					}
					
					//Try to move the model
					boolean moveDiagram = false;
					fileName = from.lastSegment();
					if (fileName.endsWith("di")) {
						moveDiagram = true;
						fileName = from.lastSegment();
						fileName = fileName.substring(0,fileName.length()-2);
						IFile model = SIDEBuilderUtil.getFile(from.removeLastSegments(1).append(fileName));
						if (model.exists()) {
							IPath _to = to.removeLastSegments(1).append(fileName);
							model.move(_to, true, null);
						}
					}
					
					//Update dependencies
					ModelsDocument doc = null;
					IProject p = delta.getResource().getProject();
					IResource r = p.findMember(SIDEBuilderConstants.referentialFileName);
					if (r != null && r instanceof IFile && r.exists()) {
						IFile referential = (IFile) r;
						referential.refreshLocal(0, null);
						doc = ModelsDocument.Factory.parse(referential.getContents());
					}
					//Compute all references
					List<Reference> references = new ArrayList<Reference>();
					if (doc != null) {
						for (Model m : doc.getModels().getModelArray()) {
							IPath _from = from;
							if (moveDiagram) {
								fileName = from.lastSegment();
								fileName = fileName.substring(0,fileName.length()-2);
								_from = from.removeLastSegments(1).append(fileName);
							}
							
							if (m.getPath().equals(_from.toString())) {
								for (Reference ref : m.getReferencedByArray())
									references.add(ref);
							}
						}
					}
					//Organize references by model
					Map<String, List<Reference>> referencesByFile = new HashMap<String, List<Reference>>();
					for (Reference ref : references) {
						if (referencesByFile.keySet().contains(ref.getModel()))
							referencesByFile.get(ref.getModel()).add(ref);
						else {
							List<Reference> l = new ArrayList<Reference>();
							l.add(ref);
							referencesByFile.put(ref.getModel(), l);
						}
					}
					//Apply modifications
					for (String file : referencesByFile.keySet()) {
						IFile f = SIDEBuilderUtil.getFile(new Path(file));
						deleteMarkers(f);
						
						//Check if the file is moved at the same moment
						IResourceDelta foundDelta = null;
						for (IResourceDelta d : movedFiles)
							if (d.getFullPath() != null && d.getFullPath().equals(new Path(file)))
								foundDelta = d;
							else {
								if (d.getFullPath() != null && d.getFullPath().lastSegment().endsWith("di")) {
									fileName = d.getFullPath().lastSegment();
									fileName = fileName.substring(0,fileName.length()-2);
									if (d.getFullPath().removeLastSegments(1).append(fileName).equals(new Path(file)))
										foundDelta = d;
								}
							}
						if (foundDelta != null) {
							IPath _to = foundDelta.getMovedToPath();
							if (_to.lastSegment().endsWith("di")) {
								fileName = _to.lastSegment();
								fileName = fileName.substring(0,fileName.length()-2);
								_to = _to.removeLastSegments(1).append(fileName);
							}
							f = SIDEBuilderUtil.getFile(_to);
						}
						
						if (f.exists()) {
							List<Reference> refs = referencesByFile.get(file);
							List<String> ids = new ArrayList<String>();
							for (Reference ref : refs)
								ids.add(ref.getUuid());
							
							IPath _to = to;
							//Check if we move the diagram
							if (moveDiagram) {
								fileName = from.lastSegment();
								fileName = fileName.substring(0,fileName.length()-2);
								_to = to.removeLastSegments(1).append(fileName);
							}
							//Check if we move the file at the same moment
							foundDelta = null;
							for (IResourceDelta d : movedFiles)
								if (d.getMovedToPath() != null && d.getMovedToPath().equals(_to))
									foundDelta = d;
								else {
									if (d.getMovedToPath() != null && d.getMovedToPath().lastSegment().endsWith("di")) {
										fileName = d.getMovedToPath().lastSegment();
										fileName = fileName.substring(0,fileName.length()-2);
										if (d.getMovedToPath().removeLastSegments(1).append(fileName).equals(_to))
											foundDelta = d;
									}
								}
							if (foundDelta != null) {
								_to = foundDelta.getMovedToPath();
								if (_to.lastSegment().endsWith("di")) {
									fileName = _to.lastSegment();
									fileName = fileName.substring(0,fileName.length()-2);
									_to = _to.removeLastSegments(1).append(fileName);
								}
							}
							applyModifications(f,_to.toString(), ids, movedFiles);
						}
					}
					
					
				} catch (Exception e) {
					//Nothing to do
					e.printStackTrace();
				}
			}
		}
		
		//Search children
		for (IResourceDelta d : delta.getAffectedChildren())
			manageDifferences(d, movedFiles);
	}

	private void applyModifications(IFile f, String newFile, List<String> ids, Collection<IResourceDelta> movedFiles) throws Exception {
		if (f.getName().endsWith(".application")) {
			applyModificationsOnApplicationModel(f,newFile,movedFiles);
		} else {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse (f.getRawLocation().toFile());
	        
	        applyModifications(doc.getDocumentElement(), f.getFullPath().toString(), newFile, ids);
	
	        //Write xml documents
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        StreamResult result = new StreamResult(new StringWriter());
	        DOMSource source = new DOMSource(doc);
	        transformer.transform(source, result);
	        PrintWriter updates = new PrintWriter(new BufferedWriter(new FileWriter(f.getRawLocation().toFile())), true);
	        updates.println(result.getWriter().toString());
		}
		
		//refresh the file
		f.refreshLocal(IResource.DEPTH_ONE, null);
	}

	private void applyModificationsOnApplicationModel(IFile f, String newFile, Collection<IResourceDelta> movedFiles) {
		IPath from = null;
		//Search the previous name
		IResourceDelta foundDelta = null;
		for (IResourceDelta d : movedFiles)
			if (d.getMovedToPath() != null && d.getMovedToPath().equals(new Path(newFile)))
				foundDelta = d;
			else {
				if (d.getMovedToPath() != null && d.getMovedToPath().lastSegment().endsWith("di")) {
					String fileName = d.getMovedToPath().lastSegment();
					fileName = fileName.substring(0,fileName.length()-2);
					if (d.getMovedToPath().removeLastSegments(1).append(fileName).equals(new Path(newFile)))
						foundDelta = d;
				}
			}
		if (foundDelta != null) {
			from = foundDelta.getFullPath();
			if (from.lastSegment().endsWith("di")) {
				String fileName = from.lastSegment();
				fileName = fileName.substring(0,fileName.length()-2);
				from = from.removeLastSegments(1).append(fileName);
			}
		}
		
		try {
			//Open the model
			Resource r = EResourceUtils.openModel(f.getLocation().toString(), new HashMap<Object, Object>());
			if (r instanceof XMIResource && from != null) {
				XMIResource xmi = (XMIResource) r;
				EObject o = xmi.getContents().get(0);
				for (EObject eobj : o.eContents()) {
					if (eobj.eClass().getName().equalsIgnoreCase("Model")) {
						Method method = eobj.getClass().getMethod("getFile",  new Class[0]);
						String path = method.invoke(eobj, new Object[0]).toString();
						if (path != null && path.equals(from.toString())) {
							//The good model is found
							Class[] paramsType = new Class[] {String.class};
							method = eobj.getClass().getMethod("setFile",  paramsType);
							method.invoke(eobj, new Object[] {newFile});
						}
					}
				}
			}
			
			//Save the resource
			EResourceUtils.export(r);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void applyModifications(Element elt, String file, String newFile, List<String> list) {
		String href = elt.getAttribute("href");
		if (href != null && href.length() > 0) {
			String id = href.substring(href.lastIndexOf('#')+1);
			if (list.contains(id)) {
				String newId = newFile + '#' + id;
				elt.setAttribute("href", newId);
			}
		}
		
		//Search in child nodes
		NodeList childs = elt.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++)
			if (childs.item(i).getNodeType() == Node.ELEMENT_NODE)
				applyModifications((Element) childs.item(i), file, newFile, list);
	}


	private Collection<IResourceDelta> getMovedFiles(IResourceDelta delta) {
		List<IResourceDelta> result = new ArrayList<IResourceDelta>();
		if (delta.getResource() instanceof IFile) { 
			if ((delta.getKind() == IResourceDelta.REMOVED) && delta.getMovedFromPath() != delta.getResource().getFullPath())
				result.add(delta);
		} else
			for (IResourceDelta d : delta.getAffectedChildren())
				result.addAll(getMovedFiles(d));
		return result;
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
	
	private void backupModel(IFile file) {
		try {
			if (file.exists()) {
				IFile backupModel = SIDEBuilderUtil.getBackupModel(file);
				IResource container = backupModel.getParent(); 
				if (!container.exists() && container instanceof IFolder)
					SIDEBuilderUtil.prepareFolder((IFolder) container);
				
				Resource src = EResourceUtils.openModel(file.getLocation().toString(), new HashMap<Object, Object>());
				Resource target = new XMIResourceImpl(URI.createFileURI(backupModel.getLocation().toString()));
				target.getContents().addAll(src.getContents());
				HashMap<String, Boolean> options = new HashMap<String, Boolean>();
				options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
				target.save(options);
				backupModel.refreshLocal(IResource.DEPTH_ONE, null);
			}
		} catch (Exception e) {
			//Nothing to do
			e.printStackTrace();
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
				
				//Specific case with application models
				if (file.getName().endsWith(".application")) {
					EObject o = xmi.getContents().get(0);
					for (EObject eobj : o.eContents()) {
						if (eobj.eClass().getName().equalsIgnoreCase("Model")) {
							Method method = eobj.getClass().getMethod("getFile",  new Class[0]);
							String path = method.invoke(eobj, new Object[0]).toString();
							IFile referencedModel = SIDEBuilderUtil.getFile(new Path(path));
							if (referencedModel.exists()) {
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
									String source = EcoreUtil.getURI(eobj).fragment(); 
									if (ref2.getModel().equals(file.getFullPath().toString()) && ref2.getUuid().equals(source))
										ref = ref2;
								}
								
								if (ref == null) {
									ref = mod.addNewReferencedBy();
									ref.setModel(file.getFullPath().toString());
									ref.setUuid(EcoreUtil.getURI(eobj).fragment());
								}
							} else {
								addMarker(file, "The model "+referencedModel.getFullPath().toString()+" does not exist.", -1, IMarker.SEVERITY_ERROR);
							}
						}
					}
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
					
				}
				//Save the referential
				IProject p = file.getProject();
				IFile referential = p.getFile(SIDEBuilderConstants.referentialFileName);
				SIDEBuilderUtil.prepareFolder((IFolder) referential.getParent());
				File f = referential.getRawLocation().toFile();
				if (!f.exists())
					f.createNewFile();
				doc.save(f);
			}
		} catch (Exception e1) {
			//Nothing to do
			e1.printStackTrace();
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
											deleteMarkers(file);
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
			//Delete EMF marker
			file.deleteMarkers("org.eclipse.emf.validation.problem", false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			IFolder folder = getProject().getFolder(SIDEBuilderConstants.metadataFolder);
			//SIDEBuilderUtil.clearFolder(folder);
			SIDEBuilderUtil.prepareFolder(folder);
			getProject().accept(new EMFResourceVisitor());
		} catch (CoreException e) {
			e.printStackTrace();
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
	
}
		