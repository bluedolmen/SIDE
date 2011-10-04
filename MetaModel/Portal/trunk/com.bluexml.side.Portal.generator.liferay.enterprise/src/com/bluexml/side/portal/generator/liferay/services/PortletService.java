package com.bluexml.side.portal.generator.liferay.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletAttributeInstance;


public class PortletService {
	public final static String PORTLET_INSTANCE = "_INSTANCE_";
	static List<Portlet> allPortlet = null;

	

	/**
	 * 
	 * @param p
	 * @return
	 */
	public static String getLiferayPortletId(Portlet p,Page page) throws Exception {
		String portletType = getLiferayPortletType(p);
		if (p.getIsInstanceOfPortletType() != null) {
			// default portlets
			if (p.getIsInstanceOfPortletType().getPortletType().isInstanceable()) {
				return portletType + buildInstanceId(p,page);
			}
		} else {
			// use defined portlets (generated) relate to Class or other BxDSMM
			Object type = p.getIsPortletInternal().getView();
			if (p.getIsPortletInternal().getForm() != null) {
				type = p.getIsPortletInternal().getForm();
			}
			// resolve to liferayPortletType
			if (GeneratedPortletsType.getEnum(type).isInstanciable()) {
				return portletType + buildInstanceId(p,page);
			}
		}
		return portletType;
	}

	private static String buildInstanceId(Portlet p,Page page) throws Exception {
		// instance id is like this : _INSTANCE_[a-z|A-Z|0-9](4)
		String padding = "ZZZZ";
		int index = getPortletIndex(p);
		int indexPage = PageService.getPageIndex(page);
		if (index == -1) {
			throw new Exception("Index not found for Portlet :" + p.getName());
		}
		String indexString = Integer.toHexString(index)+Integer.toHexString(indexPage);
		if (indexString.length() > 4) {
			throw new Exception("To many Portlets !");
		}
		String instanceid = padding.substring(indexString.length()) + indexString;
		return "_INSTANCE_" + instanceid;
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public static String getLiferayPortletType(Portlet p) throws Exception {
		if (p.getIsInstanceOfPortletType() != null) {
			// default portlets
			return p.getIsInstanceOfPortletType().getPortletType().getId();
		} else {
			// portlets liked with BxDS ModelElement
			Object type = p.getIsPortletInternal().getView();
			if (p.getIsPortletInternal().getForm() != null) {
				type = p.getIsPortletInternal().getForm();
			}
			// resolve to liferayPortletType
			String resolvedType = GeneratedPortletsType.getEnum(type).getLiferayPortletType();
			return resolvedType;
		}

	}

	/**
	 * only available for PortletType
	 * 
	 * @return
	 */
	public static Map<String, List<String>> getPortletPreferencies(Portlet p) {
		// Map(PortletAttribute : [PortletAttributeInstance])
		Map<String, List<String>> columnsPortlets = new HashMap<String, List<String>>();
		// build columnPortlets Map
		if (p.getIsInstanceOfPortletType() != null) {
			EList<PortletAttributeInstance> lhp = p.getIsInstanceOfPortletType().getInstances();
			for (PortletAttributeInstance hp : lhp) {
				String attributeName = hp.getInstanceOf().getName();
				if (columnsPortlets.containsKey(attributeName)) {
					columnsPortlets.get(attributeName).add(hp.getValue());
				} else {
					List<String> portletList = new ArrayList<String>();
					portletList.add(hp.getValue());
					columnsPortlets.put(attributeName, portletList);
				}
			}
		}
		if (p.getIsPortletInternal() != null) {			
			Object type = p.getIsPortletInternal().getView();
			if (p.getIsPortletInternal().getForm() != null) {
				type = p.getIsPortletInternal().getForm();
			}
			// build service url
			GeneratedPortletsType gpt = GeneratedPortletsType.getEnum(type);
			
			String url = gpt.buildUrl(p);
			
			// record url property
			ArrayList<String> value = new ArrayList<String>();
			value.add(url);
			columnsPortlets.put(gpt.getParameterName(), value);
		}
		return columnsPortlets;
	}

	public static String buildProperties(Portlet p) {
		String result = "";
		Map<String, List<String>> properties = getPortletPreferencies(p);
		for (Entry<String, List<String>> e : properties.entrySet()) {
			Element prop = buildProperty(e);
			result += convertDocument2String(new Document(prop)) + "\n";
		}

		return result;
	}

	public static String convertDocument2String(Document doc) {
		XMLOutputter outputter = new XMLOutputter();
		Format format = Format.getCompactFormat();
		format.setOmitEncoding(true);
		format.setOmitDeclaration(true);
		outputter.setFormat(format);
		return outputter.outputString(doc);

	}

	public static Element buildProperty(Entry<String, List<String>> e) {
		Element result = new Element("preference");
		Element name = new Element("name");
		name.setText(e.getKey());
		result.addContent(name);
		result.addContent(buildValues(e.getValue()));
		return result;
	}

	public static List<Element> buildValues(List<String> values) {
		List<Element> results = new ArrayList<Element>();
		for (String value : values) {
			Element val = new Element("value");
			val.setText(value);
			results.add(val);
		}
		return results;
	}

	/**
	 * beware of Portlet instance could not be same but are equals model is load
	 * more than one time
	 * 
	 * @param p
	 * @return
	 */
	public static int getPortletIndex(Portlet p) {
		Portal portal = (Portal)p.eContainer();		
		return portal.getPortletSet().indexOf(p);
	}

	public static void cleanList() {
		allPortlet = null;
	}
	
}
