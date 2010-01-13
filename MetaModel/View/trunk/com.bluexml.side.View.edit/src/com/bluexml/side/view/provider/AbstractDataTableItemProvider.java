/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.provider;
 

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

import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.view.AbstractDataTable;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.view.AbstractDataTable} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractDataTableItemProvider
	extends AbstractViewOfItemProvider
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
			childFeature == ViewPackage.Literals.FIELD_CONTAINER__CHILDREN ||
			childFeature == ViewPackage.Literals.FIELD_CONTAINER__DISABLED ||
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
