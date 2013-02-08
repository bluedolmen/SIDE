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
