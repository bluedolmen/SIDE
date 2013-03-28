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
package com.bluexml.side.util.componentmonitor.headless;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;

public class LabelHeadLess implements LabelInterface {
	Image image;
	String text;
	int alignement;

	public Point computeSize(int wHint, int hHint, boolean changed) {
		return null;
	}

	public int getAlignment() {
		return alignement;
	}

	public Image getImage() {
		return image;
	}

	public String getText() {
		return text;
	}

	public void setAlignment(int value) {
		alignement = value;

	}

	public void setImage(Image img) {
		image = img;

	}

	public void setText(String text) {
		this.text = text;

	}

}
