package com.bluexml.side.clazz.service.alfresco;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.Model;
import com.bluexml.side.common.Constraint;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NameSpace;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.util.metaModel.validate.OCLextension.OCLEvaluator;

public class CommonServices {
	public static String getNamedModelElementQName(NamedModelElement node) throws Exception {
		if (isNativeModel(node)) {
			return node.getName();
		}
		return convertFullNameToQualifiedName(node.getFullName());
	}

	public static String convertFullNameToQualifiedName(String fullName) {
		return fullName.replaceAll("\\.", "_");
		// return StringHelper.getJavaQName(fullName, "\\.");
	}

	public static EObject getRootContainer(EObject o) {
		if (o.eContainer() != null) {
			return getRootContainer(o.eContainer());
		} else {
			return o;
		}
	}

	public static boolean isNativeModel(ModelElement element) throws Exception {
		return getNamespaceURI(element).startsWith("http://www.alfresco.org/model");
	}

	public static String getPrefixedQName(NamedModelElement node, String separator) throws Exception {
		return getPrefix(node) + separator + CommonServices.getNamedModelElementQName(node);
	}

	public static String getPrefixedQName(NamedModelElement node) throws Exception {
		return getPrefixedQName(node, ":");
	}

	public static String getPrefix(EObject node) throws Exception {
		if (node instanceof ModelElement) {
			ModelElement namedElement = (ModelElement) node;
			NameSpace ns = namedElement.getLogicalNameSpace();
			if (ns != null) {
				return ns.getPrefix();
			}
		}

		// compute default prefix
		String prefix = "";
		EObject root = CommonServices.getRootContainer(node);
		if (root instanceof Model) {
			prefix = ((Model) root).getName();
		} else if (root instanceof ClassPackage) {
			// ensure retro compatibility
			prefix = ((ClassPackage) root).getName();
		} else {
			//			prefix="Missing_RootPackage";
			throw new Exception("Missing RootPackage object !!" + node);
		}

		return prefix;
	}

	public static String getNamespaceURI(EObject node) throws Exception {
		if (node instanceof ModelElement) {
			ModelElement namedElement = (ModelElement) node;
			NameSpace ns = namedElement.getLogicalNameSpace();
			if (ns != null) {
				return ns.getURI();
			}
		}
		// return default URI
		return "http://www.bluexml.com/model/content/" + getPrefix(node) + "/1.0";
	}

	public static String getPrefixedNamespaceQName(NamedModelElement node) throws Exception {
		return "{" + getNamespaceURI(node) + "}" + CommonServices.getNamedModelElementQName(node);
	}

	public static List<EObject> getNSL(Model node) throws Exception {
		List<EObject> l = new ArrayList<EObject>();

		List<String> prel = new ArrayList<String>();
		// add alfresco prefix that must be excluded
		List<EObject> fl = getAllExternalReference(node);

		for (EObject eObject : fl) {
			String prefix = CommonServices.getPrefix(eObject);
			if (!prel.contains(prefix)) {
				l.add(eObject);
				prel.add(prefix);
			}
		}

		return l;
	}

	public static List<EObject> getAllExternalReference(Model model) throws Exception {
		String prefix = CommonServices.getPrefix(model);
		List<EObject> linkedEObject = new ArrayList<EObject>();
		EList<AbstractClass> allAbstractClasses = model.getAllAbstractClasses();
		for (AbstractClass abstractClass : allAbstractClasses) {
			System.out.println("Scan Object :" + abstractClass);
			// generalization link
			EList<AbstractClass> generalizations = abstractClass.getGeneralizations();
			System.out.println("* generalizations :");
			for (AbstractClass abstractClass2 : generalizations) {
				System.out.println("** " + abstractClass2);
				addExternalEObject(prefix, linkedEObject, abstractClass2);
			}

			if (abstractClass instanceof Clazz) {
				Clazz c = (Clazz) abstractClass;
				EList<Aspect> aspects = c.getAspects();
				// hasAspect link
				System.out.println("* Aspects :");
				for (Aspect aspect : aspects) {
					System.out.println("** " + aspect);
					addExternalEObject(prefix, linkedEObject, aspect);
				}
			}
			System.out.println("* Attributes :");
			EList<Attribute> attributes = abstractClass.getAttributes();
			for (Attribute attribute : attributes) {
				System.out.println("** " + attribute);
				// constraints
				EList<Constraint> constraints = attribute.getConstraints();
				System.out.println("** Constraints :");
				for (Constraint constraint : constraints) {
					System.out.println("*** " + constraint);
					addExternalEObject(prefix, linkedEObject, constraint);
				}
				// enumeration
				Enumeration valueList = attribute.getValueList();
				if (valueList != null) {
					System.out.println("** ValList :" + valueList);
					addExternalEObject(prefix, linkedEObject, valueList);
				}
			}
		}
		System.out.println("Scan Associations :");
		EList<Association> allAssociations = model.getAllAssociations();
		for (Association association : allAssociations) {

			AbstractClass linkedClass = association.getFirstEnd().getLinkedClass();
			System.out.println("* FirstEnd" + linkedClass);
			addExternalEObject(prefix, linkedEObject, linkedClass);
			AbstractClass linkedClass2 = association.getSecondEnd().getLinkedClass();
			System.out.println("* SecondEnd " + linkedClass2);
			addExternalEObject(prefix, linkedEObject, linkedClass2);

		}
		System.out.println("computed :" + linkedEObject);

		return linkedEObject;
	}

	private static void addExternalEObject(String prefix, List<EObject> linkedEObject, EObject abstractClass2) throws Exception {
		String refPrefix = CommonServices.getPrefix(abstractClass2);
		if (!refPrefix.equals(prefix)) {
			System.out.println("# is External : " + prefix + " != " + refPrefix);
			linkedEObject.add(abstractClass2);
		}
	}
	
	public static Object OCLEval(EObject context, String body) throws Exception {
		OCLEvaluator evaluator = new OCLEvaluator();
		return evaluator.eval(context, body);
		
	}
}
