package com.bluexml.side.util.generator.documentation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.Util.ecore.DiagramImageExporter;
import com.bluexml.side.Util.ecore.export.JPEGExporter;
import com.bluexml.side.util.generator.acceleo.AbstractAcceleoGenerator;
import com.bluexml.side.util.generator.documentation.services.DocumentationServices;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class DocumentationGenerator extends AbstractAcceleoGenerator {
	

	public static String MMUri;
	protected static String GENERATOR_OPTIONS_SCREENSHOOT = "doc.gen.screenshoot";

	public boolean isDocumentationGenerator() {
		return true;
	}

	public DocumentationGenerator() {
		DocumentationServices.clearAll();
	}

	protected List<String> getDefaultTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("/com.bluexml.side.Util.generator.documentation/templates/manifest.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Util.generator.documentation/templates/meta.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Util.generator.documentation/templates/mimetype.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Util.generator.documentation/templates/styles.mt"); //$NON-NLS-1$
		templates.add("/com.bluexml.side.Util.generator.documentation/templates/settings.mt"); //$NON-NLS-1$
		return templates;
	}

	public Collection<IFile> complete() throws Exception {
		for (Map.Entry<String, List<IFile>> l : groupedModels.entrySet()) {
			String rootName = l.getKey();
			setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + rootName);

			String target = IFileHelper.getSystemFolderPath(getTargetPath() + File.separator + getTechVersion()) + File.separator;
			new File(target).mkdirs();
			String source = IFileHelper.getSystemFolderPath(getTemporaryFolder()) + File.separator;
			FileHelper.copyFiles(new File(source), new File(target), true);
		}

		for (IFile f : generatedFiles) {
			monitor.getLog().addFileGeneratedLog("Files Generated", f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI()); //$NON-NLS-1$//$NON-NLS-2$
		}

		return generatedFiles;
	}

	public Collection<IFile> generate(IFile model) throws Exception {
		DocumentationServices.setModelName(model.getName());

		// Check if there is a diagram file :
		IFile diag = IFileHelper.getIFile(model.getFullPath().toOSString() + "di");
		if (diag.exists() && includeScreenShoot()) {
			// If one generate image for each diagram :
			DiagramImageExporter diagExporter = new JPEGExporter();
			// TODO : add to result[]
			String pathName = getTemporarySystemFolder() + File.separator + model.getName().replaceAll("\\.", "-") + File.separator + "doc" + File.separator + "Pictures" + File.separator;
			File f = new File(pathName);
			f.mkdirs();
			diagExporter.exportFile(diag, pathName);
			// Add fileName to list (to include it in generation)
			List<String> diagImgFileName = diagExporter.getFileNames();
			for (String name : diagImgFileName) {
				DocumentationServices.addDiagImgPath(name);
			}
		}

		Collection<IFile> result = super.generate(model);
		return result;
	}

	protected String getMetamodelURI() {
		return MMUri;
	}

	public boolean check() {
		return true;
	}
	
	public boolean checkOption(String optionID) {
		return true;
	}
	public static boolean includeScreenShoot() {
		return includeScreenShoot(null);
	}

	public static boolean includeScreenShoot(EObject o) {
		return getGeneratorOptionValue(GENERATOR_OPTIONS_SCREENSHOOT);
	}
}
