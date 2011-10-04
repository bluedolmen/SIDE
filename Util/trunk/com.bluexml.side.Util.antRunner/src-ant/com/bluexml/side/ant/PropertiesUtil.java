package com.bluexml.side.ant;

import java.io.File;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.libs.IFileHelper;

public class PropertiesUtil {

	public static File getFileFromWorkspace(String filePath) {
		IFile ifile = IFileHelper.getIFile(filePath); 
		return IFileHelper.convertIRessourceToFile(ifile);
	}
	
	
	
	
	
}
