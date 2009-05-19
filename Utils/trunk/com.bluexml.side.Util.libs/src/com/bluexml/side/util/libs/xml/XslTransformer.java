/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2009
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.util.libs.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

/**
 * 
 * @author pbertrand
 *
 */
public class XslTransformer {

	private XslTransformer(){};
	
	/**
	 * Fonction qui transforme un fichier src 
	 * @param src Source file (path)
	 * @param xsl Stylesheet (path)
	 * @param result Result file (path)
	 * @return A reference on the result file
	 */
    public static Result transform(String src, String xsl,String result) {
    	try {
    	 // Création de la source DOM
        DocumentBuilderFactory fabriqueD = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructeur;
		
			constructeur = fabriqueD.newDocumentBuilder();
        File fileXml = new File(src);
        Document document = constructeur.parse(fileXml);
        Source source = new DOMSource(document);
        
        // Création du fichier de sortie
        File fileHtml = new File(result);
        Result resultat = new StreamResult(fileHtml);
        
        // Configuration du transformer
        TransformerFactory fabriqueT = TransformerFactory.newInstance();
        StreamSource stylesource = new StreamSource(xsl);
        Transformer transformer = fabriqueT.newTransformer(stylesource);
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        
        // Transformation
        transformer.transform(source, resultat);
        return resultat;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }

}
