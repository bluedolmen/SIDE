package com.bluexml.side.util.libs.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

public class UIUtils {
	
	public static int showAvert(String title, String message) {
		int style = 0;
		style |= SWT.YES | SWT.NO | SWT.CANCEL;
		MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(), style	);
		mb.setText(title);
        mb.setMessage(message);
        int val = mb.open();
        return val;
	}
	
	public static boolean showConfirmation(String title, String message) {
		boolean doWork = true;
		int style = 0;
		style |= SWT.YES | SWT.NO;
		MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(), style	);
		mb.setText(title);
        mb.setMessage(message);
        int val = mb.open();
        switch (val)
        {
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
		MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(), style	);
		mb.setText(title);
        mb.setMessage(message);
        mb.open();
	}
}
