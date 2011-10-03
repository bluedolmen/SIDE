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
