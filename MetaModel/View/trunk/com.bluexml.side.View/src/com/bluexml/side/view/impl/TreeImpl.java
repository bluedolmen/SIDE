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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.Tag;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.Editable;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldContainer;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.Filterable;
import com.bluexml.side.view.Filtering;
import com.bluexml.side.view.Movable;
import com.bluexml.side.view.Stylable;
import com.bluexml.side.view.Styling;
import com.bluexml.side.view.Sortable;
import com.bluexml.side.view.Sorting;
import com.bluexml.side.view.Tree;
import com.bluexml.side.view.ViewPackage;
import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tree</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#isEditable <em>Editable</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#isMovable <em>Movable</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getFiltering <em>Filtering</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getStyling <em>Styling</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getStereotypes <em>Stereotypes</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getMetainfo <em>Metainfo</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getMapTo <em>Map To</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getSuffix <em>Suffix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#isHidden <em>Hidden</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getViewOf <em>View Of</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getNodeOperations <em>Node Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getNodeValue <em>Node Value</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getDefaultDepth <em>Default Depth</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.TreeImpl#getMaxDepth <em>Max Depth</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TreeImpl extends SortableImpl implements Tree {
	/**
	 * The default value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EDITABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected boolean editable = EDITABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isMovable() <em>Movable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMovable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MOVABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMovable() <em>Movable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMovable()
	 * @generated
	 * @ordered
	 */
	protected boolean movable = MOVABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFiltering() <em>Filtering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFiltering()
	 * @generated
	 * @ordered
	 */
	protected Filtering filtering;

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
	 * The cached value of the '{@link #getNodeOperations() <em>Node Operations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeOperations()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent nodeOperations;

	/**
	 * The cached value of the '{@link #getNodeValue() <em>Node Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeValue()
	 * @generated
	 * @ordered
	 */
	protected FieldElement nodeValue;

	/**
	 * The default value of the '{@link #getDefaultDepth() <em>Default Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultDepth()
	 * @generated
	 * @ordered
	 */
	protected static final int DEFAULT_DEPTH_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getDefaultDepth() <em>Default Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultDepth()
	 * @generated
	 * @ordered
	 */
	protected int defaultDepth = DEFAULT_DEPTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxDepth() <em>Max Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDepth()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_DEPTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxDepth() <em>Max Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDepth()
	 * @generated
	 * @ordered
	 */
	protected int maxDepth = MAX_DEPTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TreeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.TREE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditable(boolean newEditable) {
		boolean oldEditable = editable;
		editable = newEditable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__EDITABLE, oldEditable, editable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMovable() {
		return movable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMovable(boolean newMovable) {
		boolean oldMovable = movable;
		movable = newMovable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__MOVABLE, oldMovable, movable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filtering getFiltering() {
		return filtering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFiltering(Filtering newFiltering, NotificationChain msgs) {
		Filtering oldFiltering = filtering;
		filtering = newFiltering;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__FILTERING, oldFiltering, newFiltering);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFiltering(Filtering newFiltering) {
		if (newFiltering != filtering) {
			NotificationChain msgs = null;
			if (filtering != null)
				msgs = ((InternalEObject)filtering).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__FILTERING, null, msgs);
			if (newFiltering != null)
				msgs = ((InternalEObject)newFiltering).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__FILTERING, null, msgs);
			msgs = basicSetFiltering(newFiltering, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__FILTERING, newFiltering, newFiltering));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__STYLING, oldStyling, newStyling);
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
				msgs = ((InternalEObject)styling).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__STYLING, null, msgs);
			if (newStyling != null)
				msgs = ((InternalEObject)newStyling).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__STYLING, null, msgs);
			msgs = basicSetStyling(newStyling, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__STYLING, newStyling, newStyling));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Stereotype> getStereotypes() {
		if (stereotypes == null) {
			stereotypes = new EObjectResolvingEList<Stereotype>(Stereotype.class, this, ViewPackage.TREE__STEREOTYPES);
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
			tags = new EObjectContainmentEList<Tag>(Tag.class, this, ViewPackage.TREE__TAGS);
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
			comments = new EObjectContainmentEList<Comment>(Comment.class, this, ViewPackage.TREE__COMMENTS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__DOCUMENTATION, oldDocumentation, documentation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaInfo> getMetainfo() {
		if (metainfo == null) {
			metainfo = new EObjectContainmentEList<MetaInfo>(MetaInfo.class, this, ViewPackage.TREE__METAINFO);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__NAME, oldName, name));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.TREE__MAP_TO, oldMapTo, mapTo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__MAP_TO, oldMapTo, mapTo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__PREFIX, oldPrefix, prefix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__SUFFIX, oldSuffix, suffix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__HIDDEN, oldHidden, hidden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldElement> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.TREE__CHILDREN);
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
			disabled = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.TREE__DISABLED);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__OPERATIONS, oldOperations, newOperations);
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
				msgs = ((InternalEObject)operations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__OPERATIONS, null, msgs);
			if (newOperations != null)
				msgs = ((InternalEObject)newOperations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__OPERATIONS, null, msgs);
			msgs = basicSetOperations(newOperations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__OPERATIONS, newOperations, newOperations));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.TREE__VIEW_OF, oldViewOf, viewOf));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__VIEW_OF, oldViewOf, viewOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getNodeOperations() {
		return nodeOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNodeOperations(OperationComponent newNodeOperations, NotificationChain msgs) {
		OperationComponent oldNodeOperations = nodeOperations;
		nodeOperations = newNodeOperations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__NODE_OPERATIONS, oldNodeOperations, newNodeOperations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeOperations(OperationComponent newNodeOperations) {
		if (newNodeOperations != nodeOperations) {
			NotificationChain msgs = null;
			if (nodeOperations != null)
				msgs = ((InternalEObject)nodeOperations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__NODE_OPERATIONS, null, msgs);
			if (newNodeOperations != null)
				msgs = ((InternalEObject)newNodeOperations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__NODE_OPERATIONS, null, msgs);
			msgs = basicSetNodeOperations(newNodeOperations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__NODE_OPERATIONS, newNodeOperations, newNodeOperations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldElement getNodeValue() {
		return nodeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNodeValue(FieldElement newNodeValue, NotificationChain msgs) {
		FieldElement oldNodeValue = nodeValue;
		nodeValue = newNodeValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__NODE_VALUE, oldNodeValue, newNodeValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeValue(FieldElement newNodeValue) {
		if (newNodeValue != nodeValue) {
			NotificationChain msgs = null;
			if (nodeValue != null)
				msgs = ((InternalEObject)nodeValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__NODE_VALUE, null, msgs);
			if (newNodeValue != null)
				msgs = ((InternalEObject)newNodeValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.TREE__NODE_VALUE, null, msgs);
			msgs = basicSetNodeValue(newNodeValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__NODE_VALUE, newNodeValue, newNodeValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDefaultDepth() {
		return defaultDepth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultDepth(int newDefaultDepth) {
		int oldDefaultDepth = defaultDepth;
		defaultDepth = newDefaultDepth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__DEFAULT_DEPTH, oldDefaultDepth, defaultDepth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxDepth() {
		return maxDepth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxDepth(int newMaxDepth) {
		int oldMaxDepth = maxDepth;
		maxDepth = newMaxDepth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TREE__MAX_DEPTH, oldMaxDepth, maxDepth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getFields() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDirectChildFields() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractView> getInnerView() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDisabledAndEnabledField() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getDisabledFields() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullName() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDocumentationOrName() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescriptionOrName() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.TREE__FILTERING:
				return basicSetFiltering(null, msgs);
			case ViewPackage.TREE__STYLING:
				return basicSetStyling(null, msgs);
			case ViewPackage.TREE__TAGS:
				return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
			case ViewPackage.TREE__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
			case ViewPackage.TREE__METAINFO:
				return ((InternalEList<?>)getMetainfo()).basicRemove(otherEnd, msgs);
			case ViewPackage.TREE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ViewPackage.TREE__DISABLED:
				return ((InternalEList<?>)getDisabled()).basicRemove(otherEnd, msgs);
			case ViewPackage.TREE__OPERATIONS:
				return basicSetOperations(null, msgs);
			case ViewPackage.TREE__NODE_OPERATIONS:
				return basicSetNodeOperations(null, msgs);
			case ViewPackage.TREE__NODE_VALUE:
				return basicSetNodeValue(null, msgs);
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
			case ViewPackage.TREE__EDITABLE:
				return isEditable() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.TREE__MOVABLE:
				return isMovable() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.TREE__FILTERING:
				return getFiltering();
			case ViewPackage.TREE__STYLING:
				return getStyling();
			case ViewPackage.TREE__STEREOTYPES:
				return getStereotypes();
			case ViewPackage.TREE__TAGS:
				return getTags();
			case ViewPackage.TREE__COMMENTS:
				return getComments();
			case ViewPackage.TREE__DOCUMENTATION:
				return getDocumentation();
			case ViewPackage.TREE__DESCRIPTION:
				return getDescription();
			case ViewPackage.TREE__METAINFO:
				return getMetainfo();
			case ViewPackage.TREE__NAME:
				return getName();
			case ViewPackage.TREE__MAP_TO:
				if (resolve) return getMapTo();
				return basicGetMapTo();
			case ViewPackage.TREE__PREFIX:
				return getPrefix();
			case ViewPackage.TREE__SUFFIX:
				return getSuffix();
			case ViewPackage.TREE__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.TREE__CHILDREN:
				return getChildren();
			case ViewPackage.TREE__DISABLED:
				return getDisabled();
			case ViewPackage.TREE__OPERATIONS:
				return getOperations();
			case ViewPackage.TREE__VIEW_OF:
				if (resolve) return getViewOf();
				return basicGetViewOf();
			case ViewPackage.TREE__NODE_OPERATIONS:
				return getNodeOperations();
			case ViewPackage.TREE__NODE_VALUE:
				return getNodeValue();
			case ViewPackage.TREE__DEFAULT_DEPTH:
				return new Integer(getDefaultDepth());
			case ViewPackage.TREE__MAX_DEPTH:
				return new Integer(getMaxDepth());
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
			case ViewPackage.TREE__EDITABLE:
				setEditable(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.TREE__MOVABLE:
				setMovable(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.TREE__FILTERING:
				setFiltering((Filtering)newValue);
				return;
			case ViewPackage.TREE__STYLING:
				setStyling((Styling)newValue);
				return;
			case ViewPackage.TREE__STEREOTYPES:
				getStereotypes().clear();
				getStereotypes().addAll((Collection<? extends Stereotype>)newValue);
				return;
			case ViewPackage.TREE__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends Tag>)newValue);
				return;
			case ViewPackage.TREE__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case ViewPackage.TREE__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case ViewPackage.TREE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ViewPackage.TREE__METAINFO:
				getMetainfo().clear();
				getMetainfo().addAll((Collection<? extends MetaInfo>)newValue);
				return;
			case ViewPackage.TREE__NAME:
				setName((String)newValue);
				return;
			case ViewPackage.TREE__MAP_TO:
				setMapTo((ModelElement)newValue);
				return;
			case ViewPackage.TREE__PREFIX:
				setPrefix((String)newValue);
				return;
			case ViewPackage.TREE__SUFFIX:
				setSuffix((String)newValue);
				return;
			case ViewPackage.TREE__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.TREE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends FieldElement>)newValue);
				return;
			case ViewPackage.TREE__DISABLED:
				getDisabled().clear();
				getDisabled().addAll((Collection<? extends FieldElement>)newValue);
				return;
			case ViewPackage.TREE__OPERATIONS:
				setOperations((OperationComponent)newValue);
				return;
			case ViewPackage.TREE__VIEW_OF:
				setViewOf((Container)newValue);
				return;
			case ViewPackage.TREE__NODE_OPERATIONS:
				setNodeOperations((OperationComponent)newValue);
				return;
			case ViewPackage.TREE__NODE_VALUE:
				setNodeValue((FieldElement)newValue);
				return;
			case ViewPackage.TREE__DEFAULT_DEPTH:
				setDefaultDepth(((Integer)newValue).intValue());
				return;
			case ViewPackage.TREE__MAX_DEPTH:
				setMaxDepth(((Integer)newValue).intValue());
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
			case ViewPackage.TREE__EDITABLE:
				setEditable(EDITABLE_EDEFAULT);
				return;
			case ViewPackage.TREE__MOVABLE:
				setMovable(MOVABLE_EDEFAULT);
				return;
			case ViewPackage.TREE__FILTERING:
				setFiltering((Filtering)null);
				return;
			case ViewPackage.TREE__STYLING:
				setStyling((Styling)null);
				return;
			case ViewPackage.TREE__STEREOTYPES:
				getStereotypes().clear();
				return;
			case ViewPackage.TREE__TAGS:
				getTags().clear();
				return;
			case ViewPackage.TREE__COMMENTS:
				getComments().clear();
				return;
			case ViewPackage.TREE__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case ViewPackage.TREE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ViewPackage.TREE__METAINFO:
				getMetainfo().clear();
				return;
			case ViewPackage.TREE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ViewPackage.TREE__MAP_TO:
				setMapTo((ModelElement)null);
				return;
			case ViewPackage.TREE__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case ViewPackage.TREE__SUFFIX:
				setSuffix(SUFFIX_EDEFAULT);
				return;
			case ViewPackage.TREE__HIDDEN:
				setHidden(HIDDEN_EDEFAULT);
				return;
			case ViewPackage.TREE__CHILDREN:
				getChildren().clear();
				return;
			case ViewPackage.TREE__DISABLED:
				getDisabled().clear();
				return;
			case ViewPackage.TREE__OPERATIONS:
				setOperations((OperationComponent)null);
				return;
			case ViewPackage.TREE__VIEW_OF:
				setViewOf((Container)null);
				return;
			case ViewPackage.TREE__NODE_OPERATIONS:
				setNodeOperations((OperationComponent)null);
				return;
			case ViewPackage.TREE__NODE_VALUE:
				setNodeValue((FieldElement)null);
				return;
			case ViewPackage.TREE__DEFAULT_DEPTH:
				setDefaultDepth(DEFAULT_DEPTH_EDEFAULT);
				return;
			case ViewPackage.TREE__MAX_DEPTH:
				setMaxDepth(MAX_DEPTH_EDEFAULT);
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
			case ViewPackage.TREE__EDITABLE:
				return editable != EDITABLE_EDEFAULT;
			case ViewPackage.TREE__MOVABLE:
				return movable != MOVABLE_EDEFAULT;
			case ViewPackage.TREE__FILTERING:
				return filtering != null;
			case ViewPackage.TREE__STYLING:
				return styling != null;
			case ViewPackage.TREE__STEREOTYPES:
				return stereotypes != null && !stereotypes.isEmpty();
			case ViewPackage.TREE__TAGS:
				return tags != null && !tags.isEmpty();
			case ViewPackage.TREE__COMMENTS:
				return comments != null && !comments.isEmpty();
			case ViewPackage.TREE__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case ViewPackage.TREE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ViewPackage.TREE__METAINFO:
				return metainfo != null && !metainfo.isEmpty();
			case ViewPackage.TREE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ViewPackage.TREE__MAP_TO:
				return mapTo != null;
			case ViewPackage.TREE__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case ViewPackage.TREE__SUFFIX:
				return SUFFIX_EDEFAULT == null ? suffix != null : !SUFFIX_EDEFAULT.equals(suffix);
			case ViewPackage.TREE__HIDDEN:
				return hidden != HIDDEN_EDEFAULT;
			case ViewPackage.TREE__CHILDREN:
				return children != null && !children.isEmpty();
			case ViewPackage.TREE__DISABLED:
				return disabled != null && !disabled.isEmpty();
			case ViewPackage.TREE__OPERATIONS:
				return operations != null;
			case ViewPackage.TREE__VIEW_OF:
				return viewOf != null;
			case ViewPackage.TREE__NODE_OPERATIONS:
				return nodeOperations != null;
			case ViewPackage.TREE__NODE_VALUE:
				return nodeValue != null;
			case ViewPackage.TREE__DEFAULT_DEPTH:
				return defaultDepth != DEFAULT_DEPTH_EDEFAULT;
			case ViewPackage.TREE__MAX_DEPTH:
				return maxDepth != MAX_DEPTH_EDEFAULT;
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
		if (baseClass == Editable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__EDITABLE: return ViewPackage.EDITABLE__EDITABLE;
				default: return -1;
			}
		}
		if (baseClass == Movable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__MOVABLE: return ViewPackage.MOVABLE__MOVABLE;
				default: return -1;
			}
		}
		if (baseClass == Filterable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__FILTERING: return ViewPackage.FILTERABLE__FILTERING;
				default: return -1;
			}
		}
		if (baseClass == Stylable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__STYLING: return ViewPackage.STYLABLE__STYLING;
				default: return -1;
			}
		}
		if (baseClass == ModelElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__STEREOTYPES: return CommonPackage.MODEL_ELEMENT__STEREOTYPES;
				case ViewPackage.TREE__TAGS: return CommonPackage.MODEL_ELEMENT__TAGS;
				case ViewPackage.TREE__COMMENTS: return CommonPackage.MODEL_ELEMENT__COMMENTS;
				case ViewPackage.TREE__DOCUMENTATION: return CommonPackage.MODEL_ELEMENT__DOCUMENTATION;
				case ViewPackage.TREE__DESCRIPTION: return CommonPackage.MODEL_ELEMENT__DESCRIPTION;
				case ViewPackage.TREE__METAINFO: return CommonPackage.MODEL_ELEMENT__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__NAME: return CommonPackage.NAMED_MODEL_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == FieldElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__MAP_TO: return ViewPackage.FIELD_ELEMENT__MAP_TO;
				case ViewPackage.TREE__PREFIX: return ViewPackage.FIELD_ELEMENT__PREFIX;
				case ViewPackage.TREE__SUFFIX: return ViewPackage.FIELD_ELEMENT__SUFFIX;
				case ViewPackage.TREE__HIDDEN: return ViewPackage.FIELD_ELEMENT__HIDDEN;
				default: return -1;
			}
		}
		if (baseClass == FieldContainer.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__CHILDREN: return ViewPackage.FIELD_CONTAINER__CHILDREN;
				case ViewPackage.TREE__DISABLED: return ViewPackage.FIELD_CONTAINER__DISABLED;
				default: return -1;
			}
		}
		if (baseClass == AbstractView.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__OPERATIONS: return ViewPackage.ABSTRACT_VIEW__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == AbstractViewOf.class) {
			switch (derivedFeatureID) {
				case ViewPackage.TREE__VIEW_OF: return ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF;
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
		if (baseClass == Editable.class) {
			switch (baseFeatureID) {
				case ViewPackage.EDITABLE__EDITABLE: return ViewPackage.TREE__EDITABLE;
				default: return -1;
			}
		}
		if (baseClass == Movable.class) {
			switch (baseFeatureID) {
				case ViewPackage.MOVABLE__MOVABLE: return ViewPackage.TREE__MOVABLE;
				default: return -1;
			}
		}
		if (baseClass == Filterable.class) {
			switch (baseFeatureID) {
				case ViewPackage.FILTERABLE__FILTERING: return ViewPackage.TREE__FILTERING;
				default: return -1;
			}
		}
		if (baseClass == Stylable.class) {
			switch (baseFeatureID) {
				case ViewPackage.STYLABLE__STYLING: return ViewPackage.TREE__STYLING;
				default: return -1;
			}
		}
		if (baseClass == ModelElement.class) {
			switch (baseFeatureID) {
				case CommonPackage.MODEL_ELEMENT__STEREOTYPES: return ViewPackage.TREE__STEREOTYPES;
				case CommonPackage.MODEL_ELEMENT__TAGS: return ViewPackage.TREE__TAGS;
				case CommonPackage.MODEL_ELEMENT__COMMENTS: return ViewPackage.TREE__COMMENTS;
				case CommonPackage.MODEL_ELEMENT__DOCUMENTATION: return ViewPackage.TREE__DOCUMENTATION;
				case CommonPackage.MODEL_ELEMENT__DESCRIPTION: return ViewPackage.TREE__DESCRIPTION;
				case CommonPackage.MODEL_ELEMENT__METAINFO: return ViewPackage.TREE__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (baseFeatureID) {
				case CommonPackage.NAMED_MODEL_ELEMENT__NAME: return ViewPackage.TREE__NAME;
				default: return -1;
			}
		}
		if (baseClass == FieldElement.class) {
			switch (baseFeatureID) {
				case ViewPackage.FIELD_ELEMENT__MAP_TO: return ViewPackage.TREE__MAP_TO;
				case ViewPackage.FIELD_ELEMENT__PREFIX: return ViewPackage.TREE__PREFIX;
				case ViewPackage.FIELD_ELEMENT__SUFFIX: return ViewPackage.TREE__SUFFIX;
				case ViewPackage.FIELD_ELEMENT__HIDDEN: return ViewPackage.TREE__HIDDEN;
				default: return -1;
			}
		}
		if (baseClass == FieldContainer.class) {
			switch (baseFeatureID) {
				case ViewPackage.FIELD_CONTAINER__CHILDREN: return ViewPackage.TREE__CHILDREN;
				case ViewPackage.FIELD_CONTAINER__DISABLED: return ViewPackage.TREE__DISABLED;
				default: return -1;
			}
		}
		if (baseClass == AbstractView.class) {
			switch (baseFeatureID) {
				case ViewPackage.ABSTRACT_VIEW__OPERATIONS: return ViewPackage.TREE__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == AbstractViewOf.class) {
			switch (baseFeatureID) {
				case ViewPackage.ABSTRACT_VIEW_OF__VIEW_OF: return ViewPackage.TREE__VIEW_OF;
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
		result.append(" (editable: ");
		result.append(editable);
		result.append(", movable: ");
		result.append(movable);
		result.append(", documentation: ");
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
		result.append(", defaultDepth: ");
		result.append(defaultDepth);
		result.append(", maxDepth: ");
		result.append(maxDepth);
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //TreeImpl
