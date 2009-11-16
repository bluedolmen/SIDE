package com.bluexml.side.util.componentmonitor.guiAdapter;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;
import com.bluexml.side.util.componentmonitor.headLessinterface.ProgressBarInterface;
import com.bluexml.side.util.libs.ui.UIUtils;

public class ProgressBarAdapter implements ProgressBarInterface {
	private ProgressBar progressBar;

	public ProgressBarAdapter(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public int getMaximum() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(progressBar) {
			@Override
			public void secureRun() {
				result = progressBar.getMaximum();
			}
		};
		currentDisp.syncExec(ad);
		return (Integer) ad.getResult();
	}

	public int getSelection() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(progressBar) {
			@Override
			public void secureRun() {
				result = progressBar.getSelection();
			}
		};
		currentDisp.syncExec(ad);
		return (Integer) ad.getResult();
	}

	public void setMaximum(final int value) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(progressBar) {
			@Override
			public void secureRun() {
				progressBar.setMaximum(value);
			}
		});
	}

	public Point computeSize(final int wHint, final int hHint, final boolean changed) throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(progressBar) {
			@Override
			public void secureRun() {
				result = progressBar.computeSize(wHint, hHint, changed);
			}
		};
		currentDisp.syncExec(ad);
		return (Point) ad.getResult();
	}

	public int getMinimum() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(progressBar) {
			@Override
			public void secureRun() {
				result = progressBar.getMinimum();
			}
		};
		currentDisp.syncExec(ad);
		return (Integer) ad.getResult();
	}

	public int getState() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(progressBar) {
			@Override
			public void secureRun() {
				result = progressBar.getState();
			}
		};
		currentDisp.syncExec(ad);
		return (Integer) ad.getResult();
	}

	public void setMinimum(final int value) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(progressBar) {
			@Override
			public void secureRun() {
				progressBar.setMinimum(value);
			}
		});
	}

	public void setSelection(final int selection) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(progressBar) {
			@Override
			public void secureRun() {
				progressBar.setSelection(selection);
			}
		});
	}

	public void setState(final int state) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(progressBar) {
			@Override
			public void secureRun() {
				progressBar.setState(state);
			}
		});
	}

}
