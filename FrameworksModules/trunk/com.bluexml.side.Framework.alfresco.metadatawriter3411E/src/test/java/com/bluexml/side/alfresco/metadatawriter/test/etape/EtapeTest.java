package com.bluexml.side.alfresco.metadatawriter.test.etape;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.alfresco.model.ContentModel;
import org.alfresco.service.namespace.QName;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;


import com.bluexml.side.Framework.alfresco.metadatawriter.MetadataWriterResolverImpl;
import com.bluexml.side.alfresco.metadatawriter.test.MetadataWriter;

public class EtapeTest extends TestCase {
	MetadataWriterResolverImpl resolver = new MetadataWriterResolverImpl();
	Map<String, Map<QName, Serializable>> properties = new HashMap<String, Map<QName, Serializable>>();
	Map<QName, Serializable> monEtape = new HashMap<QName, Serializable>();
	MetadataWriter meta = new MetadataWriter();
	
	@Before
	protected void setUp() {
		URL url = this.getClass().getResource("../mapping.properties");
		resolver.setResourcePattern(url.toString());
		meta.setResolver(resolver);
		meta.init();
		
		monEtape.put(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve("mapping.att1.qname")), "att1");
		monEtape.put(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve("mapping.att2.qname")), new Date(0));
		monEtape.put(ContentModel.PROP_NAME, "att2");
		
		properties.put((String) resolver.resolve("mapping.etape1.name"), monEtape);
	}
	
	@Test
	public void testGetEtapeAttributs() {
		String type = "VisaRedaction";
		Map<String, Map<QName, Serializable>> etapesMap = new HashMap<String, Map<QName, Serializable>>();
		etapesMap.put(type, null);
		
		etapesMap.put(type, meta.getEtapeAttributs(monEtape, etapesMap, type));
		assertTrue(etapesMap.get(type).equals(monEtape));
		Assert.isInstanceOf(Date.class, etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve("mapping.att2.qname"))));
		
		etapesMap.put(type, meta.getEtapeAttributs(monEtape, etapesMap, type));
		String att1 = (String) etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve("mapping.att1.qname")));
		String date = (String) etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve("mapping.att2.qname")));
		assertTrue(att1.equals("att1, att1"));
		assertTrue(date.equals("01/01/1970, 01/01/1970"));
		
		etapesMap.put(type, meta.getEtapeAttributs(monEtape, etapesMap, type));
		att1 = (String) etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve("mapping.att1.qname")));
		date = (String) etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve("mapping.att2.qname")));
		assertTrue(att1.equals("att1, att1, att1"));
		assertTrue(date.equals("01/01/1970, 01/01/1970, 01/01/1970"));
	}
	
	@Test
	public void testConcatEtapeAttributs() {
		monEtape = meta.concatEtapeAttributs(properties);
		
		Assert.isNull(monEtape.get(ContentModel.PROP_NAME));
		String att1 = (String) monEtape.get(QName.createQName(meta.getNAMESPACEURI(), ((String) resolver.resolve("mapping.etape1.att1.value")).split(":")[1]));
		assertTrue(att1.equals("att1"));
		String date = (String) monEtape.get(QName.createQName(meta.getNAMESPACEURI(), ((String) resolver.resolve("mapping.etape1.att2.value")).split(":")[1]));
		assertTrue(date.equals("01/01/1970"));
	}
}