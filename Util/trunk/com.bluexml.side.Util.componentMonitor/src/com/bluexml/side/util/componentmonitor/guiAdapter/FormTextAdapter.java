package com.bluexml.side.util.componentmonitor.guiAdapter;

import com.bluexml.side.util.componentmonitor.headLessinterface.FormTextInterface;

import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.widgets.FormText;
public class FormTextAdapter implements FormTextInterface {

	private FormText formText;
	
	public FormTextAdapter(FormText formText) {
		this.formText = formText;
	}

	public void addHyperlinkListener(HyperlinkAdapter hyperlinkAdapter) {
		formText.addHyperlinkListener(hyperlinkAdapter);
		
	}

	public void setVisible(boolean visible) {
		formText.setVisible(visible);
		
	}
	
}
