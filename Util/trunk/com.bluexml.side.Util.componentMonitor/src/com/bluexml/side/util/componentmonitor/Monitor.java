package com.bluexml.side.util.componentmonitor;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

public class Monitor extends AbstractMonitor {

	public Monitor(StyledText styletext, ProgressBar progressBar, int nbTask, Label progressBarlabel, AbstractMonitor parent) {
		super(styletext, progressBar, nbTask, progressBarlabel, parent);
	}

	public Monitor(StyledText styletext, ProgressBar progressBar, Label progressBarlabel, AbstractMonitor parent) {
		super(styletext, progressBar, progressBarlabel, parent);
	}

	public void beginTask(String name, int totalWork) {
		beginTask(name);
		progressBar.setMaximum(totalWork);
	}

	public void subTask(String name) {
		if (name != null) {
			addText(name);
			progressBarlabel.setText(name);
		}
		currentOpenTask++;
	}

	public void worked(int work) {

	}

	public void internalWorked(double work) {
		// TODO Auto-generated method stub

	}

	public boolean isCanceled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setCanceled(boolean value) {
		// TODO Auto-generated method stub

	}

	public void setTaskName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beginTask(String name) {
		if (name != null) {
			addText(name);
			progressBarlabel.setText(name);
		}
		currentOpenTask++;

	}

	public void taskDone(String text) {
		done();
		if (text != null) {
			addText(text);
			progressBarlabel.setText(text);
		}

	}

	public void done() {
		currentOpenTask--;
		addOneStep();
	}

}
