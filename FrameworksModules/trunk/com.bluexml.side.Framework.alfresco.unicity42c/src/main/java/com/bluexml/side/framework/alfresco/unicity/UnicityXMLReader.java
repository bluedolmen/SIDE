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
package com.bluexml.side.framework.alfresco.unicity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.Resource;

import com.bluexml.side.framework.alfresco.commons.configurations.AbstractConfigurationFile;

public class UnicityXMLReader extends AbstractConfigurationFile<QName, List<QName>> {
	private static Logger logger = Logger.getLogger(UnicityXMLReader.class);
	public static final String QNAME = "qname";
	public static final String TAG_TYPE = "type";
	public static final String TAG_ATTRIBUTE = "attribute";

	public static QName getQName(String qname) {
		String[] m_ = qname.split(":");
		String namespaceURI = "http://www.bluexml.com/model/content/" + m_[0] + "/1.0";
		String localName = m_[1];
		return QName.createQName(namespaceURI, localName);
	}

	@Override
	protected void loadResource(Resource r) throws Exception {
		logger.debug("Loading resource " + r.getDescription());
		Document unicityDoc = getUnicityDescriptor(r);
		Element root = unicityDoc.getRootElement();
		readType(root);
	}

	private Document getUnicityDescriptor(Resource r) throws Exception {
		Document nodeRefXbel = null;
		SAXBuilder builder = new SAXBuilder();
		InputStream in = r.getInputStream();
		nodeRefXbel = builder.build(in);
		return nodeRefXbel;
	}

	private void readType(Element root) {
		for (Object type : root.getChildren(TAG_TYPE)) {
			String typeqn = ((Element) type).getAttributeValue(QNAME);
			dictionary.put(getQName(typeqn), new ArrayList<QName>());
			readAttributes((Element) type);
		}
	}

	private void readAttributes(Element type) {
		String typeqn = type.getAttributeValue(QNAME);
		for (Object attribute : type.getChildren(TAG_ATTRIBUTE)) {
			String attqn = ((Element) attribute).getAttributeValue(QNAME);
			dictionary.get(getQName(typeqn)).add(getQName(attqn));
		}
	}
}
