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
package com.bluexml.side.util.libs.ui;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.osgi.framework.Bundle;

public class UIUtils {

	public static int showAvert(String title, String message) {
		int style = 0;
		style |= SWT.YES | SWT.NO | SWT.CANCEL;
		MessageBox mb = new MessageBox(getDisplay().getActiveShell(), style);
		mb.setText(title);
		mb.setMessage(message);
		int val = mb.open();
		return val;
	}

	public static boolean showConfirmation(String title, String message) {
		
		boolean doWork = true;
		int style = 0;
		style |= SWT.YES | SWT.NO;
		MessageBox mb = new MessageBox(getDisplay().getActiveShell(), style);
		mb.setText(title);
		mb.setMessage(message);
		int val = mb.open();
		switch (val) {
		case SWT.OK:
			doWork = true;
			break;
		case SWT.CANCEL:
			doWork = false;
			break;
		case SWT.YES:
			doWork = true;
			break;
		case SWT.NO:
			doWork = false;
			break;
		}
		return doWork;
	}

	public static void showError(String title, String message) {
		int style = 0;
		style |= SWT.OK;
		MessageBox mb = new MessageBox(getDisplay().getActiveShell(), style);
		mb.setText(title);
		mb.setMessage(message);
		mb.open();
	}

	public static Display getDisplay() {
		Display display = Display.getCurrent();
		// may be null if outside the UI thread
		if (display == null) {
			display = Display.getDefault();
		}
		return display;
	}

	public static ImageDescriptor getImage(String pluginID, String imgPath) {
		Bundle plugin = Platform.getBundle(pluginID);
		URL imgURL = plugin.getResource(imgPath);
		ImageDescriptor imgDesc = ImageDescriptor.createFromURL(imgURL);
		return imgDesc;
	}
}
