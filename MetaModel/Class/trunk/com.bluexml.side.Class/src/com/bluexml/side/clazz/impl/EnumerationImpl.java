/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.common.impl.NamedModelElementImpl;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.EnumerationImpl#getLiterals <em>Literals</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.EnumerationImpl#getDynamic <em>Dynamic</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.EnumerationImpl#getDepends <em>Depends</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumerationImpl extends NamedModelElementImpl implements Enumeration {
	/**
	 * The cached value of the '{@link #getLiterals() <em>Literals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiterals()
	 * @generated
	 * @ordered
	 */
	protected EList<EnumerationLiteral> literals;

	/**
	 * The default value of the '{@link #getDynamic() <em>Dynamic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean DYNAMIC_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getDynamic() <em>Dynamic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamic()
	 * @generated
	 * @ordered
	 */
	protected Boolean dynamic = DYNAMIC_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDepends() <em>Depends</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDepends()
	 * @generated
	 * @ordered
	 */
	protected Enumeration depends;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.ENUMERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EnumerationLiteral> getLiterals() {
		if (literals == null) {
			literals = new EObjectContainmentWithInverseEList<EnumerationLiteral>(EnumerationLiteral.class, this, ClazzPackage.ENUMERATION__LITERALS, ClazzPackage.ENUMERATION_LITERAL__ENUM);
		}
		return literals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getDynamic() {
		return dynamic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDynamic(Boolean newDynamic) {
		Boolean oldDynamic = dynamic;
		dynamic = newDynamic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ENUMERATION__DYNAMIC, oldDynamic, dynamic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration getDepends() {
		if (depends != null && depends.eIsProxy()) {
			InternalEObject oldDepends = (InternalEObject)depends;
			depends = (Enumeration)eResolveProxy(oldDepends);
			if (depends != oldDepends) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.ENUMERATION__DEPENDS, oldDepends, depends));
			}
		}
		return depends;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration basicGetDepends() {
		return depends;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDepends(Enumeration newDepends) {
		Enumeration oldDepends = depends;
		depends = newDepends;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ENUMERATION__DEPENDS, oldDepends, depends));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClazzPackage.ENUMERATION__LITERALS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLiterals()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClazzPackage.ENUMERATION__LITERALS:
				return ((InternalEList<?>)getLiterals()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClazzPackage.ENUMERATION__LITERALS:
				return getLiterals();
			case ClazzPackage.ENUMERATION__DYNAMIC:
				return getDynamic();
			case ClazzPackage.ENUMERATION__DEPENDS:
				if (resolve) return getDepends();
				return basicGetDepends();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClazzPackage.ENUMERATION__LITERALS:
				getLiterals().clear();
				getLiterals().addAll((Collection<? extends EnumerationLiteral>)newValue);
				return;
			case ClazzPackage.ENUMERATION__DYNAMIC:
				setDynamic((Boolean)newValue);
				return;
			case ClazzPackage.ENUMERATION__DEPENDS:
				setDepends((Enumeration)newValue);
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
			case ClazzPackage.ENUMERATION__LITERALS:
				getLiterals().clear();
				return;
			case ClazzPackage.ENUMERATION__DYNAMIC:
				setDynamic(DYNAMIC_EDEFAULT);
				return;
			case ClazzPackage.ENUMERATION__DEPENDS:
				setDepends((Enumeration)null);
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
			case ClazzPackage.ENUMERATION__LITERALS:
				return literals != null && !literals.isEmpty();
			case ClazzPackage.ENUMERATION__DYNAMIC:
				return DYNAMIC_EDEFAULT == null ? dynamic != null : !DYNAMIC_EDEFAULT.equals(dynamic);
			case ClazzPackage.ENUMERATION__DEPENDS:
				return depends != null;
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
		result.append(" (dynamic: ");
		result.append(dynamic);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //EnumerationImpl
