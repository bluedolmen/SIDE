package com.bluexml.side.util.generator.alfresco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.generator.packager.AbstractPackager;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.zip.ZipManager;

public class AmpPackager extends AbstractPackager {
	private static String alfrescoModuleComment = Activator.Messages.getString("AmpPackager_0");
	private Map<String, File> mapper;
	protected Properties moduleProperties;

	public AmpPackager(IFolder folder, Properties moduleProperties, IFolder ItechnoVPath) {
		super(folder, ItechnoVPath);
		this.moduleProperties = moduleProperties;
		mapper = createAMPSkelleton();
	}

	public IFile buildPackage() throws Exception {
		List<IFile> generatedFiles = IFileHelper.getAllFiles(IworkingDir);

		dispatchFiles(generatedFiles, mapper);
		buildModuleProperties();
		File ampFile = getPackageFile();
		ampFile.createNewFile();
		ZipManager.zip(getFolderToPackage(), ampFile, false);
		// if (doClean) {
		// FileHelper.deleteFile(getWorkingFolder());
		// }
		IFile ampIFile = getPackageIFile();
		return ampIFile;
	}

	@Override
	protected String getPackageFileName() {
		return "module." + moduleProperties.getProperty("module.id") + ".amp"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	@Override
	protected File getFolderToPackage() {
		return new File(getWorkingdir() + File.separator + "AMP"); //$NON-NLS-1$
	}

	private void dispatchFiles(List<IFile> files, Map<String, File> mapper) throws IOException {
		for (IFile f : files) {
			for (Map.Entry<String, File> ent : mapper.entrySet()) {
				if (IFileHelper.convertIRessourceToFile(f).getAbsolutePath().indexOf(ent.getKey()) != -1) {
					String path = f.getFullPath().makeAbsolute().toOSString();
					String pathIn = ent.getValue().getAbsolutePath() + File.separator + path.substring(path.indexOf(ent.getKey()) + ent.getKey().length());
					File dest = new File(pathIn);
					// put to this dir
					FileHelper.copyFiles(IFileHelper.convertIRessourceToFile(f), dest, true);
				}
			}
		}
	}

	private void buildModuleProperties() throws FileNotFoundException, IOException {
		File modulePropertiesfile = new File(getFolderToPackage().getAbsolutePath() + File.separator + "module.properties"); //$NON-NLS-1$
		moduleProperties.store(new FileOutputStream(modulePropertiesfile), alfrescoModuleComment);
	}

	/**
	 * Use to map generated filePath to corresponding AMP directory
	 * 
	 * @return
	 */
	private Map<String, File> createAMPSkelleton() {
		File ampRoot = getWorkingFolder();
		Map<String, File> mapper = new HashMap<String, File>();

		addToMap(mapper, "/alfresco/WEB-INF/classes/", ampRoot, "/config"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/config/", ampRoot, "/config/"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/alfresco/lib/", ampRoot, "/lib"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/licences/", ampRoot, "/licences"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/alfresco/jsp/", ampRoot, "/web/jsp"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/alfresco/css/", ampRoot, "/web/css"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/alfresco/images/", ampRoot, "/web/images"); //$NON-NLS-1$ //$NON-NLS-2$
		addToMap(mapper, "/alfresco/scripts/", ampRoot, "/web/scripts"); //$NON-NLS-1$ //$NON-NLS-2$

		return mapper;
	}

	private void addToMap(Map<String, File> map, String key, File ampRoot, String target) {
		map.put(key.replace("/", File.separator), createAndRegisterDir(ampRoot, target.replace("/", File.separator))); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private File createAndRegisterDir(File ampRoot, String p) {
		File dir = new File(getFolderToPackage().getAbsolutePath() + p);
		dir.mkdirs();
		return dir;
	}

}
