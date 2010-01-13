package com.bluexml.side.Util.MetaModel.gendoc;
 
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EEnumImpl;
import org.eclipse.emf.ecore.impl.EOperationImpl;

public class DocMetaModel {
	
	// TODO OPTIMISER!...
	// TODO gérer les erreurs (log)
	// TODO gérer la doc des EEnum (types)
	protected static Logger logger = Logger.getLogger("process");
	
	private String fileName = new String();
	private HashMap<EClassifier,Boolean> docOk = new HashMap<EClassifier, Boolean>();
	private HashMap<EClassifier,Boolean> docOclOk = new HashMap<EClassifier, Boolean>();
	private HashMap<EClassifier,Boolean> docAttOk = new HashMap<EClassifier, Boolean>();
	private HashMap<EClassifier,Boolean> docOpOk = new HashMap<EClassifier, Boolean>();
	
	/**
	 * initialise à faux les listes indiquant la présence ou non de documentation pour
	 * chaque EClassifier
	 * @param ePackage
	 */
	public void init(EPackage ePackage){
		
		List<EClassifier> classifiers = ePackage.getEClassifiers();
		for (EClassifier classifier : classifiers) {
			docOk.put(classifier, false);
			docOclOk.put(classifier, false);
			docAttOk.put(classifier, false);
			docOpOk.put(classifier, false);
			
		}
		
	}
	
	/**
	 * débute l'écriture du .docbook
	 * @param ePack
	 */
	public void head(EPackage ePackage){
		
		fileName = ePackage.getName();
		try {
			FileWriter file = new FileWriter(fileName + ".docbook");
			file.write("<?xml version='1.0'?>");
			file.write("<article>");
			file.write("<table frame='all' border='2'>");
			file.write("<title>Metamodel "+ePackage.getName()+"</title>");
			file.write("<tgroup cols='3' colsep='2' rowsep='2'>");
			file.write("<colspec colwidth='2*' colname='c1'/>");
			file.write("<colspec colwidth='3*' colname='c2'/>");
			file.write("<colspec colwidth='5*' colname='c3'/>");
			file.write("<spanspec spanname='hspan3' namest='c1' nameend='c3'/>");
			file.write("<tbody>");
			file.close();
		} catch (IOException e) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < e.getStackTrace().length; i++){
				sb.append(e.getStackTrace()[i]+"\n");
			}
			logger.log(Level.SEVERE, e.toString()+"\n"+sb.toString());
		}
		
	}
	/**
	 * clôt l'écriture du .docbook
	 * @param ePack
	 */
	public void foot(EPackage ePackage) {
		
		try {
			FileWriter file = new FileWriter(fileName + ".docbook",true);
			file.write("</tbody></tgroup></table></article>");
			file.close();
		} catch (IOException e) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < e.getStackTrace().length; i++){
				sb.append(e.getStackTrace()[i]+"\n");
			}
			logger.log(Level.SEVERE, e.toString()+"\n"+sb.toString());
		}
		
	}
	
