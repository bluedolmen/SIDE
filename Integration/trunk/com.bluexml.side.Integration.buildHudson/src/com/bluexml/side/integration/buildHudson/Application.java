package com.bluexml.side.integration.buildHudson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.bluexml.side.integration.buildHudson.utils.FileHelper;
import com.bluexml.side.integration.buildHudson.utils.Utils;

public class Application {

	public static String workspace = "";
	public static String build_number = "";
	public static String svn_revision = "";

	// si au moins un paramètre n'est pas renseigné, alors on suppose que le
	// build est lancé sans hudson
	public static boolean parametre = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String argument1 = "";
		String argument2 = "";
		String argument3 = "";

		try {
			argument1 = args[0];
			argument2 = args[1];
			argument3 = args[3];
		} catch (Exception e) {
			parametre = false;
		}

		if ("-copy".equals(argument1)) {
			try {
				FileHelper.copyFiles(new File(Utils.getFinalDirectory()
						+ File.separator + Utils.getArchivePrefix()), new File(
						Utils.getPublicUpdateSiteDirectory()), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ("-copyright".equals(argument1)) {
			if (!"".equals(argument2)) {
				workspace = argument2;

				String[] projects = Utils.getProjects();

				// On met a jour le texte de la licence et du copyright
				for (int i = 0; i < projects.length; i++) {
					if (projects[i].indexOf("feature") != -1)
						Utils.updateCopyrightLicence(projects[i]);
				}
			} else {
				System.out
						.println("Vous devez renseigner le chemin vers la copie locale du répository (ex: /root/.hudson/jobs/'project name'/workspace)");
			}

			// Si des paramètres sont en entrée
		} else if (parametre) {

			workspace = argument1;
			build_number = argument2;
			svn_revision = argument3;

		} else {

			workspace = Utils.getBuildDirectory();
		}
		System.out.println("****************************************");
		System.out.println("**** Lancement du Build Automatique ****");
		System.out.println("****************************************");

		System.out.println("\nLancé le " + Utils.getDate2() + " à "
				+ Utils.getTime());

		// création du buildSVN.xml
		System.out.println("\n- Création de " + Utils.getBuildPath()
				+ File.separator + "buildSVN.xml");
		createFile(getCorpsSVN(), Utils.getBuildPath(), "buildSVN.xml");

		//si on travaille sans Hudson, alors on va réaliser, 
		//avec ant, le checkout et/ou update
		if (!parametre) {
			// Execution du buildSVN.xml
			System.out.println("\nRéalisation du checkout et du update...");
			execBuild("buildSVN", "build");
		}

		// Mise à jour des numéros de version en fonction du fichier de log
		System.out
				.println("\nMise à jour des numéros de version (si besoin)...");

		Utils.traitementUpdate();

		// Commit
		System.out.println("\nCommit des modifications sur le répository...");
		execBuild("buildSVN", "svnCommit");

		// création du build.xml
		System.out.println("\n\n- Création de " + Utils.getBuildPath()
				+ File.separator + "build.xml");
		createFile(getCorpsBuild(), Utils.getBuildPath(), "build.xml");

		// création du buildAuto.product
		System.out.println("- Création du buildAuto.product");
		createFile(getCorpsProduct(), Utils.getBuildPath(), "buildAuto.product");

		if (parametre) {
			// copie du répository dans le repertoire de travail (en séparant
			// les plugins et les features)
			Utils.preTraitement();
		}

		// Execution du build.xml
		System.out.println("\nRéalisation du Build sur ...");

		for (String projet : Utils.getProjects()) {
			System.out.println("\t-" + projet);
		}

		execBuild("build", "build");

		// création du site.xml
		System.out.println("\nUpdate du site.xml");
		Utils.updateSiteXml();

		// creation de jar pour les plugins qui ne le sont pas
		createFile(getJarBuilder(), Utils.getBuildPath(), "jarBuilder.xml");
		execBuild("jarBuilder", "jarBuilder");

		// traitement final

		// Déplacement et suppression des répertoires
		System.out.println("\nDéplacement et suppression des répertoires");
		Utils.finalTraitement();

		/*
		 * // Build des projets seul
		 * System.out.println("Build des projets seul ..."); for (String projet
		 * : Utils.getProjectsToBuild()) { System.out.println("\t-" + projet); }
		 * execBuild("build", "buildProject");
		 */
		System.out.println("\nFINISH !");
	}

	/**
	 * Méthode qui execute la target 'target' du build.xml passé en paramètre Un
	 * fichier de log est crée: log.txt
	 * 
	 * @param build
	 *            le build.xml a executer (sans le .xml)
	 * @param target
	 *            la target présente dans ce build à éxécuter
	 * 
	 */
	private static void execBuild(String build, String target) {
		// Update code
		Project ant = new Project();

		// add a listener to see ant's log
		org.apache.tools.ant.DefaultLogger log = new org.apache.tools.ant.DefaultLogger();
		try {
			log.setOutputPrintStream(new PrintStream(new File(Utils
					.getBuildPath()
					+ File.separator + "log" + build + target + ".txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		log.setMessageOutputLevel(Project.MSG_ERR);

		ant.addBuildListener(log);

		// building ant script
		File buildFile = new File(Utils.getBuildPath() + File.separator + build
				+ ".xml");
		ant.init();
		ProjectHelper.configureProject(ant, buildFile);
		ant.executeTarget(target);
	}

	/**
	 * Créer le fichier build.xml pour chaque projet
	 */
	private static void createFile(String corps, String folderName,
			String fileName) {
		File file = new File(folderName + File.separator + fileName);
		PrintWriter writer = null;
		try {
			file.createNewFile();

			writer = new PrintWriter(new FileWriter(file));
			writer.println(corps);

		} catch (IOException e) {
			e.printStackTrace();
		}

		writer.flush();
		writer.close();
	}

	/**
	 * Retourne le corps du fichier build.xml pour le projet donné
	 */
	private static String getCorpsBuild() {
		String[] projects = Utils.getProjects();

		String out = "<?xml version=\"1.0\"?>\n";
		out += "<project name=\"build\" default=\"build\">\n";
		out += "\t<property file=\"build.properties\" />\n";

		if (parametre) {
			out += "\n\t<target name=\"build\" depends=\"pde-build, post-build, genJavadoc\" />\n";
		} else {
			out += "\n\t<target name=\"build\" depends=\"init, pde-build, post-build, genJavadoc\" />\n";
		}

		out += "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: init\n";
		out += "\t================================= -->\n\n";

		out += "\n\t<target name=\"init\">\n";
		out += "\t\t<delete verbose=\"true\" includeemptydirs=\"true\" dir=\"${buildDirectory}\" />\n";
		out += "\t\t<copy todir=\"${buildDirectory}\">\n";
		out += "\t\t\t<fileset dir=\"${buildDirectory}_CO\" includes=\"*/**\">\n";
		out += "\t\t\t</fileset>\n";
		out += "\t\t</copy>\n";
		out += "\t</target>\n";

		out += "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: pde-build\n";
		out += "\t================================= -->\n\n";

		out += "\t<target name=\"pde-build\">\n";
		out += "\t\t\t<java classname=\"org.eclipse.equinox.launcher.Main\" fork=\"true\" failonerror=\"true\">\n";
		out += "\t\t\t\t<arg value=\"-application\" />\n";
		out += "\t\t\t\t<arg value=\"org.eclipse.ant.core.antRunner\" />\n";
		out += "\t\t\t\t<arg value=\"-buildfile\" />\n";
		out += "\t\t\t\t<arg value=\"${eclipseLocation}/plugins/org.eclipse.pde.build_${pdeBuildPluginVersion}/scripts/productBuild/productBuild.xml\" />\n";
		out += "\t\t\t\t<arg value=\"-Dtimestamp=${timestamp}\" />\n";
		out += "\t\t\t\t<classpath>\n";
		out += "\t\t\t\t\t<pathelement location=\"${eclipseLocation}/plugins/org.eclipse.equinox.launcher_${equinoxLauncherPluginVersion}.jar\" />\n";
		out += "\t\t\t\t</classpath>\n";
		out += "\t\t\t</java>\n";
		out += "\t</target>\n";

		out += "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: post-build\n";
		out += "\t================================= -->\n\n";

		out += "\n\t<target name=\"post-build\">\n";
		out += "\t\t<unzip src=\"${buildDirectory}/${buildLabel}/${buildId}-${baseos}.${basews}.${basearch}.zip\" dest=\"${buildDirectory}/${buildLabel}/\"/>\n";
		out += "\n\t\t<delete file=\"${buildDirectory}/${buildLabel}/${archivePrefix}/launcher.exe\" />\n";
		out += "\n\t\t<delete dir=\"${buildDirectory}/${buildLabel}/${archivePrefix}/plugins/org.eclipse.equinox.launcher.${equinoxLauncherDirectoryVersion}\" />\n";
		out += "\t\t<delete file=\"${buildDirectory}/${buildLabel}/${archivePrefix}/plugins/org.eclipse.equinox.launcher_${equinoxLauncherPluginVersion}.jar\" />\n\n";
		out += "\t\t<mkdir dir=\"${buildDirectory}/${buildLabel}/${archivePrefix}/features\"/>\n";

		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1) {
				out += "\t\t<jar destfile=\"${buildDirectory}/${buildLabel}/${archivePrefix}/features/"
						+ projects[i]
						+ "_"
						+ Utils.getVersionNumber(projects[i])
						+ ".jar\" basedir=\"${buildDirectory}/features/"
						+ projects[i] + "\" />\n";
				out += "\t\t<delete dir=\"${buildDirectory}/${buildLabel}/${archivePrefix}/features/"
						+ projects[i] + "\" />\n\n";
			}
		}

		out += "\t</target>\n";

		out += getGenJavadoc();

		//out += getBuildProject();

		out += "</project>\n";
		return out;
	}

	/**
	 * Retourne le corps du fichier buildSVN.xml
	 */
	private static String getCorpsSVN() {
		String out = "<?xml version=\"1.0\"?>\n";
		out += "<project name=\"build\" default=\"build\">\n";
		out += "\t<property file=\"build.properties\" />\n";
		out += "\t<property name=\"antLib\" value=\"" + Utils.getBuildPath()
				+ File.separator + "lib\" />\n\n";
		out += "\t<!-- load the svn task -->\n";
		out += "\t<path id=\"project.classpath.ant\">\n";
		out += "\t\t<pathelement location=\"${antLib}" + File.separator
				+ "svnant.jar\" />\n";
		out += "\t\t<pathelement location=\"${antLib}" + File.separator
				+ "svnClientAdapter.jar\" />\n";
		out += "\t\t<pathelement location=\"${antLib}" + File.separator
				+ "svnjavahl.jar\" />\n";
		out += "\t</path>\n";
		out += "\t<taskdef resource=\"svntask.properties\" classpathref=\"project.classpath.ant\" />\n";

		out += "\n\t<target name=\"build\" depends=\"clean, svnCO, svnUD\" />\n";

		out += "\n\t<target name=\"clean\">\n";
		out += "\t\t<delete dir=\"${buildDirectory}\" />\n";
		out += "\t</target>\n";

		out += getTargetSvnCO();
		out += getTargetSvnUD();
		out += getTargetSvnCommit();

		out += "</project>\n";
		return out;
	}

	/**
	 * Retourne le corps du product
	 */
	private static String getCorpsProduct() {
		String[] projects = Utils.getProjects();

		String out = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		out += "<?pde version=\"3.4\"?>\n";

		out += "<product name=\"buildAuto\" useFeatures=\"false\">\n";
		out += "\t<configIni use=\"default\">\n";
		out += "\t</configIni>\n";
		out += "\t<launcherArgs>\n";
		out += "\t\t<vmArgsMac>-XstartOnFirstThread -Dorg.eclipse.swt.internal.carbon.smallFonts</vmArgsMac>\n";
		out += "\t</launcherArgs>\n";
		out += "\t<windowImages/>\n";
		out += "\t<launcher>\n";
		out += "\t\t<solaris/>\n";
		out += "\t\t<win useIco=\"false\">\n";
		out += "\t\t\t<bmp/>\n";
		out += "\t\t</win>\n";
		out += "\t</launcher>\n";
		out += "\t<vm></vm>\n";

		out += "\t<plugins>\n";
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") == -1) {
				out += "\t\t<plugin id=\"" + projects[i] + "\"/>\n";
			}
		}
		out += "\t</plugins>\n";

		out += "\t<features>\n";
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1) {
				out += "\t\t<feature id=\"" + projects[i] + "\" version=\""
						+ Utils.getVersionNumber(projects[i]) + "\"/>\n";
			}
		}
		out += "\t</features>\n";
		out += "</product>\n";

		return out;
	}

