/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.provider;


import com.bluexml.side.form.FormFactory;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.StaticText;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.common.utils.FormDiagramUtils;
import com.bluexml.side.form.common.utils.InternalModification;

/**
 * This is the item provider adapter for a {@link com.bluexml.side.form.FormGroup} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FormGroupItemProvider
	extends FormElementItemProvider
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
	public FormGroupItemProvider(AdapterFactory adapterFactory) {
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

			addPresentationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Presentation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPresentationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FormGroup_presentation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FormGroup_presentation_feature", "_UI_FormGroup_type"),
				 FormPackage.Literals.FORM_GROUP__PRESENTATION,
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
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(FormPackage.Literals.FORM_GROUP__CHILDREN);
			//childrenFeatures.add(formPackage.Literals.FORM_GROUP__DISABLED);
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
	 * This returns FormGroup.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FormGroup"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public String getText(Object object) {
		String label = ((FormGroup)object).getLabel();
		return label == null || label.length() == 0 ?
			getString("_UI_FormGroup_type") :
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

		switch (notification.getFeatureID(FormGroup.class)) {
			case FormPackage.FORM_GROUP__PRESENTATION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case FormPackage.FORM_GROUP__CHILDREN:
			case FormPackage.FORM_GROUP__DISABLED:
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
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		/*
		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createFormGroup()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createBooleanField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createCharField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createDateField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createDateTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createDecimalField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createFloatField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createIntegerField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createModelChoiceField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createEmailField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createFileField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createImageField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createTimeField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createURLField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createPhoneNumberField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createFormAspect()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createFormClass()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createReference()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createChoiceField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createRegexField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createPasswordField()));

		newChildDescriptors.add
			(createChildParameter
				(formPackage.Literals.FORM_GROUP__CHILDREN,
				 KerblueFormsFactory.eINSTANCE.createVirtualField()));
				 */
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
			childFeature == FormPackage.Literals.FORM_GROUP__CHILDREN ||
			childFeature == FormPackage.Literals.FORM_GROUP__DISABLED;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	@Override
	public Command createRemoveCommand(EditingDomain domain, EObject owner, EReference feature, Collection<?> collection) {
		CompoundCommand cmd = new CompoundCommand();
		// First we check if we synchronize
		if (InternalModification.getMoveToDisabled()) {
			for (Object o : collection) {
				if (o instanceof FormGroup) {
					FormGroup fg = (FormGroup) o;
					if (fg.getChildren().size() > 0) {
						if (!(o instanceof FormAspect)) {
							cmd.append(getMoveCommandForFormGroup(domain, owner, feature, fg));
						} else {
							FormGroup fgCopy = (FormGroup) EcoreUtil.copy(fg);
							Command createCmd = AddCommand.create(domain, FormDiagramUtils.getParentFormClass((FormElement)owner), FormPackage.eINSTANCE.getFormGroup_Disabled(), fgCopy);
							cmd.append(createCmd);
						}
					}
				} else if (o instanceof Field & !(o instanceof VirtualField) & !(o instanceof StaticText) & !(o instanceof ActionField)) {
					Field f = (Field) o;
					Field fcpy = (Field) EcoreUtil.copy(f);
					Command createCmd = AddCommand.create(domain, FormDiagramUtils.getParentFormClass((FormElement)owner), FormPackage.eINSTANCE.getFormGroup_Disabled(), fcpy);
					cmd.append(createCmd);
				}
			}
		}
		cmd.append(super.createRemoveCommand(domain, owner, feature, collection));
		return cmd;
	}

	protected Command getMoveCommandForFormGroup(EditingDomain domain, EObject owner, EReference feature, FormGroup fg) {
		CompoundCommand cmd = new CompoundCommand();
		for (FormElement fe : fg.getChildren()) {
			if (fe instanceof FormGroup) {
				cmd.append(getMoveCommandForFormGroup(domain, owner, feature, (FormGroup)fe));
			} else if (fe instanceof Field & !(fe instanceof VirtualField)) {
				Field f = (Field) fe;
				Field fcpy = (Field) EcoreUtil.copy(f);
				Command createCmd = AddCommand.create(domain, FormDiagramUtils.getParentFormClass((FormElement)owner), FormPackage.eINSTANCE.getFormGroup_Disabled(), fcpy);
				cmd.append(createCmd);
			}
		}
		return cmd;
	}
}