//	/**
//	 * parcourt les sous_packages d'un métamodèle et les traite
//	 * @param ePack
//	 */
//	public void processSubPackages(EPackage ePack){
//		
//		processPackage(ePack);
//		EList<EPackage> subPackages = ePack.getESubpackages();
//		if (!subPackages.isEmpty()){
//			for (EPackage subPack : subPackages) {
//				processSubPackages(subPack);
//			}
//		}
//		
//	}
	
	/**
	 * retourne la liste des EClass contenant de la documentation
	 * @param ePackage
	 * @return
	 */
	public List<EClassifier> checkDoc(EPackage ePackage){

		List<EClassifier> classifiersOk = new ArrayList<EClassifier>();

		EList<EClassifier> classifiers = ePackage.getEClassifiers();
		for (EClassifier classifier : classifiers) {
			if (classifier instanceof EClassImpl){
				EList<EAnnotation> annotations = classifier.getEAnnotations();
				for (EAnnotation annotation : annotations) {
					//Vérification d'existence de doc générale
					if (annotation.getSource() == "http://www.eclipse.org/emf/2002/GenModel" && !classifiersOk.contains(classifier)){
						docOk.put(classifier, true);
						classifiersOk.add(classifier);
					}
					//Vérification doc contraintes OCL
					//cette doc est dans une detailEntry d'une EAnnotation ayant pour key
					//"documentationxxx" où xxx est le nom de la contrainte
					int nbOclWithDoc = 0;
					if (annotation.getSource() == "http://www.bluexml.com/OCL"){
						EMap<String, String> details = annotation.getDetails();
						Collection<String> keys = details.keySet();
						for (String key : keys) {
							if (key.startsWith("documentation")){
								nbOclWithDoc++;
							}
						}
					}
					if (nbOclWithDoc >= 1 && !classifiersOk.contains(classifier)){
						docOclOk.put(classifier, true);
						classifiersOk.add(classifier);
					}
				}
				EList<EObject> contents = classifier.eContents();
				int nbAttWithDoc = 0;
				int nbOpWithDoc = 0;
				for (EObject object : contents) {
					//Vérification des attributs avec doc
					if (object instanceof EAttributeImpl){
						EAttributeImpl attribute = (EAttributeImpl) object;
						EList<EAnnotation> docsAttributes = attribute.getEAnnotations();
						for (EAnnotation annotation : docsAttributes) {
							if (annotation.getSource().equals("http://www.eclipse.org/emf/2002/GenModel")){
								nbAttWithDoc++;
								docAttOk.put(classifier, true);
							}
						}
					}
					//Vérification des opérations avec doc
					//cette dernière est située dans une EAnnotation GenModel
					if (object instanceof EOperationImpl){
						EOperationImpl operation = (EOperationImpl) object;
						EList<EAnnotation> docsOperation = operation.getEAnnotations();
						for (EAnnotation annotation : docsOperation) {
							if (annotation.getSource().equals("http://www.eclipse.org/emf/2002/GenModel")){
								nbOpWithDoc++;
								docOpOk.put(classifier, true);
							}
						}
					}
				}
				if (nbAttWithDoc >= 1 && !classifiersOk.contains(classifier)){
					classifiersOk.add(classifier);
				}
				if (nbOpWithDoc >= 1 && !classifiersOk.contains(classifier)){
					classifiersOk.add(classifier);
				}
			}
		}
		
		return classifiersOk;

	}
	
	/**
	 * traite la mise en page de la documentation pour un package d'un métamodèle
	 * @param ePackage
	 */
	public void processPackage(EPackage ePackage, List<String> ObjectsFromPalette){
		
		init(ePackage);
		List<EEnum> types = getTypes(ePackage);
		List<EClassifier> classifiers = checkDoc(ePackage);
		if (classifiers.size() != 0){
			try{
				FileWriter file = new FileWriter(fileName + ".docbook",true);
				for (EClassifier classifier : classifiers) {
					logger.log(Level.SEVERE, classifier.getName());
					if (ObjectsFromPalette.contains(classifier.getName())){
						file.write("<row><entry spanname='hspan3' align='center'>");
						file.write("<sect1><title>"+classifier.getName()+"</title></sect1>");
						file.write("</entry></row>");
						if (docOk.get(classifier)) {
							file.write("<row><entry align='center'>");
							file.write("<para><sect3><title>Description</title></sect3></para>");
							file.write("</entry>");
							writeDoc(getDoc(classifier),file);
						}
						writeType(types, classifier, file);
						if (docAttOk.get(classifier)) {
							file.write("<row><entry align='center'>");
							file.write("<para><sect3><title>Attributes</title></sect3></para>");
							file.write("</entry>");
							writeDocAtt(getDocAtt(classifier),file);
						}
						if (docOclOk.get(classifier)) {
							file.write("<row><entry align='center'>");
							file.write("<para><sect3><title>Validation rules</title></sect3></para>");
							file.write("</entry>");
							writeDocGen(getDocOcl(classifier), file);
						}
						if (docOpOk.get(classifier)) {
							file.write("<row><entry align='center'>");
							file.write("<para><sect3><title>Operations</title></sect3></para>");
							file.write("</entry>");
							writeDocGen(getDocOp(classifier), file);
						}
					} else {
						logger.log(Level.SEVERE, "CHECK: " + classifier.getName() + " is NOT in Palette");
					}
				}
				file.close();
			}
			catch(IOException e){
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < e.getStackTrace().length; i++){
					sb.append(e.getStackTrace()[i]+"\n");
				}
				logger.log(Level.SEVERE, e.toString()+"\n"+sb.toString());
			}

		}

	}
	
	/**
	 * récupère la documentation générale pour un EClassifier
	 * @param classifier
	 * @return
	 */
	public Collection<String> getDoc(EClassifier classifier){
		
		Collection<String> doc = new ArrayList<String>();
		
		EList<EAnnotation> annotations = classifier.getEAnnotations();
		for (EAnnotation annotation : annotations) {
			if (annotation.getSource().equals("http://www.eclipse.org/emf/2002/GenModel")){
				EMap<String, String> details = annotation.getDetails();
				doc = details.values();
			}
		}
		return doc;
		
	}
	
	/**
	 * traite la présentation de la doc générale
	 * @param docs
	 * @param file
	 * @throws IOException
	 */
	public void writeDoc(Collection<String> docs, FileWriter file) throws IOException{
		
		int nbRow = 0;
		for (String doc : docs) {
			if (nbRow == 0){
				file.write("<entry namest='c2' nameend='c3'><para>");
				file.write(replacenpara(doc));
				file.write("</para></entry></row>");
				nbRow++;
			}
			else{
				file.write("<row><entry><para></para></entry>");
				file.write("<entry namest='c2' nameend='c3'><para>"+replacenpara(doc)+"</para></entry>");
				file.write("</row>");
			}	
		}
	}
	
	/**
	 * récupère la documentation relative aux attributs
	 * @param classifier
	 * @return
	 */
	public HashMap<String, Collection<String>> getDocAtt(EClassifier classifier){
		
		HashMap<String, Collection<String>> docs = new HashMap<String, Collection<String>>();
		
		EList<EObject> contents = classifier.eContents();
		for (EObject object : contents) {
			if (object instanceof EAttributeImpl){
				EAttributeImpl attribute = (EAttributeImpl) object;
				EList<EAnnotation> docsAttribute = attribute.getEAnnotations();
				if (docsAttribute.size() != 0){
					for (EAnnotation annotation : docsAttribute) {
						EMap<String, String> details = annotation.getDetails();
						String key = attribute.getName();
						Collection<String> values = details.values();
						docs.put(key, values);
					}
				}	
			}
		}
		
		return docs;
		
	}
	
	
	/**
	 * remplacer les retour chariot par <para></para>
	 * @param docs
	 * @param file
	 * @throws IOException
	 */
	public String replacenpara(String valueAttributs) throws IOException{
		
		if (valueAttributs.indexOf("\r\r")>0)
		{
			valueAttributs=valueAttributs.replaceFirst("\r\r", "<para></para>");
			while(valueAttributs.indexOf("\r\r")>0){
				valueAttributs=valueAttributs.replaceFirst("\r\r", "<para></para>");
			}
		}
		if (valueAttributs.indexOf("\r")>0)
		{
			valueAttributs=valueAttributs.replaceFirst("\r", "<para></para>");
			while(valueAttributs.indexOf("\r")>0){
				valueAttributs=valueAttributs.replaceFirst("\r", "<para></para>");
			}
		}
		return valueAttributs;
	}
	
	/**
	 * traite la présentation de la doc relative aux attributs
	 * @param docs
	 * @param file
	 * @throws IOException
	 */
	public void writeDocAtt(HashMap<String, Collection<String>> docs, FileWriter file) throws IOException{
		
		int nbRow = 0;
		Set<String> keys = docs.keySet();
		for (String key : keys) {
			Object[] value = docs.get(key).toArray();
			if (nbRow == 0){
				file.write("<entry><para><sect3><title>"+key+"</title></sect3></para></entry>");
				file.write("<entry><para>"+replacenpara(value[nbRow].toString())+"</para></entry>");
				file.write("</row>");
				nbRow++;
				if (value.length > 1){
					file.write("<row><entry><para></para></entry><entry><para></para></entry>");
					for (int i = 1 ;i < value.length; i++){
						file.write("<entry><para>"+replacenpara(value[i].toString())+"</para></entry>");
						nbRow = i;
					}
					file.write("</row>");
				}
			}
			else{
				file.write("<row><entry><para /></entry>");
				file.write("<entry><para><sect3><title>"+key+"</title></sect3></para></entry>");
				file.write("<entry><para>"+replacenpara(value[0].toString())+"</para></entry>");
				file.write("</row>");
				if (value.length > 1){
					file.write("<row><entry><para></para></entry><entry><para></para></entry>");
					for (int i = 1 ;i < value.length; i++){
						file.write("<entry><para>"+replacenpara(value[i].toString())+"</para></entry>");
					}
					file.write("</row>");
				}

			}
			
		}
	}
	
	/**
	 * récupère la doc relative aux contraintes OCL
	 * @param classifier
	 * @return
	 */
	public HashMap<String,String> getDocOcl(EClassifier classifier){
		
		HashMap<String,String> docs = new HashMap<String,String>();
		
		EList<EAnnotation> annotations = classifier.getEAnnotations();
		for (EAnnotation annotation : annotations) {
			if (annotation.getSource().equals("http://www.bluexml.com/OCL")){
				EMap<String,String> details = annotation.getDetails();
				Collection<String> keys = details.keySet();
				for (String key : keys) {
					if (key.startsWith("documentation")){
						String realKey = key.substring(13);
						String doc = details.get(key);
						docs.put(realKey, doc);
					}
				}	
			}
		}
		
		return docs;
		
	}
	
	/**
	 * traite la présentation de la doc relative aux contraintes OCL et aux opérations
	 * @param docs
	 * @param file
	 * @throws IOException
	 */
	public void writeDocGen(HashMap<String,String> docs, FileWriter file) throws IOException{
		
		int nbRow = 0;
		Set<String> keys = docs.keySet();
		for (String key : keys) {
			if (nbRow == 0){
				file.write("<entry><para>"+key+"</para></entry>");
				file.write("<entry><para>"+docs.get(key)+"</para></entry>");
				file.write("</row>");
				nbRow++;
			}
			else{
				file.write("<row><entry><para></para></entry>");
				file.write("<entry><para>"+key+"</para></entry>");
				file.write("<entry><para>"+docs.get(key)+"</para></entry>");
				file.write("</row>");
			}
		}	
	}
	
	/**
	 * récupère la doc relative aux opérations
	 * @param classifier
	 * @return
	 */
	public HashMap<String, String> getDocOp(EClassifier classifier){
		
		HashMap<String, String> doc = new HashMap<String, String>();
		
		EList<EObject> contents = classifier.eContents();
		for (EObject object : contents) {
			if (object instanceof EOperationImpl){
				EOperationImpl operation = (EOperationImpl) object;
				EList<EAnnotation> annotations = operation.getEAnnotations();
				for (EAnnotation annotation : annotations) {
					if (annotation.getSource().equals("http://www.eclipse.org/emf/2002/GenModel")){
						EMap<String,String> details = annotation.getDetails();
						Set<String> keys = details.keySet();
						for (String key : keys) {
							doc.put(operation.getName(),details.get(key));
						}
					}
				}
			}
		}
		
		return doc;
		
	}
	
	/**
	 * récupère les types dans un métamodèle
	 * @param ePackage
	 * @return
	 */
	public List<EEnum> getTypes(EPackage ePackage){
		//On suppose que les types sont des EEnum
		List<EEnum> types = new ArrayList<EEnum>();
		
		EList<EClassifier> classifiers = ePackage.getEClassifiers();
		for (EClassifier classifier : classifiers) {
			if (classifier instanceof EEnumImpl){
				EEnum type = (EEnum) classifier;
				types.add(type);
			}
		}
		return types;
		
	}
	
	/**
	 * traite la présentation des types associés à un EClassifier
	 * @param types
	 * @param classifier
	 * @param file
	 * @throws IOException
	 */
	public void writeType(List<EEnum> types, EClassifier classifier, FileWriter file) throws IOException{
		
		for (EEnum type : types) {
			if ((type.getName()).equals(classifier.getName()+"Type")){
				file.write("<row><entry align='center'><para>Type</para></entry>");
				EList<EEnumLiteral> literals = type.getELiterals();
				int nbRow = 0;
				for (EEnumLiteral literal : literals){
					if (nbRow == 0){
						file.write("<entry><para>"+literal.getName()+"</para></entry>");
						file.write("</row>");
						nbRow++;
					}
					else{
						file.write("<row><entry><para></para></entry>");
						file.write("<entry><para>"+literal.getName()+"</para></entry></row>");
					}
				}
			}
		}
		
	}
	
	/**
	 * main pour générer à partir d'un .ecore directement
	 * @param args
	 */
	public static void main(String[] args){
		
		Properties properties = new Properties();
		 try {
		     properties.load(new FileInputStream("modelspath.properties"));
		 } catch (IOException e) {
			 StringBuffer sb = new StringBuffer();
				for (int i = 0; i < e.getStackTrace().length; i++){
					sb.append(e.getStackTrace()[i]+"\n");
				}
			logger.log(Level.SEVERE, e.toString()+"\n"+sb.toString());
		 }
		String formFileEcore = properties.getProperty("forms.ecore");
		EPackage ePackage = Main.getEPackage("../"+formFileEcore);
		List<String> objects = new ArrayList<String>();
		List<EClassifier> classifiers = ePackage.getEClassifiers();
		for (EClassifier classifier : classifiers) {
			objects.add(classifier.getName());
		}
		
		DocMetaModel processDoc = new DocMetaModel();
		processDoc.head(ePackage);
		processDoc.processPackage(ePackage, objects);
		processDoc.foot(ePackage);
		
	}
	
}
