package com.bluexml.xforms.messages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Use this class for building the value of the arrays in
 * {@link com.bluexml.xforms.messages.DefaultMessages}
 * <p/>
 * HOWTO: change the path to a '.properties' file and run the class. Copy the output and paste as
 * the value for a string array.
 * <p/>
 * Obviously, the '.properties' file whose path is indicated must be exactly what is expected in
 * default messages files that will be generated.
 * 
 * @author Amenel
 * 
 */
public class Transformer {

	public String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the BufferedReader.readLine() method. We
		 * iterate until the BufferedReader return null which means there's no more data to read.
		 * Each line will be appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		String outputLine;
		String replaced;
		boolean first = true;
		try {
			while ((line = reader.readLine()) != null) {
				outputLine = (first) ? "" : ",";
				replaced = line.replaceAll("\\\\", "\\\\\\\\");
				replaced = replaced.replaceAll("\"", "\\\\\"");
				outputLine += "\"" + replaced + "\"";
				System.out.println(outputLine);
				sb.append(outputLine);
				first = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String path = "/Users/amenel/workspace/repository/XForms/dev/examples/xforms/src/main/resources/messages.properties";
		FileInputStream is = new FileInputStream(new File(path));
		new Transformer().convertStreamToString(is);
	}

}
