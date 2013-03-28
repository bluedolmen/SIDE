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
package com.bluexml.xforms.controller.mapping;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeConverter {

	/** The date time formatter. */
	protected static DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();
	/** The date formatter. */
	public static DateTimeFormatter dateFormatter = ISODateTimeFormat.date();
	/** The time formatter. */
	protected static DateTimeFormatter timeFormatter = ISODateTimeFormat.time();

	private DateTimeConverter() {
		super();
	}

	public static String convert_AlfrescoToXForms_DateTime(String isoDate) {
		long value = dateTimeFormatter.parseMillis(isoDate);
		return convert_AlfrescoToXForms_DateTime(value);
	}

	public static String convert_AlfrescoToXForms_Date(String isoDate, String localTimeZone) {
		long value;
		try {
			value = dateTimeFormatter.parseMillis(isoDate);
		} catch (IllegalArgumentException e) {
			if (e.getMessage().contains("is too short")) {
				// make passing dates as url parameters easier (see #937)
				value = dateTimeFormatter.parseMillis(isoDate + "T00:00:00.000" + localTimeZone);
			} else {
				throw (e);
			}
		}
		return convert_AlfrescoToXForms_Date(value);
	}

	public static String convert_AlfrescoToXForms_Time(String isoDate) {
		long value = dateTimeFormatter.parseMillis(isoDate);
		return convert_AlfrescoToXForms_Time(value);
	}

	public static String convert_AlfrescoToXForms_DateTime(long value) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(value);
		return DatatypeConverter.printDateTime(calendar);
	}

	public static String convert_AlfrescoToXForms_Date(long value) {
		return dateFormatter.print(value);
	}

	public static String convert_AlfrescoToXForms_Time(long value) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(value);
		return DatatypeConverter.printTime(calendar);
	}

	public static String convert_XFormsToAlfresco_DateTime(long value) {
		return dateTimeFormatter.print(value);
	}

	public static String convert_XFormsToAlfresco_DateTime(String value) {
		Calendar date = DatatypeConverter.parseDateTime(value);
		return dateTimeFormatter.print(date.getTimeInMillis());
	}

	public static String convert_XFormsToAlfresco_Date(String value) {
		Calendar date = DatatypeConverter.parseDate(value);
		return dateTimeFormatter.print(date.getTimeInMillis());
	}

	public static String convert_XFormsToAlfresco_Time(String value) {
		Calendar date = DatatypeConverter.parseTime(value);
		return dateTimeFormatter.print(date.getTimeInMillis());
	}

}
