package com.bluexml.side.Portal.modeler.diagram.commands.update;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Portal.modeler.diagram.dialogs.PageEditDialog;
import com.bluexml.side.portal.Page;

public class PageUpdateCommand  extends Command {
	
	protected Page page;
	/** Map containing new association data */
	private Map<String,Object> newAssociationData;
	
	
	 
	public PageUpdateCommand(Page p_page, Map<String,Object> p_data) {
		this.page = p_page;		
		this.newAssociationData = p_data;
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
		page.setID((String) newAssociationData.get(PageEditDialog.PAGE_ID));
		
		page.setTitle((String) newAssociationData.get(PageEditDialog.PAGE_TITLE));
		
		page.setDescription((String) newAssociationData.get(PageEditDialog.PAGE_DESCRIPTION));
		
		page.setPosition(Integer.parseInt((String)newAssociationData.get(PageEditDialog.PAGE_POSITION)));
	}
}
