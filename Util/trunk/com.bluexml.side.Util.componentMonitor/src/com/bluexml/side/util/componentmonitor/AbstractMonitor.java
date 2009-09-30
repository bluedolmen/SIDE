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
import org.eclipse.swt.widgets.ProgressBar;

import com.bluexml.side.util.documentation.LogHelper;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;

public abstract class AbstractMonitor implements IProgressMonitor {
	protected LogHelper consoleLog;

	public LogHelper getConsoleLog() {
		return consoleLog;
	}

	public void setConsoleLog(LogHelper consoleLog) {
		this.consoleLog = consoleLog;
	}

	int someOfincrementedStep = 0;
	int currentOpenTask = 0;
	boolean nbTaskInitialised = false;
	protected AbstractMonitor parent;
	protected StyledText styletext;
	protected ProgressBar progressBar;
	protected Label progressBarlabel;
	protected String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$
	protected DateFormat timestampFormat = new SimpleDateFormat("HH:mm:ss");

	public AbstractMonitor(StyledText styletext, ProgressBar progressBar, Label progressBarlabel, AbstractMonitor parent) {
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
		style2.start = styletext.getText().length();
		style2.length = text.length();
		style2.foreground = Display.getDefault().getSystemColor(color);

		styletext.append(text);
		styletext.setStyleRange(style2);
		styletext.setTopIndex(styletext.getLineCount());
		logConsole(text, type);
	}

	protected void addOneStep() {
		someOfincrementedStep++;
		progressBar.setSelection(progressBar.getSelection() + 1);
		if (parent != null) {
			parent.addOneStep();
		}
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

	public void skipTasks(int nb) {
		// System.out.println("skip :" + nb);
		for (int c = 0; c < nb; c++) {
			// System.out.println("skip current skip :" + c);
			addOneStep();
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

	public void logConsole(String txt, LogEntryType type) {
		if (type.equals(LogEntryType.ERROR)) {
			consoleLog.addErrorLog(txt, "", "");
		} else if (type.equals(LogEntryType.WARNING)) {
			consoleLog.addWarningLog(txt, "", "");
		} else {
			consoleLog.addInfoLog(txt, "", "");
		}

	}
}
