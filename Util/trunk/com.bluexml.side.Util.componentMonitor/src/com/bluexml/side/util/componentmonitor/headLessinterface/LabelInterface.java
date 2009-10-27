package com.bluexml.side.util.componentmonitor.headLessinterface;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

public interface LabelInterface {
	public Point computeSize(int wHint, int hHint, boolean changed);

	public int getAlignment();

	public Image getImage();

	public String getText();

	public void setAlignment(int value);

	public void setImage(Image img);

	public void setText(String text);
}
