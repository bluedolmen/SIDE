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
import com.bluexml.side.view.Actionable;
import com.bluexml.side.view.FacetDisplayType;
import com.bluexml.side.view.FacetMap;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.Paginable;
import com.bluexml.side.view.Paging;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facet Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getPaging <em>Paging</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#isDisplayEmptyFacet <em>Display Empty Facet</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getFacetDisplayType <em>Facet Display Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FacetMapImpl extends AbstractViewOfImpl implements FacetMap {
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
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent operations;

	/**
	 * The default value of the '{@link #isDisplayEmptyFacet() <em>Display Empty Facet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisplayEmptyFacet()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISPLAY_EMPTY_FACET_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDisplayEmptyFacet() <em>Display Empty Facet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisplayEmptyFacet()
	 * @generated
	 * @ordered
	 */
	protected boolean displayEmptyFacet = DISPLAY_EMPTY_FACET_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacetDisplayType() <em>Facet Display Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacetDisplayType()
	 * @generated
	 * @ordered
	 */
	protected static final FacetDisplayType FACET_DISPLAY_TYPE_EDEFAULT = FacetDisplayType.LIST;

	/**
	 * The cached value of the '{@link #getFacetDisplayType() <em>Facet Display Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacetDisplayType()
	 * @generated
	 * @ordered
	 */
	protected FacetDisplayType facetDisplayType = FACET_DISPLAY_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FacetMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.FACET_MAP;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__PAGING, oldPaging, newPaging);
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
				msgs = ((InternalEObject)paging).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__PAGING, null, msgs);
			if (newPaging != null)
				msgs = ((InternalEObject)newPaging).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__PAGING, null, msgs);
			msgs = basicSetPaging(newPaging, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__PAGING, newPaging, newPaging));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__OPERATIONS, oldOperations, newOperations);
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
				msgs = ((InternalEObject)operations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__OPERATIONS, null, msgs);
			if (newOperations != null)
				msgs = ((InternalEObject)newOperations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__OPERATIONS, null, msgs);
			msgs = basicSetOperations(newOperations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__OPERATIONS, newOperations, newOperations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDisplayEmptyFacet() {
		return displayEmptyFacet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayEmptyFacet(boolean newDisplayEmptyFacet) {
		boolean oldDisplayEmptyFacet = displayEmptyFacet;
		displayEmptyFacet = newDisplayEmptyFacet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET, oldDisplayEmptyFacet, displayEmptyFacet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacetDisplayType getFacetDisplayType() {
		return facetDisplayType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacetDisplayType(FacetDisplayType newFacetDisplayType) {
		FacetDisplayType oldFacetDisplayType = facetDisplayType;
		facetDisplayType = newFacetDisplayType == null ? FACET_DISPLAY_TYPE_EDEFAULT : newFacetDisplayType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE, oldFacetDisplayType, facetDisplayType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldElement> getResultsAttributes() {
		if (getResultsAttributesBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.FACET_MAP.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.FACET_MAP, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getResultsAttributesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getResultsAttributesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<FieldElement> result = (Collection<FieldElement>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<FieldElement>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getResultsAttributes <em>Get Results Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultsAttributes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getResultsAttributesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.FACET_MAP__PAGING:
				return basicSetPaging(null, msgs);
			case ViewPackage.FACET_MAP__OPERATIONS:
				return basicSetOperations(null, msgs);
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
			case ViewPackage.FACET_MAP__PAGING:
				return getPaging();
			case ViewPackage.FACET_MAP__OPERATIONS:
				return getOperations();
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				return isDisplayEmptyFacet();
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				return getFacetDisplayType();
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
			case ViewPackage.FACET_MAP__PAGING:
				setPaging((Paging)newValue);
				return;
			case ViewPackage.FACET_MAP__OPERATIONS:
				setOperations((OperationComponent)newValue);
				return;
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				setDisplayEmptyFacet((Boolean)newValue);
				return;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				setFacetDisplayType((FacetDisplayType)newValue);
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
			case ViewPackage.FACET_MAP__PAGING:
				setPaging((Paging)null);
				return;
			case ViewPackage.FACET_MAP__OPERATIONS:
				setOperations((OperationComponent)null);
				return;
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				setDisplayEmptyFacet(DISPLAY_EMPTY_FACET_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				setFacetDisplayType(FACET_DISPLAY_TYPE_EDEFAULT);
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
			case ViewPackage.FACET_MAP__PAGING:
				return paging != null;
			case ViewPackage.FACET_MAP__OPERATIONS:
				return operations != null;
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				return displayEmptyFacet != DISPLAY_EMPTY_FACET_EDEFAULT;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				return facetDisplayType != FACET_DISPLAY_TYPE_EDEFAULT;
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
		if (baseClass == Paginable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FACET_MAP__PAGING: return ViewPackage.PAGINABLE__PAGING;
				default: return -1;
			}
		}
		if (baseClass == Actionable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FACET_MAP__OPERATIONS: return ViewPackage.ACTIONABLE__OPERATIONS;
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
		if (baseClass == Paginable.class) {
			switch (baseFeatureID) {
				case ViewPackage.PAGINABLE__PAGING: return ViewPackage.FACET_MAP__PAGING;
				default: return -1;
			}
		}
		if (baseClass == Actionable.class) {
			switch (baseFeatureID) {
				case ViewPackage.ACTIONABLE__OPERATIONS: return ViewPackage.FACET_MAP__OPERATIONS;
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
		result.append(" (displayEmptyFacet: ");
		result.append(displayEmptyFacet);
		result.append(", facetDisplayType: ");
		result.append(facetDisplayType);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //FacetMapImpl
