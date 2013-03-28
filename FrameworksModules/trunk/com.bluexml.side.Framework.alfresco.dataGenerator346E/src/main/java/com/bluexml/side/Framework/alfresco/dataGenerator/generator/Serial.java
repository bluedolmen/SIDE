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
/**
 * This class allows serialization of data by property and type 
 * to manage unicity of some attributes defined in the model 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.io.Serializable;

public class Serial implements Serializable {

	private static final long serialVersionUID = 4980443534822014010L;
	
	private String type;
	private String property;
	private Object data;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public Serial(String type,String property,Object data){
		this.type = type;
		this.property = property;
		this.data = data;
	}
	
}
