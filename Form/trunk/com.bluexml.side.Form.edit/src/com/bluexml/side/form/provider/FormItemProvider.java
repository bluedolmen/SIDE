/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.provider;


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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import com.bluexml.side.form.Form;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.formFactory;
import com.bluexml.side.form.formPackage;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.form.Form} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FormItemProvider
	extends ItemProviderAdapter
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
	public FormItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Form_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Form_name_feature", "_UI_Form_type"),
				 formPackage.Literals.FORM__NAME,
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
			childrenFeatures.add(formPackage.Literals.FORM__ROOT);
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
	 * This returns Form.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Form"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public String getText(Object object) {
		String label = ((Form)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Form_type") :
			label + " (" +getString("_UI_Form_type") + ") ";
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

		switch (notification.getFeatureID(Form.class)) {
			case formPackage.FORM__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case formPackage.FORM__ROOT:
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
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		if (object instanceof Form) {
			Form f = (Form) object;
			if (f.eContainer() instanceof WorkflowFormCollection) {
				newChildDescriptors.add
				(createChildParameter
					(formPackage.Literals.FORM__ROOT,
					 formFactory.eINSTANCE.createFormWorkflow()));
			} else if (f.eContainer() instanceof FormCollection) {
				newChildDescriptors.add
				(createChildParameter
					(formPackage.Literals.FORM__ROOT,
					 formFactory.eINSTANCE.createFormClass()));
			} 
		}
		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 formFactory.eINSTANCE.createFormGroup()));
		/*
		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createBooleanField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createCharField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createDateField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createDateTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createDecimalField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createFloatField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createIntegerField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createModelChoiceField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createEmailField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createFileField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createImageField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createURLField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createPhoneNumberField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createFormAspect()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createReference()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createChoiceField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createRegexField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createPasswordField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM__ROOT,
				 KerblueFormsFactory.eINSTANCE.createVirtualField()));
				 */
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return FormsEditPlugin.INSTANCE;
	}

}
