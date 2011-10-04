package com.bluexml.side.integration.eclipse.builder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.sideLabs.referential.references.Model;
import org.sideLabs.referential.references.ModelsDocument;
import org.sideLabs.referential.references.Reference;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.application.ui.action.MustBeStopped;
import com.bluexml.side.integration.eclipse.builder.incremental.IncrementalBuilderHelper;

public class SIDEBuilder extends IncrementalProjectBuilder {
	private static boolean activated = true;
	private SIDEBuilderChecker checker;

	/**
	 * @return the activated
	 */
	public static boolean isActivated() {
		return activated;
	}

	/**
	 * @param activated
	 *            the activated to set
	 */
	public static void setActivated(boolean activated) {
		System.out.println("SIDEBuilder.setActivated() new value :" + activated);
		SIDEBuilder.activated = activated;
	}

	public static final String BUILDER_ID = "com.bluexml.side.integration.eclipse.builder";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int,
	 * java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException {
		System.out.println("SIDEBuilder.build() BUILD START ...");
		Date date = new Date();
		if (isActivated()) {
			checker = new SIDEBuilderChecker();
			IFolder folder = getProject().getFolder(SIDEBuilderConstants.metadataFolder);
			SIDEBuilderUtil.prepareFolder(folder);

			if (kind == FULL_BUILD) {
				System.out.println("SIDEBuilder.build() KIND : FULL BUILD");
				//				fullBuild(monitor);
				fullBuildAndExecuteSIDEProcess(monitor);
			} else {
				IResourceDelta delta = getDelta(getProject());
				if (delta == null) {
					System.out.println("SIDEBuilder.build() DELTA EMPTY : FULL BUILD");
					fullBuild(monitor);
				} else {
					List<IResourceDelta> movedModels = getMovedModels(delta);
					if (movedModels.size() > 0) {
						System.out.println("SIDEBuilder.build() MODEL MOVED : FULL BUILD");
						manageDifferences(delta, movedModels);
						SIDEBuilderUtil.deleteEmptyFolders(folder);
						fullBuild(monitor);
					} else {
						System.out.println("SIDEBuilder.build() INCREMENTAL");
						incrementalBuild(delta, monitor);
					}
				}
			}
			SIDEBuilderUtil.cleanReferential(getProject());

			// WARN ONLY BACKUP AFTER ALL ACTION THAT NEED DELTA ARE DONES
			checker.backupModels();

		} else {
			System.out.println("SIDEBuilder.build() SIDE builder disabled ");
		}
		System.out.println("SIDEBuilder.generateChangedModels() start " + date);
		Date date2 = new Date();
		System.out.println("SIDEBuilder.generateChangedModels() end " + date2);
		long l = date2.getTime() - date.getTime();
		//		Date date3 = new Date(l);
		//		String format = new SimpleDateFormat("HH:mm:ss,SSS").format(date3);
		//		System.out.println("done in :" + format);
		System.out.println("done in :" + l + " ms");
		System.out.println("SIDEBuilder.build() BUILD ENDED");
		getProject().refreshLocal(-1, monitor);
		return null;
	}

	private void fullBuild(final IProgressMonitor monitor) throws CoreException {
		System.out.println("*** SIDEBuilder.fullBuild() ***");
		try {
			IFolder folder = getProject().getFolder(SIDEBuilderConstants.metadataFolder);
			//SIDEBuilderUtil.clearFolder(folder);
			SIDEBuilderUtil.prepareFolder(folder);
			getProject().accept(new EMFResourceVisitor(checker));
		} catch (CoreException e) {
			e.printStackTrace();
		}
		System.out.println("SIDEBuilder.fullBuild() END");
	}

	private void fullBuildAndExecuteSIDEProcess(final IProgressMonitor monitor) throws CoreException {
		fullBuild(monitor);

		// only execute project full SIDE process
		IncrementalBuilderHelper inc = new IncrementalBuilderHelper(getProject());
		
		try {
			inc.executeSIDEProjectProcess();
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
		} catch (MustBeStopped e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
		}
	}

	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		System.out.println("*** SIDEBuilder.incrementalBuild() ***");
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

