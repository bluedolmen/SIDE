package com.bluexml.side.util.libs.eclipse;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractDialogCellEditor extends Dialog {

	protected AbstractDialogCellEditor(Shell parentShell) {
		super(parentShell);
	}

	public AbstractDialogCellEditor(IShellProvider parentShell) {
		super(parentShell);
	}
	
	public abstract String getResourcePath();

	public abstract void addResourceSelectionListener(ResourceSelectionListener listener);
	
	public abstract void init(String locationLabel, String initialValue, String resource_type);
}
