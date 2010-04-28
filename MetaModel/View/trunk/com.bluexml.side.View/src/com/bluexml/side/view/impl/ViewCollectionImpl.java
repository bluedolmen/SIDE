/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.expressions.OCLExpression;
import com.bluexml.side.common.impl.PackageImpl;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.bluexml.side.view.impl.ViewCollectionImpl#getViews <em>Views</em>}</li>
 *   <li>{@link com.bluexml.side.view.impl.ViewCollectionImpl#getComposedViews <em>Composed Views</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewCollectionImpl extends PackageImpl implements ViewCollection {
	/**
	 * The cached value of the '{@link #getViews() <em>Views</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViews()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractView> views;

	/**
	 * The cached value of the '{@link #getComposedViews() <em>Composed Views</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComposedViews()
	 * @generated
	 * @ordered
	 */
	protected EList<ComposedView> composedViews;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewCollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewPackage.Literals.VIEW_COLLECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractView> getViews() {
		if (views == null) {
			views = new EObjectContainmentEList<AbstractView>(AbstractView.class, this, ViewPackage.VIEW_COLLECTION__VIEWS);
		}
		return views;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ComposedView> getComposedViews() {
		if (composedViews == null) {
			composedViews = new EObjectContainmentEList<ComposedView>(ComposedView.class, this, ViewPackage.VIEW_COLLECTION__COMPOSED_VIEWS);
		}
		return composedViews;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractView> getAllViews() {
		if (getAllViewsBodyOCL == null) {
			EOperation eOperation = ViewPackage.Literals.VIEW_COLLECTION.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(ViewPackage.Literals.VIEW_COLLECTION, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAllViewsBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAllViewsBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<AbstractView> result = (Collection<AbstractView>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<AbstractView>(result.size(), result.toArray());
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getAllViews <em>Get All Views</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllViews
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAllViewsBodyOCL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViewPackage.VIEW_COLLECTION__VIEWS:
				return ((InternalEList<?>)getViews()).basicRemove(otherEnd, msgs);
			case ViewPackage.VIEW_COLLECTION__COMPOSED_VIEWS:
				return ((InternalEList<?>)getComposedViews()).basicRemove(otherEnd, msgs);
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
			case ViewPackage.VIEW_COLLECTION__VIEWS:
				return getViews();
			case ViewPackage.VIEW_COLLECTION__COMPOSED_VIEWS:
				return getComposedViews();
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
			case ViewPackage.VIEW_COLLECTION__VIEWS:
				getViews().clear();
				getViews().addAll((Collection<? extends AbstractView>)newValue);
				return;
			case ViewPackage.VIEW_COLLECTION__COMPOSED_VIEWS:
				getComposedViews().clear();
				getComposedViews().addAll((Collection<? extends ComposedView>)newValue);
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
			case ViewPackage.VIEW_COLLECTION__VIEWS:
				getViews().clear();
				return;
			case ViewPackage.VIEW_COLLECTION__COMPOSED_VIEWS:
				getComposedViews().clear();
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
			case ViewPackage.VIEW_COLLECTION__VIEWS:
				return views != null && !views.isEmpty();
			case ViewPackage.VIEW_COLLECTION__COMPOSED_VIEWS:
				return composedViews != null && !composedViews.isEmpty();
		}
		return super.eIsSet(featureID);
	}

		private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
		private static final OCL OCL_ENV = KerblueOCL.newInstance();		
} //ViewCollectionImpl
