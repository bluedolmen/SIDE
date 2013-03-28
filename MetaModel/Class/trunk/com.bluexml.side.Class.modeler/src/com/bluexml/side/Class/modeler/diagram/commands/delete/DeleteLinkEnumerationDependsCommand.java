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
