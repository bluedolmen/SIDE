package com.bluexml.side.util.componentmonitor.headless;

import org.eclipse.swt.graphics.Point;

import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
public class progressBarHeadLess implements ProgressBarInterface {

	public int max = 1;
	public int min = 0;
	public int state = 1;
	public int selection = 0;

	public Point computeSize(int wHint, int hHint, boolean changed) {
		return null;
	}

	public int getMaximum() {
		return max;
	}

	public int getMinimum() {
		return min;
	}

	public int getSelection() {
		return selection;
	}

	public int getState() {
		return state;
	}

	public void setMaximum(int value) {
		max = value;
	}

	public void setMinimum(int value) {
		min = value;
	}

	public void setSelection(int selection) {
		this.selection = selection;
	}

	public void setState(int state) {
		this.state = state;
	}

}
