/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.MindMap.impl;

import com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation;
import com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Presentation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl#isBold <em>Bold</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl#isItalic <em>Italic</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl#getSize <em>Size</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl#getBackgroundColor <em>Background Color</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl#getColor <em>Color</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.NodePresentationImpl#isFolded <em>Folded</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodePresentationImpl extends EObjectImpl implements NodePresentation {
	/**
	 * The default value of the '{@link #isBold() <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBold()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOLD_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isBold() <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBold()
	 * @generated
	 * @ordered
	 */
	protected boolean bold = BOLD_EDEFAULT;

	/**
	 * This is true if the Bold attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean boldESet;

	/**
	 * The default value of the '{@link #isItalic() <em>Italic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isItalic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ITALIC_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isItalic() <em>Italic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isItalic()
	 * @generated
	 * @ordered
	 */
	protected boolean italic = ITALIC_EDEFAULT;

	/**
	 * This is true if the Italic attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean italicESet;

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
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final int SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected int size = SIZE_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodePresentationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mindmapPackage.Literals.NODE_PRESENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBold() {
		return bold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBold(boolean newBold) {
		boolean oldBold = bold;
		bold = newBold;
		boolean oldBoldESet = boldESet;
		boldESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE_PRESENTATION__BOLD, oldBold, bold, !oldBoldESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBold() {
		boolean oldBold = bold;
		boolean oldBoldESet = boldESet;
		bold = BOLD_EDEFAULT;
		boldESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, mindmapPackage.NODE_PRESENTATION__BOLD, oldBold, BOLD_EDEFAULT, oldBoldESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBold() {
		return boldESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isItalic() {
		return italic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItalic(boolean newItalic) {
		boolean oldItalic = italic;
		italic = newItalic;
		boolean oldItalicESet = italicESet;
		italicESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE_PRESENTATION__ITALIC, oldItalic, italic, !oldItalicESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetItalic() {
		boolean oldItalic = italic;
		boolean oldItalicESet = italicESet;
		italic = ITALIC_EDEFAULT;
		italicESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, mindmapPackage.NODE_PRESENTATION__ITALIC, oldItalic, ITALIC_EDEFAULT, oldItalicESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetItalic() {
		return italicESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE_PRESENTATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(int newSize) {
		int oldSize = size;
		size = newSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE_PRESENTATION__SIZE, oldSize, size));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE_PRESENTATION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE_PRESENTATION__COLOR, oldColor, color));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.NODE_PRESENTATION__FOLDED, oldFolded, folded, !oldFoldedESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, mindmapPackage.NODE_PRESENTATION__FOLDED, oldFolded, FOLDED_EDEFAULT, oldFoldedESet));
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case mindmapPackage.NODE_PRESENTATION__BOLD:
				return isBold();
			case mindmapPackage.NODE_PRESENTATION__ITALIC:
				return isItalic();
			case mindmapPackage.NODE_PRESENTATION__NAME:
				return getName();
			case mindmapPackage.NODE_PRESENTATION__SIZE:
				return getSize();
			case mindmapPackage.NODE_PRESENTATION__BACKGROUND_COLOR:
				return getBackgroundColor();
			case mindmapPackage.NODE_PRESENTATION__COLOR:
				return getColor();
			case mindmapPackage.NODE_PRESENTATION__FOLDED:
				return isFolded();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case mindmapPackage.NODE_PRESENTATION__BOLD:
				setBold((Boolean)newValue);
				return;
			case mindmapPackage.NODE_PRESENTATION__ITALIC:
				setItalic((Boolean)newValue);
				return;
			case mindmapPackage.NODE_PRESENTATION__NAME:
				setName((String)newValue);
				return;
			case mindmapPackage.NODE_PRESENTATION__SIZE:
				setSize((Integer)newValue);
				return;
			case mindmapPackage.NODE_PRESENTATION__BACKGROUND_COLOR:
				setBackgroundColor((String)newValue);
				return;
			case mindmapPackage.NODE_PRESENTATION__COLOR:
				setColor((String)newValue);
				return;
			case mindmapPackage.NODE_PRESENTATION__FOLDED:
				setFolded((Boolean)newValue);
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
			case mindmapPackage.NODE_PRESENTATION__BOLD:
				unsetBold();
				return;
			case mindmapPackage.NODE_PRESENTATION__ITALIC:
				unsetItalic();
				return;
			case mindmapPackage.NODE_PRESENTATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case mindmapPackage.NODE_PRESENTATION__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case mindmapPackage.NODE_PRESENTATION__BACKGROUND_COLOR:
				setBackgroundColor(BACKGROUND_COLOR_EDEFAULT);
				return;
			case mindmapPackage.NODE_PRESENTATION__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case mindmapPackage.NODE_PRESENTATION__FOLDED:
				unsetFolded();
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
			case mindmapPackage.NODE_PRESENTATION__BOLD:
				return isSetBold();
			case mindmapPackage.NODE_PRESENTATION__ITALIC:
				return isSetItalic();
			case mindmapPackage.NODE_PRESENTATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case mindmapPackage.NODE_PRESENTATION__SIZE:
				return size != SIZE_EDEFAULT;
			case mindmapPackage.NODE_PRESENTATION__BACKGROUND_COLOR:
				return BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
			case mindmapPackage.NODE_PRESENTATION__COLOR:
				return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
			case mindmapPackage.NODE_PRESENTATION__FOLDED:
				return isSetFolded();
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
		result.append(" (bold: ");
		if (boldESet) result.append(bold); else result.append("<unset>");
		result.append(", italic: ");
		if (italicESet) result.append(italic); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(", size: ");
		result.append(size);
		result.append(", backgroundColor: ");
		result.append(backgroundColor);
		result.append(", color: ");
		result.append(color);
		result.append(", folded: ");
		if (foldedESet) result.append(folded); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //NodePresentationImpl
