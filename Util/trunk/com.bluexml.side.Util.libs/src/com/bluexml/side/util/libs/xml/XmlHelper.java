package com.bluexml.side.util.libs.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;

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

	public static void writeXmlFile(File f, Document doc) throws Exception {
		XMLOutputter outputer = new XMLOutputter();
		FileWriter fwriter = new FileWriter(f);
		outputer.output(doc, fwriter);
	}

	/**
	 * Use this method to include a Document into another one
	 * 
	 * @param base
	 *            the document where include
	 * @param toInclude
	 *            the document to include
	 * @param keepRoot
	 *            true if the root
	 * @return
	 */
	public static Document includeDocument(Document base, Document toInclude, boolean keepRoot) {
		if (keepRoot) {
			base.getRootElement().addContent(toInclude.getRootElement().detach());
		} else {
			List<Content> ch = toInclude.getRootElement().getChildren();
			// use ultimate tips to avoid
			// java.util.ConcurrentModificationException
			Iterator<Content> it = ch.iterator();
			while (it.hasNext()) {
				Content current = it.next();
				it.remove();
				base.getRootElement().addContent(current);
			}
		}
		return base;
	}
	
	/**
	 * @param src
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static org.w3c.dom.Document buildW3cDocument(String src) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory fabriqueD = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructeur;
		constructeur = fabriqueD.newDocumentBuilder();
		File fileXml = new File(src);
		org.w3c.dom.Document document = constructeur.parse(fileXml);
		return document;
	}
}
