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
