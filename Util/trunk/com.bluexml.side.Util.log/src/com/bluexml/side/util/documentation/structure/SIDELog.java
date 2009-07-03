package com.bluexml.side.util.documentation.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bluexml.side.util.documentation.structure.enumeration.LogType;

public class SIDELog {
	private String name;
	private String id;
	private String description;
	private Date date;
	private String path;
	private List<LogEntry> logEntries;
	private LogType type;
	private String metaModel;
	private String techno;
	private String technoVersion;
	private String creator;
	
	public SIDELog(String creator, String id, String technoVersion, String techno, String metaModel, Date p_date, LogType p_logType) {
		this.name = creator;
		this.id = id;
		this.technoVersion = technoVersion;
		this.creator = creator;
		this.techno = techno;
		this.metaModel = metaModel;
		logEntries = new ArrayList<LogEntry>();
		date = p_date;
		type = p_logType;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LogType getType() {
		return type;
	}

	public void setType(LogType logType) {
		this.type = logType;
	}

	public List<LogEntry> getLogEntries() {
		return logEntries;
	}
	
	public void addLogEntry(LogEntry p_logEntry) {
		getLogEntries().add(p_logEntry);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
