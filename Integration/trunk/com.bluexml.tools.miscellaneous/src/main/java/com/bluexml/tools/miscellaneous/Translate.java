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
package com.bluexml.tools.miscellaneous;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

public class Translate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Translate.main() 1");
		Console console = System.console();

		System.out.println("give path to folder that contains properties files");
		Scanner scanIn = new Scanner(System.in);

		try {
			// TODO Auto-generated method stub
			String sWhatever;

			System.out.println("Translate.main() 2");
			sWhatever = scanIn.nextLine();
			System.out.println("Translate.main() 3");

			System.out.println(sWhatever);

			File inDir = new File(sWhatever);

			FilenameFilter filter = new FilenameFilter() {

				public boolean accept(File arg0, String arg1) {
					return arg1.endsWith("properties");
				}
			};
			File[] listFiles = inDir.listFiles(filter);
			for (File file : listFiles) {

				prapareFileToTranslate(file, inDir);

			}

			System.out.println("please translate text files and press enter");
			String readLine = scanIn.nextLine();
			System.out.println("Translate.main() 4");
			for (File file : listFiles) {

				File values = new File(file.getParentFile(), file.getName() + ".txt");
				writeBackValues(values, file);
				values.delete();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			scanIn.close();
		}

	}

	public static void prapareFileToTranslate(File input, File outDir) throws IOException {
		// create file that contains only text
		File output = new File(outDir, input.getName() + ".txt");

		TreeMap<String, String> props = loadProperties(input);
		Collection<String> lines = props.values();

		FileUtils.writeLines(output, "UTF-8", lines);
	}

	protected static TreeMap<String, String> loadProperties(File input) throws FileNotFoundException, IOException {
		TreeMap<String, String> map = new TreeMap<String, String>();
		Properties props = new Properties();
		FileInputStream fin = new FileInputStream(input);
		props.load(fin);
		Enumeration<Object> keys = props.keys();
		while (keys.hasMoreElements()) {
			String nextElement = (String) keys.nextElement();
			String property = props.getProperty(nextElement);

			map.put(nextElement, property);
		}
		return map;
	}

	public static void writeBackValues(File values, File propertiesFile) throws IOException {
		List<String> readLines = FileUtils.readLines(values, "UTF-8");
		Properties props = new Properties();
		TreeMap<String, String> propsMap = loadProperties(propertiesFile);
		Set<String> keySet = propsMap.keySet();
		int index = 0;
		for (String key : keySet) {
			String value = readLines.get(index);
			System.out.println("before trans :" + value);

			System.out.println("after trans :" + value);
			System.out.println();
			props.setProperty(key, value);
			index++;
		}

		FileOutputStream out = new FileOutputStream(propertiesFile);
		props.store(out, null);
		out.close();

	}
}
