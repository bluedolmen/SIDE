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
package com.bluexml.side.alfresco.metadatawriter.test.attribut;

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

public class AttributTest extends TestCase {
	MetadataWriterResolverImpl resolver = new MetadataWriterResolverImpl();
	Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
	MetadataWriter meta = new MetadataWriter();
	
/*	@Before
	protected void setUp() {
		URL url = this.getClass().getResource("../default.metadatawriter-mapping.properties");
		resolver.setResourcePattern(url.toString());
		meta.setResolver(resolver);
		
		properties.put(ContentModel.PROP_NAME, "name");
		properties.put(ContentModel.PROP_MODIFIED, new Date(0));
	}*/
	
	@Test
	public void testInit() throws Exception {
		/*meta.init("");
		
		Assert.hasLength(meta.getFOLDER_DOCUMENT_VISA());
		Assert.hasLength(meta.getNAMESPACEURI());
		Assert.notNull(meta.getASSOC_FILE_MODELE_CONTENT());
		Assert.notNull(meta.getPROP_QNAME_VISA_TYPEVISA());
		Assert.notNull(meta.getTYPE_FILE());
		Assert.notNull(meta.getTYPE_VISA());*/
	}
	
	/*public void testFormatDate() {
		properties = meta.formatDate(properties);
		
		Assert.isInstanceOf(String.class, properties.get(ContentModel.PROP_MODIFIED));
		assertTrue(properties.get(ContentModel.PROP_MODIFIED).equals("01/01/1970"));
		Assert.isInstanceOf(String.class, properties.get(ContentModel.PROP_NAME));
		assertTrue(properties.get(ContentModel.PROP_NAME).equals("name"));
	}*/
}
