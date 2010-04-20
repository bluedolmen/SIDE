package com.bluexml.side.util.generator.packager;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.libs.zip.ZipManager;

public class ZipPackager extends AbstractPackager {
	String fileName;

	public ZipPackager(IFolder folder, IFolder ItechnoVPath, String fileName) {
		super(folder, ItechnoVPath);
		this.fileName = fileName;
	}

	@Override
	public IFile buildPackage() throws Exception {
		System.out.println("Package :");
		System.out.println("** Folder to package " + this.getFolderToPackage());
		System.out.println("** Package Path :" + this.getPackagePath());
		System.out.println("** Package File :" + this.getPackageFile());
		File packageFile = getPackageFile();
		packageFile.createNewFile();
		ZipManager.zip(getFolderToPackage(), packageFile, false);
		IFile packageIFile = getPackageIFile();
		return packageIFile;
	}

	@Override
	protected File getFolderToPackage() {
		return new File(getWorkingdir());
	}

	@Override
	protected String getPackageFileName() {
		return fileName;
	}

}
