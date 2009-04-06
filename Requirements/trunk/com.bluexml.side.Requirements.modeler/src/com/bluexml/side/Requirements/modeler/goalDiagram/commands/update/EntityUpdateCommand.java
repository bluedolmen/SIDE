package com.bluexml.side.Requirements.modeler.goalDiagram.commands.update;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Requirements.modeler.goalDiagram.dialogs.EntityDialog;
import com.bluexml.side.requirements.Entity;

public class EntityUpdateCommand extends Command {

	private Entity e;
	private Map<String, Object> data;

	public EntityUpdateCommand(Entity _e, Map<String, Object> _data) {
		e = _e;
		data = _data;
	}
	
	@Override
	public void execute() {
		redo();
	}
	
	@Override
	public void redo() {
		e.setDocumentation(data.get(EntityDialog.ENTITY_DOCUMENTATION).toString());
	}

}
