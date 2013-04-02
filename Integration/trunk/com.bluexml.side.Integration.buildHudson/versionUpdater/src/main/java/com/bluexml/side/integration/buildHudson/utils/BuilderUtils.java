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
package com.bluexml.side.integration.buildHudson.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.integration.buildHudson.ProjectVersionUpdater;

public class BuilderUtils {
	private static Logger logger = Logger.getLogger(BuilderUtils.class);

	private Properties buildProperties;
	// properties
	private static final String buildDir = "buildDir";
	private static final String project = "project";
	private static final String project_enterprise = "project.enterprise";
	private static final String forceNumberVersion = "forceNumberVersion";
	private static final String number_pattern = "number-pattern";

	private String workspace;
	private String revisionNumber;
	private String build_number;
	private String sourceSVNName;
	private boolean useRepositoryCopy;

	public BuilderUtils(Properties buildProperties, String workspace, String build_number, String revisionNumber, boolean useRepositoryCopy) {
		this.buildProperties = buildProperties;
		this.workspace = workspace;
		this.build_number = build_number;
		this.revisionNumber = revisionNumber;
		this.useRepositoryCopy = useRepositoryCopy;
	}

	public void setSourceSVNName(String sourceSVNName) {
		this.sourceSVNName = sourceSVNName;
	}

	public String getSourceSVNName() {
		return sourceSVNName;
	}

	private Properties getBuildProperties() {
		return buildProperties;
	}

	/**
	 * Méthode qui execute lance un script sh
	 * 
	 * @param script
	 *            nom du script
	 * @throws Exception
	 */
	public static void launchShScript(String scriptFilePath, String baseDir, String[] args) throws Exception {

		Runtime r = Runtime.getRuntime();
		Process p;
		BufferedReader is; // reader for output of process
		String line;

		String command = "";
		File script = new File(scriptFilePath);
		if (script.exists() && script.isFile()) {

			command += script.getAbsolutePath();
			command += " ";
			for (String string : args) {
				command += string;
				command += " ";
			}
			File dir = null;
			if (baseDir != null) {
				dir = new File(baseDir);
				if (!dir.exists()) {
					throw new Exception("launchingScript baseDir not found");
				}
			}
			r.exec(command, null, dir);
			p = r.exec(command);

			is = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((line = is.readLine()) != null) {
				logger.debug(line);
			}
			System.out.flush();
			try {
				p.waitFor(); // wait for process to complete
			} catch (InterruptedException e) {
				System.err.println(e); // "Can'tHappen"
			}
			System.err.println("Process done, exit status was " + p.exitValue());

			if (p.exitValue() != 0) {
				System.exit(1);
			}
		} else {
			throw new Exception("Script not found !" + script);
		}

	}

	/**
	 * Créer le fichier build.xml pour chaque projet
	 */
	public static void createFile(String corps, String folderName, String fileName) {
		File file = new File(folderName + File.separator + fileName);
		PrintWriter writer = null;
		try {
			logger.debug("Create File:" + file);
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
	 * Méthode qui ouvre le fichier de proprerties
	 */
	public static Properties openProperties(String fichier) {
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
			// logger.debug("Properties File loaded :" +
			// props.getAbsolutePath());
			fileStream.close();
		} catch (IOException e) {
			logger.error("Error opening file " + props, e);
		}

		return properties;
	}

	/**
	 * Return the current Date
	 * 
	 * @return the current Date
	 */
	public static String getFormatedDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}

