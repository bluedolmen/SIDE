package com.bluexml.side.util.componentmonitor.headLessinterface;

import org.eclipse.swt.custom.StyleRange;

public interface StyledTextInterface {

	public String getText();

	public void append(String text);

	public void setStyleRange(StyleRange style2);

	public int getLineCount();

	public void setTopIndex(int lineCount);
	

}
