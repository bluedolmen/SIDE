package com.bluexml.side.util.componentmonitor.headLessinterface;

import org.eclipse.swt.custom.StyleRange;

import com.bluexml.side.util.componentmonitor.guiAdapter.AdaptedRunable.WidgetNotAvailable;

public interface StyledTextInterface {

	public String getText() throws WidgetNotAvailable;

	public void append(String text);

	public void setStyleRange(StyleRange style2);

	public int getLineCount() throws WidgetNotAvailable;

	public void setTopIndex(int lineCount);
	

}
