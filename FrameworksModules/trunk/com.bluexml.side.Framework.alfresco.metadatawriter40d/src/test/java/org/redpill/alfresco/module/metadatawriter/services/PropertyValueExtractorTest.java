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
package org.redpill.alfresco.module.metadatawriter.services;

import org.junit.Test;
import org.junit.Assert;
import org.redpill.alfresco.module.metadatawriter.services.ContentFacade.ContentException;
import org.redpill.alfresco.module.metadatawriter.services.impl.PropertyValueExtractor;

public class PropertyValueExtractorTest {
	//---------------------------------------------------
	//Public constructor
	//---------------------------------------------------

	//---------------------------------------------------
	//Public methods
	//---------------------------------------------------
	@Test
	public void nullProperty() throws ContentException {
		Assert.assertNull(PropertyValueExtractor.extractValue(null));
	}
	//---------------------------------------------------
	//Private methods
	//---------------------------------------------------

}
