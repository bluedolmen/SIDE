package com.bluexml.side.Class.modeler.diagram.commands.delete;

import org.eclipse.gef.commands.Command;
import org.topcased.modeler.commands.DeleteGraphElementCommand;
import org.topcased.modeler.di.model.GraphEdge;

import com.bluexml.side.clazz.Enumeration;

public class DeleteLinkEnumerationDependsCommand extends Command {

	Enumeration enumeration;
	GraphEdge eo;
	DeleteGraphElementCommand cmd;

	public DeleteLinkEnumerationDependsCommand(Enumeration enumeration, GraphEdge eo) {
		this.enumeration = enumeration;
		this.eo = eo;
	}

	@Override
	public void execute() {

		//Remove view from class
		enumeration.setDepends(null);

		//Create delete command and execute it
		cmd = new DeleteGraphElementCommand(eo, true);
		if (cmd.canExecute()) {
			cmd.execute();
		}
	}

	/**
	 * if you need to manage undo return true and implements undo() method
	 * 
	 * @Override
	 */
	public boolean canUndo() {
		return false;
	}
}
