package com.bluexml.side.integration.cleanEclipse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.integration.cleanEclipse.utils.FileHelper;

public class Application {

	public static void main(String[] args) {
		String eclipsePath = "";
		String projectName = "com.bluexml.side";
		if(args.length != 0) {
			eclipsePath = args[0];
			if(args.length == 2){
				projectName = args[1];
			}
		} else {
			System.out.println("Parameter 1 -> the eclipse path to clean.");
			System.out
					.println("Parameter 2 -> the project name (default 'com.bluexml.side').");
			System.out
					.println("\nThis application will clean eclipse of any part of the project given in parameter.");
			System.out.println("Examples:");
			System.out
					.println("\t- If you gives 'com.bluexml.side.Application', -> only this project will be deleted from eclipse.");
			System.out
					.println("\t- If you gives 'com.bluexml.side', -> all projects who contains this name will be deleted.");
			System.exit(0);
		}
		
		

		cleanArtifact(projectName, eclipsePath);

		cleanProjects(projectName, eclipsePath, "features");

		cleanProjects(projectName, eclipsePath, "plugins");
	}

	/**
	 * Delete all the file which contain the project name into the folder
	 * features or plugins
	 * 
	 * @param projectName
	 *            The project name
	 * @param eclipsePath
	 *            The path to the eclipse
	 * @param folderName
	 *            The name folder to clean ('features' or 'plugins')
	 */
	private static void cleanProjects(String projectName, String eclipsePath,
			String folderName) {

		File folder = new File(eclipsePath + File.separator + folderName);

		String[] files = folder.list();

		for (int i = 0; i < files.length; i++) {
			if (files[i].indexOf(projectName) != -1) {
				FileHelper.deleteFile(new File(eclipsePath + File.separator
						+ folderName + File.separator + files[i]));
			}
		}

	}

	/**
	 * 
	 * Clean the artifac.xml of all the projects given in parameter
	 * 
	 * @param projectName
	 *            The project to delete
	 * @param eclipsePath
	 *            The path to the eclipse
	 */
	private static void cleanArtifact(String projectName, String eclipsePath) {
		List<Element> listeArtifact = new ArrayList<Element>();

		org.jdom.Document document = null;
		org.jdom.Element racine;

		// On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();

		try {
			// On crée un nouveau document JDOM avec en argument le fichier
			// XML
			document = sxb.build(new File(eclipsePath + File.separator
					+ "artifacts.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On initialise un nouvel élément racine avec l'élément racine du
		// document.
		racine = document.getRootElement();

		// On crée une List contenant tous les noeuds "artifacts" de
		// l'Element racine
		List<?> listArtifacts = racine.getChildren("artifacts");

		// On crée un Iterator sur notre liste
		Iterator<?> i = listArtifacts.iterator();
		// on va parcourir tous les plugins
		while (i.hasNext()) {

			// On crée une List contenant tous les noeuds "artifact" de
			// l'Element racine

			Element parent = (Element) i.next();
			List<?> listArtifact = parent.getChildren("artifact");

			// On crée un Iterator sur notre liste
			Iterator<?> j = listArtifact.iterator();
			// on va parcourir tous les plugins
			while (j.hasNext()) {

				Element courant = (Element) j.next();
				if ((courant.getAttributeValue("id")).indexOf(projectName) != -1) {
					listeArtifact.add(courant);
					// parent.removeContent(courant);
				}
			}

			for (Element element : listeArtifact) {
				parent.removeContent(element);
			}
		}

		// Enregistrement du fichier
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(eclipsePath
					+ File.separator + "artifacts.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
