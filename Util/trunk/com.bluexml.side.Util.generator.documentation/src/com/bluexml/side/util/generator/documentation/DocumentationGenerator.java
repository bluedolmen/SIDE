package com.bluexml.side.util.generator.documentation;

import java.io.File;
import java.util.Collection;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoGenerator;
import com.bluexml.side.util.generator.documentation.services.DocumentationServices;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class DocumentationGenerator extends AbstractAcceleoGenerator {

	public static String MMUri;

	public Collection<IFile> complete() throws Exception {
		String target = IFileHelper.getSystemFolderPath(getTargetPath()+File.separator+getTechVersion())+File.separator;
		new File(target).mkdirs();
		String source = IFileHelper.getSystemFolderPath(getTemporaryFolder()) + File.separator;
		FileHelper.copyFiles(new File(source), new File(target), true);

		for (IFile f : generatedFiles) {
			addFileGeneratedLog("Files Generated", f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI());
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
