package com.bluexml.side.Util.MetaModel.gendoc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParsePalette {
	
	// TODO gérer les éventuelles erreurs dans le .diagramconfigurator
	protected static Logger logger = Logger.getLogger("process");

	/**
	 * Extrait les "références" des objets de la palette
	 */
	public static List<String> extractGenClass(String fileName){

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		List<String> hrefNames = new ArrayList<String>();
		try {
			Handler fh = new FileHandler("gendoc.log", false);
			fh.setFormatter(new SimpleFormatter());
			logger.addHandler(fh);			
			
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			File file = new File(fileName);
			Document document = docBuilder.parse(file);
			NodeList nodes = document.getElementsByTagName("objects");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element currentNode = (Element) nodes.item(i);
				if (currentNode.hasChildNodes()){
					Node genClass = currentNode.getFirstChild().getNextSibling();
					if (genClass != null){
						NamedNodeMap genClassAttributes = genClass.getAttributes();
						if (genClassAttributes != null){
							Node href = genClassAttributes.item(0);
							if (href != null){
								hrefNames.add(href.getTextContent());
							}
							else{
								logger.log(Level.WARNING, "Erreur process.ParsePalette.extractGenClass: " +
								"l'attribut href n'est pas renseigné.");
							}
						}
						else{
							logger.log(Level.WARNING, "Erreur process.ParsePalette.extractGenClass: " +
							"l'élément genclass ne possède pas d'attribut.");
						}
					}
					else{
						logger.log(Level.WARNING, "Erreur process.ParsePalette.extractGenClass: " +
						"l'élément 'object' ne possède pas de 'référence' " +
						"(fils genClass).");
					}
				}
			}
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < e.getStackTrace().length; i++){
				sb.append(e.getStackTrace()[i]+"\n");
			}
			logger.log(Level.SEVERE, e.toString()+"\n"+sb.toString());
		}
		return hrefNames;	
	}

	/**
	 * Extrait le chemin des métamodèles à traiter ainsi que les nom des objets de la palette associés
	 * (au métamodèle)
	 * @param hrefNames
	 * @return
	 */
	public static HashMap<String, List<String>> extractNames(List<String> hrefNames){
		
		HashMap<String, List<String>> names = new HashMap<String, List<String>>();
		for (String href : hrefNames) {
			int firstIndex = href.indexOf('#');
			String relevantInfoMetaModel = href.substring(0, firstIndex-8) + "ecore";
			String metaModelPath = relevantInfoMetaModel.substring(6);
			String relevantInfoObject = href.substring(firstIndex+3);
			int secondIndex = relevantInfoObject.indexOf('/');
			String objectName = relevantInfoObject.substring(secondIndex+1);
			if (!names.containsKey(metaModelPath)){
				List<String> objectNames = new ArrayList<String>();
				objectNames.add(objectName);
				names.put(metaModelPath, objectNames);
			}
			else{
				List<String> objectsNamesByMetaModel = names.get(metaModelPath);
				objectsNamesByMetaModel.add(objectName);
				names.remove(metaModelPath);
				names.put(metaModelPath, objectsNamesByMetaModel);
			}
				
		}
		return names;
	}
	
}
