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
package com.bluexml.side.Requirements.modeler.goalDiagram.edit.decoration;

import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

import com.bluexml.side.Requirements.modeler.goalDiagram.ReqImageRegistry;

public class ImageDecoration extends ImageFigure implements RotatableDecoration {

	private Point locationPoint;

	public ImageDecoration(String key) {
		setImage(ReqImageRegistry.getImage(key));
	}

	public ImageDecoration(Image image) {
		setImage(image);
	}

	public void setReferencePoint(Point ref) {
	}

	public void setLocation(Point p) {
		locationPoint = p;
		Image img = getImage();
		locationPoint.x -= img.getBounds().width/2;
		locationPoint.y -= img.getBounds().height/2;
		
		Rectangle r = new Rectangle(getBounds());
		r.setLocation(locationPoint);
		setBounds(r);
	}
	
}
