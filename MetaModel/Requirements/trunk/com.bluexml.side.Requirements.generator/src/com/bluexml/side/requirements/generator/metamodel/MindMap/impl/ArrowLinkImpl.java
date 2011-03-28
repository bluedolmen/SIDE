/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.MindMap.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink;
import com.bluexml.side.requirements.generator.metamodel.MindMap.Node;
import com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arrow Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl#getColor <em>Color</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl#getEndarrow <em>Endarrow</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl#getEndinclination <em>Endinclination</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl#getStartarrow <em>Startarrow</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl#getStartinclination <em>Startinclination</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.impl.ArrowLinkImpl#getDestination <em>Destination</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrowLinkImpl extends EObjectImpl implements ArrowLink {
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
	 * The default value of the '{@link #getEndarrow() <em>Endarrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndarrow()
	 * @generated
	 * @ordered
	 */
	protected static final String ENDARROW_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndarrow() <em>Endarrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndarrow()
	 * @generated
	 * @ordered
	 */
	protected String endarrow = ENDARROW_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndinclination() <em>Endinclination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndinclination()
	 * @generated
	 * @ordered
	 */
	protected static final String ENDINCLINATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndinclination() <em>Endinclination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndinclination()
	 * @generated
	 * @ordered
	 */
	protected String endinclination = ENDINCLINATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartarrow() <em>Startarrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartarrow()
	 * @generated
	 * @ordered
	 */
	protected static final String STARTARROW_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartarrow() <em>Startarrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartarrow()
	 * @generated
	 * @ordered
	 */
	protected String startarrow = STARTARROW_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartinclination() <em>Startinclination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartinclination()
	 * @generated
	 * @ordered
	 */
	protected static final String STARTINCLINATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartinclination() <em>Startinclination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartinclination()
	 * @generated
	 * @ordered
	 */
	protected String startinclination = STARTINCLINATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDestination() <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestination()
	 * @generated
	 * @ordered
	 */
	protected Node destination;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrowLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mindmapPackage.Literals.ARROW_LINK;
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
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.ARROW_LINK__COLOR, oldColor, color));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getDestination() {
		if (destination != null && destination.eIsProxy()) {
			InternalEObject oldDestination = (InternalEObject)destination;
			destination = (Node)eResolveProxy(oldDestination);
			if (destination != oldDestination) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, mindmapPackage.ARROW_LINK__DESTINATION, oldDestination, destination));
			}
		}
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetDestination() {
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestination(Node newDestination) {
		Node oldDestination = destination;
		destination = newDestination;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.ARROW_LINK__DESTINATION, oldDestination, destination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEndarrow() {
		return endarrow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndarrow(String newEndarrow) {
		String oldEndarrow = endarrow;
		endarrow = newEndarrow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.ARROW_LINK__ENDARROW, oldEndarrow, endarrow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEndinclination() {
		return endinclination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndinclination(String newEndinclination) {
		String oldEndinclination = endinclination;
		endinclination = newEndinclination;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.ARROW_LINK__ENDINCLINATION, oldEndinclination, endinclination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStartarrow() {
		return startarrow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartarrow(String newStartarrow) {
		String oldStartarrow = startarrow;
		startarrow = newStartarrow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.ARROW_LINK__STARTARROW, oldStartarrow, startarrow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStartinclination() {
		return startinclination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartinclination(String newStartinclination) {
		String oldStartinclination = startinclination;
		startinclination = newStartinclination;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mindmapPackage.ARROW_LINK__STARTINCLINATION, oldStartinclination, startinclination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case mindmapPackage.ARROW_LINK__COLOR:
				return getColor();
			case mindmapPackage.ARROW_LINK__ENDARROW:
				return getEndarrow();
			case mindmapPackage.ARROW_LINK__ENDINCLINATION:
				return getEndinclination();
			case mindmapPackage.ARROW_LINK__STARTARROW:
				return getStartarrow();
			case mindmapPackage.ARROW_LINK__STARTINCLINATION:
				return getStartinclination();
			case mindmapPackage.ARROW_LINK__DESTINATION:
				if (resolve) return getDestination();
				return basicGetDestination();
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
			case mindmapPackage.ARROW_LINK__COLOR:
				setColor((String)newValue);
				return;
			case mindmapPackage.ARROW_LINK__ENDARROW:
				setEndarrow((String)newValue);
				return;
			case mindmapPackage.ARROW_LINK__ENDINCLINATION:
				setEndinclination((String)newValue);
				return;
			case mindmapPackage.ARROW_LINK__STARTARROW:
				setStartarrow((String)newValue);
				return;
			case mindmapPackage.ARROW_LINK__STARTINCLINATION:
				setStartinclination((String)newValue);
				return;
			case mindmapPackage.ARROW_LINK__DESTINATION:
				setDestination((Node)newValue);
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
			case mindmapPackage.ARROW_LINK__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case mindmapPackage.ARROW_LINK__ENDARROW:
				setEndarrow(ENDARROW_EDEFAULT);
				return;
			case mindmapPackage.ARROW_LINK__ENDINCLINATION:
				setEndinclination(ENDINCLINATION_EDEFAULT);
				return;
			case mindmapPackage.ARROW_LINK__STARTARROW:
				setStartarrow(STARTARROW_EDEFAULT);
				return;
			case mindmapPackage.ARROW_LINK__STARTINCLINATION:
				setStartinclination(STARTINCLINATION_EDEFAULT);
				return;
			case mindmapPackage.ARROW_LINK__DESTINATION:
				setDestination((Node)null);
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
			case mindmapPackage.ARROW_LINK__COLOR:
				return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
			case mindmapPackage.ARROW_LINK__ENDARROW:
				return ENDARROW_EDEFAULT == null ? endarrow != null : !ENDARROW_EDEFAULT.equals(endarrow);
			case mindmapPackage.ARROW_LINK__ENDINCLINATION:
				return ENDINCLINATION_EDEFAULT == null ? endinclination != null : !ENDINCLINATION_EDEFAULT.equals(endinclination);
			case mindmapPackage.ARROW_LINK__STARTARROW:
				return STARTARROW_EDEFAULT == null ? startarrow != null : !STARTARROW_EDEFAULT.equals(startarrow);
			case mindmapPackage.ARROW_LINK__STARTINCLINATION:
				return STARTINCLINATION_EDEFAULT == null ? startinclination != null : !STARTINCLINATION_EDEFAULT.equals(startinclination);
			case mindmapPackage.ARROW_LINK__DESTINATION:
				return destination != null;
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
		result.append(" (color: ");
		result.append(color);
		result.append(", endarrow: ");
		result.append(endarrow);
		result.append(", endinclination: ");
		result.append(endinclination);
		result.append(", startarrow: ");
		result.append(startarrow);
		result.append(", startinclination: ");
		result.append(startinclination);
		result.append(')');
		return result.toString();
	}

} //ArrowLinkImpl
