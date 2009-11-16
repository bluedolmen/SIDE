package com.bluexml.side.util.componentmonitor.headLessinterface;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;

public interface LabelInterface {
	public Point computeSize(int wHint, int hHint, boolean changed) throws WidgetNotAvailable;

	public int getAlignment() throws WidgetNotAvailable;

	public Image getImage() throws WidgetNotAvailable;

	public String getText() throws WidgetNotAvailable;

	public void setAlignment(int value);

	public void setImage(Image img);

	public void setText(String text);
}
