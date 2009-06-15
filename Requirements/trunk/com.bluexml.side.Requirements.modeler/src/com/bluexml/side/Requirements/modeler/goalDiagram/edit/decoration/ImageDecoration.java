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
