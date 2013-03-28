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
