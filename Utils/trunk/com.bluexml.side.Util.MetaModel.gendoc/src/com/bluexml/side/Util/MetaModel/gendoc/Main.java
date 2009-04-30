package com.bluexml.side.Util.MetaModel.gendoc;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;




public class Main {
	
	// TODO erreurs d'écriture dans les .ecore à gérer
	protected static Logger logger = Logger.getLogger("process");

	/**
	 * Effectue la génération de documentation, le fichier .diagramconfigurator
	 * étant passé en paramètre
	 * @param args
	 */
	public static void main(String[] args) {
		
		HashMap<String, List<String>> palette = ParsePalette.extractNames(ParsePalette.extractGenClass(args[0]));
		Set<String> metamodels = palette.keySet();
		for (String metamodel : metamodels) {
			List<String> objects = palette.get(metamodel);
			DocMetaModel processDoc = new DocMetaModel();
			EPackage ePackage = getEPackage(metamodel);
			processDoc.head(ePackage);
			processDoc.processPackage(ePackage, objects);
			processDoc.foot(ePackage);
		}
			
	}
	
	/**
	 * Récupère le package père dans un fichier .ecore
	 * @param file
	 * @return
	 */
	public static EPackage getEPackage(String file){
		
		EPackage ePackage = null;
		try{
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.getResource(URI.createFileURI(file), true);
			EList<EObject> contents = resource.getContents();
			EObject rootContent = contents.get(0);
			if (rootContent instanceof EPackageImpl){
				ePackage = (EPackage) rootContent;
			}
			else{
				logger.log(Level.WARNING, "Erreur process.Main.getEPackage: il n'y a pas de package 'père'.");
			}
			
		}
		catch (Exception e){
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < e.getStackTrace().length; i++){
				sb.append(e.getStackTrace()[i]+"\n");
			}
			logger.log(Level.SEVERE, e.toString()+"\n"+sb.toString());
		}
		return ePackage;
		
	}

}
