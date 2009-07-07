package com.bluexml.side.clazz.generator.report;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoPackageGenerator;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;

public class ReportGenerator extends AbstractAcceleoPackageGenerator{

	public static String GENERATOR_OPTIONS_BIRT = "report.birt";
	public static String GENERATOR_CONFIGURATION_PARAMETER_AUTHOR = "report.author";
	public static String MMUri = "http://www.kerblue.org/class/1.0";
	public static String BIRT_WEBAPP_KEY = "birt.webapp";


	public ReportGenerator(){
		techVersion = "Birt_2.3.x";
		this.setTEMP_FOLDER(getTechVersion());
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		List<String> birtTemplates = new ArrayList<String>();

		if (getGeneratorOptionValue(GENERATOR_OPTIONS_BIRT)) {
			birtTemplates.add("/com.bluexml.side.Class.generator.report/src/com/bluexml/side/clazz/generator/report/birt/templates/alfrescoGeneretor_birt_all_in_one.mt");
		}

		return birtTemplates;
	}

	public boolean check() {
		return true;
	}

	/**
	 * This method return the author of the generation
	 *
	 * @return the author of the generation
	 */
	public static String getAuthor(){

		String auteur = "John Doe";
		if(!"".equals(getGenerationParameter(GENERATOR_CONFIGURATION_PARAMETER_AUTHOR))){
			auteur = getGenerationParameter(GENERATOR_CONFIGURATION_PARAMETER_AUTHOR);
		}
		return auteur;
	}

	public Collection<IFile> complete() throws Exception {

		String target = IFileHelper.getSystemFolderPath(getTargetPath()+File.separator+getTechVersion())+File.separator;
		new File(target).mkdirs();
		//String source = IFileHelper.getSystemFolderPath(getTemporaryFolder()+File.separator+groupedModels.keySet().toArray()[0].toString()) + File.separator;
		String source = IFileHelper.getSystemFolderPath(getTemporaryFolder()) + File.separator;
		FileHelper.copyFiles(new File(source), new File(target), true);

		for (IFile f : generatedFiles) {
			addFileGeneratedLog("Files Generated", f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI());
			if (generationParameters.containsKey(BIRT_WEBAPP_KEY)) {
				String birtUrl = generationParameters.get(BIRT_WEBAPP_KEY);
				String uri = birtUrl + "/frameset?__report=" + IFileHelper.getFile(f).getName();
				addServiceLog("Birt Report",uri, uri);
			}
		}

		return generatedFiles;
	}

	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
