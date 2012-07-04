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
