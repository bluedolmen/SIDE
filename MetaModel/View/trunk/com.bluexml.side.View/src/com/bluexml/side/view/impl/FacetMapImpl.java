/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import com.bluexml.side.common.Comment;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.Container;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.Tag;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.AbstractViewOf;
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

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.FacetDisplayType;
import com.bluexml.side.view.FacetMap;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldContainer;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.Stylable;
import com.bluexml.side.view.Styling;
import com.bluexml.side.view.Paginable;
import com.bluexml.side.view.Paging;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facet Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getStyling <em>Styling</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getStereotypes <em>Stereotypes</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getMetainfo <em>Metainfo</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getMapTo <em>Map To</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getSuffix <em>Suffix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#isHidden <em>Hidden</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getViewOf <em>View Of</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#isDisplayEmptyFacet <em>Display Empty Facet</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.FacetMapImpl#getFacetDisplayType <em>Facet Display Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FacetMapImpl extends PaginableImpl implements FacetMap {
	/**
	 * The cached value of the '{@link #getStyling() <em>Styling</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyling()
	 * @generated
	 * @ordered
	 */
	protected Styling styling;

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
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<FieldElement> children;

	/**
	 * The cached value of the '{@link #getDisabled() <em>Disabled</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabled()
	 * @generated
	 * @ordered
	 */
	protected EList<FieldElement> disabled;

	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent operations;

	/**
	 * The cached value of the '{@link #getViewOf() <em>View Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewOf()
	 * @generated
	 * @ordered
	 */
	protected Container viewOf;

	/**
	 * The default value of the '{@link #isDisplayEmptyFacet() <em>Display Empty Facet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisplayEmptyFacet()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISPLAY_EMPTY_FACET_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDisplayEmptyFacet() <em>Display Empty Facet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisplayEmptyFacet()
	 * @generated
	 * @ordered
	 */
	protected boolean displayEmptyFacet = DISPLAY_EMPTY_FACET_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacetDisplayType() <em>Facet Display Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacetDisplayType()
	 * @generated
	 * @ordered
	 */
	protected static final FacetDisplayType FACET_DISPLAY_TYPE_EDEFAULT = FacetDisplayType.LIST;

	/**
	 * The cached value of the '{@link #getFacetDisplayType() <em>Facet Display Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacetDisplayType()
	 * @generated
	 * @ordered
	 */
	protected FacetDisplayType facetDisplayType = FACET_DISPLAY_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FacetMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.FACET_MAP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Styling getStyling() {
		return styling;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStyling(Styling newStyling, NotificationChain msgs) {
		Styling oldStyling = styling;
		styling = newStyling;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__STYLING, oldStyling, newStyling);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyling(Styling newStyling) {
		if (newStyling != styling) {
			NotificationChain msgs = null;
			if (styling != null)
				msgs = ((InternalEObject)styling).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__STYLING, null, msgs);
			if (newStyling != null)
				msgs = ((InternalEObject)newStyling).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__STYLING, null, msgs);
			msgs = basicSetStyling(newStyling, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__STYLING, newStyling, newStyling));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Stereotype> getStereotypes() {
		if (stereotypes == null) {
			stereotypes = new EObjectResolvingEList<Stereotype>(Stereotype.class, this, ViewPackage.FACET_MAP__STEREOTYPES);
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
			tags = new EObjectContainmentEList<Tag>(Tag.class, this, ViewPackage.FACET_MAP__TAGS);
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
			comments = new EObjectContainmentEList<Comment>(Comment.class, this, ViewPackage.FACET_MAP__COMMENTS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__DOCUMENTATION, oldDocumentation, documentation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaInfo> getMetainfo() {
		if (metainfo == null) {
			metainfo = new EObjectContainmentEList<MetaInfo>(MetaInfo.class, this, ViewPackage.FACET_MAP__METAINFO);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__NAME, oldName, name));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.FACET_MAP__MAP_TO, oldMapTo, mapTo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__MAP_TO, oldMapTo, mapTo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__PREFIX, oldPrefix, prefix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__SUFFIX, oldSuffix, suffix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__HIDDEN, oldHidden, hidden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldElement> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.FACET_MAP__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldElement> getDisabled() {
		if (disabled == null) {
			disabled = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.FACET_MAP__DISABLED);
		}
		return disabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getOperations() {
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperations(OperationComponent newOperations, NotificationChain msgs) {
		OperationComponent oldOperations = operations;
		operations = newOperations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__OPERATIONS, oldOperations, newOperations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperations(OperationComponent newOperations) {
		if (newOperations != operations) {
			NotificationChain msgs = null;
			if (operations != null)
				msgs = ((InternalEObject)operations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__OPERATIONS, null, msgs);
			if (newOperations != null)
				msgs = ((InternalEObject)newOperations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.FACET_MAP__OPERATIONS, null, msgs);
			msgs = basicSetOperations(newOperations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__OPERATIONS, newOperations, newOperations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container getViewOf() {
		if (viewOf != null && viewOf.eIsProxy()) {
			InternalEObject oldViewOf = (InternalEObject)viewOf;
			viewOf = (Container)eResolveProxy(oldViewOf);
			if (viewOf != oldViewOf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.FACET_MAP__VIEW_OF, oldViewOf, viewOf));
			}
		}
		return viewOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container basicGetViewOf() {
		return viewOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewOf(Container newViewOf) {
		Container oldViewOf = viewOf;
		viewOf = newViewOf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__VIEW_OF, oldViewOf, viewOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDisplayEmptyFacet() {
		return displayEmptyFacet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayEmptyFacet(boolean newDisplayEmptyFacet) {
		boolean oldDisplayEmptyFacet = displayEmptyFacet;
		displayEmptyFacet = newDisplayEmptyFacet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET, oldDisplayEmptyFacet, displayEmptyFacet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacetDisplayType getFacetDisplayType() {
		return facetDisplayType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacetDisplayType(FacetDisplayType newFacetDisplayType) {
		FacetDisplayType oldFacetDisplayType = facetDisplayType;
		facetDisplayType = newFacetDisplayType == null ? FACET_DISPLAY_TYPE_EDEFAULT : newFacetDisplayType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE, oldFacetDisplayType, facetDisplayType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldElement> getResultsAttributes() {
		if (getResultsAttributesBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.FACET_MAP.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.FACET_MAP, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getResultsAttributesBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getResultsAttributesBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<FieldElement> result = (Collection<FieldElement>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<FieldElement>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getResultsAttributes <em>Get Results Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultsAttributes
	 * @generated
	 */
	private static OCLExpression<EClassifier> getResultsAttributesBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getFields() {
		if (getFieldsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getFieldsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getFieldsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Field> result = (Collection<Field>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Field>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getFields <em>Get Fields</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFields
	 * @generated
	 */
	private static OCLExpression<EClassifier> getFieldsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDirectChildFields() {
		if (getDirectChildFieldsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getDirectChildFieldsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getDirectChildFieldsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Field> result = (Collection<Field>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Field>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getDirectChildFields <em>Get Direct Child Fields</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirectChildFields
	 * @generated
	 */
	private static OCLExpression<EClassifier> getDirectChildFieldsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractView> getInnerView() {
		if (getInnerViewBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getInnerViewBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getInnerViewBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AbstractView> result = (Collection<AbstractView>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AbstractView>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getInnerView <em>Get Inner View</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInnerView
	 * @generated
	 */
	private static OCLExpression<EClassifier> getInnerViewBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDisabledAndEnabledField() {
		if (getDisabledAndEnabledFieldBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(3);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getDisabledAndEnabledFieldBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getDisabledAndEnabledFieldBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Field> result = (Collection<Field>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Field>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getDisabledAndEnabledField <em>Get Disabled And Enabled Field</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabledAndEnabledField
	 * @generated
	 */
	private static OCLExpression<EClassifier> getDisabledAndEnabledFieldBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDisabledFields() {
		if (getDisabledFieldsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.ABSTRACT_VIEW.getEOperations().get(4);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.ABSTRACT_VIEW, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getDisabledFieldsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getDisabledFieldsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<Field> result = (Collection<Field>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<Field>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getDisabledFields <em>Get Disabled Fields</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabledFields
	 * @generated
	 */
	private static OCLExpression<EClassifier> getDisabledFieldsBodyOCL;

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
			case ViewPackage.FACET_MAP__STYLING:
				return basicSetStyling(null, msgs);
			case ViewPackage.FACET_MAP__TAGS:
				return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
			case ViewPackage.FACET_MAP__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
			case ViewPackage.FACET_MAP__METAINFO:
				return ((InternalEList<?>)getMetainfo()).basicRemove(otherEnd, msgs);
			case ViewPackage.FACET_MAP__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ViewPackage.FACET_MAP__DISABLED:
				return ((InternalEList<?>)getDisabled()).basicRemove(otherEnd, msgs);
			case ViewPackage.FACET_MAP__OPERATIONS:
				return basicSetOperations(null, msgs);
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
			case ViewPackage.FACET_MAP__STYLING:
				return getStyling();
			case ViewPackage.FACET_MAP__STEREOTYPES:
				return getStereotypes();
			case ViewPackage.FACET_MAP__TAGS:
				return getTags();
			case ViewPackage.FACET_MAP__COMMENTS:
				return getComments();
			case ViewPackage.FACET_MAP__DOCUMENTATION:
				return getDocumentation();
			case ViewPackage.FACET_MAP__DESCRIPTION:
				return getDescription();
			case ViewPackage.FACET_MAP__METAINFO:
				return getMetainfo();
			case ViewPackage.FACET_MAP__NAME:
				return getName();
			case ViewPackage.FACET_MAP__MAP_TO:
				if (resolve) return getMapTo();
				return basicGetMapTo();
			case ViewPackage.FACET_MAP__PREFIX:
				return getPrefix();
			case ViewPackage.FACET_MAP__SUFFIX:
				return getSuffix();
			case ViewPackage.FACET_MAP__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.FACET_MAP__CHILDREN:
				return getChildren();
			case ViewPackage.FACET_MAP__DISABLED:
				return getDisabled();
			case ViewPackage.FACET_MAP__OPERATIONS:
				return getOperations();
			case ViewPackage.FACET_MAP__VIEW_OF:
				if (resolve) return getViewOf();
				return basicGetViewOf();
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				return isDisplayEmptyFacet() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				return getFacetDisplayType();
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
			case ViewPackage.FACET_MAP__STYLING:
				setStyling((Styling)newValue);
				return;
			case ViewPackage.FACET_MAP__STEREOTYPES:
				getStereotypes().clear();
				getStereotypes().addAll((Collection<? extends Stereotype>)newValue);
				return;
			case ViewPackage.FACET_MAP__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends Tag>)newValue);
				return;
			case ViewPackage.FACET_MAP__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case ViewPackage.FACET_MAP__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case ViewPackage.FACET_MAP__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ViewPackage.FACET_MAP__METAINFO:
				getMetainfo().clear();
				getMetainfo().addAll((Collection<? extends MetaInfo>)newValue);
				return;
			case ViewPackage.FACET_MAP__NAME:
				setName((String)newValue);
				return;
			case ViewPackage.FACET_MAP__MAP_TO:
				setMapTo((ModelElement)newValue);
				return;
			case ViewPackage.FACET_MAP__PREFIX:
				setPrefix((String)newValue);
				return;
			case ViewPackage.FACET_MAP__SUFFIX:
				setSuffix((String)newValue);
				return;
			case ViewPackage.FACET_MAP__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.FACET_MAP__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends FieldElement>)newValue);
				return;
			case ViewPackage.FACET_MAP__DISABLED:
				getDisabled().clear();
				getDisabled().addAll((Collection<? extends FieldElement>)newValue);
				return;
			case ViewPackage.FACET_MAP__OPERATIONS:
				setOperations((OperationComponent)newValue);
				return;
			case ViewPackage.FACET_MAP__VIEW_OF:
				setViewOf((Container)newValue);
				return;
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				setDisplayEmptyFacet(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				setFacetDisplayType((FacetDisplayType)newValue);
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
			case ViewPackage.FACET_MAP__STYLING:
				setStyling((Styling)null);
				return;
			case ViewPackage.FACET_MAP__STEREOTYPES:
				getStereotypes().clear();
				return;
			case ViewPackage.FACET_MAP__TAGS:
				getTags().clear();
				return;
			case ViewPackage.FACET_MAP__COMMENTS:
				getComments().clear();
				return;
			case ViewPackage.FACET_MAP__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__METAINFO:
				getMetainfo().clear();
				return;
			case ViewPackage.FACET_MAP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__MAP_TO:
				setMapTo((ModelElement)null);
				return;
			case ViewPackage.FACET_MAP__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__SUFFIX:
				setSuffix(SUFFIX_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__HIDDEN:
				setHidden(HIDDEN_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__CHILDREN:
				getChildren().clear();
				return;
			case ViewPackage.FACET_MAP__DISABLED:
				getDisabled().clear();
				return;
			case ViewPackage.FACET_MAP__OPERATIONS:
				setOperations((OperationComponent)null);
				return;
			case ViewPackage.FACET_MAP__VIEW_OF:
				setViewOf((Container)null);
				return;
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				setDisplayEmptyFacet(DISPLAY_EMPTY_FACET_EDEFAULT);
				return;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				setFacetDisplayType(FACET_DISPLAY_TYPE_EDEFAULT);
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
			case ViewPackage.FACET_MAP__STYLING:
				return styling != null;
			case ViewPackage.FACET_MAP__STEREOTYPES:
				return stereotypes != null && !stereotypes.isEmpty();
			case ViewPackage.FACET_MAP__TAGS:
				return tags != null && !tags.isEmpty();
			case ViewPackage.FACET_MAP__COMMENTS:
				return comments != null && !comments.isEmpty();
			case ViewPackage.FACET_MAP__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case ViewPackage.FACET_MAP__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ViewPackage.FACET_MAP__METAINFO:
				return metainfo != null && !metainfo.isEmpty();
			case ViewPackage.FACET_MAP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ViewPackage.FACET_MAP__MAP_TO:
				return mapTo != null;
			case ViewPackage.FACET_MAP__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case ViewPackage.FACET_MAP__SUFFIX:
				return SUFFIX_EDEFAULT == null ? suffix != null : !SUFFIX_EDEFAULT.equals(suffix);
			case ViewPackage.FACET_MAP__HIDDEN:
				return hidden != HIDDEN_EDEFAULT;
			case ViewPackage.FACET_MAP__CHILDREN:
				return children != null && !children.isEmpty();
			case ViewPackage.FACET_MAP__DISABLED:
				return disabled != null && !disabled.isEmpty();
			case ViewPackage.FACET_MAP__OPERATIONS:
				return operations != null;
			case ViewPackage.FACET_MAP__VIEW_OF:
				return viewOf != null;
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
				return displayEmptyFacet != DISPLAY_EMPTY_FACET_EDEFAULT;
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				return facetDisplayType != FACET_DISPLAY_TYPE_EDEFAULT;
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
		if (baseClass == Stylable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FACET_MAP__STYLING: return ViewPackage.STYLABLE__STYLING;
				default: return -1;
			}
		}
		if (baseClass == ModelElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FACET_MAP__STEREOTYPES: return CommonPackage.MODEL_ELEMENT__STEREOTYPES;
				case ViewPackage.FACET_MAP__TAGS: return CommonPackage.MODEL_ELEMENT__TAGS;
				case ViewPackage.FACET_MAP__COMMENTS: return CommonPackage.MODEL_ELEMENT__COMMENTS;
				case ViewPackage.FACET_MAP__DOCUMENTATION: return CommonPackage.MODEL_ELEMENT__DOCUMENTATION;
				case ViewPackage.FACET_MAP__DESCRIPTION: return CommonPackage.MODEL_ELEMENT__DESCRIPTION;
				case ViewPackage.FACET_MAP__METAINFO: return CommonPackage.MODEL_ELEMENT__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FACET_MAP__NAME: return CommonPackage.NAMED_MODEL_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == FieldElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FACET_MAP__MAP_TO: return ViewPackage.FIELD_ELEMENT__MAP_TO;
				case ViewPackage.FACET_MAP__PREFIX: return ViewPackage.FIELD_ELEMENT__PREFIX;
				case ViewPackage.FACET_MAP__SUFFIX: return ViewPackage.FIELD_ELEMENT__SUFFIX;
				case ViewPackage.FACET_MAP__HIDDEN: return ViewPackage.FIELD_ELEMENT__HIDDEN;
				default: return -1;
			}
		}
		if (baseClass == FieldContainer.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FACET_MAP__CHILDREN: return ViewPackage.FIELD_CONTAINER__CHILDREN;
				case ViewPackage.FACET_MAP__DISABLED: return ViewPackage.FIELD_CONTAINER__DISABLED;
				default: return -1;
			}
		}
		if (baseClass == AbstractView.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FACET_MAP__OPERATIONS: return ViewPackage.ABSTRACT_VIEW__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == AbstractViewOf.class) {
			switch (derivedFeatureID) {
				case ViewPackage.FACET_MAP__VIEW_OF: return ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF;
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
		if (baseClass == Stylable.class) {
			switch (baseFeatureID) {
				case ViewPackage.STYLABLE__STYLING: return ViewPackage.FACET_MAP__STYLING;
				default: return -1;
			}
		}
		if (baseClass == ModelElement.class) {
			switch (baseFeatureID) {
				case CommonPackage.MODEL_ELEMENT__STEREOTYPES: return ViewPackage.FACET_MAP__STEREOTYPES;
				case CommonPackage.MODEL_ELEMENT__TAGS: return ViewPackage.FACET_MAP__TAGS;
				case CommonPackage.MODEL_ELEMENT__COMMENTS: return ViewPackage.FACET_MAP__COMMENTS;
				case CommonPackage.MODEL_ELEMENT__DOCUMENTATION: return ViewPackage.FACET_MAP__DOCUMENTATION;
				case CommonPackage.MODEL_ELEMENT__DESCRIPTION: return ViewPackage.FACET_MAP__DESCRIPTION;
				case CommonPackage.MODEL_ELEMENT__METAINFO: return ViewPackage.FACET_MAP__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (baseFeatureID) {
				case CommonPackage.NAMED_MODEL_ELEMENT__NAME: return ViewPackage.FACET_MAP__NAME;
				default: return -1;
			}
		}
		if (baseClass == FieldElement.class) {
			switch (baseFeatureID) {
				case ViewPackage.FIELD_ELEMENT__MAP_TO: return ViewPackage.FACET_MAP__MAP_TO;
				case ViewPackage.FIELD_ELEMENT__PREFIX: return ViewPackage.FACET_MAP__PREFIX;
				case ViewPackage.FIELD_ELEMENT__SUFFIX: return ViewPackage.FACET_MAP__SUFFIX;
				case ViewPackage.FIELD_ELEMENT__HIDDEN: return ViewPackage.FACET_MAP__HIDDEN;
				default: return -1;
			}
		}
		if (baseClass == FieldContainer.class) {
			switch (baseFeatureID) {
				case ViewPackage.FIELD_CONTAINER__CHILDREN: return ViewPackage.FACET_MAP__CHILDREN;
				case ViewPackage.FIELD_CONTAINER__DISABLED: return ViewPackage.FACET_MAP__DISABLED;
				default: return -1;
			}
		}
		if (baseClass == AbstractView.class) {
			switch (baseFeatureID) {
				case ViewPackage.ABSTRACT_VIEW__OPERATIONS: return ViewPackage.FACET_MAP__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == AbstractViewOf.class) {
			switch (baseFeatureID) {
				case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF: return ViewPackage.FACET_MAP__VIEW_OF;
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
		result.append(", displayEmptyFacet: ");
		result.append(displayEmptyFacet);
		result.append(", facetDisplayType: ");
		result.append(facetDisplayType);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //FacetMapImpl
