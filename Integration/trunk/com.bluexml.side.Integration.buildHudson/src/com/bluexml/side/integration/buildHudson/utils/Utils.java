package com.bluexml.side.integration.buildHudson.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.bluexml.side.integration.buildHudson.Application;

public class Utils {

	private static ArrayList<String> listeFeatureModif = new ArrayList<String>();
	private static String revisionNumber;
	public static ArrayList<String> listefichierpom = new ArrayList<String>();
	public static String repositoryCopy = "repositoryCopy";

	/**
	 * Mï¿½thode qui ouvre le fichier de proprerties
	 * 
	 */
	public static Properties ouvrirFichier(String fichier) {
		String[] filePart = fichier.split("\\.");
		String fileName = filePart[0];
		String userName = System.getenv("USER");
		String userPropertyFile = fileName + "." + userName + "." + filePart[1];

		File props = new File(userPropertyFile);
		if (!props.exists()) {
			props = new File(fichier);
		}
		FileInputStream fileStream = null;
		Properties properties = null;

		try {
			fileStream = new FileInputStream(props);

			properties = new Properties();

			properties.load(fileStream);
			// System.out.println("Properties File loaded :" +
			// props.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return properties;
	}

	/**
	 * Retourne la liste des projets
	 */
	public static List<String> getProjects() {
		return getProjects("project");
	}

	/**
	 * Retourne la liste des projets
	 */
	public static List<String> getProjects(String properties) {
		String property = ouvrirFichier("build.properties").getProperty(properties);
		List<String> l = new ArrayList<String>();
		if ((property != null) && (property.length() > 0)) {
			String[] projects = property.split(",");

			if (projects.length > 0) {
				for (int i = 0; i < projects.length; i++) {
					String projectName = projects[i].split("&")[1];
					projectName.trim().replaceAll("\n", "");
					if (projectName.length() > 0) {
						l.add(projectName);
					}
				}
			}
		}

		return l;
	}

	/**
	 * Retourne la liste des projets a versionner
	 */
	public static List<String> getVersionedProjects() {
		return getProjects("projectToVersioned");
	}

	/**
	 * Retourne le chemin pour un projet donnï¿½ (par exemple
	 * MetaModel/Application pour le projet com.bluexml.side.Application
	 * 
	 * @param projectName
	 * @return
	 */
	public static String getVersionedProjectPath(String projectName) {
		String[] projects = ouvrirFichier("build.properties").getProperty("projectToVersioned").split(",");

		String path = "";
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].split("&")[1].equals(projectName)) {
				path = projects[i].split("&")[0];
			}
		}
		return path;
	}

	/**
	 * Retourne le chemin pour un projet donnï¿½ (par exemple
	 * MetaModel/Application pour le projet com.bluexml.side.Application
	 * 
	 * @param projectName
	 * @return
	 */
	public static String getProjectPath(String projectName) {
		String property = ouvrirFichier("build.properties").getProperty("project");
		String path = "";
		if ((property != null) && (property.length() > 0)) {
			String[] projects = property.split(",");
			for (int i = 0; i < projects.length; i++) {
				if (projects[i].split("&")[1].equals(projectName)) {
					path = projects[i].split("&")[0];
				}
			}
		}
		// We search in versioned projects
		if (path.length() == 0) {
			property = ouvrirFichier("build.properties").getProperty("projectToVersioned");
			if ((property != null) && (property.length() > 0)) {
				String[] projects = property.split(",");
				for (int i = 0; i < projects.length; i++) {
					if (projects[i].split("&")[1].equals(projectName)) {
						path = projects[i].split("&")[0];
					}
				}
			}
		}
		return path;
	}

	/**
	 * Return the Build Path: /home/stager/buildAuto/Ankle
	 */
	public static String getBuildPath() {

		return ouvrirFichier("build.properties").getProperty("buildDir") + File.separator + getCodeName();
	}

	/**
	 * return the buildDirectory: /home/stager/buildAuto/Ankle/workingCopy
	 */
	public static String getBuildDirectory() {
		return getBuildPath() + File.separator + ouvrirFichier("build.properties").getProperty("buildName");
	}

	/**
	 * Retourne le repository: http://svn.bluexml.com/svn/bluexml/com/
	 */
	public static String getRepository() {
		return ouvrirFichier("build.properties").getProperty("repository");
	}

	/**
	 * Retourne le nom de code: Ankle
	 */
	public static String getCodeName() {
		return ouvrirFichier("build.properties").getProperty("codeName");
	}

	/**
	 * Retourne le chemin du eclipse: /home/stager/buildAuto/eclipse
	 */
	public static String getEclipseHome() {

		return ouvrirFichier("build.properties").getProperty("eclipseLocation");
	}

	/**
	 * Retourne la version du launcher : 1.0.101.R34x_v20081125.jar
	 */
	public static String getLauncherVersion() {
		return "org.eclipse.equinox.launcher_" + ouvrirFichier("build.properties").getProperty("equinoxLauncherPluginVersion") + ".jar";
	}

	/**
	 * return the final directory: /home/stager/share/SIDE
	 */
	public static String getFinalDirectory() {
		return ouvrirFichier("build.properties").getProperty("finalDirectory");
	}

	/**
	 * return the Build Label: I.UpdateSite
	 */
	public static String getBuildLabel() {
		return ouvrirFichier("build.properties").getProperty("buildType") + "." + ouvrirFichier("build.properties").getProperty("buildId");
	}

	/**
	 * Return the name of the Update-Site: Update-Site
	 */
	public static String getArchivePrefix() {
		return ouvrirFichier("build.properties").getProperty("archivePrefix");
	}

	/**
	 * Return the Version Number to force the change of each number version
	 * (void for no change)
	 */
	public static String getForceNumberVersion() {
		return ouvrirFichier("build.properties").getProperty("forceNumberVersion");
	}

	/**
	 * Return the equinox launcher directory version:
	 * gtk.linux.x86_1.0.101.R34x_v20080805
	 */
	public static String getEquinoxLauncherDirectoryVersion() {
		return ouvrirFichier("build.properties").getProperty("equinoxLauncherDirectoryVersion");
	}

	/**
	 * Return the new category for the site.xml : Other
	 */
	public static String getNewCategory() {
		return ouvrirFichier("build.properties").getProperty("newCategory");
	}

	/**
	 * Return the path to the Public Update site: /home/stager/share/SIDE-Final
	 */
	public static String getPublicUpdateSiteDirectory() {
		return ouvrirFichier("build.properties").getProperty("publicUpdateSiteDirectory");
	}

	/**
	 * Return the license URL: http://url.to.license.com
	 */
	public static String getlicenseURL() {
		return ouvrirFichier("build.properties").getProperty("licenseURL");
	}

	/**
	 * Return the license text file: /home/stager/buildAuto/license.txt
	 */
	public static String getLicenseText() {
		return loadFile(new File(ouvrirFichier("build.properties").getProperty("licensePath")));
	}

	/**
	 * Return the copyright URL: http://url.to.copyright.com
	 */
	public static String getCopyrightURL() {
		return ouvrirFichier("build.properties").getProperty("copyrightURL");
	}

	/**
	 * Return the copyright text file: /home/stager/buildAuto/copyright.txt
	 */
	public static String getCopyrightText() {
		return loadFile(new File(ouvrirFichier("build.properties").getProperty("copyrightPath")));
	}

	/**
	 * Retourne le numï¿½ro de version en fonction de l'utilisation de hudson ou
	 * non
	 */
	public static String getRevisionNumber() {
		String number = "";
		if (Application.parametre) {
			number = Application.svn_revision;
		} else {
			number = revisionNumber;
		}

		return number;
	}

	/**
	 * Retourne le numï¿½ro de version pour un projet donnï¿½
	 * 
	 * @param projectName
	 *            le nom du projet
	 * @return le numï¿½ro de version pour un projet donnï¿½
	 */
	public static String getVersionNumber(String projectName) {
		String version = "";

		// En fonction du type du projet (feature ou plugin)
		// on ira regarder soit dans le MANIFEST ou alors dans le feature.xml
		if (projectName.indexOf("feature") == -1) {
			version = ouvrirFichier(getPathToLocalCopy(projectName) + File.separator + "META-INF" + File.separator + "MANIFEST.MF").getProperty("Bundle-Version");
		} else {
			org.jdom.Document document = null;
			org.jdom.Element racine;

			// On crï¿½e une instance de SAXBuilder
			SAXBuilder sxb = new SAXBuilder();
			try {
				// On crï¿½e un nouveau document JDOM avec en argument le
				// fichier
				// XML
				document = sxb.build(new File(getPathToLocalCopy(projectName) + File.separator + "feature.xml"));
			} catch (Exception e) {
			}

			// On initialise un nouvel ï¿½lï¿½ment racine avec l'ï¿½lï¿½ment
			// racine du
			// document.
			racine = document.getRootElement();

			version = racine.getAttributeValue("version");

		}
		return version;
	}

	/**
	 * Retourne le numï¿½ro de version pour un pom donnï¿½
	 * 
	 * @param projectName
	 *            le nom du projet
	 * @return le numï¿½ro de version pour un projet donnï¿½
	 */
	public static String getVersionNumberPom(String projectName) {
		String version = "";

		// En fonction du type du projet (feature ou plugin)
		// on ira regarder soit dans le MANIFEST ou alors dans le feature.xml

		org.jdom.Document document = null;
		org.jdom.Element racine;

		// On crï¿½e une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try {
			// On crï¿½e un nouveau document JDOM avec en argument le fichier
			// XML
			document = sxb.build(new File(projectName));
		} catch (Exception e) {
		}

		// On initialise un nouvel ï¿½lï¿½ment racine avec l'ï¿½lï¿½ment racine
		// du
		// document.
		racine = document.getRootElement();

		String oldVersionNumber = "";

		// On crï¿½e une List contenant tous les noeuds "version" de
		// l'Element racine
		List listVersion = racine.getChildren();

		Iterator<?> i = listVersion.iterator();
		// on va parcourir tous les plugins
		while (i.hasNext()) {
			// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle afin de
			// pouvoir utiliser les mï¿½thodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) i.next();

			// sauvegarde du numï¿½ro de version
			if (courant.getName().equals("version")) {
				oldVersionNumber = courant.getText();
				return oldVersionNumber;
			}

		}

		return oldVersionNumber;

	}

	/**
	 * Copie de la copie local du rï¿½pository dans le repertoire
	 * getBuildDirectory() en sï¿½parant les features et les plugins
	 */
	public static void preTraitement() {

		List<String> projects = new ArrayList<String>();
		projects.addAll(getProjects());
		projects.addAll(getProjects("projectToVersioned"));

		String path = "";

		try {

			// suppression du dossier final s'il ï¿½xiste
			if (new File(getBuildDirectory()).exists()) {
				FileHelper.deleteFile(new File(getBuildDirectory()));
			}
			new File(getBuildDirectory()).mkdir();

			for (int i = 0; i < projects.size(); i++) {

				if (!Application.projectsExcluded.contains(projects.get(i))) {

					path = Application.workspace + File.separator + "S-IDE" + File.separator + getProjectPath(projects.get(i)) + File.separator + "trunk";

					if (projects.get(i).indexOf("feature") == -1) {

						FileHelper.copyFiles(new File(path + File.separator + projects.get(i)), new File(getBuildDirectory() + File.separator + "plugins" + File.separator + projects.get(i)), true);
					}

					else {

						FileHelper.copyFiles(new File(path + File.separator + projects.get(i)), new File(getBuildDirectory() + File.separator + "features" + File.separator + projects.get(i)), true);
						updateCopyrightLicence(projects.get(i), getBuildDirectory() + File.separator + "features");
					}
				}

			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Return the path to the svn log (with or without hudson)
	 */
	public static String getPathToLog() {
		String path = "";

		// Si on utilise Hudson
		if (Application.parametre) {

			// le chemin 'Application.workspace' est sous la forme
			// 'chemin/vers/workspace' et on ne veut pas le 'workspace' a la
			// fin, on va donc le supprimer du chemin et ajouter 'builds' a la
			// place et le numï¿½ro de build
			path = Application.workspace.substring(0, Application.workspace.length() - "workspace".length());
			path = path + "builds" + File.separator + Application.build_number + File.separator + "log";

		} else {

			path = getBuildPath() + File.separator + "logbuildSVNbuild.txt";
		}

		return path;
	}

	public static void findFile(File f, String s) {
		boolean stopfind = false;
		if (f.getName().equals(s) && !(f.getPath().indexOf("src") > -1) && !(f.getPath().indexOf("config") > -1)) {

			listefichierpom.add(f.getPath());
			stopfind = true;
		}

		File[] liste_fils = f.listFiles();

		if (liste_fils != null && stopfind == false) {
			for (int i = 0; i < liste_fils.length; i++) {
				findFile(liste_fils[i], s);
			}
		}
	}

	/**
	 * Cette mï¿½thode analyse le fichier de log (il changera en fonction de
	 * l'utilisation de Hudson ou non) et regarde si des updates ont ï¿½tï¿½
	 * fait et ainsi, changer le numï¿½ro de version du projet concernï¿½
	 */
	public static void traitementUpdate() {

		copyFromRepository();

		ArrayList<String> listeProjetReels = new ArrayList<String>();
		ArrayList<String> listeProjetPoms = new ArrayList<String>();

		List<String> projects = new ArrayList<String>();
		projects.addAll(getProjects("project"));
		projects.addAll(getProjects("projectToVersioned"));

		for (int i = 0; i < projects.size(); i++) {
			if (!Application.projectsExcluded.contains(projects.get(i))) {
				if (projects.get(i).length() > 0)
					listeProjetReels.add(projects.get(i));
			}
		}

		List<String> projectsToVersioned = getVersionedProjects();

		for (int i = 0; i < projectsToVersioned.size(); i++) {
			if (projectsToVersioned.get(i).length() > 0)
				listeProjetReels.add(projectsToVersioned.get(i));
		}

		boolean end = false;

		ArrayList<String> listePlugin = new ArrayList<String>();

		String pathproject = getRepositoryCopyPath();

		listefichierpom = new ArrayList();
		findFile(new File(pathproject + "/S-IDE/"), "pom.xml");

		// si on ne force pas la mise a jour du numï¿½ro de version
		if ("".equals(getForceNumberVersion())) {
			String ligne = "";
			String modif = "";
			ArrayList<String> listeProjet = new ArrayList<String>();
			boolean update = false;

			// ouverture d'un flux du fichier
			try {
				BufferedReader ficTexte = new BufferedReader(new FileReader(new File(getPathToLog())));

				if (ficTexte == null) {
					throw new FileNotFoundException("Fichier non trouvï¿½");
				}

				// Analyse et copie de chaque ligne
				while ((ligne = ficTexte.readLine()) != null && !end) {

					// condition d'arret de la lecture du log
					// on arrete la lecture lorsque se lance le build
					if (ligne.startsWith("************")) {
						end = true;
					}

					if (Application.parametre) {
						// condition pour ne pas traiter les logs du checkout
						if (ligne.startsWith("+ svn update")) {
							update = true;
						}
						if (ligne.indexOf("Checking out " + Utils.getRepository()) != -1) {
							update = true;
						}
					} else {
						if (ligne.indexOf("svnUD:") != -1) {
							update = true;
						}
					}

					if (!"".equals(ligne) && !end) {

						if (ligne.indexOf("At revision") != -1) {
							revisionNumber = ligne.substring("At revision".length(), ligne.length()).trim();
						}

						if (update) {

							if ((ligne.charAt(0) == 'A' || ligne.charAt(0) == 'U' || ligne.charAt(0) == 'D' || ligne.charAt(0) == ' ')
									&& (ligne.charAt(1) == ' ' || ligne.charAt(1) == 'U' || ligne.charAt(1) == 'A' || ligne.charAt(1) == 'D') && update) {

								if (ligne.indexOf("Integration") > -1 || ligne.indexOf("FrameworksModules") > -1) {
									for (String valeur : listefichierpom) {
										String valeurf = valeur;
										String[] tab = valeurf.split("/S-IDE/");
										String[] tab2 = tab[1].split("/pom.xml");
										if (ligne.indexOf(tab2[0]) > -1) {
											if (!listeProjetPoms.contains(valeur))
												listeProjetPoms.add(valeur);
										}
									}
								}

								modif = ligne.substring(2, ligne.length());
								modif.trim();
								String[] proj = modif.split(File.separator);

								for (int i = 0; i < proj.length; i++) {
									if (!listeProjet.contains(proj[i])) {
										listeProjet.add(proj[i]);
									}
								}
							}
						}

					}
				}

			} catch (FileNotFoundException e) {
				e.getMessage();
			} catch (IOException e1) {
				e1.getMessage();
			}

			// Add all projects to version
			for (String p : projectsToVersioned) {
				if (!listeProjet.contains(p))
					listeProjet.add(p);
			}

			// on parcours la liste des projets qui ont ï¿½tï¿½ modifiï¿½
			for (String element : listeProjet) {
				if (listeProjetReels.contains(element)) {
					// on met tous les plugins modifiï¿½s dans un tableau
					if (element.indexOf("feature") == -1) {
						listePlugin.add(element);
					}
					// et tous les features dans un autre
					else {
						listeFeatureModif.add(element);
					}
				}
			}

			// si on force la mise a jour du numï¿½ro de version
		} else {
			System.out.println("Les numï¿½ros de version de tous les projets sont forcï¿½s ï¿½: " + getForceNumberVersion());
			for (int i = 0; i < projects.size(); i++) {
				if (projects.get(i).indexOf("feature") == -1)
					listePlugin.add(projects.get(i));
			}

		}

		// On parcours la liste des pom et on les met a jour
		for (String pom : listeProjetPoms) {
			updateVersionNumberPom(pom);

		}

		if (listeProjetPoms.size() != 0) {
			System.out.println("\nListe des poms modifiï¿½es: ");
			for (String pom : listeProjetPoms) {
				String valeurf = pom;
				String[] tab = valeurf.split("/S-IDE/");
				System.out.println("\t- " + tab[1] + ": " + getVersionNumberPom(pom));
			}

			if (listePlugin.indexOf("com.bluexml.side.Util.dependencies") == -1) {
				listePlugin.add("com.bluexml.side.Util.dependencies");
			}

		}

		// mettre a jour les plugins avec les versions des pom.xml
		// ajouter les plugins modifier dans la listePlugin
		ArrayList<String> listePomsModuleDepencies = new ArrayList<String>();
		if (listeProjetPoms.size() != 0) {
			for (String pom : listeProjetPoms) {
				String versionPom = getVersionNumberPom(pom);
				String valeurf = pom;
				String[] tab = valeurf.split("/pom.xm");
				String valeur2 = tab[0];
				String nomPom = valeur2.substring(valeur2.lastIndexOf("/") + 1);

				// fixer les versions des fichiers plugin.xml
				System.out.println("\nFixer les versions des fichiers plugins : ");
				for (String element : projects) {

					if (element.indexOf("feature") == -1) {

						// si contient reference pom alors modifie max version
						// et ajouter a la liste listePlugin
						boolean ajouter = updatePluginModuleDependencies(element, nomPom, versionPom);
						if (ajouter) {
							if (listePlugin.indexOf(element) == -1) {
								listePlugin.add(element);
								System.out.println("update plugin : " + element + " pom : " + nomPom + " version : " + versionPom);
							}

						}

					}
				}

				// fixer les versions des fichier pom.xml
				System.out.println("\nFixer les versions des fichiers pom : ");
				for (String element : listefichierpom) {

					// si contient reference pom alors modifie max version
					// et ajouter a la liste listePlugin
					boolean ajouter = updatePomModuleDependencies(element, nomPom, versionPom);
					if (ajouter) {
						if (listeProjetPoms.indexOf(element) == -1) {
							listePomsModuleDepencies.add(element);
							System.out.println("update pom : " + element + " pom : " + nomPom + " version : " + versionPom);
						}
					}
				}

			}
		}

		// mettre a jour les pom.xml modifiers
		// On parcours la liste des pom et on les met a jour
		for (String pomModuleDepencies : listePomsModuleDepencies) {
			updateVersionNumberPom(pomModuleDepencies);

		}

		if (listePomsModuleDepencies.size() != 0) {
			System.out.println("\nListe des poms modifiï¿½es suite mis a jour module: ");
			for (String pom : listePomsModuleDepencies) {
				String valeurf = pom;
				String[] tab = valeurf.split("/S-IDE/");
				System.out.println("\t- " + tab[1] + ": " + getVersionNumberPom(pom));
			}
		}

		System.out.println("\nFixer les versions des fichiers plugin : ");
		if (listePomsModuleDepencies.size() != 0) {
			for (String pom : listePomsModuleDepencies) {
				String versionPom = getVersionNumberPom(pom);
				String valeurf = pom;
				String[] tab = valeurf.split("/pom.xm");
				String valeur2 = tab[0];
				String nomPom = valeur2.substring(valeur2.lastIndexOf("/") + 1);

				// fixer les versions des fichiers plugin.xml
				for (String element : projects) {

					if (element.indexOf("feature") == -1) {

						// si contient reference pom alors modifie max version
						// et ajouter a la liste listePlugin
						boolean ajouter = updatePluginModuleDependencies(element, nomPom, versionPom);
						if (ajouter) {
							if (listePlugin.indexOf(element) == -1) {
								listePlugin.add(element);
								System.out.println("update plugin : " + element + " pom : " + nomPom + " version : " + versionPom);
							}

						}

					}
				}
			}
		}

		while (listePomsModuleDepencies.size() != 0) {

			System.out.println("\nFixer les versions des fichiers plugins suite mise a jour module : ");
			// mettre a jour les plugins avec les versions des pom.xml
			// ajouter les plugins modifier dans la listePlugin
			ArrayList<String> listePomsModuleDepencies1 = new ArrayList<String>();
			if (listePomsModuleDepencies.size() != 0) {
				for (String pom : listePomsModuleDepencies) {
					String versionPom = getVersionNumberPom(pom);
					String valeurf = pom;
					String[] tab = valeurf.split("/pom.xm");
					String valeur2 = tab[0];
					String nomPom = valeur2.substring(valeur2.lastIndexOf("/") + 1);

					// fixer les versions des fichiers plugin.xml
					System.out.println("\nFixer les versions des fichiers plugins : ");
					for (String element : projects) {

						if (element.indexOf("feature") == -1) {

							// si contient reference pom alors modifie max
							// version
							// et ajouter a la liste listePlugin
							boolean ajouter = updatePluginModuleDependencies(element, nomPom, versionPom);
							if (ajouter) {
								if (listePlugin.indexOf(element) == -1) {
									listePlugin.add(element);
									System.out.println("update plugin : " + element + " pom : " + nomPom + " version : " + versionPom);
								}

							}

						}
					}

					// fixer les versions des fichier pom.xml
					System.out.println("\nFixer les versions des fichiers pom suite mise a jour module : ");
					for (String element : listefichierpom) {

						// si contient reference pom alors modifie max version
						// et ajouter a la liste listePlugin
						boolean ajouter = updatePomModuleDependencies(element, nomPom, versionPom);
						if (ajouter) {
							if ((listePomsModuleDepencies.indexOf(element) == -1) && (listeProjetPoms.indexOf(element) == -1)) {
								listePomsModuleDepencies1.add(element);
								System.out.println("update pom : " + element + " pom : " + nomPom + " version : " + versionPom);
							}
						}
					}

				}
			}

			// mettre a jour les pom.xml modifiers
			// On parcours la liste des pom et on les met a jour
			for (String pomModuleDepencies : listePomsModuleDepencies1) {
				updateVersionNumberPom(pomModuleDepencies);

			}

			if (listePomsModuleDepencies1.size() != 0) {
				System.out.println("\nListe des poms modifiï¿½es suite mis a jour module: ");
				for (String pom : listePomsModuleDepencies1) {
					String valeurf = pom;
					String[] tab = valeurf.split("/S-IDE/");
					System.out.println("\t- " + tab[1] + ": " + getVersionNumberPom(pom));
				}
			}

			System.out.println("\nFixer les versions des fichiers plugin : ");
			if (listePomsModuleDepencies1.size() != 0) {
				for (String pom : listePomsModuleDepencies1) {
					String versionPom = getVersionNumberPom(pom);
					String valeurf = pom;
					String[] tab = valeurf.split("/pom.xm");
					String valeur2 = tab[0];
					String nomPom = valeur2.substring(valeur2.lastIndexOf("/") + 1);

					// fixer les versions des fichiers plugin.xml
					for (String element : projects) {

						if (element.indexOf("feature") == -1) {

							// si contient reference pom alors modifie max
							// version
							// et ajouter a la liste listePlugin
							boolean ajouter = updatePluginModuleDependencies(element, nomPom, versionPom);
							if (ajouter) {
								if (listePlugin.indexOf(element) == -1) {
									listePlugin.add(element);
									System.out.println("update plugin : " + element + " pom : " + nomPom + " version : " + versionPom);
								}

							}

						}
					}
				}
			}

			listePomsModuleDepencies = new ArrayList<String>();
			if (listePomsModuleDepencies1.size() != 0) {
				for (String pom : listePomsModuleDepencies1) {
					listePomsModuleDepencies.add(pom);
				}
			}

		}

		// On parcours la liste des plugins et on les met a jour
		for (String plugin : listePlugin) {
			updateVersionNumber(plugin);

		}

		// On fait la meme chose mais pour toutes les features
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).indexOf("feature") != -1) {
				updateVersionNumber(projects.get(i));
			}
		}

		// affichage des donnï¿½es
		if (listePlugin.size() != 0) {
			System.out.println("\nListe des plugins modifiï¿½s: ");
			// On parcours la liste des plugins et on les met a jour
			for (String plugin : listePlugin) {
				System.out.println("\t- " + plugin + ": " + getVersionNumber(plugin));
			}
		}

		if (listeFeatureModif.size() != 0) {
			System.out.println("\nListe des features modifiï¿½es: ");
			for (String feature : listeFeatureModif) {
				System.out.println("\t- " + feature + ": " + getVersionNumber(feature));
			}
		}

		
		// fin affichage

		copyToRepository();
	}

	public static void updateProduct() {
		// update side.product
		// mise à jour du fichier side.product (utilisé pour la creation des
		// RCP)
		// String repo = Utils.getRepositoryCopyPath();
		// File product = new File(repo + "/S-IDE/Integration/trunk/com.bluexml.side.Integration.eclipse.branding/side.product");
		String brandingPath=Utils.getPathToLocalCopy("com.bluexml.side.Integration.eclipse.branding");
		File product = new File(brandingPath + "/side.product");
		File plugin_featureRepo = new File(Utils.getBuildDirectory());
		boolean sideProductChanges = Utils.updateProduct(product, plugin_featureRepo);
		if (sideProductChanges) {
			System.out.println("- side.product updated");
		} else {
			System.out.println("- side.product no changes ");
		}
	}

	public static String getRepositoryCopyPath() {
		String pathproject = getBuildPath() + File.separator + repositoryCopy;
		return pathproject;
	}

	/**
	 * Retourne le chemin vers la copie locale du projet en fonction de
	 * l'utilisation de hudson ou non
	 */
	public static String getPathToLocalCopy(String projectName) {
		String path = "";
		if (new File(getBuildPath() + File.separator + repositoryCopy).exists()) {
			path = getBuildPath() + File.separator + repositoryCopy;
		} else {
			if (Application.parametre) {
				path = Application.workspace;
			} else {
				path = getBuildDirectory() + "_CO";
			}

		}
		if (Application.parametre) {
			path = path + File.separator + "S-IDE" + File.separator + getProjectPath(projectName) + File.separator + "trunk" + File.separator + projectName;
		} else {
			if (projectName.indexOf("feature") == -1) {

				path = path + File.separator + "plugins" + File.separator + projectName;
			} else {
				path = path + File.separator + "features" + File.separator + projectName;
			}
		}
		return path;
	}

	/**
	 * Copy the repository
	 */
	public static void copyFromRepository() {

		String from = "";
		if (Application.parametre) {
			from = Application.workspace;
		} else {
			from = getBuildDirectory() + "_CO";
		}

		String to = getRepositoryCopyPath();

		try {
			if (new File(to).exists()) {
				FileHelper.deleteFile(new File(to));
			}

			new File(getBuildPath() + File.separator + repositoryCopy).mkdir();

			FileHelper.copyFiles(new File(from), new File(to), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void copyToRepository() {

		String to = "";
		if (Application.parametre) {
			to = Application.workspace;
		} else {
			to = getBuildDirectory() + "_CO";
		}

		String from = getRepositoryCopyPath();

		try {
			FileHelper.copyFiles(new File(from), new File(to), true);

			// FileHelper.deleteFile(new File(from));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Update the version number of the modules in the files pom.xml
	 * 
	 * 
	 * @param projectName
	 */
	public static boolean updatePomModuleDependencies(String element, String module, String version) {

		boolean modifie = false;

		// chemin vers le plugin.xml
		String fileFeaturePath = element;

		boolean exists = (new File(fileFeaturePath)).exists();
		if (exists) {

			org.jdom.Document document = null;
			org.jdom.Element racine;

			// On crï¿½e une instance de SAXBuilder
			SAXBuilder sxb = new SAXBuilder();

			try {
				// On crï¿½e un nouveau document JDOM avec en argument le
				// fichier
				// XML
				document = sxb.build(new File(fileFeaturePath));
			} catch (Exception e) {
				e.printStackTrace();
			}

			// On initialise un nouvel ï¿½lï¿½ment racine avec l'ï¿½lï¿½ment
			// racine du
			// document.
			racine = document.getRootElement();

			// On va maintenant mettre a jour les numï¿½ro de version des
			// plugins
			// associï¿½s a la feature

			// on garde en mï¿½moire l'ancien numï¿½ro de version du plugin pour
			// savoir s'il a changer et ainsi savoir s'il faut changer ou non le
			// numï¿½ro de version de la feature

			// On crï¿½e une List contenant tous les noeuds "moduleDependence"
			// de
			// l'Element racine
			List<?> listDependencies = racine.getChildren();

			// On crï¿½e un Iterator sur notre liste
			Iterator<?> i = listDependencies.iterator();

			// on va parcourir tous les modules
			while (i.hasNext()) {
				// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle afin
				// de
				// pouvoir utiliser les mï¿½thodes propres aux Element comme :
				// selectionner un noeud fils, modifier du texte, etc...
				Element courant = (Element) i.next();

				if (courant.getName().equals("dependencies")) {

					List<?> listDependency = courant.getChildren();

					// On crï¿½e un Iterator sur notre liste
					Iterator<?> iDependency = listDependency.iterator();

					while (iDependency.hasNext()) {
						// On recrï¿½e l'Element courant ï¿½ chaque tour de
						// boucle afin de
						// pouvoir utiliser les mï¿½thodes propres aux Element
						// comme :
						// selectionner un noeud fils, modifier du texte, etc...
						Element courantDependency = (Element) iDependency.next();

						List<?> listDependent = courantDependency.getChildren();

						// On crï¿½e un Iterator sur notre liste
						Iterator<?> iDependent = listDependent.iterator();

						if (iDependent.hasNext()) {
							// On recrï¿½e l'Element courant ï¿½ chaque tour de
							// boucle afin de
							// pouvoir utiliser les mï¿½thodes propres aux
							// Element comme :
							// selectionner un noeud fils, modifier du texte,
							// etc...
							Element courantDependent = (Element) iDependent.next();
							String projectName;
							if (courantDependent.getName().equals("groupId")) {
								projectName = courantDependent.getText();
								if (module.contains(projectName)) {
									while (iDependent.hasNext()) {
										Element courantDependentversion = (Element) iDependent.next();
										if (courantDependentversion.getName().equals("artifactId")) {
											projectName = projectName + "." + courantDependentversion.getText();
										}
										if (courantDependentversion.getName().equals("version") && module.equals(projectName)) {
											courantDependentversion.setText(version);
											modifie = true;
										}
									}
								}

							}

						}

					}
				}

			}

			if (modifie) {
				// Enregistrement du fichier
				try {
					XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
					sortie.output(document, new FileOutputStream(fileFeaturePath));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return modifie;
	}

	/**
	 * Update the version number of the modules in the files feature.xml
	 * 
	 * 
	 * @param projectName
	 */
	public static boolean updatePluginModuleDependencies(String element, String module, String version) {

		boolean modifie = false;
		// chemin vers le plugin.xml
		String fileFeaturePath = getPathToLocalCopy(element) + File.separator + "plugin.xml";

		boolean exists = (new File(fileFeaturePath)).exists();
		if (exists) {

			org.jdom.Document document = null;
			org.jdom.Element racine;

			// On crï¿½e une instance de SAXBuilder
			SAXBuilder sxb = new SAXBuilder();

			try {
				// On crï¿½e un nouveau document JDOM avec en argument le
				// fichier
				// XML
				document = sxb.build(new File(fileFeaturePath));
			} catch (Exception e) {
				e.printStackTrace();
			}

			// On initialise un nouvel ï¿½lï¿½ment racine avec l'ï¿½lï¿½ment
			// racine du
			// document.
			racine = document.getRootElement();

			// On va maintenant mettre a jour les numï¿½ro de version des
			// plugins
			// associï¿½s a la feature

			// on garde en mï¿½moire l'ancien numï¿½ro de version du plugin pour
			// savoir s'il a changer et ainsi savoir s'il faut changer ou non le
			// numï¿½ro de version de la feature

			// On crï¿½e une List contenant tous les noeuds "moduleDependence"
			// de
			// l'Element racine
			List<?> listModules = racine.getChildren("extension");

			// On crï¿½e un Iterator sur notre liste
			Iterator<?> i = listModules.iterator();

			// on va parcourir tous les modules
			while (i.hasNext()) {
				// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle afin
				// de
				// pouvoir utiliser les mï¿½thodes propres aux Element comme :
				// selectionner un noeud fils, modifier du texte, etc...
				Element courant = (Element) i.next();

				List<?> listmetamodel = courant.getChildren("metamodel");
				Iterator<?> imetamodel = listmetamodel.iterator();

				// on va parcourir tous les modules
				while (imetamodel.hasNext()) {
					// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle
					// afin de
					// pouvoir utiliser les mï¿½thodes propres aux Element comme
					// :
					// selectionner un noeud fils, modifier du texte, etc...
					Element courantmetamodel = (Element) imetamodel.next();

					List<?> listtechnology = courantmetamodel.getChildren("technology");
					Iterator<?> itechnology = listtechnology.iterator();

					// on va parcourir tous les modules
					while (itechnology.hasNext()) {
						// On recrï¿½e l'Element courant ï¿½ chaque tour de
						// boucle afin de
						// pouvoir utiliser les mï¿½thodes propres aux Element
						// comme :
						// selectionner un noeud fils, modifier du texte, etc...
						Element couranttechnology = (Element) itechnology.next();

						List<?> listtechnologyVersion = couranttechnology.getChildren("technologyVersion");
						Iterator<?> itechnologyVersion = listtechnologyVersion.iterator();

						// on va parcourir tous les modules
						while (itechnologyVersion.hasNext()) {
							// On recrï¿½e l'Element courant ï¿½ chaque tour de
							// boucle afin de
							// pouvoir utiliser les mï¿½thodes propres aux
							// Element comme :
							// selectionner un noeud fils, modifier du texte,
							// etc...
							Element couranttechnologyVersion = (Element) itechnologyVersion.next();

							List<?> listgeneratorVersion = couranttechnologyVersion.getChildren("generatorVersion");
							Iterator<?> igeneratorVersion = listgeneratorVersion.iterator();

							// on va parcourir tous les modules
							while (igeneratorVersion.hasNext()) {
								// On recrï¿½e l'Element courant ï¿½ chaque tour
								// de boucle afin de
								// pouvoir utiliser les mï¿½thodes propres aux
								// Element comme :
								// selectionner un noeud fils, modifier du
								// texte, etc...
								Element courantgeneratorVersion = (Element) igeneratorVersion.next();

								List<?> listoption = courantgeneratorVersion.getChildren("option");
								Iterator<?> ioption = listoption.iterator();

								// on va parcourir tous les modules
								while (ioption.hasNext()) {
									// On recrï¿½e l'Element courant ï¿½ chaque
									// tour de boucle afin de
									// pouvoir utiliser les mï¿½thodes propres
									// aux Element comme :
									// selectionner un noeud fils, modifier du
									// texte, etc...
									Element courantoption = (Element) ioption.next();

									List<?> listmoduleDependence = courantoption.getChildren("moduleDependence");
									Iterator<?> imoduleDependence = listmoduleDependence.iterator();

									// on va parcourir tous les modules
									while (imoduleDependence.hasNext()) {
										// On recrï¿½e l'Element courant ï¿½
										// chaque tour de boucle afin de
										// pouvoir utiliser les mï¿½thodes
										// propres aux Element comme :
										// selectionner un noeud fils, modifier
										// du texte, etc...
										Element courantmoduleDependence = (Element) imoduleDependence.next();

										String moduleId = courantmoduleDependence.getAttributeValue("moduleId");

										// if we find the module then we add the
										// attributes versionMax and versionMin
										if (moduleId.equals(module)) {
											courantmoduleDependence.setAttribute("versionMax", version);
											courantmoduleDependence.setAttribute("versionMin", version);
											modifie = true;
										}
									}
								}

								List<?> listmdep = courantgeneratorVersion.getChildren("moduleDependence");
								Iterator<?> imdep = listmdep.iterator();

								// on va parcourir tous les modules
								while (imdep.hasNext()) {
									// On recrï¿½e l'Element courant ï¿½ chaque
									// tour de boucle afin de
									// pouvoir utiliser les mï¿½thodes propres
									// aux Element comme :
									// selectionner un noeud fils, modifier du
									// texte, etc...
									Element courantmoduleDependence1 = (Element) imdep.next();

									String moduleId = courantmoduleDependence1.getAttributeValue("moduleId");

									// if we find the module then we add the
									// attributes versionMax and versionMin
									if (moduleId.equals(module)) {
										courantmoduleDependence1.setAttribute("versionMax", version);
										courantmoduleDependence1.setAttribute("versionMin", version);
										modifie = true;
									}
								}
							}
						}

					}

				}

				// String moduleId = courant.getAttributeValue("moduleId");

				// if (moduleId.equals(module)){
				// courant.setAttribute("versionMax", version);
				// modifie=true;
				// }

			}

			if (modifie) {
				// Enregistrement du fichier
				try {
					XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
					sortie.output(document, new FileOutputStream(fileFeaturePath));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return modifie;

	}

	/**
	 * Update le numï¿½ro de version du projet, le pattern pour cet update est
	 * dans le fichier build.properties
	 * 
	 * @param projectName
	 */
	public static void updateVersionNumber(String projectName) {

		if (projectName.length() > 0) {
			String[] pattern = getNumVersionPattern();

			// En fonction du type du projet (feature ou plugin)
			// on ira regarder soit dans le MANIFEST.MF ou alors dans le
			// feature.xml
			if (projectName.indexOf("feature") == -1) {
				// chemin vers le MANIFEST.MF
				String filePluginPath = getPathToLocalCopy(projectName) + File.separator + "META-INF" + File.separator + "MANIFEST.MF";

				// on rï¿½cupï¿½re dans un tableau les 3 numï¿½ros de version du
				// projet
				String[] number = ouvrirFichier(filePluginPath).getProperty("Bundle-Version").split("\\.");

				String ligne = "";
				try {
					BufferedReader reader = new BufferedReader(new FileReader(filePluginPath));
					PrintWriter writer = new PrintWriter(new FileWriter(filePluginPath + ".txt"));
					while ((ligne = reader.readLine()) != null) {
						// si la ligne contient "Bundle-Version:"
						if (ligne.indexOf("Bundle-Version:") != -1) {
							// on supprime tout ce qui se trouve aprï¿½s
							// "Bundle-Version:"
							ligne = ligne.substring(0, "Bundle-Version:".length());
							// on ajoute a la ligne le nouveau numï¿½ro de
							// version
							// si on ne force pas la mise a jour du numï¿½ro de
							// version
							if ("".equals(getForceNumberVersion()))
								ligne += " " + update(number, pattern);
							else
								ligne += " " + update(number, getForceNumberVersion().split("\\."));
						}
						// ecriture de la ligne dans le nouveau fichier
						writer.println(ligne);
					}
					// fermeture des flux
					reader.close();
					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// Suppression de l'ancien fichier MANIFEST
				new File(filePluginPath).delete();
				// Renomage du nouveau MANIFEST
				new File(filePluginPath + ".txt").renameTo(new File(filePluginPath));

			} else {
				// boolean qui permet de savoir s'il faut changer le numï¿½ro du
				// feature ou non
				boolean featureAModifier = false;

				if (listeFeatureModif.contains(projectName))
					featureAModifier = true;

				// chemin vers le feature.xml
				String fileFeaturePath = getPathToLocalCopy(projectName) + File.separator + "feature.xml";

				org.jdom.Document document = null;
				org.jdom.Element racine;

				// On crï¿½e une instance de SAXBuilder
				SAXBuilder sxb = new SAXBuilder();

				try {
					// On crï¿½e un nouveau document JDOM avec en argument le
					// fichier
					// XML
					document = sxb.build(new File(fileFeaturePath));
				} catch (Exception e) {
					e.printStackTrace();
				}

				// On initialise un nouvel ï¿½lï¿½ment racine avec l'ï¿½lï¿½ment
				// racine du
				// document.
				racine = document.getRootElement();

				// On va maintenant mettre a jour les numï¿½ro de version des
				// plugins
				// associï¿½s a la feature

				// on garde en mï¿½moire l'ancien numï¿½ro de version du plugin
				// pour
				// savoir s'il a changer et ainsi savoir s'il faut changer ou
				// non le
				// numï¿½ro de version de la feature

				String oldVersionNumber = "";

				// On crï¿½e une List contenant tous les noeuds "plugin" de
				// l'Element racine
				List<?> listPlugins = racine.getChildren("plugin");

				// On crï¿½e un Iterator sur notre liste
				Iterator<?> i = listPlugins.iterator();
				// on va parcourir tous les plugins
				while (i.hasNext()) {
					// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle
					// afin de
					// pouvoir utiliser les mï¿½thodes propres aux Element comme
					// :
					// selectionner un noeud fils, modifier du texte, etc...
					Element courant = (Element) i.next();

					// sauvegarde du numï¿½ro de version
					oldVersionNumber = courant.getAttributeValue("version");

					// on regarde si le numï¿½ro de version du plugin a changï¿½
					if (!oldVersionNumber.equals(getVersionNumber(courant.getAttributeValue("id")))) {
						// On modifie le numï¿½ro de version du plugin courant
						courant.setAttribute("version", getVersionNumber(courant.getAttributeValue("id")));

						// on indique que le numï¿½ro de feature doit changer
						featureAModifier = true;
					}
				}

				/********************************
				 * CHECKING OF INCLUDED FEATURES
				 ********************************/

				List<?> listIncludedFeatures = racine.getChildren("includes");

				i = listIncludedFeatures.iterator();
				while (i.hasNext()) {
					Element currentNode = (Element) i.next();
					oldVersionNumber = currentNode.getAttributeValue("version");

					// check version of features
					if (!oldVersionNumber.equals(getVersionNumber(currentNode.getAttributeValue("id")))) {
						// modify the included features
						currentNode.setAttribute("version", getVersionNumber(currentNode.getAttributeValue("id")));

						// feature is modified
						featureAModifier = true;
					}
				}

				// on rï¿½cupï¿½re dans un tableau les 3 numï¿½ros de version du
				// projet
				String[] number = racine.getAttributeValue("version").split("\\.");

				// on change le numï¿½ro de version (s'il le faut)
				if ("".equals(getForceNumberVersion())) {
					if (featureAModifier) {
						if (!listeFeatureModif.contains(projectName)) {
							listeFeatureModif.add(projectName);
						}
						racine.setAttribute("version", update(number, pattern));
					}
				} else
					racine.setAttribute("version", update(number, getForceNumberVersion().split("\\.")));

				// Enregistrement du fichier
				try {
					XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
					sortie.output(document, new FileOutputStream(fileFeaturePath));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * Update le numï¿½ro de version du projet, le pattern pour cet update est
	 * dans le fichier build.properties
	 * 
	 * @param projectName
	 */
	public static void updateVersionNumberPom(String projectName) {

		String[] pattern = getNumVersionPattern();

		// chemin vers le pom.xml
		String fileFeaturePath = projectName;

		org.jdom.Document document = null;
		org.jdom.Element racine;

		// On crï¿½e une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();

		try {
			// On crï¿½e un nouveau document JDOM avec en argument le fichier
			// XML
			document = sxb.build(new File(fileFeaturePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On initialise un nouvel ï¿½lï¿½ment racine avec l'ï¿½lï¿½ment racine
		// du
		// document.
		racine = document.getRootElement();

		// On va maintenant mettre a jour les numï¿½ro de version des plugins
		// associï¿½s a la feature

		// on garde en mï¿½moire l'ancien numï¿½ro de version du plugin pour
		// savoir s'il a changer et ainsi savoir s'il faut changer ou non le
		// numï¿½ro de version de la feature

		String oldVersionNumber = "";

		// On crï¿½e une List contenant tous les noeuds "version" de
		// l'Element racine
		List listVersion = racine.getChildren();

		Iterator<?> i = listVersion.iterator();
		// on va parcourir tous les plugins
		while (i.hasNext()) {
			// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle afin de
			// pouvoir utiliser les mï¿½thodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) i.next();

			// sauvegarde du numï¿½ro de version
			if (courant.getName().equals("version")) {
				oldVersionNumber = courant.getText();
				String[] number = oldVersionNumber.split("\\.");
				courant.setText(updatepom(number, pattern));
			}

		}

		// Enregistrement du fichier
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(fileFeaturePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String[] getNumVersionPattern() {
		String[] pattern = ouvrirFichier("build.properties").getProperty("number-pattern").split("\\.");
		return pattern;
	}

	/**
	 * change le numï¿½ro de version en fonction du pattern
	 * 
	 * @param number
	 *            un tableau des 3 numï¿½ro de version
	 * @param pattern
	 *            un tableau avec les 3 ï¿½lï¿½ments du pattern
	 * @return Le numï¿½ro de version sous la forme 1.0.12
	 */
	public static String update(String[] number, String[] pattern) {

		boolean change = false;

		for (int i = 0; i < 3; i++) {
			// test si l'ï¿½lï¿½ment est un nombre si une exception est levï¿½e,
			// l'ï¿½lï¿½ment n'est pas un nombre
			try {
				Integer.valueOf(pattern[i]);
				number[i] = pattern[i];
			} catch (NumberFormatException e) {
				if (change)
					number[i] = "0";
				else {
					if (pattern[i].equals("u")) {
						change = true;
						number[i] = String.valueOf(Integer.valueOf(number[i]) + 1);
					}
				}
			}
		}
		return number[0] + "." + number[1] + "." + number[2] + ".v" + getRevisionNumber() + "-" + getDate();
	}

	/**
	 * change le numï¿½ro de version en fonction du pattern pour pom.xml
	 * 
	 * @param number
	 *            un tableau des 3 numï¿½ro de version
	 * @param pattern
	 *            un tableau avec les 3 ï¿½lï¿½ments du pattern
	 * @return Le numï¿½ro de version sous la forme 1.0.12
	 */
	public static String updatepom(String[] number, String[] pattern) {

		boolean change = false;

		for (int i = 0; i < 3; i++) {
			// test si l'ï¿½lï¿½ment est un nombre si une exception est levï¿½e,
			// l'ï¿½lï¿½ment n'est pas un nombre
			try {
				Integer.valueOf(pattern[i]);
				number[i] = pattern[i];
			} catch (NumberFormatException e) {
				if (change)
					number[i] = "0";
				else {
					if (pattern[i].equals("u")) {
						change = true;
						number[i] = String.valueOf(Integer.valueOf(number[i]) + 1);
					}
				}
			}
		}
		return number[0] + "." + number[1] + "." + number[2];
	}

	/**
	 * Met a jour le site.xml en fonction des features. Si une feature n'est pas
	 * prï¿½sente dans le site.xml, elle est ajoutï¿½e et placï¿½e dans la
	 * catï¿½gorie 'other' (retournï¿½ par la mï¿½thode getNewCategory() )
	 * 
	 */
	public static void updateSiteXml() {

		// chemin vers le feature.xml
		String fileSitePath = getBuildPath() + File.separator + "site.xml";
		// String fileSitePath = getFinalDirectory()+ File.separator +
		// getArchivePrefix() + File.separator+ "site.xml";
		List<String> projects = getProjects();

		// tableau qui contiendra la liste des features
		ArrayList<String> listeFeature = new ArrayList<String>();

		// on met tous les features dans le tableau

		for (int i = 0; i < projects.size(); i++) {

			if (projects.get(i).indexOf("feature") != -1) {
				if (!Application.projectsExcluded.contains(projects.get(i))) {
					listeFeature.add(projects.get(i));
				}
			}
		}

		org.jdom.Document document = null;
		Element racine;

		// On crï¿½e une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();

		try {
			// On crï¿½e un nouveau document JDOM avec en argument le fichier
			// XML
			document = sxb.build(new File(fileSitePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On initialise un nouvel ï¿½lï¿½ment racine avec l'ï¿½lï¿½ment racine
		// du
		// document.
		racine = document.getRootElement();

		// On crï¿½e une List contenant tous les noeuds "feature" de
		// l'Element racine
		List<?> listFeatures = racine.getChildren("feature");

		// On crï¿½e un Iterator sur notre liste
		Iterator<?> i = listFeatures.iterator();

		// Boucle qui permet de mettre ï¿½ jour le numï¿½ro de version de chaque
		// feature
		while (i.hasNext()) {
			// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle afin de
			// pouvoir utiliser les mï¿½thodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) i.next();

			// on regarde si l'ï¿½lï¿½ment parcouru est dans le tableau de
			// features
			if (listeFeature.contains(courant.getAttributeValue("id"))) {
				// on supprime le feature de la liste
				listeFeature.remove(courant.getAttributeValue("id"));

				// On modifie le numï¿½ro de version du plugin courant
				courant.setAttribute("version", getVersionNumber(courant.getAttributeValue("id")));

				courant.setAttribute("url", "features/" + courant.getAttributeValue("id") + "_" + getVersionNumber(courant.getAttributeValue("id")) + ".jar");
			}
		}

		/***********************************************************************
		 * DEPRECATED PART : BEGIN
		 ***********************************************************************/

		// on parcourt le tableau de feature
		// on va ajouter les features prï¿½sentes dans le tableau (et donc qui
		// ne
		// sont pas prï¿½sentes dans le site.xml) et les ajouter au site.xml

		// for (String feature : listeFeature) {
		// Element newElement = new Element("feature");
		//
		// newElement.setAttribute("url", "features/" + feature + "_"
		// + getVersionNumber(feature) + ".jar");
		// newElement.setAttribute("id", feature);
		// newElement.setAttribute("version", getVersionNumber(feature));
		//
		// Element newCategory = new Element("category");
		//
		// newCategory.setAttribute("name", "SIDE " + getNewCategory());
		//
		// newElement.addContent(newCategory);
		// racine.addContent(newElement);
		// }

		/***********************************************************************
		 * DEPRECATED PART : END
		 ***********************************************************************/

		// Enregistrement du fichier
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(fileSitePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Traitement final: Copie de l'update site dans le repertoire final Copie
	 * des logs dans le repertoire final Suppression de workingcopy
	 * 
	 */
	public static void finalTraitement() {
		String buildNumber = "";
		System.out.println("Final treatment : ");

		if (Application.parametre) {
			buildNumber = "-" + Application.build_number;
		}

		File finalFeatures = new File(getUpdateSiteDir() + File.separator + getCodeName() + File.separator + getRevisionNumber() + buildNumber + File.separator + "features");
		File finalPlugins = new File(getUpdateSiteDir() + File.separator + getCodeName() + File.separator + getRevisionNumber() + buildNumber + File.separator + "plugins");

		File finalSite = new File(getUpdateSiteDir() + File.separator + getCodeName() + File.separator + getRevisionNumber() + buildNumber + File.separator + "site.xml");

		try {
			// copie de l'update site
			System.out.println("\t- Update Site copy under <Revision number>-<build number> :");
			FileHelper.copyFiles(new File(getBuildDirectory() + File.separator + getBuildLabel() + File.separator + getArchivePrefix() + File.separator + "features"), finalFeatures, true);
			System.out
					.println("\t\t. on " + finalFeatures + " from " + getBuildDirectory() + File.separator + getBuildLabel() + File.separator + getArchivePrefix() + File.separator + "features DONE");
			FileHelper.copyFiles(new File(getBuildDirectory() + File.separator + getBuildLabel() + File.separator + getArchivePrefix() + File.separator + "plugins"), finalPlugins, true);
			System.out.println("\t\t. on " + finalPlugins + " from " + getBuildDirectory() + File.separator + getBuildLabel() + File.separator + getArchivePrefix() + File.separator + "plugins DONE");

			// copie du site.xml pour l'update site
			FileHelper.copyFiles(new File(getBuildPath() + File.separator + "site.xml"), finalSite, true);
			System.out.println("\t\t. on " + finalSite + " from " + getBuildPath() + File.separator + "site.xml DONE");

			// creation du dossier final s'il n'ï¿½xiste pas
			if (!new File(getFinalDirectory()).exists())
				new File(getFinalDirectory()).mkdir();

			/*
			 * System.out.println("\t- Suppression de l'ancien update site" );
			 * if (new File(getUpdateSiteDir() + File.separator + getCodeName()
			 * + File.separator + "features").exists()) {
			 * FileHelper.deleteFile(new File(getUpdateSiteDir() +
			 * File.separator + getCodeName() + File.separator + "features"));
			 * new File(getUpdateSiteDir() + File.separator + getCodeName() +
			 * File.separator + "features").mkdir(); } if (new
			 * File(getUpdateSiteDir() + File.separator + getCodeName() +
			 * File.separator + "plugins").exists()) { FileHelper.deleteFile(new
			 * File(getUpdateSiteDir() + File.separator + getCodeName() +
			 * File.separator + "plugins")); new File(getUpdateSiteDir() +
			 * File.separator + getCodeName() + File.separator +
			 * "plugins").mkdir(); }
			 */

			// copie de l'update site
			System.out.println("\t- Update Site copy :");
			FileHelper.copyFiles(new File(getBuildDirectory() + File.separator + getBuildLabel() + File.separator + getArchivePrefix() + File.separator + "features"), new File(getUpdateSiteDir()
					+ File.separator + getCodeName() + File.separator + "features"), true);
			System.out.println("\t\t. on " + getUpdateSiteDir() + File.separator + getCodeName() + File.separator + "features from " + getBuildDirectory() + File.separator + getBuildLabel()
					+ File.separator + getArchivePrefix() + File.separator + "features DONE");
			FileHelper.copyFiles(new File(getBuildDirectory() + File.separator + getBuildLabel() + File.separator + getArchivePrefix() + File.separator + "plugins"), new File(getUpdateSiteDir()
					+ File.separator + getCodeName() + File.separator + "plugins"), true);
			System.out.println("\t\t. on " + getUpdateSiteDir() + File.separator + getCodeName() + File.separator + "plugins from " + getBuildDirectory() + File.separator + getBuildLabel()
					+ File.separator + getArchivePrefix() + File.separator + "plugins DONE");

			// copie du site.xml pour l'update site
			FileHelper.copyFiles(new File(getBuildPath() + File.separator + "site.xml"), new File(getUpdateSiteDir() + File.separator + getCodeName() + File.separator + "site.xml"), true);
			System.out.println("\t\t. on " + getUpdateSiteDir() + File.separator + getCodeName() + File.separator + "site.xml from " + getBuildPath() + File.separator + "site.xml DONE");

			// copie de la doc
			System.out.println("\t- Generated Doc copy :");
			FileHelper.copyFiles(new File(getBuildPath() + File.separator + "doc"), new File(getFinalDirectory() + File.separator + "doc"), true);
			System.out.println("\t\t. on " + getFinalDirectory() + File.separator + "doc from " + getBuildPath() + File.separator + "doc DONE");

			// copie des fichiers compilï¿½s
			if (!new File(getFinalDirectory() + File.separator + "logs").exists())
				new File(getFinalDirectory() + File.separator + "logs").mkdir();

			// copie des logs (pour la compilation de chaque projet)
			System.out.println("\t- Logs copy :");
			FileHelper.copyFiles(new File(getBuildDirectory() + File.separator + getBuildLabel() + File.separator + "compilelogs"), new File(getFinalDirectory() + File.separator + "logs"
					+ File.separator + getCodeName() + File.separator + "compilelogs"), true);
			System.out.println("\t\t. on " + getFinalDirectory() + File.separator + "logs" + File.separator + getCodeName() + File.separator + "compilelogs from " + getBuildDirectory()
					+ File.separator + getBuildLabel() + File.separator + "compilelogs DONE");

			// copie des fichiers de log (pour tout le traitement)
			/*
			 * if (Application.EnterpriseRelease) { FileHelper.copyFiles(new
			 * File(getBuildPath() + File.separator +
			 * "logbuildSVNsvnCommit.txt"), new File(getFinalDirectory() +
			 * File.separator + "logs" + File.separator + getCodeName() +
			 * File.separator + "logCommit.txt"), true); }
			 */
			FileHelper.copyFiles(new File(getBuildPath() + File.separator + "logbuildbuild.txt"), new File(getFinalDirectory() + File.separator + "logs" + File.separator + getCodeName()
					+ File.separator + "logBuild.txt"), true);
			System.out.println("\t\t. on " + getFinalDirectory() + File.separator + "logs" + File.separator + getCodeName() + File.separator + "logBuild.txt from " + getBuildPath() + File.separator
					+ "logbuildbuild.txt DONE");

			FileHelper.copyFiles(new File(getBuildPath() + File.separator + "logjarBuilderjarBuilder.txt"), new File(getFinalDirectory() + File.separator + "logs" + File.separator + getCodeName()
					+ File.separator + "logBuildJar.txt"), true);
			System.out.println("\t\t. on " + getFinalDirectory() + File.separator + "logs" + File.separator + getCodeName() + File.separator + "logBuildJar.txt from " + getBuildPath()
					+ File.separator + "logjarBuilderjarBuilder.txt DONE");

			if (!Application.parametre) {
				FileHelper.copyFiles(new File(getBuildPath() + File.separator + "logbuildSVNbuild.txt"), new File(getFinalDirectory() + File.separator + "logs" + File.separator + getCodeName()
						+ File.separator + "logSVN.txt"), true);
			}

			// copie des fichiers compilï¿½s
			System.out.println("\t- Compiled files copy :");
			if (!new File(getFinalDirectory() + File.separator + "bin").exists())
				new File(getFinalDirectory() + File.separator + "bin").mkdir();

			List<String> projects = getProjects();

			for (int i = 0; i < projects.size(); i++) {
				if (projects.get(i).indexOf("feature") == -1) {
					if (new File(getBuildDirectory() + File.separator + "plugins" + File.separator + projects.get(i) + File.separator + "@dot").exists()) {
						FileHelper.copyFiles(new File(getBuildDirectory() + File.separator + "plugins" + File.separator + projects.get(i) + File.separator + "@dot"), new File(getFinalDirectory()
								+ File.separator + "bin" + File.separator + getCodeName() + File.separator + projects.get(i)), true);
						System.out.println("\t\t. on " + getFinalDirectory() + File.separator + "bin" + File.separator + getCodeName() + File.separator + projects.get(i) + " from "
								+ getBuildDirectory() + File.separator + "plugins" + File.separator + projects.get(i) + File.separator + "@dot DONE");
					}
				}
			}

			FileHelper.copyFiles(new File(getBuildDirectory() + File.separator + "features"), new File(getFinalDirectory() + File.separator + "bin" + File.separator + getCodeName()), true);
			System.out.println("\t\t. on " + getFinalDirectory() + File.separator + "bin" + File.separator + getCodeName() + " from " + getBuildDirectory() + File.separator + "features DONE");

			// suppression du repertoire de travail
			/*
			 * System.out.println("\t- Working dir Suppression" );
			 * FileHelper.deleteFile(new File(getBuildDirectory()));
			 * System.out.println("\t\t. " + getBuildDirectory() + " DONE");
			 * 
			 * FileHelper.deleteFile(new File(getBuildPath() + File.separator +
			 * "doc")); System.out.println("\t\t. " + getBuildPath() +
			 * File.separator + "doc DONE");
			 */

			// suppression des fichiers crï¿½ï¿½s
			// FileHelper.deleteFile(new File(Utils.getBuildPath()+
			// File.separator + "buildSVN.xml"));
			/*
			 * FileHelper.deleteFile(new File(Utils.getBuildPath() +
			 * File.separator + "build.xml")); FileHelper.deleteFile(new
			 * File(Utils.getBuildPath() + File.separator +
			 * "buildAuto.product")); FileHelper.deleteFile(new
			 * File(Utils.getBuildPath() + File.separator + "jarBuilder.xml"));
			 * 
			 * // suppression des fichiers de logs if
			 * (Application.EnterpriseRelease){ FileHelper.deleteFile(new
			 * File(getBuildPath() + File.separator + "logbuildSVNbuild.txt"));
			 * // FileHelper.deleteFile(new File(getBuildPath() + File.separator
			 * // + "logbuildSVNsvnCommit.txt")); } FileHelper.deleteFile(new
			 * File(getBuildPath() + File.separator + "logbuildbuild.txt"));
			 * 
			 * FileHelper.deleteFile(new File(getBuildPath() + File.separator +
			 * "logjarBuilderjarBuilder.txt"));
			 */

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getUpdateSiteDir() {
		String updateSiteDir = "updateSiteDir";
		// if (!Application.EnterpriseRelease) {
		// updateSiteDir = "updateSiteDirLabs";
		// }
		return ouvrirFichier("build.properties").getProperty(updateSiteDir);

	}

	/**
	 * Remplace, pour la feature donnï¿½e, le texte du copyright et de la
	 * licence (ainsi que leur url) par rapport au fichier indiquï¿½ dans le
	 * build.properties
	 * 
	 * @param featureName
	 */
	public static void updateCopyrightLicence(String featureName, String path) {

		// String[] tmp = featureName.split("\\.");

		// if (tmp[3].equals("Util")) {
		// tmp[3] = "Utils";
		// }

		// String path = Application.workspace + File.separator + "S-IDE"
		// + File.separator + tmp[3] + File.separator + "trunk";

		String fileFeaturePath = path + File.separator + featureName + File.separator + "feature.xml";

		org.jdom.Document document = null;
		org.jdom.Element racine;

		// On crï¿½e une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();

		try {
			// On crï¿½e un nouveau document JDOM avec en argument le fichier
			// XML
			document = sxb.build(new File(fileFeaturePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On initialise un nouvel ï¿½lï¿½ment racine avec l'ï¿½lï¿½ment racine
		// du
		// document.
		racine = document.getRootElement();

		// On crï¿½e une List contenant tous les noeuds "copyright" de
		// l'Element racine
		List<?> listCopyright = racine.getChildren("copyright");

		// On crï¿½e un Iterator sur notre liste
		Iterator<?> i = listCopyright.iterator();
		// on va parcourir tous les plugins
		while (i.hasNext()) {
			// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle afin de
			// pouvoir utiliser les mï¿½thodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) i.next();

			// on applique le texte du copyright
			courant.setText(getCopyrightText());

			// on change l'url du copyright
			courant.setAttribute("url", getCopyrightURL());
		}

		// On crï¿½e une List contenant tous les noeuds "license" de
		// l'Element racine
		List<?> listLicense = racine.getChildren("license");

		// On crï¿½e un Iterator sur notre liste
		Iterator<?> j = listLicense.iterator();
		// on va parcourir tous les plugins
		while (j.hasNext()) {
			// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle afin de
			// pouvoir utiliser les mï¿½thodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) j.next();

			// on applique le texte du license
			courant.setText(getLicenseText());

			// on change l'url du license
			courant.setAttribute("url", getlicenseURL());
		}

		// Enregistrement du fichier
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(fileFeaturePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Remplace, pour la feature donnï¿½e, le texte du copyright et de la
	 * licence (ainsi que leur url) par rapport au fichier indiquï¿½ dans le
	 * build.properties
	 * 
	 * @param featureName
	 */
	public static void updateCopyrightLicence(String featureName) {

		String[] tmp = featureName.split("\\.");

		if (tmp[3].equals("Util")) {
			tmp[3] = "Utils";
		}

		String path = Application.workspace + File.separator + "S-IDE" + File.separator + tmp[3] + File.separator + "trunk";

		String fileFeaturePath = path + File.separator + featureName + File.separator + "feature.xml";

		org.jdom.Document document = null;
		org.jdom.Element racine;

		// On crï¿½e une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();

		try {
			// On crï¿½e un nouveau document JDOM avec en argument le fichier
			// XML
			document = sxb.build(new File(fileFeaturePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On initialise un nouvel ï¿½lï¿½ment racine avec l'ï¿½lï¿½ment racine
		// du
		// document.
		racine = document.getRootElement();

		// On crï¿½e une List contenant tous les noeuds "copyright" de
		// l'Element racine
		List<?> listCopyright = racine.getChildren("copyright");

		// On crï¿½e un Iterator sur notre liste
		Iterator<?> i = listCopyright.iterator();
		// on va parcourir tous les plugins
		while (i.hasNext()) {
			// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle afin de
			// pouvoir utiliser les mï¿½thodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) i.next();

			// on applique le texte du copyright
			courant.setText(getCopyrightText());

			// on change l'url du copyright
			courant.setAttribute("url", getCopyrightURL());
		}

		// On crï¿½e une List contenant tous les noeuds "license" de
		// l'Element racine
		List<?> listLicense = racine.getChildren("license");

		// On crï¿½e un Iterator sur notre liste
		Iterator<?> j = listLicense.iterator();
		// on va parcourir tous les plugins
		while (j.hasNext()) {
			// On recrï¿½e l'Element courant ï¿½ chaque tour de boucle afin de
			// pouvoir utiliser les mï¿½thodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) j.next();

			// on applique le texte du license
			courant.setText(getLicenseText());

			// on change l'url du license
			courant.setAttribute("url", getlicenseURL());
		}

		// Enregistrement du fichier
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(fileFeaturePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Mï¿½thode qui retourne le contenu du fichier passï¿½ en paramï¿½tre
	 * 
	 * @param f
	 *            Le fichier a retourner
	 * @return Le contenu du fichier
	 */
	public static String loadFile(File f) {
		StringWriter out = null;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
			out = new StringWriter();
			int b;
			while ((b = in.read()) != -1)
				out.write(b);
			out.flush();
			out.close();
			in.close();
			return out.toString();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return out.toString();
	}

	/**
	 * Return the current Date
	 * 
	 * @return the current Date
	 */
	public static String getDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	/**
	 * Return the current Date
	 * 
	 * @return the current Date
	 */
	public static String getDate2() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}

	/**
	 * Return the current Time
	 * 
	 * @return the current Time
	 */
	public static String getTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	public static boolean updateProduct(File product, File plugin_featureRepo) {
		System.out.println("  Product to update :" + product);
		System.out.println("  Pluginq/Features Repository :" + plugin_featureRepo);

		boolean changes = false;
		try {
			Document productDoc = buildJdomDocument(product);
			// search for features reference to update
			// product/features/feature
			XPath xpa = XPath.newInstance("//product/features/feature");
			List<?> lmd = xpa.selectNodes(productDoc.getRootElement());
			for (Object object : lmd) {
				Element el = (Element) object;
				String id = null;
				String version = "";
				Attribute attId = el.getAttribute("id");
				if (attId != null) {
					id = attId.getValue();
				}
				Attribute attVersion = el.getAttribute("version");
				if (attVersion != null) {
					version = attVersion.getValue();
				}

				// search in repos this feature

				File features = new File(plugin_featureRepo, "features");
				for (File featureDir : features.listFiles()) {
					if (featureDir.isDirectory()) {
						// get feature.xml
						File featureFile = new File(featureDir, "feature.xml");
						Document featureDoc = buildJdomDocument(featureFile);
						Element featureEl = featureDoc.getRootElement();
						String id_ = null;
						String version_ = "";
						Attribute att_ = featureEl.getAttribute("id");
						if (att_ != null) {
							id_ = att_.getValue();
						}
						att_ = featureEl.getAttribute("version");
						if (att_ != null) {
							version_ = att_.getValue();
						}

						if (id_.equals(id)) {
							// feature found in repository
							if (!version_.equals(version)) {
								// update feature version in .product
								attVersion.setValue(version_);
								changes = true;
							}
						}
					}
				}
			}

			if (changes) {
				Attribute version = productDoc.getRootElement().getAttribute("version");
				String oldVersion=version.getValue();
				String[] pattern = getNumVersionPattern();
				String newversion = updatepom(version.getValue().split("\\."), pattern);
				version.setValue(newversion);
				System.out.println("sideProduct version :"+oldVersion);
				System.out.println("sideProduct new version :"+newversion);
				// save changes
				saveXMLFile(product, productDoc);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return changes;
	}

	public static void saveXMLFile(File xml, Document dom) throws FileNotFoundException, IOException {
		org.jdom.output.XMLOutputter out = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		out.setFormat(format);
		FileOutputStream outStream = new FileOutputStream(xml);
		out.output(dom, outStream);
		outStream.close();
	}

	public static Document buildJdomDocument(File xmlFile) throws Exception {
		Document doc;
		org.jdom.input.SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
		return doc;
	}
}
