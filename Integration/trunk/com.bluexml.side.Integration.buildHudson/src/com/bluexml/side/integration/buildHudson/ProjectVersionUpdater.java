package com.bluexml.side.integration.buildHudson;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bluexml.side.integration.buildHudson.updaters.CategoryUpdater;
import com.bluexml.side.integration.buildHudson.updaters.FeatureUpdater;
import com.bluexml.side.integration.buildHudson.updaters.MavenProjectUpdater;
import com.bluexml.side.integration.buildHudson.updaters.PluginsUpdater;
import com.bluexml.side.integration.buildHudson.updaters.ProductUpdater;
import com.bluexml.side.integration.buildHudson.utils.BuilderUtils;
import com.bluexml.side.integration.buildHudson.utils.SVNCommandGenerator;

public class ProjectVersionUpdater {

	/**
	 * options
	 */
	public boolean skipCopyToRepo = false;
	public boolean generateSVNCommit = true;
	public boolean useRepositoryCopy = false;
	public boolean forceProductUpdate = true;
	/**
	 * parameters
	 */
	private String workspace = "";
	private String build_number = "";
	private String build_id = "";
	private String svn_revision = "";
	private Properties conf;

	private String propertiesFile;

	/**
	 * computed parameters
	 */
	private String sourceSVNName = "";
	private Date launchDate = null;

	/**
	 * constant
	 */
	public static final String buildStartLine = "****************************************";
	public static final String SIDE_Core = "S-IDE";
	public static final String SIDE_Enterprise = "S-IDE_Enterprise";
	public static final String repositoryCopy = "repositoryCopy";
	public static final String workspaceFolderName = "workspace";
	public static final String svnLog = "svnUpdate.log";
	public static final String bluexmlPackage = "com.bluexml";

