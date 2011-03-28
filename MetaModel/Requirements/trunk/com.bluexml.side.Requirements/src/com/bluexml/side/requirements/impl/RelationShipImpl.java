/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.RelationShip;
import com.bluexml.side.requirements.RequirementsPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relation Ship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.impl.RelationShipImpl#getSource <em>Source</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.RelationShipImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.RelationShipImpl#getSourceMin <em>Source Min</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.RelationShipImpl#getSourceMax <em>Source Max</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.RelationShipImpl#getTargetMin <em>Target Min</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.impl.RelationShipImpl#getTargetMax <em>Target Max</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RelationShipImpl extends BasicElementImpl implements RelationShip {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Entity source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Entity target;

	/**
	 * The default value of the '{@link #getSourceMin() <em>Source Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceMin()
	 * @generated
	 * @ordered
	 */
	protected static final int SOURCE_MIN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSourceMin() <em>Source Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceMin()
	 * @generated
	 * @ordered
	 */
	protected int sourceMin = SOURCE_MIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceMax() <em>Source Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceMax()
	 * @generated
	 * @ordered
	 */
	protected static final int SOURCE_MAX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSourceMax() <em>Source Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceMax()
	 * @generated
	 * @ordered
	 */
	protected int sourceMax = SOURCE_MAX_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetMin() <em>Target Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetMin()
	 * @generated
	 * @ordered
	 */
	protected static final int TARGET_MIN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTargetMin() <em>Target Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetMin()
	 * @generated
	 * @ordered
	 */
	protected int targetMin = TARGET_MIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetMax() <em>Target Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetMax()
	 * @generated
	 * @ordered
	 */
	protected static final int TARGET_MAX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTargetMax() <em>Target Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetMax()
	 * @generated
	 * @ordered
	 */
	protected int targetMax = TARGET_MAX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationShipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementsPackage.Literals.RELATION_SHIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Entity)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RequirementsPackage.RELATION_SHIP__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Entity newSource) {
		Entity oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.RELATION_SHIP__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Entity)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RequirementsPackage.RELATION_SHIP__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Entity newTarget) {
		Entity oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.RELATION_SHIP__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSourceMin() {
		return sourceMin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceMin(int newSourceMin) {
		int oldSourceMin = sourceMin;
		sourceMin = newSourceMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.RELATION_SHIP__SOURCE_MIN, oldSourceMin, sourceMin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSourceMax() {
		return sourceMax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceMax(int newSourceMax) {
		int oldSourceMax = sourceMax;
		sourceMax = newSourceMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.RELATION_SHIP__SOURCE_MAX, oldSourceMax, sourceMax));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTargetMin() {
		return targetMin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetMin(int newTargetMin) {
		int oldTargetMin = targetMin;
		targetMin = newTargetMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.RELATION_SHIP__TARGET_MIN, oldTargetMin, targetMin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTargetMax() {
		return targetMax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetMax(int newTargetMax) {
		int oldTargetMax = targetMax;
		targetMax = newTargetMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementsPackage.RELATION_SHIP__TARGET_MAX, oldTargetMax, targetMax));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RequirementsPackage.RELATION_SHIP__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case RequirementsPackage.RELATION_SHIP__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case RequirementsPackage.RELATION_SHIP__SOURCE_MIN:
				return getSourceMin();
			case RequirementsPackage.RELATION_SHIP__SOURCE_MAX:
				return getSourceMax();
			case RequirementsPackage.RELATION_SHIP__TARGET_MIN:
				return getTargetMin();
			case RequirementsPackage.RELATION_SHIP__TARGET_MAX:
				return getTargetMax();
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
			case RequirementsPackage.RELATION_SHIP__SOURCE:
				setSource((Entity)newValue);
				return;
			case RequirementsPackage.RELATION_SHIP__TARGET:
				setTarget((Entity)newValue);
				return;
			case RequirementsPackage.RELATION_SHIP__SOURCE_MIN:
				setSourceMin((Integer)newValue);
				return;
			case RequirementsPackage.RELATION_SHIP__SOURCE_MAX:
				setSourceMax((Integer)newValue);
				return;
			case RequirementsPackage.RELATION_SHIP__TARGET_MIN:
				setTargetMin((Integer)newValue);
				return;
			case RequirementsPackage.RELATION_SHIP__TARGET_MAX:
				setTargetMax((Integer)newValue);
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
			case RequirementsPackage.RELATION_SHIP__SOURCE:
				setSource((Entity)null);
				return;
			case RequirementsPackage.RELATION_SHIP__TARGET:
				setTarget((Entity)null);
				return;
			case RequirementsPackage.RELATION_SHIP__SOURCE_MIN:
				setSourceMin(SOURCE_MIN_EDEFAULT);
				return;
			case RequirementsPackage.RELATION_SHIP__SOURCE_MAX:
				setSourceMax(SOURCE_MAX_EDEFAULT);
				return;
			case RequirementsPackage.RELATION_SHIP__TARGET_MIN:
				setTargetMin(TARGET_MIN_EDEFAULT);
				return;
			case RequirementsPackage.RELATION_SHIP__TARGET_MAX:
				setTargetMax(TARGET_MAX_EDEFAULT);
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
			case RequirementsPackage.RELATION_SHIP__SOURCE:
				return source != null;
			case RequirementsPackage.RELATION_SHIP__TARGET:
				return target != null;
			case RequirementsPackage.RELATION_SHIP__SOURCE_MIN:
				return sourceMin != SOURCE_MIN_EDEFAULT;
			case RequirementsPackage.RELATION_SHIP__SOURCE_MAX:
				return sourceMax != SOURCE_MAX_EDEFAULT;
			case RequirementsPackage.RELATION_SHIP__TARGET_MIN:
				return targetMin != TARGET_MIN_EDEFAULT;
			case RequirementsPackage.RELATION_SHIP__TARGET_MAX:
				return targetMax != TARGET_MAX_EDEFAULT;
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
		result.append(" (sourceMin: ");
		result.append(sourceMin);
		result.append(", sourceMax: ");
		result.append(sourceMax);
		result.append(", targetMin: ");
		result.append(targetMin);
		result.append(", targetMax: ");
		result.append(targetMax);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //RelationShipImpl
