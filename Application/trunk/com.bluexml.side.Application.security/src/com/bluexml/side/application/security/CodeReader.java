package com.bluexml.side.application.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * A class who read the program code and give a coded String from them It is
 * based on the codes in the file name codes
 * 
 * @author <a href="mailto:pbertrand@bluexml.com">Pierre BERTRAND</a>
 */
public class CodeReader implements GeneratorConstants {
	// This hashmap contains the values of the codes, the key is the
	// denomination of the code and refers to its integer value
	private static HashMap<String, Integer> codeTable = makeHashTable();

	/**
	 * Utility classes don't need to (and shouldn't) be instantiated.
	 */
	private CodeReader() {
		// prevents instantiation
	}

	/**
	 * This function read the file which has all the codes and their matches and
	 * fill an hashtable with them
	 */
	private static HashMap<String, Integer> makeHashTable() {
		HashMap<String, Integer> tableOfCode = new HashMap<String, Integer>();
		InputStream ipsCODE = null;
		ipsCODE = System.class.getClass().getResourceAsStream(FILE_PATH_CODE);
		InputStreamReader ipsrCODE = new InputStreamReader(ipsCODE);
		BufferedReader brCODE = new BufferedReader(ipsrCODE);
		String ligneCODE;
		// Reading the file "codes" and filling the HashMap
		try {
			while ((ligneCODE = brCODE.readLine()) != null) {
				String[] parsedLine = ligneCODE.split("\\"+CODE_SEPARATOR);
				tableOfCode.put(parsedLine[0], Integer.parseInt(parsedLine[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tableOfCode;
	}

	/**
	 * This function return an integer formed with the parameters and the
	 * matches in the file name codes
	 * 
	 * @param codeComponents
	 *            The code of the components separated by commas
	 *            (ex:"CODE_GED_G_W_ALFRESCO_30L,CODE_GEDPP_M_FORM,CODE_GEDPP_G_F_CHIBA")
	 * @return The codes added together (ex:"12350")
	 */
	public static Integer getCodes(String codeComponents) {
		// Initialisation
		Integer result = 0;
		String[] codes = codeComponents.split("\\"+CODE_SEPARATOR);

		// calculation of the codes
		for (int i = 0; i < codes.length; i++) {
			result += getCode(codes[i]);
		}
		return result;
	}
	
	public static Integer getCode(String code){
		return codeTable.get(code);
	}
	
}
