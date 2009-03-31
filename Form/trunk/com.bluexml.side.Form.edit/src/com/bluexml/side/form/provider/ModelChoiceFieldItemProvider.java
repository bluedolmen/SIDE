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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.formPackage;

/**
 * This is the item provider adapter for a {@link KerblueForms.ModelChoiceField} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelChoiceFieldItemProvider
	extends FieldItemProvider
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
	public ModelChoiceFieldItemProvider(AdapterFactory adapterFactory) {
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

			addReal_classPropertyDescriptor(object);
			addAssociation_classPropertyDescriptor(object);
			addMin_boundPropertyDescriptor(object);
			addMax_boundPropertyDescriptor(object);
			addTargetPropertyDescriptor(object);
			addAssociation_formClassPropertyDescriptor(object);
			addWidgetPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Real class feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReal_classPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ClassReference_real_class_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ClassReference_real_class_feature", "_UI_ClassReference_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 formPackage.Literals.CLASS_REFERENCE__REAL_CLASS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Association class feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAssociation_classPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ClassReference_association_class_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ClassReference_association_class_feature", "_UI_ClassReference_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 formPackage.Literals.CLASS_REFERENCE__ASSOCIATION_CLASS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Min bound feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMin_boundPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelChoiceField_min_bound_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelChoiceField_min_bound_feature", "_UI_ModelChoiceField_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 formPackage.Literals.MODEL_CHOICE_FIELD__MIN_BOUND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Max bound feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMax_boundPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelChoiceField_max_bound_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelChoiceField_max_bound_feature", "_UI_ModelChoiceField_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 formPackage.Literals.MODEL_CHOICE_FIELD__MAX_BOUND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Target feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelChoiceField_target_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelChoiceField_target_feature", "_UI_ModelChoiceField_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 formPackage.Literals.MODEL_CHOICE_FIELD__TARGET,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Association form Class feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAssociation_formClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelChoiceField_association_formClass_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelChoiceField_association_formClass_feature", "_UI_ModelChoiceField_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 formPackage.Literals.MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Widget feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWidgetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelChoiceField_widget_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelChoiceField_widget_feature", "_UI_ModelChoiceField_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 formPackage.Literals.MODEL_CHOICE_FIELD__WIDGET,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns ModelChoiceField.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ModelChoiceField")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public String getText(Object object) {
		String label = ((ModelChoiceField)object).getLabel();
		return label == null || label.length() == 0 ?
			getString("_UI_ModelChoiceField_type") :
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

		switch (notification.getFeatureID(ModelChoiceField.class)) {
			case formPackage.MODEL_CHOICE_FIELD__MIN_BOUND:
			case formPackage.MODEL_CHOICE_FIELD__MAX_BOUND:
			case formPackage.MODEL_CHOICE_FIELD__WIDGET:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
