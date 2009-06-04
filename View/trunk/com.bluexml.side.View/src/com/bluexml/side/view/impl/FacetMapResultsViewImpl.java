/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;

import com.bluexml.side.view.FacetMap;
import com.bluexml.side.view.FacetMapResultsView;
import com.bluexml.side.view.ViewPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facet Map Results View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapResultsViewImpl#getFacetMapDef <em>Facet Map Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FacetMapResultsViewImpl extends DataListImpl implements FacetMapResultsView {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FacetMapResultsViewImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.FACET_MAP_RESULTS_VIEW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacetMap getFacetMapDef() {
		if (eContainerFeatureID != ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF) return null;
		return (FacetMap)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFacetMapDef(FacetMap newFacetMapDef, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFacetMapDef, ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacetMapDef(FacetMap newFacetMapDef) {
		if (newFacetMapDef != eInternalContainer() || (eContainerFeatureID != ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF && newFacetMapDef != null)) {
			if (EcoreUtil.isAncestor(this, newFacetMapDef))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFacetMapDef != null)
				msgs = ((InternalEObject)newFacetMapDef).eInverseAdd(this, ViewPackage.FACET_MAP__RESULTS_VIEWER, FacetMap.class, msgs);
			msgs = basicSetFacetMapDef(newFacetMapDef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF, newFacetMapDef, newFacetMapDef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFacetMapDef((FacetMap)otherEnd, msgs);
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
			case ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF:
				return basicSetFacetMapDef(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF:
				return eInternalContainer().eInverseRemove(this, ViewPackage.FACET_MAP__RESULTS_VIEWER, FacetMap.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF:
				return getFacetMapDef();
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
			case ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF:
				setFacetMapDef((FacetMap)newValue);
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
			case ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF:
				setFacetMapDef((FacetMap)null);
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
			case ViewPackage.FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF:
				return getFacetMapDef() != null;
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //FacetMapResultsViewImpl
