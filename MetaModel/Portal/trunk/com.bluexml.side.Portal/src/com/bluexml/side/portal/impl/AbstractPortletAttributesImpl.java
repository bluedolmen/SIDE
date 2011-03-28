/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.portal.AbstractPortletAttributes;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Portlet Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class AbstractPortletAttributesImpl extends EObjectImpl implements AbstractPortletAttributes {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractPortletAttributesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortalPackage.Literals.ABSTRACT_PORTLET_ATTRIBUTES;
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

} //AbstractPortletAttributesImpl
