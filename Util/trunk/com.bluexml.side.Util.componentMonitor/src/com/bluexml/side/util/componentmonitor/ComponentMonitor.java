package com.bluexml.side.util.componentmonitor;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

import com.bluexml.side.util.documentation.LogHelper;

public class ComponentMonitor extends AbstractMonitor {
	private LogHelper log;

//	public ComponentMonitor(StyledText styletext, ProgressBar progressBar, int totalWork, Label progressBarlabel, AbstractMonitor parent) {
//		super(styletext, progressBar, totalWork, progressBarlabel, parent);
//		// TODO Auto-generated constructor stub
//	}

	public ComponentMonitor(StyledText styletext, ProgressBar progressBar, int totalWork, Label progressBarlabel, AbstractMonitor parent, LogHelper log) {
		super(styletext, progressBar, totalWork, progressBarlabel, parent);
		setLog(log);

	}

	public void setLog(LogHelper log) {
		this.log = log;
	}

	public LogHelper getLog() {
		return log;
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

	public void beginTask(String name, int totalWork) {
		beginTask(name);
		//progressBar.setMaximum(totalWork);

	}
	@Override
	public void beginTask(String name) {
		if (name != null) {
			addText(name);
			progressBarlabel.setText(name);
		}
		currentOpenTask++;
		
	}

	public void subTask(String name) {
		// acceleo use this for generation part, model access, ...

	}

	public void worked(int work) {
		// TODO Auto-generated method stub

	}

	@Override
	public void taskDone(String text) {
		if (text != null) {
			addText(text);
			progressBarlabel.setText(text);
		}
		currentOpenTask--;
		addOneStep();
		
	}

	public void done() {
		// acceleo use this for generation part, model access, ...
	}

	

}
