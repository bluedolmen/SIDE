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
