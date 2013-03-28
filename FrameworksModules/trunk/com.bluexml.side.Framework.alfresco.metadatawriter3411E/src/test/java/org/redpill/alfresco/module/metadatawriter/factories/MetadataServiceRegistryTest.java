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
package org.redpill.alfresco.module.metadatawriter.factories;

import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertNotNull;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redpill.alfresco.module.metadatawriter.factories.UnknownServiceNameException;
import org.redpill.alfresco.module.metadatawriter.factories.UnsupportedMimetypeException;
import org.redpill.alfresco.module.metadatawriter.factories.impl.MetadataServiceRegistryImpl;
import org.redpill.alfresco.module.metadatawriter.services.MetadataService;


public class MetadataServiceRegistryTest {
	

	private MetadataServiceRegistryImpl f;
	
	private final Mockery mockery = new Mockery();

	//---------------------------------------------------
	//Setup
	//---------------------------------------------------
	@Before
	public void setUp() {
		f = new MetadataServiceRegistryImpl();
		
	}
	
	
	@After
	public void assertIsSatisfied() {
		mockery.assertIsSatisfied();
	}
	
	//---------------------------------------------------
	//Test
	//---------------------------------------------------
	@Test
	public void createServiceAvailable() throws UnsupportedMimetypeException, UnknownServiceNameException {
		
		final String serviceName = "expected service";
		final MetadataService expectedService = createAndAddServiceFor(serviceName);
		
		createAndAddServiceFor("other service");
		
		final MetadataService createdService = f.findService(serviceName);
		
		assertNotNull(createdService);
		assertSame(expectedService, createdService);
	}


	@Test(expected=UnknownServiceNameException.class)
	public void createNoSupportedServiceAvailable() throws UnknownServiceNameException {
		final String serviceName = "any service";
		
		createAndAddServiceFor("supperted service 1");
		createAndAddServiceFor("supperted service 2");
		
		f.findService(serviceName);
	}

	//---------------------------------------------------
	//Helpers
	//---------------------------------------------------
	
	private MetadataService createAndAddServiceFor(final String serviceName) {
		
		final MetadataService service = mockery.mock(MetadataService.class, serviceName);
		f.register(service);
		
		mockery.checking(new Expectations() {{
			allowing(service).getServiceName();
			will(returnValue(serviceName));
		}});
		
		return service;
	}
	



}
