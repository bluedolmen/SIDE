/*******************************************************************************
 * 
 ******************************************************************************/
package com.bluexml.side.Class.modeler.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.topcased.modeler.providers.ILabelFeatureProvider;

import com.bluexml.side.common.CommonPackage;

/**
 * This is the item provider adpater for a {@link com.bluexml.side.clazz.Attribute} object.
 *
 * @generated
 */
public class AttributeModelerProvider extends
		NamedClassModelElementModelerProvider implements ILabelFeatureProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 *
	 * @param adapterFactory the adapter factory
	 * @generated
	 */
	public AttributeModelerProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * @see org.topcased.modeler.providers.ILabelFeatureProvider#getLabelFeature(java.lang.Object)
	 * @generated
	 */
	public EAttribute getLabelFeature(Object object) {
		return CommonPackage.eINSTANCE.getNamedModelElement_Name();
	}
}