	/**
	 * Return the current Time
	 * 
	 * @return the current Time
	 */
	public static String getTime(Date date) {
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	public String getRepositoryCopyPath() throws Exception {
		if (!useRepositoryCopy) {
			throw new Exception("RepositoryCopy is disable, do not use it !");
		}
		String pathproject = getBuildPath() + File.separator + ProjectVersionUpdater.repositoryCopy;
		return pathproject;
	}

	/**
	 * Return the Build Path: /home/stager/buildAuto/Ankle
	 */
	public String getBuildPath() {
		return getBuildProperties().getProperty(buildDir);
	}

	/**
	 * Retourne la liste des projets
	 */
	public List<String> getProjects(String properties) {
		String property = getBuildProperties().getProperty(properties);
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
	 * Retourne la liste des projets
	 */
	public List<String> getProjects() {
		List<String> projects = getProjects(project);
		projects.addAll(getProjects(project_enterprise));
		return projects;
	}

	public List<String> getEnterpriseProjects() {
		List<String> projects = getProjects(project_enterprise);
		return projects;
	}

	public List<String> getCommunityProjects() {
		List<String> projects = getProjects(project);
		return projects;
	}

	public static List<String> findFile(File f, String s) {
		// logger.debug("BuilderUtils.findFile() baseDir:"+f+" fileName:"+s);
		List<String> listefichierpom = new ArrayList<String>();
		boolean stopfind = false;
		if (f.getName().equals(s) && !(f.getPath().indexOf("src") > -1) && !(f.getPath().indexOf("config") > -1)) {
			listefichierpom.add(f.getPath());
			stopfind = true;
		}

		File[] liste_fils = f.listFiles();
		if (liste_fils != null && stopfind == false) {
			for (int i = 0; i < liste_fils.length; i++) {
				listefichierpom.addAll(findFile(liste_fils[i], s));
			}
		}
		return listefichierpom;
	}

	/**
	 * update list of modified project (poms, features, plugins)
	 * 
	 * @param listeProjetPomsAll
	 * @param listeProjetPomsModif
	 * @param listeProjetModif
	 * @param log
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readSvnLog(List<String> listeProjetPomsAll, List<String> listeProjetPomsModif, List<String> listeProjetModif, File log) throws FileNotFoundException, IOException {
		String ligne;
		String modif;
		// this file contains only svn command output
		if (log == null) {
			log = new File(getPathToLog());
		}
		if (!log.exists()) {
			throw new FileNotFoundException("svn log file :" + log + " not founded");
		}
		BufferedReader ficTexte = new BufferedReader(new FileReader(log));
		logger.debug("###### search for updated project from svn log " + log);

		// Analyse et copie de chaque ligne
		while ((ligne = ficTexte.readLine()) != null) {

			if (!"".equals(ligne)) {

				if (ligne.indexOf("At revision") != -1) {
					String revisionNumber = ligne.substring("At revision".length(), ligne.length()).trim();
					logger.debug("Updated at rev :" + revisionNumber);
				}

				// check svn log for file that can be in conflict
				boolean badCaseChar1 = ligne.charAt(0) == 'M' || ligne.charAt(0) == 'G' || ligne.charAt(0) == ' ';
				boolean badCaseChar2 = ligne.charAt(1) == 'M' || ligne.charAt(1) == 'G' || ligne.charAt(1) == ' ';
				if (badCaseChar1 && badCaseChar2) {
					int lastIndexOf = ligne.trim().lastIndexOf("/");
					if (lastIndexOf != -1) {

					}
					String fileName = ligne.substring(lastIndexOf);
					// MANIFEST.MF pom.xml feature.xml plugin.xml category.xml *.product
					if (fileName.endsWith("category.xml") || fileName.endsWith(".product") || fileName.endsWith("feature.xml") || fileName.endsWith("plugin.xml") || fileName.endsWith("MANIFEST.MF") || fileName.endsWith("pom.xml")) {
						throw new UnsupportedOperationException("SVN have conflict, M or G state detected, a commit have been done after SIDE build start\n relaunch the build should Fix errors line :\n" + ligne);
					}
				}

				boolean char1 = ligne.charAt(0) == 'A' || ligne.charAt(0) == 'U' || ligne.charAt(0) == 'D' || ligne.charAt(0) == ' ';
				boolean char2 = ligne.charAt(1) == 'A' || ligne.charAt(1) == 'U' || ligne.charAt(1) == 'D' || ligne.charAt(1) == ' ';
				if (char1 && char2) {
					String svnStatus = ligne.substring(0, 2);
					logger.debug("ligneChar 1,2 :" + svnStatus);
					if (ligne.indexOf("Integration") > -1 || ligne.indexOf("FrameworksModules") > -1) {
						for (String id : listeProjetPomsAll) {
							String projectNameRegExp = "^.*(/[^/]*/)pom\\.xml$";
							String pName = id.replaceAll("\\\\", "/").replaceAll(projectNameRegExp, "$1");
							if (ligne.indexOf(pName) != -1) {

								if (!listeProjetPomsModif.contains(id)) {
									listeProjetPomsModif.add(id);
									logger.debug("found an updated maven project : " + id);
								}
							}
						}
					}

					modif = ligne.substring(2, ligne.length());
					modif.trim();
					String[] proj = modif.split(File.separator);

					for (int i = 0; i < proj.length; i++) {
						String id = proj[i];
						// check if this is an eclipse project
						try {
							String pathToLocalCopy = getPathToLocalCopy(id);
							// if no error thow the project exists
							// check for .project file
							if (new File(new File(pathToLocalCopy), ".project").exists()) {
								if (!listeProjetModif.contains(id)) {
									listeProjetModif.add(id);
									logger.debug("found an updated eclipse project (plugin or feature): " + id);
								}
							} else {
								logger.debug("current 'project' :" + id + " do not have .project file");
							}
						} catch (Exception e) {
							logger.error("updated project do not exists :" + id + ", svn status :" + svnStatus, e);
						}
					}
				}
			}
		}
	}

	/**
	 * Return the path to the svn log (with or without hudson)
	 */
	public String getPathToLog() {
		String path = "";

		path = workspace.substring(0, workspace.length() - ProjectVersionUpdater.workspaceFolderName.length());
		path = path + "builds" + File.separator + build_number + File.separator + ProjectVersionUpdater.svnLog;

		return path;
	}

	/**
	 * Copy the repository
	 * 
	 * @throws Exception
	 */
	public void copyFromRepository() throws Exception {
		logger.info("Utils.copyFromRepository() start");
		String from = "";
		from = workspace;
		String to = getRepositoryCopyPath();
		try {
			if (new File(to).exists()) {
				FileHelper.deleteFile(new File(to));
			}
			new File(getBuildPath() + File.separator + ProjectVersionUpdater.repositoryCopy).mkdir();
			logger.debug("From " + from + " to " + to);
			FileHelper.copyFiles(new File(from), new File(to), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Utils.copyFromRepository() done");
	}

	public String[] getNumVersionPattern() {
		String[] pattern = getBuildProperties().getProperty(number_pattern).split("\\.");
		return pattern;
	}

	/**
	 * Retourne le chemin vers la copie locale du projet en fonction de
	 * l'utilisation de hudson ou non
	 * 
	 * @throws Exception
	 */
	public String getPathToLocalCopy(String projectName) throws Exception {
		String path = "";
		if (useRepositoryCopy) {
			path = getBuildPath() + File.separator + ProjectVersionUpdater.repositoryCopy;
		} else {
			path = workspace;
		}

		path = path + File.separator + getProjectPath(projectName);

		return path;
	}

	/**
	 * Retourne le chemin pour un projet donné (par exemple
	 * MetaModel/Application pour le projet com.bluexml.side.Application
	 * 
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public String getProjectPath(String projectName) throws Exception {
		String path = getCommunityProject(projectName);

		if (path.length() == 0) {
			path = getEnterpriseProjectPath(projectName);
		}
		if (path.length() == 0) {
			throw new Exception("Project not found :" + projectName);
		}

		return path;
	}

	private String getCommunityProject(String projectName) {
		String path = "";
		String property = getCommunityProjectProperty();
		if ((property != null) && (property.length() > 0)) {
			String[] projects = property.split(",");
			for (int i = 0; i < projects.length; i++) {
				String string = projects[i].split("&")[1];
				if (string.equals(projectName)) {
					path = projects[i].split("&")[0];
					return ProjectVersionUpdater.SIDE_Core + File.separator + path + File.separator + "trunk" + File.separator + projectName;
				}
			}
		}
		return path;
	}

	private String getCommunityProjectProperty() {
		return getBuildProperties().getProperty(project);
	}

	private String getEnterpriseProjectPath(String projectName) {
		String path = "";
		// We search in enterprise projects
		String property = getEnterpriseProjectProperty();
		if ((property != null) && (property.length() > 0)) {
			String[] projects = property.split(",");
			for (int i = 0; i < projects.length; i++) {
				if (projects[i].split("&")[1].equals(projectName)) {
					path = projects[i].split("&")[0];
					return ProjectVersionUpdater.SIDE_Enterprise + File.separator + path + File.separator + "trunk" + File.separator + projectName;
				}
			}
		}
		return path;
	}

	private String getEnterpriseProjectProperty() {
		return getBuildProperties().getProperty(project_enterprise);
	}

	/**
	 * Return the Version Number to force the change of each number version
	 * (void for no change)
	 */
	public boolean getForceNumberVersion() {
		boolean booleanValue = false;
		String value = getBuildProperties().getProperty(forceNumberVersion).trim();
		if (value != null && !value.equals("")) {
			booleanValue = Boolean.parseBoolean(value);
		}
		return booleanValue;
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
	public String update(String[] number, String[] pattern) {

		boolean change = false;

		for (int i = 0; i < 3; i++) {
			// test si l'élément est un nombre si une exception est levée,
			// l'élément n'est pas un nombre
			try {
				Integer.valueOf(pattern[i]);
				number[i] = pattern[i];
			} catch (NumberFormatException e) {
				if (change) number[i] = "0";
				else {
					if (pattern[i].equals("u")) {
						change = true;
						number[i] = String.valueOf(Integer.valueOf(number[i]) + 1);
					}
				}
			}
		}
		return number[0] + "." + number[1] + "." + number[2] + ".v" + revisionNumber + "-" + getDate();
	}

	/**
	 * Return the current Date
	 * 
	 * @return the current Date
	 */
	private static String getDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	public String getFeatureXMLFile(String projectName) throws Exception {
		return getPathToLocalCopy(projectName) + File.separator + "feature.xml";
	}

	public void copyToRepository() throws Exception {
		String to = workspace;
		String from = getRepositoryCopyPath();
		logger.info("BuilderUtils.copyToRepository() start " + from + " to " + to);
		FileHelper.copyFiles(new File(from), new File(to), true);
	}

	public static Document buildJdomDocument(File xmlFile) throws Exception {
		Document doc;
		org.jdom.input.SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
		return doc;
	}

	public static void saveXMLFile(File xml, Document dom) throws FileNotFoundException, IOException {
		org.jdom.output.XMLOutputter out = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		out.setFormat(format);
		FileOutputStream outStream = new FileOutputStream(xml);
		out.output(dom, outStream);
		outStream.close();
	}

	public File getProductFile() throws Exception {
		if (sourceSVNName.equals(ProjectVersionUpdater.SIDE_Enterprise)) {
			return getProductFile("com.bluexml.side.Integration.eclipse.branding.enterprise");
		} else {
			return getProductFile("com.bluexml.side.Integration.eclipse.branding.labs");
		}
	}

	public File getProductFile(String pluginId) throws Exception {
		String brandingPath = getPathToLocalCopy(pluginId);
		File pluginRoot = new File(brandingPath);
		File[] prodcuts = pluginRoot.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.endsWith("product");
			}
		});
		if (prodcuts.length > 0) {
			return prodcuts[0];
		} else {
			throw new Exception("no .product found at :" + pluginRoot.getAbsolutePath());
		}

	}

	public File getCategoryFile(String pluginId) throws Exception {
		String brandingPath = getPathToLocalCopy(pluginId);
		File product = new File(brandingPath + "/category.xml");
		return product;
	}

	public File getCategoryFile() throws Exception {
		if (sourceSVNName.equals(ProjectVersionUpdater.SIDE_Enterprise)) {
			return getCategoryFile("com.bluexml.side.Integration.eclipse.branding.enterprise");
		} else {
			return getCategoryFile("com.bluexml.side.Integration.eclipse.branding.labs");
		}
	}

	public static void main(String[] args) {
		try {
			String workspace_p = null;
			String revisionNumber_p = null;

			Properties buildProperties_p = new Properties();
			//			buildProperties_p.load(new FileInputStream(new File("")));

			String build_number_p = null;
			boolean useRepositoryCopy_p = false;

			BuilderUtils bu = new BuilderUtils(buildProperties_p, workspace_p, build_number_p, revisionNumber_p, useRepositoryCopy_p);
			bu.setSourceSVNName(ProjectVersionUpdater.SIDE_Core);

			List<String> listeProjetPomsAll = BuilderUtils.findFile(new File("/Volumes/Data/SVN/side/HEAD/S-IDE/FrameworksModules/trunk"), "pom.xml");
			List<String> listeProjetModif = new ArrayList<String>();

			List<String> listeProjetPomsModif = new ArrayList<String>();

			bu.readSvnLog(listeProjetPomsAll, listeProjetPomsModif, listeProjetModif, new File("/Volumes/Data/SVN/side/HEAD/S-IDE/Integration/trunk/com.bluexml.side.Integration.buildHudson/versionUpdater/svnUpdate.log"));
			System.out.println("list projects modif :");
			for (String string : listeProjetModif) {
				System.out.println(string);
			}
			System.out.println("list listeProjetPomsAll :");
			for (String string : listeProjetPomsAll) {
				System.out.println(string);
			}
			System.out.println("list listeProjetPomsModif :");
			for (String string : listeProjetPomsModif) {
				System.out.println(string);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
