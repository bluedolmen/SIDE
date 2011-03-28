/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract View Of</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.AbstractViewOfImpl#getViewOf <em>View Of</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractViewOfImpl extends AbstractViewImpl implements AbstractViewOf {
	/**
	 * The cached value of the '{@link #getViewOf() <em>View Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewOf()
	 * @generated
	 * @ordered
	 */
	protected AbstractClass viewOf;

	/**
	 * The default value of the '{@link #isGenerateWebscript() <em>Generate Webscript</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGenerateWebscript()
	 * @_generated
	 * @ordered
	 */
	protected static final boolean GENERATE_WEBSCRIPT_EDEFAULT = true;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractViewOfImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.ABSTRACT_VIEW_OF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass getViewOf() {
		if (viewOf != null && viewOf.eIsProxy()) {
			InternalEObject oldViewOf = (InternalEObject)viewOf;
			viewOf = (AbstractClass)eResolveProxy(oldViewOf);
			if (viewOf != oldViewOf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF, oldViewOf, viewOf));
			}
		}
		return viewOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass basicGetViewOf() {
		return viewOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewOf(AbstractClass newViewOf) {
		AbstractClass oldViewOf = viewOf;
		viewOf = newViewOf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF, oldViewOf, viewOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF:
				if (resolve) return getViewOf();
				return basicGetViewOf();
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
			case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF:
				setViewOf((AbstractClass)newValue);
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
			case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF:
				setViewOf((AbstractClass)null);
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
			case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF:
				return viewOf != null;
		}
		return super.eIsSet(featureID);
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //AbstractViewOfImpl
