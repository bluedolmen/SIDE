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
