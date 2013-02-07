package com.bluexml.side.Framework.alfresco.dataGenerator.context;

/**
 * SIDE Extension
 * This class allows manipulations of path patterns to access files or
 * folders
 * 
 * @author dchevrier
 */
public class PathReader {

	private String pathPattern;

	/**
	 * @return the path
	 */
	public String getPathPattern() {
		return pathPattern;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPathPattern(String pathPattern) {
		this.pathPattern = pathPattern;
	}

}
