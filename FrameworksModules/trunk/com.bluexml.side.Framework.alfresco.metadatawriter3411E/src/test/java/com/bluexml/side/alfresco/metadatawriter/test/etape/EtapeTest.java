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
package com.bluexml.side.alfresco.metadatawriter.test.etape;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.alfresco.model.ContentModel;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;


import com.bluexml.side.Framework.alfresco.metadatawriter.MetadataWriterAbstract;
import com.bluexml.side.Framework.alfresco.metadatawriter.MetadataWriterResolverImpl;
import com.bluexml.side.alfresco.metadatawriter.test.MetadataWriter;

public class EtapeTest extends TestCase {
	private static Logger logger = Logger.getLogger(EtapeTest.class);
	MetadataWriterResolverImpl resolver = new MetadataWriterResolverImpl();
	Map<String, Map<QName, Serializable>> properties = new HashMap<String, Map<QName, Serializable>>();
	Map<QName, Serializable> monEtape = new HashMap<QName, Serializable>();
	MetadataWriter meta = new MetadataWriter();
	
/*	@Before
	protected void setUp() {
		URL url = this.getClass().getResource("../default.metadatawriter-mapping.properties");
		resolver.setResourcePattern(url.toString());
		meta.setResolver(resolver);
		try {
			meta.init("");
		} catch (Exception e) {
			logger.error("init failed, test stop");
			return;
		}
	
		
		monEtape.put(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve(meta.getPROCESSUS() + ".mapping.att1.qname")), "att1");
		monEtape.put(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve(meta.getPROCESSUS() + ".mapping.att2.qname")), new Date(0));
		monEtape.put(ContentModel.PROP_NAME, "att2");
		
		properties.put((String) resolver.resolve(meta.getPROCESSUS() + ".mapping.etape1.name"), monEtape);
	}*/
	
	@Test
	public void testGetEtapeAttributs() {
	/*	String type = "VisaRedaction";
		Map<String, Map<QName, Serializable>> etapesMap = new HashMap<String, Map<QName, Serializable>>();
		etapesMap.put(type, null);
		
		etapesMap.put(type, meta.getEtapeAttributs(monEtape, etapesMap, type));
		assertTrue(etapesMap.get(type).equals(monEtape));
		Assert.isInstanceOf(Date.class, etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve(meta.getPROCESSUS() + ".mapping.att2.qname"))));
		
		etapesMap.put(type, meta.getEtapeAttributs(monEtape, etapesMap, type));
		String att1 = (String) etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve(meta.getPROCESSUS() + ".mapping.att1.qname")));
		String date = (String) etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve(meta.getPROCESSUS() + ".mapping.att2.qname")));
		assertTrue(att1.equals("att1, att1"));
		assertTrue(date.equals("01/01/1970, 01/01/1970"));
		
		etapesMap.put(type, meta.getEtapeAttributs(monEtape, etapesMap, type));
		att1 = (String) etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve(meta.getPROCESSUS() + ".mapping.att1.qname")));
		date = (String) etapesMap.get(type).get(QName.createQName(meta.getNAMESPACEURI(), (String) resolver.resolve(meta.getPROCESSUS() + ".mapping.att2.qname")));
		assertTrue(att1.equals("att1, att1, att1"));
		assertTrue(date.equals("01/01/1970, 01/01/1970, 01/01/1970"));*/
	}
	
	/*@Test
	public void testConcatEtapeAttributs() {
		monEtape = meta.concatEtapeAttributs(properties);
		
		Assert.isNull(monEtape.get(ContentModel.PROP_NAME));
		String att1 = (String) monEtape.get(QName.createQName(meta.getNAMESPACEURI(), ((String) resolver.resolve(meta.getPROCESSUS() + ".mapping.etape1.att1.value")).split(":")[1]));
		assertTrue(att1.equals("att1"));
		String date = (String) monEtape.get(QName.createQName(meta.getNAMESPACEURI(), ((String) resolver.resolve(meta.getPROCESSUS() + ".mapping.etape1.att2.value")).split(":")[1]));
		assertTrue(date.equals("01/01/1970"));
	}*/
}
