package com.bluexml.side.Requirements.modeler.goalDiagram.commands.update;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Requirements.modeler.goalDiagram.dialogs.AgentDialog;
import com.bluexml.side.requirements.Agent;

public class AgentUpdateCommand extends Command {

	private Agent a;
	private Map<String, Object> data;

	public AgentUpdateCommand(Agent _a, Map<String, Object> _data) {
		a = _a;
		data = _data;
	}
	
	@Override
	public void execute() {
		redo();
	}
	
	@Override
	public void redo() {
		a.setDocumentation(data.get(AgentDialog.AGENT_DOCUMENTATION).toString());
	}

}
