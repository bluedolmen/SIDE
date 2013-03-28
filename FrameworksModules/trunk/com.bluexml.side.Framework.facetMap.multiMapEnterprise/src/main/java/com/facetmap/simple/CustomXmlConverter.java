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
package com.facetmap.simple;

import com.facetmap.DataException;
import com.facetmap.InternalException;
import com.facetmap.Map;

public class CustomXmlConverter extends XmlConverter {

	@Override
	public Map createMap(String s) throws DataException, InternalException {
		SimpleFacetMapX simplefacetmap = new SimpleFacetMapX();
		simplefacetmap.setTitle(s);
		SimpleResourceSpace simpleresourcespace = new SimpleResourceSpace(simplefacetmap);
		simplefacetmap.setResourceSpace(simpleresourcespace);
		rParser = new XmlSimpleResourceParser(simpleresourcespace, simplefacetmap);
		return simplefacetmap;
	}

	public CustomXmlConverter() {
		super();
	}

	
}
