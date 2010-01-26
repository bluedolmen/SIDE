package com.bluexml.side.integration.buildHudson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;



import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.bluexml.side.integration.buildHudson.utils.FileHelper;
import com.bluexml.side.integration.buildHudson.utils.Utils;

public class Application {

	public static String workspace = "";
	public static String build_number = "";
	public static String build_id = "";
	public static String svn_revision = "";
	public static List<String> projectsExcluded;

	// si au moins un param�tre n'est pas renseign�, alors on suppose que le
	// build est lanc� sans hudson
	public static boolean parametre = true;
	
	// indique si on build la version enterprise ou labs
	//public static boolean EnterpriseRelease = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// arg[0] = -labs if build of Labs Release, -copy if transfert to final directory, build of Enterprise Release either
		String argument1 = "";
		// arg[1] = build workspace
		String argument2 = "";
		String argument3 = "";
		String argument4 = "";
		
		System.out.println("****************************************");
		System.out.println("**** Lancement du Build Automatique ****");
		System.out.println("****************************************");

		try {
			argument1 = args[0];
			argument2 = args[1];
			argument3 = args[2];
			argument4 = args[3];
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

			// Si des param�tres sont en entr�e
		} else if (parametre) {
//			if ("-labs".equals(argument1)) {
//				EnterpriseRelease = false;
//			}
			
			workspace = argument1;
			build_number = argument2;
			build_id = argument3;
			svn_revision = argument4;
			System.out.println("**** Parametre ****");
			System.out.println("- workspace = "+ workspace);
			System.out.println("- build_number = "+ build_number);
			System.out.println("- build_id = "+ build_id);
			System.out.println("- svn_revision = "+ svn_revision);

		} else {

			workspace = Utils.getBuildDirectory();
			System.out.println("**** Aucun Parametre ****");
			System.out.println("- workspace = "+ workspace);
		}
		
		
//		if (Application.EnterpriseRelease){
			projectsExcluded = Utils.getProjects("projectExcluded");
//		}else{
//			projectsExcluded = Utils.getProjects("projectLabsExcluded");
//		}

		System.out.println("\nLanc� le " + Utils.getDate2() + " � "
				+ Utils.getTime());

		// cr�ation du buildSVN.xml
		System.out.println("\n- Cr�ation de " + Utils.getBuildPath()
				+ File.separator + "buildSVN.xml");
		createFile(getCorpsSVN(), Utils.getBuildPath(), "buildSVN.xml");

		//si on travaille sans Hudson, alors on va r�aliser, 
		//avec ant, le checkout et/ou update
		if (!parametre) {
			// Execution du buildSVN.xml
			System.out.println("\nR�alisation du checkout et du update...");
			execBuild("buildSVN", "build");
		}

		// Mise � jour des num�ros de version en fonction du fichier de log
		//System.out.println("\nMise � jour des num�ros de version (si besoin)...");
        
		// si labs, on ne met pas � jour les versions des features et on ne commit pas

//		if (EnterpriseRelease) {
			Utils.traitementUpdate();
			// Commit
			// commit is now done at the end of the complete build when all steps (till updae-site copy) are ok
			//System.out.println("\nCommit des modifications sur le r�pository...");
			//execBuild("buildSVN", "svnCommit");
//		}

		if (parametre) {
			// copie du r�pository dans le repertoire de travail (en s�parant
			// les plugins et les features)
			Utils.preTraitement();
		}
		
		// cr�ation du build.xml
		System.out.println("\n\n- Cr�ation de " + Utils.getBuildPath()
				+ File.separator + "build.xml");
		createFile(getCorpsBuild(), Utils.getBuildPath(), "build.xml");
		
		// cr�ation du buildAuto.product
		System.out.println("- Cr�ation du buildAuto.product");
		createFile(getCorpsProduct(), Utils.getBuildPath(), "buildAuto.product");

		// Execution du build.xml
		System.out.println("\nR�alisation du Build sur ...");
		
