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
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.OperationComponent;

import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.Tag;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.Editable;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.FieldGroup;
import com.bluexml.side.view.Filterable;
import com.bluexml.side.view.Filtering;
import com.bluexml.side.view.Sortable;
import com.bluexml.side.view.Sorting;
import com.bluexml.side.view.Movable;
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
 * An implementation of the model object '<em><b>Col</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#isEditable <em>Editable</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getFiltering <em>Filtering</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getStyling <em>Styling</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getStereotypes <em>Stereotypes</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getMetainfo <em>Metainfo</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getMapTo <em>Map To</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getSuffix <em>Suffix</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#isHidden <em>Hidden</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getSorting <em>Sorting</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ColImpl#getActions <em>Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColImpl extends MovableImpl implements Col {
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
	 * The cached value of the '{@link #getSorting() <em>Sorting</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSorting()
	 * @generated
	 * @ordered
	 */
	protected Sorting sorting;

	/**
	 * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected OperationComponent actions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.COL;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.COL__STYLING, oldStyling, newStyling);
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
				msgs = ((InternalEObject)styling).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.COL__STYLING, null, msgs);
			if (newStyling != null)
				msgs = ((InternalEObject)newStyling).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.COL__STYLING, null, msgs);
			msgs = basicSetStyling(newStyling, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__STYLING, newStyling, newStyling));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Stereotype> getStereotypes() {
		if (stereotypes == null) {
			stereotypes = new EObjectResolvingEList<Stereotype>(Stereotype.class, this, ViewPackage.COL__STEREOTYPES);
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
			tags = new EObjectContainmentEList<Tag>(Tag.class, this, ViewPackage.COL__TAGS);
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
			comments = new EObjectContainmentEList<Comment>(Comment.class, this, ViewPackage.COL__COMMENTS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__DOCUMENTATION, oldDocumentation, documentation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaInfo> getMetainfo() {
		if (metainfo == null) {
			metainfo = new EObjectContainmentEList<MetaInfo>(MetaInfo.class, this, ViewPackage.COL__METAINFO);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__NAME, oldName, name));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewPackage.COL__MAP_TO, oldMapTo, mapTo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__MAP_TO, oldMapTo, mapTo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__PREFIX, oldPrefix, prefix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__SUFFIX, oldSuffix, suffix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__HIDDEN, oldHidden, hidden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldElement> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.COL__CHILDREN);
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
			disabled = new EObjectContainmentEList<FieldElement>(FieldElement.class, this, ViewPackage.COL__DISABLED);
		}
		return disabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sorting getSorting() {
		return sorting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSorting(Sorting newSorting, NotificationChain msgs) {
		Sorting oldSorting = sorting;
		sorting = newSorting;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.COL__SORTING, oldSorting, newSorting);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSorting(Sorting newSorting) {
		if (newSorting != sorting) {
			NotificationChain msgs = null;
			if (sorting != null)
				msgs = ((InternalEObject)sorting).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.COL__SORTING, null, msgs);
			if (newSorting != null)
				msgs = ((InternalEObject)newSorting).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.COL__SORTING, null, msgs);
			msgs = basicSetSorting(newSorting, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__SORTING, newSorting, newSorting));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__EDITABLE, oldEditable, editable));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.COL__FILTERING, oldFiltering, newFiltering);
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
				msgs = ((InternalEObject)filtering).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.COL__FILTERING, null, msgs);
			if (newFiltering != null)
				msgs = ((InternalEObject)newFiltering).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.COL__FILTERING, null, msgs);
			msgs = basicSetFiltering(newFiltering, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__FILTERING, newFiltering, newFiltering));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationComponent getActions() {
		return actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActions(OperationComponent newActions, NotificationChain msgs) {
		OperationComponent oldActions = actions;
		actions = newActions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewPackage.COL__ACTIONS, oldActions, newActions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActions(OperationComponent newActions) {
		if (newActions != actions) {
			NotificationChain msgs = null;
			if (actions != null)
				msgs = ((InternalEObject)actions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewPackage.COL__ACTIONS, null, msgs);
			if (newActions != null)
				msgs = ((InternalEObject)newActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewPackage.COL__ACTIONS, null, msgs);
			msgs = basicSetActions(newActions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.COL__ACTIONS, newActions, newActions));
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
			case ViewPackage.COL__FILTERING:
				return basicSetFiltering(null, msgs);
			case ViewPackage.COL__STYLING:
				return basicSetStyling(null, msgs);
			case ViewPackage.COL__TAGS:
				return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
			case ViewPackage.COL__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
			case ViewPackage.COL__METAINFO:
				return ((InternalEList<?>)getMetainfo()).basicRemove(otherEnd, msgs);
			case ViewPackage.COL__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ViewPackage.COL__DISABLED:
				return ((InternalEList<?>)getDisabled()).basicRemove(otherEnd, msgs);
			case ViewPackage.COL__SORTING:
				return basicSetSorting(null, msgs);
			case ViewPackage.COL__ACTIONS:
				return basicSetActions(null, msgs);
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
			case ViewPackage.COL__EDITABLE:
				return isEditable() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.COL__FILTERING:
				return getFiltering();
			case ViewPackage.COL__STYLING:
				return getStyling();
			case ViewPackage.COL__STEREOTYPES:
				return getStereotypes();
			case ViewPackage.COL__TAGS:
				return getTags();
			case ViewPackage.COL__COMMENTS:
				return getComments();
			case ViewPackage.COL__DOCUMENTATION:
				return getDocumentation();
			case ViewPackage.COL__DESCRIPTION:
				return getDescription();
			case ViewPackage.COL__METAINFO:
				return getMetainfo();
			case ViewPackage.COL__NAME:
				return getName();
			case ViewPackage.COL__MAP_TO:
				if (resolve) return getMapTo();
				return basicGetMapTo();
			case ViewPackage.COL__PREFIX:
				return getPrefix();
			case ViewPackage.COL__SUFFIX:
				return getSuffix();
			case ViewPackage.COL__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case ViewPackage.COL__CHILDREN:
				return getChildren();
			case ViewPackage.COL__DISABLED:
				return getDisabled();
			case ViewPackage.COL__SORTING:
				return getSorting();
			case ViewPackage.COL__ACTIONS:
				return getActions();
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
			case ViewPackage.COL__EDITABLE:
				setEditable(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.COL__FILTERING:
				setFiltering((Filtering)newValue);
				return;
			case ViewPackage.COL__STYLING:
				setStyling((Styling)newValue);
				return;
			case ViewPackage.COL__STEREOTYPES:
				getStereotypes().clear();
				getStereotypes().addAll((Collection<? extends Stereotype>)newValue);
				return;
			case ViewPackage.COL__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends Tag>)newValue);
				return;
			case ViewPackage.COL__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case ViewPackage.COL__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case ViewPackage.COL__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ViewPackage.COL__METAINFO:
				getMetainfo().clear();
				getMetainfo().addAll((Collection<? extends MetaInfo>)newValue);
				return;
			case ViewPackage.COL__NAME:
				setName((String)newValue);
				return;
			case ViewPackage.COL__MAP_TO:
				setMapTo((ModelElement)newValue);
				return;
			case ViewPackage.COL__PREFIX:
				setPrefix((String)newValue);
				return;
			case ViewPackage.COL__SUFFIX:
				setSuffix((String)newValue);
				return;
			case ViewPackage.COL__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case ViewPackage.COL__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends FieldElement>)newValue);
				return;
			case ViewPackage.COL__DISABLED:
				getDisabled().clear();
				getDisabled().addAll((Collection<? extends FieldElement>)newValue);
				return;
			case ViewPackage.COL__SORTING:
				setSorting((Sorting)newValue);
				return;
			case ViewPackage.COL__ACTIONS:
				setActions((OperationComponent)newValue);
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
			case ViewPackage.COL__EDITABLE:
				setEditable(EDITABLE_EDEFAULT);
				return;
			case ViewPackage.COL__FILTERING:
				setFiltering((Filtering)null);
				return;
			case ViewPackage.COL__STYLING:
				setStyling((Styling)null);
				return;
			case ViewPackage.COL__STEREOTYPES:
				getStereotypes().clear();
				return;
			case ViewPackage.COL__TAGS:
				getTags().clear();
				return;
			case ViewPackage.COL__COMMENTS:
				getComments().clear();
				return;
			case ViewPackage.COL__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case ViewPackage.COL__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ViewPackage.COL__METAINFO:
				getMetainfo().clear();
				return;
			case ViewPackage.COL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ViewPackage.COL__MAP_TO:
				setMapTo((ModelElement)null);
				return;
			case ViewPackage.COL__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case ViewPackage.COL__SUFFIX:
				setSuffix(SUFFIX_EDEFAULT);
				return;
			case ViewPackage.COL__HIDDEN:
				setHidden(HIDDEN_EDEFAULT);
				return;
			case ViewPackage.COL__CHILDREN:
				getChildren().clear();
				return;
			case ViewPackage.COL__DISABLED:
				getDisabled().clear();
				return;
			case ViewPackage.COL__SORTING:
				setSorting((Sorting)null);
				return;
			case ViewPackage.COL__ACTIONS:
				setActions((OperationComponent)null);
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
			case ViewPackage.COL__EDITABLE:
				return editable != EDITABLE_EDEFAULT;
			case ViewPackage.COL__FILTERING:
				return filtering != null;
			case ViewPackage.COL__STYLING:
				return styling != null;
			case ViewPackage.COL__STEREOTYPES:
				return stereotypes != null && !stereotypes.isEmpty();
			case ViewPackage.COL__TAGS:
				return tags != null && !tags.isEmpty();
			case ViewPackage.COL__COMMENTS:
				return comments != null && !comments.isEmpty();
			case ViewPackage.COL__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case ViewPackage.COL__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ViewPackage.COL__METAINFO:
				return metainfo != null && !metainfo.isEmpty();
			case ViewPackage.COL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ViewPackage.COL__MAP_TO:
				return mapTo != null;
			case ViewPackage.COL__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case ViewPackage.COL__SUFFIX:
				return SUFFIX_EDEFAULT == null ? suffix != null : !SUFFIX_EDEFAULT.equals(suffix);
			case ViewPackage.COL__HIDDEN:
				return hidden != HIDDEN_EDEFAULT;
			case ViewPackage.COL__CHILDREN:
				return children != null && !children.isEmpty();
			case ViewPackage.COL__DISABLED:
				return disabled != null && !disabled.isEmpty();
			case ViewPackage.COL__SORTING:
				return sorting != null;
			case ViewPackage.COL__ACTIONS:
				return actions != null;
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
				case ViewPackage.COL__EDITABLE: return ViewPackage.EDITABLE__EDITABLE;
				default: return -1;
			}
		}
		if (baseClass == Filterable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.COL__FILTERING: return ViewPackage.FILTERABLE__FILTERING;
				default: return -1;
			}
		}
		if (baseClass == Stylable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.COL__STYLING: return ViewPackage.STYLABLE__STYLING;
				default: return -1;
			}
		}
		if (baseClass == ModelElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.COL__STEREOTYPES: return CommonPackage.MODEL_ELEMENT__STEREOTYPES;
				case ViewPackage.COL__TAGS: return CommonPackage.MODEL_ELEMENT__TAGS;
				case ViewPackage.COL__COMMENTS: return CommonPackage.MODEL_ELEMENT__COMMENTS;
				case ViewPackage.COL__DOCUMENTATION: return CommonPackage.MODEL_ELEMENT__DOCUMENTATION;
				case ViewPackage.COL__DESCRIPTION: return CommonPackage.MODEL_ELEMENT__DESCRIPTION;
				case ViewPackage.COL__METAINFO: return CommonPackage.MODEL_ELEMENT__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.COL__NAME: return CommonPackage.NAMED_MODEL_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == FieldElement.class) {
			switch (derivedFeatureID) {
				case ViewPackage.COL__MAP_TO: return ViewPackage.FIELD_ELEMENT__MAP_TO;
				case ViewPackage.COL__PREFIX: return ViewPackage.FIELD_ELEMENT__PREFIX;
				case ViewPackage.COL__SUFFIX: return ViewPackage.FIELD_ELEMENT__SUFFIX;
				case ViewPackage.COL__HIDDEN: return ViewPackage.FIELD_ELEMENT__HIDDEN;
				default: return -1;
			}
		}
		if (baseClass == FieldGroup.class) {
			switch (derivedFeatureID) {
				case ViewPackage.COL__CHILDREN: return ViewPackage.FIELD_GROUP__CHILDREN;
				case ViewPackage.COL__DISABLED: return ViewPackage.FIELD_GROUP__DISABLED;
				default: return -1;
			}
		}
		if (baseClass == Sortable.class) {
			switch (derivedFeatureID) {
				case ViewPackage.COL__SORTING: return ViewPackage.SORTABLE__SORTING;
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
				case ViewPackage.EDITABLE__EDITABLE: return ViewPackage.COL__EDITABLE;
				default: return -1;
			}
		}
		if (baseClass == Filterable.class) {
			switch (baseFeatureID) {
				case ViewPackage.FILTERABLE__FILTERING: return ViewPackage.COL__FILTERING;
				default: return -1;
			}
		}
		if (baseClass == Stylable.class) {
			switch (baseFeatureID) {
				case ViewPackage.STYLABLE__STYLING: return ViewPackage.COL__STYLING;
				default: return -1;
			}
		}
		if (baseClass == ModelElement.class) {
			switch (baseFeatureID) {
				case CommonPackage.MODEL_ELEMENT__STEREOTYPES: return ViewPackage.COL__STEREOTYPES;
				case CommonPackage.MODEL_ELEMENT__TAGS: return ViewPackage.COL__TAGS;
				case CommonPackage.MODEL_ELEMENT__COMMENTS: return ViewPackage.COL__COMMENTS;
				case CommonPackage.MODEL_ELEMENT__DOCUMENTATION: return ViewPackage.COL__DOCUMENTATION;
				case CommonPackage.MODEL_ELEMENT__DESCRIPTION: return ViewPackage.COL__DESCRIPTION;
				case CommonPackage.MODEL_ELEMENT__METAINFO: return ViewPackage.COL__METAINFO;
				default: return -1;
			}
		}
		if (baseClass == NamedModelElement.class) {
			switch (baseFeatureID) {
				case CommonPackage.NAMED_MODEL_ELEMENT__NAME: return ViewPackage.COL__NAME;
				default: return -1;
			}
		}
		if (baseClass == FieldElement.class) {
			switch (baseFeatureID) {
				case ViewPackage.FIELD_ELEMENT__MAP_TO: return ViewPackage.COL__MAP_TO;
				case ViewPackage.FIELD_ELEMENT__PREFIX: return ViewPackage.COL__PREFIX;
				case ViewPackage.FIELD_ELEMENT__SUFFIX: return ViewPackage.COL__SUFFIX;
				case ViewPackage.FIELD_ELEMENT__HIDDEN: return ViewPackage.COL__HIDDEN;
				default: return -1;
			}
		}
		if (baseClass == FieldGroup.class) {
			switch (baseFeatureID) {
				case ViewPackage.FIELD_GROUP__CHILDREN: return ViewPackage.COL__CHILDREN;
				case ViewPackage.FIELD_GROUP__DISABLED: return ViewPackage.COL__DISABLED;
				default: return -1;
			}
		}
		if (baseClass == Sortable.class) {
			switch (baseFeatureID) {
				case ViewPackage.SORTABLE__SORTING: return ViewPackage.COL__SORTING;
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
		result.append(')');
		return result.toString();
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //ColImpl
