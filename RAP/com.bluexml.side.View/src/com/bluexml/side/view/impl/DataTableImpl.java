/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.Actionable;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.DataTable;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.DataTableImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.DataTableImpl#getDefaultColSetUp <em>Default Col Set Up</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataTableImpl extends AbstractDataTableImpl implements DataTable {
	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent operations;
	/**
	 * The cached value of the '{@link #getDefaultColSetUp() <em>Default Col Set Up</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultColSetUp()
	 * @generated
	 * @ordered
	 */
	protected Col defaultColSetUp;

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
	public OperationComponent getOperations() {
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperations(OperationComponent newOperations, NotificationChain msgs) {
		OperationComponent oldOperations = operations;
		operations = newOperations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_TABLE__OPERATIONS, oldOperations, newOperations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperations(OperationComponent newOperations) {
		if (newOperations != operations) {
			NotificationChain msgs = null;
			if (operations != null)
				msgs = ((InternalEObject)operations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_TABLE__OPERATIONS, null, msgs);
			if (newOperations != null)
				msgs = ((InternalEObject)newOperations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_TABLE__OPERATIONS, null, msgs);
			msgs = basicSetOperations(newOperations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_TABLE__OPERATIONS, newOperations, newOperations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Col getDefaultColSetUp() {
		return defaultColSetUp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultColSetUp(Col newDefaultColSetUp, NotificationChain msgs) {
		Col oldDefaultColSetUp = defaultColSetUp;
		defaultColSetUp = newDefaultColSetUp;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP, oldDefaultColSetUp, newDefaultColSetUp);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultColSetUp(Col newDefaultColSetUp) {
		if (newDefaultColSetUp != defaultColSetUp) {
			NotificationChain msgs = null;
			if (defaultColSetUp != null)
				msgs = ((InternalEObject)defaultColSetUp).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP, null, msgs);
			if (newDefaultColSetUp != null)
				msgs = ((InternalEObject)newDefaultColSetUp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP, null, msgs);
			msgs = basicSetDefaultColSetUp(newDefaultColSetUp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP, newDefaultColSetUp, newDefaultColSetUp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.DATA_TABLE__OPERATIONS:
				return basicSetOperations(null, msgs);
			case ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP:
				return basicSetDefaultColSetUp(null, msgs);
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
			case ViewPackage.DATA_TABLE__OPERATIONS:
				return getOperations();
			case ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP:
				return getDefaultColSetUp();
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
			case ViewPackage.DATA_TABLE__OPERATIONS:
				setOperations((OperationComponent)newValue);
				return;
			case ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP:
				setDefaultColSetUp((Col)newValue);
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
			case ViewPackage.DATA_TABLE__OPERATIONS:
				setOperations((OperationComponent)null);
				return;
			case ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP:
				setDefaultColSetUp((Col)null);
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
			case ViewPackage.DATA_TABLE__OPERATIONS:
				return operations != null;
			case ViewPackage.DATA_TABLE__DEFAULT_COL_SET_UP:
				return defaultColSetUp != null;
		}
		return super.eIsSet(featureID);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Actionable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.DATA_TABLE__OPERATIONS: return ViewPackage.ACTIONABLE__OPERATIONS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Actionable.class) {
			switch (baseFeatureID) {
				case ViewPackage.ACTIONABLE__OPERATIONS: return ViewPackage.DATA_TABLE__OPERATIONS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //DataTableImpl