		for (String projet : Utils.getProjects()) {
			if (!projectsExcluded.contains(projet)){
				System.out.println("\t-" + projet);
			}
		}
		for (String projet : Utils.getProjects("projectToVersioned")) {
			if (!projectsExcluded.contains(projet)){
				System.out.println("\t-" + projet);
			}
		}
		
		//create maven work folder and launch maven deploy
		launchMavenDeploy();
		
		//launch script to build repository zip file
		
		
		
		execBuild("build", "build");

		// cr�ation du site.xml
		System.out.println("\nUpdate du site.xml");
		Utils.updateSiteXml();

		// creation de jar pour les plugins qui ne le sont pas
		createFile(getJarBuilder(), Utils.getBuildPath(), "jarBuilder.xml");
		execBuild("jarBuilder", "jarBuilder");

		// traitement final

		// D�placement et suppression des r�pertoires
		System.out.println("\nD�placement et suppression des r�pertoires");
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
	 * M�thode qui execute lance la tache maven deploy
	 * fichier de log est cr�e: log_maven.txt
	 * @throws IOException 
	 * 
	 * 
	 *          
	 * 
	 *            
	 * 
	 */
	private static void launchMavenDeploy()  {
		
			
		Runtime r = Runtime.getRuntime();
		Process p;
	
	    try {
	      // file contains unsorted data
	    	String SIDE_path=Utils.getBuildPath() + File.separator + Utils.repositoryCopy;
	    	System.out.println("."+Utils.getBuildPath() + File.separator+"launch_maven.sh "+workspace+ " "+SIDE_path);
	    
	      p = r.exec(Utils.getBuildPath() + File.separator+"launch_maven.sh "+workspace+ " "+SIDE_path);
	
	      p.waitFor();
	    } catch (java.io.IOException e) {
	      System.err.println("I/O error: " + e);
	    } catch (InterruptedException e) {
	      // nothing to do
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

/*	pb de outOfmemory sur certaines machines quand utilisation de projet ant 
 *   -> autre piste de solution ci-dessous en lancant en shell:	
		try {
		String cmd="ant";
		Process proc=Runtime.getRuntime().exec(tableau);

        Process proc=Runtime.getRuntime().exec("ant build");
        InputStream in = proc.getInputStream();
        BufferedWriter out=new BufferedWriter(new FileWriter("titi.dat"));        
        int c;
        while ((c = in.read()) != -1) {
            out.write((char)c);
        }        
        in.close();
        out.flush();
        out.close();    
       } catch (Exception e) {
          e.printStackTrace();
       }
*/
		
		
		
		
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
		log.setErrorPrintStream(System.err);
//		log.setOutputPrintStream(System.out);
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
		List<String> l = new ArrayList<String>();
		l.addAll(Utils.getProjects());
		l.addAll(Utils.getProjects("projectToVersioned"));
		String[] projects = toArray(l);

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
//		out += "\t\t\t<record name=\"build_pde-build_verbose.log\" loglevel=\"verbose\" action=\"start\"/>\n";
		out += "\t\t\t<record name=\"build_pde-build_debug.log\" loglevel=\"debug\" action=\"start\"/>\n";
//		out += "\t\t\t<chmod dir=\"${buildName}\" perm=\"777\" includes=\"*/**\"/>\n";
		out += "\t\t\t<java classname=\"org.eclipse.equinox.launcher.Main\" fork=\"true\" failonerror=\"true\">\n";
		out += "\t\t\t\t<arg value=\"-Xmx512m\"/>\n";
		out += "\t\t\t\t<arg value=\"-Xms512m\"/>\n";
//		out += "\t\t\t\t<arg value=\"-Xss512k\"/>\n";
		out += "\t\t\t\t<arg value=\"-application\" />\n";
		out += "\t\t\t\t<arg value=\"org.eclipse.ant.core.antRunner\" />\n";
		out += "\t\t\t\t<arg value=\"-buildfile\" />\n";
		out += "\t\t\t\t<arg value=\"${eclipseLocation}/plugins/org.eclipse.pde.build_${pdeBuildPluginVersion}/scripts/productBuild/productBuild.xml\" />\n";
		out += "\t\t\t\t<arg value=\"-Dtimestamp=${timestamp}\" />\n";
		out += "\t\t\t\t<classpath>\n";
		out += "\t\t\t\t\t<pathelement location=\"${eclipseLocation}/plugins/org.eclipse.equinox.launcher_${equinoxLauncherPluginVersion}.jar\" />\n";
		out += "\t\t\t\t</classpath>\n";
		out += "\t\t\t</java>\n";
//		out += "\t\t\t<record name=\"build_pde-build_verbose.log\" action=\"stop\"/>\n";
		out += "\t\t\t<record name=\"build_pde-build_debug.log\" action=\"stop\"/>\n";
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
		String[] projects = toArray(Utils.getProjects());

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
				if (!projectsExcluded.contains(projects[i])){
					out += "\t\t<plugin id=\"" + projects[i] + "\"/>\n";
				}
			}
		}
		out += "\t</plugins>\n";

