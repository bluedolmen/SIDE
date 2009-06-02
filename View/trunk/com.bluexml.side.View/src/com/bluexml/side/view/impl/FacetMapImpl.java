/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;

import com.bluexml.side.view.FacetMap;
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
	protected FieldElement criterias;

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
	public FieldElement getCriterias() {
		return criterias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCriterias(FieldElement newCriterias, NotificationChain msgs) {
		FieldElement oldCriterias = criterias;
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
	public void setCriterias(FieldElement newCriterias) {
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.FACET_MAP__PAGING:
				return basicSetPaging(null, msgs);
			case ViewPackage.FACET_MAP__CRITERIAS:
				return basicSetCriterias(null, msgs);
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
				setCriterias((FieldElement)newValue);
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
				setCriterias((FieldElement)null);
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

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //FacetMapImpl
