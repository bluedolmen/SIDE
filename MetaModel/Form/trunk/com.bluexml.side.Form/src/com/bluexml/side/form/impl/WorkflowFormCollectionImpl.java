/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Workflow Form Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.form.impl.WorkflowFormCollectionImpl#getLinked_process <em>Linked process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkflowFormCollectionImpl extends FormCollectionImpl implements WorkflowFormCollection {
	/**
	 * The cached value of the '{@link #getLinked_process() <em>Linked process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinked_process()
	 * @generated
	 * @ordered
	 */
	protected com.bluexml.side.workflow.Process linked_process;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkflowFormCollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.WORKFLOW_FORM_COLLECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public com.bluexml.side.workflow.Process getLinked_process() {
		if (linked_process != null && linked_process.eIsProxy()) {
			InternalEObject oldLinked_process = (InternalEObject)linked_process;
			linked_process = (com.bluexml.side.workflow.Process)eResolveProxy(oldLinked_process);
			if (linked_process != oldLinked_process) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.WORKFLOW_FORM_COLLECTION__LINKED_PROCESS, oldLinked_process, linked_process));
			}
		}
		return linked_process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public com.bluexml.side.workflow.Process basicGetLinked_process() {
		return linked_process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinked_process(com.bluexml.side.workflow.Process newLinked_process) {
		com.bluexml.side.workflow.Process oldLinked_process = linked_process;
		linked_process = newLinked_process;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.WORKFLOW_FORM_COLLECTION__LINKED_PROCESS, oldLinked_process, linked_process));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.WORKFLOW_FORM_COLLECTION__LINKED_PROCESS:
				if (resolve) return getLinked_process();
				return basicGetLinked_process();
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
			case FormPackage.WORKFLOW_FORM_COLLECTION__LINKED_PROCESS:
				setLinked_process((com.bluexml.side.workflow.Process)newValue);
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
			case FormPackage.WORKFLOW_FORM_COLLECTION__LINKED_PROCESS:
				setLinked_process((com.bluexml.side.workflow.Process)null);
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
			case FormPackage.WORKFLOW_FORM_COLLECTION__LINKED_PROCESS:
				return linked_process != null;
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //WorkflowFormCollectionImpl
