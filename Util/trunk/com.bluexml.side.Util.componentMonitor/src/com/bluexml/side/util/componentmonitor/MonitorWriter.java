package com.bluexml.side.util.componentmonitor;

public class MonitorWriter extends java.io.StringWriter {
	ComponentMonitor monitor;
	String prefix = "";
	String suffix = "";

	public MonitorWriter(ComponentMonitor monitor, String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
		this.monitor = monitor;
	}

	public void write(String s) {
		monitor.addTextAndLog(prefix + s + suffix, null);
	}

}