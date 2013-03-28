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
package com.bluexml.side.framework.alfresco.commons.configurations;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.Resource;
/**
 * SIDE Extension
 * 
 * @author davidabad
 */
public abstract class MultiValuesConfigurationXMLFile<K, V> extends AbstractConfigurationFile<K, List<V>> {
	protected String entryTag;
	protected String entryKeyAttribute;
	protected String valueTag;

	public MultiValuesConfigurationXMLFile(String entryTag, String keyAttributeId, String valueTag) throws Exception {
		super();
		this.entryTag = entryTag;
		this.entryKeyAttribute = keyAttributeId;
		this.valueTag = valueTag;

	}

	public enum ContentType {
		ATTRIBUTE, TEXTNODE
	}

	protected abstract V getValueObject(Element value);

	protected abstract K getKeyObject(String keyString);

	@Override
	protected void loadResource(Resource r) throws Exception {
		loadXMLResource(r);
	}

	protected Document getDocument(Resource r) throws Exception {
		InputStream in = r.getInputStream();
		return getDocument(in);
	}

	protected Document getDocument(InputStream in) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document nodeRefXbel = builder.build(in);
		return nodeRefXbel;
	}

	protected void loadXMLResource(Resource r) throws Exception {
		Document document = getDocument(r);
		loadXML(document);
	}

	protected void loadXML(Document document) {
		Element root = document.getRootElement();
		for (Object keyElement : root.getChildren(entryTag)) {
			String key = ((Element) keyElement).getAttributeValue(entryKeyAttribute);
			dictionary.put(getKeyObject(key), new ArrayList<V>());
			readValues((Element) keyElement, key);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Loaded dictionary entries :");
			for (Map.Entry<K, List<V>> entry : dictionary.entrySet()) {
				List<V> value = entry.getValue();
				logger.debug(entry.getKey() + " :");
				for (V v : value) {
					logger.debug("value :" + v);
				}
			}
		}
	}

	protected void readValues(Element type, String key) {
		for (Object attribute : type.getChildren(valueTag)) {
			Element attribute2 = (Element) attribute;

			dictionary.get(getKeyObject(key)).add(getValueObject(attribute2));
		}
	}

}
