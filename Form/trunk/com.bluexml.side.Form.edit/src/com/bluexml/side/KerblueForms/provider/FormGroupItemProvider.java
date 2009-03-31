/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.KerblueForms.provider;


import com.bluexml.side.KerblueForms.FormGroup;
import com.bluexml.side.KerblueForms.formFactory;
import com.bluexml.side.KerblueForms.formPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.KerblueForms.FormGroup} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FormGroupItemProvider
	extends FormElementItemProvider
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
	public FormGroupItemProvider(AdapterFactory adapterFactory) {
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

			addPresentationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Presentation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPresentationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FormGroup_presentation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FormGroup_presentation_feature", "_UI_FormGroup_type"),
				 formPackage.Literals.FORM_GROUP__PRESENTATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(formPackage.Literals.FORM_GROUP__CHILDREN);
			childrenFeatures.add(formPackage.Literals.FORM_GROUP__DISABLED);
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
	 * This returns FormGroup.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FormGroup"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((FormGroup)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_FormGroup_type") :
			getString("_UI_FormGroup_type") + " " + label;
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

		switch (notification.getFeatureID(FormGroup.class)) {
			case formPackage.FORM_GROUP__PRESENTATION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case formPackage.FORM_GROUP__CHILDREN:
			case formPackage.FORM_GROUP__DISABLED:
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
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createFormGroup()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createBooleanField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createCharField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createDateField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createDateTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createDecimalField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createFloatField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createIntegerField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createModelChoiceField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createEmailField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createFileField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createImageField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createURLField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createPhoneNumberField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createFormAspect()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createFormClass()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createReference()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createChoiceField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createRegexField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createPasswordField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createVirtualField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createActionField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 formFactory.eINSTANCE.createTextField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createFormGroup()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createBooleanField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createCharField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createDateField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createDateTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createDecimalField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createFloatField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createIntegerField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createModelChoiceField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createEmailField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createFileField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createImageField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createURLField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createPhoneNumberField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createFormAspect()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createFormClass()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createReference()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createChoiceField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createRegexField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createPasswordField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createVirtualField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createActionField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__DISABLED,
				 formFactory.eINSTANCE.createTextField()));
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
			childFeature == formPackage.Literals.FORM_GROUP__CHILDREN ||
			childFeature == formPackage.Literals.FORM_GROUP__DISABLED;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
