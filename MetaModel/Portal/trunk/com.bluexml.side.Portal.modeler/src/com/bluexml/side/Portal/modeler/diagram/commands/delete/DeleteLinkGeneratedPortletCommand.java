package com.bluexml.side.Portal.modeler.diagram.commands.delete;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.topcased.modeler.commands.DeleteGraphElementCommand;
import org.topcased.modeler.di.model.GraphEdge;

import com.bluexml.side.Portal.modeler.diagram.commands.update.PortletUpdateCommand;
import com.bluexml.side.portal.Portlet;

public class DeleteLinkGeneratedPortletCommand extends Command {
	public static final String PORTLET_Name = "PORTLET_Name";
	public static final String PORTLET_IsInstanceOfPortletType = "PORTLET_IsInstanceOfPortletType";
	public static final String PORTLET_IsPortletInternal = "PORTLET_IsPortletInternal";

	Portlet portlet;
	Map<String, Object> newData;
	GraphEdge eo;
	PortletUpdateCommand puc;
	DeleteGraphElementCommand cmd;

	public DeleteLinkGeneratedPortletCommand(Portlet p, GraphEdge eo) {
		this.portlet = p;
		// create properties map for update
		this.newData = new HashMap<String, Object>();
		newData.put(DeleteLinkGeneratedPortletCommand.PORTLET_IsPortletInternal, null);
		newData.put(DeleteLinkGeneratedPortletCommand.PORTLET_IsInstanceOfPortletType, null);
		this.eo = eo;
	}

	@Override
	public void execute() {
		// update portlet
		puc = new PortletUpdateCommand(portlet, newData);
		if (puc.canExecute()) {
			puc.execute();
		}

		// update graphical link
		//Create delete command and execute it
		cmd = new DeleteGraphElementCommand(eo, true);
		if (cmd.canExecute()) {
			cmd.execute();
		}
	}

	@Override
	public void undo() {
		if (puc != null && cmd != null) {
			if (puc.canUndo()) {
				puc.undo();
			}
			if (cmd.canUndo()) {
				cmd.undo();
			}
		}
	}

}
