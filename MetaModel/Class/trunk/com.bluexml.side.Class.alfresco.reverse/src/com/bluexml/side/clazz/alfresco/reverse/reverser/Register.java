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
package com.bluexml.side.clazz.alfresco.reverse.reverser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.common.NameSpace;

public class Register {

	//	private Map<EObject, Object> eObjectSource = new HashMap<EObject, Object>();

	// (prefix,NS)
	private Map<String, NameSpace> reccordedNS = new HashMap<String, NameSpace>();

	// (alf-qname,EObject)
	//	private Map<String, EObject> qnameToEObject = new HashMap<String, EObject>();

	// (EObject.class,(qname,EObject))
	private Map<Class<?>, Map<String, EObject>> x = new HashMap<Class<?>, Map<String, EObject>>();

	public void recordNewEObject(EObject o, String qname) {
		if (qname != null) {
			addItem(o, qname);
		} else {
			System.err.println("try to record Object whithout NS :" + o);
		}
	}

	public EObject getEObject(Class<? extends EObject> T, String qname) {
		if (x.containsKey(T)) {
			return x.get(T).get(qname);
		}
		System.out.println("Register.getEObject() no Object registered for this type" + T);
		return null;
	}

	private void addItem(EObject o, String qName) {
		Class<? extends EObject> class1 = o.getClass();
		Map<String, EObject> map;
		if (x.containsKey(class1)) {
			map = x.get(class1);
		} else {
			map = new HashMap<String, EObject>();
			x.put(class1, map);
		}
		if (map.containsKey(qName)) {
			System.out.println("WARN try to add existing QName" + qName);
			if (!map.get(qName).equals(o)) {
				System.err.println("ERROR override existing Register entry for " + qName);
			}
		}
		map.put(qName, o);
	}

	public void printX() {
		for (Map.Entry<Class<?>, Map<String, EObject>> entx : x.entrySet()) {
			System.out.println("Type :" + entx.getKey());
			Map<String, EObject> map = entx.getValue();
			System.out.println(map);
		}

	}

	public NameSpace getNSForPrefix(String prefix) {
		NameSpace nameSpace = reccordedNS.get(prefix);
		return nameSpace;
	}

	public void addNewNS(NameSpace ns) {
		reccordedNS.put(ns.getPrefix(), ns);
	}

	public Set<Class<?>> getTypes() {
		return x.keySet();
	}
}
