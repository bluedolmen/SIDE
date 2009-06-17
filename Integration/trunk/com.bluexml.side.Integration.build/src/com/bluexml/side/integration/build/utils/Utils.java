package com.bluexml.side.integration.build.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Utils {

	private static ArrayList<String> listeFeatureModif = new ArrayList<String>();
	private static String revisionNumber = "0";

	/**
	 * Méthode qui ouvre le fichier de proprerties
	 * 
	 */
	public static Properties ouvrirFichier(String fichier) {
		FileInputStream fileStream = null;
		Properties properties = null;

		try {
			fileStream = new FileInputStream(fichier);

			properties = new Properties();

			properties.load(fileStream);

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
	public static String[] getProjects() {

		return ouvrirFichier("build.properties").getProperty("project").split(
				",");
	}

	public static String getBuildPath() {

		return ouvrirFichier("build.properties").getProperty("buildDir");
	}

	/**
	 * Retourne le repository
	 */
	public static String getRepository() {
		return ouvrirFichier("build.properties").getProperty("repository");
	}

	/**
	 * Retourne le chemin du eclipse
	 */
	public static String getEclipseHome() {

		return ouvrirFichier("build.properties").getProperty("eclipseLocation");
	}

	/**
	 * Retourne la version du launcher
	 * 
	 */
	public static String getLauncherVersion() {
		return "org.eclipse.equinox.launcher_"
				+ ouvrirFichier("build.properties").getProperty(
						"equinoxLauncherPluginVersion") + ".jar";
	}

	/**
	 * 
	 * 
	 */
	public static String getBuildDirectory() {
		return ouvrirFichier("build.properties").getProperty("buildDir")
				+ File.separator
				+ ouvrirFichier("build.properties").getProperty("buildName");
	}

	public static String getFinalDirectory() {
		return ouvrirFichier("build.properties").getProperty("finalDirectory");
	}

	public static String getBuildLabel() {
		return ouvrirFichier("build.properties").getProperty("buildType") + "."
				+ ouvrirFichier("build.properties").getProperty("buildId");
	}

	public static String getArchivePrefix() {
		return ouvrirFichier("build.properties").getProperty("archivePrefix");
	}

	public static String getForceNumberVersion() {
		return ouvrirFichier("build.properties").getProperty(
				"forceNumberVersion");
	}

	public static String getEquinoxLauncherDirectoryVersion() {
		return ouvrirFichier("build.properties").getProperty(
				"equinoxLauncherDirectoryVersion");
	}
	
	public static String getNewCategory() {
		return ouvrirFichier("build.properties").getProperty(
				"newCategory");
	}
	
	public static String getPublicUpdateSiteDirectory() {
		return ouvrirFichier("build.properties").getProperty("publicUpdateSiteDirectory");
	}
	
	public static String getSvnPassword() {
		return ouvrirFichier("build.properties").getProperty("svn-username");
	}

	public static String getSvnUserName() {
		return ouvrirFichier("build.properties").getProperty("svn-password");
	}

	/**
	 * Retourne le numéro de version pour un projet donné
	 * 
	 * @param projectName
	 *            el nom du projet
	 * @return le numéro de version pour un projet donné
	 */
	public static String getVersionNumber(String projectName) {
		String version = "";

		// En fonction du type du projet (feature ou plugin)
		// on ira regarder soit dans le MANIFEST ou alors dans le feature.xml
		if (projectName.indexOf("feature") == -1
				&& !projectName.equals("com.bluexml.side.Util")) {
			version = ouvrirFichier(
					getBuildDirectory() + "_CO" + File.separator + "plugins"
							+ File.separator + projectName + File.separator
							+ "META-INF" + File.separator + "MANIFEST.MF")
					.getProperty("Bundle-Version");
		} else {
			org.jdom.Document document = null;
			org.jdom.Element racine;

			// On crée une instance de SAXBuilder
			SAXBuilder sxb = new SAXBuilder();
			try {
				// On crée un nouveau document JDOM avec en argument le fichier
				// XML
				document = sxb.build(new File(getBuildDirectory() + "_CO"
						+ File.separator + "features" + File.separator
						+ projectName + File.separator + "feature.xml"));
			} catch (Exception e) {
			}

			// On initialise un nouvel élément racine avec l'élément racine du
			// document.
			racine = document.getRootElement();

			version = racine.getAttributeValue("version");

		}
		return version;
	}

	/**
	 * Update le numéro de version du projet, le pattern pour cet update est
	 * dans le fichier build.properties
	 * 
	 * @param projectName
	 */
	public static void updateVersionNumber(String projectName) {
		String[] pattern = ouvrirFichier("build.properties").getProperty(
				"number-pattern").split("\\.");

		// En fonction du type du projet (feature ou plugin)
		// on ira regarder soit dans le MANIFEST.MF ou alors dans le feature.xml
		if (projectName.indexOf("feature") == -1
				&& !projectName.equals("com.bluexml.side.Util")) {
			// chemin vers le MANIFEST.MF
			String filePluginPath = getBuildDirectory() + "_CO"
					+ File.separator + "plugins" + File.separator + projectName
					+ File.separator + "META-INF" + File.separator
					+ "MANIFEST.MF";

			// on récupère dans un tableau les 3 numéros de version du projet
			String[] number = ouvrirFichier(filePluginPath).getProperty(
					"Bundle-Version").split("\\.");

			String ligne = "";
			try {
				BufferedReader reader = new BufferedReader(new FileReader(
						filePluginPath));
				PrintWriter writer = new PrintWriter(new FileWriter(
						filePluginPath + ".txt"));
				while ((ligne = reader.readLine()) != null) {
					// si la ligne contient "Bundle-Version:"
					if (ligne.indexOf("Bundle-Version:") != -1) {
						// on supprime tout ce qui se trouve après
						// "Bundle-Version:"
						ligne = ligne.substring(0, "Bundle-Version:".length());
						// on ajoute a la ligne le nouveau numéro de version
						// si on ne force pas la mise a jour du numéro de
						// version
						if ("".equals(getForceNumberVersion()))
							ligne += " " + update(number, pattern);
						else
							ligne += " " + getForceNumberVersion() + ".v"
									+ getRevisionNumber() + "-" + getDate();
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
			new File(filePluginPath + ".txt")
					.renameTo(new File(filePluginPath));

		} else {
			// boolean qui permet de savoir s'il faut changer le numéro du
			// feature ou non
			boolean featureAModifier = false;

			if (listeFeatureModif.contains(projectName))
				featureAModifier = true;

			// chemin vers le feature.xml
			String fileFeaturePath = getBuildDirectory() + "_CO"
					+ File.separator + "features" + File.separator
					+ projectName + File.separator + "feature.xml";

			org.jdom.Document document = null;
			org.jdom.Element racine;

			// On crée une instance de SAXBuilder
			SAXBuilder sxb = new SAXBuilder();

			try {
				// On crée un nouveau document JDOM avec en argument le fichier
				// XML
				document = sxb.build(new File(fileFeaturePath));
			} catch (Exception e) {
				e.printStackTrace();
			}

			// On initialise un nouvel élément racine avec l'élément racine du
			// document.
			racine = document.getRootElement();

			// On va maintenant mettre a jour les numéro de version des plugins
			// associés a la feature

			// on garde en mémoire l'ancien numéro de version du plugin pour
			// savoir s'il a changer et ainsi savoir s'il faut changer ou non le
			// numéro de version de la feature

			String oldVersionNumber = "";

			// On crée une List contenant tous les noeuds "plugin" de
			// l'Element racine
			List<?> listPlugins = racine.getChildren("plugin");

			// On crée un Iterator sur notre liste
			Iterator<?> i = listPlugins.iterator();
			// on va parcourir tous les plugins
			while (i.hasNext()) {
				// On recrée l'Element courant à chaque tour de boucle afin de
				// pouvoir utiliser les méthodes propres aux Element comme :
				// selectionner un noeud fils, modifier du texte, etc...
				Element courant = (Element) i.next();

				// sauvegarde du numéro de version
				oldVersionNumber = courant.getAttributeValue("version");

				// on regarde si le numéro de version du plugin a changé
				if (!oldVersionNumber.equals(getVersionNumber(courant
						.getAttributeValue("id")))) {
					// On modifie le numéro de version du plugin courant
					courant.setAttribute("version", getVersionNumber(courant
							.getAttributeValue("id")));

					// on indique que le numéro de feature doit changer
					featureAModifier = true;
				}
			}

			// on récupère dans un tableau les 3 numéros de version du projet
			String[] number = racine.getAttributeValue("version").split("\\.");

			// on change le numéro de version (s'il le faut)
			if ("".equals(getForceNumberVersion())) {
				if (featureAModifier){
					listeFeatureModif.add(projectName);
					racine.setAttribute("version", update(number, pattern));
				}
			} else
				racine.setAttribute("version", getForceNumberVersion() + ".v"
						+ getRevisionNumber() + "-" + getDate());

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

	/**
	 * change le numéro de version en fonction du pattern
	 * 
	 * @param number
	 *            un tableau des 3 numéro de version
	 * @param pattern
	 *            un tableau avec les 3 éléments du pattern
	 * @return Le numéro de version sous la forme 1.0.12
	 */
	public static String update(String[] number, String[] pattern) {

		boolean change = false;
		
		for (int i = 0; i < 3; i++) {
			// test si l'élément est un nombre si une exception est levée,
			// l'élément n'est pas un nombre
			try {
				Integer.valueOf(pattern[i]);
				number[i] = pattern[i];
			} catch (NumberFormatException e) {
				if(change)
					number[i] = "0";
				else {
					if (pattern[i].equals("u")) {
						change = true;
						number[i] = String.valueOf(Integer.valueOf(number[i]) + 1);
					}
				}
			}
		}
		return number[0] + "." + number[1] + "." + number[2] + ".v"
				+ getRevisionNumber() + "-" + getDate();
	}

	/**
	 * Cette méthode analyse le fichier de log (créé via le log de l'update ant)
	 * et regarde si des updates ont été fait et ainsi, changer le numéro de
	 * version du projet concerné
	 */
	public static void traitementUpdate() {

		ArrayList<String> listePlugin = new ArrayList<String>();

		// si on ne force pas la mise a jour du numéro de version
		if ("".equals(getForceNumberVersion())) {
			String ligne = "";
			String modif = "";
			ArrayList<String> listeProjet = new ArrayList<String>();
			boolean update = false;

			// ouverture d'un flux du fichier
			try {
				BufferedReader ficTexte = new BufferedReader(new FileReader(
						new File(getBuildDirectory() + "_CO" + File.separator
								+ "logbuildSVNbuild.txt")));

				if (ficTexte == null) {
					throw new FileNotFoundException("Fichier non trouvé");
				}

				// Analyse et copie de chaque ligne
				while ((ligne = ficTexte.readLine()) != null) {
					// condition pour ne pas traiter les logs du checkout
					if (ligne.indexOf("svnUD:") != -1) {
						update = true;
					}
					if (!"".equals(ligne)) {

						if (ligne.indexOf("At revision ") != -1 && update) {
							revisionNumber = ligne.substring("At revision "
									.length(), ligne.length() - 1);
						} else if ((ligne.charAt(0) == 'A'
								|| ligne.charAt(0) == 'U' || ligne.charAt(0) == 'D')
								&& ligne.charAt(1) == ' ' && update) {
							modif = ligne.substring(5, ligne.length());
							modif.trim();
							modif = modif.substring(
									(getBuildDirectory() + "_CO").length(),
									modif.length());
							if (modif.indexOf("plugins") != -1)
								modif = modif.substring((File.separator
										+ "plugins" + File.separator).length(),
										modif.length());
							else
								modif = modif
										.substring(
												(File.separator + "features" + File.separator)
														.length(), modif
														.length());
							modif.trim();
							if (!listeProjet.contains((String) modif
									.subSequence(0, modif
											.indexOf(File.separator))))
								listeProjet.add((String) modif.subSequence(0,
										modif.indexOf(File.separator)));
						}
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			// on parcours la liste des projets qui ont été modifié
			for (String element : listeProjet) {
				// on met tous les plugins modifiés dans un tableau
				if (element.indexOf("feature") == -1
						&& !element.equals("com.bluexml.side.Util"))
					listePlugin.add(element);
				// et tous les features dans un autre
				else
					listeFeatureModif.add(element);
			}

			// si on force la mise a jour du numéro de version
		} else {

			String[] projects = getProjects();

			for (int i = 0; i < projects.length; i++) {
				if (projects[i].indexOf("feature") == -1
						&& !projects[i].equals("com.bluexml.side.Util"))
					listePlugin.add(projects[i]);
			}

		}
		
		// On parcours la liste des plugins et on les met a jour
		for (String plugin : listePlugin) {
			updateVersionNumber(plugin);
		}

		String[] projects = getProjects();

		// On fait la meme chose mais pour toutes les features
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].indexOf("feature") != -1
					|| projects[i].equals("com.bluexml.side.Util"))
				updateVersionNumber(projects[i]);
		}
		
		//affichage des données
		if(listePlugin.size() != 0){
			System.out.println("\nListe des plugins modifiés: ");
			// On parcours la liste des plugins et on les met a jour
			for (String plugin : listePlugin) {
				System.out.println("\t- "+plugin+": "+getVersionNumber(plugin));
			}
		}
		
		if(listeFeatureModif.size() != 0){
			System.out.println("\nListe des features modifiées: ");
			for(String feature: listeFeatureModif){
				System.out.println("\t- "+feature+": "+getVersionNumber(feature));
			}
		}
		//fin affichage

	}

	/**
	 * 
	 * Traitement final: Copie de l'update site dans le repertoire final Copie
	 * de log.txt dans le repertoire final Suppression de workingcopy
	 * 
	 */
	public static void finalTraitement() {
		try {
			// suppression du dossier final s'il éxiste
			if (new File(getFinalDirectory()).exists())
				FileUtils.recursifDelete(new File(getFinalDirectory()));

			new File(getFinalDirectory()).mkdir();

			// copie de l'update site
			FileUtils.copy(new File(getBuildDirectory() + File.separator
					+ getBuildLabel() + File.separator + getArchivePrefix()),
					new File(getFinalDirectory() + File.separator
							+ getArchivePrefix()));

			// copie du site.xml pour l'update site
			FileUtils.copy(new File(getBuildPath() + File.separator
					+ "site.xml"), new File(getFinalDirectory()
					+ File.separator + getArchivePrefix() + File.separator
					+ "site.xml"));

			// copie des fichiers compilés
			new File(getFinalDirectory() + File.separator + "Logs").mkdir();
			
			// copie des logs (pour la compilation de chaque projet)
			FileUtils.copy(new File(getBuildDirectory() + File.separator
					+ getBuildLabel() + File.separator + "compilelogs"),
					new File(getFinalDirectory() + File.separator + "Logs"
							+ File.separator + "compilelogs"));

			// copie des fichiers de log (pour tout le traitment)
			FileUtils.copy(new File(getBuildDirectory() + "_CO"
					+ File.separator + "logbuildSVNbuild.txt"), new File(
					getFinalDirectory() + File.separator + "Logs"
							+ File.separator + "logSVN.txt"));
			FileUtils.copy(new File(getBuildDirectory() + "_CO"
					+ File.separator + "logbuildbuild.txt"), new File(
					getFinalDirectory() + File.separator + "Logs"
							+ File.separator + "logBuild.txt"));
			FileUtils.copy(new File(getBuildDirectory() + "_CO"
					+ File.separator + "logbuildSVNsvnCommit.txt"), new File(
					getFinalDirectory() + File.separator + "Logs"
							+ File.separator + "logCommit.txt"));

			// copie des fichiers compilés
			new File(getFinalDirectory() + File.separator + "bin").mkdir();

			String[] projects = getProjects();

			for (int i = 0; i < projects.length; i++) {
				if (projects[i].indexOf("feature") == -1
						&& !projects[i].equals("com.bluexml.side.Util"))
					FileUtils.copy(new File(getBuildDirectory()
							+ File.separator + "plugins" + File.separator
							+ projects[i] + File.separator + "bin"), new File(
							getFinalDirectory() + File.separator + "bin"
									+ File.separator + projects[i]));
			}

			FileUtils.copy(new File(getBuildDirectory() + File.separator
					+ "features"), new File(getFinalDirectory()
					+ File.separator + "bin"));

			// suppression du repertoire de travail
			FileUtils.recursifDelete(new File(getBuildDirectory()));

			// suppression des fichiers créés
			FileUtils.deleteFile(Utils.getBuildPath() + File.separator
					+ "buildSVN.xml");
			FileUtils.deleteFile(Utils.getBuildPath() + File.separator
					+ "build.xml");
			FileUtils.deleteFile(Utils.getBuildPath() + File.separator
					+ "buildAuto.product");
			
			// suppression des fichiers de logs
			FileUtils.deleteFile(getBuildDirectory() + "_CO"
					+ File.separator + "logbuildSVNbuild.txt");
			FileUtils.deleteFile(getBuildDirectory() + "_CO"
					+ File.separator + "logbuildbuild.txt");
			FileUtils.deleteFile(getBuildDirectory() + "_CO"
					+ File.separator + "logbuildSVNsvnCommit.txt");

		} catch (IOException e) {
			e.printStackTrace();
		}
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

	/**
	 * Retourne le numéro de version
	 * 
	 * @return
	 */
	public static String getRevisionNumber() {
		return revisionNumber;
	}

	/**
	 * Met a jour le site.xml en fonction des features. Si une feature n'est pas
	 * présente dans le site.xml, elle est ajoutée et placée dans la catégorie
	 * 'other'
	 * 
	 */
	public static void updateSiteXml() {

		// chemin vers le feature.xml
		String fileSitePath = getBuildPath() + File.separator + "site.xml";
		//String fileSitePath = getFinalDirectory()+ File.separator + getArchivePrefix() + File.separator+ "site.xml";
		String[] projects = getProjects();

		// tableau qui contiendra la liste des features
		ArrayList<String> listeFeature = new ArrayList<String>();

		// on met tous les features dans le tableau
		for (int i = 0; i < projects.length; i++) {

			if (projects[i].indexOf("feature") != -1
					|| projects[i].equals("com.bluexml.side.Util"))
				listeFeature.add(projects[i]);
		}

		org.jdom.Document document = null;
		Element racine;

		// On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();

		try {
			// On crée un nouveau document JDOM avec en argument le fichier
			// XML
			document = sxb.build(new File(fileSitePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On initialise un nouvel élément racine avec l'élément racine du
		// document.
		racine = document.getRootElement();

		// On crée une List contenant tous les noeuds "feature" de
		// l'Element racine
		List<?> listFeatures = racine.getChildren("feature");

		// On crée un Iterator sur notre liste
		Iterator<?> i = listFeatures.iterator();

		// Boucle qui permet de mettre à jour le numéro de version de chaque
		// feature
		while (i.hasNext()) {
			// On recrée l'Element courant à chaque tour de boucle afin de
			// pouvoir utiliser les méthodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) i.next();

			// on regarde si l'élément parcouru est dans le tableau de features
			if (listeFeature.contains(courant.getAttributeValue("id"))) {
				// on supprime le feature de la liste
				listeFeature.remove(courant.getAttributeValue("id"));

				// On modifie le numéro de version du plugin courant
				courant.setAttribute("version", getVersionNumber(courant
						.getAttributeValue("id")));

				courant.setAttribute("url", "features/"
						+ courant.getAttributeValue("id") + "_"
						+ getVersionNumber(courant.getAttributeValue("id"))
						+ ".jar");
			}
		}

		// on parcourt le tableau de feature
		// on va ajouter les features présentes dans le tableau (et donc qui ne
		// sont pas présentes dans le site.xml) et les ajouter au site.xml

		for (String feature : listeFeature) {
			Element newElement = new Element("feature");

			newElement.setAttribute("url", "features/" + feature + "_"
					+ getVersionNumber(feature) + ".jar");
			newElement.setAttribute("id", feature);
			newElement.setAttribute("version", getVersionNumber(feature));

			Element newCategory = new Element("category");

			newCategory.setAttribute("name", "S-IDE "+getNewCategory());

			newElement.addContent(newCategory);
			racine.addContent(newElement);
		}

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

}
