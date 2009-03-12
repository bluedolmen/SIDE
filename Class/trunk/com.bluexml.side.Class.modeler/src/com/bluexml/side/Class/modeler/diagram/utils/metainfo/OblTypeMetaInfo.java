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

import com.bluexml.side.clazz.MetaInfo;
import com.bluexml.side.clazz.MetaInfoGroup;
import com.bluexml.side.clazz.impl.ClazzFactoryImpl;



public abstract class OblTypeMetaInfo {

	protected static Collection<Object> allMetaInfos;
	
	public Collection<Object> getAllMetaInfo() {
		initAllMetaInfo();
		Collection<Object> result = new ArrayList<Object>(allMetaInfos);
		return result;
	}

	public MetaInfo getMetaInfo(String key) {
		initAllMetaInfo();

		Collection<Object> result = new ArrayList<Object>();

		for (Object o : allMetaInfos) {
			if (o instanceof MetaInfo) {
				MetaInfo c = (MetaInfo) o;
				if (c.getKey().equals(key)) {
					result.add(c);
				}
			} else if (o instanceof MetaInfoGroup) {
				MetaInfoGroup cg = (MetaInfoGroup) o;
				for (Object obj : cg.getContraints()) {
					if (obj instanceof MetaInfo) {
						MetaInfo c = (MetaInfo) obj;
						if (c.getKey().equals(key)) {
							result.add(c);
						}
					}
				}
			}
		}

		if (result.size() > 0) {
			MetaInfo mi = ClazzFactoryImpl.init().createMetaInfo();
			mi.clone((MetaInfo) result.toArray()[0]);
			return mi;
		} else
			return null;
	}
	
	public abstract void initAllMetaInfo();
	
}
