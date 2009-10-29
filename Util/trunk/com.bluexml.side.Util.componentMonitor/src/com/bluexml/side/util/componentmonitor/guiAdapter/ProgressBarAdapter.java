package com.bluexml.side.util.componentmonitor.guiAdapter;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.ProgressBar;

import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
public class ProgressBarAdapter implements ProgressBarInterface {
	private ProgressBar progressBar;

	public ProgressBarAdapter(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public int getMaximum() {
		return progressBar.getMaximum();
	}

	public int getSelection() {
		return progressBar.getSelection();
	}

	public void setMaximum(int value) {
		progressBar.setMaximum(value);
	}

	public Point computeSize(int wHint, int hHint, boolean changed) {
		return progressBar.computeSize(wHint, hHint, changed);
	}

	public int getMinimum() {
		return progressBar.getMinimum();
	}

	public int getState() {
		return progressBar.getState();
	}

	public void setMinimum(int value) {
		progressBar.setMinimum(value);
	}

	public void setSelection(int selection) {
		progressBar.setSelection(selection);
	}

	public void setState(int state) {
		progressBar.setState(state);
	}

}
