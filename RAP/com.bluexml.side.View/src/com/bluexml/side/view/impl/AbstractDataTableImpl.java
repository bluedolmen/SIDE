/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.AbstractDataTable;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.DataTableElement;
import com.bluexml.side.view.Paginable;
import com.bluexml.side.view.Paging;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Data Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getPaging <em>Paging</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getHaveRowActions <em>Have Row Actions</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getHaveSelectActions <em>Have Select Actions</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getHaveDefaultColActions <em>Have Default Col Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractDataTableImpl extends AbstractViewOfImpl implements AbstractDataTable {
	/**
	 * The cached value of the '{@link #getPaging() <em>Paging</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaging()
	 * @generated
	 * @ordered
	 */
	protected Paging paging;

	/**
	 * The cached value of the '{@link #getHaveRowActions() <em>Have Row Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHaveRowActions()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent haveRowActions;

	/**
	 * The cached value of the '{@link #getHaveSelectActions() <em>Have Select Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHaveSelectActions()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent haveSelectActions;

	/**
	 * The cached value of the '{@link #getHaveDefaultColActions() <em>Have Default Col Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHaveDefaultColActions()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent haveDefaultColActions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractDataTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.ABSTRACT_DATA_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Paging getPaging() {
		return paging;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPaging(Paging newPaging, NotificationChain msgs) {
		Paging oldPaging = paging;
		paging = newPaging;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__PAGING, oldPaging, newPaging);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPaging(Paging newPaging) {
		if (newPaging != paging) {
			NotificationChain msgs = null;
			if (paging != null)
				msgs = ((InternalEObject)paging).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__PAGING, null, msgs);
			if (newPaging != null)
				msgs = ((InternalEObject)newPaging).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__PAGING, null, msgs);
			msgs = basicSetPaging(newPaging, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__PAGING, newPaging, newPaging));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getHaveRowActions() {
		return haveRowActions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHaveRowActions(OperationComponent newHaveRowActions, NotificationChain msgs) {
		OperationComponent oldHaveRowActions = haveRowActions;
		haveRowActions = newHaveRowActions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS, oldHaveRowActions, newHaveRowActions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHaveRowActions(OperationComponent newHaveRowActions) {
		if (newHaveRowActions != haveRowActions) {
			NotificationChain msgs = null;
			if (haveRowActions != null)
				msgs = ((InternalEObject)haveRowActions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS, null, msgs);
			if (newHaveRowActions != null)
				msgs = ((InternalEObject)newHaveRowActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS, null, msgs);
			msgs = basicSetHaveRowActions(newHaveRowActions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS, newHaveRowActions, newHaveRowActions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getHaveSelectActions() {
		return haveSelectActions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHaveSelectActions(OperationComponent newHaveSelectActions, NotificationChain msgs) {
		OperationComponent oldHaveSelectActions = haveSelectActions;
		haveSelectActions = newHaveSelectActions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS, oldHaveSelectActions, newHaveSelectActions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHaveSelectActions(OperationComponent newHaveSelectActions) {
		if (newHaveSelectActions != haveSelectActions) {
			NotificationChain msgs = null;
			if (haveSelectActions != null)
				msgs = ((InternalEObject)haveSelectActions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS, null, msgs);
			if (newHaveSelectActions != null)
				msgs = ((InternalEObject)newHaveSelectActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS, null, msgs);
			msgs = basicSetHaveSelectActions(newHaveSelectActions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS, newHaveSelectActions, newHaveSelectActions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getHaveDefaultColActions() {
		return haveDefaultColActions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHaveDefaultColActions(OperationComponent newHaveDefaultColActions, NotificationChain msgs) {
		OperationComponent oldHaveDefaultColActions = haveDefaultColActions;
		haveDefaultColActions = newHaveDefaultColActions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS, oldHaveDefaultColActions, newHaveDefaultColActions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHaveDefaultColActions(OperationComponent newHaveDefaultColActions) {
		if (newHaveDefaultColActions != haveDefaultColActions) {
			NotificationChain msgs = null;
			if (haveDefaultColActions != null)
				msgs = ((InternalEObject)haveDefaultColActions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS, null, msgs);
			if (newHaveDefaultColActions != null)
				msgs = ((InternalEObject)newHaveDefaultColActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS, null, msgs);
			msgs = basicSetHaveDefaultColActions(newHaveDefaultColActions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS, newHaveDefaultColActions, newHaveDefaultColActions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Col> getCols() {
		if (getColsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_DATA_TABLE.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_DATA_TABLE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getColsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getColsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Col> result = (Collection<Col>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Col>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getCols <em>Get Cols</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCols
	 * @generated
	 */
	private static OCLExpression<EClassifier> getColsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				return basicSetPaging(null, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				return basicSetHaveRowActions(null, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				return basicSetHaveSelectActions(null, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				return basicSetHaveDefaultColActions(null, msgs);
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
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				return getPaging();
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				return getHaveRowActions();
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				return getHaveSelectActions();
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				return getHaveDefaultColActions();
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
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				setPaging((Paging)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				setHaveRowActions((OperationComponent)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				setHaveSelectActions((OperationComponent)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				setHaveDefaultColActions((OperationComponent)newValue);
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
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				setPaging((Paging)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				setHaveRowActions((OperationComponent)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				setHaveSelectActions((OperationComponent)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				setHaveDefaultColActions((OperationComponent)null);
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
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				return paging != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				return haveRowActions != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				return haveSelectActions != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				return haveDefaultColActions != null;
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
		if (baseClass == DataTableElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Paginable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.ABSTRACT_DATA_TABLE__PAGING: return ViewPackage.PAGINABLE__PAGING;
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
		if (baseClass == DataTableElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Paginable.class) {
			switch (baseFeatureID) {
				case ViewPackage.PAGINABLE__PAGING: return ViewPackage.ABSTRACT_DATA_TABLE__PAGING;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //AbstractDataTableImpl
