/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip;
import com.bluexml.side.requirements.generator.metamodel.WebProject.Field;
import com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Relation Ship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl#getIdLeft <em>Id Left</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl#getIdRight <em>Id Right</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl#isMandatoryLeft <em>Mandatory Left</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl#isMandatoryRight <em>Mandatory Right</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl#isManyLeft <em>Many Left</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl#isManyRight <em>Many Right</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentRelationShipImpl extends ComponentPropertyImpl implements ComponentRelationShip {
	/**
	 * The cached value of the '{@link #getIdLeft() <em>Id Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdLeft()
	 * @generated
	 * @ordered
	 */
	protected Field idLeft;

	/**
	 * The cached value of the '{@link #getIdRight() <em>Id Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdRight()
	 * @generated
	 * @ordered
	 */
	protected Field idRight;

	/**
	 * The default value of the '{@link #isMandatoryLeft() <em>Mandatory Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatoryLeft()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANDATORY_LEFT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMandatoryLeft() <em>Mandatory Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatoryLeft()
	 * @generated
	 * @ordered
	 */
	protected boolean mandatoryLeft = MANDATORY_LEFT_EDEFAULT;

	/**
	 * The default value of the '{@link #isMandatoryRight() <em>Mandatory Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatoryRight()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANDATORY_RIGHT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMandatoryRight() <em>Mandatory Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatoryRight()
	 * @generated
	 * @ordered
	 */
	protected boolean mandatoryRight = MANDATORY_RIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #isManyLeft() <em>Many Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManyLeft()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANY_LEFT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isManyLeft() <em>Many Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManyLeft()
	 * @generated
	 * @ordered
	 */
	protected boolean manyLeft = MANY_LEFT_EDEFAULT;

	/**
	 * The default value of the '{@link #isManyRight() <em>Many Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManyRight()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANY_RIGHT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isManyRight() <em>Many Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManyRight()
	 * @generated
	 * @ordered
	 */
	protected boolean manyRight = MANY_RIGHT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentRelationShipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebProjectPackage.Literals.COMPONENT_RELATION_SHIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field getIdLeft() {
		if (idLeft != null && idLeft.eIsProxy()) {
			InternalEObject oldIdLeft = (InternalEObject)idLeft;
			idLeft = (Field)eResolveProxy(oldIdLeft);
			if (idLeft != oldIdLeft) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebProjectPackage.COMPONENT_RELATION_SHIP__ID_LEFT, oldIdLeft, idLeft));
			}
		}
		return idLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field basicGetIdLeft() {
		return idLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdLeft(Field newIdLeft) {
		Field oldIdLeft = idLeft;
		idLeft = newIdLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT_RELATION_SHIP__ID_LEFT, oldIdLeft, idLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field getIdRight() {
		if (idRight != null && idRight.eIsProxy()) {
			InternalEObject oldIdRight = (InternalEObject)idRight;
			idRight = (Field)eResolveProxy(oldIdRight);
			if (idRight != oldIdRight) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebProjectPackage.COMPONENT_RELATION_SHIP__ID_RIGHT, oldIdRight, idRight));
			}
		}
		return idRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field basicGetIdRight() {
		return idRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdRight(Field newIdRight) {
		Field oldIdRight = idRight;
		idRight = newIdRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT_RELATION_SHIP__ID_RIGHT, oldIdRight, idRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMandatoryLeft() {
		return mandatoryLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMandatoryLeft(boolean newMandatoryLeft) {
		boolean oldMandatoryLeft = mandatoryLeft;
		mandatoryLeft = newMandatoryLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_LEFT, oldMandatoryLeft, mandatoryLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMandatoryRight() {
		return mandatoryRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMandatoryRight(boolean newMandatoryRight) {
		boolean oldMandatoryRight = mandatoryRight;
		mandatoryRight = newMandatoryRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_RIGHT, oldMandatoryRight, mandatoryRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isManyLeft() {
		return manyLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManyLeft(boolean newManyLeft) {
		boolean oldManyLeft = manyLeft;
		manyLeft = newManyLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_LEFT, oldManyLeft, manyLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isManyRight() {
		return manyRight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManyRight(boolean newManyRight) {
		boolean oldManyRight = manyRight;
		manyRight = newManyRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_RIGHT, oldManyRight, manyRight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebProjectPackage.COMPONENT_RELATION_SHIP__ID_LEFT:
				if (resolve) return getIdLeft();
				return basicGetIdLeft();
			case WebProjectPackage.COMPONENT_RELATION_SHIP__ID_RIGHT:
				if (resolve) return getIdRight();
				return basicGetIdRight();
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_LEFT:
				return isMandatoryLeft();
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_RIGHT:
				return isMandatoryRight();
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_LEFT:
				return isManyLeft();
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_RIGHT:
				return isManyRight();
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
			case WebProjectPackage.COMPONENT_RELATION_SHIP__ID_LEFT:
				setIdLeft((Field)newValue);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__ID_RIGHT:
				setIdRight((Field)newValue);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_LEFT:
				setMandatoryLeft((Boolean)newValue);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_RIGHT:
				setMandatoryRight((Boolean)newValue);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_LEFT:
				setManyLeft((Boolean)newValue);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_RIGHT:
				setManyRight((Boolean)newValue);
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
			case WebProjectPackage.COMPONENT_RELATION_SHIP__ID_LEFT:
				setIdLeft((Field)null);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__ID_RIGHT:
				setIdRight((Field)null);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_LEFT:
				setMandatoryLeft(MANDATORY_LEFT_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_RIGHT:
				setMandatoryRight(MANDATORY_RIGHT_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_LEFT:
				setManyLeft(MANY_LEFT_EDEFAULT);
				return;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_RIGHT:
				setManyRight(MANY_RIGHT_EDEFAULT);
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
			case WebProjectPackage.COMPONENT_RELATION_SHIP__ID_LEFT:
				return idLeft != null;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__ID_RIGHT:
				return idRight != null;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_LEFT:
				return mandatoryLeft != MANDATORY_LEFT_EDEFAULT;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANDATORY_RIGHT:
				return mandatoryRight != MANDATORY_RIGHT_EDEFAULT;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_LEFT:
				return manyLeft != MANY_LEFT_EDEFAULT;
			case WebProjectPackage.COMPONENT_RELATION_SHIP__MANY_RIGHT:
				return manyRight != MANY_RIGHT_EDEFAULT;
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
		result.append(" (mandatoryLeft: ");
		result.append(mandatoryLeft);
		result.append(", mandatoryRight: ");
		result.append(mandatoryRight);
		result.append(", manyLeft: ");
		result.append(manyLeft);
		result.append(", manyRight: ");
		result.append(manyRight);
		result.append(')');
		return result.toString();
	}

} //ComponentRelationShipImpl
