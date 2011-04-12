package com.bluexml.side.util.documentation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IContainer;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.ProcessingInstruction;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.bluexml.side.util.documentation.structure.LogEntry;
import com.bluexml.side.util.documentation.structure.SIDELog;
import com.bluexml.side.util.documentation.structure.URIConverter;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.xml.XslTransformer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LogSave {
	public static final String encoding = "UTF-8";
	protected String fileSeparator = System.getProperty("file.separator"); //$NON-NLS-1$
	public static String LOG_FILE_NAME = "side-report.xml"; //$NON-NLS-1$
	public static String LOG_HTML_FILE_NAME = "side-report.html"; //$NON-NLS-1$
	public static String LOG_STAMP_FOLDER = "stamp"; //$NON-NLS-1$
	public static String LOG_TEMP_FOLDER = "work"; //$NON-NLS-1$
	public static String LOG_DOC_FOLDER = "doc"; //$NON-NLS-1$
	public static String LOG_FILE_EXT = ".odt"; //$NON-NLS-1$

	public static void toXml(SIDELog log, String fileName, File folder) throws Exception {
		folder.mkdirs();
		FileWriterWithEncoding fos;
		File file = new File(folder, fileName);
		file.createNewFile();
		fos = new FileWriterWithEncoding(file, "UTF-8");
		toXml(log, fos);
		fos.close();

	}

	/**
	 * Render a SIDELog to a xml file using the given fileName in the given
	 * folderName
	 * 
	 * @param log
	 * @param fileName
	 * @param folderName
	 * @throws Exception
	 */
	public static void toXml(SIDELog log, String fileName, String folderName) throws Exception {
		IFolder folder = IFileHelper.createFolder(folderName);
		File f = IFileHelper.getFile(folder);
		FileWriterWithEncoding fos;
		File file = new File(f, fileName);
		file.createNewFile();
		fos = new FileWriterWithEncoding(file, "UTF-8");
		toXml(log, fos);
		IFileHelper.refreshFolder((IContainer)folder);
		fos.close();
	}

	/**
	 * Record the SIDELog into the given outputStream
	 * 
	 * @param log
	 * @param fos
	 */
	protected static void toXml(SIDELog log, Writer fos) {
		System.out.println("Save To XML");
		XStream xstream = new XStream(new DomDriver());

		// Improve XML
		xstream.alias("SIDELog", SIDELog.class); //$NON-NLS-1$
		xstream.alias("logEntry", LogEntry.class); //$NON-NLS-1$
		xstream.addImplicitCollection(SIDELog.class, "logEntries"); //$NON-NLS-1$

		xstream.useAttributeFor(SIDELog.class, "date"); //$NON-NLS-1$
		xstream.useAttributeFor(SIDELog.class, "name"); //$NON-NLS-1$
		xstream.useAttributeFor(SIDELog.class, "id"); //$NON-NLS-1$
		xstream.useAttributeFor(SIDELog.class, "type"); //$NON-NLS-1$

		xstream.useAttributeFor(LogEntry.class, "type"); //$NON-NLS-1$
		xstream.registerConverter(new URIConverter());

		// TODO : use a converter (if we have other List<String>)
		xstream.alias("model", String.class); //$NON-NLS-1$
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
		IFolder logFolder = IFileHelper.createFolder(folderName);
		IFolder tmpFolder = IFileHelper.createFolder(logFolder.getFullPath().append(LOG_TEMP_FOLDER).toOSString());
		IFolder docFolder = IFileHelper.createFolder(logFolder.getFullPath().append(LOG_DOC_FOLDER).toOSString());

		// We create the top root element of the general log file
		Element rootNode = new Element("logRoot"); //$NON-NLS-1$
		Document doc = new Document();
		ProcessingInstruction pi = new ProcessingInstruction("xml-stylesheet", //$NON-NLS-1$
				"type='text/xsl' href='stylesheet/log2html.xsl'"); //$NON-NLS-1$
		doc.addContent(pi);
		doc.setRootElement(rootNode);

		// For all .xml files we get their content and add it to general log
		// file
		agregateLogs(rootNode, tmpFolder);

		// We search for xml file in stamp folder to know which generator have
		// been deployed
		addGeneratorStamp(rootNode, logFolder);

		// We search for all docs
		addDocLink(rootNode, docFolder);

		// We create the general log file
		IFileHelper.deleteFile(logFolder.getFullPath() + File.separator + LOG_FILE_NAME);
		IFile genLog = IFileHelper.createFile(logFolder, LOG_FILE_NAME);
		if (genLog != null) {
			File genLogFile = IFileHelper.getFile(genLog);
			FileWriterWithEncoding writer = new FileWriterWithEncoding(genLogFile, encoding, false);
			Format outputFormat = Format.getCompactFormat();
			outputFormat.setEncoding(encoding);
			XMLOutputter outputter = new XMLOutputter(outputFormat);
			outputter.output(doc, writer);
			writer.close();
			moveStaticRessources(logFolder, genLogFile);
		}

	}

	/**
	 * Add link to documentation (put in LOG_DOC_FOLDER)
	 * 
	 * @param rootNode
	 * @param docFolder
	 * @throws Exception
	 */
	private static void addDocLink(Element rootNode, IFolder docFolder) throws Exception {
		IFileHelper.refreshFolder((IContainer)docFolder);
		List<IFile> toLink = IFileHelper.getAllFilesForFolder(docFolder);
		Element rootDoc = new Element("documentation"); //$NON-NLS-1$
		for (IFile xmlFile : toLink) {
			if (xmlFile.getName().endsWith(LOG_FILE_EXT)) {
				Element entry = new Element("entry"); //$NON-NLS-1$
				entry.setAttribute("path", LOG_DOC_FOLDER + "/" + xmlFile.getName()); //$NON-NLS-1$//$NON-NLS-2$
				rootDoc.addContent(entry);
			}
		}
		rootNode.addContent(rootDoc);
	}

	/**
	 * Aggregate all log files.
	 * 
	 * @param rootNode
	 * @param tmpFolder
	 * @throws Exception
	 */
	private static void agregateLogs(Element rootNode, IFolder tmpFolder) throws Exception {
		// File f = IFileHelper.getFile(folder);
		// We get all files
		List<IFile> toMerge = IFileHelper.getAllFilesForFolder(tmpFolder);
		for (IFile xmlFile : toMerge) {
			if (xmlFile.getName().endsWith(".xml") && !xmlFile.getName().equals(LOG_FILE_NAME)) { //$NON-NLS-1$
				SAXBuilder builder = new SAXBuilder();
				Document xml = builder.build(IFileHelper.getFile(xmlFile));
				rootNode.addContent((Element) xml.getRootElement().clone());
			}
		}
	}

	/**
	 * Seek xml stamp file used as stamp file.
	 * 
	 * @param rootNode
	 * @param logFolder
	 * @throws Exception
	 */
	private static void addGeneratorStamp(Element rootNode, IFolder logFolder) throws Exception {
		IFolder stampFolder = IFileHelper.getIFolder(logFolder.getFullPath().append(LOG_STAMP_FOLDER).toOSString());
		if (stampFolder.exists()) {
			List<IFile> deployedStamps = IFileHelper.getAllFilesForFolder(stampFolder);
			Element rootDeployed = new Element("deployed"); //$NON-NLS-1$
			for (IFile xmlFile : deployedStamps) {
				// TODO : improve this to avoid error when xml files are
				// generated in the same folder
				if (xmlFile.getName().endsWith(".xml") && !xmlFile.getName().equals(LOG_FILE_NAME)) { //$NON-NLS-1$
					SAXBuilder builder = new SAXBuilder();
					Document xml = builder.build(IFileHelper.getFile(xmlFile));
					rootDeployed.addContent((Element) xml.getRootElement().clone());
				}
			}
			rootNode.addContent(rootDeployed);
		}
	}

	/**
	 * Move resources in jar to the given folder.
	 * 
	 * @param folderDest
	 * @param doc
	 * @throws Exception
	 */
	private static void moveStaticRessources(IFolder folderDest, File doc) throws Exception {
		String folderPath = folderDest.getLocation().toOSString() + System.getProperty("file.separator");
		String folderSource = "com/bluexml/side/util/documentation/staticResources/";
		// We use xsl transformation and ouput html file into log directory
		// We move all files to the log directory
		try {
			moveFile(folderPath + "stylesheet" + System.getProperty("file.separator"), "log2html.xsl", folderSource + "stylesheet");
			moveFile(folderPath + "css" + System.getProperty("file.separator"), "style.css", folderSource + "css");
			moveFile(folderPath + "img" + System.getProperty("file.separator"), "background.png", folderSource + "img");
			moveFile(folderPath + "img" + System.getProperty("file.separator"), "link.png", folderSource + "img");
			moveFile(folderPath + "img" + System.getProperty("file.separator"), "collapse.png", folderSource + "img");
			moveFile(folderPath + "img" + System.getProperty("file.separator"), "expand.png", folderSource + "img");
			moveFile(folderPath + "js" + System.getProperty("file.separator"), "jquery.js", folderSource + "js");
			moveFile(folderPath + "js" + System.getProperty("file.separator"), "log.js", folderSource + "js");
		} catch (Exception e) {
			throw new Exception("Error when copy files from static ressources", e);
		}
		String xsl = new File(folderPath, "stylesheet" + System.getProperty("file.separator") + "log2html.xsl").getAbsolutePath();
		String output = new File(folderPath, LOG_HTML_FILE_NAME).getAbsolutePath();
		XslTransformer.transform(doc.getAbsolutePath(), xsl, output);
	}

	/**
	 * Move file from this jar to the specified folder
	 * 
	 * @param folderDest
	 * @param fileName
	 * @param folderSource
	 * @throws Exception
	 */
	private static void moveFile(String folderDest, String fileName, String folderSource) throws Exception {
		/*
		 * InputStream in = LogSave.class.getResourceAsStream(folderSource +
		 * fileName);
		 */
		InputStream in = LogSave.class.getClassLoader().getResourceAsStream(folderSource + "/" + fileName);
		File dest = new File(folderDest);
		if (!dest.exists()) {
			dest.mkdirs();
		}
		File file = new File(folderDest + fileName);
		FileOutputStream fos;
		fos = new FileOutputStream(file);
		int data;
		data = in.read();
		while (data != -1) {
			fos.write(data);
			data = in.read();
		}
		fos.close();
	}
}
