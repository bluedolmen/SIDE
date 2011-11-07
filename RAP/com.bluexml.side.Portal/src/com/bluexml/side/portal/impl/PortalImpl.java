/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.common.impl.PackageImpl;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Portal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.impl.PortalImpl#getPageSet <em>Page Set</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortalImpl#getLayoutSet <em>Layout Set</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortalImpl#getPortletSet <em>Portlet Set</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortalImpl#getPortletSetType <em>Portlet Set Type</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortalImpl#getInternalPortletSet <em>Internal Portlet Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortalImpl extends PackageImpl implements Portal {
	/**
	 * The cached value of the '{@link #getPageSet() <em>Page Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Page> pageSet;

	/**
	 * The cached value of the '{@link #getLayoutSet() <em>Layout Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLayoutSet()
	 * @generated
	 * @ordered
	 */
	protected EList<PortalLayout> layoutSet;

	/**
	 * The cached value of the '{@link #getPortletSet() <em>Portlet Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortletSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Portlet> portletSet;

	/**
	 * The cached value of the '{@link #getPortletSetType() <em>Portlet Set Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortletSetType()
	 * @generated
	 * @ordered
	 */
	protected EList<PortletType> portletSetType;

	/**
	 * The cached value of the '{@link #getInternalPortletSet() <em>Internal Portlet Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalPortletSet()
	 * @generated
	 * @ordered
	 */
	protected EList<PortletInternal> internalPortletSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.PORTAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Page> getPageSet() {
		if (pageSet == null) {
			pageSet = new EObjectContainmentEList<Page>(Page.class, this, PortalPackage.PORTAL__PAGE_SET);
		}
		return pageSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortalLayout> getLayoutSet() {
		if (layoutSet == null) {
			layoutSet = new EObjectContainmentEList<PortalLayout>(PortalLayout.class, this, PortalPackage.PORTAL__LAYOUT_SET);
		}
		return layoutSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Portlet> getPortletSet() {
		if (portletSet == null) {
			portletSet = new EObjectContainmentEList<Portlet>(Portlet.class, this, PortalPackage.PORTAL__PORTLET_SET);
		}
		return portletSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortletType> getPortletSetType() {
		if (portletSetType == null) {
			portletSetType = new EObjectContainmentEList<PortletType>(PortletType.class, this, PortalPackage.PORTAL__PORTLET_SET_TYPE);
		}
		return portletSetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortletInternal> getInternalPortletSet() {
		if (internalPortletSet == null) {
			internalPortletSet = new EObjectContainmentEList<PortletInternal>(PortletInternal.class, this, PortalPackage.PORTAL__INTERNAL_PORTLET_SET);
		}
		return internalPortletSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortalPackage.PORTAL__PAGE_SET:
				return ((InternalEList<?>)getPageSet()).basicRemove(otherEnd, msgs);
			case PortalPackage.PORTAL__LAYOUT_SET:
				return ((InternalEList<?>)getLayoutSet()).basicRemove(otherEnd, msgs);
			case PortalPackage.PORTAL__PORTLET_SET:
				return ((InternalEList<?>)getPortletSet()).basicRemove(otherEnd, msgs);
			case PortalPackage.PORTAL__PORTLET_SET_TYPE:
				return ((InternalEList<?>)getPortletSetType()).basicRemove(otherEnd, msgs);
			case PortalPackage.PORTAL__INTERNAL_PORTLET_SET:
				return ((InternalEList<?>)getInternalPortletSet()).basicRemove(otherEnd, msgs);
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
			case PortalPackage.PORTAL__PAGE_SET:
				return getPageSet();
			case PortalPackage.PORTAL__LAYOUT_SET:
				return getLayoutSet();
			case PortalPackage.PORTAL__PORTLET_SET:
				return getPortletSet();
			case PortalPackage.PORTAL__PORTLET_SET_TYPE:
				return getPortletSetType();
			case PortalPackage.PORTAL__INTERNAL_PORTLET_SET:
				return getInternalPortletSet();
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
			case PortalPackage.PORTAL__PAGE_SET:
				getPageSet().clear();
				getPageSet().addAll((Collection<? extends Page>)newValue);
				return;
			case PortalPackage.PORTAL__LAYOUT_SET:
				getLayoutSet().clear();
				getLayoutSet().addAll((Collection<? extends PortalLayout>)newValue);
				return;
			case PortalPackage.PORTAL__PORTLET_SET:
				getPortletSet().clear();
				getPortletSet().addAll((Collection<? extends Portlet>)newValue);
				return;
			case PortalPackage.PORTAL__PORTLET_SET_TYPE:
				getPortletSetType().clear();
				getPortletSetType().addAll((Collection<? extends PortletType>)newValue);
				return;
			case PortalPackage.PORTAL__INTERNAL_PORTLET_SET:
				getInternalPortletSet().clear();
				getInternalPortletSet().addAll((Collection<? extends PortletInternal>)newValue);
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
			case PortalPackage.PORTAL__PAGE_SET:
				getPageSet().clear();
				return;
			case PortalPackage.PORTAL__LAYOUT_SET:
				getLayoutSet().clear();
				return;
			case PortalPackage.PORTAL__PORTLET_SET:
				getPortletSet().clear();
				return;
			case PortalPackage.PORTAL__PORTLET_SET_TYPE:
				getPortletSetType().clear();
				return;
			case PortalPackage.PORTAL__INTERNAL_PORTLET_SET:
				getInternalPortletSet().clear();
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
			case PortalPackage.PORTAL__PAGE_SET:
				return pageSet != null && !pageSet.isEmpty();
			case PortalPackage.PORTAL__LAYOUT_SET:
				return layoutSet != null && !layoutSet.isEmpty();
			case PortalPackage.PORTAL__PORTLET_SET:
				return portletSet != null && !portletSet.isEmpty();
			case PortalPackage.PORTAL__PORTLET_SET_TYPE:
				return portletSetType != null && !portletSetType.isEmpty();
			case PortalPackage.PORTAL__INTERNAL_PORTLET_SET:
				return internalPortletSet != null && !internalPortletSet.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //PortalImpl
