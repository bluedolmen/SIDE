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

	public static String convert_AlfrescoToXForms_Date(String isoDate) {
		long value;
		try {
			value = dateTimeFormatter.parseMillis(isoDate);
		} catch (IllegalArgumentException e) {
			if (e.getMessage().contains("is too short")) {
				// make passing dates as url parameters easier (see #937)
				value = dateTimeFormatter.parseMillis(isoDate + "T00:00:00.000Z");
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
