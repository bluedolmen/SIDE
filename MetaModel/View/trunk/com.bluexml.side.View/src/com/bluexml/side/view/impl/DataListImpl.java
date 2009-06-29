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
import com.bluexml.side.view.Col;
import com.bluexml.side.view.DataList;
import com.bluexml.side.view.Editable;
import com.bluexml.side.view.Filterable;
import com.bluexml.side.view.Filtering;
import com.bluexml.side.view.Movable;
import com.bluexml.side.view.Sortable;
import com.bluexml.side.view.Sorting;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.DataListImpl#isMovable <em>Movable</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.DataListImpl#isEditable <em>Editable</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.DataListImpl#getFiltering <em>Filtering</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.DataListImpl#getSorting <em>Sorting</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.DataListImpl#getActions <em>Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataListImpl extends AbstractDataTableImpl implements DataList {
	/**
	 * The default value of the '{@link #isMovable() <em>Movable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMovable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MOVABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMovable() <em>Movable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMovable()
	 * @generated
	 * @ordered
	 */
	protected boolean movable = MOVABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EDITABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected boolean editable = EDITABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFiltering() <em>Filtering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFiltering()
	 * @generated
	 * @ordered
	 */
	protected Filtering filtering;

	/**
	 * The cached value of the '{@link #getSorting() <em>Sorting</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSorting()
	 * @generated
	 * @ordered
	 */
	protected Sorting sorting;

	/**
	 * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent actions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.DATA_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMovable() {
		return movable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMovable(boolean newMovable) {
		boolean oldMovable = movable;
		movable = newMovable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_LIST__MOVABLE, oldMovable, movable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditable(boolean newEditable) {
		boolean oldEditable = editable;
		editable = newEditable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_LIST__EDITABLE, oldEditable, editable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filtering getFiltering() {
		return filtering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFiltering(Filtering newFiltering, NotificationChain msgs) {
		Filtering oldFiltering = filtering;
		filtering = newFiltering;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_LIST__FILTERING, oldFiltering, newFiltering);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFiltering(Filtering newFiltering) {
		if (newFiltering != filtering) {
			NotificationChain msgs = null;
			if (filtering != null)
				msgs = ((InternalEObject)filtering).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_LIST__FILTERING, null, msgs);
			if (newFiltering != null)
				msgs = ((InternalEObject)newFiltering).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_LIST__FILTERING, null, msgs);
			msgs = basicSetFiltering(newFiltering, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_LIST__FILTERING, newFiltering, newFiltering));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sorting getSorting() {
		return sorting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSorting(Sorting newSorting, NotificationChain msgs) {
		Sorting oldSorting = sorting;
		sorting = newSorting;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_LIST__SORTING, oldSorting, newSorting);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSorting(Sorting newSorting) {
		if (newSorting != sorting) {
			NotificationChain msgs = null;
			if (sorting != null)
				msgs = ((InternalEObject)sorting).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_LIST__SORTING, null, msgs);
			if (newSorting != null)
				msgs = ((InternalEObject)newSorting).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_LIST__SORTING, null, msgs);
			msgs = basicSetSorting(newSorting, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_LIST__SORTING, newSorting, newSorting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getActions() {
		return actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActions(OperationComponent newActions, NotificationChain msgs) {
		OperationComponent oldActions = actions;
		actions = newActions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_LIST__ACTIONS, oldActions, newActions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActions(OperationComponent newActions) {
		if (newActions != actions) {
			NotificationChain msgs = null;
			if (actions != null)
				msgs = ((InternalEObject)actions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_LIST__ACTIONS, null, msgs);
			if (newActions != null)
				msgs = ((InternalEObject)newActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.DATA_LIST__ACTIONS, null, msgs);
			msgs = basicSetActions(newActions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.DATA_LIST__ACTIONS, newActions, newActions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.DATA_LIST__FILTERING:
				return basicSetFiltering(null, msgs);
			case ViewPackage.DATA_LIST__SORTING:
				return basicSetSorting(null, msgs);
			case ViewPackage.DATA_LIST__ACTIONS:
				return basicSetActions(null, msgs);
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
			case ViewPackage.DATA_LIST__MOVABLE:
				return isMovable() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.DATA_LIST__EDITABLE:
				return isEditable() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.DATA_LIST__FILTERING:
				return getFiltering();
			case ViewPackage.DATA_LIST__SORTING:
				return getSorting();
			case ViewPackage.DATA_LIST__ACTIONS:
				return getActions();
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
			case ViewPackage.DATA_LIST__MOVABLE:
				setMovable(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.DATA_LIST__EDITABLE:
				setEditable(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.DATA_LIST__FILTERING:
				setFiltering((Filtering)newValue);
				return;
			case ViewPackage.DATA_LIST__SORTING:
				setSorting((Sorting)newValue);
				return;
			case ViewPackage.DATA_LIST__ACTIONS:
				setActions((OperationComponent)newValue);
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
			case ViewPackage.DATA_LIST__MOVABLE:
				setMovable(MOVABLE_EDEFAULT);
				return;
			case ViewPackage.DATA_LIST__EDITABLE:
				setEditable(EDITABLE_EDEFAULT);
				return;
			case ViewPackage.DATA_LIST__FILTERING:
				setFiltering((Filtering)null);
				return;
			case ViewPackage.DATA_LIST__SORTING:
				setSorting((Sorting)null);
				return;
			case ViewPackage.DATA_LIST__ACTIONS:
				setActions((OperationComponent)null);
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
			case ViewPackage.DATA_LIST__MOVABLE:
				return movable != MOVABLE_EDEFAULT;
			case ViewPackage.DATA_LIST__EDITABLE:
				return editable != EDITABLE_EDEFAULT;
			case ViewPackage.DATA_LIST__FILTERING:
				return filtering != null;
			case ViewPackage.DATA_LIST__SORTING:
				return sorting != null;
			case ViewPackage.DATA_LIST__ACTIONS:
				return actions != null;
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
		if (baseClass == Movable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.DATA_LIST__MOVABLE: return ViewPackage.MOVABLE__MOVABLE;
				default: return -1;
			}
		}
		if (baseClass == Editable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.DATA_LIST__EDITABLE: return ViewPackage.EDITABLE__EDITABLE;
				default: return -1;
			}
		}
		if (baseClass == Filterable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.DATA_LIST__FILTERING: return ViewPackage.FILTERABLE__FILTERING;
				default: return -1;
			}
		}
		if (baseClass == Sortable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.DATA_LIST__SORTING: return ViewPackage.SORTABLE__SORTING;
				default: return -1;
			}
		}
		if (baseClass == Col.class) {
			switch (derivedFeatureID) {
				case ViewPackage.DATA_LIST__ACTIONS: return ViewPackage.COL__ACTIONS;
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
		if (baseClass == Movable.class) {
			switch (baseFeatureID) {
				case ViewPackage.MOVABLE__MOVABLE: return ViewPackage.DATA_LIST__MOVABLE;
				default: return -1;
			}
		}
		if (baseClass == Editable.class) {
			switch (baseFeatureID) {
				case ViewPackage.EDITABLE__EDITABLE: return ViewPackage.DATA_LIST__EDITABLE;
				default: return -1;
			}
		}
		if (baseClass == Filterable.class) {
			switch (baseFeatureID) {
				case ViewPackage.FILTERABLE__FILTERING: return ViewPackage.DATA_LIST__FILTERING;
				default: return -1;
			}
		}
		if (baseClass == Sortable.class) {
			switch (baseFeatureID) {
				case ViewPackage.SORTABLE__SORTING: return ViewPackage.DATA_LIST__SORTING;
				default: return -1;
			}
		}
		if (baseClass == Col.class) {
			switch (baseFeatureID) {
				case ViewPackage.COL__ACTIONS: return ViewPackage.DATA_LIST__ACTIONS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (movable: ");
		result.append(movable);
		result.append(", editable: ");
		result.append(editable);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //DataListImpl
