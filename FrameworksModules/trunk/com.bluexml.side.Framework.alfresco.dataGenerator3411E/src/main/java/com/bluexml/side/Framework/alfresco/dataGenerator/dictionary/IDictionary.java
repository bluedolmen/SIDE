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
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.dictionary;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;


/**
 * @author davidchevrier
 *
 */
public interface IDictionary {
	
	/**
	 * allows access to structure of the loaded model (types, associations, properties, aspects, ...)
	 * @param qnameModel
	 * @return the filled structure of the loaded model
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public IStructure getStructure(String model) throws ParserConfigurationException, SAXException, IOException;

}
