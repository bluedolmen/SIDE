package com.bluexml.tools.miscellaneous;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.RuntimeErrorException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang.StringUtils;

public class PrepareSIDEModulesMigration {

	private static final String MIGRATION_FOLDER = "migrationFolder";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean inplace = false;

		String workspace = "/Users/davidabad/workspaces/SIDE-Modules/";
		String frameworkmodulesPath = "/Volumes/Data/SVN/side/HEAD/S-IDE/FrameworksModules/trunk/";
		String classifier_base = "enterprise";
		String version_base = "3.4.6";
		String classifier_target = "enterprise";
		String version_target = "3.4.11";
		String frameworkmodulesInplace = "/Volumes/Data/SVN/projects/Ifremer/IfremerV5/src/modules/mavenProjects";

		Properties props = new Properties();
		try {
			InputStream resourceAsStream = PrepareSIDEModulesMigration.class.getResourceAsStream("config.properties");
			if (resourceAsStream != null) {
				props.load(resourceAsStream);

				inplace = Boolean.parseBoolean(props.getProperty("inplace", Boolean.toString(inplace)));
				workspace = props.getProperty("workspace", workspace);
				frameworkmodulesPath = props.getProperty("frameworkmodulesPath", frameworkmodulesPath);
				classifier_base = props.getProperty("classifier_base", classifier_base);
				version_base = props.getProperty("version_base", version_base);
				classifier_target = props.getProperty("classifier_target", classifier_target);
				version_target = props.getProperty("version_target", version_target);
				frameworkmodulesInplace = props.getProperty("frameworkmodulesInplace", frameworkmodulesInplace);
			} else {
				System.out.println("no configuration founded in classpath config.properties");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		System.out.println("properties :");
		Enumeration<?> propertyNames = props.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String nextElement = propertyNames.nextElement().toString();
			System.out.println("\t " + nextElement + " : " + props.getProperty(nextElement));
		}

		File workspaceFile = new File(workspace);

		File targetHome = new File(workspaceFile, MIGRATION_FOLDER);
		if (targetHome.exists()) {
			try {
				FileUtils.deleteDirectory(targetHome);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		final String versionInProjectName = getVersionInProjectName(classifier_base, version_base);
		String versionInProjectName2 = getVersionInProjectName(classifier_target, version_target);

		if (frameworkmodulesPath.contains(",")) {
			// this is a list of paths
			String[] split = frameworkmodulesPath.split(",");
			for (String string : split) {
				if (StringUtils.trimToNull(string) != null) {
					executeInpath(inplace, string, classifier_base, version_base, classifier_target, version_target, frameworkmodulesInplace, workspaceFile, versionInProjectName, versionInProjectName2);
				}
			}
		} else {
			executeInpath(inplace, frameworkmodulesPath, classifier_base, version_base, classifier_target, version_target, frameworkmodulesInplace, workspaceFile, versionInProjectName, versionInProjectName2);
		}

		System.out.println("Job's done !");
		System.out.println("Please check " + MIGRATION_FOLDER);
		System.out.println("If all is ok you can use commit.sh in a terminal do : cd " + MIGRATION_FOLDER + "; sh commit.sh");
		System.out.println("This script will create new svn projet and commit resources, add 'target' to svn:ignore ...");

	}

	private static void executeInpath(boolean inplace, String frameworkmodulesPath, String classifier_base, String version_base, String classifier_target, String version_target, String frameworkmodulesInplace, File workspaceFile, final String versionInProjectName,
			String versionInProjectName2) {
		File frameworkmodulesPathFile = new File(frameworkmodulesPath);
		if (inplace) {
			frameworkmodulesPathFile = new File(frameworkmodulesInplace);
			Collection<?> listFiles = FileUtils.listFiles(frameworkmodulesPathFile, TrueFileFilter.INSTANCE, new IOFileFilter() {

				public boolean accept(File dir, String name) {
					return !name.equals(".svn");
				}

				public boolean accept(File file) {
					return !file.getName().equals(".svn");
				}
			});
			for (Object object : listFiles) {

				File f = (File) object;
				//				System.out.println("PrepareSIDEModulesMigration.main() file :" + f);
				replaceVersionInFile(versionInProjectName, versionInProjectName2, version_base, classifier_base, version_target, classifier_target, f, true);
			}

		} else if (frameworkmodulesPathFile.exists()) {

			// copy resource
			// get project to copy

			File[] listFilesT = frameworkmodulesPathFile.listFiles();
			System.out.println("PrepareSIDEModulesMigration.main() all :" + listFilesT.length);
			File[] listFiles = frameworkmodulesPathFile.listFiles(new FileFilter() {
				public boolean accept(File file) {
					if (file.isDirectory()) {

						String pat = versionInProjectName;
						pat = ".*" + Pattern.quote(pat) + ".*";
						boolean matches = file.getName().matches(pat);
						System.out.println("accept() file" + file.getName() + " pattern :" + pat + " ?" + matches);
						return matches;
					}
					System.out.println("PrepareSIDEModulesMigration.main(...).new FileFilter() {...}.accept() FALSE");
					return false;
				}
			});
			System.out.println("PrepareSIDEModulesMigration.main() to copy : " + listFiles.length);
			for (File srcDir : listFiles) {
				System.out.println("PrepareSIDEModulesMigration.main() copy dir :" + srcDir);
				File copyDir = copyDir(workspaceFile, srcDir);

				reNameResources(versionInProjectName, copyDir, versionInProjectName2, version_base, classifier_base, version_target, classifier_target);
			}

		} else {
			System.err.println("frameworkmodulesPathFile do not exists :" + frameworkmodulesPathFile);
		}
	}

	protected static void reNameResources(final String versionInProjectName, File copyDir, String versionInProjectName2, String version_base, String classifier_base, String version_target, String classifier_target) {
		System.out.println("PrepareSIDEModulesMigration.reNameResources() " + versionInProjectName + "->" + versionInProjectName2);
		Collection<?> listFiles = FileUtils.listFiles(copyDir, TrueFileFilter.INSTANCE, new IOFileFilter() {

			public boolean accept(File dir, String name) {
				return !name.equals(".svn");
			}

			public boolean accept(File file) {
				return !file.getName().equals(".svn");
			}
		});
		for (Object object : listFiles) {

			File f = (File) object;

			replaceVersionInFile(versionInProjectName, versionInProjectName2, version_base, classifier_base, version_target, classifier_target, f, false);

			renameFile(versionInProjectName, versionInProjectName2, f);
		}

		// renameFile(versionInProjectName, versionInProjectName2, copyDir);

		renameDirectories(copyDir, versionInProjectName, versionInProjectName2);
	}

	protected static void replaceVersionInFile(final String versionInProjectName, String versionInProjectName2, String version_base, String classifier_base, String version_target, String classifier_target, File f, boolean inplace) {
		boolean ok = f.isFile();
		ok |= f.getName().toLowerCase().endsWith(".xml");
		ok |= f.getName().toLowerCase().endsWith(".properties");

		if (ok) {
			// replace in files contents
			try {

				String readFileToString = FileUtils.readFileToString(f);
				if (readFileToString.indexOf(versionInProjectName) != -1 || inplace) {
					System.out.println("PrepareSIDEModulesMigration.reNameResources() replace in file :" + f);
					readFileToString = readFileToString.replace(versionInProjectName, versionInProjectName2);

					if (f.getName().equals("pom.xml")) {
						// replace dependencies
						System.out.println("PrepareSIDEModulesMigration.reNameResources() file is a pom.xml");
						System.out.println("PrepareSIDEModulesMigration.reNameResources() file content before replace :" + readFileToString);
						readFileToString = replaceInpom(version_base, classifier_base, readFileToString, version_target, classifier_target);
						System.out.println("PrepareSIDEModulesMigration.reNameResources() file content after replace :" + readFileToString);
					}

					FileUtils.writeStringToFile(f, readFileToString);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected static String replaceInpom(String version_base, String classifier_base, String readFileToString, String version_target, String classifier_target) {
		String replaced = "";
		String depRegExp = "<dependency>(\\s*)" + "<groupId>org.alfresco</groupId>(\\s*)" + "<artifactId>(.*)</artifactId>(\\s*)" + "<version>" + version_base + "</version>(\\s*)" + "<classifier>" + classifier_base + "</classifier>(\\s*)" + "<scope>provided</scope>(\\s*)"
				+ "</dependency>";
		String depReplacement = "<dependency>$1" + "<groupId>org.alfresco</groupId>$2" + "<artifactId>$3</artifactId>$4" + "<version>" + version_target + "</version>$5" + "<classifier>" + classifier_target + "</classifier>$6" + "<scope>provided</scope>$7" + "</dependency>";

		replaced = replaceAll(readFileToString, depRegExp, depReplacement);

		String depRegExp2 = "<dependency>(\\s*)" + "<groupId>org.alfresco</groupId>(\\s*)" + "<artifactId>(.*)</artifactId>(\\s*)" + "<version>" + version_base + "</version>(\\s*)" + "<scope>provided</scope>(\\s*)" + "<classifier>" + classifier_base + "</classifier>(\\s*)"
				+ "</dependency>";
		String depReplacement2 = "<dependency>$1" + "<groupId>org.alfresco</groupId>$2" + "<artifactId>$3</artifactId>$4" + "<version>" + version_target + "</version>$5" + "<scope>provided</scope>$6" + "<classifier>" + classifier_target + "</classifier>$7" + "</dependency>";

		replaced = replaceAll(replaced, depRegExp2, depReplacement2);

		return replaced;
	}

	protected static String replaceAll(String readFileToString, String depRegExp, String depReplacement) {
		String replaced;
		Pattern pattern = Pattern.compile(depRegExp);
		Matcher matcher = pattern.matcher(readFileToString);
		matcher.find();
		replaced = matcher.replaceAll(depReplacement);
		return replaced;
	}

	protected static void renameFile(final String versionInProjectName, String versionInProjectName2, File f) {
		String replace = f.getName().replace(versionInProjectName, versionInProjectName2);
		File file = new File(f.getParentFile(), replace);
		if (!f.getName().equals(replace)) {
			System.out.println("PrepareSIDEModulesMigration.reNameResources() try to rename file :" + f.getName() + " to " + file.getName());
			boolean renameTo = f.renameTo(file);
			System.out.println("PrepareSIDEModulesMigration.reNameResources() rename :" + renameTo);
		}
	}

	protected static File copyDir(File workspaceFile, File srcDir) {

		File destDir0 = new File(workspaceFile, MIGRATION_FOLDER);
		File destDir = new File(destDir0, srcDir.getName());
		try {
			FileUtils.copyDirectory(srcDir, destDir, new FileFilter() {

				public boolean accept(File pathname) {
					String name = pathname.getName();
					boolean equals = name.equals(".svn") || name.equals("target");

					return !equals;
				}
			});
			return destDir;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * You need to rename Directory from the deeper (to avoid that subPath
	 * changes before completion)
	 * 
	 * @param home
	 * @param name
	 * @param newName
	 */
	protected static void renameDirectories(File home, String name, String newName) {
		File[] listFiles = home.listFiles();

		for (File file : listFiles) {
			if (file.isDirectory()) {
				// rename subDirectories FIRST
				renameDirectories(file, name, newName);
			}
		}
		// try to rename this Directory
		renameFile(name, newName, home);
	}

	protected static String getVersionInProjectName(final String classifier_base, final String version_base) {
		String pat = version_base + (classifier_base.equals("enterprise") ? "E" : "");
		pat = pat.replace(".", "");
		return pat;
	}

}
