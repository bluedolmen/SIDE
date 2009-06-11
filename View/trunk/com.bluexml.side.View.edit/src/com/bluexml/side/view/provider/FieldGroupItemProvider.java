/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.provider;


import com.bluexml.side.view.FieldGroup;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;

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

/**
 * This is the item provider adapter for a {@link com.bluexml.side.view.FieldGroup} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FieldGroupItemProvider
	extends FieldElementItemProvider
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
	public FieldGroupItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(ViewPackage.Literals.FIELD_GROUP__CHILDREN);
			childrenFeatures.add(ViewPackage.Literals.FIELD_GROUP__DISABLED);
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
	 * This returns FieldGroup.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FieldGroup"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @_generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((FieldGroup)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_FieldGroup_type") :
			label;
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

		switch (notification.getFeatureID(FieldGroup.class)) {
			case ViewPackage.FIELD_GROUP__CHILDREN:
			case ViewPackage.FIELD_GROUP__DISABLED:
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
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createCol()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createFieldGroup()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createTextField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createPasswordField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createBooleanField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createFloatField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createActionField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createDateField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createDateTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createPhoneNumberField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createEmailField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createFileField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createFacetMap()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createDataList()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createTree()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createDataTable()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createSelectField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createHtmlField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__CHILDREN,
				 ViewFactory.eINSTANCE.createIntegerField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createCol()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createFieldGroup()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createTextField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createPasswordField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createBooleanField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createFloatField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createActionField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createDateField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createDateTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createPhoneNumberField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createEmailField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createFileField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createFacetMap()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createDataList()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createTree()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createDataTable()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createSelectField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createHtmlField()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.FIELD_GROUP__DISABLED,
				 ViewFactory.eINSTANCE.createIntegerField()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == ViewPackage.Literals.FIELD_GROUP__CHILDREN ||
			childFeature == ViewPackage.Literals.FIELD_GROUP__DISABLED;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
