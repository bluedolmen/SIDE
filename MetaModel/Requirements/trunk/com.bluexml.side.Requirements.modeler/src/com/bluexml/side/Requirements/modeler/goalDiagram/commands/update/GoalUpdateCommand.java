package com.bluexml.side.Requirements.modeler.goalDiagram.commands.update;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Requirements.modeler.goalDiagram.dialogs.GoalDialog;
import com.bluexml.side.requirements.Goal;

public class GoalUpdateCommand extends Command {

	private Goal g;
	private Map<String, Object> data;

	public GoalUpdateCommand(Goal _g, Map<String, Object> _data) {
		g = _g;
		data = _data;
	}
	
	@Override
	public void execute() {
		redo();
	}
	
	@Override
	public void redo() {
		g.setDocumentation(data.get(GoalDialog.GOAL_DOCUMENTATION).toString());
	}

}
