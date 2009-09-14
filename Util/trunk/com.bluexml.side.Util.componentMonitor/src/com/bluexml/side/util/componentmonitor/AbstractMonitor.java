package com.bluexml.side.util.componentmonitor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

public abstract class AbstractMonitor implements IProgressMonitor {
	int someOfincrementedStep = 0;
	int currentOpenTask = 0;
	boolean nbTaskInitialised = false;
	protected AbstractMonitor parent;
	protected StyledText styletext;
	protected ProgressBar progressBar;
	protected Label progressBarlabel;
	protected String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$

	public AbstractMonitor(StyledText styletext, ProgressBar progressBar, Label progressBarlabel, AbstractMonitor parent) {
		this.parent = parent;
		this.styletext = styletext;
		this.progressBar = progressBar;
		this.progressBarlabel = progressBarlabel;
		progressBar.setSelection(0);
	}

	public AbstractMonitor(StyledText styletext, ProgressBar progressBar, int totalWork, Label progressBarlabel, AbstractMonitor parent) {
		this.parent = parent;
		this.styletext = styletext;
		this.progressBar = progressBar;
		this.progressBarlabel = progressBarlabel;
		progressBar.setSelection(0);
		progressBar.setMaximum(totalWork);
	}

	public void addText(String text) {
		addText(lineSeparator + text, SWT.COLOR_BLACK);
	}

	public void addErrorText(String text) {
		addText(lineSeparator + text, SWT.COLOR_RED);
	}

	public void addWarningText(String text) {
		addText(lineSeparator + text, SWT.COLOR_DARK_YELLOW);
	}

	private void addText(String text, int color) {
		StyleRange style2 = new StyleRange();
		style2.start = styletext.getText().length();
		style2.length = text.length();
		style2.foreground = Display.getDefault().getSystemColor(color);
		styletext.append(text);
		styletext.setStyleRange(style2);
		styletext.setTopIndex(styletext.getLineCount());
	}

	protected void addOneStep() {
		someOfincrementedStep++;
		progressBar.setSelection(progressBar.getSelection() + 1);
		if (parent != null) {
			parent.addOneStep();
		}
		System.out.println("New Monitor state :\n" + this);

	}

	public abstract void taskDone(String text);

	public abstract void beginTask(String name);

	public String toString() {
		String st = "======================================" + "\n";
		// st += "parent monitor :" + this.parent.getClass() + "\n";
		st += "monitor current step :" + this.progressBar.getSelection() + "\n";
		st += "monitor total step :" + this.progressBar.getMaximum() + "\n";
		st += "monitor label :" + this.progressBarlabel.getText() + "\n";
		st += "monitor textField length " + this.styletext.getText().length() + "\n";
		st += "currentOpenTask :" + currentOpenTask + "\n";
		st += this.getClass().getName() + " :nb steps :" + someOfincrementedStep + "/" + progressBar.getMaximum() + "\n";
		st += "======================================" + "\n";
		return st;
	}

	public void setMaxTaskNb(int nb) {
		progressBar.setMaximum(nb);
	}

	public void skipTasks(int nb) {
		System.out.println("skip :" + nb);
		for (int c = 0; c < nb; c++) {
			//System.out.println("skip current skip :" + c);
			addOneStep();
		}
	}
}
