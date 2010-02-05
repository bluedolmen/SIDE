package com.bluexml.side.clazz.generator.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoPackageGenerator;
import com.bluexml.side.util.generator.packager.WarPatchPackager;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class ReportGenerator extends AbstractAcceleoPackageGenerator{

	public static String GENERATOR_CODE = "CODE_GED_G_C_REPORT"; //$NON-NLS-1$
	public static String GENERATOR_CONFIGURATION_PARAMETER_AUTHOR = "report.author"; //$NON-NLS-1$
	public static String MMUri = "http://www.kerblue.org/class/1.0"; //$NON-NLS-1$
	public static String BIRT_WEBAPP_KEY = "birt.webapp"; //$NON-NLS-1$
	public static final String CONFIGURATION_PARAMETER_ALFRESCO_HOME = "alfresco.url"; //$NON-NLS-1$


	public ReportGenerator(){
		techVersion = "Birt_2.3.x"; //$NON-NLS-1$
		this.setTEMP_FOLDER(getTechVersion());
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		List<String> birtTemplates = new ArrayList<String>();
		birtTemplates.add("/com.bluexml.side.Class.generator.report/templates/birt/alfrescoGeneretor_birt_all_in_one.mt"); //$NON-NLS-1$
		return birtTemplates;
	}

	public boolean check() {
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

	/**
	 * This method return the author of the generation
	 *
	 * @return the author of the generation
	 */
	public static String getAuthor(){

		String author = "John Doe";
		if(!"".equals(getGenerationParameter(GENERATOR_CONFIGURATION_PARAMETER_AUTHOR))){ //$NON-NLS-1$
			author = getGenerationParameter(GENERATOR_CONFIGURATION_PARAMETER_AUTHOR);
		}
		return author;
	}

	public static String getAlfrescoURL() {
		String alfrescoURL = "John Doe";
		if(!"".equals(getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCO_HOME))){ //$NON-NLS-1$
			alfrescoURL = getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCO_HOME);
		}
		return alfrescoURL;
	}
	/*public Collection<IFile> complete() throws Exception {

		String target = IFileHelper.getSystemFolderPath(getTargetPath()+File.separator+getTechVersion())+File.separator;
		new File(target).mkdirs();
		//String source = IFileHelper.getSystemFolderPath(getTemporaryFolder()+File.separator+groupedModels.keySet().toArray()[0].toString()) + File.separator;
		String source = IFileHelper.getSystemFolderPath(getTemporaryFolder()) + File.separator;
		FileHelper.copyFiles(new File(source), new File(target), true);

		for (IFile f : generatedFiles) {
		monitor.getLog().addFileGeneratedLog("Files Generated", f.getLocation().toOSString() + "", IFileHelper.getFile(f).toURI());
			if (generationParameters.containsKey(BIRT_WEBAPP_KEY)) {
				String birtUrl = generationParameters.get(BIRT_WEBAPP_KEY);
				String uri = birtUrl + "/frameset?__report=" + IFileHelper.getFile(f).getName(); //$NON-NLS-1$
			monitor.getLog().addServiceLog("Birt Report",uri, uri);
			}
		}

		return generatedFiles;
	}
*/
	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		Collection<IFile> pkgs = new ArrayList<IFile>();
		WarPatchPackager pkger = new WarPatchPackager(IFileHelper.getIFolder(getTemporaryFolder()), "SIDE_Birt_"+modelId, techVersion, "birt");
		IFile package_ = pkger.buildPackage();
		pkgs.add(package_);
		return pkgs;
	}

}