		delta.accept(new SIDEDeltaVisitor(checker));

		// now all models are visited we can execute incremental generation
		// get model list from checker and run incremental generation
		List<IFile> models = checker.getModels();
		IncrementalBuilderHelper inc = new IncrementalBuilderHelper(getProject());
		
		try {
			if (models.size() == 1) {
				inc.generateChangedModels(true, models.get(0));
			} else if (models.size() > 1) {
				System.out.println("SIDEBuilder.incrementalBuild() more than one models :" + models.size());
				for (IFile iFile : models) {
					System.out.println("SIDEBuilder.incrementalBuild() model " + iFile);
				}
				//			throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "ERROR more than one model changed at same time ..."));
				// can happen after a initialize action
				// get application and run SIDE process without clean options (incremental mode)
				inc.generateChangedModelsIncremental();

			}
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
		} catch (MustBeStopped e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
		}
		System.out.println("SIDEBuilder.incrementalBuild() END");

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
					if (backupModel.exists()) {
						backupModel.delete(true, null);
					}

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
						fileName = fileName.substring(0, fileName.length() - 2);
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
								fileName = fileName.substring(0, fileName.length() - 2);
								_from = from.removeLastSegments(1).append(fileName);
							}

							if (m.getPath().equals(_from.toString())) {
								for (Reference ref : m.getReferencedByArray()) {
									references.add(ref);
								}
							}
						}
					}
					//Organize references by model
					Map<String, List<Reference>> referencesByFile = new HashMap<String, List<Reference>>();
					for (Reference ref : references) {
						if (referencesByFile.keySet().contains(ref.getModel())) {
							referencesByFile.get(ref.getModel()).add(ref);
						} else {
							List<Reference> l = new ArrayList<Reference>();
							l.add(ref);
							referencesByFile.put(ref.getModel(), l);
						}
					}
					//Apply modifications
					for (String file : referencesByFile.keySet()) {
						IFile f = SIDEBuilderUtil.getFile(new Path(file));
						MarkerHelper.deleteMarkers(f);

						//Check if the file is moved at the same moment
						IResourceDelta foundDelta = null;
						for (IResourceDelta d : movedFiles) {
							if (d.getFullPath() != null && d.getFullPath().equals(new Path(file))) {
								foundDelta = d;
							} else {
								if (d.getFullPath() != null && d.getFullPath().lastSegment().endsWith("di")) {
									fileName = d.getFullPath().lastSegment();
									fileName = fileName.substring(0, fileName.length() - 2);
									if (d.getFullPath().removeLastSegments(1).append(fileName).equals(new Path(file))) {
										foundDelta = d;
									}
								}
							}
						}
						if (foundDelta != null) {
							IPath _to = foundDelta.getMovedToPath();
							if (_to.lastSegment().endsWith("di")) {
								fileName = _to.lastSegment();
								fileName = fileName.substring(0, fileName.length() - 2);
								_to = _to.removeLastSegments(1).append(fileName);
							}
							f = SIDEBuilderUtil.getFile(_to);
						}

						if (f.exists()) {
							List<Reference> refs = referencesByFile.get(file);
							List<String> ids = new ArrayList<String>();
							for (Reference ref : refs) {
								ids.add(ref.getUuid());
							}

							IPath _to = to;
							//Check if we move the diagram
							if (moveDiagram) {
								fileName = from.lastSegment();
								fileName = fileName.substring(0, fileName.length() - 2);
								_to = to.removeLastSegments(1).append(fileName);
							}
							//Check if we move the file at the same moment
							foundDelta = null;
							for (IResourceDelta d : movedFiles) {
								if (d.getMovedToPath() != null && d.getMovedToPath().equals(_to)) {
									foundDelta = d;
								} else {
									if (d.getMovedToPath() != null && d.getMovedToPath().lastSegment().endsWith("di")) {
										fileName = d.getMovedToPath().lastSegment();
										fileName = fileName.substring(0, fileName.length() - 2);
										if (d.getMovedToPath().removeLastSegments(1).append(fileName).equals(_to)) {
											foundDelta = d;
										}
									}
								}
							}
							if (foundDelta != null) {
								_to = foundDelta.getMovedToPath();
								if (_to.lastSegment().endsWith("di")) {
									fileName = _to.lastSegment();
									fileName = fileName.substring(0, fileName.length() - 2);
									_to = _to.removeLastSegments(1).append(fileName);
								}
							}
							applyModifications(f, _to.toString(), ids, movedFiles);
						}
					}

				} catch (Exception e) {
					//Nothing to do
					e.printStackTrace();
				}
			}
		}

		//Search children
		for (IResourceDelta d : delta.getAffectedChildren()) {
			manageDifferences(d, movedFiles);
		}
	}

	private void applyModifications(IFile f, String newFile, List<String> ids, Collection<IResourceDelta> movedFiles) throws Exception {
		if (f.getName().endsWith(".application")) {
			applyModificationsOnApplicationModel(f, newFile, movedFiles);
		} else {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(f.getRawLocation().toFile());

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
		for (IResourceDelta d : movedFiles) {
			if (d.getMovedToPath() != null && d.getMovedToPath().equals(new Path(newFile))) {
				foundDelta = d;
			} else {
				if (d.getMovedToPath() != null && d.getMovedToPath().lastSegment().endsWith("di")) {
					String fileName = d.getMovedToPath().lastSegment();
					fileName = fileName.substring(0, fileName.length() - 2);
					if (d.getMovedToPath().removeLastSegments(1).append(fileName).equals(new Path(newFile))) {
						foundDelta = d;
					}
				}
			}
		}
		if (foundDelta != null) {
			from = foundDelta.getFullPath();
			if (from.lastSegment().endsWith("di")) {
				String fileName = from.lastSegment();
				fileName = fileName.substring(0, fileName.length() - 2);
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
						Method method = eobj.getClass().getMethod("getFile", new Class[0]);
						String path = method.invoke(eobj, new Object[0]).toString();
						if (path != null && path.equals(from.toString())) {
							//The good model is found
							Class<?>[] paramsType = new Class[] { String.class };
							method = eobj.getClass().getMethod("setFile", paramsType);
							method.invoke(eobj, new Object[] { newFile });
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
			String id = href.substring(href.lastIndexOf('#') + 1);
			if (list.contains(id)) {
				String newId = newFile + '#' + id;
				elt.setAttribute("href", newId);
			}
		}

		//Search in child nodes
		NodeList childs = elt.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			if (childs.item(i).getNodeType() == Node.ELEMENT_NODE) {
				applyModifications((Element) childs.item(i), file, newFile, list);
			}
		}
	}

	private List<IResourceDelta> getMovedModels(IResourceDelta delta) {
		List<IResourceDelta> result = new ArrayList<IResourceDelta>();
		List<IResourceDelta> moved = getMovedFiles(delta);
		for (IResourceDelta iResourceDelta : moved) {
			if (SIDEBuilderUtil.isModel(iResourceDelta.getResource()) || SIDEBuilderUtil.isDiagram(iResourceDelta.getResource())) {
				result.add(iResourceDelta);
			}
		}

		return result;
	}

	private List<IResourceDelta> getMovedFiles(IResourceDelta delta) {
		List<IResourceDelta> result = new ArrayList<IResourceDelta>();
		if (delta.getResource() instanceof IFile) {
			if (delta.getMovedFromPath() != null && delta.getMovedToPath() != null) {
				System.out.println("SIDEBuilder.getMovedFiles() as MOVED :" + delta.getFullPath());
				System.out.println("* delta  from :" + delta.getMovedFromPath());
				System.out.println("* delta  to :" + delta.getMovedToPath());
				result.add(delta);
			}
		} else {
			for (IResourceDelta d : delta.getAffectedChildren()) {
				result.addAll(getMovedFiles(d));
			}
		}
		return result;
	}

}
