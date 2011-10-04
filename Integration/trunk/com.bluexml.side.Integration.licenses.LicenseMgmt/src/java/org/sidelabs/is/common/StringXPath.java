package org.sidelabs.is.common;

import org.w3c.dom.*; 
import org.xml.sax.*;
import javax.xml.xpath.*;
import java.util.ArrayList;

public class StringXPath {

	public String expression;
	public InputSource documentXML;
	static NodeList liste;
	public ArrayList<String> license;
	
	public ArrayList<String> recuperationValAttribut(InputSource doc, String expression){
		this.documentXML = doc;		
		this.expression = expression;
		liste = evaluerNode(documentXML, expression);
		int capaciteLicense = liste.getLength();
		this.license = new ArrayList<String>(capaciteLicense);
		if(liste != null){
			for(int i = 0; i<liste.getLength(); i++){
				Node node = liste.item(i);
				this.license.add(i, node.getTextContent());
			}
		}
		return this.license;
	}
	
	public static NodeList evaluerNode(InputSource stream, String expression){
		liste = null;
		try{
			InputSource source = stream;
			XPathFactory fabrique = XPathFactory.newInstance();
			XPath xpath = fabrique.newXPath();
			XPathExpression exp = xpath.compile(expression);
			liste = (NodeList)exp.evaluate(source,XPathConstants.NODESET);
		}catch(XPathExpressionException xpee){
			xpee.printStackTrace();
		}
		return liste;
	}
}
