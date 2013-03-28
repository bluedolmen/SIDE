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
/*******************************************************************************
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram.utils.metainfo;

import java.util.ArrayList;

import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.impl.CommonFactoryImpl;


public class OblAssociationMetaInfo extends OblTypeMetaInfo {

	public void initAllMetaInfo() {
		allMetaInfos = new ArrayList<Object>();
//
		CommonFactory fact = CommonFactoryImpl.init();
		MetaInfo c;

		c = fact.createMetaInfo();
		c.setKey("duplicate");
		c.setValueType(boolean.class);
		c.setDefaultValueBoolean(false);
		allMetaInfos.add(c);
		
		c = fact.createMetaInfo();
		c.setKey("propagateTimestamps");
		c.setDefaultValueBoolean(false);
		c.setValueType(boolean.class);
		allMetaInfos.add(c);
		
		c = fact.createMetaInfo();
		c.setKey("searchable");
		c.setDefaultValueBoolean(false);
		c.setValueType(boolean.class);
		allMetaInfos.add(c);
		
		
	}

}
