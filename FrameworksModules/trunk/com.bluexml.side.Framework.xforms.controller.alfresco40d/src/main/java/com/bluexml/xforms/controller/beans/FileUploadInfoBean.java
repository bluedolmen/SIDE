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
/**
 * 
 */
package com.bluexml.xforms.controller.beans;

import com.bluexml.xforms.controller.binding.GenericAttribute;

/**
 * @author Amenel
 * 
 */
public class FileUploadInfoBean {
	/** Complete path (including name and extension) on the file system */
	private String path;

	/** name and extension chosen for the repo object */
	private String name;

	/** MIME type of the uploaded file */
	private String mimeType;

	/** The attribute that provided the path, name and mimetype */
	private GenericAttribute attribute;

	/**
	 * if set to true, requests an index to be appended to the filename if the original filename is
	 * not available, e.g "base.ext" becomes "base(1).ext" or "base(2).ext", etc.
	 */
	private boolean shouldAppendSuffix;

	public FileUploadInfoBean(String path, String name, String mimetype, GenericAttribute attr,
			boolean shouldAppendSuffix) {
		super();
		this.path = path;
		this.name = name;
		this.mimeType = mimetype;
		this.attribute = attr;
		this.shouldAppendSuffix = shouldAppendSuffix;
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

	/**
	 * @return the shouldAppendSuffix status
	 */
	public boolean isShouldAppendSuffix() {
		return shouldAppendSuffix;
	}

}
