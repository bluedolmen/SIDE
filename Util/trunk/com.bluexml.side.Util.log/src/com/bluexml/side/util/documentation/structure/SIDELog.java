/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.util.documentation.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bluexml.side.util.documentation.structure.enumeration.LogType;

public class SIDELog {
	protected String name;
	protected String id;
	protected String description;
	protected Date date;
	protected String path;
	protected List<LogEntry> logEntries;
	protected LogType type;
	protected String metaModel;
	protected String techno;
	protected String technoVersion;
	protected String creator;
	protected List<String> models;


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
		models = new ArrayList<String>();
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

	public void addModel(String metaModelName) {
		models.add(metaModelName);
	}

	public List<String> getModels() {
		return models;
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
