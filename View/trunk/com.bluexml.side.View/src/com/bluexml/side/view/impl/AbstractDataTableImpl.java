/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;

import com.bluexml.side.common.Comment;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.Container;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.Tag;

import com.bluexml.side.view.AbstractDataTable;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.FieldGroup;
import com.bluexml.side.view.Paginable;
import com.bluexml.side.view.Paging;
import com.bluexml.side.view.Stylable;
import com.bluexml.side.view.Styling;
import com.bluexml.side.view.ViewPackage;

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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Data Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getStereotypes <em>Stereotypes</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getMetainfo <em>Metainfo</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getStyling <em>Styling</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getMapTo <em>Map To</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getSuffix <em>Suffix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#isHidden <em>Hidden</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getViewOf <em>View Of</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getInnerView <em>Inner View</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getPaging <em>Paging</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getHaveRowActions <em>Have Row Actions</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getHaveSelectActions <em>Have Select Actions</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.AbstractDataTableImpl#getHaveDefaultColActions <em>Have Default Col Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractDataTableImpl extends DataTableElementImpl implements AbstractDataTable {
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
	 * The cached value of the '{@link #getStyling() <em>Styling</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyling()
	 * @generated
	 * @ordered
	 */
	protected Styling styling;

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
	 * The cached value of the '{@link #getViewOf() <em>View Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewOf()
	 * @generated
	 * @ordered
	 */
	protected Container viewOf;

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
	 * The cached value of the '{@link #getInnerView() <em>Inner View</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInnerView()
	 * @generated
	 * @ordered
	 */
	protected AbstractView innerView;

	/**
	 * The cached value of the '{@link #getPaging() <em>Paging</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaging()
	 * @generated
	 * @ordered
	 */
	protected Paging paging;

	/**
	 * The cached value of the '{@link #getHaveRowActions() <em>Have Row Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHaveRowActions()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent haveRowActions;

	/**
	 * The cached value of the '{@link #getHaveSelectActions() <em>Have Select Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHaveSelectActions()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent haveSelectActions;

	/**
	 * The cached value of the '{@link #getHaveDefaultColActions() <em>Have Default Col Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHaveDefaultColActions()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent haveDefaultColActions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractDataTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.ABSTRACT_DATA_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Stereotype> getStereotypes() {
		if (stereotypes == null) {
			stereotypes = new EObjectResolvingEList<Stereotype>(Stereotype.class, this, ViewPackage.ABSTRACT_DATA_TABLE__STEREOTYPES);
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
			tags = new EObjectContainmentEList<Tag>(Tag.class, this, ViewPackage.ABSTRACT_DATA_TABLE__TAGS);
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
			comments = new EObjectContainmentEList<Comment>(Comment.class, this, ViewPackage.ABSTRACT_DATA_TABLE__COMMENTS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__DOCUMENTATION, oldDocumentation, documentation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaInfo> getMetainfo() {
		if (metainfo == null) {
			metainfo = new EObjectContainmentEList<MetaInfo>(MetaInfo.class, this, ViewPackage.ABSTRACT_DATA_TABLE__METAINFO);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__STYLING, oldStyling, newStyling);
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
				msgs = ((InternalEObject)styling).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__STYLING, null, msgs);
			if (newStyling != null)
				msgs = ((InternalEObject)newStyling).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__STYLING, null, msgs);
			msgs = basicSetStyling(newStyling, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__STYLING, newStyling, newStyling));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.ABSTRACT_DATA_TABLE__MAP_TO, oldMapTo, mapTo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__MAP_TO, oldMapTo, mapTo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__PREFIX, oldPrefix, prefix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__SUFFIX, oldSuffix, suffix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HIDDEN, oldHidden, hidden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldElement> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.ABSTRACT_DATA_TABLE__CHILDREN);
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
			disabled = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.ABSTRACT_DATA_TABLE__DISABLED);
		}
		return disabled;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.ABSTRACT_DATA_TABLE__VIEW_OF, oldViewOf, viewOf));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__VIEW_OF, oldViewOf, viewOf));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS, oldOperations, newOperations);
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
				msgs = ((InternalEObject)operations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS, null, msgs);
			if (newOperations != null)
				msgs = ((InternalEObject)newOperations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS, null, msgs);
			msgs = basicSetOperations(newOperations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS, newOperations, newOperations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractView getInnerView() {
		return innerView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInnerView(AbstractView newInnerView, NotificationChain msgs) {
		AbstractView oldInnerView = innerView;
		innerView = newInnerView;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW, oldInnerView, newInnerView);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInnerView(AbstractView newInnerView) {
		if (newInnerView != innerView) {
			NotificationChain msgs = null;
			if (innerView != null)
				msgs = ((InternalEObject)innerView).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW, null, msgs);
			if (newInnerView != null)
				msgs = ((InternalEObject)newInnerView).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW, null, msgs);
			msgs = basicSetInnerView(newInnerView, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW, newInnerView, newInnerView));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Paging getPaging() {
		return paging;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPaging(Paging newPaging, NotificationChain msgs) {
		Paging oldPaging = paging;
		paging = newPaging;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__PAGING, oldPaging, newPaging);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPaging(Paging newPaging) {
		if (newPaging != paging) {
			NotificationChain msgs = null;
			if (paging != null)
				msgs = ((InternalEObject)paging).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__PAGING, null, msgs);
			if (newPaging != null)
				msgs = ((InternalEObject)newPaging).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__PAGING, null, msgs);
			msgs = basicSetPaging(newPaging, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__PAGING, newPaging, newPaging));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getHaveRowActions() {
		return haveRowActions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHaveRowActions(OperationComponent newHaveRowActions, NotificationChain msgs) {
		OperationComponent oldHaveRowActions = haveRowActions;
		haveRowActions = newHaveRowActions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS, oldHaveRowActions, newHaveRowActions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHaveRowActions(OperationComponent newHaveRowActions) {
		if (newHaveRowActions != haveRowActions) {
			NotificationChain msgs = null;
			if (haveRowActions != null)
				msgs = ((InternalEObject)haveRowActions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS, null, msgs);
			if (newHaveRowActions != null)
				msgs = ((InternalEObject)newHaveRowActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS, null, msgs);
			msgs = basicSetHaveRowActions(newHaveRowActions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS, newHaveRowActions, newHaveRowActions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getHaveSelectActions() {
		return haveSelectActions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHaveSelectActions(OperationComponent newHaveSelectActions, NotificationChain msgs) {
		OperationComponent oldHaveSelectActions = haveSelectActions;
		haveSelectActions = newHaveSelectActions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS, oldHaveSelectActions, newHaveSelectActions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHaveSelectActions(OperationComponent newHaveSelectActions) {
		if (newHaveSelectActions != haveSelectActions) {
			NotificationChain msgs = null;
			if (haveSelectActions != null)
				msgs = ((InternalEObject)haveSelectActions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS, null, msgs);
			if (newHaveSelectActions != null)
				msgs = ((InternalEObject)newHaveSelectActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS, null, msgs);
			msgs = basicSetHaveSelectActions(newHaveSelectActions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS, newHaveSelectActions, newHaveSelectActions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getHaveDefaultColActions() {
		return haveDefaultColActions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHaveDefaultColActions(OperationComponent newHaveDefaultColActions, NotificationChain msgs) {
		OperationComponent oldHaveDefaultColActions = haveDefaultColActions;
		haveDefaultColActions = newHaveDefaultColActions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS, oldHaveDefaultColActions, newHaveDefaultColActions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHaveDefaultColActions(OperationComponent newHaveDefaultColActions) {
		if (newHaveDefaultColActions != haveDefaultColActions) {
			NotificationChain msgs = null;
			if (haveDefaultColActions != null)
				msgs = ((InternalEObject)haveDefaultColActions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS, null, msgs);
			if (newHaveDefaultColActions != null)
				msgs = ((InternalEObject)newHaveDefaultColActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS, null, msgs);
			msgs = basicSetHaveDefaultColActions(newHaveDefaultColActions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS, newHaveDefaultColActions, newHaveDefaultColActions));
	}

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
			case ViewPackage.ABSTRACT_DATA_TABLE__TAGS:
				return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__METAINFO:
				return ((InternalEList<?>)getMetainfo()).basicRemove(otherEnd, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__STYLING:
				return basicSetStyling(null, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__DISABLED:
				return ((InternalEList<?>)getDisabled()).basicRemove(otherEnd, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS:
				return basicSetOperations(null, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW:
				return basicSetInnerView(null, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				return basicSetPaging(null, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				return basicSetHaveRowActions(null, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				return basicSetHaveSelectActions(null, msgs);
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				return basicSetHaveDefaultColActions(null, msgs);
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
			case ViewPackage.ABSTRACT_DATA_TABLE__STEREOTYPES:
				return getStereotypes();
			case ViewPackage.ABSTRACT_DATA_TABLE__TAGS:
				return getTags();
			case ViewPackage.ABSTRACT_DATA_TABLE__COMMENTS:
				return getComments();
			case ViewPackage.ABSTRACT_DATA_TABLE__DOCUMENTATION:
				return getDocumentation();
			case ViewPackage.ABSTRACT_DATA_TABLE__DESCRIPTION:
				return getDescription();
			case ViewPackage.ABSTRACT_DATA_TABLE__METAINFO:
				return getMetainfo();
			case ViewPackage.ABSTRACT_DATA_TABLE__NAME:
				return getName();
			case ViewPackage.ABSTRACT_DATA_TABLE__STYLING:
				return getStyling();
			case ViewPackage.ABSTRACT_DATA_TABLE__MAP_TO:
				if (resolve) return getMapTo();
				return basicGetMapTo();
			case ViewPackage.ABSTRACT_DATA_TABLE__PREFIX:
				return getPrefix();
			case ViewPackage.ABSTRACT_DATA_TABLE__SUFFIX:
				return getSuffix();
			case ViewPackage.ABSTRACT_DATA_TABLE__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.ABSTRACT_DATA_TABLE__CHILDREN:
				return getChildren();
			case ViewPackage.ABSTRACT_DATA_TABLE__DISABLED:
				return getDisabled();
			case ViewPackage.ABSTRACT_DATA_TABLE__VIEW_OF:
				if (resolve) return getViewOf();
				return basicGetViewOf();
			case ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS:
				return getOperations();
			case ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW:
				return getInnerView();
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				return getPaging();
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				return getHaveRowActions();
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				return getHaveSelectActions();
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				return getHaveDefaultColActions();
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
			case ViewPackage.ABSTRACT_DATA_TABLE__STEREOTYPES:
				getStereotypes().clear();
				getStereotypes().addAll((Collection<? extends Stereotype>)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends Tag>)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__METAINFO:
				getMetainfo().clear();
				getMetainfo().addAll((Collection<? extends MetaInfo>)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__NAME:
				setName((String)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__STYLING:
				setStyling((Styling)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__MAP_TO:
				setMapTo((ModelElement)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__PREFIX:
				setPrefix((String)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__SUFFIX:
				setSuffix((String)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends FieldElement>)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__DISABLED:
				getDisabled().clear();
				getDisabled().addAll((Collection<? extends FieldElement>)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__VIEW_OF:
				setViewOf((Container)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS:
				setOperations((OperationComponent)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW:
				setInnerView((AbstractView)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				setPaging((Paging)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				setHaveRowActions((OperationComponent)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				setHaveSelectActions((OperationComponent)newValue);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				setHaveDefaultColActions((OperationComponent)newValue);
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
			case ViewPackage.ABSTRACT_DATA_TABLE__STEREOTYPES:
				getStereotypes().clear();
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__TAGS:
				getTags().clear();
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__COMMENTS:
				getComments().clear();
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__METAINFO:
				getMetainfo().clear();
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__STYLING:
				setStyling((Styling)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__MAP_TO:
				setMapTo((ModelElement)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__SUFFIX:
				setSuffix(SUFFIX_EDEFAULT);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HIDDEN:
				setHidden(HIDDEN_EDEFAULT);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__CHILDREN:
				getChildren().clear();
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__DISABLED:
				getDisabled().clear();
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__VIEW_OF:
				setViewOf((Container)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS:
				setOperations((OperationComponent)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW:
				setInnerView((AbstractView)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				setPaging((Paging)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				setHaveRowActions((OperationComponent)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				setHaveSelectActions((OperationComponent)null);
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				setHaveDefaultColActions((OperationComponent)null);
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
			case ViewPackage.ABSTRACT_DATA_TABLE__STEREOTYPES:
				return stereotypes != null && !stereotypes.isEmpty();
			case ViewPackage.ABSTRACT_DATA_TABLE__TAGS:
				return tags != null && !tags.isEmpty();
			case ViewPackage.ABSTRACT_DATA_TABLE__COMMENTS:
				return comments != null && !comments.isEmpty();
			case ViewPackage.ABSTRACT_DATA_TABLE__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case ViewPackage.ABSTRACT_DATA_TABLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ViewPackage.ABSTRACT_DATA_TABLE__METAINFO:
				return metainfo != null && !metainfo.isEmpty();
			case ViewPackage.ABSTRACT_DATA_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ViewPackage.ABSTRACT_DATA_TABLE__STYLING:
				return styling != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__MAP_TO:
				return mapTo != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case ViewPackage.ABSTRACT_DATA_TABLE__SUFFIX:
				return SUFFIX_EDEFAULT == null ? suffix != null : !SUFFIX_EDEFAULT.equals(suffix);
			case ViewPackage.ABSTRACT_DATA_TABLE__HIDDEN:
				return hidden != HIDDEN_EDEFAULT;
			case ViewPackage.ABSTRACT_DATA_TABLE__CHILDREN:
				return children != null && !children.isEmpty();
			case ViewPackage.ABSTRACT_DATA_TABLE__DISABLED:
				return disabled != null && !disabled.isEmpty();
			case ViewPackage.ABSTRACT_DATA_TABLE__VIEW_OF:
				return viewOf != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS:
				return operations != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW:
				return innerView != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
				return paging != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
				return haveRowActions != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
				return haveSelectActions != null;
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
				return haveDefaultColActions != null;
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
				case ViewPackage.ABSTRACT_DATA_TABLE__STEREOTYPES: return CommonPackage.MODEL_ELEMENT__STEREOTYPES;
				case ViewPackage.ABSTRACT_DATA_TABLE__TAGS: return CommonPackage.MODEL_ELEMENT__TAGS;
				case ViewPackage.ABSTRACT_DATA_TABLE__COMMENTS: return CommonPackage.MODEL_ELEMENT__COMMENTS;
				case ViewPackage.ABSTRACT_DATA_TABLE__DOCUMENTATION: return CommonPackage.MODEL_ELEMENT__DOCUMENTATION;
				case ViewPackage.ABSTRACT_DATA_TABLE__DESCRIPTION: return CommonPackage.MODEL_ELEMENT__DESCRIPTION;
				case ViewPackage.ABSTRACT_DATA_TABLE__METAINFO: return CommonPackage.MODEL_ELEMENT__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.ABSTRACT_DATA_TABLE__NAME: return CommonPackage.NAMED_MODEL_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == Stylable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.ABSTRACT_DATA_TABLE__STYLING: return ViewPackage.STYLABLE__STYLING;
				default: return -1;
			}
		}
		if (baseClass == FieldElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.ABSTRACT_DATA_TABLE__MAP_TO: return ViewPackage.FIELD_ELEMENT__MAP_TO;
				case ViewPackage.ABSTRACT_DATA_TABLE__PREFIX: return ViewPackage.FIELD_ELEMENT__PREFIX;
				case ViewPackage.ABSTRACT_DATA_TABLE__SUFFIX: return ViewPackage.FIELD_ELEMENT__SUFFIX;
				case ViewPackage.ABSTRACT_DATA_TABLE__HIDDEN: return ViewPackage.FIELD_ELEMENT__HIDDEN;
				default: return -1;
			}
		}
		if (baseClass == FieldGroup.class) {
			switch (derivedFeatureID) {
				case ViewPackage.ABSTRACT_DATA_TABLE__CHILDREN: return ViewPackage.FIELD_GROUP__CHILDREN;
				case ViewPackage.ABSTRACT_DATA_TABLE__DISABLED: return ViewPackage.FIELD_GROUP__DISABLED;
				default: return -1;
			}
		}
		if (baseClass == AbstractView.class) {
			switch (derivedFeatureID) {
				case ViewPackage.ABSTRACT_DATA_TABLE__VIEW_OF: return ViewPackage.ABSTRACT_VIEW__VIEW_OF;
				case ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS: return ViewPackage.ABSTRACT_VIEW__OPERATIONS;
				case ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW: return ViewPackage.ABSTRACT_VIEW__INNER_VIEW;
				default: return -1;
			}
		}
		if (baseClass == Paginable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.ABSTRACT_DATA_TABLE__PAGING: return ViewPackage.PAGINABLE__PAGING;
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
				case CommonPackage.MODEL_ELEMENT__STEREOTYPES: return ViewPackage.ABSTRACT_DATA_TABLE__STEREOTYPES;
				case CommonPackage.MODEL_ELEMENT__TAGS: return ViewPackage.ABSTRACT_DATA_TABLE__TAGS;
				case CommonPackage.MODEL_ELEMENT__COMMENTS: return ViewPackage.ABSTRACT_DATA_TABLE__COMMENTS;
				case CommonPackage.MODEL_ELEMENT__DOCUMENTATION: return ViewPackage.ABSTRACT_DATA_TABLE__DOCUMENTATION;
				case CommonPackage.MODEL_ELEMENT__DESCRIPTION: return ViewPackage.ABSTRACT_DATA_TABLE__DESCRIPTION;
				case CommonPackage.MODEL_ELEMENT__METAINFO: return ViewPackage.ABSTRACT_DATA_TABLE__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (baseFeatureID) {
				case CommonPackage.NAMED_MODEL_ELEMENT__NAME: return ViewPackage.ABSTRACT_DATA_TABLE__NAME;
				default: return -1;
			}
		}
		if (baseClass == Stylable.class) {
			switch (baseFeatureID) {
				case ViewPackage.STYLABLE__STYLING: return ViewPackage.ABSTRACT_DATA_TABLE__STYLING;
				default: return -1;
			}
		}
		if (baseClass == FieldElement.class) {
			switch (baseFeatureID) {
				case ViewPackage.FIELD_ELEMENT__MAP_TO: return ViewPackage.ABSTRACT_DATA_TABLE__MAP_TO;
				case ViewPackage.FIELD_ELEMENT__PREFIX: return ViewPackage.ABSTRACT_DATA_TABLE__PREFIX;
				case ViewPackage.FIELD_ELEMENT__SUFFIX: return ViewPackage.ABSTRACT_DATA_TABLE__SUFFIX;
				case ViewPackage.FIELD_ELEMENT__HIDDEN: return ViewPackage.ABSTRACT_DATA_TABLE__HIDDEN;
				default: return -1;
			}
		}
		if (baseClass == FieldGroup.class) {
			switch (baseFeatureID) {
				case ViewPackage.FIELD_GROUP__CHILDREN: return ViewPackage.ABSTRACT_DATA_TABLE__CHILDREN;
				case ViewPackage.FIELD_GROUP__DISABLED: return ViewPackage.ABSTRACT_DATA_TABLE__DISABLED;
				default: return -1;
			}
		}
		if (baseClass == AbstractView.class) {
			switch (baseFeatureID) {
				case ViewPackage.ABSTRACT_VIEW__VIEW_OF: return ViewPackage.ABSTRACT_DATA_TABLE__VIEW_OF;
				case ViewPackage.ABSTRACT_VIEW__OPERATIONS: return ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS;
				case ViewPackage.ABSTRACT_VIEW__INNER_VIEW: return ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW;
				default: return -1;
			}
		}
		if (baseClass == Paginable.class) {
			switch (baseFeatureID) {
				case ViewPackage.PAGINABLE__PAGING: return ViewPackage.ABSTRACT_DATA_TABLE__PAGING;
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
} //AbstractDataTableImpl
