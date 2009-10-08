package com.bluexml.side.util.documentation.structure;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import com.bluexml.side.util.documentation.structure.enumeration.LogEntryType;

public class LogEntry {
	private Date date;
	private String name;
	private List<String> description;
	private URI uri;
	private LogEntryType type;
	
	
	public LogEntry(String name, List<String> description, String uri, LogEntryType logEntryType) {
		this.name = name;
		this.description = description;
		this.date = new Date();
		this.type = logEntryType;
		if (uri != null && uri.length() > 0) {
			try {
				this.uri = new URI(uri);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	public LogEntry(String name, List<String> description, URI uri, LogEntryType logEntryType) {
		this.name = name;
		this.description = description;
		this.date = new Date();
		this.type = logEntryType;
		this.uri = uri;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
		this.description = description;
	}
	public URI getUri() {
		return uri;
	}
	public void setUri(URI uri) {
		this.uri = uri;
	}
	public LogEntryType getType() {
		return type;
	}
	public void setType(LogEntryType logEntryType) {
		this.type = logEntryType;
	}
	
	
	
}
