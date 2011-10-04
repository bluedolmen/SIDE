/**
 * 
 */
package com.bluexml.side.Framework.alfresco.workflow.pdfGenerator.language;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author dchevrier
 *
 */
public class LanguageMethods {

	public static HashMap<String, String> getScriptCommands(Map<String, String> commands) {
		HashMap<String, String> instructions = new HashMap<String, String>();
		Set<String> keysCommands = commands.keySet();
		for (String key : keysCommands) {
			if (key.startsWith(ConstantsLanguage.PDF_PARAMETER_PREFIX)){
				String pdfKey = key.substring(ConstantsLanguage.PDF_PARAMETER_PREFIX.length()+1);
				instructions.put(pdfKey, commands.get(key));
			}
		}
		return instructions;
	}

}
