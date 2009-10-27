package com.bluexml.side.util.componentmonitor.headless;

import org.eclipse.swt.custom.StyleRange;

import com.bluexml.side.util.componentmonitor.headLessinterface.StyledTextInterface;

public class StyledTextHeadless implements StyledTextInterface {
	static String linesep = "\n";
	String text;
	int lignCount;
	int currentTopIndex;

	public void append(String text) {
		this.text += linesep + text;
	}

	public int getLineCount() {
		int count = 0;
		int index = 0;
		while ((index = text.indexOf(linesep, index)) != -1) {
			++index;
			++count;
		}
		return count;
	}

	public String getText() {
		return text;
	}

	public void setStyleRange(StyleRange style2) {
		// nothing to do
	}

	public void setTopIndex(int lineCount) {
		// nothing to do
	}

}
