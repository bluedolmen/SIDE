package com.bluexml.side.integration.build;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.bluexml.side.integration.build.utils.FileUtils;
import com.bluexml.side.integration.build.utils.Utils;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String arguement = "";
		try{
			arguement = args[0];
		}
		catch (Exception e){
			//nothing
		}
		if ("-copy".equals(arguement)) {
			try {
				FileUtils.copyDirectory(new File(Utils.getFinalDirectory() + File.separator + Utils.getArchivePrefix()), new File(Utils.getPublicUpdateSiteDirectory()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("****************************************");
			System.out.println("**** Lancement du Build Automatique ****");
			System.out.println("****************************************");

			System.out.println("\nLanc� le " + Utils.getDate2() + " � "
					+ Utils.getTime());
			// cr�ation du buildSVN.xml
			System.out.println("\n- Cr�ation de " + Utils.getBuildPath()
					+ File.separator + "buildSVN.xml");
			createFile(getCorpsSVN(), Utils.getBuildPath(), "buildSVN.xml");

			// Execution du buildSVN.xml
			System.out.println("\nR�alisation du checkout et du update...");
			execBuild("buildSVN", "build");

			// Mise � jour des num�ros de version en fonction du fichier de log
			System.out
					.println("\nMise � jour des num�ros de version (si besoin)...");
			Utils.traitementUpdate();

			// cr�ation du build.xml
			System.out.println("\n\n- Cr�ation de " + Utils.getBuildPath()
					+ File.separator + "build.xml");
			createFile(getCorpsBuild(), Utils.getBuildPath(), "build.xml");

			// cr�ation du buildAuto.product
			System.out.println("- Cr�ation du buildAuto.product");
			createFile(getCorpsProduct(), Utils.getBuildPath(),
					"buildAuto.product");

			// Execution du build.xml
			System.out.println("\nR�alisation du Build...");
			execBuild("build", "build");

			// cr�ation du site.xml
			System.out.println("\nUpdate du site.xml");
			// createFile(getCorpsSite(), Utils.getBuildPath(), "site.xml");
			Utils.updateSiteXml();

			// traitement final

			// Commit
			System.out
					.println("\nCommit des modifications sur le r�pository...");
			execBuild("buildSVN", "svnCommit");

			// D�placement et suppression des r�pertoires
			System.out.println("\nD�placement et suppression des r�pertoires");
			Utils.finalTraitement();

			System.out.println("\nFINISH !");
		}
	}

	/**
	 * M�thode qui execute la target 'target' du build.xml pass� en param�tre Un
	 * fichier de log est cr�e: log.txt
	 * 
	 * @param build
	 *            le build.xml a executer (sans le .xml)
	 * @param target
	 *            la target pr�sente dans ce build � �x�cuter
	 * 
	 */
	private static void execBuild(String build, String target) {
		// Update code
		Project ant = new Project();

		// add a listener to see ant's log
		org.apache.tools.ant.DefaultLogger log = new org.apache.tools.ant.DefaultLogger();
		// org.apache.tools.ant.DefaultLogger log2 = new
		// org.apache.tools.ant.DefaultLogger();
		// log.setOutputPrintStream(System.out);
		try {
			log
					.setOutputPrintStream(new PrintStream(new File(Utils
							.getBuildDirectory()
							+ "_CO"
							+ File.separator
							+ "log"
							+ build
							+ target
							+ ".txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		log.setMessageOutputLevel(Project.MSG_INFO);

		ant.addBuildListener(log);

		// building ant script
		File buildFile = new File(Utils.getBuildPath() + File.separator + build
				+ ".xml");
		ant.init();
		ProjectHelper.configureProject(ant, buildFile);
		ant.executeTarget(target);
	}

	/**
	 * Cr�er le fichier build.xml pour chaque projet
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
	 * Retourne le corps du fichier build.xml pour le projet donn�
	 */
	private static String getCorpsBuild() {
		String[] projects = Utils.getProjects();

		String out = "<?xml version=\"1.0\"?>\n";
		out += "<project name=\"build\" default=\"build\">\n";
		out += "\t<property file=\"build.properties\" />\n";
		out += "\n\t<target name=\"build\" depends=\"init, pde-build, post-build\" />\n";

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

		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1
					|| projects[i].equals("com.bluexml.side.Util")) {
				out += "\t\t<jar destfile=\"${buildDirectory}/${buildLabel}/${archivePrefix}/features/"
						+ projects[i]
						+ "_"
						+ Utils.getVersionNumber(projects[i])
						+ ".jar\" basedir=\"${buildDirectory}/${buildLabel}/${archivePrefix}/features/"
						+ projects[i]
						+ "_"
						+ Utils.getVersionNumber(projects[i]) + "\" />\n";
				out += "\t\t<delete dir=\"${buildDirectory}/${buildLabel}/${archivePrefix}/features/"
						+ projects[i]
						+ "_"
						+ Utils.getVersionNumber(projects[i]) + "\" />\n\n";
			}
		}

		out += "\t</target>\n";

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
		out += "\t<property name=\"antLib\" value=\"./lib\" />\n\n";
		out += "\t<!-- load the svn task -->\n";
		out += "\t<path id=\"project.classpath.ant\">\n";
		out += "\t\t<pathelement location=\"${antLib}/svnant.jar\" />\n";
		out += "\t\t<pathelement location=\"${antLib}/svnClientAdapter.jar\" />\n";
		out += "\t\t<pathelement location=\"${antLib}/svnjavahl.jar\" />\n";
		out += "\t</path>\n";
		out += "\t<taskdef resource=\"svntask.properties\" classpathref=\"project.classpath.ant\" />\n";

		out += "\n\t<target name=\"build\" depends=\"clean, svnCO, svnUD\" />\n";

		out += "\n\t<target name=\"clean\">\n";
		out += "\t\t<svn username=\""+Utils.getSvnUserName()+"\" password=\""+Utils.getSvnPassword()+"\" />\n";
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

		out += "<product name=\"buildAuto\" useFeatures=\"true\">\n";
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
			if (projects[i].indexOf("feature") == -1
					&& !projects[i].equals("com.bluexml.side.Util")) {
				out += "\t\t<plugin id=\"" + projects[i] + "\"/>\n";
			}
		}
		out += "\t</plugins>\n";

		out += "\t<features>\n";
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1
					|| projects[i].equals("com.bluexml.side.Util")) {
				out += "\t\t<feature id=\"" + projects[i] + "\" version=\""
						+ Utils.getVersionNumber(projects[i]) + "\"/>\n";
			}
		}
		out += "\t</features>\n";
		out += "</product>\n";

		return out;
	}

	/**
	 * @deprecated <i> Plus utilis� car maintenant on traite et on modifie
	 *             directement le fichier<br/>
	 *             (utiliser la m�thode updateSiteXML de la classe Utils)</i><br/>
	 * <br/>
	 *             Retourne le corps du site.xml
	 * 
	 */
	@SuppressWarnings("unused")
	private static String getCorpsSite() {
		String[] projects = Utils.getProjects();
		ArrayList<String> categories = new ArrayList<String>();

		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1
					|| projects[i].equals("com.bluexml.side.Util")) {
				String[] branche = projects[i].split("\\.");
				if (!categories.contains(branche[3])) {
					categories.add(branche[3]);
				}
			}
		}

		String out = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		out += "<site>\n\n";
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1
					|| projects[i].equals("com.bluexml.side.Util")) {
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

				// on r�cup�re le nom de la branche (Application, Util, etc ...)
				String[] branche = projects[i].split("\\.");

				if (branche[3].equals("Util"))
					branche[3] += "s";
				// si le mot 'feature' n'est pas pr�sent dans le nom du projet
				if (projects[i].indexOf("feature") == -1
						&& !projects[i].equals("com.bluexml.side.Util"))
					out += "\t\t\t<checkout url=\"" + Utils.getRepository()
							+ "S-IDE/" + branche[3] + "/trunk/" + projects[i]
							+ "\" destPath=\"${pluginsPath}" + File.separator
							+ projects[i] + "\" />\n";
				// si 'feature' est pr�sent
				else if (projects[i].indexOf("feature") != -1
						|| projects[i].equals("com.bluexml.side.Util"))
					out += "\t\t\t<checkout url=\"" + Utils.getRepository()
							+ "S-IDE/" + branche[3] + "/trunk/" + projects[i]
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

			// si le mot 'feature' n'est pas pr�sent dans le nom du projet
			if (projects[i].indexOf("feature") == -1
					&& !projects[i].equals("com.bluexml.side.Util"))
				out += "\t\t\t<update dir=\"${buildDirectory}_CO"
						+ File.separator + "plugins" + File.separator
						+ projects[i] + "\" recurse=\"yes\"/>\n";
			// si 'feature' est pr�sent
			else if (projects[i].indexOf("feature") != -1
					|| projects[i].equals("com.bluexml.side.Util"))
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
		String[] projects = Utils.getProjects();

		String out = "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: svnCommit\n";
		out += "\t================================= -->\n\n";

		out += "\t<target name=\"svnCommit\" depends=\"\" description=\"description\">\n";

		out += "\t\t<svn>\n";
		out += "\t\t\t<commit message=\"buildAuto du " + getDate() + "\">\n";
		for (int i = 0; i < projects.length; i++) {

			// si le mot 'feature' n'est pas pr�sent dans le nom du projet
			if (projects[i].indexOf("feature") == -1
					&& !projects[i].equals("com.bluexml.side.Util")) {
				out += "\t\t\t<fileset dir=\"${buildDirectory}_CO"
						+ File.separator + "plugins" + File.separator
						+ projects[i] + File.separator + "META-INF\">\n";
				out += "\t\t\t\t<include name=\"MANIFEST.MF\" />\n";
				out += "\t\t\t</fileset>\n";

			} // si 'feature' est pr�sent
			else if (projects[i].indexOf("feature") != -1
					|| projects[i].equals("com.bluexml.side.Util")) {
				out += "\t\t\t<fileset dir=\"${buildDirectory}_CO"
						+ File.separator + "features" + File.separator
						+ projects[i] + "\">\n";
				out += "\t\t\t\t<include name=\"feature.xml\" />\n";
				out += "\t\t\t</fileset>\n";
			}
		}
		out += "\t\t\t</commit>\n";
		out += "\t\t</svn>\n";

		out += "\t</target>\n";
		return out;
	}

	public static String getDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}

}
