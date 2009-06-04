package com.bluexml.side.application.documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.ProcessingInstruction;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.application.documentation.structure.LogEntry;
import com.bluexml.side.application.documentation.structure.SIDELog;
import com.bluexml.side.util.libs.IFileHelper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LogSave {

	/**
	 * Render a SIDELog to a xml file using the given fileName in the given
	 * folderName
	 * 
	 * @param log
	 * @param fileName
	 * @param folderName
	 */
	public static void toXml(SIDELog log, String fileName, String folderName) {
		try {
			IFolder folder = IFileHelper.createFolder(folderName);
			File f = IFileHelper.getFile(folder);

			FileOutputStream fos;
			File file = new File(f, fileName);
			file.createNewFile();
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

	/**
	 * Record the SIDELog into the given outputStream
	 * 
	 * @param log
	 * @param fos
	 */
	protected static void toXml(SIDELog log, OutputStream fos) {
		XStream xstream = new XStream(new DomDriver());

		// Improve XML
		xstream.alias("SIDELog", SIDELog.class);
		xstream.alias("logEntry", LogEntry.class);

		xstream.addImplicitCollection(SIDELog.class, "logEntries");

		xstream.useAttributeFor(SIDELog.class, "date");
		xstream.useAttributeFor(SIDELog.class, "name");
		xstream.useAttributeFor(SIDELog.class, "type");

		xstream.useAttributeFor(LogEntry.class, "type");

		xstream.toXML(log, fos);
	}

	/**
	 * Will build a unique log file from all the xml files available in the
	 * given folder
	 * 
	 * @param folderName
	 * @throws Exception
	 */
	public static void buildGeneraLogFile(String folderName) throws Exception {
		IFolder folder = IFileHelper.createFolder(folderName);
		// File f = IFileHelper.getFile(folder);
		// We get all files
		List<IFile> toMerge = IFileHelper.getAllFiles(folder);

		// We create the top root element of the general log file
		Element rootNode = new Element("logRoot");
		Document doc = new Document();
		ProcessingInstruction pi = new ProcessingInstruction("xml-stylesheet",
				"type='text/xsl' href='log2html.xsl'");
		doc.addContent(pi);
		doc.setRootElement(rootNode);

		// For all .xml files we get their content and add it to general log
		// file
		for (IFile xmlFile : toMerge) {
			if (xmlFile.getName().endsWith(".xml")
					&& !xmlFile.getName().equals("log.xml")) {
				SAXBuilder builder = new SAXBuilder();
				try {
					Document xml = builder.build(IFileHelper.getFile(xmlFile));
					rootNode.addContent((Element) xml.getRootElement().clone());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// We create the general log file
		IFileHelper.deleteFile(folder.getFullPath()
				+ System.getProperty("file.separator") + "log.xml");
		IFile genLog = IFileHelper.createFile(folder, "log.xml");
		if (genLog != null) {
			File genLogFile = IFileHelper.getFile(genLog);

			XMLOutputter outputter = new XMLOutputter();
			FileWriter writer = new FileWriter(genLogFile, false);
			outputter.output(doc, writer);
			writer.close();
		}
		InputStream in = LogSave.class
				.getResourceAsStream("staticResources/log2html.xsl");
		File xsl = new File(folder.getFullPath()
				+ System.getProperty("file.separator") + "log2html.xsl");
		FileOutputStream fos = new FileOutputStream(xsl);
		int data = in.read();
		while (data != -1) {
			fos.write(data);
			data = in.read();
		}
	}
}
