package com.bluexml.side.Portal.modeler.diagram.commands.update;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Portal.modeler.diagram.dialogs.PageEditDialog;
import com.bluexml.side.common.Visibility;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.isChildPage;

public class PageUpdateCommand extends Command {

	protected Page page;
	/** Map containing new association data */
	private Map<String, Object> newAssociationData;
	private Map<String, Object> oldAssociationData = new HashMap<String, Object>();

	public PageUpdateCommand(Page p_page, Map<String, Object> p_data) {
		this.page = p_page;
		this.newAssociationData = p_data;
	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		
		oldAssociationData.put(PageEditDialog.PAGE_DESCRIPTION, page.getDescription());
		oldAssociationData.put(PageEditDialog.PAGE_Generate, page.isGenerate());
		oldAssociationData.put(PageEditDialog.PAGE_ID, page.getID());
		oldAssociationData.put(PageEditDialog.PAGE_IsChildPageOf, page.getIsChildPageOf());
		oldAssociationData.put(PageEditDialog.PAGE_Portlets, page.getPortlets());
		oldAssociationData.put(PageEditDialog.PAGE_POSITION, page.getPosition());
		oldAssociationData.put(PageEditDialog.PAGE_TITLE, page.getTitle());
		oldAssociationData.put(PageEditDialog.PAGE_UseLayout, page.getUseLayout());
		oldAssociationData.put(PageEditDialog.PAGE_Visibility, page.getVisibility());
		
		update(newAssociationData);
	}

	private void update(Map<String, Object> p_data) {
		if (p_data.containsKey(PageEditDialog.PAGE_DESCRIPTION)) {
			page.setDescription((String) p_data.get(PageEditDialog.PAGE_DESCRIPTION));
		}
		if (p_data.containsKey(PageEditDialog.PAGE_ID)) {
			page.setID((String) p_data.get(PageEditDialog.PAGE_ID));
		}
		if (p_data.containsKey(PageEditDialog.PAGE_POSITION)) {
			page.setPosition(Integer.parseInt((String) p_data.get(PageEditDialog.PAGE_POSITION)));
		}
		if (p_data.containsKey(PageEditDialog.PAGE_TITLE)) {
			page.setTitle((String) p_data.get(PageEditDialog.PAGE_TITLE));
		}
		
		
		if (p_data.containsKey(PageEditDialog.PAGE_Generate)) {
			page.setGenerate(Boolean.parseBoolean((String) p_data.get(PageEditDialog.PAGE_Generate)));
		}
		if (p_data.containsKey(PageEditDialog.PAGE_IsChildPageOf)) {
			page.setIsChildPageOf((isChildPage) p_data.get(PageEditDialog.PAGE_IsChildPageOf));
		}
		if (p_data.containsKey(PageEditDialog.PAGE_Portlets)) {
			page.getPortlets().clear();
			page.getPortlets().addAll((Collection<HavePortlet>) p_data.get(PageEditDialog.PAGE_Portlets));
		}
		if (p_data.containsKey(PageEditDialog.PAGE_UseLayout)) {
			page.setUseLayout((PortalLayout) p_data.get(PageEditDialog.PAGE_UseLayout));
		}
		if (p_data.containsKey(PageEditDialog.PAGE_Visibility)) {
			page.setVisibility((Visibility) p_data.get(PageEditDialog.PAGE_Visibility));
		}
		
		
	}

	@Override
	public void undo() {
		update(oldAssociationData);
	}
	
	
}
