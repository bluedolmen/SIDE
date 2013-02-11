package com.bluexml.tools.miscellaneous;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang.NotImplementedException;

public class DetectChanges {

	/**
	 * get the list of files in modules that override existing resource in
	 * alfresco exact path (example client side js) extension path (webscript
	 * overriding in extensions paths)
	 * 1) check if change exists between 2 alfresco version for this kind of
	 * files 2) ouput files
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String tomcatV1 = "/Users/davidabad/servers/alfresco-community-4.0.d/tomcat";
		String tomcatV2 = "/Users/davidabad/servers/alfresco-enterprise-4.0.2.9/tomcat";
		String modules = "/Users/davidabad/SIDE_SVN/FrameworksModules/trunk";
		if (args.length == 3) {
			tomcatV1 = args[0];
			tomcatV2 = args[1];
			modules = args[2];
		}

		final File v1 = new File(tomcatV1 + "/webapps/alfresco");
		final File v2 = new File(tomcatV2 + "/webapps/alfresco");

		Map<File, ArrayList<Map<String, File>>> tocheck = new HashMap<File, ArrayList<Map<String, File>>>();

		final File module = new File(modules);

		final Map<File, ArrayList<Map<String, File>>> tocheckA = new HashMap<File, ArrayList<Map<String, File>>>();
		Thread a = new Thread(new Runnable() {

			public void run() {
				checkWebApp(tocheckA, module, v1, v2, ".*4029E");
			}
		});
		a.start();

		final Map<File, ArrayList<Map<String, File>>> tocheckB = new HashMap<File, ArrayList<Map<String, File>>>();
		final File v12 = new File(tomcatV1 + "/webapps/share");
		final File v22 = new File(tomcatV2 + "/webapps/share");
		Thread b = new Thread(new Runnable() {

			public void run() {
				checkWebApp(tocheckB, module, v12, v22, ".*4029E");
			}
		});
		b.start();

		while (b.isAlive() || a.isAlive()) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		tocheck = tocheckA;
		Set<Entry<File, ArrayList<Map<String, File>>>> entrySet = tocheckB.entrySet();
		for (Entry<File, ArrayList<Map<String, File>>> entry : entrySet) {
			File key = entry.getKey();
			ArrayList<Map<String, File>> value = entry.getValue();
			if (tocheck.containsKey(key)) {
				tocheck.get(key).addAll(value);
			} else {
				tocheck.put(key, value);
			}
		}

		System.out.println("SUMMARY :");
		int count = 0;
		for (Entry<File, ArrayList<Map<String, File>>> map : tocheck.entrySet()) {
			ArrayList<Map<String, File>> value = map.getValue();

			if (value.size() > 0) {
				File key = map.getKey();
				System.out.println("* module :" + key.getName());
				for (Map<String, File> map2 : value) {
					File file = map2.get("file");
					String relativePath = file.getAbsolutePath().replaceFirst(key.getAbsolutePath(), "");
					System.out.println("\tcheck :" + relativePath);
					count++;
				}
			}
		}
		System.out.println("DetectChanges.main() found " + count + " files to check");

	}

	private static void checkWebApp(Map<File, ArrayList<Map<String, File>>> tocheck, File module, File v1, File v2, String pattern) {

		boolean share = v1.getAbsolutePath().endsWith("share");
		System.out.println("DetectChanges.checkWebApp() search in " + (share ? "share" : "alfresco") + " START " + new Date());
		File[] matchingModules = DetectChanges.getMatchingModules(module, (share ? ".*share" : "") + pattern);
		for (File file : matchingModules) {
			System.out.println("DetectChanges.main() Module :" + file);
			ArrayList<File> files = DetectChanges.getFiles(file);
			for (File file2 : files) {
				File computePathV1 = DetectChanges.computePath(v1, file2);
				File computePathV2 = DetectChanges.computePath(v2, file2);
				//				System.out.println("DetectChanges.main() V1 :" + computePathV1);
				//				System.out.println("DetectChanges.main() V1 :" + computePathV2);
				if (computePathV1 != null) {

					try {
						boolean contentEquals = FileUtils.contentEquals(computePathV1, computePathV2);
						//						boolean contentEquals = false;
						if (contentEquals) {
							//							System.out.println("DetectChanges.main() no changes between alfresco version for " + file2);
						} else {
							Map<String, File> map = new HashMap<String, File>();
							map.put("file", file2);
							//							map.put("v1", computePathV1);
							//							map.put("v2", computePathV2);
							if (tocheck.containsKey(file)) {
								tocheck.get(file).add(map);
							} else {
								ArrayList<Map<String, File>> arrayList = new ArrayList<Map<String, File>>();
								arrayList.add(map);
								tocheck.put(file, arrayList);
							}
							System.out.println("DetectChanges.checkWebApp() TO CHECK :" + map);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					//					System.out.println("DetectChanges.main() no matches for " + file2);
				}
			}
		}
		System.out.println("DetectChanges.checkWebApp() search in " + (share ? "share" : "alfresco") + " END " + new Date());
	}

	/**
	 * files in Module to check
	 * 
	 * @param module
	 * @return
	 */
	public static ArrayList<File> getFiles(File module) {
		ArrayList<File> ok = new ArrayList<File>();
		Collection<?> listFiles = FileUtils.listFiles(module, null, true);
		for (Object object : listFiles) {
			File f = (File) object;
			if (f.isFile() && f.getAbsolutePath().matches(".*main/resources.*") && !f.getAbsolutePath().matches(".*target.*")) {
				ok.add(f);
			}
		}
		return ok;
	}

	public static File[] getMatchingModules(File modules_home, final String modules_filterPattern) {
		return modules_home.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isDirectory() && pathname.getName().matches(modules_filterPattern);
			}
		});
	}

	public static File computePath(final File org, final File f) {
		final String replaceFirst = f.getAbsolutePath().replaceFirst(".*main/resources", "").replaceFirst(".*alfresco/extension", "");

		//		System.out.println("DetectChanges.computePath() search for " + replaceFirst + " in " + org);
		
		
		
		
		Iterator<?> iterator = FileUtils.listFiles(org, new IOFileFilter() {

			public boolean accept(File dir, String name) {
				throw new NotImplementedException("");
			}

			public boolean accept(File file) {
				return file.isFile() && file.getAbsolutePath().contains(replaceFirst);
			}
		}, TrueFileFilter.INSTANCE).iterator();
		return iterator.hasNext() ? (File) iterator.next() : null;

	}
}
