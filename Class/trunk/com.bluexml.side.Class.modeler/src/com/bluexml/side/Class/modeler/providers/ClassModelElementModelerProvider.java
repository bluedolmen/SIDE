/*******************************************************************************
 * 
 ******************************************************************************/
package com.bluexml.side.Class.modeler.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.topcased.modeler.providers.ILabelFeatureProvider;

import com.bluexml.side.clazz.ClazzPackage;

/**
 * This is the item provider adpater for a {@link com.bluexml.side.clazz.ClassModelElement} object.
 *
 * @generated
 */
public class ClassModelElementModelerProvider extends ItemProviderAdapter
		implements ILabelFeatureProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 *
	 * @param adapterFactory the adapter factory
	 * @generated
	 */
	public ClassModelElementModelerProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * @see org.topcased.modeler.providers.ILabelFeatureProvider#getLabelFeature(java.lang.Object)
	 * @generated
	 */
	public EAttribute getLabelFeature(Object object) {
		return ClazzPackage.eINSTANCE.getClassModelElement_Documentation();
	}
}