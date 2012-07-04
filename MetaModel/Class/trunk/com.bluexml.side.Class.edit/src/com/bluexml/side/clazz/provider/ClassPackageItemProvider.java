/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.provider.PackageItemProvider;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.clazz.ClassPackage} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassPackageItemProvider
	extends PackageItemProvider
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
	public ClassPackageItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(ClazzPackage.Literals.CLASS_PACKAGE__CLASS_SET);
			childrenFeatures.add(ClazzPackage.Literals.CLASS_PACKAGE__ASSOCIATION_SET);
			childrenFeatures.add(ClazzPackage.Literals.CLASS_PACKAGE__ASPECT_SET);
			childrenFeatures.add(ClazzPackage.Literals.CLASS_PACKAGE__ENUMERATION_SET);
			childrenFeatures.add(ClazzPackage.Literals.CLASS_PACKAGE__CONSTRAINT_SET);
			childrenFeatures.add(ClazzPackage.Literals.CLASS_PACKAGE__CUSTOM_DATA_TYPE_SET);
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
	 * This returns ClassPackage.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ClassPackage"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ClassPackage)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ClassPackage_type") :
			getString("_UI_ClassPackage_type") + " " + label;
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

		switch (notification.getFeatureID(ClassPackage.class)) {
			case ClazzPackage.CLASS_PACKAGE__CLASS_SET:
			case ClazzPackage.CLASS_PACKAGE__ASSOCIATION_SET:
			case ClazzPackage.CLASS_PACKAGE__ASPECT_SET:
			case ClazzPackage.CLASS_PACKAGE__ENUMERATION_SET:
			case ClazzPackage.CLASS_PACKAGE__CONSTRAINT_SET:
			case ClazzPackage.CLASS_PACKAGE__CUSTOM_DATA_TYPE_SET:
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
				(CommonPackage.Literals.MODEL_ELEMENT__COMMENTS,
				 ClazzFactory.eINSTANCE.createClassComment()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.MODEL_ELEMENT__COMMENTS,
				 ClazzFactory.eINSTANCE.createAssociationEnd()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.PACKAGE__PACKAGE_SET,
				 ClazzFactory.eINSTANCE.createClassPackage()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.PACKAGE__PACKAGE_SET,
				 ClazzFactory.eINSTANCE.createModel()));

		newChildDescriptors.add
			(createChildParameter
				(ClazzPackage.Literals.CLASS_PACKAGE__CLASS_SET,
				 ClazzFactory.eINSTANCE.createClazz()));

		newChildDescriptors.add
			(createChildParameter
				(ClazzPackage.Literals.CLASS_PACKAGE__CONSTRAINT_SET,
				 CommonFactory.eINSTANCE.createConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(ClazzPackage.Literals.CLASS_PACKAGE__CUSTOM_DATA_TYPE_SET,
				 CommonFactory.eINSTANCE.createCustomDataType()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ClassEditPlugin.INSTANCE;
	}

}
