/**
 * 
 */
package com.bluexml.xforms.controller.mapping;

import com.bluexml.xforms.controller.binding.GenericAttribute;

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

	/** */
	private GenericAttribute attribute;

	public RepoContentInfoBean(String path, String name, String mimetype, GenericAttribute attr) {
		super();
		this.path = path;
		this.name = name;
		this.mimeType = mimetype;
		this.attribute = attr;
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

	/**
	 * @return the attribute
	 */
	public GenericAttribute getAttribute() {
		return attribute;
	}

}
