/*******************************************************************************
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
package com.bluexml.side.Class.modeler.diagram.utils.metainfo.value;

import java.util.ArrayList;
import java.util.List;

public enum PackageActionGroup_Enum {

	read_package_item_actions, edit_package_item_actions, remove_package_item_actions,edit_and_remove_package_item_actions,add_package_item_actions;
	
	public static List<String> toCollection() {
		PackageActionGroup_Enum[] tab = PackageActionGroup_Enum.values();
		List<String> result = new ArrayList<String>();
		for (PackageActionGroup_Enum item : tab) {
			result.add(item.toString());
		}
		return result;
	}
}
