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
package com.bluexml.side.util.libs.view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class OutlineHTMLView extends ViewPart {
	private Browser browser;

	/**
	 * The constructor.
	 */
	public OutlineHTMLView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		browser = new Browser(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		browser.setText("<html>" +
				"<head>" +
				"<style type=\"text/css\">" +
				"body {" +
				"font-family:\"Lucida Grande\",\"Trebuchet MS\",Verdana,Helvetica,sans-serif;" +
				"font-size:90%;" +
				"font-size-adjust:none;" +
				"font-style:normal;" +
				"font-variant:normal;" +
				"font-weight:normal;" +
				"line-height:normal;" +
				"}" +
				"</style>" +
				"</head>" +
				"<body>Outline view" +
				"<br>" +
				"<br>" +
				"<a href=\"http://www.bluexml.com\">BlueXML.com</a>" +
				"</body></html>");
	}


	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		browser.setFocus();
	}

	public void setContent(String content) {
		browser.setText(content);
	}
}
