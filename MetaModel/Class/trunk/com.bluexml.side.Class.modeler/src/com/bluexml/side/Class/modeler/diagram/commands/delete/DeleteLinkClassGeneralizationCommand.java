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

import com.bluexml.side.clazz.AbstractClass;

public class DeleteLinkClassGeneralizationCommand extends Command {
	GraphEdge eo;
	AbstractClass clazz1;
	AbstractClass clazz2;

	DeleteGraphElementCommand cmd;

	public DeleteLinkClassGeneralizationCommand(GraphEdge eo, AbstractClass clazz1, AbstractClass clazz2) {
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
