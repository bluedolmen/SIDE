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

		list = new ArrayList<File>();
		File file = new File("/Users/davidabad/servers/alfresco-enterprise-3.4.11/tomcat/webapps/alfresco/WEB-INF/classes/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/doclist.get.js");
		File concatened = new File("/Volumes/Data/SVN/projects/Ifremer/IfremerV5/src/modules/mavenProjects/alfresco_ifremer_wcmqs/concatened.js");
		
		try {
			FileUtils.writeStringToFile(concatened, "");
			int countLine = countLine(file, new HashMap<File, Integer>(),concatened);
			System.out.println("GetRealLine.main() total :" + countLine);
			list.remove(0);
			for (File f : list) {
				System.out.println("GetRealLine.main() file " + f.getName() + " #" + map.get(f));
			}
			String[] fileForLine = getFileForLine(500);

			System.out.println("File :" + fileForLine[0] + "#" + fileForLine[1]);
			
			System.out.println("GetRealLine.main() build concatened file");
			
			
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static String[] getFileForLine(int l) {
		String[] rt = new String[] {};
		int c = 0;
		for (File f1 : list) {
			if (c + map.get(f1) >= l) {
				return new String[] { f1.getAbsolutePath(), "" + (l - c) };
			}
			c += map.get(f1);
		}
		return null;
	}

	protected static int countLine(File file, Map<File, Integer> reg, File concatened) throws IOException {
		list.add(file);
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
				FileWriter fileWritter = new FileWriter(concatened,true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(nextLine+"\n");
    	        bufferWritter.close();
    	        c++;
			}
			
			cFile++;
		}
		map.put(file, cFile);
		return c;
	}

}
