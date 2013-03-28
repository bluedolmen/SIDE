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

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;
import com.bluexml.side.util.componentmonitor.headLessinterface.LabelInterface;
import com.bluexml.side.util.libs.ui.UIUtils;

public class LabelAdapter implements LabelInterface {
	private Label label;

	public LabelAdapter(Label label) {
		this.label = label;
	}

	public Point computeSize(final int wHint, final int hHint, final boolean changed) throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				result = label.computeSize(wHint, hHint, changed);
			}
		};
		currentDisp.syncExec(ad);
		return (Point) ad.getResult();
	}

	public int getAlignment() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				result = label.getAlignment();
			}
		};
		currentDisp.syncExec(ad);
		return (Integer) ad.getResult();
	}

	public Image getImage() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				result = label.getImage();
			}
		};
		currentDisp.syncExec(ad);
		return (Image) ad.getResult();
	}

	public String getText() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				result = label.getText();
			}
		};
		currentDisp.syncExec(ad);
		return (String) ad.getResult();
	}

	public void setAlignment(final int value) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				label.setAlignment(value);
			}
		});
	}

	public void setImage(final Image img) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				label.setImage(img);
			}
		});
	}

	public void setText(final String text) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(label) {
			@Override
			public void secureRun() {
				label.setText(text);
			}
		});
	}

}
