/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
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
package com.bluexml.side.clazz.service.alfresco;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.common.CustomDataType;
import com.bluexml.side.common.DataType;

public class AttributeServices {

	static String numberRange = "/org/alfresco/components/form/controls/numberrange.ftl";
	static String dateRange = "/org/alfresco/components/form/controls/daterange.ftl";

	static String selectMany = "/org/alfresco/components/form/controls/selectmany.ftl";

	public String getPropertyType(EObject node) throws Exception {
		if (node instanceof Attribute) {
			Attribute object = (Attribute) node;

			DataType typ = object.getTyp();
			if (typ == DataType.CUSTOM) {
				// need to get the 
				CustomDataType customType = object.getCustomType();
				if (customType != null) {
					return CommonServices.getPrefixedQName(customType);
				} else {
					new Exception("attribute type=CUSTOM but customType is null !!");
				}
			} else {
				Alfresco_Data_Type alf_DataType = Alfresco_Data_Type.getAlf_DataType(typ);
				return alf_DataType.qname;
			}
			throw new Exception("somethings wrong ... " + object);
		}
		throw new Exception("node must be an attribute");
	}

	public String getShareSearchFormControl(Attribute object) throws Exception {

		if (object.getValueList() != null) {
			return selectMany;
		}
		DataType typ = object.getTyp();
		Alfresco_Data_Type alf_DataType = Alfresco_Data_Type.getAlf_DataType(typ);
		return alf_DataType.control;

	}

	public static DataType getPropertyType(String type) throws Exception {
		DataType dataType = Alfresco_Data_Type.getDataType(type);
		return dataType;
	}

	public String getFtlTypeConverter(EObject node) throws Exception {
		if (node instanceof Attribute) {
			Attribute object = (Attribute) node;

			String string = "?js_string";
			DataType typ = object.getTyp();

			Alfresco_Data_Type alf_DataType = Alfresco_Data_Type.getAlf_DataType(typ);
			if (alf_DataType.js_freemarker) {
				return string;
			} else {
				return alf_DataType.freemarker;
			}

		}
		throw new Exception("node must be an attribute");
	}

	public enum Alfresco_Data_Type {
		Any("d:any", "?string", false, DataType.OBJECT),
		Text("d:text", "?string", false, DataType.STRING),
		Mltext("d:mltext", "?string", false, DataType.CUSTOM),
		Content("d:content", "?string", false, DataType.CUSTOM),
		Int("d:int", "?int", false, DataType.INT, numberRange),
		Long("d:long", "?long", false, DataType.LONG, numberRange),
		Float("d:float", "?float", false, DataType.FLOAT, numberRange),
		Double("d:double", "?double", false, DataType.DOUBLE, numberRange),
		Date("d:date", "?date?string.full", false, DataType.DATE, dateRange),
		Datetime("d:datetime", "?datetime?string.full", false, DataType.DATE_TIME, dateRange),
		Boolean("d:boolean", "?string", false, DataType.BOOLEAN),
		Qname("d:qname", "?string", false, DataType.CUSTOM),
		Noderef("d:noderef", "?string", false, DataType.CUSTOM),
		Childassocref("d:childassocref", "?string", false, DataType.CUSTOM),
		Assocref("d:assocref", "?string", false, DataType.CUSTOM),
		Path("d:path", "?string", false, DataType.CUSTOM),
		Category("d:category", "?string", false, DataType.CUSTOM),
		Locale("d:locale", "?string", false, DataType.CUSTOM),
		Version("d:version", "?string", false, DataType.CUSTOM),
		Period("d:period", "?string", false, DataType.CUSTOM),
		Cmisid("cmis:id", "?string", false, DataType.CUSTOM),
		Cmisuri("cmis:uri", "?string", false, DataType.CUSTOM),
		Cmishtml("cmis:html", "?string", false, DataType.CUSTOM);
		/*
		 * d:any
		 * d:text
		 * d:mltext
		 * d:content
		 * d:int
		 * d:long
		 * d:float
		 * d:double
		 * d:date
		 * d:datetime
		 * d:boolean
		 * d:qname
		 * d:noderef
		 * d:childassocref
		 * d:assocref
		 * d:path
		 * d:category
		 * d:locale
		 * d:version
		 * d:period
		 */

		static Map<String, DataType> m = null;
		static Map<DataType, Alfresco_Data_Type> m2 = null;

		static {
			// initialize m2
			m2 = new HashMap<DataType, Alfresco_Data_Type>();
			Alfresco_Data_Type[] values = values();
			for (Alfresco_Data_Type alfresco_Data_Type : values) {
				m2.put(alfresco_Data_Type.datatype, alfresco_Data_Type);
			}
			// add none bijective mapping
			m2.put(DataType.CHAR, Text);
			m2.put(DataType.BYTE, Int);
			m2.put(DataType.SHORT, Int);
			m2.put(DataType.TIME, Datetime);

		}
		static Map<String, Alfresco_Data_Type> valueof = null;
		static {
			// initialize valueof
			valueof = new HashMap<String, Alfresco_Data_Type>();
			Alfresco_Data_Type[] values = values();
			for (Alfresco_Data_Type alfresco_Data_Type : values) {
				valueof.put(alfresco_Data_Type.qname, alfresco_Data_Type);
			}
		}
		static String js_String = "?js_string";

		String qname = null;
		String freemarker = null;
		String control = "";
		boolean js_freemarker = false;
		DataType datatype = null;

		Alfresco_Data_Type(String qname, String freemarker, boolean js_freemarker, DataType datatype, String control) {
			this(qname, freemarker, js_freemarker, datatype);
			this.control = control;
		}

		Alfresco_Data_Type(String qname, String freemarker, boolean js_freemarker, DataType datatype) {
			this.qname = qname;
			this.freemarker = freemarker;
			this.js_freemarker = js_freemarker;
			this.datatype = datatype;
		}

		public static Alfresco_Data_Type getAlf_DataType(String qname) {
			return valueof.get(qname);
		}

		public static Alfresco_Data_Type getAlf_DataType(DataType qname) {
			return m2.get(qname);
		}

		public static DataType getDataType(String qname) {
			if (null == null) {
				m = new HashMap<String, DataType>();
				Alfresco_Data_Type[] values = values();
				for (Alfresco_Data_Type alfresco_Data_Type : values) {
					m.put(alfresco_Data_Type.qname, alfresco_Data_Type.datatype);
				}
			}
			return m.get(qname);

		}
	}

	public static String getListContraintPrefixedQName(Enumeration en) throws Exception {
		if (CommonServices.isSimpleName(en)) {
			return CommonServices.getPrefixedQName(en);
		}
		return CommonServices.getPrefix(en) + ":nomenclature:" + CommonServices.getNamedModelElementQName(en);

	}
}
