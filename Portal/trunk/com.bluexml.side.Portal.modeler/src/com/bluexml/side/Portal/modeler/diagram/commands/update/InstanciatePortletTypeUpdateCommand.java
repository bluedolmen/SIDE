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
				
				PortletAttribute attribute = getAttribute(inst.getAttributeName());
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
