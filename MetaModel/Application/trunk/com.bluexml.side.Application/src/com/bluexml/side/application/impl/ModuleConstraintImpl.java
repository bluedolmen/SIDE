/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.ModuleConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.application.impl.ModuleConstraintImpl#getModuleId <em>Module Id</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ModuleConstraintImpl#getVersionMin <em>Version Min</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ModuleConstraintImpl#getVersionMax <em>Version Max</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ModuleConstraintImpl#getModuleType <em>Module Type</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ModuleConstraintImpl#getTechnologyVersion <em>Technology Version</em>}</li>
 *   <li>{@link com.bluexml.side.application.impl.ModuleConstraintImpl#getClassifier <em>Classifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModuleConstraintImpl extends EObjectImpl implements ModuleConstraint {
	/**
	 * The default value of the '{@link #getModuleId() <em>Module Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleId()
	 * @generated
	 * @ordered
	 */
	protected static final String MODULE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModuleId() <em>Module Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleId()
	 * @generated
	 * @ordered
	 */
	protected String moduleId = MODULE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersionMin() <em>Version Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersionMin()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_MIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersionMin() <em>Version Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersionMin()
	 * @generated
	 * @ordered
	 */
	protected String versionMin = VERSION_MIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersionMax() <em>Version Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersionMax()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_MAX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersionMax() <em>Version Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersionMax()
	 * @generated
	 * @ordered
	 */
	protected String versionMax = VERSION_MAX_EDEFAULT;

	/**
	 * The default value of the '{@link #getModuleType() <em>Module Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleType()
	 * @generated
	 * @ordered
	 */
	protected static final String MODULE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModuleType() <em>Module Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleType()
	 * @generated
	 * @ordered
	 */
	protected String moduleType = MODULE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTechnologyVersion() <em>Technology Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTechnologyVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String TECHNOLOGY_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTechnologyVersion() <em>Technology Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTechnologyVersion()
	 * @generated
	 * @ordered
	 */
	protected String technologyVersion = TECHNOLOGY_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassifier() <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifier()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASSIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassifier() <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifier()
	 * @generated
	 * @ordered
	 */
	protected String classifier = CLASSIFIER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModuleConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.MODULE_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModuleId(String newModuleId) {
		String oldModuleId = moduleId;
		moduleId = newModuleId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE_CONSTRAINT__MODULE_ID, oldModuleId, moduleId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersionMin() {
		return versionMin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersionMin(String newVersionMin) {
		String oldVersionMin = versionMin;
		versionMin = newVersionMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE_CONSTRAINT__VERSION_MIN, oldVersionMin, versionMin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersionMax() {
		return versionMax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersionMax(String newVersionMax) {
		String oldVersionMax = versionMax;
		versionMax = newVersionMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE_CONSTRAINT__VERSION_MAX, oldVersionMax, versionMax));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModuleType() {
		return moduleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModuleType(String newModuleType) {
		String oldModuleType = moduleType;
		moduleType = newModuleType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE_CONSTRAINT__MODULE_TYPE, oldModuleType, moduleType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTechnologyVersion() {
		return technologyVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTechnologyVersion(String newTechnologyVersion) {
		String oldTechnologyVersion = technologyVersion;
		technologyVersion = newTechnologyVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE_CONSTRAINT__TECHNOLOGY_VERSION, oldTechnologyVersion, technologyVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassifier() {
		return classifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassifier(String newClassifier) {
		String oldClassifier = classifier;
		classifier = newClassifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE_CONSTRAINT__CLASSIFIER, oldClassifier, classifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.MODULE_CONSTRAINT__MODULE_ID:
				return getModuleId();
			case ApplicationPackage.MODULE_CONSTRAINT__VERSION_MIN:
				return getVersionMin();
			case ApplicationPackage.MODULE_CONSTRAINT__VERSION_MAX:
				return getVersionMax();
			case ApplicationPackage.MODULE_CONSTRAINT__MODULE_TYPE:
				return getModuleType();
			case ApplicationPackage.MODULE_CONSTRAINT__TECHNOLOGY_VERSION:
				return getTechnologyVersion();
			case ApplicationPackage.MODULE_CONSTRAINT__CLASSIFIER:
				return getClassifier();
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
			case ApplicationPackage.MODULE_CONSTRAINT__MODULE_ID:
				setModuleId((String)newValue);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__VERSION_MIN:
				setVersionMin((String)newValue);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__VERSION_MAX:
				setVersionMax((String)newValue);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__MODULE_TYPE:
				setModuleType((String)newValue);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__TECHNOLOGY_VERSION:
				setTechnologyVersion((String)newValue);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__CLASSIFIER:
				setClassifier((String)newValue);
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
			case ApplicationPackage.MODULE_CONSTRAINT__MODULE_ID:
				setModuleId(MODULE_ID_EDEFAULT);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__VERSION_MIN:
				setVersionMin(VERSION_MIN_EDEFAULT);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__VERSION_MAX:
				setVersionMax(VERSION_MAX_EDEFAULT);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__MODULE_TYPE:
				setModuleType(MODULE_TYPE_EDEFAULT);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__TECHNOLOGY_VERSION:
				setTechnologyVersion(TECHNOLOGY_VERSION_EDEFAULT);
				return;
			case ApplicationPackage.MODULE_CONSTRAINT__CLASSIFIER:
				setClassifier(CLASSIFIER_EDEFAULT);
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
			case ApplicationPackage.MODULE_CONSTRAINT__MODULE_ID:
				return MODULE_ID_EDEFAULT == null ? moduleId != null : !MODULE_ID_EDEFAULT.equals(moduleId);
			case ApplicationPackage.MODULE_CONSTRAINT__VERSION_MIN:
				return VERSION_MIN_EDEFAULT == null ? versionMin != null : !VERSION_MIN_EDEFAULT.equals(versionMin);
			case ApplicationPackage.MODULE_CONSTRAINT__VERSION_MAX:
				return VERSION_MAX_EDEFAULT == null ? versionMax != null : !VERSION_MAX_EDEFAULT.equals(versionMax);
			case ApplicationPackage.MODULE_CONSTRAINT__MODULE_TYPE:
				return MODULE_TYPE_EDEFAULT == null ? moduleType != null : !MODULE_TYPE_EDEFAULT.equals(moduleType);
			case ApplicationPackage.MODULE_CONSTRAINT__TECHNOLOGY_VERSION:
				return TECHNOLOGY_VERSION_EDEFAULT == null ? technologyVersion != null : !TECHNOLOGY_VERSION_EDEFAULT.equals(technologyVersion);
			case ApplicationPackage.MODULE_CONSTRAINT__CLASSIFIER:
				return CLASSIFIER_EDEFAULT == null ? classifier != null : !CLASSIFIER_EDEFAULT.equals(classifier);
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
		result.append(" (moduleId: ");
		result.append(moduleId);
		result.append(", versionMin: ");
		result.append(versionMin);
		result.append(", versionMax: ");
		result.append(versionMax);
		result.append(", moduleType: ");
		result.append(moduleType);
		result.append(", technologyVersion: ");
		result.append(technologyVersion);
		result.append(", classifier: ");
		result.append(classifier);
		result.append(')');
		return result.toString();
	}

} //ModuleConstraintImpl
