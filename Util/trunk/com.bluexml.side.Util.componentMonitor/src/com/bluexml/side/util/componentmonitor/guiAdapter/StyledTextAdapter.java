package com.bluexml.side.util.componentmonitor.guiAdapter;

import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;
import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;
import com.bluexml.side.util.libs.ui.UIUtils;

public class StyledTextAdapter implements StyledTextInterface {
	private StyledText styledtext;

	public StyledTextAdapter(StyledText styledtext) {
		this.styledtext = styledtext;
	}

	public void append(final String text) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				styledtext.append(text);
			}
		});
	}

	public int getLineCount() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				result = styledtext.getLineCount();
			}
		};
		currentDisp.syncExec(ad);
		return (Integer) ad.getResult();
	}

	public String getText() throws WidgetNotAvailable {
		Display currentDisp = UIUtils.getDisplay();
		AdaptedRunable ad = new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				result = styledtext.getText();
			}
		};
		currentDisp.syncExec(ad);
		return (String) ad.getResult();
	}

	public void setStyleRange(final StyleRange style2) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				styledtext.setStyleRange(style2);
			}
		});
	}

	public void setTopIndex(final int topIndex) {
		Display currentDisp = UIUtils.getDisplay();
		currentDisp.syncExec(new AdaptedRunable(styledtext) {
			@Override
			public void secureRun() {
				styledtext.setTopIndex(topIndex);
			}
		});
	}

}
