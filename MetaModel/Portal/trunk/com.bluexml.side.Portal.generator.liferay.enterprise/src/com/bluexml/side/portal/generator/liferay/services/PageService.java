package com.bluexml.side.portal.generator.liferay.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PositionGroup;


public class PageService {
	
	
	public static int getPageIndex(Page p) {
		return ((Portal)p.eContainer()).getPageSet().indexOf(p);
	}


	
	public static String getLayoutColumn(Page p) throws Exception{
		
		PortalLayout pl = getPortalLayout(p);
		String layoutName = pl.getName();

		Map<String, HashMap<Integer,String>> columnsPortlets = getLayoutColumn_(p, null);
		
		// build <type-settings> text value for layout.xml
		String typeSettings = "<![CDATA[";
		// add columns
		for (String column : columnsPortlets.keySet()) {
			typeSettings += column + "=";
			// add portlet list
			typeSettings += buildPortletList(columnsPortlets.get(column));
			typeSettings += "\n";
		}
		// add layout template id
		typeSettings += "layout-template-id=" + layoutName;

		// close CDATA declaration
		typeSettings += "]]>";

		return typeSettings;
	}

	public static Map<String, HashMap<Integer,String>> getLayoutColumn_(Page p,Map<String, HashMap<Integer,String>> columnsPortlets) throws Exception{
		PortalLayout pl = getPortalLayout(p);
		if (columnsPortlets == null) {
			columnsPortlets = new HashMap<String, HashMap<Integer,String>>();
		}
		EList<HavePortlet> lhp = p.getPortlets();
		for (HavePortlet hp : lhp) {
			Portlet port = hp.getAssociationPortlet();
			String portletId = PortletService.getLiferayPortletId(port,p);
			PositionGroup pg = getRightPositionGroup(hp.getPositionGroup(), pl);
			// get the position
			int position= pg.getPosition();
			String columnName = pg.getOnColumn().getName();

			if (columnsPortlets.containsKey(columnName)) {
				
				columnsPortlets.get(columnName).put(position,portletId);
			} else {
				HashMap<Integer,String> portletList = new HashMap<Integer,String>();
				portletList.put(position,portletId);
				columnsPortlets.put(columnName, portletList);
			}
		}
		// add inherited portlet from ancestor Pages
		if (p.getIsChildPageOf() != null && p.getIsChildPageOf().isInherit()) {
			columnsPortlets = getLayoutColumn_(p.getIsChildPageOf().getIsChildPageOf(),columnsPortlets);
		}
		return columnsPortlets;
	}
	
	
	public static PositionGroup getRightPositionGroup(EList<PositionGroup> lpg, PortalLayout pl) {
		for (PositionGroup pg : lpg) {
			if (pg.getOnLayout().equals(pl)) {
				return pg;
			}
		}
		return null;
	}
	
	
	
	public static PortalLayout getPortalLayout(Page p) {
		if (p.getIsChildPageOf() != null && p.getIsChildPageOf().isInherit()) {
			return getPortalLayout(p.getIsChildPageOf().getIsChildPageOf());
		} else {
			return p.getUseLayout();
		}
	}
	
	public static String getParentPageIndex(Page p) {
		if (p.getIsChildPageOf() != null) {			
			return Integer.toString(getPageIndex(p.getIsChildPageOf().getIsChildPageOf())+1);
		} else {
			return "0";
		}
	}
	
	private static String buildPortletList(HashMap<Integer,String> list) {
		ArrayList<String> l = new ArrayList<String>();
		for (int c=0;c<list.size();c++) {
			if (list.get(c) !=null) {
				l.add(list.get(c));
			}			
		}
		return l.toString().replaceAll("[\\[\\] ]", "");
	}
	
}
