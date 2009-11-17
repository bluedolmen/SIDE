package com.bluexml.side.util.componentmonitor;

import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;

public interface MonitorListener {

	void addText(String txt,LogEntryType type);
	
	void isCanceled();
	
	void skipTasks(int skippedTask);
	
}
