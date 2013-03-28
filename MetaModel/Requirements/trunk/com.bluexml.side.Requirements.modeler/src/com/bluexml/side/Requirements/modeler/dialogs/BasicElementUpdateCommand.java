/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
