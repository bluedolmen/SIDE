package com.bluexml.side.framework.facetmap.alfrescoConnector;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.Project;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import com.facetmap.Selection;
import com.facetmap.simple.XmlGenerator;

public class Helper {
	public static String cacheRep = "/multimap/cache";
	public static String alfrescoConnector = "/alfrescoConnector";

	public Properties getProperties(String mapId) {
		InputStream in = this.getClass().getResourceAsStream(alfrescoConnector+"/build."+mapId+".properties");
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public File getBuildFile() throws Exception {
		return getFileFromClassPath(alfrescoConnector+"/build.xml");
	}

	public File getFileFromClassPath(String path) throws Exception {
		URL url = this.getClass().getResource(path);
		File file = new File(url.toURI().getPath());
		return file;
	}

	public void setProperties(Project ant, Properties p) {
		for (Map.Entry<Object, Object> it : p.entrySet()) {
			ant.setProperty(it.getKey().toString(), it.getValue().toString());
		}
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
		boolean auth =false;
		
		
		return auth;
	}
}
