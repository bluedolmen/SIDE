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
package com.bluexml.side.form.clazz;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormUI;

public class MoveSiblingDown extends MoveSiblingUp {

	@Override
	public int getNewIndex(FormElement fc, EList<?> list) {
		int newIndex;
		int indexOf = list.indexOf(fc);
		int lastIndex = list.size() -1;
		newIndex = indexOf < lastIndex ? indexOf + 1 : lastIndex;
		return newIndex;
	}

	@Override
	public String getText() {
		return FormUI.Messages.getString("Actions_moveSiblingDown");
	}

}
