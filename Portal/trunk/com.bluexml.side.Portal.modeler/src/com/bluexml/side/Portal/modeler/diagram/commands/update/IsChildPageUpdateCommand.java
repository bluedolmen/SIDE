package com.bluexml.side.Portal.modeler.diagram.commands.update;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Portal.modeler.diagram.dialogs.IsChildPageEditDialog;
import com.bluexml.side.portal.isChildPage;

public class IsChildPageUpdateCommand extends Command {
	private isChildPage isChildPage;
	
	private Map<String,Object> newData;
	
	public IsChildPageUpdateCommand(isChildPage p_isChildPage, Map<String,Object> p_data) {
		this.isChildPage = p_isChildPage;		
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
		isChildPage.setInherit((Boolean) newData.get(IsChildPageEditDialog.ISCHILDPAGE_Inherit));
	}
}
