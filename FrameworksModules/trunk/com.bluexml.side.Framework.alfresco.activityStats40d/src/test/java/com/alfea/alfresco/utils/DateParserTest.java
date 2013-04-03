package com.alfea.alfresco.utils;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;


public class DateParserTest {
	@Test
	public void testParseDate(){
		String dateStr = "2011-08-26T15:30:26.000+02:00";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
		try {
			simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			fail("Fail to parse date " + dateStr);
			e.printStackTrace();
		}
		
	}
}
