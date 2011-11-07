/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.requirements.Agent;
import com.bluexml.side.requirements.Goal;
import com.bluexml.side.requirements.RequirementsPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Agent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.impl.AgentImpl#isIsHuman <em>Is Human</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.AgentImpl#getIsResponsible <em>Is Responsible</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AgentImpl extends AnnotableElementImpl implements Agent {
	/**
	 * The default value of the '{@link #isIsHuman() <em>Is Human</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsHuman()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_HUMAN_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIsHuman() <em>Is Human</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsHuman()
	 * @generated
	 * @ordered
	 */
	protected boolean isHuman = IS_HUMAN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIsResponsible() <em>Is Responsible</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsResponsible()
	 * @generated
	 * @ordered
	 */
	protected EList<Goal> isResponsible;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AgentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementsPackage.Literals.AGENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsHuman() {
		return isHuman;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsHuman(boolean newIsHuman) {
		boolean oldIsHuman = isHuman;
		isHuman = newIsHuman;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.AGENT__IS_HUMAN, oldIsHuman, isHuman));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Goal> getIsResponsible() {
		if (isResponsible == null) {
			isResponsible = new EObjectWithInverseResolvingEList.ManyInverse<Goal>(Goal.class, this, RequirementsPackage.AGENT__IS_RESPONSIBLE, RequirementsPackage.GOAL__RESPONSIBLE);
		}
		return isResponsible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RequirementsPackage.AGENT__IS_RESPONSIBLE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIsResponsible()).basicAdd(otherEnd, msgs);
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
			case RequirementsPackage.AGENT__IS_RESPONSIBLE:
				return ((InternalEList<?>)getIsResponsible()).basicRemove(otherEnd, msgs);
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
			case RequirementsPackage.AGENT__IS_HUMAN:
				return isIsHuman();
			case RequirementsPackage.AGENT__IS_RESPONSIBLE:
				return getIsResponsible();
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
			case RequirementsPackage.AGENT__IS_HUMAN:
				setIsHuman((Boolean)newValue);
				return;
			case RequirementsPackage.AGENT__IS_RESPONSIBLE:
				getIsResponsible().clear();
				getIsResponsible().addAll((Collection<? extends Goal>)newValue);
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
			case RequirementsPackage.AGENT__IS_HUMAN:
				setIsHuman(IS_HUMAN_EDEFAULT);
				return;
			case RequirementsPackage.AGENT__IS_RESPONSIBLE:
				getIsResponsible().clear();
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
			case RequirementsPackage.AGENT__IS_HUMAN:
				return isHuman != IS_HUMAN_EDEFAULT;
			case RequirementsPackage.AGENT__IS_RESPONSIBLE:
				return isResponsible != null && !isResponsible.isEmpty();
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
		result.append(" (isHuman: ");
		result.append(isHuman);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //AgentImpl
