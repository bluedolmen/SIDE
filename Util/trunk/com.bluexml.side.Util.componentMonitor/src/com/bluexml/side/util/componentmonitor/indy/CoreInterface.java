/**
 * 
 */
package com.bluexml.side.util.componentmonitor.indy;

/**
 * Interface added for making the generation independent of the "as an Eclipse plugin" feature
 * so that the worker can also be a Maven plugin.<br/>
 * Because of svn:externals, this file should be the only file in its package.
 * 
 * @author Amenel
 * 
 */
public interface CoreInterface {
	void addText(String text);

	void addErrorTextAndLog(String title, Throwable error, String uri);

	void addServiceLog(String title, String description, String uri);
	
	void setTaskName(String name);

}
