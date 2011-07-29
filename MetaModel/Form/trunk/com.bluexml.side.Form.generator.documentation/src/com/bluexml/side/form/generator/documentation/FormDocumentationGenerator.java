package com.bluexml.side.form.generator.documentation;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import com.bluexml.side.form.common.RefreshOutlineAction;
import com.bluexml.side.form.editor.views.service.OutlineViewService;
import com.bluexml.side.util.generator.documentation.DocumentationGenerator;
import com.bluexml.side.util.generator.documentation.services.DocumentationServices;

public class FormDocumentationGenerator extends DocumentationGenerator {

	public FormDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/form/1.0"; //$NON-NLS-1$
	}

	@Override
	public Collection<IFile> generate(IFile model) throws Exception {
		OutlineViewService.setDoAll(true);

		RefreshOutlineAction action = new RefreshOutlineAction();
		IFile out = action.doGeneration(URI.createURI(model.getFullPath().toString()));

		String pathName = getTargetPath() + File.separator + getTEMP_FOLDER() + File.separator + model.getName().replaceAll("\\.", "-") + File.separator + "doc" + File.separator + "html";
		IPath path = new Path(pathName);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(path.segment(0));
		IFolder folder = project.getFolder(path.removeFirstSegments(1));

		if (!folder.exists())
			createFolder(folder);

		if (out.exists())
			out.move(folder.getFullPath().append(out.getFullPath().lastSegment()), true, new NullProgressMonitor());

		action.doClean();

		DocumentationServices.addOutlineRelativePath("html/output.html");
		OutlineViewService.setDoAll(false);
		return super.generate(model);
	}

	private void createFolder(IFolder folder) throws CoreException {
		if (!folder.exists()) {
			if (!folder.getParent().exists()) {
				IContainer c = folder.getParent();
				if (c instanceof IFolder) {
					IFolder _f = (IFolder) c;
					createFolder(_f);
				}
			}

			folder.create(true, true, new NullProgressMonitor());
		}
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = super.getMainTemplates();
		templates.add("/com.bluexml.side.Form.generator.documentation/templates/content.mt"); //$NON-NLS-1$
		return templates;
	}

}
