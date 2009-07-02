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
		try{
			eclipsePath = args[0];
		} catch (Exception e) {
			System.out.println("Indiquez le chemin de votre Eclipse en paramètre svp...");
			System.exit(0);
		}
		

		cleanArtifact(eclipsePath);

		cleanProjects(eclipsePath, "features");

		cleanProjects(eclipsePath, "plugins");
	}

	private static void cleanProjects(String eclipsePath, String folderName) {

		File folder = new File(eclipsePath + File.separator + folderName);

		String[] files = folder.list();

		for (int i = 0; i < files.length; i++) {
			if (files[i].indexOf("com.bluexml.side") != -1) {
				FileHelper.deleteFile(new File(eclipsePath + File.separator
						+ folderName + File.separator + files[i]));
			}
		}

	}

	private static void cleanArtifact(String eclipsePath) {
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
				System.out.println(courant.getAttributeValue("id"));
				if ((courant.getAttributeValue("id"))
						.indexOf("com.bluexml.side") != -1) {
					
					System.out.println(courant.getAttributeValue("id"));
					listeArtifact.add(courant);
					//parent.removeContent(courant);
				}
			}
			
			for(Element element: listeArtifact){
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
