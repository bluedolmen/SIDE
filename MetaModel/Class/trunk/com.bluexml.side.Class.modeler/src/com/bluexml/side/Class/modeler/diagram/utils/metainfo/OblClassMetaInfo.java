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



public class OblClassMetaInfo extends OblTypeMetaInfo {

	public void initAllMetaInfo() {
		allMetaInfos = new ArrayList<Object>();
//
		CommonFactory fact = CommonFactoryImpl.init();
		MetaInfo c;

		c = fact.createMetaInfo();
		c.setKey("archive");
		c.setDefaultValueBoolean(true);
		c.setValueType(boolean.class);
		allMetaInfos.add(c);
		
		c = fact.createMetaInfo();
		c.setKey("includedInSuperTypeQuery");
		c.setDefaultValueBoolean(false);
		c.setValueType(boolean.class);
		allMetaInfos.add(c);

//		c = fact.createMetaInfo();
//		c.setKey("isContainer");
//		c.setValueType(boolean.class);
//		allMetaInfos.add(c);

//		c = fact.createMetaInfo();
//		c.setKey("visual-component");
//		c.setValueType(VisualTypeComponent_Enum.class);
//		c.setValueSet(VisualTypeComponent_Enum.toCollection());
//		allMetaInfos.add(c);
	}

}
