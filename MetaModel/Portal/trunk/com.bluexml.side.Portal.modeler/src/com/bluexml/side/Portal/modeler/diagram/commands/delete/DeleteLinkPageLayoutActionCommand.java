package com.bluexml.side.Portal.modeler.diagram.commands.delete;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.topcased.modeler.commands.DeleteGraphElementCommand;
import org.topcased.modeler.di.model.GraphEdge;

import com.bluexml.side.Portal.modeler.diagram.commands.update.PageUpdateCommand;
import com.bluexml.side.Portal.modeler.diagram.dialogs.PageEditDialog;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.Page;

public class DeleteLinkPageLayoutActionCommand extends Command {
	Page page;
	Map<String, Object> newData;

	PageUpdateCommand puc;
	DeleteGraphElementCommand cmd;
	GraphEdge eo;

	public DeleteLinkPageLayoutActionCommand(Page page, GraphEdge eo) {
		this.page = page;
		this.newData = new HashMap<String, Object>();
		newData.put(PageEditDialog.PAGE_UseLayout, null);
		this.eo = eo;
	}

	@Override
	public void execute() {
		// update page
		puc = new PageUpdateCommand(page, newData);
		if (puc.canExecute()) {
			puc.execute();
		}

		// update havePortlets
		for (HavePortlet hp : page.getPortlets()) {
			if (hp.getPositionGroup() != null) {
				hp.getPositionGroup().removeAll(hp.getPositionGroup());
			}
		}

		// update graphical link
		//Create delete command and execute it
		cmd = new DeleteGraphElementCommand(eo, true);
		if (cmd.canExecute()) {
			cmd.execute();
		}
	}

	@Override
	public boolean canUndo() {
		// user must redraw the link
		return false;
	}

}