	/**
	 * @deprecated <i> Plus utilisé car maintenant on traite et on modifie
	 *             directement le fichier<br/>
	 *             (utiliser la méthode updateSiteXML de la classe Utils)</i><br/>
	 * <br/>
	 *             Retourne le corps du site.xml
	 * 
	 */
	@SuppressWarnings("unused")
	private static String getCorpsSite() {
		String[] projects = Utils.getProjects();
		ArrayList<String> categories = new ArrayList<String>();

		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1) {
				String[] branche = projects[i].split("\\.");
				if (!categories.contains(branche[3])) {
					categories.add(branche[3]);
				}
			}
		}

		String out = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		out += "<site>\n\n";
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1) {
				out += "\t<feature url=\"features/" + projects[i] + "_"
						+ Utils.getVersionNumber(projects[i]) + ".jar\" id=\""
						+ projects[i] + "\" version=\""
						+ Utils.getVersionNumber(projects[i]) + "\">\n";
				String[] branche = projects[i].split("\\.");
				out += "\t\t<category name=\"S-IDE " + branche[3] + "\"/>\n";
				out += "\t</feature>\n\n";
			}
		}
		for (String category : categories) {
			out += "\t<category-def name=\"S-IDE " + category
					+ "\" label=\"S-IDE " + category + "\"/>\n";
		}
		out += "\n</site>\n";

		return out;
	}

	/**
	 * Retourne le corps de la target svnCO
	 */
	private static String getTargetSvnCO() {
		String out = "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: svnCO\n";
		out += "\t================================= -->\n\n";

		out += "\t<target name=\"svnCO\" depends=\"\" description=\"description\">\n";
		out += "\t\t<property name=\"featuresPath\" value=\"${buildDirectory}_CO"
				+ File.separator + "features\" />\n";
		out += "\t\t<property name=\"pluginsPath\" value=\"${buildDirectory}_CO"
				+ File.separator + "plugins\" />\n";

		if (!new File(Utils.getBuildDirectory() + "_CO").exists())
			new File(Utils.getBuildDirectory() + "_CO").mkdir();

		String[] projects = Utils.getProjects();

		// On va lister la liste des dossier qui se trouve dans
		// ${buildDirectory}_CO et si un projet qui est dans la liste de
		// build.properties n'est pas dans un des dossiers, alors on va faire un
		// checkout dessus
		File featureDir = new File(Utils.getBuildDirectory() + "_CO"
				+ File.separator + "features");
		File pluginsDir = new File(Utils.getBuildDirectory() + "_CO"
				+ File.separator + "plugins");

		ArrayList<String> finalListDirectories = new ArrayList<String>();

		if (featureDir.exists() && pluginsDir.exists()) {
			for (String element : featureDir.list()) {
				if (new File(Utils.getBuildDirectory() + "_CO" + File.separator
						+ "features" + File.separator + element).isDirectory()) {
					finalListDirectories.add(element);
				}
			}

			for (String element : pluginsDir.list()) {
				if (new File(Utils.getBuildDirectory() + "_CO" + File.separator
						+ "plugins" + File.separator + element).isDirectory()) {
					finalListDirectories.add(element);
				}
			}
		}

		for (int i = 0; i < projects.length; i++) {
			if (!finalListDirectories.contains(projects[i])) {
				out += "\t\t<svn>\n";

				// si le mot 'feature' n'est pas présent dans le nom du projet
				if (projects[i].indexOf("feature") == -1)
					out += "\t\t\t<checkout url=\"" + Utils.getRepository()
							+ "S-IDE/" + Utils.getProjectPath(projects[i])
							+ "/trunk/" + projects[i]
							+ "\" destPath=\"${pluginsPath}" + File.separator
							+ projects[i] + "\" />\n";
				// si 'feature' est présent
				else if (projects[i].indexOf("feature") != -1)
					out += "\t\t\t<checkout url=\"" + Utils.getRepository()
							+ "S-IDE/" + Utils.getProjectPath(projects[i])
							+ "/trunk/" + projects[i]
							+ "\" destPath=\"${featuresPath}" + File.separator
							+ projects[i] + "\" />\n";

				out += "\t\t</svn>\n";
			}
		}
		out += "\t</target>\n";
		return out;
	}

	/**
	 * Retourne le corps de la target svnUD
	 */
	private static String getTargetSvnUD() {
		String[] projects = Utils.getProjects();

		String out = "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: svnUD\n";
		out += "\t================================= -->\n\n";

		out += "\t<target name=\"svnUD\" depends=\"\" description=\"description\">\n";

		out += "\t\t<svn>\n";
		for (int i = 0; i < projects.length; i++) {

			// si le mot 'feature' n'est pas présent dans le nom du projet
			if (projects[i].indexOf("feature") == -1)
				out += "\t\t\t<update dir=\"${buildDirectory}_CO"
						+ File.separator + "plugins" + File.separator
						+ projects[i] + "\" recurse=\"yes\"/>\n";
			// si 'feature' est présent
			else if (projects[i].indexOf("feature") != -1)
				out += "\t\t\t<update dir=\"${buildDirectory}_CO"
						+ File.separator + "features" + File.separator
						+ projects[i] + "\" recurse=\"yes\"/>\n";
		}
		out += "\t\t</svn>\n";

		out += "\t</target>\n";
		return out;
	}

	/**
	 * Retourne le corps de la target svnUD
	 */
	private static String getTargetSvnCommit() {
		String[] projects = null;

		String out = "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: svnCommit\n";
		out += "\t================================= -->\n\n";

		out += "\t<target name=\"svnCommit\" depends=\"\" description=\"description\">\n";

		out += "\t\t<svn>\n";
		out += "\t\t\t<commit message=\"buildAuto du " + Utils.getDate2()
				+ "\">\n";
		
		for(int j=0; j < 2; j++){
			if (j == 0)
					projects = Utils.getProjects();
			if (j == 1 )
					projects = Utils.getVersionedProjects();
		
		for (int i = 0; i < projects.length; i++) {

			// si le mot 'feature' n'est pas présent dans le nom du projet
			if (projects[i].indexOf("feature") == -1) {
				out += "\t\t\t<fileset dir=\""
						+ Utils.getPathToLocalCopy(projects[i])
						+ File.separator + "META-INF\">\n";
				out += "\t\t\t\t<include name=\"MANIFEST.MF\" />\n";
				out += "\t\t\t</fileset>\n";

			} // si 'feature' est présent
			else if (projects[i].indexOf("feature") != -1) {
				out += "\t\t\t<fileset dir=\""
						+ Utils.getPathToLocalCopy(projects[i]) + "\">\n";
				out += "\t\t\t\t<include name=\"feature.xml\" />\n";
				out += "\t\t\t</fileset>\n";
			}
		}
		}
		out += "\t\t\t</commit>\n";
		out += "\t\t</svn>\n";

		out += "\t</target>\n";
		return out;
	}

	/**
	 * Retourne le corps de la target genJavadoc
	 */
	private static String getGenJavadoc() {
		String[] projects = Utils.getProjects();

		if (!new File(Utils.getBuildPath() + File.separator + "doc").exists())
			new File(Utils.getBuildPath() + File.separator + "doc").mkdir();

		String out = "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: genJavadoc\n";
		out += "\t================================= -->\n\n";

		out += "\t<target name=\"genJavadoc\" depends=\"\" description=\"description\">\n";
		out += "\t\t<javadoc destdir=\"${buildDir}" + File.separator
				+ "${codeName}" + File.separator + "doc" + File.separator
				+ Utils.getCodeName() + File.separator + "Javadoc\">\n";

		for (int i = 0; i < projects.length; i++) {
			// si le mot 'feature' n'est pas présent dans le nom du projet
			if (projects[i].indexOf("feature") == -1) {
				out += "\t\t\t<fileset dir=\""
						+ Utils.getPathToLocalCopy(projects[i]) + "\">\n";
				out += "\t\t\t\t<include name=\"**/*.java\" />\n";
				out += "\t\t\t</fileset>\n";
			}
		}
		out += "\t\t</javadoc>\n";

		out += "\t</target>\n";
		return out;
	}

	
	
	/**
	 * Retourne le corps de la target buildProject
	 */
	/*private static String getBuildProject() {
		String[] projects = Utils.getProjectsToBuild();

		String out = "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: buildProject\n";
		out += "\t================================= -->\n\n";

		out += "\t<target name=\"buildProject\" depends=\"\" description=\"description\">\n";
		for (int i = 0; i < projects.length; i++) {
			out += "\t\t\t<mkdir dir=\"" + Utils.getFinalDirectory()
					+ File.separator + "bin" + File.separator + "Ankle"
					+ File.separator + projects[i] + "\" />\n";
			out += "\t\t\t<javac destdir=\"" + Utils.getFinalDirectory()
					+ File.separator + "bin" + File.separator + "Ankle"
					+ File.separator + projects[i] + "\" srcdir=\"" + workspace
					+ File.separator + "S-IDE" + File.separator
					+ Utils.getProjectToBuildPath(projects[i]) + File.separator
					+ "trunk" + File.separator + projects[i] + File.separator
					+ "src" + "\">\n";
			out += "\t\t\t\t<classpath>\n";
			out += "\t\t\t\t\t<pathelement location=\"${eclipseLocation}/plugins/*\" />\n";
			out += "\t\t\t\t\t<pathelement location=\"" + workspace
					+ File.separator + "S-IDE" + File.separator
					+ Utils.getProjectToBuildPath(projects[i]) + File.separator
					+ "trunk" + File.separator + projects[i] + File.separator
					+ "*\" />\n";
			out += "\t\t\t\t</classpath>\n";
			out += "</javac>\n";
		}

		out += "\t</target>\n";
		return out;
	}*/

	private static String getJarBuilder() {

		String out = "<?xml version=\"1.0\"?>\n";
		out += "<project name=\"jarBuilder\" default=\"jarBuilder\">\n";
		out += "\t<property file=\"build.properties\" />\n";

		out += "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: jarBuilder\n";
		out += "\t================================= -->\n\n";

		out += "\t<target name=\"jarBuilder\" depends=\"\" description=\"description\">\n";

		// On va parcourir les plugins, et si des plugins n'ont pas étés mis en
		// jar on le fait manuelement
		File pluginRep = new File(Utils.getBuildDirectory() + File.separator
				+ Utils.getBuildLabel() + File.separator
				+ Utils.getArchivePrefix() + File.separator + "plugins");

		File[] list = pluginRep.listFiles();
		for (File file : list) {
			if (file.isDirectory()) {

				out += "\t\t<jar destfile=\"" + file.getAbsolutePath()
						+ ".jar\" basedir=\"" + file.getAbsolutePath()
						+ "\" manifest=\"" + file.getAbsolutePath()
						+ File.separator + "META-INF" + File.separator
						+ "MANIFEST.MF\"/>\n";

				out += "\t\t<delete dir=\"" + file.getAbsolutePath() + "\" />";
			}
		}

		out += "\t</target>\n";

		out += "</project>";

		return out;
	}

}
