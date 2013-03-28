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
package com.bluexml.side.util.libs;

import java.io.File;
import java.io.FileFilter;

public class FileExtensionFilter implements FileFilter {
	String exts[];

	public FileExtensionFilter(String exts) {
		this.exts = exts.split("\\|");
	}

	public boolean accept(File file) {
		boolean ok = true;
		try {
			if (file.isFile()) {
				String ext = FileHelper.getFileExt(file);
				for (String ext_ : exts) {
					ok &= ext_.equals(ext);
				}
			} else {
				ok = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
	}
}
