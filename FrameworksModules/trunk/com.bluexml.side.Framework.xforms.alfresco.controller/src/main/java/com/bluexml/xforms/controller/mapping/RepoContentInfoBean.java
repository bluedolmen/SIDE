/**
 * 
 */
package com.bluexml.xforms.controller.mapping;

/**
 * @author Amenel
 * 
 */
public class RepoContentInfoBean {
	/** Complete path (including name and extension) on the file system */
	private String path;
	/** name and extension chosen for the repo object */
	private String name;
	/** MIME type of the uploaded file */
	private String mimeType;

	public RepoContentInfoBean(String path, String name, String mimetype) {
		super();
		this.path = path;
		this.name = name;
		this.mimeType = mimetype;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getMimeType() {
		return mimeType;
	}

}
