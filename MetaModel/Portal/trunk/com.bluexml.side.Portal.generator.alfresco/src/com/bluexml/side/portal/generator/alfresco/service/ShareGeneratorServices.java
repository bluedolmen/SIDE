package com.bluexml.side.portal.generator.alfresco.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.common.Visibility;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;

public class ShareGeneratorServices {
	Properties defaultShareElements;
	List<String> publicPages = new ArrayList<String>();
	public final static String defaultPublicPagesKey = "defaultPublicPages";
	List<String> defaultPages = new ArrayList<String>();
	public final static String defaultPagesKey = "defaultPages";

	public ShareGeneratorServices() {
		defaultShareElements = new Properties();
		InputStream in = this.getClass().getResourceAsStream(
				"defaultShareElements.properties");

		try {

			defaultShareElements.load(in);
			
			String[] pages =defaultShareElements.getProperty(defaultPagesKey).split(",");
			for (String string : pages) {
				publicPages.add(string);
			}
			String[] publicPages =defaultShareElements.getProperty(defaultPublicPagesKey).split(",");
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
		String result ="";
		EList<Page> l=p.getPageSet();
		List<String> rt = new ArrayList<String>();
		for (Page page : l) {
			if (page.getVisibility().equals(Visibility.PUBLIC)) {
				rt.add("{\"pageId\":\""+page.getID()+"\"}");
			}
		}
		
		return rt.toString();
	}
}
