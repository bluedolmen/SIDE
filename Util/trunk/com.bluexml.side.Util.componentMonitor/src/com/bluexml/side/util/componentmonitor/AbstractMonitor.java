package com.bluexml.side.util.componentmonitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.documentation.LogHelper;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;

public abstract class AbstractMonitor implements IProgressMonitor {
	int someOfincrementedStep = 0;
	int currentOpenTask = 0;
	boolean nbTaskInitialised = false;
	protected AbstractMonitor parent;
	protected StyledTextInterface styletext;
	protected ProgressBarInterface progressBar;
	protected LabelInterface progressBarlabel;
	protected String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$
	protected DateFormat timestampFormat = new SimpleDateFormat("HH:mm:ss");
	protected LogHelper consoleLog;

	public LogHelper getConsoleLog() {
		return consoleLog;
	}

	public void setConsoleLog(LogHelper consoleLog) {
		this.consoleLog = consoleLog;
	}

	

	public AbstractMonitor(StyledTextInterface styletext, ProgressBarInterface progressBar, LabelInterface progressBarlabel, AbstractMonitor parent) {
		this.parent = parent;
		this.styletext = styletext;
		this.progressBar = progressBar;
		this.progressBarlabel = progressBarlabel;
	}

	public void setTimeStemp(String format) {
		timestampFormat = new SimpleDateFormat(format);
	}

	public void addText(String text) {
		addText(text, LogEntryType.CONSOLE);
	}

	public void addErrorText(String text) {
		addText(text, LogEntryType.ERROR);
	}

	public void addWarningText(String text) {
		addText(text, LogEntryType.WARNING);
	}

	private void addText(String text, LogEntryType type) {
		int color = getColor(type);
		if (timestampFormat != null) {
			String date = timestampFormat.format(new Date());
			text = date + " :" + text;
		}
		text = lineSeparator + text;
		StyleRange style2 = new StyleRange();
		if (styletext.getText() != null) {
		   style2.start = styletext.getText().length();
		} else {
		   style2.start = 0;
		}
		style2.length = text.length();
		style2.foreground = Display.getDefault().getSystemColor(color);

		styletext.append(text);
		styletext.setStyleRange(style2);
		styletext.setTopIndex(styletext.getLineCount());
		logConsole(text, type);
	}

	protected void addOneStep() {
		skipTasks(1);
		// System.out.println("New Monitor state :\n" + this);

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

	public void skipAllTasks(boolean includeParent) {
		if (includeParent) {
			// force skipping for parent so fix at 100%
			progressBar.setSelection(progressBar.getMaximum());
			if (parent != null) {
				parent.skipAllTasks(includeParent);
			}
		} else {
			int toskip = progressBar.getMaximum() - progressBar.getSelection();
			skipTasks(toskip);
		}
	}

	public void skipTasks(int nb) {
		someOfincrementedStep += nb;
		progressBar.setSelection(progressBar.getSelection() + nb);
		if (parent != null) {
			parent.skipTasks(nb);
		}
	}

	public int getColor(LogEntryType type) {
		if (type.equals(LogEntryType.WARNING)) {
			return SWT.COLOR_DARK_YELLOW;
		} else if (type.equals(LogEntryType.ERROR)) {
			return SWT.COLOR_RED;
		}
		return SWT.COLOR_BLACK;

	}

	protected void logConsole(String txt, LogEntryType type) {
		if (type.equals(LogEntryType.ERROR)) {
			consoleLog.addErrorLog(txt, "", "");
		} else if (type.equals(LogEntryType.WARNING)) {
			consoleLog.addWarningLog(txt, "", "");
		} else {
			consoleLog.addInfoLog(txt, "", "");
		}

	}
}
