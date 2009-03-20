package com.bluexml.side.workflow.generator.alfresco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator;

public class WorkflowGenerator extends AbstractAcceleoGenerator {

	public boolean check() {
		// Already usable
		return true;
	}

	@Override
	protected String getMetamodelURI() {
		return "http://www.kerblue.org/workflow/1.0";
	}

	@Override
	protected List<String> getTemplates() {
		List<String> result = new ArrayList<String>();
		result
				.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_context.mt");
		result
				.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_jpdl.mt");
		result
				.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_model.mt");
		result
				.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_properties.mt");
		return result;
	}

	public Collection<String> complete() throws Exception {
		List<String> result = new ArrayList<String>();
		// Aggregate workflow context
		aggregateWorkflowContext();
		result.add(getTargetPath()
				+ "/shared/classes/alfresco/extension/workflow-context.xml");

		// Aggregate alfresco model
		aggregateWorkflowModel();
		result.add(getTargetPath()
				+ "/shared/classes/alfresco/extension/generated/bpm/model.xml");

		return result;
	}

	private void aggregateWorkflowContext() throws Exception {
		IPath outputPath = new Path(getTargetPath());
		IPath extensionPath = outputPath
				.append("/shared/classes/alfresco/extension");
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace()
				.getRoot();
		IFolder extensionFolder = myWorkspaceRoot.getFolder(extensionPath);
		IFile workflowContext = extensionFolder.getFile("workflow-context.xml");
		if (workflowContext.exists()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document wcFile = db.parse(workflowContext.getRawLocation()
					.toFile());
			wcFile.getDocumentElement().normalize();

			IFile searchedFile = null;
			for (IFile f : generatedFiles)
				if (f.getName().endsWith("-workflow-context.xml.temporary"))
					searchedFile = f;
			if (searchedFile != null) {
				Document twcFile = db.parse(searchedFile.getRawLocation()
						.toFile());
				twcFile.getDocumentElement().normalize();

				NodeList properties = wcFile.getElementsByTagName("property");
				NodeList tproperties = twcFile.getElementsByTagName("property");

				// Copy workflowDefinitions
				Node node = searchNode(properties, "name",
						"workflowDefinitions");
				Node tnode = searchNode(tproperties, "name",
						"workflowDefinitions");
				Node list = getFirstChild(node);
				Node props = getFirstChild(getFirstChild(tnode));
				wcFile.adoptNode(props);
				list.appendChild(props);

				// Copy properties
				node = searchNode(properties, "name", "labels");
				tnode = searchNode(tproperties, "name", "labels");
				list = getFirstChild(node);
				Node value = getFirstChild(getFirstChild(tnode));
				wcFile.adoptNode(value);
				list.appendChild(value);

				// Save workflow-context.xml
				TransformerFactory tranFactory = TransformerFactory
						.newInstance();
				Transformer aTransformer = tranFactory.newTransformer();
				Source src = new DOMSource(wcFile);
				Result dest = new StreamResult(workflowContext.getRawLocation()
						.toFile());
				aTransformer.transform(src, dest);
			}
		} else {
			IFile searchedFile = null;
			for (IFile f : generatedFiles)
				if (f.getName().endsWith("-workflow-context.xml.temporary"))
					searchedFile = f;
			searchedFile.copy(workflowContext.getFullPath(), true,
					new NullProgressMonitor());
		}
	}

	private void aggregateWorkflowModel() throws Exception {
		IPath outputPath = new Path(getTargetPath());
		IPath extensionPath = outputPath
				.append("/shared/classes/alfresco/extension/generated/bpm");
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace()
				.getRoot();
		IFolder extensionFolder = myWorkspaceRoot.getFolder(extensionPath);
		IFile workflowModel = extensionFolder.getFile("model.xml");
		if (workflowModel.exists()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document wmFile = db.parse(workflowModel.getRawLocation()
					.toFile());
			wmFile.getDocumentElement().normalize();

			IFile searchedFile = null;
			for (IFile f : generatedFiles)
				if (f.getName().endsWith("-model.xml.temporary"))
					searchedFile = f;
			if (searchedFile != null) {
				Document twmFile = db.parse(searchedFile.getRawLocation()
						.toFile());
				twmFile.getDocumentElement().normalize();

				Node types = wmFile.getElementsByTagName("types").item(0);
				NodeList type = twmFile.getElementsByTagName("type");
				for (int i = 0; i < type.getLength(); ++i) {
					Node node = type.item(i).cloneNode(true);
					wmFile.adoptNode(node);
					types.appendChild(node);
				}

				// Save model.xml
				TransformerFactory tranFactory = TransformerFactory
						.newInstance();
				Transformer aTransformer = tranFactory.newTransformer();
				Source src = new DOMSource(wmFile);
				Result dest = new StreamResult(workflowModel.getRawLocation()
						.toFile());
				aTransformer.transform(src, dest);
			}
		} else {
			IFile searchedFile = null;
			for (IFile f : generatedFiles)
				if (f.getName().endsWith("-model.xml.temporary"))
					searchedFile = f;
			searchedFile.copy(workflowModel.getFullPath(), true,
					new NullProgressMonitor());
		}
	}

	private Node getFirstChild(Node node) {
		for (int i = 0; i < node.getChildNodes().getLength(); ++i) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeType() != Node.TEXT_NODE)
				return child;
		}
		return null;
	}

	private Node searchNode(NodeList nodeList, String attributeName,
			String attributeValue) {
		for (int i = 0; i < nodeList.getLength(); ++i) {
			Node node = nodeList.item(i);
			Node attribute = node.getAttributes().getNamedItem(attributeName);
			if (attribute.getNodeValue().equalsIgnoreCase(attributeValue))
				return node;
		}
		return null;
	}

}
