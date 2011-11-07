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
import com.bluexml.side.view.Editable;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.Filterable;
import com.bluexml.side.view.Filtering;
import com.bluexml.side.view.Movable;
import com.bluexml.side.view.Sortable;
import com.bluexml.side.view.Sorting;
import com.bluexml.side.view.Tree;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tree</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getSorting <em>Sorting</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#isEditable <em>Editable</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#isMovable <em>Movable</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getFiltering <em>Filtering</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getNodeOperations <em>Node Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getNodeValue <em>Node Value</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getDefaultDepth <em>Default Depth</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getMaxDepth <em>Max Depth</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TreeImpl extends AbstractViewOfImpl implements Tree {
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
	 * The cached value of the '{@link #getFiltering() <em>Filtering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFiltering()
	 * @generated
	 * @ordered
	 */
	protected Filtering filtering;

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
	 * The cached value of the '{@link #getNodeOperations() <em>Node Operations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeOperations()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent nodeOperations;

	/**
	 * The cached value of the '{@link #getNodeValue() <em>Node Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeValue()
	 * @generated
	 * @ordered
	 */
	protected FieldElement nodeValue;

	/**
	 * The default value of the '{@link #getDefaultDepth() <em>Default Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultDepth()
	 * @generated
	 * @ordered
	 */
	protected static final int DEFAULT_DEPTH_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getDefaultDepth() <em>Default Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultDepth()
	 * @generated
	 * @ordered
	 */
	protected int defaultDepth = DEFAULT_DEPTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxDepth() <em>Max Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDepth()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_DEPTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxDepth() <em>Max Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDepth()
	 * @generated
	 * @ordered
	 */
	protected int maxDepth = MAX_DEPTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TreeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.TREE;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__SORTING, oldSorting, newSorting);
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
				msgs = ((InternalEObject)sorting).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__SORTING, null, msgs);
			if (newSorting != null)
				msgs = ((InternalEObject)newSorting).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__SORTING, null, msgs);
			msgs = basicSetSorting(newSorting, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__SORTING, newSorting, newSorting));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__EDITABLE, oldEditable, editable));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__MOVABLE, oldMovable, movable));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__FILTERING, oldFiltering, newFiltering);
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
				msgs = ((InternalEObject)filtering).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__FILTERING, null, msgs);
			if (newFiltering != null)
				msgs = ((InternalEObject)newFiltering).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__FILTERING, null, msgs);
			msgs = basicSetFiltering(newFiltering, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__FILTERING, newFiltering, newFiltering));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__OPERATIONS, oldOperations, newOperations);
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
				msgs = ((InternalEObject)operations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__OPERATIONS, null, msgs);
			if (newOperations != null)
				msgs = ((InternalEObject)newOperations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__OPERATIONS, null, msgs);
			msgs = basicSetOperations(newOperations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__OPERATIONS, newOperations, newOperations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getNodeOperations() {
		return nodeOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNodeOperations(OperationComponent newNodeOperations, NotificationChain msgs) {
		OperationComponent oldNodeOperations = nodeOperations;
		nodeOperations = newNodeOperations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__NODE_OPERATIONS, oldNodeOperations, newNodeOperations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeOperations(OperationComponent newNodeOperations) {
		if (newNodeOperations != nodeOperations) {
			NotificationChain msgs = null;
			if (nodeOperations != null)
				msgs = ((InternalEObject)nodeOperations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__NODE_OPERATIONS, null, msgs);
			if (newNodeOperations != null)
				msgs = ((InternalEObject)newNodeOperations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__NODE_OPERATIONS, null, msgs);
			msgs = basicSetNodeOperations(newNodeOperations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__NODE_OPERATIONS, newNodeOperations, newNodeOperations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldElement getNodeValue() {
		return nodeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNodeValue(FieldElement newNodeValue, NotificationChain msgs) {
		FieldElement oldNodeValue = nodeValue;
		nodeValue = newNodeValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__NODE_VALUE, oldNodeValue, newNodeValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeValue(FieldElement newNodeValue) {
		if (newNodeValue != nodeValue) {
			NotificationChain msgs = null;
			if (nodeValue != null)
				msgs = ((InternalEObject)nodeValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__NODE_VALUE, null, msgs);
			if (newNodeValue != null)
				msgs = ((InternalEObject)newNodeValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__NODE_VALUE, null, msgs);
			msgs = basicSetNodeValue(newNodeValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__NODE_VALUE, newNodeValue, newNodeValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDefaultDepth() {
		return defaultDepth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultDepth(int newDefaultDepth) {
		int oldDefaultDepth = defaultDepth;
		defaultDepth = newDefaultDepth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__DEFAULT_DEPTH, oldDefaultDepth, defaultDepth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxDepth() {
		return maxDepth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxDepth(int newMaxDepth) {
		int oldMaxDepth = maxDepth;
		maxDepth = newMaxDepth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__MAX_DEPTH, oldMaxDepth, maxDepth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.TREE__SORTING:
				return basicSetSorting(null, msgs);
			case ViewPackage.TREE__FILTERING:
				return basicSetFiltering(null, msgs);
			case ViewPackage.TREE__OPERATIONS:
				return basicSetOperations(null, msgs);
			case ViewPackage.TREE__NODE_OPERATIONS:
				return basicSetNodeOperations(null, msgs);
			case ViewPackage.TREE__NODE_VALUE:
				return basicSetNodeValue(null, msgs);
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
			case ViewPackage.TREE__SORTING:
				return getSorting();
			case ViewPackage.TREE__EDITABLE:
				return isEditable();
			case ViewPackage.TREE__MOVABLE:
				return isMovable();
			case ViewPackage.TREE__FILTERING:
				return getFiltering();
			case ViewPackage.TREE__OPERATIONS:
				return getOperations();
			case ViewPackage.TREE__NODE_OPERATIONS:
				return getNodeOperations();
			case ViewPackage.TREE__NODE_VALUE:
				return getNodeValue();
			case ViewPackage.TREE__DEFAULT_DEPTH:
				return getDefaultDepth();
			case ViewPackage.TREE__MAX_DEPTH:
				return getMaxDepth();
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
			case ViewPackage.TREE__SORTING:
				setSorting((Sorting)newValue);
				return;
			case ViewPackage.TREE__EDITABLE:
				setEditable((Boolean)newValue);
				return;
			case ViewPackage.TREE__MOVABLE:
				setMovable((Boolean)newValue);
				return;
			case ViewPackage.TREE__FILTERING:
				setFiltering((Filtering)newValue);
				return;
			case ViewPackage.TREE__OPERATIONS:
				setOperations((OperationComponent)newValue);
				return;
			case ViewPackage.TREE__NODE_OPERATIONS:
				setNodeOperations((OperationComponent)newValue);
				return;
			case ViewPackage.TREE__NODE_VALUE:
				setNodeValue((FieldElement)newValue);
				return;
			case ViewPackage.TREE__DEFAULT_DEPTH:
				setDefaultDepth((Integer)newValue);
				return;
			case ViewPackage.TREE__MAX_DEPTH:
				setMaxDepth((Integer)newValue);
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
			case ViewPackage.TREE__SORTING:
				setSorting((Sorting)null);
				return;
			case ViewPackage.TREE__EDITABLE:
				setEditable(EDITABLE_EDEFAULT);
				return;
			case ViewPackage.TREE__MOVABLE:
				setMovable(MOVABLE_EDEFAULT);
				return;
			case ViewPackage.TREE__FILTERING:
				setFiltering((Filtering)null);
				return;
			case ViewPackage.TREE__OPERATIONS:
				setOperations((OperationComponent)null);
				return;
			case ViewPackage.TREE__NODE_OPERATIONS:
				setNodeOperations((OperationComponent)null);
				return;
			case ViewPackage.TREE__NODE_VALUE:
				setNodeValue((FieldElement)null);
				return;
			case ViewPackage.TREE__DEFAULT_DEPTH:
				setDefaultDepth(DEFAULT_DEPTH_EDEFAULT);
				return;
			case ViewPackage.TREE__MAX_DEPTH:
				setMaxDepth(MAX_DEPTH_EDEFAULT);
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
			case ViewPackage.TREE__SORTING:
				return sorting != null;
			case ViewPackage.TREE__EDITABLE:
				return editable != EDITABLE_EDEFAULT;
			case ViewPackage.TREE__MOVABLE:
				return movable != MOVABLE_EDEFAULT;
			case ViewPackage.TREE__FILTERING:
				return filtering != null;
			case ViewPackage.TREE__OPERATIONS:
				return operations != null;
			case ViewPackage.TREE__NODE_OPERATIONS:
				return nodeOperations != null;
			case ViewPackage.TREE__NODE_VALUE:
				return nodeValue != null;
			case ViewPackage.TREE__DEFAULT_DEPTH:
				return defaultDepth != DEFAULT_DEPTH_EDEFAULT;
			case ViewPackage.TREE__MAX_DEPTH:
				return maxDepth != MAX_DEPTH_EDEFAULT;
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
		if (baseClass == Sortable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__SORTING: return ViewPackage.SORTABLE__SORTING;
				default: return -1;
			}
		}
		if (baseClass == Editable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__EDITABLE: return ViewPackage.EDITABLE__EDITABLE;
				default: return -1;
			}
		}
		if (baseClass == Movable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__MOVABLE: return ViewPackage.MOVABLE__MOVABLE;
				default: return -1;
			}
		}
		if (baseClass == Filterable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__FILTERING: return ViewPackage.FILTERABLE__FILTERING;
				default: return -1;
			}
		}
		if (baseClass == Actionable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__OPERATIONS: return ViewPackage.ACTIONABLE__OPERATIONS;
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
		if (baseClass == Sortable.class) {
			switch (baseFeatureID) {
				case ViewPackage.SORTABLE__SORTING: return ViewPackage.TREE__SORTING;
				default: return -1;
			}
		}
		if (baseClass == Editable.class) {
			switch (baseFeatureID) {
				case ViewPackage.EDITABLE__EDITABLE: return ViewPackage.TREE__EDITABLE;
				default: return -1;
			}
		}
		if (baseClass == Movable.class) {
			switch (baseFeatureID) {
				case ViewPackage.MOVABLE__MOVABLE: return ViewPackage.TREE__MOVABLE;
				default: return -1;
			}
		}
		if (baseClass == Filterable.class) {
			switch (baseFeatureID) {
				case ViewPackage.FILTERABLE__FILTERING: return ViewPackage.TREE__FILTERING;
				default: return -1;
			}
		}
		if (baseClass == Actionable.class) {
			switch (baseFeatureID) {
				case ViewPackage.ACTIONABLE__OPERATIONS: return ViewPackage.TREE__OPERATIONS;
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
		result.append(" (editable: ");
		result.append(editable);
		result.append(", movable: ");
		result.append(movable);
		result.append(", defaultDepth: ");
		result.append(defaultDepth);
		result.append(", maxDepth: ");
		result.append(maxDepth);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //TreeImpl
