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
import com.bluexml.side.util.documentation.structure.URIConverter;
import com.bluexml.side.util.libs.IFileHelper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LogSave {

	public static String LOG_FILE_NAME = "side-report.xml"; //$NON-NLS-1$
	public static String LOG_STAMP_FOLDER = "stamp"; //$NON-NLS-1$
	public static String LOG_TEMP_FOLDER = "work"; //$NON-NLS-1$
	public static String LOG_DOC_FOLDER = "doc"; //$NON-NLS-1$
	public static String LOG_FILE_EXT = ".odt"; //$NON-NLS-1$

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
			IFileHelper.refreshFolder(folder);
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
		xstream.alias("SIDELog", SIDELog.class); //$NON-NLS-1$
		xstream.alias("logEntry", LogEntry.class); //$NON-NLS-1$

		xstream.addImplicitCollection(SIDELog.class, "logEntries"); //$NON-NLS-1$

		xstream.useAttributeFor(SIDELog.class, "date"); //$NON-NLS-1$
		xstream.useAttributeFor(SIDELog.class, "name"); //$NON-NLS-1$
		xstream.useAttributeFor(SIDELog.class, "id"); //$NON-NLS-1$
		xstream.useAttributeFor(SIDELog.class, "type"); //$NON-NLS-1$

		xstream.useAttributeFor(LogEntry.class, "type"); //$NON-NLS-1$
		xstream.registerConverter(new URIConverter());

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

		// We search for xml file in stamp folder to know which generator have been deployed
		addGeneratorStamp(rootNode, logFolder);

		// We search for all docs
		addDocLink(rootNode, docFolder);

		// We create the general log file
		IFileHelper.deleteFile(logFolder.getFullPath()
				+ File.separator + LOG_FILE_NAME);
		IFile genLog = IFileHelper.createFile(logFolder, LOG_FILE_NAME);
		if (genLog != null) {
			File genLogFile = IFileHelper.getFile(genLog);

			XMLOutputter outputter = new XMLOutputter();
			FileWriter writer = new FileWriter(genLogFile, false);
			outputter.output(doc, writer);
			writer.close();
		}

		moveStaticRessources(logFolder, doc);
	}

	/**
	 * Add link to documentation (put in LOG_DOC_FOLDER)
	 * @param rootNode
	 * @param docFolder
	 * @throws Exception
	 */
	private static void addDocLink(Element rootNode, IFolder docFolder) throws Exception {
		IFileHelper.refreshFolder(docFolder);
		List<IFile> toLink = IFileHelper.getAllFilesForFolder(docFolder);
		Element rootDoc = new Element("documentation"); //$NON-NLS-1$
		for (IFile xmlFile : toLink) {
			if (xmlFile.getName().endsWith(LOG_FILE_EXT)) {
				Element entry = new Element("entry"); //$NON-NLS-1$
				entry.setAttribute("path", LOG_DOC_FOLDER + "/" + xmlFile.getName());  //$NON-NLS-1$//$NON-NLS-2$
				rootDoc.addContent(entry);
			}
		}
		rootNode.addContent(rootDoc);
	}

	/**
	 * Aggregate all log files.
	 * @param rootNode
	 * @param tmpFolder
	 * @throws Exception
	 */
	private static void agregateLogs(Element rootNode, IFolder tmpFolder) throws Exception {
		// File f = IFileHelper.getFile(folder);
		// We get all files
		List<IFile> toMerge = IFileHelper.getAllFilesForFolder(tmpFolder);
		for (IFile xmlFile : toMerge) {
			if (xmlFile.getName().endsWith(".xml") //$NON-NLS-1$
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
	}

	/**
	 * Seek xml stamp file used as stamp file.
	 * @param rootNode
	 * @param logFolder
	 * @throws Exception
	 */
	private static void addGeneratorStamp(Element rootNode, IFolder logFolder)
			throws Exception {
		IFolder stampFolder = IFileHelper.getIFolder(logFolder.getFullPath().append(LOG_STAMP_FOLDER).toOSString());
		if (stampFolder.exists()) {
			List<IFile> deployedStamps = IFileHelper.getAllFilesForFolder(stampFolder);
			Element rootDeployed = new Element("deployed"); //$NON-NLS-1$
			for (IFile xmlFile : deployedStamps) {
				//TODO : improve this to avoid error when xml files are generated in the same folder
				if (xmlFile.getName().endsWith(".xml") //$NON-NLS-1$
						&& !xmlFile.getName().equals(LOG_FILE_NAME)) {
					SAXBuilder builder = new SAXBuilder();
					try {
						Document xml = builder.build(IFileHelper.getFile(xmlFile));
						rootDeployed.addContent((Element) xml.getRootElement().clone());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			rootNode.addContent(rootDeployed);
		}
	}

	/**
	 * Move resources in jar to the given folder.
	 * @param folderDest
	 * @param doc
	 * @throws IOException
	 * @throws TransformerException
	 */
	private static void moveStaticRessources(IFolder folderDest, Document doc)
			throws IOException, TransformerException {
		String folderPath = folderDest.getLocation().toOSString()
				+ System.getProperty("file.separator");
		String folderSource = "src/com/bluexml/side/util/documentation/staticResources/";
		// We use xsl transformation and ouput html file into log directory
		// We move all files to the log directory
		moveFile(folderPath + "stylesheet"
				+ System.getProperty("file.separator"), "log2html.xsl",
				folderSource + "stylesheet");
		moveFile(folderPath + "css" + System.getProperty("file.separator"),
				"style.css", folderSource + "css");
		moveFile(folderPath + "img" + System.getProperty("file.separator"),
				"background.png", folderSource + "img");
		moveFile(folderPath + "img" + System.getProperty("file.separator"),
				"link.png", folderSource + "img");
		moveFile(folderPath + "img" + System.getProperty("file.separator"),
				"collapse.png", folderSource + "img");
		moveFile(folderPath + "img" + System.getProperty("file.separator"),
				"expand.png", folderSource + "img");
		moveFile(folderPath + "js" + System.getProperty("file.separator"),
				"jquery.js", folderSource + "js");
		moveFile(folderPath + "js" + System.getProperty("file.separator"),
				"log.js", folderSource + "js");
		// makeHtml(doc, folderPath, "log2html.xsl", "log.html");
	}

	/**
	 * Will ouput html using xml with an xsl transfo
	 *
	 * @param doc
	 * @param folderDest
	 * @param xslName
	 * @throws TransformerException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private static void makeHtml(Document doc, String folderDest,
			String xslName, String htmlName) throws TransformerException,
			FileNotFoundException, IOException {
		JDOMResult docResult = new JDOMResult();
		org.jdom.Document resultat = null;
		TransformerFactory factory = TransformerFactory.newInstance();

		Transformer transformer = factory.newTransformer(new StreamSource(
				new File(folderDest + xslName)));

		transformer
				.transform(new org.jdom.transform.JDOMSource(doc), docResult);
		resultat = docResult.getDocument();
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(resultat, new FileOutputStream(folderDest + htmlName));
	}

	/**
	 * Move file from this jar to the specified folder
	 *
	 * @param folderDest
	 * @param fileName
	 * @param folderSource
	 * @throws IOException
	 * @throws IOException
	 */
	private static void moveFile(String folderDest, String fileName,
			String folderSource) throws IOException  {
		/*InputStream in = LogSave.class.getResourceAsStream(folderSource
				+ fileName);*/
		InputStream in = LogSave.class.getClassLoader().getResourceAsStream(folderSource+"/"+fileName);
		File dest = new File(folderDest);
		if (!dest.exists()) {
			dest.mkdirs();
		}

		File file = new File(folderDest + fileName);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			System.err.println("FileOutputStream can't be call.");
			e.printStackTrace();
			throw e;
		}
		int data;
		try {
			data = in.read();
		} catch (IOException e) {
			System.err.println("Data can't be read");
			e.printStackTrace();
			throw e;
		}
		while (data != -1) {
			fos.write(data);
			data = in.read();
		}
		fos.close();
	}
}
