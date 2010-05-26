package com.bluexml.side.integration.buildHudson;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bluexml.side.integration.buildHudson.utils.BuilderUtils;
import com.bluexml.side.integration.buildHudson.utils.FeatureUpdater;
import com.bluexml.side.integration.buildHudson.utils.MavenProjectUpdater;
import com.bluexml.side.integration.buildHudson.utils.PluginsUpdater;
import com.bluexml.side.integration.buildHudson.utils.ProductUpdater;
import com.bluexml.side.integration.buildHudson.utils.SVNCommandGenerator;

public class ProjectVersionUpdater {
	/**
	 * options
	 */
	public boolean skipCopyToRepo = false;
	public boolean generateSVNCommit = true;

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

	/**
	 * attributes
	 */
	private BuilderUtils bu = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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

		// initialize Builder
		ProjectVersionUpdater builder = new ProjectVersionUpdater(workspace, build_number, build_id, svn_revision, propertiesFilePath);
		if (args.length == 6 && args[5].equals("skipCopyToRepo")) {
			builder.skipCopyToRepo = true;
		}
		try {
			// launch version updater
			builder.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ProjectVersionUpdater(String workspace, String build_number, String build_id, String svn_revision, String propertiesFilePath) {
		this.workspace = workspace;
		this.build_number = build_number;
		this.build_id = build_id;
		this.svn_revision = svn_revision;
		this.propertiesFile = propertiesFilePath;
		this.conf = BuilderUtils.openProperties(propertiesFilePath);
		this.bu = new BuilderUtils(conf, workspace, build_number, svn_revision);
		// set computed parameters
		String pathprojectSVN = bu.getRepositoryCopyPath();
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

		// build process

		System.out.println("\nLancé le " + BuilderUtils.getFormatedDate(launchDate) + " à " + BuilderUtils.getTime(launchDate));

		// define projects lists
		List<String> listeProjet = new ArrayList<String>();
		List<String> listeProjetReels = new ArrayList<String>();

		List<String> listeProjetPoms = new ArrayList<String>();
		List<String> listeProjetPomsModif = new ArrayList<String>();

		List<String> listePlugin = new ArrayList<String>();
		List<String> listePluginModif = new ArrayList<String>();

		List<String> listeFeature = new ArrayList<String>();
		List<String> listeFeatureModif = new ArrayList<String>();

		List<String> projects = new ArrayList<String>();
		projects.addAll(bu.getProjects("project"));
		projects.addAll(bu.getProjects("project.enterprise"));

		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).length() > 0) {
				listeProjetReels.add(projects.get(i));
			}
		}
		String pathproject = bu.getRepositoryCopyPath();

		// copy svn repository to a working folder
		bu.copyFromRepository();

		// get All poms
		System.out.println("Search maven2 projects :");
		File searchFrom = new File(pathproject + "/" + sourceSVNName + "/");
		System.out.println("from " + searchFrom);

		listeProjetPoms = BuilderUtils.findFile(searchFrom, "pom.xml");
		if (sourceSVNName.equals(ProjectVersionUpdater.SIDE_Enterprise)) {
			BuilderUtils.findFile(new File(pathproject + "/" + Application.SIDE_Core + "/"), "pom.xml");
		}

		// read svn log to list modified projects
		bu.readSvnLog(listeProjetPoms, listeProjetPomsModif, listeProjet);

		// dispatch modified project according to project type (plugin or
		// feature)
		for (String element : listeProjet) {
			if (listeProjetReels.contains(element)) {
				// on met tous les plugins modifiés dans un tableau
				if (element.indexOf("feature") == -1) {
					listePluginModif.add(element);
				}
				// et tous les features dans un autre
				else {
					listeFeatureModif.add(element);
				}
			}
		}

		// dispatch all project according to project type (plugin or feature)
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).indexOf("feature") != -1) {
				listeFeature.add(projects.get(i));
			} else {
				listePlugin.add(projects.get(i));
			}
		}
		// lists initializing done.

		System.out.println(" Found :");
		System.out.println(" plugins :" + listePlugin.size());
		System.out.println(" Features :" + listeFeature.size());
		System.out.println(" poms :" + listeProjetPoms.size());
		System.out.println(" project updated (svn) :");
		System.out.println(" plugins :" + listePluginModif.size());
		System.out.println(" Features :" + listeFeatureModif);
		System.out.println(" poms :" + listeProjetPomsModif.size());

		if (listeFeature.size() == 0 || listePlugin.size() == 0 || listeProjetPoms.size() == 0) {
			// something wrong
			throw new Exception("Updater Stoped ! please check configuration somes projects could not be found in repository");
		}

		// launch maven project updater
		MavenProjectUpdater mpu = new MavenProjectUpdater(listeProjetPoms, listeProjetPomsModif, bu);
		mpu.checkAndUpdateAllPoms();
		System.out.println("Updated Maven2 projects :");
		for (Map.Entry<String, String> entry : mpu.getPomsNewsVersion().entrySet()) {
			System.out.println("\t- " + entry.getKey() + " : " + entry.getValue());
		}

		// launch plugin project updater
		PluginsUpdater pu = new PluginsUpdater(listePlugin, listePluginModif, bu.getProjects("project"), mpu, bu);
		pu.checkAndUpdate();
		System.out.println("Updated Plugins :");
		for (Map.Entry<String, String> entry : pu.getPluginsNewVersion().entrySet()) {
			System.out.println("\t- " + entry.getKey() + " : " + entry.getValue());
		}

		// launch feature project updater
		FeatureUpdater fu = new FeatureUpdater(listeFeature, listeFeatureModif, bu.getProjects("project"), pu, bu);
		fu.checkAndUpdateAllFeatures();
		System.out.println("Updated Features :");
		for (Map.Entry<String, String> entry : fu.getFeaturesNewsVersion().entrySet()) {
			System.out.println("\t- " + entry.getKey() + " : " + entry.getValue());
		}

		// launch product updater
		ProductUpdater produ = new ProductUpdater(fu, bu);
		boolean sideProductChanges = produ.updateProduct();
		if (sideProductChanges) {
			System.out.println("- side.product updated " + produ.getNewVersion());
		} else {
			System.out.println("- side.product no changes " + produ.getNewVersion());
		}

		// project version update done.

		if (!skipCopyToRepo) {
			// get modified files and copy them into svn local copy
			bu.copyToRepository();
		}
		
		if (generateSVNCommit) {
			// generate ant script to commit changes
			SVNCommandGenerator svnCg = new SVNCommandGenerator(bu, launchDate, listeProjetPomsModif, listePluginModif, listeFeatureModif);
			svnCg.createAntFile();
		}
	}

}
