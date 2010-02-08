/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.provider;


import com.bluexml.side.clazz.ClazzFactory;
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

import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.ViewPackage;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.view.FieldElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FieldElementItemProvider
	extends StylableItemProvider
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
	public FieldElementItemProvider(AdapterFactory adapterFactory) {
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

			addStereotypesPropertyDescriptor(object);
			addDocumentationPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addMapToPropertyDescriptor(object);
			addPrefixPropertyDescriptor(object);
			addSuffixPropertyDescriptor(object);
			addHiddenPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Stereotypes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStereotypesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelElement_stereotypes_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelElement_stereotypes_feature", "_UI_ModelElement_type"),
				 CommonPackage.Literals.MODEL_ELEMENT__STEREOTYPES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Documentation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDocumentationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelElement_documentation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelElement_documentation_feature", "_UI_ModelElement_type"),
				 CommonPackage.Literals.MODEL_ELEMENT__DOCUMENTATION,
				 true,
				 true,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelElement_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelElement_description_feature", "_UI_ModelElement_type"),
				 CommonPackage.Literals.MODEL_ELEMENT__DESCRIPTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
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
				 getString("_UI_NamedModelElement_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_NamedModelElement_name_feature", "_UI_NamedModelElement_type"),
				 CommonPackage.Literals.NAMED_MODEL_ELEMENT__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Map To feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMapToPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FieldElement_mapTo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FieldElement_mapTo_feature", "_UI_FieldElement_type"),
				 ViewPackage.Literals.FIELD_ELEMENT__MAP_TO,
				 false,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Prefix feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPrefixPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FieldElement_prefix_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FieldElement_prefix_feature", "_UI_FieldElement_type"),
				 ViewPackage.Literals.FIELD_ELEMENT__PREFIX,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Suffix feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSuffixPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FieldElement_suffix_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FieldElement_suffix_feature", "_UI_FieldElement_type"),
				 ViewPackage.Literals.FIELD_ELEMENT__SUFFIX,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Hidden feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHiddenPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FieldElement_hidden_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FieldElement_hidden_feature", "_UI_FieldElement_type"),
				 ViewPackage.Literals.FIELD_ELEMENT__HIDDEN,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__TAGS);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__COMMENTS);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__METAINFO);
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
	 * @_generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((FieldElement)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_FieldElement_type") :
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

		switch (notification.getFeatureID(FieldElement.class)) {
			case ViewPackage.FIELD_ELEMENT__DOCUMENTATION:
			case ViewPackage.FIELD_ELEMENT__DESCRIPTION:
			case ViewPackage.FIELD_ELEMENT__NAME:
			case ViewPackage.FIELD_ELEMENT__PREFIX:
			case ViewPackage.FIELD_ELEMENT__SUFFIX:
			case ViewPackage.FIELD_ELEMENT__HIDDEN:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ViewPackage.FIELD_ELEMENT__TAGS:
			case ViewPackage.FIELD_ELEMENT__COMMENTS:
			case ViewPackage.FIELD_ELEMENT__METAINFO:
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
	 * @_generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
		(createChildParameter
			(CommonPackage.Literals.MODEL_ELEMENT__COMMENTS,
			 CommonFactory.eINSTANCE.createComment()));
	}

}
