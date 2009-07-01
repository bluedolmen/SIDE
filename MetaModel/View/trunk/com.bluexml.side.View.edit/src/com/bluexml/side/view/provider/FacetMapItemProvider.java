/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.provider;

import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.view.FacetMap;
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
 * This is the item provider adapter for a {@link com.bluexml.side.view.FacetMap} object.
 * <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * @generated
 */
public class FacetMapItemProvider extends PaginableItemProvider implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FacetMapItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
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
			addViewOfPropertyDescriptor(object);
			addDisplayEmptyFacetPropertyDescriptor(object);
			addFacetDisplayTypePropertyDescriptor(object);
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
				 getString("_UI_AbstractViewOf_viewOf_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractViewOf_viewOf_feature", "_UI_AbstractViewOf_type"),
				 ViewPackage.Literals.ABSTRACT_VIEW_OF__VIEW_OF,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Display Empty Facet feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDisplayEmptyFacetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FacetMap_displayEmptyFacet_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FacetMap_displayEmptyFacet_feature", "_UI_FacetMap_type"),
				 ViewPackage.Literals.FACET_MAP__DISPLAY_EMPTY_FACET,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Facet Display Type feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFacetDisplayTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FacetMap_facetDisplayType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FacetMap_facetDisplayType_feature", "_UI_FacetMap_type"),
				 ViewPackage.Literals.FACET_MAP__FACET_DISPLAY_TYPE,
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ViewPackage.Literals.STYLABLE__STYLING);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__TAGS);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__COMMENTS);
			childrenFeatures.add(CommonPackage.Literals.MODEL_ELEMENT__METAINFO);
			childrenFeatures.add(ViewPackage.Literals.FIELD_CONTAINER__CHILDREN);
			childrenFeatures.add(ViewPackage.Literals.FIELD_CONTAINER__DISABLED);
			childrenFeatures.add(ViewPackage.Literals.ABSTRACT_VIEW__OPERATIONS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns FacetMap.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FacetMap"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @_generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((FacetMap) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_FacetMap_type")
				: label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(FacetMap.class)) {
			case ViewPackage.FACET_MAP__DOCUMENTATION:
			case ViewPackage.FACET_MAP__DESCRIPTION:
			case ViewPackage.FACET_MAP__NAME:
			case ViewPackage.FACET_MAP__PREFIX:
			case ViewPackage.FACET_MAP__SUFFIX:
			case ViewPackage.FACET_MAP__HIDDEN:
			case ViewPackage.FACET_MAP__DISPLAY_EMPTY_FACET:
			case ViewPackage.FACET_MAP__FACET_DISPLAY_TYPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ViewPackage.FACET_MAP__STYLING:
			case ViewPackage.FACET_MAP__TAGS:
			case ViewPackage.FACET_MAP__COMMENTS:
			case ViewPackage.FACET_MAP__METAINFO:
			case ViewPackage.FACET_MAP__CHILDREN:
			case ViewPackage.FACET_MAP__DISABLED:
			case ViewPackage.FACET_MAP__OPERATIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @_generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(
				ViewPackage.Literals.PAGINABLE__PAGING, ViewFactory.eINSTANCE
						.createPaging()));

//		newChildDescriptors.add(createChildParameter(
//				ViewPackage.Literals.FIELD_CONTAINER__CHILDREN,
//				ViewFactory.eINSTANCE.createDataList()));
//
//		newChildDescriptors.add(createChildParameter(
//				ViewPackage.Literals.FIELD_CONTAINER__CHILDREN,
//				ViewFactory.eINSTANCE.createDataTable()));
//
//		newChildDescriptors.add(createChildParameter(
//				ViewPackage.Literals.FIELD_CONTAINER__CHILDREN,
//				ViewFactory.eINSTANCE.createFacetMap()));
//
//		newChildDescriptors.add(createChildParameter(
//				ViewPackage.Literals.FIELD_CONTAINER__CHILDREN,
//				ViewFactory.eINSTANCE.createTree()));
	}

	/**
	 * This returns the label text for
	 * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == ViewPackage.Literals.FIELD_CONTAINER__CHILDREN ||
			childFeature == ViewPackage.Literals.FIELD_CONTAINER__DISABLED;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
