/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.provider;


import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.CommonPackage;

import com.bluexml.side.view.Col;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;

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
 * This is the item provider adapter for a {@link com.bluexml.side.view.Col} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ColItemProvider
	extends MovableItemProvider
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
	public ColItemProvider(AdapterFactory adapterFactory) {
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

			addEditablePropertyDescriptor(object);
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
	 * This adds a property descriptor for the Editable feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditablePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Editable_editable_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Editable_editable_feature", "_UI_Editable_type"),
				 ViewPackage.Literals.EDITABLE__EDITABLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
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
				 false,
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
				 true,
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
			childrenFeatures.add(ViewPackage.Literals.FILTERABLE__FILTERING);
			childrenFeatures.add(ViewPackage.Literals.STYLABLE__STYLING);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__TAGS);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__COMMENTS);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__METAINFO);
			childrenFeatures.add(ViewPackage.Literals.FIELD_GROUP__CHILDREN);
			childrenFeatures.add(ViewPackage.Literals.FIELD_GROUP__DISABLED);
			childrenFeatures.add(ViewPackage.Literals.SORTABLE__SORTING);
			childrenFeatures.add(ViewPackage.Literals.COL__ACTIONS);
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
	 * This returns Col.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Col"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @_generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Col)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Col_type") :
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

		switch (notification.getFeatureID(Col.class)) {
			case ViewPackage.COL__EDITABLE:
			case ViewPackage.COL__DOCUMENTATION:
			case ViewPackage.COL__DESCRIPTION:
			case ViewPackage.COL__NAME:
			case ViewPackage.COL__PREFIX:
			case ViewPackage.COL__SUFFIX:
			case ViewPackage.COL__HIDDEN:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ViewPackage.COL__FILTERING:
			case ViewPackage.COL__STYLING:
			case ViewPackage.COL__TAGS:
			case ViewPackage.COL__COMMENTS:
			case ViewPackage.COL__METAINFO:
			case ViewPackage.COL__CHILDREN:
			case ViewPackage.COL__DISABLED:
			case ViewPackage.COL__SORTING:
			case ViewPackage.COL__ACTIONS:
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
				(ViewPackage.Literals.FILTERABLE__FILTERING,
				 ViewFactory.eINSTANCE.createFiltering()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.STYLABLE__STYLING,
				 ViewFactory.eINSTANCE.createStyling()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.MODEL_ELEMENT__TAGS,
				 CommonFactory.eINSTANCE.createTag()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.MODEL_ELEMENT__COMMENTS,
				 CommonFactory.eINSTANCE.createComment()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.MODEL_ELEMENT__METAINFO,
				 CommonFactory.eINSTANCE.createMetaInfo()));

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

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.SORTABLE__SORTING,
				 ViewFactory.eINSTANCE.createSorting()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.COL__ACTIONS,
				 CommonFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.COL__ACTIONS,
				 CommonFactory.eINSTANCE.createOperationGroup()));
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
