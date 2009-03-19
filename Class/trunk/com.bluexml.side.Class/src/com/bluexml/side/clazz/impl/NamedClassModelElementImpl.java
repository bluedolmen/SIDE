/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.MetaInfo;
import com.bluexml.side.clazz.NamedClassModelElement;

import com.bluexml.side.common.impl.NamedModelElementImpl;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.Tag;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Class Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.NamedClassModelElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.NamedClassModelElementImpl#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NamedClassModelElementImpl extends ClassModelElementImpl implements NamedClassModelElement {
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
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedClassModelElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.NAMED_CLASS_MODEL_ELEMENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullName() {
		if (getFullNameBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.NAMED_CLASS_MODEL_ELEMENT.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.NAMED_CLASS_MODEL_ELEMENT, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getFullNameBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getFullNameBodyOCL);
	
		return (String) query.evaluate(this);
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getFullName <em>Get Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullName
	 * @generated
	 */
	private static OCLExpression<EClassifier> getFullNameBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescriptionOrName() {
		if (getDescriptionOrNameBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.NAMED_CLASS_MODEL_ELEMENT.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.NAMED_CLASS_MODEL_ELEMENT, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getDescriptionOrNameBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getDescriptionOrNameBodyOCL);
	
		return (String) query.evaluate(this);
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getDescriptionOrName <em>Get Description Or Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptionOrName
	 * @generated
	 */
	private static OCLExpression<EClassifier> getDescriptionOrNameBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		if (getLabelBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.NAMED_CLASS_MODEL_ELEMENT.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.NAMED_CLASS_MODEL_ELEMENT, eOperation);
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__NAME:
				return getName();
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION:
				return getDescription();
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
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
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
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
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
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
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
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //NamedClassModelElementImpl
