package com.bluexml.side.Requirements.modeler.actions;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.bluexml.side.Requirements.modeler.actions.engine.MatchEngine;
import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.integration.eclipse.builder.SIDEBuilderUtil;
import com.bluexml.side.requirements.Annotation;
import com.bluexml.side.requirements.Goal;
import com.bluexml.side.requirements.Privilege;
import com.bluexml.side.requirements.PrivilegeGroup;
import com.bluexml.side.requirements.RequirementsDefinition;
import com.bluexml.side.requirements.RequirementsFactory;
import com.bluexml.side.requirements.util.RequirementsResource;
import com.bluexml.side.requirements.util.RequirementsResourceFactory;

public class BackupSpecification implements IObjectActionDelegate {

	private ISelection _selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		if (_selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) _selection;
			if (iss.getFirstElement() instanceof IFile) {
				IFile requirements_model = (IFile) iss.getFirstElement();
				
				Set<IEditorPart> editorsToClose = new HashSet<IEditorPart>();
				
				IWorkbench workbench = PlatformUI.getWorkbench();
				final IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
				for (IEditorReference ref : activePage.getEditorReferences()) {
					IEditorPart part = ref.getEditor(false);
					if (part instanceof com.bluexml.side.requirements.presentation.RequirementsEditor) {
						com.bluexml.side.requirements.presentation.RequirementsEditor editor = (com.bluexml.side.requirements.presentation.RequirementsEditor) part;
						
						for (Resource r : editor.getEditingDomain().getResourceSet().getResources()) {
							if (r.equals(requirements_model))
								editorsToClose.add(editor);
						}
					} else if (part instanceof com.bluexml.side.Requirements.modeler.editor.RequirementsEditor) {
						com.bluexml.side.Requirements.modeler.editor.RequirementsEditor editor = (com.bluexml.side.Requirements.modeler.editor.RequirementsEditor) part;

						for (Resource r : editor.getEditingDomain().getResourceSet().getResources()) {
							IPath ePath = new Path(r.getURI().devicePath().toString());
							ePath = ePath.removeFirstSegments(1);
							ePath = ePath.makeAbsolute();
							
							IPath mPath = requirements_model.getFullPath();
							String filename = mPath.lastSegment() + "di";
							mPath = mPath.removeLastSegments(1);
							mPath = mPath.append(filename);
							if (ePath.equals(mPath))
								editorsToClose.add(editor);
						}
					}
				}
				
				boolean cont = false;
				if (editorsToClose.size() > 0) {
					if (MessageDialog.openConfirm(null, "Close all editors", "Before backuping your model, you must close all editors. Do you agree ?")) {
						for (IEditorPart part : editorsToClose)
							activePage.closeEditor(part, true);
						cont = true;
					}
				} else
					cont = true;
						
				if (cont) {
					try {
						requirements_model.refreshLocal(IResource.DEPTH_ONE, null);
						IFile backupModel = SIDEBuilderUtil.getBackupModel(requirements_model);

						Resource res = getResource(requirements_model);
						RequirementsDefinition reqDefs = getDefinition(res);
						if (reqDefs != null) {
							String version = reqDefs.getVersion();
							System.out.println("Old version : "+version);
							if (version == null || version.length() == 0)
								version = "1.0";
							else {
								//increment version
								StringTokenizer st = new StringTokenizer(version,".");
								if (st.countTokens() == 2) {
									String major = st.nextToken();
									String minor = st.nextToken();
									version = major.concat("." + String.valueOf(Integer.valueOf(minor)+1));
								} else 
									version = "1.0";

							}

							System.out.println("New version : "+version);

							reqDefs.setVersion(version);
							reqDefs.setDate(new Date());
							//and save the model
							saveDefinition(reqDefs,requirements_model);
							requirements_model.refreshLocal(0, null);

							//Backup of the model
							String nameFile = requirements_model.getName().substring(0, requirements_model.getName().lastIndexOf(".")).concat("."+version).concat(".requirements");
							IPath target = backupModel.getFullPath();
							target = target.removeLastSegments(1);
							target = target.append(".backup."+nameFile);
							IResource container = backupModel.getParent(); 
							if (!container.exists() && container instanceof IFolder)
								SIDEBuilderUtil.prepareFolder((IFolder) container);
							requirements_model.copy(target, true, null);

							//compute risk
							computeRisk(requirements_model, version);
							requirements_model.refreshLocal(0, null);
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void computeRisk(IFile reqModel, String version) {
		IFile backupModel = SIDEBuilderUtil.getBackupModel(reqModel);
			
		try {
			String fileName = ".analysis." + reqModel.getName();
			IFile analysisModel = backupModel.getParent().getFile(new Path(fileName));
			if (!analysisModel.exists())
				initializeAnalysisModel(analysisModel);

			Resource res = getResource(analysisModel);
			RequirementsDefinition defs = getDefinition(res);

			Collection<Goal> previousGoals = collectAllGoals(res);
			Collection<Goal> currentGoals = collectAllGoals(reqModel);
			MatchEngine engine = new MatchEngine();
			engine.reset();

			for (Goal g : currentGoals) {
				Goal selected = null;
				double similarity = -1;
				Iterator<Goal> it = previousGoals.iterator();
				while (it.hasNext()) {
					Goal p = it.next();

					double new_similarity = engine.computeSimilarity(g,p);
					System.out.println("*"+g.getName()+"=="+p.getName()+" ["+new_similarity+"]");
					if (new_similarity > similarity) {
						selected = p;
						similarity = new_similarity;
					}
				}

				Goal go = cloneGoal(g);
				if (selected == null) {
					Annotation a = RequirementsFactory.eINSTANCE.createAnnotation();
					a.setAuthor("Risk analysis");
					a.setId(version);
					a.setAnnotation("1.0");
					go.getAnnotation().add(a);
					
					defs.getChildElements().add(go);
				} else {
					Annotation a = RequirementsFactory.eINSTANCE.createAnnotation();
					a.setAuthor("Risk analysis");
					a.setId(version);
					a.setAnnotation(String.valueOf(similarity));
					selected.getAnnotation().add(a);
					
					go.getAnnotation().addAll(selected.getAnnotation());
					defs.getChildElements().remove(selected);
					defs.getChildElements().add(go);
					System.out.println(g.getName()+"=="+selected.getName()+" ["+similarity+"]");
				}
			}

			saveDefinition(defs, analysisModel);
		} catch (Exception e) {
			//Nothing to do
			e.printStackTrace();
		}
	}

	private Goal cloneGoal(Goal g) {
		Goal result = RequirementsFactory.eINSTANCE.createGoal();
		result.setDocumentation(g.getDocumentation());
		result.setId(g.getId());
		result.setName(g.getName());
		result.setPriority(g.getPriority());
		
		for (PrivilegeGroup pg : g.getPrivilegeGroup()) {
			PrivilegeGroup pg2 = RequirementsFactory.eINSTANCE.createPrivilegeGroup();
			for (Privilege p : pg.getPrivileges()) {
				Privilege p2 = RequirementsFactory.eINSTANCE.createPrivilege();
				p2.setCategory(p.getCategory());
				pg2.getPrivileges().add(p2);
			}
			result.getPrivilegeGroup().add(pg2);
		}
		
		return result;
	}

	private Collection<Goal> collectAllGoals(Resource res) {
		Set<Goal> goals = new HashSet<Goal>();
		try {
			TreeIterator<EObject> it = res.getAllContents();
			while (it.hasNext()) {
				EObject o = it.next();
				if (o instanceof Goal) {
					Goal goal = (Goal) o;
					goals.add(goal);
				}
			}
		} catch (Exception e) {
			//Nothing to do
		}		
		return goals;
	}

	private void initializeAnalysisModel(IFile analysisModel) throws Exception {
		IResource container = analysisModel.getParent();
		if (container instanceof IFolder)
			SIDEBuilderUtil.prepareFolder((IFolder) container);
		
		RequirementsResourceFactory factory = new RequirementsResourceFactory();
		RequirementsResource res = (RequirementsResource) factory.createResource(URI.createURI(analysisModel.getFullPath().toString()));
		HashMap<String, Boolean> options = new HashMap<String, Boolean>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		res.getContents().add(RequirementsFactory.eINSTANCE.createRequirementsDefinition());
		res.save(options);
	}

	private Collection<Goal> collectAllGoals(IFile model) {
		Set<Goal> goals = new HashSet<Goal>();
		try {
			if (model.exists()) {
				Resource res = EResourceUtils.openModel(model.getLocation().toFile().toString(), new HashMap<Object, Object>());
				TreeIterator<EObject> it = res.getAllContents();
				while (it.hasNext()) {
					EObject o = it.next();
					if (o instanceof Goal) {
						Goal goal = (Goal) o;
						goals.add(goal);
					}
				}
			}
		} catch (Exception e) {
			//Nothing to do
		}		
		return goals;
	}

	private String computeFileNameVersion(IFile model, String version) {
		String fileName = ".backup.";
		fileName += model.getName().substring(0, model.getName().lastIndexOf('.')+1);
		fileName += version;
		fileName += model.getName().substring(model.getName().lastIndexOf('.'));
		return fileName;
	}

	private IFile getPreviousVersion(IFile model, String version) {
		String[] v = version.split("\\.");
		IFile versionedModel = null;
		if (v.length == 2) {
			int minor = Integer.valueOf(v[1]);
			int major = Integer.valueOf(v[0]);
			boolean found = false;
			if (major >= 0) {
				while (minor > 0 && !found) {
					minor = minor - 1;
					String new_v = major+"."+minor;
					String fileName = computeFileNameVersion(model, new_v);
					versionedModel = model.getParent().getFile(new Path(fileName));
					found = versionedModel.exists();
				}
			}
			minor = Integer.valueOf(v[1]);
			if (!found) {
				try {
					String prefix = ".backup.";
					prefix += model.getName().substring(0, model.getName().lastIndexOf('.')+1);
					
					int selectedMajor = -1;
					int selectedMinor = -1;
					
					for (IResource r : model.getParent().members()) {
						if (r instanceof IFile) {
							IFile file = (IFile) r;
							if (file.getName().startsWith(prefix)) {
								String current_version = file.getName().substring(prefix.length());
								int current_major = Integer.valueOf(current_version.substring(0, current_version.indexOf('.')));
								current_version = current_version.substring(current_version.indexOf('.')+1);
								int current_minor = Integer.valueOf(current_version.substring(0, current_version.indexOf('.')));
								boolean new_version_found = false;
								
								if (current_major != major || current_minor != minor)
									if (current_major <= major) 
										if (current_major > selectedMajor)
											new_version_found = true;
										else
											if (current_major == selectedMajor)
												if (current_minor > selectedMinor)
													new_version_found = true;
								
								if (new_version_found) {
									selectedMajor = current_major;
									selectedMinor = current_minor;
								}
							}
						}
					}
					String fileName = computeFileNameVersion(model, selectedMajor+"."+selectedMinor);
					versionedModel = model.getParent().getFile(new Path(fileName));
					if (!versionedModel.exists())
						versionedModel = null;
				} catch (CoreException e) {
					//Nothing to do
				}
			}
		}
		return versionedModel;
	}

	private void saveDefinition(RequirementsDefinition reqDefs, IFile model) {
		URI fileURI = URI.createURI(model.getLocationURI().toString());
		Resource resource = new RequirementsResourceFactory().createResource(fileURI);
		resource.getContents().add(reqDefs);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private RequirementsDefinition getDefinition(Resource res) {
		RequirementsDefinition reqDefs = null;
		reqDefs = (RequirementsDefinition) res.getContents().get(0);
		return reqDefs;
	}
	
	private Resource getResource(IFile rwm_model) {
		Resource res = null;
		try {
			// Register the XMI resource factory for the .requirements extension
			Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
			Map<String, Object> m = reg.getExtensionToFactoryMap();
			m.put("requirements", new RequirementsResourceFactory());
			ResourceSet resSet=new ResourceSetImpl();
			res = resSet.getResource(URI.createURI(rwm_model.getLocationURI().toString()),true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = selection;
	}

}
