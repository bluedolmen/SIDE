/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Association End</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationEndImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationEndImpl#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationEndImpl#getCardMax <em>Card Max</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationEndImpl#isNavigable <em>Navigable</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationEndImpl#getLinkedClass <em>Linked Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationEndImpl extends TitledNamedClassModelElementImpl
		implements AssociationEnd {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCardMin() <em>Card Min</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCardMin()
	 * @generated
	 * @ordered
	 */
	protected static final String CARD_MIN_EDEFAULT = "0";

	/**
	 * The cached value of the '{@link #getCardMin() <em>Card Min</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCardMin()
	 * @generated
	 * @ordered
	 */
	protected String cardMin = CARD_MIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getCardMax() <em>Card Max</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCardMax()
	 * @generated
	 * @ordered
	 */
	protected static final String CARD_MAX_EDEFAULT = "1";

	/**
	 * The cached value of the '{@link #getCardMax() <em>Card Max</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCardMax()
	 * @generated
	 * @ordered
	 */
	protected String cardMax = CARD_MAX_EDEFAULT;

	/**
	 * The default value of the '{@link #isNavigable() <em>Navigable</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isNavigable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NAVIGABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNavigable() <em>Navigable</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isNavigable()
	 * @generated
	 * @ordered
	 */
	protected boolean navigable = NAVIGABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLinkedClass() <em>Linked Class</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLinkedClass()
	 * @generated
	 * @ordered
	 */
	protected AbstractClass linkedClass;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationEndImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.ASSOCIATION_END;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION_END__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getCardMin() {
		return cardMin;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCardMin(String newCardMin) {
		String oldCardMin = cardMin;
		cardMin = newCardMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION_END__CARD_MIN, oldCardMin, cardMin));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getCardMax() {
		return cardMax;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCardMax(String newCardMax) {
		String oldCardMax = cardMax;
		cardMax = newCardMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION_END__CARD_MAX, oldCardMax, cardMax));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNavigable() {
		return navigable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigable(boolean newNavigable) {
		boolean oldNavigable = navigable;
		navigable = newNavigable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION_END__NAVIGABLE, oldNavigable, navigable));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass getLinkedClass() {
		if (linkedClass != null && linkedClass.eIsProxy()) {
			InternalEObject oldLinkedClass = (InternalEObject)linkedClass;
			linkedClass = (AbstractClass)eResolveProxy(oldLinkedClass);
			if (linkedClass != oldLinkedClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.ASSOCIATION_END__LINKED_CLASS, oldLinkedClass, linkedClass));
			}
		}
		return linkedClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass basicGetLinkedClass() {
		return linkedClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkedClass(AbstractClass newLinkedClass) {
		AbstractClass oldLinkedClass = linkedClass;
		linkedClass = newLinkedClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION_END__LINKED_CLASS, oldLinkedClass, linkedClass));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMandatory() {
		if (isMandatoryBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ASSOCIATION_END.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ASSOCIATION_END, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isMandatoryBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isMandatoryBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #isMandatory <em>Is Mandatory</em>}' operation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #isMandatory
	 * @generated
	 */
	private static OCLExpression<EClassifier> isMandatoryBodyOCL;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMany() {
		if (isManyBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ASSOCIATION_END.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ASSOCIATION_END, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				isManyBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(isManyBodyOCL);
	
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #isMany
	 * <em>Is Many</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #isMany
	 * @generated
	 */
	private static OCLExpression<EClassifier> isManyBodyOCL;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationEnd getOpposite() {
		if (getOppositeBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ASSOCIATION_END.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ASSOCIATION_END, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getOppositeBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getOppositeBodyOCL);
	
		return (AssociationEnd) query.evaluate(this);
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getOpposite <em>Get Opposite</em>}' operation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getOpposite
	 * @generated
	 */
	private static OCLExpression<EClassifier> getOppositeBodyOCL;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClazzPackage.ASSOCIATION_END__VALUE:
				return getValue();
			case ClazzPackage.ASSOCIATION_END__CARD_MIN:
				return getCardMin();
			case ClazzPackage.ASSOCIATION_END__CARD_MAX:
				return getCardMax();
			case ClazzPackage.ASSOCIATION_END__NAVIGABLE:
				return isNavigable();
			case ClazzPackage.ASSOCIATION_END__LINKED_CLASS:
				if (resolve) return getLinkedClass();
				return basicGetLinkedClass();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClazzPackage.ASSOCIATION_END__VALUE:
				setValue((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION_END__CARD_MIN:
				setCardMin((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION_END__CARD_MAX:
				setCardMax((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION_END__NAVIGABLE:
				setNavigable((Boolean)newValue);
				return;
			case ClazzPackage.ASSOCIATION_END__LINKED_CLASS:
				setLinkedClass((AbstractClass)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ClazzPackage.ASSOCIATION_END__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION_END__CARD_MIN:
				setCardMin(CARD_MIN_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION_END__CARD_MAX:
				setCardMax(CARD_MAX_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION_END__NAVIGABLE:
				setNavigable(NAVIGABLE_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION_END__LINKED_CLASS:
				setLinkedClass((AbstractClass)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ClazzPackage.ASSOCIATION_END__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case ClazzPackage.ASSOCIATION_END__CARD_MIN:
				return CARD_MIN_EDEFAULT == null ? cardMin != null : !CARD_MIN_EDEFAULT.equals(cardMin);
			case ClazzPackage.ASSOCIATION_END__CARD_MAX:
				return CARD_MAX_EDEFAULT == null ? cardMax != null : !CARD_MAX_EDEFAULT.equals(cardMax);
			case ClazzPackage.ASSOCIATION_END__NAVIGABLE:
				return navigable != NAVIGABLE_EDEFAULT;
			case ClazzPackage.ASSOCIATION_END__LINKED_CLASS:
				return linkedClass != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Comment.class) {
			switch (derivedFeatureID) {
				case ClazzPackage.ASSOCIATION_END__VALUE: return CommonPackage.COMMENT__VALUE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Comment.class) {
			switch (baseFeatureID) {
				case CommonPackage.COMMENT__VALUE: return ClazzPackage.ASSOCIATION_END__VALUE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (value: ");
		result.append(value);
		result.append(", cardMin: ");
		result.append(cardMin);
		result.append(", cardMax: ");
		result.append(cardMax);
		result.append(", navigable: ");
		result.append(navigable);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

	public void setLinkedClass(Clazz value) {
		AbstractClass oldLinkedClass = linkedClass;
		linkedClass = value;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClazzPackage.ASSOCIATION_END__LINKED_CLASS, oldLinkedClass,
					linkedClass));
	}
} // AssociationEndImpl
