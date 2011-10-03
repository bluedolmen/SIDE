package com.bluexml.side.integration.buildHudson.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> listeProjetPoms = new ArrayList<String>();
		File f = new File("/Users/davidabad/.hudson/jobs/Build_RCP_Community_TEST/buildAuto/Ankle/repositoryCopy/S-IDE");
		listeProjetPoms = BuilderUtils.findFile(f, "pom.xml");

		for (String string : listeProjetPoms) {
			System.out.println(string);
		}
		System.out.println("poms :"+listeProjetPoms.size());
	}

}
