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
package com.bluexml.side.framework.facetmap.multimap;

import java.io.File;
import java.net.URL;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import com.facetmap.Selection;
import com.facetmap.simple.XmlGenerator;

public class Helper {
	
	

	

	
	public File getFileFromClassPath(String path) throws Exception {
		URL url = this.getClass().getResource(path);
		File file = new File(url.toURI().getPath());
		return file;
	}
	

	
	public String serializeFMap(com.facetmap.Map selection) throws Exception {
		XmlGenerator xml = new XmlGenerator();     
        return serializeDom(xml.documentOf(selection));
	}
	
	public String serializeFMap(Selection selection) throws Exception {
		XmlGenerator xml = new XmlGenerator();     
        return serializeDom(xml.documentOf(selection));
	}
	
	public String serializeDom(Node doc) throws Exception {

		DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();

		DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");

		LSSerializer writer = impl.createLSSerializer();

		String str = writer.writeToString(doc);
		return str;
	}
	
	public boolean checkUserSession() {
		boolean auth =true;
		
		
		return auth;
	}
}
