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
package com.bluexml.side.portal.generator.alfresco.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.common.Visibility;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.portal.PositionGroup;

public class ShareGeneratorServices {
	Properties defaultShareElements;
	List<String> publicPages = new ArrayList<String>();
	public final static String defaultPublicPagesKey = "defaultPublicPages";
	List<String> defaultPages = new ArrayList<String>();
	public final static String defaultPagesKey = "defaultPages";

	public ShareGeneratorServices() {
		defaultShareElements = new Properties();
		InputStream in = this.getClass().getResourceAsStream("defaultShareElements.properties");

		try {

			defaultShareElements.load(in);

			String[] pages = defaultShareElements.getProperty(defaultPagesKey).split(",");
			for (String string : pages) {
				publicPages.add(string);
			}
			String[] publicPages = defaultShareElements.getProperty(defaultPublicPagesKey).split(",");
			for (String string : publicPages) {
				defaultPages.add(string);
			}

		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ShareGeneratorServices();
	}

	public static String getPublicPageList(Portal p) {
		EList<Page> l = p.getPageSet();

		// sort page following page.position
		List<Page> sortedPage = new ArrayList<Page>(l);
		Collections.sort(sortedPage, new Comparator<Page>() {

			public int compare(Page arg0, Page arg1) {
				return arg0.getPosition() - arg1.getPosition();
			}

		});

		List<String> rt = new ArrayList<String>();
		for (Page page : sortedPage) {
			if (page.getVisibility().equals(Visibility.PUBLIC)) {
				rt.add("{\"pageId\":\"" + page.getID().toLowerCase() + "\"}");
			}
		}

		return rt.toString();
	}

	public static boolean isFormPortlet(Portlet p) {
		PortletInternal pInt = p.getIsPortletInternal();
		return pInt != null && pInt.getType().equals(InternalPortletType.FORM);
	}

	public static boolean isViewPortlet(Portlet p) {
		PortletInternal pInt = p.getIsPortletInternal();
		return pInt != null && pInt.getType().equals(InternalPortletType.VIEW);
	}

	public static boolean isCustomPortlet(Portlet p) {
		return p.getIsPortletInternal() == null && p.getIsInstanceOfPortletType() != null;
	}
	
	/**
	 * Hack : acceleo mt template do not "see" the getSortedHavePortletsInColumn method
	 * So we define a java service for acceleo mt to access to the method
	 * @param p
	 * @param c
	 * @return
	 */
	public static List<HavePortlet> getOrderedHaveProtlets(Page p, Column c) {
		System.out.println("ShareGeneratorServices.getOrderedHaveProtlets()");
		System.out.println("\tpage :"+p.getID());
		System.out.println("\tcolumn :"+c.getFullName());
		List<Integer> li = new ArrayList<Integer>();
		List<HavePortlet> l = new ArrayList<HavePortlet>();
		EList<HavePortlet> sortedHavePortletsInColumn = p.getSortedHavePortletsInColumn(c);
		for (HavePortlet havePortlet : sortedHavePortletsInColumn) {
			int position = havePortlet.getPositionGroup().get(0).getPosition();
			if (!li.contains(position)) {
				l.add(havePortlet);
				li.add(position);
			} else {
				System.out.println("ShareGeneratorServices.getOrderedHaveProtlets() same position :");
				System.out.println("\tportlet :"+havePortlet.getAssociationPortlet().getName());
			}
		}
		
		
		System.out.println("");
		return l;
	}
}
