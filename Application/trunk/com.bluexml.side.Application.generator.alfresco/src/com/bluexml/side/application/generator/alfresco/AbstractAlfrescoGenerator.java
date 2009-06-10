package com.bluexml.side.application.generator.alfresco;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.application.deployer.alfresco.Packager;
import com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.model.merge.MergeUtils;

public abstract class AbstractAlfrescoGenerator extends AbstractAcceleoGenerator {

	public static String CONFIGURATION_PARAMETER_CATALINA_HOME = "CATALINA_HOME";
	protected Properties moduleProperties;
	protected IFile ampIFile = null;
	protected String mergedFilePath = "mergedFile";
	protected Map<String, List<IFile>> groupedModels = null;

	public String getTEMP_FOLDER(String model) {
		return getTEMP_FOLDER() + File.separator + model;
	}

	

	abstract public Properties buildModuleProperties(String modelId);

	public IFile buildAMPPackage(String modelId) throws Exception {

		Packager alfrescoPakager = new Packager(IFileHelper.getIFolder(getTemporaryFolder()), buildModuleProperties(modelId), techVersion);
		ampIFile = alfrescoPakager.buildAMP(null, doClean());
		return ampIFile;
	}

	/**
	 * this implementation take care of multi-model
	 */
	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String id_metamodel) throws Exception {
		Collection<IFile> results = new ArrayList<IFile>();
		if (modelsInfo.get(id_metamodel) != null && modelsInfo.get(id_metamodel).size() > 0) {
			List<IFile> models = modelsInfo.get(id_metamodel);
			groupedModels = MergeUtil.groupByRootPackage(models);
			for (Map.Entry<String, List<IFile>> l : groupedModels.entrySet()) {
				String rootName = l.getKey();
				List<IFile> models_ = l.getValue();
				IFile mergedModel = merging(models_);
				// initialize generator we must change the TEMP_FOLDER
				setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + rootName);
				// generate
				results.addAll(generate(mergedModel));
			}

		}
		return results;
	}

	@Override
	protected String getMetamodelURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> getTemplates() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<IFile> complete() throws Exception {
		for (Map.Entry<String, List<IFile>> l : groupedModels.entrySet()) {
			String rootName = l.getKey();
			setTEMP_FOLDER("generator_" + getClass().getName() + File.separator + rootName);
			IFile ampIFile = buildAMPPackage(rootName);
			generatedFiles.add(ampIFile);
			addFileGeneratedLog(ampIFile.getName() + " created.", ampIFile.getName() + " created in " + ampIFile.getFullPath(), IFileHelper.getFile(ampIFile).toURI());
		}
		for (IFile f : generatedFiles) {
			addFileGeneratedLog("Files Generated", f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI());
		}
		return generatedFiles;
	}

	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

	private IFile merging(List<IFile> models) throws IOException {
		if (models.size() == 1) {
			return models.get(0);
		} else {
			// create resource for merged file
			IFile mergedIFile = IFileHelper.getIFile(models.get(0).getParent().getRawLocation().makeAbsolute().toOSString() + File.separator + mergedFilePath + File.separator + models.get(0).getFileExtension());
			// do merge
			MergeUtils.merge(mergedIFile, models, this.getClass().getClassLoader());
			return mergedIFile;
		}
	}

}
