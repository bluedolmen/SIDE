package com.bluexml.tools.miscellaneous;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ConvertFileContentIntoJSString {

	public static void main(String[] args) {
		String file = args.length > 0 ? args[0] : "/Users/davidabad/workspaces/SIDE-Modules/com.bluexml.tools.miscellaneous/in.txt";

		String fileNameout = "out.js";
		parseFile(new File(file), fileNameout);
	}

	protected static void parseFile(File input, String fileNameout) {

		if (input.isDirectory()) {
			File[] listFiles = input.listFiles();
			for (File file2 : listFiles) {
				parseFile(file2, fileNameout);
			}
		} else {
			File output = new File(input.getParentFile(), input.getName() + "-" + fileNameout);
			writeFiles(input, output);
		}

	}

	protected static void writeFiles(File input, File output) {
		try {
			String readFileToString = FileUtils.readFileToString(input);

			readFileToString = readFileToString.replaceAll("\"", "\\\\\"");

			readFileToString = readFileToString.replaceAll("\n", "\\\\n\\\\\\\n");
			if (readFileToString.endsWith("\n")) {
				readFileToString = readFileToString.substring(0, readFileToString.length() - 2);
			}

			readFileToString = nativeToAscii("\"" + readFileToString + "\"");

			System.out.println(readFileToString);

			FileUtils.writeStringToFile(output, readFileToString, "UTF-8");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String nativeToAscii(String input) {
		if (input == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer(input.length() + 60);
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c <= 0x7E) {
				buffer.append(c);
			} else {
				buffer.append("\\u");
				String hex = Integer.toHexString(c);
				for (int j = hex.length(); j < 4; j++) {
					buffer.append('0');
				}
				buffer.append(hex);
			}
		}
		return buffer.toString();
	}
}
