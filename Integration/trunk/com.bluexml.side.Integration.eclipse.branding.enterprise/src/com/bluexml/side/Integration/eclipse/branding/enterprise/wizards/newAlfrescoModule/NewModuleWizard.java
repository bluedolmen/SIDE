package com.bluexml.side.Integration.eclipse.branding.enterprise.wizards.newAlfrescoModule;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.execution.MavenExecutionResult;
import org.eclipse.core.resources.IProject;
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

		// 3.2r2
		HashMap<String, String> archetype_amp = new HashMap<String, String>();
		archetype_amp.put("archetypeGroupId", "com.bluexml.side.Framework.maven");
		archetype_amp.put("archetypeArtifactId", "ampArchetypeForSide");
		archetype_amp.put("archetypeVersion", "1.0.5");
		archetype_amp.put("interactive", "false");
		// archetype_amp.put("archetypeRepository", ARCHETYPE_REPO);
		archetypes.put(ModuleType.AMP_32R2CE.label, archetype_amp);

		HashMap<String, String> archetype_share = new HashMap<String, String>();
		archetype_share.put("archetypeGroupId", "com.bluexml.side.Framework.maven");
		archetype_share.put("archetypeArtifactId", "warPatchArchetypeForSide");
		archetype_share.put("archetypeVersion", "1.0.5");
		archetype_share.put("webapp-name", "share");
		archetype_share.put("interactive", "false");
		// archetype_share.put("archetypeRepository", ARCHETYPE_REPO);		
		archetypes.put(ModuleType.SHARE_32R2CE.label, archetype_share);

		// 3.4.d CE
		HashMap<String, String> archetype_amp_34dCE = new HashMap<String, String>();
		archetype_amp_34dCE.put("archetypeGroupId", "com.bluexml.side.Framework.maven");
		archetype_amp_34dCE.put("archetypeArtifactId", "ampArchetypeForSide_34dCE");
		archetype_amp_34dCE.put("archetypeVersion", "1.0.0");
		archetype_amp_34dCE.put("interactive", "false");
		// archetype_amp.put("archetypeRepository", ARCHETYPE_REPO);
		archetypes.put(ModuleType.AMP_34dCE.label, archetype_amp_34dCE);

		HashMap<String, String> archetype_share_34dCE = new HashMap<String, String>();
		archetype_share_34dCE.put("archetypeGroupId", "com.bluexml.side.Framework.maven");
		archetype_share_34dCE.put("archetypeArtifactId", "warPatchArchetypeForSide_34dCE");
		archetype_share_34dCE.put("archetypeVersion", "1.0.0");
		archetype_share_34dCE.put("webapp-name", "share");
		archetype_share_34dCE.put("interactive", "false");
		// archetype_share.put("archetypeRepository", ARCHETYPE_REPO);		
		archetypes.put(ModuleType.SHARE_34dCE.label, archetype_share_34dCE);

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

	public enum ModuleType {
		AMP_32R2CE("Alfresco Extension 3.2r2 CE"), SHARE_32R2CE("Alfresco Share Extension 3.2r2 CE"), AMP_34dCE("Alfresco Extension 3.4.d CE"), SHARE_34dCE("Alfresco Share Extension 3.4.d CE");
		String label;

		ModuleType(String label) {
			this.label = label;
		}

		@Override
		public String toString() {
			return label;
		}
	}
}
