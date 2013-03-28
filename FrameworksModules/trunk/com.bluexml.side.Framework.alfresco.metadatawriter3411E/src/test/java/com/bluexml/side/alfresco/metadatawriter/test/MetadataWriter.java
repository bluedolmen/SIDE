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
package com.bluexml.side.alfresco.metadatawriter.test;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.service.namespace.QName;

import com.bluexml.side.Framework.alfresco.metadatawriter.MetadataWriterAbstract;

public class MetadataWriter extends MetadataWriterAbstract {

	@Override
	protected Map<QName, Serializable> getSpecialProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<QName, Serializable> getSpecialVisaProperties() {
		// TODO Auto-generated method stub
		return null;
	}

}
