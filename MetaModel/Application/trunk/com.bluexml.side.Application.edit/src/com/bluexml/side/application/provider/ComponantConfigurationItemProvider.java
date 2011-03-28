/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application.provider;


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

import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.ApplicationPackage;
import com.bluexml.side.application.ComponantConfiguration;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.application.ComponantConfiguration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponantConfigurationItemProvider
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
	public ComponantConfigurationItemProvider(AdapterFactory adapterFactory) {
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

			addId_techno_versionPropertyDescriptor(object);
			addImpl_classPropertyDescriptor(object);
			addIdPropertyDescriptor(object);
			addMetaModelNamePropertyDescriptor(object);
			addTechnologyVersionNamePropertyDescriptor(object);
			addTechnologyNamePropertyDescriptor(object);
			addModuleContraintsPropertyDescriptor(object);
			addContributorIdPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id techno version feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addId_techno_versionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponantConfiguration_id_techno_version_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponantConfiguration_id_techno_version_feature", "_UI_ComponantConfiguration_type"),
				 ApplicationPackage.Literals.COMPONANT_CONFIGURATION__ID_TECHNO_VERSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Impl class feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addImpl_classPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponantConfiguration_impl_class_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponantConfiguration_impl_class_feature", "_UI_ComponantConfiguration_type"),
				 ApplicationPackage.Literals.COMPONANT_CONFIGURATION__IMPL_CLASS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponantConfiguration_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponantConfiguration_id_feature", "_UI_ComponantConfiguration_type"),
				 ApplicationPackage.Literals.COMPONANT_CONFIGURATION__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Meta Model Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMetaModelNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponantConfiguration_metaModelName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponantConfiguration_metaModelName_feature", "_UI_ComponantConfiguration_type"),
				 ApplicationPackage.Literals.COMPONANT_CONFIGURATION__META_MODEL_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Technology Version Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTechnologyVersionNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponantConfiguration_technologyVersionName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponantConfiguration_technologyVersionName_feature", "_UI_ComponantConfiguration_type"),
				 ApplicationPackage.Literals.COMPONANT_CONFIGURATION__TECHNOLOGY_VERSION_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Technology Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTechnologyNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponantConfiguration_technologyName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponantConfiguration_technologyName_feature", "_UI_ComponantConfiguration_type"),
				 ApplicationPackage.Literals.COMPONANT_CONFIGURATION__TECHNOLOGY_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Module Contraints feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addModuleContraintsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponantConfiguration_moduleContraints_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponantConfiguration_moduleContraints_feature", "_UI_ComponantConfiguration_type"),
				 ApplicationPackage.Literals.COMPONANT_CONFIGURATION__MODULE_CONTRAINTS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Contributor Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContributorIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ComponantConfiguration_contributorId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ComponantConfiguration_contributorId_feature", "_UI_ComponantConfiguration_type"),
				 ApplicationPackage.Literals.COMPONANT_CONFIGURATION__CONTRIBUTOR_ID,
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
			childrenFeatures.add(ApplicationPackage.Literals.COMPONANT_CONFIGURATION__OPTIONS);
			childrenFeatures.add(ApplicationPackage.Literals.COMPONANT_CONFIGURATION__MODULE_CONTRAINTS);
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ComponantConfiguration)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_ComponantConfiguration_type") :
			getString("_UI_ComponantConfiguration_type") + " " + label;
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

		switch (notification.getFeatureID(ComponantConfiguration.class)) {
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID_TECHNO_VERSION:
			case ApplicationPackage.COMPONANT_CONFIGURATION__IMPL_CLASS:
			case ApplicationPackage.COMPONANT_CONFIGURATION__ID:
			case ApplicationPackage.COMPONANT_CONFIGURATION__META_MODEL_NAME:
			case ApplicationPackage.COMPONANT_CONFIGURATION__TECHNOLOGY_VERSION_NAME:
			case ApplicationPackage.COMPONANT_CONFIGURATION__TECHNOLOGY_NAME:
			case ApplicationPackage.COMPONANT_CONFIGURATION__CONTRIBUTOR_ID:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ApplicationPackage.COMPONANT_CONFIGURATION__OPTIONS:
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
				(ApplicationPackage.Literals.COMPONANT_CONFIGURATION__OPTIONS,
				 ApplicationFactory.eINSTANCE.createOption()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ApplicationEditPlugin.INSTANCE;
	}

}
