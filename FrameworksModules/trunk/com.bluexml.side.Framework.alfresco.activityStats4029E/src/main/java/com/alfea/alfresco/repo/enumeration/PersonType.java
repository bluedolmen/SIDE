/**
 * 
 */
package com.alfea.alfresco.repo.enumeration;

/**
 * @author gwillems
 *
 */
public enum PersonType {
		MINE(), 
		OTHERS(), 
		ALL();
		
		/**
		 * String to Period
		 * @param str
		 * @return
		 */
		public static PersonType toPerson(String str)
	    {
	        try {
	            return valueOf(str.toUpperCase());
	        } 
	        catch (Exception ex) {
	            return ALL;
	        }
	    }  
}
