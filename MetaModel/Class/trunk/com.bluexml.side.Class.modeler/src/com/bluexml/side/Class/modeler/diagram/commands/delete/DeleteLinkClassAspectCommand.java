package com.bluexml.side.Class.modeler.diagram.commands.delete;

import org.eclipse.gef.commands.Command;
import org.topcased.modeler.commands.DeleteGraphElementCommand;
import org.topcased.modeler.di.model.GraphEdge;

import com.bluexml.side.Class.modeler.diagram.edit.hasAspectEditPart;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Clazz;

public class DeleteLinkClassAspectCommand extends Command {
	Clazz clazz;
	Aspect aspect;
	GraphEdge eo;
	DeleteGraphElementCommand cmd;
	
	public DeleteLinkClassAspectCommand(hasAspectEditPart editPart,Clazz clazz, Aspect aspect, GraphEdge eo) {
		this.aspect = aspect;
		this.clazz = clazz;
		this.eo = eo;
	}
	
	@Override
	public void execute() {		
		//Remove aspect from class
		clazz.getAspects().remove(aspect);
		
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
