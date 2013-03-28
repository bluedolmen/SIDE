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
