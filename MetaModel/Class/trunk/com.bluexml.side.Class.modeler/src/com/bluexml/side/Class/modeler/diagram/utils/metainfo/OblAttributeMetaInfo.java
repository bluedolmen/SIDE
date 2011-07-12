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
import java.util.Collection;

import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.MetaInfoGroup;
import com.bluexml.side.common.impl.CommonFactoryImpl;



public class OblAttributeMetaInfo extends OblTypeMetaInfo {

	public Collection<Object> getMetaInfo(DataType typ) {
		initAllMetaInfo();

		Collection<Object> result = new ArrayList<Object>();

		for (Object o : allMetaInfos) {
			if (o instanceof MetaInfo) {
				MetaInfo c = (MetaInfo) o;
				if (c.getConstraintType().equals(DataType.OBJECT)) {
					result.add(c);
				} else if (c.getConstraintType().equals(typ)) {
					result.add(c);
				}
			} else if (o instanceof MetaInfoGroup) {
				MetaInfoGroup cg = (MetaInfoGroup) o;
				boolean find = false;
				for (Object obj : cg.getChildren()) {
					if (obj instanceof MetaInfo) {
						MetaInfo c = (MetaInfo) obj;
						if (c.getConstraintType().equals(
								DataType.OBJECT)) {
							find = true;
							break;
						} else if (c.getConstraintType().equals(typ)) {
							find = true;
							break;
						}
					}
				}
				if (find) {
					result.add(cg);
				}
			}
		}

		return result;
	}

	@Override
	public void initAllMetaInfo() {
		allMetaInfos = new ArrayList<Object>();

		CommonFactory fact = CommonFactoryImpl.init();
		MetaInfo c;
		MetaInfoGroup cg;

		/**
		 * String
		 */

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.STRING);
		c.setKey("email");
		c.setValueType(boolean.class);
		allMetaInfos.add(c);

//		cg = fact.createMetaInfoGroup();
//		cg.setName("htmlarea");
//		allMetaInfos.add(cg);
//
//		c = fact.createMetaInfo();
//		c.setConstraintType(DataType.STRING);
//		c.setKey("rte-rows");
//		c.setValueType(int.class);
//		cg.getChildren().add(c);
//
//		c = fact.createMetaInfo();
//		c.setConstraintType(DataType.STRING);
//		c.setKey("rte-cols");
//		c.setValueType(int.class);
//		cg.getChildren().add(c);
//
//		c = fact.createMetaInfo();
//		c.setConstraintType(DataType.STRING);
//		c.setKey("rte-width");
//		c.setValueType(int.class);
//		cg.getChildren().add(c);
//
//		c = fact.createMetaInfo();
//		c.setConstraintType(DataType.STRING);
//		c.setKey("rte-height");
//		c.setValueType(int.class);
//		cg.getChildren().add(c);
//
//		cg = fact.createMetaInfoGroup();
//		cg.setName("textarea");
		//allMetaInfos.add(cg);
//
//		c = fact.createMetaInfo();
//		c.setConstraintType(DataType.STRING);
//		c.setKey("textarea-rows");
//		c.setValueType(int.class);
//		cg.getChildren().add(c);
//
//		c = fact.createMetaInfo();
//		c.setConstraintType(DataType.STRING);
//		c.setKey("textarea-cols");
//		c.setValueType(int.class);
//		cg.getChildren().add(c);

		cg = fact.createMetaInfoGroup();
		cg.setName("length");
		allMetaInfos.add(cg);

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.STRING);
		c.setKey("min-length");
		c.setValueType(int.class);
		cg.getChildren().add(c);

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.STRING);
		c.setKey("max-length");
		c.setValueType(int.class);
		cg.getChildren().add(c);

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.STRING);
		c.setKey("regular-expression");
		c.setValueType(String.class);
		allMetaInfos.add(c);
		
		c = fact.createMetaInfo();
		c.setConstraintType(DataType.STRING);
		c.setKey("regular-expression.match");
		c.setDefaultValueBoolean(true);
		c.setValueType(boolean.class);
		allMetaInfos.add(c);
		
		
		// field to describe the regular-expression
		c = fact.createMetaInfo();
		c.setConstraintType(DataType.STRING);
		c.setKey("regular-expression-desc");
		c.setValueType(String.class);
		allMetaInfos.add(c);

		/**
		 * Integer
		 */

		cg = fact.createMetaInfoGroup();
		cg.setName("bounds");
		allMetaInfos.add(cg);

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.INT);
		c.setKey("minBound");
		c.setValueType(int.class);
		cg.getChildren().add(c);

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.INT);
		c.setKey("maxBound");
		c.setValueType(String.class);
		cg.getChildren().add(c);

		/**
		 * Common
		 */

//		c = fact.createMetaInfo();
//		c.setConstraintType(DataType.OBJECT);
//		c.setKey("size");
//		c.setValueType(int.class);
//		allMetaInfos.add(c);

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.OBJECT);
		c.setKey("required");
		c.setValueType(boolean.class);
		allMetaInfos.add(c);
		
		c = fact.createMetaInfo();
		c.setConstraintType(DataType.OBJECT);
		c.setKey("mandatory.enforced");
		c.setValueType(boolean.class);
		allMetaInfos.add(c);

		
		
		c = fact.createMetaInfo();
		c.setConstraintType(DataType.OBJECT);
		c.setKey("hidden");
		c.setValueType(boolean.class);
		allMetaInfos.add(c);

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.OBJECT);
		c.setKey("read-only");
		c.setValueType(boolean.class);
		allMetaInfos.add(c);

//		c = fact.createMetaInfo();
//		c.setConstraintType(DataType.OBJECT);
//		c.setKey("treeNodeName");
//		c.setValueType(boolean.class);
//		allMetaInfos.add(c);
//
//		c = fact.createMetaInfo();
//		c.setConstraintType(DataType.OBJECT);
//		c.setKey("tree");
//		c.setValueType(booleanWithParameter.class);
//		allMetaInfos.add(c);

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.OBJECT);
		c.setKey("propertySearched");
		c.setValueType(boolean.class);
		//c.setValue("true");
		c.setDefaultValueBoolean(true);
		allMetaInfos.add(c);

		c = fact.createMetaInfo();
		c.setConstraintType(DataType.OBJECT);
		c.setKey("index.stored");
		c.setValueType(boolean.class);
		//c.setValue("true");
		c.setDefaultValueBoolean(false);
		allMetaInfos.add(c);

		
		c = fact.createMetaInfo();
		c.setConstraintType(DataType.OBJECT);
		c.setKey("index.atomic");
		c.setValueType(boolean.class);
		//c.setValue("true");
		c.setDefaultValueBoolean(true);
		allMetaInfos.add(c);


		c = fact.createMetaInfo();
		c.setConstraintType(DataType.OBJECT);
		c.setKey("index.tokenised");
		c.setValueType(String.class);
		c.setValue("true");		
		allMetaInfos.add(c);
		
		c = fact.createMetaInfo();
		c.setConstraintType(DataType.OBJECT);
		c.setKey("multiple");
		c.setValueType(boolean.class);
		allMetaInfos.add(c);

	}

}
