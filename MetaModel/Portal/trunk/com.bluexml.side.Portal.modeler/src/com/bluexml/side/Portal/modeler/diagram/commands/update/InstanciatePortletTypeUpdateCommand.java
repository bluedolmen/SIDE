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

import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Portal.modeler.diagram.dialogs.InstanciatePortletTypeEditDialog;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.InstancesDataStructure;
import com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure.InstancesDataStructure.InstancesObject;
import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletAttributeInstance;

public class InstanciatePortletTypeUpdateCommand extends Command {
	private InstanciatePortletType instances;
	
	private Map<String,Object> newData;
	
	public InstanciatePortletTypeUpdateCommand(InstanciatePortletType p_instances, Map<String,Object> p_data) {
		this.instances = p_instances;		
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
		
		InstancesDataStructure instancesDS = (InstancesDataStructure) newData.get(InstanciatePortletTypeEditDialog.INSTANCIATEPORTLETTYPE_instances);
		
		// Perform update for input parameters
		if (instancesDS.getData() != null) {
			Iterator<InstancesObject> iterator = instancesDS.getData().iterator();
			instances.getInstances().clear();
			while (iterator.hasNext()) {
				InstancesObject inst = iterator.next();	
				
				PortletAttribute attribute = getAttribute(inst.getKey());
				if (attribute != null) {
					PortletAttributeInstance instance = PortalFactory.eINSTANCE.createPortletAttributeInstance();	
					instance.setValue(inst.getValue());
					instance.setInstanceOf(attribute);				
					instances.getInstances().add(instance);
				}				
			}
		}
	}	
	
	protected PortletAttribute getAttribute(String p_name) {
		Iterator<PortletAttribute> itAttr = instances.getPortletType().getHaveAttribute().iterator();
		boolean found = false;
		PortletAttribute wanted = null;
		
		while (itAttr.hasNext() && !found) {
			PortletAttribute attr = itAttr.next();
			if (attr.getName().equalsIgnoreCase(p_name)) {
				found = true;
				wanted = attr;
			}
		}		
		return wanted;
	}
}
