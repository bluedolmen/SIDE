package com.bluexml.side.util.componentmonitor.guiAdapter;

import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;

import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;

public class StyledTextAdapter implements StyledTextInterface {
	private StyledText styledtext;

	public StyledTextAdapter(StyledText styledtext) {
		this.styledtext = styledtext;
	}

	public void append(String text) {
		styledtext.append(text);
	}

	public int getLineCount() {
		return styledtext.getLineCount();
	}

	public String getText() {
		return styledtext.getText();
	}

	public void setStyleRange(StyleRange style2) {
		styledtext.setStyleRange(style2);
	}

	public void setTopIndex(int topIndex) {
		styledtext.setTopIndex(topIndex);
	}
}
