/**
 * 
 */
package com.alfea.alfresco.repo.enumeration;

/**
 * @author gwillems
 *
 */
public enum PeriodType {
		TODAY(), 
		WEEK(), 
		MONTH(), 
		YEAR(), 
		NOVALUE();
		
		/**
		 * String to Period
		 * @param str
		 * @return
		 */
		public static PeriodType toPeriod(String str)
	    {
	        try {
	            return valueOf(str.toUpperCase());
	        } 
	        catch (Exception ex) {
	            return NOVALUE;
	        }
	    }  
}
