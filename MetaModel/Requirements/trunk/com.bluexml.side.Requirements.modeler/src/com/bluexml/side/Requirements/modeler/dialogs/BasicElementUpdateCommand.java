package com.bluexml.side.Requirements.modeler.dialogs;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.requirements.BasicElement;

public class BasicElementUpdateCommand extends Command {

	private BasicElement element;
	private Map<String, Object> data;

	public BasicElementUpdateCommand(BasicElement _element, Map<String, Object> _data) {
		element = _element;
		data = _data;
	}
	
	@Override
	public void execute() {
		redo();
	}
	
	@Override
	public void redo() {
		element.setDocumentation(data.get(BasicElementDialog.BASICELEMENT_DOCUMENTATION).toString());
		element.setName(data.get(BasicElementDialog.BASICELEMENT_NAME).toString());
	}

}
