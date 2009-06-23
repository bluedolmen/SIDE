package com.bluexml.side.util.documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.ProcessingInstruction;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;

import com.bluexml.side.util.documentation.structure.LogEntry;
import com.bluexml.side.util.documentation.structure.SIDELog;
import com.bluexml.side.util.libs.IFileHelper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LogSave {
	
	public static String LOG_FILE_NAME = "log.xml";

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
					&& !xmlFile.getName().equals(LOG_FILE_NAME)) {
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
				+ System.getProperty("file.separator") + LOG_FILE_NAME);
		IFile genLog = IFileHelper.createFile(folder, LOG_FILE_NAME);
		if (genLog != null) {
			File genLogFile = IFileHelper.getFile(genLog);

			XMLOutputter outputter = new XMLOutputter();
			FileWriter writer = new FileWriter(genLogFile, false);
			outputter.output(doc, writer);
			writer.close();
		}

		moveStaticRessources(folder,doc);

	}

	private static void moveStaticRessources(IFolder folderDest, Document doc)
			throws IOException, TransformerException {
		String folderPath = folderDest.getLocation().toOSString()
				+ System.getProperty("file.separator");
		String folderSource = "staticResources"
				+ System.getProperty("file.separator");
		// We use xsl transformation and ouput html file into log directory
		// We move all files to the log directory
		moveFile(folderPath, "log2html.xsl", folderSource);
		moveFile(folderPath + "css" + System.getProperty("file.separator"),
				"style.css", folderSource + "css"
						+ System.getProperty("file.separator"));
		moveFile(folderPath + "img" + System.getProperty("file.separator"),
				"background.png", folderSource + "img"
						+ System.getProperty("file.separator"));
		moveFile(folderPath + "img" + System.getProperty("file.separator"),
				"link.png", folderSource + "img"
						+ System.getProperty("file.separator"));
		moveFile(folderPath + "img" + System.getProperty("file.separator"),
				"collapse.png", folderSource + "img"
						+ System.getProperty("file.separator"));
		moveFile(folderPath + "img" + System.getProperty("file.separator"),
				"expand.png", folderSource + "img"
						+ System.getProperty("file.separator"));
		moveFile(folderPath + "js" + System.getProperty("file.separator"),
				"jquery.js", folderSource + "js"
						+ System.getProperty("file.separator"));
		moveFile(folderPath + "js" + System.getProperty("file.separator"),
				"log.js", folderSource + "js"
						+ System.getProperty("file.separator"));
		makeHtml(doc, folderPath, "log2html.xsl", "log.html");
	}

	/**
	 * Will ouput html using xml with an xsl transfo
	 * @param doc
	 * @param folderDest
	 * @param xslName
	 * @throws TransformerException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void makeHtml(Document doc, String folderDest, String xslName, String htmlName) throws TransformerException, FileNotFoundException, IOException {
		JDOMResult docResult = new JDOMResult();
		org.jdom.Document resultat = null;
		TransformerFactory factory = TransformerFactory.newInstance();
		
		Transformer transformer = factory.newTransformer(new StreamSource(new File(folderDest + xslName)));
		
		transformer.transform(new org.jdom.transform.JDOMSource(doc), docResult);
		resultat = docResult.getDocument();
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(resultat, new FileOutputStream(folderDest + htmlName));
	}

	/**
	 * Move file from this jar to the specified folder
	 * @param folderDest
	 * @param fileName
	 * @param folderSource
	 * @throws IOException
	 */
	private static void moveFile(String folderDest, String fileName,
			String folderSource) throws IOException {
		InputStream in = LogSave.class.getResourceAsStream(folderSource
				+ fileName);

		File dest = new File(folderDest);
		if (!dest.exists()) {
			dest.mkdirs();
		}

		File file = new File(folderDest + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		int data = in.read();
		while (data != -1) {
			fos.write(data);
			data = in.read();
		}
		fos.close();
	}
}
