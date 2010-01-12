package com.bluexml.side.Portal.modeler.diagram.commands.update;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Portal.modeler.diagram.dialogs.PortletInternalEditDialog;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
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
