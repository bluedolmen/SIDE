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
