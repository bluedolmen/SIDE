/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.util;

import com.bluexml.side.clazz.*;

import com.bluexml.side.common.Comment;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.clazz.ClazzPackage
 * @generated
 */
public class ClazzSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ClazzPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClazzSwitch() {
		if (modelPackage == null) {
			modelPackage = ClazzPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ClazzPackage.CLASS_MODEL_ELEMENT: {
				ClassModelElement classModelElement = (ClassModelElement)theEObject;
				T result = caseClassModelElement(classModelElement);
				if (result == null) result = caseModelElement(classModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT: {
				NamedClassModelElement namedClassModelElement = (NamedClassModelElement)theEObject;
				T result = caseNamedClassModelElement(namedClassModelElement);
				if (result == null) result = caseClassModelElement(namedClassModelElement);
				if (result == null) result = caseModelElement(namedClassModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.CLASS_PACKAGE: {
				ClassPackage classPackage = (ClassPackage)theEObject;
				T result = caseClassPackage(classPackage);
				if (result == null) result = casePackage(classPackage);
				if (result == null) result = caseNamedModelElement(classPackage);
				if (result == null) result = caseModelElement(classPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.CLAZZ: {
				Clazz clazz = (Clazz)theEObject;
				T result = caseClazz(clazz);
				if (result == null) result = caseAbstractClass(clazz);
				if (result == null) result = caseAbstractContainer(clazz);
				if (result == null) result = caseTitledNamedClassModelElement(clazz);
				if (result == null) result = caseNamedClassModelElement(clazz);
				if (result == null) result = caseClassModelElement(clazz);
				if (result == null) result = caseModelElement(clazz);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.ASSOCIATION: {
				Association association = (Association)theEObject;
				T result = caseAssociation(association);
				if (result == null) result = caseTitledNamedClassModelElement(association);
				if (result == null) result = caseNamedClassModelElement(association);
				if (result == null) result = caseClassModelElement(association);
				if (result == null) result = caseModelElement(association);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.ATTRIBUTE: {
				Attribute attribute = (Attribute)theEObject;
				T result = caseAttribute(attribute);
				if (result == null) result = caseTitledNamedClassModelElement(attribute);
				if (result == null) result = caseNamedClassModelElement(attribute);
				if (result == null) result = caseClassModelElement(attribute);
				if (result == null) result = caseModelElement(attribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.ENUMERATION: {
				Enumeration enumeration = (Enumeration)theEObject;
				T result = caseEnumeration(enumeration);
				if (result == null) result = caseNamedClassModelElement(enumeration);
				if (result == null) result = caseClassModelElement(enumeration);
				if (result == null) result = caseModelElement(enumeration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.ENUMERATION_LITERAL: {
				EnumerationLiteral enumerationLiteral = (EnumerationLiteral)theEObject;
				T result = caseEnumerationLiteral(enumerationLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.OPERATION: {
				Operation operation = (Operation)theEObject;
				T result = caseOperation(operation);
				if (result == null) result = caseNamedClassModelElement(operation);
				if (result == null) result = caseClassModelElement(operation);
				if (result == null) result = caseModelElement(operation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.PARAMETER: {
				Parameter parameter = (Parameter)theEObject;
				T result = caseParameter(parameter);
				if (result == null) result = caseNamedClassModelElement(parameter);
				if (result == null) result = caseClassModelElement(parameter);
				if (result == null) result = caseModelElement(parameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.ASPECT: {
				Aspect aspect = (Aspect)theEObject;
				T result = caseAspect(aspect);
				if (result == null) result = caseAbstractClass(aspect);
				if (result == null) result = caseAbstractContainer(aspect);
				if (result == null) result = caseTitledNamedClassModelElement(aspect);
				if (result == null) result = caseNamedClassModelElement(aspect);
				if (result == null) result = caseClassModelElement(aspect);
				if (result == null) result = caseModelElement(aspect);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.ABSTRACT_CLASS: {
				AbstractClass abstractClass = (AbstractClass)theEObject;
				T result = caseAbstractClass(abstractClass);
				if (result == null) result = caseAbstractContainer(abstractClass);
				if (result == null) result = caseTitledNamedClassModelElement(abstractClass);
				if (result == null) result = caseNamedClassModelElement(abstractClass);
				if (result == null) result = caseClassModelElement(abstractClass);
				if (result == null) result = caseModelElement(abstractClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.ABSTRACT_CONTAINER: {
				AbstractContainer abstractContainer = (AbstractContainer)theEObject;
				T result = caseAbstractContainer(abstractContainer);
				if (result == null) result = caseTitledNamedClassModelElement(abstractContainer);
				if (result == null) result = caseNamedClassModelElement(abstractContainer);
				if (result == null) result = caseClassModelElement(abstractContainer);
				if (result == null) result = caseModelElement(abstractContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.VIEW: {
				View view = (View)theEObject;
				T result = caseView(view);
				if (result == null) result = caseNamedClassModelElement(view);
				if (result == null) result = caseClassModelElement(view);
				if (result == null) result = caseModelElement(view);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.VIEW_ITEM: {
				ViewItem viewItem = (ViewItem)theEObject;
				T result = caseViewItem(viewItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.META_INFO: {
				MetaInfo metaInfo = (MetaInfo)theEObject;
				T result = caseMetaInfo(metaInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.META_INFO_GROUP: {
				MetaInfoGroup metaInfoGroup = (MetaInfoGroup)theEObject;
				T result = caseMetaInfoGroup(metaInfoGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT: {
				TitledNamedClassModelElement titledNamedClassModelElement = (TitledNamedClassModelElement)theEObject;
				T result = caseTitledNamedClassModelElement(titledNamedClassModelElement);
				if (result == null) result = caseNamedClassModelElement(titledNamedClassModelElement);
				if (result == null) result = caseClassModelElement(titledNamedClassModelElement);
				if (result == null) result = caseModelElement(titledNamedClassModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClazzPackage.CLASS_COMMENT: {
				ClassComment classComment = (ClassComment)theEObject;
				T result = caseClassComment(classComment);
				if (result == null) result = caseComment(classComment);
				if (result == null) result = caseModelElement(classComment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassModelElement(ClassModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Class Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Class Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedClassModelElement(NamedClassModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassPackage(ClassPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Clazz</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Clazz</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClazz(Clazz object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociation(Association object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttribute(Attribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumeration(Enumeration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumerationLiteral(EnumerationLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperation(Operation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Aspect</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Aspect</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAspect(Aspect object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractClass(AbstractClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractContainer(AbstractContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseView(View object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>View Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>View Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseViewItem(ViewItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Meta Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Meta Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetaInfo(MetaInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Meta Info Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Meta Info Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetaInfoGroup(MetaInfoGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Titled Named Class Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Titled Named Class Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTitledNamedClassModelElement(TitledNamedClassModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassComment(ClassComment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedModelElement(NamedModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(com.bluexml.side.common.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComment(Comment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ClazzSwitch
