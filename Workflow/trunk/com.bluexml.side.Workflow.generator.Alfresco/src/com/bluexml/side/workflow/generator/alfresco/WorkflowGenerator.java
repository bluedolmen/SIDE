package com.bluexml.side.workflow.generator.alfresco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
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
import org.eclipse.emf.ecore.EObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bluexml.side.application.generator.ConflitResolverHelper;
import com.bluexml.side.application.generator.UnresolvedConflictException;
import com.bluexml.side.application.generator.XMLConflictResolver;
import com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator;

public class WorkflowGenerator extends AbstractAcceleoGenerator {

	XMLConflictResolver xmlresolver = null;

	public XMLConflictResolver getXmlresolver() {
		if (xmlresolver == null) {
			xmlresolver = new XMLConflictResolver(new ConflitResolverHelper(getTargetPath(), getTemporaryFolder()));
		}
		return xmlresolver;
	}

	public static String getTEMP_FOLDER(EObject node) {
		return TEMP_FOLDER;
	}
	
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
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_context.mt");
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_jpdl.mt");
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_model.mt");
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_properties.mt");
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_web_client_config.mt");
		return result;
	}

	public Collection<IFile> complete() throws Exception {
		List<IFile> conflict = searchForConflict();
		List<IFile> unresolvedconflict = new ArrayList<IFile>();
		boolean allresolved = true;
		for (IFile f : conflict) {
			if (f.getFullPath().toString().indexOf("/shared/classes/alfresco/extension/web-client-config-custom.xml") != -1 || f.getFullPath().toString().indexOf("/shared/classes/alfresco/extension/workflow-context.xml") != -1
					|| f.getFullPath().toString().indexOf("/shared/classes/alfresco/extension/generated/bpm/model.xml") != -1) {
				// known conflict, apply specific process

				getXmlresolver().resolveByMerging(f);
				System.out.println("resolved conflict on :" + f.getFullPath());
			} else {
				// unknown conflict
				allresolved = false;
				System.err.println("Unknow conflict detected on :" + f.getFullPath());
			}
		}
		if (!allresolved) {
			throw new UnresolvedConflictException(unresolvedconflict);
		}
		// conflicts are resolved
		getCresolver().copyToFinalFolder();
		// delete temporary folder
		ConflitResolverHelper.deleteFolder(getTemporaryFolder());
		return generatedFiles;
	}

	

}
