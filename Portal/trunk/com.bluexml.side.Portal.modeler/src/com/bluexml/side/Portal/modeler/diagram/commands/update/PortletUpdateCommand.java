package com.bluexml.side.Portal.modeler.diagram.commands.update;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Portal.modeler.diagram.dialogs.PortletEditDialog;
import com.bluexml.side.portal.Portlet;

public class PortletUpdateCommand   extends Command {
	
	protected Portlet portlet;
	
	private Map<String,Object> newData;
	
	public PortletUpdateCommand(Portlet p_portlet, Map<String,Object> p_data) {
		this.portlet = p_portlet;		
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
		portlet.setName((String) newData.get(PortletEditDialog.PORTLET_Name));				
	}

}
