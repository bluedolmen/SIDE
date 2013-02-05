package com.bluexml.xforms.messages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;



/**
 * This class is used to generate the content of the default properties files.
 * 
 * @author Amenel
 */
public class DefaultMessages {
	
	public static final String CONFIGURATION_PARAMETER_ALFRESCO_URL = "alfresco.url";
	public static final String CONFIGURATION_PARAMETER_ALFRESCO_ADMIN_LOGIN = "alfresco.admin.login";
	public static final String CONFIGURATION_PARAMETER_ALFRESCO_ADMIN_PSW = "alfresco.admin.psw";

	private static final String[] allFormsProperties = getPropertiesAsStringArray("forms.properties");

	private static final String[] allMessagesProperties = getPropertiesAsStringArray("messages.properties");

	/**
	 * @param args
	 * @throws IOException
	 */
	public static boolean generateMessagesFile(String filePath) throws IOException {
		return generate(filePath, allMessagesProperties);
	}

	/**
	 * @param generationParameters
	 * @param args
	 * @throws IOException
	 */
	public static boolean generateFormsFile(String filePath, Map<String, String> generationParameters) throws IOException {
		String[] properties = allFormsProperties;

		String alfURL = generationParameters.get(CONFIGURATION_PARAMETER_ALFRESCO_URL);
		String alfAdminLogin = generationParameters.get(CONFIGURATION_PARAMETER_ALFRESCO_ADMIN_LOGIN);
		String alfAdminPassWord = generationParameters.get(CONFIGURATION_PARAMETER_ALFRESCO_ADMIN_PSW);
		
		boolean haveNewURL = alfURL != null && !alfURL.equals("");
		boolean haveNewLogin = alfAdminLogin != null && !alfAdminLogin.equals("");		
		boolean haveNewPswd = alfAdminPassWord != null && !alfAdminPassWord.equals("");
		
		if (haveNewURL || haveNewLogin || haveNewPswd) {
			for (int i = 0; i < properties.length; i++) {
				String string = properties[i];
				if (string.startsWith("alfresco.url") && haveNewURL) {
					properties[i] = string.replaceFirst("(alfresco\\.url=).*$", "$1" + alfURL);
				}
				if (string.startsWith("user.name") && haveNewLogin) {
					properties[i] = string.replaceFirst("(user\\.name=).*$", "$1" + alfAdminLogin);
				}
				if (string.startsWith("user.pswd") && haveNewPswd) {
					properties[i] = string.replaceFirst("(user\\.pswd=).*$", "$1" + alfAdminPassWord);
				}
			}
		}

		return generate(filePath, properties);
	}

	/**
	 * @param filePath
	 * @param allLines
	 * @return
	 * @throws IOException
	 */
	private static boolean generate(String filePath, String[] allLines) throws IOException {
		String line;
		FileOutputStream fos = null;
		try {
			int pos = filePath.lastIndexOf(File.separator);
			String dirPath = filePath.substring(0, pos);
			File dir = new File(dirPath);
			dir.mkdirs();
			fos = new FileOutputStream(filePath);
			for (String property : allLines) {
				line = property + "\n";
				fos.write(line.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		DefaultMessages.generateMessagesFile("/Users/amenel/temp/messages.properties");
	}

	private static String[] getPropertiesAsStringArray(String fileName) {
		String[] lines = null;

		// get file stream from classPath
		InputStream in = DefaultMessages.class.getResourceAsStream(fileName);
		List<?> l = null;
		try {
			l = IOUtils.readLines(in);
		} catch (IOException e) {
			l = new ArrayList<String>();
			System.err.println("Stream for default file " + fileName + " can't be oppened :" + e);
		}
		lines = l.toArray(new String[l.size()]);
		return lines;
	}
}
