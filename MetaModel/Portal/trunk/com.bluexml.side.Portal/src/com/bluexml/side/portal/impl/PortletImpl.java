/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.expressions.OCLExpression;
import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Portlet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.impl.PortletImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortletImpl#getIsPortletInternal <em>Is Portlet Internal</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortletImpl#getIsInstanceOfPortletType <em>Is Instance Of Portlet Type</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortletImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.portal.impl.PortletImpl#getSubPortlets <em>Sub Portlets</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortletImpl extends PortalModelElementImpl implements Portlet {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIsPortletInternal() <em>Is Portlet Internal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsPortletInternal()
	 * @generated
	 * @ordered
	 */
	protected PortletInternal isPortletInternal;

	/**
	 * The cached value of the '{@link #getIsInstanceOfPortletType() <em>Is Instance Of Portlet Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsInstanceOfPortletType()
	 * @generated
	 * @ordered
	 */
	protected InstanciatePortletType isInstanceOfPortletType;

	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSubPortlets() <em>Sub Portlets</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubPortlets()
	 * @generated
	 * @ordered
	 */
	protected EList<Portlet> subPortlets;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortletImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.PORTLET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortletInternal getIsPortletInternal() {
		if (isPortletInternal != null && isPortletInternal.eIsProxy()) {
			InternalEObject oldIsPortletInternal = (InternalEObject)isPortletInternal;
			isPortletInternal = (PortletInternal)eResolveProxy(oldIsPortletInternal);
			if (isPortletInternal != oldIsPortletInternal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortalPackage.PORTLET__IS_PORTLET_INTERNAL, oldIsPortletInternal, isPortletInternal));
			}
		}
		return isPortletInternal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortletInternal basicGetIsPortletInternal() {
		return isPortletInternal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsPortletInternal(PortletInternal newIsPortletInternal) {
		PortletInternal oldIsPortletInternal = isPortletInternal;
		isPortletInternal = newIsPortletInternal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET__IS_PORTLET_INTERNAL, oldIsPortletInternal, isPortletInternal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanciatePortletType getIsInstanceOfPortletType() {
		return isInstanceOfPortletType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIsInstanceOfPortletType(InstanciatePortletType newIsInstanceOfPortletType, NotificationChain msgs) {
		InstanciatePortletType oldIsInstanceOfPortletType = isInstanceOfPortletType;
		isInstanceOfPortletType = newIsInstanceOfPortletType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET__IS_INSTANCE_OF_PORTLET_TYPE, oldIsInstanceOfPortletType, newIsInstanceOfPortletType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsInstanceOfPortletType(InstanciatePortletType newIsInstanceOfPortletType) {
		if (newIsInstanceOfPortletType != isInstanceOfPortletType) {
			NotificationChain msgs = null;
			if (isInstanceOfPortletType != null)
				msgs = ((InternalEObject)isInstanceOfPortletType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PortalPackage.PORTLET__IS_INSTANCE_OF_PORTLET_TYPE, null, msgs);
			if (newIsInstanceOfPortletType != null)
				msgs = ((InternalEObject)newIsInstanceOfPortletType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PortalPackage.PORTLET__IS_INSTANCE_OF_PORTLET_TYPE, null, msgs);
			msgs = basicSetIsInstanceOfPortletType(newIsInstanceOfPortletType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET__IS_INSTANCE_OF_PORTLET_TYPE, newIsInstanceOfPortletType, newIsInstanceOfPortletType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortalPackage.PORTLET__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Portlet> getSubPortlets() {
		if (subPortlets == null) {
			subPortlets = new EObjectResolvingEList<Portlet>(Portlet.class, this, PortalPackage.PORTLET__SUB_PORTLETS);
		}
		return subPortlets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		if (getLabelBodyOCL == null) {
			EOperation eOperation = PortalPackage.Literals.PORTLET.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(PortalPackage.Literals.PORTLET, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getLabelBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getLabelBodyOCL);
	
		return (String) query.evaluate(this);
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getLabel <em>Get Label</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel
	 * @generated
	 */
	private static OCLExpression<EClassifier> getLabelBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortalPackage.PORTLET__IS_INSTANCE_OF_PORTLET_TYPE:
				return basicSetIsInstanceOfPortletType(null, msgs);
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
			case PortalPackage.PORTLET__NAME:
				return getName();
			case PortalPackage.PORTLET__IS_PORTLET_INTERNAL:
				if (resolve) return getIsPortletInternal();
				return basicGetIsPortletInternal();
			case PortalPackage.PORTLET__IS_INSTANCE_OF_PORTLET_TYPE:
				return getIsInstanceOfPortletType();
			case PortalPackage.PORTLET__TITLE:
				return getTitle();
			case PortalPackage.PORTLET__SUB_PORTLETS:
				return getSubPortlets();
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
			case PortalPackage.PORTLET__NAME:
				setName((String)newValue);
				return;
			case PortalPackage.PORTLET__IS_PORTLET_INTERNAL:
				setIsPortletInternal((PortletInternal)newValue);
				return;
			case PortalPackage.PORTLET__IS_INSTANCE_OF_PORTLET_TYPE:
				setIsInstanceOfPortletType((InstanciatePortletType)newValue);
				return;
			case PortalPackage.PORTLET__TITLE:
				setTitle((String)newValue);
				return;
			case PortalPackage.PORTLET__SUB_PORTLETS:
				getSubPortlets().clear();
				getSubPortlets().addAll((Collection<? extends Portlet>)newValue);
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
			case PortalPackage.PORTLET__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PortalPackage.PORTLET__IS_PORTLET_INTERNAL:
				setIsPortletInternal((PortletInternal)null);
				return;
			case PortalPackage.PORTLET__IS_INSTANCE_OF_PORTLET_TYPE:
				setIsInstanceOfPortletType((InstanciatePortletType)null);
				return;
			case PortalPackage.PORTLET__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case PortalPackage.PORTLET__SUB_PORTLETS:
				getSubPortlets().clear();
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
			case PortalPackage.PORTLET__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PortalPackage.PORTLET__IS_PORTLET_INTERNAL:
				return isPortletInternal != null;
			case PortalPackage.PORTLET__IS_INSTANCE_OF_PORTLET_TYPE:
				return isInstanceOfPortletType != null;
			case PortalPackage.PORTLET__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case PortalPackage.PORTLET__SUB_PORTLETS:
				return subPortlets != null && !subPortlets.isEmpty();
		}
		return super.eIsSet(featureID);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", title: ");
		result.append(title);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //PortletImpl
