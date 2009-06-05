package com.bluexml.side.application.deployer.alfresco;

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

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.zip.ZipManager;

public class Packager {
	private String workingdir;
	private IFolder IworkingDir; // generated folder
	private Properties moduleProperties;
	private static String alfrescoModuleComment = "generated by S-IDE Alfresco Generator";
	private String technoV;

	public Packager(IFolder folder, Properties moduleProperties, String technoV) {
		this.IworkingDir = folder;
		this.workingdir = IFileHelper.convertIRessourceToSystemString(folder);
		this.moduleProperties = moduleProperties;
		this.technoV = technoV;
	}

	public IFile buildAMP(List<IFile> generatedFiles, boolean doClean) throws Exception {
		if (generatedFiles == null) {
			// must use all generated files
			generatedFiles = IFileHelper.getAllFiles(IworkingDir);
		}
		Map<String, File> mapper = createAMPSkelleton();
		dispatchFiles(generatedFiles, mapper);
		buildModuleProperties();
		File ampFile = getAMPFile();
		ampFile.createNewFile();
		ZipManager.zip(getAMPFolder(), ampFile, false);
		if (doClean) {
			FileHelper.deleteFile(getWorkingFolder());
		}
		IFile ampIFile = getAMPIFile();
		return ampIFile;
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
		File modulePropertiesfile = new File(getAMPFolder().getAbsolutePath() + File.separator + "module.properties");
		moduleProperties.store(new FileOutputStream(modulePropertiesfile), alfrescoModuleComment);
	}

	public String getWorkingdir() {
		return workingdir;
	}

	private String getAMPPath() {
		// TODO : get technoV from extension point tree
		return getTechnoPath() + File.separator + "module." + moduleProperties.getProperty("module.id") + ".amp";
	}

	private String getTechnoPath() {
		return File.separator + ".." + File.separator + ".." + File.separator + technoV;
	}

	private File getAMPFile() {
		File container = new File(getWorkingdir() + getTechnoPath());
		container.mkdirs();
		return new File(getWorkingdir() + getAMPPath());
	}

	private IFile getAMPIFile() {
		return IFileHelper.getIFile(this.IworkingDir.toString().replaceFirst("[^/]*/", "/") + getAMPPath());
	}

	private File getWorkingFolder() {
		return new File(getWorkingdir());
	}
	/**
	 * Use to map generated filePath to corresponding AMP directory
	 * 
	 * @return
	 */
	private Map<String, File> createAMPSkelleton() {
		File ampRoot = getWorkingFolder();

		Map<String, File> mapper = new HashMap<String, File>();
		mapper.put("/WEB-INF/classes/", createAndRegisterDir(ampRoot, "/config"));
		mapper.put("/config/", createAndRegisterDir(ampRoot, "/config"));
		mapper.put("/lib/", createAndRegisterDir(ampRoot, "/lib"));
		mapper.put("/licences/", createAndRegisterDir(ampRoot, "/licenses"));
		mapper.put("/jsp/", createAndRegisterDir(ampRoot, "/web/jsp"));
		mapper.put("/css/", createAndRegisterDir(ampRoot, "/web/css"));
		mapper.put("/images/", createAndRegisterDir(ampRoot, "/web/images"));
		mapper.put("/scripts/", createAndRegisterDir(ampRoot, "/web/scripts"));
		return mapper;
	}

	private File createAndRegisterDir(File ampRoot, String p) {
		File dir = new File(getAMPFolder().getAbsolutePath() + p.replace("/", File.separator));
		dir.mkdirs();
		return dir;
	}

	private File getAMPFolder() {
		return new File(getWorkingdir() + File.separator + "AMP");
	}

}
