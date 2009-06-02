package com.bluexml.side.application.documentation.structure;

import java.net.URI;
import java.util.Date;

public class LogEntry {
	private Date date;
	private String title;
	private String description;
	private URI uri;
	private LogEntryType logEntryType;
	
	public LogEntry(String title, LogEntryType logEntryType) {
		this.title = title;
		this.logEntryType = logEntryType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public URI getUri() {
		return uri;
	}
	public void setUri(URI uri) {
		this.uri = uri;
	}
	public LogEntryType getLogEntryType() {
		return logEntryType;
	}
	public void setLogEntryType(LogEntryType logEntryType) {
		this.logEntryType = logEntryType;
	}
	
	
	
}
