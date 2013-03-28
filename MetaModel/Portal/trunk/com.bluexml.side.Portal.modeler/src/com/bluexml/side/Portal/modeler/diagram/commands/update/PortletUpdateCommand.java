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
package com.bluexml.side.Portal.modeler.diagram.commands.update;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletInternal;

public class PortletUpdateCommand extends Command {

	protected Portlet portlet;

	private Map<String, Object> newData;
	private Map<String, Object> oldData = new HashMap<String, Object>();

	public static final String PORTLET_Name = "PORTLET_Name";
	public static final String PORTLET_IsInstanceOfPortletType = "PORTLET_IsInstanceOfPortletType";
	public static final String PORTLET_IsPortletInternal = "PORTLET_IsPortletInternal";

	public PortletUpdateCommand(Portlet p_portlet, Map<String, Object> p_data) {
		this.portlet = p_portlet;
		this.newData = p_data;
	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		// save previous values
		oldData.put(PORTLET_IsPortletInternal, portlet.getIsPortletInternal());
		oldData.put(PORTLET_IsInstanceOfPortletType, portlet.getIsInstanceOfPortletType());
		oldData.put(PORTLET_Name, portlet.getName());

		// update portlet
		updatePortal(newData);
	}

	@Override
	public void undo() {
		updatePortal(oldData);
	}

	/**
	 * read properties map and update Portlet
	 */
	private void updatePortal(Map<String, Object> data) {

		if (data.containsKey(PORTLET_IsInstanceOfPortletType)) {
			portlet.setIsInstanceOfPortletType((InstanciatePortletType) data.get(PORTLET_IsInstanceOfPortletType));
		}
		if (data.containsKey(PORTLET_IsPortletInternal)) {
			portlet.setIsPortletInternal((PortletInternal) data.get(PORTLET_IsPortletInternal));
		}
		if (data.containsKey(PORTLET_Name)) {
			portlet.setName((String) data.get(PORTLET_Name));
		}

	}
}
