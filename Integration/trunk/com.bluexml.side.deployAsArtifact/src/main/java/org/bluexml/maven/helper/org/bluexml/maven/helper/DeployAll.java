package org.bluexml.maven.helper.org.bluexml.maven.helper;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class DeployAll {
	static String artifactFolder = "artifactFolder";
	static String folderType = "folderType";

	static String defaultGroupId = "defaultGroupId";
	static String mavenRepositoryURL = "mavenRepositoryURL";
	static String mavenSnapshotRepositoryURL = "mavenSnapshotRepositoryURL";
	static String profiles = "profiles";
	static String repoIDKey = "repoID";
	static String repoSnapshotIDKey = "repoSnapshotID";
	static String defaultClassifier = "defaultClassifier";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties props = new Properties();

		File propertiesFile = null;
		if (args.length > 0 && !args[0].equals("")) {
			propertiesFile = new File(args[0]);
		} else {
			propertiesFile = new File("./build.properties");
		}
		FileInputStream inStream;
		try {
			inStream = new FileInputStream(propertiesFile);

			props.load(inStream);

			deploy(props);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deploy(Properties props) throws Exception {
		File bashScript = new File("deployArtifacts.sh");
		if (bashScript.exists()) {
			bashScript.delete();
		}
		FileWriter fw = new FileWriter(bashScript);
		File jarToDeploy = new File(props.getProperty(artifactFolder, "artifacts"));
		URL repoUrl = null;
		URL repoSnapshot = null;
		try {
			repoUrl = new URL(props.getProperty(mavenRepositoryURL, "http://pipin.bluexml.com/nexus/content/repositories/thirdparty"));
			repoSnapshot = new URL(props.getProperty(mavenSnapshotRepositoryURL, "http://pipin.bluexml.com/nexus/content/repositories/thirdpartysnapshot"));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String repoID = props.getProperty(repoIDKey, "thirdparty");
		String repoSnapshotID = props.getProperty(repoSnapshotIDKey, "thirdpartysnapshot");

		String classifier = StringUtils.trimToNull(props.getProperty(defaultClassifier));

		String profilesString = props.getProperty(profiles);
		List<String> profiles = null;
		if (profilesString != null && !profilesString.equals("")) {
			profiles = new ArrayList<String>();
		}

		try {
			final String folderType = props.getProperty("folderType");

			String rexexp = "(.*)-([0-9]+(\\.[0-9]+)*(\\.[^-]*)?(-SNAPSHOT)?)+(-(.*))?\\.(.*)";

			if (jarToDeploy.exists() && jarToDeploy.isDirectory()) {

				File[] entry = jarToDeploy.listFiles(new FilenameFilter() {

					public boolean accept(File arg0, String arg1) {

						return !arg1.startsWith(".");
					}
				});
				for (File file : entry) {
					String fileName = file.getName();
					String artifactID = fileName.replaceFirst(rexexp, "$1");
					String version = fileName.replaceFirst(rexexp, "$2");
					String qualifier = fileName.replaceFirst(rexexp, "$7");
					String artifactsType = fileName.replaceFirst(rexexp, "$8");
					URL repo = repoUrl;
					String repoIdentifier = repoID;
					if (version.endsWith("SNAPSHOT")) {
						// switch to snapshot repository
						repo = repoSnapshot;
						repoIdentifier = repoSnapshotID;
					}

					System.out.println("ArtifactId: " + artifactID + ", version: " + version + ", qualifier: " + qualifier);
					if (!"sources".equals(qualifier)) {

						String cl = "";
						if (folderType.equals("pom")) {
							// pom location
							File pomHomes = new File(props.getProperty("pom.homes", "poms/maven"));
							File pomFile = null;
							// search folder with same artifactId
							// could be in some subfolders
							File artifactFolder = getArtifactFolder(pomHomes, artifactID).get(0);
							FileFilter ff = new FileFilter() {

								public boolean accept(File pathname) {
									return pathname.getName().equals("pom.xml");
								}
							};
							File[] listFiles = artifactFolder.listFiles(ff);
							if (listFiles.length == 1) {
								pomFile = listFiles[0];
							}

							if (pomFile != null) {
								cl = buildCommandLineFromPom(repo, repoIdentifier, file, profiles, pomFile);
							}

						} else {

							cl = buildCommandLine(repo, repoIdentifier, file, profiles, props.getProperty(defaultGroupId, artifactID), artifactID, version, artifactsType, classifier);

						}
						cl = "cd " + props.getProperty("project.home", ".") + ";\n" + cl;

						System.out.println(cl);
						fw.write(cl);
					}
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			fw.flush();
			fw.close();
		}

	}

	public static String buildCommandLine(URL repoUrl, String repoID, File file, List<String> profiles, String groupID, String artifactID, String version, String packaging, String classifier) throws Exception {
		String cl = "mvn deploy:deploy-file";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("url", repoUrl.toString());
		parameters.put("repositoryId", repoID);
		parameters.put("file", file.getAbsolutePath());
		parameters.put("groupId", groupID);
		parameters.put("artifactId", artifactID);
		parameters.put("version", version);
		parameters.put("packaging", packaging);
		parameters.put("generatePom", "true");
		if (classifier != null) {
			parameters.put("classifier", classifier);
		}

		File sourceForArtifact = getSourceForArtifact(file);
		if (sourceForArtifact.exists()) {
			parameters.put("sources", sourceForArtifact.getAbsolutePath());
		}
		String options = " ";
		for (Map.Entry<String, String> en : parameters.entrySet()) {
			if (en.getValue() != null) {
				String param = "-D" + en.getKey();
				options += " " + param + "=" + en.getValue();
			}
		}

		String p_ = "";
		if (profiles != null && profiles.size() > 0) {
			p_ = " -P ";
			for (Iterator<String> iterator = profiles.iterator(); iterator.hasNext();) {
				String string2 = iterator.next();
				p_ += string2;
				if (iterator.hasNext()) {
					p_ += ",";
				}
			}
		}
		return cl + options + p_ + ";\n";
	}

	public static String buildCommandLineFromPom(URL repoUrl, String repoID, File file, List<String> profiles, File pom) throws Exception {
		String cl = "mvn deploy:deploy-file";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("url", repoUrl.toString());
		parameters.put("repositoryId", repoID);
		parameters.put("file", file.getAbsolutePath());
		parameters.put("pomFile", pom.getAbsolutePath());

		String options = " ";
		for (Map.Entry<String, String> en : parameters.entrySet()) {
			String param = "-D" + en.getKey();
			options += " " + param + "=" + en.getValue();
		}

		String p_ = "";
		if (profiles != null && profiles.size() > 0) {
			p_ = " -P ";
			for (Iterator<String> iterator = profiles.iterator(); iterator.hasNext();) {
				String string2 = iterator.next();
				p_ += string2;
				if (iterator.hasNext()) {
					p_ += ",";
				}
			}
		}
		return cl + options + p_ + ";\n";
	}

	public static List<File> getArtifactFolder(File root, String artifiactId) {
		List<File> lf = new ArrayList<File>();
		if (root.getName().equals(artifiactId)) {
			lf.add(root);
		} else if (root.isDirectory()) {
			File[] l = root.listFiles();
			for (File file : l) {
				lf.addAll(getArtifactFolder(file, artifiactId));
			}
		}
		return lf;
	}

	public static File getSourceForArtifact(File artifact) {
		String name = artifact.getName();
		return new File(artifact.getParentFile(), name.replace(".jar", "-sources.jar"));
	}

}
