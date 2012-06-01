package com.bluexml.side.portal.helper;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.Visibility;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletAttributeInstance;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.portal.PositionGroup;

public class PortalHelper {

	public static PortalLayout createDefaultPortalLayout(Portal portal) {
		PortalLayout layout = PortalFactory.eINSTANCE.createPortalLayout();
		layout.setName("defaultLayout"); //$NON-NLS-1$
		Column createColumn = PortalFactory.eINSTANCE.createColumn();
		createColumn.setName("defaultColumn"); //$NON-NLS-1$
		layout.getColumns().add(createColumn);
		portal.getLayoutSet().add(layout);
		return layout;
	}

	public static Page createPage(Portal portal, PortalLayout layout, boolean generate, String id, Visibility visibility) {
		Page p = PortalFactory.eINSTANCE.createPage();
		p.setUseLayout(layout);
		p.setID(id);
		p.setGenerate(generate);
		p.setVisibility(visibility);
		portal.getPageSet().add(p);
		return p;
	}

	public static void createHavePortlet(PortalLayout layout, Column createColumn, int eobjectIndex, Page page, Portlet portletView) {
		HavePortlet haveProtView = PortalFactory.eINSTANCE.createHavePortlet();
		haveProtView.setAssociationPage(page);
		haveProtView.setAssociationPortlet(portletView);

		PositionGroup createPositionGroup = PortalFactory.eINSTANCE.createPositionGroup();
		createPositionGroup.setOnColumn(createColumn);
		createPositionGroup.setOnLayout(layout);
		createPositionGroup.setPosition(eobjectIndex);
		haveProtView.getPositionGroup().add(createPositionGroup);
		page.getPortlets().add(haveProtView);
	}

	public static Portlet createPortlet(Portal portal, String portletName) throws Exception {
		Portlet portletView = PortalFactory.eINSTANCE.createPortlet();
		portletView.setName(portletName);
		portal.getPortletSet().add(portletView);
		return portletView;
	}
	
	
	public static Portlet createPortlet(Portal portal, String portletName, Map<String, String> properties) throws Exception {
		Portlet portletView = PortalFactory.eINSTANCE.createPortlet();
		portletView.setIsInstanceOfPortletType(createPortletInstance(portal, properties));
		portletView.setName(portletName);
		portal.getPortletSet().add(portletView);
		return portletView;
	}

	public static InstanciatePortletType createPortletInstance(Portal portal, Map<String, String> properties) throws Exception {
		InstanciatePortletType portletTypeInstance = PortalFactory.eINSTANCE.createInstanciatePortletType();

		String typeId = "";
		for (Map.Entry<String, String> entries : properties.entrySet()) {
			typeId += "-" + entries.getKey();
		}
		PortletType portletType = searchIn(portal.getPortletSetType(), typeId);
		if (portletType == null) {
			portletType = PortalFactory.eINSTANCE.createPortletType();
			portletType.setId(typeId);
			portal.getPortletSetType().add(portletType);
		}
		portletTypeInstance.setPortletType(portletType);

		for (Map.Entry<String, String> prop : properties.entrySet()) {
			createPortletAttributeInstance(portletTypeInstance, prop.getKey(), prop.getValue());
		}
		return portletTypeInstance;
	}

	public static PortletAttributeInstance createPortletAttributeInstance(InstanciatePortletType portletTypeInstance, String propertyName, String value) {
		PortletType portletType = portletTypeInstance.getPortletType();
		EList<PortletAttribute> haveAttribute = portletType.getHaveAttribute();
		boolean exists = false;
		PortletAttribute att = null;
		for (PortletAttribute portletAttribute : haveAttribute) {
			if (portletAttribute.getName().equals(propertyName)) {
				exists = true;
				att = portletAttribute;
				break;
			}
		}
		if (!exists) {
			att = PortalFactory.eINSTANCE.createPortletAttribute();
			att.setName(propertyName);
			haveAttribute.add(att);
		}

		PortletAttributeInstance e = PortalFactory.eINSTANCE.createPortletAttributeInstance();
		e.setInstanceOf(att);
		e.setValue(value);
		PortalHelper.createMetaInfo(e, "escape", "false", false);
		portletTypeInstance.getInstances().add(e);
		return e;
	}

	public static PortletType searchIn(List<PortletType> l, String itemId) {
		for (PortletType object : l) {
			if (itemId.equals(object.getId())) {
				return object;
			}
		}
		return null;
	}

	public static void createMetaInfos(Map<String, String> properties, ModelElement c, boolean multiline) {
		for (Map.Entry<String, String> prop : properties.entrySet()) {
			createMetaInfo(c, prop.getKey(), prop.getValue(), multiline);
		}
	}

	public static void createMetaInfos(Map<String, String> properties, ModelElement c, boolean multiline, String keyPrefix) {
		for (Map.Entry<String, String> prop : properties.entrySet()) {
			createMetaInfo(c, keyPrefix + prop.getKey(), prop.getValue(), multiline);
		}
	}

	public static void createMetaInfo(ModelElement c, String key, String value, boolean multiline) {
		MetaInfo m = CommonFactory.eINSTANCE.createMetaInfo();
		m.setKey(key);

		if (multiline) {
			m.setMultilineValue(value);
		} else {
			m.setValue(value);
		}
		c.getMetainfo().add(m);
	}
}
