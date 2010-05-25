package com.bluexml.side.integration.buildHudson;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.bluexml.side.integration.buildHudson.utils.BuilderUtils;
import com.bluexml.side.integration.buildHudson.utils.FeatureUpdater;
import com.bluexml.side.integration.buildHudson.utils.MavenProjectUpdater;
import com.bluexml.side.integration.buildHudson.utils.PluginsUpdater;
import com.bluexml.side.integration.buildHudson.utils.ProductUpdater;

public class ProjectVersionUpdater {
	public boolean skipCopyToRepo = false;
	/**
	 * parameters
	 */
	private String workspace = "";
	private String build_number = "";
	private String build_id = "";
	private String svn_revision = "";
	private String rcp = "";
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

		// get Parameters
		String workspace = args[0];
		String build_number = args[1];
		String build_id = args[2];
		String svn_revision = args[3];
		String rcp = args[4];
		String propertiesFilePath = args[5];

		// initialize Builder
		ProjectVersionUpdater builder = new ProjectVersionUpdater(workspace, build_number, build_id, svn_revision, rcp, propertiesFilePath);
		if (args.length == 7 && args[6].equals("skipCopyToRepo")) {
			builder.skipCopyToRepo = true;
		}
		try {
			builder.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ProjectVersionUpdater(String workspace, String build_number, String build_id, String svn_revision, String rcp, String propertiesFilePath) {
		this.workspace = workspace;
		this.build_number = build_number;
		this.build_id = build_id;
		this.svn_revision = svn_revision;
		this.rcp = rcp;
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
		System.out.println("- rcp = " + rcp);
		System.out.println("- propertiesFile = " + propertiesFile);

		// build process

		System.out.println("\nLancé le " + BuilderUtils.getFormatedDate(launchDate) + " à " + BuilderUtils.getTime(launchDate));

		// define projects lists
		List<String> listeProjet = new ArrayList<String>();
		List<String> listeProjetReels = new ArrayList<String>();

		List<String> listeProjetPoms = new ArrayList<String>();
		List<String> listeProjetPomsModif = new ArrayList<String>();

		List<String> listePlugin = new ArrayList<String>();
		List<String> listePluginModif = new ArrayList<String>();

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

		// get All poms
		listeProjetPoms = bu.findFile(new File(pathproject + "/" + sourceSVNName + "/"), "pom.xml");
		if (sourceSVNName.equals(ProjectVersionUpdater.SIDE_Enterprise)) {
			bu.findFile(new File(pathproject + "/" + Application.SIDE_Core + "/"), "pom.xml");
		}

		bu.readSvnLog(listeProjetPoms, listeProjetPomsModif, listeProjet);

		bu.copyFromRepository();
		// on parcours la liste des projets qui ont été modifié
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
		System.out.println("FeatureUpdater.main() projects :" + listeProjet);
		System.out.println("FeatureUpdater.main() lpoms :" + listeProjetPoms.size());
		System.out.println("FeatureUpdater.main() lpomsSVNUpdated :" + listeProjetPomsModif);

		ArrayList<String> featuresList = new ArrayList<String>();
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).indexOf("feature") != -1) {
				featuresList.add(projects.get(i));
			} else {
				listePlugin.add(projects.get(i));
			}
		}
		MavenProjectUpdater mpu = new MavenProjectUpdater(listeProjetPoms, listeProjetPomsModif, bu);

		System.out.println("MavenProjectUpdater.main() mvn projects :");
		for (String string : listeProjetPoms) {
			System.out.println("\t- " + string);
		}
		mpu.checkAndUpdateAllPoms();
		System.out.println("MavenProjectUpdater.main() listNewVersions :");
		for (Map.Entry<String, String> entry : mpu.getPomsNewsVersion().entrySet()) {
			System.out.println("\t- " + entry.getKey() + ":" + entry.getValue());
		}

		System.out.println("PluginsUpdater.main() Update Plugins");
		PluginsUpdater pu = new PluginsUpdater(listePlugin, listePluginModif, bu.getProjects("project"), mpu, bu);
		pu.checkAndUpdate();
		System.out.println("MavenProjectUpdater.main() Plugins :");
		for (Map.Entry<String, String> entry : pu.getPluginsNewVersion().entrySet()) {
			System.out.println("\t- " + entry.getKey() + ":" + entry.getValue());
		}

		FeatureUpdater fu = new FeatureUpdater(featuresList, listeFeatureModif, bu.getProjects("project"), pu, bu);

		fu.checkAndUpdateAllFeatures();
		System.out.println("MavenProjectUpdater.main() Features :");
		for (Map.Entry<String, String> entry : fu.getFeaturesNewsVersion().entrySet()) {
			System.out.println("\t- " + entry.getKey() + ":" + entry.getValue());
		}

		ProductUpdater produ = new ProductUpdater(fu, bu);
		boolean sideProductChanges = produ.updateProduct();
		if (sideProductChanges) {
			System.out.println("- side.product updated " + produ.getNewVersion());
		} else {
			System.out.println("- side.product no changes " + produ.getNewVersion());
		}
		if (!skipCopyToRepo) {
			// get modified files and copy them into svn local copy
			bu.copyToRepository();
		}
	}

}
