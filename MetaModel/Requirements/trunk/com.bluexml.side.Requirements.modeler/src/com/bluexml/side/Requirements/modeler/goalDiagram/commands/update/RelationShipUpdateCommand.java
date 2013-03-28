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
package com.bluexml.side.Requirements.modeler.goalDiagram.commands.update;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Requirements.modeler.dialogs.BasicElementDialog;
import com.bluexml.side.Requirements.modeler.dialogs.RelationShipDialog;
import com.bluexml.side.requirements.RelationShip;

public class RelationShipUpdateCommand extends Command {

	private RelationShip element;
	private Map<String, Object> data;

	public RelationShipUpdateCommand(RelationShip _element, Map<String, Object> _data) {
		element = _element;
		data = _data;
	}
	
	@Override
	public void execute() {
		redo();
	}
	
	@Override
	public void redo() {
		element.setName((String) data.get(BasicElementDialog.BASICELEMENT_NAME));
		element.setDocumentation((String) data.get(BasicElementDialog.BASICELEMENT_DOCUMENTATION));
		element.setSourceMin(((Boolean) data.get(RelationShipDialog.RELATIONSHIP_SOURCE_MIN)) ? 0 : 1);
		element.setSourceMax(((Boolean) data.get(RelationShipDialog.RELATIONSHIP_SOURCE_MAX)) ? -1 : 1);
		element.setTargetMin(((Boolean) data.get(RelationShipDialog.RELATIONSHIP_TARGET_MIN)) ? 0 : 1);
		element.setTargetMax(((Boolean) data.get(RelationShipDialog.RELATIONSHIP_TARGET_MAX)) ? -1 : 1);
	}
}
