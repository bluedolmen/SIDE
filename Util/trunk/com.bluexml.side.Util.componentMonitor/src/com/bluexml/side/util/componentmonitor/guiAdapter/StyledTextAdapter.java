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

import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.libs.ui.UIUtils;

public class StyledTextAdapter implements StyledTextInterface {
	private StyledText styledtext;

	public StyledTextAdapter(StyledText styledtext) {
		this.styledtext = styledtext;
	}

	public void append(final String text) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				styledtext.append(text);
			}
		});
	}

	public int getLineCount() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				result = styledtext.getLineCount();
			}
		};
		currentDisp.syncExec(ad);
		return (Integer) ad.getResult();
	}

	public String getText() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				result = styledtext.getText();
			}
		};
		currentDisp.syncExec(ad);
		return (String) ad.getResult();
	}

	public void setStyleRange(final StyleRange style2) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				styledtext.setStyleRange(style2);
			}
		});
	}

	public void setTopIndex(final int topIndex) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				styledtext.setTopIndex(topIndex);
			}
		});
	}

}
