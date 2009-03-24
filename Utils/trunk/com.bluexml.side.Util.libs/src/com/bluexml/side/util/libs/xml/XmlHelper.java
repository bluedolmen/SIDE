package com.bluexml.side.util.libs.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class XmlHelper {

	public static Document buildJdomDocument(String serializedDoc) throws JDOMException, IOException {
		Document doc;
		org.jdom.input.SAXBuilder builder = new SAXBuilder();
		StringReader sr = new StringReader(serializedDoc);
		doc = builder.build(sr);
		return doc;
	}
	
	
	public static Document buildJdomDocument(File xmlFile) throws Exception {		
		Document doc;
		org.jdom.input.SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
		return doc;		
	}
	
	public static void writeXmlFile(File f,Document doc) throws Exception {
		XMLOutputter outputer = new XMLOutputter();
		FileWriter fwriter = new FileWriter(f);
		outputer.output(doc, fwriter);
	}
	
}
