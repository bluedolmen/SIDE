package com.bluexml.side.util.componentmonitor;

import java.util.ArrayList;
import java.util.List;

public class MonitorWriter extends java.io.StringWriter {
	ComponentMonitor monitor;
	String prefix = "";
	String suffix = "";
	List<String> list = new ArrayList<String>();

	public MonitorWriter(ComponentMonitor monitor, String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
		this.monitor = monitor;
	}

	@Override
	public void write(String s) {
		monitor.addTextAndLog(prefix + s + suffix, null);
		list.add(s);
	}

	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}
	
	

}