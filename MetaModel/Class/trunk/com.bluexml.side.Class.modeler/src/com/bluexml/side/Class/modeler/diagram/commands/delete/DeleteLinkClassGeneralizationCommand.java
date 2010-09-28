package com.bluexml.side.Class.modeler.diagram.commands.delete;

import org.eclipse.gef.commands.Command;
import org.topcased.modeler.commands.DeleteGraphElementCommand;
import org.topcased.modeler.di.model.GraphEdge;

import com.bluexml.side.clazz.Clazz;

public class DeleteLinkClassGeneralizationCommand extends Command {
	GraphEdge eo;
	Clazz clazz1;
	Clazz clazz2;

	DeleteGraphElementCommand cmd;

	public DeleteLinkClassGeneralizationCommand(GraphEdge eo, Clazz clazz1, Clazz clazz2) {
		this.eo = eo;
		this.clazz1 = clazz1;
		this.clazz2 = clazz2;
	}

	@Override
	public void execute() {
		
		clazz1.getGeneralizations().remove(clazz2);
		
		//Create delete command and execute it
		cmd = new DeleteGraphElementCommand(eo, true);
		if (cmd.canExecute()) {
			cmd.execute();
		}
	}

	@Override
	public boolean canUndo() {
		return false;
	}
}
