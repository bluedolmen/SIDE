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
