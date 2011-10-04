package com.bluexml.side.util.antrunner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.util.componentmonitor.ComponentMonitor;
import com.bluexml.side.util.componentmonitor.Monitor;
import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.componentmonitor.headless.LabelHeadLess;
import com.bluexml.side.util.componentmonitor.headless.StyledTextHeadless;
import com.bluexml.side.util.componentmonitor.headless.progressBarHeadLess;
import com.bluexml.side.util.documentation.structure.enumeration.LogType;
import com.bluexml.side.util.generator.acceleo.SimpleAcceleoGenerator;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.ui.UIUtils;

public class AntFileGeneratorAction implements IObjectActionDelegate {
	protected static List<String> inUse = new ArrayList<String>();

	private static ISelection selection = null;
	private static IFile app = null;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) selection;
			if (iss.getFirstElement() instanceof IFile) {
				final IFile rwm_model = (IFile) iss.getFirstElement();
				// launch generation only if no other generation is launched
				if (!inUse(rwm_model)) {
					addFileUnUse(rwm_model);

					generate(rwm_model);
					removeFileUnUse(rwm_model);
				}
			} else {
				UIUtils.showError(Activator.Messages.getString("Erreur.Title.1"), Activator.Messages.getString("Erreur.Msg.1")); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		
	}

	/**
	 * @param rwm_model
	 */
	public static void generate(final IFile rwm_model) {
		if (selection == null) {
			final List<IFile> l = new ArrayList<IFile>();
			l.add(rwm_model);
			selection = new IStructuredSelection() {
				public boolean isEmpty() {
					return false;
				}

				public List<IFile> toList() {
					return l;
				}

				public Object[] toArray() {
					return l.toArray();
				}

				public int size() {
					return l.size();
				}

				public Iterator<IFile> iterator() {
					return l.iterator();
				}

				public Object getFirstElement() {
					return l.get(0);
				}
			};
		}
		String parent = IFileHelper.convertIRessourceToFile(rwm_model).getParent();
		ComponentMonitor monitor = getHeadlessComponantMonitor(null, parent + File.separator + "build.xml.log", "conf", "_.xml");

		HashMap<String, String> configurationParameters = new HashMap<String, String>();
		String parentIFilePath = rwm_model.getParent().toString().substring(1);
		configurationParameters.put(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral(), parentIFilePath);
		configurationParameters.put("configurationName", "sideLog");
		monitor.initialize(0, configurationParameters, LogType.CONSOLE, "generator.log");
		String metamodelURI = "http://www.bluexml.com/application/1.0/";
		List<String> templates = new ArrayList<String>();
		templates.add("/com.bluexml.side.Util.antRunner/com/bluexml/side/util/antrunner/templates/build.xml.mt");

		SimpleAcceleoGenerator gen = new SimpleAcceleoGenerator(metamodelURI, templates, ".antBuildGenerator", monitor);

		try {
			gen.generate(rwm_model, parentIFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			UIUtils.showError("Error when generation", e.getClass().getName());
		}
		selection = null;
	}

	private static ComponentMonitor getHeadlessComponantMonitor(Monitor generalMonitor, String generatorLog, String configurationName, String fileName) {
		ProgressBarInterface progressBar2 = new progressBarHeadLess();
		LabelInterface label2 = new LabelHeadLess();
		StyledTextInterface styletext = new StyledTextHeadless();
		if (generalMonitor == null) {
			ProgressBarInterface progressBar = new progressBarHeadLess();
			LabelInterface label = new LabelHeadLess();
			generalMonitor = new Monitor(styletext, progressBar, label, generatorLog, configurationName, fileName);
		}
		ComponentMonitor componentMonitor = new ComponentMonitor(styletext, progressBar2, null, label2, generalMonitor, null, LogType.GENERATION, generalMonitor.getConsoleLog(), fileName);
		return componentMonitor;
	}

	protected boolean inUse(IFile model) {
		return inUse.contains(model.getLocation().toOSString());
	}

	protected void addFileUnUse(IFile model) {
		inUse.add(model.getLocation().toOSString());
	}

	protected void removeFileUnUse(IFile model) {
		inUse.remove(model.getLocation().toOSString());
	}

	public void selectionChanged(IAction action, ISelection _selection) {
		selection = _selection;
	}

	public static String getSelection(EObject obj) {
		String path = null;
		IFile model = getSelectionIFile();
		if (model != null) {
			path = model.getFullPath().toString();
		}

		return path;
	}

	private static IFile getSelectionIFile() {
		if (selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) selection;
			if (iss.getFirstElement() instanceof IFile) {
				final IFile rwm_model = (IFile) iss.getFirstElement();
				return rwm_model;
			}
		}
		return null;
	}
}
