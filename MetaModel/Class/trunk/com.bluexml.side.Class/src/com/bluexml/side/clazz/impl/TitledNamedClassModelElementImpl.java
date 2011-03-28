/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.clazz.ClassComment;
import com.bluexml.side.clazz.ClassModelElement;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.TitledNamedClassModelElement;
import com.bluexml.side.common.impl.NamedModelElementImpl;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Titled Named Class Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.TitledNamedClassModelElementImpl#getHasComments <em>Has Comments</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.TitledNamedClassModelElementImpl#getTitle <em>Title</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TitledNamedClassModelElementImpl extends NamedModelElementImpl implements TitledNamedClassModelElement {
	/**
	 * The cached value of the '{@link #getHasComments() <em>Has Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasComments()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassComment> hasComments;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TitledNamedClassModelElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.TITLED_NAMED_CLASS_MODEL_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassComment> getHasComments() {
		if (hasComments == null) {
			hasComments = new EObjectContainmentEList<ClassComment>(ClassComment.class, this, ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS);
		}
		return hasComments;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		if (getLabelBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.TITLED_NAMED_CLASS_MODEL_ELEMENT.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.TITLED_NAMED_CLASS_MODEL_ELEMENT, eOperation);
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
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS:
				return ((InternalEList<?>)getHasComments()).basicRemove(otherEnd, msgs);
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
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS:
				return getHasComments();
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE:
				return getTitle();
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
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS:
				getHasComments().clear();
				getHasComments().addAll((Collection<? extends ClassComment>)newValue);
				return;
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE:
				setTitle((String)newValue);
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
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS:
				getHasComments().clear();
				return;
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE:
				setTitle(TITLE_EDEFAULT);
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
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS:
				return hasComments != null && !hasComments.isEmpty();
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
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
		if (baseClass == ClassModelElement.class) {
			switch (derivedFeatureID) {
				case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS: return ClazzPackage.CLASS_MODEL_ELEMENT__HAS_COMMENTS;
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
		if (baseClass == ClassModelElement.class) {
			switch (baseFeatureID) {
				case ClazzPackage.CLASS_MODEL_ELEMENT__HAS_COMMENTS: return ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS;
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
		result.append(" (title: ");
		result.append(title);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //TitledNamedClassModelElementImpl
