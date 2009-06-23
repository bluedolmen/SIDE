/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Workflow.modeler.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.topcased.modeler.providers.ILabelFeatureProvider;

import com.bluexml.side.workflow.WorkflowPackage;

/**
 * This is the item provider adpater for a {@link com.bluexml.side.workflow.Swimlane} object.
 *
 * @generated
 */
public class SwimlaneModelerProvider extends ItemProviderAdapter implements
		ILabelFeatureProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 *
	 * @param adapterFactory the adapter factory
	 * @generated
	 */
	public SwimlaneModelerProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * @see org.topcased.modeler.providers.ILabelFeatureProvider#getLabelFeature(java.lang.Object)
	 * @generated
	 */
	public EAttribute getLabelFeature(Object object) {
		return WorkflowPackage.eINSTANCE.getSwimlane_Name();
	}
}