package com.bluexml.side.util.componentmonitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;
import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.documentation.LogHelper;
import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;
import com.bluexml.side.util.libs.ui.UIUtils;

public abstract class AbstractMonitor implements IProgressMonitor {
	boolean canceled = false;
	int someOfincrementedStep = 0;
	int currentOpenTask = 0;
	boolean nbTaskInitialised = false;
	String currentTask = "";
	protected AbstractMonitor parent;
	protected StyledTextInterface styletext;
	protected ProgressBarInterface progressBar;
	protected LabelInterface progressBarlabel;
	protected String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$
	protected DateFormat timestampFormat = new SimpleDateFormat("HH:mm:ss");
	protected LogHelper consoleLog;

	protected List<MonitorListener> listeners = new ArrayList<MonitorListener>();

	public void addMonitorListener(MonitorListener listener) {
		listeners.add(listener);
	}

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
		if (parent != null) {
			parent.setTaskName(format);
		}

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

	private void addText(final String text, final LogEntryType type) {
		for (MonitorListener monitorListener : listeners) {
			monitorListener.addText(text, type);
		}
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new Runnable() {
			public void run() {
				String newText = "";
				int color = getColor(type);
				if (timestampFormat != null) {
					String date = timestampFormat.format(new Date());
					newText = date + " :" + text;
				}
				newText = lineSeparator + text;
				StyleRange style2 = new StyleRange();
				try {
					style2.start = styletext.getText().length();
					style2.length = newText.length();
					style2.foreground = Display.getDefault().getSystemColor(color);
					styletext.append(newText);
					styletext.setStyleRange(style2);
					styletext.setTopIndex(styletext.getLineCount());
				} catch (WidgetNotAvailable e) {
					// nothing to do
				}
				logConsole(newText, type);
			}
		});
	}

	protected void addOneStep() {
		skipTasks(1);
		// System.out.println("New Monitor state :\n" + this);

	}

	public abstract void taskDone(String text);

	public abstract void beginTask(String name);

	// public String toString() {
	// String st = "======================================" + "\n";
	// // st += "parent monitor :" + this.parent.getClass() + "\n";
	// st += "monitor current step :" + this.progressBar.getSelection() + "\n";
	// st += "monitor total step :" + this.progressBar.getMaximum() + "\n";
	// st += "monitor label :" + this.progressBarlabel.getText() + "\n";
	// st += "monitor textField length " + this.styletext.getText().length() +
	// "\n";
	// st += "currentOpenTask :" + currentOpenTask + "\n";
	// st += this.getClass().getName() + " :nb steps :" + someOfincrementedStep
	// + "/" + progressBar.getMaximum() + "\n";
	// st += "======================================" + "\n";
	// return st;
	// }

	/**
	 * @return the currentTask
	 */
	public String getCurrentTask() {
		return currentTask;
	}

	public void setMaxTaskNb(int nb) {
		progressBar.setMaximum(nb);
	}

	public void skipAllTasks(boolean includeParent) {
		try {
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
		} catch (WidgetNotAvailable e) {
			// nothing to do caused by job in background (dialog disposed)
		}
	}

	public void skipTasks(int nb) {
		try {
			someOfincrementedStep += nb;
			progressBar.setSelection(progressBar.getSelection() + nb);
			if (parent != null) {
				parent.skipTasks(nb);
			}
		} catch (WidgetNotAvailable e) {
			// nothing to do caused by job in background (dialog disposed)
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

	public boolean isCanceled() {
		if (parent != null) {
			canceled |= parent.isCanceled();
		}
		return canceled;

	}

	public void setCanceled(boolean value) {
		canceled = value;
		if (parent != null) {
			parent.setCanceled(value);
		}

	}

	/**
	 * @return the parent
	 */
	public AbstractMonitor getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(AbstractMonitor parent) {
		this.parent = parent;
	}
}
