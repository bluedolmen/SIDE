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
		if (args.length != 0) {
			eclipsePath = args[0];
			if (args.length == 2) {
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
					.println("\t- If you gives 'com.bluexml.side', -> all projects which contains this name will be deleted.");
			System.exit(0);
		}

/*		
		browse(projectName, new File(eclipsePath + File.separator
				+ "configuration" + File.separator + "org.eclipse.osgi"
				+ File.separator + "bundles"), ".xml", "units", "unit",
				"id");
		
		browse(projectName, new File(eclipsePath + File.separator
				+ "p2" + File.separator + "org.eclipse.equinox.p2.core"
				+ File.separator + "cache"), "content.xml", "units", "unit",
				"id");

		browse(projectName, new File(eclipsePath + File.separator
				+ "configuration" + File.separator + "org.eclipse.update"
				+ File.separator + "history"), ".xml", "site", "feature", "id");
*/		 
		cleanXml(projectName, eclipsePath + File.separator + "artifacts.xml",
				"artifacts", "artifact", "id");

		cleanProjects(projectName, eclipsePath, "features");

		cleanProjects(projectName, eclipsePath, "plugins");
	}

	/**
	 * Browse the file in parameter, and when it find a file which contains the
	 * xmlFileName, it execute 'cleanXml' with the other arguments
	 * 
	 * 
	 * @param projectName
	 *            The project name to delete
	 * @param file
	 *            The file (folder) to browse
	 * @param xmlFileName
	 *            The XML file name to analyze
	 * @param rootName
	 *            The root Name if the XML file
	 * @param childrenName
	 *            The children name -> The node to delete if the 'attributeName'
	 *            match with the 'projectName'
	 * @param attributeName
	 *            The attribute of the node to analyze
	 */
	private static void browse(String projectName, File file,
			String xmlFileName, String rootName, String childrenName,
			String attributeName) {
		if (file.isDirectory()) {
			File[] fl = file.listFiles();
			for (int i = 0; i < fl.length; i++) {
				browse(projectName, fl[i], xmlFileName, rootName, childrenName,
						attributeName);
			}
		}
		if (file.exists() && file.getName().indexOf(xmlFileName) != -1) {
			cleanXml(projectName, file.getAbsolutePath(), rootName,
					childrenName, attributeName);
		}
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
	 *            The folder name to clean ('features' or 'plugins')
	 */
	private static void cleanProjects(String projectName, String eclipsePath,
			String folderName) {

		File folder = new File(eclipsePath + File.separator + folderName);

		String[] files = folder.list();

		for (int i = 0; i < files.length; i++) {
			if ((files[i].toLowerCase()).indexOf(projectName.toLowerCase()) != -1) {
				System.out.print("\t- " + folderName + " : " + files[i]
						+ " -> ");

				boolean out = FileHelper.deleteFile(new File(eclipsePath
						+ File.separator + folderName + File.separator
						+ files[i]));

				if (out) {
					System.out.print("Supprimé\n");
				} else {
					System.out.print("Non Supprimé\n");
				}
			}
		}

	}

	/**
	 * Analyze the 'filePath' and delete all occurrence of the 'projectName'
	 * which match with the 'attributeName' into all nodes 'childrenName' of the 'rootName'
	 * 
	 * @param projectName
	 *            The project name to delete
	 * @param filePath
	 *            The XML file name to analyze
	 * @param rootName
	 *            The root Name if the XML file
	 * @param childrenName
	 *            The children name -> The node to delete if the 'attributeName'
	 *            match with the 'projectName'
	 * @param attributeName
	 *            The attribute of the node to analyze
	 */
	private static void cleanXml(String projectName, String filePath,
			String rootName, String childrenName, String attributeName) {
		System.out.println("Dans le fichier: " + filePath);

		List<Element> listeArtifact = new ArrayList<Element>();

		org.jdom.Document document = null;
		org.jdom.Element racine;

		// On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();

		try {
			// On crée un nouveau document JDOM avec en argument le fichier
			// XML
			document = sxb.build(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// On initialise un nouvel élément racine avec l'élément racine du
		// document.
		racine = document.getRootElement();

		// On crée une List contenant tous les noeuds "artifacts" de
		// l'Element racine
		List<?> listArtifacts = racine.getChildren(rootName);

		// On crée un Iterator sur notre liste
		Iterator<?> i = listArtifacts.iterator();
		// on va parcourir tous les plugins
		while (i.hasNext()) {

			// On crée une List contenant tous les noeuds "artifact" de
			// l'Element racine

			Element parent = (Element) i.next();
			List<?> listArtifact = parent.getChildren(childrenName);

			// On crée un Iterator sur notre liste
			Iterator<?> j = listArtifact.iterator();
			// on va parcourir tous les plugins
			while (j.hasNext()) {

				Element courant = (Element) j.next();
				if ((courant.getAttributeValue(attributeName).toLowerCase())
						.indexOf(projectName.toLowerCase()) != -1) {
					listeArtifact.add(courant);
					// parent.removeContent(courant);
				}
			}

			for (Element element : listeArtifact) {
				System.out.println("\t- Suppression de : "
						+ element.getAttributeValue(attributeName));
				parent.removeContent(element);
			}
		}

		// Enregistrement du fichier
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
