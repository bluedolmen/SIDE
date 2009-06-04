package com.bluexml.side.application.documentation;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

import com.bluexml.side.application.documentation.structure.LogEntry;
import com.bluexml.side.application.documentation.structure.SIDELog;
import com.bluexml.side.util.libs.IFileHelper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LogSave {

	public static void toXml(SIDELog log, String fileName, String folderName) {
		try {
			IFolder folder = IFileHelper.createFolder(folderName);
			File f = IFileHelper.getFile(folder);
			
			FileOutputStream fos;
			File file = new File(f,fileName);
			fos = new FileOutputStream(file);
			toXml(log, fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
	}
	
	protected static void toXml(SIDELog log, FileOutputStream fos) {
		XStream xstream = new XStream(new DomDriver());
		
		//Improve XML 
		xstream.alias("SIDELog", SIDELog.class);
		xstream.alias("logEntry", LogEntry.class);
		
		xstream.addImplicitCollection(SIDELog.class, "logEntries");
		
		xstream.useAttributeFor(SIDELog.class, "date");
		xstream.useAttributeFor(SIDELog.class, "name");
		xstream.useAttributeFor(SIDELog.class, "type");
		
		xstream.useAttributeFor(LogEntry.class, "type");
		
		xstream.toXML(log,fos);
	}
}
