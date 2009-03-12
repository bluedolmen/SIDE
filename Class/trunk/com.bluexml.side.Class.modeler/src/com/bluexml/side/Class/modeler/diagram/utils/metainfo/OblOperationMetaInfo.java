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

import com.bluexml.side.Class.modeler.diagram.utils.metainfo.value.TypeOperation_Enum;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.MetaInfo;
import com.bluexml.side.clazz.impl.ClazzFactoryImpl;



public class OblOperationMetaInfo extends OblTypeMetaInfo {

	public void initAllMetaInfo() {
		allMetaInfos = new ArrayList<Object>();

		ClazzFactory fact = ClazzFactoryImpl.init();
		MetaInfo c;

		c = fact.createMetaInfo();
		c.setKey("type");
		c.setValueType(TypeOperation_Enum.class);
		c.setValueSet(TypeOperation_Enum.toCollection());
		allMetaInfos.add(c);
	}

}
