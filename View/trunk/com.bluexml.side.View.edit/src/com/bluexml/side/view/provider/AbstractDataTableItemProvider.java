/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.provider;


import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.CommonPackage;

import com.bluexml.side.view.AbstractDataTable;
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
 * This is the item provider adapter for a {@link com.bluexml.side.view.AbstractDataTable} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractDataTableItemProvider
	extends DataTableElementItemProvider
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
	public AbstractDataTableItemProvider(AdapterFactory adapterFactory) {
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
			addViewOfPropertyDescriptor(object);
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
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__TAGS);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__COMMENTS);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__METAINFO);
			childrenFeatures.add(ViewPackage.Literals.STYLABLE__STYLING);
			childrenFeatures.add(ViewPackage.Literals.ABSTRACT_VIEW__OPERATIONS);
			childrenFeatures.add(ViewPackage.Literals.ABSTRACT_VIEW__INNER_VIEW);
			childrenFeatures.add(ViewPackage.Literals.PAGINABLE__PAGING);
			childrenFeatures.add(ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS);
			childrenFeatures.add(ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS);
			childrenFeatures.add(ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS);
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
		String label = ((AbstractDataTable)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AbstractDataTable_type") :
			getString("_UI_AbstractDataTable_type") + " " + label;
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

		switch (notification.getFeatureID(AbstractDataTable.class)) {
			case ViewPackage.ABSTRACT_DATA_TABLE__DOCUMENTATION:
			case ViewPackage.ABSTRACT_DATA_TABLE__DESCRIPTION:
			case ViewPackage.ABSTRACT_DATA_TABLE__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ViewPackage.ABSTRACT_DATA_TABLE__TAGS:
			case ViewPackage.ABSTRACT_DATA_TABLE__COMMENTS:
			case ViewPackage.ABSTRACT_DATA_TABLE__METAINFO:
			case ViewPackage.ABSTRACT_DATA_TABLE__STYLING:
			case ViewPackage.ABSTRACT_DATA_TABLE__OPERATIONS:
			case ViewPackage.ABSTRACT_DATA_TABLE__INNER_VIEW:
			case ViewPackage.ABSTRACT_DATA_TABLE__PAGING:
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS:
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS:
			case ViewPackage.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS:
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

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.PAGINABLE__PAGING,
				 ViewFactory.eINSTANCE.createPaging()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS,
				 CommonFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS,
				 CommonFactory.eINSTANCE.createOperationGroup()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS,
				 CommonFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS,
				 CommonFactory.eINSTANCE.createOperationGroup()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS,
				 CommonFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS,
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
			childFeature == ViewPackage.Literals.ABSTRACT_VIEW__OPERATIONS ||
			childFeature == ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS ||
			childFeature == ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS ||
			childFeature == ViewPackage.Literals.ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
