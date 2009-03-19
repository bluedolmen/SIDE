/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.ViewItem;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.ViewItemImpl#getAssociation <em>Association</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ViewItemImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ViewItemImpl#getClasse <em>Classe</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ViewItemImpl#getAspect <em>Aspect</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.ViewItemImpl#getRole <em>Role</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewItemImpl extends EObjectImpl implements ViewItem {
	/**
	 * The cached value of the '{@link #getAssociation() <em>Association</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociation()
	 * @generated
	 * @ordered
	 */
	protected Association association;

	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected Attribute attribute;

	/**
	 * The cached value of the '{@link #getClasse() <em>Classe</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasse()
	 * @generated
	 * @ordered
	 */
	protected Clazz classe;

	/**
	 * The cached value of the '{@link #getAspect() <em>Aspect</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAspect()
	 * @generated
	 * @ordered
	 */
	protected Aspect aspect;

	/**
	 * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected String role = ROLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.VIEW_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Association getAssociation() {
		if (association != null && association.eIsProxy()) {
			InternalEObject oldAssociation = (InternalEObject)association;
			association = (Association)eResolveProxy(oldAssociation);
			if (association != oldAssociation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.VIEW_ITEM__ASSOCIATION, oldAssociation, association));
			}
		}
		return association;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Association basicGetAssociation() {
		return association;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociation(Association newAssociation) {
		Association oldAssociation = association;
		association = newAssociation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.VIEW_ITEM__ASSOCIATION, oldAssociation, association));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute getAttribute() {
		if (attribute != null && attribute.eIsProxy()) {
			InternalEObject oldAttribute = (InternalEObject)attribute;
			attribute = (Attribute)eResolveProxy(oldAttribute);
			if (attribute != oldAttribute) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.VIEW_ITEM__ATTRIBUTE, oldAttribute, attribute));
			}
		}
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute basicGetAttribute() {
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttribute(Attribute newAttribute) {
		Attribute oldAttribute = attribute;
		attribute = newAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.VIEW_ITEM__ATTRIBUTE, oldAttribute, attribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz getClasse() {
		if (classe != null && classe.eIsProxy()) {
			InternalEObject oldClasse = (InternalEObject)classe;
			classe = (Clazz)eResolveProxy(oldClasse);
			if (classe != oldClasse) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.VIEW_ITEM__CLASSE, oldClasse, classe));
			}
		}
		return classe;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clazz basicGetClasse() {
		return classe;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClasse(Clazz newClasse) {
		Clazz oldClasse = classe;
		classe = newClasse;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.VIEW_ITEM__CLASSE, oldClasse, classe));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Aspect getAspect() {
		if (aspect != null && aspect.eIsProxy()) {
			InternalEObject oldAspect = (InternalEObject)aspect;
			aspect = (Aspect)eResolveProxy(oldAspect);
			if (aspect != oldAspect) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.VIEW_ITEM__ASPECT, oldAspect, aspect));
			}
		}
		return aspect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Aspect basicGetAspect() {
		return aspect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAspect(Aspect newAspect) {
		Aspect oldAspect = aspect;
		aspect = newAspect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.VIEW_ITEM__ASPECT, oldAspect, aspect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRole(String newRole) {
		String oldRole = role;
		role = newRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.VIEW_ITEM__ROLE, oldRole, role));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean equalsForMerge(ViewItem other) {
		if (equalsForMergeBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.VIEW_ITEM.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.VIEW_ITEM, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				equalsForMergeBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(equalsForMergeBodyOCL);
	 
		EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();
		
		evalEnv.add("other", other);
	  
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #equalsForMerge <em>Equals For Merge</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #equalsForMerge
	 * @generated
	 */
	private static OCLExpression<EClassifier> equalsForMergeBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClazzPackage.VIEW_ITEM__ASSOCIATION:
				if (resolve) return getAssociation();
				return basicGetAssociation();
			case ClazzPackage.VIEW_ITEM__ATTRIBUTE:
				if (resolve) return getAttribute();
				return basicGetAttribute();
			case ClazzPackage.VIEW_ITEM__CLASSE:
				if (resolve) return getClasse();
				return basicGetClasse();
			case ClazzPackage.VIEW_ITEM__ASPECT:
				if (resolve) return getAspect();
				return basicGetAspect();
			case ClazzPackage.VIEW_ITEM__ROLE:
				return getRole();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClazzPackage.VIEW_ITEM__ASSOCIATION:
				setAssociation((Association)newValue);
				return;
			case ClazzPackage.VIEW_ITEM__ATTRIBUTE:
				setAttribute((Attribute)newValue);
				return;
			case ClazzPackage.VIEW_ITEM__CLASSE:
				setClasse((Clazz)newValue);
				return;
			case ClazzPackage.VIEW_ITEM__ASPECT:
				setAspect((Aspect)newValue);
				return;
			case ClazzPackage.VIEW_ITEM__ROLE:
				setRole((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ClazzPackage.VIEW_ITEM__ASSOCIATION:
				setAssociation((Association)null);
				return;
			case ClazzPackage.VIEW_ITEM__ATTRIBUTE:
				setAttribute((Attribute)null);
				return;
			case ClazzPackage.VIEW_ITEM__CLASSE:
				setClasse((Clazz)null);
				return;
			case ClazzPackage.VIEW_ITEM__ASPECT:
				setAspect((Aspect)null);
				return;
			case ClazzPackage.VIEW_ITEM__ROLE:
				setRole(ROLE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ClazzPackage.VIEW_ITEM__ASSOCIATION:
				return association != null;
			case ClazzPackage.VIEW_ITEM__ATTRIBUTE:
				return attribute != null;
			case ClazzPackage.VIEW_ITEM__CLASSE:
				return classe != null;
			case ClazzPackage.VIEW_ITEM__ASPECT:
				return aspect != null;
			case ClazzPackage.VIEW_ITEM__ROLE:
				return ROLE_EDEFAULT == null ? role != null : !ROLE_EDEFAULT.equals(role);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Role: ");
		result.append(role);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //ViewItemImpl
