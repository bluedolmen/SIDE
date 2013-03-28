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
package com.bluexml.side.framework.alfresco.unicity;
 
import java.util.List;

import org.alfresco.service.namespace.QName;

public abstract class  AbstractUnicityChecker implements UnicityChecker {
	
	
	
	UnicityXMLReader unicityDescriptorReader = null;
	
	/**
	 * @return the unicityDescriptorReader
	 */
	public UnicityXMLReader getUnicityDescriptorReader() {
		return unicityDescriptorReader;
	}

	/**
	 * @param unicityDescriptorReader the unicityDescriptorReader to set
	 */
	public void setUnicityDescriptorReader(UnicityXMLReader unicityDescriptorReader) {
		this.unicityDescriptorReader = unicityDescriptorReader;
	}
	
	public List<QName> getUnicityKeysFor(QName type) throws Exception {
		return getUnicityDescriptorReader().getUnicityDictionary().get(type);
	}
	
	
	
}
