/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.MindMap.impl;

import com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink;
import com.bluexml.side.requirements.generator.metamodel.MindMap.Font;
import com.bluexml.side.requirements.generator.metamodel.MindMap.Node;
import com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl#getFont <em>Font</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl#getBackgroundColor <em>Background Color</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl#getColor <em>Color</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodeImpl#isFolded <em>Folded</em>}</li>
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
	 * The cached value of the '{@link #getFont() <em>Font</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFont()
	 * @generated
	 * @ordered
	 */
	protected EList<Font> font;

	/**
	 * The default value of the '{@link #getBackgroundColor() <em>Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackgroundColor()
	 * @generated
	 * @ordered
	 */
	protected static final String BACKGROUND_COLOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBackgroundColor() <em>Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackgroundColor()
	 * @generated
	 * @ordered
	 */
	protected String backgroundColor = BACKGROUND_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected String color = COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isFolded() <em>Folded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFolded()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FOLDED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isFolded() <em>Folded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFolded()
	 * @generated
	 * @ordered
	 */
	protected boolean folded = FOLDED_EDEFAULT;

	/**
	 * This is true if the Folded attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean foldedESet;

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
	public EList<Font> getFont() {
		if (font == null) {
			font = new EObjectContainmentEList<Font>(Font.class, this, mindmapPackage.NODE__FONT);
		}
		return font;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBackgroundColor(String newBackgroundColor) {
		String oldBackgroundColor = backgroundColor;
		backgroundColor = newBackgroundColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(String newColor) {
		String oldColor = color;
		color = newColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE__COLOR, oldColor, color));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFolded() {
		return folded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolded(boolean newFolded) {
		boolean oldFolded = folded;
		folded = newFolded;
		boolean oldFoldedESet = foldedESet;
		foldedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE__FOLDED, oldFolded, folded, !oldFoldedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFolded() {
		boolean oldFolded = folded;
		boolean oldFoldedESet = foldedESet;
		folded = FOLDED_EDEFAULT;
		foldedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, mindmapPackage.NODE__FOLDED, oldFolded, FOLDED_EDEFAULT, oldFoldedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFolded() {
		return foldedESet;
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
			case mindmapPackage.NODE__FONT:
				return ((InternalEList<?>)getFont()).basicRemove(otherEnd, msgs);
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
			case mindmapPackage.NODE__FONT:
				return getFont();
			case mindmapPackage.NODE__BACKGROUND_COLOR:
				return getBackgroundColor();
			case mindmapPackage.NODE__COLOR:
				return getColor();
			case mindmapPackage.NODE__FOLDED:
				return isFolded();
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
			case mindmapPackage.NODE__FONT:
				getFont().clear();
				getFont().addAll((Collection<? extends Font>)newValue);
				return;
			case mindmapPackage.NODE__BACKGROUND_COLOR:
				setBackgroundColor((String)newValue);
				return;
			case mindmapPackage.NODE__COLOR:
				setColor((String)newValue);
				return;
			case mindmapPackage.NODE__FOLDED:
				setFolded((Boolean)newValue);
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
			case mindmapPackage.NODE__FONT:
				getFont().clear();
				return;
			case mindmapPackage.NODE__BACKGROUND_COLOR:
				setBackgroundColor(BACKGROUND_COLOR_EDEFAULT);
				return;
			case mindmapPackage.NODE__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case mindmapPackage.NODE__FOLDED:
				unsetFolded();
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
			case mindmapPackage.NODE__FONT:
				return font != null && !font.isEmpty();
			case mindmapPackage.NODE__BACKGROUND_COLOR:
				return BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
			case mindmapPackage.NODE__COLOR:
				return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
			case mindmapPackage.NODE__FOLDED:
				return isSetFolded();
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
		result.append(" (backgroundColor: ");
		result.append(backgroundColor);
		result.append(", color: ");
		result.append(color);
		result.append(", folded: ");
		if (foldedESet) result.append(folded); else result.append("<unset>");
		result.append(", text: ");
		result.append(text);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //NodeImpl
