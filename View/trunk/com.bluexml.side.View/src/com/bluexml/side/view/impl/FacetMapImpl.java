/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;

import com.bluexml.side.view.FacetDisplayType;
import com.bluexml.side.view.FacetMap;
import com.bluexml.side.view.FacetMapResultsView;
import com.bluexml.side.view.FieldGroup;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.Paginable;
import com.bluexml.side.view.Paging;
import com.bluexml.side.view.ViewPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facet Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getPaging <em>Paging</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getCriterias <em>Criterias</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#isDisplayEmptyFacet <em>Display Empty Facet</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getFacetDisplayType <em>Facet Display Type</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getResultsViewer <em>Results Viewer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FacetMapImpl extends AbstractViewImpl implements FacetMap {
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
	 * The cached value of the '{@link #getCriterias() <em>Criterias</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCriterias()
	 * @generated
	 * @ordered
	 */
	protected FieldGroup criterias;

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
	 * The cached value of the '{@link #getResultsViewer() <em>Results Viewer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultsViewer()
	 * @generated
	 * @ordered
	 */
	protected FacetMapResultsView resultsViewer;

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
	public FieldGroup getCriterias() {
		return criterias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCriterias(FieldGroup newCriterias, NotificationChain msgs) {
		FieldGroup oldCriterias = criterias;
		criterias = newCriterias;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__CRITERIAS, oldCriterias, newCriterias);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCriterias(FieldGroup newCriterias) {
		if (newCriterias != criterias) {
			NotificationChain msgs = null;
			if (criterias != null)
				msgs = ((InternalEObject)criterias).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__CRITERIAS, null, msgs);
			if (newCriterias != null)
				msgs = ((InternalEObject)newCriterias).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__CRITERIAS, null, msgs);
			msgs = basicSetCriterias(newCriterias, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__CRITERIAS, newCriterias, newCriterias));
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
	public FacetMapResultsView getResultsViewer() {
		return resultsViewer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResultsViewer(FacetMapResultsView newResultsViewer, NotificationChain msgs) {
		FacetMapResultsView oldResultsViewer = resultsViewer;
		resultsViewer = newResultsViewer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__RESULTS_VIEWER, oldResultsViewer, newResultsViewer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultsViewer(FacetMapResultsView newResultsViewer) {
		if (newResultsViewer != resultsViewer) {
			NotificationChain msgs = null;
			if (resultsViewer != null)
				msgs = ((InternalEObject)resultsViewer).eInverseRemove(this, ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF, FacetMapResultsView.class, msgs);
			if (newResultsViewer != null)
				msgs = ((InternalEObject)newResultsViewer).eInverseAdd(this, ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF, FacetMapResultsView.class, msgs);
			msgs = basicSetResultsViewer(newResultsViewer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__RESULTS_VIEWER, newResultsViewer, newResultsViewer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.FACET_MAP__RESULTS_VIEWER:
				if (resultsViewer != null)
					msgs = ((InternalEObject)resultsViewer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__RESULTS_VIEWER, null, msgs);
				return basicSetResultsViewer((FacetMapResultsView)otherEnd, msgs);
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
			case ViewPackage.FACET_MAP__PAGING:
				return basicSetPaging(null, msgs);
			case ViewPackage.FACET_MAP__CRITERIAS:
				return basicSetCriterias(null, msgs);
			case ViewPackage.FACET_MAP__RESULTS_VIEWER:
				return basicSetResultsViewer(null, msgs);
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
			case ViewPackage.FACET_MAP__CRITERIAS:
				return getCriterias();
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				return isDisplayEmptyFacet() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				return getFacetDisplayType();
			case ViewPackage.FACET_MAP__RESULTS_VIEWER:
				return getResultsViewer();
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
			case ViewPackage.FACET_MAP__PAGING:
				setPaging((Paging)newValue);
				return;
			case ViewPackage.FACET_MAP__CRITERIAS:
				setCriterias((FieldGroup)newValue);
				return;
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				setDisplayEmptyFacet(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				setFacetDisplayType((FacetDisplayType)newValue);
				return;
			case ViewPackage.FACET_MAP__RESULTS_VIEWER:
				setResultsViewer((FacetMapResultsView)newValue);
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
			case ViewPackage.FACET_MAP__CRITERIAS:
				setCriterias((FieldGroup)null);
				return;
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				setDisplayEmptyFacet(DISPLAY_EMPTY_FACET_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				setFacetDisplayType(FACET_DISPLAY_TYPE_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__RESULTS_VIEWER:
				setResultsViewer((FacetMapResultsView)null);
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
			case ViewPackage.FACET_MAP__CRITERIAS:
				return criterias != null;
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				return displayEmptyFacet != DISPLAY_EMPTY_FACET_EDEFAULT;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				return facetDisplayType != FACET_DISPLAY_TYPE_EDEFAULT;
			case ViewPackage.FACET_MAP__RESULTS_VIEWER:
				return resultsViewer != null;
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
