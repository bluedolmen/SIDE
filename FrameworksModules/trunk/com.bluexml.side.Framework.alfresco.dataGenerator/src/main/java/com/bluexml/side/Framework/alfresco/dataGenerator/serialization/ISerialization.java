/**
 * This interface defines serialization methods
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization;


public interface ISerialization {
	
	/**
	 * write in file a xml structured object
	 * @return true if the process is successful
	 * @throws Exception
	 */
	public boolean serializeXml() throws Exception;

}
