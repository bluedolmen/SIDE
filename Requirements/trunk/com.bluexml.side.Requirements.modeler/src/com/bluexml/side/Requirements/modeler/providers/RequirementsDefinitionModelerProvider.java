/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.topcased.modeler.providers.ILabelFeatureProvider;

import com.bluexml.side.requirements.RequirementsPackage;

/**
 * This is the item provider adpater for a {@link com.bluexml.side.requirements.RequirementsDefinition} object.
 *
 * @generated
 */
public class RequirementsDefinitionModelerProvider extends
		OrganizationModelerProvider implements ILabelFeatureProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 *
	 * @param adapterFactory the adapter factory
	 * @generated
	 */
	public RequirementsDefinitionModelerProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * @see org.topcased.modeler.providers.ILabelFeatureProvider#getLabelFeature(java.lang.Object)
	 * @generated
	 */
	public EAttribute getLabelFeature(Object object) {
		return RequirementsPackage.eINSTANCE.getBasicElement_Name();
	}
}