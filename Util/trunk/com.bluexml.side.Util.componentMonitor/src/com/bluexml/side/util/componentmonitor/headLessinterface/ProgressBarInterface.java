package com.bluexml.side.util.componentmonitor.headLessinterface;
import org.eclipse.swt.graphics.Point;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;
public interface ProgressBarInterface {

	public Point computeSize(int wHint, int hHint, boolean changed) throws WidgetNotAvailable;

	public int getMaximum() throws WidgetNotAvailable;

	public int getMinimum() throws WidgetNotAvailable;

	public int getSelection() throws WidgetNotAvailable;

	public int getState() throws WidgetNotAvailable;

	public void setMaximum(int value);

	public void setMinimum(int value);

	public void setSelection(int selection);

	public void setState(int state);
}
