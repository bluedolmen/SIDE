/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.portal.AbstractPortletAttributes;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.Group;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.PortalModelElement;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletAttributeInstance;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.portal.PositionGroup;
import com.bluexml.side.portal.isChildPage;

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
 * @see com.bluexml.side.portal.PortalPackage
 * @generated
 */
public class PortalSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PortalPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalSwitch() {
		if (modelPackage == null) {
			modelPackage = PortalPackage.eINSTANCE;
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
			case PortalPackage.PORTAL_MODEL_ELEMENT: {
				PortalModelElement portalModelElement = (PortalModelElement)theEObject;
				T result = casePortalModelElement(portalModelElement);
				if (result == null) result = caseModelElement(portalModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.PORTAL: {
				Portal portal = (Portal)theEObject;
				T result = casePortal(portal);
				if (result == null) result = casePackage(portal);
				if (result == null) result = caseNamedModelElement(portal);
				if (result == null) result = caseModelElement(portal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.PAGE: {
				Page page = (Page)theEObject;
				T result = casePage(page);
				if (result == null) result = casePortalModelElement(page);
				if (result == null) result = caseModelElement(page);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.PORTAL_LAYOUT: {
				PortalLayout portalLayout = (PortalLayout)theEObject;
				T result = casePortalLayout(portalLayout);
				if (result == null) result = casePortalModelElement(portalLayout);
				if (result == null) result = caseModelElement(portalLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.COLUMN: {
				Column column = (Column)theEObject;
				T result = caseColumn(column);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.PORTLET: {
				Portlet portlet = (Portlet)theEObject;
				T result = casePortlet(portlet);
				if (result == null) result = casePortalModelElement(portlet);
				if (result == null) result = caseModelElement(portlet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.PORTLET_TYPE: {
				PortletType portletType = (PortletType)theEObject;
				T result = casePortletType(portletType);
				if (result == null) result = casePortalModelElement(portletType);
				if (result == null) result = caseModelElement(portletType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.PORTLET_INTERNAL: {
				PortletInternal portletInternal = (PortletInternal)theEObject;
				T result = casePortletInternal(portletInternal);
				if (result == null) result = casePortalModelElement(portletInternal);
				if (result == null) result = caseModelElement(portletInternal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.ABSTRACT_PORTLET_ATTRIBUTES: {
				AbstractPortletAttributes abstractPortletAttributes = (AbstractPortletAttributes)theEObject;
				T result = caseAbstractPortletAttributes(abstractPortletAttributes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.GROUP: {
				Group group = (Group)theEObject;
				T result = caseGroup(group);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.PORTLET_ATTRIBUTE: {
				PortletAttribute portletAttribute = (PortletAttribute)theEObject;
				T result = casePortletAttribute(portletAttribute);
				if (result == null) result = caseAbstractPortletAttributes(portletAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.HAVE_PORTLET: {
				HavePortlet havePortlet = (HavePortlet)theEObject;
				T result = caseHavePortlet(havePortlet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.POSITION_GROUP: {
				PositionGroup positionGroup = (PositionGroup)theEObject;
				T result = casePositionGroup(positionGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.INSTANCIATE_PORTLET_TYPE: {
				InstanciatePortletType instanciatePortletType = (InstanciatePortletType)theEObject;
				T result = caseInstanciatePortletType(instanciatePortletType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.PORTLET_ATTRIBUTE_INSTANCE: {
				PortletAttributeInstance portletAttributeInstance = (PortletAttributeInstance)theEObject;
				T result = casePortletAttributeInstance(portletAttributeInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PortalPackage.IS_CHILD_PAGE: {
				isChildPage isChildPage = (isChildPage)theEObject;
				T result = caseisChildPage(isChildPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	public T casePortalModelElement(PortalModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Portal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Portal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortal(Portal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePage(Page object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortalLayout(PortalLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseColumn(Column object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Portlet</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Portlet</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortlet(Portlet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Portlet Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Portlet Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortletType(PortletType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Portlet Internal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Portlet Internal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortletInternal(PortletInternal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Portlet Attributes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Portlet Attributes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractPortletAttributes(AbstractPortletAttributes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroup(Group object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Portlet Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Portlet Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortletAttribute(PortletAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Have Portlet</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Have Portlet</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHavePortlet(HavePortlet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Position Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Position Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePositionGroup(PositionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instanciate Portlet Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instanciate Portlet Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstanciatePortletType(InstanciatePortletType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Portlet Attribute Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Portlet Attribute Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortletAttributeInstance(PortletAttributeInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>is Child Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>is Child Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseisChildPage(isChildPage object) {
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

} //PortalSwitch
