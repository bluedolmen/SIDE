package com.bluexml.side.util.generator.acceleo;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractAcceleoPackageGenerator extends AbstractAcceleoGenerator {

	abstract public Collection<IFile> buildPackages(String modelId) throws Exception;
	
	public Collection<IFile> complete() throws Exception {
		for (Map.Entry<String, List<IFile>> l : groupedModels.entrySet()) {
			String rootName = l.getKey();
			setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + rootName);
			Collection<IFile> packageFile = buildPackages(rootName);
			generatedFiles.addAll(packageFile);
			for (IFile p : packageFile) {
				monitor.getLog().addFileGeneratedLog(p.getName() + " created.", p.getName() + " created in " + p.getFullPath(), IFileHelper.getFile(p).toURI());
			}
		}
		for (IFile f : generatedFiles) {
			monitor.getLog().addFileGeneratedLog("Files Generated", f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI());
		}
		
		// add resources to match with package dependencies
		addDependences();
		
		return generatedFiles;
	}

}