	/**
	 * attributes
	 */
	private BuilderUtils bu = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length < 5) {
				System.out.println("Usage : java -jar Builder.jar <workspace> <build_number> <build_id> <svn_revision> <propertiesFilePath> [skipCopyToRepo]");
				System.out.println("\t<workspace>          : workspace folder, folder contains S-IDE ...");
				System.out.println("\t<build_number>       : Hudson build number");
				System.out.println("\t<build_id>           : identifier like yyyy-MM-dd_HH-mm-ss");
				System.out.println("\t<svn_revision>       : the svn revision number");
				System.out.println("\t<propertiesFilePath> : file path of properties file to use");
				System.out.println("\t[skipCopyToRepo]     : optional, disable last action that copy modified files into svn local copy (mainly for testing)");
			}

			// get Parameters
			String workspace = args[0];
			String build_number = args[1];
			String build_id = args[2];
			String svn_revision = args[3];
			String propertiesFilePath = args[4];
			boolean skipCopyToRepo = false;
			if (args.length == 6 && args[5].equals("skipCopyToRepo")) {
				skipCopyToRepo = true;
			}
			// initialize Builder
			ProjectVersionUpdater builder = new ProjectVersionUpdater(workspace, build_number, build_id, svn_revision, propertiesFilePath, skipCopyToRepo);

			// launch version updater
			builder.build();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public ProjectVersionUpdater(String workspace, String build_number, String build_id, String svn_revision, String propertiesFilePath, boolean skipCopyToRepo) {
		this.workspace = workspace;
		this.build_number = build_number;
		this.build_id = build_id;
		this.svn_revision = svn_revision;
		this.propertiesFile = propertiesFilePath;
		this.conf = BuilderUtils.openProperties(propertiesFilePath);
		if (skipCopyToRepo) {
			this.useRepositoryCopy = true;
			this.skipCopyToRepo = true;
		} else {
			this.useRepositoryCopy = false;
			this.skipCopyToRepo = false;
		}
		this.bu = new BuilderUtils(conf, workspace, build_number, svn_revision, useRepositoryCopy);

		// set computed parameters
		String pathprojectSVN = getUsedWorkspace();
		if (pathprojectSVN.contains("Build_RCP_Enterprise")) {
			sourceSVNName = SIDE_Enterprise;
		} else {
			sourceSVNName = SIDE_Core;
		}
		bu.setSourceSVNName(sourceSVNName);
	}

	public void build() throws Exception {
		launchDate = new Date();
		System.out.println(buildStartLine);
		System.out.println("**** Lancement du Build Automatique ****");
		System.out.println("****************************************");
		System.out.println("**** Parametre ****");
		System.out.println("- workspace = " + workspace);
		System.out.println("- build_number = " + build_number);
		System.out.println("- build_id = " + build_id);
		System.out.println("- svn_revision = " + svn_revision);
		System.out.println("- propertiesFile = " + propertiesFile);
		System.out.println("- sourceSVNName = " + sourceSVNName);
		System.out.println("- skipCopyToRepo = " + skipCopyToRepo);
		// build process

		System.out.println("\nLancé le " + BuilderUtils.getFormatedDate(launchDate) + " à " + BuilderUtils.getTime(launchDate));

		// define projects lists
		List<String> projectList = new ArrayList<String>();
		List<String> projectRealList = new ArrayList<String>();

		List<String> projetPomsList = new ArrayList<String>();
		List<String> projectPoms2Update = new ArrayList<String>();

		List<String> pluginsList = new ArrayList<String>();
		List<String> plugins2UpdateList = new ArrayList<String>();

		List<String> featuresList = new ArrayList<String>();
		List<String> features2UpdateList = new ArrayList<String>();

		List<String> projects = new ArrayList<String>();
		projects.addAll(bu.getProjects("project"));
		if (isEnterpriseBuild()) {
			// add Enterprise projects
			projects.addAll(bu.getProjects("project.enterprise"));
		}
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).length() > 0) {
				projectRealList.add(projects.get(i));
			}
		}
		String pathproject = getUsedWorkspace();

		// copy svn repository to a working folder
		if (useRepositoryCopy) {
			bu.copyFromRepository();
		}

		// get All poms
		System.out.println("Search maven2 projects :");
		File searchFrom = new File(pathproject + "/" + sourceSVNName + "/");
		System.out.println("from " + searchFrom);

		projetPomsList = BuilderUtils.findFile(searchFrom, "pom.xml");

		// read svn log to list modified projects
		bu.readSvnLog(projetPomsList, projectPoms2Update, projectList);

		// dispatch modified project according to project type (plugin or
		// feature)
		for (String element : projectList) {
			if (projectRealList.contains(element)) {
				// on met tous les plugins modifiés dans un tableau
				if (element.indexOf("feature") == -1) {
					plugins2UpdateList.add(element);
				}
				// et tous les features dans un autre
				else {
					features2UpdateList.add(element);
				}
			}
		}

		// dispatch all project according to project type (plugin or feature)
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).indexOf("feature") != -1) {
				featuresList.add(projects.get(i));
			} else {
				pluginsList.add(projects.get(i));
			}
		}
		// lists initializing done.

		System.out.println(" Found :");
		System.out.println(" plugins :" + pluginsList.size());
		System.out.println(" Features :" + featuresList.size());
		System.out.println(" poms :" + projetPomsList.size());
		System.out.println(" project updated (svn) :");
		System.out.println(" plugins :" + plugins2UpdateList.size());
		System.out.println(" Features :" + features2UpdateList);
		System.out.println(" poms :" + projectPoms2Update.size());

		if (featuresList.size() == 0 || pluginsList.size() == 0 || projetPomsList.size() == 0) {
			// something wrong
			throw new Exception("Updater Stoped ! please check configuration somes projects could not be found in repository");
		}

		// launch maven project updater
		// get projects from Core repository
		List<String> corePoms = null;
		if (isEnterpriseBuild()) {
			// just to have Core project in full list
			corePoms = BuilderUtils.findFile(new File(pathproject + "/" + Application.SIDE_Core + "/"), "pom.xml");
			// add them to poms list
			projetPomsList.addAll(corePoms);
		}
		MavenProjectUpdater mpu = new MavenProjectUpdater(projetPomsList, projectPoms2Update, corePoms, bu);
		mpu.checkAndUpdateAllPoms();
		System.out.println("Updated Maven2 projects :");
		for (Map.Entry<String, String> entry : mpu.getPomsNewsVersion().entrySet()) {
			System.out.println("\t- " + entry.getKey() + " : " + entry.getValue());
		}

		if (mpu.getPomsNewsVersion().size() > 0) {
			// maven2 project updated, so must update plugin that contains
			// dependencies
			String dependenciesPluginId = "";
			if (isEnterpriseBuild()) {
				dependenciesPluginId = "com.bluexml.side.Util.dependencies.repository.enterprise";
			} else {
				dependenciesPluginId = "com.bluexml.side.Util.dependencies.repository";
			}
			if (!plugins2UpdateList.contains(dependenciesPluginId)) {
				plugins2UpdateList.add(dependenciesPluginId);
			}
		}

		if (forceProductUpdate) {
			/**
			 * side.product must be updated so branding too, this to avoid cycle
			 * in update : t0 side.product updated and commited t1 scan svnlog
			 * branding is changed, marked for update ...
			 */
			String brandingPluginId = "";
			if (isEnterpriseBuild()) {
				brandingPluginId = "com.bluexml.side.Integration.eclipse.branding.enterprise";
			} else {
				brandingPluginId = "com.bluexml.side.Integration.eclipse.branding";
			}
			if (!plugins2UpdateList.contains(brandingPluginId)) {
				plugins2UpdateList.add(brandingPluginId);
			}
		}

		// launch plugin project updater

		PluginsUpdater pu = null;
		if (isEnterpriseBuild()) {
			pu = new PluginsUpdater(pluginsList, plugins2UpdateList, bu.getProjects("project"), mpu, bu);
		} else {
			pu = new PluginsUpdater(pluginsList, plugins2UpdateList, null, mpu, bu);
		}
		pu.checkAndUpdate();
		System.out.println("Updated Plugins :");
		for (Map.Entry<String, String> entry : pu.getPluginsNewVersion().entrySet()) {
			System.out.println("\t- " + entry.getKey() + " : " + entry.getValue());
		}

		// launch feature project updater
		FeatureUpdater fu = null;
		if (isEnterpriseBuild()) {
			fu = new FeatureUpdater(featuresList, features2UpdateList, bu.getProjects("project"), pu, bu);
		} else {
			fu = new FeatureUpdater(featuresList, features2UpdateList, null, pu, bu);
		}

		fu.checkAndUpdateAllFeatures();
		System.out.println("Updated Features :");
		for (Map.Entry<String, String> entry : fu.getFeaturesNewsVersion().entrySet()) {
			System.out.println("\t- " + entry.getKey() + " : " + entry.getValue());
		}

		// launch product updater
		ProductUpdater produ = new ProductUpdater(fu, bu, forceProductUpdate);
		boolean sideProductChanges = produ.updateProduct();
		if (sideProductChanges) {
			System.out.println("- side.product updated " + produ.getNewVersion());
		} else {
			System.out.println("- side.product no changes " + produ.getNewVersion());
		}

		CategoryUpdater catUp = new CategoryUpdater(fu, bu);
		boolean categoryChanges = catUp.updateCategory();
		if (categoryChanges) {
			System.out.println("- category.xml updated");
		} else {
			System.out.println("- category.xml no changes");
		}
		// project version update done.

		if (useRepositoryCopy && !skipCopyToRepo) {
			// get modified files and copy them into svn local copy
			bu.copyToRepository();
		}

		if (generateSVNCommit) {
			// generate ant script to commit changes
			SVNCommandGenerator svnCg = new SVNCommandGenerator(bu, launchDate, projectPoms2Update, plugins2UpdateList, features2UpdateList);
			svnCg.createAntFile();
		}
	}

	public String getUsedWorkspace() {
		String path = null;
		if (useRepositoryCopy) {
			try {
				path = bu.getRepositoryCopyPath();
			} catch (Exception e) {
				// can't happen
				e.printStackTrace();
			}
		} else {
			path = workspace;
		}
		return path;
	}

	public boolean isEnterpriseBuild() {
		return sourceSVNName.equals(SIDE_Enterprise);
	}

}
