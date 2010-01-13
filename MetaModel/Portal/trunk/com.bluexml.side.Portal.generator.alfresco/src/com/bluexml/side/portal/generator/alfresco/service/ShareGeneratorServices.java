package com.bluexml.side.portal.generator.alfresco.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

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

	
}
