/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue;
import com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Informal Table Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueImpl#getCols <em>Cols</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueImpl#getBodyRows <em>Body Rows</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.impl.InformalTableValueImpl#getHeadRows <em>Head Rows</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InformalTableValueImpl extends ParagraphValueImpl implements InformalTableValue {
	/**
	 * The default value of the '{@link #getCols() <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCols()
	 * @generated
	 * @ordered
	 */
	protected static final int COLS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getCols() <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCols()
	 * @generated
	 * @ordered
	 */
	protected int cols = COLS_EDEFAULT;
	/**
	 * The cached value of the '{@link #getBodyRows() <em>Body Rows</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBodyRows()
	 * @generated
	 * @ordered
	 */
	protected EList<InformalTableValueRow> bodyRows;
	/**
	 * The cached value of the '{@link #getHeadRows() <em>Head Rows</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadRows()
	 * @generated
	 * @ordered
	 */
	protected EList<InformalTableValueRow> headRows;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InformalTableValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DocumentationPackage.Literals.INFORMAL_TABLE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCols(int newCols) {
		int oldCols = cols;
		cols = newCols;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DocumentationPackage.INFORMAL_TABLE_VALUE__COLS, oldCols, cols));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InformalTableValueRow> getBodyRows() {
		if (bodyRows == null) {
			bodyRows = new EObjectContainmentEList<InformalTableValueRow>(InformalTableValueRow.class, this, DocumentationPackage.INFORMAL_TABLE_VALUE__BODY_ROWS);
		}
		return bodyRows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InformalTableValueRow> getHeadRows() {
		if (headRows == null) {
			headRows = new EObjectContainmentEList<InformalTableValueRow>(InformalTableValueRow.class, this, DocumentationPackage.INFORMAL_TABLE_VALUE__HEAD_ROWS);
		}
		return headRows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DocumentationPackage.INFORMAL_TABLE_VALUE__BODY_ROWS:
				return ((InternalEList<?>)getBodyRows()).basicRemove(otherEnd, msgs);
			case DocumentationPackage.INFORMAL_TABLE_VALUE__HEAD_ROWS:
				return ((InternalEList<?>)getHeadRows()).basicRemove(otherEnd, msgs);
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
			case DocumentationPackage.INFORMAL_TABLE_VALUE__COLS:
				return getCols();
			case DocumentationPackage.INFORMAL_TABLE_VALUE__BODY_ROWS:
				return getBodyRows();
			case DocumentationPackage.INFORMAL_TABLE_VALUE__HEAD_ROWS:
				return getHeadRows();
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
			case DocumentationPackage.INFORMAL_TABLE_VALUE__COLS:
				setCols((Integer)newValue);
				return;
			case DocumentationPackage.INFORMAL_TABLE_VALUE__BODY_ROWS:
				getBodyRows().clear();
				getBodyRows().addAll((Collection<? extends InformalTableValueRow>)newValue);
				return;
			case DocumentationPackage.INFORMAL_TABLE_VALUE__HEAD_ROWS:
				getHeadRows().clear();
				getHeadRows().addAll((Collection<? extends InformalTableValueRow>)newValue);
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
			case DocumentationPackage.INFORMAL_TABLE_VALUE__COLS:
				setCols(COLS_EDEFAULT);
				return;
			case DocumentationPackage.INFORMAL_TABLE_VALUE__BODY_ROWS:
				getBodyRows().clear();
				return;
			case DocumentationPackage.INFORMAL_TABLE_VALUE__HEAD_ROWS:
				getHeadRows().clear();
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
			case DocumentationPackage.INFORMAL_TABLE_VALUE__COLS:
				return cols != COLS_EDEFAULT;
			case DocumentationPackage.INFORMAL_TABLE_VALUE__BODY_ROWS:
				return bodyRows != null && !bodyRows.isEmpty();
			case DocumentationPackage.INFORMAL_TABLE_VALUE__HEAD_ROWS:
				return headRows != null && !headRows.isEmpty();
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
		result.append(" (cols: ");
		result.append(cols);
		result.append(')');
		return result.toString();
	}

} //InformalTableValueImpl
