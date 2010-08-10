/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.MindMap.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink;
import com.bluexml.side.requirements.generator.metamodel.MindMap.Node;
import com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation;
import com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl#getPresentation <em>Presentation</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl#getText <em>Text</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl#getArrowlink <em>Arrowlink</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends EObjectImpl implements Node {
	/**
	 * The cached value of the '{@link #getPresentation() <em>Presentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresentation()
	 * @generated
	 * @ordered
	 */
	protected NodePresentation presentation;

	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected String text = TEXT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSub() <em>Sub</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSub()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> sub;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArrowlink() <em>Arrowlink</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrowlink()
	 * @generated
	 * @ordered
	 */
	protected EList<ArrowLink> arrowlink;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mindmapPackage.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodePresentation getPresentation() {
		return presentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPresentation(NodePresentation newPresentation, NotificationChain msgs) {
		NodePresentation oldPresentation = presentation;
		presentation = newPresentation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE__PRESENTATION, oldPresentation, newPresentation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPresentation(NodePresentation newPresentation) {
		if (newPresentation != presentation) {
			NotificationChain msgs = null;
			if (presentation != null)
				msgs = ((InternalEObject)presentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - mindmapPackage.NODE__PRESENTATION, null, msgs);
			if (newPresentation != null)
				msgs = ((InternalEObject)newPresentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - mindmapPackage.NODE__PRESENTATION, null, msgs);
			msgs = basicSetPresentation(newPresentation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE__PRESENTATION, newPresentation, newPresentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ArrowLink> getArrowlink() {
		if (arrowlink == null) {
			arrowlink = new EObjectContainmentEList<ArrowLink>(ArrowLink.class, this, mindmapPackage.NODE__ARROWLINK);
		}
		return arrowlink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText() {
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE__TEXT, oldText, text));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Node> getSub() {
		if (sub == null) {
			sub = new EObjectContainmentEList<Node>(Node.class, this, mindmapPackage.NODE__SUB);
		}
		return sub;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case mindmapPackage.NODE__PRESENTATION:
				return basicSetPresentation(null, msgs);
			case mindmapPackage.NODE__SUB:
				return ((InternalEList<?>)getSub()).basicRemove(otherEnd, msgs);
			case mindmapPackage.NODE__ARROWLINK:
				return ((InternalEList<?>)getArrowlink()).basicRemove(otherEnd, msgs);
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
			case mindmapPackage.NODE__PRESENTATION:
				return getPresentation();
			case mindmapPackage.NODE__TEXT:
				return getText();
			case mindmapPackage.NODE__SUB:
				return getSub();
			case mindmapPackage.NODE__ID:
				return getId();
			case mindmapPackage.NODE__ARROWLINK:
				return getArrowlink();
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
			case mindmapPackage.NODE__PRESENTATION:
				setPresentation((NodePresentation)newValue);
				return;
			case mindmapPackage.NODE__TEXT:
				setText((String)newValue);
				return;
			case mindmapPackage.NODE__SUB:
				getSub().clear();
				getSub().addAll((Collection<? extends Node>)newValue);
				return;
			case mindmapPackage.NODE__ID:
				setId((String)newValue);
				return;
			case mindmapPackage.NODE__ARROWLINK:
				getArrowlink().clear();
				getArrowlink().addAll((Collection<? extends ArrowLink>)newValue);
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
			case mindmapPackage.NODE__PRESENTATION:
				setPresentation((NodePresentation)null);
				return;
			case mindmapPackage.NODE__TEXT:
				setText(TEXT_EDEFAULT);
				return;
			case mindmapPackage.NODE__SUB:
				getSub().clear();
				return;
			case mindmapPackage.NODE__ID:
				setId(ID_EDEFAULT);
				return;
			case mindmapPackage.NODE__ARROWLINK:
				getArrowlink().clear();
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
			case mindmapPackage.NODE__PRESENTATION:
				return presentation != null;
			case mindmapPackage.NODE__TEXT:
				return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
			case mindmapPackage.NODE__SUB:
				return sub != null && !sub.isEmpty();
			case mindmapPackage.NODE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case mindmapPackage.NODE__ARROWLINK:
				return arrowlink != null && !arrowlink.isEmpty();
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
		result.append(" (text: ");
		result.append(text);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //NodeImpl
