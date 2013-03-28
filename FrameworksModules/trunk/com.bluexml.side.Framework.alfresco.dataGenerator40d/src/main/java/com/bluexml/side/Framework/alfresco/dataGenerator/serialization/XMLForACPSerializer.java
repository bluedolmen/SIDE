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
 * This class allows the serialization of the builded xml containing 
 * metadata for .acp
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping.XMLForACPMappingBuilder;

/**
 * @author dchevrier
 *
 */
public class XMLForACPSerializer implements ISerialization{

	private XMLForACPMappingBuilder builder;
	private String fileName;

	public XMLForACPMappingBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(XMLForACPMappingBuilder builder) {
		this.builder = builder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * write the Document object (dom4j) in file of given name
	 * @return true if the process is successful
	 * @throws Exception
	 */
	public boolean serializeXml() throws Exception {
		FileOutputStream fos = new FileOutputStream(fileName);
		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
	    builder.build().write(osw); 
	    osw.close();
	    fos.close();
	    return true;
	}	
}
