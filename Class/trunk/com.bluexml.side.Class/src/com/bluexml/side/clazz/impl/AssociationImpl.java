/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.ClassModelElement;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getDestination <em>Destination</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#isIsNavigableSRC <em>Is Navigable SRC</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#isIsNavigableTARGET <em>Is Navigable TARGET</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getMinSRC <em>Min SRC</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getMaxSRC <em>Max SRC</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getMinTARGET <em>Min TARGET</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getMaxTARGET <em>Max TARGET</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getAssociationType <em>Association Type</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getAssociationsClass <em>Associations Class</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getRoleSrc <em>Role Src</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getRoleTarget <em>Role Target</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getRoleSrcTitle <em>Role Src Title</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.impl.AssociationImpl#getRoleTargetTitle <em>Role Target Title</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationImpl extends NamedClassModelElementImpl implements Association {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected AbstractClass source;

	/**
	 * The cached value of the '{@link #getDestination() <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestination()
	 * @generated
	 * @ordered
	 */
	protected AbstractClass destination;

	/**
	 * The default value of the '{@link #isIsNavigableSRC() <em>Is Navigable SRC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNavigableSRC()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NAVIGABLE_SRC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNavigableSRC() <em>Is Navigable SRC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNavigableSRC()
	 * @generated
	 * @ordered
	 */
	protected boolean isNavigableSRC = IS_NAVIGABLE_SRC_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsNavigableTARGET() <em>Is Navigable TARGET</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNavigableTARGET()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NAVIGABLE_TARGET_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNavigableTARGET() <em>Is Navigable TARGET</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNavigableTARGET()
	 * @generated
	 * @ordered
	 */
	protected boolean isNavigableTARGET = IS_NAVIGABLE_TARGET_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinSRC() <em>Min SRC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinSRC()
	 * @generated
	 * @ordered
	 */
	protected static final String MIN_SRC_EDEFAULT = "1";

	/**
	 * The cached value of the '{@link #getMinSRC() <em>Min SRC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinSRC()
	 * @generated
	 * @ordered
	 */
	protected String minSRC = MIN_SRC_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxSRC() <em>Max SRC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxSRC()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_SRC_EDEFAULT = "1";

	/**
	 * The cached value of the '{@link #getMaxSRC() <em>Max SRC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxSRC()
	 * @generated
	 * @ordered
	 */
	protected String maxSRC = MAX_SRC_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinTARGET() <em>Min TARGET</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinTARGET()
	 * @generated
	 * @ordered
	 */
	protected static final String MIN_TARGET_EDEFAULT = "1";

	/**
	 * The cached value of the '{@link #getMinTARGET() <em>Min TARGET</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinTARGET()
	 * @generated
	 * @ordered
	 */
	protected String minTARGET = MIN_TARGET_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxTARGET() <em>Max TARGET</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxTARGET()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_TARGET_EDEFAULT = "1";

	/**
	 * The cached value of the '{@link #getMaxTARGET() <em>Max TARGET</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxTARGET()
	 * @generated
	 * @ordered
	 */
	protected String maxTARGET = MAX_TARGET_EDEFAULT;

	/**
	 * The default value of the '{@link #getAssociationType() <em>Association Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationType()
	 * @generated
	 * @ordered
	 */
	protected static final AssociationType ASSOCIATION_TYPE_EDEFAULT = AssociationType.DIRECT;

	/**
	 * The cached value of the '{@link #getAssociationType() <em>Association Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationType()
	 * @generated
	 * @ordered
	 */
	protected AssociationType associationType = ASSOCIATION_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAssociationsClass() <em>Associations Class</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationsClass()
	 * @generated
	 * @ordered
	 */
	protected EList<Clazz> associationsClass;

	/**
	 * The default value of the '{@link #getRoleSrc() <em>Role Src</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleSrc()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_SRC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRoleSrc() <em>Role Src</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleSrc()
	 * @generated
	 * @ordered
	 */
	protected String roleSrc = ROLE_SRC_EDEFAULT;

	/**
	 * The default value of the '{@link #getRoleTarget() <em>Role Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleTarget()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_TARGET_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getRoleTarget() <em>Role Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleTarget()
	 * @generated
	 * @ordered
	 */
	protected String roleTarget = ROLE_TARGET_EDEFAULT;

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
	 * The default value of the '{@link #getRoleSrcTitle() <em>Role Src Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleSrcTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_SRC_TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRoleSrcTitle() <em>Role Src Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleSrcTitle()
	 * @generated
	 * @ordered
	 */
	protected String roleSrcTitle = ROLE_SRC_TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRoleTargetTitle() <em>Role Target Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleTargetTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_TARGET_TITLE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getRoleTargetTitle() <em>Role Target Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleTargetTitle()
	 * @generated
	 * @ordered
	 */
	protected String roleTargetTitle = ROLE_TARGET_TITLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClazzPackage.Literals.ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (AbstractClass)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.ASSOCIATION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(AbstractClass newSource) {
		AbstractClass oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass getDestination() {
		if (destination != null && destination.eIsProxy()) {
			InternalEObject oldDestination = (InternalEObject)destination;
			destination = (AbstractClass)eResolveProxy(oldDestination);
			if (destination != oldDestination) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClazzPackage.ASSOCIATION__DESTINATION, oldDestination, destination));
			}
		}
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractClass basicGetDestination() {
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestination(AbstractClass newDestination) {
		AbstractClass oldDestination = destination;
		destination = newDestination;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__DESTINATION, oldDestination, destination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsNavigableSRC() {
		return isNavigableSRC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsNavigableSRC(boolean newIsNavigableSRC) {
		boolean oldIsNavigableSRC = isNavigableSRC;
		isNavigableSRC = newIsNavigableSRC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__IS_NAVIGABLE_SRC, oldIsNavigableSRC, isNavigableSRC));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsNavigableTARGET() {
		return isNavigableTARGET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsNavigableTARGET(boolean newIsNavigableTARGET) {
		boolean oldIsNavigableTARGET = isNavigableTARGET;
		isNavigableTARGET = newIsNavigableTARGET;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__IS_NAVIGABLE_TARGET, oldIsNavigableTARGET, isNavigableTARGET));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMinSRC() {
		return minSRC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinSRC(String newMinSRC) {
		String oldMinSRC = minSRC;
		minSRC = newMinSRC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__MIN_SRC, oldMinSRC, minSRC));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaxSRC() {
		return maxSRC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxSRC(String newMaxSRC) {
		String oldMaxSRC = maxSRC;
		maxSRC = newMaxSRC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__MAX_SRC, oldMaxSRC, maxSRC));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMinTARGET() {
		return minTARGET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinTARGET(String newMinTARGET) {
		String oldMinTARGET = minTARGET;
		minTARGET = newMinTARGET;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__MIN_TARGET, oldMinTARGET, minTARGET));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaxTARGET() {
		return maxTARGET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxTARGET(String newMaxTARGET) {
		String oldMaxTARGET = maxTARGET;
		maxTARGET = newMaxTARGET;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__MAX_TARGET, oldMaxTARGET, maxTARGET));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationType getAssociationType() {
		return associationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationType(AssociationType newAssociationType) {
		AssociationType oldAssociationType = associationType;
		associationType = newAssociationType == null ? ASSOCIATION_TYPE_EDEFAULT : newAssociationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE, oldAssociationType, associationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clazz> getAssociationsClass() {
		if (associationsClass == null) {
			associationsClass = new EObjectResolvingEList<Clazz>(Clazz.class, this, ClazzPackage.ASSOCIATION__ASSOCIATIONS_CLASS);
		}
		return associationsClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRoleSrc() {
		return roleSrc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleSrc(String newRoleSrc) {
		String oldRoleSrc = roleSrc;
		roleSrc = newRoleSrc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__ROLE_SRC, oldRoleSrc, roleSrc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRoleTarget() {
		return roleTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleTarget(String newRoleTarget) {
		String oldRoleTarget = roleTarget;
		roleTarget = newRoleTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__ROLE_TARGET, oldRoleTarget, roleTarget));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRoleSrcTitle() {
		return roleSrcTitle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleSrcTitle(String newRoleSrcTitle) {
		String oldRoleSrcTitle = roleSrcTitle;
		roleSrcTitle = newRoleSrcTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__ROLE_SRC_TITLE, oldRoleSrcTitle, roleSrcTitle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRoleTargetTitle() {
		return roleTargetTitle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleTargetTitle(String newRoleTargetTitle) {
		String oldRoleTargetTitle = roleTargetTitle;
		roleTargetTitle = newRoleTargetTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClazzPackage.ASSOCIATION__ROLE_TARGET_TITLE, oldRoleTargetTitle, roleTargetTitle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean equalsForMerge(Association other) {
		if (equalsForMergeBodyOCL == null) {
			EOperation eOperation = ClazzPackage.Literals.ASSOCIATION.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ClazzPackage.Literals.ASSOCIATION, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				equalsForMergeBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(equalsForMergeBodyOCL);
	 
		EvaluationEnvironment<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();
		
		evalEnv.add("other", other);
	  
		return ((Boolean) query.evaluate(this)).booleanValue();
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #equalsForMerge <em>Equals For Merge</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #equalsForMerge
	 * @generated
	 */
	private static OCLExpression<EClassifier> equalsForMergeBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClazzPackage.ASSOCIATION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ClazzPackage.ASSOCIATION__DESTINATION:
				if (resolve) return getDestination();
				return basicGetDestination();
			case ClazzPackage.ASSOCIATION__IS_NAVIGABLE_SRC:
				return isIsNavigableSRC() ? Boolean.TRUE : Boolean.FALSE;
			case ClazzPackage.ASSOCIATION__IS_NAVIGABLE_TARGET:
				return isIsNavigableTARGET() ? Boolean.TRUE : Boolean.FALSE;
			case ClazzPackage.ASSOCIATION__MIN_SRC:
				return getMinSRC();
			case ClazzPackage.ASSOCIATION__MAX_SRC:
				return getMaxSRC();
			case ClazzPackage.ASSOCIATION__MIN_TARGET:
				return getMinTARGET();
			case ClazzPackage.ASSOCIATION__MAX_TARGET:
				return getMaxTARGET();
			case ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE:
				return getAssociationType();
			case ClazzPackage.ASSOCIATION__ASSOCIATIONS_CLASS:
				return getAssociationsClass();
			case ClazzPackage.ASSOCIATION__ROLE_SRC:
				return getRoleSrc();
			case ClazzPackage.ASSOCIATION__ROLE_TARGET:
				return getRoleTarget();
			case ClazzPackage.ASSOCIATION__TITLE:
				return getTitle();
			case ClazzPackage.ASSOCIATION__ROLE_SRC_TITLE:
				return getRoleSrcTitle();
			case ClazzPackage.ASSOCIATION__ROLE_TARGET_TITLE:
				return getRoleTargetTitle();
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
			case ClazzPackage.ASSOCIATION__SOURCE:
				setSource((AbstractClass)newValue);
				return;
			case ClazzPackage.ASSOCIATION__DESTINATION:
				setDestination((AbstractClass)newValue);
				return;
			case ClazzPackage.ASSOCIATION__IS_NAVIGABLE_SRC:
				setIsNavigableSRC(((Boolean)newValue).booleanValue());
				return;
			case ClazzPackage.ASSOCIATION__IS_NAVIGABLE_TARGET:
				setIsNavigableTARGET(((Boolean)newValue).booleanValue());
				return;
			case ClazzPackage.ASSOCIATION__MIN_SRC:
				setMinSRC((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION__MAX_SRC:
				setMaxSRC((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION__MIN_TARGET:
				setMinTARGET((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION__MAX_TARGET:
				setMaxTARGET((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE:
				setAssociationType((AssociationType)newValue);
				return;
			case ClazzPackage.ASSOCIATION__ASSOCIATIONS_CLASS:
				getAssociationsClass().clear();
				getAssociationsClass().addAll((Collection<? extends Clazz>)newValue);
				return;
			case ClazzPackage.ASSOCIATION__ROLE_SRC:
				setRoleSrc((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION__ROLE_TARGET:
				setRoleTarget((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION__TITLE:
				setTitle((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION__ROLE_SRC_TITLE:
				setRoleSrcTitle((String)newValue);
				return;
			case ClazzPackage.ASSOCIATION__ROLE_TARGET_TITLE:
				setRoleTargetTitle((String)newValue);
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
			case ClazzPackage.ASSOCIATION__SOURCE:
				setSource((AbstractClass)null);
				return;
			case ClazzPackage.ASSOCIATION__DESTINATION:
				setDestination((AbstractClass)null);
				return;
			case ClazzPackage.ASSOCIATION__IS_NAVIGABLE_SRC:
				setIsNavigableSRC(IS_NAVIGABLE_SRC_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__IS_NAVIGABLE_TARGET:
				setIsNavigableTARGET(IS_NAVIGABLE_TARGET_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__MIN_SRC:
				setMinSRC(MIN_SRC_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__MAX_SRC:
				setMaxSRC(MAX_SRC_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__MIN_TARGET:
				setMinTARGET(MIN_TARGET_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__MAX_TARGET:
				setMaxTARGET(MAX_TARGET_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE:
				setAssociationType(ASSOCIATION_TYPE_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__ASSOCIATIONS_CLASS:
				getAssociationsClass().clear();
				return;
			case ClazzPackage.ASSOCIATION__ROLE_SRC:
				setRoleSrc(ROLE_SRC_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__ROLE_TARGET:
				setRoleTarget(ROLE_TARGET_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__ROLE_SRC_TITLE:
				setRoleSrcTitle(ROLE_SRC_TITLE_EDEFAULT);
				return;
			case ClazzPackage.ASSOCIATION__ROLE_TARGET_TITLE:
				setRoleTargetTitle(ROLE_TARGET_TITLE_EDEFAULT);
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
			case ClazzPackage.ASSOCIATION__SOURCE:
				return source != null;
			case ClazzPackage.ASSOCIATION__DESTINATION:
				return destination != null;
			case ClazzPackage.ASSOCIATION__IS_NAVIGABLE_SRC:
				return isNavigableSRC != IS_NAVIGABLE_SRC_EDEFAULT;
			case ClazzPackage.ASSOCIATION__IS_NAVIGABLE_TARGET:
				return isNavigableTARGET != IS_NAVIGABLE_TARGET_EDEFAULT;
			case ClazzPackage.ASSOCIATION__MIN_SRC:
				return MIN_SRC_EDEFAULT == null ? minSRC != null : !MIN_SRC_EDEFAULT.equals(minSRC);
			case ClazzPackage.ASSOCIATION__MAX_SRC:
				return MAX_SRC_EDEFAULT == null ? maxSRC != null : !MAX_SRC_EDEFAULT.equals(maxSRC);
			case ClazzPackage.ASSOCIATION__MIN_TARGET:
				return MIN_TARGET_EDEFAULT == null ? minTARGET != null : !MIN_TARGET_EDEFAULT.equals(minTARGET);
			case ClazzPackage.ASSOCIATION__MAX_TARGET:
				return MAX_TARGET_EDEFAULT == null ? maxTARGET != null : !MAX_TARGET_EDEFAULT.equals(maxTARGET);
			case ClazzPackage.ASSOCIATION__ASSOCIATION_TYPE:
				return associationType != ASSOCIATION_TYPE_EDEFAULT;
			case ClazzPackage.ASSOCIATION__ASSOCIATIONS_CLASS:
				return associationsClass != null && !associationsClass.isEmpty();
			case ClazzPackage.ASSOCIATION__ROLE_SRC:
				return ROLE_SRC_EDEFAULT == null ? roleSrc != null : !ROLE_SRC_EDEFAULT.equals(roleSrc);
			case ClazzPackage.ASSOCIATION__ROLE_TARGET:
				return ROLE_TARGET_EDEFAULT == null ? roleTarget != null : !ROLE_TARGET_EDEFAULT.equals(roleTarget);
			case ClazzPackage.ASSOCIATION__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case ClazzPackage.ASSOCIATION__ROLE_SRC_TITLE:
				return ROLE_SRC_TITLE_EDEFAULT == null ? roleSrcTitle != null : !ROLE_SRC_TITLE_EDEFAULT.equals(roleSrcTitle);
			case ClazzPackage.ASSOCIATION__ROLE_TARGET_TITLE:
				return ROLE_TARGET_TITLE_EDEFAULT == null ? roleTargetTitle != null : !ROLE_TARGET_TITLE_EDEFAULT.equals(roleTargetTitle);
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
		result.append(" (isNavigableSRC: ");
		result.append(isNavigableSRC);
		result.append(", isNavigableTARGET: ");
		result.append(isNavigableTARGET);
		result.append(", minSRC: ");
		result.append(minSRC);
		result.append(", maxSRC: ");
		result.append(maxSRC);
		result.append(", minTARGET: ");
		result.append(minTARGET);
		result.append(", maxTARGET: ");
		result.append(maxTARGET);
		result.append(", associationType: ");
		result.append(associationType);
		result.append(", roleSrc: ");
		result.append(roleSrc);
		result.append(", roleTarget: ");
		result.append(roleTarget);
		result.append(", title: ");
		result.append(title);
		result.append(", roleSrcTitle: ");
		result.append(roleSrcTitle);
		result.append(", roleTargetTitle: ");
		result.append(roleTargetTitle);
		result.append(')');
		return result.toString();
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //AssociationImpl
