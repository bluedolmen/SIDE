/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
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

import com.bluexml.side.common.Comment;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.Tag;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getStereotypes <em>Stereotypes</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getMetainfo <em>Metainfo</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getMapTo <em>Map To</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#getSuffix <em>Suffix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FieldElementImpl#isHidden <em>Hidden</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FieldElementImpl extends StylableImpl implements FieldElement {
	/**
	 * The cached value of the '{@link #getStereotypes() <em>Stereotypes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Stereotype> stereotypes;

	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected EList<Tag> tags;

	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> comments;

	/**
	 * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected String documentation = DOCUMENTATION_EDEFAULT;

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
	 * The cached value of the '{@link #getMetainfo() <em>Metainfo</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetainfo()
	 * @generated
	 * @ordered
	 */
	protected EList<MetaInfo> metainfo;

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
	 * The cached value of the '{@link #getMapTo() <em>Map To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapTo()
	 * @generated
	 * @ordered
	 */
	protected ModelElement mapTo;

	/**
	 * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected String prefix = PREFIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getSuffix() <em>Suffix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuffix()
	 * @generated
	 * @ordered
	 */
	protected static final String SUFFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSuffix() <em>Suffix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuffix()
	 * @generated
	 * @ordered
	 */
	protected String suffix = SUFFIX_EDEFAULT;

	/**
	 * The default value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDDEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected boolean hidden = HIDDEN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.FIELD_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Stereotype> getStereotypes() {
		if (stereotypes == null) {
			stereotypes = new EObjectResolvingEList<Stereotype>(Stereotype.class, this, ViewPackage.FIELD_ELEMENT__STEREOTYPES);
		}
		return stereotypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tag> getTags() {
		if (tags == null) {
			tags = new EObjectContainmentEList<Tag>(Tag.class, this, ViewPackage.FIELD_ELEMENT__TAGS);
		}
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Comment> getComments() {
		if (comments == null) {
			comments = new EObjectContainmentEList<Comment>(Comment.class, this, ViewPackage.FIELD_ELEMENT__COMMENTS);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentation(String newDocumentation) {
		String oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD_ELEMENT__DOCUMENTATION, oldDocumentation, documentation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD_ELEMENT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaInfo> getMetainfo() {
		if (metainfo == null) {
			metainfo = new EObjectContainmentEList<MetaInfo>(MetaInfo.class, this, ViewPackage.FIELD_ELEMENT__METAINFO);
		}
		return metainfo;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelElement getMapTo() {
		if (mapTo != null && mapTo.eIsProxy()) {
			InternalEObject oldMapTo = (InternalEObject)mapTo;
			mapTo = (ModelElement)eResolveProxy(oldMapTo);
			if (mapTo != oldMapTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.FIELD_ELEMENT__MAP_TO, oldMapTo, mapTo));
			}
		}
		return mapTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelElement basicGetMapTo() {
		return mapTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapTo(ModelElement newMapTo) {
		ModelElement oldMapTo = mapTo;
		mapTo = newMapTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD_ELEMENT__MAP_TO, oldMapTo, mapTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrefix(String newPrefix) {
		String oldPrefix = prefix;
		prefix = newPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD_ELEMENT__PREFIX, oldPrefix, prefix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuffix(String newSuffix) {
		String oldSuffix = suffix;
		suffix = newSuffix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD_ELEMENT__SUFFIX, oldSuffix, suffix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHidden(boolean newHidden) {
		boolean oldHidden = hidden;
		hidden = newHidden;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FIELD_ELEMENT__HIDDEN, oldHidden, hidden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ViewCollection> getViewCollection() {
		if (getViewCollectionBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.FIELD_ELEMENT.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.FIELD_ELEMENT, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getViewCollectionBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getViewCollectionBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<ViewCollection> result = (Collection<ViewCollection>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<ViewCollection>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getViewCollection <em>Get View Collection</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewCollection
	 * @generated
	 */
	private static OCLExpression<EClassifier> getViewCollectionBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullName() {
		if (getFullNameBodyOCL == null) {
			EOperation eOperation = CommonPackage.Literals.NAMED_MODEL_ELEMENT.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(CommonPackage.Literals.NAMED_MODEL_ELEMENT, eOperation);
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
	public String getDocumentationOrName() {
		if (getDocumentationOrNameBodyOCL == null) {
			EOperation eOperation = CommonPackage.Literals.NAMED_MODEL_ELEMENT.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(CommonPackage.Literals.NAMED_MODEL_ELEMENT, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getDocumentationOrNameBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getDocumentationOrNameBodyOCL);
	
		return (String) query.evaluate(this);
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getDocumentationOrName <em>Get Documentation Or Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentationOrName
	 * @generated
	 */
	private static OCLExpression<EClassifier> getDocumentationOrNameBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescriptionOrName() {
		if (getDescriptionOrNameBodyOCL == null) {
			EOperation eOperation = CommonPackage.Literals.NAMED_MODEL_ELEMENT.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(CommonPackage.Literals.NAMED_MODEL_ELEMENT, eOperation);
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.FIELD_ELEMENT__TAGS:
				return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
			case ViewPackage.FIELD_ELEMENT__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
			case ViewPackage.FIELD_ELEMENT__METAINFO:
				return ((InternalEList<?>)getMetainfo()).basicRemove(otherEnd, msgs);
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
			case ViewPackage.FIELD_ELEMENT__STEREOTYPES:
				return getStereotypes();
			case ViewPackage.FIELD_ELEMENT__TAGS:
				return getTags();
			case ViewPackage.FIELD_ELEMENT__COMMENTS:
				return getComments();
			case ViewPackage.FIELD_ELEMENT__DOCUMENTATION:
				return getDocumentation();
			case ViewPackage.FIELD_ELEMENT__DESCRIPTION:
				return getDescription();
			case ViewPackage.FIELD_ELEMENT__METAINFO:
				return getMetainfo();
			case ViewPackage.FIELD_ELEMENT__NAME:
				return getName();
			case ViewPackage.FIELD_ELEMENT__MAP_TO:
				if (resolve) return getMapTo();
				return basicGetMapTo();
			case ViewPackage.FIELD_ELEMENT__PREFIX:
				return getPrefix();
			case ViewPackage.FIELD_ELEMENT__SUFFIX:
				return getSuffix();
			case ViewPackage.FIELD_ELEMENT__HIDDEN:
				return isHidden();
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
			case ViewPackage.FIELD_ELEMENT__STEREOTYPES:
				getStereotypes().clear();
				getStereotypes().addAll((Collection<? extends Stereotype>)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends Tag>)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__METAINFO:
				getMetainfo().clear();
				getMetainfo().addAll((Collection<? extends MetaInfo>)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__MAP_TO:
				setMapTo((ModelElement)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__PREFIX:
				setPrefix((String)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__SUFFIX:
				setSuffix((String)newValue);
				return;
			case ViewPackage.FIELD_ELEMENT__HIDDEN:
				setHidden((Boolean)newValue);
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
			case ViewPackage.FIELD_ELEMENT__STEREOTYPES:
				getStereotypes().clear();
				return;
			case ViewPackage.FIELD_ELEMENT__TAGS:
				getTags().clear();
				return;
			case ViewPackage.FIELD_ELEMENT__COMMENTS:
				getComments().clear();
				return;
			case ViewPackage.FIELD_ELEMENT__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case ViewPackage.FIELD_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ViewPackage.FIELD_ELEMENT__METAINFO:
				getMetainfo().clear();
				return;
			case ViewPackage.FIELD_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ViewPackage.FIELD_ELEMENT__MAP_TO:
				setMapTo((ModelElement)null);
				return;
			case ViewPackage.FIELD_ELEMENT__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case ViewPackage.FIELD_ELEMENT__SUFFIX:
				setSuffix(SUFFIX_EDEFAULT);
				return;
			case ViewPackage.FIELD_ELEMENT__HIDDEN:
				setHidden(HIDDEN_EDEFAULT);
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
			case ViewPackage.FIELD_ELEMENT__STEREOTYPES:
				return stereotypes != null && !stereotypes.isEmpty();
			case ViewPackage.FIELD_ELEMENT__TAGS:
				return tags != null && !tags.isEmpty();
			case ViewPackage.FIELD_ELEMENT__COMMENTS:
				return comments != null && !comments.isEmpty();
			case ViewPackage.FIELD_ELEMENT__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case ViewPackage.FIELD_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ViewPackage.FIELD_ELEMENT__METAINFO:
				return metainfo != null && !metainfo.isEmpty();
			case ViewPackage.FIELD_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ViewPackage.FIELD_ELEMENT__MAP_TO:
				return mapTo != null;
			case ViewPackage.FIELD_ELEMENT__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case ViewPackage.FIELD_ELEMENT__SUFFIX:
				return SUFFIX_EDEFAULT == null ? suffix != null : !SUFFIX_EDEFAULT.equals(suffix);
			case ViewPackage.FIELD_ELEMENT__HIDDEN:
				return hidden != HIDDEN_EDEFAULT;
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
		if (baseClass == ModelElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FIELD_ELEMENT__STEREOTYPES: return CommonPackage.MODEL_ELEMENT__STEREOTYPES;
				case ViewPackage.FIELD_ELEMENT__TAGS: return CommonPackage.MODEL_ELEMENT__TAGS;
				case ViewPackage.FIELD_ELEMENT__COMMENTS: return CommonPackage.MODEL_ELEMENT__COMMENTS;
				case ViewPackage.FIELD_ELEMENT__DOCUMENTATION: return CommonPackage.MODEL_ELEMENT__DOCUMENTATION;
				case ViewPackage.FIELD_ELEMENT__DESCRIPTION: return CommonPackage.MODEL_ELEMENT__DESCRIPTION;
				case ViewPackage.FIELD_ELEMENT__METAINFO: return CommonPackage.MODEL_ELEMENT__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FIELD_ELEMENT__NAME: return CommonPackage.NAMED_MODEL_ELEMENT__NAME;
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
		if (baseClass == ModelElement.class) {
			switch (baseFeatureID) {
				case CommonPackage.MODEL_ELEMENT__STEREOTYPES: return ViewPackage.FIELD_ELEMENT__STEREOTYPES;
				case CommonPackage.MODEL_ELEMENT__TAGS: return ViewPackage.FIELD_ELEMENT__TAGS;
				case CommonPackage.MODEL_ELEMENT__COMMENTS: return ViewPackage.FIELD_ELEMENT__COMMENTS;
				case CommonPackage.MODEL_ELEMENT__DOCUMENTATION: return ViewPackage.FIELD_ELEMENT__DOCUMENTATION;
				case CommonPackage.MODEL_ELEMENT__DESCRIPTION: return ViewPackage.FIELD_ELEMENT__DESCRIPTION;
				case CommonPackage.MODEL_ELEMENT__METAINFO: return ViewPackage.FIELD_ELEMENT__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (baseFeatureID) {
				case CommonPackage.NAMED_MODEL_ELEMENT__NAME: return ViewPackage.FIELD_ELEMENT__NAME;
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
		result.append(" (documentation: ");
		result.append(documentation);
		result.append(", description: ");
		result.append(description);
		result.append(", name: ");
		result.append(name);
		result.append(", prefix: ");
		result.append(prefix);
		result.append(", suffix: ");
		result.append(suffix);
		result.append(", hidden: ");
		result.append(hidden);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //FieldElementImpl
