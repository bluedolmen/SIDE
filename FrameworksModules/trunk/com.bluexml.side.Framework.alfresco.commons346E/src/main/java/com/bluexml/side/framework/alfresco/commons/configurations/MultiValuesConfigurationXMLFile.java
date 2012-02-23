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
