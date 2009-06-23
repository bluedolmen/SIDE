/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 *******************************************************************************/
package com.bluexml.side.Util.ecore;



/**
 *  pour savoir si resource externe eProxy ??
 */



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class has utility methods for EObject
 * @author Constantin Madola
 */


public abstract class EObjectUtils {

	/**
	 * retrun true if target is present in source contains
	 * equalForMerge method are used for the comparison 
	 * @param source
	 * @param target
	 * @param eRefLink
	 * @return
	 */
	@Deprecated //ClassLoader is needed for remote method calling
	public static boolean isTargetPresentHasChildInSourceUsingERefLInk(EObject source,EObject target,EReference eRefLink){
		boolean result = false;
		Object eRefLinkContent = source.eGet(eRefLink);
		if(eRefLinkContent instanceof List<?>){
			List<EObject> rawList = (List<EObject>) eRefLinkContent;
			ListIterator<EObject> iterListEObject = rawList.listIterator();
			while(iterListEObject.hasNext() && !result){
				EObject current = iterListEObject.next();
				try {
					result = equalsForMerge(current,target);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return result;
	}

	/**
	 * retrun true if target is present in source contains
	 * equalForMerge method are used for the comparison 
	 * @param source
	 * @param target
	 * @param eRefLink
	 * @return
	 */
	public static boolean isTargetPresentHasChildInSourceUsingERefLInk(EObject source,EObject target,EReference eRefLink,ClassLoader cl){
		boolean result = false;
		Object eRefLinkContent = source.eGet(eRefLink);
		if(eRefLinkContent instanceof List<?>){
			List<EObject> rawList = (List<EObject>) eRefLinkContent;

			ListIterator<EObject> iterListEObject = rawList.listIterator();
			while(iterListEObject.hasNext() && !result){
				EObject current = iterListEObject.next();
				try {
					result = equalsForMerge(current,target,cl);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		return result;
	}

	/**
	 * Return true if the two arguments are eObject from the same Eclass (name comparison)
	 * @param eObject
	 * @param otherEObject
	 * @return
	 */
	public static boolean areFromSameEClassName(EObject eObject,EObject otherEObject){
		return eObject.eClass().getName().equals(otherEObject.eClass().getName());
	}


	/**
	 * Check if the two eObject are from the same Eclass, and then if
	 * they have te same attribute and value
	 * @param eObject
	 * @param otherEObject
	 * @return
	 */

	public static boolean hasStrictEqualsOnAttributes(EObject eObject, EObject otherEObject){
		boolean result = false;
		if(areFromSameEClassName(eObject,otherEObject)){
			EList<EAttribute> eListAttributes =eObject.eClass().getEAllAttributes();	
			int compteur = 0;
			ListIterator<EAttribute> iterListEAtt = eListAttributes.listIterator();
			boolean subResult = true;
			while(iterListEAtt.hasNext() && subResult){
				EStructuralFeature esf = iterListEAtt.next();
				if(eObject.eIsSet(esf) && otherEObject.eIsSet(esf)){
					subResult = subResult && areEObjectAttributesValueEquals(eObject.eGet(esf),otherEObject.eGet(esf));
				}else{
					if((eObject.eIsSet(esf) && !otherEObject.eIsSet(esf)) || (!eObject.eIsSet(esf) && otherEObject.eIsSet(esf)) ){
						subResult =false;
					}
				}
			}
			result = subResult;
		}else{
			result = false;
		}
		return result;
	}

	/**
	 * Deprecated because remote method invocation needs a classloader in wich required class is specified
	 * use other method getting a class loader in argument
	 * Check if the two eObject are fro the same Eclass, and then if
	 * they have te same attribute value
	 * @param eObject
	 * @param otherEObject
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Deprecated
	public static boolean equalsForMerge(EObject eObject, EObject otherEObject) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException{

		boolean result = false;
		if(eObject.eClass().getName().equals(otherEObject.eClass().getName())){
			result = true;
			ListIterator<EOperation> eleop = eObject.eClass().getEOperations().listIterator();
			boolean founded = false;
			Boolean strictEquals = null;
			Boolean forMerge = null;
			EOperation op = null;
			while(!founded && eleop.hasNext()){
				EOperation eop = eleop.next();
				if(eop.getName().contains("equalsForMerge")){
					founded = true;
					op = eop;
				}
			}

			if (founded){
				Method equalForMerge = eObject.getClass().getMethod("equalsForMerge", Class.forName(eObject.eClass().getInstanceClassName()));
				if(equalForMerge!=null){
					// >!< Object b = Class.forName(eObject.eClass().getInstanceClassName()).cast(eObject);
					// >!< Object c = Class.forName(otherEObject.eClass().getInstanceClassName()).cast(otherEObject);
					forMerge = (Boolean)equalForMerge.invoke(eObject, otherEObject);

				}
			}
			strictEquals = hasStrictEqualsOnAttributes(eObject, otherEObject);
			result = strictEquals;
			if(forMerge != null){
				if(forMerge && !strictEquals){
					System.out.println("WARNING ON "+eObject+" and "+otherEObject);

				}
				result = forMerge;
			}
		}
		return result;
	}
	/**
	 * Check if the two eObject are fro the same Eclass
	 * if it is the case, look into their definition if they own an equalForMerge EOperation
	 * if not, then default equality method is called (equality between attributes)
	 * otherwise, the Eobject corresponding implementation class is loaded
	 * the method equalForMerge is invoked
	 * the result of the method is the result of equalsForMerge EOperation ocl interpretation
	 * warning may be raise
	 * @param eObject
	 * @param otherEObject
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	public static boolean equalsForMerge(EObject eObject, EObject otherEObject,ClassLoader cl) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException{
			
		boolean result = false;
		if(eObject.eClass().getName().equals(otherEObject.eClass().getName())){
			result = true;
			ListIterator<EOperation> eleop = eObject.eClass().getEOperations().listIterator();
			boolean founded = false;
			Boolean strictEquals = null;
			Boolean forMerge = null;
			EOperation op = null;
			while(!founded && eleop.hasNext()){
				EOperation eop = eleop.next();
				if(eop.getName().contains("equalsForMerge")){
					founded = true;
					op = eop;
				}
			}

		
			if (founded){
				//Class[] parameters = {eObject.getClass()};
				//AssociationImpl asoc;

				Method equalForMerge = eObject.getClass().getMethod("equalsForMerge", Class.forName(eObject.eClass().getInstanceClassName(),false,cl));
				if(equalForMerge!=null){
					//	Object b = Class.forName(eObject.eClass().getInstanceClassName()).cast(eObject);
					//	Object c = Class.forName(otherEObject.eClass().getInstanceClassName()).cast(otherEObject);
					forMerge = (Boolean)equalForMerge.invoke(eObject, otherEObject);

				}
			}

			strictEquals = hasStrictEqualsOnAttributes(eObject, otherEObject);
			result = strictEquals;

			if(forMerge != null){
				if(forMerge && !strictEquals){
					System.out.println("WARNING ON "+eObject+" and "+otherEObject);

				}
				result = forMerge;
			}
		}
		return result;
	}

	/**
	 * Return Object equality, hoping that equals method was redefined
	 * @param obj
	 * @param otherObj
	 * @return
	 */
	public static boolean areEObjectAttributesValueEquals(Object obj, Object otherObj){
		boolean result =false;
		result =  obj.equals(otherObj);
		if(result){
			System.out.println(" thinks that "+obj+" equals "+otherObj);
		}
		return result;
	}
	
	/**
	 * Returns an empty Eobject of the EClass given in argument using the EPackage
	 * @param toCopy
	 * @param metaModelPackage
	 * @return
	 */
	public static EObject simpleEObjectCopy(EClass toCopy,EPackage metaModelPackage){
		return  metaModelPackage.getEFactoryInstance().create(toCopy);
	}
	
	/**
	 * Add target to source using given Ereference
	 * @param source
	 * @param target
	 * @param eRefLink
	 */
	public static void addTargetToSourceByEReference(EObject source, EObject target, EReference eRefLink){
		Object o = source.eGet(eRefLink);
		if(o instanceof List<?>){
			//TODO use EObjectContainmentEList<E> instead
			List<EObject> listResolvedErefLink = (List<EObject>) o;
			listResolvedErefLink.add(target);
		}

	}
	/**
	 * unused debug method
	 * @param source
	 * @param target
	 * @param eRefLink
	 * @param pading
	 */
	public static void DEBUGaddTargetToSourceByEReference(EObject source, EObject target, EReference eRefLink,String pading){
		Object o = source.eGet(eRefLink);
		if(o instanceof List<?>){
			List<EObject> listResolvedErefLink = (List<EObject>) o;
			listResolvedErefLink.add(target);


		}

	}

	/** deprecated
	 * classloader is needed to remote call
	 * @param elMergeResource
	 * @param elModelToMerge
	 * @param refLink
	 */
	@Deprecated 
	public static EObject getEObjectFromSourceEqualsTarget(EObject source, EObject target, EReference eRefLink) {
		boolean bPresent = false;
		EObject result=null;
		Object eRefLinkContent = source.eGet(eRefLink);
		if(eRefLinkContent instanceof List<?>){
			List<EObject> rawList = (List<EObject>) eRefLinkContent;

			ListIterator<EObject> iterListEObject = rawList.listIterator();
			while(iterListEObject.hasNext() && !bPresent){
				EObject current = iterListEObject.next();
				try {
					bPresent = equalsForMerge(current,target);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bPresent){
					result = current;
				}
			}

		}

		return result;

	}
	/**
	 * Return the first Eobject in source which is equalForMerge to target using a specified eRefLink
	 * @param elMergeResource
	 * @param elModelToMerge
	 * @param refLink
	 */
	public static EObject getEObjectFromSourceEqualsTarget(EObject source, EObject target, EReference eRefLink,ClassLoader cl) {
		boolean bPresent = false;
		EObject result=null;
		Object eRefLinkContent = source.eGet(eRefLink);
		if(eRefLinkContent instanceof List<?>){
			List<EObject> rawList = (List<EObject>) eRefLinkContent;

			ListIterator<EObject> iterListEObject = rawList.listIterator();
			while(iterListEObject.hasNext() && !bPresent){
				EObject current = iterListEObject.next();
				try {
					bPresent = equalsForMerge(current,target,cl);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bPresent){
					result = current;
				}
			}

		}

		return result;

	}
	/** 
	 * return the value of an EStructuralFeature from its name
	 * May return null
	 * @param elMergeResource
	 * @param elModelToMerge
	 * @param refLink
	 */
	public static Object eGetFromString(EObject source, String esfString) {
		Object result = null;
		EStructuralFeature esf = source.eClass().getEStructuralFeature(esfString);
		if(esf != null){
			// the using of eGet(EStructuralFeature,Boolean)  prevent from resolving proxies during the get
			result = source.eGet(esf,false);
		}

		return result;

	}


}
