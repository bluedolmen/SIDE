/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram.commands.update;

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Class.modeler.diagram.dialogs.CommentEditDialog;
import com.bluexml.side.common.Comment;

/**
 * Class that create a command in order to update operation parameters <br>
 * creation : 12 mai 2005
 */
public class CommentUpdateCommand extends Command {

	private Comment comment;

	/** Map containing new association data */
	private Map newData;
	
	/**
	 * Create a command for updating parameters on a given comment
	 * 
	 * @param comment
	 *            the comment to update
	 * @param data
	 *            Map containing comment data
	 */
	public CommentUpdateCommand(Comment comment, Map data) {
		this.comment = comment;
		this.newData = data;
	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}

	/**
	 * Set the new values
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		// Update the class properties
		comment.setValue((String) newData.get(CommentEditDialog.COMMENT_BODY));
	}
}
