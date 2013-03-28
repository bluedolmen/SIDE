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
package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newAlfrescoModule;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.execution.MavenExecutionResult;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import com.bluexml.side.Integration.eclipse.branding.enterprise.Activator;
import com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newAlfrescoModule.pages.GeneralProjectInformationsPage;
import com.bluexml.side.util.dependencies.MavenUtil;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.eclipse.EclipseUtils;
import com.bluexml.side.util.libs.eclipse.ExtensionPointUtils;

public class NewModuleWizard extends Wizard implements IWorkbenchWizard {
	// private static final String ARCHETYPE_REPO = "public";
	IProject currentProject;
	String pathToMavenProject = "/src/modules/mavenProjects";
	HashMap<String, Map<String, String>> archetypes = new HashMap<String, Map<String, String>>();

	public NewModuleWizard(IProject currentProject) {
		super();
		setNeedsProgressMonitor(true);
		this.currentProject = currentProject;

		// archetypes used to create new maven project to extends alfresco

		// get module list from extension point
		String nodeName = "moduleDependence";
		List<IConfigurationElement> configurationElements = ExtensionPointUtils.getConfigurationElements(com.bluexml.side.util.alfresco.tools.Activator.EXTENSION_POINT_TOOLING, nodeName);

		for (IConfigurationElement iConfigurationElement : configurationElements) {
			String moduleId = iConfigurationElement.getAttribute("moduleId");
			String versionMax = iConfigurationElement.getAttribute("versionMax");
			String label = iConfigurationElement.getAttribute("label");

			System.out.println("NewModuleWizard.NewModuleWizard() modulId :" + moduleId);
			HashMap<String, String> archetypeDef = new HashMap<String, String>();
			int lastIndexOf = moduleId.lastIndexOf(".");
			archetypeDef.put("archetypeGroupId", moduleId.substring(0, lastIndexOf));
			archetypeDef.put("archetypeArtifactId", moduleId.substring(lastIndexOf + 1));
			archetypeDef.put("archetypeVersion", versionMax);
			archetypeDef.put("interactive", "false");
			archetypeDef.put("webapp-name", "share");
			// archetype_amp.put("archetypeRepository", ARCHETYPE_REPO);
			archetypes.put(label, archetypeDef);
		}

	}

	@Override
	public boolean performFinish() {
		try {
			System.out.println("Finish called");

			// get values from the unique page
			GeneralProjectInformationsPage page = (GeneralProjectInformationsPage) getContainer().getCurrentPage();

			// execute maven goal
			final MavenUtil mu = new MavenUtil();

			final String goal = "archetype:generate";
			final File baseDir = getBaseDir();
			if (!baseDir.exists()) {
				baseDir.mkdirs();
			}
			String artifactId = page.getArtifactId();
			final File projectFile = new File(baseDir, artifactId);
			if (projectFile.exists()) {
				// error project already exist
				page.setErrorMessage("artifact :" + artifactId + " already exist, use another value");
				return false;
			}

			// setParameters
			final HashMap<String, String> params = new HashMap<String, String>();
			params.put("groupId", page.getGroupId());

			params.put("artifactId", artifactId);
			params.put("version", page.getArtifactVersion());
			params.put("project-name", page.getArtifactName());

			String moduleType = page.getModuleType();

			Map<String, String> map = archetypes.get(moduleType);
			if (map != null) {
				params.putAll(map);
			} else {
				System.err.println("moduleType do not match");
			}

			IRunnableWithProgress runPro = new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Start creating alfresco extension project", -1);
					System.out.println("launch maven execution");
					try {
						MavenExecutionResult result = mu.doMavenGoal(baseDir, new String[] { goal }, params, new String[] {}, false);
						if (result.getExceptions().size() > 0) {
							throw new Exception(result.getExceptions().get(0));
						}
						System.out.println("refresh baseDir folder");

						IFileHelper.refreshFolder(currentProject);

						System.out.println("import new eclipse project into workspace");
						EclipseUtils.importEclipseProject(projectFile);
					} catch (Exception e) {
						e.printStackTrace();
						throw new InterruptedException(e.getMessage());
					}
					monitor.done();
				}

			};

			this.getContainer().run(false, false, runPro);

		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), "Archetype fail to create project see log view for details", e.getLocalizedMessage());
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Alfresco Extension project creation fail !", e)); //$NON-NLS-1$
		}
		return true;
	}

	/**
	 * @return
	 */
	private File getBaseDir() {
		String osString = currentProject.getLocationURI().getPath();
		String replace = pathToMavenProject.replace("/", File.separator);
		return new File(osString + File.separator + replace);
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	@Override
	public void addPages() {
		super.addPages();
		addPage(new GeneralProjectInformationsPage());
	}

}
