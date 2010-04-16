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
