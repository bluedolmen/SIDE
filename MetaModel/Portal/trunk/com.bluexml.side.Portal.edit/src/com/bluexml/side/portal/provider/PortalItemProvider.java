/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.provider;


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

import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.provider.PackageItemProvider;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.workflow.WorkflowFactory;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.portal.Portal} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PortalItemProvider
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
	public PortalItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(PortalPackage.Literals.PORTAL__PAGE_SET);
			childrenFeatures.add(PortalPackage.Literals.PORTAL__LAYOUT_SET);
			childrenFeatures.add(PortalPackage.Literals.PORTAL__PORTLET_SET);
			childrenFeatures.add(PortalPackage.Literals.PORTAL__PORTLET_SET_TYPE);
			childrenFeatures.add(PortalPackage.Literals.PORTAL__INTERNAL_PORTLET_SET);
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
	 * This returns Portal.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Portal"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Portal)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Portal_type") :
			getString("_UI_Portal_type") + " " + label;
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

		switch (notification.getFeatureID(Portal.class)) {
			case PortalPackage.PORTAL__PAGE_SET:
			case PortalPackage.PORTAL__LAYOUT_SET:
			case PortalPackage.PORTAL__PORTLET_SET:
			case PortalPackage.PORTAL__PORTLET_SET_TYPE:
			case PortalPackage.PORTAL__INTERNAL_PORTLET_SET:
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
				 PortalFactory.eINSTANCE.createPortal()));

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
				(CommonPackage.Literals.PACKAGE__PACKAGE_SET,
				 FormFactory.eINSTANCE.createWorkflowFormCollection()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.PACKAGE__PACKAGE_SET,
				 FormFactory.eINSTANCE.createClassFormCollection()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.PACKAGE__PACKAGE_SET,
				 FormFactory.eINSTANCE.createSearchFormCollection()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.PACKAGE__PACKAGE_SET,
				 ViewFactory.eINSTANCE.createViewCollection()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.PACKAGE__PACKAGE_SET,
				 WorkflowFactory.eINSTANCE.createProcess()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.PACKAGE__PACKAGE_SET,
				 WorkflowFactory.eINSTANCE.createWfPackage()));

		newChildDescriptors.add
			(createChildParameter
				(PortalPackage.Literals.PORTAL__PAGE_SET,
				 PortalFactory.eINSTANCE.createPage()));

		newChildDescriptors.add
			(createChildParameter
				(PortalPackage.Literals.PORTAL__LAYOUT_SET,
				 PortalFactory.eINSTANCE.createPortalLayout()));

		newChildDescriptors.add
			(createChildParameter
				(PortalPackage.Literals.PORTAL__PORTLET_SET,
				 PortalFactory.eINSTANCE.createPortlet()));

		newChildDescriptors.add
			(createChildParameter
				(PortalPackage.Literals.PORTAL__PORTLET_SET_TYPE,
				 PortalFactory.eINSTANCE.createPortletType()));

		newChildDescriptors.add
			(createChildParameter
				(PortalPackage.Literals.PORTAL__INTERNAL_PORTLET_SET,
				 PortalFactory.eINSTANCE.createPortletInternal()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return PortalEditPlugin.INSTANCE;
	}

}
