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
package com.bluexml.side.util.componentmonitor.headLessinterface;

import org.eclipse.swt.custom.StyleRange;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;

public interface StyledTextInterface {

	public String getText() throws WidgetNotAvailable;

	public void append(String text);

	public void setStyleRange(StyleRange style2);

	public int getLineCount() throws WidgetNotAvailable;

	public void setTopIndex(int lineCount);
	

}
