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

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.widgets.FormText;

import com.bluexml.side.util.componentmonitor.headLessinterface.FormTextInterface;
import com.bluexml.side.util.libs.ui.UIUtils;

public class FormTextAdapter implements FormTextInterface {

	private FormText formText;

	public FormTextAdapter(FormText formText) {
		this.formText = formText;
	}

	public void addHyperlinkListener(final HyperlinkAdapter hyperlinkAdapter) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(formText) {
			@Override
			public void secureRun() {
				formText.addHyperlinkListener(hyperlinkAdapter);
			}
		});
	}

	public void setVisible(final boolean visible) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(formText) {
			@Override
			public void secureRun() {
				formText.setVisible(visible);
			}
		});
	}

}
