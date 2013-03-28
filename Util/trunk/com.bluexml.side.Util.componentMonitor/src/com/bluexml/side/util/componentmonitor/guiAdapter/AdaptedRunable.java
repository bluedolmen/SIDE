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

import org.eclipse.swt.widgets.Widget;

public abstract class AdaptedRunable implements Runnable {
	Object result = null;
	Widget swtObject = null;
	Boolean available = null;

	public AdaptedRunable(Widget swtObject) {
		this.swtObject = swtObject;
	}

	public Object getResult() throws WidgetNotAvailable {
		if (!available) {
			throw new WidgetNotAvailable();
		}
		return result;
	}

	final public void run() {
		if ((swtObject != null && !swtObject.isDisposed())) {
			available=true;
			secureRun();
		} else {
			available=false;
		}
	}

	abstract public void secureRun();

	public class WidgetNotAvailable extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2557281795818530770L;
		
	}
}
