package com.bluexml.side.util.componentmonitor;

public class MonitorWriter extends java.io.StringWriter {
	AbstractMonitor monitor;
	String prefix = "";
	String suffix = "";

	public MonitorWriter(AbstractMonitor monitor, String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
		this.monitor = monitor;
	}

	public void write(String s) {
		monitor.addText(prefix + s + suffix);
	}

}