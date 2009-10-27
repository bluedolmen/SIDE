package com.bluexml.side.util.componentmonitor.headLessinterface;
import org.eclipse.swt.graphics.Point;
public interface ProgressBarInterface {

	public Point computeSize(int wHint, int hHint, boolean changed);

	public int getMaximum();

	public int getMinimum();

	public int getSelection();

	public int getState();

	public void setMaximum(int value);

	public void setMinimum(int value);

	public void setSelection(int selection);

	public void setState(int state);
}
