package com.bluexml.side.clazz.generator.alfresco.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.generator.report.ReportGenerator;

public class ParameterServices {

	/**
	 * Return the Author of the Birt Report
	 * 
	 * @param e
	 * @return the Author of the Birt Report
	 */
	public String getAuthor(EObject e) {
		return ReportGenerator.getAuthor();
	}

	/**
	 * Return the current Date
	 * 
	 * @param e
	 * @return the current Date
	 */
	public String getDate(EObject e) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	}
	
	/**
	 * Return the current Date
	 * 
	 * @param e
	 * @return the current Date
	 */
	public String getSimpleDate(EObject e) {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
	/**
	 * Return the current Date
	 * 
	 * @param e
	 * @return the current Date
	 */
	public String getDatePath(EObject e) {
		return new SimpleDateFormat("ddMMyyyyHHmm").format(new Date());
	}

	public String getAlfrescoURL(EObject e) {
		return ReportGenerator.getAlfrescoURL();
	}
}
