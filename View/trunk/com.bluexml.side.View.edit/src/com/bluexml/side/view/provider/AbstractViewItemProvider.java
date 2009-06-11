/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.provider;


import com.bluexml.side.common.CommonFactory;

import com.bluexml.side.common.provider.NamedModelElementItemProvider;

import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.view.AbstractView} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractViewItemProvider
	extends NamedModelElementItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractViewItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addViewOfPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the View Of feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addViewOfPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractView_viewOf_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractView_viewOf_feature", "_UI_AbstractView_type"),
				 ViewPackage.Literals.ABSTRACT_VIEW__VIEW_OF,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ViewPackage.Literals.STYLABLE__STYLING);
			childrenFeatures.add(ViewPackage.Literals.ABSTRACT_VIEW__OPERATIONS);
			childrenFeatures.add(ViewPackage.Literals.ABSTRACT_VIEW__INNER_VIEW);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns AbstractView.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AbstractView"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AbstractView)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AbstractView_type") :
			getString("_UI_AbstractView_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(AbstractView.class)) {
			case ViewPackage.ABSTRACT_VIEW__STYLING:
			case ViewPackage.ABSTRACT_VIEW__OPERATIONS:
			case ViewPackage.ABSTRACT_VIEW__INNER_VIEW:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.STYLABLE__STYLING,
				 ViewFactory.eINSTANCE.createStyling()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_VIEW__OPERATIONS,
				 CommonFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_VIEW__OPERATIONS,
				 CommonFactory.eINSTANCE.createOperationGroup()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_VIEW__INNER_VIEW,
				 ViewFactory.eINSTANCE.createFacetMap()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_VIEW__INNER_VIEW,
				 ViewFactory.eINSTANCE.createDataList()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_VIEW__INNER_VIEW,
				 ViewFactory.eINSTANCE.createTree()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_VIEW__INNER_VIEW,
				 ViewFactory.eINSTANCE.createDataTable()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ViewEditPlugin.INSTANCE;
	}

}
