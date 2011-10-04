/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2009
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package keyManager;

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
		ipsCODE = CodeReader.class.getResourceAsStream(FILE_NAME);
		InputStreamReader ipsrCODE = new InputStreamReader(ipsCODE);
		BufferedReader brCODE = new BufferedReader(ipsrCODE);
		String ligneCODE;
		// Reading the file "codes" and filling the HashMap
		try {
			while ((ligneCODE = brCODE.readLine()) != null) {
				String[] parsedLine = ligneCODE.split("\\" + CODE_SEPARATOR);
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
	 *            (ex:"CODE_GED_G_W_ALFRESCO_30L,CODE_GEDPP_M_FORM,CODE_GEDPP_G_F_CHIBA"
	 *            )
	 * @return The codes added together (ex:"12350")
	 */
	public static Integer getCodes(String codeComponents) {
		// Initialisation
		Integer result = 0;
		String[] codes = codeComponents.split("\\" + CODE_SEPARATOR);

		// calculation of the codes
		for (int i = 0; i < codes.length; i++) {
			String code = codes[i];
			Integer code2 = getCode(code);
			if (code2 != null) {
				result += code2;
			} else {
				System.err.println("code Not found :" + code);
			}

		}
		return result;
	}

	public static Integer getCode(String code) {
		return codeTable.get(code);
	}

}
