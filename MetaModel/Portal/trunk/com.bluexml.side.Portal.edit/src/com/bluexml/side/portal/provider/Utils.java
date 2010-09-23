package com.bluexml.side.portal.provider;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;

import com.bluexml.side.form.provider.FormItemProviderAdapterFactory;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.view.provider.ViewItemProviderAdapterFactory;

public class Utils {

	/**
	 * @param pi
	 * @return
	 */
	public static String getPortletInternalLabel(PortletInternal pi) {
		String textLabel = "";
		if (pi.getType() != null && pi.getType().toString().length() > 0) {
			if (pi.getType().equals(InternalPortletType.FORM) && pi.getForm() != null) {
				ILabelProvider labelProvider = new AdapterFactoryLabelProvider(new FormItemProviderAdapterFactory());
				textLabel += labelProvider.getText(pi.getForm());
			} else if (pi.getType().equals(InternalPortletType.VIEW) && pi.getView() != null) {
				ILabelProvider labelProvider = new AdapterFactoryLabelProvider(new ViewItemProviderAdapterFactory());
				textLabel = pi.getType() + " ";
				textLabel += labelProvider.getText(pi.getView());
			}
		}
		return textLabel;
	}
}
