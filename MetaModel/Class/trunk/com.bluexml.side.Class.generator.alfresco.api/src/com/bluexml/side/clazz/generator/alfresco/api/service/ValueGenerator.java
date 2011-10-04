package com.bluexml.side.clazz.generator.alfresco.api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.clazz.service.alfresco.CommonServices;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;

public class ValueGenerator {

	public String getPropertyTestValue(Attribute node, String seedS) throws Exception {
		if (node.getValueList() != null) {
			EnumerationLiteral enumerationLiteral = node.getValueList().getLiterals().get(0);
			if (enumerationLiteral.getValue() == null || enumerationLiteral.getValue().equals("")) {
				return enumerationLiteral.getName();
			}
			return enumerationLiteral.getValue();
		} else {
			int seed;
			if (!seedS.equals("")) {
				seed = Integer.parseInt(seedS);
			} else {
				seed = 0;
			}

			DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();
			Date date = null;
			Calendar c = Calendar.getInstance();
			c.set(1978 + seed, 11, 22, 13, 30, 0);
			date = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String dateString = sdf.format(date);
			dateString = dateTimeFormatter.print(date.getTime());
			Boolean bool = seed % 2 == 0;
			Integer integer = 5 + seed;
			String str = "text" + seedS;
			Double db = 5.65699245 + seed;
			Float fl = 2.345F + seed;
			Long lg = 634554345345L + seed;

			if (node instanceof Attribute) {
				Attribute object = (Attribute) node;
				if (object.getTyp() == DataType.BOOLEAN) {
					return bool.toString();
				} else if (object.getTyp() == DataType.BYTE) {
					return integer.toString();
				} else if (object.getTyp() == DataType.CHAR) {
					return str;
				} else if (object.getTyp() == DataType.DATE) {
					return dateString;
				} else if (object.getTyp() == DataType.DATE_TIME) {
					return dateString;
				} else if (object.getTyp() == DataType.DOUBLE) {
					return db.toString();
				} else if (object.getTyp() == DataType.FLOAT) {
					return fl.toString();
				} else if (object.getTyp() == DataType.INT) {
					return integer.toString();
				} else if (object.getTyp() == DataType.LONG) {
					return lg.toString();
				} else if (object.getTyp() == DataType.OBJECT) {
					// return "d:content";
					return "";
				} else if (object.getTyp() == DataType.SHORT) {
					return integer.toString();
				} else if (object.getTyp() == DataType.STRING) {
					return str;
				} else if (object.getTyp() == DataType.TIME) {
					return dateString;
				}
			}
		}
		throw new Exception("node must be an attribute");
	}

	public String getPropertyTestAssertion(Attribute node, String val1, String val2) throws Exception {
		String assertion = "";
		assertion += "assertEquals(";

		Attribute object = (Attribute) node;
		if (object.getTyp() == DataType.BOOLEAN) {
			String term1 = "Boolean.parseBoolean(" + val1 + ")";
			String term2 = "Boolean.parseBoolean(" + val2 + ")";
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.BYTE) {
			String term1 = "Byte.parseByte(" + val1 + ")";
			String term2 = "Byte.parseByte(" + val2 + ")";
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.CHAR) {
			String term1 = val1;
			String term2 = val2;
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.DATE) {
			String term1 = val1;
			String term2 = val2;
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.DATE_TIME) {
			String term1 = val1;
			String term2 = val2;
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.DOUBLE) {
			String term1 = "Double.parseDouble(" + val1 + ")";
			String term2 = "Double.parseDouble(" + val2 + ")";
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.FLOAT) {
			String term1 = "Float.parseFloat(" + val1 + ")";
			String term2 = "Float.parseFloat(" + val2 + ")";
			String term3 = "0.000001F";
			assertion += term1 + ", " + term2 + ", " + term3;
		} else if (object.getTyp() == DataType.INT) {
			String term1 = "Integer.parseInt(" + val1 + ")";
			String term2 = "Integer.parseInt(" + val2 + ")";
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.LONG) {
			String term1 = "Long.parseLong(" + val1 + ")";
			String term2 = "Long.parseLong(" + val2 + ")";
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.OBJECT) {
			String term1 = val1;
			String term2 = val2;
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.SHORT) {
			String term1 = "Short.parseShort(" + val1 + ")";
			String term2 = "Short.parseShort(" + val2 + ")";
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.STRING) {
			String term1 = val1;
			String term2 = val2;
			assertion += term1 + ", " + term2;
		} else if (object.getTyp() == DataType.TIME) {
			String term1 = val1;
			String term2 = val2;
			assertion += term1 + ", " + term2;
		}

		assertion += ");";
		return assertion;
	}

	public List<Attribute> getSearchableAttibutes(AbstractClass c) {
		List<Attribute> l = new ArrayList<Attribute>();
		EList<Attribute> allAttributes = null;
		if (c instanceof Clazz) {
			Clazz cz = (Clazz) c;
			allAttributes = cz.getAllAttributes();
		} else {
			allAttributes = c.getAttributes();
		}
		for (Attribute att : allAttributes) {
			for (MetaInfo mi : att.getMetainfo()) {
				if (mi.getKey().equalsIgnoreCase("propertySearched") && mi.getValue().equalsIgnoreCase("true")) {
					l.add(att);
				}
			}
		}
		return l;
	}

	public List<Attribute> getAllAbstractClassSortedAttibutes(AbstractClass c) {
		List<Attribute> l = new ArrayList<Attribute>();
		if (c instanceof Clazz) {
			l.addAll(((Clazz) c).getClassAndAspectAttributes());
		} else {
			l.addAll(c.getAttributes());
		}
		return l;
	}

	public List<Attribute> getAllSortedAttibutes(AbstractClass c) {
		List<Attribute> l = new ArrayList<Attribute>();
		if (c instanceof Clazz) {
			l.addAll(((Clazz) c).getAllAttributes());
		} else {
			l.addAll(c.getAttributes());
		}
		return l;
	}

	public List<Attribute> filterList(List<Attribute> atts, String filter) {
		boolean negative = false;
		if (filter.startsWith("!=")) {
			negative = true;
			filter = filter.substring(2);
		}
		List<Attribute> r = new ArrayList<Attribute>();
		for (Attribute attribute : atts) {
			String fullName = attribute.getFullName();
			boolean matches = fullName.matches(filter);
			if (negative && !matches || !negative && matches) {
				r.add(attribute);
			}
		}
		return r;
	}

	public List<Clazz> getClazzWithoutAsptect(Aspect a) {
		List<Clazz> classWithAsptect = new ArrayList<Clazz>();
		List<Clazz> l = ((ClassPackage) CommonServices.getRootContainer(a)).getAllClasses();
		for (Clazz clazz : l) {
			for (Aspect ca : clazz.getAllLinkedAspects()) {
				if (ca.getName().equals(a.getName())) {
					classWithAsptect.add(clazz);
				}
			}
		}
		l.removeAll(classWithAsptect);
		return l;
	}

}
