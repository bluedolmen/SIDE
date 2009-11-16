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
