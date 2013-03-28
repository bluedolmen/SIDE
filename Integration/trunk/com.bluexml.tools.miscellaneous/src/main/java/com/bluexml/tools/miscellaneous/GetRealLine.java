package com.bluexml.tools.miscellaneous;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class GetRealLine {
	static List<File> list = new ArrayList<File>();
	static Map<File, Integer> map = new HashMap<File, Integer>();
	static File home = new File("/Users/davidabad/servers/alfresco-enterprise-3.4.11/tomcat/webapps/alfresco/WEB-INF/classes");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("GetRealLine.main()" + args[0] + " " + args[1]);
		list = new ArrayList<File>();

		File file = new File(args[0]);
		String absolutePath = file.getAbsolutePath();
		String substring = absolutePath.substring(0, absolutePath.indexOf("WEB-INF/classes") + 15);
		System.out.println("GetRealLine.main() :" + substring);
		home = new File(substring);

		File concatened = new File("/Volumes/Data/SVN/side/HEAD/S-IDE/Integration/trunk/com.bluexml.tools.miscellaneous/test.js");

		try {
			FileUtils.writeStringToFile(concatened, "");
			int countLine = countLine(file, new HashMap<File, Integer>(), concatened);
			System.out.println("GetRealLine.main() total :" + countLine);
			//			File remove = list.remove(0);
			//			list.add(remove);
			for (File f : list) {
				System.out.println("GetRealLine.main() file " + f.getName() + " #" + map.get(f));
			}
			if (args[1] != null) {
				String[] fileForLine = getFileForLine(Integer.parseInt(args[1]));
				if (fileForLine != null) {
					System.out.println("File :" + fileForLine[0] + "#" + fileForLine[1]);
					System.out.println("line " + args[1] + " is " + fileForLine[1]);
				} else {
					System.out.println("There is't line #" + args[1]);
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static String[] getFileForLine(int l) {
		String[] rt = new String[] {};
		int c = 0;
		int p = 0;
		for (File f1 : list) {
			if (c + map.get(f1) >= l) {
				return new String[] { f1.getAbsolutePath(), "" + (l + p - c) };
			}
			c += map.get(f1);
			p++;
		}
		return null;
	}

	protected static int countLine(File file, Map<File, Integer> reg, File concatened) throws IOException {
		LineIterator lineIterator = FileUtils.lineIterator(file);
		int c = 0;
		int cFile = 0;
		while (lineIterator.hasNext()) {
			String nextLine = lineIterator.nextLine();
			Pattern p = Pattern.compile("<import resource=\"classpath:/([^\"]*)\">$");
			Matcher matcher = p.matcher(nextLine);
			matcher.find();
			if (matcher.matches()) {
				System.out.println("GetRealLine.countLine() matchs :" + matcher.group());
				String group = matcher.group(1);
				// open targetFile and recursively call countLine
				File inFile = new File(home, group);
				c += countLine(inFile, reg, concatened);
			} else {
				FileWriter fileWritter = new FileWriter(concatened, true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(nextLine + "\n");
				bufferWritter.close();
				c++;
			}

			cFile++;
		}
		map.put(file, cFile);
		list.add(file);
		return c;
	}

}
