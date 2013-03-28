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
package com.bluexml.side.integration.buildHudson;

import java.util.List;

public class Application {
	public static final String buildStartLine = "****************************************";
	public static final String SIDE_Core = "S-IDE";
	public static final String SIDE_Enterprise = "S-IDE_Enterprise";

	public static String workspace = "";
	public static String build_number = "";
	public static String build_id = "";
	public static String svn_revision = "";
	public static String rcp = "";

	public static List<String> projectsExcluded;

	// si au moins un paramètre n'est pas renseigné, alors on suppose que le
	// build est lancé sans hudson
	public static boolean parametre = true;

	// indique si on build la version enterprise ou labs
	// public static boolean EnterpriseRelease = true;

}
