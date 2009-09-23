package com.bluexml.side.util.generator.documentation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoGenerator;
import com.bluexml.side.util.generator.documentation.services.DocumentationServices;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class DocumentationGenerator extends AbstractAcceleoGenerator {

	public static String MMUri;
	protected List<String> templates = new ArrayList<String>();

	public boolean isDocumentationGenerator() {
		return true;
	}

	public DocumentationGenerator() {
		// Static templates
		templates.add("/com.bluexml.side.Util.generator.documentation/src/templates/manifest.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Util.generator.documentation/src/templates/meta.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Util.generator.documentation/src/templates/mimetype.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Util.generator.documentation/src/templates/styles.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Util.generator.documentation/src/templates/settings.mt"); //$NON-NLS-1$
	}

	public Collection<IFile> complete() throws Exception {
		for (Map.Entry<String, List<IFile>> l : groupedModels.entrySet()) {
			String rootName = l.getKey();
			setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + rootName);

			String target = IFileHelper.getSystemFolderPath(getTargetPath()+File.separator+getTechVersion())+File.separator;
			new File(target).mkdirs();
			String source = IFileHelper.getSystemFolderPath(getTemporaryFolder()) + File.separator;
			FileHelper.copyFiles(new File(source), new File(target), true);
		}



		for (IFile f : generatedFiles) {
			monitor.getLog().addFileGeneratedLog("Files Generated", f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI());  //$NON-NLS-1$//$NON-NLS-2$
		}

		return generatedFiles;
	}

	public Collection<IFile> generate(IFile model) throws Exception {
		DocumentationServices.setModelName(model.getName());
		return super.generate(model);
	}

	protected String getMetamodelURI() {
		return MMUri;
	}

	public boolean check() {
		return true;
	}
}
