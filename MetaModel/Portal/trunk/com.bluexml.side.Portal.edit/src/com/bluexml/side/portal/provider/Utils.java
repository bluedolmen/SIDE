/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
