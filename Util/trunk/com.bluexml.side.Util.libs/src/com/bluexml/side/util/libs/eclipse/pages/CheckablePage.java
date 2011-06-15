package com.bluexml.side.util.libs.eclipse.pages;

import org.eclipse.swt.widgets.Composite;

public interface CheckablePage {

	public void checkPageComplite();
	
	abstract void createFieldsControls(Composite composite);

}