		out += "\t<features>\n";
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1) {
				if (!projectsExcluded.contains(projects[i])){
					out += "\t\t<feature id=\"" + projects[i] + "\" version=\""
						+ Utils.getVersionNumber(projects[i]) + "\"/>\n";
				}
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
		String[] projects = toArray(Utils.getProjects());
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
				out += "\t\t<category name=\"SIDE " + branche[3] + "\"/>\n";
				out += "\t</feature>\n\n";
			}
		}
		for (String category : categories) {
			out += "\t<category-def name=\"SIDE " + category
					+ "\" label=\"SIDE " + category + "\"/>\n";
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

		String[] projects = toArray(Utils.getProjects());

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

				// si le mot 'feature' n'est pas pr�sent dans le nom du projet
				if (projects[i].indexOf("feature") == -1)
					out += "\t\t\t<checkout url=\"" + Utils.getRepository()
							+ "S-IDE/" + Utils.getProjectPath(projects[i])
							+ "/trunk/" + projects[i]
							+ "\" destPath=\"${pluginsPath}" + File.separator
							+ projects[i] + "\" />\n";
				// si 'feature' est pr�sent
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
		String[] projects = toArray(Utils.getProjects());

		String out = "\n\t<!-- ================================= \n";
		out += "\t\t\ttarget: svnUD\n";
		out += "\t================================= -->\n\n";

		out += "\t<target name=\"svnUD\" depends=\"\" description=\"description\">\n";

		out += "\t\t<svn>\n";
		for (int i = 0; i < projects.length; i++) {

			// si le mot 'feature' n'est pas pr�sent dans le nom du projet
			if (projects[i].indexOf("feature") == -1)
				out += "\t\t\t<update dir=\"${buildDirectory}_CO"
						+ File.separator + "plugins" + File.separator
						+ projects[i] + "\" recurse=\"yes\"/>\n";
			// si 'feature' est pr�sent
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

		out += "\t\t<svn username=\"build-auto\" password=\"build.auto\">\n";
		out += "\t\t\t<commit message=\"buildAuto du " + Utils.getDate2()
				+ "\">\n";
		
		for(int j=0; j < 2; j++){
			if (j == 0) 
					projects = toArray(Utils.getProjects());
			if (j == 1 )
					projects = toArray(Utils.getVersionedProjects());
		
		for (int i = 0; i < projects.length; i++) {

			if (projects[i].length() > 0) {
				// si le mot 'feature' n'est pas pr�sent dans le nom du projet
				if (projects[i].indexOf("feature") == -1) {
					out += "\t\t\t<fileset dir=\""
							+ Utils.getPathToLocalCopy(projects[i])
							+ File.separator + "META-INF\">\n";
					out += "\t\t\t\t<include name=\"MANIFEST.MF\" />\n";
					out += "\t\t\t</fileset>\n";
					
					String fileFeaturePath = Utils.getPathToLocalCopy(projects[i])
					+ File.separator + "plugin.xml";
					
					boolean exists = (new File(fileFeaturePath)).exists();
					if (exists) { 
					
						out += "\t\t\t<fileset dir=\""
							+ Utils.getPathToLocalCopy(projects[i]) + "\">\n";
						out += "\t\t\t\t<include name=\"plugin.xml\" />\n";
						out += "\t\t\t</fileset>\n";
					}	
					
	
				} // si 'feature' est pr�sent
				else if (projects[i].indexOf("feature") != -1) {
					out += "\t\t\t<fileset dir=\""
							+ Utils.getPathToLocalCopy(projects[i]) + "\">\n";
					out += "\t\t\t\t<include name=\"feature.xml\" />\n";
					out += "\t\t\t</fileset>\n";
				}
			}
		}
		}
		
		// fichier pom.xml
		Utils.listefichierpom=new ArrayList();
		//String pathproject = Utils.getBuildPath() + File.separator + Utils.repositoryCopy;
		String pathproject = workspace + "/S-IDE";
		
		Utils.findFile(new File(pathproject+"/Integration/trunk"),"pom.xml");
		Utils.findFile(new File(pathproject+"/FrameworksModules/trunk"),"pom.xml");
		System.out.println("#### Utils.listefichierpom.size()=" + Utils.listefichierpom.size() );
		System.out.println("#### Utils.listefichierpom=" + Utils.listefichierpom );
		if (Utils.listefichierpom.size() != 0) {
			for (String pom : Utils.listefichierpom) {
				String [] tab=pom.split("/pom.xml");
				
				out += "\t\t\t<fileset dir=\""
					+ tab[0] + "\">\n";
				out += "\t\t\t\t<include name=\"pom.xml\" />\n";
				out += "\t\t\t</fileset>\n";
				System.out.println("#### tab=" + tab[0] );
			}
			}
		out += "\t\t\t</commit>\n";
		out += "\t\t</svn>\n";

		out += "\t</target>\n";
		return out;
	}

	private static String[] toArray(List<String> l) {
		String[] array = new String[l.size()];
		int i = 0;
		for (String string : l) {
			array[i] = string;
			i++;
		}
		return array;
	}

	/**
	 * Retourne le corps de la target genJavadoc
	 */
	private static String getGenJavadoc() {
		List<String> list = new ArrayList<String>();
		list.addAll(Utils.getProjects());
		list.addAll(Utils.getProjects("projectToVersioned"));
		
		String[] projects = toArray(list);

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
			// si le mot 'feature' n'est pas pr�sent dans le nom du projet
			if (!projectsExcluded.contains(projects[i])){
				if (projects[i].indexOf("feature") == -1) {
					out += "\t\t\t<fileset dir=\""
							+ Utils.getPathToLocalCopy(projects[i]) + "\">\n";
					out += "\t\t\t\t<include name=\"**/*.java\" />\n";
					out += "\t\t\t</fileset>\n";
				}
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

		// On va parcourir les plugins, et si des plugins n'ont pas �t�s mis en
		// jar on le fait manuelement
		File pluginRep = new File(Utils.getBuildDirectory() + File.separator
				+ Utils.getBuildLabel() + File.separator
				+ Utils.getArchivePrefix() + File.separator + "plugins");

		File[] list = pluginRep.listFiles();
		for (File file : list) {
			if (!projectsExcluded.contains(file.getName())){
				if (file.isDirectory()) {
					
					out += "\t\t<jar destfile=\"" + file.getAbsolutePath()
							+ ".jar\" basedir=\"" + file.getAbsolutePath()
							+ "\" manifest=\"" + file.getAbsolutePath()
							+ File.separator + "META-INF" + File.separator
							+ "MANIFEST.MF\"/>\n";
	
					out += "\t\t<delete dir=\"" + file.getAbsolutePath() + "\" />";
				}
			}
		}

		out += "\t</target>\n";

		out += "</project>";

		return out;
	}

}
