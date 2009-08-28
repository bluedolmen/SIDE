package com.bluexml.side.integration.standalone.metamodel.documentation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

/**
 * provide useful method for models documentation generation
 * @author Constantin Madola
 *
 */
public class CustomDocumentationService {
	
	public static final String DOCUMENTATION_SOURCE = "http://www.eclipse.org/emf/2002/GenModel";
	public static final String OCL_SOURCE = "http://www.bluexml.com/OCL";
	public static final String DOCUMENTATION_KEY = "documentation";
	public static final String DESCRIPTION_KEY = "description";
	
	
	
	/**
	 * Replaces special character in the string
	 * @param 
	 * @return
	 */
	private static String processString(String c){
		String result = new String(c);
		result =result.replaceAll("<", "&lt;");
		result =result.replaceAll(">", "&gt;");
		return c!=null?result:null;
	}
	
	/**
	 * Return the contains of the EAnnotation documentation
	 * @param emElt
	 * @return
	 */
	public static String getSrvDocumentation(EModelElement emElt){
		String result = null;
		EAnnotation annotation = emElt.getEAnnotation(DOCUMENTATION_SOURCE);
		if(annotation!=null){
			EMap<String,String> details = annotation.getDetails();
			result = details.get("documentation");
		}
		return result;
	}
	
	public static String getSrvDocumentation(EClass ec){
		return getSrvDocumentation((EModelElement)ec);
	}
	
	public static String getSrvDocumentation(EClassifier ec){
		return getSrvDocumentation((EModelElement)ec);
	}
	public static String getSrvDocumentation(EAttribute ea){
		return getSrvDocumentation((EModelElement)ea);
	}
	
	public static String getSrvDocumentation(EReference ea){
		return getSrvDocumentation((EModelElement)ea);
	}
	
	/**
	 * Returns an Elist containing the current EClassifier EAttributes
	 * @param ec
	 * @return
	 */
	public static List<EAttribute> getSrvEAttributes(EClassifier ec){
		List<EAttribute> result = new ArrayList<EAttribute>();
		if(ec instanceof EClass){
			result = ((EClass)ec).getEAllAttributes();
		}
		return result;
	}
	


	
	/**
	 * Return the list of defined validation rules has a list of EStringToStringMapEntry except for description
	 * Special characters are removed from the EStringToStringMapEntrys during the process
	 * @param emElt
	 * @return
	 */
	public static List<EStringToStringMapEntryImpl> getSrvValidationRule(EModelElement emElt){
		List<EStringToStringMapEntryImpl> result = null;
		EAnnotation annotation = emElt.getEAnnotation(OCL_SOURCE);

		if(annotation!=null){
			Object[] tab = annotation.getDetails().entrySet().toArray();
			int c = 0;
			List<EStringToStringMapEntryImpl> listeBien = new ArrayList<EStringToStringMapEntryImpl>();
			while(c<tab.length){
				EStringToStringMapEntryImpl entry = (EStringToStringMapEntryImpl)tab[c];
				if(!"description".equalsIgnoreCase(entry.getKey())){
					entry.setValue(processString(entry.getValue()));
					listeBien.add(entry);
				}
				c++;
			}	
			result = listeBien;
		}
		return result;
	}
	

	public static List<EStringToStringMapEntryImpl> getSrvValidationRule(EClassifier ec){
		return getSrvValidationRule((EModelElement)ec);
	}
	
	/**
	 * Returns the the overall description of the EObject validation rule
	 * @param emElt
	 * @return
	 */
	public static String getSrvValidationRuleDescription(EModelElement emElt){
		String result = null;
		EAnnotation annotation = emElt.getEAnnotation(OCL_SOURCE);
		if(annotation!=null){
			EMap<String,String> tab = annotation.getDetails();
			 
			result = tab.get("description");
			
		}
		return processString(result);
	}
	
	public static String getSrvValidationRuleDescription(EClassifier ec){
		return getSrvValidationRuleDescription((EModelElement)ec);
	}
	
	/**
	 * Returns the Eclasssifer's association as an EReferenceList
	 * @param ec
	 * @return
	 */
	public static List<EReference> getSrvAssociations(EClassifier ec){
		List<EReference> result = new ArrayList<EReference>();
		if(ec instanceof EClass){
			result = ((EClass)ec).getEAllReferences();
		}
		return result;
	}
	

}
