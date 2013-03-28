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

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Portal.modeler.diagram.dialogs.PortletInternalEditDialog;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.view.AbstractView;

public class PortletInternalUpdateCommand extends Command  {
	
	protected PortletInternal portletInternal;
	
private Map<String,Object> newData;
	
	public PortletInternalUpdateCommand(PortletInternal p_portletInternal, Map<String,Object> p_data) {
		this.portletInternal = p_portletInternal;		
		this.newData = p_data;
	}
	
	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}
	
	/**
	 * Set the new values
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {		
		portletInternal.setType((InternalPortletType) newData.get(PortletInternalEditDialog.PORTLETINTERNAL_Type));
		portletInternal.setView((AbstractView) newData.get(PortletInternalEditDialog.PORTLETINTERNAL_View));
		portletInternal.setForm((FormCollection) newData.get(PortletInternalEditDialog.PORTLETINTERNAL_Form));
		
	}
}
