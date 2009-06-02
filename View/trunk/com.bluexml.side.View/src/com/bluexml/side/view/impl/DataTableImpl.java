/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;

import com.bluexml.side.view.Col;
import com.bluexml.side.view.DataTable;
import com.bluexml.side.view.DefaultCol;
import com.bluexml.side.view.ViewPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.DataTableImpl#getDefaultColSetUp <em>Default Col Set Up</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.DataTableImpl#getCols <em>Cols</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataTableImpl extends AbstractDataTableImpl implements DataTable {
	/**
	 * The cached value of the '{@link #getDefaultColSetUp() <em>Default Col Set Up</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultColSetUp()
	 * @generated
	 * @ordered
	 */
	protected DefaultCol defaultColSetUp;

	/**
	 * The cached value of the '{@link #getCols() <em>Cols</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCols()
	 * @generated
	 * @ordered
	 */
	protected EList<Col> cols;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.DATA_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefaultCol getDefaultColSetUp() {
		if (defaultColSetUp != null && defaultColSetUp.eIsProxy()) {
			InternalEObject oldDefaultColSetUp = (InternalEObject)defaultColSetUp;
			defaultColSetUp = (DefaultCol)eResolveProxy(oldDefaultColSetUp);
			if (defaultColSetUp != oldDefaultColSetUp) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP, oldDefaultColSetUp, defaultColSetUp));
			}
		}
		return defaultColSetUp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefaultCol basicGetDefaultColSetUp() {
		return defaultColSetUp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultColSetUp(DefaultCol newDefaultColSetUp) {
		DefaultCol oldDefaultColSetUp = defaultColSetUp;
		defaultColSetUp = newDefaultColSetUp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP, oldDefaultColSetUp, defaultColSetUp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Col> getCols() {
		if (cols == null) {
			cols = new EObjectContainmentEList<Col>(Col.class, this, ViewPackage.DATA_TABLE__COLS);
		}
		return cols;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.DATA_TABLE__COLS:
				return ((InternalEList<?>)getCols()).basicRemove(otherEnd, msgs);
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
			case ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP:
				if (resolve) return getDefaultColSetUp();
				return basicGetDefaultColSetUp();
			case ViewPackage.DATA_TABLE__COLS:
				return getCols();
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
			case ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP:
				setDefaultColSetUp((DefaultCol)newValue);
				return;
			case ViewPackage.DATA_TABLE__COLS:
				getCols().clear();
				getCols().addAll((Collection<? extends Col>)newValue);
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
			case ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP:
				setDefaultColSetUp((DefaultCol)null);
				return;
			case ViewPackage.DATA_TABLE__COLS:
				getCols().clear();
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
			case ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP:
				return defaultColSetUp != null;
			case ViewPackage.DATA_TABLE__COLS:
				return cols != null && !cols.isEmpty();
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //DataTableImpl
