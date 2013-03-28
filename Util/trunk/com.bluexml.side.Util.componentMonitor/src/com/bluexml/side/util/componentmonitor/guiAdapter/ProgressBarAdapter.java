/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
