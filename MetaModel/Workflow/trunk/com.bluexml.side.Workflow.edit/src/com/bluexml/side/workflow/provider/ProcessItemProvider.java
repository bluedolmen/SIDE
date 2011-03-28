/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow.provider;


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
import org.eclipse.emf.edit.provider.ViewerNotification;

import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.provider.PackageItemProvider;
import com.bluexml.side.workflow.WorkflowFactory;
import com.bluexml.side.workflow.WorkflowPackage;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.workflow.Process} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessItemProvider
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
	public ProcessItemProvider(AdapterFactory adapterFactory) {
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

			addContentTypePropertyDescriptor(object);
			addTitlePropertyDescriptor(object);
			addProcessDescriptionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Content Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContentTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Process_contentType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Process_contentType_feature", "_UI_Process_type"),
				 WorkflowPackage.Literals.PROCESS__CONTENT_TYPE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Title feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTitlePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Process_title_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Process_title_feature", "_UI_Process_type"),
				 WorkflowPackage.Literals.PROCESS__TITLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Process Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProcessDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Process_processDescription_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Process_processDescription_feature", "_UI_Process_type"),
				 WorkflowPackage.Literals.PROCESS__PROCESS_DESCRIPTION,
				 true,
				 true,
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
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__SWIMLANE);
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__STARTSTATE);
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__ENDSTATE);
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__NODE);
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__TASKNODE);
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__PROCESSSTATE);
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__FORK);
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__JOIN);
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__DECISION);
			childrenFeatures.add(WorkflowPackage.Literals.PROCESS__ELEMENTS);
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
	 * This returns Process.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Process"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((com.bluexml.side.workflow.Process)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Process_type") :
			getString("_UI_Process_type") + " " + label;
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

		switch (notification.getFeatureID(com.bluexml.side.workflow.Process.class)) {
			case WorkflowPackage.PROCESS__TITLE:
			case WorkflowPackage.PROCESS__PROCESS_DESCRIPTION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case WorkflowPackage.PROCESS__SWIMLANE:
			case WorkflowPackage.PROCESS__STARTSTATE:
			case WorkflowPackage.PROCESS__ENDSTATE:
			case WorkflowPackage.PROCESS__NODE:
			case WorkflowPackage.PROCESS__TASKNODE:
			case WorkflowPackage.PROCESS__PROCESSSTATE:
			case WorkflowPackage.PROCESS__FORK:
			case WorkflowPackage.PROCESS__JOIN:
			case WorkflowPackage.PROCESS__DECISION:
			case WorkflowPackage.PROCESS__ELEMENTS:
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
				 WorkflowFactory.eINSTANCE.createProcess()));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.PACKAGE__PACKAGE_SET,
				 WorkflowFactory.eINSTANCE.createWfPackage()));

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
				(WorkflowPackage.Literals.PROCESS__SWIMLANE,
				 WorkflowFactory.eINSTANCE.createSwimlane()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__STARTSTATE,
				 WorkflowFactory.eINSTANCE.createStartState()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ENDSTATE,
				 WorkflowFactory.eINSTANCE.createEndState()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__NODE,
				 WorkflowFactory.eINSTANCE.createNode()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__TASKNODE,
				 WorkflowFactory.eINSTANCE.createTaskNode()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__PROCESSSTATE,
				 WorkflowFactory.eINSTANCE.createProcessState()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__FORK,
				 WorkflowFactory.eINSTANCE.createFork()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__JOIN,
				 WorkflowFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__DECISION,
				 WorkflowFactory.eINSTANCE.createDecision()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createSwimlane()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createTransitionTask()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createUserTask()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createStartState()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createTaskNode()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createEndState()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createNode()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createProcessState()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createFork()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createDecision()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createAction()));

		newChildDescriptors.add
			(createChildParameter
				(WorkflowPackage.Literals.PROCESS__ELEMENTS,
				 WorkflowFactory.eINSTANCE.createTimer()));
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
			childFeature == WorkflowPackage.Literals.PROCESS__SWIMLANE ||
			childFeature == WorkflowPackage.Literals.PROCESS__ELEMENTS ||
			childFeature == WorkflowPackage.Literals.PROCESS__STARTSTATE ||
			childFeature == WorkflowPackage.Literals.PROCESS__ENDSTATE ||
			childFeature == WorkflowPackage.Literals.PROCESS__NODE ||
			childFeature == WorkflowPackage.Literals.PROCESS__TASKNODE ||
			childFeature == WorkflowPackage.Literals.PROCESS__PROCESSSTATE ||
			childFeature == WorkflowPackage.Literals.PROCESS__FORK ||
			childFeature == WorkflowPackage.Literals.PROCESS__JOIN ||
			childFeature == WorkflowPackage.Literals.PROCESS__DECISION;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return WorkflowEditPlugin.INSTANCE;
	}

}
