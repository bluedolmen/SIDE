/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import com.bluexml.side.requirements.Organization;
import com.bluexml.side.requirements.RequirementsFactory;
import com.bluexml.side.requirements.RequirementsPackage;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.requirements.Organization} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OrganizationItemProvider
	extends AnnotableElementItemProvider
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
	public OrganizationItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS);
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
	 * This returns Organization.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Organization"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = crop(((Organization)object).getName());
		return label == null || label.length() == 0 ?
			getString("_UI_Organization_type") :
			getString("_UI_Organization_type") + " " + label;
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

		switch (notification.getFeatureID(Organization.class)) {
			case RequirementsPackage.ORGANIZATION__CHILD_ELEMENTS:
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
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createEntity()));

		newChildDescriptors.add
			(createChildParameter
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createRelationShip()));

		newChildDescriptors.add
			(createChildParameter
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createAttribute()));

		newChildDescriptors.add
			(createChildParameter
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createAnnotableElement()));

		newChildDescriptors.add
			(createChildParameter
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createOrganization()));

		newChildDescriptors.add
			(createChildParameter
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createAgent()));

		newChildDescriptors.add
			(createChildParameter
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createGoal()));

		newChildDescriptors.add
			(createChildParameter
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createRequirementsDefinition()));

		newChildDescriptors.add
			(createChildParameter
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createPrivilegeGroup()));

		newChildDescriptors.add
			(createChildParameter
				(RequirementsPackage.Literals.ORGANIZATION__CHILD_ELEMENTS,
				 RequirementsFactory.eINSTANCE.createProcess()));
	}

}
