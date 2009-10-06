/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation.impl;

import com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueBody;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueHead;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Informal Table Value Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueGroupImpl#getCols <em>Cols</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueGroupImpl#getThead <em>Thead</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueGroupImpl#getTbody <em>Tbody</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InformalTableValueGroupImpl extends EObjectImpl implements InformalTableValueGroup {
	/**
	 * The default value of the '{@link #getCols() <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCols()
	 * @generated
	 * @ordered
	 */
	protected static final int COLS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCols() <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCols()
	 * @generated
	 * @ordered
	 */
	protected int cols = COLS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getThead() <em>Thead</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThead()
	 * @generated
	 * @ordered
	 */
	protected InformalTableValueHead thead;

	/**
	 * The cached value of the '{@link #getTbody() <em>Tbody</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTbody()
	 * @generated
	 * @ordered
	 */
	protected InformalTableValueBody tbody;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InformalTableValueGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DocumentationPackage.Literals.INFORMAL_TABLE_VALUE_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCols(int newCols) {
		int oldCols = cols;
		cols = newCols;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__COLS, oldCols, cols));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformalTableValueHead getThead() {
		return thead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThead(InformalTableValueHead newThead, NotificationChain msgs) {
		InformalTableValueHead oldThead = thead;
		thead = newThead;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__THEAD, oldThead, newThead);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThead(InformalTableValueHead newThead) {
		if (newThead != thead) {
			NotificationChain msgs = null;
			if (thead != null)
				msgs = ((InternalEObject)thead).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__THEAD, null, msgs);
			if (newThead != null)
				msgs = ((InternalEObject)newThead).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__THEAD, null, msgs);
			msgs = basicSetThead(newThead, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__THEAD, newThead, newThead));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformalTableValueBody getTbody() {
		return tbody;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTbody(InformalTableValueBody newTbody, NotificationChain msgs) {
		InformalTableValueBody oldTbody = tbody;
		tbody = newTbody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__TBODY, oldTbody, newTbody);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTbody(InformalTableValueBody newTbody) {
		if (newTbody != tbody) {
			NotificationChain msgs = null;
			if (tbody != null)
				msgs = ((InternalEObject)tbody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__TBODY, null, msgs);
			if (newTbody != null)
				msgs = ((InternalEObject)newTbody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__TBODY, null, msgs);
			msgs = basicSetTbody(newTbody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__TBODY, newTbody, newTbody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__THEAD:
				return basicSetThead(null, msgs);
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__TBODY:
				return basicSetTbody(null, msgs);
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
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__COLS:
				return getCols();
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__THEAD:
				return getThead();
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__TBODY:
				return getTbody();
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
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__COLS:
				setCols((Integer)newValue);
				return;
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__THEAD:
				setThead((InformalTableValueHead)newValue);
				return;
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__TBODY:
				setTbody((InformalTableValueBody)newValue);
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
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__COLS:
				setCols(COLS_EDEFAULT);
				return;
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__THEAD:
				setThead((InformalTableValueHead)null);
				return;
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__TBODY:
				setTbody((InformalTableValueBody)null);
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
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__COLS:
				return cols != COLS_EDEFAULT;
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__THEAD:
				return thead != null;
			case DocumentationPackage.INFORMAL_TABLE_VALUE_GROUP__TBODY:
				return tbody != null;
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
		result.append(" (cols: ");
		result.append(cols);
		result.append(')');
		return result.toString();
	}

} //InformalTableValueGroupImpl
